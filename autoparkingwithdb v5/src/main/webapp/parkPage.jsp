<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.autoparkingwithdb.services.*"%>
<%@page import="com.autoparkingwith.persistence.*"%>


<%@page import="java.io.PrintWriter"%>
<%@page import="java.io.IOException"%>

<%!
    public App appObject;
    
    public void jspInit() {
        try{
    		appObject = new App();
		    appObject.setSlots();
		} catch(Exception e) {
			System.out.print(e.getMessage());
		}
		    
	}
	String getResult(String plate, javax.servlet.jsp.JspWriter out) throws IOException{
		String result="";
		try {
		    Park parkVehi = new Park();
		    parkVehi.park(appObject.getVehicleList(), appObject.getAvailableSlots(),
				   plate,appObject.getTransactionDbName(),new PrintWriter(out,true));
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
					<br><br><br><input id="submit" type="submit" value="SUBMIT"  />
				</form>
				<%out.print(getResult(request.getParameter("plate"),out));%>

			</div>
		</div>

		<div class = "split right" id="getDetails">
			<%@include file="getDetails.jsp" %>
		</div>

	</body>
</html>
			