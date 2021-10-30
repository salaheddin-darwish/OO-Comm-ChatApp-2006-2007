
/*
Class kkmsthread taken from KKMultiServerThread online
in Campione, M. and Walthrath, K (1998) The Java
Tutorial http://java.sun.com/docs/books/tutorial
/networking/sockets/
example-1dot1/KKMultiServerThread.java
Modified by Simon Taylor (1998) for CS3024A NODS
I modified by salaheddin  Drwish for the assignment of object oriented communication 

*/

import java.net.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.io.*;
public class Brokerthread extends Thread {
		// Variable declarations
		private Socket socket = null;
		private String AgentName ="";
		public static ArrayList<AgentRecord> v; 
		public static  AgentRecord Ar = new AgentRecord(null, null)  ;
		ArrayList<AgentRecord> LA = new ArrayList<AgentRecord> ();
		//Constructor method
    	public Brokerthread(Socket socket)
		{
			super("Brokerthread");
			this.socket = socket;
		}
		public void run() 
		{
			try 
			{    // sender side
				PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
				// reciever Side
				BufferedReader in = new BufferedReader( new InputStreamReader(socket.getInputStream()));
				
				String inputLine ="", 
				       outputLine ="" ,
				       ip         = "";
				    	   ;
				
						
				BrokerState kks = new BrokerState();
				outputLine = kks.processInput(null);
				out.println(outputLine);
				ip =socket.getInetAddress().getHostAddress().toString();
				//System.out.println(inputLine);
				System.out.println(ip+" is connected with broker" );
			l:	while ((inputLine = in.readLine()) != null) 
				{
				 System.out.println("recieve from Agent  "+AgentName+":"+ inputLine);
				   
				 outputLine = kks.processInput(inputLine);
				  
				 System.out.println("state from Broker : " + outputLine);
				  
			  	if (outputLine.equals("REG")) 
					{
						if (! Broker.AgentsDB.nameExists(inputLine))  
						{  
							try
							{
							Broker.AgentsDB.insertAgent(ip ,inputLine );
							System.out.println(inputLine+" is registered");
							AgentName = inputLine;
							
							} catch (InterruptedException e)
							{ System.err.println(e);}
							out.println("SR");
							outputLine = kks.processInput("SR");
							
						} 
					else  
						{
						   out.println("USR");
						   System.out.println("unsuccessful Registration");  
						   outputLine = kks.processInput("USR");
						 }
					} 	
					else 
					{
					  if (outputLine.equals("BUSY")) // talker 
						{
						      
						      Broker.AgentsDB.upadateStatus(AgentName , false); // make him busy 
						      continue l ; // continue the While Loop 
						}
					  else if (outputLine.equals("FREELISTENER"))  //make him free listener who need t interact
					    	
					  {     
						    Broker.AgentsDB.upadateStatus(AgentName , true); // make him free 
						      continue l ; // continue the While Loop 
					  }
					  else if (outputLine.equals("AGENTS-LIST"))
							
						{
					           v = Broker.AgentsDB.searchAvailabelAgents() ;
						          Iterator<AgentRecord> iterator = v.iterator();
								 if ( v.isEmpty() )
								 { 
									 outputLine = kks.processInput("EMPTYLIST");
								    out.println("EMPTYLIST") ;
								    // System.out.println("EMPTYLIST");
								 }
								else{ 
						            out.println("ls"); // send a sign for the agent to start send the list
						            // delay the process of sending the list 
								 
								     try{sleep(2000);}
						            catch (InterruptedException e){ System.err.println(e);}
								
						            while (iterator.hasNext())
									 { 
						        	   System.out.println("list");
						        	   Ar = iterator.next();
									   System.out.println(Ar.getName());
									   out.println(Ar.getName());
									 }
						        out.println("le");
						        continue l ;
								}
						  } 
					   else if (outputLine.equals("CHECKAVAILABLILITY"))
						  {
						    if ( Broker.AgentsDB.isAvailable(inputLine)) 
						     {
						    	try{
						    	    Ar= Broker.AgentsDB.getAgentDetails(inputLine);
						    	    Broker.AgentsDB.upadateStatus(inputLine, false); // the state of chosen agent
								    } catch (InterruptedException e){ System.err.println(e);}
								    
							     out.println(Ar.getIp());//send Ip to the agent 
							     outputLine = kks.processInput("as"); 
						     } else
						        {
								  out.println("NF"); //send Not Free for a chosen Agent to the agent
								
								  outputLine = kks.processInput("uas");
						        }
						  }
					  else if (outputLine.equals("EXIT"))
								  
								  {
							       out.println("exit");
							       Broker.AgentsDB.deleteAgent(AgentName); 
							       //delete information of this agent because of exit						       
							       break;
							      
								  }
				     } 
						  
														
				} // end loop 

				System.out.println("End session with Agnet "+AgentName);	
				out.close();
				in.close();
				socket.close();
			} 
			catch (SocketException e )
			{   
				System.err.println(AgentName +" Thread Shuts Down ....");
				Broker.AgentsDB.deleteAgent(AgentName); //delete agent 
			}
			catch (IOException e) { //System.err.println("Thread Shuts Down ...."); 
			e.printStackTrace();
			System.err.println(AgentName +" Thread Shuts Down ....\n "+e);
			  Broker.AgentsDB.deleteAgent(AgentName); //delete agent 
}
}
}