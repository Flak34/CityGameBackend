package com.example.citygamebackend.messages;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseMessage {
    private String gameId;
    private Integer movingPlayerId;
    private String lastWord;
    private String info;
}
