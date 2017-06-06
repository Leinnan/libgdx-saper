package com.mygdx.game;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.Batch;

public class Board {
	private Color borders_color = new Color(50,200,11,1);
	private DrawableRectangle borders = new DrawableRectangle(0, 0, 500, 300,borders_color);
	
	// nie działa
	public void draw(SpriteBatch p_batch){
		borders.draw(p_batch, 1);
	}
	public Texture getBordersTexture() {
		return borders.getTexture();
	}
}
