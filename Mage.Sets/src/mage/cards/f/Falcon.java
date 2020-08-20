package mage.cards.f;

import java.util.UUID;
import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.common.AttacksTriggeredAbility;
import mage.abilities.effects.OneShotEffect;
import mage.abilities.effects.common.CreateTokenEffect;
import mage.constants.Outcome;
import mage.constants.SubType;
import mage.constants.SuperType;
import mage.abilities.keyword.FlyingAbility;
import mage.abilities.keyword.HasteAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.game.Game;
import mage.game.permanent.token.RedwingToken;

/**
 *
 * @author NinthWorld
 */
public final class Falcon extends CardImpl {

    public Falcon(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{1}{R}{W}");
        
        this.addSuperType(SuperType.LEGENDARY);
        this.subtype.add(SubType.HUMAN);
        this.subtype.add(SubType.BIRD);
        this.subtype.add(SubType.WARRIOR);
        this.power = new MageInt(2);
        this.toughness = new MageInt(2);

        // Flying
        this.addAbility(FlyingAbility.getInstance());

        // Haste
        this.addAbility(HasteAbility.getInstance());

        // Whenever Falcon attacks, create a legendary 1/1 red and white Bird creature token with flying named Redwing that's tapped and attacking. Exile that token at end of combat.
        this.addAbility(new AttacksTriggeredAbility(new FalconEffect(), false));
    }

    private Falcon(final Falcon card) {
        super(card);
    }

    @Override
    public Falcon copy() {
        return new Falcon(this);
    }
}

class FalconEffect extends OneShotEffect {

    public FalconEffect() {
        super(Outcome.PutCreatureInPlay);
        this.staticText = "create a legendary 1/1 red and white Bird creature token with flying named Redwing that's tapped and attacking. Exile that token at end of combat";
    }

    public FalconEffect(final FalconEffect effect) {
        super(effect);
    }

    @Override
    public FalconEffect copy() {
        return new FalconEffect(this);
    }

    @Override
    public boolean apply(Game game, Ability source) {

        CreateTokenEffect effect = new CreateTokenEffect(new RedwingToken(), 1, true, true);
        if (effect.apply(game, source)) {
            effect.exileTokensCreatedAtEndOfCombat(game, source);
            return true;
        }
        return false;
    }
}