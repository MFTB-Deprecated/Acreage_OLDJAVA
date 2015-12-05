package blue.made.acreage.client.net.request;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

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
	
	public void addData(ByteBuf data)
	{
		try
		{
			this.data.write(data.array());
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	@Override
	public ByteBuf data()
	{
		return Unpooled.wrappedBuffer(this.bytes.toByteArray());
	}
}
