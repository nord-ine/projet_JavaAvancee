package org.nor.GameLogic;

public class Point {

    private int x;
    private int y;
    private int shotNumber;

    public Point(int shotNb){
        shotNumber=shotNb;
    }

    public Point(int x, int y){
        this.x = y;
        this.y = y;
    }

    public int getShotNhmber() {
        return shotNumber;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


    @Override
    public boolean equals(Object o){
        if(! (o instanceof Point)) return false;

        Point p = (Point) o;

        return  (x==p.getX()) && (y==p.getY());
    }
}



