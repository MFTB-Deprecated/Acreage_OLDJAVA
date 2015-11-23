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
	
	/** A utility form of {@link #setTile(WorldPos, Tile) */
	public void setTile(int x, int y, Tile tile)
	{
		this.setTile(new WorldPos(x, y), tile);
	}
	
	/**
	 * Sets a tile in the grid. This method guarantees that no two tiles can
	 * have the same position at the same time, if a tile already exists at the
	 * given position, then that tile is replaced and its position is set to
	 * null. These changes are seen instantly through {@link Tile#pos()
	 * Tile.pos()} and {@link #getTile(WorldPos)}. Equivalent to calling
	 * {@link Tile#setPos(WorldPos) tile.setPos(pos)}.
	 * 
	 * <pre>
	 * <code>
	 * Tile a;
	 * Tile b;
	 * a.pos(); // null
	 * world.setTile(0, 0, a);
	 * a.pos(); // {0, 0}
	 * world.setTile(0, 0, b);
	 * a.pos(); // null
	 * b.pos(); // {0, 0}
	 * world.getTile(0, 0) == b // true
	 * </code>
	 * </pre>
	 * 
	 * @param pos
	 *            The grid location that should be changed
	 * @param tile
	 *            The tile that should be placed at that location
	 */
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
	
	/** A utility form of {@link #getTile(WorldPos) */
	public Tile getTile(int x, int y)
	{
		return this.getTile(new WorldPos(x, y));
	}
	
	/**
	 * Gets the tile at the given location.
	 * 
	 * <pre>
	 * <code>
	 * at.equals(world.getTile(at).pos()) // true
	 * </code>
	 * </pre>
	 * 
	 * @param pos
	 *            The grid position to search
	 * @return The tile at pos, or null if pos is empty
	 */
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
	
	/** A utility form of {@link #removeTile(WorldPos) */
	public void removeTile(int x, int y)
	{
		this.removeTile(new WorldPos(x, y));
	}
	
	/**
	 * Removes the tile at the given position. Also removes the chunk at the
	 * given location if it is empty.
	 */
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
