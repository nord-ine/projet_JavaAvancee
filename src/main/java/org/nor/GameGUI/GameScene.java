package org.nor.GameGUI;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import org.nor.GameLogic.GameState;
import org.nor.GameLogic.Lines;
import org.nor.GameLogic.Point;
import org.nor.GameLogic.PointLines;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class GameScene {
    Scene sc;
    Stage window;
    Scene menu;
    GameState model;

    GridPane pointsGrid;
    Pane stack;
    int score = 0;
    private final static int CELL_SIZE=28;
    final static double CELL_SIZE_BIAS = 8;

    static int counter=0;
    private StringProperty scoreLabelValue = new SimpleStringProperty("score : 0");

    public GameScene(Stage window, Scene menu, GameState model){
        this.menu=menu;
        this.window=window;
        this.model=model;
        buildGameScene();
    }

    public ImageView getImageView(Point p){
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

    public void drawPointCandidates(List<PointLines> listPointLines){

        for(PointLines pl : listPointLines){
            ImageView viewPoint =new ImageView();
            viewPoint.xProperty().setValue(pl.getPoint().getX());
            viewPoint.yProperty().setValue(pl.getPoint().getY());
            viewPoint.setFitHeight(CELL_SIZE);
            viewPoint.setFitWidth(CELL_SIZE);
            viewPoint.setImage(new Image("file:Images/button-2.png"));
            pointsGrid.add(viewPoint,pl.getPoint().getX(),pl.getPoint().getY());
        }
    }
    public void reDrawPointCandidates(List<PointLines> listPointLines){

        for(PointLines pl : listPointLines){
            ImageView viewPoint =new ImageView();
            viewPoint.xProperty().setValue(pl.getPoint().getX());
            viewPoint.yProperty().setValue(pl.getPoint().getY());
            viewPoint.setFitHeight(CELL_SIZE);
            viewPoint.setFitWidth(CELL_SIZE);
            viewPoint.setImage(new Image("file:Images/button-1.png"));
            pointsGrid.add(viewPoint,pl.getPoint().getX(),pl.getPoint().getY());
        }
    }

    //function to replace image of point with the shot number image

    public StackPane getCheckedPoint(Point p){
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




    public void upadateViewPoint(List<Point> listPoints){

        for(Point p : listPoints){
            pointsGrid.add(getImageView(p),p.getY(),p.getX());
        }
    }

    public void drawChoiceLines(List<Lines> listLines){
        for(Lines line: listLines){
            Line l = new Line(mapModelCoordinateToViewCoordinate(line.getExtremite1().getY()), mapModelCoordinateToViewCoordinate(line.getExtremite1().getX()), mapModelCoordinateToViewCoordinate(line.getExtremite2().getY()), mapModelCoordinateToViewCoordinate(line.getExtremite2().getX()));
            l.setStroke(Color.GREEN);
            l.setStrokeWidth(3);
            stack.getChildren().add(l);
        }
    }

    public void drawMoveLine(Lines line){
        Line l = new Line(mapModelCoordinateToViewCoordinate(line.getExtremite1().getY()), mapModelCoordinateToViewCoordinate(line.getExtremite1().getX()), mapModelCoordinateToViewCoordinate(line.getExtremite2().getY()), mapModelCoordinateToViewCoordinate(line.getExtremite2().getX()));
        l.setStroke(Color.GRAY);
        stack.getChildren().add(l);
        counter++;
        System.out.println("jai affché ca"+counter);
    }

    private double mapModelCoordinateToViewCoordinate(int coordinate){
        return coordinate*CELL_SIZE+CELL_SIZE/2;
    }

    protected void displayGrid(){


        //pointsGrid = new GridPane();
        Point p;
        for(int i=0; i<20; i++) {
            for (int j = 0; j < 20; j++) {
                p=model.getGameGrid()[i][j];
                if(p.getState()>0){
                    //pointsGrid.setColumnIndex(getCheckedPoint(p), i);
                    //pointsGrid.setRowIndex(getCheckedPoint(p), j);
                    //pointsGrid.getChildren().add(i*j,getCheckedPoint(p));
                    pointsGrid.add(getCheckedPoint(p),j,i);
                }
                else {
                    pointsGrid.add(getImageView(p),j,i);
                }
                //ImageView viewPoint = getImageView(model.getGameGrid()[i][j]);
                /*Label ll = new Label("25");
                ll.setFont(new Font("Arial",5));
                StackPane sp = new StackPane(viewPoint,ll);*/
                //pointsGrid.add(viewPoint, j,i);
            }
        }
        drawAllLines(model.getAllListLines());

    }


    private void drawAllLines(List<Lines> listLines){
        for(Lines l : listLines) drawMoveLine(l);
    }


    protected void buildGameScene(){
        Label labelGameversion= new Label("game version : ");
        Label labelGameRecord= new Label("game record : ");
        Button goBackToMenuButton= new Button("quit game");
        goBackToMenuButton.setOnAction(e -> window.setScene(menu));

        Button  bb = new Button("b");
        bb.setOnAction(e->{
            drawMoveLine(new Lines(new Point(1,1),new Point(4,2)));
        });
        HBox header= new HBox(200);
        header.getChildren().addAll(labelGameversion, labelGameRecord,goBackToMenuButton,bb);

        pointsGrid = new GridPane();
        stack = new Pane();

        displayGrid();


        pointsGrid.setAlignment(Pos.CENTER);

        //Line l = new Line(mapModelCoordinateToViewCoordinate(0), mapModelCoordinateToViewCoordinate(3), mapModelCoordinateToViewCoordinate(4), mapModelCoordinateToViewCoordinate(4));
        //l.setStroke(Color.RED);

        stack.getChildren().add(pointsGrid);

        BorderPane borderPane = new BorderPane();

        Label scoreLabel = new Label(scoreLabelValue.getValue());
        scoreLabel.textProperty().bind(scoreLabelValue);

        borderPane.setTop(header);
        borderPane.setCenter(stack);
        borderPane.setLeft(scoreLabel);



        BorderPane.setMargin(stack, new Insets(30,120,120,120));


        sc= new Scene(borderPane);



    }

    protected void UpdateScore(){
        scoreLabelValue.setValue("score : "+model.getScore());
    }
}

