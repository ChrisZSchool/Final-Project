import java.util.Random;

public class Math implements Problem {

    public static int target;

    @Override
    public String generateProblem() {
        Random r = new Random(System.currentTimeMillis());
        int a = r.nextInt(100);
        int b = r.nextInt(100);
        target = a + b;
        return String.format("%d + %d = ?", a, b);
    }

    public int generateRandomNumber() {
        Random r = new Random();
        return r.nextInt(100);
    }

    public int generateRandomNumber(int bound) {
        Random r = new Random();
        return r.nextInt(bound);
    }

}
