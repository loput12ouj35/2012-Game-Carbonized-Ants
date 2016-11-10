package com.game.team309b.ca.insects;

import com.game.team309b.ca.CarbonizedAnts;
import com.game.team309b.ca.ExtendedSprite;

public class Nabang extends GoodInsectBase {
	private static final float speed  = 152.f;
	private static final float bonus  = -1.2f;
	private static final float energy = 11.5f;
	

	public Nabang()
	{
		super(new ExtendedSprite(CarbonizedAnts.context.nabang_tex), speed, bonus, energy);
		// TODO Auto-generated constructor stub
	}
}
