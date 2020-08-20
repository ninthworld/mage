package mage.game.permanent.token;

import mage.MageInt;
import mage.abilities.keyword.FlyingAbility;
import mage.constants.CardType;
import mage.constants.SubType;
import mage.constants.SuperType;

/**
 * @author NinthWorld
 */
public final class OnslaughtToken extends TokenImpl {

    public OnslaughtToken() {
        super("Onslaught", "X/X blue and black Mutant Nightmare legendary creature token named Onslaught");
        this.setOriginalExpansionSetCode("MRV");
        supertype.add(SuperType.LEGENDARY);
        cardType.add(CardType.CREATURE);
        color.setBlue(true);
        color.setBlack(true);
        subtype.add(SubType.MUTANT);
        subtype.add(SubType.NIGHTMARE);
        power = new MageInt(0);
        toughness = new MageInt(0);
    }

    public OnslaughtToken(final OnslaughtToken token) {
        super(token);
    }

    @Override
    public OnslaughtToken copy() {
        return new OnslaughtToken(this);
    }
}
