package com.pluralsight;

import java.util.ArrayList;
import java.util.List;

public class Dealership {

private String name;
private String address;
private String phone;
private ArrayList<Vehicle> inventory;

    public Dealership(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.inventory = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Vehicle> getVehiclesByPrice(Double min, Double max) {
        List<Vehicle> vehicles = new ArrayList<>();

        for (Vehicle vehicle : inventory) {
            if ((min == null || vehicle.getPrice() >= min) && (max == null || vehicle.getPrice() <= max)) {
                vehicles.add(vehicle);
            }
        }
        return vehicles;
    }

    public List<Vehicle> getVehiclesByMakeModel(String make, String model) {
        List<Vehicle> vehicles = new ArrayList<>();

        for (Vehicle vehicle : inventory) {
            if ((vehicle.getMake().equalsIgnoreCase(make) && vehicle.getModel().equalsIgnoreCase(model)) ||
                    (vehicle.getMake().equalsIgnoreCase(make) && model.isEmpty())) {
                vehicles.add(vehicle);
            }
        }
        return vehicles;
    }

    public List<Vehicle> getVehiclesByYear(Integer min, Integer max) {
        List<Vehicle> vehicles = new ArrayList<>();

        for(Vehicle vehicle : inventory){
            if((max == null || vehicle.getYear() <= max) && (min == null || vehicle.getYear() >= min)){
                vehicles.add(vehicle);
            }
        }
        return vehicles;
    }

    public List<Vehicle> getVehiclesByColor(String color) {
        List<Vehicle> vehicles = new ArrayList<>();

        for (Vehicle vehicle : inventory) {
            if (vehicle.getColor().equalsIgnoreCase(color)) {
                vehicles.add(vehicle);
            }
        }
        return vehicles;
    }

    public List<Vehicle> getVehiclesByMileage(Double min, Double max) {
        List<Vehicle> vehicles = new ArrayList<>();

        for (Vehicle vehicle : inventory) {
            if ((max == null || vehicle.getOdometer() <= max) && (min == null || vehicle.getOdometer() >= min)) {
                vehicles.add(vehicle);
            }
        }
        return vehicles;
    }

    public List<Vehicle> getVehiclesByType(String vehicleType) {
        List<Vehicle> vehicles = new ArrayList<>();

        for (Vehicle vehicle : inventory) {
            if (vehicle.getVehicleType().equalsIgnoreCase(vehicleType)) {
                vehicles.add(vehicle);
            }
        }
        return vehicles;
    }

    public List<Vehicle> getAllVehicles() {
        return inventory;
    }

    public void addVehicle(Vehicle vehicle) {
        inventory.add(vehicle);
    }

    public void removeVehicle(Vehicle vehicle) {
        inventory.remove(vehicle);
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        output.append(String.format("%s|%s|%s",name,address,phone));
        for(Vehicle vehicle : inventory){
            output.append(String.format("%n%d|%d|%s|%s|%s|%s|%d|%.2f",
                    vehicle.getVin(),vehicle.getYear(),vehicle.getMake(),vehicle.getModel(),vehicle.getVehicleType(),
                    vehicle.getColor(),vehicle.getOdometer(),vehicle.getPrice()));
        }
        return output.toString();
    }
}
