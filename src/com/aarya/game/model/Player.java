package com.aarya.game.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Player implements Serializable {

	private List<Card> hand;

	private int sweeps = 0;

	//TODO: implement keyBox code
	private HashMap<Card, House> keyBox;

	private House collection;

	public Player() { this.hand = new ArrayList<>(); }

	public Player(List<Card> hand) {
		this.hand = hand;
	}

	public List<Card> getHand() {
		return this.hand;
	}

	public House getCollection() {
		return this.collection;
	}

	public void setCollection(House collection) { this.collection = collection; }

	public int getSweeps() {
		return sweeps;
	}

	public void setSweeps(int sweeps) {
		this.sweeps = sweeps;
	}

	public void setHand(List<Card> cards) {
		this.hand = cards;
	}

	public HashMap<Card, House> getKeyBox() {
		return keyBox;
	}

	public void setKeyBox(HashMap<Card, House> keyBox) {
		this.keyBox = keyBox;
	}

	/**
     *
	 * @return points captured by player
	 */
	public int getPoints() {
		if(this.collection == null) {
			return 0;
		}

		int points = 0;

		for (House house : this.collection) {
			points += house.getPoints();
		}

		return points;
	}

	public String toString() {
		return "Player # " + this.hashCode();
	}

	public void displayCards() {
		System.out.println("[START] " + this + " [START]");

		System.out.println("Hand");

		for (Card card : this.hand) {
			System.out.println(card);
		}

		System.out.println("Collection");

		for(House house: this.collection) {
			house.displayCards();
		}

		System.out.println("[END] " + this + " [END]");
	}

	public void add(Card card) {
		if(!this.hand.contains(card)) {
			this.hand.add(card);
		}
	}

	public void remove(Card card) {
		this.hand.remove(card);
	}

	public void add(House house) {
		if(this.collection == null) {
			this.collection = house;
		} else {
			this.collection.add(house);
		}
	}

	public void remove(House house) {
		if(this.collection == null) {
			return;
		}
		if(house.getParent() == null) {
			this.collection.remove(house);
		} else {
			house.getParent().remove(house);
		}
	}

}
