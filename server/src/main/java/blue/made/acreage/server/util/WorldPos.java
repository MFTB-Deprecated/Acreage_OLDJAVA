package blue.made.acreage.server.util;

public class WorldPos
{
	public final int x;
	public final int y;
	
	public WorldPos(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	@Override
	public String toString()
	{
		return String.format("{%d, %d}", this.x, this.y);
	}
	
	@Override
	public boolean equals(Object other)
	{
		if (other instanceof WorldPos)
		{
			WorldPos pos = (WorldPos) other;
			return pos.x == this.x && pos.y == this.y;
		}
		return false;
	}
}
