package org.nor.GameLogic;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Main {

	
    public static void main(String[] args) {
    	GameState gs = new GameState();
    	gs.gameGrid[2][8].shotNumber = 3;
    	gs.gameGrid[6][4].shotNumber = 0;
    	gs.gameGrid[4][6].shotNumber = 0;
    	gs.gameGrid[3][7].shotNumber = 0;
    	
    	gs.gameGrid[5][4].shotNumber = 0;
    	gs.gameGrid[5][1].shotNumber = 0;
    	gs.gameGrid[5][2].shotNumber = 0;
    	gs.gameGrid[5][3].shotNumber = 0;
    	
    	gs.gameGrid[2][4].shotNumber = 0;
    	gs.gameGrid[2][1].shotNumber = 0;
    	gs.gameGrid[2][2].shotNumber = 0;
    	gs.gameGrid[2][3].shotNumber = 0;
    	
    	gs.listLines.add(new Line(new Point(5, 1), new Point(5, 4)));
    	List<PointLines>  listPointLines = gs.getValidePoints();
    	System.out.println(listPointLines.size());
    	for(PointLines pointLine : listPointLines){
    		System.out.println(pointLine);
    	}
	
    	
    	//System.out.println(gs.pointInLine(new Point(0,0), new Line(new Point(0, 0), new Point(2, 2))));
    }
}
