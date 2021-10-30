/**
 * @author cspgssd salaheddin Darwish 
 * assignment Object-oriented Communication 
 * I have used Simon JE Taylor  Code (2001) in Tutorial #6 about managing Shared Data 
 * Modified By Salaheddin Darwish 
 */
import java.util.*;
public class AgentsDetails {
	
   
	private   List<AgentRecord> ListOfAgents ; // the Shared Data 
	private int readers=0; // number of concurrent	readers
	private boolean writing=false; // true a thread is writing, false otherwise
	private int waitingW=0; // number of waiting writers
	// Constructor 
	public  AgentsDetails ()
	{		
		// intialize the Shared Data 
		ListOfAgents = new ArrayList<AgentRecord> ();
		
	}

	
	public void insertAgent (String ipNewAgent,String newAgenName ) throws InterruptedException 
	{
		AgentRecord rA  ;
		// This method to insert Information about new Agent connected to the broker 
	 
		try
		{ 
			rA = new AgentRecord(ipNewAgent,newAgenName);
			this.acquireWrite();
			ListOfAgents.add(rA);
			this.releaseWrite();
		} 
		catch(Exception e)
		{	System.err.println(e);  	}
	
	}// end inserAgent 

	public AgentRecord getAgentDetails (String naAG)throws InterruptedException 
{
	AgentRecord rA = null  ;
	// This method to insert Information about new Agent connected to the broker 
	Iterator<AgentRecord> iterator = ListOfAgents.iterator();
	try
	{ 	;
		this.acquireRead();
		while (iterator.hasNext())
			{
			  rA  = iterator.next();
			  if(naAG.equals(rA.getName()))
				{
					break;
				}// end if
			}
		this.releaseRead();
		return (rA) ;		
	}
	catch(Exception e)
	{		
		System.err.println(e);
	    return null ;
	}
	
}

	public void upadateStatus (String naAU , boolean bS ) 
	{
		AgentRecord rA = null ;
		// This method to update the status of agents in the list  
		Iterator<AgentRecord> iterator = ListOfAgents.iterator();
		try
		{ 	
			this.acquireWrite();
			while (iterator.hasNext())
				{
				  rA  = iterator.next();
				  if(naAU.equals(rA.getName()))
					{
					   rA.changeStatus(bS);
      				  break;
					}// end if
				}
			this.releaseWrite();
				
		}
		catch(Exception e)
		{		
			System.err.println(e);
		}
		
	}

	public void deleteAgent  (String naD) 
	{
	
		AgentRecord rA ;
		// This method to update the status of agents in the list  
		Iterator<AgentRecord> iterator = ListOfAgents.iterator();
		try
		{ 	
			this.acquireWrite();
			while (iterator.hasNext())
				{
				  rA  = iterator.next();
				  if(naD.equals(rA.getName()))
					{
					  ListOfAgents.remove(rA);
					  break;
					}// end if
				} // end while s
			this.releaseWrite();
				
		}
		catch(Exception e)
		{		
			System.err.println(e);
		}
	
	} // end deleteAgent method 

	public ArrayList<AgentRecord>  searchAvailabelAgents ()
	{
		ArrayList<AgentRecord> x = new ArrayList<AgentRecord> ();
		AgentRecord rA = null  ;
		// This method to insert Information about new Agent connected to the broker 
		Iterator<AgentRecord> iterator = ListOfAgents.iterator();
		try
		{ 	
			this.acquireRead();
			while (iterator.hasNext())
				{ 
				  rA  = iterator.next();				  
				  if(rA.getStatus()) x.add(rA);	
											
				} // end While 
			this.releaseRead();
			
			return x  ;	
			
		}
		catch(Exception e)
		{		
			System.err.println(e);
		    return null ;
		}
		
	}
	
	public boolean  nameExists (String Na)
	{
		boolean isX = false ;
		AgentRecord rA = null  ;
		// This method to give all the age Information about new Agent connected to the broker 
		Iterator<AgentRecord> iterator = ListOfAgents.iterator();
		try
		{ 	
			this.acquireRead();
			while (iterator.hasNext())
				{ 
				  rA  = iterator.next();				  
				  if( rA.getName().equals(Na)) 
				  {  
					  isX= true  ;
					  break ;
				  }
											
				} // end While 
			this.releaseRead();
			
			return isX ;	
			
		}
		catch(Exception e)
		{		
			System.err.println(e);
		    return true ;
		}
		
	}

	public boolean  isAvailable (String Na)
	{
	  AgentRecord rA = null ;
	  boolean x =false ;
	// This method to insert Information about new Agent connected to the broker 
	Iterator<AgentRecord> iterator = ListOfAgents.iterator();
	try
	{ 	
		this.acquireRead();
		while (iterator.hasNext())
			{ 
			  rA  = iterator.next();				  
			  if( rA.getName().equals(Na)) 
			  {   
				if (rA.getStatus()==true)
					{
					  x= true ;
				      break ;
					}
			  }
										
			} // end While 
		this.releaseRead();
		
				
	}
	catch(Exception e)
	{		
		System.err.println(e);
	 }
	return x;
	}
	
   // grabs lock to read
	public synchronized void acquireRead() throws 	InterruptedException
	{
		while(writing || waitingW>0) 
			{  
			wait();
			}
		++readers;
	}
	
  // releases lock to read
	public synchronized void releaseRead() {
		--readers;
		if(readers==0) 
		{
			notifyAll();
		}
	}
	
   // grabs lock to write
	// grabs lock to write 
	public synchronized void acquireWrite() throws InterruptedException{
		++waitingW;
		while(readers>0 || writing) 
		{ 	
		wait();
		}
		--waitingW;
		writing =true;
		}
	
	// releases lock to write
	public synchronized void releaseWrite() 
		{
			writing=false;
			notifyAll();
		}
 
	// provides state information about the shared data object
/*		private synchronized void dumpState() {
		System.out.print ("[R="+readers+",S="+writing+"writing reading,W="+waitingW+"]\n");
			}    */
		

}// end AgentsDetails Class 
