package com.game.team309b.ca;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Lens {
	private final float alpha;

	private Pixmap pm;
	private Sprite sprite;
	
	public Lens(float alpha)
	{
		this.alpha = alpha;

		pm = new Pixmap(get2p((int)CarbonizedAnts.context.screen_size.x),
						get2p((int)CarbonizedAnts.context.screen_size.y),
						Pixmap.Format.RGBA8888);
		
		draw_lens();
	}
	
	private int get2p(int v)
	{
		 int i;
         
         for (i = 1; i < v; i *= 2);
         
         return i;
	}
	
	private void draw_lens()
	{
		 Pixmap.setBlending(Pixmap.Blending.None);
         
         pm.setColor(0, 0, 0, alpha);
         pm.fill();
         
         pm.setColor(1, 1, 1, 0);
         pm.fillCircle((int)CarbonizedAnts.context.screen_center.x, (int)CarbonizedAnts.context.screen_center.y,
                         (int)(CarbonizedAnts.context.screen_center.y * 2 / 3));
         
         Pixmap.setBlending(Pixmap.Blending.SourceOver);
         
         sprite = new Sprite(new TextureRegion(new Texture(pm),
                         (int)CarbonizedAnts.context.screen_size.x,
                         (int)CarbonizedAnts.context.screen_size.y));
         
         sprite.setX(0);
         sprite.setY(0);
	}

	public void render(SpriteBatch batch)
	{
		sprite.draw(batch);
	}
	
	public double getR()
	{
		double w = pm.getWidth() / 2;
		double h = pm.getHeight() / 2;
		
		return Math.sqrt(w * w + h * h);
	}
}
