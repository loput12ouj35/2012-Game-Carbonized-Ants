package com.game.team309b.ca;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.game.team309b.ca.scene.MainScene;

public class CarbonizedAnts implements ApplicationListener {
	private SpriteBatch batch;
	private GameCallback callback;
	
	public static GameContext context;
	
	private static class NullCallback implements GameCallback {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void idle() {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	public CarbonizedAnts(GameCallback callback) {
		this.callback = callback;
	}
	
	public CarbonizedAnts() {
		this(new NullCallback());
	}
	
	@Override
	public void create() {
		batch = new SpriteBatch();
		
		context = new GameContext(this, callback);
		context.setScene(new MainScene());
	}

	@Override
	public void dispose() {
		Gdx.app.log("wahaha", "wahaha");
		
		batch.dispose();
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		context.getScene().render(batch);
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}
