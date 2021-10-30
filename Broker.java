/*
Class kkmsserver taken from KKMultiServer online in
Campione, M. and Walthrath, K (1998) The Java Tutorial
http://java.sun.com/docs/books/tutorial/networking/sock
ets/example-1dot1/KKMultiServer.java
Modified by Simon Taylor (1998) for CS3024A NODS
I modified to implement my assignment (salaheddin darwish ) 
*/

import java.net.*;
import java.io.*;
public class Broker {
public static AgentsDetails AgentsDB = new AgentsDetails (); 
public static void main(String[] args) throws
IOException 
{ 
//Set up the local variables
ServerSocket serverSocket = null;
boolean listening = true;
// Make the server socket
try {
	serverSocket = new ServerSocket(4444);
	} catch (IOException e) {
System.err.println("Could not listen on port:4444.");
System.exit(-1);
}

/*Whenever a client attempts to connect on the serversocket setup above, setup a new connection.
Do this forever The new connections are made by starting a new execution thread Brokerthread. 

The argument serverSocket.accept means that the thread will 
connect to whichever client demanded the connection The start command just tells the kkmsthread object
to begin execution.Every thread object has a run() method instead of a main() method. Start() just tells the object to use
the run() method to begin execution. These threads will be timesliced by the operating system
*/
System.out.println("Broker Starts Up on port 4444  .... ");
while (listening){
try {
	new Brokerthread(serverSocket.accept()).start();
	} catch (SocketException e )
	{    System.err.println("Server Shuts Down ....");
		System.exit(1);
	}

}
serverSocket.close();
}
}