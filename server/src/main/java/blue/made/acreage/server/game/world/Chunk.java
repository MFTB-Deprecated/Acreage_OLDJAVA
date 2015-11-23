package blue.made.acreage.server.game.world;

public class Chunk
{
	public final World world;
	private byte count = 0;
	
	private TileObject[] tiles = new TileObject[64];
	
	public Chunk(World world, int x, int y)
	{
		this.world = world;
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
		TileObject old = this.tiles[id];
		this.tiles[id] = tile;
		tile.world = this.world;
		tile.onPlaced(old);
	}
	
	public TileObject getTile(int x, int y)
	{
		return this.tiles[this.id(x, y)];
	}
	
	public TileObject removeTile(int x, int y)
	{
		int id = this.id(x, y);
		TileObject out = this.tiles[id];
		if (out != null)
		{
			out.onReplaced(null);
			this.count--;
		}
		this.tiles[id].world = null;
		this.tiles[id] = null;
		return out;
	}
	
	public int count()
	{
		return this.count;
	}
}
