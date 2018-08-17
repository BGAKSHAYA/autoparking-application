/**.
 * This package contains classes to help user to find an empty parking space
 */
package com.autoparkingwithdb.persistence;

import java.sql.*;


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
            final String newLine) throws Exception {
    	Class.forName("com.mysql.jdbc.Driver");
    	try(Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/parking","root","oracle");) {
			Statement stmt=con.createStatement(); 
			
			String[] rowDetails =newLine.split("/");
			String licenseNumber = rowDetails[0];
			int slotNumber = Integer.parseInt(rowDetails[1]);
			Date intime = Date.valueOf(rowDetails[2]);
			
			if("transaction".equals(dbName)) {
               stmt.executeUpdate("insert into transaction values("+licenseNumber+","+slotNumber+","+intime+");");  	
			} else {
				Date outtime = Date.valueOf(rowDetails[3]);
               stmt.executeUpdate("insert into transaction values("+licenseNumber+","+slotNumber+","+intime+","+outtime+");");  		
			}	
		}catch(Exception e){ 
			e.printStackTrace();		
		}  
   }
    
    public void readFromDB(final String dbName) throws Exception {
    	
    	Class.forName("com.mysql.jdbc.Driver");
    	try(Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/parking","root","oracle");) {
			Statement stmt=con.createStatement(); 
			ResultSet rs=stmt.executeQuery("select * from "+dbName+";");  
			while(rs.next())  
				out.print("<tr><td>"+rs.getString(1)+"</td>"+"<td>"+rs.getInt(2)+"</td>"+"<td>"+rs.getTime(3)+"</td></tr>");
			
		}catch(Exception e){ 
			e.printStackTrace();		
		}  
   }
   

}
