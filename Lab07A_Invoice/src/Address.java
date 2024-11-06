public class Address {
    private String street;

    private String City;

    private String State;

    private int zipCode;

    public Address(String street, String City, String State, int zipCode) {

        this.street = street;
        this.City = City;
        this.State = State;
        this.zipCode = zipCode;
    }

    public String getCity() {
        return City;
    }

    public int getZipCode() {
        return zipCode;
    }

    public String getStreet() {
        return street;
    }

    public String getState() {
        return State;
    }
}
