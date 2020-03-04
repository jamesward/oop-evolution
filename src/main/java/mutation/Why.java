package mutation;

import static mutation.Something.addOne;

public class Why {

    public static void main(String[] args) {

        Integer i = addOne(AnotherThing.toAdd);

        Integer j = addOne(3);

        Integer k = addOne(3);

        Integer l = addOne(3);

        Integer m = addOne(3);
    }

}
