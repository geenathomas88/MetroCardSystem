package com.main;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.card.BuyCard;
import com.ticket.Ticket;
import com.ticket.TicketTracker;
import com.user.User;
import com.user.UserManagement;

	public class OptionSelect {
	
		public static void mainChoice() throws Exception{
			Scanner s1 = new Scanner(System.in);
		
			do{
				message();			
				try {
					int choice = s1.nextInt();
					
					switch(choice) {
						case 1: User.createUser();
							break;
						case 2: UserManagement.editUser();
							break;
						case 3: UserManagement.listUsers();
							break;
						case 4: BuyCard.showUserCard();
							break;
						case 5: BuyCard.checkBalance();
							break;
						case 6: BuyCard.addCredits();
							break;
						case 7: Ticket.buyTicket();
							break;
						//case 8: TicketTracker.listTravels();
						//	break;	
						case 8: TicketTracker.sortOnDate();
							break;
						case 9:	System.out.println("Bye,ThankYou for using Metro Card System!");
							s1.close();
							System.exit(0);						
					}
				} catch (InputMismatchException e) {
					System.out.println("Incorrect input type. Try again.");
					mainChoice();	
				}
			}while(true);
						
			//s1.close();		
	}
		
	public static void message() throws SQLException{
		System.out.println("Enter your choice");
		
		MysqlConnect db = MysqlConnect.getDbConnection();
		String sql = "select * from choices";		
		
		ResultSet rs = db.query(sql);
		while(rs.next()){
			System.out.println(rs.getInt(1) + ". " + rs.getString(2));
		}
	}
}
