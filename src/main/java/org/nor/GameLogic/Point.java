package org.nor.GameLogic;

import java.util.List;
import java.util.Set;

public class Point {

    private int x;
    private int y;
    private int state;

    public Point(int x, int y, int shotNb,int limitHight,int limitWidth){
    	if(valideCoordianate(x,limitHight) && valideCoordianate(y,limitWidth) ) {
            this.x = x;
            this.y = y;
            state =shotNb;
    	}else System.err.println("the point ("+ x +  ","+ y+ ")  cannot be instantiated");
    		
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

	public Point(int x, int y,int limitHight,int limitWidth){
    	if(valideCoordianate(x,limitHight) && valideCoordianate(y,limitWidth) ) {
            this.x = x;
            this.y = y;
    	}else System.err.println("the point ("+ x +  ","+ y+ ")  cannot be instantiated");
    }


    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }

    public Point(int x, int y,int s){
        this.x = x;
        this.y = y;
        this.state=s;
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
    
    public boolean validePoint(int limitHight,int limitWidth){
    	return valideCoordianate(this.x, limitHight) && valideCoordianate(this.y, limitWidth);
    }
    
    

    public boolean isPointInSetLines(Direction direction, List<Lines> setLines){
        for(Lines l : setLines){
            if(l.getDirection() == direction){
            	
                if(l.isPointInLine(this)) {return true;}
            }
        }
        return false;
    }
    

    public boolean isPointTotallyInsideSetLines(Direction direction, List<Lines> setLines){
        for(Lines l : setLines){
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



