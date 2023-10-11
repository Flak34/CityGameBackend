package com.example.citygamebackend.messages;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RequestMessage {
    private String gameId;
    private String suggestedWord;
}
