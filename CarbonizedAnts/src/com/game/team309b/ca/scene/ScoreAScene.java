package com.game.team309b.ca.scene;

public class ScoreAScene extends ScoreScene {
		
	public ScoreAScene()
	{
		super();
	}
	
	@Override
	public String setGrade() {
		float bonus = setBonusbylevel();
		
		if(!scoreSet.success){
			return "F";
		} else if(scoreSet.time < 80 * bonus){
			return (scoreSet.time < 60 * bonus) ?  "S" :
				((scoreSet.time < 70 * bonus) ?  "A" : "B");
		} else{
			return (scoreSet.time < 100 * bonus) ?  "C" : "D";
		}	
		
	}
	
	@Override
	public float setBonusbylevel(){
		switch(scoreSet.level){
		default:
		case EASY:
			return 1.00f;
		case NORMAL:
			return 1.25f;
		case HARD:
			return 1.5f;		
		}
	}

}
