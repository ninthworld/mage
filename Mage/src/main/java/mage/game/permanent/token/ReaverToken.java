package mage.game.permanent.token;

import mage.MageInt;
import mage.constants.CardType;
import mage.constants.SubType;
import mage.constants.SuperType;

/**
 * @author NinthWorld
 */
public final class ReaverToken extends TokenImpl {

    public ReaverToken() {
        super("Reaver", "2/2 black Reaver artifact creature token");
        this.setOriginalExpansionSetCode("MRV");
        cardType.add(CardType.CREATURE);
        cardType.add(CardType.ARTIFACT);
        color.setBlack(true);
        subtype.add(SubType.REAVER);
        power = new MageInt(2);
        toughness = new MageInt(2);
    }

    public ReaverToken(final ReaverToken token) {
        super(token);
    }

    @Override
    public ReaverToken copy() {
        return new ReaverToken(this);
    }
}
