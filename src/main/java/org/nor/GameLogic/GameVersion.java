package org.nor.GameLogic;

import java.util.Set;

public interface GameVersion {
	boolean canTUseThisPoint(Point p, Direction direction,Set<Line> setLines);
}
