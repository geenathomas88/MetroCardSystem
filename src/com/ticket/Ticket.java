package com.ticket;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Scanner;
import com.card.BuyCard;
import com.main.MysqlConnect;
import com.user.UserManagement;

public class Ticket implements Comparable<Ticket>{
	
	String boardStation;
	String alightStation;
	LocalDateTime timeOfTravel;
	int userId;
	
	public Ticket(String boardStation, String alightStation,int userid) throws SQLException {
		this.boardStation = boardStation;
		this.alightStation = alightStation;	
		this.userId = userid;
		insertTicket(this);
	}

	public static void insertTicket(Ticket ticket) throws SQLException{
		String bStation = ticket.boardStation;
		String aStation = ticket.alightStation;
		LocalDateTime time_of_travel = LocalDateTime.now();
		int uId = ticket.userId;
		
		MysqlConnect db = MysqlConnect.getDbConnection();
		String sql = "insert into ticket (board_station,alight_station,time_of_travel,user_id) values ('"+bStation+"','"+aStation+"','"+time_of_travel+"', "+uId+")";
		db.insert(sql);
	}
	
	@Override
	public String toString() {
		return "Ticket [boardStation=" + boardStation + ", alightStation="
				+ alightStation + ", timeOfTravel=" + timeOfTravel
				+ ", userId=" + userId + "]";
	}

	public static void buyTicket() throws SQLException{		
		int cId = UserManagement.existingUser1();
		ResultSet rs = BuyCard.queryUsersCard(cId);
		int balance= 0;
		int uId = 0;
		while(rs.next()){
			balance = rs.getInt(2);
			uId = rs.getInt(3);
		}
		if(balance < 20){
			System.out.println("Sorry, You dont have minimum balance to make a trip! Please recharge your card.");
		}
		else
		{
			int boardingStation = Station.getBoardingStation();
			int alightingStation = Station.getAlightingStation();
			int diffStation = Station.abs(alightingStation - boardingStation);
			if(diffStation == 0 || diffStation < 1 || diffStation >  (Station.NOOFSTATIONS - 1)){
				System.out.println("Sorry!, Wrong Station Chosen");
			}else{
				if(grantTicket(cId,boardingStation,alightingStation)){
					String bStation = Station.stationToString(boardingStation);
					String aStation = Station.stationToString(alightingStation);
					
					new Ticket(bStation, aStation,uId);
					//ticketHash.put(u.getUserId(), ticket);
					System.out.println("Have a happy journey!");
				}else{
					System.out.println("Error Occured!!");
				}
			}
		
		}

	}
	

	public static boolean grantTicket(int cId,int boardingStation,int aightingStation) throws SQLException{
		int diff = Station.abs(aightingStation - boardingStation);
		int cost = Station.NOOFSTATIONS-diff;
		
		int bal = BuyCard.getBalance(cId) - (Station.NOOFSTATIONS-diff);
		
		System.out.println("Your trip costs " + cost+ "$.");
		System.out.println("Please swipe your card to proceed : (Enter your card number)");
		Scanner s = new Scanner(System.in);
		int cardId = s.nextInt();
		
		int userCard = 0;
		try {
			userCard = BuyCard.getCardId(cId);
		} catch (NullPointerException e) {
			System.out.println("Please enter a valid card number!!");
			return false;
		}
		
		if (cardId == userCard){
			System.out.println("Processing...");
			BuyCard.setBalance(cId, bal);
			System.out.println("Your balance is "+ BuyCard.getBalance(cId)+"$");
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
