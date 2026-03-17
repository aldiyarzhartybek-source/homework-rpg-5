package com.narxoz.rpg.facade;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AdventureResult {
    private String winner;
    private int rounds;
    private String reward;
    private boolean heroWon;
    private final List<String> log = new ArrayList<>();

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public int getRounds() {
        return rounds;
    }

    public void setRounds(int rounds) {
        this.rounds = rounds;
    }

    public String getReward() {
        return reward;
    }

    public void setReward(String reward) {
        this.reward = reward;
    }

    public boolean isHeroWon() {
        return heroWon;
    }

    public void setHeroWon(boolean heroWon) {
        this.heroWon = heroWon;
    }

    public void addLine(String line) {
        log.add(line);
    }

    public List<String> getLog() {
        return Collections.unmodifiableList(log);
    }
}