Feature: Players can take turns playing paper scissors rock.
  They can be on different timezones. Play

  Scenario: Rock beats Scissors
    Given player Blue plays Scissors
    When player Red plays Rock
    Then Red wins
