package com.aarya.game.view;

import com.aarya.game.model.Card;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;

import java.util.Hashtable;

public class View extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    Hashtable<String, Image> cardImages;

    static String[] suits = {"H","D","C","S"};
    static String[] ranks = {"A","2","3","4","5","6","7","8","9","10","J","Q","K"};

    public void loadImages() {
        cardImages = new Hashtable<>();
        for(String suit:suits) {
            for(String rank:ranks){
                String name = CardPane.getFileURL(suit, rank);
                Image image = new Image(name);
                cardImages.put(name, image);
                System.out.println("Loaded Image: " + name);
            }
        }
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Game");

        CardPane card1 = new CardPane("H", "4");
        CardPane card2 = new CardPane("D", "10");
        CardPane card3 = new CardPane("C", "A");
        CardPane card4 = new CardPane("S", "K");

        CardContainer container = new CardContainer();

        container.put(card1);
        container.put(card2);
        container.put(card3);
        container.put(card4);

        StackPane stackPane = new StackPane(container);

        Scene scene = new Scene(stackPane, 600, 600);

        stage.setTitle("ImageView");
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();
    }
}
