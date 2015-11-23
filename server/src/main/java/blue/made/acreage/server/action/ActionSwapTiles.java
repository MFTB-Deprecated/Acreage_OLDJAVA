package blue.made.acreage.server.action;

import java.io.IOException;

import blue.made.acreage.server.game.Game;
import blue.made.acreage.server.game.world.TileObject;
import blue.made.acreage.server.net.ServerPacket;

public class ActionSwapTiles extends Action
{
	private TileObject a;
	private TileObject b;
	
	public ActionSwapTiles(TileObject a, TileObject b)
	{
		this.a = a;
		this.b = b;
	}
	
	@Override
	protected void evaluate(Game game)
	{
		TileObject newb = this.a.at(this.b.x, this.b.y);
		TileObject newa = this.b.at(this.a.x, this.a.y);
		game.world.setTile(newa);
		game.world.setTile(newb);
	}
	
	@Override
	protected void send(Game game) throws IOException
	{
		ServerPacket out = ServerPacket.create("swap_tiles");
		out.write(this.a.x);
		out.write(this.a.y);
		out.write(this.b.x);
		out.write(this.b.y);
		
		game.sendToAllPlayers(out);
	}
}
