package thread;

import java.util.Random;

public class Producator extends Thread {

	public int randomElement;
	public int item;

	@Override
	public void run() {
		try {
			while (true) {
				synchronized(Main.lock) {
					item = produce();
					Thread.sleep(150);
					
					
					//Main.semFree.acquire();
					//Main.s.acquire();
	
					while (Main.list.size() == Main.listSize) {
						System.out.println("Lista este plina");
						synchronized (Main.conditieProducatori) {
							Main.conditieProducatori.wait();
						}
					}
	
					Main.list.add(item);
					System.out.println("A fost produs elementul " + item);
					System.out.println(" ");
	
					 //Main.semFull.release();
					 //Main.s.release();
				}
				synchronized(Main.conditieConsumatori) {
					Main.conditieConsumatori.notify();
				}
					
				
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public int produce() {
		randomElement = new Random().nextInt(10);
		return randomElement;
	}
}
