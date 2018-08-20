/**.
 * This package contains classes to help user to find an empty parking space
 */
package com.autoparkingwithdb.services;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.autoparkingwithdb.Exceptions.InvalidCredentialsException;
import com.autoparkingwithdb.persistence.DbDao;


import java.text.SimpleDateFormat;
/**
 * @author Akshaya_Gowri
 */
public class App {

    /** vehicleList contains the list of all the vehicles.*/
    private HashMap<String, Vehicle> vehicleList;
    /** avaiableSlots contains a list of all the available slots.*/
    private LinkedList<Integer> availableSlots;
    /** transactionDbName. */
    private String transactionDbName;
    /** logDbName. */
    private String logDbName;
    /** No of slots. */
    private int noOfSlots = 5;
    private static Logger logger = Logger.getLogger("com.autoparkingwithio.Park");


    /**.
     * App constructor
     */
    public App() {
        vehicleList = new HashMap<String, Vehicle>();
        availableSlots = new LinkedList<Integer>();
        transactionDbName = "transaction";
        logDbName = "log";
        noOfSlots = 5;
    }
    
    public HashMap<String, Vehicle> getVehicleList() {
    	return this.vehicleList;
    }
    
    public LinkedList<Integer> getAvailableSlots() {
    	return this.availableSlots;
    }
    
    public String getTransactionDbName() {
    	return this.transactionDbName;
    }
    
    public String getLogDbName() {
    	return this.logDbName;
    }
    
    public int getSlots() {
    	return this.noOfSlots;
    }

    public void setTransactionDbName(String path) {
        this.transactionDbName = path;
    }
    
    public void setVehicleList(HashMap<String, Vehicle> vehicleList) {
        this.vehicleList= vehicleList;
    }
    
    public void setAvailableSlots(LinkedList<Integer> availableSlots) {
        this.availableSlots = availableSlots;
    }
    
   boolean validateCredentials(final String username, final String password) {

	  boolean isValid = false;
	  try {
	      if (AdminSingleton.getInstance().checkCredentials(username, password)) {
	          System.out.println("Welcome Admin");
	          isValid = true;
	      } else {
	    	  throw new InvalidCredentialsException("Invalid credentials");
	      }
	  }
     catch(Exception e) {
    	  logger.log(Level.SEVERE, "exception occured is", e);
     }
	 return isValid;
	          
   }
   
  
  
    /** Set the available slots.
     * @return avaiableSlots
     * @throws Exception i.e. IOException, FileNotFoundException.
     */

   public LinkedList<Integer> setSlots() throws Exception {
        new DbDao().readFromDB("transaction", this);
        return availableSlots;
    }
  
  
  
}
