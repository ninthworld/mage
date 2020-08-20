package mage.cards.c;

import java.util.UUID;

import mage.abilities.Ability;
import mage.abilities.TriggeredAbilityImpl;
import mage.abilities.common.SimpleActivatedAbility;
import mage.abilities.costs.common.PayAnyEnergyCost;
import mage.abilities.costs.common.SacrificeSourceCost;
import mage.abilities.costs.common.TapSourceCost;
import mage.abilities.dynamicvalue.DynamicValue;
import mage.abilities.dynamicvalue.common.GetXValue;
import mage.abilities.effects.OneShotEffect;
import mage.abilities.effects.common.counter.GetEnergyCountersControllerEffect;
import mage.abilities.mana.ColorlessManaAbility;
import mage.constants.*;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.filter.StaticFilters;
import mage.game.Game;
import mage.game.events.DamagedEvent;
import mage.game.events.GameEvent;
import mage.game.permanent.Permanent;
import mage.players.Player;
import mage.target.TargetPlayer;

/**
 *
 * @author NinthWorld
 */
public final class Cavorite extends CardImpl {

    public Cavorite(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.ARTIFACT}, "{2}");
        
        this.addSuperType(SuperType.LEGENDARY);

        // Whenever a source you control deals noncombat damage, you get {E}.
        this.addAbility(new CavoriteAbility());

        // {T}: Add {C}.
        this.addAbility(new ColorlessManaAbility());

        // Pay any amount of {E}, {T}, Sacrifice Cavorite: It deals damage to target player and each creature that player controls equal to the amount of {E} paid this way.
        Ability ability = new SimpleActivatedAbility(
                new DamageAllControlledAndControllerTargetEffect(GetXValue.instance),
                new PayAnyEnergyCost());
        ability.addCost(new TapSourceCost());
        ability.addCost(new SacrificeSourceCost());
        ability.addTarget(new TargetPlayer());
        this.addAbility(ability);
    }

    private Cavorite(final Cavorite card) {
        super(card);
    }

    @Override
    public Cavorite copy() {
        return new Cavorite(this);
    }
}

class CavoriteAbility extends TriggeredAbilityImpl {

    public CavoriteAbility() {
        super(Zone.BATTLEFIELD, new GetEnergyCountersControllerEffect(1));
    }

    public CavoriteAbility(final CavoriteAbility ability) {
        super(ability);
    }

    @Override
    public CavoriteAbility copy() {
        return new CavoriteAbility(this);
    }

    @Override
    public boolean checkEventType(GameEvent event, Game game) {
        return event.getType() == GameEvent.EventType.DAMAGED_CREATURE ||
                event.getType() == GameEvent.EventType.DAMAGED_PLANESWALKER ||
                event.getType() == GameEvent.EventType.DAMAGED_PLAYER;
    }

    @Override
    public boolean checkTrigger(GameEvent event, Game game) {
        DamagedEvent damageEvent = (DamagedEvent) event;
        return !damageEvent.isCombatDamage();
    }

    @Override
    public String getRule() {
        return "Whenever a source you control deals noncombat damage, you get {E}.";
    }
}

class DamageAllControlledAndControllerTargetEffect extends OneShotEffect {

    private DynamicValue amount;

    public DamageAllControlledAndControllerTargetEffect(DynamicValue amount) {
        super(Outcome.Damage);
        this.amount = amount;
        this.staticText = "It deals damage to target player and each creature that player controls equal to the amount of {E} paid this way";
    }

    public DamageAllControlledAndControllerTargetEffect(final DamageAllControlledAndControllerTargetEffect effect) {
        super(effect);
        this.amount = effect.amount;
    }

    @Override
    public DamageAllControlledAndControllerTargetEffect copy() {
        return new DamageAllControlledAndControllerTargetEffect(this);
    }

    @Override
    public boolean apply(Game game, Ability source) {
        Player player = game.getPlayer(source.getFirstTarget());
        if (player != null) {
            int value = amount.calculate(game, source, this);
            player.damage(value, source.getSourceId(), game);
            for (Permanent permanent : game.getBattlefield().getAllActivePermanents(StaticFilters.FILTER_PERMANENT_CREATURE, player.getId(), game)) {
                permanent.damage(value, source.getSourceId(), game, false, true);
            }
            return true;
        }
        return false;
    }
}