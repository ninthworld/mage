package mage.cards.c;

import java.util.UUID;

import mage.abilities.Ability;
import mage.abilities.Mode;
import mage.abilities.common.SimpleActivatedAbility;
import mage.abilities.common.SimpleStaticAbility;
import mage.abilities.costs.CompositeCost;
import mage.abilities.costs.common.PayAnyEnergyCost;
import mage.abilities.costs.common.SacrificeSourceCost;
import mage.abilities.costs.common.TapSourceCost;
import mage.abilities.dynamicvalue.AdditiveDynamicValue;
import mage.abilities.dynamicvalue.DynamicValue;
import mage.abilities.dynamicvalue.common.GetXValue;
import mage.abilities.effects.ContinuousRuleModifyingEffectImpl;
import mage.abilities.effects.common.CantBeRegeneratedTargetEffect;
import mage.abilities.effects.common.DamageTargetEffect;
import mage.abilities.effects.common.GainLifeEffect;
import mage.abilities.effects.common.continuous.CantGainLifeAllEffect;
import mage.abilities.keyword.RadioactiveAbility;
import mage.abilities.mana.ColorlessManaAbility;
import mage.constants.*;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.filter.FilterPermanent;
import mage.game.Game;
import mage.game.events.GameEvent;
import mage.game.permanent.Permanent;
import mage.target.common.TargetCreaturePermanent;

/**
 *
 * @author NinthWorld
 */
public final class Carbonadium extends CardImpl {

    public Carbonadium(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.ARTIFACT}, "{2}");
        
        this.addSuperType(SuperType.LEGENDARY);

        // Your opponents can't gain life and creatures your opponents control can't be regenerated.
        this.addAbility(new SimpleStaticAbility(Zone.BATTLEFIELD, new CantGainLifeAllEffect(Duration.WhileOnBattlefield, TargetController.OPPONENT)));
        this.addAbility(new SimpleStaticAbility(Zone.BATTLEFIELD, new CantBeRegeneratedAllEffect(Duration.WhileOnBattlefield, TargetController.OPPONENT)));

        // Radioactive
        this.addAbility(new RadioactiveAbility());

        // {T}: Add {C}.
        this.addAbility(new ColorlessManaAbility());

        // Pay any amount of {E}, {T}, Sacrifice Carbonadium: You gain life equal to the amount of {E} paid this way.
        Ability ability = new SimpleActivatedAbility(
                new GainLifeEffect(GetXValue.instance),
                new PayAnyEnergyCost());
        ability.addCost(new TapSourceCost());
        ability.addCost(new SacrificeSourceCost());
        this.addAbility(ability);
    }

    private Carbonadium(final Carbonadium card) {
        super(card);
    }

    @Override
    public Carbonadium copy() {
        return new Carbonadium(this);
    }
}

class CantBeRegeneratedAllEffect extends ContinuousRuleModifyingEffectImpl {

    private TargetController filterController;

    public CantBeRegeneratedAllEffect(Duration duration, TargetController filterController) {
        super(duration, Outcome.Benefit, false, false);
        this.filterController = filterController;
    }

    public CantBeRegeneratedAllEffect(final CantBeRegeneratedAllEffect effect) {
        super(effect);
        this.filterController = effect.filterController;
    }

    @Override
    public CantBeRegeneratedAllEffect copy() {
        return new CantBeRegeneratedAllEffect(this);
    }

    @Override
    public boolean apply(Game game, Ability source) {
        return true;
    }

    @Override
    public boolean checksEventType(GameEvent event, Game game) {
        return event.getType() == GameEvent.EventType.REGENERATE;
    }

    @Override
    public boolean applies(GameEvent event, Ability source, Game game) {
        Permanent permanent = game.getPermanentOrLKIBattlefield(event.getTargetId());
        if (permanent != null) {
            FilterPermanent filter = new FilterPermanent();
            filter.add(filterController.getControllerPredicate());
            return filter.match(permanent, source.getSourceId(), source.getControllerId(), game);
        }
        return false;
    }

    @Override
    public String getText(Mode mode) {
        if (staticText != null && !staticText.isEmpty()) {
            return staticText;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("creatures ").append(filterController).append(" control ");
        sb.append("can't be regenerated");
        if (!duration.toString().isEmpty()) {
            if (duration == Duration.EndOfTurn) {
                sb.append(" this turn");
            } else {
                sb.append(' ').append(duration.toString());
            }
        }
        return sb.toString();
    }

}
