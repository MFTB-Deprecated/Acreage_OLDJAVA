package blue.made.acreage.server.action;

import java.io.IOException;

import blue.made.acreage.server.game.Game;
import blue.made.acreage.server.game.world.TileObject;
import blue.made.acreage.server.net.ServerPacket;
import blue.made.acreage.server.util.JsonFilter;

public class ActionRevealTile extends Action
{
	private TileObject tile;
	
	public ActionRevealTile(TileObject tile)
	{
		this.tile = tile;
	}
	
	@Override
	protected void evaluate(Game game)
	{
		this.tile.setExplored(true);
	}
	
	@Override
	protected void send(Game game) throws IOException
	{
		ServerPacket out = ServerPacket.create("reveal_tile");
		out.write(this.tile.x);
		out.write(this.tile.y);
		out.writeUTF(this.tile.getTile().id);
		out.writeBoolean(this.tile.isExplored());
		out.writeJsonChars(JsonFilter.filter(this.tile.extra));
		
		game.sendToAllPlayers(out);
	}
}
