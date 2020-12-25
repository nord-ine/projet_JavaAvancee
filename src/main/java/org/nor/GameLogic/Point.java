package org.nor.GameLogic;

public class Point {

    private int x;
    private int y;
    public int shotNumber;

    public Point(int x, int y, int shotNb){
        this.x = x;
        this.y = y;
        shotNumber=shotNb;
    }

    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getShotNumber() {
        return shotNumber;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
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



