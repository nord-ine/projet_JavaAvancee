package org.nor.GameGUI;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.ScatterChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {



    private final static int HIGHT = 600;
    private final static int WIDTH = 700;
    Scene scene1, scene2;


    @Override
    public void start(Stage stage) {


        stage.setTitle("Morpion Game");

//Scene 1
        Label label1= new Label("This is the first scene");
        Button button1= new Button("Go to scene 2");
        Button buttun3 = new Button("new ");
        button1.setOnAction(e -> stage.setScene(scene2));
        HBox layout1 = new HBox(20);
        layout1.getChildren().addAll(label1, button1,buttun3);
        scene1= new Scene(layout1, WIDTH, HIGHT);

//Scene 2
        Label label2= new Label("This is the second scene");
        Button button2= new Button("Go to scene 1");
        button2.setOnAction(e -> stage.setScene(scene1));
        VBox layout2= new VBox(20);
        layout2.getChildren().addAll(label2, button2);
        scene2= new Scene(layout2,WIDTH,HIGHT);


        stage.setScene(scene1);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }


    }

