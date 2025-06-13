package com.game;

import org.junit.jupiter.api.Test;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MineSweeperTest {

    private final MineSweeper mineSweeper = new MineSweeper();

    @Test
    void testvalidateCellId() {
        assertEquals(FALSE,
                mineSweeper.validateCellId("A5", 4));
        assertEquals(TRUE,
                mineSweeper.validateCellId("A5", 5));
        assertEquals(TRUE,
                mineSweeper.validateCellId("A24", 26));
        assertEquals(FALSE,
                mineSweeper.validateCellId("A24", 28));
    }
}
