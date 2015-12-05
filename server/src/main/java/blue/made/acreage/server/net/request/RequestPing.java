package blue.made.acreage.server.net.request;

import blue.made.acreage.server.net.response.IResponse;
import blue.made.acreage.server.net.response.ResponseRaw;
import blue.made.acreage.server.world.Player;
import io.netty.buffer.ByteBuf;

public class RequestPing extends Request
{
	private ByteBuf data;
	
	public RequestPing(String name, Player player, ByteBuf data)
	{
		super(name, player);
		this.data = data;
	}
	
	@Override
	public void run()
	{
		IResponse res = new ResponseRaw("ping", this.data);
		this.from.sendResponse(res);
	}
}