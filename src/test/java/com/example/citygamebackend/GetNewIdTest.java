package com.example.citygamebackend;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GetNewIdTest {

    @Test
    public void getNewIdTest() {
        CitiesService citiesService = new CitiesService("20K+cities.txt");
        GameService gameService = new GameService(citiesService);
        Assertions.assertEquals(1, gameService.getNewId());
    }
}
