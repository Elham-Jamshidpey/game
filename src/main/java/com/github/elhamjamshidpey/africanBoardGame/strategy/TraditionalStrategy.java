package com.github.elhamjamshidpey.africanBoardGame.strategy;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.elhamjamshidpey.africanBoardGame.Game;
import com.github.elhamjamshidpey.africanBoardGame.component.Board;
import com.github.elhamjamshidpey.africanBoardGame.component.Pit;
import com.github.elhamjamshidpey.africanBoardGame.component.Player;
import com.github.elhamjamshidpey.africanBoardGame.exception.InvalidMoveException;


@Component
public class TraditionalStrategy implements GameStrategy {

	@Autowired
	private Game game;

	private Boolean playerHasAnotherTurn = false;
	
	public void play(Integer srcIndex) throws InvalidMoveException {
		--srcIndex;
		Board board = game.getBoard();
		movingValidation(srcIndex);

		//not completed! just implement general logic 
		
		List<Pit> srcPits = game.getCurrentPlayer() == game.getFirstPlayer() ? board.getFirstPlayerAPits()
				: board.getSecondPlayerBPits();
		Pit srcLargePit = game.getCurrentPlayer() == game.getFirstPlayer() ? board.getFirstPlayerLargerPit()
				: board.getSecondPlayerLargerPit();
		List<Pit> otherPits = game.getCurrentPlayer() == game.getFirstPlayer() ? board.getSecondPlayerBPits()
				: board.getFirstPlayerAPits();
		Pit otherLargePit = game.getCurrentPlayer() == game.getFirstPlayer() ? board.getSecondPlayerLargerPit()
				: board.getFirstPlayerLargerPit();
		int desIndex = srcIndex + 1;
		Pit srcPit = srcPits.get(srcIndex);
		int stones = srcPit.getStoneNumber();
		while (desIndex < srcPits.size()) {
			Pit p = srcPits.get(desIndex);
			p.addOneStone();
			--stones;
			srcPit.setStoneNumber(stones);
			if (stones <= 0) {
				return;
			}
			++desIndex;
		}

		if (stones <= 0) {
			return;
		}

		srcLargePit.addOneStone();
		--stones;
		srcPit.setStoneNumber(stones);

		if (stones <= 0) {
			playerHasAnotherTurn =true;
			return;
		}

		desIndex = 0;
		while (desIndex < otherPits.size()) {
			Pit p = otherPits.get(desIndex);
			p.addOneStone();
			--stones;
			srcPit.setStoneNumber(stones);
			++desIndex;
			if (stones <= 0) {
				return;
			}
		}

	}

	public boolean movingValidation(Integer srcIndex) throws InvalidMoveException {
		/*
		 * TODO game rules: moving just to right no stone land in opponent's big pit
		 */
		return true;
	}

	public void switchPlayer() {
		if (!playerHasAnotherTurn) {
			if (game.getCurrentPlayer().equals(game.getFirstPlayer()))
				game.setCurrentPlayer(game.getSecondPlayer());
			else
				game.setCurrentPlayer(game.getFirstPlayer());
		}else {
			playerHasAnotherTurn = false;
		}
	}

	public boolean gameIsFinish() {
		Board board = game.getBoard();
		int playerApits = board.getFirstPlayerAPits().stream().map(Pit::getStoneNumber).mapToInt(Integer::intValue)
				.sum();
		int playerBpits = board.getSecondPlayerBPits().stream().map(Pit::getStoneNumber).mapToInt(Integer::intValue)
				.sum();

		if (playerApits > 0 && playerBpits > 0)
			return false;

		board.getFirstPlayerLargerPit().addSomeStones(playerApits);
		board.getFirstPlayerAPits().clear();
		board.getSecondPlayerLargerPit().addSomeStones(playerBpits);
		board.getSecondPlayerBPits().clear();

		return true;
	}

	public Player findWinner() {
		if (game.getBoard().getFirstPlayerLargerPit().getStoneNumber() > game.getBoard().getSecondPlayerLargerPit()
				.getStoneNumber())
			return game.getFirstPlayer();
		return game.getSecondPlayer();

	}

}
