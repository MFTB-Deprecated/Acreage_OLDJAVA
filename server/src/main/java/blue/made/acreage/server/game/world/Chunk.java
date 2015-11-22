package blue.made.acreage.server.game.world;

public class Chunk
{
	private byte count = 0;
	
	private TileObject[] tiles = new TileObject[64];
	
	public Chunk(int x, int y)
	{
		
	}
	
	private int clip(int x)
	{
		return (x % 8 + x) % 8;
	}
	
	private int id(int x, int y)
	{
		return (this.clip(y) << 3) | this.clip(x);
	}
	
	public void setTile(TileObject tile)
	{
		int id = this.id(tile.x, tile.y);
		if (this.tiles[id] == null)
		{
			this.count++;
		}
		else
		{
			this.tiles[id].onReplaced(tile);
		}
		this.tiles[id] = tile;
	}
	
	public TileObject getTile(int x, int y)
	{
		return this.tiles[this.id(x, y)];
	}
	
	public void removeTile(int x, int y)
	{
		int id = this.id(x, y);
		if (this.tiles[id] != null)
		{
			this.tiles[id].onReplaced(null);
			this.count--;
		}
		this.tiles[id] = null;
	}
	
	public int count()
	{
		return this.count;
	}
}
