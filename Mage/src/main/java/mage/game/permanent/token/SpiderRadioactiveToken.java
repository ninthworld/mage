

package mage.game.permanent.token;

import mage.MageInt;
import mage.abilities.keyword.DefenderAbility;
import mage.abilities.keyword.RadioactiveAbility;
import mage.abilities.keyword.ReachAbility;
import mage.constants.CardType;
import mage.constants.SubType;

/**
 *
 * @author NinthWorld
 */
public final class SpiderRadioactiveToken extends TokenImpl {

    public SpiderRadioactiveToken() {
        super("Spider", "1/2 green Spider creature token with reach and radioactive");
        this.setOriginalExpansionSetCode("MRV");
        cardType.add(CardType.CREATURE);
        color.setGreen(true);
        subtype.add(SubType.SPIDER);
        power = new MageInt(1);
        toughness = new MageInt(2);

        // Reach
        this.addAbility(ReachAbility.getInstance());

        // Radioactive
        this.addAbility(new RadioactiveAbility());
    }

    public SpiderRadioactiveToken(final SpiderRadioactiveToken token) {
        super(token);
    }

    public SpiderRadioactiveToken copy() {
        return new SpiderRadioactiveToken(this);
    }
}
