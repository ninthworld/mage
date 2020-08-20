package mage.cards.b;

import java.util.UUID;
import mage.MageInt;
import mage.abilities.common.SimpleStaticAbility;
import mage.abilities.effects.common.cost.SpellsCostReductionAllEffect;
import mage.constants.SubType;
import mage.constants.SuperType;
import mage.abilities.keyword.CrewAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.filter.FilterCard;

/**
 *
 * @author NinthWorld
 */
public final class BattleVan extends CardImpl {

    private static final FilterCard filter = new FilterCard("Equipment spells");

    static {
        filter.add(SubType.EQUIPMENT.getPredicate());
    }

    public BattleVan(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.ARTIFACT}, "{2}");
        
        this.addSuperType(SuperType.LEGENDARY);
        this.subtype.add(SubType.VEHICLE);
        this.power = new MageInt(3);
        this.toughness = new MageInt(2);

        // Equipment spells you cast cost {1} less to cast.
        this.addAbility(new SimpleStaticAbility(new SpellsCostReductionAllEffect(filter, 1)));

        // Crew 1
        this.addAbility(new CrewAbility(1));
    }

    private BattleVan(final BattleVan card) {
        super(card);
    }

    @Override
    public BattleVan copy() {
        return new BattleVan(this);
    }
}
