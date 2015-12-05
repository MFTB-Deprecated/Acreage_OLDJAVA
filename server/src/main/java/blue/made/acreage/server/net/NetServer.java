package blue.made.acreage.server.net;

import blue.made.acreage.server.world.Player;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.AttributeKey;

public class NetServer
{
	public static AttributeKey<Player> playerKey = AttributeKey.newInstance("player");
	public static final int PORT = 5929;
	
	private EventLoopGroup bossGroup = new NioEventLoopGroup(1);
	private EventLoopGroup workerGroup = new NioEventLoopGroup();
	
	public void start()
	{
		try
		{
			ServerBootstrap b = new ServerBootstrap();
			b.group(this.bossGroup, this.workerGroup).channel(NioServerSocketChannel.class).handler(new LoggingHandler(LogLevel.INFO)).childHandler(new NetServerInitializer());
			
			b.bind(PORT).sync().channel().closeFuture().sync();
		}
		catch (Exception e)
		{
			System.exit(0);
		}
	}
	
	public void stop()
	{
		this.bossGroup.shutdownGracefully();
		this.workerGroup.shutdownGracefully();
	}
}
