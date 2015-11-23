
package blue.made.acreage.server.net;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import com.google.gson.JsonElement;

public class ServerPacket extends DataOutputStream
{
	protected ByteArrayOutputStream bytes;
	public final String id;
	
	private ServerPacket(String id, ByteArrayOutputStream stream)
	{
		super(stream);
		this.id = id;
		this.bytes = stream;
	}
	
	public void writeJsonChars(JsonElement json) throws IOException
	{
		this.writeChars(json.getAsString());
	}
	
	public void writeJson(JsonElement json) throws IOException
	{
		this.writeUTF(json.getAsString());
	}
	
	public static ServerPacket create(String id)
	{
		return new ServerPacket(id, new ByteArrayOutputStream());
	}
}
