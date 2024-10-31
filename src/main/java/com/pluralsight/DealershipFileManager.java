package com.pluralsight;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class DealershipFileManager {
    private final String fileName;
    private final Dealership dealership;

    public DealershipFileManager() {
        this.fileName = "dealership.csv";
        this.dealership = new Dealership("", "", "");
    }

    public Dealership getDealership() {

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
            String input;

            int vin = 0;
            int year = 0;
            String make = "";
            String model = "";
            String vehicleType = "";
            String color = "";
            int odometer = 0;
            double price = 0;

            while ((input = bufferedReader.readLine()) != null) {

                String[] strings = input.split("\\|");

                if (strings.length == 3) {
                    String name = strings[0];
                    String address = strings[1];
                    String phone = strings[2];
                }

                if (strings.length == 8) {

                    vin = Integer.parseInt(strings[0]);
                    year = Integer.parseInt(strings[1]);
                    make = strings[2];
                    model = strings[3];
                    vehicleType = strings[4];
                    color = strings[5];
                    odometer = Integer.parseInt(strings[6]);
                    price = Double.parseDouble(strings[7]);

                    Vehicle vehicle = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);
                    dealership.addVehicle(vehicle);
                }
            }
            return dealership;
        } catch (Exception e) {
            System.err.println("Error: File not read.");

        } return null;
    }

    public void overwriteDealership(Dealership dealership) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("dealership.csv"))) {
            bufferedWriter.write(dealership.getName() + "|" + dealership.getAddress() + "|" + dealership.getPhone());
            bufferedWriter.newLine();
            for (Vehicle vehicle : dealership.getAllVehicles()) {
                bufferedWriter.write(vehicle.getVin() + "|" +
                        vehicle.getYear() + "|" +
                        vehicle.getMake() + "|" +
                        vehicle.getModel() + "|" +
                        vehicle.getVehicleType() + "|" +
                        vehicle.getColor() + "|" +
                        vehicle.getOdometer() + "|" +
                        vehicle.getPrice());
                bufferedWriter.newLine();
            }
        } catch (Exception e) {
            System.out.println("Error: File not written: " + e.getMessage());
        }
    }
}