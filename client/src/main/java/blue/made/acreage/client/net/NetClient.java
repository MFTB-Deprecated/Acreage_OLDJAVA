package blue.made.acreage.client.net;

import blue.made.acreage.client.net.request.IRequest;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NetClient
{
	public static final String HOST = "127.0.0.1";
	public static final int PORT = 5929;
	
	public static EventLoopGroup group = new NioEventLoopGroup();
	public static Channel ch;
	
	public static void start()
	{
		try
		{
			Bootstrap b = new Bootstrap();
			b.group(group).channel(NioSocketChannel.class).handler(new NetClientInitializer());
			
			ch = b.connect(HOST, PORT).sync().channel();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	public static void stop()
	{
		group.shutdownGracefully();
	}
	
	public static void send(IRequest request)
	{
		ch.writeAndFlush(request);
	}
}
