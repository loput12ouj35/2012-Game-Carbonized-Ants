package com.game.team309b.ca.scene;

public class ScoreCScene extends ScoreScene {
	
	public ScoreCScene()
	{
		super();
	}
	
	@Override
	public String setGrade() {
		float bonus = setBonusbylevel();
		
		if(!scoreSet.success){
			return "F";
		} else if(scoreSet.time < 60 * bonus){
			return (scoreSet.time < 20 * bonus) ?  "S" :
				((scoreSet.time < 30 * bonus) ?  "A" : "B");
		} else{
			return (scoreSet.time < 90 * bonus) ?  "C" : "D";
		}
		
	}
	
	@Override
	public float setBonusbylevel(){
		switch(scoreSet.level){
		default:
		case EASY:
			return 1.0f;
		case NORMAL:
			return 1.2f;
		case HARD:
			return 1.4f;		
		}
	}

}
