package org.nor;


import org.junit.jupiter.api.Test;


import org.nor.GameLogic.AI;
import org.nor.GameLogic.D;
import org.nor.GameLogic.GameState;
import org.nor.GameLogic.RandomAI;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AITest {

	@Test
	public void startPlayTest() {
		AI ai = new RandomAI();
		GameState gs= new GameState(5, new D());
		ai.startPlay(gs);
		assertTrue(gs.getValidePoints().size() == 0);
	}

}
