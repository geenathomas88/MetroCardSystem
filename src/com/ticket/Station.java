package com.ticket;

import java.util.Scanner;

public class Station {
	
	String boardStation,alightStation;
	static int NOOFSTATIONS = 6;
	
	public enum StationName{
		STATIONA(1),STATIONB(2),STATIONC(3),STATIOND(4),STATIONE(5),STATIONF(6);
		private int value;

		private StationName(int value) {
			this.value = value;
		}		
		
		private int getValue(){
			return value;
		}
	}
//	public String getBoardStation() {
//		return boardStation;
//	}
//
//	public void setBoardStation(String boardStation) {
//		this.boardStation = boardStation;
//	}
//
//	public String getAlightStation() {
//		return alightStation;
//	}
//
//	public void setAlightStation(String alightStation) {
//		this.alightStation = alightStation;
//	}
//	
	public static Object getData(String type){
		Scanner sc = new Scanner(System.in);
		if(type.equalsIgnoreCase("int"))
			return sc.nextInt();
		else
			return sc.next();	
	}
	
	public static int getBoardingStation(){
		System.out.println("Select a number corresponding to your boarding station");
		System.out.println("1. Station A 2. Station B , 3. Station C 4. Station D, 5.Station E, 6.Station F");
		
		int bStation;
		
		while (true) {
			try {
				bStation = (int) getData("int");
				break;
			} catch (Exception E) {
				System.out.println("Please enter a valid number");
			}
		}

		if(bStation > NOOFSTATIONS || bStation < 1)
		{
			System.out.println("Your input doesn't correspond to a station name---EXITING!!");
			System.exit(0);
			}
		return bStation;	
						
	}
	public static int getAlightingStation(){
		System.out.println("Select a number corresponding to your alighting station");
		System.out.println("1. Station A 2. Station B , 3. Station C 4. Station D, 5.Station E, 6.Station F");
		
		int aStation;
		while (true) {
			try {
				aStation = (int) getData("int");
				break;
			} catch (Exception E) {
				System.out.println("Please enter a valid number");
			}
		}
		if(aStation > NOOFSTATIONS || aStation < 1)
		{	System.out.println("Your input doesn't correspond to a station name---EXITING!!");
			System.exit(0);
			}
		return aStation;

	}
	
	public static int abs(int num){
	    return num < 0 ? num * -1 : num;
	}
	public static String stationToString(int station){
		String stationName = null;
		
		for(StationName stName : StationName.values()) {
	        if(stName.getValue() == station)
	        	stationName = stName.toString();	       
	     }				
		return stationName;		
	}
}
