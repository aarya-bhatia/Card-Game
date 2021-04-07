package com.aarya.game.view;

import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class CardPane extends Pane {

    public CardPane(String url) {
        ImageView imageView = new ImageView(new Image(url));
        imageView.setFitHeight(120);

        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);
        imageView.setCache(true);

        setPadding(new Insets(5));

        getChildren().add(imageView);
    }

}
