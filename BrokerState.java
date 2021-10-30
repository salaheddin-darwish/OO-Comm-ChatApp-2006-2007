/* The class is made to give any status of the broker " any step that protocol have reached "*/
public class BrokerState 


	{
	  private static final int WAITING = 0;//S.1
	  private static final int SENTCONNECTED = 1;//S.2
	  private static final int SENTREGISTRATIONSTATUS = 2;//S.6
	  private static final int SENTAGENTSLIST = 3;//S.9
	  private int state  = WAITING;


      public String processInput(String theInput)
	  {
	    String theOutput = null;
	    switch(state)
	    {
	    	case WAITING://S.1
	    		theOutput = "CONNECTED to Broker";
	    		state = SENTCONNECTED;//Change state to S.2
	    		break;
	  
	    	case SENTCONNECTED://S.2
	    		//Register this agent as busy
	    	    //Change state to S.22
	     	  if(theInput.toLowerCase().equals("exit"))
	    		{
	    		  theOutput = "EXIT";
	        	  state = WAITING ;//Change state to S.1 
	    		}
	     	  else if (theInput.equals("USR") ) 
	     		  {
	     		    state = SENTCONNECTED ;//Change state to S.2
	     	       
	     		  }
	    		else if (theInput.equals("SR"))
	    		  {
	    			state = SENTREGISTRATIONSTATUS ;
	                  
	    		  }
	    		else 
	    		 {
	    			theOutput = "REG"; // first step of registration S.3  
	    			
	    		  }
	            break;
	      
	    	   case SENTREGISTRATIONSTATUS ://S.8
	    		
	    		 if(theInput.toLowerCase().equals("talker"))
	    		      {
	    		        theOutput = "BUSY";
	    		        //Register this agent as busy
	    		       state = SENTREGISTRATIONSTATUS ;//Change state to S.8
	    		      }
	    		      else if(theInput.equals("DA"))
	    		      {
	    		        theOutput = "AGENTS-LIST";
	    		        state = SENTAGENTSLIST;//Change state to S.14
	    		      }
	    		      else if (theInput.toLowerCase().equals("listener"))
	    		      {
		    		        theOutput = "FREELISTENER";
		    		        state =SENTREGISTRATIONSTATUS ;//Change state to S.21  
	    		      }
	    		      else if (theInput.toLowerCase().equals("exit")){
	    		          theOutput = "EXIT"; //Change state to S.15
	    		          state = WAITING;
	    		      }
	                  break;
	      
	    
			    case SENTAGENTSLIST://State S.14
			      if(theInput.toLowerCase().equals("exit"))
			      {
			        theOutput = "EXIT";
			        state = WAITING;//Change state to S.1
			      }
			      else if( theInput.toLowerCase().equals("uas"))
			       {
			    	theOutput = "NF";
			    	state = SENTREGISTRATIONSTATUS ; //Change state to S.8
			        }
			    	else if (theInput.toLowerCase().equals("as"))
			    	{
			    		  theOutput = " Sent sIP";
			    		  state = SENTREGISTRATIONSTATUS ; //Change state to S.21
			    	}
			    	 /* else if(theInput.equals("AVAILABLE"))
				      {
				        //Register this agent as busy
				        theOutput = "FREE";
				        state = SENTREGISTRATIONSTATUS;//Change state to S.8
				      } */
			    	 else if (theInput.equals("EMPTYLIST")) // Empty List  his mind  
			    	  {
			    		       theOutput = "EMPTYLIST";
			    		       state = SENTREGISTRATIONSTATUS ;
			    	  }
			    	  else 
			    	  {
			    	  theOutput = "CHECKAVAILABLILITY";
			    	  state = SENTAGENTSLIST ; //Change state to S.14
			    	  }
			      
			      break;
			      
		
	/*      case SENTBUSYAGENT://State S.21
	      if(theInput.equals("AVAILABLE"))
	      {
	        //Register this agent as busy
	        theOutput = "FREE";
	        state = SENTREGISTRATIONSTATUS;//Change state to S.8
	      }
	      else if (theInput.toLowerCase().equals("exit"))
	      {
	          theOutput = "EXIT";
	          state = WAITING;//Change state to S.1
	      }
	      break;*/
	    }
	    return theOutput;

	  }
	}

			