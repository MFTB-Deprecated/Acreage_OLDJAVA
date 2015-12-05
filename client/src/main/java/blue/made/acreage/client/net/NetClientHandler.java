package blue.made.acreage.client.net;

import blue.made.acreage.client.net.response.IResponse;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class NetClientHandler extends SimpleChannelInboundHandler<IResponse>
{
	@Override
	protected void messageReceived(ChannelHandlerContext ctx, IResponse msg) throws Exception
	{
		msg.run();
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
	{
		cause.printStackTrace();
		ctx.close();
	}
}
