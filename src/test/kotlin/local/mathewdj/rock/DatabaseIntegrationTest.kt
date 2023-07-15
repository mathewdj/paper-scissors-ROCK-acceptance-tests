package local.mathewdj.rock

import local.mathewdj.rock.repository.AsyncGameRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers

@SpringBootTest(classes = [RockPaperScissorsApplication::class])
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Testcontainers
class DatabaseIntegrationTest {

    @Autowired
    private lateinit var asyncGameRepository: AsyncGameRepository

    @Nested
    inner class AsyncGamePersistenceTest {
        @Test
        fun `should persist first round of a game`() {
            val game = createAsyncGameEntity()

            val entity = asyncGameRepository.save(game)

            val matchInDb = asyncGameRepository.findById(entity.id ?: error("no id")).get()

            assertThat(matchInDb)
                    .usingRecursiveComparison()
                    .ignoringFields("id")
                    .isEqualTo(game)
        }
    }

    @Nested
    inner class DatabaseConnectivitySmokeTest {
        @Autowired
        private lateinit var jdbcTemplate: JdbcTemplate

        @Test
        fun `when database is connected then it should be Postgres version 14`() {
            val actualDatabaseVersion = jdbcTemplate.queryForObject("SELECT version()", String::class.java)
            assertThat(actualDatabaseVersion).contains("PostgreSQL 14.5")
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
