import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import Request.IRequest;
import Request.JPGRequest;
import Request.Request;
import Request.StringRequest;


public class Main {
	public static void main(String[] args) throws InterruptedException, IOException
	{
		final int portNumber = 78;
		//Server server = new Server (portNumber);
		//Client client = new Client ("localhost", portNumber);
		Thread t1 = new Thread(new Server (portNumber));
		Thread t2 = new Thread(new Client ("localhost", portNumber));
		t1.start();	
		t2.start();
		
		
	   /* byte[] text = "0to jest jakis ciekawy tekst".getBytes();
	    byte[] picture = "1to jest jakies ciekawe zdjecie".getBytes();
	    StringRequest text1 = new StringRequest();
	    JPGRequest picture1 = new JPGRequest();
	    IRequest n = text1.handleRequest(text);
		System.out.println(((Request) n).getData());
		byte[] pic2 = text1.code("nowe zdjecie");
		IRequest n1 = text1.handleRequest(pic2);
		System.out.println(((Request) n1).getData());*/
	}
}
