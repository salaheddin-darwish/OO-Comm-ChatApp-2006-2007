package MYwork;
import java.io.*;
import java.net.*;

public class ThreadChat extends Thread  {
	
	  protected ServerSocket  ss;
	  protected Socket  as ;
	  protected BufferedReader i;
	  protected PrintWriter o;
	  public String aIP , nameOtherAgent ,myName,  text ,inputb;
	  protected int chatPort ;
	  protected String St ;
	  public static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

	  
	  // constructor 
	 public ThreadChat (int chatPort,String myName,String status) throws IOException 
	  {
		  
		    super("ThreadChat");    
		    this.chatPort= chatPort ;
		    this.aIP = "";
		    this.nameOtherAgent="";
		    this.myName =myName ;
		    this.St=status ;
		    

       }
	 public ThreadChat (int chatPort, String aIP ,String nameOtherAgent,String myName,String status) throws IOException 
	  {
		  
		    super("ThreadChat");    
		    this.chatPort= chatPort ;
		    this.aIP = aIP ;
		    this.nameOtherAgent= nameOtherAgent;
		    this.myName =myName ;
		    this.St=status ;
		    

      } 
	 
	 
     public boolean workAsServer()
     {
      try{
    	 ss = new ServerSocket( this.chatPort);
		   
		   //do 
   			//{ 
			 System.out.println("waiting for connection..........");
			 as = ss.accept();
			 System.out.println(" connection with someone");
			 
		     // System.out.print( as.getInetAddress().getHostAddress().toString());
   			//} while (!as.getInetAddress().getHostAddress().toString().equals(this.aIP));

		    return (true);
      } catch (IOException e) 
        	{
        		System.err.println("Accept failed.");
        		return (false);
        	}
      
     }
     public boolean workAsClient()
     {
		 try
		 {
			as = new Socket (this.aIP,this.chatPort);
			System.out.println(" make connection with "+this.nameOtherAgent);

			return (true);
		 }  catch (IOException e) 
			{
	 			System.err.println("Couldn't get I/O for the connection to:"+this.nameOtherAgent);
	 			return (false);
			}
     }
	     
     public  void run()
	    {
	      boolean x = false;
		  try{
			   if (this.St.equals("l")) 
	    		 x = workAsServer() ; //work as server for the agent who want to listen
        	   else if ( this.St.equals("t"))   
        	 	 x=workAsClient(); //work as server for the agent who want to talk
        		  
	    	  if(x == true) // make sure that everything working 
	    	   {
		    	     
				o = new PrintWriter(as.getOutputStream(), true);
				i = new BufferedReader(new InputStreamReader(as.getInputStream()));
	    			    	  	 
                 System.out.println("\t\t\t +++++ ( Successful Connection )+++++\t\t\t\n");
               
                 
                 
		    	if (this.St.equals("l"))
		    	 { 	 
		    	   
		    	    while ((inputb=i.readLine())!= null  )
		    	    { 
		    	       
		    	    	// recieve message from the other Agent who is connect with you
		    	            System.out.println(inputb);
		    	    	    if ( inputb.toLowerCase().contains("end chat")) break; 
		    	    	    // send the meesage to the other Agent who is connect with you
		    	    	    System.out.print("|<"+this.myName+"> : ");
		    	    	    do { text =input.readLine();} while (text.equals(""));
		    	    	    o.println("|<"+this.myName+"> : "+text); 
		    	            if (text.toLowerCase().contains("end chat")) break ;
		    	    	    		             
		    			  }// end while
		    	    ss.close();
		    	   }
		        else if (this.St.equals("t"))
	    	      {       // to make some kind of synchronization amon chatting ,
		        	     //intial step one agent sends message as first message and the Other should wait this message
		        		 System.out.print("|<"+this.myName+"> : ");
	 	    	         do { text =input.readLine();} while (text.equals(""));
	 	    	         o.println("|<"+this.myName+"> : "+text);   
		    	         while ((inputb=i.readLine())!= null )
		    	         {// recieve message from the other Agent who is connect with you
				    	      
				    	    	    System.out.println(inputb);
				    	    	    if ( inputb.toLowerCase().contains("end chat")) break; 
				    	    	    // send the meesage to the other Agent who is connect with you
				    	    	    System.out.print("|<"+this.myName+"> : ");
				    	    	    do { text =input.readLine();} while (text.equals(""));
				    	    	    o.println("|<"+this.myName+"> : "+text);  				    	    	    
				    	    	    if (text.toLowerCase().contains("end chat")) break ;
				    	    	   
		    	    	} 
	    	    	  }
		    	 o.close();
		    	 i.close();
		    	 as.close();
	    	   }// end if 
	    	  else  System.out.println("\t\t++++++++ Error is encountered  ++++++++\t\t");
	    	}catch(Exception e)  { System.err.println(e) ; }
	   	 System.out.println("\t\t++++++++ End Session ++++++++\t\t");   
		              
	                
	       }// end run 
	    }// end ThreadChat class

	  

