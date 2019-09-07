import java.util.Scanner;

public class Main
{
	public static void main(String[] args)
	{
		Main.Stix();
	}
	
	public static void Stix()
	{
		// Declaring variables.
		int numStix = 0;
		String cpuFirst = "n";
		int playerTurn = 0;
		
		System.out.println("How many sticks to start with? [5 - 30]");
			
		numStix = Main.validIntNum();
		
		System.out.println("Does the CPU go first? [y / n]");
		
		cpuFirst = Main.validString();
		
		// if y, assign playerTurn to 0 for CPU.
		if(cpuFirst.equals("y") || cpuFirst.equals("yes"))
			playerTurn = 0;
		// if n, assign playerTurn to 1 for user player.
		if(cpuFirst.equals("n") || cpuFirst.equals("no"))
			playerTurn = 1;
		
		do
		{
			switch(playerTurn)
			{
			case 0:
				Main.printStix(numStix);
				numStix = cpuTurn(numStix);
				playerTurn = 1;
				break;
			case 1:
				Main.printStix(numStix);
				numStix = playerTurn(numStix);
				playerTurn = 0;
				break;
			}
			
		}
		while(numStix > 1);
		
		Main.winner(numStix, playerTurn);
	}
	
	public static int validIntNum()
	{
		Scanner input = new Scanner(System.in);
		int numStix;
		Boolean error = true;
		
		do
		{
			// Checking if the next input is an integer.
			while(!input.hasNextInt())
			{
				System.err.println("ERROR: Please only enter an integer between 5 - 30");
				input.next();
			}
			
			numStix = input.nextInt();
			
			// Checks if input is between 5 and 30.
			if(numStix >= 5 && numStix <= 30)
				error = false;
			else
				System.err.println("ERROR: Please only enter an integer between 5 - 30");

		}
		while(error == true);
		
		return numStix;
	}
	
	public static int validIntChoice(int numStix)
	{
		Scanner input = new Scanner(System.in);
		int numPickup;
		Boolean error = true;
		
		do
		{
			while(!input.hasNextInt())
			{
				System.err.println("ERROR: Please only enter an integer 1, 2 or 3.");
				input.next();
			}
			
			numPickup = input.nextInt();
			if(!(numPickup == 1 || numPickup == 2 || numPickup == 3))
				System.err.println("ERROR: Please only enter an integer between 1 or 3.");
			else if(numPickup > numStix)
			{
				System.err.println("ERROR: Please only enter a valid amount.");
				
				// Prints out remaining sticks.
				Main.printStix(numStix);
			}
			else
				error = false;
		}
		while(error == true);
		
		return numPickup;
	}
	
	
	public static String validString()
	{
		Scanner input = new Scanner(System.in);
		String choice = "";
		Boolean error = true;
		
		choice = input.next();
		
		do
		{	
			// Checks if input is between 5 and 30.
			if (!(choice.equals("y") || choice.equals("yes") || choice.equals("n") || choice.equals("no")))
			{
				System.err.println("ERROR: Please input a valid option.");
				System.out.println("CPU goes first? [y / n]");
				input.nextLine();
				choice = input.next();
			}
			else
				error = false;
		}
		while(error == true);
		
		return choice;
	}
	
	// Computer turn.
	public static int cpuTurn(int numStix)
	{
		int choice = 0;
		System.out.println("Computer turn");
		System.out.println();
				
		// CPU decision making.
		if(numStix % 4 == 3)
			choice = 2;
		else if(numStix % 4 == 2)
			choice = 1;
		else if(numStix % 4 == 0)
			choice = 3;
		else if(numStix % 4 == 1)
			choice = 3;
					
		switch (choice)
		{
			case 1:
				numStix -= 1;
				System.out.println("CPU took 1 stick.");
				break;
			case 2:
				numStix -= 2;
				System.out.println("CPU took 2 sticks.");
				break;
			case 3:
				numStix -= 3;
				System.out.println("CPU took 3 sticks.");
				break;				
		}
		return numStix;
	}
	
	// Player turn.
	public static int playerTurn(int numStix)
	{
		int choice = 0;
		
		System.out.println("Player turn");
		System.out.println();
		
		System.out.println("Choose an option");
		System.out.println("1. Take one stick.");
		System.out.println("2. Take two sticks.");
		System.out.println("3. Take three sticks.");
		
		choice = Main.validIntChoice(numStix);
			
		// removes either 1, 2, 3 from numStix.
		switch (choice)
		{
			case 1:
				numStix -= 1;
				System.out.println("Player took 1 stick.");
				break;
			case 2:
				numStix -= 2;
				System.out.println("Player took 2 sticks.");
				break;
			case 3:
				numStix -= 3;
				System.out.println("Player took 3 sticks.");
				break;
		}
		return numStix;
	}
	
	// Prints the remaining amount of sticks on the table.
	public static void printStix(int numStix)
	{
		System.out.println();
		System.out.print("Stix on the table: ");
		for (int i = 0; i < numStix; i++)
		{
			System.out.print("| ");
		}
		System.out.println("\n");
	}
	
	// Prints out who won the game.
	public static void winner(int numStix, int playerTurn)
	{
		switch(playerTurn)
		{
		case 0:
			if(numStix == 0)
				System.out.print("CPU wins!");
			else
				System.out.print("Player wins!");
			break;
			
		case 1:
			if(numStix == 0)
				System.out.print("Player wins!");
			else
				System.out.print("CPU wins!");
			break;
		}
	}
}
