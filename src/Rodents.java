import java.util.Scanner;

public class Rodents {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner kb = new Scanner(System.in);
		// Stores population value.
		int population;
		// Stores percentage increase as an integer for ease of input.
		int increase;
		// Stores number of years to estimate the population for.
		int numOfYears;
		
		//Reads user input for initial population
		System.out.println("Enter initial population of Rodents of Unusual Size");
		population = kb.nextInt();
		//Validates population data. No value under 300 is accepted.
		while (population < 300) {
			System.out.println("Minimum population is 300. Re-enter this value.");
			population = kb.nextInt();
		}
		//Reads user input for annual population increase.
		System.out.println("Enter annual population increase percentage");
		increase = kb.nextInt();
		//Validates annual population increase. Only positive changes are accepted (i.e. increases).
		while (increase < 0) {
			System.out.println("Increase must be a positive value. Re-enter this value.");
			increase = kb.nextInt();
		}
		//Reads user input for number of years to estimate population.
		System.out.println("Enter number of years to estimate population");
		numOfYears = kb.nextInt();
		//Validates number of years to estimate population. The minimum number of years is 10.
		while (numOfYears < 10) {
			System.out.println("Minimum number of tracked years is 10. Re-enter this value.");
			numOfYears = kb.nextInt();
		}
		
		//Prints initial population as Year 0.
		System.out.println("Year 0: " + population);
		//Calculates and prints the following years and their respective estimated population.
		for (int i = 1; i <= numOfYears; i++) {
			population = population + population * increase/100;
			System.out.println("Year " + i + ": " + population);
		}
		
	}

}
