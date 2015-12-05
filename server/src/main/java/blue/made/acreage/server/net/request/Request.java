package blue.made.acreage.server.net.request;

import blue.made.acreage.server.world.Player;

public abstract class Request
{
	public final Player from;
	public final String name;
	
	public Request(String name, Player player)
	{
		this.name = name;
		this.from = player;
	}
	
	public abstract void run();
}
