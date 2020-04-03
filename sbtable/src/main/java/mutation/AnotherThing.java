package mutation;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.agent.ByteBuddyAgent;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;
import net.bytebuddy.matcher.ElementMatchers;

import java.util.Random;

public class AnotherThing {

    public static final Integer toAdd = 3;

    static class MyAdvice {
        @Advice.OnMethodEnter
        public static void enter() {
            Something.current = new Random().nextInt();
        }
    }

    static {
        ByteBuddyAgent.install();

        new ByteBuddy()
                .rebase(Something.class)
                .method(ElementMatchers.isDeclaredBy(Something.class))
                .intercept(Advice.to(MyAdvice.class))
                .make()
                .load(Something.class.getClassLoader(), ClassReloadingStrategy.fromInstalledAgent());
    }

}
