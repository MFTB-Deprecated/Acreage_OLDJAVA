package blue.made.acreage.client.command;

import blue.made.acreage.client.util.EnumDir;

public class CommandMove extends Command
{
	public static class Builder implements ICommandBuilder
	{
		@Override
		public Command parse(String name, String... args)
		{
			String dirs = args[0].toUpperCase();
			switch (dirs)
			{
				case "Y+":
				case "UP":
				case "U":
					return new CommandMove(name, EnumDir.UP);
				case "Y-":
				case "DOWN":
				case "D":
					return new CommandMove(name, EnumDir.DOWN);
				case "X+":
				case "RIGHT":
				case "R":
					return new CommandMove(name, EnumDir.RIGHT);
				case "X-":
				case "LEFT":
				case "L":
					return new CommandMove(name, EnumDir.LEFT);
				default:
					return null;
			}
		}
		
		@Override
		public String info(String name)
		{
			String out = name + " <dir>";
			out += "\n\tMoves the player";
			out += "\n\n\tdir can be:";
			out += "\n\t* Y+|UP|U";
			out += "\n\t* Y-|DOWN|D";
			out += "\n\t* X+|RIGHT|R";
			out += "\n\t* X-|LEFT|L";
			return out;
		}
	}
	
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
		System.out.printf("Moving %s...%n", this.direction);
	}
}
