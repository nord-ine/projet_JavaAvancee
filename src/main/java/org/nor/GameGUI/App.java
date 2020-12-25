package org.nor.GameGUI;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.ScatterChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.File;


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

        Image imgGO = new Image("file:images/go.jpg");
        //ImageView goView = new ImageView(imgGO);
        ImageView iv2 = new ImageView();
        iv2.setImage(imgGO);
        iv2.setFitWidth(100);
        iv2.setPreserveRatio(true);
        iv2.setSmooth(true);
        iv2.setCache(true);
        buttun3.setOnAction(e->System.out.println(iv2.getId()));
        HBox layout1 = new HBox(40);
        layout1.getChildren().addAll(label1, button1,buttun3,iv2);
        scene1= new Scene(layout1, WIDTH, HIGHT);


//Scene 2
        Label label2= new Label("This is the second scene");
        Button button2= new Button("Go to scene 1");
        button2.setOnAction(e -> stage.setScene(scene1));

        GridPane buttonGrid = new GridPane();
        Image img = new Image("file:Images/plus.png");
        //ImageView view = new ImageView(img);
        //view.setFitHeight(12);
        //view.setFitWidth(12);

        for(int i=0; i<20; i++) {
            for (int j = 0; j < 20; j++) {
                Button b = new Button();
                //b.setPrefSize(1, 1);
                //b.setGraphic(view);
                ImageView view = new ImageView(img);
                view.xProperty().setValue(i);
                view.yProperty().setValue(j);
                view.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                    System.out.println(view.xProperty().getValue()+" "+ view.yProperty().getValue());
                    event.consume();
                });
                view.setFitHeight(18);
                view.setFitWidth(18);
                buttonGrid.add(view, j, i);
            }
        }

        buttonGrid.setHgap(0);
        buttonGrid.setVgap(0);
        //buttonGrid.setGridLinesVisible(true);
        VBox layout2= new VBox(20);
        layout2.getChildren().addAll(label2, button2,buttonGrid);
        scene2= new Scene(layout2,WIDTH,HIGHT);


        stage.setScene(scene1);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }


    }

