<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.autoparkingwithui.services.*"%>
<%@page import="com.autoparkingwithui.persistence.FileOperations"%>


<%@page import="java.io.PrintWriter"%>
<%@page import="java.io.IOException"%>
<%@page import="java.io.BufferedReader"%>


<%!
	String getResult(String plate, javax.servlet.jsp.JspWriter out) throws IOException{
		App appObject = new App();
		String result="";
		try {
		appObject.setSlots();
	    Park parkVehi = new Park();
	    parkVehi.park(appObject.getVehicleList(), appObject.getAvailableSlots(),
			   plate,appObject.getTransactionFilePath(),new PrintWriter(out,true));
		}
		catch(Exception e) {
			result= e.getMessage();
		}
		return result;
	}
%>
				
				
<html>
	<head>
		<title> Parking Page </title>
		<style>
			.split {
				height: 100%;
				width: 50%;
				position: fixed;
				z-index: 1;
				top: 0;
				overflow-x: hidden;
				padding-top: 20px;
			}
			.left {
				left: 0;
			}
			.right {
				right: 0;
			}
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
		<div class = "split left">
			<div class = "centered">
				<h3> Welcome to Auto Parking Application!<h3><br>
				<h3> You have access to Park the vehicle</h3>
				<form>
					Enter the license number to Park: <input type="text" name="plate"/>
					<br><br><br><input type="submit" value="SUBMIT"/>
				</form>
				<%out.print(getResult(request.getParameter("plate"),out));%>
			</div>
		</div>
		
		
		<div class = "split right">
			<div class = "centered">

				<h3> Get the details of the parking slots</h3>
				<form>
					Enter the Search criteria <input type="text" name="search"/>
					<input type="submit" value="SUBMIT"/><br><br>
				</form>
				<%
					out.print("<table align=\"center\" border=\"1\">");
					FileOperations file = new FileOperations();
					BufferedReader transactionalFile = file.openFileInReadMode(new App().getTransactionFilePath());
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
		</div>
	</body>
</html>
			