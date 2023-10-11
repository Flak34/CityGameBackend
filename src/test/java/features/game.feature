@all
  Feature: process player word
    @success
    Scenario: this word is the first in game
      Given the game is created
      When function is called with any existed city
      Then this word is allowed