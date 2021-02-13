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

	public void displayState() {
		System.out.println("Player 1\n");
		this.player1.show();

		System.out.println("Player 2\n");
		this.player2.show();

		System.out.println("floor\n");
		this.floor.show();

		System.out.println("deck\n");
		this.deck.show();
	}

	public static void main(String[] args) {
		Sweep sweep = new Sweep();
		sweep.displayState();
	}

}
