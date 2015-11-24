package blue.made.acreage.client.net.request;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public class Request implements IRequest
{
	public final String name;
	public DataOutputStream data;
	private ByteArrayOutputStream bytes;
	
	public Request(String name)
	{
		this.name = name;
		this.bytes = new ByteArrayOutputStream();
		this.data = new DataOutputStream(this.bytes);
	}
	
	@Override
	public String name()
	{
		return this.name;
	}
	
	@Override
	public ByteBuf data()
	{
		return Unpooled.wrappedBuffer(this.bytes.toByteArray());
	}
}
