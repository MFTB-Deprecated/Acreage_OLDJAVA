package blue.made.acreage.server.game.world;

import com.google.gson.JsonObject;

import blue.made.acreage.server.card.tile.Tile;

public class TileObject
{
	public World world;
	public final int x;
	public final int y;
	public JsonObject extra = new JsonObject();
	private Tile tile;
	private boolean explored = false;
	
	public TileObject(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public Tile getTile()
	{
		return this.tile;
	}
	
	public void setTile(Tile type)
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
	
	public TileObject at(int x, int y)
	{
		TileObject out = new TileObject(x, y);
		out.extra = this.extra;
		out.tile = this.tile;
		out.explored = this.explored;
		return out;
	}
	
	/**
	 * (this.world is not null when this is called)
	 */
	public void onReplaced(TileObject with)
	{
	
	}
	
	/**
	 * (this.world is not null when this is called)
	 */
	public void onPlaced(TileObject old)
	{
	
	}
}
