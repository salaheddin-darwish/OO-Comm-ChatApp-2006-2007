/* the class is used to build an agent that is used to make a communication between a 
 broker (server)  and a user and besides it makes avalible  to an agent to connect with another agent */

import java.io.*;
import java.net.*;
import java.util.*;


public class Agent 
{

	public static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	public static final int choosingAgentStatus =1;
	public static final int displayAgentsList =2;
	public static final int AvailabeAgent  =3;
	public static  int state = choosingAgentStatus ;
	public static boolean  exit = false ; // shut doen the system 
	public static Vector<String>  LV ;
	public static int portChat =4441;
	public static String fServer =" ";
	public static String  getUserName()
	{
		String UserN="";	// User name of client
	  
		try
		{
		do
		{
			System.out.print("Enter User Name: ");
			UserN = input.readLine();
			if (UserN.length()<3) System.out.print(" User Name is invalid , it should be more than 3 letters");
		}
		while (UserN.equals("")|| UserN.length()<3);  // repeat until valid user name is given	
		} 
		catch(IOException e) 
	 		{
		     System.err.println(e);
		     System.exit(1);
		    }
		
			return (UserN);
	}
	public static String  getUserStateChoice()
	{	String sc="";	//selecting any status that the agent want to 
	
		try
		{
			do
			 {
				 System.out.println("Enter What you want to be?  ");   
				 System.out.println("Press (1): I want to talk   ");  
				 System.out.println("Press (2): I want to listen  ");
				 System.out.println("Press (0): I want to shut down the application  ");
			     System.out.print("Choice: ");
			     sc = input.readLine();
				 if (!(sc.equals("1")||sc.equals("2")||sc.equals("0")))
				 {  //   inappropriate input
					 System.out.println("Incorrect choice , Enter Again");
					 sc="";
				 }
			  }while (sc.equals(""));  // repeat until valid user name is given
			
		} catch(IOException e) 
	 		{
		     System.err.println(e);
		    System.exit(1);
	 		}
		return sc ;
	} // end get choice 
    
public static void main(String []args)
		{
			
			// -------------------------------------------------------------------------------
			int port=4444 ,		// server port
			    agentChoice  ;   // agentChoice
			   
			String sPort="",		// server port
				   ip="",  			// IP of server
				   userName="" ,      // User Name
				   agentIP="" ,       // selected agent IP
			       selectedAgent ,    // selected agent name
			       inToServ  ;
			// Get ip, port & user name from the client
	
			// A0 .
			try
			{
				
    			// get IP from the user
				System.out.print("\n\nEnter IP of the server ( default IP 127.0.0.1): ");
				ip = input.readLine();
				if (ip.equals("")) ip = "127.0.0.1";	// default IP

				// get port from user
				System.out.print("Port Number (default port 4444): ");
				sPort = input.readLine();
				if (sPort.equals("")) 
					port = 4444;// default port
									
				else port = Integer.parseInt(sPort); 
				
    			
				 // create a new socket
				 Socket socket = new Socket(ip, port);
				
				 // to get data to and from server
				 BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				 PrintWriter pwr = new PrintWriter(socket.getOutputStream(), true);
				 // A1 .
			

				// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
				// IP, port and username is complete at this point
				// Now, create a socket to connect to server.
				// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
				
				   
				// Connection successfull at this point, so inform user about this
				   fServer =br.readLine();
				   System.out.println("You are "+fServer );
				   System.out.println("\n\n\t\tConnection is successful.\n\t\t----------------------");
			       // A2 .
				  // --------------------------------------------------------------
				 //	 get user name from the client		
				 userName = getUserName();
    			  if ( userName.toLowerCase().equals("exit"))  exit=true ; // user type exit to end his process
				    // send user name to the server
			     pwr.println(userName);	
				
			      // A3.
			     // -------------------------------------------------------------------------------
			     
			     while ((fServer = br.readLine()) != null && !exit  ) //A.4
			     {
			    	
				    if (fServer.equals("exit"))
				    	{
				    		exit=true ;// shut down the process of agents 
				    		 break ;
				       // A.5		 
				    	}
				    else if ( fServer.equals("SR") ) 
			    	{
			            // successfull registration on Server for the agent 
			    		System.out.println("\n\n\t\t The Registration is successful.\n\t\t---------------------------");
			    		System.out.println("\n\n\t\t Your User Name is "+userName+"\n\t\t+++++++++++++++++++++++");
			    		
			    		break;
			    	   //A.6
			    	} // end if
			    	
			    	else if (fServer.equals("USR"))
			    	{
			           /*	Unsuccessfull registration on Server for the agent , enter again new User Name, because 
			    	   the user name that is entered before is duplicated , it is not allowed on Server, so the agent
			    	   should enter again  */
			    		System.out.println("\n\n\t\t The Registration is Unsuccessful because User Name is duplicate .\n\t\t---------------------------------------------------------");
			    		System.out.println("\n\n\t\t Reinsert new User Name Again.\n\t\t--------------------------------\n");
			    		userName = getUserName();
			    		pwr.println(userName);	
			    	} // end if

			     }// end while
			     
				  
			     // -------------------------------------------------------------------------------
			     
		    	//Give the agent a chance to choose its status (talking , listenning) 
	  l1:	while (!exit)
		{
			     // make user to take a decision about his state (talker or listener )		  
			     agentChoice = Integer.parseInt(getUserStateChoice()) ;
			     //A.7 
			   
			     	 	           
			   l2:  switch ( agentChoice  )
			     {
			     case 0 : exit = true ; 
			              break; 
			              // A.8 
			     
			     case 1 :  // A.9
			    	      // talking state
	    		         // send to the broker to make the agent unavailable
                         pwr.println("talker");
                        //A.10 
                        //send to the broker an order to bring him the list of available agents 
			    	     do 
		     			 {
		    	      		System.out.print("\n\n Enter (3) to display available agent in the network \n Enter(0) to shutdown ?\n ----> ");
		    	      		inToServ = input.readLine();
		    	      		
		    	      		if(!(inToServ.equals("3")||inToServ.equals("0") ))System.out.println("Wrong Input Try Again \n") ; 
		    	      		if (inToServ.equals("0")) { exit =true ; break l1;}
		     			 }  while (!(inToServ.equals("3")||inToServ.equals("0")));
		    	          pwr.println("DA");
		    	          //A.11
		    	          fServer ="";
		    	        
		    	          // this While loop just of sign to inform the Agent the list will come after that  
		    	         w1: while ( (fServer = br.readLine()) != null) 
		    	         {
		    	        	 if ( fServer.equals("ls") ) break w1;
		    	        	 else if ( fServer.equals("EMPTYLIST"))	
		                	  {  
		                		System.out.println("There is no Available Agent , come later \n ") ; 
		                		// pwr.println("");
		                		break l2 ;
		                	  }
		    	        	else if (fServer.toLowerCase().equals("exit")) {exit= true ; break l1 ;} // shuts down the process
		    	         }
		    	         // Start to print the list from Broker 
				            fServer ="";
				            LV = new Vector<String> ();
				                  // waiting the List of available agents
				             w2:  while (((fServer = br.readLine()) != null))
					           	{	
				            	      
				            	       // print the list of agents
	   				  				   if (fServer.equals("le")) break w2 ;	   				  					 
  				  					   System.out.println(fServer) ;
  				  					   // save the List 
   				  					   LV.add(fServer);
   				  					  
	   				  			}  // end while
				                  // A.12
				              
	   							// While loop to ensure that the input By Agent contains in the List  

		                	  
				               do
	   							{
   				                  System.out.println("\n Select the User Name ,  you would like to contact or type (exit) to shutdown ");
		   							selectedAgent = input.readLine();
		   							if ( LV.contains(selectedAgent)||selectedAgent.toLowerCase().equals("exit"))  break; 
		   							System.out.println("\n You Enter Wrong Name  , Try Again ! ");
		   						   						
	   							}while ( true  ) ;
				                // send Agent's choice to the broker and waiting 
				                //A.15
	   							 if (selectedAgent.toLowerCase().equals("exit")){ exit =true ; break l1;} // end the global loop 
	   							 //A.16
	   							 pwr.println(selectedAgent);
	   							 // A.14
	   						     fServer =""; 
	   						     agentIP="";
					             while ((fServer = br.readLine()) != null ) // waiting the IP of the selected Agent
					            {	
	   				  			 if (fServer.equals("NF"))  
	   				  				 {
	   				  				 	System.out.println("\n an Agent You have chosen becomes unavailable ");
	   				  				 	break l1 ; 
	   				  				 }
	   				  			  else {
	   				  			         agentIP=fServer;
	   				  			         break;
	   				  			        }
	   				  			 }
					             // A.17 ,
					             new ChatThread(portChat, agentIP,selectedAgent,userName,"t").start();		
					            // A.18 
	   							 break;
	   				 			                      		 
			    		
                  case 2 :pwr.println("listener");
                	      //A.22
                        new ChatThread(portChat,userName,"l").start(); break l2 ;
                         //A.23
      
			     }// end switch 
		    
    	  		     
			      do{ } // this loop  wait until finish the chat 
			      while (ChatThread.activeCount()==2); 
				     
			       // end Session 
				  // make the listener agent free 
			     // if  (agentChoice==2) pwr.println("AVAILABLE");
				  // A.27
				   System.out.println("Do you want to Exit ?  (N)   : Y/N");
				   String endConnect = input.readLine();
				   if ( endConnect == null || endConnect.toLowerCase().equals("y")) break l1;
				   //.A 28 
		 }// end globale while loop 
	
	      // send end connection 
		  pwr.println("exit");
		  // A.29
		}
			     
		catch(UnknownHostException e) 
	            {   System.err.println("Don't know about host: "+ip);
	                     	System.exit(1);
	            } 
           catch (IOException e) 
                {
                	System.err.println("Couldn't get I/O for the connection to: "+ip+" : "+port);
                	System.exit(1);
                }
			  
			
    }	// End of main()
}	// End of class	

