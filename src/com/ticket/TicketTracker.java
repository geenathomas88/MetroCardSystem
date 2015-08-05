package com.ticket;

import java.sql.ResultSet;
import java.sql.SQLException;
import com.main.MysqlConnect;

public class TicketTracker {

	public static void listTravels() throws SQLException{
		System.out.println("UserName ------ ------ ------ BoardngStaion ------- AlightingStation ----- DateOfTravel");
		System.out.println("");
		MysqlConnect db = MysqlConnect.getDbConnection();
		String sql = "select * from ticket";
		ResultSet rs = db.query(sql);
		while(rs.next()){
			String sql1 = "select name from users where id = "+rs.getInt(5);
			ResultSet rs1 = db.query(sql1);
			while(rs.next()){System.out.print(rs1.getString(2));}
			System.out.println(" ----- -------" + rs.getString(2)+ " -------- " + rs.getString(3)+ " ------- " + rs.getTimestamp(4));
		}
	}

	public static void sortOnDate() throws Exception{
		
		MysqlConnect db = MysqlConnect.getDbConnection();
		String sql = "select * from ticket order by time_of_travel";
		ResultSet rs = db.query(sql);
		System.out.println("UserName ------ ------  BoardngStaion ------- AlightingStation ----- DateOfTravel");
		System.out.println("");
		while(rs.next()){
			String sql1 = "select * from users where id = "+rs.getInt(5);
			ResultSet rs1 = db.query(sql1);
			while(rs1.next()){System.out.print(rs1.getString(2));}
			System.out.println(" ----- -------" + rs.getString(2)+ " -------- " + rs.getString(3)+ " ------- " + rs.getTimestamp(4));
		}
	}
	
	}


