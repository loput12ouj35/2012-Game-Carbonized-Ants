package com.game.team309b.ca.scene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.game.team309b.ca.CarbonizedAnts;
import com.game.team309b.ca.ExtendedSprite;
import com.game.team309b.ca.GameScore.Mode;

public class ModeselectScene implements Scene {
	private ExtendedSprite modeA_button;
	private ExtendedSprite modeB_button;
	private ExtendedSprite modeC_button;
	private ExtendedSprite modeSelect;
	
	private boolean selected;
	
	private Music bgm;
	
	public ModeselectScene()
	{		
		modeA_button = new ExtendedSprite(new Texture(Gdx.files.internal("data/modeA.png")));
		modeB_button = new ExtendedSprite(new Texture(Gdx.files.internal("data/modeB.png")));
		modeC_button = new ExtendedSprite(new Texture(Gdx.files.internal("data/modeC.png")));
		modeSelect = new ExtendedSprite(new Texture(Gdx.files.internal("data/modeselect.png")));
		
		modeA_button.setNewSize();
		modeB_button.setNewSize();
		modeC_button.setNewSize();
		modeSelect.setNewSize();
		
		modeSelect.setColor(Color.RED);
				
		float x = CarbonizedAnts.context.screen_size.x;
		float y = CarbonizedAnts.context.screen_size.y;
				
		modeA_button.setCenter(x * 360 / 1920, y / 2);
		modeB_button.setCenter(x * 960 / 1920, y / 2);
		modeC_button.setCenter(x * 1560 / 1920, y / 2);
		modeSelect.setCenter(x * 360 / 1920, y / 2);
		
		bgm = Gdx.audio.newMusic(Gdx.files.internal("data/bgm2.mp3"));
		bgm.setLooping(true);
		if(CarbonizedAnts.context.play_bgm)
			bgm.play();
		
		selected = false;
	}
	
	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		if(keycode == Keys.BACK){
			bgm.dispose();
			CarbonizedAnts.context.setScene(new MainScene());
		}
		
		if(keycode == Keys.ESCAPE){
			bgm.dispose();
			CarbonizedAnts.context.setScene(new MainScene());
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
		if (modeA_button.overlaps(x,  CarbonizedAnts.context.screen_size.y - y)) {
			modeSelect.setCenter(CarbonizedAnts.context.screen_size.x * 360 / 1920,
					CarbonizedAnts.context.screen_size.y / 2);
			selected = true;
		}
		
		if (modeB_button.overlaps(x,  CarbonizedAnts.context.screen_size.y - y)) {
			modeSelect.setCenter(CarbonizedAnts.context.screen_size.x * 960 / 1920,
					CarbonizedAnts.context.screen_size.y / 2);
			selected = true;
		}
		
		if (modeC_button.overlaps(x,  CarbonizedAnts.context.screen_size.y - y)) {
			modeSelect.setCenter(CarbonizedAnts.context.screen_size.x * 1560 / 1920,
					CarbonizedAnts.context.screen_size.y / 2);
			selected = true;
		}
		
		return false;
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		// TODO Auto-generated method stub
		
		if (modeA_button.overlaps(x,  CarbonizedAnts.context.screen_size.y - y)) {
			bgm.dispose();
			CarbonizedAnts.context.setScene(new LevelselectScene(Mode.A));
		}
		
		if (modeB_button.overlaps(x,  CarbonizedAnts.context.screen_size.y - y)) {
			bgm.dispose();
			CarbonizedAnts.context.setScene(new LevelselectScene(Mode.B));
		}
		
		if (modeC_button.overlaps(x,  CarbonizedAnts.context.screen_size.y - y)) {
			bgm.dispose();
			CarbonizedAnts.context.setScene(new LevelselectScene(Mode.C));
		}
		
		return false;
	}

	@Override
	public boolean touchDragged(int x, int y, int pointer) {
		// TODO Auto-generated method stub
		if (modeA_button.overlaps(x, CarbonizedAnts.context.screen_size.y - y)) {
			modeSelect.setCenter(CarbonizedAnts.context.screen_size.x * 360 / 1920,
					CarbonizedAnts.context.screen_size.y / 2);
			selected = true;
		} else if (modeB_button.overlaps(x, CarbonizedAnts.context.screen_size.y - y)) {
			modeSelect.setCenter(CarbonizedAnts.context.screen_size.x * 960 / 1920,
					CarbonizedAnts.context.screen_size.y / 2);
			selected = true;
		} else if (modeC_button.overlaps(x, CarbonizedAnts.context.screen_size.y - y)) {
			modeSelect.setCenter(CarbonizedAnts.context.screen_size.x * 1560 / 1920, 
					CarbonizedAnts.context.screen_size.y / 2);
			selected = true;
		} else {
			selected = false;
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
		modeA_button.draw(batch);
		modeB_button.draw(batch);
		modeC_button.draw(batch);
		
		if(selected)
			modeSelect.draw(batch);
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

}
