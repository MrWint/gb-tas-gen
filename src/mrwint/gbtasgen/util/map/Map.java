package mrwint.gbtasgen.util.map;

public abstract class Map {

	public abstract int getStepWidth();

	public abstract int getStepHeight();

	public abstract void printMap();

	public abstract boolean isInversePassable(int nx, int ny, int x, int y, int i);

	public abstract boolean isPassable(int nx, int ny, int x, int y, int dir);

	public static void printDistMap(int[][] dist) {
		for(int y=0;y<dist[0].length;y++) {
			for(int x=0;x<dist.length;x++)
				System.out.print("#0123456789ABCDEF".charAt(Math.min(dist[x][y]+1,16)));
			System.out.println();
		}
		System.out.println();
	}
	
	public interface MapFactory {
		Map create(boolean blockAllWarps, boolean ignoreTrainers);
	}
}
