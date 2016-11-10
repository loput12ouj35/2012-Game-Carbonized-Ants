package com.game.team309b.ca;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.game.team309b.ca.scene.MainScene;
import com.game.team309b.ca.scene.ModeselectScene;
import com.game.team309b.ca.scene.Scene;
import com.game.team309b.ca.scene.ScoreAScene;
import com.game.team309b.ca.scene.ScoreBScene;
import com.game.team309b.ca.scene.ScoreCScene;

public class GameContext {
	private Scene scene;
	private GameScore score;
	public boolean play_bgm; 
	
	public final Vector2 map_size;
	public final Vector2 screen_size;
	public final Vector2 screen_center;
	
	public final Texture tex_ash;
	public final Texture tex_smoke;
	
	public final Sound ashing;
	public final Sound burning;
	
	public final Texture nabang_tex;
	public final Texture nabi_tex;
	public final Texture ant_tex;
	
	private GameCallback callback;
	
	public GameContext(CarbonizedAnts ca, GameCallback callback)
	{
		map_size = new Vector2(Gdx.graphics.getWidth() * 2, Gdx.graphics.getHeight() * 2);
		screen_size = new Vector2(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		screen_center = new Vector2(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
		
		tex_ash = new Texture(Gdx.files.internal("data/ash.png"));
		tex_smoke = new Texture(Gdx.files.internal("data/smoke.png"));
		
		ashing  = Gdx.audio.newSound(Gdx.files.internal("data/ashing.mp3"));
		burning = Gdx.audio.newSound(Gdx.files.internal("data/burning.ogg"));
		
		nabang_tex = new Texture(Gdx.files.internal("data/nabang.png"));
		nabi_tex   = new Texture(Gdx.files.internal("data/nabi.png"));
		ant_tex    = new Texture(Gdx.files.internal("data/ant1-4.png"));
		
		play_bgm = true;
		
		this.callback = callback;
	}
	
	public void setScene(Scene scene)
	{
		if (this.scene != null)
			this.scene.dispose();
		
		if (scene instanceof ModeselectScene) {
			callback.run();
		} else if (scene instanceof ScoreAScene) {
			callback.idle();
		} else if (scene instanceof ScoreBScene) {
			callback.idle();
		} else if (scene instanceof ScoreCScene) {
			callback.idle();
		} else if (scene instanceof MainScene) {
			callback.idle();
		}
		
		this.scene = scene;
		Gdx.input.setInputProcessor(scene);
	}
	
	public Scene getScene()
	{
		return scene;
	}

	public GameScore getScore()
	{
		return score;
	}

	public void setScore(GameScore score)
	{
		this.score = score;
	}
	
	public void exit()
	{
//		ant_tex.dispose();
//		nabi_tex.dispose();
//		nabang_tex.dispose();
//		
//		tex_ash.dispose();
//		tex_smoke.dispose();
		
		Gdx.app.exit();
	}
}
