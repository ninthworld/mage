package mage.cards.j;

import java.util.UUID;
import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.TriggeredAbilityImpl;
import mage.abilities.common.SimpleActivatedAbility;
import mage.abilities.costs.common.DiscardCardCost;
import mage.abilities.costs.common.PayEnergyCost;
import mage.abilities.effects.common.DamageTargetEffect;
import mage.abilities.effects.common.DoIfCostPaid;
import mage.abilities.effects.common.DrawCardSourceControllerEffect;
import mage.abilities.effects.common.GainLifeEffect;
import mage.abilities.keyword.GeneratorAbility;
import mage.constants.SubType;
import mage.constants.SuperType;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.Zone;
import mage.filter.StaticFilters;
import mage.game.Game;
import mage.game.events.DamagedEvent;
import mage.game.events.GameEvent;
import mage.target.common.TargetCreaturePermanent;

/**
 *
 * @author NinthWorld
 */
public final class Jubilee extends CardImpl {

    public Jubilee(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{R}{W}");
        
        this.addSuperType(SuperType.LEGENDARY);
        this.subtype.add(SubType.MUTANT);
        this.subtype.add(SubType.WARRIOR);
        this.power = new MageInt(2);
        this.toughness = new MageInt(2);

        // Generator
        this.addAbility(new GeneratorAbility());

        // Whenever a source you control deals noncombat damage to a creature, you may discard a card. If you do, draw a card. Otherwise, you gain 1 life.
        this.addAbility(new JubileeAbility());

        // Pay {E} {E}: Jubilee deals 1 damage to target creature an opponent controls.
        Ability ability = new SimpleActivatedAbility(new DamageTargetEffect(1), new PayEnergyCost(2));
        ability.addTarget(new TargetCreaturePermanent(StaticFilters.FILTER_OPPONENTS_PERMANENT_CREATURE));
        this.addAbility(ability);
    }

    private Jubilee(final Jubilee card) {
        super(card);
    }

    @Override
    public Jubilee copy() {
        return new Jubilee(this);
    }
}

class JubileeAbility extends TriggeredAbilityImpl {

    public JubileeAbility() {
        super(Zone.BATTLEFIELD, new DoIfCostPaid(new DrawCardSourceControllerEffect(1), new GainLifeEffect(1), new DiscardCardCost(StaticFilters.FILTER_CARD_A), true));
    }

    public JubileeAbility(final JubileeAbility ability) {
        super(ability);
    }

    @Override
    public JubileeAbility copy() {
        return new JubileeAbility(this);
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
        return "Whenever a source you control deals noncombat damage, you may discard a card. If you do, draw a card. Otherwise, you gain 1 life.";
    }
}
