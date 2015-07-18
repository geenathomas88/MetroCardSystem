package com.ticket;

import java.util.Date;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import com.user.User;
import com.user.UserManagement;



public class TicketTracker {

	public static void listTravels1(){
		User u = UserManagement.existingUser();
		//System.out.println(Ticket.ticketHash.get(u.getUserId()));
	}
	public static void listTravels(){
		Iterator<Date> it = Ticket.ticketHash.keySet().iterator();
		Date key;
		System.out.println("UserName ------ ------ ------ BoardngStaion ------- AlightingStation ----- DateOfTravel");
		System.out.println();
		while(it.hasNext()){
			key = it.next();
		    User.printUser(Ticket.ticketHash.get(key).userId);
			System.out.println(" ----- -------" + Ticket.ticketHash.get(key).alightStation+ " -------- " + Ticket.ticketHash.get(key).boardStation+ " ------- " + Ticket.ticketHash.get(key).timeOfTravel);
		}
		System.out.println();
	}
	public static void sortOnDate(){
		TreeMap<Date, Ticket> sortedTree = new TreeMap<Date,Ticket>(Ticket.ticketHash);
		
		Set<Entry<Date, Ticket>> sortedSet = sortedTree.entrySet();
		for(Entry<Date, Ticket> ss: sortedSet){
			System.out.println(ss.getKey() + " ==> " + ss.getValue());
		}
	}

}


