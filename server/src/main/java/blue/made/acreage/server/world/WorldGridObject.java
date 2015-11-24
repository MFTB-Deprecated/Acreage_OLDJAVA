package blue.made.acreage.server.world;

import blue.made.acreage.server.util.WorldPos;

public class WorldGridObject
{
	public World world;
	WorldPos pos;
	
	public WorldGridObject()
	{
	}
	
	public WorldGridObject(World world)
	{
		this.world = world;
	}
	
	/**
	 * @return The current position on the grid
	 */
	public WorldPos pos()
	{
		return this.pos;
	}
	
	/** A utility form of {@link #setPos(WorldPos) */
	public void setPos(int x, int y)
	{
		this.setPos(new WorldPos(x, y));
	}
	
	public void setPos(WorldPos to)
	{
		this.pos = to;
	}
}
