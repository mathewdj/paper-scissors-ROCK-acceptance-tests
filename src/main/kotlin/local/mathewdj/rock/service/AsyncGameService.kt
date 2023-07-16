package local.mathewdj.rock.service

import com.netflix.dgs.codegen.generated.types.GameLoss
import com.netflix.dgs.codegen.generated.types.GameTie
import com.netflix.dgs.codegen.generated.types.GameWin
import com.netflix.dgs.codegen.generated.types.PlayTurnResponse
import com.netflix.dgs.codegen.generated.types.WaitingOnOtherPlayers
import local.mathewdj.rock.repository.AsyncGameRepository
import org.springframework.stereotype.Service
import java.util.UUID
import local.mathewdj.rock.domain.Attack
import local.mathewdj.rock.domain.ScissorsPaperRockRuleEngine
import local.mathewdj.rock.entity.AsyncGameEntity
import org.slf4j.LoggerFactory

@Service
class AsyncGameService(
    private val asyncGameRepository: AsyncGameRepository
) {

    fun playTurn(gameId: UUID, playerId: UUID, attack: Attack): PlayTurnResponse {
        val previousGames = asyncGameRepository.findByGameId(gameId)

        val turn = AsyncGameEntity(gameId, playerId, attack)
        asyncGameRepository.save(turn)

        if (previousGames.isEmpty()) {
            return WaitingOnOtherPlayers()
        }

        val previousGame = previousGames.first()

        val playTurnResponse = when (ScissorsPaperRockRuleEngine.play(attack, previousGame.playerAttack)) {
            ScissorsPaperRockRuleEngine.Result.Win -> GameWin()
            ScissorsPaperRockRuleEngine.Result.Tie -> GameTie()
            ScissorsPaperRockRuleEngine.Result.Loss -> GameLoss()
        }
        logger.info("PlayerId=$playerId response=$playTurnResponse")
        return playTurnResponse
    }


    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java)
    }
}
