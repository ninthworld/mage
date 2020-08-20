package mage.cards.d;

import java.util.UUID;
import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.TriggeredAbilityImpl;
import mage.abilities.common.SimpleActivatedAbility;
import mage.abilities.condition.Condition;
import mage.abilities.costs.mana.ManaCostsImpl;
import mage.abilities.decorator.ConditionalOneShotEffect;
import mage.abilities.effects.common.RegenerateTargetEffect;
import mage.abilities.effects.common.TransformSourceEffect;
import mage.abilities.effects.common.counter.AddCountersSourceEffect;
import mage.abilities.keyword.TransformAbility;
import mage.constants.*;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.counters.CounterType;
import mage.game.Game;
import mage.game.events.GameEvent;
import mage.game.permanent.Permanent;
import mage.target.common.TargetControlledCreaturePermanent;

/**
 *
 * @author NinthWorld
 */
public final class DoctorCurtConnors extends CardImpl {

    public DoctorCurtConnors(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{G}");
        
        this.addSuperType(SuperType.LEGENDARY);
        this.subtype.add(SubType.HUMAN);
        this.subtype.add(SubType.SCIENTIST);
        this.power = new MageInt(0);
        this.toughness = new MageInt(1);

        this.transformable = true;
        this.secondSideCardClazz = mage.cards.t.TheLizard.class;

        // {2}{G}: Regenerate target creature you control.
        Ability ability = new SimpleActivatedAbility(new RegenerateTargetEffect(), new ManaCostsImpl("{2}{G}"));
        ability.addTarget(new TargetControlledCreaturePermanent());

        // Whenever a creature you control regenerates, put a +1/+1 counter on Doctor Curt Connors. Then if there are two or more +1/+1 counters on him, transform him.
        this.addAbility(new TransformAbility());
        this.addAbility(new DoctorCurtConnorsTriggeredAbility());
    }

    private DoctorCurtConnors(final DoctorCurtConnors card) {
        super(card);
    }

    @Override
    public DoctorCurtConnors copy() {
        return new DoctorCurtConnors(this);
    }
}

class DoctorCurtConnorsTriggeredAbility extends TriggeredAbilityImpl {

    public DoctorCurtConnorsTriggeredAbility() {
        super(Zone.BATTLEFIELD, new AddCountersSourceEffect(CounterType.P1P1.createInstance()));
        this.addEffect(new ConditionalOneShotEffect(new TransformSourceEffect(true), DoctorCurtConnorsCounterCondition.instance));
    }

    public DoctorCurtConnorsTriggeredAbility(final DoctorCurtConnorsTriggeredAbility effect) {
        super(effect);
    }

    @Override
    public boolean checkEventType(GameEvent event, Game game) {
        return event.getType() == GameEvent.EventType.REGENERATE;
    }

    @Override
    public boolean checkTrigger(GameEvent event, Game game) {
        Permanent permanent = game.getPermanentOrLKIBattlefield(event.getTargetId());
        return permanent != null && permanent.isCreature() && permanent.getControllerId().equals(getControllerId());
    }

    @Override
    public DoctorCurtConnorsTriggeredAbility copy() {
        return new DoctorCurtConnorsTriggeredAbility(this);
    }
}

enum DoctorCurtConnorsCounterCondition implements Condition {
    instance;

    @Override
    public boolean apply(Game game, Ability source) {
        Permanent permanent = game.getPermanent(source.getSourceId());
        if (permanent != null) {
            return permanent.getCounters(game).getCount(CounterType.P1P1) >= 2;
        }
        return false;
    }
}
