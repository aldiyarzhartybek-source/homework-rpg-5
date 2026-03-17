package com.narxoz.rpg.facade;

import com.narxoz.rpg.decorator.AttackAction;
import com.narxoz.rpg.enemy.BossEnemy;
import com.narxoz.rpg.hero.HeroProfile;

public class PreparationService {
    public String prepare(HeroProfile hero, BossEnemy boss, AttackAction action) {
        if (hero == null || boss == null || action == null) {
            return "Preparation failed: missing hero, boss, or attack action.";
        }

        return "Preparation complete: Hero " + hero.getName()
                + " (HP=" + hero.getHealth() + ") enters the dungeon against "
                + boss.getName() + " (HP=" + boss.getHealth() + ", ATK=" + boss.getAttackPower() + ") using "
                + action.getActionName() + " [DMG=" + action.getDamage() + ", Effects=" + action.getEffectSummary() + "]";
    }
}