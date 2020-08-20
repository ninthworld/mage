package mage.cards.m;

import java.util.UUID;
import mage.MageInt;
import mage.abilities.common.SimpleActivatedAbility;
import mage.abilities.common.SpellCastControllerTriggeredAbility;
import mage.abilities.costs.common.SacrificeTargetCost;
import mage.abilities.costs.mana.GenericManaCost;
import mage.abilities.effects.common.CreateTokenEffect;
import mage.abilities.effects.common.DoIfCostPaid;
import mage.abilities.effects.keyword.InventEffect;
import mage.constants.SubType;
import mage.constants.SuperType;
import mage.abilities.keyword.FlyingAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.filter.StaticFilters;
import mage.game.permanent.token.IllusionSacrificeToken;
import mage.target.common.TargetControlledPermanent;

/**
 *
 * @author NinthWorld
 */
public final class Mysterio extends CardImpl {

    public Mysterio(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{2}{U}");
        
        this.addSuperType(SuperType.LEGENDARY);
        this.subtype.add(SubType.HUMAN);
        this.subtype.add(SubType.ARTIFICER);
        this.power = new MageInt(3);
        this.toughness = new MageInt(2);

        // Flying
        this.addAbility(FlyingAbility.getInstance());

        // Whenever you cast an instant or sorcery spell, you may pay {1}. If you do, invent.
        this.addAbility(new SpellCastControllerTriggeredAbility(
                new DoIfCostPaid(new InventEffect(), new GenericManaCost(1)),
                StaticFilters.FILTER_SPELL_AN_INSTANT_OR_SORCERY,
                false));

        // Sacrifice an artifact: Create a 1/1 blue Illusion creature token with "Whenever this creature becomes the target of a spell or ability, sacrifice it."
        this.addAbility(new SimpleActivatedAbility(
                new CreateTokenEffect(new IllusionSacrificeToken()),
                new SacrificeTargetCost(new TargetControlledPermanent(StaticFilters.FILTER_CONTROLLED_PERMANENT_ARTIFACT))));
    }

    private Mysterio(final Mysterio card) {
        super(card);
    }

    @Override
    public Mysterio copy() {
        return new Mysterio(this);
    }
}
