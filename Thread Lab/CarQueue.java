import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class CarQueue {

    private BlockingQueue<Integer> queue = new LinkedBlockingQueue<>();
    private Random rand = new Random();

    public CarQueue() {
        for (int i = 0; i < 6; i++) {
            queue.add(rand.nextInt(4));
        }
    }

    public void addToQueue() {
        class Producer implements Runnable {
            public void run() {
                try {
                    while (true) {
                        queue.put(rand.nextInt(4));
                        Thread.sleep(500);
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }

        Thread t = new Thread(new Producer());
        t.start();
    }

    public int deleteQueue() {
        try {
            return queue.take();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return 2;
        }
    }
}
