import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class JNIReflectionBenchmark {
    static {
        System.loadLibrary("bridge");
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        for (int i = 0; i < 10000000; i++) {
            accessReflective();
        }

        long middle = System.currentTimeMillis();

        for (int i = 0; i < 10000000; i++) {
            accessNative();
        }

        long end = System.currentTimeMillis();

        System.out.println("Results:");
        System.out.println("\tReflective: " + (middle - start) + "ms");
        System.out.println("\tNative: " + (end - middle) + "ms");
    }

    private static void accessReflective() {
        try {
            Method method = JNIReflectionBenchmark.class.getMethod("getData");
            method.setAccessible(true);
            String string = (String) method.invoke(null);

            if (!string.equals("Reflection vs JNI")) {
                throw new AssertionError();
            }
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private static void accessNative() {
        String string = (String) executeMethod("getData");

        if (!string.equals("Reflection vs JNI")) {
            throw new AssertionError();
        }
    }

    private native static Object executeMethod(String methodName);

    public static String getData() {
        return "Reflection vs JNI";
   }
}
