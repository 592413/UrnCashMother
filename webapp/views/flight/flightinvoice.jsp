
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@page import="java.util.Map"%>
    <%@page import="java.util.List"%>
    <%@page import="com.skyflight.model.Flightdetail"%>
    <%@page import="com.skyflight.model.PassengerDetail"%>
     <%@page import="com.skyflight.model.Passengerfare"%>
      <%@page import="com.skyflight.model.FlightFare"%>
    <%	
    Map<String,Object> flightbooking=(Map<String,Object>)session.getAttribute("flightbooking");
    
    List<Flightdetail> Flightdetail=( List<Flightdetail>)session.getAttribute("flightdetails");
    
    List<PassengerDetail> passengerlist=(List<PassengerDetail>)session.getAttribute("passengerlist");
    
    List<Passengerfare> listPassengerfare=(List<Passengerfare>)session.getAttribute("listPassengerfare");
    
    
    List<FlightFare> listFlightFare=(List<FlightFare>)session.getAttribute("listFlightFare");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
<title>Flight</title>
<link rel="icon" href="#" type="image/x-icon" />
<link href="https://fonts.googleapis.com/css?family=Roboto:400,700&subset=latin,cyrillic-ext" rel="stylesheet" type="text/css">
<link href="assets/css/material-icons.css" rel="stylesheet">
<link href="assets/css/bootstrap.css" rel="stylesheet">
<link href="assets/css/jquery-ui.css" rel="stylesheet">
<link href="assets/css/bootstrap-select.css" rel="stylesheet">
<link href="assets/css/waves.css" rel="stylesheet" />
<link href="assets/css/animate.css" rel="stylesheet" />
<link href="assets/css/morris.css" rel="stylesheet" />
<link href="assets/css/style.css" rel="stylesheet">
<link href="assets/css/all-themes.css" rel="stylesheet" />
<!-- <link href="assets/css/font-awesome.min.css" rel="stylesheet" /> -->
<link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
<link href="assets/css/chosen.css" rel="stylesheet" />
<link href="assets/css/bootstrap-datepicker.min.css" rel="stylesheet" />
<link href="assets/css/responsive.css" rel="stylesheet" />
<link href="assets/css/sweetalert.css" rel="stylesheet" />
<!-- Latest compiled and minified JavaScript -->

	
<script src="assets/js/jquery.min.js"></script> 

<script src="assets/js/jquery-1.11.3.min.js"></script>

<script	src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.9.0/moment-with-locales.min.js"></script>
<script	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/3.1.3/js/bootstrap-datetimepicker.min.js"></script>
		<script src="assets/js/sweetalert.min.js"></script> 

<style type="text/css">
.floatMessage {
	position: absolute;
    z-index: 999999;
    left: 40%;
    top: 29%;
    width: 27%;
	
}

.customIcon .body .btn {
    width: 92%;
    color: #fff !important;
    background: #cddc39 !important;
}

	.floatMessage {
		position: absolute;
		z-index: 999999;
		left: 40%;
		top: 29%;
		width: 27%;
	}
	
	.pre-div {
		background-color: #fff;
		bottom: 0;
		height: 100%;
		left: 0;
		position: fixed;
		right: 0;
		top: 0;
		z-index: 10000;
	}
	
	.fade-scale {
	  transform: scale(0);
	  opacity: 0;
	  -webkit-transition: all .25s linear;
	  -o-transition: all .25s linear;
	  transition: all .25s linear;
	}
	
	.fade-scale.in {
	  opacity: 1;
	  transform: scale(1);
	}

.page-loader-wrapper {
	z-index: 99999999;
	position: fixed;
	top: 0;
	left: 0;
	bottom: 0;
	right: 0;
	width: 100%;
	height: 100%;
	background: #ffffff;
	overflow: hidden;
	text-align: center;
}

.page-loader-wrapper p {
    font-size: 22px;
    margin-top: -59px;
    font-weight: bold;
    color: #444;
}
.page-loader-wrapper .loader img {
    width: 13%;
}

.page-loader-wrapper .loader {
    position: relative;
    top: calc(40% - 30px);
}

.SUCCESS {
	color: green !important;
	background-color: #fff !important;
}

.FAILED {
	color: red;
}



.PENDING {
	color: #ffa700;
}
.REFUND {
	color: cyan;
}

.visiable-on-responsive{
		display: none !important;
	}

@media (max-width: 768px){
	.visiable-on-responsive{
		display: block !important;
	}
}


.fticketdetails{
	border: 1px dashed #3c763d;
    padding: 12px;
    margin: 12px;
}


</style>

</head>
<body class="{{reseller.theme}}" ng-app = "app" ng-controller = "appController">
<input type="text" id="flightbooking" value="${flightbooking}">
<input type="text" id="flightdetails" value="${flightdetails}">
<input type="text" id="listPassengerfare" value="${listPassengerfare}">
<input type="text" id="passengerlist" value="${passengerlist}">
	<!-- Page Loader -->
	<div class="page-loader-wrapper" ng-show="loader" >
		<div class="loader">
			<div class="preloader"><img alt="" src="assets/images/loader.gif"></div>
		</div>
	</div>
	<alert-message alert="alertMessage"></alert-message>
	
	<!-- #END# Page Loader -->
	<!-- Overlay For Sidebars -->
	<div class="overlay"></div>
	<!-- #END# Overlay For Sidebars -->

	 <!-- Top Bar -->
<%-- 	<div class="container-fluid navbar1 navbar" style="box-shadow: 0 2px 10px rgba(0, 0, 0, 0.2);">
	<div class="row">
	<div class="col-md-2"> 
		<!-- <a class="navbar-brand " href="home"><img class="logo" alt="" src="assets/images/logo.png" ></a> -->
		<a class="navbar-brand " href="home"><img class="logo img-responsive" id="logo" src = ""></a>
	</div>
	<div class="col-md-9">
		<nav class="navbar">
		<div class="container" style="width: 97%; height: 10vh;"> 
			<div class="navbar-header">
				<a href="javascript:void(0);" class="navbar-toggle collapsed col-xs-hidden" data-toggle="collapse" data-target="#navbar-collapse" aria-expanded="false"></a> 
				<a href="javascript:void(0);" class="bars"></a> 
			</div>
			<div class="collapse navbar-collapse" id="navbar-collapse">
				<div class="col-md-8 col-xs-hidden ">
					<marquee style="margin-left: -10%; margin-top: 5%;">
						<a class="navbar-brand1" href="#" style="color: #fff">{{News.news}}</a>
					</marquee>
				</div>
				<div class="col-md-3 col-xs-hidden navbar-header"
					style="color: #fff; padding-top: 1.4%; padding-left: 4%">
					<%if(user.getRoleId() != 1){ %>
					<h4>
						Balance <b>Rs</b><strong>{{userDetails.balance}}/-</strong>
					</h4>
					<%} %>
				</div>
				<div class="col-md-1">
					
					
				</div>
				
			</div>
		</div>
	</nav>
	</div>
	
	<div class="col-md-1">
		
	
	</div>
	</div>
	</div> --%>
	<!-- #Top Bar -->
	<!-- #Top Bar -->
	
	<section class="content">
		<div class="container-fluid">

		<!-- div1011  -->
		
		<div id="div1011" class="new_flight-detals" style="background-color: white;">
        <div class="container">
            <div class="it-row" style="margin-right: -15px;margin-left: -15px;">
                <div class="col-md-8">
                    <div class="tc-panel-group" style="margin-bottom: 20px;">
                        <div class="tc-panel tc-panel-primary" style="margin-bottom: 0;border-radius: 4px;border: 1px solid #337ab7;">
                            <div class="tc-panel-heading" style="background-color: #337ab7;color: #fff;padding: 5px 15px;">
                                <img class="logo img-responsive" id="logo" src = "">
                                <h4 style="font-size: 15px;font-weight: 700;display: inline-block;">TICKET - Confirmed</h4>
                                <p style="color: #fff;font-size: 12px;display: inline-block;float: right;margin-top: 10px;">Booking Id: NF78176248706468</p>
                            </div>
                            <div class="tc-panel-body" style="padding: 15px;display:flow-root;">
                                <ul style="background-color: #a6c8ff;padding: 4px 10px;">
                                    <li style="margin-right: 50px;display: inline-block;color: #3909ff;font-weight: 700;"> SUN, 01 DEC '19 
                                    </li>
                                    <li style="margin-right: 50px;display: inline-block;color: #3909ff;font-weight: 700;"> KOLKATA TO DELHI  
                                    </li>
                                    <li style="margin-right: 50px;display: inline-block;color: #3909ff;font-weight: 700;float: right;"> 2h 15m 
                                    </li>
                                </ul>

                                <div class="wrwa">
                                    <div class="colf-md-4" style="width:33%;float:left;padding-right: 15px;padding-left: 15px;">
                                        <img class="pr_img" src="img/AI.png">
                                        <h5>Air India</h5>
                                    </div>
                                    <div class="ff-md-3" style="width: 25%;float: left;margin-bottom:20px;">
                                        <h3 style="font-weight: 700;margin: 10px 0 2px;">CCU</h3>
                                        <p style="border-bottom: 2px solid #ccc;padding-bottom: 10px;">KOLKATA</p>
                                        <p style="margin-bottom: 0;">20:30 hrs, 01 Dec</p>
                                        <p style="margin-bottom: 0;font-size: 13px;color: #8a8a8a;">Nscbi Airport</p>
                                        <p style="margin-bottom: 0;color: blue;">Terminal 2</p>   
                                    </div>
                                    <div class="ff-md-2" style="width:16%;float: left;">
                                        <p style="margin-bottom: 0px;margin-top: 12px;"><img src="assets/images/asdi.jpeg"></p>
                                        <h5 style="border-bottom: 2px solid #ccc;padding-bottom: 10px;">02h 10m</h5>
                                        <p style="font-size: 13px;color: #5f5f5f;">Economy</p>
                                    </div>
                                    <div class="ff-md-3" style="width: 25%;float: left;margin-bottom:20px;">
                                        <h3 style="font-weight: 700;margin: 10px 0 2px;">DEL</h3>
                                        <p style="border-bottom: 2px solid #ccc;padding-bottom: 10px;">DELHI</p>
                                        <p style="margin-bottom: 0;">20:30 hrs, 01 Dec</p>
                                        <p style="margin-bottom: 0;font-size: 13px;color: #8a8a8a;">Nscbi Airport</p>
                                        <p style="margin-bottom: 0;color: blue;">Terminal 2</p>
                                    </div>
                                </div>


                                 <div class="fr-l">
                                    <div class="ygdg fare table-responsive" style="width: 100%;float: left;">
                                        <div class="dgdf">
                                            <table class="table table-bordered table-hover">
                                                <thead>
                                                <tr>
                                                    <th>PASSENGER NAME</th>
                                                    <th>PNR</th>
                                                    <th>E-TICKET NO.</th>
                                                    <th>SEAT</th>
                                                </tr>
                                                </thead>
                                                <tbody>
                                                 <%
                                        for(int i=0;i<passengerlist.size();i++){
                                        
                                        %>
                                                <tr>
                                                    <td><%=passengerlist.get(i).getTitle()%>. <%=passengerlist.get(i).getFirstname()%>  <%=passengerlist.get(i).getLastname()%>, Adult</td>
                                                    <td><%=flightbooking.get("PNR") %></td>
                                                    <td><%=passengerlist.get(i).getTicketnumber()%></td>
                                                    <td>1</td>
                                                </tr>
                                                 <%
                                        }%>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>

                                <div class="bild-e" style="border: 1px solid #ccc;float: left;padding: 5px;">
                                    <h4>IMPORTANT INFORMATION</h4>
                                    <ul>
                                        <li>* This is computer generated invoice signature not required.</li>
                                        <li>* All Cases Disputes are subject to New Delhi Jurisdiction.</li>
                                        <li>* Refunds Cancellations are subject to Airline's approval.</li>
                                        <li>* Kindly check all details carefully to avoid unnecessary complications.</li>
                                        <li>* CHEQUE : Must be drawn in favour of 'Travel site name'.</li>
                                        <li>* LATE PAYMENT : Interest per annum will be charged on all outstanding bills after due date.</li>
                                    </ul>
                                </div>

                                <div class="fr-l">
                                    <div class="ygdg fare table-responsive" style="width: 100%;float: left;">
                                        <div class="dgdf">
                                            <h4 style="font-size: 15px;font-weight: 600;">BAGGAGE INFORMATION</h4>
                                            <table class="table table-bordered table-hover">
                                                <thead>
                                                <tr>
                                                    <th>Type </th>
                                                    <th>Sector</th>
                                                    <th>Cabin</th>
                                                    <th>Check-in</th>
                                                </tr>
                                                </thead>
                                                <tbody>
                                                <tr>
                                                    <td>Arun Goswami, Adult</td>
                                                    <td>HG4DD</td>
                                                    <td>098-9445607370</td>
                                                    <td>1</td>
                                                </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>

                                <div class="fr-l">
                                    <div class="ygdg fare table-responsive" style="width: 100%;float: left;">
                                        <div class="dgdf">
                                            <h4 style="font-size: 15px;font-weight: 600;">CANCELLATION AND DATE CHANGE CHARGES</h4>
                                            <div class="fdfg" style="width: 50%;float: left;">
                                                <table class="table table-bordered table-hover">
                                                    <thead>
                                                    <tr>
                                                        <th>CCU-DEL</th>
                                                        <th colspan="5">Cancellation Charges</th>
                                                    </tr>
                                                    </thead>
                                                    <tbody>
                                                        <tr>
                                                            <td>Type</td>
                                                            <td>Condition</td>
                                                            <td>Airline</td>
                                                            <td>co-name</td>
                                                        </tr>
                                                        <tr>
                                                            <td>adult</td>
                                                            <td>After departure</td>
                                                            <td colspan="5">Non-Refundable</td>
                                                        </tr>
                                                        <tr>
                                                            <td></td>
                                                            <td>0 hrs - 1 day</td>
                                                            <td colspan="5">Non-Refundable</td>
                                                        </tr>
                                                        <tr>
                                                            <td></td>
                                                            <td>1 day - 365 days</td>
                                                            <td>2625</td>
                                                            <td>500</td>
                                                        </tr>
                                                    </tbody>
                                                </table>
                                            </div>
                                            <div class="fdfg" style="width: 50%;float: left;padding-left: 15px;">
                                                <table class="table table-bordered table-hover">
                                                    <thead>
                                                        <tr>
                                                            <th>CCU-DEL</th>
                                                            <th colspan="5">Date Change Charges</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <tr>
                                                            <td>Type</td>
                                                            <td>Condition</td>
                                                            <td>Airline</td>
                                                            <td>co-name</td>
                                                        </tr>
                                                        <tr>
                                                            <td>adult</td>
                                                            <td>After departure</td>
                                                            <td colspan="5">Non-Refundable</td>
                                                        </tr>
                                                        <tr>
                                                            <td></td>
                                                            <td>0 hrs - 1 day</td>
                                                            <td colspan="5">Non-Refundable</td>
                                                        </tr>
                                                        <tr>
                                                            <td></td>
                                                            <td>1 day - 365 days</td>
                                                            <td>2625</td>
                                                            <td>500</td>
                                                        </tr>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="fr-l">
                                    <div class="ygdg fare table-responsive" style="width: 100%;float: left;">
                                        <div class="dgdf">
                                            <h4 style="font-size: 15px;font-weight: 600;">24x7 CUSTOMER SUPPORT</h4>
                                            <div class="fdfg" style="width: 50%;float: left;">
                                                <table class="table table-bordered table-hover">
                                                    <thead>
                                                    <tr>
                                                        <th colspan="5">company name Support</th>
                                                    </tr>
                                                    </thead>
                                                    <tbody>
                                                        <tr>
                                                            <td>Tel</td>
                                                            <td>+ 4646686416</td>
                                                        </tr>
                                                    </tbody>
                                                </table>
                                            </div>
                                            <div class="fdfg" style="width: 50%;float: left;padding-left: 15px;">
                                                <table class="table table-bordered table-hover">
                                                    <thead>
                                                        <tr>
                                                            <th colspan="5">Airline Support</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <tr>
                                                            <td>AIR INDIA</td>
                                                            <td>+ 4646686416</td>
                                                        </tr>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
		
	<!-- div1012  -->
	
		<div class="invoice" style="padding: 20px 0;background-color: #fff;">
        <div class="in-container" style="width:1170px;padding-right: 15px;padding-left: 15px;margin-right: auto;margin-left: auto;">
            <div class="inv-row" style="margin-right: -15px;margin-left: -15px;">
                <div class="in-col-md-10" style="padding-right: 15px;padding-left: 15px;width: 83%;">
                    <div class="inv-panel-group" style="margin-bottom: 20px;">
                        <div class="inv-panel inv-panel-primary" style="margin-bottom: 0;border-radius: 4px;border: 1px solid #337ab7;">
                            <div class="inv-panel-heading" style="background-color: #337ab7;color: #fff;padding: 10px 15px;">
                                <div class="inv-row" style="margin-right: -15px;margin-left: -15px;">
                                    <div class="col-md-12-inv" style="padding-right: 15px;padding-left: 15px;">
                                        <h4 style="font-size:25px;font-weight:600;text-transform: uppercase;margin: 0;">Invoice :-</h4>
                                    </div>
                                </div>
                            </div>
                            <div class="inv-panel-body" style="padding: 15px;display: flow-root;">
                                <div class="inv-row" style="margin-right: -15px;margin-left: -15px;    display: flex;margin-bottom:50px;">
                                    <div class="inv-col-md-8" style="width: 66%;display: inline-block;padding-right: 15px;padding-left: 15px;">
                                            <div class="invo-le" style="padding: 20px;border-radius: 2px;">
                                            <ul>
                                                <li style="margin-bottom: 8px;font-weight: 600;">Invoice Details</li>
                                                <li style="margin-bottom: 8px;">Invoice No. : <span>4646614</span></li>
                                                <li style="margin-bottom: 8px;">Invoice Date : <span>  2019-12-01</span></li>
                                                <li style="margin-bottom: 8px;">PAN No : <span>AADCM5146R</span></li>
                                                <li style="margin-bottom: 8px;">GSTIN No : <span>  06AADCM5146R1ZZ</span></li>
                                                <li style="margin-bottom: 8px;">Service Category : <span>  Reservation services for air transportation. SAC Code : 998551</span></li>
                                            </ul>
                                        </div>
                                    </div>
                                    <div class="col-md-4-inv" style="width:33%;position: relative;min-height: 1px;padding-right: 15px;padding-left: 15px;float: left;">
                                        <div class="img-responsive-in" style="background-color: #e2e2e2;border: 2px dashed #337ab7;padding: 20px; text-align: center;">
                                            <h2 style="margin: 0;">LOGO</h2>
                                        </div>
                                        <h3 style="margin: 5px;font-size: 15px;font-weight: 600;">Customer Details</h3>
                                        <p>Place of Supply: Haryana</p>
                                    </div>
                                </div>
                                 <div class="in-tebil table-responsive-in" style="min-height: .01%;overflow-x: auto;">
                                    <table class="table-in in-table-bordered" style="width: 100%;max-width: 100%;margin-bottom: 20px;border: 1px solid #ddd;">
                                        <thead>
                                        <tr style="border-bottom: 1px solid #ccc;">
                                            <th style="border-right: 1px solid #ccc;font-size: 12px;font-weight: 500;padding: 10px;">Booked by</th>
                                            <th style="border-right: 1px solid #ccc;font-size: 12px;font-weight: 500;padding: 10px;"   style="border-right: 1px solid #ccc;">Booked ID </th>
                                            <th style="border-right: 1px solid #ccc;font-size: 12px;font-weight: 500;padding: 10px;"   style="border-right: 1px solid #ccc;">Booked Date</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <td style="border-right: 1px solid #ccc;padding: 10px;">ARUN GOSWAMI<br>(prateetisingha21@gmail.com)<br>(919051297971)</td>
                                                <td style="border-right: 1px solid #ccc;padding: 10px;">NF78176248706468</td>
                                                <td style="border-right: 1px solid #ccc;padding: 10px;">Sun Dec 01 14:00:01 IST 2019</td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>

                                <div class="in-tebil table-responsive-in" style="min-height: .01%;overflow-x: auto;">
                                    <h5 style="font-size: 15px;font-weight: 600;">Flight Details</h5>
                                    <table class="table-in in-table-bordered" style="width: 100%;max-width: 100%;margin-bottom: 20px;border: 1px solid #ddd;">
                                        <thead>
                                        <tr style="border-bottom: 1px solid #ccc;">
                                            <th style="border-right: 1px solid #ccc;font-size: 12px;font-weight: 500;padding: 10px;">AIR INDIA</th>
                                            <th style="border-right: 1px solid #ccc;font-size: 12px;font-weight: 500;padding: 10px;"   style="border-right: 1px solid #ccc;">CCU</th>
                                            <th style="border-right: 1px solid #ccc;font-size: 12px;font-weight: 500;padding: 10px;"   style="border-right: 1px solid #ccc;">DEL</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        </tbody>
                                    </table>
                                </div>

                                <div class="in-tebil table-responsive-in" style="min-height: .01%;overflow-x: auto;">
                                    <h5 style="font-size: 15px;font-weight: 600;">Passengers:</h5>
                                    <table class="table-in in-table-bordered" style="width: 100%;max-width: 100%;margin-bottom: 20px;border: 1px solid #ddd;">
                                        <thead>
                                        <tr style="border-bottom: 1px solid #ccc;">
                                            <th style="border-right: 1px solid #ccc;font-size: 12px;font-weight: 500;padding: 10px;">01. ARUN GOSWAMI
                                            </th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        </tbody>
                                    </table>
                                </div>

                                <div class="in-tebil table-responsive-in" style="min-height: .01%;overflow-x: auto;">
                                    <div class="in-row" style="margin-right: 15px;margin-left:15px;">
                                        <div class="serf-md-6" style="width:50%;position: relative;min-height: 1px;padding-right: 15px;float: left;">
                                        <h5 style="font-size: 15px;font-weight: 600;">Fare Details:</h5>
                                        <table class="table-in in-table-bordered" style="width: 100%;max-width: 100%;margin-bottom: 20px;border: 1px solid #ddd;">
                                        <thead>
                                        <tr style="border-bottom: 1px solid #ccc;">
                                            <th style="border-right: 1px solid #ccc;font-size: 12px;font-weight: 500;padding: 10px;">Fare/Charges
                                            </th>
                                            <th style="border-right: 1px solid #ccc;font-size: 12px;font-weight: 500;padding: 10px;">Passenger 01
                                            </th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <td style="border-right: 1px solid #ccc;padding: 10px;">Base Fare</td>
                                                <td style="border-right: 1px solid #ccc;padding: 10px;">5530</td>
                                            </tr>
                                        </tbody>
                                    </table>
                                    </div>
                                    <div class="gs-md-6" style="width:50%;position: relative;min-height: 1px;padding-right: 15px;float: left;">
                                        <h5 style="font-size: 15px;font-weight: 600;">Tax and Other Charges:</h5>
                                        <table class="table-in in-table-bordered" style="width: 100%;max-width: 100%;margin-bottom: 20px;border: 1px solid #ddd;">
                                        <thead>
                                        <tr style="border-bottom: 1px solid #ccc;">
                                            <th style="border-right: 1px solid #ccc;font-size: 12px;font-weight: 500;padding: 10px;">Other Surcharge
                                            </th>
                                            <th style="border-right: 1px solid #ccc;font-size: 12px;font-weight: 500;padding: 10px;">6464.00
                                            </th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <td style="border-right: 1px solid #ccc;padding: 10px;">Total Fare</td>
                                                <td style="border-right: 1px solid #ccc;padding: 10px;">6464.00</td>
                                            </tr>
                                        </tbody>
                                    </table>
                                    </div>
                                    </div>
                                </div>

                                <div class="in-tebil table-responsive-in" style="min-height: .01%;overflow-x: auto;">
                                    <table class="table-in in-table-bordered" style="width: 100%;max-width: 100%;margin-bottom: 20px;border: 1px solid #ddd;">
                                        <thead>
                                        <tr style="border-bottom: 1px solid #ccc;">
                                            <th style="border-right: 1px solid #ccc;font-size: 12px;font-weight: 500;padding: 10px;">*Total Fare (All Passenger):</th>
                                            <th style="border-right: 1px solid #ccc;font-size: 12px;font-weight: 500;padding: 10px;"   style="border-right: 1px solid #ccc;">INR 6824.00</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <td style="border-right: 1px solid #ccc;padding: 10px;">NF78176248706468</td>
                                                <td style="border-right: 1px solid #ccc;padding: 10px;">INR 6824.00</td>
                                            </tr>
                                            <tr>
                                                <td style="border-right: 1px solid #ccc;padding: 10px;">Convenience Fee:</td>
                                                <td style="border-right: 1px solid #ccc;padding: 10px;">INR 6824.00</td>
                                            </tr>
                                            <tr>
                                                <td style="border-right: 1px solid #ccc;padding: 10px;">Reversal of Convenience Fee:</td>
                                                <td style="border-right: 1px solid #ccc;padding: 10px;">INR 6824.00</td>
                                            </tr>
                                            <tr>
                                                <td style="border-right: 1px solid #ccc;padding: 10px;">CGST @9% on (a): </td>
                                                <td style="border-right: 1px solid #ccc;padding: 10px;">INR 6824.00</td>
                                            </tr>
                                            <tr>
                                                <td style="border-right: 1px solid #ccc;padding: 10px;">SGST @9% on (a):  </td>
                                                <td style="border-right: 1px solid #ccc;padding: 10px;">INR 6824.00</td>
                                            </tr>
                                            <tr style="background: #d8faff;">
                                                <td style="border-right: 1px solid #ccc;padding: 10px;">Grand Total:</td>
                                                <td style="border-right: 1px solid #ccc;padding: 10px;">INR 6824.00</td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>



                                <div class="confi-bod-in" style="border: 2px dashed #337ab7;padding: 20px;">
                                    <div class="inv-row" style="margin-right: -15px;margin-left: -15px;display: flex;">
                                        <div class="col-md-8-in table-responsive-in" style="min-height: .01%;overflow-x: auto;padding-right:15px;padding-left:15px;width: 66%;">
                                             <h3 style="margin-top: 10px;font-size: 16px;font-weight: 600;">Support Details:</h3>
                                            <table class="table-in in-table-bordered" style="width: 100%;max-width: 100%;margin-bottom: 20px;border: 1px solid #ddd;">
                                                <thead>
                                                    <tr style="border-bottom: 1px solid #ccc;">
                                                        <th style="border-right: 1px solid #ccc;padding: 10px;">company Address</th>
                                                        <th style="border-right: 1px solid #ccc;padding: 10px;">Support:</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <tr>
                                                        <td style="border-right:1px solid #ccc; border-bottom: 1px solid #ccc; padding: 10px;">Less</td>
                                                        <td style="border-right:1px solid #ccc; border-bottom: 1px solid #ccc; padding: 10px;">Commission Earned</td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>

                                <div class="gst" style="margin-top: 20px;">
                                    <div class="inv-row" style="margin-right: -15px;margin-left: -15px;display: flex;">
                                        <div class="col-md-6-in table-responsive-in" style="width: 100%; padding-right: 15px;padding-left: 15px; display: inline-block; min-height: .01%;overflow-x: auto;">
                                            <!-- <h2 style="font-size: 16px;font-weight: 600;">Passenger GST Details:</h2> -->
                                            <table class="table-in" style="width: 100%;max-width: 100%;margin-bottom: 20px;border: 1px solid #ddd;">
                                                <thead>
                                                    <tr style="border-bottom: 1px solid #ccc;">
                                                        <th style="font-size: 13px; font-weight: normal; padding: 10px; border-right: 1px solid #ccc;">Name</th>
                                                        <th style="font-size: 13px; font-weight: normal; padding: 10px; border-right: 1px solid #ccc;">company name</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <tr>
                                                        <td style="border-right:1px solid #ccc; border-bottom: 1px solid #ccc; padding: 10px;font-size: 13px;font-weight: normal;">Registered Office</td>
                                                        <td style="border-right:1px solid #ccc; border-bottom: 1px solid #ccc; padding: 10px;font-size: 13px;font-weight: normal;">address</td>
                                                    </tr>
                                                    <tr>
                                                        <td style="border-right:1px solid #ccc; border-bottom: 1px solid #ccc; padding: 10px;font-size: 13px;font-weight: normal;">Tel No.</td>
                                                        <td style="border-right:1px solid #ccc; border-bottom: 1px solid #ccc; padding: 10px;font-size: 13px;font-weight: normal;">46426656657</td>
                                                    </tr>
                                                    <tr>
                                                        <td style="border-right:1px solid #ccc; border-bottom: 1px solid #ccc; padding: 10px;font-size: 13px;font-weight: normal;">Fax No.</td>
                                                        <td style="border-right:1px solid #ccc; border-bottom: 1px solid #ccc; padding: 10px;font-size: 13px;font-weight: normal;">address</td>
                                                    </tr>
                                                    <tr>
                                                        <td style="border-right:1px solid #ccc; border-bottom: 1px solid #ccc; padding: 10px;font-size: 13px;font-weight: normal;">Corporate Identity No.(CIN)</td>
                                                        <td style="border-right:1px solid #ccc; border-bottom: 1px solid #ccc; padding: 10px;font-size: 13px;font-weight: normal;">address</td>
                                                    </tr>
                                                    <tr>
                                                        <td style="border-right:1px solid #ccc; border-bottom: 1px solid #ccc; padding: 10px;font-size: 13px;font-weight: normal;">Website Address</td>
                                                        <td style="border-right:1px solid #ccc; border-bottom: 1px solid #ccc; padding: 10px;font-size: 13px;font-weight: normal;">ingo.com</td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
		
		<!-- ======================================== End ========================================================== -->

		<!------------ Main Div End ------------------->
</div>
</section>
	
	<script src="assets/js/angular.min.js"></script>	
	<script src="assets/js/bootstrap.js"></script>
	<script src="assets/js/jquery.scrollabletab.js"></script>
	<script src="assets/js/autocompletedata.js"></script>
	<script src="assets/js/jquery-ui.js"></script> 
	<script src="assets/js/chosen.jquery.js"></script>
	<script src="assets/js/bootstrap-datepicker.min.js"></script>
	<script src="assets/js/jquery.slimscroll.js"></script>
	<script src="assets/js/jquery.countTo.js"></script>
	<script src="assets/js/raphael.min.js"></script>
	<script src="assets/js/morris.js"></script>
	<script src="assets/js/angular-sanitize.min.js"></script>
	<script src="assets/js/cookieStore.js"></script>
	<script src="assets/js/alasql.min.js?version=1.0.0.2"></script>
	<script src="assets/js/xlsx.core.min.js?version=1.0.0.2"></script>
	<script src="assets/scripts/angular_Av2.min.js?version=1.0.5"></script>
	<script src="assets/js/admin.js"></script>
	<script src="assets/js/pages/index.js"></script>
	<script src="assets/js/demo.js"></script>
	<script src="assets/js/datepicker-date.js"></script>

	<script type="text/javascript">
		function showStatusModal() {
			//var
			//alert(oid);
		}
	</script>
	<script type="text/javascript">
		function showDiv(divId) {
			var div = "#" + divId;
			$('div[id^=div]').hide();
			$(div).slideToggle(600).show();
		}
		
		function sshowDiv(divId) {
			var div = "#" + divId;
			$('div[id^=sdiv]').hide();
			$(div).slideToggle(600).show();
		}
	</script>
	<script type="text/javascript">
		$(document).ready(function() {
			$(".theme").click(function() {
				$(".theme").removeClass("active");
				$(this).addClass("active");
				
			});
		});
	</script>

</body>

</html>