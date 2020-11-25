package com.github.elhamjamshidpey.africanBoardGame.component;

public class Pit {

	private String id;

	private Integer stoneNumber;

	public Pit(String id, Integer stoneNumber) {
		super();
		this.id = id;
		this.stoneNumber = stoneNumber;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getStoneNumber() {
		return stoneNumber;
	}

	public void setStoneNumber(Integer stoneNumber) {
		this.stoneNumber = stoneNumber;
	}

	public void addOneStone() {
		this.stoneNumber += 1;
	}
	
	public void addSomeStones(Integer stones) {
		this.stoneNumber+= stones;
	}

	@Override
	public String toString() {
		return stoneNumber + " ";
	}

}
