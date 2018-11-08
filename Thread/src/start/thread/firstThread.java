package start.thread;

public class firstThread extends Thread{

	@Override
	public void run() {
		for(int i = 0 ; i < 100; i++) {
			System.out.println("Ìı¸è");
		}
		
	}
	public static void main(String[] args) {
		Thread thread = new Thread();
		thread.start();
		for(int i = 0 ; i < 100; i++) {
			System.out.println("ÇÃ´úÂë");
		}
		
	}
	
}
