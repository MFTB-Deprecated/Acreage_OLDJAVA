package blue.made.acreage.client.command;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JsonCommand extends Command
{
	public JsonElement data;
	
	public JsonCommand(String name, JsonElement data)
	{
		super(name);
		this.data = data;
	}
	
	public JsonCommand(String name)
	{
		this(name, new JsonObject());
	}
	
	public JsonCommand(String name, String json)
	{
		this(name, (new JsonParser()).parse(json));
	}
}
