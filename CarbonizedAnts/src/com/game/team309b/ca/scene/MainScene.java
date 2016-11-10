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
import com.game.team309b.ca.insects.Ant;
import com.game.team309b.ca.insects.InsectBase;

public class MainScene implements Scene {
	private ExtendedSprite logo;
	private ExtendedSprite start_button;
	private ExtendedSprite exit_button;
	private ExtendedSprite tip_button;
	private ExtendedSprite ok_button;
	private ExtendedSprite bgm_button;
	private ExtendedSprite help;
	
	private HashSet<InsectBase> insects;
	
	private BitmapFont team_font;
	
	private Music bgm;
	
	private boolean tip_open;
	
	public MainScene()
	{
		Gdx.input.setCatchBackKey(true);
		
		logo = new ExtendedSprite(new Texture(Gdx.files.internal("data/Carbonized_Ants.png")));
		start_button = new ExtendedSprite(new Texture(Gdx.files.internal("data/Start.png")));
		exit_button = new ExtendedSprite(new Texture(Gdx.files.internal("data/Exit.png")));
		ok_button = new ExtendedSprite(new Texture(Gdx.files.internal("data/Ok.png")));
		tip_button = new ExtendedSprite(new Texture(Gdx.files.internal("data/tipbutton.png")));
		bgm_button = new ExtendedSprite(new Texture(Gdx.files.internal("data/bgm.png"))); 
		help = new ExtendedSprite(new Texture(Gdx.files.internal("data/tip.png")));
		
		insects = new HashSet<InsectBase>();
		
		logo.setNewSize();
		start_button.setNewSize();
		exit_button.setNewSize();
		ok_button.setNewSize();
		tip_button.setNewSize();
		bgm_button.setNewSize();
		help.setNewSize();
				
		start_button.setScale(0.9f);
		exit_button.setScale(0.9f);
		ok_button.setScale(0.9f);
		tip_button.setScale(0.9f);
		bgm_button.setScale(0.9f);
		
		tip_button.setColor(Color.BLACK);
		bgm_button.setColor(Color.BLACK);

		float x = CarbonizedAnts.context.screen_size.x;
		float y = CarbonizedAnts.context.screen_size.y;
		
		logo.setCenter(x / 2, y * 380 / 480);
		start_button.setCenter(x / 2, y * 230 / 480);
		exit_button.setCenter(x / 2, y * 130 / 480);
		ok_button.setCenter(x / 2, y / 2 - (help.getHeight() * 2 / 5));
		tip_button.setCenter(x - tip_button.getWidth() / 2, tip_button.getHeight() / 2);
		bgm_button.setCenter(bgm_button.getWidth() / 2, bgm_button.getHeight() / 2);
		help.setCenter(x / 2, y / 2);
		
		
		bgm = Gdx.audio.newMusic(Gdx.files.internal("data/burning.ogg"));
		bgm.setLooping(true);
		bgm.play();
		
		for (int i = 0; i < 10; ++i) {
			insects.add(new Ant());
		}
		
		team_font = new BitmapFont(Gdx.files.internal("data/font.fnt"));
		team_font.setScale(CarbonizedAnts.context.screen_size.x / 1920);
		team_font.setColor(0, 0, 0, 1);
		
		tip_open = false;
	
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		if(keycode == Keys.ENTER){
			if(tip_open){
				CarbonizedAnts.context.setScene(new ModeselectScene());
			}
			
			tip_open = true;
		}
		
		if(keycode == Keys.BACK){
			bgm.dispose();
			CarbonizedAnts.context.exit();
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
		if (start_button.overlaps(x,  CarbonizedAnts.context.screen_size.y - y) && !tip_open) {
			start_button.setScale(1);
		}
		
		if (exit_button.overlaps(x,  CarbonizedAnts.context.screen_size.y - y) && !tip_open) {
			exit_button.setScale(1);
		}
		
		if (ok_button.overlaps(x,  CarbonizedAnts.context.screen_size.y - y) && tip_open) {
			ok_button.setScale(1);
		}
		
		if (tip_button.overlaps(x,  CarbonizedAnts.context.screen_size.y - y)) {
			tip_button.setColor(Color.BLUE);
		}
		return false;
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		// TODO Auto-generated method stub
		if (start_button.overlaps(x,  CarbonizedAnts.context.screen_size.y - y) && !tip_open) {
			bgm.dispose();
			CarbonizedAnts.context.setScene(new ModeselectScene());
		}
		
		if (exit_button.overlaps(x,  CarbonizedAnts.context.screen_size.y - y) && !tip_open) {
			bgm.dispose();
			CarbonizedAnts.context.exit();
		}
		
		if (ok_button.overlaps(x,  CarbonizedAnts.context.screen_size.y - y) && tip_open) {
			tip_open = false;
		}
		
		if (tip_button.overlaps(x,  CarbonizedAnts.context.screen_size.y - y)) {
			tip_open = !tip_open;
		}
		
		if (tip_button.overlaps(x,  CarbonizedAnts.context.screen_size.y - y)) {
			tip_button.setColor(Color.BLACK);
		}
		
		if (bgm_button.overlaps(x,  CarbonizedAnts.context.screen_size.y - y)) {
			CarbonizedAnts.context.play_bgm = !CarbonizedAnts.context.play_bgm;
			if(!CarbonizedAnts.context.play_bgm){
				bgm.pause();
				bgm_button.setColor(Color.GRAY);
			} else{
				bgm.play();
				bgm_button.setColor(Color.BLACK);
			}
		}
		
		return false;
	}

	@Override
	public boolean touchDragged(int x, int y, int pointer) {
		// TODO Auto-generated method stub
		if (start_button.overlaps(x, CarbonizedAnts.context.screen_size.y - y) && !tip_open) {
			start_button.setScale(1);
		} else {
			start_button.setScale(0.9f);
		}
		
		if (exit_button.overlaps(x, CarbonizedAnts.context.screen_size.y - y) && !tip_open) {
			exit_button.setScale(1);
		} else {
			exit_button.setScale(0.9f);
		}
		
		if (ok_button.overlaps(x, CarbonizedAnts.context.screen_size.y - y) && tip_open) {
			ok_button.setScale(1);
		} else {
			ok_button.setScale(0.9f);
		}
		
		if (tip_button.overlaps(x, CarbonizedAnts.context.screen_size.y - y)) {
			tip_button.setColor(Color.BLUE);
		} else {
			tip_button.setColor(Color.BLACK);
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
		
		Iterator<InsectBase> it = insects.iterator();
		
		while (it.hasNext()) {
			InsectBase ins = it.next();
			
			ins.move(1f, 0.01f);
			ins.render(batch, 1f, new Vector2(0,0));
		}
		
		logo.draw(batch);
		start_button.draw(batch);
		exit_button.draw(batch);
		tip_button.draw(batch);
		bgm_button.draw(batch);
		
		if(tip_open){
			help.draw(batch);
			ok_button.draw(batch);
		}
//		String team = "Team 309B";
//		team_font.draw(batch, team, (CarbonizedAnts.context.screen_size.x - team_font.getBounds(team).width) * 780/800,
//				CarbonizedAnts.context.screen_size.y * 20/480);
		
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
