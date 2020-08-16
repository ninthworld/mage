
package mage.game.permanent.token;

import mage.MageInt;
import mage.abilities.common.SimpleActivatedAbility;
import mage.abilities.costs.mana.ColorlessManaCost;
import mage.abilities.effects.keyword.AdaptEffect;
import mage.constants.CardType;
import mage.constants.SubType;
import mage.constants.SuperType;

/**
 *
 * @author NinthWorld
 */
public final class ZabuToken extends TokenImpl {

    public ZabuToken() {
        super("Zabu", "2/3 green Cat legendary creature token");
        this.setOriginalExpansionSetCode("MCU");
        supertype.add(SuperType.LEGENDARY);
        cardType.add(CardType.CREATURE);
        color.setGreen(true);
        subtype.add(SubType.CAT);
        power = new MageInt(2);
        toughness = new MageInt(3);
    }

    public ZabuToken(final ZabuToken token) {
        super(token);
    }

    public ZabuToken copy() {
        return new ZabuToken(this);
    }
}