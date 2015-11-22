package blue.made.acreage.server.game.world;

import blue.made.acreage.server.card.tile.ITile;

public class TileObject
{
	public World world;
	public final int x;
	public final int y;
	private ITile tile;
	private boolean explored = false;
	
	public TileObject(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public ITile getTile()
	{
		return this.tile;
	}
	
	public void setTile(ITile type)
	{
		this.tile = type;
		if (this.world != null)
		{
			this.world.setTile(this);
		}
	}
	
	public boolean isExplored()
	{
		return this.explored;
	}
	
	public void setExplored(boolean explored)
	{
		this.explored = explored;
	}
	
	public void onReplaced(TileObject with)
	{
		
	}
}
