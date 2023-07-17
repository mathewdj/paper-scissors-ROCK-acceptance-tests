Feature: Rock, Paper, Scissors Deck builder

  @under-development
  Scenario: when both players use same card but one player uses a higher level power
    then the losing player loses a heart from their health bar
    Given player Red plays scissors with power level 2
    When player Blue plays scissors with power level 1
    Then losing player stats are:
      | Player name | Player health |
      | Blue        | ♥️♥️♥         |
