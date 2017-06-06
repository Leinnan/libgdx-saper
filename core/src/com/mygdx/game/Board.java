package com.mygdx.game;
import java.util.Random;

import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.Batch;

public class Board {
	final int FIELDS_X_Y = 10;
	final int BOMBS_AMOUNT = 10;
	private DrawableRectangle borders;
	private Field[][] fields;
	private Texture field_img;
	
	public Board(){
		borders = new DrawableRectangle(30, 30, 320, 320,Color.LIGHT_GRAY);
		fields = new Field[FIELDS_X_Y][FIELDS_X_Y];
		field_img = new Texture("bomb_0.png");

		for(int i = 0;i<fields.length;i++){
			for(int j = 0;j<fields[i].length;j++){
				fields[i][j] = new Field();
			}
		}
		generateBombs();
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
	private void generateBombs(){
		int m_generated_bombs = 0;
		Random m_generator = new Random();
		int m_x = 0;
		int m_y = 0;
		// First, it generates two random numbers from range [0,fields length]
		// then check if there is already bomb- 
		// if not then place there bomb and increase amount of m_generated_bombs
		// repeat until there is enough bombs
		while(m_generated_bombs < BOMBS_AMOUNT){
			m_x = m_generator.nextInt(fields.length);
			m_y = m_generator.nextInt(fields.length);
			System.out.println(m_x + " " + m_y);
			if(fields[m_x][m_y].isContainingBomb() == false){
				fields[m_x][m_y].setContainBomb();
				m_generated_bombs++;
			}
		}
	};
	public void dispose(){
		field_img.dispose();
	}
	/*
	 * Setters and getters
	 */
	public Texture getBordersTexture(){
		return borders.getTexture();
	}
}
