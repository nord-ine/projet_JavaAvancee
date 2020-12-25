package org.nor.GameLogic;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PointLines {
	Point point;
	List<Line> listLines;
	
	public PointLines(Point p, List<Line> l){
		point = p;
		listLines = l;
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
