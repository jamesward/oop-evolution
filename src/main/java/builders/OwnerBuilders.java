package builders;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class OwnerBuilders {

    public static void main(String[] args) {
        VehicleBuilders.Vehicle vehicle1 = new VehicleBuilders.VehicleBuilder("Pinto").numWheels(3).build();
        VehicleBuilders.Vehicle vehicle2 = new VehicleBuilders.VehicleBuilder("Tesla").build();
        VehicleBuilders.Vehicle vehicle3 = new VehicleBuilders.VehicleBuilder("Ferrari").build();

        Owner owner = new OwnerBuilder("Rich Guy")
                .setAge(43)
                .addVehicle(vehicle1)
                .addVehicle(vehicle2)
                .addVehicle(vehicle3)
                .build();

        VehicleBuilders.Vehicle tesla = owner.vehicles.stream()
                .filter(vehicle -> "Tesla".equals(vehicle.getName()))
                .findFirst()
                .orElseGet(() -> { throw new IllegalStateException("Tesla not found"); });

        VehicleBuilders.Vehicle updatedTesla = tesla
                .toBuilder()
                .numCylinders(8)
                .build();

        List<VehicleBuilders.Vehicle> myNonTeslaVehicles = owner.vehicles.stream()
                .filter(vehicle -> !"Tesla".equals(vehicle.getName()))
                .collect(Collectors.toList());

        Owner updatedOwner = owner
                .toBuilder()
                .clearVehicles()
                .addAllVehicles(myNonTeslaVehicles)
                .addVehicle(updatedTesla)
                .build();

        System.out.println(updatedOwner.takeEmForASpin());
    }

    static class Owner {
        private final String name;
        private final Integer age;
        private final List<VehicleBuilders.Vehicle> vehicles;

        Owner(String name, Integer age, List<VehicleBuilders.Vehicle> vehicles) {
            this.name = name;
            this.age = age;
            this.vehicles = vehicles;
        }

        OwnerBuilder toBuilder() {
            return new OwnerBuilder(name).setAge(age).addAllVehicles(vehicles);
        }

        String takeEmForASpin() {
            String base = "Hi, I'm " + name + " and I am " + age + " with " + vehicles.size() + " vehicles.";
            return vehicles.stream().reduce(base, (s, vehicle) -> s + "\n" + vehicle.vroom(), (a, b) -> a + b);
        }
    }

    static class OwnerBuilder {
        private final String name;
        private Integer age;
        private List<VehicleBuilders.Vehicle> vehicles = new ArrayList<>();

        OwnerBuilder(String name) {
            this.name = name;
        }

        OwnerBuilder setAge(Integer age) {
            this.age = age;
            return this;
        }

        OwnerBuilder addVehicle(VehicleBuilders.Vehicle vehicle) {
            vehicles.add(vehicle);
            return this;
        }

        OwnerBuilder addAllVehicles(Collection<VehicleBuilders.Vehicle> vehicles) {
            if (vehicles != null) {
                this.vehicles.addAll(vehicles);
            }
            return this;
        }

        OwnerBuilder clearVehicles() {
            vehicles = new ArrayList<>();
            return this;
        }

        Owner build() {
            if (age == null) {
                throw new IllegalStateException("age must be set");
            }

            return new Owner(name, age, vehicles);
        }
    }
}

