package thread;

public class Consumator extends Thread {
	int item;
	
	@Override
	public void run() {
		try {
			while(true) {
				
				//Main.semFull.acquire();
				//Main.s.acquire();
				
				//item = Main.list.getFirst();
				while(Main.list.isEmpty() == true) {
					System.out.println("Lista este goala");
					synchronized (Main.conditieConsumatori) {
					Main.conditieConsumatori.wait();
					}
				}
					System.out.println("A fost consumat elementul" + Main.list.getFirst());
					System.out.println(" ");
					Main.list.remove();
				//Main.s.release();
				//Main.semFree.release();
				
				synchronized (Main.conditieProducatori) {
					Main.conditieProducatori.notify();
				}
				
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
