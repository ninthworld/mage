package mage.game.permanent.token;

import mage.MageInt;
import mage.abilities.keyword.IndestructibleAbility;
import mage.constants.CardType;
import mage.constants.SubType;
import mage.constants.SuperType;

/**
 * @author NinthWorld
 */
public final class ChthonToken extends TokenImpl {

    public ChthonToken() {
        super("Chthon", "7/7 black Demon God legendary creature token with indestructible");
        this.setOriginalExpansionSetCode("MRV");
        supertype.add(SuperType.LEGENDARY);
        cardType.add(CardType.CREATURE);
        color.setBlack(true);
        subtype.add(SubType.DEMON);
        subtype.add(SubType.GOD);
        power = new MageInt(7);
        toughness = new MageInt(7);

        // Indestructible
        this.addAbility(IndestructibleAbility.getInstance());
    }

    public ChthonToken(final ChthonToken token) {
        super(token);
    }

    @Override
    public ChthonToken copy() {
        return new ChthonToken(this);
    }
}
