package org.nor;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


import org.nor.GameLogic.*;

public class GameStateTest {

	@Test
	public void changeStateTest() {
		GameState gs = new GameState(5,new D());
		Point p = new Point(0,0);
		Line l =  new Line(new Point(1,1),new Point(2,2));
		gs.changeState(p,l);
		assertTrue(gs.getGameGrid()[0][0].getState() == p.getState());
		assertTrue(gs.getAllListLines().contains(l));
	}
	
	@Test
	public void getValidePointsTest() {
		GameState gs = new GameState(5,new D());
		List<PointLines> lpl= gs.getValidePoints();
		Point p1 = new Point(gs.getGridHight()/2-2,gs.getGridHight()/2-1);
		Point p2 = new Point(gs.getGridHight()/2-2,gs.getGridHight()/2-5);
		
		Line l = new Line(p1,p2);
		List<Line> ll = new ArrayList<>();
		ll.add(l);
		PointLines pl = new PointLines(p1,ll);
		
		assertTrue(lpl.contains(pl));
	}
	

}
