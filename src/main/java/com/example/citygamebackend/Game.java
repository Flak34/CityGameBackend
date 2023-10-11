package com.example.citygamebackend;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;

@Getter
@Setter
public class Game {
    private final String gameId;
    private final Integer firstPlayerId;
    private final Integer secondPlayerId;
    private final HashSet<String> usedWords = new HashSet<>();
    private String lastWord;
    private int idOfMovingPlayer;

    public Game(int firstPlayerId, int secondPlayerId) {
        this.firstPlayerId = firstPlayerId;
        this.secondPlayerId = secondPlayerId;
        idOfMovingPlayer = firstPlayerId;
        gameId = firstPlayerId + "_" + secondPlayerId;
        lastWord = "";
    }

    public void addWord(String word) {
        usedWords.add(word);
        lastWord = word;
    }

    public boolean isWordUsed(String word) {
        return usedWords.contains(word);
    }

    public void changeMovingPlayer() {
        if(idOfMovingPlayer == firstPlayerId)
            idOfMovingPlayer = secondPlayerId;
        else
            idOfMovingPlayer = firstPlayerId;
    }
}
