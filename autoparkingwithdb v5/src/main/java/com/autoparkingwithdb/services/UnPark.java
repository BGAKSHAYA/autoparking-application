/**.
 * This package contains classes to help user to find an empty parking space
 */
package com.autoparkingwithdb.services;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.autoparkingwithdb.Exceptions.*;
import com.autoparkingwithdb.persistence.*;


import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.io.PrintStream;
import java.io.File;


/**
 *
 * @author Akshaya_Bindu_Gowri
 *
 */
 public class UnPark {
    /** The format in which the date has to be printed. */
    private DateFormat dateFormat = new SimpleDateFormat(
            "yyyy-mm-dd hh:mm:ss");
    private static Logger logger = Logger.getLogger("com.autoparkingwithio.Park");

    /** A method to park the vehicle.
     * @param vehicleList List of vehicles.
     * @param availableSlots List of available slots.
     * @param plate license plate.
     * @param transactionFilePath Path to transaction file.
     * @param logFilePath Path to log file.
     * @return true if unparking is successful.
     * @throws Exception i.e. IOException, FileNotFoundException.
     */

    public boolean unPark(final HashMap<String, Vehicle> vehicleList,
            final LinkedList<Integer> availableSlots,
            final String plate,
            final String transactionFilePath,
            final String logFilePath, final PrintWriter outServlet) throws Exception {

	 boolean isUnParked = false;
	 System.setOut(new PrintStream(new File("logginh.txt")));
	 try {
	     DbDao2 file = new DbDao2();
	     if (Vehicle.checkFormatOfLicensePlate(plate)) {
	         if (vehicleList.get(plate) != null) {
	              Vehicle unparked = vehicleList.get(plate);
	              availableSlots.add(unparked.getSlotNumber());
	              unparked.setOutTime(new Date());
	              this.appendTransaction(logFilePath,
	                      unparked.getSlotNumber() + "/" + plate + "/"
	                      + dateFormat.format(unparked.getInTime())
	                      + "/"
	                      + dateFormat.format(unparked.getOutTime()));
	              
	              file.deleteTransaction("transaction", plate);
	         	  outServlet.print("Vehicle successfully exited");
	              System.out.println("Vehicle successfully exited");
	              isUnParked = true;
	         } else {
	        	 outServlet.print("Sorry, vehicle not been occupied");
	        	 throw new NoSuchVehicleException("Sorry, vehicle not been occupied");
	         }
	     } else {
        	 outServlet.print("Incorrect license plate format");
	         throw new IncorrectPlateFormatException("Incorrect license plate format");
	     }
     }
     catch(Exception e) {
			System.out.println(e.getMessage());

   	  logger.log(Level.SEVERE, "exception occured is", e);
    }
     return isUnParked;
    }
    
    void appendTransaction(String path, String newLine) throws Exception{
    	try {
            DbDao file = new DbDao();
            file.appendTransaction(path, newLine);
    	}
        catch(Exception e) {
			System.out.println(e.getMessage());

      	  logger.log(Level.SEVERE, "exception occured is", e);
       }
    }
}
