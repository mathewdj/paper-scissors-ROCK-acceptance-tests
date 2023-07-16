package local.mathewdj.rock.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import local.mathewdj.rock.domain.Attack
import org.hibernate.annotations.JdbcTypeCode
import org.hibernate.type.SqlTypes
import java.time.LocalDateTime
import java.util.*

@Entity(name = "async_game")
data class AsyncGameEntity(

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
        var createdAtUtc: LocalDateTime = LocalDateTime.now(),

        @Id
        @GeneratedValue(strategy = GenerationType.UUID)
        var id: UUID? = null,
)
