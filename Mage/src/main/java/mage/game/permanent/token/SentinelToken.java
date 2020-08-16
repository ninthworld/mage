
package mage.game.permanent.token;

import mage.MageInt;
import mage.abilities.common.SimpleActivatedAbility;
import mage.abilities.costs.mana.ColorlessManaCost;
import mage.abilities.costs.mana.GenericManaCost;
import mage.abilities.effects.keyword.AdaptEffect;
import mage.abilities.keyword.GraftAbility;
import mage.constants.CardType;
import mage.constants.SubType;

/**
 *
 * @author NinthWorld
 */
public final class SentinelToken extends TokenImpl {

    public SentinelToken() {
        super("Sentinel", "3/3 colorless Sentinel artifact creature token with \"{4}: Adapt 2\"");
        this.setOriginalExpansionSetCode("MCU");
        cardType.add(CardType.CREATURE);
        cardType.add(CardType.ARTIFACT);
        subtype.add(SubType.SENTINEL);
        power = new MageInt(3);
        toughness = new MageInt(3);

        // {4}: Adapt 2
        this.addAbility(new SimpleActivatedAbility(new AdaptEffect(2), new GenericManaCost(4)));
    }

    public SentinelToken(final SentinelToken token) {
        super(token);
    }

    public SentinelToken copy() {
        return new SentinelToken(this);
    }
}