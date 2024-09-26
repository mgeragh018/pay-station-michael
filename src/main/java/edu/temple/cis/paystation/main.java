//this is the file we will use for our main program
package edu.temple.cis.paystation;
import java.util.Map;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        // Create a Scanner object for user input
        Scanner scanner = new Scanner(System.in);
        PayStationImpl payStation = new PayStationImpl(); // Create an instance of PayStationImpl

        // Display the menu to the user
        while (true) {
            System.out.println("Welcome to the Pay Station");
            System.out.println("Please choose an option:");
            System.out.println("1. Deposit Coins");
            System.out.println("2. Display");
            System.out.println("3. Buy Ticket");
            System.out.println("4. Cancel");
            System.out.println("5. Empty (Admin)");
            System.out.println("6. Change Rate Strategy (Admin)");
            System.out.println("0. Exit");

            // Get the user's choice
            int choice = scanner.nextInt();

            // Handle user choice (currently empty, will add functionality step by step)
            switch (choice) {
                case 1:
                    System.out.println("Deposit Coins selected.");
                    // Logic for depositing coins
                    System.out.print("Enter coin (5, 10, 25): ");
                    int coin = scanner.nextInt();
                    try {
                        payStation.addPayment(coin); // Call the addCoin method
                    } catch (IllegalCoinException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
                    System.out.println("Display selected.");
                    // Logic for displaying time
                    System.out.println(payStation.readDisplay());
                    break;
                case 3:
                    System.out.println("Buy Ticket selected.");
                    System.out.println("Buy Ticket selected.");
                    // Logic for buying a ticket
                    if (payStation.readDisplay() > 0) {
                        Receipt receipt = payStation.buy();
                        System.out.println("Ticket purchased. Time: " + receipt.value() + " minutes.");
                    } else {
                        System.out.println("No time bought. Please insert coins before buying a ticket.");
                    }
                    break;
                case 4:
                    System.out.println("Cancel selected.");
                    // Logic for canceling the transaction
                    Map<Integer, Integer> refundedCoins = payStation.cancel();
                    if (refundedCoins.isEmpty()) {
                        System.out.println("No coins to refund.");
                    } else {
                        System.out.println("Transaction canceled. Refunded coins:");
                        refundedCoins.forEach((coinValue, count) ->
                                System.out.println(count + " coin(s) of " + coinValue + " cents."));
                    }
                    break;
                case 5:
                    System.out.println("Empty (Admin) selected.");
                    // Logic for emptying the pay station
                    System.out.print("Are you an admin? Enter password(123): ");
                    String password_input = scanner.next();

                    if (password_input.equals("123")) {
                        int totalEmptied = payStation.empty(); // Assuming empty() returns the total amount emptied
                        System.out.println("Pay Station emptied: " + totalEmptied + " cents.");
                    } else {
                        System.out.println("Incorrect password. Access denied.");
                    }
                    break;
                case 6:
                    System.out.println("Change Rate Strategy (Admin) selected.");
                    System.out.print("Are you an admin? Enter password(123): ");
                    String password_input_two = scanner.next();

                    if (password_input_two.equals("123")) {
                        // Prompt the admin to select a rate strategy
                        System.out.println("Select a Rate Strategy:");
                        System.out.println("1. LinearOneRate");
                        System.out.println("2. ProgressiveRate");
                        System.out.println("3. AlternatingRate");
                        System.out.println("4. LinearTwoRate");
                        System.out.println("5. AlternatingTwoRate");
                        System.out.print("Enter your choice: ");
                        int rateChoice = scanner.nextInt();
                        // Call the changeRateStrategy method to change the strategy based on the input
                        payStation.changeRateStrategy(rateChoice);
                    } else {
                        System.out.println("Incorrect password. Access denied.");
                    }
                    break;
                case 0:
                    System.out.println("Exiting the program. Thank you!");
                    scanner.close();
                    return; // Exit the program
                default:
                    System.out.println("Invalid choice. Please enter a number between 0 and 6.");
                    break;
            }
        }
    }
}






