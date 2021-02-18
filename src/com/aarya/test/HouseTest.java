package com.aarya.test;

import com.aarya.game.model.*;

import java.util.*;

import org.junit.Test;

import static org.junit.Assert.*;

public class HouseTest {

    House house;

    public HouseTest() {
        house = new House(Rank.KING);
        System.out.println("House Created: " + house);
    }

    @Test
    public void addCardsTest() throws RankMismatchException {
        house.addCards(Arrays.asList(
                new Card(Rank.SEVEN, Suit.SPADE),
                new Card(Rank.SIX, Suit.SPADE)
        ));

        house.displayCards();

        assertNotNull("House should exist", house);
        assertNotNull("House should have cards", house.getCards());
        assertNull("House should not have a parent", house.getParent());
        assertEquals("House should have 13 points", house.getPoints(), 13);
        assertTrue("House should be closed", house.isClosed());
    }

    @Test
    public void addChildHousesTest() throws RankMismatchException {
        assertNotNull(house.getChildren());
        assertEquals("House should not have any children", house.getChildren().size(), 0);

        House child1 = new House(house.getRank());
        House child2 = new House(house.getRank());
        House child3 = new House(house.getRank());

        System.out.println("Created House: " + child1);
        System.out.println("Created House: " + child2);
        System.out.println("Created House: " + child3);

        Card c1 = new Card(Rank.FIVE, Suit.CLUB);
        Card c2 = new Card(Rank.EIGHT, Suit.CLUB);

        List<Card> l1 = Arrays.asList(c1, c2);

        Card c3 = new Card(Rank.TWO, Suit.CLUB);
        Card c4 = new Card(Rank.JACK, Suit.CLUB);

        List<Card> l2 = Arrays.asList(c3, c4);

        Card c5 = new Card(Rank.ACE, Suit.SPADE);
        Card c6 = new Card(Rank.TWO, Suit.SPADE);
        Card c7 = new Card(Rank.TEN, Suit.DIAMOND);

        List<Card> l3 = Arrays.asList(c5, c6, c7);

        child1.addCards(l1);
        child2.addCards(l2);
        child3.addCards(l3);

        house.add(child1);
        house.add(child2);
        house.add(child3);

        assertEquals("Should have three children", house.getChildren().size(), 3);
        house.displayCards();
    }

    @Test(expected = RankMismatchException.class)
    public void addIllegalHouse() throws RankMismatchException {
        int prevHouseSize = house.getChildren().size();
        Card c8 = new Card(Rank.FOUR, Suit.HEART);
        Card c9 = new Card(Rank.EIGHT, Suit.HEART);
        List<Card> l = Arrays.asList(c8, c9);
        house.addCards(l);
        house.addCards(null);
        assertEquals("Should not affect the house", house.getChildren().size(), prevHouseSize);
    }

    @Test
    public void removeChildHousesTest() {
        System.out.println("Deleting children");
        House.destroy(house);
        house.displayCards();
    }

    @Test
    public void houseIteratorTest() throws RankMismatchException {
        Rank rank = Rank.NINE;

        House h1 = new House(rank);
        h1.addCards(Arrays.asList(new Card(Rank.TWO, Suit.SPADE), new Card(Rank.SEVEN, Suit.CLUB)));

        House h2 = new House(rank);
        h2.addCards(Arrays.asList(new Card(Rank.ACE, Suit.SPADE), new Card(Rank.EIGHT, Suit.HEART)));

        House h3 = new House(rank);
        h3.addCards(Arrays.asList(new Card(Rank.NINE, Suit.DIAMOND)));

        House main = new House(rank);

        main.addCard(new Card(Rank.NINE, Suit.SPADE));

        main.add(h1);
        main.add(h2);
        main.add(h3);

        main.displayCards();

        List<House> houses = Arrays.asList(h1, h2, h3);
        int count = 0;

        Iterator<House> it = main.iterator();

        while(it.hasNext()) {
            House nextHouse = it.next();
            int idx = houses.indexOf(nextHouse);
            if(idx >= 0) {
                count++;
            }
        }

        assertEquals("count should equal size of list", count, houses.size());
    }

}
