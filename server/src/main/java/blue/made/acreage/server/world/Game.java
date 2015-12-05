package blue.made.acreage.server.world;

public class Game
{
	public static Game instance;
	
	public Game()
	{
		if (instance == null)
		{
			instance = this;
		}
		else
		{
			throw new IllegalStateException();
		}
	}
	
	public World world = new World();
}
