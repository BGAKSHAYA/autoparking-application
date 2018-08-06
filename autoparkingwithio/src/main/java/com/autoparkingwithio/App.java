/**.
 * This package contains classes to help user to find an empty parking space
 */
package com.autoparkingwithio;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;
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
    /** transactionFilePath. */
    private String transactionFilePath;
    /** logFilePath. */
    private String logFilePath;
    /** No of slots. */
    private int noOfSlots = 2 + 1 + 2;

    /**.
     * App constructor
     */
    App() {
        consoleScanner = new Scanner(System.in);
        vehicleList = new HashMap<String, Vehicle>();
        availableSlots = new LinkedList<Integer>();
        transactionFilePath = "transactionalFile.txt";
        logFilePath = "logFile.txt";
        noOfSlots = 2 + 1 + 2;
    }
    
    void setTransactionFilePath(String path) {
        this.transactionFilePath = path;
    }
    
   boolean validateCredentials() {
	   boolean isValid = false;
	   try {
	      System.out.println("Enter username for admin");
	      String username = this.getStringFromConsole();
	      System.out.println("Enter password for admin");
	      String password = this.getStringFromConsole();

	      if (AdminSingleton.getInstance().checkCredentials(username, password)) {
	          System.out.println("Welcome Admin");
	          isValid = true;
	      } else {
	    	  throw new ExceptionHandler("Invalid credentials");
	      }
	  }
      catch(ExceptionHandler e) {
    	  System.out.println(e.getMessage());
      }
	 return isValid;
	          
   }
   
   
   /**
    * @param args command line arguments
    * @throws Exception i.e. IOException, FileNotFoundException.
    */
   public static void main(final String[] args) throws Exception {
	      App appObject = new App();
	      if(appObject.validateCredentials()) {
	          appObject.setSlots();
	          appObject.printMenu();
	          while (appObject.menuOperations(appObject.consoleScanner.nextInt())) {
	              System.out.println("Enter ur choice");
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

   LinkedList<Integer> setSlots() throws Exception {
	    FileOperations file = new FileOperations();
        int[] frequencyArrayOfSlots = new int[noOfSlots];

          BufferedReader transactionalFile =
                file.openFileInReadMode(transactionFilePath);
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
                              transactionFilePath);
                      break;
              case 2: System.out.println("Enter licenseno. of vehicle "
                         + "to unpark");
                      UnPark unParkVehi = new UnPark();
                      unParkVehi.unPark(vehicleList, availableSlots,
                    		  this.getStringFromConsole(),
                              transactionFilePath,
                              logFilePath);
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
