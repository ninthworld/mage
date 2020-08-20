package mage.cards.m;

import java.util.UUID;
import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.DelayedTriggeredAbility;
import mage.abilities.common.BeginningOfUpkeepTriggeredAbility;
import mage.abilities.common.SimpleActivatedAbility;
import mage.abilities.costs.common.RemoveCountersSourceCost;
import mage.abilities.costs.mana.ManaCostsImpl;
import mage.abilities.effects.common.CreateDelayedTriggeredAbilityEffect;
import mage.abilities.effects.common.DrawCardSourceControllerEffect;
import mage.abilities.effects.common.ExileGraveyardAllTargetPlayerEffect;
import mage.abilities.effects.common.continuous.BoostTargetEffect;
import mage.abilities.effects.common.counter.AddCountersSourceEffect;
import mage.abilities.effects.common.counter.AddCountersTargetEffect;
import mage.constants.*;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.counters.CounterType;
import mage.game.Game;
import mage.game.events.GameEvent;
import mage.game.events.ZoneChangeEvent;
import mage.target.Target;
import mage.target.TargetPlayer;
import mage.target.common.TargetCreaturePermanent;

/**
 *
 * @author NinthWorld
 */
public final class Marrow extends CardImpl {

    public Marrow(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{1}{B/G}{B/G}");
        
        this.addSuperType(SuperType.LEGENDARY);
        this.subtype.add(SubType.SKELETON);
        this.subtype.add(SubType.MUTANT);
        this.subtype.add(SubType.ASSASSIN);
        this.power = new MageInt(3);
        this.toughness = new MageInt(4);

        // At the beginning of your upkeep, put a +1/+1 counter on Marrow.
        this.addAbility(new BeginningOfUpkeepTriggeredAbility(new AddCountersSourceEffect(CounterType.P1P1.createInstance()), TargetController.YOU, false));

        // {B}, Remove a +1/+1 counter from Marrow: Target creature gets -1/-1 until end of turn. If that creature dies this turn, draw a card.
        Ability ability = new SimpleActivatedAbility(new BoostTargetEffect(-1, -1, Duration.EndOfTurn), new ManaCostsImpl("{B}"));
        ability.addCost(new RemoveCountersSourceCost(CounterType.P1P1.createInstance()));
        ability.addTarget(new TargetCreaturePermanent());
        ability.addEffect(new CreateDelayedTriggeredAbilityEffect(new MarrowDelayedTriggeredAbility()));
    }

    private Marrow(final Marrow card) {
        super(card);
    }

    @Override
    public Marrow copy() {
        return new Marrow(this);
    }
}

class MarrowDelayedTriggeredAbility extends DelayedTriggeredAbility {

    public MarrowDelayedTriggeredAbility() {
        super(new DrawCardSourceControllerEffect(1), Duration.EndOfTurn, true);
    }

    public MarrowDelayedTriggeredAbility(MarrowDelayedTriggeredAbility ability) {
        super(ability);
    }

    @Override
    public boolean checkEventType(GameEvent event, Game game) {
        return event.getType() == GameEvent.EventType.ZONE_CHANGE;
    }

    @Override
    public boolean checkTrigger(GameEvent event, Game game) {
        ZoneChangeEvent zEvent = (ZoneChangeEvent) event;
        if (zEvent.isDiesEvent() && zEvent.getTarget() != null && zEvent.getTargetId().equals(getTargets().getFirstTarget())) {
            this.getTargets().clear();
            return true;
        }
        return false;
    }

    @Override
    public MarrowDelayedTriggeredAbility copy() {
        return new MarrowDelayedTriggeredAbility(this);
    }

    @Override
    public String getRule() {
        return "If that creature dies this turn, draw a card.";
    }
}