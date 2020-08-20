package mage.cards.k;

import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.common.SimpleActivatedAbility;
import mage.abilities.common.SkipUntapOptionalAbility;
import mage.abilities.condition.common.SourceTappedCondition;
import mage.abilities.costs.CompositeCost;
import mage.abilities.costs.common.TapSourceCost;
import mage.abilities.costs.mana.ManaCostsImpl;
import mage.abilities.decorator.ConditionalContinuousEffect;
import mage.abilities.effects.common.continuous.GainControlTargetEffect;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.*;
import mage.target.common.TargetCreaturePermanent;

import java.util.UUID;

/**
 * @author NinthWorld
 */
public final class KaramaMRV extends CardImpl {

    public KaramaMRV(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{1}{U}");

        this.supertype.add(SuperType.LEGENDARY);
        this.subtype.add(SubType.MUTANT);
        this.subtype.add(SubType.TELEPATH);
        this.power = new MageInt(2);
        this.toughness = new MageInt(2);

        // You may choose not to untap Karma during your untap step.
        this.addAbility(new SkipUntapOptionalAbility());

        // {U}, {T}: Gain control of target creature for as long as Karma remains tapped.
        Ability ability = new SimpleActivatedAbility(Zone.BATTLEFIELD,
                new ConditionalContinuousEffect(
                    new GainControlTargetEffect(Duration.OneUse),
                    SourceTappedCondition.instance,
                    "Gain control of target creature for as long as {this} remains tapped"),
                new CompositeCost(new ManaCostsImpl("{U}"), new TapSourceCost(), "{U}, {T}"));
        ability.addTarget(new TargetCreaturePermanent());
        this.addAbility(ability);
    }

    public KaramaMRV(final KaramaMRV card) {
        super(card);
    }

    @Override
    public KaramaMRV copy() {
        return new KaramaMRV(this);
    }
}