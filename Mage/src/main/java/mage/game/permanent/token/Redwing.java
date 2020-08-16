package mage.game.permanent.token;

import mage.MageInt;
import mage.abilities.keyword.FlyingAbility;
import mage.constants.CardType;
import mage.constants.SubType;
import mage.constants.SuperType;

/**
 * @author NinthWorld
 */
public final class Redwing extends TokenImpl {

    public Redwing() {
        super("Redwing", "1/1 red and black Bird legendary creature token with flying named Redwing");
        this.setOriginalExpansionSetCode("MCU");
        supertype.add(SuperType.LEGENDARY);
        cardType.add(CardType.CREATURE);
        color.setRed(true);
        color.setBlack(true);
        subtype.add(SubType.BIRD);
        power = new MageInt(1);
        toughness = new MageInt(1);

        // Flying
        this.addAbility(FlyingAbility.getInstance());
    }

    public Redwing(final Redwing token) {
        super(token);
    }

    @Override
    public Redwing copy() {
        return new Redwing(this);
    }
}
