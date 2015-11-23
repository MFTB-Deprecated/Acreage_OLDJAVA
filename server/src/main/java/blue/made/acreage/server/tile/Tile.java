package blue.made.acreage.server.tile;

import blue.made.acreage.server.card.tile.TileType;
import blue.made.acreage.server.util.WorldPos;
import blue.made.acreage.server.world.World;
import blue.made.acreage.server.world.WorldGridObject;

public class Tile extends WorldGridObject
{
	public TileType type;
	public boolean visible;
	
	public Tile(World world, TileType type)
	{
		super(world);
		this.type = type;
	}
	
	@Override
	public void setPos(WorldPos to)
	{
		this.world.setTile(to, this);
	}
}
