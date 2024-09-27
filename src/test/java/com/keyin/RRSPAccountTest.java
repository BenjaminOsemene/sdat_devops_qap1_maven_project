package com.keyin;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RRSPAccountTest {
    private RRSPAccount rrsp;

    @BeforeEach
    public void setUp() {
        rrsp = new RRSPAccount();
    }

    @Test
    void testInitialBalance() {
        assertEquals(0.0, rrsp.getBalance(), "Initial balance must be 0.0");
    }

    @Test
     void testDeposit() {
        rrsp.deposit(100.0);
        assertEquals(100.0, rrsp.getBalance(), "Balance must be 100.0 after deposit");

        rrsp.deposit(50.5);
        assertEquals(150.5, rrsp.getBalance(), "Balance should be 150.5 after second deposit");
    }

    @Test
    void testInvalidDeposit() {
        assertThrows(IllegalArgumentException.class, () -> rrsp.deposit(-50.0),
                "Depositing negative amount should throw IllegalArgumentException");

        assertThrows(IllegalArgumentException.class, () -> rrsp.deposit(0.0),
                "Depositing zero should throw IllegalArgumentException");
    }

    @Test
    void testRollover() {
        rrsp.deposit(1000.0);
        rrsp.rollover(500.0);
        assertEquals(500.0, rrsp.getBalance(), "Balance must be 500.0 after rollover");

        rrsp.rollover(300.0);
        assertEquals(200.0, rrsp.getBalance(), "Balance must be 200.0 after second rollover");
    }

    @Test
    void testInvalidRollover() {
        rrsp.deposit(100.0);

        assertThrows(IllegalArgumentException.class, () -> rrsp.rollover(150.0),
                "Rolling over more than balance must throw IllegalArgumentException");

        assertThrows(IllegalArgumentException.class, () -> rrsp.rollover(0.0),
                "Rolling over zero must throw IllegalArgumentException");

        assertThrows(IllegalArgumentException.class, () -> rrsp.rollover(-50.0),
                "Rolling over negative amount must throw IllegalArgumentException");

        assertEquals(100.0, rrsp.getBalance(), "Balance must not change after invalid rollovers");
    }
}