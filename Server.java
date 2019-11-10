/*import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.Scanner;

import Request.IRequest;
import Request.StringRequest;

public class Server implements Runnable {

	final int portNumber;
	private ServerSocket serverSocket;
	
	public Server(int portNumber) 
	{
		System.out.println("Creating server socket on port " + portNumber);

		this.portNumber = portNumber;
		this.serverSocket = null;
		try {
			serverSocket = new ServerSocket(portNumber);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void readTextfromKeyboard(String text, IRequest obj) throws IOException
	{
			Socket socket = null;
			try {
				socket = serverSocket.accept();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			OutputStream os = null;
			try {
				os = socket.getOutputStream(); //otwieranie przejscia
			} catch (IOException e) {
				e.printStackTrace();
			}
			PrintWriter pw = new PrintWriter(os, true);
			pw.println(text);

			/*BufferedReader br = null;
			try {
				br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			} catch (IOException e) {
				e.printStackTrace();
			}
			byte[] str = null;
			
			InputStream in	 = null; 
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			byte buffer[] = new byte[1024];
			baos.write(buffer, 0 , in.read(buffer));
			
			byte result[] = baos.toByteArray();

			String res = Arrays.toString(result);
			System.out.println("Recieved from client : "+res); 
			/*try {
				str = br.readFully(); //czytanie tekstu z socket'a
			} catch (IOException e) {
				e.printStackTrace();
			}

			pw.println(" Przeslana wiadomosc to: " + obj.handleRequest(result));
			pw.close();
			try {
				socket.close(); //zamykanie przejscia
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	@Override
	public void run() 
	{
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		Scanner scan = new Scanner(System.in);
		int option;
		while(true)
		{
			System.out.println("1.Pobierz screenshot od u퓓tkownia\n"
							 + "2.Pobierz tekst od u퓓tkownika\n"
							 + "3.Wylacz serwer");
			option = scan.nextInt();
			if(option == 2)
			{
				try {
					this.readTextfromKeyboard(" Podaj tekst: ", new StringRequest());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else if(option == 3)
				break;
		}
		try {
			serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.exit(1);
	
	}

}*/

//A Java program for a Server 
import java.net.*;
import java.util.Arrays;
import java.util.Scanner;

import Request.IRequest;
import Request.JPGRequest;
import Request.Request;
import Request.StringRequest;

import java.io.*; 

public class Server implements Runnable
{ 
	//initialize socket and input stream 
	private Socket		 socket = null; 
	private ServerSocket server = null; 
	private InputStream in	 = null; 
	private OutputStream out = null;
	private int port;

	// constructor with port 
	public Server(int port) 
	{ 
		this.port = port;
		
	}

	@Override
	public void run() 
	{
		try
		{ 
			server = new ServerSocket(port); 
			System.out.println("Server started"); 

			System.out.println("Waiting for a client ..."); 

			socket = server.accept(); 

			System.out.println("Client accepted"); 

			// takes input from the client socket 
			in = new DataInputStream(socket.getInputStream()); 
			//writes on client socket
			out = new DataOutputStream(socket.getOutputStream());
			while(true)
			{
				Scanner scan = new Scanner(System.in);
				int option;
				System.out.println("1.Pobierz screenshot od u퓓tkownia\n"
						 + "2.Pobierz tekst od u퓓tkownika\n"
						 + "3.Wylacz serwer");
				option = scan.nextInt();
				byte[] arr = null;
				IRequest request = (Request) new StringRequest();
				if(option == 2)
				{
					System.out.println("Wpisz tekst");
					arr = "0Wpisz tekst".getBytes();
				}
					
				else if(option == 1)
				{
					System.out.println("Przeslij screenshot");
					arr = "0Przeslij screenshot".getBytes();
					request = (Request) new JPGRequest();
				}
				out.write(arr);
				
				// Receiving data from client
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				byte buffer[] = new byte[1024];
				baos.write(buffer, 0 , in.read(buffer));

				byte result[] = baos.toByteArray();

				String res = Arrays.toString(result);
				request = (IRequest) request.handleRequest(result); 
				//echoing back to client
				//out.write(result);
				// close connection 
				if(option == 3)
					break;
			}
			socket.close(); 
			in.close(); 
			
		} 
		catch(IOException i) 
		{ 
			System.out.println(i); 
		} 
		
	} 
}
