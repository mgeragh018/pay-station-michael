//this is the file we will use for our main program

package edu.temple.cis.paystation;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        // Create an instance of PayStationImpl
        PayStationImpl payStation = new PayStationImpl();
        Scanner scanner = new Scanner(System.in);

        // Admin sets the rate strategy
        System.out.println("Admin: Enter rate strategy option (1: Default, 2: Discounted, 3: Mixed, 4: Simple):");
        int adminOption = scanner.nextInt();
        payStation.changeRateStrategy(adminOption);

        // Loop to allow the user to add payments
        while (true) {
            System.out.println("Please enter a coin value (5, 10, 25) to add time, or enter 0 to stop:");
            try {
                int coinValue = scanner.nextInt();
                if (coinValue == 0) {
                    break; // Exit the loop if 0 is entered
                }
                payStation.addPayment(coinValue);
                System.out.println("You entered: " + coinValue + " cents.");
            } catch (IllegalCoinException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // Clear the invalid input
            }
        }
        scanner.close();
        System.out.println("Thank you for using the Pay Station.");
    }
}





