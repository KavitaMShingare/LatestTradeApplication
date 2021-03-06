package tradeTransmission;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Trade Transmission 
 * Date Create: 9th March 21
 * @author Kavita Shingare
 *
 */

public class TradeTransitionMain {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		TradeTransition tf=new TradeTransition();
		Date todaysDate=Calendar.getInstance ().getTime ();
		SimpleDateFormat sd=new SimpleDateFormat("dd/MM/yyyy");
		
		
		
//		Adding Trade T1,T2,T3,T4
		Date maturityDate=sd.parse("10/05/2021");
		Trade t1=new Trade("T1",1,"CP-1","B1",maturityDate, todaysDate, 'N');
		tf.addTrade(t1);
		
		
		maturityDate=sd.parse("20/05/2021");
		Trade t2=new Trade("T2",2,"CP-2","B1",maturityDate, todaysDate, 'N');
		tf.addTrade(t2);		
		
		Trade t3=new Trade("T3",5,"CP-4","B1",maturityDate, todaysDate, 'N');
		tf.addTrade(t3);		
		
		maturityDate=sd.parse("20/05/2021");
		Trade t4=new Trade("T4",5,"CP-3","B2",maturityDate, todaysDate, 'N');
		tf.addTrade(t4);
		
		
		
		//Display all Trade
		System.out.println("\n\n");
		System.out.println("Displaying total number of Trade in the list");
		tf.displayTrade();
		System.out.println("\n\n");	
				
		//Checking for all Expired Flag
		System.out.println("Checking for Expired Flag");
		maturityDate=sd.parse("20/05/2020");
		Trade t6=new Trade("T2",2,"CP-2","B1",maturityDate, todaysDate, 'N');
		tf.trades.replace("T2", t6);
		
		maturityDate=sd.parse("20/05/2020");
		Trade t7=new Trade("T4",5,"CP-3","B2",maturityDate, todaysDate, 'N');
		tf.trades.replace("T4", t7);
		tf.checkExpiredDates();
		tf.displayTrade();
		
		

	}

}
