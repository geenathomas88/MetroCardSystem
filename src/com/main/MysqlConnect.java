package com.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MysqlConnect {
	public Connection conn;	
	private Statement statement;
	public static MysqlConnect db;

	private MysqlConnect() {
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/metrocardsystem";
		String user = "root";
		String password ="root";
		
		try {
			Class.forName(driver).newInstance();
			this.conn = (Connection)DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static MysqlConnect getDbConnection(){
		if(db == null){
			db = new MysqlConnect();}
		return db;
	}
	
	public ResultSet query(String sql) throws SQLException{
		statement = conn.createStatement();
		ResultSet res = statement.executeQuery(sql);
		return res;
		}
	
	public int insert(String sql) throws SQLException{
		statement = conn.createStatement();
		int result = statement.executeUpdate(sql,Statement.RETURN_GENERATED_KEYS);
		ResultSet rs = statement.getGeneratedKeys();
		int id = 0;
		while(rs.next()){
			id = rs.getInt(1);
		}
		return id;	
	}
	public int update(String sql) throws SQLException{
		statement = conn.createStatement();
		int result = statement.executeUpdate(sql);
		return result;
	}
}
