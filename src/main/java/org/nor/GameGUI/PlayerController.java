package org.nor.GameGUI;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import org.nor.GameLogic.GameState;
import org.nor.GameLogic.Lines;
import org.nor.GameLogic.Point;
import org.nor.GameLogic.PointLines;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PlayerController {

    GameState model;
    GameScene view;
    List<PointLines> listPointLines = new ArrayList<>();

   public  PlayerController(GameState model,GameScene view){
        this.view=view;
        this.model=model;
        listPointLines=model.getValidePoints();
        drawPointCandidates(listPointLines);
    }


    public void validateSolution(PointLines pl){
        //System.out.println(p);
        if(pl.getLines().size()>1){
            List<Line> listViewLine= new ArrayList<>();
            for(Lines line: pl.getLines()){

                Line l = new Line(view.mapModelCoordinateToViewCoordinate(line.getExtremite1().getY()), view.mapModelCoordinateToViewCoordinate(line.getExtremite1().getX()), view.mapModelCoordinateToViewCoordinate(line.getExtremite2().getY()), view.mapModelCoordinateToViewCoordinate(line.getExtremite2().getX()));
                listViewLine.add(l);
                l.setStroke(Color.rgb(getRandomRGBNumber(),getRandomRGBNumber(),getRandomRGBNumber()));
                l.setStrokeWidth(5);
                l.setOnMouseClicked(e->{
                    for(int i =0;i<listViewLine.size();i++) view.stack.getChildren().remove(listViewLine.get(i));
                    //view.stack.getChildren().remove(view.stack.getChildren().size());
                    validateFinalSolution(pl.getPoint(),line);
                });
                view.stack.getChildren().add(l);
            }

        }
        else {
            validateFinalSolution(pl.getPoint(),pl.getLines().get(0));
        }
    }

    private int getRandomRGBNumber(){

       return (int)(Math.random() * 255);
    }

    public void validateFinalSolution(Point p, Lines l){
        model.setScore(model.getScore()+1);
        p.setState(model.getScore());
        model.changeState(p,l);
        view.updateScore();
        view.drawLine(l);
        view.pointsGrid.add(view.getCheckedPoint(p),p.getY(),p.getX());
        listPointLines = model.getValidePoints();
        System.out.println(listPointLines);
        drawPointCandidates(listPointLines);

        /*model.setScore(model.getScore()+1);
        pl.getPoint().setState(model.getScore());
        model.changeState(pl.getPoint(),pl.getLines().get(0));
        view.drawLine(pl.getLines().get(0));
        view.pointsGrid.add(view.getCheckedPoint(pl.getPoint()),pl.getPoint().getY(),pl.getPoint().getX());
        listPointLines = model.getValidePoints();
        System.out.println(listPointLines);
        drawPointCandidates(listPointLines);*/
    }


    public void drawPointCandidates(List<PointLines> listPointLines){

        for(PointLines pl : listPointLines){
            ImageView viewPoint =new ImageView();
            viewPoint.xProperty().setValue(pl.getPoint().getX());
            viewPoint.yProperty().setValue(pl.getPoint().getY());
            viewPoint.setFitHeight(view.CELL_SIZE);
            viewPoint.setFitWidth(view.CELL_SIZE);
            viewPoint.setImage(new Image("file:Images/button-2.png"));
            viewPoint.setOnMouseClicked(e->{
                System.out.println(pl);
                reDrawPointCandidates(listPointLines);
                validateSolution(pl);
            });
            view.pointsGrid.add(viewPoint,pl.getPoint().getY(),pl.getPoint().getX());
        }
    }
    public void reDrawPointCandidates(List<PointLines> listPointLines){

        for(PointLines pl : listPointLines){
            ImageView viewPoint =new ImageView();
            viewPoint.xProperty().setValue(pl.getPoint().getX());
            viewPoint.yProperty().setValue(pl.getPoint().getY());
            viewPoint.setFitHeight(view.CELL_SIZE);
            viewPoint.setFitWidth(view.CELL_SIZE);
            viewPoint.setImage(new Image("file:Images/button-1.png"));
            view.pointsGrid.add(viewPoint,pl.getPoint().getY(),pl.getPoint().getX());
        }
    }


}
