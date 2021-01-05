package org.nor.GameLogic;


import java.util.List;

/**
 * class that represents the D version of the Game
 */
public class D implements GameVersion {

	/**
	 * returns true if a point can be used to make a line , returns false otherwise
	 * @param p Point
	 * @param direction Direction
	 * @param listLines List<Lines>
	 * @return
	 */
	@Override
	public boolean canIUseThisPoint(Point p, Direction direction, List<Line> listLines) {
		return p.getState() >= 0 && !p.isPointInListLines(direction, listLines);
	}
	
}
