package com.narxoz.rpg.decorator;

public class CriticalFocusDecorator extends ActionDecorator {
    public CriticalFocusDecorator(AttackAction wrappedAction) {
        super(wrappedAction);
    }

    @Override
    public String getActionName() {
        return "Critical " + super.getActionName();
    }

    @Override
    public int getDamage() {
        return (int) Math.round(super.getDamage() * 1.5);
    }

    @Override
    public String getEffectSummary() {
        return super.getEffectSummary() + ", critical focus";
    }
}