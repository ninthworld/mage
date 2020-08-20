package mage.cards.c;

import java.util.UUID;
import mage.MageInt;
import mage.ObjectColor;
import mage.abilities.Ability;
import mage.abilities.common.CantBlockAbility;
import mage.abilities.common.SimpleActivatedAbility;
import mage.abilities.common.SimpleStaticAbility;
import mage.abilities.condition.common.PermanentsOnTheBattlefieldCondition;
import mage.abilities.costs.common.TapSourceCost;
import mage.abilities.costs.mana.ManaCostsImpl;
import mage.abilities.decorator.ConditionalContinuousEffect;
import mage.abilities.decorator.ConditionalReplacementEffect;
import mage.abilities.effects.ContinuousRuleModifyingEffectImpl;
import mage.abilities.effects.ReplacementEffectImpl;
import mage.abilities.effects.common.ExileTargetEffect;
import mage.abilities.effects.common.ExileTargetForSourceEffect;
import mage.abilities.effects.common.ReturnToBattlefieldUnderOwnerControlTargetEffect;
import mage.abilities.effects.common.continuous.GainAbilitySourceEffect;
import mage.abilities.effects.common.counter.AddCountersSourceEffect;
import mage.abilities.effects.common.counter.AddCountersTargetEffect;
import mage.abilities.effects.common.ruleModifying.CantHaveCountersSourceEffect;
import mage.abilities.keyword.CantBeBlockedSourceAbility;
import mage.abilities.keyword.ProtectionAbility;
import mage.constants.*;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.counters.CounterType;
import mage.filter.common.FilterCreaturePermanent;
import mage.filter.predicate.mageobject.NamePredicate;
import mage.game.Game;
import mage.game.events.GameEvent;
import mage.game.permanent.Permanent;
import mage.target.common.TargetAttackingCreature;

/**
 *
 * @author NinthWorld
 */
public final class Cloak extends CardImpl {

    private static final FilterCreaturePermanent filter = new FilterCreaturePermanent();

    static {
        filter.add(TargetController.YOU.getControllerPredicate());
        filter.add(new NamePredicate("Dagger"));
    }

    public Cloak(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{3}{B}");
        
        this.addSuperType(SuperType.LEGENDARY);
        this.subtype.add(SubType.SHADE);
        this.subtype.add(SubType.MUTANT);
        this.subtype.add(SubType.ROGUE);
        this.power = new MageInt(3);
        this.toughness = new MageInt(5);

        // Cloak can't block and can't be blocked.
        this.addAbility(new CantBlockAbility());
        this.addAbility(new CantBeBlockedSourceAbility());

        // {1}{B}, {T}: Exile target attacking creature, then return it to the battlefield tapped with a -1/-1 counter on it. Put a -1/-1 counter on Cloak.
        Ability ability = new SimpleActivatedAbility(new ExileTargetForSourceEffect(), new ManaCostsImpl("{1}{B}"));
        ability.addCost(new TapSourceCost());
        ability.addTarget(new TargetAttackingCreature());
        ability.addEffect(new ReturnToBattlefieldUnderOwnerControlTargetEffect(true, false));
        ability.addEffect(new AddCountersTargetEffect(CounterType.M1M1.createInstance()));
        ability.addEffect(new AddCountersSourceEffect(CounterType.M1M1.createInstance()));

        // Cloak can't have -1/-1 counters placed on him as long as you control a creature named Dagger.
        this.addAbility(new SimpleStaticAbility(Zone.BATTLEFIELD,
                new ConditionalReplacementEffect(
                        new CloakEffect(),
                        new PermanentsOnTheBattlefieldCondition(filter))));
    }

    private Cloak(final Cloak card) {
        super(card);
    }

    @Override
    public Cloak copy() {
        return new Cloak(this);
    }
}

class CloakEffect extends ReplacementEffectImpl {

    public CloakEffect() {
        super(Duration.WhileOnBattlefield, Outcome.PreventDamage);
        staticText = "{this} can't have -1/-1 counters placed on him as long as you control a creature named Dagger";
    }

    public CloakEffect(final CloakEffect effect) {
        super(effect);
    }

    @Override
    public CloakEffect copy() {
        return new CloakEffect(this);
    }

    @Override
    public boolean replaceEvent(GameEvent event, Ability source, Game game) {
        return true;
    }

    @Override
    public boolean checksEventType(GameEvent event, Game game) {
        return event.getType() == GameEvent.EventType.ADD_COUNTERS;
    }

    @Override
    public boolean applies(GameEvent event, Ability source, Game game) {
        if (event.getData().equals(CounterType.M1M1.getName())) {
            UUID sourceId = source.getSourceId();
            return sourceId != null && sourceId.equals(event.getTargetId());
        }
        return false;
    }
}