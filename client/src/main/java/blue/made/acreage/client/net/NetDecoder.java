package blue.made.acreage.client.net;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

import blue.made.acreage.client.net.response.IResponseBuilder;
import blue.made.acreage.client.net.response.Responses;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

public class NetDecoder extends ReplayingDecoder<Void>
{
	public static Charset charset = Charset.forName("US-ASCII");
	
	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception
	{
		int size = in.readInt();
		int nameSize = in.readByte() & 0xFF;
		size--;
		String name = in.readBytes(nameSize).toString(charset);
		size -= nameSize;
		ByteBuf data = in.readBytes(size);
		IResponseBuilder build = Responses.builders.get(name);
		if (build == null)
		{
			throw new IOException(String.format("Unknown Response: %s", name));
		}
		out.add(build.decode(name, data));
	}
}
