package com.github.elhamjamshidpey.africanBoardGame.strategy;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.github.elhamjamshidpey.africanBoardGame.Game;
import com.github.elhamjamshidpey.africanBoardGame.component.Board;
import com.github.elhamjamshidpey.africanBoardGame.component.Pit;
import com.github.elhamjamshidpey.africanBoardGame.component.Player;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TraditionalStrategyTest {
	
	@MockBean
	private Game gameMock;
	
	@Autowired
	private TraditionalStrategy strategy;
	
	@Test
	public void testGameIsNotFinish() {
		Mockito.when(gameMock.getBoard()).thenReturn(createBoardInStart());
		Assert.assertFalse(strategy.gameIsFinish());
	}

	@Test
	public void testGameIsFinish() {
		Mockito.when(gameMock.getBoard()).thenReturn(createBoardAfterFinish());
		Assert.assertTrue(strategy.gameIsFinish());
	}
	
	@Test
	public void testFindWinner() {
		Mockito.when(gameMock.getBoard()).thenReturn(createBoardAfterFinish());
		Player p1 = new Player("Elham");
		Player p2 = new Player("John");
		Mockito.when(gameMock.getFirstPlayer()).thenReturn(p1);
		Mockito.when(gameMock.getSecondPlayer()).thenReturn(p2);
		Assert.assertEquals(p1,strategy.findWinner());
	}
	
	private Board createBoardInStart() {
		List<Pit> pitsA = new ArrayList<Pit>();
		Pit pa1 = new Pit("a",6);
		Pit pa2 = new Pit("b",6);
		Pit pa3 = new Pit("c",6);
		Pit pa4 = new Pit("d",6);
		Pit pa5 = new Pit("e",6);
		Pit pa6 = new Pit("f",6);
		pitsA.add(pa1);
		pitsA.add(pa2);
		pitsA.add(pa3);
		pitsA.add(pa4);
		pitsA.add(pa5);
		pitsA.add(pa6);
		
		List<Pit> pitsB = new ArrayList<Pit>();
		Pit pb1 = new Pit("a",6);
		Pit pb2 = new Pit("b",6);
		Pit pb3 = new Pit("c",6);
		Pit pb4 = new Pit("d",6);
		Pit pb5 = new Pit("e",6);
		Pit pb6 = new Pit("f",6);
		pitsB.add(pb1);
		pitsB.add(pb2);
		pitsB.add(pb3);
		pitsB.add(pb4);
		pitsB.add(pb5);
		pitsB.add(pb6);
		
		Board board = new Board(pitsA,pitsB);
		return board;
	}
	
	private Board createBoardAfterFinish() {
		List<Pit> pitsA = new ArrayList<Pit>();
		Pit pa1 = new Pit("a",0);
		Pit pa2 = new Pit("b",0);
		Pit pa3 = new Pit("c",0);
		Pit pa4 = new Pit("d",0);
		Pit pa5 = new Pit("e",0);
		Pit pa6 = new Pit("f",0);
		pitsA.add(pa1);
		pitsA.add(pa2);
		pitsA.add(pa3);
		pitsA.add(pa4);
		pitsA.add(pa5);
		pitsA.add(pa6);
		
		List<Pit> pitsB = new ArrayList<Pit>();
		Pit pb1 = new Pit("a",2);
		Pit pb2 = new Pit("b",1);
		Pit pb3 = new Pit("c",0);
		Pit pb4 = new Pit("d",0);
		Pit pb5 = new Pit("e",1);
		Pit pb6 = new Pit("f",3);
		pitsB.add(pb1);
		pitsB.add(pb2);
		pitsB.add(pb3);
		pitsB.add(pb4);
		pitsB.add(pb5);
		pitsB.add(pb6);
		
		Board board = new Board(pitsA,pitsB);
		board.setFirstPlayerLargerPit(new Pit("L",42));
		board.setSecondPlayerLargerPit(new Pit("L",23));
		return board;
	}
}
