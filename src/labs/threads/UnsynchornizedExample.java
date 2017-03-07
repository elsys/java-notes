package labs.threads;

public class UnsynchornizedExample{
	final static int THREADS_COUNT=50;
	final static int CYCLES=1000000;

	public long value=0;

	public static class Incrementor implements Runnable{
		private UnsynchornizedExample ute;
		public Incrementor(UnsynchornizedExample ute){
			this.ute=ute;
		}
		public void run(){
			for(int i=0;i<CYCLES;i++){
				ute.value++;
			}
		}
	}

	public static void main(String[] args){
		UnsynchornizedExample ute
				=new UnsynchornizedExample();
		Thread[] threads=new Thread[THREADS_COUNT];
		for(int i=0;i<THREADS_COUNT;i++){
			threads[i]=new Thread(new Incrementor(ute));
		}
		for(int i=0;i<THREADS_COUNT;i++){
			threads[i].start();
		}
		try{
			for(int i=0;i<THREADS_COUNT;i++){
				threads[i].join();
			}
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		System.out.println("value="+ute.value);
	}
}
