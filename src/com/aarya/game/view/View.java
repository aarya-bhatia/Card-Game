package com.aarya.game.view;

import com.aarya.game.Game;
import com.aarya.game.model.Card;
import com.aarya.game.model.House;
import com.aarya.game.model.Rank;
import com.aarya.game.model.Suit;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class View extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Game");

        Game game = new Game(2);

        ScrollPane scroller = new ScrollPane();

        VBox container = new VBox();

        container.getChildren().add(new Text("Player 1"));
        container.getChildren().add(game.getPlayers()[0].getCardContainer());
        container.getChildren().add(new Text("Floor"));
        container.getChildren().add(game.getFloor().getCardContainer());
        container.getChildren().add(new Text("Player 2"));
        container.getChildren().add(game.getPlayers()[1].getCardContainer());

        StackPane stackPane = new StackPane(container);

        scroller.setContent(container);
        Scene scene = new Scene(scroller, 600, 600);

        stage.setTitle("ImageView");
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();
    }
}
