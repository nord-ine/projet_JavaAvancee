package org.nor.GameLogic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GameState {


    private final static int GRID_HIGHT = 20;
    private final static int GRID_WIDTH = 20;
    GameVersion gameVersion;
    int lineSize;
    boolean touch;
    Point[][] gameGrid = new Point[GRID_HIGHT][GRID_WIDTH];
    public List<Line> listLines = new ArrayList<>();
    private int score;

    public GameState(){
    	for (int i = 0; i < GRID_HIGHT ; i++) {
    		for ( int j = 0 ; j < GRID_WIDTH ; j++ ) {
    			this.gameGrid[i][j] = new Point(i, j, -1); 
    		}
    	}
    }


    public List<PointLines> getValidePoints(){
    	List<PointLines> listPointLines = new ArrayList<>();
    	PointLines pl;
    	List<Line> listLines;

        for(int i =0; i < GRID_HIGHT; i++){
            for(int j=0; j < GRID_WIDTH; j++){
                if(gameGrid[i][j].getShotNumber() == -1){
                	listLines = getPossibleLinesOfShots(gameGrid[i][j]);
                	if(listLines.size() > 0){
                		pl = new PointLines(gameGrid[i][j],listLines);
                		listPointLines.add(pl);
                	}
                }
            }
        }
        return listPointLines;
    }


    public List<Line> getPossibleLinesOfShots(Point p){
        int x = p.getX();
        int y = p.getY();
        int x_,y_;

        List<Line> candidatesLines= new ArrayList<>();

        for(y_ = y-4; y_<=y ; y_++){
        	if(valideCoordianateY(y_) && valideCoordianateX(y_ + 4) ) {
        		candidatesLines.add(new Line(gameGrid[x][y_],gameGrid[x][y_+4]));
        	}
        }

        for(x_ = x-4 ; x_<=x ; x_++){
        	if(valideCoordianateY(x_) && valideCoordianateX(x_ + 4) ) {
        		candidatesLines.add(new Line(gameGrid[x_][y],gameGrid[x_+4][y]));
        	}
        }

        for(int i = -4 ; i <= 0 ; i++){
        	y_ = y+i;
        	x_ = x+i;
        	if(valideCoordianateY(y_) && valideCoordianateX(x_) && valideCoordianateY(y_ + 4) && valideCoordianateX(x_ + 4)) {
                candidatesLines.add(new Line(gameGrid[x_][y_],gameGrid[x_+4][y_+4]));
        	}
        }

        for(int i = -4 ; i <= 0 ; i++){
        	y_ = y + i;
        	x_ = x - i;
        	if(valideCoordianateY(y_) && valideCoordianateX(x_) && valideCoordianateY(y_ + 4) && valideCoordianateX(x_ - 4)) {
                candidatesLines.add(new Line(gameGrid[x_][y_],gameGrid[x_- 4][ y_ + 4 ]));
        	}
        }
        x_ = 0;
        y_ = 0;
        Direction dir;
        boolean bool;
        x_ = x - 4;
        while(x_ <= x + 4 && candidatesLines.size() > 0){
	        y_ = y - 4;
	        while(y_ <= y + 4 && candidatesLines.size() > 0){
	        	bool = false;
	        	if(valideCoordianateY(y_) && valideCoordianateX(x_) && (x != x_|| y != y_)){ 
	       			if(x == x_) {dir = Direction.horizontal; bool = true;}
	       			else if(y == y_) {dir = Direction.vertical;bool = true;}
	       			else if(x-y == x_-y_) {dir = Direction.diagonal1;bool = true;}
	       			else if(x+y == x_+ y_) {dir = Direction.diagonal2;bool = true;}
	       			else { bool = false; dir = Direction.none;}
        			if(bool){
        				if((gameGrid[x_][y_].getShotNumber() >= 0 && isPointExtrimityOfListLines(gameGrid[x_][y_],dir)) || gameGrid[x_][y_].getShotNumber() == -1) {
        	       			
		                    Iterator<Line> it = candidatesLines.iterator();
		                    while(it.hasNext()) {
		                        Line l = it.next() ;
		                        if(l.getDir() == dir && pointInLine(gameGrid[x_][y_],l)){
		                            it.remove();
		                        }
		                    }
		                }
        			}
	        	}
	        	y_++;
	        }
	        x_++;
        }
        return candidatesLines;
    }

    private boolean valideCoordianateX(int x){
        return x >= 0 && x < GRID_HIGHT;
    }

    private boolean valideCoordianateY(int y){
        return y >= 0 && y < GRID_WIDTH;
    }

    boolean isPointExtrimityOfListLines(Point p,Direction d){
        for(Line l : this.listLines){
            if(l.getDir() == d){
                if(p.equals(l.getStartPoint()) || p.equals(l.getEndpoint())) return true;
            }
        }
        return false;
    }

    boolean pointInLine(Point p, Line l){
        Point vecteur1 = new Point( p.getX() - l.getStartPoint().getX(),  p.getY() -l.getStartPoint().getY() );

        Point vecteur2 = new Point( l.getEndpoint().getX() - l.getStartPoint().getX(),  l.getEndpoint().getY() -l.getStartPoint().getY() );

        float k1 = (float)vecteur1.getX()/vecteur2.getX();
        if(vecteur2.getY()*k1 == vecteur1.getY() && k1 >= 0 && k1 <= 1) return true;
        
        
        float k2 = (float)vecteur1.getY()/vecteur2.getY();
        if(vecteur2.getX()*k2 == vecteur1.getX() && k2 >= 0 && k2 <= 1) return true;
        
        return false;
    }
}
