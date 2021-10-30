

	import java.net.*;
import java.io.*;
	public class test1 {
		 Socket as ;
	public static void main(String[] args) throws
	IOException 
	{
//	Set up the local variables
	ServerSocket serverSocket = null;
//	 Make the server socket
	try {
		serverSocket = new ServerSocket(8888);
	} catch (IOException e) {
	System.err.println("Could not listen on port:4444.");
	System.exit(-1);
	}
	 new ChatThread(4444,"127.0.0.1","Salah","Hamza","r").start();
	  System.out.println("end");
	  serverSocket.close();
	}
	}