package blue.made.acreage.server.net;

import blue.made.acreage.server.net.request.Request;
import blue.made.acreage.server.world.Player;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class NetClientHandler extends SimpleChannelInboundHandler<Request>
{
	@Override
	public void channelActive(final ChannelHandlerContext ctx)
	{
		Player player = new Player();
		ctx.attr(NetServer.playerKey).set(player);
		player.connection = ctx.channel();
	}
	
	@Override
	protected void messageReceived(ChannelHandlerContext ctx, Request msg) throws Exception
	{
		System.out.println(msg);
		msg.run();
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
	{
		cause.printStackTrace();
		ctx.close();
	}
}
