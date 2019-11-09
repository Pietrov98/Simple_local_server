import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

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
	
	public void readTextfromKeyboard(String text)
	{
		/*System.out.println("Creating server socket on port " + portNumber);
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(portNumber);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			PrintWriter pw = new PrintWriter(os, true);
			pw.println(text);

			BufferedReader br = null;
			try {
				br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			} catch (IOException e) {
				e.printStackTrace();
			}
			String str = null;
			try {
				str = br.readLine(); //czytanie tekstu z socket'a
			} catch (IOException e) {
				e.printStackTrace();
			}

			pw.println(" Przeslana wiadomosc to: " + str);
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
			System.out.println("1.Pobierz screenshot od u¿ytkownia\n"
							 + "2.Pobierz tekst od u¿ytkownika\n"
							 + "3.Wylacz serwer");
			option = scan.nextInt();
			if(option == 2)
			{
				this.readTextfromKeyboard(" Podaj tekst: ");
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

}
