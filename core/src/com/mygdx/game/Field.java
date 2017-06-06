package com.mygdx.game;

public class Field {
	private boolean clicked;
	private boolean marked;
	private boolean contain_bomb;
	private int bombs_in_neighborhood;
	private int img_index;
	
	public Field(){
		bombs_in_neighborhood = 0;
		img_index = 9;
		clicked = false;
		contain_bomb = false;
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
	public void setClicked(boolean m_clicked) {
		this.clicked = m_clicked;
	}
	public boolean isMarked() {
		return marked;
	}
	public void setMarked() {
		this.marked = true;
	}
	public void setMarked(boolean m_marked) {
		this.marked = m_marked;
	}
    public void switchMarked(){
        this.marked = !this.marked;
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

	public int getBombsInNeighborhood() {
		return bombs_in_neighborhood;
	}

	public void setBombInNeighborhood(int bomb_in_neighborhood) {
		this.bombs_in_neighborhood = bomb_in_neighborhood;
	}

	public int getImgIndex() {
		return img_index;
	}

	public void setImgIndex(int p_img_index) {
		this.img_index = p_img_index;
	}

	
}
