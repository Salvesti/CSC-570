package main;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Brad Olah
 */

public class Item
{
	String name;
	String time;
	String age;

	public Item(String name, String time, int age)
	{
		this.name = formatName(name);
		this.time = formatTime(time);
		this.age = formatAge(age);
	}

	/**
	 * Cleans up name input to make sure it is
	 * at most 10 characters long.
	 * @param name
	 * @return
	 */
	private String formatName(String name)
	{
		if (name.length() > 10)
		{
			System.out.println("Name too long. Shortened to 10 characters.");
			name = name.substring(0, 9);
		}else if(name.length()<10)
		{
			System.out.println("Filling space to 10 characters.");
			while(name.length()<10)
			{
				name = name+" ";
			}
		}
		return name;
	}


	/**
	 * Cleans up time input to make sure it is
	 * formatted ##:##.
	 * @param time
	 * @return
	 */
	private String formatTime(String time)
	{
		Pattern format = Pattern.compile("\\d\\d:\\d\\d");
		Matcher matcher = format.matcher(time);
		Boolean hasMatched = matcher.matches();
		if(hasMatched)
		{
			return time;
		}else
		{
			return "00:00";
		}
	}

	/**
	 * Cleans up age input to make sure it is
	 * 3 numerical characters.
	 * @param age
	 * @return
	 */
	private String formatAge(int age)
	{
		// TODO Auto-generated method stub
		if(age>999)
		{
			System.out.println("Age too high. Must be under 999");
			age = 999;
			return ""+age;
		}else if(age>99)
		{
			return age+" ";
		}else if(age>9)
		{
			return age+"  ";
		}else if(age>0)
		{
			return 0+"  ";
		}
		return "000";
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
	public String getAge()
	{
		return age;
	}
	public void setAge(int age)
	{
		this.age = formatAge(age);
	}


}
