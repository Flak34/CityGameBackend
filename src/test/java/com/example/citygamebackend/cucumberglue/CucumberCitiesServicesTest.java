package com.example.citygamebackend.cucumberglue;

import com.example.citygamebackend.CitiesService;
import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@CucumberContextConfiguration
@SpringBootTest
public class CucumberCitiesServicesTest {
    private CitiesService citiesService;
    private String w1, w2;
    @Дано("Требуется проверить слова Астрахань, Москва")
    public void требуется_проверить_слова_астрахань_москва() {
        citiesService = new CitiesService("20K+cities.txt");
    }

    @Когда("^Вызывается функция проверки со словами (.*?), (.*?)$")
    public void вызывается_функция_проверки_со_словами_астархань_москва(String w1, String w2) {
        this.w1 = w1;
        this.w2 = w2;
    }

    @Тогда("возвращается true")
    public void возвращается_true() {
        assertTrue(citiesService.isCityPairValid(w1, w2));

    }
}
