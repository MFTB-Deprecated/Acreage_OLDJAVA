package blue.made.acreage.server.world;

import blue.made.acreage.server.tile.Tile;
import blue.made.acreage.server.util.WorldPos;

class Chunk
{
	public final World world;
	private byte count = 0;
	
	private Tile[] tiles = new Tile[64];
	
	public Chunk(World world, int x, int y)
	{
		this.world = world;
	}
	
	private int clip(int x)
	{
		return (x % 8 + 8) % 8;
	}
	
	private int id(int x, int y)
	{
		return (this.clip(y) << 3) | this.clip(x);
	}
	
	public void setTile(WorldPos pos, Tile tile)
	{
		int id = this.id(pos.x, pos.y);
		WorldGridObject old = this.tiles[id];
		WorldGridObject to = tile;
		if (old == null && to != null)
		{
			this.count++;
		}
		else if (old != null && to == null)
		{
			this.count--;
		}
		if (old != null)
		{
			old.pos = null;
		}
		if (to != null)
		{
			if (to.pos != null)
			{
				this.world.removeTile(to.pos);
			}
			to.pos = pos;
		}
		this.tiles[id] = tile;
	}
	
	public Tile getTile(WorldPos pos)
	{
		return this.tiles[this.id(pos.x, pos.y)];
	}
	
	public int count()
	{
		return this.count;
	}
}
