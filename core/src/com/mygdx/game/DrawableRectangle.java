package com.mygdx.game;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class DrawableRectangle extends Actor {
	private Texture texture;
	private Sprite sprite;
	
	public DrawableRectangle(float p_x,float p_y,float p_width,float p_height, Color p_color){
		this.generateSolidTexture((int)p_width,(int)p_height,p_color);
		setX(p_x);
		setY(p_y);
		setWidth(p_width);
		setHeight(p_height);
	}
	
	private void generateSolidTexture(int p_width,int p_height,Color p_color){
		Pixmap m_pixmap = new Pixmap(p_width,p_height,Pixmap.Format.RGBA8888);
		m_pixmap.setColor(p_color);
		m_pixmap.fillRectangle(0, 0, p_width, p_height);
		texture = new Texture(m_pixmap);
		sprite = new Sprite(texture);
		m_pixmap.dispose();
	}

    
    /*
     * Setters and getters
     */

	public Texture getTexture() {
		return texture;
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
	}

	public Sprite getSprite() {
		return sprite;
	}

	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}

}
