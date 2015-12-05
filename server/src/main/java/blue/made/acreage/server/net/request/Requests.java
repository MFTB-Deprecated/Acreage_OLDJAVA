package blue.made.acreage.server.net.request;

import java.util.HashMap;

public class Requests
{
	public static HashMap<String, IRequestBuilder> builders = new HashMap<>();
	
	static
	{
		register("ping", (name, player, data) -> new RequestPing(name, player, data));
	}
	
	public static void register(String name, IRequestBuilder builder)
	{
		builders.put(name, builder);
	}
}
