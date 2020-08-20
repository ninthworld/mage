
package mage.cards.j;

import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.common.*;
import mage.abilities.condition.common.PermanentsOnTheBattlefieldCondition;
import mage.abilities.decorator.ConditionalOneShotEffect;
import mage.abilities.effects.common.DestroyTargetEffect;
import mage.abilities.effects.common.TransformSourceEffect;
import mage.abilities.keyword.IndestructibleAbility;
import mage.abilities.keyword.TrampleAbility;
import mage.abilities.keyword.TransformAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.*;
import mage.filter.FilterPermanent;
import mage.target.TargetPermanent;

import java.util.UUID;

/**
 *
 * @author NinthWorld
 */
public final class JuggernautMRV extends CardImpl {

    private static final FilterPermanent filter1 = new FilterPermanent("artifact or land");
    private static final FilterPermanent filter2 = new FilterPermanent("you don't control a legendary artifact");

    static {
        filter1.add(CardType.ARTIFACT.getPredicate());
        filter1.add(CardType.LAND.getPredicate());
        filter2.add(TargetController.YOU.getControllerPredicate());
        filter2.add(SuperType.LEGENDARY.getPredicate());
        filter2.add(CardType.ARTIFACT.getPredicate());
    }

    public JuggernautMRV(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "");

        this.addSuperType(SuperType.LEGENDARY);
        this.subtype.add(SubType.HUMAN);
        this.subtype.add(SubType.AVATAR);
        this.power = new MageInt(7);
        this.toughness = new MageInt(7);

        this.color.setRed(true);

        this.nightCard = true;
        this.transformable = true;

        // Trample
        this.addAbility(TrampleAbility.getInstance());

        // Indestructible
        this.addAbility(IndestructibleAbility.getInstance());

        // Juggernaut attacks each combat if able.
        this.addAbility(new AttacksEachCombatStaticAbility());

        // Whenever Juggernaut attacks, destroy target artifact or land.
        Ability ability = new AttacksTriggeredAbility(new DestroyTargetEffect(), false);
        ability.addTarget(new TargetPermanent());
        this.addAbility(ability);

        // At the beginning of your upkeep, if you don't control a legendary artifact, transform Juggernaut.
        this.addAbility(new TransformAbility());
        this.addAbility(new BeginningOfUpkeepTriggeredAbility(
                new ConditionalOneShotEffect(
                        new TransformSourceEffect(false),
                        new PermanentsOnTheBattlefieldCondition(filter2, ComparisonType.EQUAL_TO, 0)),
                TargetController.YOU,
                false));
    }

    public JuggernautMRV(final JuggernautMRV card) {
        super(card);
    }

    @Override
    public JuggernautMRV copy() {
        return new JuggernautMRV(this);
    }

}
