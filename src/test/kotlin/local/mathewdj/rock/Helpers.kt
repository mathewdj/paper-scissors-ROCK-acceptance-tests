package local.mathewdj.rock

import io.github.atomfinger.touuid.toUuid
import local.mathewdj.rock.acceptance.Player
import local.mathewdj.rock.domain.Attack
import local.mathewdj.rock.entity.AsyncGameEntity
import java.time.LocalDateTime
import java.util.UUID


val timestamp: LocalDateTime = LocalDateTime.of(2023, 6, 14, 13, 0, 0, 0)

fun createAsyncGameEntity(
        id: UUID = UUID.randomUUID(),
        gameId: UUID = UUID.randomUUID(),
        playerId: UUID = Player.Red.name.hashCode().toUuid(),
        attack: Attack = Attack.Rock,
        createdAtUtc: LocalDateTime = timestamp
) = AsyncGameEntity(
        gameId,
        playerId,
        attack,
        createdAtUtc,
        id
)
