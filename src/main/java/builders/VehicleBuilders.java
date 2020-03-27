package builders;

import java.util.Optional;

public class VehicleBuilders {

    public static void main(String[] args) {
        usingConstructors();
        usingBuilder();
    }

    static class Vehicle {
        private final String name;
        private final Integer numWheels;
        private final Integer defaultNumberOfWheels = 4;
        private final Optional<Integer> maybeNumCylinders;
        private final Optional<Integer> defaultNumCylinders = Optional.empty();

        public Vehicle(String name) {
            this.name = name;
            this.numWheels = defaultNumberOfWheels;
            this.maybeNumCylinders = defaultNumCylinders;
        }

        public Vehicle(String name, Integer numWheels) {
            this.name = name;
            this.numWheels = numWheels;
            this.maybeNumCylinders = defaultNumCylinders;
        }

        public Vehicle(String name, Optional<Integer> maybeNumCylinders) {
            this.name = name;
            this.numWheels = defaultNumberOfWheels;
            this.maybeNumCylinders = maybeNumCylinders;
        }

        public Vehicle(String name, Integer numWheels, Optional<Integer> maybeNumCylinders) {
            this.name = name;
            this.numWheels = numWheels;
            this.maybeNumCylinders = maybeNumCylinders;
        }

        public String getName() {
            return name;
        }

        public Integer getNumWheels() {
            return numWheels;
        }

        public Optional<Integer> getMaybeNumCylinders() {
            return maybeNumCylinders;
        }

        public VehicleBuilder toBuilder() {
            VehicleBuilder baseVehicleBuilder = new VehicleBuilder(name).numWheels(numWheels);
            return maybeNumCylinders.map(baseVehicleBuilder::numCylinders).orElse(baseVehicleBuilder);
        }

        public String vroom() {
            final String baseVroom = "Here goes " + name + " with its " + numWheels + " wheels";

            return maybeNumCylinders
                    .map(numCylinders -> baseVroom + " and its " + numCylinders + " cylinders")
                    .orElse(baseVroom);
        }
    }

    static void usingConstructors() {
        Vehicle a = new Vehicle("Pinto");
        System.out.println(a.vroom());

        Vehicle b = new Vehicle("Pinto", 3);
        System.out.println(b.vroom());

        Vehicle c = new Vehicle("Pinto", Optional.of(4));
        System.out.println(c.vroom());

        Vehicle d = new Vehicle("Pinto", 3, Optional.of(4));
        System.out.println(d.vroom());
    }

    static class VehicleBuilder {
        private final String name;
        private Integer numWheels = 4;
        private Optional<Integer> maybeNumCylinders = Optional.empty();

        public VehicleBuilder(String name) {
            this.name = name;
        }

        public VehicleBuilder numWheels(Integer numWheels) {
            this.numWheels = numWheels;
            return this;
        }

        public VehicleBuilder numCylinders(Integer numCylinders) {
            this.maybeNumCylinders = Optional.of(numCylinders);
            return this;
        }

        public Vehicle build() {
            return new Vehicle(name, numWheels, maybeNumCylinders);
        }
    }

    static void usingBuilder() {
        Vehicle a = new VehicleBuilder("Pinto").build();
        System.out.println(a.vroom());

        Vehicle b = new VehicleBuilder("Pinto").numWheels(3).build();
        System.out.println(b.vroom());

        Vehicle c = new VehicleBuilder("Pinto").numCylinders(4).build();
        System.out.println(c.vroom());

        Vehicle d = new VehicleBuilder("Pinto").numWheels(3).numCylinders(4).build();
        System.out.println(d.vroom());
    }

}
