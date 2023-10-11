package com.example.citygamebackend.cucumberglue;

import com.example.citygamebackend.CitiesService;
import com.example.citygamebackend.GameService;
import com.example.citygamebackend.messages.ResponseMessage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

public class CucumberGameServiceTest {

    private GameService gameService;
    private ResponseMessage responseMessage;

    @Given("the game is created")
    public void game_is_created() {
        CitiesService citiesService = new CitiesService("20K+cities.txt");
        gameService = new GameService(citiesService);
        gameService.createNewGame(1);
        gameService.createNewGame(2);
    }

    @When("function is called with any existed city")
    public void function_is_called_with_any_existed_city() {
        responseMessage = gameService.processMove("1_2", "Москва");
    }

    @Then("this word is allowed")
    public void this_word_is_allowed() {
        Assert.assertEquals(responseMessage.getLastWord(), "Москва");
    }

}
