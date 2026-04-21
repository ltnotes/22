import java.util.Scanner;

class Shared {
    int a, b;
    boolean ready = false;

    synchronized void produce(int x, int y) {
        a = x; b = y;
        ready = true;
        notifyAll();
    }

    synchronized void consume() {
        while (!ready)
            try { wait(); } catch (Exception e) {}

        System.out.println("Addition: " + (a + b));
        System.out.println("Subtraction: " + (a - b));
        System.out.println("Multiplication: " + (a * b));
        System.out.println("Division: " + (a / b));
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Shared s = new Shared();

        System.out.print("Enter first number: ");
        int a = sc.nextInt();
        System.out.print("Enter second number: ");
        int b = sc.nextInt();

        new Thread(() -> s.produce(a, b)).start();
        new Thread(() -> s.consume()).start();

        sc.close();
    }
}
