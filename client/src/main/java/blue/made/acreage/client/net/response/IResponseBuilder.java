package blue.made.acreage.client.net.response;

import io.netty.buffer.ByteBuf;

public interface IResponseBuilder
{
	public IResponse decode(String name, ByteBuf data);
}
