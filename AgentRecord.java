
/* this clases makes the form of a record for the agent " Agent's information 
 * it is used basically in creating the shared list of agents connected to the broker
 */
public class AgentRecord {
	
	private  String agentIp  ;
	private  String agentName  ;
	private  boolean availabble   ;
	
	public AgentRecord (String agentIp , String agentName )
	{
		this.agentIp=agentIp ;
		this.agentName=agentName ;
		this.availabble= false ;
			
	}// end constructor

	public String getIp ()
	{
		return (this.agentIp);
	}
		
	public String getName ()
	{
		return (this.agentName);
	}
	
	public boolean getStatus ()
	{
		return (this.availabble);
	}	
	public  void updateName(String N )
	{
		this.agentName = N ;
		
	}
	public  void updateIp(String N )
	{
		this.agentIp = N ;
		
	}
	public  void changeStatus(boolean B)
	{
	  this.availabble= B;
	  
	}
	}// end class
