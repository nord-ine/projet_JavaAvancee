package org.nor.GameLogic;

import java.util.Set;

public class D implements GameVersion {

	@Override
	public boolean canTUseThisPoint(Point p, Direction direction, Set<Line> setLines) {
		return p.getShotNumber() >= 0 && !p.isPointInSetLines(direction, setLines);
	}
	
}
