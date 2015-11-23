package blue.made.acreage.server.world;

import java.util.Random;

import blue.made.acreage.server.tile.Tile;

public class WorldGen
{
	public final long seed;
	
	public Random random;
	
	public WorldGen()
	{
		this.seed = System.currentTimeMillis();
		this.random = new Random(this.seed);
	}
	
	public Tile getNewTile(int x, int y)
	{
		// TODO
		return null;
	}
}
