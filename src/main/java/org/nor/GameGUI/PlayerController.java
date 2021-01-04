package org.nor.GameGUI;

import org.nor.GameLogic.GameState;
import org.nor.GameLogic.Lines;
import org.nor.GameLogic.Point;
import org.nor.GameLogic.PointLines;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller class to manage the game in player mode
 */
public class PlayerController {

    private GameState model;
    private GameScene view;
    private List<PointLines> listPointLines = new ArrayList<>();

    /**
     * class constructor
     * @param model GameState
     * @param view GameScene
     */
   protected PlayerController(GameState model,GameScene view){
        this.view=view;
        this.model=model;
        listPointLines=model.getValidePoints();
        playMove(listPointLines);
        //drawPointCandidates(listPointLines);
    }


    /**
     * method callsed when the player plays a move
     * @param listPointLines  List<PointLines>
     */
    private void playMove(List<PointLines> listPointLines){
        view.drawCandidatePoints(listPointLines,this);

    }

    /**
     * method for validating the choice of a line by the player
     * @param pl PointLines
     */
    protected void validateLine(PointLines pl){
        //System.out.println(p);
        if(pl.getListLines().size()>1){

            view.drawChoiceLines(pl,this);
        }
        else {
            validateMove(pl.getPoint(),pl.getListLines().get(0));
        }
    }


    /**
     * method for validating a move
     * @param p Point
     * @param l Lines
     */
    protected void validateMove(Point p, Lines l){
        model.setScore(model.getScore()+1);
        p.setState(model.getScore());
        model.changeState(p,l);
        view.updateScore();
        view.drawLine(l);
        view.pointsGrid.add(view.getViewOfAMovePoint(p),p.getY(),p.getX());
        listPointLines = model.getValidePoints();
        playMove(listPointLines);
    }
}
