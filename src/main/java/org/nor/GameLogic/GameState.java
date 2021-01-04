package org.nor.GameLogic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * class that represents the model of the game
 */
public class GameState {


    private final static int GRID_HIGHT = 20;
    private final static int GRID_WIDTH = 20;
    
    private GameVersion gameVersion;
    private AI aI;
    private int lineSize;
    private int score;

    private Point[][] gameGrid = new Point[GRID_HIGHT][GRID_WIDTH];
    private List<Lines> allListLines = new ArrayList<>();


	/**
	 * class constructor
	 * @param lineSize number of points in a line
	 * @param gameVersion T or D
	 * @param gameMode player or AI
	 */
	public GameState(int lineSize,GameVersion gameVersion, AI gameMode){
		this.aI = gameMode;
		this.lineSize = lineSize;
		this.gameVersion = gameVersion;
		this.gameGrid = Grid.startingGrid(lineSize,GRID_HIGHT,GRID_WIDTH);
	}

	/**
	 * class constructor
	 * @param lineSize number of points in a line
	 * @param gameVersion T or D
	 */
	public GameState(int lineSize,GameVersion gameVersion){
		this.lineSize = lineSize;
		this.gameVersion = gameVersion;
		this.gameGrid = Grid.startingGrid(lineSize,GRID_HIGHT,GRID_WIDTH);
	}


	/**
	 * method that interfaces the Ai to the controller
	 */
    public void startGame() {
    	aI.startPlay(this);
    }

   /* private void printGrid(){
		for (int i = 0; i < GRID_HIGHT ; i++) {
			System.out.println();
			for ( int j = 0 ; j < GRID_WIDTH ; j++ ) {
				System.out.print('\t');
				System.out.print(gameGrid[i][j].getState());
			}
		}
	}
*/

	/**
	 * method for changing the state of the game (validate a move)
	 * @param p Point
	 * @param l Lines
	 */
    public void changeState(Point p, Lines l) {
    	this.gameGrid[p.getX()][p.getY()].setState(p.getState());
    	this.allListLines.add(l);
    }

	/**
	 * method that searches in the grid for Points that can be played and return them as a list of the data structure PointLines
	 * @return PointLines
	 */
	public List<PointLines> getValidePoints(){
    	List<PointLines> listPointLines = new ArrayList<>();
    	PointLines pl;
    	List<Lines> listLines;

        for(int i =0; i < GRID_HIGHT; i++){
            for(int j=0; j < GRID_WIDTH; j++){
                if(gameGrid[i][j].getState() == -1){
                	listLines = getValidLinesForAPoint(gameGrid[i][j]);
                	if(listLines.size() > 0){
                		pl = new PointLines(gameGrid[i][j],listLines);
                		listPointLines.add(pl);
                	}
                }
            }
        }
        return listPointLines;
    }


	/**
	 * method that returns a list of valid Lines to play for a particular Point
	 * @param p Point
	 * @return List<Lines>
	 */
    public List<Lines> getValidLinesForAPoint(Point p){
        int x = p.getX();
        int y = p.getY();
        int x_,y_;

        List<Lines> candidatesLines= getCandidateLinesForAPoint(p);
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
        	       			else { direction = Direction.none;bool = false;}
                			if(bool){
                				if(!gameVersion.canTUseThisPoint(gameGrid[x_][y_], direction, allListLines)) {
        		                    Iterator<Lines> it = candidatesLines.iterator();
        		                    while(it.hasNext()) {
        		                        Lines l = it.next() ;
        		                        
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


	/**
	 * method that return a list of all possible Lines that pass through a point
	 * @param p Point
	 * @return List<Lines>
	 */
	private List<Lines> getCandidateLinesForAPoint(Point p){
		List<Lines> candidateLines= new ArrayList<>();
		candidateLines.addAll(getCandidateLinesForAPointInOneDirection(p,Direction.horizontal));
		candidateLines.addAll(getCandidateLinesForAPointInOneDirection(p,Direction.vertical));
		candidateLines.addAll(getCandidateLinesForAPointInOneDirection(p,Direction.diagonal1));
		candidateLines.addAll(getCandidateLinesForAPointInOneDirection(p,Direction.diagonal2));
		return candidateLines;
	}

	/**
	 * method that return a list of all possible Lines that pass through a point in a particular direction
	 * @param p Point
	 * @param direction
	 * @return
	 */
	private List<Lines> getCandidateLinesForAPointInOneDirection(Point p,Direction direction){
		List<Lines> candidateLinesInOneDirection= new ArrayList<>();
		int x_, y_;
		int x = p.getX(), y = p.getY();
		for(int i = -((lineSize-1)) ; i <= 0 ; i++){
			int incrX = direction.getIncrementX();
			int incrY = direction.getIncrementY();
			y_ = y + incrY* i;
			x_ = x +incrX*i;
			if (Point.valideCoordianate(y_,GRID_WIDTH) && Point.valideCoordianate(x_,GRID_HIGHT) && Point.valideCoordianate(y_+incrY*(lineSize-1),GRID_WIDTH) && Point.valideCoordianate(x_+incrX*(lineSize-1),GRID_HIGHT))	{
			candidateLinesInOneDirection.add(new Lines(gameGrid[x_][y_],gameGrid[x_+incrX*(lineSize-1)][y_+incrY*(lineSize-1)]));
			}
		}

		return candidateLinesInOneDirection;
	}


    public GameVersion getGameVersion() {
		return gameVersion;
	}
	public void setGameVersion(GameVersion gameVersion) {
		this.gameVersion = gameVersion;
	}
	public AI getAI() {return aI;}
	public void setAI(AI aI) {
		this.aI = aI;
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
	public List<Lines> getAllListLines() {
		return allListLines;
	}
	public void setAllListLines(List<Lines> allListLines) {
		this.allListLines = allListLines;
	}
	public static int getGridHight() {
		return GRID_HIGHT;
	}
	public static int getGridWidth() {
		return GRID_WIDTH;
	}
}
