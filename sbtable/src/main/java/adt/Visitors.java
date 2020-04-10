package adt;

import java.util.*;

public class Visitors {

    public static void main(String[] args) {
        Style toFormat = new Space();
        System.out.println("this is" + toFormat.accept(new StyleCode()) + "right");


        Style codeBlock = new CodeBlock(Collections.emptyList())
                .add(new Tab())
                .add(new Identifier("a"))
                .add(new Space())
                .add(new Tab())
                .add(new Identifier("b"));
        System.out.println(codeBlock.accept(new StyleCode()));


        System.out.println(codeBlock.accept(new StyleHorrible()));
    }

    interface StyleVisitor<A> {
        A visitTab(Tab tab);
        A visitSpace(Space space);
        A visitIdentifier(Identifier identifier);
        A visitCodeBlock(CodeBlock codeBlock);
    }

    interface Style {
        <A> A accept(StyleVisitor<A> styleVisitor);
        default Style add(Style other) {
            return new CodeBlock(Arrays.asList(this, other));
        }
    }

    static class Tab implements Style {
        @Override
        public <A> A accept(StyleVisitor<A> styleVisitor) {
            return styleVisitor.visitTab(this);
        }
    }

    static class Space implements Style {
        @Override
        public <A> A accept(StyleVisitor<A> styleVisitor) {
            return styleVisitor.visitSpace(this);
        }
    }

    static class Identifier implements Style {

        public final String name;

        public Identifier(String name) {
            this.name = name;
        }

        @Override
        public <A> A accept(StyleVisitor<A> styleVisitor) {
            return styleVisitor.visitIdentifier(this);
        }
    }

    static class CodeBlock implements Style {
        public final List<Style> styles;

        CodeBlock(List<Style> styles) {
            this.styles = styles;
        }

        @Override
        public <A> A accept(StyleVisitor<A> styleVisitor) {
            return styleVisitor.visitCodeBlock(this);
        }

        @Override
        public Style add(Style other) {
            List<Style> newStyles = new ArrayList<>(styles);
            newStyles.add(other);
            return new CodeBlock(newStyles);
        }
    }

    static class StyleCode implements StyleVisitor<String> {
        @Override
        public String visitTab(Tab tab) {
            return "  ";
        }

        @Override
        public String visitSpace(Space space) {
            return " ";
        }

        @Override
        public String visitIdentifier(Identifier identifier) {
            return identifier.name;
        }

        @Override
        public String visitCodeBlock(CodeBlock codeBlock) {
            return codeBlock.styles
                    .stream()
                    .map(style -> style.accept(this))
                    .reduce("", (a, b) -> a + b);
        }
    }

    static class StyleHorrible implements StyleVisitor<String> {
        List<String> emojis = Arrays.asList(
                "\uD83D\uDE02",
                "\u267E\uFE0F",
                "\uD83E\uDDFB"
        );

        @Override
        public String visitTab(Tab tab) {
            return emojis.get(new Random().nextInt(emojis.size()));
        }

        @Override
        public String visitSpace(Space space) {
            return " ";
        }

        @Override
        public String visitIdentifier(Identifier identifier) {
            return identifier.name;
        }

        @Override
        public String visitCodeBlock(CodeBlock codeBlock) {
            return codeBlock.styles
                    .stream()
                    .map(style -> style.accept(this))
                    .reduce("", (a, b) -> a + b);
        }
    }

}
