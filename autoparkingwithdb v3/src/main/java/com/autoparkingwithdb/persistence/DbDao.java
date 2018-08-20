/**.
 * This package contains classes to help user to find an empty parking space
 */
package com.autoparkingwithdb.persistence;


import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.LinkedList;

import com.autoparkingwithdb.services.*;


/**
 *  @author Akshaya_Bindugowri
 */
public class DbDao {

	
    /**
     * @param path Path to the file.
     * @param newLine the parking details
     * @throws Exception i.e. IOException, FileNotFoundException
     */
    public void appendTransaction(final String dbName,
            final String newLine) throws ClassNotFoundException, IOException {
    	Class.forName("com.mysql.jdbc.Driver");
    	try(Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/parking","root","oracle");) {

			String[] rowDetails =newLine.split("/");
			System.out.println(rowDetails[0] + " "+ rowDetails[1] + " "+  rowDetails[2]);
			int slotNumber = Integer.parseInt(rowDetails[0]);
			String licenseNumber = rowDetails[1];
			Date intime = new Date(new SimpleDateFormat(
		            "yyyy-mm-dd hh:mm:ss").parse(rowDetails[2]).getTime());
			if("transaction".equals(dbName)) {
				System.out.println("GGGGGNNNNNNNGNGGGG INTO TRANSACTION");
				PreparedStatement stmt = con.prepareStatement("insert into ? values(?, ?,"+intime+");");
                stmt.setString(1, "transaction");
                stmt.setString(2, licenseNumber);
                stmt.setInt(3, slotNumber);
				stmt.executeUpdate();	
			} else {
				Date outtime = new Date(new SimpleDateFormat(
			            "yyyy-mm-dd hh:mm:ss").parse(rowDetails[2]).getTime());				
				System.out.println("GGGGGNNNNNNNGNGGGG INTO TRANSACTION log");
				PreparedStatement stmt = con.prepareStatement("insert into ? values(?, ?, intime, outtime );");
                stmt.setString(1, dbName);
                stmt.setString(2, licenseNumber);
                stmt.setInt(3, slotNumber);
				stmt.executeUpdate();
			}	
		}catch(Exception e){
			System.out.println(e.getMessage());
			e.printStackTrace();
		}  
   }
    
    /**
     * @param path Path to the file.
     * @param newLine the parking details
     * @throws Exception i.e. IOException, FileNotFoundException
     */
    public void deleteTransaction(final String dbName,
            final String licensePlate) throws Exception {
    	Class.forName("com.mysql.jdbc.Driver");
    	try(Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/parking","root","oracle");) {
               PreparedStatement stmt = con.prepareStatement("delete from transaction where license_number = ?;");
               stmt.setString(1,licensePlate);
               stmt.executeUpdate();

		}catch(Exception e){ 
			System.out.println(e.getMessage());
			e.printStackTrace();		
		}  
   }
    
    public void readFromDB(final String dbName, App app) throws Exception {
    	
    	HashMap<String, Vehicle> vehicleList= app.getVehicleList();
    	LinkedList<Integer> availableSlots = app.getAvailableSlots();
        int[] frequencyArrayOfSlots = new int[app.getSlots()];
    	Class.forName("com.mysql.jdbc.Driver");
    	try(Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/parking","root","oracle");) {
			Statement stmt=con.createStatement(); 
			ResultSet rs=stmt.executeQuery("select * from "+dbName+";");  
			while(rs.next()) {
			 	vehicleList.put(rs.getString(1), new Vehicle(rs.getString(1), rs.getInt(2), rs.getTime(3)));
	            frequencyArrayOfSlots[rs.getInt(2) - 1] = 1;
			} 
	          for (int i = 1; i <= app.getSlots(); i++) {
	              if (frequencyArrayOfSlots[i - 1] == 0) {
	                  availableSlots.add(i);
	              }
	          }
		}catch(Exception e){ 
			System.out.println(e.getMessage());

			e.printStackTrace();		
		}  
    	app.setVehicleList(vehicleList);
    	app.setAvailableSlots(availableSlots);

   }
   

}
