package org.nor.GameLogic;

public class Line {

    private Point extremite1;
    private Point extremite2;

    private Direction direction;



    public Line(Point s,Point e){
        extremite1=s;
        extremite2=e;
        direction = direction.none;
        if(s.getX()==e.getX()) direction=Direction.horizontal;
        if(s.getY()==e.getY()) direction=Direction.vertical;
        if((s.getX()>e.getX() && s.getY()>e.getY()) ||(s.getX()<e.getX() && s.getY()<e.getY())) direction=Direction.diagonal1;
        if((s.getX()>e.getX() && s.getY()<e.getY()) ||(s.getX()<e.getX() && s.getY()>e.getY())) direction=Direction.diagonal2;

    }


    public Direction getDirection() {
        return direction;
    }
 
    public Point getExtremite2() {
        return extremite2;
    }

    public Point getExtremite1() {
        return extremite1;
    }
    
    
    
    public boolean isPointInLine(Point p){
        Point vecteur1 = new Point( p.getX() - this.getExtremite1().getX(),  p.getY() -this.getExtremite1().getY());

        Point vecteur2 = new Point( this.getExtremite2().getX() - this.getExtremite1().getX(),  this.getExtremite2().getY() -this.getExtremite1().getY());

        float k1 = (float)vecteur1.getX()/vecteur2.getX();
        if(vecteur2.getY()*k1 == vecteur1.getY() && k1 >= 0 && k1 <= 1) return true;
        
        
        float k2 = (float)vecteur1.getY()/vecteur2.getY();
        if(vecteur2.getX()*k2 == vecteur1.getX() && k2 >= 0 && k2 <= 1) return true;
        
        return false;
    }
    
    public boolean isPointExtrimityLine(Point p) {
    	return p.equals(this.extremite1) || p.equals(this.extremite2);
    }
    
    
    public boolean isPointTotallyInsideLine(Point p) {
    	return isPointInLine(p) && !isPointExtrimityLine(p);
    }
    
    
    
    
    
    @Override
    public String toString () {
    	return "extrémité " + this.extremite1 + " extremité " + this.extremite2 + "\n";
    }

    @Override
    public boolean equals(Object o){
        if(! (o instanceof Line)) return false;

        Line l = (Line) o;

        return  (extremite1==l.getExtremite1()) && (extremite2==l.getExtremite2()) || (extremite1==l.getExtremite2()) && (extremite2==l.getExtremite1());
    }
}
