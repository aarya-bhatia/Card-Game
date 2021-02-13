package com.aarya.game.controller;

import com.aarya.game.model.Player;
import com.aarya.game.model.Rank;
import com.aarya.game.model.Card;
import com.aarya.game.model.House;
import com.aarya.game.model.Rank;
import com.aarya.game.model.RankNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class PlayerController {
	/*private Controller controller;
	private HouseController houseController;

	public PlayerController(Controller c, HouseController hc) {
		controller = c;
		houseController = hc;
	}

	public static Rank rankOfCardsSelected(List<Card> cards) {
		int value = Card.getTotalValue(cards);
		for (int i = 13; i >= 9; i--) {
			if (value % i == 0) {
				return Rank.find(i);
			}
		}
		return null;
	}

	public static void merge(Player player, Card playerCard, List<Card> floorCards, List<House> floorHouses,
			House customHouse) throws RankNotFoundException, HouseKeyNotFoundException {

		List<Card> cards = selectCards(floorCards, floorHouses);

		Rank combinedRank;

		if (cards.size() == 0) {
			combinedRank = playerCard.getRank();
		} else {
			cards.add(playerCard);
			combinedRank = rankOfCardsSelected(cards);
		}

		if (combinedRank == null) {
			throw new RankNotFoundException();
		}

		boolean hasKey = false;

		for (Card c : player.getHand()) {
			if (c != playerCard && c.getRank().equals(combinedRank)) {
				hasKey = true;
				break;
			}
		}

		if (!hasKey) {
			throw new HouseKeyNotFoundException();
		}

		if (customHouse != null && customHouse.canAccept(cards)) {
			houseController.deposit(customHouse, cards);
		} else {
			House house = controller.containsHouseOfRank(combinedRank) ? controller.getHouseOfRank(combinedRank)
					: controller.createHouseOfRank(combinedRank);

			houseController.deposit(house, cards);
		}
		player.removeCard(playerCard);
	}

	public static List<Card> selectCards(List<Card> cards, List<House> houses) {
		if (cards == null) {
			cards = new ArrayList<Card>();
		}

		if (houses != null) {
			for (House h : houses) {
				List<Card> houseCards = h.getCards();
				cards.addAll(houseCards);
			}
		}

		return cards;
	}*/

}
