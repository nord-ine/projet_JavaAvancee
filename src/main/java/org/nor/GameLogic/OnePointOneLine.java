package org.nor.GameLogic;

public class OnePointOneLine {
	private Point point;
	private Lines line;

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
