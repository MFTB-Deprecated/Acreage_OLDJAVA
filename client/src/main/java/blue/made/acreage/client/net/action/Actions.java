package blue.made.acreage.client.net.action;

import java.util.HashMap;

public class Actions
{
	public static HashMap<String, IActionBuilder> builders = new HashMap<>();
	
	static
	{
	
	}
	
	public static void register(String name, IActionBuilder builder)
	{
		builders.put(name, builder);
	}
}
