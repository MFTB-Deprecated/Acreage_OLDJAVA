package blue.made.acreage.server.world;

import java.util.HashMap;

import blue.made.acreage.server.tile.Tile;
import blue.made.acreage.server.util.WorldPos;

public class World
{
	private HashMap<Long, Chunk> chunks = new HashMap<>();
	
	public WorldGen gen;
	
	private long getChunkId(WorldPos pos)
	{
		int chunkx = pos.x / 8;
		int chunky = pos.y / 8;
		return ((long) chunkx << 32) | (chunky & 0xFFFFFFFL);
	}
	
	public void setTile(int x, int y, Tile tile)
	{
		this.setTile(new WorldPos(x, y), tile);
	}
	
	public void setTile(WorldPos pos, Tile tile)
	{
		long chunkid = this.getChunkId(pos);
		Chunk chunk = this.chunks.get(chunkid);
		if (chunk == null)
		{
			chunk = new Chunk(this, pos.x / 8, pos.y / 8);
			this.chunks.put(chunkid, chunk);
		}
		chunk.setTile(pos, tile);
	}
	
	public Tile getTile(int x, int y)
	{
		return this.getTile(new WorldPos(x, y));
	}
	
	public Tile getTile(WorldPos pos)
	{
		long chunkid = this.getChunkId(pos);
		Chunk chunk = this.chunks.get(chunkid);
		if (chunk == null)
		{
			return null;
		}
		return chunk.getTile(pos);
	}
	
	public void removeTile(int x, int y)
	{
		this.removeTile(new WorldPos(x, y));
	}
	
	public void removeTile(WorldPos pos)
	{
		long chunkid = this.getChunkId(pos);
		Chunk chunk = this.chunks.get(chunkid);
		if (chunk != null)
		{
			chunk.setTile(pos, null);
		}
		if (chunk.count() == 0)
		{
			this.chunks.remove(chunkid);
		}
	}
}
