package blue.made.acreage.server.card.tile;

import blue.made.acreage.server.tile.Tile;
import blue.made.acreage.server.world.World;

public class TileType
{
	public final String name;
	
	public TileType(String name)
	{
		this.name = name;
	}
	
	public Tile create(World world)
	{
		return new Tile(world, this);
	}
}
