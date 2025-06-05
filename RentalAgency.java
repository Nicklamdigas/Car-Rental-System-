import java.time.LocalDate;
import java.util.*;

// RentalAgency - minimal version
public class RentalAgency {
    private List<Car> cars;
    private List<Customer> customers;
    private List<RentalTransaction> transactions;

    public RentalAgency(String name) {
        this.cars = new ArrayList<>();
        this.customers = new ArrayList<>();
        this.transactions = new ArrayList<>();
    }

    public void addCar(Car car) { cars.add(car); }
    public void addCustomer(Customer customer) { customers.add(customer); }
    public boolean rentCar(String customerId, String regNo, LocalDate start, LocalDate end) {
        Car car = cars.stream().filter(c -> c.getRegistrationNumber().equals(regNo) && c.isAvailable()).findFirst().orElse(null);
        Customer cust = customers.stream().filter(c -> c.getCustomerId().equals(customerId) && !c.isCurrentlyRenting()).findFirst().orElse(null);
        if (car != null && cust != null) {
            car.rent();
            cust.setCurrentlyRenting(true);
            transactions.add(new RentalTransaction("TXN-" + (transactions.size()+1), cust, car, start, end));
            return true;
        }
        return false;
    }
    public boolean returnCar(String transactionId) {
        RentalTransaction txn = transactions.stream().filter(t -> t.getTransactionId().equals(transactionId) && !t.isCompleted()).findFirst().orElse(null);
        if (txn != null) {
            txn.complete();
            return true;
        }
        return false;
    }
}