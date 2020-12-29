package org.nor.GameGUI;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.nor.GameLogic.GameState;
import org.nor.GameLogic.Lines;
import org.nor.GameLogic.Point;
import org.nor.GameLogic.PointLines;

import java.util.ArrayList;
import java.util.List;

public class GameScene {
    Scene sc;
    Stage window;
    Scene menu;
    GameState model;

    GridPane pointsGrid;
    Pane stack;

    final static int CELL_SIZE=28;
    private StringProperty scoreLabelValue = new SimpleStringProperty("score : 0");

    public GameScene(Stage window, Scene menu, GameState model){
        this.menu=menu;
        this.window=window;
        this.model=model;
        buildGameScene();
    }



    protected void buildGameScene(){
        Label gameVersionLabel = new Label("model.getGameVersion().toString()");
        Button goBackToMenuButton= new Button("quit game");
        goBackToMenuButton.setOnAction(e -> window.setScene(menu));


        HBox header= new HBox(300);
        header.getChildren().addAll(gameVersionLabel,goBackToMenuButton);

        header.setAlignment(Pos.CENTER);
        pointsGrid = new GridPane();
        stack = new Pane();

        displayGrid();

        pointsGrid.setAlignment(Pos.CENTER);

        stack.getChildren().add(pointsGrid);

        BorderPane borderPane = new BorderPane();

        Label scoreLabel = new Label(scoreLabelValue.getValue());
        scoreLabel.textProperty().bind(scoreLabelValue);

        borderPane.setTop(header);
        borderPane.setCenter(stack);
        borderPane.setLeft(scoreLabel);

        BorderPane.setMargin(stack, new Insets(50,120,120,300));

        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        sc= new Scene(borderPane,screenBounds.getWidth(), screenBounds.getHeight());

    }
    public void updateScore(){
        scoreLabelValue.setValue("score : "+model.getScore());
    }

    protected void displayGrid(){
        Point p;
        for(int i=0; i<GameState.getGridHight(); i++) {
            for (int j = 0; j < GameState.getGridWidth(); j++) {
                p=model.getGameGrid()[i][j];
                if(p.getState()>0){

                    pointsGrid.add(getViewOfAMovePoint(p),j,i);
                }
                else {
                    pointsGrid.add(getViewOfAPoint(p),j,i);
                }
            }
        }
        drawAllLines(model.getAllListLines());

    }

    private ImageView getViewOfAPoint(Point p){
        ImageView viewPoint =new ImageView();
        viewPoint.xProperty().setValue(p.getX());
        viewPoint.yProperty().setValue(p.getY());

        viewPoint.setFitHeight(CELL_SIZE);
        viewPoint.setFitWidth(CELL_SIZE);
        switch (p.getState()){
            case -2:
                viewPoint.setImage(new Image("file:Images/button-2.png"));
                return viewPoint;
            case -1:
                viewPoint.setImage(new Image("file:Images/button-1.png"));
                return viewPoint;
            default:
                viewPoint.setImage(new Image("file:Images/button0.png"));
                return viewPoint;
        }

    }
    //function to replace image of point with the shot number image

    protected StackPane getViewOfAMovePoint(Point p){
        int i =p.getX();
        int j = p.getY();

        ImageView imV = new ImageView(new Image("file:Images/button-x.png"));
        imV.xProperty().setValue(i);
        imV.yProperty().setValue(j);
        imV.setFitHeight(CELL_SIZE);
        imV.setFitWidth(CELL_SIZE);
        Label ll = new Label(String.valueOf(p.getState()));
        ll.setFont(Font.font("Verdana", FontWeight.EXTRA_BOLD, 10));
        StackPane sp = new StackPane(imV,ll);
        return sp;
    }
    private void drawAllLines(List<Lines> listLines){
        for(Lines l : listLines) drawLine(l);
    }

    protected void drawLine(Lines line){
        Line l = new Line(mapModelCoordinateToViewCoordinate(line.getExtremite1().getY()), mapModelCoordinateToViewCoordinate(line.getExtremite1().getX()), mapModelCoordinateToViewCoordinate(line.getExtremite2().getY()), mapModelCoordinateToViewCoordinate(line.getExtremite2().getX()));
        l.setStroke(Color.GRAY);
        stack.getChildren().add(l);
    }

    public void drawCandidatePoints(List<PointLines> listPointLines, PlayerController playerController){

        for(PointLines pl : listPointLines){
            ImageView viewPoint =new ImageView();
            viewPoint.xProperty().setValue(pl.getPoint().getX());
            viewPoint.yProperty().setValue(pl.getPoint().getY());
            viewPoint.setFitHeight(CELL_SIZE);
            viewPoint.setFitWidth(CELL_SIZE);
            viewPoint.setImage(new Image("file:Images/button-2.png"));
            viewPoint.setOnMouseClicked(e->{
                //System.out.println(pl);
                eraseDrawOfCandidatePoints(listPointLines);
                playerController.validateLine(pl);
            });
            pointsGrid.add(viewPoint,pl.getPoint().getY(),pl.getPoint().getX());
        }
    }
    public void eraseDrawOfCandidatePoints(List<PointLines> listPointLines){

        for(PointLines pl : listPointLines){
            ImageView viewPoint =new ImageView();
            viewPoint.xProperty().setValue(pl.getPoint().getX());
            viewPoint.yProperty().setValue(pl.getPoint().getY());
            viewPoint.setFitHeight(CELL_SIZE);
            viewPoint.setFitWidth(CELL_SIZE);
            viewPoint.setImage(new Image("file:Images/button-1.png"));
            pointsGrid.add(viewPoint,pl.getPoint().getY(),pl.getPoint().getX());
        }
    }

    protected void drawChoiceLines(PointLines pl,PlayerController playerController){
        List<Line> listViewLine= new ArrayList<>();
        for(Lines line: pl.getLines()){

            Line l = new Line(mapModelCoordinateToViewCoordinate(line.getExtremite1().getY()), mapModelCoordinateToViewCoordinate(line.getExtremite1().getX()),mapModelCoordinateToViewCoordinate(line.getExtremite2().getY()),mapModelCoordinateToViewCoordinate(line.getExtremite2().getX()));
            listViewLine.add(l);
            l.setStroke(Color.rgb(getRandomRGBNumber(),getRandomRGBNumber(),getRandomRGBNumber()));
            l.setStrokeWidth(5);
            l.setOnMouseClicked(e->{
                for(int i =0;i<listViewLine.size();i++) stack.getChildren().remove(listViewLine.get(i));
                playerController.validateMove(pl.getPoint(),line);
            });
            stack.getChildren().add(l);
        }
    }
    private int getRandomRGBNumber(){

        return (int)(Math.random() * 255);
    }


    private double mapModelCoordinateToViewCoordinate(int coordinate){
        return coordinate*CELL_SIZE+CELL_SIZE/2;
    }



}

