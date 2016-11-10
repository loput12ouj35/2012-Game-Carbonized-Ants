package com.game.team309b.ca.scene;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.game.team309b.ca.CarbonizedAnts;
import com.game.team309b.ca.GameScore.GameLevel;
import com.game.team309b.ca.Lens;
import com.game.team309b.ca.insects.Ant;
import com.game.team309b.ca.insects.Nabang;
import com.game.team309b.ca.insects.Nabi;

public class PlayCScene extends PlayScene {
	private float energy_origin;
	private float speed_origin;
	
	public PlayCScene(GameLevel level)
	{
		super();
		
		mode = mode.C;
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
			number_ants = 1;
			number_nabang = 10;
			number_nabi = 10;
			energy_origin = 150;
			speed_origin = 150;
			break;
		case NORMAL:
			number_ants = 1;
			number_nabang = 15;
			number_nabi = 15;
			energy_origin = 200;
			speed_origin = 200;
			break;
		case HARD:
			number_ants = 1;
			number_nabang = 20;
			number_nabi = 20;
			energy_origin = 250;
			speed_origin = 250;
			break;
		}
		
		for (int i = 0; i < number_ants; ++i) {
			insects.add(new Ant(energy_origin, speed_origin));
		}
		
		for (int i = 0; i < number_nabang; ++i) {
			insects.add(new Nabang());
		}
		
		for (int i = 0; i < number_nabi; ++i) {
			insects.add(new Nabi());
		}
	}

	@Override
	public void render(SpriteBatch batch)
	{
		super.render(batch);
		
		if(!paused)
			energy_current /= energy_origin;
		
		right_upside = "Ant : " + (int)(energy_current * 100) + "%";
		
		time_font.draw(batch, "Time : " + ((float)((int)(play_time * 10)) / 10), 0, CarbonizedAnts.context.screen_size.y);

		if(energy_current < 0.1f){
			ant_font.setColor(1, 0, 0, 1);
		} else if(energy_current < 0.5f){
			ant_font.setColor(1, 1, 0, 1);
		}
		
		ant_font.draw(batch, right_upside, CarbonizedAnts.context.screen_size.x - ant_font.getBounds(right_upside).width, CarbonizedAnts.context.screen_size.y);
	}
}
