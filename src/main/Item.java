package main;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A class that handles the logic for an item.
 * Takes care of input clean up.
 * @author Brad Olah
 */

public class Item
{
	//Stores the name of the item entry.
	String name;
	//Stores the time of the item entry.
	String time;
	//Stores the age of the item entry.
	String age;

	/**
	 * A creator to make a new item.
	 * @param name
	 * @param time
	 * @param age
	 */
	public Item(String name, String time, int age)
	{
		setName(name);
		setTime(time);
		setAge(age);
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
		//Uses a Regular Expression to check if the time is in the correct format.
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
		
		if(age>999)
		{
			System.out.println("Age too high. Must be under 999");
			age = 999;
			return ""+age;
			
		}else if( age<=999)
		{
			return ""+age;
				
		}else if(age<=99)
		{
			return age+" ";
			
		}else if(age<=9)
		{
			return age+"  ";
			
		}else if(age<=0)
		{
			return 0+"  ";
			
		}
		return "000";
	}

	
	/**
	 * Returns currently stored name.
	 * @return
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * Sets the stored name.
	 * @param name
	 */
	public void setName(String name)
	{
		this.name = formatName(name);
	}
	
	/**
	 * Returns currently stored name.
	 * @return
	 */
	public String getTime() 
	{
		return time;
	}
	
	/**
	 * Sets the stored time.
	 * @param time
	 */
	public void setTime(String time)
	{
		this.time = formatTime(time);
	}
	
	/**
	 * Returns the currently stored age.
	 * @return
	 */
	public String getAge()
	{
		return age;
	}
	
	/**
	 * Sets the stored age.
	 * @param age
	 */
	public void setAge(int age)
	{
		this.age = formatAge(age);
	}


}
