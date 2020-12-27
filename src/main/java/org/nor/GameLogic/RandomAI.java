package org.nor.GameLogic;

import java.util.List;

public class RandomAI extends AI{

	@Override
	public OnePointOneLine whichChoiceToTake(List<PointLines> listPointLines) {
		int randomPoint = (int)(Math.random() * (listPointLines.size()));
		int randomLine =  (int)(Math.random() * (listPointLines.get(randomPoint).listLines.size()));	
		return new OnePointOneLine(listPointLines.get(randomPoint).point, listPointLines.get(randomPoint).listLines.get(randomLine));
		
	}

}
