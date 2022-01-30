<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@page import="com.recharge.model.User"%>   
    
     <%
 
 //String url=(String)session.getAttribute("url");
 String hash=(String)session.getAttribute("hash");
 String api_key = "7rnFly"; 
 String description="Wallet";
 String amount=(String)session.getAttribute("amount");
 String return_url="http://encodigi.net.in/payesponse";
 String ORDER_ID=(String)session.getAttribute("ORDER_ID");
 User userDetails = (User) session.getAttribute("UserDetails");
 
 %>
<!DOCTYPE html>
<html>
   <head>
      <title>Merchant Check Out Page</title>
     <script type="text/javascript" src= "assets/js/jquery-1.10.2.min.js"></script>
   </head>
   <body>
    <script type="text/javascript">
  function submitForm() { 
	  // submits form
	
	  document.getElementById("formButton").click();
	}
  
  $( document ).ready(function() {
	
	  if (document.getElementById("ismForm")) {
		 
		    setTimeout("submitForm()", 1000); // set timout 
		}
	 
	});
  </script>
     <div style="display: none;">
 <form method="post" action="https://test.payu.in/_payment" id="ismForm"> -->
      <!--   <form method="post" action="https://secure.payu.in/_payment" id="ismForm"> -->
               <input type="hidden" name="key" value="<%=api_key%>">
               <input type="hidden" name="amount" value="<%=amount%>">
               <input type="hidden" name="productinfo" value="<%=description%>">
               <input type="hidden" name="email" value="<%=userDetails.getEmail()%>">
               <input type="hidden" name="firstname" value="<%=userDetails.getName()%>">
               <input type="hidden" name="txnid" value="<%=ORDER_ID%>">
               <input type="hidden" name="phone" value="<%=userDetails.getMobile()%>">
               <input type="hidden" name="surl" value="<%=return_url%>">
               <input type="hidden" name="furl" value="<%=return_url%>">
               <input type="hidden" name="hash" value="<%=hash%>">
           <button  id="formButton" >  </button>
       
      </form>
      </div>
      
        <center><img src="assets/images/loader.gif" style="margin-top:5%;"/></center>
   </body>
</html>