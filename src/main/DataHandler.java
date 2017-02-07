package main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

import com.sun.xml.internal.ws.util.StringUtils;

/**
 * A class that handles the data manipulation in of the storage file.
 * @author Brad Olah
 */

public class DataHandler
{
	//The file that is currently open.
	RandomAccessFile file;
	//The scanner that is currently open.
	Scanner s;


	/**
	 * Constructor that makes a DataHandler for the given file.
	 * @param fileName
	 */
	public DataHandler(String fileName)
	{
		openFile(fileName);
		init();
	}

	/**
	 * Initializes the DataHandler and runs the interface for
	 * working with the file this handler was created for.
	 */
	public void init()
	{
		boolean running = true;
		s = new Scanner(System.in);
		while(running)
		{
			System.out.println("What would you like to do?");
			System.out.println("1 - Add Record");
			System.out.println("2 - Lookup Record");
			System.out.println("3 - Modify Record");
			System.out.println("4 - Print All Records");
			System.out.println("5 - Close program");
			String input = s.nextLine();
			switch(Integer.parseInt(input.substring(0, 1)))
			{
			case 1:
				promptForEntryAdd();
				System.out.print("\n\n\n");
				break;
			case 2:
				promptForEntryLookup();
				System.out.print("\n\n\n");
				break;
			case 3:
				promptForEntryModify();
				System.out.print("\n\n\n");
				break;
			case 4:
				printAllRecords();
				System.out.print("\n\n\n");
				break;
			case 5:
				running = false;
				System.out.println("Exiting.");
				s.close();
				closeFile();
				System.exit(0);
			}
		}
	}
	
	/**
	 * Prints all the records.
	 */
	private void printAllRecords() 
	{
		try {
		file.seek(0);
		int currentEntry = 1;
		file.readLine();
		
			while(file.readLine() != null)
			{
				System.out.print("Entry "+currentEntry+": ");
				printItem(currentEntry);
				file.readLine();
				currentEntry++;
			}
			System.out.print("Entry "+currentEntry+": ");
			printItem(currentEntry);
		} catch (IOException e) 
		{

			e.printStackTrace();
		}
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
	 * Prompts the user for a record to add.
	 */
	public void promptForEntryAdd()
	{
		String name;
		String time;
		String age;
		System.out.print("Enter the name for the entry.\nNames must be 1-10 characters long\n");
		name = s.nextLine();
		System.out.print("Enter the time for the entry.\nTimes must be 5 characters long in the format ##:##\n");
		time = s.nextLine();
		System.out.println("Enter the age for the entry.\nAges must be a number from 0-999\n");
		age = s.nextLine();
		
		int ageInt;
		try 
		{
			ageInt = Integer.parseInt(age.trim());
		} catch (NumberFormatException ex) 
		{
			System.out.println("Invalid Entry. Defaulting to 000");
			ageInt = 000;
		}
		addItem(name,time,ageInt);
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
			System.out.println("--Record Added--");
			
		} catch (IOException e)
		{
			e.printStackTrace();
		}

	}

	/**
	 * Prompts the user for a record to lookup.
	 */
	public void promptForEntryLookup()
	{
		System.out.print("Enter the entry number that you want to lookup.");
		int entryNumber = Integer.parseInt(s.nextLine());
		System.out.print("The entry at space "+entryNumber+" is: ");
		printItem(entryNumber);
	}
	
	/**
	 * Prints out the values stored in the given entry.
	 * @param targetEntry
	 */
	public void printItem(int targetEntry)
	{
		Item item = getItem(targetEntry);
		System.out.println(item.name+"\t"+item.time+"\t"+item.age);
	}

	/**
	 * Get the string that represents an item from the database.
	 * @param targetEntry
	 * @return the item
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

	/**
	 * Prompts the user for a record to modify, and how
	 * to modify it.
	 */
	public void promptForEntryModify()
	{
		System.out.print("Enter the entry number that you want to modify.");
		int entryNumber = Integer.parseInt(s.nextLine());
		Item item = getItem(entryNumber);
		System.out.print("\n");
		Boolean running = true;
		while(running)
		{
			System.out.print("The entry at space "+entryNumber+" is: ");
			printItem(entryNumber);
			System.out.print("Modifications:\n1 - Name\n2 - Time\n3 - Age\n4 - Done Modifying");
			String input = s.nextLine();
			switch(Integer.parseInt(input.substring(0, 1)))
			{
			case 1:
				modifyName(item);
				executeModification(entryNumber,item);
				break;
			case 2:
				modifyTime(item);
				executeModification(entryNumber,item);
				break;
			case 3:
				modifyAge(item);
				executeModification(entryNumber,item);
				break;
			case 4:
				running = false;
				System.out.println("Done Modifying.");
			}
		}
		
	}


	/**
	 * Modifies the given entry number to be the new item.
	 * @param entryNumber
	 * @param item
	 */
	private void executeModification(int entryNumber, Item item) 
	{
		try 
		{
			file.seek(0);
			int currentEntry = 1;
			while (currentEntry < entryNumber)
			{
				file.readLine();
				currentEntry++;
			}
			file.writeBytes(""+item.name+"\t"+item.time+"\t"+item.age+"\n");
			System.out.println("--Record Added--");
			
		} catch (IOException e) 
		{
			e.printStackTrace();
		}

	}

	/**
	 * Modifies the name of the given record.
	 * @param item
	 */
	private void modifyName(Item item) 
	{
		System.out.print("Enter the name for the entry.\nNames must be 1-10 characters long\n");
		String name = s.nextLine();
		item.setName(name);
	}
	
	/**
	 * Modifies the time of the given record.
	 * @param item
	 */
	private void modifyTime(Item item) 
	{
		System.out.print("Enter the time for the entry.\nTimes must be 5 characters long in the format ##:##\n");
		String time = s.nextLine();
		item.setTime(time);
	}	
	
	/**
	 * Modifies the age of the given record.
	 * @param item
	 */
	private void modifyAge(Item item) 
	{
		System.out.println("Enter the age for the entry.\nAges must be a number from 0-999\n");
		String age = s.nextLine();
		int ageInt;
		try 
		{
			ageInt = Integer.parseInt(age.trim());
		} catch (NumberFormatException ex) 
		{
			System.out.println("Invalid Entry. Defaulting to 000");
			ageInt = 000;
		}
		item.setAge(ageInt);
		
	}
}
