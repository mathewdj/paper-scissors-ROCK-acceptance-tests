package local.mathewdj.rock.graphql

import com.netflix.dgs.codegen.generated.client.PlayTurnGraphQLQuery
import com.netflix.dgs.codegen.generated.client.PlayTurnProjectionRoot
import com.netflix.dgs.codegen.generated.types.Attack
import com.netflix.dgs.codegen.generated.types.PlayGameInput
import com.netflix.graphql.dgs.client.CustomGraphQLClient
import com.netflix.graphql.dgs.client.GraphQLClient
import com.netflix.graphql.dgs.client.HttpResponse
import com.netflix.graphql.dgs.client.codegen.GraphQLQueryRequest
import graphql.schema.Coercing
import java.util.UUID
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.web.client.RestTemplate


class GraphqlUtil {

    fun playTurn(port: Int, gameId: UUID, playerId: UUID, attack: Attack): String {
        val graphQLQueryRequest = GraphQLQueryRequest(
                query = PlayTurnGraphQLQuery
                        .newRequest()
                        .playGameInput(PlayGameInput(gameId, playerId, attack))
                        .build(),
                projection = PlayTurnProjectionRoot()
                        .onWaitingOnOtherPlayers().message()
                        .parent().onGameWin().playerId()
                        .parent().onGameLoss().playerId()
                        .parent().onGameTie().playerId(),
                scalars = scalars
        )

        val client = customGraphQLClient(port)

        val query = graphQLQueryRequest.serialize()

        return client.executeQuery(query).extractValue<String>("data.playTurn.__typename")
    }

    private fun customGraphQLClient(port: Int): CustomGraphQLClient {
        val restTemplate = RestTemplate();
        val client = GraphQLClient.createCustom("http://localhost:$port/graphql") { url, headers, body ->
            val httpHeaders = HttpHeaders()
            headers.forEach { httpHeaders.addAll(it.key, it.value) }

            val exchange = restTemplate.exchange(url, HttpMethod.POST, HttpEntity(body, httpHeaders), String::class.java)

            HttpResponse(exchange.statusCodeValue, exchange.body)
        }
        return client
    }

    companion object {
        val scalars: MutableMap<Class<*>, Coercing<*, *>> = HashMap()

    }

    init {
        scalars[UUID::class.java] = UUIDScalar()
    }

}
