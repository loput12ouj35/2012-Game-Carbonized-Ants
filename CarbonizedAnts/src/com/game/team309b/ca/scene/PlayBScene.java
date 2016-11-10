package com.game.team309b.ca.scene;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.game.team309b.ca.CarbonizedAnts;
import com.game.team309b.ca.GameScore.GameLevel;
import com.game.team309b.ca.Lens;

public class PlayBScene extends PlayScene {
	
	private final static float init_time = 60f;
	
	public PlayBScene(GameLevel level)
	{
		super();
		
		mode = mode.B;
		this.level = level;
		
		switch (level) {
		default:
		case EASY:
			lens = new Lens(0.75f);
			break;
		case NORMAL:
			lens = new Lens(0.85f);
			break;
		case HARD:
			lens = new Lens(0.95f);
			break;
		}
		
		switch (level) {
		default:
		case EASY:
			number_ants = 15;
			number_nabang = 10;
			number_nabi = 10;
			break;
		case NORMAL:
			number_ants = 15;
			number_nabang = 20;
			number_nabi = 20;
			break;
		case HARD:
			number_ants = 15;
			number_nabang = 35;
			number_nabi = 35;
			break;
		}
		setInsects();
		
		remain_time = init_time;
	}

	@Override
	public void render(SpriteBatch batch)
	{
		super.render(batch);
		
		if (remain_time < 0) {
			endGame(false);
		}
		
	
		time_font.draw(batch, "Time : " + ((float)((int)(remain_time * 10)) / 10), 0, CarbonizedAnts.context.screen_size.y);
		if(remain_time <= 5.0){
			time_font.setColor(1, 0, 0, 1);
		}
		
		right_upside = "Ants : " + (number_ants-insects_killed) + "/" + number_ants;
		ant_font.draw(batch, right_upside, CarbonizedAnts.context.screen_size.x - ant_font.getBounds(right_upside).width, CarbonizedAnts.context.screen_size.y);
	}
}
