package com.aarya.game.view;

import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class CardPane extends Pane {

    public CardPane(String suit, String rank) {

        String url = getFileURL(suit, rank);

        ImageView imageView = new ImageView(new Image(url));
        imageView.setFitHeight(120);

        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);
        imageView.setCache(true);

        setPadding(new Insets(5));

        getChildren().add(imageView);
    }

    /**
     * @param suit [H, D, S, C]
     * @param rank [A, 2, 3, 5, 6, 7, 8, 9, 10, J, Q, K]
     * @return image file url
     */
    public static String getFileURL(String suit, String rank) {
        return String.format("file:Cards/%s%s.png",rank,suit);
    }
}
