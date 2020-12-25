package org.nor.GameLogic;

public class Line {

    private Point startPoint;
    private Point endpoint;

    private Direction dir;



    public Line(Point s,Point e){
        startPoint=s;
        endpoint=e;
        if(s.getX()==e.getX()) dir=Direction.horizontal;
        if(s.getY()==e.getY()) dir=Direction.vertical;
        if((s.getX()>e.getX() && s.getY()>e.getY()) ||(s.getX()<e.getX() && s.getY()<e.getY())) dir=Direction.diagonal1;
        if((s.getX()>e.getX() && s.getY()<e.getY()) ||(s.getX()<e.getX() && s.getY()>e.getY())) dir=Direction.diagonal2;

    }


    public Direction getDir() {
        return dir;
    }
 
    public Point getEndpoint() {
        return endpoint;
    }

    public Point getStartPoint() {
        return startPoint;
    }
    
    @Override
    public String toString () {
    	return "extrémité " + this.startPoint + " extremité " + this.endpoint + "\n";
    }

    @Override
    public boolean equals(Object o){
        if(! (o instanceof Line)) return false;

        Line l = (Line) o;

        return  (startPoint==l.getStartPoint()) && (endpoint==l.getEndpoint()) || (startPoint==l.getEndpoint()) && (endpoint==l.getStartPoint());
    }
}
