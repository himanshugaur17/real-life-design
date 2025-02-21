import javax.management.RuntimeErrorException;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        try {
            throw new RuntimeException("ji");
        } catch (Exception e) {
            System.out.println("this got run:" + e.getMessage());
        }
        System.out.println("this did not");
    }
}
