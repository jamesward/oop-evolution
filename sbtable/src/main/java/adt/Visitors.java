package adt;

public class Visitors {

    public static void main(String[] args) {
        CodeStyles style = new CodeStyles();
        style.visit(new Tab());
        style.visit(new Space());
        style.visit(new Space());

        System.out.println(style.correct + "right");
    }

    interface Style {
        void visit(Tab tab);
        void visit(Space space);
    }

    static class Tab { }

    static class Space { }

    static class CodeStyles implements Style {
        public String correct = "";

        @Override
        public void visit(Tab tab) {
            correct += "  ";
        }

        @Override
        public void visit(Space space) {
            correct += " ";
        }
    }
}
