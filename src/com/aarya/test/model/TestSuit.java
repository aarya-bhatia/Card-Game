package com.aarya.test.model;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
        CardTest.class,
        RankTest.class,
        DeckTest.class,
        CardSelectorTest.class,
        HouseTest.class,
        FloorTest.class
})

public class TestSuit {
}
