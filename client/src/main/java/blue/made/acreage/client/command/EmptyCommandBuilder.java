package blue.made.acreage.client.command;

public class EmptyCommandBuilder implements ICommandBuilder
{
	String usage;
	
	public EmptyCommandBuilder(String usage)
	{
		this.usage = usage;
	}
	
	public EmptyCommandBuilder()
	{
	}
	
	@Override
	public Command parse(String name, String... args)
	{
		return new Command(name) {};
	}
	
	@Override
	public String info(String name)
	{
		if (this.usage == null)
		{
			return name;
		}
		return name + "\n\t" + this.usage.replaceAll("(\\n)(\\t*.+)", "$1\t$2");
	}
}
