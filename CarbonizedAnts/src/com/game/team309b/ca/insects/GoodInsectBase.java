package com.game.team309b.ca.insects;

import com.badlogic.gdx.math.Vector2;
import com.game.team309b.ca.CarbonizedAnts;
import com.game.team309b.ca.ExtendedSprite;

public class GoodInsectBase extends InsectBase {

	public GoodInsectBase(ExtendedSprite sprite, float speed, float bonus, float energy)
	{
		super(sprite, speed, new Vector2(
				(float)Math.random() * CarbonizedAnts.context.map_size.x,
				(float)Math.random() * CarbonizedAnts.context.map_size.y),
				(float)Math.random() * 360.0f, bonus, energy);
	}

	private float f(float x)
	{
		int p = ((int)x)/360;
		if(x<0)
			return x-p*360 + 180;
		else
			return x-p*360 - 180;
	}
	private double h = 2.0;
	private float g(float x)
	{
		float d = f(x);
		if(d > h)d=(float) h;
		if(d < -h)d=(float) -h;
		return d;
	}
	@Override
	public void move_in_range()
	{
		  Vector2 tmp = new Vector2(CarbonizedAnts.context.screen_center);
          float tangle;
          
          tmp.sub(center);
          tangle = tmp.angle();
          
          dir += g(dir - tangle);
	}

}
