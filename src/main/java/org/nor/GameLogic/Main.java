package org.nor.GameLogic;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Main {

	
    public static void main(String[] args) {
    	
    	GameState gs = new GameState(5,new D(), new RandomAI());
    	gs.startGame();
    	/*
    	Set<Line> sl = gs.getPossibleLinesOfShots(new Point(5, 5));
    	System.out.println();
    	for(Line l : sl){
    		System.out.println(l);
    	}
    	
	/*
    	Line l = new Line(new Point(0,0),new Point(2, 2));
    	
    	System.out.println(l.isPointTotallyInsideLine(new Point(0, 0)));
    	*/
    	//System.out.println(gs.pointInLine(new Point(0,0), new Line(new Point(0, 0), new Point(2, 2))));
    }
}
