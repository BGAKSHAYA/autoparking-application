<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.autoparkingwithui.services.*"%>
<%@page import="com.autoparkingwithui.persistence.*"%>

<%@page import="java.io.BufferedReader"%>
<%@page import="java.sql.*"%>
				
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
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js" type="text/javascript">
	
		   $(document).ready(function(){
			  $("#search").on("keyup", function() {
			    var value = $(this).val().toLowerCase();
			    $("#transactionTable tr").filter(function() {
			      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
			    });
			  });
			});
		
		</script>
		
	</head>
	<body>
		<div class = "centered">
			<h3> Get the details of the parking slots</h3>
			<form>
				Enter the Search criteria <input type="text" id="search"/>
				<input type="submit" value="SUBMIT"/><br><br>
			</form>
			
			<%
			   out.print("<table align=\"center\" border=\"1\">");
			   out.print("<tr><th>License Number</th><th>Slot Number</th><th>In Time</th></tr>");
            %>
            <tbody id= "transactionTable">
            <%
			   try {
			        Class.forName("com.mysql.jdbc.Driver");
			        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/parking","root","oracle");   
					Statement stmt=con.createStatement();  
					ResultSet rs=stmt.executeQuery("select * from transaction");  
					while(rs.next())  
						out.print("<tr align=\"center\"><td>"+rs.getString(1)+"</td>"+"<td>"+rs.getInt(2)+"</td>"+"<td>"+rs.getTimestamp(3)+"</td></tr>");
					con.close();
				}catch(Exception e){ 
   						 e.printStackTrace();		
				}  
				out.print("</table>");
		     %>
		     </tbody>
		     
		</div>
	</body>
</html>
			