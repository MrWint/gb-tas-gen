package mrwint.gbtasgen.segment.pokemon.gen1.glitchless;

import mrwint.gbtasgen.metric.pokemon.gen1.CheckDisableEffectMisses;
import mrwint.gbtasgen.metric.pokemon.gen1.CheckPoisonEffectMisses;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.pokemon.gen1.OverworldInteract;
import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.pokemon.TextSegment;
import mrwint.gbtasgen.segment.pokemon.WalkToSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EndFightSegment;
import mrwint.gbtasgen.segment.pokemon.fight.InitFightSegment;
import mrwint.gbtasgen.segment.pokemon.fight.KillEnemyMonSegment;
import mrwint.gbtasgen.segment.pokemon.fight.KillEnemyMonSegment.EnemyMoveDesc;
import mrwint.gbtasgen.segment.pokemon.fight.NewEnemyMonSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.UseBikeSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.segment.util.SkipTextsSegment;

public class SafariSquirtle extends SeqSegment {

	@Override
	public void execute() {
    {
      seq(Segment.repress(Move.START));
      seq(Segment.scrollA(1)); // mon
      seqSkipInput(1);
      seq(Segment.scrollAF(1)); // spearow
      seq(Segment.repress(Move.A)); // fly
      seq(Segment.scrollA(1)); // celadon
    }
    seqSkipInput(1);
    seq(new UseBikeSegment(1, 0));
    seq(new WalkToSegment(35, 30)); // bush
    seq(new WalkToSegment(35, 31)); // bush
    {
      seq(Segment.repress(Move.START));
      seq(Segment.scrollA(-1)); // mon
      seqSkipInput(1);
      seq(Segment.scrollAF(1)); // sandshrew
      seq(Segment.repress(Move.A)); // cut
      seqButton(Move.B); // hacked away (no text scroll)?
    }
    seq(new WalkToSegment(12, 27)); // enter gym
    seq(new WalkToSegment(1, 4)); // bush
    {
      seq(Segment.repress(Move.START));
      seq(Segment.repress(Move.A)); // mon
      seq(Segment.repress(Move.A)); // sandshrew
      seq(Segment.repress(Move.A)); // cut
      seqButton(Move.B); // hacked away (no text scroll)?
    }
    seq(new WalkToSegment(3, 4)); // engage
    seq(new InitFightSegment(2)); // start fight
    {
      KillEnemyMonSegment kems = new KillEnemyMonSegment();
      kems.attackCount[2][0] = 1; // ice beam
      seq(kems); // exeggcute
    }
    seq(new EndFightSegment(1)); // player defeated enemy
    seq(new WalkToSegment(4, 3, false)); // engage erika
    seqMove(new OverworldInteract(1)); // engage erika
		seq(new InitFightSegment(13)); // start fight
		{
			KillEnemyMonSegment kems = new KillEnemyMonSegment();
      kems.attackCount[2][1] = 1; // ice beam crit
      kems.numExpGainers = 2; // L34;
			seq(kems); // victreebel
		}
    seq(NewEnemyMonSegment.any()); // next mon
    {
      KillEnemyMonSegment kems = new KillEnemyMonSegment();
      kems.attackCount[2][0] = 1; // ice beam
      seq(kems); // tangela
    }
    seq(NewEnemyMonSegment.any()); // next mon
    {
      KillEnemyMonSegment kems = new KillEnemyMonSegment();
      kems.attackCount[2][1] = 1; // ice beam crit
      seq(kems); // vileplume
    }
		seq(new EndFightSegment(3)); // player defeated enemy
		seq(new SkipTextsSegment(11)); // after battle texts
    seq(new WalkToSegment(5, 5)); // bush
    seq(new WalkToSegment(5, 6)); // bush
    {
      seq(Segment.repress(Move.START));
      seq(Segment.scrollA(1)); // mon
      seqSkipInput(1);
      seq(Segment.scrollAF(-1)); // sandshrew
      seq(Segment.repress(Move.A)); // cut
      seqButton(Move.B); // hacked away (no text scroll)?
    }
    seq(new WalkToSegment(5, 18, false)); // leave gym

	  save("sa1");
	  load("sa1");
	  {
      seq(new UseBikeSegment(1, 0));
      seq(new WalkToSegment(12, 31, false)); // ledge
      seq(new WalkToSegment(35, 33)); // bush
      {
        seq(Segment.repress(Move.START));
        seq(Segment.scrollA(-1)); // mon
        seq(Segment.repress(Move.A)); // sandshrew
        seq(Segment.repress(Move.A)); // cut
        seqButton(Move.B); // hacked away (no text scroll)?
      }
	  }
    seq(new WalkToSegment(-1, 18)); // leave celadon
    seq(new WalkToSegment(27, 10)); // snorlax
    {
      seq(Segment.repress(Move.START));
      seq(Segment.scrollA(1)); // items
      seq(Segment.scrollFastA(14)); // poke flute
      seq(Segment.repress(Move.A)); // use
    }
    seq(new SkipTextsSegment(1)); // playing flute
    seq(new SkipTextsSegment(2)); // snorlax attacks
    seq(new SkipTextsSegment(1)); // wild snorlax
    seq(new TextSegment()); // go
    seqButton(Move.DOWN | Move.RIGHT); // run
    seqButton(Move.A); // run
    seq(new SkipTextsSegment(1)); // escaped
    seq(new WalkToSegment(23, 10, false)); // enter house
    seq(new WalkToSegment(-1, 8, false)); // leave house
    seq(new WalkToSegment(11, 18)); // enter slope
    seq(new WalkToSegment(18, 7)); // evade grass
    seq(new WalkToSegment(13, 143, false)); // leave slope
    seq(new WalkToSegment(34, 8, false)); // enter house
    seq(new WalkToSegment(8, 4, false)); // leave house
    seq(new UseBikeSegment(2, 0));
    seq(new WalkToSegment(50, 8)); // enter fuchsia

    save("sa2");
    load("sa2");

    seq(new WalkToSegment(18, 19, false)); // bush
    {
      seq(Segment.repress(Move.START));
      seq(Segment.scrollA(-1)); // mon
      seqSkipInput(1);
      seq(Segment.scrollAF(-1)); // sandshrew
      seq(Segment.repress(Move.A)); // cut
      seqButton(Move.B); // hacked away (no text scroll)?
    }
    seq(new WalkToSegment(16, 13)); // bush
    seq(new WalkToSegment(16, 12)); // bush
    {
      seq(Segment.repress(Move.START));
      seq(Segment.repress(Move.A)); // mon
      seq(Segment.repress(Move.A)); // sandshrew
      seq(Segment.repress(Move.A)); // cut
      seqButton(Move.B); // hacked away (no text scroll)?
    }

    seq(new WalkToSegment(18, 3)); // enter safari house
    seq(new WalkToSegment(3, 2)); // go pay
    seq(new SkipTextsSegment(4)); // welcome to safari
    seq(new SkipTextsSegment(1, true)); // yes, go on safari
    seq(new SkipTextsSegment(7)); // welcome to safari
    seq(new UseBikeSegment(1, 0));
    seq(new WalkToSegment(30, 11, false)); // enter east
    seq(new WalkToSegment(-1, 5, false)); // enter north
    seq(new WalkToSegment(3, 36, false)); // enter west
    seq(new WalkToSegment(19, 5)); // gold teeth
    seq(new WalkToSegment(19, 6)); // gold teeth
    seqMove(new OverworldInteract(4)); // pick up gold teeth
    seq(new TextSegment());
    seq(new WalkToSegment(3, 3)); // enter surf house
    seq(new WalkToSegment(3, 5)); // surf guy
    seq(new WalkToSegment(3, 4)); // surf guy
    seqMove(new OverworldInteract(1)); // surf guy
    seq(new SkipTextsSegment(8)); // get surf
    seq(new WalkToSegment(3, 8, false)); // leave surf house
    {
      seq(Segment.repress(Move.START));
      seq(Segment.repress(Move.A)); // items
      seq(Segment.scrollFastAF(1)); // escape rope
      seq(Segment.repress(Move.A)); // use
    }

    save("sa3");
    load("sa3");

    seqSkipInput(2);
    {
      seq(Segment.repress(Move.START));
      seq(Segment.repress(Move.A)); // items
      seq(Segment.scrollFastA(16)); // HM03
      seq(Segment.repress(Move.A)); // use
      seq(new SkipTextsSegment(2)); // booted up TM, contains xyz
      seq(new SkipTextsSegment(1, true)); // learn
      seqSkipInput(1);
      seq(Segment.scrollAF(1)); // wartortle
      seq(new SkipTextsSegment(5)); // learned HM
      seq(new SkipTextsSegment(1, true)); // yes, overwrite
      seq(new TextSegment(Move.B));
      seq(Segment.scrollAF(1)); // bite
      seq(new TextSegment(Move.A, false)); // 1,2 and
      seqButton(Move.B); // skip 30f
      seqButton(Move.B);
      seq(new TextSegment()); // poof
      seqButton(Move.A); // skip 30f
      seqButton(Move.B);
      seq(new SkipTextsSegment(2)); // learned HM
      seq(new SkipTextsSegment(1, true)); // learned HM
      seq(Segment.repress(Move.B)); // exit item menu

      seq(Segment.scrollA(-1)); // mon
      seqSkipInput(1);
      seq(Segment.scrollAF(1)); // spearow
      seq(Segment.repress(Move.A)); // fly
      seq(Segment.scrollA(1)); // fuchsia
    }
    seqSkipInput(1);
    seq(new WalkToSegment(5, 27)); // enter gym
    seq(new WalkToSegment(7, 8, false)); // engage
    seqMove(new OverworldInteract(3));
    seq(new InitFightSegment(2)); // start fight
    {
      KillEnemyMonSegment kems = new KillEnemyMonSegment();
      kems.attackCount[1][1] = 1; // surf crit
      seq(kems); // drowzee
    }
    seq(NewEnemyMonSegment.any()); // next mon
    {
      KillEnemyMonSegment kems = new KillEnemyMonSegment();
      kems.attackCount[1][1] = 1; // surf crit
      kems.numExpGainers = 2; // L35
      seq(kems); // drowzee
    }
    seq(NewEnemyMonSegment.any()); // next mon
    {
      KillEnemyMonSegment kems = new KillEnemyMonSegment();
      kems.enemyMoveDesc = new EnemyMoveDesc[]{EnemyMoveDesc.missWith(new CheckDisableEffectMisses(), 50)}; // disable
      kems.attackCount[1][1] = 1; // surf crit
      seq(kems); // kadabra
    }
    seq(NewEnemyMonSegment.any()); // next mon
    {
      KillEnemyMonSegment kems = new KillEnemyMonSegment();
      kems.attackCount[1][1] = 1; // surf crit
      seq(kems); // drowzee
    }
    seq(new EndFightSegment(1)); // player defeated enemy
    seq(new WalkToSegment(1, 7)); // engage


    save("sa4");
    load("sa4");

    seq(new InitFightSegment(3)); // start fight
    {
      KillEnemyMonSegment kems = new KillEnemyMonSegment();
      kems.enemyMoveDesc = new EnemyMoveDesc[]{EnemyMoveDesc.missWith(new CheckPoisonEffectMisses(), 139)}; // poison gas
      kems.attackCount[0][1] = 1; // mega kick crit
//      kems.attackCount[3][1] = 2; // bubblebeam crit
      seq(kems); // drowzee
    }
    seq(NewEnemyMonSegment.any()); // next mon
    {
      KillEnemyMonSegment kems = new KillEnemyMonSegment();
      kems.enemyMoveDesc = new EnemyMoveDesc[]{EnemyMoveDesc.missWith(new CheckDisableEffectMisses(), 50)}; // disable
      kems.attackCount[2][1] = 1; // ice beam crit
      kems.attackCount[1][1] = 1; // surf crit
//      kems.attackCount[0][1] = 2; // mega punch crit
      kems.numExpGainers = 2; // L36
      seq(kems); // hypno
    }
    seq(new EndFightSegment(1)); // player defeated enemy
    seq(new TextSegment()); // evo
    seq(new TextSegment()); // evo
    seq(new WalkToSegment(4, 9)); // engage koga
    seq(new WalkToSegment(4, 10, false)); // engage koga
    seqMove(new OverworldInteract(1));
		seq(new InitFightSegment(9)); // start fight
    {
      KillEnemyMonSegment kems = new KillEnemyMonSegment();
//      kems.attackCount[3][1] = 1; // bubblebeam crit
      kems.attackCount[2][1] = 1; // ice beam crit
      seq(kems); // koffing
    }
    seq(NewEnemyMonSegment.any()); // next mon
    {
      KillEnemyMonSegment kems = new KillEnemyMonSegment();
      kems.enemyMoveDesc = new EnemyMoveDesc[]{EnemyMoveDesc.missWith(new CheckDisableEffectMisses(), 50)}; // disable
//      kems.attackCount[3][1] = 2; // bubblebeam crit
      kems.attackCount[2][1] = 2; // ice beam crit
      seq(kems); // muk
    }
    seq(NewEnemyMonSegment.any()); // next mon
    {
      KillEnemyMonSegment kems = new KillEnemyMonSegment();
      kems.attackCount[2][1] = 1; // ice beam crit
//      kems.attackCount[3][1] = 1; // bubblebeam crit
      seq(kems); // koffing
    }
    seq(NewEnemyMonSegment.any()); // next mon
    {
      KillEnemyMonSegment kems = new KillEnemyMonSegment();
      kems.enemyMoveDesc = new EnemyMoveDesc[]{EnemyMoveDesc.missWith(new CheckPoisonEffectMisses(), 92)}; // toxic
//      kems.attackCount[2][1] = 2; // ice beam crit
      kems.attackCount[3][1] = 2; // bubblebeam crit
      kems.numExpGainers = 2; // L37
      seq(kems); // weezing // TODO: selfdestruct
    }
		seq(new EndFightSegment(3)); // player defeated enemy
		seq(new SkipTextsSegment(11)); // after battle texts
    seq(new WalkToSegment(5, 18, false)); // leave gym

    save("sa5");
//    load("sa5");

    seq(new UseBikeSegment(2, 0));
    seq(new WalkToSegment(23, 30, false)); // ledge
    seq(new WalkToSegment(27, 27)); // enter ward house
    seq(new WalkToSegment(2, 5)); // ward
    seq(new WalkToSegment(2, 4)); // ward
    seqMove(new OverworldInteract(1));
    seq(new SkipTextsSegment(11)); // get HM04
    seq(new WalkToSegment(4, 8, false)); // leave ward house
	}
}
