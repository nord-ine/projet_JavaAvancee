package org.nor.GameLogic;


import java.util.*;

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

	@Override
	public String toString(){
		String str = point.toString();
		
		for(Lines line : listLines) {
			str = str +" "+ line.toString();
		}
		return str;
	}
}
