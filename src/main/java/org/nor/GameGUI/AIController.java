package org.nor.GameGUI;

import org.nor.GameLogic.GameState;

/**
 * Controller class to manage the game in AI mode
 */
public class AIController {
    private GameState model;
    private GameScene view;

    /**
     * class constructor
     * @param model GameState
     * @param view GameScene
     */
    protected AIController(GameState model, GameScene view){
        this.view=view;
        this.model=model;
        playGame();
    }

    /**
     * method that controls the view and the modem
     */
    private void playGame(){
        model.startGame();
        view.displayGrid();
        view.updateScore();
    }
}
