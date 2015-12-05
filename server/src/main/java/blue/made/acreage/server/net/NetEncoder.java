package blue.made.acreage.server.net;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.List;

import blue.made.acreage.server.net.response.IResponse;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

public class NetEncoder extends MessageToMessageEncoder<IResponse>
{
	public static Charset charset = Charset.forName("US-ASCII");
	
	@Override
	protected void encode(ChannelHandlerContext ctx, IResponse msg, List<Object> out) throws Exception
	{
		int l = 1;
		ByteBuffer name = charset.encode(msg.name());
		l += name.capacity();
		ByteBuf dat = msg.data(ctx.attr(NetServer.playerKey).get());
		l += dat.capacity();
		ByteBuf pak = Unpooled.buffer(l + 4);
		pak.writeInt(l);
		pak.writeByte(name.capacity());
		pak.writeBytes(name);
		pak.writeBytes(dat);
		out.add(pak);
	}
}
