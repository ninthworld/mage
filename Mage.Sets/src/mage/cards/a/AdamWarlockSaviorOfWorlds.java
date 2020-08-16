package mage.cards.a;

import java.util.UUID;
import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.common.LeavesBattlefieldTriggeredAbility;
import mage.abilities.effects.common.ReturnFromGraveyardToBattlefieldTargetEffect;
import mage.constants.ComparisonType;
import mage.constants.SubType;
import mage.constants.SuperType;
import mage.abilities.keyword.FlyingAbility;
import mage.abilities.keyword.LifelinkAbility;
import mage.abilities.keyword.UndyingAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.filter.common.FilterCreatureCard;
import mage.filter.predicate.mageobject.PowerPredicate;
import mage.target.common.TargetCardInYourGraveyard;

/**
 *
 * @author NinthWorld
 */
public final class AdamWarlockSaviorOfWorlds extends CardImpl {

    private static final FilterCreatureCard filter = new FilterCreatureCard();

    static {
        filter.add(new PowerPredicate(ComparisonType.FEWER_THAN, 4));
    }

    public AdamWarlockSaviorOfWorlds(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{4}{W}{W}");
        
        this.addSuperType(SuperType.LEGENDARY);
        this.subtype.add(SubType.COSMIC);
        this.subtype.add(SubType.AVATAR);
        this.power = new MageInt(5);
        this.toughness = new MageInt(6);

        // Flying
        this.addAbility(FlyingAbility.getInstance());

        // Lifelink
        this.addAbility(LifelinkAbility.getInstance());

        // When Adam Warlock, Savior of Worlds leaves the battlefield, return target creature card with power 3 or less from your graveyard to the battlefield.
        Ability ability = new LeavesBattlefieldTriggeredAbility(new ReturnFromGraveyardToBattlefieldTargetEffect(false), false);
        ability.addTarget(new TargetCardInYourGraveyard(filter));
        this.addAbility(ability);

        // Undying
        this.addAbility(new UndyingAbility());

    }

    private AdamWarlockSaviorOfWorlds(final AdamWarlockSaviorOfWorlds card) {
        super(card);
    }

    @Override
    public AdamWarlockSaviorOfWorlds copy() {
        return new AdamWarlockSaviorOfWorlds(this);
    }
}