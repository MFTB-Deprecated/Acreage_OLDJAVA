package blue.made.acreage.server.util;

public enum EnumDir
{
	UP(0, 1), DOWN(0, -1), RIGHT(1, 0), LEFT(-1, 0);
	
	public final int dx;
	public final int dy;
	
	EnumDir(int dx, int dy)
	{
		this.dx = dx;
		this.dy = dy;
	}
}
