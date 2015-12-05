package blue.made.acreage.server.world;

import blue.made.acreage.server.net.response.IResponse;
import io.netty.channel.Channel;

public class Player extends WorldGridObject
{
	private static int nextId = 0;
	public final int id;
	
	public Channel connection;
	
	public int coins = 0;
	
	public Player()
	{
		this.id = nextId++;
	}
	
	@Override
	public int hashCode()
	{
		return this.id;
	}
	
	public void sendResponse(IResponse res)
	{
		this.connection.writeAndFlush(res);
	}
}
