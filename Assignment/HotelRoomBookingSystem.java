package com.wt;

import java.util.*;

abstract class Room {
    private String roomNumber;
    private double rate;

    public Room(String roomNumber, double rate) {
        this.roomNumber = roomNumber;
        this.rate = rate;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public double getRate() {
        return rate;
    }

    public abstract String getRoomType();
}

class StandardRoom extends Room {
    public StandardRoom(String roomNumber, double rate) {
        super(roomNumber, rate);
    }

    @Override
    public String getRoomType() {
        return "Standard Room";
    }
}

class DeluxeRoom extends Room {
    public DeluxeRoom(String roomNumber, double rate) {
        super(roomNumber, rate);
    }

    @Override
    public String getRoomType() {
        return "Deluxe Room";
    }
}

class HotelCustomer {
    private String name;
    private String contact;

    public HotelCustomer(String name, String contact) {
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

class Booking {
    private Room room;
    private HotelCustomer customer;
    private int stayDays;

    public Booking(Room room, HotelCustomer customer, int stayDays) {
        this.room = room;
        this.customer = customer;
        this.stayDays = stayDays;
    }

    public void bookRoom() {
        System.out.println(customer.getName() + " booked a " + room.getRoomType() +
                " (Room number: " + room.getRoomNumber() + ")");
    }

    public void checkOut() {
        System.out.println(customer.getName() + " checked out from " + room.getRoomType() +
                " (Room number: " + room.getRoomNumber() + ")");
    }

    public double calculateCost() {
        return room.getRate() * stayDays;
    }
}

public class HotelRoomBookingSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Sample Rooms
        Room standardRoom = new StandardRoom("101", 100.0);  // $100 per day
        Room deluxeRoom = new DeluxeRoom("202", 200.0);  // $200 per day

        // Sample HotelCustomer
        HotelCustomer customer = new HotelCustomer("Jane Smith", "987-654-3210");

        boolean running = true;
        while (running) {
            System.out.println("\nHotel Room Booking System Menu:");
            System.out.println("1. Book Room");
            System.out.println("2. Check-Out Room");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Choose room to book:");
                    System.out.println("1. Standard Room");
                    System.out.println("2. Deluxe Room");
                    int roomChoice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter number of stay days: ");
                    int stayDays = scanner.nextInt();

                    Room selectedRoom = (roomChoice == 1) ? standardRoom : deluxeRoom;
                    Booking booking = new Booking(selectedRoom, customer, stayDays);
                    booking.bookRoom();
                    System.out.println("Total booking cost: $" + booking.calculateCost());
                    break;

                case 2:
                    System.out.println("Enter room type to check-out:");
                    System.out.println("1. Standard Room");
                    System.out.println("2. Deluxe Room");
                    int checkOutChoice = scanner.nextInt();

                    if (checkOutChoice == 1) {
                        booking = new Booking(standardRoom, customer, 0); // Checking out
                    } else {
                        booking = new Booking(deluxeRoom, customer, 0); // Checking out
                    }
                    booking.checkOut();
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

