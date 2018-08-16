/**
 * This package contains classes to help user to find an empty parking space
 */
package com.autoparkingwithui.services;

//import junit.framework.TestCase;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import org.junit.Before;
import org.junit.Test;

import com.autoparkingwithui.persistence.FileOperations;
import com.autoparkingwithui.services.App;
import com.autoparkingwithui.services.Vehicle;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.io.File;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;

/**
 *
 * @author Akshaya_BinduGowri
 *
 */
public class AppTest {
    /** vehicleList contains the list of all the vehicles.*/
    private HashMap<String, Vehicle> vehicleList = new HashMap<>();
    /** avaiableSlots contains a list of all the available slots.*/
    private LinkedList<Integer> availableSlots = new LinkedList<Integer>();
    /** The format in which the date has to be printed. */
    private DateFormat dateFormat = new SimpleDateFormat(
            "yyyy-mm-dd_hh:mm:ss");
    /** App object */
    private App app = new App();   /** file object*/
    FileOperations file = new FileOperations();
    
    /**Initailization.*/
    @Before
    public void init() {
    System.setIn(new ByteArrayInputStream("1 . 3  . .".getBytes()));
    vehicleList.put("AP16BA1231", new Vehicle("AP16BA1231", 1, new Date()));
    vehicleList.put("AP16BA1232", new Vehicle("AP16BA1232", 2, new Date()));
    vehicleList.put("AP16BA1233", new Vehicle("AP16BA1233", 1 + 2, new Date()));
    availableSlots.add(2 + 2);
    availableSlots.add(1 + 2 + 2);
    file = new FileOperations();
    
    }
    
    /** Testing menuOperations method.
     * @throws Exception */
    @Test
    public void testMenuOperations() throws Exception {
        String path = "tempFile.txt";
        System.setOut(System.out);

        file.openFileInWriteMode(path);
        assertTrue(app.menuOperations(1));
        assertTrue(app.menuOperations(2));
        assertFalse(app.menuOperations(1 + 2));
        assertTrue(app.menuOperations(1 + 2 + 2));
        assertTrue(app.menuOperations(2 + 2));

    }

    /** Testing PrintMenu method.
     * @throws Exception*/
    @Test
    public void testPrintMenu() throws Exception {
       OutputStream os = new ByteArrayOutputStream();
       PrintStream ps = new PrintStream(os);
       System.setOut(ps);
       app.printMenu();
	   String outputNeeded = "\t** MENU **\n"
		       + "\t1. Park\n"
		       + "\t2. Un Park\n"
		       + "\t3. Exit\n"
		       + "\tEnter ur choice";
       assertEquals(outputNeeded, os.toString());
       PrintStream originalOut = System.out;
       System.setOut(originalOut);
       ps.close();
       os.close();
    }

    /** Testing Park method.
     * @throws Exception */
    @Test
    public void testSetSlots() throws Exception {
        String path = "testTransactionalFile.tmp";
        File temp = File.createTempFile("testTransactionalFile", ".tmp"); 
        file.appendTransaction(path, 1 + " " + "AP16BA1231"
                + " " + dateFormat.format(new Date()));
        file.appendTransaction(path, 2 + " " + "AP16BA1232"
                + " " + dateFormat.format(new Date()));
        file.appendTransaction(path, (1 + 2) + " " + "AP16BA1233"
                + " " + dateFormat.format(new Date()));
        app.setTransactionFilePath(path);
        assertEquals(availableSlots, app.setSlots());
        temp.deleteOnExit();
    }
    

    /** Testing PrintMenu method.
     * @throws Exception*/
    @Test
    public void testValidateCredentials() throws Exception {
       assertTrue(app.validateCredentials("admin", "java"));
       assertFalse(app.validateCredentials("admin", "dfjhkds"));
    }
    
   
    /** Testing Main method.
     * @throws Exception*/
    @Test
    public void testMain() throws Exception {
        App.main(new String[] {"admin", "java", "1", "25"});
        App.main(new String[] {"admin", "javas", "0"});
    }
    
    @Test
    public void testGetVehicleList() {
    	App test = new App();
        assertEquals(new HashMap<String, Vehicle>(), test.getVehicleList());
   }
    
    @Test
    public void testGetAvailableSlots() {
    	App test = new App();
        assertEquals(new LinkedList<Integer>(), test.getAvailableSlots());
    }
    
    @Test
    public void testGetTransactionFilePath() {
        assertEquals("transactionalFile.txt",app.getTransactionFilePath());
    }
   
    @Test
    public void testGetLogFilePath() {
        assertEquals("logFile.txt", app.getLogFilePath());
    }
    
}
