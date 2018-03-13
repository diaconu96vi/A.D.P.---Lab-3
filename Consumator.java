package thread;

public class Consumator extends Thread {
	int item;
	
	@Override
	public void run() {
		try {
			while(true) {
				Main.lock.lock();
				Main.semFull.acquire();
				Main.s.acquire();
				item = Main.list.getFirst();
				while(Main.list.isEmpty() == true) {
					System.out.println("Lista este goala");
					Main.conditieConsumatori.await();
				}
					System.out.println("A fost consumat elementul" + Main.list.getLast());
					System.out.println(" ");
					Main.list.remove();
				Main.s.release();
				Main.semFree.release();
				Main.conditieProducatori.signal();
				Main.lock.unlock();
				consume(item);
				Thread.sleep(300);

				
			}
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void consume(int item) {
		item = 0;
	}
}
