package mage.cards.b;

import java.util.UUID;
import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.common.SimpleActivatedAbility;
import mage.abilities.common.SimpleStaticAbility;
import mage.abilities.costs.CompositeCost;
import mage.abilities.costs.common.PayAnyEnergyCost;
import mage.abilities.costs.common.TapSourceCost;
import mage.abilities.dynamicvalue.AdditiveDynamicValue;
import mage.abilities.dynamicvalue.DynamicValue;
import mage.abilities.dynamicvalue.common.GetXValue;
import mage.abilities.effects.PreventionEffectImpl;
import mage.abilities.effects.common.DamageTargetEffect;
import mage.constants.Duration;
import mage.constants.SubType;
import mage.constants.SuperType;
import mage.abilities.keyword.VigilanceAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.counters.CounterType;
import mage.game.Game;
import mage.game.events.DamageEvent;
import mage.game.events.GameEvent;
import mage.players.Player;
import mage.target.common.TargetCreaturePermanent;

/**
 *
 * @author NinthWorld
 */
public final class BishopTheLastXMan extends CardImpl {

    public BishopTheLastXMan(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{1}{R}{W}");
        
        this.addSuperType(SuperType.LEGENDARY);
        this.subtype.add(SubType.MUTANT);
        this.subtype.add(SubType.SOLDIER);
        this.power = new MageInt(3);
        this.toughness = new MageInt(3);

        // Vigilance
        this.addAbility(VigilanceAbility.getInstance());

        // If noncombat damage would be dealt to Bishop, the Last X-Man, prevent that damage. You get {E} for each 1 damage prevented this way.
        this.addAbility(new SimpleStaticAbility(new BishopTheLastXManPreventEffect()));

        // Pay any amount of {E}, {T}: Bishop, the Last X-Man deals damage to target creature equal to twice the amount of {E} paid this way.
        DynamicValue xValue = GetXValue.instance;
        Ability ability = new SimpleActivatedAbility(
                new DamageTargetEffect(new AdditiveDynamicValue(xValue, xValue)),
                new PayAnyEnergyCost());
        ability.addCost(new TapSourceCost());
        ability.addTarget(new TargetCreaturePermanent());
        this.addAbility(ability);
    }

    private BishopTheLastXMan(final BishopTheLastXMan card) {
        super(card);
    }

    @Override
    public BishopTheLastXMan copy() {
        return new BishopTheLastXMan(this);
    }
}

class BishopTheLastXManPreventEffect extends PreventionEffectImpl {

    public BishopTheLastXManPreventEffect() {
        super(Duration.WhileOnBattlefield);
        this.staticText = "If noncombat damage would be dealt to {this}, prevent that that damage. You get {E} for each 1 damage prevented this way.";
    }

    public BishopTheLastXManPreventEffect(BishopTheLastXManPreventEffect effect) {
        super(effect);
    }

    @Override
    public boolean replaceEvent(GameEvent event, Ability source, Game game) {
        int damage = preventDamageAction(event, source, game).getPreventedDamage();
        if (damage > 0) {
            Player controller = game.getPlayer(source.getControllerId());
            if (controller != null) {
                controller.addCounters(CounterType.ENERGY.createInstance(damage), game);
            }
        }
        return false;
    }

    @Override
    public boolean apply(Game game, Ability source) {
        return true;
    }

    @Override
    public boolean applies(GameEvent event, Ability source, Game game) {
        if (!((DamageEvent) event).isCombatDamage() && event.getTargetId() == event.getSourceId()) {
            return super.applies(event, source, game);
        }
        return false;
    }

    @Override
    public BishopTheLastXManPreventEffect copy() {
        return new BishopTheLastXManPreventEffect(this);
    }
}