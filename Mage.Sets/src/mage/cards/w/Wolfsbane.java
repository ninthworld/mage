package mage.cards.w;

import java.util.UUID;
import mage.MageInt;
import mage.abilities.TriggeredAbility;
import mage.abilities.common.BeginningOfUpkeepTriggeredAbility;
import mage.abilities.common.SimpleActivatedAbility;
import mage.abilities.condition.common.TwoOrMoreSpellsWereCastLastTurnCondition;
import mage.abilities.costs.mana.ManaCostsImpl;
import mage.abilities.decorator.ConditionalInterveningIfTriggeredAbility;
import mage.abilities.effects.common.RegenerateSourceEffect;
import mage.abilities.effects.common.TransformSourceEffect;
import mage.abilities.keyword.TransformAbility;
import mage.constants.SubType;
import mage.constants.SuperType;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.TargetController;

/**
 *
 * @author NinthWorld
 */
public final class Wolfsbane extends CardImpl {

    public Wolfsbane(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "");
        
        this.addSuperType(SuperType.LEGENDARY);
        this.subtype.add(SubType.WEREWOLF);
        this.subtype.add(SubType.MUTANT);
        this.subtype.add(SubType.AGENT);
        this.power = new MageInt(3);
        this.toughness = new MageInt(3);

        this.nightCard = true;
        this.transformable = true;

        // At the beginning of each upkeep, if a player cast two or more spells last turn, transform Wolfsbane.
        TriggeredAbility ability = new BeginningOfUpkeepTriggeredAbility(new TransformSourceEffect(false), TargetController.ANY, false);
        this.addAbility(new ConditionalInterveningIfTriggeredAbility(ability, TwoOrMoreSpellsWereCastLastTurnCondition.instance, TransformAbility.TWO_OR_MORE_SPELLS_TRANSFORM_RULE));

        // {1}{G}: Regenerate Wolfsbane.
        this.addAbility(new SimpleActivatedAbility(new RegenerateSourceEffect(), new ManaCostsImpl("{1}{G}")));
    }

    private Wolfsbane(final Wolfsbane card) {
        super(card);
    }

    @Override
    public Wolfsbane copy() {
        return new Wolfsbane(this);
    }
}
