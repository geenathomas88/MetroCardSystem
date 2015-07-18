package com.ticket;

import java.util.Scanner;

public class Station {
	
	String boardStation,alightStation;
	static int NOOFSTATIONS = 6;
	
	public String getBoardStation() {
		return boardStation;
	}

	public void setBoardStation(String boardStation) {
		this.boardStation = boardStation;
	}

	public String getAlightStation() {
		return alightStation;
	}

	public void setAlightStation(String alightStation) {
		this.alightStation = alightStation;
	}
	
	public static int getBoardingStation(){
		System.out.println("Select a number corresponding to your boarding station");
		System.out.println("1. Station A 2. Station B , 3. Station C 4. Station D, 5.Station E, 6.Station F");
		Scanner s = new Scanner(System.in);
		int bStation = s.nextInt();
		return bStation;	
		//s.close();						
	}
	public static int getAlightingStation(){
		System.out.println("Select a number corresponding to your alighting station");
		System.out.println("1. Station A 2. Station B , 3. Station C 4. Station D, 5.Station E, 6.Station F");
		Scanner s = new Scanner(System.in);
		int aStation = s.nextInt();
		return aStation;
		//s.close();
	}
	
	public static int abs(int num){
	    return num < 0 ? num * -1 : num;
	}
	public static String stationToString(int station){
		String stationName = null;
		switch(station){
			case 1: stationName = "Station A";
				break;
			case 2: stationName = "Station B";
				break;
			case 3: stationName = "Station C";
				break;	
			case 4: stationName = "Station D";
				break;
			case 5: stationName = "Station E";
				break;
			case 6: stationName = "Station F";
				break;
		}
		return stationName;
		
	}
}
