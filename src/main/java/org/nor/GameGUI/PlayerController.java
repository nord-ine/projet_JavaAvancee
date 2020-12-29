package org.nor.GameGUI;

import org.nor.GameLogic.GameState;
import org.nor.GameLogic.Lines;
import org.nor.GameLogic.Point;
import org.nor.GameLogic.PointLines;

import java.util.ArrayList;
import java.util.List;

public class PlayerController {

    private GameState model;
    private GameScene view;
    private List<PointLines> listPointLines = new ArrayList<>();

   protected PlayerController(GameState model,GameScene view){
        this.view=view;
        this.model=model;
        listPointLines=model.getValidePoints();
        playMove(listPointLines);
        //drawPointCandidates(listPointLines);
    }

    private void playMove(List<PointLines> listPointLines){
        // TODO: 29/12/2020  add gameover here
        view.drawCandidatePoints(listPointLines,this);

    }
    protected void validateLine(PointLines pl){
        //System.out.println(p);
        if(pl.getLines().size()>1){

            view.drawChoiceLines(pl,this);
        }
        else {
            validateMove(pl.getPoint(),pl.getLines().get(0));
        }
    }
    protected void validateMove(Point p, Lines l){
        model.setScore(model.getScore()+1);
        p.setState(model.getScore());
        model.changeState(p,l);
        view.updateScore();
        view.drawLine(l);
        view.pointsGrid.add(view.getViewOfAMovePoint(p),p.getY(),p.getX());
        listPointLines = model.getValidePoints();
        System.out.println(listPointLines);
        playMove(listPointLines);
    }
}
