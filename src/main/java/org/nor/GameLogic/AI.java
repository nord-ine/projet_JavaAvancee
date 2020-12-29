package org.nor.GameLogic;

import java.util.List;

public abstract class AI{

	public abstract OnePointOneLine whichChoiceToTake(List<PointLines> listPointLines);
	
	public void startPlay(GameState gs) {
    	List<PointLines> listPointLines = gs.getValidePoints();
    	OnePointOneLine pointLine;
    	int shotNumber = 1;
    	while (listPointLines.size() > 0){
    		pointLine = whichChoiceToTake(listPointLines);
    		System.out.println(pointLine.point);
    		System.out.print(pointLine.line);
			System.out.print(shotNumber+"\n\n");
    		pointLine.point.setState(shotNumber);
			gs.getAllListLines().add(pointLine.line);
			gs.setScore(shotNumber);
			shotNumber++;
    		listPointLines = gs.getValidePoints();
		}
		System.out.print(gs.getAllListLines().size());

    }
}
