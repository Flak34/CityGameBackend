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
        Game game = activeGames.get(gameId);

        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setGameId(gameId);
        responseMessage.setInfo("");

        if(game.isWordUsed(nextWord)) {
            responseMessage.setInfo("Данный город уже использовался!");
        }
        //проверка на существование города
        else if(citiesService.isCityExist(nextWord)) {
            responseMessage.setInfo("Кажется, город с таким названием не существует.");
        }
        else if(game.getUsedWords().size() != 0 &&
                !citiesService.isCityPairValid(nextWord, game.getLastWord())) {
            char lastLetter = game.getLastWord().charAt(game.getLastWord().length() - 1);
            if((lastLetter == 'ъ' || lastLetter == 'ь') && game.getLastWord().length() > 1)
                lastLetter = game.getLastWord().charAt(game.getLastWord().length() - 2);
            responseMessage.setInfo("Город должен начинаться на букву " + lastLetter + "!");
        }
        else {
            game.addWord(nextWord);
            game.changeMovingPlayer();
        }

        responseMessage.setLastWord(game.getLastWord());
        responseMessage.setMovingPlayerId(game.getIdOfMovingPlayer());
        return responseMessage;
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
