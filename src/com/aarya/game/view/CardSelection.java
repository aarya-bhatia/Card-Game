package com.aarya.game.view;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.aarya.game.model.*;

public class CardSelection {

    private final static Pattern R_PATTERN = Pattern.compile("[1-9]|1[1-3]|[ajdk]|ace|jack|queen|king"); // rank pattern
    private final static Pattern S_PATTERN = Pattern.compile("[scdh]|spade|diamond|club|heart"); // suit pattern

    private static int getLetterRankValue(char c) {
        switch (c) {
            case 'j': {
                return 11;
            }
            case 'q': {
                return 12;
            }
            case 'k': {
                return 13;
            }
            case 'a': {
                return 1;
            }
            default: {
                return 0;
            }
        }
    }

    private static Suit getSuit(char c) {
        switch (c) {
            case 's': {
                return Suit.SPADE;
            }
            case 'd': {
                return Suit.DIAMOND;
            }
            case 'h': {
                return Suit.HEART;
            }
            case 'c': {
                return Suit.CLUB;
            }
            default:
                return null;
        }
    }

    public static Rank parseRankFromInput(String input) {
        input = input.toLowerCase();
        Matcher r_matcher = R_PATTERN.matcher(input);
        int value = 0;
        if (r_matcher.find()) {
            String group = r_matcher.group();
            try {
                value = Integer.parseInt(group);
            } catch (NumberFormatException ex) {
                value = getLetterRankValue(group.charAt(0));
            }
        }
        return Rank.find(value);
    }
    public static Suit parseSuitFromInput(String input) {
        input = input.toLowerCase();
        Matcher s_matcher = S_PATTERN.matcher(input);
        if (s_matcher.find()) {
            String group = s_matcher.group();
            return getSuit(group.charAt(0));
        }
        return null;
    }

}
