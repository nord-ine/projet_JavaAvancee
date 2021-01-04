package org.nor.GameLogic;

import java.util.List;

/**
 * class for a random research algorithm
 */
public class RandomAI extends AI{

	@Override
	public OnePointOneLine whichChoiceToTake(List<PointLines> listPointLines) {
		int randomPoint = (int)(Math.random() * (listPointLines.size()));
		int randomLine =  (int)(Math.random() * (listPointLines.get(randomPoint).getListLines().size()));	
		return new OnePointOneLine(listPointLines.get(randomPoint).getPoint(), listPointLines.get(randomPoint).getListLines().get(randomLine));
	}

}
