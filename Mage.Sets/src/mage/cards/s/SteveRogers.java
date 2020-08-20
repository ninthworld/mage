package mage.cards.s;

import java.util.UUID;
import mage.MageInt;
import mage.abilities.common.EntersBattlefieldAbility;
import mage.abilities.effects.common.GainLifeEffect;
import mage.abilities.effects.common.TransformSourceEffect;
import mage.abilities.keyword.HeroicAbility;
import mage.abilities.keyword.TransformAbility;
import mage.constants.SubType;
import mage.constants.SuperType;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;

/**
 *
 * @author NinthWorld
 */
public final class SteveRogers extends CardImpl {

    public SteveRogers(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{W}");
        
        this.addSuperType(SuperType.LEGENDARY);
        this.subtype.add(SubType.HUMAN);
        this.subtype.add(SubType.SOLDIER);
        this.power = new MageInt(1);
        this.toughness = new MageInt(2);

        this.transformable = true;
        this.secondSideCardClazz = mage.cards.c.CaptainAmerica.class;

        // When Steve Rogers enters the battlefield, you gain 2 life.
        this.addAbility(new EntersBattlefieldAbility(new GainLifeEffect(2)));

        // <i>Heroic</i> - Whenever you cast a spell that targets Steve Rogers, transform him.
        this.addAbility(new TransformAbility());
        this.addAbility(new HeroicAbility(new TransformSourceEffect(true), false));
    }

    private SteveRogers(final SteveRogers card) {
        super(card);
    }

    @Override
    public SteveRogers copy() {
        return new SteveRogers(this);
    }
}
