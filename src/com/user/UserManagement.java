package com.user;

import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class UserManagement {

	public static User existingUser(){

		long userid =swipeCard();
				
		if(!User.userCardHash.containsKey(userid)){
			System.out.println("User not found.");
			System.exit(0);			
			}
		System.out.println(userid);
		User u = User.userCardHash.get(userid);			
		System.out.println(u);
		System.out.println(u.getName() + " ------ " + u.getUserId() + " ------ " +u.getUsercard());
		
		return u;		
	}
	public static long swipeCard(){
		long uid = 0;
		System.out.println("Please swipe your card to proceed : (Enter your card number)");
		Scanner s = new Scanner(System.in);
		
		try {
			uid = s.nextLong();							
		}
		 catch (NullPointerException e) {
			System.out.println("Please enter a valid card number");
			existingUser();			
		}
			
		return uid;
	}
	
	public static void listUsers(){
		Set<Long> userSet = User.userCardHash.keySet();
		Iterator<Long> it = userSet.iterator();
		
		while(it.hasNext()){
			System.out.println(User.userCardHash.get(it.next()));
		}
		
	}
	
	public static void editUser(){
		User u = existingUser();
		System.out.println("Enter New Name : ");
		Scanner s = new Scanner(System.in);
		if(User.userCardHash.containsKey(u.userId)){
				u.userName = s.nextLine();
				User.userCardHash.replace(u.userId, u);
				System.out.println("Username is changed to : " + u.toString());
			}else
				System.out.println("Incorrect details");
		} 
		
	
}
