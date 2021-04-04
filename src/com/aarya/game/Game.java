package com.aarya.game;

import com.aarya.game.model.*;
import com.aarya.game.view.GameView;

import java.util.ArrayList;
import java.util.List;

// TODO: Add multiple rounds option
// TODO: Accept player names in constructor

public class Game {

	private final Player players[];
	private final Deck deck = new Deck();
	private final Floor floor = new Floor(this.deck.drawCards(4));

	FloorController floorController = new FloorController(floor);

	private final GameView view = new GameView(floorController);

	private List<Command> commandHistory = new ArrayList<>();

	private int currentPlayerIndex = 0;

	public Game(int numPlayers) {
		if(numPlayers < 2) {
			throw new IllegalArgumentException("Cannot create game with less than two players");
		}
		this.players = new Player[numPlayers];
		for(int i = 0; i < numPlayers; i++) {
			// TODO: change to 48
			this.players[i] = new Player(this.deck.drawCards(24/numPlayers));
		}
	}

	/**
	 *	Game is over when all players are out of cards.
	 * @return tells if current game is over
	 */
	private boolean isOver() {
		for(Player player: players) {
			if(!player.getHand().isEmpty()) {
				return false;
			}
		}
		return true;
	}

	public void play() {
		boolean nextMove;

		while(!isOver()) {

			System.out.println("Player " + (currentPlayerIndex+1) + "'s turn...");

			Player currentPlayer = players[currentPlayerIndex];
			view.showCards(currentPlayer, floor);

			do {
		    	nextMove = false;
		    	CardSelector cardSelector = new CardSelector();

				try {
					view.selectCard(currentPlayer, cardSelector);
					view.selectCardsAndHouses(currentPlayer, cardSelector);
					Command command = view.selectCommand(currentPlayer, cardSelector);

					try {
						command.execute();
						nextMove = true;
						currentPlayerIndex = (currentPlayerIndex + 1) % players.length;
						commandHistory.add(command);

					} catch (IllegalMoveException e) {
						System.out.println("Invalid move: " + e.getMessage());
					}
				}
				catch(Exception e) {
					System.out.println("Card selection failed: " + e.getMessage());
				}

			} while(!nextMove);
		}
	}

	public static void main(String[] args) {
		Game game = new Game(2);
		boolean debug = false;
		if(debug) {
			System.out.println("PLAYERS");
			int i = 1;
			for(Player player: game.players) {
				System.out.println("Player #" + i++ + ": ");
				player.displayCards();
			}
			System.out.println("FLOOR");
			game.floor.show();
			System.out.println("DECK");
			game.deck.show();
		}
		game.play();
	}

}
