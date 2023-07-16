package local.mathewdj.rock.graphql

import com.netflix.dgs.codegen.generated.types.PlayGameInput
import com.netflix.dgs.codegen.generated.types.PlayTurnResponse
import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsMutation
import local.mathewdj.rock.service.AsyncGameService
import org.springframework.stereotype.Component

@Component
@DgsComponent
class Mutations(private val asyncGameService: AsyncGameService) {
    @DgsMutation
    fun playTurn(playGameInput: PlayGameInput): PlayTurnResponse {
        val attack = playGameInput.attack.toGraphql()

        return asyncGameService.playTurn(playGameInput.gameId, playGameInput.playerId, attack)
    }
}
