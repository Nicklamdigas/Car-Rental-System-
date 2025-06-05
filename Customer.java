// Customer class - minimal version
public class Customer {
    private String customerId;
    private String firstName;
    private String lastName;
    private boolean isCurrentlyRenting;

    public Customer(String customerId, String firstName, String lastName) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isCurrentlyRenting = false;
    }

    public String getCustomerId() { return customerId; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public boolean isCurrentlyRenting() { return isCurrentlyRenting; }
    public void setCurrentlyRenting(boolean renting) { isCurrentlyRenting = renting; }
}