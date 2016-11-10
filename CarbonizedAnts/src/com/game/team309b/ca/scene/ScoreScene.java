package com.game.team309b.ca.scene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.game.team309b.ca.CarbonizedAnts;
import com.game.team309b.ca.ExtendedSprite;
import com.game.team309b.ca.GameScore;

public abstract class ScoreScene implements Scene {
	protected GameScore scoreSet;
	
	protected BitmapFont score_font;
	protected BitmapFont level_font;
	
	protected String grade;
		
	protected ExtendedSprite gameOver;
	protected ExtendedSprite grade_sprite;
		
	protected Music Bgm;

	public ScoreScene()
	{
		scoreSet = CarbonizedAnts.context.getScore();
		
		level_font = new BitmapFont(Gdx.files.internal("data/font.fnt"));
		level_font.setColor(0, 0, 0, 1);
		level_font.setScale(CarbonizedAnts.context.screen_size.x / 1920);
		
		score_font = new BitmapFont(Gdx.files.internal("data/font.fnt"));
		score_font.setColor(0, 0, 0, 1);
		score_font.setScale(CarbonizedAnts.context.screen_size.x / 1920);
		
		grade = setGrade();
				
		gameOver = new ExtendedSprite(new Texture(Gdx.files.internal("data/Game Over.png")));
		grade_sprite =  new ExtendedSprite(new Texture(Gdx.files.internal("data/" + grade + ".png")));
		
		gameOver.setNewSize();
		
		gameOver.setCenter(CarbonizedAnts.context.screen_size.x / 2, CarbonizedAnts.context.screen_size.y * 400 / 480);
		
		grade_sprite.setCenter(CarbonizedAnts.context.screen_size.x * 3 / 4, CarbonizedAnts.context.screen_size.y * 225 /480);
		grade_sprite.setColor(0, 0, 0, 1);
		grade_sprite.setScale(CarbonizedAnts.context.screen_size.x / 1920 * 0.5f);
					
		Bgm = Gdx.audio.newMusic(Gdx.files.internal("data/burning.ogg"));
		Bgm.setLooping(true);
		if(CarbonizedAnts.context.play_bgm)
			Bgm.play();
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		if(keycode == Keys.BACK){
			Bgm.dispose();
			CarbonizedAnts.context.setScene(new ModeselectScene());
		}
		
		if(keycode == Keys.ENTER){
			Bgm.dispose();
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
				
		return false;
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		// TODO Auto-generated method stub\		
		return false;
	}

	@Override
	public boolean touchDragged(int x, int y, int pointer) {
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
		level_font.draw(batch, "Level : " + String.valueOf(scoreSet.level),
				CarbonizedAnts.context.screen_size.x / 8, CarbonizedAnts.context.screen_size.y * 300 / 480);

		score_font.draw(batch, "Played time : "+ ((float)((int)(scoreSet.time * 10)) / 10) + "s",
				CarbonizedAnts.context.screen_size.x / 8, CarbonizedAnts.context.screen_size.y * 250 / 480);
						
		score_font.draw(batch, "Carbonized Ants : "+ scoreSet.killed,
				CarbonizedAnts.context.screen_size.x / 8, CarbonizedAnts.context.screen_size.y * 150 / 480);
				
		gameOver.draw(batch);
		grade_sprite.draw(batch);
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
	
	public abstract String setGrade();
	
	public abstract float setBonusbylevel();

}
