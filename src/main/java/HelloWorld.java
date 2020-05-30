public class HelloWorld {
    static {
        System.loadLibrary("bridge");
    }

    public static void main(String[] args) {
        sayHello();
    }

    private static native void sayHello();
}
