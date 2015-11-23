package blue.made.acreage.server.action;

import java.io.IOException;

import blue.made.acreage.server.game.Game;

public abstract class Action
{
	public void run()
	{
		this.serverRun();
		this.clientRun();
	}
	
	public void serverRun()
	{
		// TODO
	}
	
	public void clientRun()
	{
		// TODO
	}
	
	protected abstract void evaluate(Game game);
	
	protected abstract void send(Game game) throws IOException;
	
	public static void clientStep()
	{
		// TODO
	}
	
	public static void serverStep()
	{
		// TODO
	}
	
	public static void step()
	{
		clientStep();
	}
}
