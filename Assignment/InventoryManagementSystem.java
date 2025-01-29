package com.wt;
import java.util.*;

interface SupplierOperations {
    void addSupplier(String name, String contact);
    void viewSuppliers();
}

class Productt {
    private int id;
    private String name;
    private int stock;

    public Productt(int id, String name, int stock) {
        this.id = id;
        this.name = name;
        this.stock = stock;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public int getStock() { return stock; }

    public void updateStock(int quantity) {
        this.stock += quantity;
    }

    @Override
    public String toString() {
        return "Product ID: " + id + ", Name: " + name + ", Stock: " + stock;
    }
}

class Inventory implements SupplierOperations {
    private List<Productt> products = new ArrayList<>();
    private Map<String, String> suppliers = new HashMap<>();

    public void addProduct(int id, String name, int stock) {
        products.add(new Productt(id, name, stock));
        System.out.println("Product added successfully!");
    }

    public void updateProductStock(int id, int quantity) {
        for (Productt product : products) {
            if (product.getId() == id) {
                product.updateStock(quantity);
                System.out.println("Stock updated successfully!");
                return;
            }
        }
        System.out.println("Product not found!");
    }

    public void removeProduct(int id) {
        products.removeIf(product -> product.getId() == id);
        System.out.println("Product removed successfully!");
    }

    public void viewLowStock(int threshold) {
        System.out.println("Low-stock products (below " + threshold + "):");
        for (Productt product : products) {
            if (product.getStock() < threshold) {
                System.out.println(product);
            }
        }
    }

    public void viewProducts() {
        System.out.println("Product List:");
        for (Productt product : products) {
            System.out.println(product);
        }
    }

    @Override
    public void addSupplier(String name, String contact) {
        suppliers.put(name, contact);
        System.out.println("Supplier added successfully!");
    }

    @Override
    public void viewSuppliers() {
        System.out.println("Supplier List:");
        for (Map.Entry<String, String> entry : suppliers.entrySet()) {
            System.out.println("Name: " + entry.getKey() + ", Contact: " + entry.getValue());
        }
    }
}

public class InventoryManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Inventory inventory = new Inventory();

        boolean running = true;
        while (running) {
            System.out.println("\nInventory Management Menu:");
            System.out.println("1. Add Product");
            System.out.println("2. Update Product Stock");
            System.out.println("3. Remove Product");
            System.out.println("4. View Products");
            System.out.println("5. View Low-Stock Products");
            System.out.println("6. Add Supplier");
            System.out.println("7. View Suppliers");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Product ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Product Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Initial Stock: ");
                    int stock = scanner.nextInt();
                    inventory.addProduct(id, name, stock);
                    break;

                case 2:
                    System.out.print("Enter Product ID: ");
                    int updateId = scanner.nextInt();
                    System.out.print("Enter Quantity to Add/Remove: ");
                    int quantity = scanner.nextInt();
                    inventory.updateProductStock(updateId, quantity);
                    break;

                case 3:
                    System.out.print("Enter Product ID to Remove: ");
                    int removeId = scanner.nextInt();
                    inventory.removeProduct(removeId);
                    break;

                case 4:
                    inventory.viewProducts();
                    break;

                case 5:
                    System.out.print("Enter Low-Stock Threshold: ");
                    int threshold = scanner.nextInt();
                    inventory.viewLowStock(threshold);
                    break;

                case 6:
                    System.out.print("Enter Supplier Name: ");
                    String supplierName = scanner.nextLine();
                    System.out.print("Enter Supplier Contact: ");
                    String contact = scanner.nextLine();
                    inventory.addSupplier(supplierName, contact);
                    break;

                case 7:
                    inventory.viewSuppliers();
                    break;

                case 8:
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

