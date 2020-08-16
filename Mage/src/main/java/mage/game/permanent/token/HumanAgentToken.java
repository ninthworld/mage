package mage.game.permanent.token;

import mage.MageInt;
import mage.abilities.keyword.DefenderAbility;
import mage.constants.CardType;
import mage.constants.SubType;

/**
 * @author NinthWorld
 */
public final class HumanAgentToken extends TokenImpl {

    public HumanAgentToken() {
        super("Human Agent", "1/1 black Human Agent creature token");
        this.setOriginalExpansionSetCode("MCU");
        cardType.add(CardType.CREATURE);
        color.setBlack(true);
        subtype.add(SubType.HUMAN);
        subtype.add(SubType.AGENT);
        power = new MageInt(1);
        toughness = new MageInt(1);
    }

    public HumanAgentToken(final HumanAgentToken token) {
        super(token);
    }

    @Override
    public HumanAgentToken copy() {
        return new HumanAgentToken(this);
    }
}
