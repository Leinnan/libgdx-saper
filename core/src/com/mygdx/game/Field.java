package com.mygdx.game;

public class Field {
	private boolean clicked;
	private boolean contain_bomb = false;
	private int bombs_in_neighborhood;
	private int img_index;
	
	public Field(){
		bombs_in_neighborhood = 0;
	}
	/*
	 * Setters and getters
	 */

	public boolean isClicked() {
		return clicked;
	}
	public void setClicked() {
		this.clicked = true;
	}
	public void setClicked(boolean clicked) {
		this.clicked = clicked;
	}

	public boolean isContainingBomb() {
		return contain_bomb;
	}

	public void setContainBomb() {
		this.contain_bomb = true;
	}

	public void setContainBomb(boolean contain_bomb) {
		this.contain_bomb = contain_bomb;
	}

	public int getBombInNeighborhood() {
		return bombs_in_neighborhood;
	}

	public void setBombInNeighborhood(int bomb_in_neighborhood) {
		this.bombs_in_neighborhood = bomb_in_neighborhood;
	}

	
}
