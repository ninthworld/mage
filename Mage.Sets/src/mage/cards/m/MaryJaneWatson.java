package mage.cards.m;

import java.util.UUID;
import mage.MageInt;
import mage.abilities.common.SimpleStaticAbility;
import mage.abilities.effects.common.continuous.BoostAllEffect;
import mage.abilities.effects.common.continuous.BoostAllOfChosenSubtypeEffect;
import mage.constants.Duration;
import mage.constants.SubType;
import mage.constants.SuperType;
import mage.abilities.keyword.LifelinkAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.filter.common.FilterCreaturePermanent;

/**
 *
 * @author NinthWorld
 */
public final class MaryJaneWatson extends CardImpl {

    private static final FilterCreaturePermanent filter = new FilterCreaturePermanent("Spiders");

    static {
        filter.add(SubType.SPIDER.getPredicate());
    }

    public MaryJaneWatson(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{W}");
        
        this.addSuperType(SuperType.LEGENDARY);
        this.subtype.add(SubType.HUMAN);
        this.subtype.add(SubType.CITIZEN);
        this.power = new MageInt(1);
        this.toughness = new MageInt(1);

        // Lifelink
        this.addAbility(LifelinkAbility.getInstance());

        // Spiders you control get +1/+1.
        this.addAbility(new SimpleStaticAbility(new BoostAllOfChosenSubtypeEffect(1, 1, Duration.WhileOnBattlefield, filter, false)));
    }

    private MaryJaneWatson(final MaryJaneWatson card) {
        super(card);
    }

    @Override
    public MaryJaneWatson copy() {
        return new MaryJaneWatson(this);
    }
}
