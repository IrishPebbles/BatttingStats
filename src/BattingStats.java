import java.util.Scanner;

public class BattingStats {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		int playerCount = 1;
		int arrSize = 0;

		System.out.println("Welcome to the Grand Circus Batting Average Calculator!");
		System.out.println();

		do {
			// Ask for the number of at bats
			arrSize = getInt(scan, "How many times was Player " + playerCount + " at bat? ", 0, 9);
			
			int atBat[] = new int[arrSize]; // Use an array to store the at-bat results for each player
			// ask the user for the # of bases earned by the batter
			getBases(scan, atBat, arrSize);

			// Display the batting average for each batter
			System.out.printf(
					"The batting average for Player " + playerCount + " is: %.3f", battingAverage(atBat, arrSize));
			System.out.println();
			// Format the batting average and slugging percentages to 3 decimal places

			// Display the slugging percentage for each batter
			System.out.printf(
					"The slugging average for Player " + playerCount + " is: %.3f",  sluggingAverage(atBat, arrSize));
			System.out.println();
			// FIXME: formatting for average above

			playerCount++;

			// Validate the user's response to "Another batter?" (YyNn only)
		} while (wantToContinue(scan, "Would you like to continue? (y/n) "));

		System.out.println("Thank you! Goodbye.");

		scan.close();

	}

	private static boolean wantToContinue(Scanner scnr, String prompt) {
		boolean valid = false;
		String response = "";

		System.out.println(prompt);

		while (!valid) {
			response = scnr.nextLine();

			if (response.equalsIgnoreCase("y") || response.equalsIgnoreCase("n")) {
				valid = true;
			} else {
				System.out.println("You must enter y or n. Please try again.");
			}
		}
		return response.equalsIgnoreCase("y");
	}

	private static void getBases(Scanner scan, int[] bats, int arrSize) {
		int atBat = 1;

		for (int i = 0; i < arrSize; i++) {
			// Validate the input for only positive integers (For at-bat only 0-4)
			bats[i] = getInt(scan, "How many bases did the runner earn for at bat " + atBat + " ?\n"
					+ "(i.e., 0 = out, 1 = single, 2 = double, 3 = triple, 4 = homerun)\n", 0, 4);
			atBat++;
		}
	}

	private static double battingAverage(int[] bats, int arrSize) {
		double count = 0.0;
		double avg = 0.0;

		// loop through at bats & count non-zero
		// divide non-zero by total at bats
		for (int i = 0; i < arrSize; i++) {
			if (bats[i] != 0) {
				count++;
			}
		}
		avg = count / arrSize;

		return avg;
	}

	private static double sluggingAverage(int[] bats, int arrSize) {
		double count = 0.0;
		double avg = 0.0;

		// loop through at bats & count total bases earned
		// divide non-zero by total at bats
		for (int i = 0; i < arrSize; i++) {
			if (bats[i] != 0) {
				count += bats[i];
			}
		}
		avg = count / arrSize;

		return avg;
	}

	public static int getInt(Scanner sc, String prompt,
		    int min, int max)
		    {
		        int i = 0;
		        boolean isValid = false;
		        while (isValid == false)
		        {
		            i = getInt(sc, prompt);
		            if (i < min)
		                System.out.println(
		                    "Error! Number must be " + min + " or greater.");
		            else if (i > max)
		                System.out.println(
		                    "Error! Number must be " + max + " or less.");
		            else
		                isValid = true;
		        }
		        return i;
		    }

	public static int getInt(Scanner sc, String prompt) {
		int i = 0;
		boolean isValid = false;

		while (isValid == false) {
			System.out.print(prompt);
			if (sc.hasNextInt()) {
				i = sc.nextInt();
				isValid = true;
			} else {
				System.out.println("Error! Invalid integer value. Try again.");
			}
			sc.nextLine(); // discard any other data entered on the line
		}
		return i;
	}
}
