package main;
/**
 *
 * @author Brad Olah
 */

public class Runner
{

	public static void main(String[] args)
	{
		DataHandler funHandler = new DataHandler("fun.dat");
		funHandler.addItem("John", "11:11", 21);
		funHandler.closeFile();
	}

}
