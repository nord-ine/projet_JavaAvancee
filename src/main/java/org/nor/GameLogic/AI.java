package org.nor.GameLogic;

import java.util.List;

public abstract class AI implements GameMode{

	public abstract OnePointOneLine whichChoiceToTake(List<PointLines> listPointLines);
	
	public void startPlay(GameState gs) {
    	List<PointLines> listPointLines = gs.getValidePoints();
    	OnePointOneLine pointLine;
    	int shotNumber = 1;
    	while (listPointLines.size() > 0){
    		pointLine = whichChoiceToTake(listPointLines);
    		System.out.println(pointLine.point);
    		System.out.print(pointLine.line);
    		pointLine.point.setShotNumber(shotNumber);
			gs.getAllListLines().add(pointLine.line);
			shotNumber++;
    		listPointLines = gs.getValidePoints();
		}
    }
}
