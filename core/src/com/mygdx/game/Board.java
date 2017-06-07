package com.mygdx.game;
import java.util.Random;

import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.Batch;

public class Board {
	final int FIELDS_X_Y = 10;
    final int FIELD_LENGTH = 30;
	final int BOMBS_AMOUNT = 10;
    final int BOMB_IMG = 10;
    final int HIDE_IMG = 0;
    final int NONE_IMG = 9;
    final int HOVER_IMG = 11;
    final int MARKED_IMG = 12;
    final boolean DEBUG_MODE = false;
    final int BORDERS_MARGIN = 30;
    final int BORDERS_PADDING = 10;
	private DrawableRectangle borders;
	private Field[][] fields;
	private Texture[] fields_img;
    
    private int marked_fields;
	
	public Board(){
		borders = new DrawableRectangle(BORDERS_MARGIN, BORDERS_MARGIN, 320, 320,Color.LIGHT_GRAY);
		fields = new Field[FIELDS_X_Y][FIELDS_X_Y];
		marked_fields = 0;

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
		fields_img = new Texture[13];
		for(int i = 0;i<fields_img.length;i++){
			fields_img[i] = new Texture("bomb_" + i + ".png");
		}
	}
	
	public void draw(Batch p_batch){
		p_batch.draw(borders.getTexture(), borders.getX(),borders.getY());
		drawFields(p_batch);
		
	}
	private void drawFields(Batch p_batch){
		int m_x_pos = (int)borders.getX()+BORDERS_PADDING;
		int m_y_pos = (int)borders.getY()+BORDERS_PADDING;
		
		for(int i = 0;i<fields.length;i++){
			for(int j = 0;j<fields[i].length;j++){
				p_batch.draw(fields_img[fields[i][j].getImgIndex()],m_x_pos,m_y_pos);
                // after we draw it if it was hovered we want to cancel that
                if(fields[i][j].getImgIndex() == HOVER_IMG){
                    updateFieldImg(i,j);
                }
				m_y_pos +=FIELD_LENGTH;
			}
			
			m_x_pos += FIELD_LENGTH;
			m_y_pos = (int)borders.getX()+BORDERS_PADDING;
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
    
    public void handleInput(int p_mouse_x,int p_mouse_y,boolean p_left_button,boolean p_right_button){
        //first lets calculate above which field mouse is
        int m_index_x = p_mouse_x - BORDERS_PADDING - BORDERS_MARGIN;
        int m_index_y = p_mouse_y - BORDERS_PADDING - BORDERS_MARGIN;
        m_index_x /= 30;
        m_index_y /= 30;
        // if number is out of range prevent crash
        if(m_index_x >= FIELDS_X_Y || m_index_y >= FIELDS_X_Y ||
           m_index_x < 0 || m_index_y < 0 ){
            return;
        }
        if(DEBUG_MODE == true){
            System.out.println(m_index_x + "," + m_index_y);
        }
        
        if(p_left_button == true){
            if(fields[m_index_x][m_index_y].isMarked() == false){
                fields[m_index_x][m_index_y].setClicked();
                
                if(fields[m_index_x][m_index_y].getBombsInNeighborhood() == 0 &&
                   fields[m_index_x][m_index_y].isContainingBomb() == false){
                    revealEmptyNeighbors(m_index_x,m_index_y);
                }
                
                updateFieldImg(m_index_x,m_index_y);
            }
        }
        else if(p_right_button == true){
            if(fields[m_index_x][m_index_y].isClicked() == false){
                fields[m_index_x][m_index_y].switchMarked();
                fields[m_index_x][m_index_y].setImgIndex(MARKED_IMG);
                if(fields[m_index_x][m_index_y].isMarked()){
                    marked_fields++;
                }
                else{
                    marked_fields--;
                }
            }
        }
        else if(fields[m_index_x][m_index_y].isClicked() == false &&
                fields[m_index_x][m_index_y].isMarked() == false){
            fields[m_index_x][m_index_y].setImgIndex(HOVER_IMG);
        }
    }
    
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
    
    public void revealEmptyNeighbors(int p_x, int p_y){
        
        
		for(int i = p_x-1;i<p_x+2;i++){
			for(int j = p_y-1;j<p_y+2;j++){
                if(i < 0 || i >= FIELDS_X_Y || j < 0 || j >= FIELDS_X_Y){
                    continue;
                }
                else if(DEBUG_MODE == true){
                    System.out.println("SPRAWDZAM [" + i + "," + j + "]");
                }
                
                if(fields[i][j].isContainingBomb() == false && fields[i][j].isClicked() == false){
                    fields[i][j].setClicked();
                    
                    updateFieldImg(i,j);
                    if(fields[i][j].getBombsInNeighborhood() == 0){
                        revealEmptyNeighbors(i,j);
                    }
                }
			}
		}
    
    };
    
    public void updateFieldsImg(){
		for(int i = 0;i<fields.length;i++){
			for(int j = 0;j<fields[i].length;j++){
                updateFieldImg(i,j);
			}
		}
    };
    private void updateFieldImg(int p_x, int p_y){
        // first check if it is clicked and visible
        // then if there is a bomb
        // if not then lets set index with value of amount 
        // bombs in neighborhood
        if(fields[p_x][p_y].isClicked() == false){
            fields[p_x][p_y].setImgIndex(NONE_IMG);
        }
        else if(fields[p_x][p_y].isMarked() == true){
            fields[p_x][p_y].setImgIndex(MARKED_IMG);
        }
        else if(fields[p_x][p_y].isContainingBomb() == true){
            fields[p_x][p_y].setImgIndex(BOMB_IMG);
        }
        else{
            fields[p_x][p_y].setImgIndex(fields[p_x][p_y].getBombsInNeighborhood());
        }
    }
    
    public boolean isAnyBombExposed(){
		for(int i = 0;i<fields.length;i++){
			for(int j = 0;j<fields[i].length;j++){
                if(fields[i][j].isContainingBomb() == true)
                    if(fields[i][j].isClicked() == true)
                        return true;
			}
		}
        return false;
        
    }
        
	public void dispose(){
        for(int i = 0;i<fields_img.length;i++){
			fields_img[i].dispose();
		}
	}
	/*
	 * Setters and getters
	 */
	public Texture getBordersTexture(){
		return borders.getTexture();
	}
    public int getMarkedFields(){
        return this.marked_fields;
    }
}
