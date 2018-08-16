<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.autoparkingwithui.services.*"%>
<%@page import="com.autoparkingwithui.persistence.FileOperations"%>

<%@page import="java.io.BufferedReader"%>
			
				
<html>
	<head>
		<title> Get Details Page </title>
		<style>
			.centered {
				position: absolute;
				top: 50%;
				left: 50%;
				transform: translate(-50%, -50%);
				text-align: center;
			}
		</style>
	</head>
	<body>
		<div class = "centered">
			<h3> Get the details of the parking slots</h3>
			<form>
				Enter the Search criteria <input type="text" name="search"/>
				<input type="submit" value="SUBMIT"/><br><br>
			</form>
			<%
			   try{  
						Class.forName("com.mysql.jdbc.Driver");  
						Connection con=DriverManager.getConnection(  
						"jdbc:mysql://localhost:3306/sonoo","root","root");  
						//here sonoo is database name, root is username and password  
						Statement stmt=con.createStatement();  
						ResultSet rs=stmt.executeQuery("select * from emp");  
						while(rs.next())  
						System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));  
						con.close();  
						}catch(Exception e){ System.out.println(e);}  
						}  
						} 
				out.print("<table align=\"center\" border=\"1\">");
				FileOperations file = new FileOperations();

		        String newLine;
				out.print("<tr><td>Slot Number</td><td>License Number</td><td>In Time</td></tr>");
		        while ((newLine = transactionalFile.readLine()) != null) {
				  out.print("<tr>");
				  String[] line = newLine.split(" ");
				  for(int i=0;i<line.length;i++){
					  out.print("<td>");
					  out.print(line[i]);
					  out.print("</td>");
				  }
				  out.print("</tr>");
		        }
				out.print("</table>");
		  %>
		</div>
	</body>
</html>
			