package blue.made.acreage.server.game.world.gen;

import java.util.Random;

import blue.made.acreage.server.game.world.TileObject;

public class WorldGen
{
	public final long seed;
	
	public Random random;
	
	public WorldGen()
	{
		this.seed = System.currentTimeMillis();
		this.random = new Random(this.seed);
	}
	
	public TileObject getNewTile(int x, int y)
	{
		TileObject out = new TileObject(x, y);
		return out;
	}
}
