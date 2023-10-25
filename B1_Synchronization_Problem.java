// Vedant Kokane - 31430
import java.util.Vector;
import java.util.concurrent.Semaphore;

class Boundedbuffer{
    Vector<Integer> buffer;
    Semaphore empty,mutex,full;
    Boundedbuffer(int size){
        buffer = new Vector<>(size);
        mutex = new Semaphore(1);
        empty = new Semaphore(size);
        full = new Semaphore(0);
    }
    
    public void produce(int item)throws InterruptedException{
        empty.acquire();
        mutex.acquire();
        buffer.add(item);
        mutex.release();
        full.release();
    }
    
    public int consume()throws InterruptedException{
        full.acquire();
        mutex.acquire();
        int item = buffer.firstElement();
        buffer.removeElementAt(0);
        mutex.release();
        empty.release();
        return item;
    }
}

class Producer extends Thread{
    Boundedbuffer buffer;
    Producer(Boundedbuffer buffer){
        this.buffer = buffer;
    }
    
    public void run(){
        try{
            for(int i=0;i<10;i++){
                buffer.produce(i);
                System.out.println("Produced " + i);
                Thread.sleep(100);
            }
        }
        catch(InterruptedException e){
            Thread.currentThread().interrupt();
        }
    }
}

class Consumer extends Thread{
    Boundedbuffer buffer;
    Consumer(Boundedbuffer buffer){
        this.buffer = buffer;
    }
    
    public void run(){
        try{
            for(int i=0;i<10;i++){
                int item = buffer.consume();
                Thread.sleep(100);
                System.out.println("Consumed " + item);
            }
        }
        catch(InterruptedException e){
            Thread.currentThread().interrupt();
        }
    }
}

public class Main
{
	public static void main(String[] args) {
		Boundedbuffer buffer = new Boundedbuffer(5);
		Producer producer1 = new Producer(buffer); 
		Consumer consumer1 = new Consumer(buffer); 
		Producer producer2 = new Producer(buffer); 
		Consumer consumer2 = new Consumer(buffer); 
		
		
		producer1.start();
		consumer1.start();
		producer2.start();
		consumer2.start();
	}
}
