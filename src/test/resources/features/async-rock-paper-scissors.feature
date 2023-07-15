Feature: Players can take turns playing paper scissors rock.
  They can be on different timezones. Play

  @under-development
  Scenario: Rock beats Scissors
    Given player Blue
    And player Red
    When Blue plays Scissors
    And Red plays Rock
    Then Red wins
