package mage.cards.h;

import java.util.UUID;
import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.TriggeredAbilityImpl;
import mage.abilities.common.BeginningOfUpkeepTriggeredAbility;
import mage.abilities.common.EntersBattlefieldAbility;
import mage.abilities.effects.OneShotEffect;
import mage.abilities.effects.common.CreateTokenEffect;
import mage.abilities.effects.common.TransformSourceEffect;
import mage.abilities.effects.keyword.InventEffect;
import mage.constants.*;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.filter.StaticFilters;
import mage.filter.common.FilterArtifactPermanent;
import mage.game.Game;
import mage.game.events.GameEvent;
import mage.game.permanent.Permanent;
import mage.game.permanent.token.ThopterColorlessToken;
import mage.players.Player;
import mage.target.Target;
import mage.target.TargetPermanent;

/**
 *
 * @author NinthWorld
 */
public final class HankPym extends CardImpl {

    public HankPym(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{X}{G/U}{G/U}");
        
        this.addSuperType(SuperType.LEGENDARY);
        this.subtype.add(SubType.HUMAN);
        this.subtype.add(SubType.SCIENTIST);
        this.power = new MageInt(1);
        this.toughness = new MageInt(1);

        this.transformable = true;
        this.secondSideCardClazz = mage.cards.g.GiantMan.class;

        // When Hank Pym enters the battlefield, invent X times.
        this.addAbility(new EntersBattlefieldAbility(new HankPymEffect()));

        // At the beginning of your upkeep, if you control two or more artifacts, transform Hank Pym.
        this.addAbility(new HankPymUpkeepTriggeredAbility());
    }

    private HankPym(final HankPym card) {
        super(card);
    }

    @Override
    public HankPym copy() {
        return new HankPym(this);
    }
}

class HankPymEffect extends OneShotEffect {

    public HankPymEffect() {
        super(Outcome.PutCreatureInPlay);
        this.staticText = "invent X times";
    }

    public HankPymEffect(final HankPymEffect effect) {
        super(effect);
    }

    @Override
    public HankPymEffect copy() {
        return new HankPymEffect(this);
    }

    @Override
    public boolean apply(Game game, Ability source) {
        Player controller = game.getPlayer(source.getControllerId());
        if (controller != null) {
            int repeat = source.getManaCostsToPay().getX();
            for (int i = 0; i < repeat; i++) {
                source.addEffect(new InventEffect());
            }
            return true;
        }
        return false;
    }
}

class HankPymUpkeepTriggeredAbility extends TriggeredAbilityImpl {

    public HankPymUpkeepTriggeredAbility() {
        super(Zone.BATTLEFIELD, new TransformSourceEffect(true), false);
    }

    public HankPymUpkeepTriggeredAbility(final HankPymUpkeepTriggeredAbility ability) {
        super(ability);
    }

    @Override
    public HankPymUpkeepTriggeredAbility copy() {
        return new HankPymUpkeepTriggeredAbility(this);
    }

    @Override
    public boolean checkEventType(GameEvent event, Game game) {
        return event.getType() == GameEvent.EventType.UPKEEP_STEP_PRE;
    }

    @Override
    public boolean checkTrigger(GameEvent event, Game game) {
        return event.getPlayerId().equals(this.controllerId);
    }

    @Override
    public boolean checkInterveningIfClause(Game game) {
        return game.getBattlefield().countAll(new FilterArtifactPermanent(), this.controllerId, game) >= 2;
    }

    @Override
    public String getRule() {
        return "At the beginning of your upkeep, if you control two or more artifacts, transform Hank Pym";
    }
}