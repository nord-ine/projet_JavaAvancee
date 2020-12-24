package org.nor.GameLogic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GameState {


    private final static int GRID_HIGHT = 20;
    private final static int GRID_WIDTH = 20;
    int LineSize=5;
    boolean touch = true;
    Point[][] gameGrid = new Point[GRID_HIGHT][GRID_WIDTH];
    private List<Line> listLines = new ArrayList<>();



     private int score;

    public GameState(){

    }


    public List<Point> getValidePoints(){

        for(int i =0; i < GRID_HIGHT; i++){
            for(int j=0; j < GRID_WIDTH; j++){
                if(gameGrid[i][j].getShotNhmber() == -1){

                }
            }
        }

    }


    public List<Line> getvalideShots(Point p){
        int x = p.getX();
        int y = p.getY();
        int x_,y_;

        List<Line> candidatesHorizontal= new ArrayList<>();

        for(int i=y-4;i<=y;i++){
            candidatesHorizontal.add(new Line(gameGrid[x][i],gameGrid[x][i+5]));
        }
        for(y_= y+1 ; y_ <= y + LineSize-1; y_++){
            if(valideCoordianateY(y_) && gameGrid[x][y_].getShotNhmber() >= 0){
                if(isPointExtrimityOfListLines(gameGrid[x][y_],Direction.horizontal)){
                    Iterator<Line> it = candidatesHorizontal.iterator() ;
                    while(it.hasNext()) {
                        Line l = it.next() ;
                        if(pointInLine(gameGrid[x][y_],l)){
                            it.remove();
                        }
                    }
                    break;
                }
            }
        }
        for(y_= y-1 ; y_ <= y - LineSize-1; y_--){
            if(valideCoordianateY(y_) && gameGrid[x][y_].getShotNhmber() >= 0){
                if(isPointExtrimityOfListLines(gameGrid[x][y_],Direction.horizontal)){

                    break;
                }
            }
        }

    }

    private boolean valideCoordianateX(int x){
        return x > 0 && x < GRID_HIGHT;
    }

    private boolean valideCoordianateY(int y){
        return y > 0 && y < GRID_WIDTH;
    }

    boolean isPointExtrimityOfListLines(Point p,Direction d){
        for(Line l : this.listLines){
            if(l.dir==d){
                if(p.equals(l.getStartPoint()) || p.equals(l.getEndpoint())) return true;
            }
        }
        return false;
    }

    boolean pointInLine(Point p, Line l){
        Point vecteur1 = new Point( p.getX() - l.getStartPoint().getX(),  p.getY() -l.getStartPoint().getY() );

        Point vecteur2 = new Point( l.getEndpoint().getX() - l.getStartPoint().getX(),  l.getEndpoint().getY() -l.getStartPoint().getY() );

        float k1 = (float)vecteur1.getX()/vecteur2.getX();
        float k2 = (float)vecteur1.getY()/vecteur2.getY();

        if(k1 == k2 && k1 < 1 && k1>0) return true;
        return false;
    }





}
