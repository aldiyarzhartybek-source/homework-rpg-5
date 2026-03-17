package com.narxoz.rpg.facade;

import com.narxoz.rpg.decorator.AttackAction;
import com.narxoz.rpg.enemy.BossEnemy;
import com.narxoz.rpg.hero.HeroProfile;

import java.util.Random;

public class BattleService {
    private static final int MAX_ROUNDS = 20;
    private Random random = new Random(1L);

    public BattleService setRandomSeed(long seed) {
        this.random = new Random(seed);
        return this;
    }

    public AdventureResult battle(HeroProfile hero, BossEnemy boss, AttackAction action) {
        AdventureResult result = new AdventureResult();

        if (hero == null || boss == null || action == null) {
            result.setWinner("Invalid");
            result.setRounds(0);
            result.setHeroWon(false);
            result.addLine("Battle failed: invalid input.");
            return result;
        }

        result.addLine("Battle begins between " + hero.getName() + " and " + boss.getName() + ".");

        int rounds = 0;

        while (hero.isAlive() && boss.isAlive() && rounds < MAX_ROUNDS) {
            rounds++;
            result.addLine("Round " + rounds + ":");

            int heroDamage = action.getDamage();
            boss.takeDamage(heroDamage);
            result.addLine("  " + hero.getName() + " uses " + action.getActionName()
                    + " for " + heroDamage + " damage. "
                    + boss.getName() + " HP=" + boss.getHealth());

            if (!boss.isAlive()) {
                break;
            }

            int bonus = random.nextInt(4);
            int bossDamage = boss.getAttackPower() + bonus;
            hero.takeDamage(bossDamage);
            result.addLine("  " + boss.getName() + " strikes back for " + bossDamage
                    + " damage (rage bonus +" + bonus + "). "
                    + hero.getName() + " HP=" + hero.getHealth());
        }

        result.setRounds(rounds);

        if (hero.isAlive() && !boss.isAlive()) {
            result.setWinner(hero.getName());
            result.setHeroWon(true);
            result.addLine("The hero cleared the dungeon.");
        } else if (boss.isAlive() && !hero.isAlive()) {
            result.setWinner(boss.getName());
            result.setHeroWon(false);
            result.addLine("The boss defeated the hero.");
        } else {
            result.setWinner("Draw");
            result.setHeroWon(false);
            result.addLine("The dungeon run ended in a draw after reaching the round limit.");
        }

        return result;
    }
}