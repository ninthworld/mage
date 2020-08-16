package mage.cards.a;

import java.util.UUID;
import mage.MageInt;
import mage.abilities.common.SimpleStaticAbility;
import mage.abilities.common.SpellCastControllerTriggeredAbility;
import mage.abilities.effects.common.DrawCardSourceControllerEffect;
import mage.abilities.effects.common.DrawCardTargetEffect;
import mage.abilities.effects.common.continuous.BoostAllEffect;
import mage.constants.*;
import mage.abilities.keyword.FlashAbility;
import mage.abilities.keyword.FlyingAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.filter.FilterSpell;
import mage.filter.common.FilterEnchantmentCard;
import mage.filter.common.FilterOpponentsCreaturePermanent;

/**
 *
 * @author NinthWorld
 */
public final class AmoraTheEnchantress extends CardImpl {

    private static final FilterOpponentsCreaturePermanent filter = new FilterOpponentsCreaturePermanent();
    private static final FilterSpell filterSpell = new FilterSpell("an enchantment spell");

    static {
        filterSpell.add(CardType.ENCHANTMENT.getPredicate());
    }

    public AmoraTheEnchantress(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{4}{B}{G}");
        
        this.addSuperType(SuperType.LEGENDARY);
        this.subtype.add(SubType.WIZARD);
        this.subtype.add(SubType.GOD);
        this.power = new MageInt(4);
        this.toughness = new MageInt(6);

        // Flash
        this.addAbility(FlashAbility.getInstance());

        // Flying
        this.addAbility(FlyingAbility.getInstance());

        // Creatures your opponents control get -1/-1.
        this.addAbility(new SimpleStaticAbility(Zone.BATTLEFIELD, new BoostAllEffect(-1, -1, Duration.WhileOnBattlefield, filter, true)));

        // Whenever you cast an enchantment spell, you may draw a card.
        this.addAbility(new SpellCastControllerTriggeredAbility(new DrawCardSourceControllerEffect(1, "you"), filterSpell, true));
    }

    private AmoraTheEnchantress(final AmoraTheEnchantress card) {
        super(card);
    }

    @Override
    public AmoraTheEnchantress copy() {
        return new AmoraTheEnchantress(this);
    }
}
