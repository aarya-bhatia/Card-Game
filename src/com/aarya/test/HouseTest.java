package com.aarya.test;

import com.aarya.game.model.*;

import java.util.*;

import org.junit.Test;

import static org.junit.Assert.*;

public class HouseTest {

    @Test
    public void addTest() {
        System.out.println("In addTest()");
        House house = new House(Rank.KING);

        house.setCards(Arrays.asList(
                new Card(Rank.SEVEN, Suit.SPADE),
                new Card(Rank.SIX, Suit.SPADE)
        ));

        System.out.println("House Created: " + house);
        house.displayCards();

        assertNotNull("House should exist", house);
        assertNotNull("House should have cards", house.getCards());
        assertNull("House should not have a parent", house.getParent());
        assertEquals("House should have 13 points", house.getPoints(), 13);
        assertTrue("House should be closed", house.isClosed());
    }

    @Test
    public void addRemoveChildTest() {
        System.out.println("In addRemoveChildTest()");

        House house = new House(Rank.KING);
        System.out.println("House Created: " + house);

        assertEquals("House should not have any children", house.getChildren().size(), 0);

        House child1 = new House(house.getRank());
        House child2 = new House(house.getRank());
        House child3 = new House(house.getRank());

        System.out.println("Created House: " + child1);
        System.out.println("Created House: " + child2);
        System.out.println("Created House: " + child3);

        Card c1 = new Card(Rank.FIVE, Suit.CLUB);
        Card c2 = new Card(Rank.EIGHT, Suit.CLUB);

        Card c3 = new Card(Rank.TWO, Suit.CLUB);
        Card c4 = new Card(Rank.JACK, Suit.CLUB);

        Card c5 = new Card(Rank.ACE, Suit.SPADE);
        Card c6 = new Card(Rank.TWO, Suit.SPADE);
        Card c7 = new Card(Rank.TEN, Suit.DIAMOND);

        child1.setCards(Arrays.asList(c1, c2));
        child2.setCards(Arrays.asList(c3, c4));
        child3.setCards(Arrays.asList(c5, c6, c7));

        house.add(child1);
        house.add(child2);
        house.add(child3);

        assertEquals("Should have three children", house.getChildren().size(), 3);
        house.displayCards();

        System.out.println("Deleting children");

        for(House child: house) {
            house.remove(child);
        }

        assertEquals("House should not have any children", house.getChildren().size(), 0);

        house.displayCards();
    }


    @Test
    public void houseIteratorTest() {
        System.out.println("In houseIteratorTest()");

        Rank rank = Rank.NINE;

        House h1 = new House(rank);
        h1.setCards(Arrays.asList(new Card(Rank.TWO, Suit.SPADE), new Card(Rank.SEVEN, Suit.CLUB)));

        House h2 = new House(rank);
        h2.setCards(Arrays.asList(new Card(Rank.ACE, Suit.SPADE), new Card(Rank.EIGHT, Suit.HEART)));

        House h3 = new House(rank);
        h3.setCards(Collections.singletonList(new Card(Rank.NINE, Suit.DIAMOND)));

        House main = new House(rank);
        main.setCards(Collections.singletonList(new Card(Rank.NINE, Suit.SPADE)));
        main.setChildren(Arrays.asList(h1, h2, h3));
        main.displayCards();

        List<House> houses = Arrays.asList(h1, h2, h3);
        int count = 0;

        for (House nextHouse : main) {
            int idx = houses.indexOf(nextHouse);
            if (idx >= 0) {
                count++;
            }
        }

        assertEquals("count should equal size of list", count, houses.size());
    }

}
