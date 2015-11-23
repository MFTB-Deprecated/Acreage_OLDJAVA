package blue.made.acreage.server.tile;

import blue.made.acreage.server.card.tile.TileType;
import blue.made.acreage.server.util.WorldPos;
import blue.made.acreage.server.world.World;
import blue.made.acreage.server.world.WorldGridObject;

public class Tile extends WorldGridObject
{
	public TileType type;
	public boolean visible;
	public boolean crumble;
	
	public Tile(World world, TileType type)
	{
		super(world);
		this.type = type;
	}
	
	/**
	 * Changes/sets the position of this tile on the grid. If a tile already
	 * exists at the given position, then that tile is replaced and its position
	 * is set to null. Equivalent to calling
	 * {@link World#setTile(WorldPos, Tile) World.setTile(to, this)}.
	 */
	@Override
	public void setPos(WorldPos to)
	{
		this.world.setTile(to, this);
	}
}
