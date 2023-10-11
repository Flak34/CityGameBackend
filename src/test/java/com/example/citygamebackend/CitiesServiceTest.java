package com.example.citygamebackend;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest()
public class CitiesServiceTest {
    @Test
    @DisplayName("Check is city exist or not")
    public void checkIsCityExistsSuccessCase1(){
        CitiesService citiesService = new CitiesService("20K+cities.txt");
        assertFalse(citiesService.isCityExist("Москва"));
    }

    @Test
    @DisplayName("Check is city exist or not")
    public void checkIsCityExistsSuccessCase2(){
        CitiesService citiesService = new CitiesService("20K+cities.txt");
        assertFalse(citiesService.isCityExist("марафон"));
    }

    @Test
    @DisplayName("Check is city exists or not")
    public void checkIsCityExistNotSuccess(){
        CitiesService citiesService = new CitiesService("20K+cities.txt");
        assertFalse(citiesService.isCityExist("Найт сити"));
    }
}
