package mage.cards.l;

import java.util.UUID;
import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.ActivatedAbility;
import mage.abilities.Mode;
import mage.abilities.common.SimpleActivatedAbility;
import mage.abilities.costs.common.PayLifeCost;
import mage.abilities.costs.mana.GenericManaCost;
import mage.abilities.effects.ContinuousEffectImpl;
import mage.abilities.effects.common.DestroySourceEffect;
import mage.abilities.effects.common.InfoEffect;
import mage.abilities.effects.common.continuous.GainControlTargetEffect;
import mage.constants.*;
import mage.abilities.keyword.VigilanceAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.game.Game;
import mage.game.events.GameEvent;
import mage.game.permanent.Permanent;
import mage.players.Player;
import mage.target.Target;
import mage.util.CardUtil;

/**
 *
 * @author NinthWorld
 */
public final class LukeCageHeroForHire extends CardImpl {

    public LukeCageHeroForHire(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{1}{G/W}{G/W}");
        
        this.addSuperType(SuperType.LEGENDARY);
        this.subtype.add(SubType.HUMAN);
        this.subtype.add(SubType.MERCENARY);
        this.power = new MageInt(4);
        this.toughness = new MageInt(5);

        // Vigilance
        this.addAbility(VigilanceAbility.getInstance());

        // {4}: Gain control of Luke Cage, Hero for Hire until end of turn. Any player may play this ability.
        SimpleActivatedAbility ability = new SimpleActivatedAbility(Zone.BATTLEFIELD, new LukeCageEffect(), new GenericManaCost(4));
        ability.setMayActivate(TargetController.ANY);
        ability.addEffect(new InfoEffect("Any player may activate this ability"));
        this.addAbility(ability);
    }

    private LukeCageHeroForHire(final LukeCageHeroForHire card) {
        super(card);
    }

    @Override
    public LukeCageHeroForHire copy() {
        return new LukeCageHeroForHire(this);
    }
}

class LukeCageEffect extends ContinuousEffectImpl {

    public LukeCageEffect() {
        super(Duration.EndOfTurn, Layer.ControlChangingEffects_2, SubLayer.NA, Outcome.GainControl);
    }

    public LukeCageEffect(final LukeCageEffect effect) {
        super(effect);
    }

    @Override
    public LukeCageEffect copy() {
        return new LukeCageEffect(this);
    }

    @Override
    public boolean apply(Game game, Ability source) {
        Player abilityController = game.getPlayer(source.getControllerId());
        Permanent permanent = source.getSourcePermanentIfItStillExists(game);
        if (abilityController != null) {
            if (!permanent.isControlledBy(source.getControllerId())) {
                GameEvent loseControlEvent = GameEvent.getEvent(GameEvent.EventType.LOSE_CONTROL, permanent.getId(), source.getId(), permanent.getControllerId());
                if (game.replaceEvent(loseControlEvent)) {
                    return false;
                }
                if (permanent.changeControllerId(source.getControllerId(), game)) {
                    permanent.getAbilities().setControllerId(source.getControllerId());
                }
            }
            return true;
        }
        return false;
    }
}
