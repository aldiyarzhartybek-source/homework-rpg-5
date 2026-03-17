package com.narxoz.rpg;

import com.narxoz.rpg.decorator.AttackAction;
import com.narxoz.rpg.decorator.BasicAttack;
import com.narxoz.rpg.decorator.CriticalFocusDecorator;
import com.narxoz.rpg.decorator.FireRuneDecorator;
import com.narxoz.rpg.decorator.PoisonCoatingDecorator;
import com.narxoz.rpg.enemy.BossEnemy;
import com.narxoz.rpg.facade.AdventureResult;
import com.narxoz.rpg.facade.DungeonFacade;
import com.narxoz.rpg.hero.HeroProfile;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Homework 5 Demo: Decorator + Facade ===\n");

        HeroProfile hero = new HeroProfile("Alden", 120);
        BossEnemy boss = new BossEnemy("Ancient Dragon", 160, 18);

        AttackAction basic = new BasicAttack("Sword Slash", 18);
        AttackAction fire = new FireRuneDecorator(basic);
        AttackAction firePoison = new PoisonCoatingDecorator(fire);
        AttackAction firePoisonCritical = new CriticalFocusDecorator(firePoison);
        AttackAction criticalThenFire = new FireRuneDecorator(new CriticalFocusDecorator(basic));

        System.out.println("--- Decorator Demo ---");
        printAction("Base action", basic);
        printAction("Fire upgrade", fire);
        printAction("Fire + Poison", firePoison);
        printAction("Fire + Poison + Critical", firePoisonCritical);
        printAction("Critical first, then Fire", criticalThenFire);

        System.out.println("\nThis proves runtime stacking and order-sensitive composition.");
        System.out.println("Notice that 'Fire + Poison + Critical' and 'Critical first, then Fire' have different damage.");

        System.out.println("\n--- Facade Dungeon Run ---");
        DungeonFacade facade = new DungeonFacade().setRandomSeed(42L);
        AdventureResult result = facade.runAdventure(hero, boss, firePoisonCritical);

        System.out.println("Winner: " + result.getWinner());
        System.out.println("Rounds: " + result.getRounds());
        System.out.println("Reward: " + result.getReward());
        System.out.println("\nAdventure log:");
        for (String line : result.getLog()) {
            System.out.println(line);
        }

        System.out.println("\n=== Demo Complete ===");
    }

    private static void printAction(String label, AttackAction action) {
        System.out.println(label + ":");
        System.out.println("  Name: " + action.getActionName());
        System.out.println("  Damage: " + action.getDamage());
        System.out.println("  Effects: " + action.getEffectSummary());
    }
}