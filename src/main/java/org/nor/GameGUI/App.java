package org.nor.GameGUI;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import org.nor.GameLogic.*;


/**
 * JavaFX App
 */
public class App extends Application {

    GameState gameModel;

    Scene menuScene;
    GameScene gameScene;

    final static double bias = 10;



    @Override
    public void start(Stage stage) {


        stage.setTitle("Morpion Game");
        stage.setResizable(false);

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


        VBox layout1 = new VBox(20);
        layout1.setAlignment(Pos.CENTER);
        layout1.getChildren().addAll(gameName,gameTypeChoiceBox,playerButton,computerButton,AlgoChoiceBox,algorithmButton);
        menuScene = new Scene(layout1);

        playerButton.setOnAction(e -> {
            gameModel = new GameState(getLineSize(gameTypeChoiceBox), getGameVersion(gameTypeChoiceBox));
            gameScene = new GameScene(stage,menuScene,gameModel);
            PlayerController pc = new PlayerController(gameModel,gameScene);
            stage.setScene(gameScene.sc);



        });
        computerButton.setOnAction(e -> {
                    gameModel = new GameState(getLineSize(gameTypeChoiceBox), getGameVersion(gameTypeChoiceBox), getAIAlgorithm(AlgoChoiceBox));
                    gameScene = new GameScene(stage, menuScene, gameModel);
                    stage.setScene(gameScene.sc);
                    gameModel.startGame();
                    gameScene.displayGrid();
                    gameScene.UpdateScore();

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

