package com.game.team309b.ca.scene;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.game.team309b.ca.CarbonizedAnts;

public class ScoreBScene extends ScoreScene {

	public ScoreBScene()
	{
		super();
	}
	
	@Override
	public void render(SpriteBatch batch) {
		super.render(batch);
		score_font.draw(batch, "Penalty : " + ((float)((int)((scoreSet.penalty_time) * 10)) / 10) + "s",
				CarbonizedAnts.context.screen_size.x / 8, CarbonizedAnts.context.screen_size.y * 200 / 480);
	}

	@Override
	public String setGrade() {
		float bonus = setBonusbylevel();
		
		if(scoreSet.success){
			return (scoreSet.time < 30f + bonus) ?  "S" :
				((scoreSet.time < 45f + bonus) ?  "A" : "B");
		} else{
			return (scoreSet.killed == 0) ? "F" : ((scoreSet.killed < scoreSet.init_ant /3) ? "D" :
				((scoreSet.killed < scoreSet.init_ant * 2 / 3) ? "C" : "B"));
		}
				
		
	}
	
	@Override
	public float setBonusbylevel(){
		switch(scoreSet.level){
		default:
		case EASY:
			return 0f;
		case NORMAL:
			return 5f;
		case HARD:
			return 10f;		
		}
	}

}
