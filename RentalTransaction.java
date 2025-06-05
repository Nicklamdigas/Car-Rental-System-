// RentalTransaction.java - minimal version
import java.time.LocalDate;

public class RentalTransaction {
    private String transactionId;
    private boolean isCompleted;

    public RentalTransaction(String transactionId, Customer customer, Car car, LocalDate startDate, LocalDate endDate) {
        this.transactionId = transactionId;
        this.isCompleted = false;
    }

    public String getTransactionId() { return transactionId; }
    public boolean isCompleted() { return isCompleted; }
    public void complete() { isCompleted = true; }
}