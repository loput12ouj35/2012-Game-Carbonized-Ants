package com.game.team309b.ca.scene;

import java.util.HashSet;
import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.game.team309b.ca.CarbonizedAnts;
import com.game.team309b.ca.ExtendedSprite;
import com.game.team309b.ca.GameScore;
import com.game.team309b.ca.GameScore.GameLevel;
import com.game.team309b.ca.GameScore.Mode;
import com.game.team309b.ca.Lens;
import com.game.team309b.ca.insects.Ant;
import com.game.team309b.ca.insects.InsectBase;
import com.game.team309b.ca.insects.Nabang;
import com.game.team309b.ca.insects.Nabi;

public abstract class PlayScene implements Scene {	
	protected GameLevel level;
	protected Mode mode;

	protected ExtendedSprite resume_button;
	protected ExtendedSprite giveup_button;
	
	protected BitmapFont time_font;
	protected BitmapFont ant_font;
	
	protected HashSet<InsectBase> insects;
	
	protected Vector2 prev_touch;
	protected Vector2 map_offset;
	protected Vector2 touch2_offset;
	
	protected float prev_dst;
	protected boolean is_multt;
	
	protected Lens lens;
	
	protected float scale;
	
	protected float play_time;
	protected float remain_time;
	protected float penalty_time;		//for mode B
	
	protected int insects_killed;
	protected int number_ants;
	protected int number_nabi;
	protected int number_nabang;
	
	protected float saved_time;
	
	protected Music bgm;
	
	protected boolean paused;
	
	protected String right_upside;
	
	protected float energy_current;		//for mode C
	
	public PlayScene()
	{
		play_time = 0f;
		remain_time = 60f;
		penalty_time = 0f;
		insects_killed = 0;
		
		scale = 1f;
		
		saved_time = 0f;
		
		prev_touch = new Vector2(0, 0);
		map_offset = new Vector2(0, 0);
		
		touch2_offset = new Vector2(0, 0);
		
		resume_button = new ExtendedSprite(new Texture(Gdx.files.internal("data/resume.png")));
		resume_button.setNewSize();
		resume_button.setScale(0.9f);
		resume_button.setCenter(CarbonizedAnts.context.screen_size.x / 2,
				CarbonizedAnts.context.screen_size.y * 3 / 5);
		resume_button.setColor(Color.BLACK);
		
		giveup_button = new ExtendedSprite(new Texture(Gdx.files.internal("data/Giveup.png")));
		giveup_button.setNewSize();
		giveup_button.setScale(0.9f);
		giveup_button.setCenter(CarbonizedAnts.context.screen_size.x / 2,
				CarbonizedAnts.context.screen_size.y * 2 / 5);
		giveup_button.setColor(Color.BLACK);
		
		time_font = new BitmapFont(Gdx.files.internal("data/font.fnt"));
		time_font.setScale(CarbonizedAnts.context.screen_size.x / 1920);
		time_font.setColor(1, 1, 1, 1);
		
		ant_font = new BitmapFont(Gdx.files.internal("data/font.fnt"));
		ant_font.setScale(CarbonizedAnts.context.screen_size.x / 1920);
		ant_font.setColor(1, 1, 1, 1);

		insects = new HashSet<InsectBase>();
			
		right_upside = new String();
		energy_current = 150;
		
		bgm = Gdx.audio.newMusic(Gdx.files.internal("data/bgm2.mp3"));
		bgm.setLooping(true);
		if(CarbonizedAnts.context.play_bgm)
			bgm.play();
	}

	@Override
	public boolean keyDown(int keycode)
	{
		// TODO Auto-generated method stub
		if(keycode == Keys.BACK){
			pause();
		}
		
		if(keycode == Keys.ESCAPE){
			pause();
		}
		
		if(keycode == Keys.ENTER){
			endGame(false);
		}
		
		return false;
	}

	@Override
	public boolean keyUp(int keycode)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int x, int y, int pointer, int button)
	{
		if (pointer == 0) {
			prev_touch.set(x, -y);
		} else {
			touch2_offset.set(x, -y);
			
			prev_dst = prev_touch.dst(touch2_offset);
			is_multt = true;
		}
		
		if (resume_button.overlaps(x,  CarbonizedAnts.context.screen_size.y - y) && paused) {
			resume_button.setScale(1);
		}
		
		if (giveup_button.overlaps(x,  CarbonizedAnts.context.screen_size.y - y) && paused) {
			giveup_button.setScale(1);
		}
		
		return false;
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button)
	{
		if (is_multt) {
			is_multt = false;
		} else {
			map_offset.add(x, -y);
			map_offset.sub(prev_touch);
			
			prev_touch.set(0, 0);
		}
		
		if (resume_button.overlaps(x,  CarbonizedAnts.context.screen_size.y - y) && paused) {
			paused = false;
			giveup_button.setScale(0.9f);
		}
		
		if (giveup_button.overlaps(x,  CarbonizedAnts.context.screen_size.y - y) && paused) {
			endGame(false);
		}
		
		return false;
	}

	@Override
	public boolean touchDragged(int x, int y, int pointer)
	{
		if (is_multt) {
			if (pointer == 0)
				prev_touch.set(x, -y);
			else 
				touch2_offset.set(x, -y);
			
			float cur_dst = prev_touch.dst(touch2_offset);
			
			change_scale(0.004f * (cur_dst - prev_dst));
			prev_dst = cur_dst;
		} else {
			map_offset.add(x, -y);
			map_offset.sub(prev_touch);
			
			
			prev_touch.set(x, -y);
		}
		
		if (resume_button.overlaps(x, CarbonizedAnts.context.screen_size.y - y) && paused) {
			resume_button.setScale(1);
		} else {
			resume_button.setScale(0.9f);
		}
		
		if (giveup_button.overlaps(x, CarbonizedAnts.context.screen_size.y - y) && paused) {
			giveup_button.setScale(1);
		} else {
			giveup_button.setScale(0.9f);
		}
				
		return false;
	}


	@Override
	public boolean scrolled(int amount)
	{
		change_scale(-0.25f * amount);
		
		return false;
	}

	@Override
	public void render(SpriteBatch batch)
	{
		saved_time += Gdx.graphics.getDeltaTime();
		
//		if (saved_time < (1f / 60f))
//			return;
		if(!paused){
			remain_time -= saved_time;
			play_time   += saved_time;
		}
				
		Iterator<InsectBase> it = insects.iterator();
				
		if (map_offset.len() > lens.getR() / 3) {
			map_offset = map_offset.nor();
		}
		
		while (!paused && it.hasNext()) {
			InsectBase ins = it.next();
			
			if (ins.died(scale, saved_time)) {
				
				if (ins instanceof Ant)
					++insects_killed;
				
				it.remove();
				remain_time += ins.bonus;
				penalty_time += ins.bonus;
				
				continue;
			}
			if (ins instanceof Ant)
				energy_current = ins.getEnergy();
			
			ins.move(scale, saved_time);
			ins.render(batch, scale, map_offset);
		}
		

		if (insects_killed == number_ants) {
			endGame(true);
			return;
		}
		
		lens.render(batch);
		
		if(paused) {
			resume_button.draw(batch);
			giveup_button.draw(batch);
		}
				
		saved_time = 0;
		map_offset.set(0, 0);
	}
	
	private void change_scale(float mod)
	{
		scale += mod;
		
		if (scale < 1)
			scale = 1;
		else if (scale > 2.5f)
			scale = 2.5f;
	}

	@Override
	public void dispose()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public void pause() {
		paused = !paused;
		
		if(paused){
			bgm.pause();
			
		} else{
			bgm.play();
		}
		
	}
	
	protected void setInsects(){
		for (int i = 0; i < number_ants; ++i) {
			insects.add(new Ant());
		}
		
		for (int i = 0; i < number_nabang; ++i) {
			insects.add(new Nabang());
		}
		
		for (int i = 0; i < number_nabi; ++i) {
			insects.add(new Nabi());
		}
	}
	
	protected void endGame(boolean success){
		bgm.dispose();
		CarbonizedAnts.context.setScore(new GameScore(penalty_time, play_time, number_ants, insects_killed, level, success, mode));
		
		switch(mode){
		default:
		case A:
			CarbonizedAnts.context.setScene(new ScoreAScene());
			break;
		case B:
			CarbonizedAnts.context.setScene(new ScoreBScene());
			break;
		case C:
			CarbonizedAnts.context.setScene(new ScoreCScene());
			break;
		}
	}
}
