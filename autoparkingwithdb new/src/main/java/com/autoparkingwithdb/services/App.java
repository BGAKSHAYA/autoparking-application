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

import java.io.BufferedReader;
import java.text.SimpleDateFormat;
/**
 * @author Akshaya_Gowri
 */
public class App {

    /** consoleScanner opens a input stream from console. */
    private Scanner consoleScanner = new Scanner(System.in);
    /** vehicleList contains the list of all the vehicles.*/
    private HashMap<String, Vehicle> vehicleList;
    /** avaiableSlots contains a list of all the available slots.*/
    private LinkedList<Integer> availableSlots;
    /** transactionDbName. */
    private String transactionDbName;
    /** logDbName. */
    private String logDbName;
    /** No of slots. */
    private int noOfSlots = 2 + 1 + 2;
    private static Logger logger = Logger.getLogger("com.autoparkingwithio.Park");


    /**.
     * App constructor
     */
    public App() {
        consoleScanner = new Scanner(System.in);
        vehicleList = new HashMap<String, Vehicle>();
        availableSlots = new LinkedList<Integer>();
        transactionDbName = "transaction";
        logDbName = "log";
        noOfSlots = 2 + 1 + 2;
    }
    
    public HashMap<String, Vehicle> getVehicleList() {
    	return this.vehicleList;
    }
    
    public LinkedList<Integer> getAvailableSlots() {
    	return this.availableSlots;
    }
    
    public String gettransactionDbName() {
    	return this.transactionDbName;
    }
    
    public String getlogDbName() {
    	return this.logDbName;
    }

    void settransactionDbName(String path) {
        this.transactionDbName = path;
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
   
   
   /**
    * @param args command line arguments
    * @throws Exception i.e. IOException, FileNotFoundException.
    */
   public static void main(final String[] args) throws Exception {
	      App appObject = new App();
	      if(appObject.validateCredentials(args[0], args[1])) {
	    	  int thirdArgument = Integer.parseInt(args[2]);
	    	  if(thirdArgument == 1) 
	    		  appObject.noOfSlots = Integer.parseInt(args[3]);
	          appObject.setSlots();
	          appObject.printMenu();
	          while (appObject.menuOperations(appObject.getIntFromConsole())) {
	              System.out.println("Enter your choice");
	          }
	      }
   }

   /**
    *  Print Menu.
    */
   void printMenu() {
	   String outputNeeded = "\t** MENU **\n"
		       + "\t1. Park\n"
		       + "\t2. Un Park\n"
		       + "\t3. Exit\n"
		       + "\tEnter ur choice";
	   
       System.out.print(outputNeeded);

   }

    /** Set the available slots.
     * @return avaiableSlots
     * @throws Exception i.e. IOException, FileNotFoundException.
     */

   public LinkedList<Integer> setSlots() throws Exception {
	    DbDao file = new DbDao();
        int[] frequencyArrayOfSlots = new int[noOfSlots];

          String newLine;
          int slotNumber;

          while ((newLine = transactionalFile.readLine()) != null) {
              String[] line = newLine.split(" ");
              slotNumber = Integer.parseInt(line[0]);
              vehicleList.put(line[1], new Vehicle(line[1], slotNumber,
                        new SimpleDateFormat(
                        "yyyy-mm-dd_hh:mm:ss").parse(line[2])));
              frequencyArrayOfSlots[slotNumber - 1] = 1;
          }

          for (int i = 1; i <= noOfSlots; i++) {
              if (frequencyArrayOfSlots[i - 1] == 0) {
                  availableSlots.add(i);
              }
          }
          return availableSlots;
    }
  
  
  String getStringFromConsole() {
	  return consoleScanner.next();
  }
  
  int getIntFromConsole() {
	  return consoleScanner.nextInt();
  }
  /** Menu Operations.
   * @param choice user choice.
   * @return isExited.
   * @throws Exception i.e. IOException, FileNotFoundException.
   */
  boolean menuOperations(int choice) throws Exception {
          boolean isNotExited = true;
          switch (choice) {
              case 1: System.out.println("Enter licenseno. of vehicle"
                         + " to park");
                      Park parkVehi = new Park();
                      parkVehi.park(vehicleList, availableSlots,
                    		  this.getStringFromConsole(),
                              transactionDbName,null);
                      break;
              case 2: System.out.println("Enter licenseno. of vehicle "
                         + "to unpark");
                      UnPark unParkVehi = new UnPark();
                      unParkVehi.unPark(vehicleList, availableSlots,
                    		  this.getStringFromConsole(),
                              transactionDbName,
                              logDbName,null);
                      break;
              case 1 + 2: System.out.println("Program Exited");
                          consoleScanner.close();
                          isNotExited = false;
                          break;
              default: System.out.println("Wrong choice");
          }
          return isNotExited;
   }
}
