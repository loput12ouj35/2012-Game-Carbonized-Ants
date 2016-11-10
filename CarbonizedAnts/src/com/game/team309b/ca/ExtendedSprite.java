package com.game.team309b.ca;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class ExtendedSprite extends Sprite {

	public ExtendedSprite()
	{
	}

	public ExtendedSprite(Texture texture)
	{
		super(texture);
	}

	public ExtendedSprite(TextureRegion region)
	{
		super(region);
	}

	public ExtendedSprite(Sprite sprite)
	{
		super(sprite);
	}

	public ExtendedSprite(Texture texture, int srcWidth, int srcHeight)
	{
		super(texture, srcWidth, srcHeight);
	}

	public ExtendedSprite(Texture texture, int srcX, int srcY, int srcWidth,
			int srcHeight)
	{
		super(texture, srcX, srcY, srcWidth, srcHeight);
	}

	public ExtendedSprite(TextureRegion region, int srcX, int srcY,
			int srcWidth, int srcHeight)
	{
		super(region, srcX, srcY, srcWidth, srcHeight);
	}

	public void setCenter(float x, float y)
	{
		setX(x - getWidth() / 2);
		setY(y - getHeight() / 2);
		
		setOriginCenter();
	}
	
	public void setCenter(Vector2 vec)
	{
		setX(vec.x - getWidth() / 2);
		setY(vec.y - getHeight() / 2);
		
		setOriginCenter();
	}
	
	public void setNewSize()
	{
		this.setSize(getWidth() * CarbonizedAnts.context.screen_size.x / 1920,
				getHeight() * CarbonizedAnts.context.screen_size.y / 1080);
	}
	
	public boolean overlaps(float x, float y)
	{
		if ((x - getX()) > getWidth())
			return false;
		if ((getX() - x) > 0)
			return false;
		
		if ((y - getY()) > getHeight())
			return false;
		if ((getY() - y) > 0)
			return false;
		
		return true;
	}
	
	public boolean overlaps(float x, float y, float width, float height)
	{
		return getBoundingRectangle().overlaps(new Rectangle(x, y, width, height));
	}
	
	public boolean overlaps(Rectangle rect)
	{
		return getBoundingRectangle().overlaps(rect);
	}
}
