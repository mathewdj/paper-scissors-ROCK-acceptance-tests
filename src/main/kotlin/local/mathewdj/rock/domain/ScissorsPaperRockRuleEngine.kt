package local.mathewdj.rock.domain

import local.mathewdj.rock.domain.Attack.*

object ScissorsPaperRockRuleEngine {
    fun play(left: Attack, right: Attack): Result {
        if (left == right)
            return Result.Tie

        return when (left) {
            Paper -> when (right) {
                Scissors -> Result.Loss
                Rock -> Result.Win
                else -> Result.Tie
            }
            Scissors -> when (right) {
                Paper -> Result.Win
                Rock -> Result.Loss
                else -> Result.Tie
            }
            Rock -> when (right) {
                Paper -> Result.Loss
                Scissors -> Result.Win
                else -> Result.Tie
            }
        }
    }

    enum class Result {
        Win,
        Tie,
        Loss
    }
}

