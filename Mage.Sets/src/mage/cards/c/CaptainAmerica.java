package mage.cards.c;

import java.util.UUID;
import mage.MageInt;
import mage.abilities.common.SimpleActivatedAbility;
import mage.abilities.costs.mana.ManaCostsImpl;
import mage.abilities.effects.common.continuous.GainAbilitySourceEffect;
import mage.abilities.effects.keyword.BolsterEffect;
import mage.abilities.keyword.HeroicAbility;
import mage.abilities.keyword.IndestructibleAbility;
import mage.constants.Duration;
import mage.constants.SubType;
import mage.constants.SuperType;
import mage.abilities.keyword.VigilanceAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;

/**
 *
 * @author NinthWorld
 */
public final class CaptainAmerica extends CardImpl {

    public CaptainAmerica(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "");
        
        this.addSuperType(SuperType.LEGENDARY);
        this.subtype.add(SubType.HUMAN);
        this.subtype.add(SubType.SOLDIER);
        this.power = new MageInt(3);
        this.toughness = new MageInt(3);

        this.color.setWhite(true);

        this.nightCard = true;
        this.transformable = true;

        // Vigilance
        this.addAbility(VigilanceAbility.getInstance());

        // <i>Heroic</i> - Whenever you cast a spell that targets Captain America, bolster 1.
        this.addAbility(new HeroicAbility(new BolsterEffect(1), false));

        // {2}{W}: Captain America gains indestructible until end of turn.
        this.addAbility(new SimpleActivatedAbility(new GainAbilitySourceEffect(IndestructibleAbility.getInstance(), Duration.EndOfTurn), new ManaCostsImpl("{2}{W}")));
    }

    private CaptainAmerica(final CaptainAmerica card) {
        super(card);
    }

    @Override
    public CaptainAmerica copy() {
        return new CaptainAmerica(this);
    }
}
