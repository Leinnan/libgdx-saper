package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Texture;


public class Game extends ApplicationAdapter {
	private SpriteBatch batch;
	private Board board;
    private BitmapFont font;
    private Texture game_lost_tex;
    private Color font_color;
	private String text;
    private int win_h;
    private int win_w;
    final private float TIME_BETWEEN_CLICKS = 0.2f;
    private float time_since_last_click = 0.0f;
    private boolean is_game_lost = false;
    
	@Override
	public void create () {
		batch = new SpriteBatch();
		board = new Board();
        font = new BitmapFont();
        font_color = new Color(0.8359375f,0.8359375f,0.8359375f,1);
        game_lost_tex = new Texture("game_lost.png");
        
        win_h = Gdx.graphics.getHeight();
        win_w =  Gdx.graphics.getWidth();
        font.setColor(font_color);
        font.getData().setScale(1.5f);
        text = "There was no mouse click yet!";
	}

    public void update(float p_delta){
        int m_mouse_x = Gdx.input.getX();
        int m_mouse_y = win_h - Gdx.input.getY();
        time_since_last_click += p_delta;
        
        if(is_game_lost == false){
            text = "Marked bombs: ";
            text += board.getMarkedFields();
            text += "/10";
            
            
            if(Gdx.input.isButtonPressed(Buttons.LEFT) == true || 
               Gdx.input.isButtonPressed(Buttons.RIGHT) == true){
                if(time_since_last_click >= TIME_BETWEEN_CLICKS){
                    if(Gdx.input.isButtonPressed(Buttons.LEFT) == true)
                        board.handleInput(m_mouse_x,m_mouse_y,true,false);
                    else
                        board.handleInput(m_mouse_x,m_mouse_y,false,true);
                    time_since_last_click = 0.f;
                    if(board.isAnyBombExposed() == true){
                        text = "GAME LOST!";
                        is_game_lost = true;
                        // make player wait longer for starting new game
                        time_since_last_click = -0.5f;
                    }
                }
            }
            else{
                board.handleInput(m_mouse_x,m_mouse_y,false,false);
            }
        }
        else{
            if(time_since_last_click >= TIME_BETWEEN_CLICKS){
                if(Gdx.input.isButtonPressed(Buttons.LEFT) == true){
                    board = new Board();
                    is_game_lost = false;
                    time_since_last_click = -0.2f;
                }
            }
        }   
    }

	@Override
	public void render () {
        update(Gdx.graphics.getDeltaTime());
		Gdx.gl.glClearColor(0.1171875f, 0.16796875f, 0.2109375f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		board.draw(batch);
        if(is_game_lost == false){
        font.draw(batch, text, 20, Gdx.graphics.getHeight()-20);
        }
        else{
            batch.draw(game_lost_tex,0,Gdx.graphics.getHeight()-200);
        }
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		board.dispose();
        game_lost_tex.dispose();
		
	}
}
