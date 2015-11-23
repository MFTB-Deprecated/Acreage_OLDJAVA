package blue.made.acreage.server.game;

import blue.made.acreage.server.game.world.World;
import blue.made.acreage.server.net.ServerPacket;

public class Game
{
	private static Game instance;
	
	public static Game instance()
	{
		return instance;
	}
	
	public World world;
	
	public void sendToAllPlayers(ServerPacket packet)
	{
		// TODO
	}
}
