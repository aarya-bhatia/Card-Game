package com.aarya.game.view;

import com.aarya.game.model.Card;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class CardContainer extends HBox {

    FlowPane container;

    public CardContainer() {

        container = new FlowPane();

        this.getChildren().add(container);
    }

    public void put(CardPane card){
        container.getChildren().add(card);
    }

    public void remove(CardPane card){
        container.getChildren().remove(card);
    }
}
