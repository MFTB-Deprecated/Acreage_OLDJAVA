package blue.made.acreage.client.command;

import java.util.HashMap;
import java.util.function.Consumer;

import blue.made.acreage.client.util.EnumDir;

public class Commands
{
	public static HashMap<String, ICommandBuilder<? extends Command>> builders = new HashMap<>();
	public static HashMap<String, Consumer<? extends Command>> runners = new HashMap<>();
	
	static
	{
		register((ICommandBuilder<CommandMove>) (name, args) -> {
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
		} , "move");
	}
	
	public static <C extends Command> void register(ICommandBuilder<C> builder, Consumer<C> runner, String... names)
	{
		register(runner, names);
		register(builder, names);
	}
	
	public static <C extends Command> void register(Consumer<C> runner, String... names)
	{
		for (String name : names)
		{
			runners.put(name, runner);
		}
	}
	
	public static <C extends Command> void register(ICommandBuilder<C> builder, String... names)
	{
		for (String name : names)
		{
			builders.put(name, builder);
		}
	}
}
