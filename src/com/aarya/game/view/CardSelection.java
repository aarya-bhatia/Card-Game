package com.aarya.game.view;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.aarya.game.model.Rank;
import com.aarya.game.model.Rank;
import com.aarya.game.model.Suit;

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

    public static void filter(String input) {
        input = input.toLowerCase();

        Matcher r_matcher = R_PATTERN.matcher(input);
        Matcher s_matcher = S_PATTERN.matcher(input);

        int value;
        Rank r = null;

        if (r_matcher.find()) {
            String group = r_matcher.group();
            try {
                value = Integer.parseInt(group);
            } catch (NumberFormatException ex) {
                value = getLetterRankValue(group.charAt(0));
            }

            r = Rank.find(value);
        }

        if (r == null) {
            System.out.println("rank not found");
            return;
        }

        Suit suit = null;

        if (s_matcher.find()) {
            String group = s_matcher.group();
            suit = getSuit(group.charAt(0));
        }

        if (suit == null) {
            System.out.println("rank not found");
            return;
        }

        System.out.println("Card Selected: " + r.getValue() + suit.getSymbol());
    }

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Enter card: ");
            String cardText = sc.nextLine();
            filter(cardText);
        }
    }
}
