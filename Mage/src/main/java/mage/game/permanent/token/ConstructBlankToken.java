

package mage.game.permanent.token;

import mage.MageInt;
import mage.abilities.keyword.DefenderAbility;
import mage.constants.CardType;
import mage.constants.SubType;

/**
 *
 * @author NinthWorld
 */
public final class ConstructBlankToken extends TokenImpl {

    public ConstructBlankToken() {
        super("Construct", "1/1 colorless Construct artifact creature token");
        this.setOriginalExpansionSetCode("MCU");
        cardType.add(CardType.ARTIFACT);
        cardType.add(CardType.CREATURE);
        subtype.add(SubType.CONSTRUCT);
        power = new MageInt(1);
        toughness = new MageInt(1);
    }

    public ConstructBlankToken(final ConstructBlankToken token) {
        super(token);
    }

    public ConstructBlankToken copy() {
        return new ConstructBlankToken(this);
    }
}
