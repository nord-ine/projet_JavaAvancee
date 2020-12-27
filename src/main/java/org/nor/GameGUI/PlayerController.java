package org.nor.GameGUI;

import org.nor.GameLogic.GameState;
import org.nor.GameLogic.PointLines;

import java.util.ArrayList;
import java.util.List;

public class PlayerController {

    GameState model;
    GameScene view;
   public  PlayerController(GameState model,GameScene view){
        this.view=view;
        this.model=model;
    }


    public void startGame(){
       List<PointLines> listPointLines = new ArrayList<>();
       do{
           listPointLines = model.getValidePoints();
           view.drawPointCandidates(listPointLines);


       } while(listPointLines.size()>0);
    }

    public void setListners(){

    }

}
