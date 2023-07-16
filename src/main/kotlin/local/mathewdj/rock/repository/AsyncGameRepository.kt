package local.mathewdj.rock.repository

import local.mathewdj.rock.entity.AsyncGameEntity
import org.springframework.data.repository.CrudRepository
import java.util.*

interface AsyncGameRepository : CrudRepository<AsyncGameEntity, UUID> {
    fun findByGameId(gameId: UUID): List<AsyncGameEntity>
}
