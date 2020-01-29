package mutation;

public class Something {

    public static Integer current = 0;

    static Integer addOne(Integer i) {
        current++;

        if (current == 3) {
            return null;
        }
        else if (current == 4) {
            throw new IllegalStateException();
        }
        else {
            return i + current;
        }
    }

}
