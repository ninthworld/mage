package mage.cards.a;

import java.util.UUID;

import mage.abilities.common.EntersBattlefieldAbility;
import mage.abilities.condition.Condition;
import mage.abilities.condition.common.OneOpponentCondition;
import mage.abilities.condition.common.PermanentsOnTheBattlefieldCondition;
import mage.abilities.decorator.ConditionalOneShotEffect;
import mage.abilities.effects.common.CreateTokenEffect;
import mage.abilities.effects.common.TapSourceEffect;
import mage.abilities.mana.BlueManaAbility;
import mage.constants.ComparisonType;
import mage.constants.SubType;
import mage.constants.SuperType;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.filter.FilterPermanent;
import mage.filter.common.FilterControlledPermanent;
import mage.game.permanent.token.MerfolkToken;

/**
 *
 * @author NinthWorld
 */
public final class Atlantis extends CardImpl {

    private static final FilterPermanent filter = new FilterControlledPermanent(SubType.ISLAND);
    private static final Condition condition
            = new PermanentsOnTheBattlefieldCondition(filter, ComparisonType.FEWER_THAN, 2);

    public Atlantis(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.LAND}, "");
        
        this.addSuperType(SuperType.LEGENDARY);

        this.color.setBlue(true);

        // Atlantis enters the battlefield tapped unless you control two or more Islands.
        this.addAbility(new EntersBattlefieldAbility(new ConditionalOneShotEffect(
                new TapSourceEffect(), condition
        ), "tapped unless you control two or more Islands"));

        // When Atlantis enters the battlefield, create a 1/1 blue Merfolk creature token.
        this.addAbility(new EntersBattlefieldAbility(new CreateTokenEffect(new MerfolkToken())));

        // {T}: Add {U}.
        this.addAbility(new BlueManaAbility());
    }

    private Atlantis(final Atlantis card) {
        super(card);
    }

    @Override
    public Atlantis copy() {
        return new Atlantis(this);
    }
}
