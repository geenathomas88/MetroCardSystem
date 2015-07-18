package com.ticket;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

import com.user.User;
import com.user.UserManagement;

public class Ticket implements Comparable<Ticket>{
	
	String boardStation;
	String alightStation;
	Date timeOfTravel;
	long userId;
	
	static HashMap<Date, Ticket> ticketHash = new HashMap<Date, Ticket>();
	
	public Ticket(String boardStation, String alightStation,long userid) {
		this.boardStation = boardStation;
		this.alightStation = alightStation;
		this.timeOfTravel = Calendar.getInstance().getTime();
		this.userId = userid;
		
		ticketHash.put(this.timeOfTravel, this);
	}

	public String getBoardStation() {
		return boardStation;
	}

	public void setBoardStation(String boardStation) {
		this.boardStation = boardStation;
	}

	public String getAlightStation() {
		return alightStation;
	}

	public void setAlightStation(String alightStation) {
		this.alightStation = alightStation;
	}
	
	@Override
	public String toString() {
		return "Ticket [boardStation=" + boardStation + ", alightStation="
				+ alightStation + ", timeOfTravel=" + timeOfTravel
				+ ", userId=" + userId + "]";
	}

	public static void buyTicket(){		
		User u = UserManagement.existingUser();
		if(u.getUsercard().getBalance() < 20)
		{
			System.out.println("Sorry, You dont have minimum balance to make a trip! Please recharge your card.");
		}
		else{
			int boardingStation = Station.getBoardingStation();
			int alightingStation = Station.getAlightingStation();
			int diffStation = Station.abs(alightingStation - boardingStation);
			if(diffStation == 0 || diffStation < 1 || diffStation >  (Station.NOOFSTATIONS - 1)){
				System.out.println("Sorry!, Wrong Station Chosen");
			}else{
				if(grantTicket(u,boardingStation,alightingStation)){
					String bStation = Station.stationToString(boardingStation);
					String aStation = Station.stationToString(alightingStation);
					
					new Ticket(bStation, aStation,u.getUserId());
					//ticketHash.put(u.getUserId(), ticket);
					System.out.println("Have a happy journey!");
				}else{
					System.out.println("Error Occured!!");
				}
			}
		}
	}
	
	public static boolean grantTicket(User u,int boardingStation,int aightingStation){
		int diff = Station.abs(aightingStation - boardingStation);
		int cost = Station.NOOFSTATIONS-diff;
		int bal = u.getUsercard().getBalance() - (Station.NOOFSTATIONS-diff);
		System.out.println("Your trip costs " + cost+ "$.");
		System.out.println("Please swipe your card to proceed : (Enter your card number)");
		Scanner s = new Scanner(System.in);
		long cardId = s.nextLong();
		
		if (cardId == u.getUsercard().getCardId()){
			System.out.println("Processing...");
			u.getUsercard().setBalance(bal);
			System.out.println("Your balance is "+ u.getUsercard().getBalance()+"$");
			return true;
		}
		else
		{
			System.out.println("Please enter a valid card number!!");
			return false;
		}
		//s.close();		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((timeOfTravel == null) ? 0 : timeOfTravel.hashCode());
		result = prime * result + (int) (userId ^ (userId >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ticket other = (Ticket) obj;
		if (timeOfTravel == null) {
			if (other.timeOfTravel != null)
				return false;
		} else if (!timeOfTravel.equals(other.timeOfTravel))
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}

	@Override
	public int compareTo(Ticket t) {
		if((this.timeOfTravel).compareTo(t.timeOfTravel) < 0)
			return 1;
		else
			return -1;
	}
	
}
