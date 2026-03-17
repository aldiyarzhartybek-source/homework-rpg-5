package com.narxoz.rpg.facade;

import com.narxoz.rpg.decorator.AttackAction;
import com.narxoz.rpg.enemy.BossEnemy;
import com.narxoz.rpg.hero.HeroProfile;

public class DungeonFacade {
    private final PreparationService preparationService = new PreparationService();
    private final BattleService battleService = new BattleService();
    private final RewardService rewardService = new RewardService();

    public DungeonFacade setRandomSeed(long seed) {
        battleService.setRandomSeed(seed);
        return this;
    }

    public AdventureResult runAdventure(HeroProfile hero, BossEnemy boss, AttackAction action) {
        AdventureResult finalResult = new AdventureResult();

        String preparationSummary = preparationService.prepare(hero, boss, action);
        finalResult.addLine(preparationSummary);

        if (hero == null || boss == null || action == null) {
            finalResult.setWinner("Invalid");
            finalResult.setRounds(0);
            finalResult.setHeroWon(false);
            finalResult.setReward("No reward");
            return finalResult;
        }

        AdventureResult battleResult = battleService.battle(hero, boss, action);

        finalResult.setWinner(battleResult.getWinner());
        finalResult.setRounds(battleResult.getRounds());
        finalResult.setHeroWon(battleResult.isHeroWon());

        for (String line : battleResult.getLog()) {
            finalResult.addLine(line);
        }

        finalResult.setReward(rewardService.determineReward(battleResult));
        return finalResult;
    }
}