package blue.made.acreage.server.action;

import java.io.IOException;

import blue.made.acreage.server.game.Game;
import blue.made.acreage.server.game.world.TileObject;
import blue.made.acreage.server.net.ServerPacket;

public class ActionRemoveTile extends Action
{
	private int x;
	private int y;
	
	public ActionRemoveTile(TileObject tile)
	{
		this.x = tile.x;
		this.y = tile.y;
	}
	
	@Override
	protected void evaluate(Game game)
	{
		game.world.removeTile(this.x, this.y);
	}
	
	@Override
	protected void send(Game game) throws IOException
	{
		ServerPacket out = ServerPacket.create("remove_tile");
		out.write(this.x);
		out.write(this.y);
		
		game.sendToAllPlayers(out);
	}
}
