package com.game.team309b.ca.scene;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.game.team309b.ca.CarbonizedAnts;
import com.game.team309b.ca.GameScore.GameLevel;
import com.game.team309b.ca.Lens;

public class PlayAScene extends PlayScene {
		
	public PlayAScene(GameLevel level)
	{		
		super();
		
		mode = mode.A;
		this.level = level;
		
		switch (level) {
		default:
		case EASY:
			lens = new Lens(0.75f);
			break;
		case NORMAL:
			lens = new Lens(0.75f);
			break;
		case HARD:
			lens = new Lens(0.75f);
			break;
		}	
		
		switch (level) {
		default:
		case EASY:
			number_ants = 100;
			number_nabang = 10;
			number_nabi = 10;
			break;
		case NORMAL:
			number_ants = 300;
			number_nabang = 15;
			number_nabi = 15;
			break;
		case HARD:
			number_ants = 500;
			number_nabang = 20;
			number_nabi = 20;
			break;
		}
		setInsects();		
	}

	@Override
	public void render(SpriteBatch batch)
	{
		super.render(batch);

		time_font.draw(batch, "Time : " + ((float)((int)(play_time * 10)) / 10), 0, CarbonizedAnts.context.screen_size.y);
		
		right_upside = "Ants : " + (number_ants-insects_killed) + "/" + number_ants;
		ant_font.draw(batch, right_upside, CarbonizedAnts.context.screen_size.x - ant_font.getBounds(right_upside).width, CarbonizedAnts.context.screen_size.y);
		
	}
}
