package blue.made.acreage.server.card.tile;

import blue.made.acreage.server.game.world.TileObject;

public class Tiles
{
	public static Tile feild = new Tile("field");
	
	public static Tile swamp = new Tile("swamp")
	{
		@Override
		public void onCreate(TileObject tile)
		{
			tile.extra.addProperty("crumble", true);
		}
	};
}
