package com.donkeykong.game.Entities;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.donkeykong.game.Assets;

public class Pauline extends Entity
{
	private TextureRegion graphic = new TextureRegion(Assets.tileset, 168, 16, 16, 24);

	public Pauline(float x, float y)
	{
		super(new Vector2(x, y), new Rectangle(0,0,16,24), "pauline");
	}
	
	@Override
	public void draw(Batch batch)
	{
		batch.draw(graphic, pos.x, pos.y);
	}

}
