package com.aarya.game.view;

import com.aarya.game.model.Card;
import com.aarya.game.model.House;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class CardContainer extends HBox {

    FlowPane container;

    public CardContainer() {

        container = new FlowPane();

        this.getChildren().add(container);
    }

    public void put(CardPane cardPane){
        container.getChildren().add(cardPane);
    }

    public void put(HousePane housePane){
        container.getChildren().add(housePane);
    }

    public void remove(CardPane cardPane){
        container.getChildren().remove(cardPane);
    }

    public void remove(HousePane housePane) {
        container.getChildren().remove(housePane);
    }
}
