package mage.game.permanent.token;

import mage.MageInt;
import mage.abilities.keyword.ChangelingAbility;
import mage.constants.CardType;
import mage.constants.SubType;

/**
 * @author NinthWorld
 */
public final class ElementalToken extends TokenImpl {

    public ElementalToken() {
        super("Elemental", "1/1 red Elemental creature token");
        this.setOriginalExpansionSetCode("MCU");
        cardType.add(CardType.CREATURE);
        color.setRed(true);
        subtype.add(SubType.ELEMENTAL);
        power = new MageInt(1);
        toughness = new MageInt(1);
    }

    public ElementalToken(final ElementalToken token) {
        super(token);
    }

    @Override
    public ElementalToken copy() {
        return new ElementalToken(this);
    }
}
