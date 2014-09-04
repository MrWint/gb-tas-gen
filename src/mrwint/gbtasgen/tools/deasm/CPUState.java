package mrwint.gbtasgen.tools.deasm;

import java.util.Arrays;

import mrwint.gbtasgen.tools.deasm.specialCallHandler.SpecialCallHandler;


public class CPUState {
	
	public static final int B = 0;
	public static final int C = 1;
	public static final int D = 2;
	public static final int E = 3;
	public static final int H = 4;
	public static final int L = 5;
	public static final int F = 6; // also (HL)
	public static final int A = 7;
	public static String[] registerNames = {"B","C","D","E","H","L","F","A"};
	public static final int NUM_REGISTERS = 8;
	
	public static final int CARRYMASK = 0x10;
	public static final int HALFCARRYMASK = 0x20;
	public static final int NEGATIVEMASK = 0x40;
	public static final int ZEROMASK = 0x80;

	
	public int r[];
	public int rMask[];
	public int rLoadedFromAddress[];
	//public Stack<Integer> stack;
	public int loadedBank;
	
	public CPUState() {
		invalidateAll();
	}
	
	public CPUState(CPUState o) {
		this();
		for(int i=0;i<NUM_REGISTERS;i++) {
			r[i] = o.r[i];
			rMask[i] = o.rMask[i];
			rLoadedFromAddress[i] = o.rLoadedFromAddress[i];
		}
		//stack = (Stack<Integer>)o.stack.clone();
		loadedBank = o.loadedBank;
	}
	
	public static final int UNKNOWN     = -1;
	public static final int KNOWN_FALSE = 0;
	public static final int KNOWN_TRUE  = 1;
	
	public int getCarry() {
		if((rMask[F] & CARRYMASK) == 0)
			return -1;
		if((r[F] & CARRYMASK) == 0)
			return 0;
		return 1;
	}
	
	public int getZero() {
		if((rMask[F] & ZEROMASK) == 0)
			return -1;
		if((r[F] & ZEROMASK) == 0)
			return 0;
		return 1;
	}

	public void setCarry(boolean value) {
		rMask[F] |= CARRYMASK;
		if(value)
			r[F] |= CARRYMASK;
		else
			r[F] &= (~CARRYMASK);
	}
	
	public void invalidateCarry() {
		rMask[F] &= (~CARRYMASK);
	}
	
	public void setZero(boolean value) {
		rMask[F] |= ZEROMASK;
		if(value)
			r[F] |= ZEROMASK;
		else
			r[F] &= (~ZEROMASK);
	}

	public void invalidateZero() {
		rMask[F] &= (~ZEROMASK);
	}
	
	public void prepareJump(int opCodeValue, int opData) {
		// invalidate data if conditional jump is not fulfilled in the current state
		if(Arrays.asList(new Integer[]{0x20,0xC0,0xC2,0xC4}).contains(opCodeValue)) // jr ret jp call: nz
			if(getZero() != KNOWN_FALSE)
				invalidateAll();
		if(Arrays.asList(new Integer[]{0x28,0xC8,0xCA,0xCC}).contains(opCodeValue)) // jr ret jp call: z
			if(getZero() != KNOWN_TRUE)
				invalidateAll();
		if(Arrays.asList(new Integer[]{0x30,0xD0,0xD2,0xD4}).contains(opCodeValue)) // jr ret jp call: nc
			if(getCarry() != KNOWN_FALSE)
				invalidateAll();
		if(Arrays.asList(new Integer[]{0x38,0xD8,0xDA,0xDC}).contains(opCodeValue)) // jr ret jp call: c
			if(getCarry() != KNOWN_TRUE)
				invalidateAll();
	}

	public void invalidateAll() {
		invalidateRegisters();
		loadedBank = -1;
	}

	public void invalidateRegisters() {
		r = new int[NUM_REGISTERS];
		rMask = new int[NUM_REGISTERS];
		rLoadedFromAddress = new int[NUM_REGISTERS];
		for(int i=0;i<8;i++)
			rLoadedFromAddress[i] = -1;
		//stack = new Stack<Integer>();
	}
	
	// true -> veto continue
	public boolean prepareForgoJump(int opCodeValue, int currentAddress, int jumpAddress, SpecialCallHandler sch) {
		// jumps, calls, ret, rst
		// invalidate data if conditional jump may have been fulfilled in the current state
		if(Arrays.asList(new Integer[]{0x20,0xC0,0xC2,0xC4}).contains(opCodeValue)) // jr ret jp call: nz
			if(getZero() != KNOWN_TRUE)
				if(opCodeValue != 0xC4 || !sch.handleAfterCall(currentAddress, jumpAddress, this))
					invalidateAll();
		if(Arrays.asList(new Integer[]{0x28,0xC8,0xCA,0xCC}).contains(opCodeValue)) // jr ret jp call: z
			if(getZero() != KNOWN_FALSE)
				if(opCodeValue != 0xCC || !sch.handleAfterCall(currentAddress, jumpAddress, this))
					invalidateAll();
		if(Arrays.asList(new Integer[]{0x30,0xD0,0xD2,0xD4}).contains(opCodeValue)) // jr ret jp call: nc
			if(getCarry() != KNOWN_TRUE)
				if(opCodeValue != 0xD4 || !sch.handleAfterCall(currentAddress, jumpAddress, this))
					invalidateAll();
		if(Arrays.asList(new Integer[]{0x38,0xD8,0xDA,0xDC}).contains(opCodeValue)) // jr ret jp call: c
			if(getCarry() != KNOWN_FALSE)
				if(opCodeValue != 0xDC || !sch.handleAfterCall(currentAddress, jumpAddress, this))
					invalidateAll();
		if(Arrays.asList(new Integer[]{	0xc7,0xcf,0xd7,0xdf,0xe7,0xef,0xf7,0xff, // rst
										0xC9, 0xD9, // ret(i)
										0xCD}).contains(opCodeValue)) // call
			if(!sch.handleAfterCall(currentAddress, jumpAddress, this))
				invalidateAll();
		
		return sch.vetoContinueAfterCall(currentAddress, jumpAddress);
	}

	public void prepareContinue(int opCodeValue, int opData, int opAddress) {
		
		// 0x40 - 0x7f range
		if(opCodeValue >= 0x40 && opCodeValue < 0x80) {
			int rTo = (opCodeValue-0x40)/8;
			int rFrom = (opCodeValue-0x40)%8;
			if(rTo != 6) { // exclude (hl)
				r[rTo] = r[rFrom];
				rMask[rTo] = rMask[rFrom];
				rLoadedFromAddress[rTo] = rLoadedFromAddress[rFrom];
				if(rFrom == 6) // loading from (hl), value unknown
					rMask[rTo] = 0;
			} else {
				if(opCodeValue == 0x76) // halt
					return;
				if((rMask[H] & rMask[L]) == 0xFF) {
					int add = (r[H] << 8) | r[L];
					handleMemWrite(add,r[rFrom],rMask[rFrom]);
				}
			}
			return;
		}
		
		// 0x80 - 0xbf range
		if(opCodeValue >= 0x80 && opCodeValue < 0xbf) {
			int op = (opCodeValue-0x80)/8;
			int rFrom = (opCodeValue-0x80)%8;
			int val = r[rFrom];
			int mask = rMask[rFrom];
			if(rFrom == 6)
				mask = 0; // loading from (hl), value unknown
			if(opCodeValue == 0xAF || opCodeValue == 0x97) { // xor A; sub A
				rMask[A] = 0xFF;
				r[A] = 0;
				rLoadedFromAddress[A] = -1;
				setZero(true);
				setCarry(false);
			} else
				doAccumulatorCalculation(op, val, mask);
			return;
		}
		
		// range 0x00 to 0x3f
		if(opCodeValue >= 0x0 && opCodeValue < 0x40) {
			if((opCodeValue & 7) == 4) { // inc
				int rTo = opCodeValue / 8;
				if(rTo == 6) // skip (hl)
					return;
				rLoadedFromAddress[rTo] = -1;
				if(rMask[rTo] == 0xFF) {
					r[rTo] = (r[rTo]+1) & 0xFF;
					setZero(r[rTo] == 0);
				} else {
					rMask[rTo] = 0;
					invalidateZero();
				}
				return;
			}
			if((opCodeValue & 7) == 5) { // dec
				int rTo = opCodeValue / 8;
				if(rTo == 6) // skip (hl)
					return;
				rLoadedFromAddress[rTo] = -1;
				if(rMask[rTo] == 0xFF) {
					r[rTo] = (r[rTo]+0xFF) & 0xFF;
					setZero(r[rTo] == 0);
				} else {
					rMask[rTo] = 0;
					invalidateZero();
				}
				return;
			}
			if((opCodeValue & 7) == 6) { // ld x,d8
				int rTo = opCodeValue / 8;
				if(rTo == 6) { // skip (hl)
					if((rMask[H] & rMask[L]) == 0xFF) {
						int add = (r[H] << 8) | r[L];
						handleMemWrite(add,opData & 0xFF,0xFF);
					}
					return;
				}
				r[rTo] = opData & 0xFF;
				rMask[rTo] = 0xFF;
				rLoadedFromAddress[rTo] = opAddress;
				return;
			}
			if((opCodeValue & 7) == 3) { // 16-bit inc/dec
				int rTo = 2*(opCodeValue / 16);
				if(rTo == 6) // skip (sp)
					return;
				incDec16bit(rTo, (opCodeValue%16)/8 == 0);
				return;
			}
			if((opCodeValue & 0xF) == 1) { // 16-bit ld
				int rTo = 2*(opCodeValue / 16);
				if(rTo == 6) // ld sp,d16
					return;
				r[rTo] = opData / 0x100;
				r[rTo+1] = opData % 0x100;
				rMask[rTo] = rMask[rTo+1] = 0xFF;
				rLoadedFromAddress[rTo] = rLoadedFromAddress[rTo+1] = opAddress;
				return;
			}
			if((opCodeValue & 0xF) == 9) { // 16-bit add
				rLoadedFromAddress[H] = rLoadedFromAddress[L] = -1;
				int rFrom = 2*(opCodeValue / 16);
				if(rFrom == 6) { // add hl,sp
					rMask[H] = rMask[L] = 0;
					invalidateCarry();
					return;
				}
				if((rMask[rFrom] & rMask[rFrom+1] & rMask[H] & rMask[L]) == 0xFF) {
					r[L] += r[rFrom+1];
					if(r[L] >= 0x100) {
						r[L] -= 0x100;
						r[H]++;
					}
					r[H] += r[rFrom];
					setCarry(r[H] >= 0x100);
					r[H] &= 0xFF;
				} else {
					rMask[H] = rMask[L] = 0;
					invalidateCarry();
				}
				return;
			}
			if((opCodeValue & 7) == 2) { // ld ()
				int rC = 2*(opCodeValue / 16);
				if((opCodeValue & 0xF) == 0xA) { // ld a,()
					rMask[A] = 0;
					rLoadedFromAddress[A] = -1;
					if(rC >= 2)
						incDec16bit(H,rC==2);
				} else { // ld (),a
					int rTo = Math.max(rC, 2);
					if((rMask[rTo] & rMask[rTo+1]) == 0xFF) {
						int add = (r[rTo] << 8) | r[rTo+1];
						handleMemWrite(add,r[A],rMask[A]);
					}
					if(rC >= 2)
						incDec16bit(H,rC==2);
				}
				return;
			}
			if((opCodeValue & 7) == 7) { // misc stuff
				if(opCodeValue/16 == 3) // scf/ccf
					setCarry((opCodeValue%16) == 7);
				if(opCodeValue == 0x2F) { // cpl
					r[A] = (~r[A])&0xFF;
					rLoadedFromAddress[A] = -1;
				}
				if(opCodeValue == 0x27) { // daa
					rMask[A] = 0; // relies on H flag that is not tracked
					rLoadedFromAddress[A] = -1;
					invalidateCarry();
					invalidateZero();
				}
				if(opCodeValue == 0x07) { // rlca
					r[A] = rotateLeft(r[A]);
					rMask[A] = rotateLeft(rMask[A]);
					rLoadedFromAddress[A] = -1;
					if((rMask[A] & 1) == 1)
						setCarry((r[A] & 1) == 1);
					else
						invalidateCarry();
				}
				if(opCodeValue == 0x0F) { // rrca
					if((rMask[A] & 1) == 1)
						setCarry((r[A] & 1) == 1);
					else
						invalidateCarry();
					r[A] = rotateRight(r[A]);
					rMask[A] = rotateRight(rMask[A]);
					rLoadedFromAddress[A] = -1;
				}
				if(opCodeValue == 0x17) { // rla
					int carry = getCarry();
					if((rMask[A]>>7) == 1)
						setCarry((r[A]>>7) == 1);
					else
						invalidateCarry();
					r[A] = shiftLeft(r[A],carry&1);
					rMask[A] = shiftLeft(rMask[A],carry!=-1 ? 1 : 0);
					rLoadedFromAddress[A] = -1;
				}
				if(opCodeValue == 0x1f) { // rra
					int carry = getCarry();
					if((rMask[A]&1) == 1)
						setCarry((r[A]&1) == 1);
					else
						invalidateCarry();
					r[A] = shiftRight(r[A],carry&1);
					rMask[A] = shiftRight(rMask[A],carry!=-1 ? 1 : 0);
					rLoadedFromAddress[A] = -1;
				}
				return;
			}
			if((opCodeValue & 7) == 0) // jrs;nop;stop;ld (a16),sp
				return;
			System.err.println("00-3f");
			return;
		}
		
		// range 0xc0 - 0xff
		if(opCodeValue >= 0xc0 && opCodeValue < 0x100) {
			if((opCodeValue & 7) == 6) // calculations with d8
				doAccumulatorCalculation((opCodeValue-0xc0)/8,opData&0xFF,0xFF);
			if(opCodeValue == 0xCB)
				handleCB(opData & 0xFF);
			if(opCodeValue == 0xFA) { // ld a, (a16)
				rMask[A] = 0;
				rLoadedFromAddress[A] = -1;
			}
			if(opCodeValue == 0xEA) { // ld (a16), a
				handleMemWrite(opData & 0xFFFF, r[A], rMask[A]);
			}
			if(opCodeValue == 0xF8) { // ld hl, sp+r8
				rMask[H] = rMask[L] = 0;
				rLoadedFromAddress[H] = rLoadedFromAddress[L] = -1;
				invalidateCarry();
			}
			if(opCodeValue == 0xF2) { // ld a, (c)
				rMask[A] = 0;
				rLoadedFromAddress[A] = -1;
			}
			if(opCodeValue == 0xE2) { // ld (c), a
				if(rMask[C] == 0xFF)
					handleMemWrite(0xFF00 | r[C], r[A], rMask[A]);
			}
			if((opCodeValue & 0xF) == 1) {
				int rC = (opCodeValue-0xc0)/8;
				rMask[rC] = rMask[rC+1] = 0;
				rLoadedFromAddress[rC] = rLoadedFromAddress[rC+1] = -1;
			}
			if(opCodeValue == 0xF0) { // ldh a, (a8)
				rMask[A] = 0;
				rLoadedFromAddress[A] = -1;
			}
			return;
		}
	}

	private void handleMemWrite(int add, int val, int mask) {
		if(add >= 0x2000 && add < 0x4000) {
			if(mask == 0xFF)
				loadedBank = val;
			else
				loadedBank = -1;
		}
	}

	private void handleCB(int opData) {
		int rTo = opData%8;
		if(opData >= 0xc0) { // set bit
			opData -= 0xc0;
			int bit = opData/8;
			if(rTo == 6) // skip (hl)
				return;
			r[rTo] |= 1<<bit;
			rMask[rTo] |= 1<<bit;
			rLoadedFromAddress[rTo] = -1;
			return;
		}
		if(opData >= 0x80) { // reset bit
			opData -= 0x80;
			int bit = opData/8;
			if(rTo == 6) // skip (hl)
				return;
			r[rTo] &= ~(1<<bit);
			rMask[rTo] |= 1<<bit;
			rLoadedFromAddress[rTo] = -1;
			return;
		}
		if(opData >= 0x40) { // check bit
			opData -= 0x40;
			int bit = opData/8;
			if(rTo == 6) { // skip (hl)
				invalidateZero();
				return;
			}
			if((rMask[rTo] & (1<<bit)) != 0)
				setZero((r[rTo] & (1<<bit)) == 0);
			else
				invalidateZero();
			return;
		}
		if(rTo == 6) { // skip (hl)
			invalidateZero();
			if(opData < 0x30 || opData >= 0x38) // swaps
				invalidateCarry();
			else
				setCarry(false);
			return;
		}
		switch (opData/8) {
		case 0: // rlc
			r[rTo] = rotateLeft(r[rTo]);
			rMask[rTo] = rotateLeft(rMask[rTo]);
			rLoadedFromAddress[rTo] = -1;
			if((rMask[rTo] & 1) == 1)
				setCarry((r[rTo] & 1) == 1);
			else
				invalidateCarry();
			updateZero(rTo);
			return;
		case 1: // rrc
			if((rMask[rTo] & 1) == 1)
				setCarry((r[rTo] & 1) == 1);
			else
				invalidateCarry();
			r[rTo] = rotateRight(r[rTo]);
			rMask[rTo] = rotateRight(rMask[rTo]);
			rLoadedFromAddress[rTo] = -1;
			updateZero(rTo);
			return;
		case 2: // rl
			int carryRL = getCarry();
			if((rMask[rTo]>>7) == 1)
				setCarry((r[rTo]>>7) == 1);
			else
				invalidateCarry();
			r[rTo] = shiftLeft(r[rTo],carryRL&1);
			rMask[rTo] = shiftLeft(rMask[rTo],carryRL!=-1 ? 1 : 0);
			rLoadedFromAddress[rTo] = -1;
			updateZero(rTo);
			return;
		case 3: // rr
			int carryRR = getCarry();
			if((rMask[rTo]&1) == 1)
				setCarry((r[rTo]&1) == 1);
			else
				invalidateCarry();
			r[rTo] = shiftRight(r[rTo],carryRR&1);
			rMask[rTo] = shiftRight(rMask[rTo],carryRR!=-1 ? 1 : 0);
			rLoadedFromAddress[rTo] = -1;
			updateZero(rTo);
			return;
		case 4: // sla
			if((rMask[rTo]>>7) == 1)
				setCarry((r[rTo]>>7) == 1);
			else
				invalidateCarry();
			r[rTo] = shiftLeft(r[rTo],0);
			rMask[rTo] = shiftLeft(rMask[rTo],1);
			rLoadedFromAddress[rTo] = -1;
			updateZero(rTo);
			return;
		case 5: // sra
			if((rMask[rTo]&1) == 1)
				setCarry((r[rTo]&1) == 1);
			else
				invalidateCarry();
			r[rTo] = shiftRight(r[rTo],(r[rTo]>>7)&1);
			rMask[rTo] = shiftRight(rMask[rTo],(rMask[rTo]>>7)&1);
			rLoadedFromAddress[rTo] = -1;
			updateZero(rTo);
			return;
		case 6: // swap
			r[rTo] = ((r[rTo]&0x0F) << 4) | ((r[rTo]&0xF0) >> 4);
			rMask[rTo] = ((rMask[rTo]&0x0F) << 4) | ((rMask[rTo]&0xF0) >> 4);
			rLoadedFromAddress[rTo] = -1;
			updateZero(rTo);
			return;
		case 7: // srl
			if((rMask[rTo]&1) == 1)
				setCarry((r[rTo]&1) == 1);
			else
				invalidateCarry();
			r[rTo] = shiftRight(r[rTo],0);
			rMask[rTo] = shiftRight(rMask[rTo],1);
			rLoadedFromAddress[rTo] = -1;
			updateZero(rTo);
			return;
		}
	}
	
	private void updateZero(int rTo) {
		if(rMask[rTo] != 0xFF && (r[rTo] & rMask[rTo]) == 0) // no full information
			invalidateZero();
		else
			setZero(r[rTo] == 0);
	}

	private void doAccumulatorCalculation(int op, int val, int mask) {
		switch(op) {
		case 0: // add
			rLoadedFromAddress[A] = -1;
			if(rMask[A] != 0xFF || mask != 0xFF) { // no full information
				rMask[A] = 0;
				invalidateCarry();
				invalidateZero();
			}
			else {
				r[A] += val;
				setCarry(r[A] >= 0x100);
				r[A] %= 0x100;
				setZero(r[A] == 0);
			}
			return;
		case 1: // adc
			rLoadedFromAddress[A] = -1;
			if(rMask[A] != 0xFF || mask != 0xFF || getCarry() == -1) { // no full information
				rMask[A] = 0;
				invalidateCarry();
				invalidateZero();
			}
			else {
				r[A] += val + getCarry();
				setCarry(r[A] >= 0x100);
				r[A] %= 0x100;
				setZero(r[A] == 0);
			}
			return;
		case 2: // sub
			rLoadedFromAddress[A] = -1;
			if(rMask[A] != 0xFF || mask != 0xFF) { // no full information
				rMask[A] = 0;
				invalidateCarry();
				invalidateZero();
			}
			else {
				r[A] -= val;
				setCarry(r[A] < 0x0);
				while(r[A] < 0x0)
					r[A] += 0x100;
				setZero(r[A] == 0);
			}
			return;
		case 3: // sbc
			rLoadedFromAddress[A] = -1;
			if(rMask[A] != 0xFF || mask != 0xFF || getCarry() == -1) { // no full information
				rMask[A] = 0;
				invalidateCarry();
				invalidateZero();
			}
			else {
				r[A] -= val + getCarry();
				setCarry(r[A] < 0x0);
				while(r[A] < 0x0)
					r[A] += 0x100;
				setZero(r[A] == 0);
			}
			return;
		case 4: // and
			rLoadedFromAddress[A] = -1;
			rMask[A] = (mask & (~val)) | (rMask[A] & (~r[A])) | (mask & rMask[A]);
			r[A] &= val;
			setCarry(false);
			updateZero(A);
			return;
		case 5: // xor
			rLoadedFromAddress[A] = -1;
			rMask[A] = (mask & rMask[A]);
			r[A] ^= val;
			setCarry(false);
			updateZero(A);
			return;
		case 6: // or
			rLoadedFromAddress[A] = -1;
			rMask[A] = (mask & val) | (rMask[A] & r[A]) | (mask & rMask[A]);
			r[A] |= val;
			setCarry(false);
			updateZero(A);
			return;
		case 7: // cp
			if(rMask[A] != 0xFF || mask != 0xFF) { // no full information
				invalidateCarry();
				invalidateZero();
			}
			else {
				setCarry(r[A] < val);
				setZero(r[A] == val);
			}
			return;
		}
	}
	
	private int rotateRight(int val) {
		return shiftRight(val,val&1);
	}
	
	private int shiftRight(int val, int i) {
		return (((i&1)<<7) | (val >> 1)) & 0xFF;
	}

	private int rotateLeft(int val) {
		return shiftLeft(val,(val>>7)&1);
	}
	
	private int shiftLeft(int val, int i) {
		return ((i&1) | (val << 1)) & 0xFF;
	}

	private void incDec16bit(int rTo, boolean inc) {
		rLoadedFromAddress[rTo] = rLoadedFromAddress[rTo+1] = -1;
		if(rMask[rTo] == 0xFF && rMask[rTo+1] == 0xFF) {
			if(inc)
				r[rTo+1]++;
			else
				r[rTo+1]--;
			if(r[rTo+1] >= 0x100) {
				r[rTo+1] -= 0x100;
				r[rTo] = (r[rTo]+1) & 0xFF;
			}
			if(r[rTo+1] < 0x0) {
				r[rTo+1] += 0x100;
				r[rTo] = (r[rTo]+0xFF) & 0xFF;
			}
		} else {
			rMask[rTo] = rMask[rTo+1] = 0;
		}
	}
	
	public String getCPUStateInfo() {
		String ret = "";
		for(int i=0;i<8;i++)
			if(rMask[i] == 0xFF)
				ret += " " + registerNames[i]+" = "+Integer.toHexString(r[i]);
		if(getCarry() != -1)
			ret += " carry = "+getCarry();
		if(getZero() != -1)
			ret += " zero = "+getZero();
		if(loadedBank != -1)
			ret += " bank = "+Integer.toHexString(loadedBank);
		return ret;
	}
}
