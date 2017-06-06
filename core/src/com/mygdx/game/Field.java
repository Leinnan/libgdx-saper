package com.mygdx.game;

public class Field {
	private boolean clicked;
	private boolean contain_bomb;
	private int bomb_in_neighborhood;
	private int index_x;
	private int index_y;
	
	public Field(int p_index_x,int p_index_y){
		contain_bomb = false;
		bomb_in_neighborhood = 0;
		index_x = p_index_x;
		index_y = p_index_y;
	}
}
