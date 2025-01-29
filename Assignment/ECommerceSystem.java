package com.wt;

import java.io.*;
import java.util.*;

class Product {
    private int id;
    private String name;
    private double price;

    public Product(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Product ID: " + id + ", Name: " + name + ", Price: $" + price;
    }
}

class Customer {
    private int id;
    private String name;

    public Customer(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Customer ID: " + id + ", Name: " + name;
    }
}

class Order {
    private int orderId;
    private Customer customer;
    private List<Product> products;
    private double totalCost;

    public Order(int orderId, Customer customer) {
        this.orderId = orderId;
        this.customer = customer;
        this.products = new ArrayList<>();
        this.totalCost = 0.0;
    }

    public void addProduct(Product product) {
        products.add(product);
        totalCost += product.getPrice();
    }

    public void removeProduct(int productId) {
        Iterator<Product> iterator = products.iterator();
        while (iterator.hasNext()) {
            Product product = iterator.next();
            if (product.getId() == productId) {
                totalCost -= product.getPrice();
                iterator.remove();
                break;
            }
        }
    }

    public double calculateTotalCost() {
        return totalCost;
    }

    public void saveOrderToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("order_history.txt", true))) {
            writer.write("Order ID: " + orderId + "\n");
            writer.write("Customer: " + customer.getName() + "\n");
            writer.write("Products:\n");
            for (Product product : products) {
                writer.write(product.toString() + "\n");
            }
            writer.write("Total Cost: $" + totalCost + "\n");
            writer.write("-----------------------------\n");
        } catch (IOException e) {
            System.out.println("Error saving order to file: " + e.getMessage());
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Order ID: ").append(orderId).append("\n");
        sb.append("Customer: ").append(customer.getName()).append("\n");
        sb.append("Products:\n");
        for (Product product : products) {
            sb.append(product.toString()).append("\n");
        }
        sb.append("Total Cost: $").append(totalCost).append("\n");
        return sb.toString();
    }
}

public class ECommerceSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Product> productCatalog = new ArrayList<>();
        List<Customer> customers = new ArrayList<>();
        List<Order> orders = new ArrayList<>();

        // Adding sample products and customers
        productCatalog.add(new Product(1, "Laptop", 999.99));
        productCatalog.add(new Product(2, "Phone", 499.99));
        productCatalog.add(new Product(3, "Headphones", 49.99));

        customers.add(new Customer(1, "Alice"));
        customers.add(new Customer(2, "Bob"));

        System.out.println("Welcome to the E-Commerce System!");
        boolean running = true;

        while (running) {
            System.out.println("\nMenu:");
            System.out.println("1. View Products");
            System.out.println("2. Create Order");
            System.out.println("3. View Orders");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("\nProduct Catalog:");
                    for (Product product : productCatalog) {
                        System.out.println(product);
                    }
                    break;

                case 2:
                    System.out.println("\nSelect a Customer:");
                    for (Customer customer : customers) {
                        System.out.println(customer);
                    }
                    System.out.print("Enter Customer ID: ");
                    int customerId = scanner.nextInt();
                    Customer selectedCustomer = customers.stream()
                            .filter(c -> c.getId() == customerId)
                            .findFirst()
                            .orElse(null);

                    if (selectedCustomer == null) {
                        System.out.println("Invalid Customer ID!");
                        break;
                    }

                    Order order = new Order(orders.size() + 1, selectedCustomer);
                    boolean addingProducts = true;

                    while (addingProducts) {
                        System.out.println("\nProduct Catalog:");
                        for (Product product : productCatalog) {
                            System.out.println(product);
                        }
                        System.out.print("Enter Product ID to add to the order (or 0 to finish): ");
                        int productId = scanner.nextInt();

                        if (productId == 0) {
                            addingProducts = false;
                        } else {
                            Product selectedProduct = productCatalog.stream()
                                    .filter(p -> p.getId() == productId)
                                    .findFirst()
                                    .orElse(null);

                            if (selectedProduct != null) {
                                order.addProduct(selectedProduct);
                                System.out.println("Product added to the order.");
                            } else {
                                System.out.println("Invalid Product ID!");
                            }
                        }
                    }

                    orders.add(order);
                    order.saveOrderToFile();
                    System.out.println("Order created successfully!");
                    break;

                case 3:
                    System.out.println("\nOrders:");
                    for (Order o : orders) {
                        System.out.println(o);
                    }
                    break;

                case 4:
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
