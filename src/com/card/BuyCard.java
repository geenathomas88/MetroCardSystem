package com.card;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.main.MysqlConnect;
import com.user.*;

public class BuyCard {

	int cardId;
	int balance;
	
	public BuyCard(int uid){
		super();
		this.cardId = uid;
		this.balance = 20;		
	}

	public static int getCardId(int uId) throws SQLException{
		ResultSet rs = queryUsersCard(uId);
		int cardId = 0;
		while(rs.next()){
			cardId = rs.getInt(1);
		}
		
		return cardId;
	}
	
	public static int getBalance(int uId) throws SQLException{
		ResultSet rs = queryUsersCard(uId);
		int balance = 0;
		while(rs.next()){
			balance = rs.getInt(2);
		}
		
		return balance;
	}
	public static int setBalance(int cId,int bal) throws SQLException{
		MysqlConnect db = MysqlConnect.getDbConnection();
		String sql = "update users_card set balance = "+bal+" where id=" +cId+"";
		int count = db.update(sql);
		
		return count;
	}

	public void setCardId(int id){
		this.cardId= id;
	}
	public static ResultSet queryUsersCard(int cId) throws SQLException{
		MysqlConnect db = MysqlConnect.getDbConnection();
		String sql = "select * from users_card where id =" +cId+"";
		ResultSet rs = db.query(sql);
		return rs;
	}

	public static void addCredits() throws SQLException{
		int cId = UserManagement.existingUser1();
		Scanner s = new Scanner(System.in);
		System.out.println("Plaese enter the amount to be credited");
		int oldBalance = getBalance(cId);
		int a = s.nextInt();
		int credit = oldBalance+a;
		
		MysqlConnect db = MysqlConnect.getDbConnection();
		String sql = "update users_card set balance = "+credit+" where id=" +cId+"";
		int count = db.update(sql);
		if(count > 0){
			String sql1 = "select * from users_card where id =" +cId+"";
			ResultSet rs = db.query(sql1);
		
			while(rs.next()){
			System.out.println("Recharge successful, Your new balance is : " + rs.getInt(2)); }
		}
		
	}
	
	public static void checkBalance() throws SQLException{
		int cId = UserManagement.existingUser1();
		MysqlConnect db = MysqlConnect.getDbConnection();
		String sql = "select * from users_card where id =" +cId+"";
		ResultSet rs = db.query(sql);
		while(rs.next()){
			System.out.println("Your balance is: "+ rs.getInt(2) );
		}
		
	}
	public static void showUserCard() throws SQLException{
		int cId = UserManagement.getUserIdFromUser();
		MysqlConnect db = MysqlConnect.getDbConnection();
		String sql = "select * from users_card where user_id =" +cId+"";
		ResultSet rs = db.query(sql);
		
		while(rs.next()){
			System.out.println("User Id : " +rs.getInt(3) +" Balance : "+ rs.getInt(2) + " User Card Number : " + rs.getInt(1) );
		}
	}
	public static int insertIntoUserCard(int userid) throws SQLException{
		int uId = userid;
		int balance = 20;
		MysqlConnect db = MysqlConnect.getDbConnection();
				
		String sql = "insert into users_card (balance,user_id) values ("+balance+","+uId+")";
		int cardId = db.insert(sql);
		return cardId;
	}
	
	@Override
	public String toString() {
		return "Card [cardId=" + cardId + ", balance=" + balance + "]";
	}
}
