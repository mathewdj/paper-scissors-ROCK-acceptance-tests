package local.mathewdj.rock.service

import com.netflix.dgs.codegen.generated.types.GameWin
import com.netflix.dgs.codegen.generated.types.PlayTurnResponse
import com.netflix.dgs.codegen.generated.types.WaitingOnOtherPlayers
import local.mathewdj.rock.repository.AsyncGameRepository
import org.springframework.stereotype.Service
import java.util.UUID
import local.mathewdj.rock.domain.Attack
import local.mathewdj.rock.entity.AsyncGameEntity

@Service
class AsyncGameService(
    private val asyncGameRepository: AsyncGameRepository
) {

    fun playTurn(gameId: UUID, playerId: UUID, attack: Attack): PlayTurnResponse {
        val previousGames = asyncGameRepository.findByGameId(gameId)

        val turn = AsyncGameEntity(gameId, playerId, attack)
        val persistedTurn = asyncGameRepository.save(turn)

        if (previousGames.isEmpty()) {
            return WaitingOnOtherPlayers()
        }

        return GameWin()
    }

    fun asyncGameById(gameId: UUID) {
        asyncGameRepository.findByGameId(gameId)
    }
}
