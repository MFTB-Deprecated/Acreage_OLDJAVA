package blue.made.acreage.server.action;

import java.io.IOException;

import blue.made.acreage.server.game.Game;
import blue.made.acreage.server.game.world.TileObject;
import blue.made.acreage.server.net.ServerPacket;

public class ActionSetTile extends Action
{
	private TileObject tile;
	
	public ActionSetTile(TileObject tile)
	{
		this.tile = tile;
	}
	
	@Override
	protected void evaluate(Game game)
	{
		game.world.setTile(this.tile);
	}
	
	@Override
	protected void send(Game game) throws IOException
	{
		ServerPacket out = ServerPacket.create("set_tile");
		out.write(this.tile.x);
		out.write(this.tile.y);
		
		game.sendToAllPlayers(out);
	}
}
