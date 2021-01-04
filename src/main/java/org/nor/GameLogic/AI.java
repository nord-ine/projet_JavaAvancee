package org.nor.GameLogic;

import java.util.List;

/**
 * abstract class that represents searching algorithms
 */
public abstract class AI{

	/**
	 * abstract method implemented by searching algorithms classes to make a choose the next move from the candidate valid PointLines
	 * @param listPointLines
	 * @return OnePointOneLine
	 */
	public abstract OnePointOneLine whichChoiceToTake(List<PointLines> listPointLines);

	/**
	 * method that launches the searching algorithm
	 * @param gs GameState
	 */
	public void startPlay(GameState gs) {
    	List<PointLines> listPointLines = gs.getValidePoints();
    	OnePointOneLine pointLine;
    	int shotNumber = 1;
    	while (listPointLines.size() > 0){
    		pointLine = whichChoiceToTake(listPointLines);
    		pointLine.point.setState(shotNumber);
			gs.getAllListLines().add(pointLine.line);
			gs.setScore(shotNumber);
			shotNumber++;
    		listPointLines = gs.getValidePoints();
		}
    }
}
