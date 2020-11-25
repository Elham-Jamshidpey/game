package com.github.elhamjamshidpey.africanBoardGame.component;


import java.util.List;

public class Board {

	private List<Pit> firstPlayerAPits;

	private Pit firstPlayerLargerPit;

	private List<Pit> secondPlayerBPits;

	private Pit secondPlayerLargerPit;

	public Board(List<Pit> playerAPits, List<Pit> playerBPits) {
		this.firstPlayerAPits = playerAPits;
		this.firstPlayerLargerPit = new Pit("L", 0);
		this.secondPlayerBPits = playerBPits;
		this.secondPlayerLargerPit = new Pit("L", 0);
	}

	public List<Pit> getFirstPlayerAPits() {
		return firstPlayerAPits;
	}

	public void setFirstPlayerAPits(List<Pit> firstPlayerAPits) {
		this.firstPlayerAPits = firstPlayerAPits;
	}

	public Pit getFirstPlayerLargerPit() {
		return firstPlayerLargerPit;
	}

	public void setFirstPlayerLargerPit(Pit firstPlayerLargerPit) {
		this.firstPlayerLargerPit = firstPlayerLargerPit;
	}

	public List<Pit> getSecondPlayerBPits() {
		return secondPlayerBPits;
	}

	public void setSecondPlayerBPits(List<Pit> secondPlayerBPits) {
		this.secondPlayerBPits = secondPlayerBPits;
	}

	public Pit getSecondPlayerLargerPit() {
		return secondPlayerLargerPit;
	}

	public void setSecondPlayerLargerPit(Pit secondPlayerLargerPit) {
		this.secondPlayerLargerPit = secondPlayerLargerPit;
	}

}
