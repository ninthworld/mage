package mage.game.permanent.token;

import mage.MageInt;
import mage.abilities.common.BecomesTargetTriggeredAbility;
import mage.abilities.effects.common.SacrificeSourceEffect;
import mage.abilities.effects.common.continuous.BoostSourceEffect;
import mage.constants.CardType;
import mage.constants.Duration;
import mage.constants.SubType;

/**
 * @author NinthWorld
 */
public final class IllusionSacrificeToken extends TokenImpl {

    public IllusionSacrificeToken() {
        super("Illusion", "1/1 blue Illusion creature token with \"Whenever this creature becomes the target of a spell or ability, sacrifice it.\"");
        this.setOriginalExpansionSetCode("MCU");
        cardType.add(CardType.CREATURE);
        color.setBlue(true);
        subtype.add(SubType.ILLUSION);
        power = new MageInt(1);
        toughness = new MageInt(1);

        // Whenever this creature becomes the target of a spell or ability, sacrifice it.
        this.addAbility(new BecomesTargetTriggeredAbility(new SacrificeSourceEffect()));
    }

    public IllusionSacrificeToken(final IllusionSacrificeToken token) {
        super(token);
    }

    @Override
    public IllusionSacrificeToken copy() {
        return new IllusionSacrificeToken(this);
    }
}
