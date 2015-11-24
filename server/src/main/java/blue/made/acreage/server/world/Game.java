package blue.made.acreage.server.world;

import blue.made.acreage.server.net.ServerPacket;

public class Game
{
	public static Game instance;
	
	public Game()
	{
		if (instance == null)
		{
			instance = this;
		}
		else
		{
			throw new IllegalStateException();
		}
	}
	
	public World world = new World();
	
	public void sendToAllPlayers(ServerPacket packet)
	{
		// TODO
	}
}
