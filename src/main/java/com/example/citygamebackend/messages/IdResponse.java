package com.example.citygamebackend.messages;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IdResponse {
    private int id;

    public IdResponse(int id) {
        this.id = id;
    }
}
