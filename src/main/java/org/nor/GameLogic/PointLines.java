package org.nor.GameLogic;


import java.util.*;

/**
 * class that represents a data structure for a valid point and the valid lines that pass through it
 */
public class PointLines {
	Point point;
	List<Lines> listLines;
	
	public PointLines(Point p, List<Lines> l){
		this.point = p;
		this.listLines = l;
	}

	public Point getPoint(){
		return point;
	}

	public List<Lines> getLines(){
		return listLines;
	}
	@Override
	public String toString(){
		String str = point.toString();
		
		for(Lines line : listLines) {
			str = str +" "+ line.toString();
		}
		return str;
	}
}
