package com.game.team309b.ca.scene;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface Scene extends InputProcessor {
	public void render(SpriteBatch batch);
	public void dispose();
}
