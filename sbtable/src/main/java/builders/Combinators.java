package builders;

import java.util.Optional;
import java.util.function.Predicate;

public class Combinators {

    public static void main(String[] args) {
        VehicleBuilders.Vehicle vehicle1 = new VehicleBuilders.Vehicle("Volvo", Optional.of(4));
        VehicleBuilders.Vehicle vehicle2 = new VehicleBuilders.Vehicle("Ford", Optional.of(8));
        VehicleBuilders.Vehicle vehicle3 = new VehicleBuilders.Vehicle("GM", Optional.of(12));

        Predicate<Integer> balanced = i -> i % 2 == 0;
        Predicate<Integer> beefy = i -> i > 8;

        Optional<Integer> yup = vehicle1.getMaybeNumCylinders().filter(balanced);
        Optional<Integer> nope = vehicle1.getMaybeNumCylinders().filter(beefy);

        Predicate<Integer> balancedOrBeefy = balanced.or(beefy);

        Optional<Integer> sweet = vehicle2.getMaybeNumCylinders().filter(balancedOrBeefy);
        Optional<Integer> awesome = vehicle3.getMaybeNumCylinders().filter(balancedOrBeefy);
    }

}

