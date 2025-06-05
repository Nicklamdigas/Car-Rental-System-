// Car class - minimal version
public class Car {
    private String make;
    private String model;
    private String registrationNumber;
    private boolean isAvailable;

    public Car(String make, String model, String registrationNumber) {
        this.make = make;
        this.model = model;
        this.registrationNumber = registrationNumber;
        this.isAvailable = true;
    }

    public String getMake() { return make; }
    public String getModel() { return model; }
    public String getRegistrationNumber() { return registrationNumber; }
    public boolean isAvailable() { return isAvailable; }
    public boolean rent() {
        if (isAvailable) {
            isAvailable = false;
            return true;
        }
        return false;
    }
    public void returnCar() { isAvailable = true; }
}