package thread;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.LinkedList;

public class Main {
	public static int listSize = 10;
	public static LinkedList<Integer> list = new LinkedList<Integer>();
	public static Semaphore semFree = new Semaphore(5);
	public static Semaphore semFull = new Semaphore(0);
	public static Semaphore s = new Semaphore(1);
	public static final Lock lock = new ReentrantLock();
    public static final Condition conditieProducatori = lock.newCondition();
    public static final Condition conditieConsumatori = lock.newCondition();

	
	public static void main(String[] args) {
		int i;
		/*Scanner sc = new Scanner(System.in);
		System.out.println("Introduceti nr. de thread-uri");
		int threadsNumber = sc.nextInt();
		*/
		int threadsNumber = 5;
		//Producator producator = new Producator();
		Producator[] producator = new Producator[threadsNumber];
		Consumator[] consumator = new Consumator[threadsNumber];
		
		for(i=0;i<threadsNumber;i++) {
			producator[i] = new Producator();
			consumator[i] = new Consumator();
			
			producator[i].start();
			consumator[i].start();
		}
		 System.out.println("Incepe simularea...");
		 System.out.println(" ");


	 for(i=0;i<threadsNumber;i++) {
		 try {
			producator[i].join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 try {
			consumator[i].join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
	 
	
	}

}
