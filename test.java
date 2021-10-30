


	import java.net.*;
import java.io.*;
	public class test {
		 Socket as ;
	public static void main(String[] args) throws
	IOException 
	{
//	Set up the local variables
	ServerSocket serverSocket = null;
//	 Make the server socket
	try {
		serverSocket = new ServerSocket(2333);
	} catch (IOException e) {
	System.err.println("Could not listen on port:4444.");
	System.exit(-1);
	}
	 new ChatThread(4444,"127.0.0.1","Hamza","Salah","l").start();
	 System.out.println("end");
	   serverSocket.close();
	}
	}