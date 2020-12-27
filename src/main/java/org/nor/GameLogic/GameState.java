package org.nor.GameLogic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class GameState {


    private final static int GRID_HIGHT = 20;
    private final static int GRID_WIDTH = 20;
    
    private GameVersion gameVersion;
    private GameMode gameMode;
    private int lineSize;
    private int score;
    
    private Point[][] gameGrid = new Point[GRID_HIGHT][GRID_WIDTH];
    private Set<Line> allListLines = new HashSet<>();

    
    


	public GameState(int lineSize,GameVersion gameVersion, GameMode gameMode){
    	this.gameMode = gameMode;
    	this.lineSize = lineSize;
    	this.gameVersion = gameVersion;
    	startingGrid();
    }
    
    public void startGame() {
    	gameMode.startPlay(this);
    }
    
    public void changeState(Point p, Line l) {
    	this.gameGrid[p.getX()][p.getY()].setShotNumber(p.getShotNumber());
    	this.allListLines.add(l);
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

        List<Line> candidatesLines= allCandidatesLinesOfPoint(p);
        x_ = 0;
        y_ = 0;
        Direction direction;
        boolean bool;
        x_ = x - (lineSize-1);
        while(x_ <= x + (lineSize-1) && candidatesLines.size() > 0){
        	if (Point.valideCoordianate(x_, GRID_HIGHT)) {
    	        y_ = y - ((lineSize-1));
    	        while(y_ <= y + (lineSize-1) && candidatesLines.size() > 0){
    	        	if (Point.valideCoordianate(y_, GRID_WIDTH)) {
        	        	bool = false;
        	        	if(x != x_|| y != y_){
        	       			if(x == x_) {direction = Direction.horizontal; bool = true;}
        	       			else if(y == y_) {direction = Direction.vertical;bool = true;}
        	       			else if(x-y == x_-y_) {direction = Direction.diagonal1;bool = true;}
        	       			else if(x+y == x_+ y_) {direction = Direction.diagonal2;bool = true;}
        	       			else { bool = false; direction = Direction.none;}
                			if(bool){
                				if(!gameVersion.canTUseThisPoint(gameGrid[x_][y_], direction, allListLines)) {
        		                    Iterator<Line> it = candidatesLines.iterator();
        		                    while(it.hasNext()) {
        		                        Line l = it.next() ;
        		                        
        		                        if(l.getDirection() == direction && l.isPointInLine(gameGrid[x_][y_])){
        		                            it.remove();
        		                        }
        		                    }
        		                }
                			}
        	        	}
					}
    	        	y_++;
    	        }
			}
	        x_++;
        }
        return candidatesLines;
    }


    
    public void startingGrid() {
    	
    	for (int i = 0; i < GRID_HIGHT ; i++) {
    		for ( int j = 0 ; j < GRID_WIDTH ; j++ ) {
    			this.gameGrid[i][j] = new Point(i, j, -1 ,GRID_HIGHT,GRID_WIDTH); 
    		}
    	}
    	
    	int x = (int) GRID_HIGHT/2;
    	int y = (int) GRID_WIDTH/2;
    	int a = lineSize-1;
    	
    	if (a == 4) {

        	gameGrid[x - a][y-1].setShotNumber(0);
        	gameGrid[x - a][y].setShotNumber(0);
        	gameGrid[x - a][y + 1].setShotNumber(0);
        	gameGrid[x - a][y + 2].setShotNumber(0);
        	

        	gameGrid[x - 1][y-1].setShotNumber(0);
        	gameGrid[x - 1][y-2].setShotNumber(0);
        	gameGrid[x - 1][y-3].setShotNumber(0); 
        	gameGrid[x - 1][y-4].setShotNumber(0);
        	
        	gameGrid[x - 1][y+2].setShotNumber(0);
        	gameGrid[x - 1][y+3].setShotNumber(0); 
        	gameGrid[x - 1][y+4].setShotNumber(0); 
        	gameGrid[x - 1][y+5].setShotNumber(0);
        	

        	gameGrid[x + 2][y-1].setShotNumber(0); 
        	gameGrid[x + 2][y-2].setShotNumber(0); 
        	gameGrid[x + 2][y-3].setShotNumber(0); 
        	gameGrid[x + 2][y-4].setShotNumber(0); 
        	
        	gameGrid[x + 2][y+2].setShotNumber(0); 
        	gameGrid[x + 2][y+3].setShotNumber(0); 
        	gameGrid[x + 2][y+4].setShotNumber(0); 
        	gameGrid[x + 2][y+5].setShotNumber(0); 

        	gameGrid[x + 5][y-1].setShotNumber(0); 
        	gameGrid[x + 5][y].setShotNumber(0);
        	gameGrid[x + 5][y + 1].setShotNumber(0);
        	gameGrid[x + 5][y + 2].setShotNumber(0);
        	

        	gameGrid[x][y - 4].setShotNumber(0); 
        	gameGrid[x + 1][y - 4].setShotNumber(0);
        	
        	gameGrid[x - 3][y - 1].setShotNumber(0);
        	gameGrid[x - 2][y - 1].setShotNumber(0);
        	gameGrid[x + 3][y - 1].setShotNumber(0);
        	gameGrid[x + 4][y - 1].setShotNumber(0);

        	gameGrid[x][y + 5].setShotNumber(0);
        	gameGrid[x + 1][y + 5].setShotNumber(0);
        	
        	gameGrid[x - 3][y + 2].setShotNumber(0);
        	gameGrid[x - 2][y + 2].setShotNumber(0);
        	gameGrid[x + 3][y + 2].setShotNumber(0);
        	gameGrid[x + 4][y + 2].setShotNumber(0);
        	
    	}
    	
    	if (a == 3) {

        	gameGrid[x - 3][y - 1].setShotNumber(0);
        	gameGrid[x - 3][y].setShotNumber(0);
        	gameGrid[x - 3][y + 1].setShotNumber(0); 
        	
        	gameGrid[x + 3][y - 1].setShotNumber(0); 
        	gameGrid[x + 3][y].setShotNumber(0); 
        	gameGrid[x + 3][y + 1].setShotNumber(0);

        	gameGrid[x - 1][y - 3].setShotNumber(0);
        	gameGrid[x - 1][y - 2].setShotNumber(0);
        	gameGrid[x - 1][y - 1].setShotNumber(0);
        	gameGrid[x - 1][y + 1].setShotNumber(0);
        	gameGrid[x - 1][y + 2].setShotNumber(0);
        	gameGrid[x - 1][y + 3].setShotNumber(0);
        	

        	gameGrid[x + 1][y - 3].setShotNumber(0); 
        	gameGrid[x + 1][y - 2].setShotNumber(0); 
        	gameGrid[x + 1][y - 1].setShotNumber(0); 
        	gameGrid[x + 1][y + 1].setShotNumber(0); 
        	gameGrid[x + 1][y + 2].setShotNumber(0); 
        	gameGrid[x + 1][y + 3].setShotNumber(0); 
        	
        	gameGrid[x - 2][y - 1].setShotNumber(0); 
        	gameGrid[x - 2][y + 1].setShotNumber(0); 
        	gameGrid[x][y + 3].setShotNumber(0); 
        	gameGrid[x][y - 3].setShotNumber(0); 
        	gameGrid[x + 2][y - 1].setShotNumber(0); 
        	gameGrid[x + 2][y + 1].setShotNumber(0); 
		}
    }

    private List<Line> allCandidatesLinesOfPoint(Point p){

        List<Line> candidatesLines= new ArrayList<>();
        int x_, y_;
        int x = p.getX(), y = p.getY();
        
        for(y_ = y-((lineSize-1)); y_<=y ; y_++){
        	if(Point.valideCoordianate(y_,GRID_WIDTH) && Point.valideCoordianate(y_ + (lineSize-1),GRID_WIDTH)) {
        		candidatesLines.add(new Line(gameGrid[x][y_],gameGrid[x][y_+(lineSize-1)]));
        	}
        }

        for(x_ = x-(lineSize-1) ; x_<=x ; x_++){
        	if(Point.valideCoordianate(x_,GRID_HIGHT) &&Point.valideCoordianate(x_+(lineSize-1),GRID_HIGHT) ) {
        		candidatesLines.add(new Line(gameGrid[x_][y],gameGrid[x_+(lineSize-1)][y]));
        	}
        }

        for(int i = -((lineSize-1)) ; i <= 0 ; i++){
        	y_ = y+i;
        	x_ = x+i;
        	if(Point.valideCoordianate(y_,GRID_WIDTH) && Point.valideCoordianate(x_,GRID_HIGHT)&& Point.valideCoordianate(y_+(lineSize-1),GRID_WIDTH) && Point.valideCoordianate(x_+(lineSize-1),GRID_HIGHT)) {
                candidatesLines.add(new Line(gameGrid[x_][y_],gameGrid[x_+(lineSize-1)][y_+(lineSize-1)]));
        	}
        }

        for(int i = -((lineSize-1)) ; i <= 0 ; i++){
        	y_ = y + i;
        	x_ = x - i;
        	if(Point.valideCoordianate(y_,GRID_WIDTH) && Point.valideCoordianate(x_,GRID_HIGHT) && Point.valideCoordianate(y_+(lineSize-1),GRID_WIDTH) && Point.valideCoordianate(x_-(lineSize-1),GRID_HIGHT)) {
                candidatesLines.add(new Line(gameGrid[x_][y_],gameGrid[x_- (lineSize-1)][ y_ + (lineSize-1) ]));
        	}
        }
        
        return candidatesLines;
    }
    
    
    
    public GameVersion getGameVersion() {
		return gameVersion;
	}
	public void setGameVersion(GameVersion gameVersion) {
		this.gameVersion = gameVersion;
	}
	public GameMode getGameMode() {
		return gameMode;
	}
	public void setGameMode(GameMode gameMode) {
		this.gameMode = gameMode;
	}
	public int getLineSize() {
		return lineSize;
	}
	public void setLineSize(int lineSize) {
		this.lineSize = lineSize;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public Point[][] getGameGrid() {
		return gameGrid;
	}
	public void setGameGrid(Point[][] gameGrid) {
		this.gameGrid = gameGrid;
	}
	public Set<Line> getAllListLines() {
		return allListLines;
	}
	public void setAllListLines(Set<Line> allListLines) {
		this.allListLines = allListLines;
	}
	public static int getGridHight() {
		return GRID_HIGHT;
	}
	public static int getGridWidth() {
		return GRID_WIDTH;
	}


}
