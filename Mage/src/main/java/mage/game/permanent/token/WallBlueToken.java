

package mage.game.permanent.token;

import mage.MageInt;
import mage.abilities.keyword.DefenderAbility;
import mage.constants.CardType;
import mage.constants.SubType;

/**
 *
 * @author NinthWorld
 */
public final class WallBlueToken extends TokenImpl {

    public WallBlueToken() {
        super("Wall", "0/4 blue Wall creature token with defender");
        this.setOriginalExpansionSetCode("MCU");
        cardType.add(CardType.CREATURE);
        color.setBlue(true);
        subtype.add(SubType.WALL);
        power = new MageInt(0);
        toughness = new MageInt(4);
        this.addAbility(DefenderAbility.getInstance());
    }

    public WallBlueToken(final WallBlueToken token) {
        super(token);
    }

    public WallBlueToken copy() {
        return new WallBlueToken(this);
    }
}
