package com.aarya.game.view;

import com.aarya.game.model.Card;
import com.aarya.game.model.House;
import com.aarya.game.model.Rank;
import com.aarya.game.model.Suit;
import javafx.scene.layout.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class View extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Game");

        CardPane card1 = new Card(Rank.ACE, Suit.HEART).getCardPane();
        CardPane card2 = new Card(Rank.TWO, Suit.SPADE).getCardPane();
        CardPane card3 = new Card(Rank.KING, Suit.DIAMOND).getCardPane();

        House house = new House(Rank.KING);
        HousePane h = house.getHousePane();

        CardContainer container = new CardContainer();

        container.put(card1);
        container.put(card2);
        container.put(card3);
        container.put(h);

        StackPane stackPane = new StackPane(container);

        Scene scene = new Scene(stackPane, 600, 600);

        stage.setTitle("ImageView");
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();
    }
}
