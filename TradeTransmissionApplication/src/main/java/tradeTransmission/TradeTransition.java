package tradeTransmission;

import java.util.Date;
import java.util.HashMap;

/**
 * Trade Transmission to add trade
 * Date Create: 9th March 21
 * @author Kavita Shingare
 *
 */
public class TradeTransition {
	
	
	HashMap<String,Trade> trades=new HashMap<String,Trade>();
	
	
	//check if no trade Exists
	public boolean checkIfTradeEmpty()
	{
		return trades.isEmpty();
	}
	
	//Check if the lower version is being received by the store it will reject the trade and throw an exception. 
	//If the version is same it will override the existing record
	public void checkVersion(Trade t,int version) throws Exception
	{
		if(t.getVersion()< version)
		{
			throw new Exception(t.getVersion()+" is less than "+ version);
			
		}
		
	}
	
	//Check if maturityDate
	public boolean checkMaturityDate(Date maturityDate,Date CurrentDate)
	{
		
	
		if(CurrentDate.compareTo(maturityDate)>0)
			return false;
		
		return true;
		
		
		
	}
	
	public void checkExpiredDates()
	{
		
		//SimpleDateFormat sd=new SimpleDateFormat("dd/MM/yyyy");
		Date currentDate=new Date();
		
		for(String strKey : trades.keySet() ){
		    if(currentDate.compareTo(trades.get(strKey).getMaturityDate())>0)
		    {
		    		Trade t=trades.get(strKey);
		    		t.setExpired('Y');
		    		trades.replace(strKey, t);
		    }
		}
		
	}
	
	
	//add Trade
	public void addTrade(Trade T) throws Exception
	{
		if(trades.containsKey(T.getTradeId()))
		{
			checkVersion(T, trades.get(T.getTradeId()).getVersion());
			
			if(checkMaturityDate(T.getMaturityDate(), trades.get(T.getTradeId()).getMaturityDate()))
			{
				trades.replace(T.getTradeId(), T);
				System.out.println(T.getTradeId()+" is added to the Store");
			}
			else
			{
				System.out.println("Not able to add "+T.getTradeId()+" in the store as maturity date is lower than current date");
			}
		}
		else
		{
			
			if(checkMaturityDate(T.getMaturityDate(), T.getCreatedDate()))
			{
				
					trades.put(T.getTradeId(), T);
					System.out.println(T.getTradeId()+" is added to the Store");
			
			}
			else
			{
				System.out.println("Not able to add "+T.getTradeId()+" in the store as maturity date is lower than current date");
			}
		}
		
	}
	
	
	//get trade
	public Trade getTrade(String tId) throws Exception
	{
		if(trades.containsKey(tId))
			return trades.get(tId);
		throw new Exception ("Trade with "+tId+" not Found");
		
	}
	
	//display all trades
	public void displayTrade()
	{
		for(String trade : trades.keySet())
		{
			System.out.println(trades.get(trade).toString());
		}
	}
}
