package com.pluralsight;

import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private Dealership dealership;
    private DealershipFileManager fileManager = new DealershipFileManager();
    private Scanner scanner = new Scanner(System.in);

    public UserInterface() {
    }

    public void display() {
        init();

        while (true) {
            System.out.println("\nDEALERSHIP MENU: ");
            System.out.println("\t1 - Search By Price");
            System.out.println("\t2 - Search By Make/Model");
            System.out.println("\t3 - Search By Year");
            System.out.println("\t4 - Search By Color");
            System.out.println("\t5 - Search By Mileage");
            System.out.println("\t6 - Search By Type");
            System.out.println("\t7 - List All Vehicles");
            System.out.println("\t8 - Add A Vehicle");
            System.out.println("\t9 - Remove A Vehicle");
            System.out.println("\t99 - Quit");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> searchByPrice();
                case 2 -> searchByMakeModel();
                case 3 -> searchByYear();
                case 4 -> searchByColor();
                case 5 -> searchByMileage();
                case 6 -> searchByVehicleType();
                case 7 -> seeAllVehicles();
                case 8 -> processAddVehicle();
                case 9 -> processRemoveVehicle();
                case 99 -> {
                    return;
                }
                default -> System.out.println("Error: Invalid choice.");
            }
        }
    }

    public void searchByPrice() {
        System.out.print("Enter the min. price: ");
        double min = scanner.nextDouble();
        System.out.print("Enter the max. price: ");
        double max = scanner.nextDouble();

        displayVehicles(dealership.getVehiclesByPrice(min, max));
    }

    public void searchByMakeModel() {
        System.out.print("Enter the make: ");
        String make = scanner.nextLine();
        System.out.print("Enter the model: ");
        String model = scanner.nextLine();

        displayVehicles(dealership.getVehiclesByMakeModel(make, model));
    }

    public void searchByYear() {
        System.out.print("Enter the min. year: ");
        int minYear = scanner.nextInt();
        System.out.print("Enter the max. year: ");
        int maxYear = scanner.nextInt();

        displayVehicles(dealership.getVehiclesByYear(minYear, maxYear));
    }

    public void searchByColor() {
        System.out.print("Enter the color: ");
        String color = scanner.nextLine();

        displayVehicles(dealership.getVehiclesByColor(color));
    }

    public void searchByMileage() {
        System.out.print("Enter the min. mileage: ");
        double minMileage = scanner.nextInt();
        System.out.print("Enter the max. mileage: ");
        double maxMileage = scanner.nextInt();

        displayVehicles(dealership.getVehiclesByMileage(minMileage, maxMileage));
    }

    public void searchByVehicleType() {
        System.out.print("Enter vehicle type: ");
        String vehicleType = scanner.nextLine();

        displayVehicles(dealership.getVehiclesByType(vehicleType));
    }

    public void seeAllVehicles() {
        displayVehicles(dealership.getAllVehicles());
    }

    public void processAddVehicle() {
        System.out.print("Enter the Vin: ");
        int vin = scanner.nextInt();

        System.out.print("Enter the Year: ");
        int year = scanner.nextInt();

        System.out.print("Enter the Make: ");
        String make = scanner.nextLine();

        System.out.print("Enter the Model: ");
        String model = scanner.nextLine();

        System.out.print("Enter the Vehicle Type: ");
        String vehicleType = scanner.nextLine();

        System.out.print("Enter the Color: ");
        String color = scanner.nextLine();

        System.out.print("Enter the Odometer(Mileage): ");
        int odometer = scanner.nextInt();

        System.out.print("Enter the Price: ");
        double price = scanner.nextDouble();

        Vehicle vehicle = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);

        vehicle.setVin(vin);
        vehicle.setYear(year);
        vehicle.setMake(make);
        vehicle.setModel(model);
        vehicle.setVehicleType(vehicleType);
        vehicle.setColor(color);
        vehicle.setOdometer(odometer);
        vehicle.setPrice(price);

        dealership.addVehicle(vehicle);
    }

    public void processRemoveVehicle() {
        System.out.println("Enter VIN to remove a vehicle: ");
        int vin = scanner.nextInt();
        scanner.nextLine();

        Vehicle vehicle = dealership.getVehiclesByVin(vin);
        if (vehicle != null) {
            dealership.removeVehicle(vehicle);
            fileManager.overwriteDealership(dealership);
            System.out.println("Vehicle with VIN " + vin + " was removed successfully.");
        } else {
            System.out.println("Vehicle with VIN " + vin + " not found.");
        }
    }

    private void init() {
        DealershipFileManager dealershipFileManager = new DealershipFileManager();
        this.dealership = dealershipFileManager.getDealership();
    }

    private void displayVehicles(List<Vehicle> inventory) {
        for (Vehicle vehicle : inventory) {
            System.out.println(vehicle);
        }
    }
}