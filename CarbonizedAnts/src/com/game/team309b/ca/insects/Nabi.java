package com.game.team309b.ca.insects;

import com.game.team309b.ca.CarbonizedAnts;
import com.game.team309b.ca.ExtendedSprite;

public class Nabi extends GoodInsectBase {
	private static final float speed  = 152.f;
	private static final float bonus  = -1.2f;
	private static final float energy = 13.5f;
	
	public Nabi()
	{
		super(new ExtendedSprite(CarbonizedAnts.context.nabi_tex), speed, bonus, energy);
		// TODO Auto-generated constructor stub
	}

}
