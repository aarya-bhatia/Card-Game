package com.aarya.game.view;

import com.aarya.game.controller.FloorController;
import com.aarya.game.controller.PlayerController;
import com.aarya.game.model.*;

import java.util.Scanner;

public class GameView {

    FloorController floorController;

    private final static Scanner sc = new Scanner(System.in);

    public GameView(FloorController floorController) {
        this.floorController = floorController;
    }

    public static void showCards(Player player, Floor floor) {
        char key;
        do {
            System.out.println("Type 1 to list cards available in your hand");
            System.out.println("Type 2 to list cards available on the floor");
            System.out.println("Type any other key to proceed");
            key = sc.next().charAt(0);
            if (key == '1') {
                player.displayCards();
            } else if (key == '2') {
                floor.show();
            }
        } while(key == '1' || key == '2');
    }

    public void selectCard(Player player, CardSelector cardSelector) throws NullPointerException {
        System.out.println("Select a card to play.");
        sc.nextLine();
        String input = sc.nextLine();
        Rank rank = CardSelection.parseRankFromInput(input);
        Suit suit = CardSelection.parseSuitFromInput(input);

        if (suit == null) {
            throw new NullPointerException("Suit not found");
        }
        if (rank == null) {
            throw new NullPointerException("Rank not found");
        }

        System.out.println("Card Selected: " + rank.getValue() + suit.getSymbol());

        Card card = PlayerController.findCard(player, rank, suit);
        cardSelector.setPlayerCard(card);
    }

    public void selectCardsAndHouses(Player player, CardSelector cardSelector){
        showCards(player, floorController.getFloor());

        char response;

        do {
            System.out.println("Type 0 to view cards on floor.");
            System.out.println("Type 1 to select more cards/houses.");
            System.out.println("Type 2 to see what you have selected so far.");
            System.out.println("Type q to proceed.");
            response = sc.next().charAt(0);

            if(response == '0') {
                floorController.getFloor().show();
            }
            else if(response == '1') {
                System.out.println("Type 1 to select a card.");
                System.out.println("Type 2 to select a house.");

                char r = sc.next().charAt(0);

                if(r == '1'){
                    System.out.println("Select card.");
                    sc.nextLine();
                    String input = sc.nextLine();
                    Rank rank = CardSelection.parseRankFromInput(input);
                    Suit suit = CardSelection.parseSuitFromInput(input);

                    Card card = floorController.findCard(rank,suit);
                    if(card != null) {
                        cardSelector.select(floorController.findCard(rank, suit));
                    }

                }
                else {
                    System.out.println("Select house rank.");
                    sc.nextLine();
                    String input = sc.nextLine();
                    Rank rank = CardSelection.parseRankFromInput(input);
                    House house = floorController.findHouse(rank);
                    if(house != null) {
                        cardSelector.select(house);
                    }
                }
            }
            else if(response == '2') {
                cardSelector.show();
            }
        } while(response != 'q');

    }

    public Command selectCommand(Player player, CardSelector cardSelector) throws NullPointerException, IllegalMoveException {
        Command command;
        System.out.println("Select move: [0 -> MERGE/ 1-> CLAIM] ");
        char response = sc.next().charAt(0);
        if(response == '0') {
            command = new MergeCommand(cardSelector, player, floorController);
        } else if(response == '1') {
            command = new ClaimCommand(cardSelector, player, floorController);
        }
        else {
            throw new NullPointerException("Invalid option selected");
        }
        return command;
    }
}
