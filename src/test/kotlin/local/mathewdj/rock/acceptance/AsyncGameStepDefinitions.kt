package local.mathewdj.rock.acceptance

import io.cucumber.java.Before
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import io.cucumber.spring.CucumberContextConfiguration
import io.github.atomfinger.touuid.toUuid
import java.util.UUID
import local.mathewdj.rock.domain.Attack
import local.mathewdj.rock.graphql.GraphqlUtil
import org.assertj.core.api.Assertions.assertThat
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

    private var turnResult: MutableMap<UUID, String> = mutableMapOf()
    @When("player {player} plays {attack}")
    fun `player plays attack`(player: Player, attack: Attack) {
        val playerId = player.name.hashCode().toUuid()

        turnResult[playerId] = GraphqlUtil().playTurn(
            paperScissorsRockContainer.firstMappedPort,
            gameIdStub,
                playerId,
            com.netflix.dgs.codegen.generated.types.Attack.valueOf(attack.name)
        )
    }

    @Then("{player} wins")
    fun `player wins`(player: Player) {
        val playerId = player.name.hashCode().toUuid()

        assertThat(turnResult[playerId]).isEqualTo("GameWin")
    }

}
