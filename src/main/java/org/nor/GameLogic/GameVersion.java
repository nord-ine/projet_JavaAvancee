package org.nor.GameLogic;

import java.util.List;

public interface GameVersion {
	boolean canIUseThisPoint(Point p, Direction direction, List<Lines> setLines);
}
