import java.util.Scanner;

public class BookShop {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner kb = new Scanner(System.in);
		
		// Indicates whether the program is online and accepting payments.
		String acceptingPayments = "N";
		// Indicates whether there is an active transaction.
		String transaction = "N";
		
		// Allows user to start the program.
		System.out.println("Start accepting payments? Y/N");
		acceptingPayments = kb.nextLine();
		// Allows for user to start the program after a previous refusal.
		while (acceptingPayments.equalsIgnoreCase("N")) {
			System.out.println("System is currently not accepting payments. Press Y to start.");
			acceptingPayments = kb.nextLine();
			// Ensures no other input other than Y or N is accepted.
			while (!acceptingPayments.equalsIgnoreCase("Y")) {
				System.out.println("Invalid command. Press Y to start");
				acceptingPayments = kb.nextLine();
			}
		}
		
		// Contains all processes that occur when the system is on.
		while (acceptingPayments.equalsIgnoreCase("Y")) {
			// Allows for the user to start a new transaction.
			System.out.println("Start new transaction? Y/N");
			transaction = kb.nextLine();
			// Ensures a transaction is not started when any other value other Y (including N) is inputed.
			while (!transaction.equalsIgnoreCase("Y")) {
				System.out.println("No transaction started. Enter Y to start a new transaction.");
				transaction = kb.nextLine();
			}
			
			// Contains all process that occur during an active transaction.
			while (transaction.equalsIgnoreCase("Y")) {
				// Stores number of unique book titles
				int bookNo;
				System.out.println("Enter number of individual titles");
				bookNo = kb.nextInt();
				// Ensures only positive values for total titles are accepted.
				while (bookNo < 0) {
					System.out.println("Number must be positive. Re-enter this value");
					bookNo = kb.nextInt();
				}
				
				// Stores total due.
				float totalDue = 0;
				// Ensures the data for each title is recorded.
				for (int i = 1; i <= bookNo; i++) {
					// Stores the quantity of the current title.
					int quantity;
					// Stores the price of the current title.
					float indivPrice;
					
					// Accepts an input for the quantity.
					System.out.println("Enter quantity of title " + i);
					quantity = kb.nextInt();
					// Ensures only positive values are accepted for quantity.
					while (quantity < 0) {
						System.out.println("Quantity must be positive. Re-enter this value.");
						quantity = kb.nextInt();
					}
					// Accepts an input for a title's price.
					System.out.println("Enter price of title " + i);
					indivPrice = kb.nextFloat();
					// Ensures only positive values are accepted for price.
					while (indivPrice < 0) {
						System.out.println("Price must be positive. Re-enter this value");
						indivPrice = kb.nextFloat();
					}
					// Current total is calculated. The loop will end with a final total due.
					totalDue = totalDue + quantity * indivPrice;
				}
				
				// Stores payment method.
				String paymentMethod;
				// Accepts an input for payment method.
				System.out.println("Select a payment method.");
				System.out.println("Enter [CC] for credit card, [C] for cash.");
				paymentMethod = kb.next();
				// Ensures only two payment methods (cash and credit) are accepted.
				while (!paymentMethod.equalsIgnoreCase("C") && !paymentMethod.equalsIgnoreCase("CC")) {
					System.out.println("Invalid payment method.");
					System.out.println("Enter [CC] for credit card, [C] for cash.");
					paymentMethod = kb.next();
				}
				
				// Stores cash tendered. This is set to 0 for the case that it isn't applicable to the current transaction.
				float cashTendered = 0;
				// Stores change. This is set to 0 in the case that it isn't applicable to the current transaction.
				float change = 0;
				// Stores credit payment. This is set to 0 for the case that it isn't applicable to the current transaction.
				float creditPayment = 0;
				
				// Contains all processes that happen in the event of choosing to pay by cash.
				while (paymentMethod.equalsIgnoreCase("C")) {
					// Accepts a cash value.
					System.out.println("Enter the value of cash tendered");
					cashTendered = kb.nextFloat();
					// Ensures only positive values are accepted.
					while (cashTendered < 0) {
						System.out.println("Invalid value. Enter thhe value of cash tendered.");
						cashTendered = kb.nextFloat();
					}
					// Case: Insufficient cash
					if (cashTendered < totalDue) {
						// Allows user to cancel the transaction or charge to a card.
						System.out.println("Insufficient funds. Enter [S] to stop transaction or [CC] to charge the remainder to a card.");
						kb.nextLine();
						paymentMethod = kb.nextLine();
						// Ensures the user can only cancel or charge to a card.
						while (!paymentMethod.equalsIgnoreCase("S") && !paymentMethod.equalsIgnoreCase("CC")) {
							System.out.println("Invalid payment method.");
							System.out.println("Enter [S] to stop transaction or [CC] to charge the remainder to a card.");
							paymentMethod = kb.next();
						}
					// Case: Cash is in excess
					} else if (cashTendered > totalDue){
						// Calculates the value of change
						change = cashTendered - totalDue;
						paymentMethod = "S";
					// Case: The exact amount is tendered.
					} else {
					//Stops payment and proceeds to receipt.
					paymentMethod = "S";
					}
				}
				
				// Contains the processes that happens when the total is charged to a card.
				while (paymentMethod.equalsIgnoreCase("CC")) {
					// Calculates credit payment. In the case where credit card was chosen from the start, cashTendered will still be 0 and therefore not affect the calculation.
					creditPayment = totalDue - cashTendered;
					paymentMethod = "S";
				}
				
				//Generates the receipt after the payment process has finished.
				System.out.println("===============================");
				if (paymentMethod.equalsIgnoreCase("S")) {
					System.out.println("Transaction Summary");
					System.out.println("-------------------------------");
					System.out.println("Total Due: $" + totalDue);
					// Only prints if cash was used at any point of the process.
					if (cashTendered != 0) {
						System.out.println("Cash Tendered: $" + cashTendered);
					}
					// Only prints if cash tendered was in excess.
					if (change != 0) {
						System.out.println("Change: $" + change);
					}
					// Only prints if a card was used at any point of the process.
					if (creditPayment != 0) {
						System.out.println("Credit Card Payment: $" + creditPayment);
					}
				}
				System.out.println("===============================");
				// Ends current transaction
				transaction = "N";
			}
			// Allows user to end the program.
			System.out.println("Continue accepting payments? Y/N");
			acceptingPayments = kb.nextLine();
			
		}
		
	}

}
