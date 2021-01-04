package org.nor.GameLogic;


import java.util.*;

/**
 * class that represents a data structure for a valid point and the valid lines that pass through it
 */
public class PointLines {
	private Point point;
	private List<Lines> listLines;

	/**
	 * class constructor
	 * @param p
	 * @param l
	 */
	public PointLines(Point p, List<Lines> l){
		this.point = p;
		this.listLines = l;
	}

	public Point getPoint(){
		return point;
	}

	public List<Lines> getListLines(){
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
	

    @Override
    public boolean equals(Object o){
        if(! (o instanceof PointLines)) return false;

        PointLines l = (PointLines) o;

        return  this.getPoint().equals(l.getPoint()) && this.getListLines().equals(l.getListLines());
    }
}
