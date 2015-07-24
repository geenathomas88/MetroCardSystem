package com.user;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Scanner;

import com.card.BuyCard;

public class User {

	String userName;
	long userId;
	private BuyCard usercard;
	static HashMap<Long, User> userCardHash = new HashMap<Long, User>();
	public static long count =1;
	
	public User(String name) {
		this.userName = name;
		//this.userId = Calendar.getInstance().getTimeInMillis();
		this.userId = count;	
		setUsercard(new BuyCard(this.userId));
		userCardHash.put(userId, this);
	}
	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
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
	
	public static void createUser(){
		Scanner s = new Scanner(System.in);
		System.out.println("Enter new User Details");
		System.out.println("User Name : ");
		String n = s.nextLine();
		
		User u = new User(n);		
		System.out.println(u.toString());
		System.out.println("ThankYou, Your account is created!");
		count++;
		//OptionSelect.continueChoice();
		//s.close();
	}

}
