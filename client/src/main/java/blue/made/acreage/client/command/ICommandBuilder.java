package blue.made.acreage.client.command;

public interface ICommandBuilder<C extends Command>
{
	public C parse(String name, String... args);
}
