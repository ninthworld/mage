package mage.cards.c;

import java.util.UUID;

import mage.MageInt;
import mage.ObjectColor;
import mage.abilities.common.SimpleActivatedAbility;
import mage.abilities.costs.common.TapSourceCost;
import mage.abilities.effects.common.TransformSourceEffect;
import mage.abilities.effects.common.continuous.BoostControlledEffect;
import mage.abilities.keyword.HeroicAbility;
import mage.abilities.keyword.ProtectionAbility;
import mage.abilities.keyword.VigilanceAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.Duration;
import mage.constants.SubType;
import mage.constants.SuperType;
import mage.filter.FilterCard;

/**
 *
 * @author NinthWorld
 */
public final class Colossus extends CardImpl {

    public Colossus(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "");

        this.addSuperType(SuperType.LEGENDARY);
        this.subtype.add(SubType.MUTANT);
        this.subtype.add(SubType.WARRIOR);
        this.power = new MageInt(6);
        this.toughness = new MageInt(6);

        this.color.setWhite(true);

        this.nightCard = true;
        this.transformable = true;

        // Vigilance
        this.addAbility(VigilanceAbility.getInstance());

        // Protection from red
        this.addAbility(ProtectionAbility.from(ObjectColor.RED));

        // Heroic - Whenever you cast a spell that targets Colossus, creatures you control get +0/+1 until end of turn.
        this.addAbility(new HeroicAbility(new BoostControlledEffect(0, 1, Duration.EndOfTurn)));

        // {T}: Transform Colossus.
        this.addAbility(new SimpleActivatedAbility(new TransformSourceEffect(false), new TapSourceCost()));
    }

    private Colossus(final Colossus card) {
        super(card);
    }

    @Override
    public Colossus copy() {
        return new Colossus(this);
    }
}
