package com.game.team309b.ca.insects;

import com.game.team309b.ca.CarbonizedAnts;
import com.game.team309b.ca.ExtendedSprite;

public class Ant extends AntBase {
	private static final float speed  = 112.f;
	private static final float bonus  = 0.0f;
	private static final float energy = 12.5f;

	public Ant(){
		this(energy, speed);
	}
	
	public Ant(float energy, float speed)
	{
		super(new ExtendedSprite(CarbonizedAnts.context.ant_tex), speed, bonus, energy);
		// TODO Auto-generated constructor stub
	}

}
