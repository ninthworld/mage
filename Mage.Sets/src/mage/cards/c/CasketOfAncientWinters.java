package mage.cards.c;

import java.util.UUID;

import mage.abilities.Ability;
import mage.abilities.common.SimpleStaticAbility;
import mage.abilities.effects.RestrictionUntapNotMoreThanEffect;
import mage.constants.Duration;
import mage.constants.SuperType;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.Zone;
import mage.filter.common.FilterControlledLandPermanent;
import mage.filter.common.FilterControlledPermanent;
import mage.game.Game;
import mage.players.Player;

/**
 *
 * @author NinthWorld
 */
public final class CasketOfAncientWinters extends CardImpl {

    public CasketOfAncientWinters(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.ARTIFACT}, "{2}");
        
        this.addSuperType(SuperType.LEGENDARY);

        // Players can't untap more than one land during their untap steps.
        this.addAbility(new SimpleStaticAbility(Zone.BATTLEFIELD, new CasketOfAncientWintersEffect()));
    }

    private CasketOfAncientWinters(final CasketOfAncientWinters card) {
        super(card);
    }

    @Override
    public CasketOfAncientWinters copy() {
        return new CasketOfAncientWinters(this);
    }
}

class CasketOfAncientWintersEffect extends RestrictionUntapNotMoreThanEffect {

    private static final FilterControlledPermanent filter = new FilterControlledLandPermanent();

    public CasketOfAncientWintersEffect() {
        super(Duration.WhileOnBattlefield, 1, filter);
        staticText = "Players can't untap more than one land during their untap steps";
    }

    public CasketOfAncientWintersEffect(final CasketOfAncientWintersEffect effect) {
        super(effect);
    }

    @Override
    public boolean applies(Player player, Ability source, Game game) {
        return true;
    }

    @Override
    public CasketOfAncientWintersEffect copy() {
        return new CasketOfAncientWintersEffect(this);
    }

}
