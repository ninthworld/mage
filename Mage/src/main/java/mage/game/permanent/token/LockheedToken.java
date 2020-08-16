package mage.game.permanent.token;

import mage.MageInt;
import mage.abilities.keyword.FlyingAbility;
import mage.constants.CardType;
import mage.constants.SubType;
import mage.constants.SuperType;

/**
 * @author NinthWorld
 */
public final class LockheedToken extends TokenImpl {

    public LockheedToken() {
        super("Lockheed", "1/1 blue Drake legendary creature token with flying named Lockheed");
        this.setOriginalExpansionSetCode("MCU");
        supertype.add(SuperType.LEGENDARY);
        cardType.add(CardType.CREATURE);
        color.setBlue(true);
        subtype.add(SubType.DRAKE);
        power = new MageInt(1);
        toughness = new MageInt(1);

        // Flying
        this.addAbility(FlyingAbility.getInstance());
    }

    public LockheedToken(final LockheedToken token) {
        super(token);
    }

    @Override
    public LockheedToken copy() {
        return new LockheedToken(this);
    }
}
