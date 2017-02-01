package main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 *
 * @author Brad Olah
 */

public class DataHandler
{
	RandomAccessFile file;


	/**
	 * Constructor that makes a DataHandler for the given file.
	 * @param fileName
	 */
	public DataHandler(String fileName)
	{
		openFile(fileName);
	}

	/**
	 * Opens the given file for reading and writing.
	 * @param fileName
	 */
	public void openFile(String fileName)
	{
		try
		{
			file = new RandomAccessFile(fileName,"rw");
		} catch (FileNotFoundException e)
		{
			System.out.println("File not found.");
			e.printStackTrace();
		}
	}

	/**
	 * Closes the currently open file.
	 */
	public void closeFile()
	{
		try
		{
			file.close();
		} catch (IOException e)
		{
			System.out.println("File not found.");
			e.printStackTrace();
		}
	}

	/**
	 * Adds a record to the file.
	 * @param name
	 * @param time
	 * @param age
	 */
	public void addItem(String name, String time, int age)
	{
		Item item = new Item(name,time,age);
		try
		{
			file.seek(file.length());
			file.writeBytes(""+item.name+"\t"+item.time+"\t"+item.age+"\n");
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void printItem(int targetEntry)
	{
		Item item = getItem(targetEntry);
		System.out.println(item.name+"\t"+item.time+"\t"+item.age);
	}

	/**
	 * Get the string that represents an item from the database.
	 * @param targetEntry
	 * @return
	 */
	public Item getItem(int targetEntry)
	{
		try
		{
			file.seek(0);
			int currentEntry = 0;
			String entry = null;
			while(currentEntry < targetEntry)
			{
				entry = file.readLine();
				currentEntry++;
			}
			return buildItem(entry);


		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Returns an item based on the given entry string.
	 * @param entry
	 * @return
	 */
	private Item buildItem(String entry)
	{
		Item item;
		String name;
		String time;
		int age;
		String[] splitEntry = entry.split("\t");

		name = splitEntry[0];
		time = splitEntry[1];
		age = Integer.parseInt(splitEntry[2].trim());

		item = new Item(name,time,age);

		return item;
	}

	public void modifyName(int entryNum)
	{

	}
}
