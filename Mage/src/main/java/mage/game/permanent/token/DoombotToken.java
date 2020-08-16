package mage.game.permanent.token;

import mage.MageInt;
import mage.abilities.keyword.FlyingAbility;
import mage.constants.CardType;
import mage.constants.SubType;

/**
 * @author NinthWorld
 */
public final class DoombotToken extends TokenImpl {

    public DoombotToken() {
        super("Doombot", "1/1 black Doombot artifact creature token with flying");
        this.setOriginalExpansionSetCode("MCU");
        cardType.add(CardType.CREATURE);
        cardType.add(CardType.ARTIFACT);
        color.setBlack(true);
        subtype.add(SubType.DOOMBOT);
        power = new MageInt(1);
        toughness = new MageInt(1);

        // Flying
        this.addAbility(FlyingAbility.getInstance());
    }

    public DoombotToken(final DoombotToken token) {
        super(token);
    }

    @Override
    public DoombotToken copy() {
        return new DoombotToken(this);
    }
}
