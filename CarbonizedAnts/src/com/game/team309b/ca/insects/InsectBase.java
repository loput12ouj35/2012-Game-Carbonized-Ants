package com.game.team309b.ca.insects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.game.team309b.ca.CarbonizedAnts;
import com.game.team309b.ca.ExtendedSprite;

public abstract class InsectBase {
	protected ExtendedSprite drawing;
	protected ExtendedSprite sprite;
	private final ExtendedSprite smoke;
	
	protected float speed;
	protected float dir;
	
	protected Vector2 center;
	
	protected float energy; 
	protected final float damage;
	
	private final float init_speed;
	private final float init_energy;
	
	public final float bonus;
	
	private boolean burn;
	private boolean died;
	
	private float remain_time;

	public InsectBase(ExtendedSprite sprite, float speed, Vector2 center, float dir, float bonus, float energy)
	{
		this.sprite = new ExtendedSprite(sprite);
		this.speed  = speed * CarbonizedAnts.context.screen_size.x / 800;
		this.damage = 7.2f;
		
		this.remain_time = 1;
		
		this.dir = dir;
		this.center = new Vector2(center);
		this.bonus = bonus;
		this.energy = energy;
		
		this.init_speed  = speed * CarbonizedAnts.context.screen_size.x / 800;
		this.init_energy = energy;
		
		this.smoke = new ExtendedSprite(CarbonizedAnts.context.tex_smoke);
		
		this.burn = false;
		this.died = false;
	}

	public boolean died(float scale, float e_time)
	{
		if (died) {
			sprite.setRegion(CarbonizedAnts.context.tex_ash);
			sprite.setRotation(0);
			remain_time -= e_time;
			
			if (remain_time > 0)
				return false;
			
			return true;
		}
		
		if (in_range(scale)) {
			energy -= damage  * scale * e_time;
		}
		
		if (energy > 0)
			return false;
		
		died = true;
		if(CarbonizedAnts.context.play_bgm)
			CarbonizedAnts.context.ashing.play();
		
		return false;
	}
	
	public void move(float scale, float e_time)
	{
		if (energy < 0)
			return;
		
		if (in_range(scale))
			move_in_range();
		
		if (energy > (init_energy / 2))
			move_random();
		else
			move_crazy();
		
		sprite.setRotation(dir);
		
		center.add(speed * e_time * (float)Math.cos(dir * 2 * Math.PI / 360.0),
				speed * e_time * (float)Math.sin(dir * 2 * Math.PI / 360.0));
		
		center.add(CarbonizedAnts.context.map_size);
	}
	
	private void move_random()
	{
		dir += (10 - Math.random() * 20);
	}
	
	private void move_crazy()
	{
		speed = init_speed * 2;
		dir += (20 - Math.random() * 40); 
	}
	
	abstract public void move_in_range();
	
	public void render(SpriteBatch batch, float scale, Vector2 offset)
	{
		center.add(offset);
		
		center.x %= CarbonizedAnts.context.map_size.x;
		center.y %= CarbonizedAnts.context.map_size.y;
		
		if (center.x > (CarbonizedAnts.context.screen_size.x))
			return;
		if (center.y > (CarbonizedAnts.context.screen_size.y))
			return;

		Vector2 tmp = new Vector2(center);
		
		tmp.sub(CarbonizedAnts.context.screen_center);
		tmp.scl(scale);
		tmp.add(CarbonizedAnts.context.screen_center);
		
		sprite.setScale(0.25f * scale * CarbonizedAnts.context.screen_size.x / 800);
		sprite.setColor((init_energy - energy) / init_energy / 2, 0, 0, 1);
		sprite.setCenter(tmp);
		
		sprite.getTexture().bind();
		sprite.draw(batch);
		
		if (burn) {
			smoke.setScale(0.25f * scale * CarbonizedAnts.context.screen_size.x / 800);
			smoke.setCenter(tmp.x, (tmp.y + smoke.getHeight() * 0.10f));
			smoke.draw(batch);
			return;
		}
		
		if (energy < (init_energy / 2)) {
			burn = true;
			if(CarbonizedAnts.context.play_bgm)
				CarbonizedAnts.context.burning.play(0.5f);
		}
	}
	
	private boolean in_range(float scale){
		return (center.dst(CarbonizedAnts.context.screen_center) < (CarbonizedAnts.context.screen_center.y * 2 / 3 / scale));
	}

	public float getEnergy() {
		return energy;
	}
}
