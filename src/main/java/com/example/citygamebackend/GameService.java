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
    private CitiesService citiesService;

    public GameService(CitiesService citiesService) {
        this.citiesService = citiesService;
    }

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
        ResponseMessage responseMessage;
        if(playersInGame.containsKey(playerId)) {
            responseMessage = new ResponseMessage(playersInGame.get(playerId), playerId, "", "");
            responseMessage.setLastWord(activeGames.get(playersInGame.get(playerId)).getLastWord());
            responseMessage.setMovingPlayerId(activeGames.get(playersInGame.get(playerId)).getIdOfMovingPlayer());
        }
        else if(freePlayerId == -1 || freePlayerId == playerId) {
            responseMessage = new ResponseMessage("-", playerId, "", "");
            freePlayerId = playerId;
        }
        else {
            Game newGame = new Game(freePlayerId, playerId);
            activeGames.put(newGame.getGameId(), newGame);

            playersInGame.put(freePlayerId, newGame.getGameId());
            playersInGame.put(playerId, newGame.getGameId());

            freePlayerId = -1;
            responseMessage = new ResponseMessage(newGame.getGameId(),
                    newGame.getIdOfMovingPlayer(), "", "");
        }
        return responseMessage;
    }

}
