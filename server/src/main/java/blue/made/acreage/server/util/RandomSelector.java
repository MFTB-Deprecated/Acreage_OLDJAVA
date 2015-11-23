package blue.made.acreage.server.util;

import java.util.ArrayList;
import java.util.Random;

public class RandomSelector<T>
{
	private class Option
	{
		public T object;
		public float weight;
		/**
		 * The total weight of the options to the left of this one in the list
		 */
		public float left;
		
		public Option(T object, float weight, float left)
		{
			this.object = object;
			this.weight = weight;
			this.left = left;
		}
	}
	
	private ArrayList<Option> list = new ArrayList<Option>();
	/** The total weight of all the options in the {@link #list list} */
	private float total = 0;
	
	/** Add another weighted option to select */
	public void add(T object, float weight)
	{
		this.list.add(new Option(object, this.total, weight));
		this.total += weight;
	}
	
	public T pick()
	{
		return this.pick(new Random());
	}
	
	public T pick(Random rand)
	{
		return this.pick(rand.nextFloat() * this.total);
	}
	
	/**
	 * Picks a random option with weights using a binary search algorithm
	 * 
	 * @param randn
	 *            A float between 0 and {@link #total total}
	 */
	private T pick(float randn)
	{
		int max = this.list.size() - 1;
		int min = 0;
		while (min <= max)
		{
			int mid = min + (max - min) / 2;
			Option op = this.list.get(mid);
			if (op.left < randn)
			{
				max = mid - 1;
				continue;
			}
			if (op.left + op.weight > randn)
			{
				min = mid + 1;
				continue;
			}
			return op.object;
		}
		return null;
	}
}
