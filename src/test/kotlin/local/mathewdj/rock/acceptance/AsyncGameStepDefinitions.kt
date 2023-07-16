package local.mathewdj.rock.acceptance

import io.cucumber.java.Before
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import io.cucumber.spring.CucumberContextConfiguration
import local.mathewdj.rock.domain.Attack

@CucumberContextConfiguration
class AsyncGameStepDefinitions {

    @Before
    fun setup() {
        val postgresContainer = Containers.postgresContainer
        postgresContainer.start()
        //TODO add better strategy
        Thread.sleep(3_000)

        val paperScissorsRockContainer = Containers.paperScissorsRockContainer()
        paperScissorsRockContainer.start()
    }

    @When("player {player} plays {attack}")
    fun `player uses attack`(player: Player, attack: Attack) {
        TODO("$player $attack")
    }

    @Then("{player} wins")
    fun `{player} wins`(player: Player) {
        TODO("$player")
    }

}
