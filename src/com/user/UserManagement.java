package com.user;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import com.main.MysqlConnect;

public class UserManagement {

	public static int existingUser1() throws SQLException{

		int userid =swipeCard();
		while(!userPresent(userid))
		{	
			try{
				System.out.println(userid);
				
				MysqlConnect db = MysqlConnect.getDbConnection();
				String sql = "select * from users where id =" +userid+"";
				ResultSet rs = db.query(sql);
				while(rs.next()){
					System.out.println("User Id: "+ rs.getInt(1) + " Name: " + rs.getString(2));
				}

				break;
			}catch(Exception e){
				swipeCard();
				
				}
		}
		return userid;		
	}
	
	public static int swipeCard(){
		int uid;

		System.out.println("Please swipe your card to proceed : (Enter your card number)");
		Scanner s = new Scanner(System.in);

		while (true) {
			try {
				uid = (int) checkValidInt();
				break;
			} catch (Exception e) {
				System.out.println("Please enter a valid card number");
			}
		}
		return uid;
	}

	public static void listUsers() throws SQLException{
		System.out.println("User Id ---- User Name");
		MysqlConnect db = MysqlConnect.getDbConnection();
		String sql = "select * from users";
		ResultSet rs = db.query(sql);

		while(rs.next()){
			System.out.println(rs.getInt(1) + " ---- " + rs.getString(2));
		}

	}

	public static void editUser() throws SQLException{
		int uId = getUserIdFromUser();
		System.out.println("Enter New Name : ");
		Scanner s = new Scanner(System.in);
		String uName = s.nextLine();

		MysqlConnect db = MysqlConnect.getDbConnection();
		String sql = "update users set name = '"+uName+"' where id = "+uId+" ";
		int count = db.update(sql);
		if(count > 0){
			String sql1 = "select * from users where id =" +uId+"";
			ResultSet rs = db.query(sql1);
		
			while(rs.next()){
			System.out.println("Username is changed to : " + rs.getString(2));}
		}
	} 

	public static Object checkValidInt(){
		Scanner sc = new Scanner(System.in);
		int userid;
		if(sc.hasNextInt()){
			userid = sc.nextInt();			
			return userid;}
		else			
			return sc.next();			
	}

	public static boolean userPresent(int id) throws SQLException{
		int uId = id;
		MysqlConnect db = MysqlConnect.getDbConnection();
		
		String sql = "select id from users where id =" +uId+"";
		ResultSet rs = db.query(sql);
		return rs.next();
	}
	public static int getUserIdFromUser(){
		System.out.println("Enter user id");
		Scanner sc = new Scanner(System.in);
		int uId;
		
		while (true) {
			try {
				uId = (int) checkValidInt();
				break;
			} catch (Exception e) {
				System.out.println("Please enter a valid user id");
			}
		}
		
		return uId;
	}
}
