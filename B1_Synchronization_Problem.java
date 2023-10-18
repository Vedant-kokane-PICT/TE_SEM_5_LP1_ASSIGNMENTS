// Vedant Kokane - 31430
import java.util.Vector;
import java.util.concurrent.Semaphore;

class BoundedBuffer {
    private Vector<Integer> buffer;
    private Semaphore empty;
    private Semaphore full;
    private Semaphore mutex;

    public BoundedBuffer(int size) {
        buffer = new Vector<>(size);
        empty = new Semaphore(size);
        full = new Semaphore(0);
        mutex = new Semaphore(1);
    }

    public void produce(int item) throws InterruptedException {
        empty.acquire();
        mutex.acquire();
        buffer.add(item);
        mutex.release();
        full.release();
    }

    public int consume() throws InterruptedException {
        full.acquire();
        mutex.acquire();
        int item = buffer.firstElement();
        buffer.removeElementAt(0);
        mutex.release();
        empty.release();
        return item;
    }
}

class Producer extends Thread {
    private BoundedBuffer buffer;

    public Producer(BoundedBuffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                buffer.produce(i);
                Thread.sleep(100); // Simulate work
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

class Consumer extends Thread {
    private BoundedBuffer buffer;

    public Consumer(BoundedBuffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                int item = buffer.consume();
                Thread.sleep(100); // Simulate work
                System.out.println("Consumed: " + item);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

public class Main {
    public static void main(String[] args) {
        BoundedBuffer buffer = new BoundedBuffer(5);
        Producer producer1 = new Producer(buffer);
        Producer producer2 = new Producer(buffer);
        Consumer consumer1 = new Consumer(buffer);
        Consumer consumer2 = new Consumer(buffer);

        producer1.start();
        producer2.start();
        consumer1.start();
        consumer2.start();
    }
}
