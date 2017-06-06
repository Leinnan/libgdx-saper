package com.mygdx.game;
import java.util.Random;

import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.Batch;

public class Board {
	final int FIELDS_X_Y = 10;
	final int BOMBS_AMOUNT = 10;
    final int BOMB_IMG = 10;
    final int HIDE_IMG = 0;
    final int NONE_IMG = 9;
    final int HOVER_IMG = 11;
    final boolean DEBUG_MODE = true;
	private DrawableRectangle borders;
	private Field[][] fields;
	private Texture field_img;
	private Texture[] fields_img;
	
	public Board(){
		borders = new DrawableRectangle(30, 30, 320, 320,Color.LIGHT_GRAY);
		fields = new Field[FIELDS_X_Y][FIELDS_X_Y];
		field_img = new Texture("bomb_0.png");
		

		for(int i = 0;i<fields.length;i++){
			for(int j = 0;j<fields[i].length;j++){
				fields[i][j] = new Field();
			}
		}
		loadFieldsImg();
		
		generateBombs();
		for(int i = 0;i<fields.length;i++){
			for(int j = 0;j<fields[i].length;j++){
				calculateNeighbors(i,j);
			}
		}
        updateFieldsImg();
	}

	private void loadFieldsImg() {
		fields_img = new Texture[12];
		for(int i = 0;i<fields_img.length;i++){
			fields_img[i] = new Texture("bomb_" + i + ".png");
		}
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
				p_batch.draw(fields_img[fields[i][j].getImgIndex()],m_x_pos,m_y_pos);
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
			if(fields[m_x][m_y].isContainingBomb() == false){
				fields[m_x][m_y].setContainBomb();
                fields[m_x][m_y].setImgIndex(BOMB_IMG);
				m_generated_bombs++;
			}
		}
	};
    
    private void calculateNeighbors(int p_x, int p_y){
        // if it is bomb there is no reason for calculate this
        if(fields[p_x][p_y].isContainingBomb() == true){
            return;
        }
        int m_bombs_in_neighborhood = 0;
        
        if(DEBUG_MODE == true){
            String m_calculation_info;
            m_calculation_info = "Index ["  + p_x + "," + p_y + "]\n";
            m_calculation_info += "Przeszukiwane indexy:\n";
            m_calculation_info += "\tpoczątek: [" + (p_x-1) + "," + (p_y-1) + "]\n";
            m_calculation_info += "\tkoniec: [" + (p_x+1) + "," + (p_y+1) + "]\n";
            System.out.println(m_calculation_info);
        }   
        
		for(int i = p_x-1;i<p_x+2;i++){
			for(int j = p_y-1;j<p_y+2;j++){
                if(i < 0 || i >= FIELDS_X_Y || j < 0 || j >= FIELDS_X_Y){
                    continue;
                }
                else if(DEBUG_MODE == true){
                    System.out.println("SPRAWDZAM [" + i + "," + j + "]");
                }
                
                if(fields[i][j].isContainingBomb() == true){
                    m_bombs_in_neighborhood++;
                }
			}
		}
        fields[p_x][p_y].setBombInNeighborhood(m_bombs_in_neighborhood);
        
        if(DEBUG_MODE == true){
            fields[p_x][p_y].setImgIndex(m_bombs_in_neighborhood);
        }
    };
    
    public void updateFieldsImg(){
		for(int i = 0;i<fields.length;i++){
			for(int j = 0;j<fields[i].length;j++){
				// first check if it is clicked and visible
                // then if there is a bomb
                // if not then lets set index with value of amount 
                // bombs in neighborhood
                if(fields[i][j].isClicked() == false){
                    fields[i][j].setImgIndex(NONE_IMG);
                }
                else if(fields[i][j].isContainingBomb() == true){
                    fields[i][j].setImgIndex(BOMB_IMG);
                }
                else{
                    fields[i][j].setImgIndex(fields[i][j].getBombsInNeighborhood());
                }
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
