package local.mathewdj.rock.acceptance

import org.testcontainers.containers.GenericContainer
import org.testcontainers.containers.Network
import org.testcontainers.containers.PostgreSQLContainer

object Containers {
    private val network = Network.newNetwork()

    val postgresContainer: PostgreSQLContainer<*> = PostgreSQLContainer("postgres:14.5")
            .withDatabaseName("paper_scissors_rock_db")
            .withUsername("test")
            .withPassword("test")
            .withNetworkAliases("postgres_db")
            .withNetwork(network)

    fun paperScissorsRockContainer(): GenericContainer<*> =
            GenericContainer("paperscissorsrock:0.1-SNAPSHOT")
                    .withEnv("DB_URL", "jdbc:postgresql://postgres_db:5432/paper_scissors_rock_db")
                    .withEnv("DB_USERNAME", "test")
                    .withEnv("DB_PASSWORD", "test")
                    .withExposedPorts(8080)
                    .withNetwork(network)
}
