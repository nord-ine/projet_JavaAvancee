package org.nor.GameLogic;

import java.util.List;
import java.util.Set;

public interface GameVersion {
	boolean canTUseThisPoint(Point p, Direction direction, List<Lines> setLines);
}
