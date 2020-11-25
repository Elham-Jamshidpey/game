package com.github.elhamjamshidpey.africanBoardGame.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.elhamjamshidpey.africanBoardGame.Game;
import com.github.elhamjamshidpey.africanBoardGame.component.Board;

@Component
public class SimpleBoardView implements BoardView {

	@Autowired
	private Game game;
	
	@Override
	public void showBoard() {
		Board board = game.getBoard();
		System.out.print("  ");
		board.getFirstPlayerAPits().stream().forEach(System.out::print);
		System.out.println("");
		System.out.println(board.getFirstPlayerLargerPit().toString() + "a b c d e f "
				+ board.getSecondPlayerLargerPit().toString());
		System.out.print("  ");
		board.getSecondPlayerBPits().stream().forEach(System.out::print);
		System.out.println("");
	}

}
