<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%
    String encodeURL=(String)session.getAttribute("encodeURL");
    
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript" src= "assets/js/jquery-1.11.3.min.js"></script>
</head>
<body onLoad="setTimeout('loadNewDoc()', 35000)">
<script type="text/javascript">
  function submitForm() { 
	  // submits form
	
	  document.getElementById("formButton").click();
	}
  
  $( document ).ready(function() {
	
	  if (document.getElementById("ismForm")) {
		//  window.location.href = "home";
		    setTimeout("submitForm()", 15000); // set timout 
		}
	 
	});
  </script>
  <script type="text/javascript">
  function loadNewDoc(){
      window.location="home";
  }
  </script>
<div style="display: none;">
<!--  <form action="http://uat5yesmoney.easypay.co.in:5050/epyesbc/agent/login/fino" method="get" id="ismForm" target="_blank"> --> 
 <form action="https://pay.encodigi.net.in" method="post" id="ismForm" target="_blank">
    <input type="hidden"  name="params" value="<%=encodeURL%>"/>
        
              
  	<input type="submit"  id="formButton" />
  	
  </form>
  </div>
  
  <center>
	<img src="assets/images/loader.gif" style="margin-top:5%;"/>	
</center>
 <%!
/*  public String getSuccessPath(HttpServletRequest request) {

		StringBuilder path = new StringBuilder();
		path.append("http://");
		path.append(request.getServerName());
		path.append(":");
		path.append(request.getServerPort());
		path.append(request.getContextPath());
		path.append("/yesaepsReturn");
		return path.toString();
	} */


	
	%>
</body>
</html>