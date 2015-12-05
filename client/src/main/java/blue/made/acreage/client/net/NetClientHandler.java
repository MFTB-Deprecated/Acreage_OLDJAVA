package blue.made.acreage.client.net;

import blue.made.acreage.client.net.action.IAction;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class NetClientHandler extends SimpleChannelInboundHandler<IAction>
{
	@Override
	protected void messageReceived(ChannelHandlerContext ctx, IAction msg) throws Exception
	{
		NetClient.recieve(msg);
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
	{
		cause.printStackTrace();
		ctx.close();
	}
}
