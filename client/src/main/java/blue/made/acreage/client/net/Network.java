package blue.made.acreage.client.net;

import blue.made.acreage.client.net.action.IAction;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class Network
{
	class Adapter extends ChannelHandlerAdapter
	{
		@Override
		public void channelRead(ChannelHandlerContext ctx, Object msg)
		{
			if (msg != null)
			{
				Network.this.receiveAction((IAction) msg);
			}
		}
		
		@Override
		public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
		{
			cause.printStackTrace();
			ctx.close();
		}
	}
	
	protected void receiveAction(IAction action)
	{
	
	}
}
