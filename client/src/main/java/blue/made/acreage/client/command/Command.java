package blue.made.acreage.client.command;

import java.util.function.Consumer;

public abstract class Command
{
	public final String name;
	
	public Command(String name)
	{
		this.name = name;
	}
	
	@SuppressWarnings("unchecked")
	public void run()
	{
		Consumer<Command> run = (Consumer<Command>) Commands.runners.get(this.name);
		if (run != null)
		{
			run.accept(this);
		}
		throw new IllegalArgumentException("No runner found for command \"" + this.name + "\"");
	}
}
