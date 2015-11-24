package blue.made.acreage.client.net.request;

import io.netty.buffer.ByteBuf;

public interface IRequest
{
	public String name();
	
	public ByteBuf data();
}
