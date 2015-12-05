package blue.made.acreage.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import blue.made.acreage.client.command.Command;
import blue.made.acreage.client.command.Commands;
import blue.made.acreage.client.command.ICommandBuilder;
import blue.made.acreage.client.net.NetClient;

public class Main
{
	public static void main(String[] args) throws IOException
	{
		System.out.println("Acreage Client Test");
		System.out.println("(type \"help\" for a list of commands)");
		System.out.printf("Connecting to [%s:%d]...%n", NetClient.HOST, NetClient.PORT);
		NetClient.run();
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run()
			{
				System.out.println("Stopping...");
				NetClient.stop();
			}
		});
		if (NetClient.isRunning())
		{
			System.out.println("Connected");
		}
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		while (true)
		{
			String[] input = in.readLine().split(" ");
			String comname = input[0];
			String[] comargs = Arrays.copyOfRange(input, 1, input.length);
			ICommandBuilder builder = Commands.builders.get(comname);
			Command com = null;
			try
			{
				com = builder.parse(comname, comargs);
			}
			catch (Exception e)
			{
				System.out.println(builder.parseError(comname, comargs, e));
			}
			if (com != null)
			{
				com.run();
			}
		}
	}
}
