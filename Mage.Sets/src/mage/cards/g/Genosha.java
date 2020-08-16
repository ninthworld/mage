package mage.cards.g;

import java.util.UUID;

import mage.abilities.Ability;
import mage.abilities.TriggeredAbilityImpl;
import mage.abilities.common.DiesCreatureTriggeredAbility;
import mage.abilities.common.SimpleActivatedAbility;
import mage.abilities.costs.CompositeCost;
import mage.abilities.costs.common.TapSourceCost;
import mage.abilities.costs.mana.GenericManaCost;
import mage.abilities.dynamicvalue.common.CountersCount;
import mage.abilities.effects.common.DrawCardSourceControllerEffect;
import mage.abilities.effects.common.TransformSourceEffect;
import mage.abilities.effects.common.counter.AddCountersTargetEffect;
import mage.abilities.mana.SimpleManaAbility;
import mage.abilities.mana.WhiteManaAbility;
import mage.constants.SubType;
import mage.constants.SuperType;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.Zone;
import mage.counters.CounterType;
import mage.filter.FilterPermanent;
import mage.filter.common.FilterCreaturePermanent;
import mage.game.Game;
import mage.game.events.GameEvent;
import mage.game.events.ZoneChangeEvent;
import mage.game.permanent.Permanent;
import mage.target.TargetPermanent;

/**
 *
 * @author NinthWorld
 */
public final class Genosha extends CardImpl {

    private static final FilterPermanent filter = new FilterPermanent();

    static {
        filter.add(SubType.MUTANT.getPredicate());
    }

    public Genosha(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.LAND}, "");
        
        this.addSuperType(SuperType.LEGENDARY);

        this.color.setWhite(true);

        this.transformable = true;
        this.secondSideCardClazz = mage.cards.n.Necrosha.class;

        // {T}: Add {W}.
        this.addAbility(new WhiteManaAbility());

        // {3}, {T}: Put a -1/-1 counter on target Mutant.
        Ability ability = new SimpleActivatedAbility(new AddCountersTargetEffect(CounterType.M1M1.createInstance()), new CompositeCost(new GenericManaCost(3), new TapSourceCost(), "{3}, {T}"));
        ability.addTarget(new TargetPermanent(filter));
        this.addAbility(ability);

        // Whenever a creature with a -1/-1 counter on it dies, transform Genosha.
        this.addAbility(new GenoshaTriggeredAbility());
    }

    private Genosha(final Genosha card) {
        super(card);
    }

    @Override
    public Genosha copy() {
        return new Genosha(this);
    }
}


class GenoshaTriggeredAbility extends TriggeredAbilityImpl {

    public GenoshaTriggeredAbility() {
        super(Zone.BATTLEFIELD, new TransformSourceEffect(true), false);
    }

    public GenoshaTriggeredAbility(GenoshaTriggeredAbility ability) {
        super(ability);
    }

    @Override
    public GenoshaTriggeredAbility copy() {
        return new GenoshaTriggeredAbility(this);
    }

    @Override
    public boolean checkEventType(GameEvent event, Game game) {
        return event.getType() == GameEvent.EventType.ZONE_CHANGE;
    }

    @Override
    public boolean checkTrigger(GameEvent event, Game game) {
        ZoneChangeEvent zEvent = (ZoneChangeEvent) event;
        if (zEvent.isDiesEvent()) {
            Permanent permanent = game.getPermanentOrLKIBattlefield(zEvent.getTargetId());
            if (permanent != null
                    && permanent.isCreature()
                    && permanent.getCounters(game).containsKey(CounterType.M1M1)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String getRule() {
        return "Whenever a creature with a -1/-1 counter on it dies, transform Genosha.";
    }
}