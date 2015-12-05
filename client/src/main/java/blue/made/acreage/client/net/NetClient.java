package blue.made.acreage.client.net;

import java.util.LinkedList;

import blue.made.acreage.client.net.action.IAction;
import blue.made.acreage.client.net.request.IRequest;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NetClient
{
	private static class NetworkThread extends Thread
	{
		public boolean run = true;
		
		EventLoopGroup group = new NioEventLoopGroup();
		Channel ch;
		
		public boolean begin()
		{
			try
			{
				Bootstrap b = new Bootstrap();
				b.group(this.group).channel(NioSocketChannel.class).handler(new NetClientInitializer());
				
				// Start the connection attempt.
				this.ch = b.connect(HOST, PORT).sync().channel();
				return true;
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			this.group.shutdownGracefully();
			return false;
		}
		
		@Override
		public void run()
		{
			try
			{
				// Read commands from the stdin.
				ChannelFuture lastWriteFuture = null;
				
				while (this.run)
				{
					LinkedList<IRequest> out = null;
					synchronized (NetClient.lock)
					{
						if (!NetClient.out.isEmpty())
						{
							out = NetClient.out;
							NetClient.out = new LinkedList<>();
						}
					}
					
					if (out != null)
					{
						for (IRequest req : out)
						{
							lastWriteFuture = this.ch.writeAndFlush(req);
						}
					}
					
					try
					{
						this.wait(10);
					}
					catch (Exception e)
					{
						e.printStackTrace();
					}
				}
				
				// Wait until all messages are flushed before closing the
				// channel.
				if (lastWriteFuture != null)
				{
					lastWriteFuture.sync();
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			finally
			{
				// The connection is closed automatically on shutdown.
				synchronized (NetClient.lock)
				{
					this.group.shutdownGracefully();
					NetClient.net = null;
				}
			}
		}
	}
	
	public static final String HOST = "127.0.0.1";
	public static final int PORT = 5929;
	
	private static Object lock = new Object();
	private static LinkedList<IRequest> out = new LinkedList<>();
	private static NetworkThread net;
	
	public static void run()
	{
		synchronized (lock)
		{
			if (net == null)
			{
				net = new NetworkThread();
				if (net.begin())
				{
					net.run();
				}
				else
				{
					net = null;
				}
			}
		}
	}
	
	public static boolean isRunning()
	{
		synchronized (lock)
		{
			return net != null;
		}
	}
	
	public static void stop()
	{
		synchronized (lock)
		{
			if (net != null)
			{
				net.run = false;
			}
		}
	}
	
	public static void send(IRequest request)
	{
		synchronized (lock)
		{
			if (net != null)
			{
				out.add(request);
			}
			else
			{
				throw new IllegalStateException("The network has not been started");
			}
		}
	}
	
	static void recieve(IAction action)
	{
		synchronized (lock)
		{
			if (net != null)
			{
				System.out.printf("Msg received: %s%n", action);
				// TODO
			}
			else
			{
				throw new IllegalStateException("The network has not been started");
			}
		}
	}
}
