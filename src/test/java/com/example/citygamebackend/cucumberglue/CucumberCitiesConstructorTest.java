package com.example.citygamebackend.cucumberglue;

import com.example.citygamebackend.CitiesService;
import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import org.junit.jupiter.api.Assertions;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;

@SpringBootTest
public class CucumberCitiesConstructorTest {
    private CitiesService citiesService;
    private String file;
    @Дано("Требуется проверить создание экземпляра CitiesService с файлом {}")
    public void требуется_проверить_создание_экземпляра_CitiesService(String file) {
        this.file = file;
    }

    @Когда("Вызывается функция проверки создания объекта citiesService")
    public void вызывается_функция_проверки_с_файлом() {
        citiesService = new CitiesService(file);
    }

    @Тогда("Поле cities не пустое")
    public void поле_cities_не_пустое() throws NoSuchFieldException {
        Assertions.assertNotEquals(citiesService.getCities(), new HashSet<>());
    }
}
