package com.mygdx.game;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Board {
	private DrawableRectangle borders;
	private Field[][] fields;
	
	public Board(){
		borders = new DrawableRectangle(10, 10, 380, 380,Color.LIGHT_GRAY);
		fields = new Field[10][10];
	}
	
	public void draw(Batch p_batch){
		p_batch.draw(borders.getTexture(), borders.getX(),borders.getY());
	}
	/*
	 * Setters and getters
	 */
	public Texture getBordersTexture(){
		return borders.getTexture();
	}
}
