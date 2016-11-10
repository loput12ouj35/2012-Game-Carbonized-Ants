package com.game.team309b.ca;

import java.io.Serializable;


public class GameScore implements Serializable {
	
	public static enum GameLevel {
		EASY,
		NORMAL,
		HARD
	}
	
	public static enum Mode{
		A,
		B,
		C
	}
	
	
	private static final long serialVersionUID = 383102700212347649L;
	
	public final float penalty_time;
	public final float time;
	public final int init_ant;
	public final int killed;
	public final GameLevel level;
	public final boolean success;
	public final Mode mode;
	
	public GameScore(float penalty_time, float time, int init_ant, int killed,
			GameLevel level, boolean success, Mode mode)
	{
		this.penalty_time = penalty_time;
		this.time = time;
		this.init_ant = init_ant;
		this.killed = killed;
		this.level = level;
		this.success = success;
		this.mode = mode;
	}

	@Override
	public String toString()
	{
		return String.valueOf(time) + "\t" + String.valueOf(killed);
	}
}
