package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;

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
			file.writeChars(item.name+" "+item.time+" "+item.age+"\n");


		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Item getItem(int entryNum)
	{
		return null;

	}

	public void modifyName(int entryNum)
	{

	}
}
