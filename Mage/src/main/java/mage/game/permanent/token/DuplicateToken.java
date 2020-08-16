package mage.game.permanent.token;

import mage.MageInt;
import mage.abilities.keyword.ChangelingAbility;
import mage.abilities.keyword.FlyingAbility;
import mage.constants.CardType;
import mage.constants.SubType;

/**
 * @author NinthWorld
 */
public final class DuplicateToken extends TokenImpl {

    public DuplicateToken() {
        super("Duplicate", "3/3 blue Duplicate creature token with changeling");
        this.setOriginalExpansionSetCode("MCU");
        cardType.add(CardType.CREATURE);
        color.setBlue(true);
        subtype.add(SubType.DUPLICATE);
        power = new MageInt(3);
        toughness = new MageInt(3);

        // Changeling
        this.addAbility(ChangelingAbility.getInstance());
    }

    public DuplicateToken(final DuplicateToken token) {
        super(token);
    }

    @Override
    public DuplicateToken copy() {
        return new DuplicateToken(this);
    }
}
