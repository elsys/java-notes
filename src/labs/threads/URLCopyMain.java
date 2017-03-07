package labs.threads;

public class URLCopyMain{
	public static void main(String argv[]){
		String[][] fileList={
{
"http://www.nps.gov/imr/pgallerycontent/p/l/20071129184415.jpg",
"Bryce.jpg"},
{
"http://microscopy.fsu.edu/micro/gallery/dinosaur/dino1.jpg",
"dino1.jpg"},
{
"http://java.sun.com/docs/books/tutorial/index.html",
"tutorial.index.html"}};
		
		for(int i=0;i<fileList.length;i++){
			Thread th;
			String threadName="T"+i;
			String fromURL=fileList[i][0];
			String toFile=fileList[i][1];
			
			th=new URLCopyThread(threadName,fromURL,toFile);
			th.start();
			System.err.println("Thread "+th.getName()
					+" to copy from "+fileList[i][0]+" to "
					+fileList[i][1]+" started");
		}
	}
}
