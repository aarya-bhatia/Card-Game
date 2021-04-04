package com.aarya.test.model;

import com.aarya.game.model.Rank;
import org.junit.Test;

import static org.junit.Assert.*;

public class RankTest {

    @Test
    public void findRankTest() {
        assertEquals("Should fetch the rank by capture value", Rank.find(5), Rank.FIVE);
        assertEquals("Should fetch the rank by capture value", Rank.find(1), Rank.ACE);
        assertEquals("Should fetch the rank by capture value", Rank.find(11), Rank.JACK);
        assertEquals("Rank king should be the max rank", Rank.find(Rank.MAX_RANK), Rank.KING);
        assertEquals("Rank nine should be the min rank", Rank.find(Rank.MIN_RANK), Rank.NINE);
        assertNull("Should not be able to fetch this rank", Rank.find(0));
        assertNull("Should not be able to fetch this rank", Rank.find(100));
    }

    @Test
    public void addRankTest() {
        assertNull("Should not be able to return a rank", Rank.add(Rank.KING, Rank.NINE));
        assertEquals("Should be able to add two ranks", Rank.add(Rank.ACE, Rank.TWO), Rank.THREE);
        assertEquals("Should be able to add two ranks", Rank.add(Rank.FIVE, Rank.FIVE), Rank.TEN);
        assertEquals("Should be able to add two ranks", Rank.add(Rank.JACK, Rank.ACE), Rank.QUEEN);
    }

    @Test
    public void subRankTest() {
        assertNull("Should not be able to return a rank", Rank.sub(Rank.TWO, Rank.TWO));
        assertEquals("Should be able to subtract two ranks", Rank.sub(Rank.THREE, Rank.TWO), Rank.ACE);
        assertEquals("Should be able to subtract  two ranks", Rank.sub(Rank.KING, Rank.FIVE), Rank.EIGHT);
        assertEquals("Should be able to subtract two ranks", Rank.sub(Rank.QUEEN, Rank.ACE), Rank.JACK);
    }

    @Test
    public void normalizeRankTest() {
        assertNull("Cannot normalise this rank", Rank.normaliseRank(25));
        assertEquals("Should not normalise this rank", Rank.normaliseRank(8), Rank.EIGHT);
        assertEquals("Should normalise the rank", Rank.normaliseRank(26), Rank.KING);
        assertEquals("Should normalise the rank", Rank.normaliseRank(18), Rank.add(Rank.EIGHT, Rank.ACE));
    }

    @Test
    public void isValidRankTest() {
        assertFalse("Rank is invalid", Rank.isValidRank(null));
        assertFalse("Rank is invalid", Rank.isValidRank(Rank.ACE));
        assertTrue("Rank is valid", Rank.isValidRank(Rank.NINE));
        assertTrue("Rank is valid", Rank.isValidRank(Rank.TEN));
        assertTrue("Rank is valid", Rank.isValidRank(Rank.JACK));
        assertTrue("Rank is valid", Rank.isValidRank(Rank.KING));
    }
}
