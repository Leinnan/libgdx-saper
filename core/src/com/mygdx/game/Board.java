package com.mygdx.game;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Board {
	final int FIELDS_X_Y = 10;
	private DrawableRectangle borders;
	private Field[][] fields;
	private Texture field_img;
	
	public Board(){
		borders = new DrawableRectangle(30, 30, 320, 320,Color.LIGHT_GRAY);
		fields = new Field[FIELDS_X_Y][FIELDS_X_Y];
		field_img = new Texture("bomb_0.png");
	}
	
	public void draw(Batch p_batch){
		p_batch.draw(borders.getTexture(), borders.getX(),borders.getY());
		drawFields(p_batch);
		
	}
	private void drawFields(Batch p_batch){
		int m_x_pos = (int)borders.getX()+10;
		int m_y_pos = (int)borders.getY()+10;
		
		for(int i = 0;i<fields.length;i++){
			for(int j = 0;j<fields[i].length;j++){
				p_batch.draw(field_img,m_x_pos,m_y_pos);
				m_x_pos +=30;
			}
			
			m_y_pos += 30;
			m_x_pos = (int)borders.getX()+10;
		}
		
	}
	/*
	 * Setters and getters
	 */
	public Texture getBordersTexture(){
		return borders.getTexture();
	}
}
