package org.nor.GameLogic;

import java.util.Set;

public class T implements GameVersion{

	@Override
	public boolean canTUseThisPoint(Point p, Direction direction, Set<Line> setLines) {
		return p.getShotNumber() >= 0 && !p.isPointTotallyInsideSetLines(direction, setLines);
	}
}
