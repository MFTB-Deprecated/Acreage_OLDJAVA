package blue.made.acreage.client.net.response;

import java.nio.charset.Charset;
import java.util.HashMap;

public class Responses
{
	public static HashMap<String, IResponseBuilder> builders = new HashMap<>();
	
	static
	{
		register("ping", (name, data) -> () -> {
			long time = System.currentTimeMillis() - data.readLong();
			if (data.isReadable())
			{
				String text = data.toString(Charset.defaultCharset());
				System.out.printf("Ping - %dms - %s%n", time, text);
			}
			else
			{
				System.out.printf("Ping - %dms%n", time);
			}
		});
	}
	
	public static void register(String name, IResponseBuilder builder)
	{
		builders.put(name, builder);
	}
}
