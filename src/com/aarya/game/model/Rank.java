package com.aarya.game.model;

/**
 * The Rank constants represents the thirteen possible ranks and their capture
 * values in the deck of cards.
 */
public enum Rank {
    ACE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10), JACK(11), QUEEN(12),
    KING(13);

    private final int value;

    Rank(int r) {
        value = r;
    }

    /**
     * @return capture value of the current card
     */
    public int getValue() {
        return value;
    }

    /**
     * The highest allowed rank for house is that of King
     */
    public static final int MAX_RANK = 13;

    /**
     * The lowest allowed rank for house is that of Nine
     */
    public static final int MIN_RANK = 9;

    /**
     * This array allows each access to the Rank instance through its capture value.
     * Much like a dictionary.
     */
    private static final Rank[] cache = new Rank[13];

    /*
      Static block initialised the cache array by iterating through all the Ranks
      respectively. The ranks are now zero-indexed so Rank R would be at index R-1
      etc.
     */
    static {
        int i = 0;
        for (Rank r : values()) {
            cache[i++] = r;
        }
    }

    /**
     * Returns the Rank instance represented by the capture value
     * 
     * @param value capture value
     * @return a rank
     */
    public static Rank find(int value) {
        int index = value - 1;
        if (index < 0 || index > MAX_RANK - 1) {
            return null;
        }
        return cache[index];
    }

    /**
     * Adds two ranks and returns the raised Rank
     * 
     * @param r1 rank of first element
     * @param r2 rank of second element
     * @return new rank
     */
    public static Rank add(Rank r1, Rank r2) {
        return find(r1.getValue() + r2.getValue());
    }

    /**
     * A rank of house can be raised if the new rank is still less than or equal to
     * the max rank, i.e. King
     * 
     * @param r1 first rank
     * @param r2 second rank
     * @return true or false
     */
    public static boolean canRaiseRank(Rank r1, Rank r2) {
        return r1.getValue() + r2.getValue() <= MAX_RANK;
    }

    /**
     * Subtracts two ranks and returns the lowered Rank
     * 
     * @param rank first rank
     * @param rank2 second rank
     * @return new rank
     */
    public static Rank sub(Rank rank, Rank rank2) {
        return find(rank.getValue() - rank2.getValue());
    }

    /**
     * A set of cards is considered valid if the total captureValue is a multiple of
     * any number from 9 to King, i.e 13. This method will reduce the capture value
     * to a known Rank and return it if applicable.
     * 
     * @param captureValue sum of the captureValues of each card and house
     *                     respectively
     * @return a rank representing the set of cards and houses
     */
    public static Rank normaliseRank(int captureValue) {
        for (int i = MAX_RANK; i >= MIN_RANK; i--) {
            if (captureValue % i == 0) {
                return find(i);
            }
        }
        return null;
    }

    public static boolean isValidRank(Rank rank) {
        return rank != null && rank.getValue() >= MIN_RANK && rank.getValue() <= MAX_RANK;
    }

}
