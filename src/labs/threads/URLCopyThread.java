package labs.threads;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class URLCopyThread extends Thread{
	private URL fromURL;
	private BufferedInputStream input;
	private BufferedOutputStream output;
	private String from,to;

	public URLCopyThread(String n,String f,String t){
		super(n);
		from=f;
		to=t;
		try{
			fromURL=new URL(from);
			input=new BufferedInputStream(fromURL.openStream());
			output=new BufferedOutputStream(
					new FileOutputStream(to));
		}catch(MalformedURLException m){
			System.err.println("MalformedURLException "
					+" creating URL "+from);
		}catch(IOException io){
			System.err.println("IOException "+io.toString());
		}
	}

	public void run(){
		byte[] buf=new byte[512];
		int nread;
		try{
			while((nread=input.read(buf,0,512))>0){
				output.write(buf,0,nread);
				System.err
						.println(getName()+": "+nread+" bytes");
			}
			input.close();
			output.close();
			System.err.println("Thread "+getName()+" copying "
					+from+" to "+to+" finished");
		}catch(IOException ioe){
			System.out.println("IOException:"+ioe.toString());
		}
	}
}
