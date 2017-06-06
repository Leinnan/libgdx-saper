package com.mygdx.game;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Board {
	private DrawableRectangle borders;
	
	public Board(){
		borders = new DrawableRectangle(10, 10, 380, 380,Color.LIGHT_GRAY);
		
	}
	/*
	 * Setters and getters
	 */
	public Texture getBordersTexture(){
		return borders.getTexture();
	}
	public Sprite getBordersSprite() {
		return borders.getSprite();
	}
	public float getBordersX(){
		return borders.getX();
	}
	public float getBordersY(){
		return borders.getY();
	}
}
