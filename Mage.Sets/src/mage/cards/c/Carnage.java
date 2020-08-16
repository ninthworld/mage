package mage.cards.c;

import java.util.UUID;

import mage.MageInt;
import mage.abilities.TriggeredAbilityImpl;
import mage.abilities.common.DealtDamageToSourceTriggeredAbility;
import mage.abilities.common.DiesSourceTriggeredAbility;
import mage.abilities.common.SimpleActivatedAbility;
import mage.abilities.costs.mana.ManaCostsImpl;
import mage.abilities.decorator.ConditionalActivatedAbility;
import mage.abilities.decorator.ConditionalContinuousEffect;
import mage.abilities.effects.common.CreateTokenEffect;
import mage.abilities.effects.common.TapSourceEffect;
import mage.abilities.effects.common.continuous.BoostSourceEffect;
import mage.abilities.keyword.FirstStrikeAbility;
import mage.abilities.keyword.MenaceAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.*;
import mage.game.Game;
import mage.game.events.DamagedCreatureEvent;
import mage.game.events.DamagedPlayerEvent;
import mage.game.events.GameEvent;
import mage.game.permanent.token.SymbioteToken;

/**
 *
 * @author NinthWorld
 */
public final class Carnage extends CardImpl {

    public Carnage(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "");

        this.addSuperType(SuperType.LEGENDARY);
        this.subtype.add(SubType.HUMAN);
        this.subtype.add(SubType.SYMBIOTE);
        this.subtype.add(SubType.ASSASSIN);
        this.power = new MageInt(5);
        this.toughness = new MageInt(5);

        this.color.setRed(true);
        this.color.setBlack(true);

        this.nightCard = true;
        this.transformable = true;

        // Menace
        this.addAbility(new MenaceAbility());

        // First strike
        this.addAbility(FirstStrikeAbility.getInstance());

        // {1}{R}: Carnage gets +1/+0 until end of turn.
        this.addAbility(new SimpleActivatedAbility(new BoostSourceEffect(1, 0, Duration.EndOfTurn), new ManaCostsImpl("{1}{R}")));

        // Whenever Carnage is dealt noncombat damage, tap him.
        this.addAbility(new CarnageAbility());

        // When Carnage dies, create a 0/0 black Symbiote creature token with graft 2.
        this.addAbility(new DiesSourceTriggeredAbility(new CreateTokenEffect(new SymbioteToken())));
    }

    private Carnage(final Carnage card) {
        super(card);
    }

    @Override
    public Carnage copy() {
        return new Carnage(this);
    }
}

class CarnageAbility extends TriggeredAbilityImpl {

    public CarnageAbility() {
        super(Zone.BATTLEFIELD, new TapSourceEffect(), false);
    }

    public CarnageAbility(final CarnageAbility ability) {
        super(ability);
    }

    @Override
    public boolean checkEventType(GameEvent event, Game game) {
        return event.getType() == GameEvent.EventType.DAMAGED_CREATURE;
    }

    @Override
    public boolean checkTrigger(GameEvent event, Game game) {
        DamagedCreatureEvent damageEvent = (DamagedCreatureEvent)event;
        return !damageEvent.isCombatDamage() && this.getSourceId() == event.getTargetId();
    }

    @Override
    public String getRule() {
        return "Whenever Carnage is dealt noncombat damage, tap him";
    }

    @Override
    public CarnageAbility copy() {
        return new CarnageAbility(this);
    }
}