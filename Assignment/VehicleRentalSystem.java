package com.wt;
import java.util.*;

abstract class Vehicle {
    private String model;
    private String brand;
    private double dailyRate;

    public Vehicle(String model, String brand, double dailyRate) {
        this.model = model;
        this.brand = brand;
        this.dailyRate = dailyRate;
    }

    public String getModel() {
        return model;
    }

    public String getBrand() {
        return brand;
    }

    public double getDailyRate() {
        return dailyRate;
    }

    public abstract String getVehicleType();

    public double calculateRentalCost(int days) {
        return dailyRate * days;
    }
}

class Car extends Vehicle {
    public Car(String model, String brand, double dailyRate) {
        super(model, brand, dailyRate);
    }

    @Override
    public String getVehicleType() {
        return "Car";
    }
}

class Bike extends Vehicle {
    public Bike(String model, String brand, double dailyRate) {
        super(model, brand, dailyRate);
    }

    @Override
    public String getVehicleType() {
        return "Bike";
    }
}

class Customerr {
    private String name;
    private String contact;

    public Customerr(String name, String contact) {
        this.name = name;
        this.contact = contact;
    }

    public String getName() {
        return name;
    }

    public String getContact() {
        return contact;
    }
}

class Rental {
    private Vehicle vehicle;
    private Customerr customer;
    private int rentalDays;

    public Rental(Vehicle vehicle, Customerr customer, int rentalDays) {
        this.vehicle = vehicle;
        this.customer = customer;
        this.rentalDays = rentalDays;
    }

    public void rentVehicle() {
        System.out.println(customer.getName() + " rented a " + vehicle.getVehicleType() +
                " (" + vehicle.getBrand() + " " + vehicle.getModel() + ")");
    }

    public void returnVehicle() {
        System.out.println(customer.getName() + " returned the " + vehicle.getVehicleType() +
                " (" + vehicle.getBrand() + " " + vehicle.getModel() + ")");
    }

    public double calculateCost() {
        return vehicle.calculateRentalCost(rentalDays);
    }
}

public class VehicleRentalSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Sample Vehicles
        Vehicle car = new Car("Sedan", "Toyota", 50.0);  // $50 per day
        Vehicle bike = new Bike("Sport", "Yamaha", 30.0); // $30 per day

        // Sample Customer
        Customerr customer = new Customerr("John Doe", "123-456-7890");

        boolean running = true;
        while (running) {
            System.out.println("\nVehicle Rental System Menu:");
            System.out.println("1. Rent Vehicle");
            System.out.println("2. Return Vehicle");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Choose vehicle to rent:");
                    System.out.println("1. Car");
                    System.out.println("2. Bike");
                    int vehicleChoice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter number of rental days: ");
                    int days = scanner.nextInt();

                    Vehicle selectedVehicle = (vehicleChoice == 1) ? car : bike;
                    Rental rental = new Rental(selectedVehicle, customer, days);
                    rental.rentVehicle();
                    System.out.println("Total rental cost: $" + rental.calculateCost());
                    break;

                case 2:
                    System.out.println("Enter vehicle type to return:");
                    System.out.println("1. Car");
                    System.out.println("2. Bike");
                    int returnChoice = scanner.nextInt();

                    if (returnChoice == 1) {
                        rental = new Rental(car, customer, 0); // Just returning the car
                    } else {
                        rental = new Rental(bike, customer, 0); // Just returning the bike
                    }
                    rental.returnVehicle();
                    break;

                case 3:
                    running = false;
                    System.out.println("Exiting the system. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }

        scanner.close();
    }
}

