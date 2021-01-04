package org.nor.GameLogic;

import java.util.List;


/**
 * class that represents the D version of the Game
 */
public class T implements GameVersion{

	/**
	 * returns true if a point can be used to make a line , returns false otherwise
	 * @param p
	 * @param direction
	 * @param setLines
	 * @return
	 */
	@Override
	public boolean canIUseThisPoint(Point p, Direction direction, List<Lines> setLines) {
		return p.getState() >= 0 && !p.isPointTotallyInsideSetLines(direction, setLines);
	}
}
