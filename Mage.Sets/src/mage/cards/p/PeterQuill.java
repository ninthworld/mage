package mage.cards.p;

import java.util.UUID;
import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.common.BeginningOfUpkeepTriggeredAbility;
import mage.abilities.common.SimpleActivatedAbility;
import mage.abilities.condition.common.PermanentsOnTheBattlefieldCondition;
import mage.abilities.costs.common.TapSourceCost;
import mage.abilities.costs.mana.ManaCostsImpl;
import mage.abilities.decorator.ConditionalOneShotEffect;
import mage.abilities.effects.common.TransformSourceEffect;
import mage.abilities.effects.common.UntapTargetEffect;
import mage.abilities.effects.common.continuous.GainAbilityTargetEffect;
import mage.abilities.effects.common.continuous.GainControlTargetEffect;
import mage.abilities.keyword.HasteAbility;
import mage.abilities.keyword.TransformAbility;
import mage.constants.*;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.filter.common.FilterCreaturePermanent;

/**
 *
 * @author NinthWorld
 */
public final class PeterQuill extends CardImpl {

    private static final FilterCreaturePermanent filter = new FilterCreaturePermanent("Cosmic creatures");

    static {
        filter.add(SubType.COSMIC.getPredicate());
    }

    public PeterQuill(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{1}{R}");
        
        this.addSuperType(SuperType.LEGENDARY);
        this.subtype.add(SubType.COSMIC);
        this.subtype.add(SubType.HUMAN);
        this.subtype.add(SubType.NOMAD);
        this.power = new MageInt(2);
        this.toughness = new MageInt(2);

        this.transformable = true;
        this.secondSideCardClazz = mage.cards.s.Starlord.class;

        // {2}{R}, {T}: Gain control of target artifact until end of turn. Untap it. It gains haste until end of turn.
        Ability ability = new SimpleActivatedAbility(new GainControlTargetEffect(Duration.EndOfTurn), new ManaCostsImpl("{2}{R}"));
        ability.addCost(new TapSourceCost());
        ability.addEffect(new UntapTargetEffect());
        ability.addEffect(new GainAbilityTargetEffect(HasteAbility.getInstance(), Duration.EndOfTurn));
        this.addAbility(ability);

        // At the beginning of your upkeep, if there are two or more Cosmic creatures on the battlefield, transform Peter Quill.
        this.addAbility(new TransformAbility());
        this.addAbility(new BeginningOfUpkeepTriggeredAbility(
                new ConditionalOneShotEffect(
                        new TransformSourceEffect(true),
                        new PermanentsOnTheBattlefieldCondition(filter, ComparisonType.MORE_THAN, 1),
                        "if there are two or more Cosmic creatures on the battlefield, transform {this}"),
                TargetController.YOU, false));
    }

    private PeterQuill(final PeterQuill card) {
        super(card);
    }

    @Override
    public PeterQuill copy() {
        return new PeterQuill(this);
    }
}
