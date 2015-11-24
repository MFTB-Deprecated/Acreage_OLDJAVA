package blue.made.acreage.client.command;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.function.Consumer;

public class Commands
{
	public static HashMap<String, ICommandBuilder> builders = new HashMap<>();
	public static HashMap<String, Consumer<? extends Command>> runners = new HashMap<>();
	
	static
	{
		register(new CommandMove.Builder(), "move");
		register(new EmptyCommandBuilder("Exits the game"), (com) -> {
			System.exit(0);
		} , "exit");
		register(new EmptyCommandBuilder("Lists commands"), (com) -> {
			for (Entry<String, ICommandBuilder> e : builders.entrySet())
			{
				System.out.println(e.getValue().info(e.getKey()));
			}
		} , "help");
	}
	
	public static <C extends Command> void register(ICommandBuilder builder, Consumer<C> runner, String... names)
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
	
	public static void register(ICommandBuilder builder, String... names)
	{
		for (String name : names)
		{
			builders.put(name, builder);
		}
	}
}
