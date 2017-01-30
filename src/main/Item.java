package main;

/**
 *
 * @author Brad Olah
 */

public class Item
{
	String name;
	String time;
	int age;

	public Item(String name, String time, int age)
	{
		this.name = formatName(name);
		this.time = formatTime(time);
		this.age = formatAge(age);
	}

	/**
	 * Cleans up name input to make sure it is
	 * at most 10 characters long.
	 * @param name2
	 * @return
	 */
	private String formatName(String name2)
	{
		// TODO Auto-generated method stub
		return null;
	}


	/**
	 * Cleans up time input to make sure it is
	 * formatted ##:##.
	 * @param time2
	 * @return
	 */
	private String formatTime(String time2)
	{
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Cleans up age input to make sure it is
	 * 3 numerical characters.
	 * @param age
	 * @return
	 */
	private int formatAge(int age)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = formatName(name);
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time)
	{
		this.time = formatTime(time);
	}
	public int getAge()
	{
		return age;
	}
	public void setAge(int age)
	{
		this.age = formatAge(age);
	}


}
