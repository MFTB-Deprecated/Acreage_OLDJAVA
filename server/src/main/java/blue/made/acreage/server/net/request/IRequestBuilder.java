package blue.made.acreage.server.net.request;

import blue.made.acreage.server.world.Player;
import io.netty.buffer.ByteBuf;

public interface IRequestBuilder
{
	public Request decode(String name, Player player, ByteBuf data);
}
