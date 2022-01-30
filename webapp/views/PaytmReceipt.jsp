<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@page import="com.recharge.model.User"%>
<%
	User user = (User) session.getAttribute("UserDetails");		
	
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>PayTm Receipt</title>
    <link rel="stylesheet" href="assets/css/bootstrap.min.css">
    <link rel="stylesheet" href="assets/font/css/font-awesome.min.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="assets/js/jquery.js"></script>
    <script src="assets/js/bootstrap.min.js"></script>
<style type="text/css">
@media print
   {
      .btn{
      	display: none !important;
      }
   }
</style>
</head>
<body>
<div class="container">
    <div class="row" style="margin-top: 4%">
        
        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
        	<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
            	<!-- <img src="assets/images/RBL-logo.jpg" alt="RBL Bank" class="img-responsive"> -->
            </div>
            <div class="col-lg-2 col-md-2 col-sm-2 col-xs-2 "></div>
            <div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 "><h3 class="lead">Customer Copy</h3></div>
        </div>
     
    </div>
    <div class="row" style="margin-top: 4%">
        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
            <div style="width: 75%; margin: 0 auto; border: 1px #999 solid; border-radius: 10px;padding: 2%;">
                <table class="table table-bordered table-responsive " style="width: 100%;">

                    <tbody>
                        <tr>
                            <td>Date & Time: </td>
                            <td>${PayTmreceipt.addedon}</td>
                        </tr>
                        <tr>
                            <td>ORDERID:</td>
                            <td>${PayTmreceipt.txnid}</td>
                        </tr>
                        <tr>
                            <td>TXNID:</td>
                            <td>${PayTmreceipt.mihpayid}</td>
                        </tr>
                       <%--  <tr>
                            <td>Name:</td>
                            <td><%=user.getName() %></td>
                        </tr> --%>
                       <%--  <tr>
                            <td>BC Location:</td>
                            <td><%=user.getAddress() %></td>
                        </tr> --%>
                       <%--  <tr>
                            <td>TXNAMOUNT:</td>
                            <td>${PayTmreceipt.TXNAMOUNT}</td>
                        </tr> --%>
                        <tr>
                            <td>PAYMENTMODE:</td>
                            <td>${PayTmreceipt.mode}</td>
                        </tr>
                      <%--   <tr>
                            <td>STATUS:</td>
                            <td>${PayTmreceipt.STATUS}</td>
                        </tr> --%>
                       
                      <tr>
                            <td>BANKTXNID:</td>
                            <td>${PayTmreceipt.bank_ref_num}</td>
                        </tr> 
                        <tr>
                            <td>status::</td>
                            <td>${PayTmreceipt.status}</td>
                        </tr>
                        <tr>
                            <td>Txn Amt:</td>
                            <td>${PayTmreceipt.amount}</td>
                        </tr>
                      <%--   <tr>
                            <td>A/c bal:</td>
                            <td>${RBLTransaction.accountBalance}</td>
                        </tr>
                        <tr>
                            <td>Response Code: </td>
                            <td>${RBLTransaction.statusCode}</td>
                        </tr>
                         --%>
                       


                    </tbody>
                </table>
            </div>
        </div>
        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-left" style="margin-top: 2%">
        	<div class="col-lg-5 col-md-5 col-sm-6 col-xs-6 text-left">
            	<h3 class="lead">Technology Service Provider</h3>
            </div>
            <div class="col-lg-7 col-md-7 col-sm-6 col-xs-6 text-center">
            	<button class="btn btn-info" onclick="window.print();">Print</button>
            	<a class="btn btn-wotning" href="home">Go Back to Home</a>
            </div>
        </div>
    </div>
</div>
</body>
</html>