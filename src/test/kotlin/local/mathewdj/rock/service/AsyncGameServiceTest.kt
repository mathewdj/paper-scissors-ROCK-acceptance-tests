package local.mathewdj.rock.service

import com.netflix.dgs.codegen.generated.types.WaitingOnOtherPlayers
import java.util.UUID
import local.mathewdj.rock.domain.Attack
import local.mathewdj.rock.repository.AsyncGameRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Testcontainers
class AsyncGameServiceTest {

    @Autowired
    private lateinit var asyncGameRepository: AsyncGameRepository

    @Autowired
    private lateinit var asynGameService: AsyncGameService

    private val playerIdStub: UUID = UUID.randomUUID()
    private val gameIdStub: UUID = UUID.randomUUID()

    @BeforeEach
    fun setup() {
        asyncGameRepository.deleteAll()
    }

    @Nested
    inner class HappyCaseTest {
        @Test
        fun `should persist first round of a game`() {
            val response = asynGameService.playTurn(gameIdStub, playerIdStub, Attack.Rock)
            assertThat(response).isEqualTo(WaitingOnOtherPlayers())
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
