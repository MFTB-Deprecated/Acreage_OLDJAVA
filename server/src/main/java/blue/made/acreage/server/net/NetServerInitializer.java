package blue.made.acreage.server.net;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

public class NetServerInitializer extends ChannelInitializer<SocketChannel>
{
	@Override
	protected void initChannel(SocketChannel ch) throws Exception
	{
		ChannelPipeline pipeline = ch.pipeline();
		
		pipeline.addLast(new NetDecoder());
		pipeline.addLast(new NetEncoder());
		
		pipeline.addLast(new NetClientHandler());
	}
}
