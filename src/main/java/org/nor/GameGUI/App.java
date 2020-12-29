package org.nor.GameGUI;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import javafx.stage.Screen;
import javafx.stage.Stage;
import org.nor.GameLogic.*;


/**
 * JavaFX App
 */
public class App extends Application {
    private Scene menuScene;

    @Override
    public void start(Stage stage) {

        stage.setTitle("Morpion Game");
        stage.setResizable(false);
        stage.setMaximized(true);

        Label gameName= new Label("Morpion Solitaire");
        Button playerButton= new Button("play");
        Button computerButton= new Button("let the AI play");
        Button algorithmButton= new Button("compare the algorithms");


        ChoiceBox<String> gameTypeChoiceBox = new ChoiceBox();
        gameTypeChoiceBox.getItems().addAll("5T","5D","4T","4D");
        gameTypeChoiceBox.setValue("5T");

        ChoiceBox<String> AlgoChoiceBox = new ChoiceBox();
        AlgoChoiceBox.getItems().addAll("Random","NMCS");
        AlgoChoiceBox.setValue("Random");


        VBox menu = new VBox(20);
        menu.setAlignment(Pos.CENTER);
        menu.getChildren().addAll(gameName,gameTypeChoiceBox,playerButton,computerButton,AlgoChoiceBox,algorithmButton);

        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        menuScene = new Scene(menu,screenBounds.getWidth(), screenBounds.getHeight());

        playerButton.setOnAction(e -> {
                    GameState gameModel = new GameState(getLineSize(gameTypeChoiceBox), getGameVersion(gameTypeChoiceBox));
                    GameScene gameScene = new GameScene(stage,menuScene,gameModel);
                    PlayerController pc = new PlayerController(gameModel,gameScene);
                    stage.setScene(gameScene.sc);

        });
        computerButton.setOnAction(e -> {
                    GameState gameModel = new GameState(getLineSize(gameTypeChoiceBox), getGameVersion(gameTypeChoiceBox), getAIAlgorithm(AlgoChoiceBox));
                    GameScene gameScene = new GameScene(stage, menuScene, gameModel);
                    stage.setScene(gameScene.sc);
                    AIController aic = new AIController(gameModel,gameScene);
                });

        stage.setScene(menuScene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    private int getLineSize(ChoiceBox<String> cb){
            return Integer.parseInt(cb.getValue().substring(0,1));

    }

    private GameVersion getGameVersion(ChoiceBox<String> cb){

        switch (cb.getValue().substring(1)){
            case "T":
                return new T();

            case "D":
                return new D();

            default:
                return new T();
        }
    }

    private AI getAIAlgorithm(ChoiceBox<String> cb){

        switch (cb.getValue()){
            case "Random":
                return new RandomAI();

            case "NMCS":
                return new NMCSAI();
            default:
                return new RandomAI();
        }

    }




}

