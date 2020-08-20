package mage.cards.t;

import java.util.UUID;
import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.common.DiesCreatureTriggeredAbility;
import mage.abilities.common.DiesThisOrAnotherCreatureTriggeredAbility;
import mage.abilities.common.LandfallAbility;
import mage.abilities.effects.common.CreateTokenEffect;
import mage.abilities.effects.common.GainLifeEffect;
import mage.abilities.effects.common.LoseLifeOpponentsEffect;
import mage.abilities.effects.common.PopulateEffect;
import mage.constants.SubType;
import mage.constants.SuperType;
import mage.abilities.keyword.MenaceAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.game.permanent.token.CosmicWarriorToken;

/**
 *
 * @author NinthWorld
 */
public final class ThanosTheMadTitan extends CardImpl {

    public ThanosTheMadTitan(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{4}{B}{B}");
        
        this.addSuperType(SuperType.LEGENDARY);
        this.subtype.add(SubType.COSMIC);
        this.subtype.add(SubType.VILLAIN);
        this.power = new MageInt(7);
        this.toughness = new MageInt(6);

        // Menace
        this.addAbility(new MenaceAbility());

        // Whenever another creature dies, each opponent loses 1 life and you gain 1 life.
        Ability ability1 = new DiesCreatureTriggeredAbility(new LoseLifeOpponentsEffect(1), false, true);
        ability1.addEffect(new GainLifeEffect(1));
        this.addAbility(ability1);

        // <i>Landfall</i> - Whenever a land enters the battlefield under your control, create a 2/2 black Cosmic Warrior creature token, then populate.
        Ability ability2 = new LandfallAbility(new CreateTokenEffect(new CosmicWarriorToken()), false);
        ability2.addEffect(new PopulateEffect());
        this.addAbility(ability2);
    }

    private ThanosTheMadTitan(final ThanosTheMadTitan card) {
        super(card);
    }

    @Override
    public ThanosTheMadTitan copy() {
        return new ThanosTheMadTitan(this);
    }
}
