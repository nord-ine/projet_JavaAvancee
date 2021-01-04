package org.nor.GameLogic;

import java.util.List;

/**
 * interface used to define different game versions
 */
public interface GameVersion {
	boolean canIUseThisPoint(Point p, Direction direction, List<Lines> setLines);
}
