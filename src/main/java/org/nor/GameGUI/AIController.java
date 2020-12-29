package org.nor.GameGUI;

import org.nor.GameLogic.GameState;

public class AIController {
    private GameState model;
    private GameScene view;

    protected AIController(GameState model, GameScene view){
        this.view=view;
        this.model=model;
        playGame();
    }
    private void playGame(){
        model.startGame();
        view.displayGrid();
        view.updateScore();
    }
}
