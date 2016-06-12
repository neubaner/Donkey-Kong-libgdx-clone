package com.donkeykong.game.Entities;

import com.badlogic.gdx.Input.Keys;
import com.donkeykong.game.JumpmanState;

public class Jumpman extends Entity
{
	private JumpmanState state;
	private float vel;
	
	public Jumpman()
	{
		super(0,0,12,12,"Jumpman");
		state = JumpmanState.idle; 
		vel = 35;
	}
	
	@Override
	public void update(float dt)
	{
		super.update(dt);
	}

	@Override
	public void draw()
	{
		
	}

	@Override
	public void keyUp(int keycode)
	{
		if(state == JumpmanState.idle)
		{
			if(keycode == Keys.RIGHT)
				velx = 0;
			if(keycode == Keys.LEFT)
				velx = 0;
		}
	}

	@Override
	public void keyDown(int keycode)
	{
		if(state == JumpmanState.idle)
		{
			if(keycode == Keys.RIGHT)
				velx = vel;
			if(keycode == Keys.LEFT)
				velx = -vel;
		} 
			
	}

}
