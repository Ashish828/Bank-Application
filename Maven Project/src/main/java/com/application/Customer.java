package com.application;

class Customer {

    private static int customerIdGenerator = 0;
    private final int id;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private long phoneNumber;

    Customer(String firstName, String lastName, String email, String address, long phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.id = ++customerIdGenerator;
    }

    protected String getFirstName() {
        return firstName;
    }

    protected String getLastName() {
        return lastName;
    }

    protected int getId() {
        return id;
    }

    protected String getAddress() {
        return address;
    }

    protected long getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public String toString() {
        return "ID: " + id +
                ", First Name: " + firstName+
                ", Last Name: " + lastName+
                ", Email: '" + email+
                ", Address: " + address +
                ", Phone no: " + phoneNumber;
    }
}
