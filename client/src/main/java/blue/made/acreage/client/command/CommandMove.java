package blue.made.acreage.client.command;

import blue.made.acreage.client.util.EnumDir;

public class CommandMove extends Command
{
	public EnumDir direction;
	
	public CommandMove(String name, EnumDir direction)
	{
		super(name);
		this.direction = direction;
	}
	
	@Override
	public void run()
	{
		// TODO
	}
}
