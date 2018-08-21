

/**.
 * This package contains classes to help user to find an empty parking space
 */
package com.autoparkingwithdb.persistence;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Iterator; 

import com.autoparkingwithdb.services.*;

import org.hibernate.Session;  
import org.hibernate.SessionFactory;    
import org.hibernate.Transaction;  
import org.hibernate.boot.Metadata;  
import org.hibernate.boot.MetadataSources;  
import org.hibernate.boot.registry.StandardServiceRegistry;  
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;  


/**
 *  @author Akshaya_Bindugowri
 */
public class DbDao2 {
	StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();  
	Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build(); 
    /**
     * @param path Path to the file.
     * @param newLine the parking details
     * @throws Exception i.e. IOException, FileNotFoundException
     */
    public void appendTransaction(final String dbName,
            final String newLine) throws Exception {
    	
    	SessionFactory factory = meta.getSessionFactoryBuilder().build();  
    	Session session = factory.openSession();  
    	Transaction transaction = session.beginTransaction();   
    	
		String[] rowDetails =newLine.split("/");

	    Vehicle insertVehicle = new Vehicle(rowDetails[1],Integer.parseInt(rowDetails[0]), new SimpleDateFormat("yyyy-mm-dd hh:mm:ss").parse(rowDetails[2]) );    
        session.save(insertVehicle);  
	    transaction.commit();  
	    factory.close();  
	    session.close();       
    }    
   
    
    /**
     * @param path Path to the file.
     * @param newLine the parking details
     * @throws Exception i.e. IOException, FileNotFoundException
     */
    public void deleteTransaction(final String dbName,
            final String licensePlate) throws Exception {
    	
    	SessionFactory factory = meta.getSessionFactoryBuilder().build();  
    	Session session = factory.openSession();  
    	Transaction transaction = session.beginTransaction();   
    	

	    Vehicle deleteVehicle = session.find(Vehicle.class, licensePlate);
	    session.remove(deleteVehicle);
	    transaction.commit();  
	    factory.close();  
	    session.close();  
   }
    
    public void readFromDB(final String dbName, App app) throws Exception {
    	  
    	SessionFactory factory = meta.getSessionFactoryBuilder().build();  
    	Session session = factory.openSession();  
    	Transaction transaction = session.beginTransaction(); 
    	
    	HashMap<String, Vehicle> vehicleList= app.getVehicleList();
    	LinkedList<Integer> availableSlots = app.getAvailableSlots();
    	
        int[] frequencyArrayOfSlots = new int[app.getSlots()];

    	@SuppressWarnings("unchecked")
		Iterator<Vehicle> iterator= session.createQuery("from Vehicle").list().iterator();
    	Vehicle readVehicleFromTable;
    	while(iterator.hasNext())
    	    {
    		     readVehicleFromTable= iterator.next();
    	         vehicleList.put(readVehicleFromTable.getLicenseNumber(), readVehicleFromTable);
    	         frequencyArrayOfSlots[readVehicleFromTable.getSlotNumber()-1] = 1;
    	   }
    	
    	for (int i = 1; i <= app.getSlots(); i++) {
            if (frequencyArrayOfSlots[i - 1] == 0) {
                availableSlots.add(i);
            }
        }
    	app.setVehicleList(vehicleList);
    	app.setAvailableSlots(availableSlots);
    	
	    transaction.commit();  
	    factory.close();  
	    session.close(); 
   }
}
