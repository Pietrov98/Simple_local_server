/*import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.net.Socket;

import Request.IRequest;
import Request.JPGRequest;
import Request.Request;
import Request.StringRequest;

public class Client implements Runnable {

	final private int portNumber;
	
	public Client(int portNumber) 
	{
		this.portNumber = portNumber;
	}
	
	public IRequest makeAnObject(String option)
	{
		IRequest obj = (Request) new StringRequest();
		
		System.out.println(option);
		
		if(option.equals("Przeslij screenshot"))
		{
			obj = (Request) new JPGRequest();
		}	
		return obj;
		
	}
	
	@Override
	public void run()
	{
		final String host = "localhost";
		System.out.println("Creating socket to '" + host + "' on port " + portNumber);
		byte[] info;
		IRequest obj = (Request) new StringRequest();
		while (true) 
		{
			Socket socket = null;
			try {
				socket = new Socket(host, portNumber);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			BufferedReader br = null;
			try {
				br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			PrintWriter out = null;
			try {
				out = new PrintWriter(socket.getOutputStream(), true);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				System.out.println("server says:" + br.readLine());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			/*IRequest obj = null;
			try {
				obj = makeAnObject(br.readLine());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}*/
			
			/*IRequest obj = (Request) new StringRequest();
			try {
				if(br.readLine().equals("Przeslij screenshot"))
				{
					obj = (Request) new JPGRequest();
				}
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}	
			
			BufferedReader userInputBR = new BufferedReader(new InputStreamReader(System.in));
			String userInput = null;
			try {
				userInput = userInputBR.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			info = userInput.getBytes();
			if(userInput.equals("Podaj tekst"));
			{	
				try {
					info = new StringRequest().code(userInput);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			if(userInput.equals("Przeslij screenshot"));
			{
				try {
					info = new JPGRequest().code(userInput);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			out.println(info);
			try {
				System.out.println("server says:" + br.readLine());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if ("exit".equalsIgnoreCase(userInput)) {
				try {
					socket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			}
		}
		
	}

}*/

// A Java program for a Client 
import java.net.*;
import java.util.Arrays;
import java.util.Scanner;

import Request.IRequest;
import Request.Request;
import Request.StringRequest;

import java.io.*;

public class Client implements Runnable{
	// initialize socket and input output streams
	private Socket socket = null;
	private OutputStream out = null;
	private InputStream in = null;
	private String address;
	private int port;

	// constructor to put ip address and port
	public Client(String address, int port)
	{
		this.address = address;
		this.port = port;
		
	}

	@Override
	public void run() {
		try {
			socket = new Socket(address, port);
			if (socket.isConnected()) {
				System.out.println("Connected");
			}

			// sends output to the socket
			out = new DataOutputStream(socket.getOutputStream());
			//takes input from socket
			in = new DataInputStream(socket.getInputStream());
		} catch (UnknownHostException u) {
			System.out.println(u);
		} catch (IOException i) {
			System.out.println(i);
		}
 
		try {
			byte[] arr = null;

			// Receiving message from server
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			
			Scanner scanner = new Scanner(System.in);
			while(true)
			{
				byte buffer[] = new byte[1024];
				baos.write(buffer, 0 , in.read(buffer));

				byte result[] = baos.toByteArray();
				
				String res = Arrays.toString(result);
				IRequest request = (Request) new StringRequest();
				// printing reply to console
				//request.handleRequest(result);
				
				String input;
				
				input = scanner.next();
				if(input.charAt(0) != '0' && input.charAt(0) != '1')
					input = "0" + input;
				arr = input.getBytes();

				// sending data to server
				out.write(arr);
			}
			

			//String req = Arrays.toString(arr);
			//printing request to console
			//System.out.println("Sent to server : " + req);

		} catch (IOException i) {
			System.out.println(i);
		}
		// }

		// close the connection
		try {
			// input.close();
			in.close();
			out.close();
			socket.close();
		} catch (IOException i) {
			System.out.println(i);
		}
		
	}
}