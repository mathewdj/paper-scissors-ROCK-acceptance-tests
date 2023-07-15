package local.mathewdj.rock.acceptance

import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import local.mathewdj.rock.domain.Attack

class AsyncGameStepDefinitions {
    @Given("player {player}")
    fun `given player`(player: Player) {

    }

    @When("{player} plays {attack}")
    fun `player uses attack`(attack: Attack) {

    }

    @Then("{player} wins")
    fun `{player} wins`() {

    }
}
