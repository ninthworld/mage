package mage.cards.p;

import java.util.UUID;
import mage.MageInt;
import mage.abilities.common.SpellCastControllerTriggeredAbility;
import mage.abilities.effects.common.CreateTokenEffect;
import mage.constants.SubType;
import mage.constants.SuperType;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.filter.StaticFilters;
import mage.game.permanent.token.ElementalToken;

/**
 *
 * @author NinthWorld
 */
public final class Pyro extends CardImpl {

    public Pyro(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{1}{R}");
        
        this.addSuperType(SuperType.LEGENDARY);
        this.subtype.add(SubType.ELEMENTAL);
        this.subtype.add(SubType.MUTANT);
        this.subtype.add(SubType.VILLAIN);
        this.power = new MageInt(2);
        this.toughness = new MageInt(2);

        // Whenever you cast an instant or sorcery spell, create a 1/1 red Elemental creature token.
        this.addAbility(new SpellCastControllerTriggeredAbility(new CreateTokenEffect(new ElementalToken()), StaticFilters.FILTER_SPELL_AN_INSTANT_OR_SORCERY, false));
    }

    private Pyro(final Pyro card) {
        super(card);
    }

    @Override
    public Pyro copy() {
        return new Pyro(this);
    }
}
