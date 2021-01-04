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


/**
 * class that contains the game scene object and the grid and methods to draw components in this scene
 */
public class GameScene {

    Scene sc;
    Stage window;
    Scene menu;
    GameState model;

    GridPane pointsGrid;
    Pane stack;

    final static int CELL_SIZE=28;
    private StringProperty scoreLabelValue = new SimpleStringProperty("score : 0");

    /**
     * @param window the GUI window
     * @param menu the menu scene (created in the class App)
     * @param model an instantiation of the Gamestate class
     */
    public GameScene(Stage window, Scene menu, GameState model){
        this.menu=menu;
        this.window=window;
        this.model=model;
        buildGameScene();
    }


    /**
     * method responsible for drawing the initial view of the game scene (buttons , initial grid)
     */
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

    /**
     * method for updating the view of the score attribute
     */
    public void updateScore(){
        scoreLabelValue.setValue("score : "+model.getScore());
    }


    /**
     * method responsible of drawing the grid
     */
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

    /**
     * creates an imageview component corresponding to the state value of the point p
     * @param p Point
     * @return  Imageview
     */
    private ImageView getViewOfAPoint(Point p){
        ImageView viewPoint =new ImageView();
        viewPoint.xProperty().setValue(p.getX());
        viewPoint.yProperty().setValue(p.getY());
        viewPoint.setFitHeight(CELL_SIZE);
        viewPoint.setFitWidth(CELL_SIZE);

            if(p.getState()==-1) {
                viewPoint.setImage(new Image("file:button-1.png"));
                return viewPoint;
            }
            else {
                viewPoint.setImage(new Image("file:button0.png"));
                return viewPoint;
            }
        }

    //function to replace image of point with the shot number image

    /**
     * creates a stackpane by stacking an image with a text corresponding the value of the point's p state
     * @param p Point
     * @return  StackPane
     */
    protected StackPane getViewOfAMovePoint(Point p){
        int i =p.getX();
        int j = p.getY();

        ImageView imV = new ImageView(new Image("file:button-x.png"));
        imV.xProperty().setValue(i);
        imV.yProperty().setValue(j);
        imV.setFitHeight(CELL_SIZE);
        imV.setFitWidth(CELL_SIZE);
        Label ll = new Label(String.valueOf(p.getState()));
        ll.setFont(Font.font("Verdana", FontWeight.EXTRA_BOLD, 10));
        StackPane sp = new StackPane(imV,ll);
        return sp;
    }

    /**
     * draw a list of lines on the grid
     * @param listLines
     */
    private void drawAllLines(List<Lines> listLines){
        for(Lines l : listLines) drawLine(l);
    }


    /**
     * draw a line on the grid
     * @param line
     */
    protected void drawLine(Lines line){
        Line l = new Line(mapModelCoordinateToViewCoordinate(line.getExtremite1().getY()), mapModelCoordinateToViewCoordinate(line.getExtremite1().getX()), mapModelCoordinateToViewCoordinate(line.getExtremite2().getY()), mapModelCoordinateToViewCoordinate(line.getExtremite2().getX()));
        l.setStroke(Color.GRAY);
        stack.getChildren().add(l);
    }


    /**
     * draw red points on the grid to give a hint for the player about what points he can choose as move
     * @param listPointLines PointLines
     * @param playerController PlayerController
     */
    public void drawCandidatePoints(List<PointLines> listPointLines, PlayerController playerController){

        for(PointLines pl : listPointLines){
            ImageView viewPoint =new ImageView();
            viewPoint.xProperty().setValue(pl.getPoint().getX());
            viewPoint.yProperty().setValue(pl.getPoint().getY());
            viewPoint.setFitHeight(CELL_SIZE);
            viewPoint.setFitWidth(CELL_SIZE);
            viewPoint.setImage(new Image("file:button-2.png"));
            viewPoint.setOnMouseClicked(e->{
                //System.out.println(pl);
                eraseDrawOfCandidatePoints(listPointLines);
                playerController.validateLine(pl);
            });
            pointsGrid.add(viewPoint,pl.getPoint().getY(),pl.getPoint().getX());
        }
    }

    /**
     * method for erasing the red points drawn by the drawCandidatePoints method
     * @param listPointLines PointLines
     */
    public void eraseDrawOfCandidatePoints(List<PointLines> listPointLines){

        for(PointLines pl : listPointLines){
            ImageView viewPoint =new ImageView();
            viewPoint.xProperty().setValue(pl.getPoint().getX());
            viewPoint.yProperty().setValue(pl.getPoint().getY());
            viewPoint.setFitHeight(CELL_SIZE);
            viewPoint.setFitWidth(CELL_SIZE);
            viewPoint.setImage(new Image("file:button-1.png"));
            pointsGrid.add(viewPoint,pl.getPoint().getY(),pl.getPoint().getX());
        }
    }


    /**
     * method for drawing possible lines to choose to play a move
     * @param pl PointLines
     * @param playerController PlayerController
     */
    protected void drawChoiceLines(PointLines pl,PlayerController playerController){
        List<Line> listViewLine= new ArrayList<>();
        for(Lines line: pl.getListLines()){

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

    /**
     * creates a random number between 0 and 255
     * @return int
     */
    private int getRandomRGBNumber(){

        return (int)(Math.random() * 255);
    }


    /**
     * method for mapping model coordinates to the view coordinates
     * @param coordinate int
     * @return double
     */
    private double mapModelCoordinateToViewCoordinate(int coordinate){
        return coordinate*CELL_SIZE+CELL_SIZE/2;
    }


}

