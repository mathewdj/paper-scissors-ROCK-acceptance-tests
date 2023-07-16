package local.mathewdj.rock.acceptance

import io.cucumber.java.Before
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import io.cucumber.spring.CucumberContextConfiguration
import io.github.atomfinger.touuid.toUuid
import java.util.UUID
import local.mathewdj.rock.domain.Attack
import local.mathewdj.rock.graphql.GraphqlUtil
import org.testcontainers.containers.GenericContainer

@CucumberContextConfiguration
class AsyncGameStepDefinitions {

    private lateinit var paperScissorsRockContainer: GenericContainer<*>
    private val gameIdStub: UUID = UUID.randomUUID()

    @Before
    fun setup() {
        val postgresContainer = Containers.postgresContainer
        postgresContainer.start()
        //TODO add better strategy
        Thread.sleep(3_000)

        paperScissorsRockContainer = Containers.paperScissorsRockContainer()
        paperScissorsRockContainer.start()
    }

    @When("player {player} plays {attack}")
    fun `player uses attack`(player: Player, attack: Attack) {
        GraphqlUtil().playTurn(
            paperScissorsRockContainer.firstMappedPort,
            gameIdStub,
            player.name.hashCode().toUuid(),
            com.netflix.dgs.codegen.generated.types.Attack.valueOf(attack.name)
        )
    }

    @Then("{player} wins")
    fun `{player} wins`(player: Player) {
        TODO("$player")
    }

}
