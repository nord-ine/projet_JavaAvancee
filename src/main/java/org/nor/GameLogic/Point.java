package org.nor.GameLogic;

import java.util.List;


/**
 * class that represents a point
 */
public class Point {

    private int x;
    private int y;
    private int state;

    /**
     * class constructor
     * @param x
     * @param y
     * @param shotNb
     * @param limitHight
     * @param limitWidth
     */
    public Point(int x, int y, int shotNb,int limitHight,int limitWidth){
    	if(valideCoordianate(x,limitHight) && valideCoordianate(y,limitWidth) ) {
            this.x = x;
            this.y = y;
            state =shotNb;
    	}else System.err.println("the point ("+ x +  ","+ y+ ")  cannot be instantiated");
    		
    }

    /**
     * class constructor
     * @param x
     * @param y
     * @param limitHight
     * @param limitWidth
     */
	public Point(int x, int y,int limitHight,int limitWidth){
    	if(valideCoordianate(x,limitHight) && valideCoordianate(y,limitWidth) ) {
            this.x = x;
            this.y = y;
    	}else System.err.println("the point ("+ x +  ","+ y+ ")  cannot be instantiated");
    }


    /**
     * class constructor
     * @param x
     * @param y
     */
    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }

    /**
     * class constructor
     * @param x
     * @param y
     * @param s
     */
    public Point(int x, int y,int s){
        this.x = x;
        this.y = y;
        this.state=s;
    }
    

    public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setState(int state) {
		this.state = state;
	}

    public int getState() {
        return state;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    public static boolean valideCoordianate(int x, int limit){
        return x >= 0 && x < limit;
    }


    /**
     * returns true if the point is in one of lines of the listLines in a particular direction
     * @param direction
     * @param listLines
     * @return
     */
    public boolean isPointInListLines(Direction direction, List<Line> listLines){
        for(Line l : listLines){
            if(l.getDirection() == direction){
            	
                if(l.isPointInLine(this)) {return true;}
            }
        }
        return false;
    }


    /**
     * returns true if the point is in one of lines of the listLines but not an extremity in a particular direction
     * @param direction
     * @param setLines
     * @return
     */
    public boolean isPointTotallyInsideSetLines(Direction direction, List<Line> setLines){
        for(Line l : setLines){
            if(l.getDirection() == direction){
                if(l.isPointTotallyInsideLine(this)) {;return true;}
            }
        }
        return false;
    }

    
    @Override
    public String toString() {
    	return "("+ x+  ","+ y+ ")";
    }

    @Override
    public boolean equals(Object o){
        if(! (o instanceof Point)) return false;

        Point p = (Point) o;

        return  (x==p.getX()) && (y==p.getY());
    }
}



