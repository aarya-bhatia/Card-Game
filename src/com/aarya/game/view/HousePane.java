package com.aarya.game.view;

import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class HousePane extends Pane {

    private static Font font = Font.font(20);

    public HousePane(String url, String name) {

        ImageView view = new ImageView(new Image(url));
        view.setFitHeight(120);
        view.setPreserveRatio(true);
        view.setSmooth(true);
        view.setCache(true);

        setPadding(new Insets(5));

        Text text = new Text(name);
        text.setFont(font);
        StackPane stack = new StackPane();
        stack.getChildren().add(view);
        stack.getChildren().add(text);

        getChildren().add(stack);
    }
}
