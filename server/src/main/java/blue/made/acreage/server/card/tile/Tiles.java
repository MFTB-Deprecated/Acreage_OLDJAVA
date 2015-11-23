package blue.made.acreage.server.card.tile;

import java.util.HashMap;

import blue.made.acreage.server.tile.Tile;
import blue.made.acreage.server.util.RandomSelector;
import blue.made.acreage.server.world.World;

public class Tiles
{
	public static final RandomSelector<TileType> spawn = new RandomSelector<>();
	public static final HashMap<String, TileType> types = new HashMap<>();
	
	public static void register(TileType type, float spawnWeight)
	{
		types.put(type.name, type);
		spawn.add(type, spawnWeight);
	}
	
	// Tile Types
	public static TileType field = new TileType("field");
	public static TileType swamp = new TileType("swamp")
	{
		@Override
		public Tile create(World world)
		{
			Tile out = super.create(world);
			out.crumble = true;
			return out;
		}
	};
	
	// Register
	static
	{
		register(field, 100);
		register(swamp, 20);
	}
}
