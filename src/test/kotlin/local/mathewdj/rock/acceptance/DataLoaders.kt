package local.mathewdj.rock.acceptance

import io.cucumber.java.ParameterType
import local.mathewdj.rock.domain.Attack

class DataLoaders {

    @ParameterType("Red|Blue|Yellow")
    fun player(color: String): Player = Player.valueOf(color)

    @ParameterType("Scissors|Paper|Rock")
    fun attack(attack: String): Attack = Attack.valueOf(attack)

}
