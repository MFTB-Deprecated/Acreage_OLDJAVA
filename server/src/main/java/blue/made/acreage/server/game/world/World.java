package blue.made.acreage.server.game.world;

import java.util.HashMap;

import blue.made.acreage.server.game.world.gen.WorldGen;

public class World
{
	private HashMap<Long, Chunk> chunks = new HashMap<>();
	
	public WorldGen gen;
	
	private long getChunkId(int x, int y)
	{
		int chunkx = x / 8;
		int chunky = y / 8;
		return ((long) chunkx << 32) | (chunky & 0xFFFFFFFL);
	}
	
	public void setTile(TileObject tile)
	{
		if (tile == null || tile.getTile() == null)
		{
			this.removeTile(tile.x, tile.y);
			return;
		}
		long chunkid = this.getChunkId(tile.x, tile.y);
		Chunk chunk = this.chunks.get(chunkid);
		if (chunk == null)
		{
			chunk = new Chunk(this, tile.x / 8, tile.y / 8);
			this.chunks.put(chunkid, chunk);
		}
		chunk.setTile(tile);
	}
	
	public TileObject getTile(int x, int y)
	{
		long chunkid = this.getChunkId(x, y);
		Chunk chunk = this.chunks.get(chunkid);
		if (chunk == null)
		{
			return null;
		}
		return chunk.getTile(x, y);
	}
	
	public void removeTile(int x, int y)
	{
		long chunkid = this.getChunkId(x, y);
		Chunk chunk = this.chunks.get(chunkid);
		if (chunk != null)
		{
			chunk.removeTile(x, y);
		}
		if (chunk.count() == 0)
		{
			this.chunks.remove(chunkid);
		}
	}
}
