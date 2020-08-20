package org.mage.test.cards.single.mcu;

import mage.constants.PhaseStep;
import mage.constants.Zone;
import mage.counters.CounterType;
import org.junit.Test;
import org.mage.test.serverside.base.CardTestPlayerBase;

public class CarbonadiumTest extends CardTestPlayerBase {

    public static final String CARD_NAME = "Carbonadium";

    @Test
    public void test_carbonadium_cantregenerate_opponent() {

        addCard(Zone.BATTLEFIELD, playerA, CARD_NAME, 1);
        addCard(Zone.HAND, playerA, "Murder", 1);
        addCard(Zone.BATTLEFIELD, playerA, "Swamp", 3);
        castSpell(1,  PhaseStep.BEGIN_COMBAT, playerA, "Murder", "Ancient Silverback");

        addCard(Zone.BATTLEFIELD, playerB, "Forest", 1);
        addCard(Zone.BATTLEFIELD, playerB, "Ancient Silverback", 1);
        activateAbility(1, PhaseStep.PRECOMBAT_MAIN, playerB, "{G}: Regenerate {this}");

        setStrictChooseMode(true);
        setStopAt(2, PhaseStep.UPKEEP);
        execute();
        assertAllCommandsUsed();

        assertPermanentCount(playerB, "Ancient Silverback", 0);
    }

    @Test
    public void test_carbonadium_cantregenerate_you() {

        addCard(Zone.BATTLEFIELD, playerA, CARD_NAME, 1);
        addCard(Zone.BATTLEFIELD, playerA, "Forest", 1);
        addCard(Zone.BATTLEFIELD, playerA, "Ancient Silverback", 1);
        activateAbility(1, PhaseStep.PRECOMBAT_MAIN, playerA, "{G}: Regenerate {this}");

        addCard(Zone.HAND, playerB, "Murder", 1);
        addCard(Zone.BATTLEFIELD, playerB, "Swamp", 3);
        castSpell(1,  PhaseStep.BEGIN_COMBAT, playerB, "Murder", "Ancient Silverback");

        setStrictChooseMode(true);
        setStopAt(2, PhaseStep.UPKEEP);
        execute();
        assertAllCommandsUsed();

        assertPermanentCount(playerA, "Ancient Silverback", 1);
    }

    @Test
    public void test_carbonadium_activatedability() {
        playerA.initLife(20);

        playerA.addCounters(CounterType.ENERGY.createInstance(5), currentGame);
        addCard(Zone.BATTLEFIELD, playerA, CARD_NAME, 1);

        // Pay any amount of {E}, {T}, Sacrifice {this}: You gain life equal to the amount of {E} paid this way.
        activateAbility(1, PhaseStep.PRECOMBAT_MAIN, playerA, "Pay any amount of {E}, {T}, Sacrifice {this}");
        setChoice(playerA, "X=5");

        setStrictChooseMode(true);
        setStopAt(2, PhaseStep.UPKEEP);
        execute();
        assertAllCommandsUsed();

        assertPermanentCount(playerA, CARD_NAME, 0);
        assertLife(playerA, 20 - 1 + 5);
    }
}
