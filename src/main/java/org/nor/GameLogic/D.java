package org.nor.GameLogic;

import java.util.List;
import java.util.Set;

public class D implements GameVersion {

	@Override
	public boolean canTUseThisPoint(Point p, Direction direction, List<Lines> setLines) {
		return p.getState() >= 0 && !p.isPointInSetLines(direction, setLines);
	}
	
}
