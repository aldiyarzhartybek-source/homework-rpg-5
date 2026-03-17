package com.narxoz.rpg.facade;

public class RewardService {
    public String determineReward(AdventureResult battleResult) {
        if (battleResult == null) {
            return "No reward";
        }

        if (!battleResult.isHeroWon()) {
            return "No reward: dungeon failed";
        }

        if (battleResult.getRounds() <= 3) {
            return "Legendary chest: 150 gold, 60 XP, Epic Rune";
        }

        if (battleResult.getRounds() <= 6) {
            return "Victory reward: 100 gold, 40 XP, Rare Potion";
        }

        return "Survivor reward: 60 gold, 20 XP";
    }
}