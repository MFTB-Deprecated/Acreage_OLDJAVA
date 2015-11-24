package blue.made.acreage.client.command;

public interface ICommandBuilder
{
	public Command parse(String name, String... args);
	
	public default String info(String name)
	{
		return name + "\n\t(No usage provided)";
	}
	
	public default String parseError(String name, String[] args, Exception error)
	{
		return String.format("Parse Error: %s%nUsage:%n%s", error.toString(), this.info(name));
	}
}
