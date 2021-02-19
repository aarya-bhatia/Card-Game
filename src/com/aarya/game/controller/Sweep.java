package com.aarya.game.controller;

import com.aarya.game.model.*;

public class Sweep {

	private Player player1;
	private Player player2;
	private Floor floor;
	private Deck deck;

	private final static int CARDS_PER_HAND = 12;

	public Sweep() {
		this.deck = new Deck();
		this.player1 = new Player(this.deck.drawCards(CARDS_PER_HAND));
		this.player2 = new Player(this.deck.drawCards(CARDS_PER_HAND));
		this.floor = new Floor(this.deck.drawCards(4));
	}

	public static void main(String[] args) {

	}

}
