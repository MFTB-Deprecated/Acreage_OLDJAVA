package blue.made.acreage.server.util;

import java.util.function.Predicate;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class JsonFilter
{
	public static JsonElement filter(JsonElement in)
	{
		return filter(in, s -> {
			return !s.startsWith("_");
		});
	}
	
	public static JsonElement filter(JsonElement in, Predicate<String> filter)
	{
		if (in.isJsonObject())
		{
			JsonObject out = new JsonObject();
			JsonObject ob = in.getAsJsonObject();
			ob.entrySet().forEach(e -> {
				if (filter.test(e.getKey()))
				{
					out.add(e.getKey(), filter(e.getValue(), filter));
				}
			});
			return ob;
		}
		else if (in.isJsonArray())
		{
			JsonArray out = new JsonArray();
			JsonArray ar = in.getAsJsonArray();
			ar.forEach(e -> {
				out.add(filter(e, filter));
			});
			return out;
		}
		else
		{
			return in;
		}
	}
}
