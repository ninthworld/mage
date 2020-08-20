package mage.cards.m;

import java.util.UUID;

import mage.ObjectColor;
import mage.abilities.Ability;
import mage.abilities.common.SimpleStaticAbility;
import mage.abilities.costs.mana.GenericManaCost;
import mage.abilities.effects.common.continuous.BoostEquippedEffect;
import mage.abilities.effects.common.continuous.GainAbilityAttachedEffect;
import mage.abilities.keyword.EquipAbility;
import mage.abilities.keyword.ProtectionAbility;
import mage.abilities.keyword.ReachAbility;
import mage.constants.*;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.filter.common.FilterControlledCreaturePermanent;
import mage.filter.predicate.mageobject.NamePredicate;
import mage.target.common.TargetControlledCreaturePermanent;

/**
 *
 * @author NinthWorld
 */
public final class MagnetosHelmet extends CardImpl {

    private static final FilterControlledCreaturePermanent filter = new FilterControlledCreaturePermanent("Magneto");

    static {
        filter.add(new NamePredicate("Magneto"));
    }

    public MagnetosHelmet(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.ARTIFACT}, "{1}");
        
        this.addSuperType(SuperType.LEGENDARY);
        this.subtype.add(SubType.EQUIPMENT);

        // Equipped creature gets +0/+1 and has protection from blue.
        Ability ability = new SimpleStaticAbility(new BoostEquippedEffect(0, 1));
        ability.addEffect(new GainAbilityAttachedEffect(ProtectionAbility.from(ObjectColor.BLUE), AttachmentType.EQUIPMENT));

        // Equip Magneto {0}
        this.addAbility(new EquipAbility(Outcome.AddAbility, new GenericManaCost(0), new TargetControlledCreaturePermanent(filter)));

        // Equip {1}
        this.addAbility(new EquipAbility(1));
    }

    private MagnetosHelmet(final MagnetosHelmet card) {
        super(card);
    }

    @Override
    public MagnetosHelmet copy() {
        return new MagnetosHelmet(this);
    }
}
