package com.mygdx.game;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Board {
	private Color borders_color;
	private DrawableRectangle borders;
	public Board(){
		borders_color = new Color(Color.LIGHT_GRAY);
		borders = new DrawableRectangle(0, 0, 500, 300,borders_color);
		
	}
	// nie działa
	public void draw(SpriteBatch p_batch){
		borders.draw(p_batch, 1);
	}
	public Texture getBordersTexture(){
		return borders.getTexture();
	}
	public Sprite getBordersSprite() {
		return borders.getSprite();
	}
}
