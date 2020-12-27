package org.nor.GameLogic;


import java.util.*;

public class PointLines {
	Point point;
	List<Line> listLines;
	
	public PointLines(Point p, List<Line> l){
		this.point = p;
		this.listLines = l;
	}


	@Override
	public String toString(){
		String str = point.toString();
		
		for(Line line : listLines) {
			str = str +" "+ line.toString();
		}
		return str;
	}
}
