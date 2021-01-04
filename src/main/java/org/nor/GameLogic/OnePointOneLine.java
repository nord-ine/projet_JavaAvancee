package org.nor.GameLogic;

/**
 * class containing a move point and it's corresponding line
 */
public class OnePointOneLine {
	private Point point;
	private Lines line;

	/**
	 * class constructor
	 * @param point
	 * @param line
	 */
	public OnePointOneLine(Point point, Lines line) {
		this.point=point;
		this.line = line;
	}
	
	public Point getPoint() {
		return point;
	}

	public void setPoint(Point point) {
		this.point = point;
	}

	public Lines getLine() {
		return line;
	}

	public void setLine(Lines line) {
		this.line = line;
	}

}
