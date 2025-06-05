import java.time.LocalDate;
import java.util.Scanner;

// Main class to demonstrate the simple car rental system interactively
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RentalAgency agency = new RentalAgency("Simple Rentals");
        boolean running = true;
        System.out.println("Welcome to Simple Rentals Car Rental System!\n");
        while (running) {
            // Add a car interactively
            System.out.println("Enter car details to add:");
            System.out.print("Make: ");
            String make = scanner.nextLine(); // e.g. Toyota
            System.out.print("Model: ");
            String model = scanner.nextLine(); // e.g. Corolla
            System.out.print("Registration Number: ");
            String regNo = scanner.nextLine(); // e.g. KCA 123A
            Car car = new Car(make, model, regNo);
            agency.addCar(car);
            System.out.println("Car added successfully!\n");

            // Add a customer interactively
            System.out.println("Enter customer details to add:");
            String custId;
            while (true) {
                System.out.print("Customer ID (numbers only): ");
                custId = scanner.nextLine();
                if (custId.matches("\\d+")) {
                    break;
                } else {
                    System.out.println("Invalid input. Customer ID must be numbers only.");
                }
            }
            System.out.print("First Name: ");
            String firstName = scanner.nextLine(); // e.g. John
            System.out.print("Last Name: ");
            String lastName = scanner.nextLine(); // e.g. Doe
            Customer customer = new Customer(custId, firstName, lastName);
            agency.addCustomer(customer);
            System.out.println("Customer added successfully!\n");

            // Try to rent the car to the customer
            boolean rented = agency.rentCar(custId, regNo, LocalDate.now(), LocalDate.now().plusDays(3));
            int txnNumber = getTransactionCount(agency); // Helper method to get transaction count
            String transactionId = "TXN-" + txnNumber;
            if (rented) {
                System.out.println("Car rented successfully to " + firstName + "!");
                System.out.println("Transaction ID for this rental: " + transactionId + "\n");
            } else {
                System.out.println("Could not rent the car. Maybe it's already rented or info is wrong.\n");
            }

            // Option to return the car
            System.out.print("Do you want to return the car now? (yes/no): ");
            String returnChoice = scanner.nextLine();
            if (returnChoice.equalsIgnoreCase("yes")) {
                // Show customer and car details
                System.out.println("\n--- Rental Details ---");
                System.out.println("Customer: " + customer.getFirstName() + " " + customer.getLastName() + " (ID: " + customer.getCustomerId() + ")");
                System.out.println("Car: " + car.getMake() + " " + car.getModel() + " (Reg: " + car.getRegistrationNumber() + ")");
                System.out.println("Rental Period: 3 days");
                System.out.print("Enter the Transaction ID to return: ");
                String txnId = scanner.nextLine();
                boolean returned = agency.returnCar(txnId);
                if (returned) {
                    System.out.println("Car returned successfully. Thank you, " + firstName + "!\n");
                } else {
                    System.out.println("Could not return the car. Maybe the transaction ID is wrong or already returned.\n");
                }
            } else {
                System.out.println("You chose not to return the car now.\n");
            }

            // Option to rent another car
            System.out.print("Do you want to rent another car to a customer? (yes/no): ");
            String again = scanner.nextLine();
            if (!again.equalsIgnoreCase("yes")) {
                System.out.println("Thank you for using Simple Rentals. Goodbye!");
                running = false;
            } else {
                System.out.println("\n--- Starting new rental process ---\n");
            }
        }
        scanner.close();
    }

    // Helper method to get transaction count
    private static int getTransactionCount(RentalAgency agency) {
        try {
            java.lang.reflect.Field field = agency.getClass().getDeclaredField("transactions");
            field.setAccessible(true);
            java.util.List<?> transactions = (java.util.List<?>) field.get(agency);
            return transactions.size();
        } catch (Exception e) {
            return 1; // fallback
        }
    }
}