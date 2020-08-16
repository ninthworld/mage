package mage.cards.a;

import java.util.UUID;

import mage.abilities.TriggeredAbilityImpl;
import mage.abilities.common.DiesCreatureTriggeredAbility;
import mage.abilities.common.TapForManaAllTriggeredManaAbility;
import mage.abilities.effects.common.counter.AddCountersTargetEffect;
import mage.abilities.effects.mana.AddConditionalColorlessManaEffect;
import mage.abilities.keyword.GraftAbility;
import mage.abilities.mana.ColorlessManaAbility;
import mage.constants.SuperType;
import mage.abilities.keyword.IndestructibleAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.Zone;
import mage.counters.Counter;
import mage.counters.CounterType;
import mage.counters.Counters;
import mage.game.Game;
import mage.game.events.GameEvent;
import mage.game.events.ZoneChangeEvent;
import mage.game.permanent.Permanent;
import mage.target.common.TargetControlledCreaturePermanent;

/**
 *
 * @author NinthWorld
 */
public final class Adamantium extends CardImpl {

    public Adamantium(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.ARTIFACT}, "{2}");
        
        this.addSuperType(SuperType.LEGENDARY);

        // Indestructible
        this.addAbility(IndestructibleAbility.getInstance());

        // Whenever a creature you control with one or more +1/+1 counters on it dies, you may put those counters on Adamantium.
        this.addAbility(new AdamantiumTriggeredAbility());

        // {T}: Add {C}.
        this.addAbility(new ColorlessManaAbility());

        // Graft 1
        this.addAbility(new GraftAbility(this, 1));
    }

    private Adamantium(final Adamantium card) {
        super(card);
    }

    @Override
    public Adamantium copy() {
        return new Adamantium(this);
    }
}

class AdamantiumTriggeredAbility extends TriggeredAbilityImpl {

    public AdamantiumTriggeredAbility() {
        super(Zone.BATTLEFIELD, null);
    }

    public AdamantiumTriggeredAbility(final AdamantiumTriggeredAbility ability) {
        super(ability);
    }

    @Override
    public AdamantiumTriggeredAbility copy() {
        return new AdamantiumTriggeredAbility(this);
    }

    @Override
    public boolean checkEventType(GameEvent event, Game game) {
        return event.getType() == GameEvent.EventType.ZONE_CHANGE;
    }

    @Override
    public boolean checkTrigger(GameEvent event, Game game) {
        if (((ZoneChangeEvent) event).getToZone() == Zone.GRAVEYARD
                && ((ZoneChangeEvent) event).getFromZone() == Zone.BATTLEFIELD) {
            Permanent permanent = (Permanent) game.getLastKnownInformation(event.getTargetId(), Zone.BATTLEFIELD);
            if (permanent != null && permanent.isControlledBy(this.getControllerId()) && permanent.isCreature() && permanent.getCounters(game).containsKey(CounterType.P1P1)) {
                Counter counters = permanent.getCounters(game).get(CounterType.P1P1);
                if (counters.getCount() > 0) {
                    this.getTargets().clear();
                    this.addTarget(new TargetControlledCreaturePermanent());
                    this.getEffects().clear();
                    this.addEffect(new AddCountersTargetEffect(CounterType.P1P1.createInstance(counters.getCount())));
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public String getRule() {
        return "Whenever a creature you control with one or more +1/+1 counters on it dies, you may put those counters on Adamantium.";
    }
}
