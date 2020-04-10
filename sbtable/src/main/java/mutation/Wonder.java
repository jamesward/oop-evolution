package mutation;

public class Wonder {

    static int addOne(final int i) {
        return i + 1;
    }

    public static void main(String[] args) {
        final int i = addOne(4);

        final int j = addOne(4);

        final int k = addOne(4);

        final int l = addOne(4);

        final int m = addOne(4);
    }

}
