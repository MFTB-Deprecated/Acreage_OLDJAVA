package blue.made.acreage.server.card.tile;

import blue.made.acreage.server.action.Action;
import blue.made.acreage.server.action.ActionSetTile;
import blue.made.acreage.server.card.ICard;
import blue.made.acreage.server.game.Player;
import blue.made.acreage.server.game.world.TileObject;

public class Tile implements ICard
{
	public final String id;
	
	public Tile(String id)
	{
		this.id = id;
	}
	
	public void onCreate(TileObject tile)
	{
	
	}
	
	public void onExplored(TileObject tile)
	{
	
	}
	
	public void onPlayerEnter(TileObject tile, Player player)
	{
		this.onExplored(tile);
	}
	
	public void onPlayerLeave(TileObject tile, Player player)
	{
	
	}
	
	public void afterPlayerLeave(TileObject tile, Player player)
	{
		if (tile.extra.has("crumble") && tile.extra.get("crumble").getAsBoolean())
		{
			TileObject replace = tile.world.gen.getNewTile(tile.x, tile.y);
			(new ActionSetTile(replace)).run();
			Action.step();
		}
	}
	
	public void onPlayerTurnStart(TileObject tile, Player player)
	{
	
	}
	
	public void onPlayerTurnEnd(TileObject tile, Player player)
	{
	
	}
}
