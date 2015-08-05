package com.user;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Scanner;

import com.card.BuyCard;
import com.main.MysqlConnect;

public class User {

	String userName;
	int userId;
	private BuyCard usercard;
	static HashMap<Long, User> userCardHash = new HashMap<Long, User>();
	//public static int count =1;
	
	public User(String name) throws SQLException {
		this.userName = name;
		//this.userId = count;
		insertUser(this);		
		//setUsercard(new BuyCard(this.userId));		
		//userCardHash.put(userId, this);
	}
	public long getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getName(){
		return userName;
	}
	
	public static void printUser(long uid){
		System.out.print(userCardHash.get(uid));
	}
	public BuyCard getUsercard() {
		return usercard;
	}
	public void setUsercard(BuyCard usercard) {
		this.usercard = usercard;
	}
	public String toString() {
		return "User [username=" + userName +  ", userid=" + userId
				+ "]";
	}
	
	public static void createUser() throws SQLException{
		Scanner s = new Scanner(System.in);
		System.out.println("Enter new User Details");
		System.out.println("User Name : ");
		String n = s.nextLine();
		
		User u = new User(n);		
		//System.out.println(u.toString());
		System.out.println("ThankYou, Your account is created!");
		//count++;
		//s.close();
	}
	public static void insertUser(User user) throws SQLException{
		String uName = user.userName;
		MysqlConnect db = MysqlConnect.getDbConnection();
				
		String sql = "insert into users (name) values ('"+uName+"')";
		int generatedUserid = db.insert(sql);
		System.out.println("User "+ uName + " is created. " + "User id is " +generatedUserid );
		int cId = BuyCard.insertIntoUserCard(generatedUserid);
		System.out.println("Your card number is : " +cId+". Use this id for further steps.");
		
	}
}
