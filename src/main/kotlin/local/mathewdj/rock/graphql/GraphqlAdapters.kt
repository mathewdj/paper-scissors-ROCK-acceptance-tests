package local.mathewdj.rock.graphql

import local.mathewdj.rock.domain.Attack
import com.netflix.dgs.codegen.generated.types.Attack as AttackGraphql

fun AttackGraphql.toGraphql(): Attack = when (this) {
    AttackGraphql.Scissors -> Attack.Scissors
    AttackGraphql.Paper -> Attack.Paper
    AttackGraphql.Rock -> Attack.Rock
}
