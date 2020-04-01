package builders;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Combinators {

    public static void main(String[] args) {

        VehicleBuilders.Vehicle vehicle1 = new VehicleBuilders.Vehicle("Volvo", Optional.of(4));
        VehicleBuilders.Vehicle vehicle2 = new VehicleBuilders.Vehicle("Ford", Optional.of(8));
        VehicleBuilders.Vehicle vehicle3 = new VehicleBuilders.Vehicle("GM", Optional.of(12));

        Predicate<Integer> v8 = i -> i == 8;
        Predicate<Integer> beefy = i -> i > 8;

        Optional<Integer> nope = vehicle1.getMaybeNumCylinders().filter(v8);
        Optional<Integer> yup = vehicle3.getMaybeNumCylinders().filter(beefy);

        Predicate<Integer> v8OrBeefy = v8.or(beefy);

        Optional<Integer> sweet = vehicle2.getMaybeNumCylinders().filter(v8OrBeefy);
        Optional<Integer> awesome = vehicle3.getMaybeNumCylinders().filter(v8OrBeefy);
    }

}

