package blue.made.acreage.client.net.action;

import io.netty.buffer.ByteBuf;

public interface IActionBuilder
{
	public IAction decode(String name, ByteBuf data);
}
