package com.card;

import java.util.Scanner;

import com.user.*;

public class BuyCard {

	long cardId;
	int balance;
	
	public BuyCard(long uid){
		super();
		this.cardId = uid;
		this.balance = 20;		
		printCardDetails(this);
	}
	public long getCardId(){
		return cardId;
	}
	public int getBalance(){
		return balance;
	}
	public void setBalance(int bal){
		this.balance = bal;
	}
	@Override
	public String toString() {
		return "Card [cardId=" + cardId + ", balance=" + balance + "]";
	}
	public void setCardId(long id){
		this.cardId= id;
	}
	
	public static void printCardDetails(BuyCard card){
		System.out.println("Card Id : " + card.getCardId() +" , "+ "Current Balance : " + card.getBalance());
	}
	public static void addCredits(){
		User u = UserManagement.existingUser();
		Scanner s = new Scanner(System.in);
		System.out.println("Plaese enter the amount to be credited");
		
		int a = s.nextInt();
		System.out.println("Recharge successful, Your new balance is :  "+rechargeCard(u.getUsercard(), a));
		//s.close();
	}
	private static int rechargeCard(BuyCard card, int amount){
		 card.balance = card.getBalance() + amount;
		 return card.balance;
	}
	public static void checkBalance(){
		User u = UserManagement.existingUser();
		System.out.println("Your balance is " + u.getUsercard().getBalance() + "$");		
	}
	public static void showUserCard(){
		User u = UserManagement.existingUser();
		//printCardDetails(u.getUsercard());
	}
}
