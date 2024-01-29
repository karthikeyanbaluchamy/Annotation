package reflectionUsecase;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class JdkProxyDemo {

    interface If {
        String originalMethod(String s,int i,String d);
    }

    static class Original implements If {
        public String originalMethod(String s,int i,String d) {
            System.out.println(s+" "+i+" "+d);
            return s+" "+i;
        }
    }

    static class Handler implements InvocationHandler {

        private final If original;

        public Handler(If original) {
            this.original = original;
        }
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("BEFORE");
            String d="done";
            System.out.println(Arrays.toString(args));
            args[2]=d;

            System.out.println(method.getName());
           Object res= method.invoke(original, args);
            System.out.println("AFTER");
            return res;
        }
    }

    public static void main(String[] args) {
        Original original = new Original();
        Handler handler = new Handler(original);
        System.out.println(If.class.getClassLoader());
        If f = (If) Proxy.newProxyInstance(If.class.getClassLoader(),
                new Class[] { If.class },
                handler);
        f.originalMethod("Hallo",21,"");
    }
}
