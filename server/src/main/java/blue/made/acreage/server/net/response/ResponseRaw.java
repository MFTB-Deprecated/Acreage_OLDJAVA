package blue.made.acreage.server.net.response;

import blue.made.acreage.server.world.Player;
import io.netty.buffer.ByteBuf;

public class ResponseRaw implements IResponse
{
	public final String name;
	public final ByteBuf data;
	
	public ResponseRaw(String name, ByteBuf data)
	{
		this.name = name;
		this.data = data;
	}
	
	@Override
	public String name()
	{
		return this.name;
	}
	
	@Override
	public ByteBuf data(Player player)
	{
		return this.data;
	}
}
