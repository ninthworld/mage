package mage.cards.b;

import java.util.UUID;

import mage.abilities.Ability;
import mage.abilities.common.SimpleStaticAbility;
import mage.abilities.costs.mana.GenericManaCost;
import mage.abilities.effects.common.continuous.BoostEquippedEffect;
import mage.abilities.effects.common.continuous.GainAbilityAttachedEffect;
import mage.abilities.keyword.EquipAbility;
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
public final class BillyClub extends CardImpl {

    private static final FilterControlledCreaturePermanent filter = new FilterControlledCreaturePermanent("Daredevil, the Man Without Fear");

    static {
        filter.add(new NamePredicate("Daredevil, the Man Without Fear"));
    }

    public BillyClub(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.ARTIFACT}, "{1}");
        
        this.addSuperType(SuperType.LEGENDARY);
        this.subtype.add(SubType.EQUIPMENT);

        // Equipped creature gets +2/+0 and has reach.
        Ability ability = new SimpleStaticAbility(new BoostEquippedEffect(2, 0));
        ability.addEffect(new GainAbilityAttachedEffect(ReachAbility.getInstance(), AttachmentType.EQUIPMENT));

        // Equip Daredevil, the Man Without Fear {0}
        this.addAbility(new EquipAbility(Outcome.AddAbility, new GenericManaCost(0), new TargetControlledCreaturePermanent(filter)));

        // Equip {2}
        this.addAbility(new EquipAbility(2));

    }

    private BillyClub(final BillyClub card) {
        super(card);
    }

    @Override
    public BillyClub copy() {
        return new BillyClub(this);
    }
}
