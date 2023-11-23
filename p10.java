import java.util.concurrent.Semaphore;

class Philosopher extends Thread {
    private Semaphore leftFork;
    private Semaphore rightFork;
    private int philosopherId;
    private volatile boolean running = true;

    public Philosopher(Semaphore leftFork, Semaphore rightFork, int philosopherId) {
        this.leftFork = leftFork;
        this.rightFork = rightFork;
        this.philosopherId = philosopherId;
    }

    public void run() {
        while (running) {
            try {
                System.out.println("Philosopher " + philosopherId + " is thinking.");
                Thread.sleep(1000);

                leftFork.acquire();
                System.out.println("Philosopher " + philosopherId + " picks up the left fork.");

                rightFork.acquire();
                System.out.println("Philosopher " + philosopherId + " picks up the right fork and eats.");
                Thread.sleep(1000); 

                leftFork.release();
                System.out.println("Philosopher " + philosopherId + " puts down the left fork.");

                rightFork.release();
                System.out.println("Philosopher " + philosopherId + " puts down the right fork and finishes eating.");

                stopPhilosopher(); 
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void stopPhilosopher() {
        running = false;
        interrupt();
    }
}

public class p10 {
    public static void main(String[] args) {
        int numPhilosophers = 5;
        Semaphore[] forks = new Semaphore[numPhilosophers];

        for (int i = 0; i < numPhilosophers; i++) {
            forks[i] = new Semaphore(1);
        }

        Philosopher[] philosophers = new Philosopher[numPhilosophers];

        for (int i = 0; i < numPhilosophers; i++) {
            Semaphore leftFork = forks[i];
            Semaphore rightFork = forks[(i + 1) % numPhilosophers];
            philosophers[i] = new Philosopher(leftFork, rightFork, i);
        }

        for (Philosopher philosopher : philosophers) {
            philosopher.start();
        }
    }
}