package org.nor.GameLogic;


import java.util.List;

public class D implements GameVersion {

	@Override
	public boolean canIUseThisPoint(Point p, Direction direction, List<Lines> setLines) {
		return p.getState() >= 0 && !p.isPointInSetLines(direction, setLines);
	}
	
}
