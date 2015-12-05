package blue.made.acreage.client.command;

import java.nio.charset.Charset;

import blue.made.acreage.client.net.NetClient;
import blue.made.acreage.client.net.request.Request;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public class CommandPing extends Command
{
	public static class Builder implements ICommandBuilder
	{
		@Override
		public Command parse(String name, String... args)
		{
			String data = String.join(" ", args);
			return new CommandPing(name, data);
		}
		
		@Override
		public String info(String name)
		{
			String out = name + " [data]";
			out += "\n\tPings the server";
			out += "\n\n\tdata\n\t\tAdditional text to include";
			return out;
		}
	}
	
	public ByteBuf data;
	
	public CommandPing(String name, String data)
	{
		super(name);
		this.data = Unpooled.copiedBuffer(data, Charset.defaultCharset());
	}
	
	@Override
	public void run() throws Exception
	{
		Request req = new Request("ping");
		req.data.writeLong(System.currentTimeMillis());
		req.addData(this.data);
		NetClient.send(req);
	}
}
