package local.mathewdj.rock.acceptance

import com.netflix.dgs.codegen.generated.types.Attack
import java.util.UUID
import local.mathewdj.rock.RockPaperScissorsApplication
import local.mathewdj.rock.graphql.GraphqlUtil
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Lazy
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = [RockPaperScissorsApplication::class, EvolvedIntegrationTestAcceptanceTest.TestConfig::class]
)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
@Testcontainers
class EvolvedIntegrationTestAcceptanceTest {

    @Nested
    @DisplayName("Given player plays Rock")
    inner class GivenPlayerPlaysRock {
        private val gameIdStub = UUID.randomUUID()
        private val player = UUID.randomUUID()

        @Autowired
        private var graphqlClientPort: Int = 0

        @BeforeEach
        fun setup() {
            GraphqlUtil().playTurn(graphqlClientPort, gameIdStub, player, Attack.Rock)
        }

        @Nested
        @DisplayName("When other player plays Paper")
        inner class WhenOtherPlayerPlaysPaper {
            private val otherPlayer = UUID.randomUUID()

            @Test
            fun `then other player wins`() {
                val result = GraphqlUtil().playTurn(graphqlClientPort, gameIdStub, otherPlayer, Attack.Paper)
                assertThat(result).isEqualTo("GameWin")
            }
        }
    }


    @Lazy
    @TestConfiguration
    class TestConfig {
        @Bean
        fun graphqlClientPort(@LocalServerPort port: Int): Int {
            return port
        }
    }

    companion object {
        @Container
        private val postgreSQLContainer = PostgreSQLContainer<Nothing>("postgres:14.5")

        @DynamicPropertySource
        @JvmStatic
        fun registerDynamicProperties(registry: DynamicPropertyRegistry) {
            registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl)
            registry.add("spring.datasource.username", postgreSQLContainer::getUsername)
            registry.add("spring.datasource.password", postgreSQLContainer::getPassword)
        }
    }
}
