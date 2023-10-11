package com.example.citygamebackend;

import com.example.citygamebackend.messages.IdResponse;
import com.example.citygamebackend.messages.RequestMessage;
import com.example.citygamebackend.messages.ResponseMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
@CrossOrigin
public class GameController {
    private final GameService gameService;
    private final SimpMessagingTemplate messagingTemplate;

    public GameController(GameService gameService, SimpMessagingTemplate messagingTemplate) {
        this.gameService = gameService;
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/move")
    public void processMove(@Payload RequestMessage requestMessage, Principal principal) {
        ResponseMessage responseMessage = gameService
                .processMove(requestMessage.getGameId(), requestMessage.getSuggestedWord());

        messagingTemplate.convertAndSendToUser(principal.getName(), "/queue/game-moves", responseMessage);
        if(responseMessage.getMovingPlayerId() != Integer.parseInt(principal.getName())) {
            messagingTemplate.convertAndSendToUser(responseMessage.getMovingPlayerId().toString(),
                    "/queue/game-moves", responseMessage);
        }
    }

    @SubscribeMapping("/{id}/queue/game-moves")
    public void createGame(@DestinationVariable Integer id) {
        ResponseMessage responseMessage = gameService.createNewGame(id);
        if(responseMessage.getGameId().equals("-")) {
            messagingTemplate.convertAndSendToUser(id.toString(), "/queue/game-moves", responseMessage);
        }
        else {
            String firstPlayerId = gameService.getGame(responseMessage.getGameId()).getFirstPlayerId().toString();
            String secondPlayerId = gameService.getGame(responseMessage.getGameId()).getSecondPlayerId().toString();
            messagingTemplate.convertAndSendToUser(firstPlayerId, "/queue/game-moves", responseMessage);
            messagingTemplate.convertAndSendToUser(secondPlayerId, "/queue/game-moves", responseMessage);
        }
    }

    @GetMapping("/get/id")
    public ResponseEntity<IdResponse> getId() {
        return ResponseEntity.ok(new IdResponse(gameService.getNewId()));
    }
}