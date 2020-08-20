package mage.cards.c;

import java.util.UUID;
import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.common.EntersBattlefieldAbility;
import mage.abilities.common.SimpleActivatedAbility;
import mage.abilities.condition.common.SourceOnBattlefieldControlUnchangedCondition;
import mage.abilities.costs.common.TapTargetCost;
import mage.abilities.costs.mana.ManaCostsImpl;
import mage.abilities.decorator.ConditionalContinuousEffect;
import mage.abilities.effects.common.DrawCardSourceControllerEffect;
import mage.abilities.effects.common.continuous.BecomesChosenCreatureTypeSourceEffect;
import mage.abilities.effects.common.continuous.GainControlTargetEffect;
import mage.constants.*;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.filter.StaticFilters;
import mage.target.common.TargetArtifactPermanent;
import mage.target.common.TargetControlledPermanent;

/**
 *
 * @author NinthWorld
 */
public final class Chameleon extends CardImpl {

    public Chameleon(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{2}{U}{U}");
        
        this.addSuperType(SuperType.LEGENDARY);
        this.subtype.add(SubType.HUMAN);
        this.subtype.add(SubType.SHAPESHIFTER);
        this.subtype.add(SubType.AGENT);
        this.power = new MageInt(2);
        this.toughness = new MageInt(2);

        // When Chameleon enters the battlefield, you may gain control of target artifact for as long as you control Chameleon.
        Ability ability = new EntersBattlefieldAbility(
                new ConditionalContinuousEffect(
                    new GainControlTargetEffect(Duration.Custom),
                    new SourceOnBattlefieldControlUnchangedCondition(),
                    "you may gain control of target artifact for as long as you control {this}"),
                true);
        ability.addTarget(new TargetArtifactPermanent());
        this.addAbility(ability);

        // Tap two untapped artifacts you control: Draw a card.
        this.addAbility(new SimpleActivatedAbility(
                new DrawCardSourceControllerEffect(1),
                new TapTargetCost(new TargetControlledPermanent(2, StaticFilters.FILTER_CONTROLLED_PERMANENT_ARTIFACT))));

        // {U}: Chameleon becomes the creature type of your choice until end of turn.
        this.addAbility(new SimpleActivatedAbility(new BecomesChosenCreatureTypeSourceEffect(), new ManaCostsImpl("{U}")));
    }

    private Chameleon(final Chameleon card) {
        super(card);
    }

    @Override
    public Chameleon copy() {
        return new Chameleon(this);
    }
}
