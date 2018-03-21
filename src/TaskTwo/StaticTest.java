package TaskTwo;

public interface StaticTest {
    int k = 0;
    default void printK(){
        System.out.println(k);
    }
    static void keyx(){
        System.out.println("StaticShit");
    };
}
