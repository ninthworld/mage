package mage.cards.s;

import java.util.UUID;
import mage.MageInt;
import mage.abilities.common.SimpleActivatedAbility;
import mage.abilities.costs.mana.ManaCostsImpl;
import mage.abilities.effects.common.TransformSourceEffect;
import mage.abilities.keyword.GeneratorAbility;
import mage.constants.SubType;
import mage.constants.SuperType;
import mage.abilities.keyword.FlyingAbility;
import mage.abilities.keyword.IndestructibleAbility;
import mage.abilities.keyword.SunburstAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;

/**
 *
 * @author NinthWorld
 */
public final class Sentry extends CardImpl {

    public Sentry(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{5}{W}{W}");
        
        this.addSuperType(SuperType.LEGENDARY);
        this.subtype.add(SubType.HUMAN);
        this.subtype.add(SubType.WARRIOR);
        this.power = new MageInt(5);
        this.toughness = new MageInt(5);

        this.transformable = true;
        this.secondSideCardClazz = mage.cards.s.SentryVoid.class;

        // Flying
        this.addAbility(FlyingAbility.getInstance());

        // Indestructible
        this.addAbility(IndestructibleAbility.getInstance());

        // Sunburst
        this.addAbility(new SunburstAbility(this));

        // Generator
        this.addAbility(new GeneratorAbility());

        // {4}{B}{B}: Transform Sentry.
        this.addAbility(new SimpleActivatedAbility(new TransformSourceEffect(true), new ManaCostsImpl("{4}{B}{B}")));
    }

    private Sentry(final Sentry card) {
        super(card);
    }

    @Override
    public Sentry copy() {
        return new Sentry(this);
    }
}
