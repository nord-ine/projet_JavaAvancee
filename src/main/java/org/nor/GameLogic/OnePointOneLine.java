package org.nor.GameLogic;

/**
 * class containing a move point and it's corresponding line
 */
public class OnePointOneLine {
	private Point point;
	private Line line;

	/**
	 * class constructor
	 * @param point
	 * @param line
	 */
	public OnePointOneLine(Point point, Line line) {
		this.point=point;
		this.line = line;
	}
	
	public Point getPoint() {
		return point;
	}

	public void setPoint(Point point) {
		this.point = point;
	}

	public Line getLine() {
		return line;
	}

	public void setLine(Line line) {
		this.line = line;
	}

}
