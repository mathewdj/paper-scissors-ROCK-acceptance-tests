package local.mathewdj.rock.service

import local.mathewdj.rock.repository.AsyncGameRepository
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class AsyncGameService(
    private val asyncGameRepository: AsyncGameRepository
) {

    fun asyncGameById(gameId: UUID) {
        asyncGameRepository.findByGameId(gameId)
    }
}
