package com.example.citygamebackend;

import com.example.citygamebackend.messages.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;


@Component
public class GameService {
    private static int count = 0;
    private final HashMap<String, Game> activeGames = new HashMap<>();
    private final HashMap<Integer, String> playersInGame = new HashMap<>();
    private int freePlayerId = -1;

    @Autowired
    private CitiesService citiesService;

    public int getNewId() {
        count++;
        return count;
    }

    public Game getGame(String gameId) {
        return activeGames.get(gameId);
    }

    public ResponseMessage processMove(String gameId, String nextWord) {
        return new ResponseMessage();
    }

    public ResponseMessage createNewGame(int playerId) {
        return new ResponseMessage();
    }
}
