package blue.made.acreage.server.net.response;

import blue.made.acreage.server.world.Player;
import io.netty.buffer.ByteBuf;

public interface IResponse
{
	public String name();
	
	public ByteBuf data(Player player);
}
