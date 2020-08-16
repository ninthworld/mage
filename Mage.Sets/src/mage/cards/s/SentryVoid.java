package mage.cards.s;

import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.common.BeginningOfUpkeepTriggeredAbility;
import mage.abilities.common.SimpleActivatedAbility;
import mage.abilities.costs.common.PayEnergyCost;
import mage.abilities.costs.mana.ManaCostsImpl;
import mage.abilities.effects.common.ExileTargetEffect;
import mage.abilities.effects.common.SacrificeControllerEffect;
import mage.abilities.effects.common.TransformSourceEffect;
import mage.abilities.keyword.FlyingAbility;
import mage.abilities.keyword.GeneratorAbility;
import mage.abilities.keyword.IndestructibleAbility;
import mage.abilities.keyword.SunburstAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.*;
import mage.filter.FilterPermanent;
import mage.filter.common.FilterCreaturePermanent;
import mage.filter.predicate.Predicates;
import mage.filter.predicate.permanent.AnotherPredicate;
import mage.target.common.TargetNonlandPermanent;

import java.util.UUID;

/**
 *
 * @author NinthWorld
 */
public final class SentryVoid extends CardImpl {

    private static final FilterPermanent filter = new FilterPermanent("another nonland permanent");

    static {
        filter.add(AnotherPredicate.instance);
    }

    public SentryVoid(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "");

        this.addSuperType(SuperType.LEGENDARY);
        this.subtype.add(SubType.HUMAN);
        this.subtype.add(SubType.SHADE);
        this.subtype.add(SubType.WARRIOR);
        this.power = new MageInt(5);
        this.toughness = new MageInt(5);

        this.color.setBlack(true);

        this.nightCard = true;
        this.transformable = true;

        // Flying
        this.addAbility(FlyingAbility.getInstance());

        // Indestructible
        this.addAbility(IndestructibleAbility.getInstance());

        // At the beginning of your upkeep, sacrifice another nonland permanent.
        this.addAbility(new BeginningOfUpkeepTriggeredAbility(Zone.BATTLEFIELD, new SacrificeControllerEffect(filter, 1, ""), TargetController.YOU, false));

        // Pay {E}{E}{E}: Exile target nonland permanent.
        Ability ability = new SimpleActivatedAbility(new ExileTargetEffect(), new PayEnergyCost(3));
        ability.addTarget(new TargetNonlandPermanent());
        this.addAbility(ability);

        // {4}{W}{W}: Transform Void.
        this.addAbility(new SimpleActivatedAbility(new TransformSourceEffect(false), new ManaCostsImpl("{4}{W}{W}")));
    }

    private SentryVoid(final SentryVoid card) {
        super(card);
    }

    @Override
    public SentryVoid copy() {
        return new SentryVoid(this);
    }
}
