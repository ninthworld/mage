package mage.cards.c;

import java.util.UUID;
import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.common.BeginningOfUpkeepTriggeredAbility;
import mage.abilities.common.EntersBattlefieldAbility;
import mage.abilities.condition.common.AnyPlayerControlsCondition;
import mage.abilities.condition.common.PermanentsOnTheBattlefieldCondition;
import mage.abilities.decorator.ConditionalOneShotEffect;
import mage.abilities.effects.common.DestroyTargetEffect;
import mage.abilities.effects.common.TransformSourceEffect;
import mage.abilities.keyword.TransformAbility;
import mage.constants.SubType;
import mage.constants.SuperType;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.TargetController;
import mage.filter.FilterPermanent;
import mage.target.common.TargetCreaturePermanent;

/**
 *
 * @author NinthWorld
 */
public final class CletusKasady extends CardImpl {

    private static final FilterPermanent filter = new FilterPermanent();

    static {
        filter.add(SubType.SYMBIOTE.getPredicate());
    }

    public CletusKasady(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{2}{B}{B}");
        
        this.addSuperType(SuperType.LEGENDARY);
        this.subtype.add(SubType.HUMAN);
        this.subtype.add(SubType.ASSASSIN);
        this.power = new MageInt(2);
        this.toughness = new MageInt(1);

        this.transformable = true;
        this.secondSideCardClazz = mage.cards.c.Carnage.class;

        // When Cletus Kasady enters the battlefield, destroy target creature.
        Ability ability = new EntersBattlefieldAbility(new DestroyTargetEffect());
        ability.addTarget(new TargetCreaturePermanent());
        this.addAbility(ability);

        // At the beginning of your upkeep, if any player controls a Symbiote, transform Cletus Kasady.
        this.addAbility(new TransformAbility());
        this.addAbility(new BeginningOfUpkeepTriggeredAbility(
                new ConditionalOneShotEffect(
                        new TransformSourceEffect(true),
                        new AnyPlayerControlsCondition(filter),
                        "if any player controls a Symbiote, transform Cletus Kasady"),
                TargetController.YOU, false));
    }

    private CletusKasady(final CletusKasady card) {
        super(card);
    }

    @Override
    public CletusKasady copy() {
        return new CletusKasady(this);
    }
}
