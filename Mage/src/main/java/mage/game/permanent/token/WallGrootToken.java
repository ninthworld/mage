

package mage.game.permanent.token;

import mage.MageInt;
import mage.abilities.keyword.DefenderAbility;
import mage.constants.CardType;
import mage.constants.SubType;

/**
 *
 * @author NinthWorld
 */
public final class WallGrootToken extends TokenImpl {

    public WallGrootToken() {
        super("", "4/7 wall creature with defender");
        cardType.add(CardType.CREATURE);
        subtype.add(SubType.WALL);
        power = new MageInt(4);
        toughness = new MageInt(6);
        this.addAbility(DefenderAbility.getInstance());
    }

    public WallGrootToken(final WallGrootToken token) {
        super(token);
    }

    public WallGrootToken copy() {
        return new WallGrootToken(this);
    }
}
