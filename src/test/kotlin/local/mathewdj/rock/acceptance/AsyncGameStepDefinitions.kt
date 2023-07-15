package local.mathewdj.rock.acceptance

import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import local.mathewdj.rock.domain.Attack

class AsyncGameStepDefinitions {
    @Given("player {player}")
    fun `given player`(player: Player) {
        TODO("$player")
    }

    @When("{player} plays {attack}")
    fun `player uses attack`(player: Player, attack: Attack) {
        TODO("$player $attack")
    }

    @Then("{player} wins")
    fun `{player} wins`(player: Player) {
        TODO("$player")
    }
}
