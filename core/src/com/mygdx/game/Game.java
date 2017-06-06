package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Game extends ApplicationAdapter {
	private SpriteBatch batch;
	private Board board;
    private BitmapFont font;
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
        
        win_h = Gdx.graphics.getHeight();
        win_w =  Gdx.graphics.getWidth();
        font.setColor(Color.RED);
        text = "There was no mouse click yet!";
	}

    public void update(float p_delta){
        int m_mouse_x = Gdx.input.getX();
        int m_mouse_y = win_h - Gdx.input.getY();
        
        if(is_game_lost == false){
            text = "Mouse pos: ";
            text += m_mouse_x;
            text += ",";
            text += m_mouse_y;
        }   
        time_since_last_click += p_delta;
        
        if(Gdx.input.isButtonPressed(Buttons.LEFT) == true || Gdx.input.isButtonPressed(Buttons.RIGHT) == true){
            if(time_since_last_click >= TIME_BETWEEN_CLICKS){
                font.setColor(Color.GREEN);
                if(Gdx.input.isButtonPressed(Buttons.LEFT) == true)
                    board.handleInput(m_mouse_x,m_mouse_y,true,false);
                else
                    board.handleInput(m_mouse_x,m_mouse_y,false,true);
                time_since_last_click = 0.f;
                if(board.isAnyBombExposed() == true){
                    text = "GAME LOST!";
                    is_game_lost = true;
                }
            }
        }
        else{
            board.handleInput(m_mouse_x,m_mouse_y,false,false);
            font.setColor(Color.RED);
        }
    }

	@Override
	public void render () {
        update(Gdx.graphics.getDeltaTime());
		Gdx.gl.glClearColor(0, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		board.draw(batch);
        font.draw(batch, text, 200, Gdx.graphics.getHeight()-50);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		board.dispose();
		
	}
}
