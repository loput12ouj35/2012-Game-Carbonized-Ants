package com.game.team309b.ca.scene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.game.team309b.ca.CarbonizedAnts;
import com.game.team309b.ca.ExtendedSprite;
import com.game.team309b.ca.GameScore.GameLevel;
import com.game.team309b.ca.GameScore.Mode;

public class LevelselectScene implements Scene {
	private ExtendedSprite easy;
	private ExtendedSprite normal;
	private ExtendedSprite hard;
	private Music bgm;
	private Mode mode;
	
	public LevelselectScene(Mode mode)
	{		
		this.mode = mode;
		
		easy = new ExtendedSprite(new Texture(Gdx.files.internal("data/Easy.png")));
		normal = new ExtendedSprite(new Texture(Gdx.files.internal("data/Normal.png")));
		hard = new ExtendedSprite(new Texture(Gdx.files.internal("data/Hard.png")));

		easy.setNewSize();
		normal.setNewSize();
		hard.setNewSize();
		
		float x = CarbonizedAnts.context.screen_size.x;
		float y = CarbonizedAnts.context.screen_size.y;
		
		easy.setScale(0.9f);
		normal.setScale(0.9f);
		hard.setScale(0.9f);
		
		easy.setCenter(x / 2, y * 3 /4);
		normal.setCenter(x / 2, y / 2);
		hard.setCenter(x / 2, y / 4);
		
		easy.setColor(Color.BLACK);
		normal.setColor(Color.BLACK);
		hard.setColor(Color.BLACK);
			
		bgm = Gdx.audio.newMusic(Gdx.files.internal("data/bgm2.mp3"));
		bgm.setLooping(true);
		if(CarbonizedAnts.context.play_bgm)
			bgm.play();
	}
	
	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		if(keycode == Keys.BACK){
			bgm.dispose();
			CarbonizedAnts.context.setScene(new ModeselectScene());
		}
		
		if(keycode == Keys.ESCAPE){
			bgm.dispose();
			CarbonizedAnts.context.setScene(new ModeselectScene());
		}
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		// TODO Auto-generated method stub
		if (easy.overlaps(x,  CarbonizedAnts.context.screen_size.y - y)) {
			easy.setScale(1);
		}
		
		if (normal.overlaps(x,  CarbonizedAnts.context.screen_size.y - y)) {
			normal.setScale(1);
		}
		
		if (hard.overlaps(x,  CarbonizedAnts.context.screen_size.y - y)) {
			hard.setScale(1);
		}
		
		return false;
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		// TODO Auto-generated method stub
		
		if (easy.overlaps(x,  CarbonizedAnts.context.screen_size.y - y)) {
			bgm.dispose();
			gameStart(GameLevel.EASY);
		}
		
		if (normal.overlaps(x,  CarbonizedAnts.context.screen_size.y - y)) {
			bgm.dispose();
			gameStart(GameLevel.NORMAL);
		}
		
		if (hard.overlaps(x,  CarbonizedAnts.context.screen_size.y - y)) {
			bgm.dispose();
			gameStart(GameLevel.HARD);
		}
		
		return false;
	}

	@Override
	public boolean touchDragged(int x, int y, int pointer) {
		// TODO Auto-generated method stub
		if (easy.overlaps(x, CarbonizedAnts.context.screen_size.y - y)) {
			easy.setScale(1);
		} else {
			easy.setScale(0.9f);
		}
		
		if (normal.overlaps(x, CarbonizedAnts.context.screen_size.y - y)) {
			normal.setScale(1);
		} else {
			normal.setScale(0.9f);
		}
		
		if (hard.overlaps(x, CarbonizedAnts.context.screen_size.y - y)) {
			hard.setScale(1);
		} else {
			hard.setScale(0.9f);
		}
		
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void render(SpriteBatch batch) {
		// TODO Auto-generated method stub
		easy.draw(batch);
		normal.draw(batch);
		hard.draw(batch);
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
	
	public void gameStart(GameLevel level) {
		switch(mode) {
		default:
		case A:
			CarbonizedAnts.context.setScene(new PlayAScene(level));
			break;
		case B:
			CarbonizedAnts.context.setScene(new PlayBScene(level));
			break;
		case C:
			CarbonizedAnts.context.setScene(new PlayCScene(level));
			break;		
		}
	}

}
