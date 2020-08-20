package org.mage.test.cards.single.mcu;

import mage.constants.PhaseStep;
import mage.constants.Zone;
import mage.counters.CounterType;
import mage.game.GameException;
import org.junit.Test;
import org.mage.test.serverside.base.CardTestMultiPlayerBase;
import org.mage.test.serverside.base.CardTestPlayerBase;

public class DraxTheDestroyerTest extends CardTestMultiPlayerBase {

    public static final String DRAX = "Drax the Destroyer";
    public static final String THANOS = "Thanos, the Mad Titan";

    @Test
    public void test_drax_nothanos() {
        addCard(Zone.BATTLEFIELD, playerA, DRAX, 1);

        attack(1, playerA, DRAX, playerB);

        setStrictChooseMode(true);
        setStopAt(2, PhaseStep.UPKEEP);
        execute();
        assertAllCommandsUsed();
    }

    @Test
    public void test_drax_thanos_b() {
        addCard(Zone.BATTLEFIELD, playerA, DRAX, 1);
        addCard(Zone.BATTLEFIELD, playerB, THANOS, 1);

        attack(1, playerA, DRAX, playerB);

        setStrictChooseMode(true);
        setStopAt(2, PhaseStep.UPKEEP);
        execute();
        assertAllCommandsUsed();
    }

    @Test
    public void test_drax_thanos_c() throws GameException {
        addCard(Zone.BATTLEFIELD, playerA, DRAX, 1);
        addCard(Zone.BATTLEFIELD, playerC, THANOS, 1);

        attack(1, playerA, DRAX, playerB);

        setStrictChooseMode(true);
        setStopAt(2, PhaseStep.UPKEEP);
        execute();
        assertAllCommandsUsed();
    }

    @Test
    public void test_drax_thanos_a() {
        addCard(Zone.BATTLEFIELD, playerA, DRAX, 1);
        addCard(Zone.BATTLEFIELD, playerA, THANOS, 1);

        attack(1, playerA, DRAX, playerB);

        setStrictChooseMode(true);
        setStopAt(2, PhaseStep.UPKEEP);
        execute();
        assertAllCommandsUsed();
    }
}
