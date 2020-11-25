package com.github.elhamjamshidpey.africanBoardGame.strategy;

import com.github.elhamjamshidpey.africanBoardGame.component.Player;
import com.github.elhamjamshidpey.africanBoardGame.exception.InvalidMoveException;

public interface GameStrategy {

	void play(Integer srcIndex) throws InvalidMoveException;
    boolean movingValidation(Integer srcIndex) throws InvalidMoveException;
    void switchPlayer();
	boolean gameIsFinish();
	Player findWinner();
}
