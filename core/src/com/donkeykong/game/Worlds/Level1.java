package com.donkeykong.game.Worlds;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.donkeykong.game.Entities.Barrel;
import com.donkeykong.game.Entities.Jumpman;
import com.donkeykong.game.Entities.Ladder;
import com.donkeykong.game.Entities.Solid;

public class Level1 extends World
{
	private Jumpman jumpman;
	private ArrayList<Solid> solids;
	
	public Level1(OrthographicCamera camera)
	{
		super(camera);
		jumpman = new Jumpman(9,9);
		solids = new ArrayList<Solid>();
		shouldDrawHitbox = false;
		
		addEntity(jumpman);
		addEntity(new Barrel(180,48,false));
		
		Solid solid;
		
		addEntity(new Ladder(80,8));
		addEntity(new Ladder(80,32));
		addEntity(new Ladder(184,13));
		addEntity(new Ladder(184,21));
		
		//Floor1
		for(int i=0;i<14;i++)
		{
			solid = new Solid(i*8,0,8,8);
			solids.add(solid);
			addEntity(solid);
		}
		for(int i=14;i<28;i++)
		{
			solid = new Solid(i*8,Math.round((i-13)/2f),8,8);
			solids.add(solid);
			addEntity(solid);
		}
		
		//Floor2
		for(int i=0;i<26;i++)
		{
			solid = new Solid(i*8,40 - Math.round((i+1)/2f),8,8);
			solids.add(solid);
			addEntity(solid);
		}
	}

}
