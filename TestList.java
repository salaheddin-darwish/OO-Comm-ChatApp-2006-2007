import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TestList {

	/**
	 * @param args
	 */
	public static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	private static  AgentsDetails L;
	private static  AgentRecord Ar = new AgentRecord(null, null)  ;
	private static ArrayList<AgentRecord> v; 
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// AgentsDetails L = new  AgentsDetails ();
		
		
		String x,y;
		boolean z ;
		try{	
					
		System.out.println("start insert list of agents :\n ");
	
	for (int i=0 ; i<2; i++)
		{
			System.out.println(" agent"+i);
			x=input.readLine();
			y=input.readLine();
			//1
			Broker.AgentsDB.insertAgent(x, y);	 	
		} 
		
			
		
		//2 
		v =  new ArrayList<AgentRecord> ();
 
		/*	
		System.out.println("delete agent ist of agents :\n ");
		String u=input.readLine();
	    //3 
		L.deleteAgent(u); 
		
	   System.out.println("change status :\n ");
		String q=input.readLine(); 
	   //4 
		L.upadteStatus(q);
		
		v =L.searchAvailabelAgents() ;
	   Iterator<AgentRecord> iterator1 = v.iterator();
	   System.out.println("display itemsl of the ist of agents :\n ");
		
		while (iterator1.hasNext())
			{ 
			  Ar = iterator1.next();
			
			System.out.println(Ar.getIp()+"\t"+Ar.getName()+"\t"+Ar.getStatus());	
			}		
		*/
	
		}	catch(Exception e)
		{		
			System.err.println(e);
		   
		}
		

	}

}
