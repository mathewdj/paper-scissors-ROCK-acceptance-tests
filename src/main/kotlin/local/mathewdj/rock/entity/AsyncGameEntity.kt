package local.mathewdj.rock.entity

import jakarta.persistence.*
import local.mathewdj.rock.domain.Attack
import org.hibernate.annotations.JdbcTypeCode
import org.hibernate.type.SqlTypes
import java.time.LocalDateTime
import java.util.*

@Entity(name = "async_game")
data class AsyncGameEntity(
        @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    var id: UUID? = null,

    @Column(columnDefinition = "VARCHAR(36)")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    val gameId: UUID,

    @Column(columnDefinition = "VARCHAR(36)")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    val playerId: UUID,

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    val playerAttack: Attack,

    @Column(nullable = false)
    var createdAtUtc: LocalDateTime = LocalDateTime.now()
)
