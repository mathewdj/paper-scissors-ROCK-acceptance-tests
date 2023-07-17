Feature: Players can take turns playing paper scissors rock.
  PLayers don't have to be on the same timezone or at the same time.

  Scenario: Rock beats Scissors
    Given player Blue plays Scissors
    When player Red plays Rock
    Then Red wins

