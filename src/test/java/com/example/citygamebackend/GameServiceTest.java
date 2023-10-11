package com.example.citygamebackend;

import com.example.citygamebackend.messages.ResponseMessage;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class GameServiceTest {

    @Autowired
    private GameService gameService;

    @Test
    @DisplayName("Check game creation with without free players")
    @Order(1)
    public void checkGameCreationWithoutFreePlayers(){
        ResponseMessage responseMessage = gameService.createNewGame(1);
        Assertions.assertEquals("-", responseMessage.getGameId());
    }

    @Test
    @DisplayName("Check game creation with free players")
    @Order(2)
    public void checkGameCreationWithFreePlayer(){
        ResponseMessage responseMessage = gameService.createNewGame(2);
        Assertions.assertEquals("1_2", responseMessage.getGameId());
    }

    @Test
    @DisplayName("Check game creation for player in game")
    @Order(3)
    public void checkGameCreationForPlayerInGame(){
        ResponseMessage responseMessage = gameService.createNewGame(2);
        Assertions.assertEquals("1_2", responseMessage.getGameId());
    }

}
