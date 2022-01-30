<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@page import="com.recharge.model.User"%>
<%@page import="org.apache.log4j.Logger"%>

<%	
	User user = (User) session.getAttribute("UserDetails");	
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
	<!-- Search Bar -->
	<div class="search-bar  col-xs-hidden">
		<div class="col-md-6" style="margin-top: 2%;">
			<div class="col-md-2">
				<div class="search-icon">
					<label>Search User:</label>
				</div>
			</div>
			<div class="col-md-8">
				<div class="form-group form-float">
					<div class="form-line input-group">
						<span class="input-group-addon" id="basic-addon1"><i class="fa fa-search"></i></span> 
						<input type="text" class="form-control" aria-describedby="basic-addon1" ng-model = "searchUser.mobile">
					</div>
				</div>
			</div>
			<div class="col-md-2">
				<button type="button" class="btn btn-info waves-effect close-search1" onClick="showDiv('div12')" 
				ng-click = "AdvancedSearchUser(searchUser)">Submit</button>
			</div>
		</div>
		<div class="col-md-6" style="margin-top: 2%;">
			<div class="col-md-2">
				<div class="search-icon">
					<label>Customer No:</label>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group form-float">
					<div class="form-line input-group">
						<span class="input-group-addon" id="basic-addon2"><i class="fa fa-search"></i></span> 
						<input type="text" class="form-control"	aria-describedby="basic-addon1" ng-model = "searchUser.customerNo">
					</div>
				</div>
			</div>
			<div class="col-md-2">
				<button type="button" class="btn btn-info waves-effect close-search1" 
				ng-click = "AdvancedCustomerNo(searchUser)" onClick="showDiv('div29')">Submit</button>
			</div>
		</div>
		<div class="close-search">
			<i class="material-icons">close</i>
		</div>
	</div>	
	 
	 <!-- Top Bar -->
	<div class="container-fluid navbar1 navbar" style="box-shadow: 0 2px 10px rgba(0, 0, 0, 0.2);">
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
					
					<!-- #User Info -->
					<div class="user-info navbar" style="margin-bottom: 0px;">
								
						<div class="info-container"	style="margin-left: 32px; margin-top: 7px;">
							<div class=" user-helper-dropdown">
								<div class="image" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
									<img src="assets/images/user.png" width="48" height="48" alt="User"  />
								</div>
								<ul class="dropdown-menu pull-right">
									<li><a href="javascript:void(0);">
										<div class="name" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">{{userDetails.name}}({{userDetails.firmName}})</div>
										<div class="email">{{userDetails.email}}</div></a>
									</li>
									<li><a href="javascript:void(0);" onClick="showDiv('div2')"><i class="material-icons">person</i>Profile</a></li>
									<li role="seperator" class="divider"></li>
									<li><a href="logout"><i class="material-icons">input</i>Sign Out</a></li>
								</ul>
							</div>
						</div>
					</div>
				</div>
				
			</div>
		</div>
	</nav>
	</div>
	
	<div class="col-md-1">
		<ul class="nav navbar-nav hidden-xs" >
					<!-- Call Search -->
					<li>
						<a href="javascript:void(0);" class="js-search" data-close="true" title="Advanced Search Option"><i	class="material-icons">search</i></a>
					</li>
					<!-- #END# Call Search -->
					<!-- Notifications -->
					<li class="dropdown">
						<a href="javascript:void(0);" class="dropdown-toggle" data-toggle="dropdown" role="button"> 
							<i class="material-icons">notifications</i> 
							<%if(user.getRoleId() == 1){ %>
							<span class="label-count">{{notification.complain + notification.balRequest + notification.utility + notification.insurance}}</span>
							<%} else { %>
							<span class="label-count">{{notification.complain + notification.balRequest}}</span>
							<%} %>
						</a>
						<ul class="dropdown-menu">
							<li class="header">NOTIFICATIONS</li>
							<li class="body">
								<ul class="menu">
									<li ng-if = "notification.complain > 0">
										<a href="#" onClick="showDiv('div33')" ng-click="viewPendingComplain(viewComplain);">
											<div class="icon-circle bg-light-green"><i class="material-icons">watch_later</i></div>
											<div class="menu-info"><h4>{{notification.complain}} Complains</h4></div>
										</a>
									</li>
									<li ng-if = "notification.balRequest > 0">
										<a href="javascript:void(0);" ng-click="viewPendingBalanceRequest(viewBalanceRequest);" onClick="showDiv('div26')">
											<div class="icon-circle bg-light-green"><i class="material-icons">watch_later</i></div>
											<div class="menu-info"><h4>{{notification.balRequest}} Balance Request</h4></div>
										</a>
									</li>
									<%if(user.getRoleId() == 1){ %>
									<li ng-if = "notification.utility > 0">
										<a href="javascript:void(0);" ng-click="viewPendingUtilityRequest(viewUtilityRequest);" onClick="showDiv('div32')">
											<div class="icon-circle bg-light-green"><i class="material-icons">watch_later</i></div>
											<div class="menu-info"><h4>{{notification.utility}} Utility Request</h4></div>
										</a>
									</li>
									<li ng-if = "notification.insurance > 0">
										<a href="javascript:void(0);" ng-click="viewPendingInsuranceRequest(viewInsuranceRequest);" onClick="showDiv('div36')">
											<div class="icon-circle bg-light-green"><i class="material-icons">watch_later</i></div>
											<div class="menu-info"><h4>{{notification.insurance}} Insurance Request</h4></div>
										</a>
									</li>
									<%} %>
								</ul>
							</li>
						</ul>
					</li>
				</ul>
	
	</div>
	</div>
	</div>
	<!-- #Top Bar -->
	<!-- #Top Bar -->
	<section> 
		<aside id="leftsidebar" class="sidebar">
		
		<div class="user-info navbar visiable-on-responsive">
				<div class="image"><img src="assets/images/user.png" width="48" height="48" alt="User" /></div>
				<div class="info-container" style="margin-left: 56px;margin-top: -59px;">
				<div class="name" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">{{userDetails.name}}({{userDetails.firmName}})</div>
				<div class="email">{{userDetails.email}}</div>
				<div class="btn-group user-helper-dropdown"><i class="material-icons" data-toggle="dropdown"aria-haspopup="true" aria-expanded="true">keyboard_arrow_down</i>
					<ul class="dropdown-menu pull-right">
						<li><a href="javascript:void(0);"><i class="material-icons">person</i>Profile</a></li>
						<li role="seperator" class="divider"></li>
						<li><a href="logout"><i class="material-icons">input</i>Sign Out</a></li>
					</ul>
				</div>
			</div>
		</div>	
		
		<div class="menu" style="background: #fff !important;">
			<ul class="list">
				<li class="header">MAIN NAVIGATION </li>
				<li ><a href="home"> <i class="material-icons">home</i><span>Home</span></a></li>
				<li><a  onClick="showDiv('div59')" > <i class="material-icons">widgets</i><span>Flight Markup Management</span></a></li>
			
			<li class="active"><a href="skyflightsearch" > <i class="material-icons">content_copy</i> <span>Flight</span></a></li>
			<li><a  onClick="showDiv('div1')" > <i class="material-icons">widgets</i><span>My Booking</span></a></li>
			</ul>
		</div>
		</aside> 
	</section>
	<section class="content">
		<div class="container-fluid">
		
		<div id="div1" class="row clearfix" style="display: none; margin: 0px;">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<div class="card">
					<div class="header">
						<h2>My Booking fyr</h2>
					</div>
					<div class="body">
						<form class="form-horizontal">
							<div class="row clearfix">
								<div class="col-md-4">
									<div
										class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
										<label for="email_address_2">Start Date</label>
									</div>
									<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
										<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
											<div class="form-group form-float"
												style="margin-left: 0px !important;">
												<div class="form-line">
													<input type="text" class="form-control" id="dp13" ng-model="bookingRequest.startDate" readonly="readonly" />
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="col-md-4">
									<div
										class="col-lg-6 col-md-6 col-sm-6 col-xs-6 form-control-label">
										<label for="email_address_2">End Date</label>
									</div>
									<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">

										<div class="form-group">
											<div class="form-group form-float" style="margin-left: 6%">
												<div class="form-line">
													<input type="text" class="form-control" id="dp14" ng-model="bookingRequest.endDate" readonly="readonly" />
												</div>
											</div>
										</div>

									</div>
								</div>
								<div class="col-md-4">
									<div class="col-lg-offset-2 col-md-offset-2 col-sm-offset-2 col-xs-offset-2">
									<button type="button" class="btn btn-primary m-t-15 waves-effect" ng-click = "viewBooikngReport(bookingRequest);">Submit</button>
								</div>
								</div>
								</div>
						</form>
							
						<div class="row clearfix" style="margin-top: 2%">
							<div class="col-md-12">
								<div class="panel panel-success" ng-if="bookingreport.length > 0">
									<div class="panel-heading">
											<h4><b>Ticket Queue Details</b></h4>
									  </div>
									  
									    <div class="panel-body" ng-repeat="ticket in bookingreport">
									    	<div class="fticketdetails">
									    		<div class="row">
									    			<div class="col-md-7">
									    				<div class="table-responsive">          
														  <table class="table table-bordered">
															    <thead>
																      <tr>
																        <th>Traveler Detail</th>
																        <th>Ticketed</th>
																      </tr>
															    </thead>
															    <tbody>
																      <tr ng-repeat="passenger in ticket.passengerlist">
																        <td>{{passenger.title}}. {{passenger.firstname}} {{passenger.lastname}}</td>
																        <td>Booking Status</td>
																      </tr>
																      
																       <tr>
																       <td><h4>Booked On :</h4></td>
																        <td>{{ticket.date}} {{ticket.time}}</td>
																      </tr>
															    </tbody>
														  </table>
													  </div>
									    			</div>
									    			<div class="col-md-5">
									    			 <div class="table-responsive">          
														  <table class="table table-bordered">
														    <thead>
														      <tr>
														        <th><b>PNR Number</b></th>
														        <th><b>Ref Number</b></th>
														      </tr>
														    </thead>
														    <tbody>
														      <tr>
														        <td>{{ticket.PNR}}</td>
														        <td>{{ticket.booking_id}}</td>
														      </tr>
														    </tbody>
														  </table>
														  </div>
									    			</div>							    			
									    		</div>
									    		
									    		<div class="row">
									    			<div class="col-md-12">
									    				<div class="table-responsive">          
														  <table class="table">
															    
															    <tbody>
																      <tr>
																        <!-- <td><button type="button" class="btn btn-info ">Fare Rule</button></td> -->
																        <td><button type="button" class="btn btn-info" ng-click="viewTicket(ticket)">view ticket</button></td>
																        <td><button type="button" class="btn btn-info" ng-click="cancelRequest(ticket)">Change Request</button></td>
																        <td></td>
																        <td></td>
																        <td></td>
																        <td></td>
																        <td></td>
																        <td><button type="button" class="btn btn-info " ng-click = "ViewTicket(ticket);"><a href="flightTicket" target="_blank">View Invoice</a></button></td>
																        <td><button type="button" class="btn btn-info ">Open</button></td>
																        
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
						
						
						
						
						
						
						
							<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 table-responsive"  ng-if="bookingreport.length > 0">
							
							<!-- <div class="panel panel-default m-b-0  m-l-10 m-r-10 m-t-20 m-b-20" ng-repeat="ticket in bookingreport">
										<div class="card m-b-0">
											<div class="row border-bottom m-l-10 m-r-10">
												<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 p-t-10 p-b-0 m-b-0">
													<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 p-t-0 p-b-0 m-b-0 m-t-0 border-bottom">
														<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6 p-t-0 p-b-0 m-b-0 m-t-0">
															<h4>Traveler Detail</h4>
														</div>
														<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6 p-t-0 p-b-0 m-b-0 m-t-0"> 
															<h4>Ticketed</h4>
														</div>
													</div>
													<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 m-b-0 p-t-10 p-b-10 border-bottom">
														<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6 p-t-0 p-b-0 m-b-0 m-t-0">
															<h5 ng-repeat="passenger in ticket.passengerlist">{{passenger.title}} {{passenger.firstname}} {{passenger.lastname}}</h5>
															
														</div>
														<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6 p-t-0 p-b-0 m-b-0 m-t-0"> 
															<h5>Booking Status</h5>
														</div>
													</div>
													<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 p-t-0 p-b-0 m-b-0 m-t-0 bg-lime ">
														<span class="lead font-20">Booked On :</span> <span class="lead font-15 p-l-20">{{ticket.date}}  {{ticket.time}}</span>
													</div>
												</div>
												<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 p-t-10 p-b-10 p-l-0 p-r-10 m-b-0">
													<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 p-t-10 p-b-10 m-b-0">
														<div class="panel panel-default panel-table m-b-0">
															<div class="card m-b-0 p-t-10 p-b-10 p-l-10 p-r-10 bg-lime">
																<div class="header text-center p-t-0 p-b-0 m-b-0 m-t-0" style="padding: 0px !important;">
																	<h4 class="m-t-0 m-b-0" style="font-weight: 100;">PNR Details</h4>
																</div>
																<div class="row m-l-0 m-r-0">
																	<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6 p-t-10 p-b-10 m-b-0 text-right">
																		<h5 class="m-b-10">PNR Number:</h5>
																		<h5 class="m-b-10">Ref Number:</h5>
																	</div>
																	<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6 p-t-10 p-b-10 m-b-0 text-left">
																		<h5 class="m-l-10">{{ticket.airlinepnr}}</h5>
																		<h5 class="m-l-10">{{ticket.booking_id}}</h5>
																	</div>
																</div>
															</div>
														</div>
													</div>
												</div>
											</div>
											<div class="row m-l-0 m-r-0">
												<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 m-l-0 m-r-0 m-t-0 m-b-0 p-t-0 p-b-0">
													<div class="col-lg-7 col-md-7 col-sm-7 col-xs-7 m-l-0 m-r-0 m-t-0 m-b-0 p-t-20 p-b-0 text-left">
														<button type="button" class="btn btn-info ">Fare Rule</button>
														<button type="button" class="btn btn-info" ng-click="viewTicket(ticket)">view ticket</button>
														<button type="button" class="btn btn-info" ng-click="cancelRequest(ticket)">Change Request</button>
													</div>
													<div class="col-lg-5 col-md-5 col-sm-5 col-xs-5 m-l-0 m-r-0 m-t-0 m-b-0 p-t-20 p-b-10 p-l-20">
														<span class="pull-right">
														<button type="button" class="btn btn-info " ng-click = "ViewTicket(ticket);"><a href="flightTicket" target="_blank">View Invoice</a></button>
														<button type="button" class="btn btn-info ">Open</button>
														</span>
													</div>
												</div>
											</div>
											</div>  
											</div> -->
							
								<!-- <table id="mainTable" class="table table-striped">
									<thead>
										<tr style="font-size: 13px; font-weight: bold;">
											<th class="success text-center">Sl. No</th>
											<th class="success text-center">USER</th>
											<th class="success text-center">Source</th>
											<th class="success text-center">Destination</th>
											<th class="success text-center">Travel Date</th>
											<th class="success text-center">PNR</th>
											<th class="success text-center">TrackId</th>
											<th class="success text-center">Booking Id</th>
											<th class="success text-center">STATUS</th>	
											<th class="success text-center">Date &amp; Time</th>	
											<th class="success text-center">View Ticket</th>
											
										</tr>
									</thead>
									<tbody>
										<tr ng-repeat="report in bookingreport ">
											<td style="font-size: 15px; text-align: center;">{{$index + 1}}</td>
											<td style="font-size: 15px; text-align: center;">{{report.username}}</td>
											<td style="font-size: 15px; text-align: center;">{{report.source}}</td>
											<td style="font-size: 15px; text-align: center;">{{report.destination}}</td>
											<td style="font-size: 15px; text-align: center;">{{report.travel_date}}</td>
											<td style="font-size: 15px; text-align: center;">{{report.PNR}}	</td>
											<td style="font-size: 15px; text-align: center;">{{report.traceid}}</td>
											<td style="font-size: 15px; text-align: center;">{{report.booking_id}}</td>
											<td style="font-size: 15px; text-align: center;">{{report.booking_status}}</td>
											<td style="font-size: 15px; text-align: center;">{{report.date}} {{report.time}}</td>
											
											<td style="font-size: 15px; text-align: center;">
													<button class="edit-button" ng-click = "ViewTicket(report);"><a href="flightTicket" target="_blank">View</a></button>
											</td>
											
										</tr>
									</tbody>
								</table> -->
							</div>
							
							<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 table-responsive" ng-if="bookingreport.length <= 0">
								<table cellspacing="1" class="table table-striped table-bordered table-hover">
									<tbody>
										<tr>
											<th align="center" style="color: red;">No Records Found</th>
										</tr>
									</tbody>
								</table>
							</div>
							
						</div>
					</div>
				</div>
			</div>
		</div>
		
		<!-------------- Div41 ---------------------->
		<div id="div41" class="row clearfix"  style="display: none;">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<div class="card">
					<div class="header">
						<h2>Change Request for PNR :{{bookingcancel.PNR}} <span class="pull-right"><a href="#"  class="body" onclick="showDiv('div1')"><i class="material-icons">arrow_back</i></a></span></h2>
					</div>
					<div class="body">
						<form class="form-horizontal">
							<div class="row clearfix">
								<div class="col-lg-5 col-md-5 col-sm-4 col-xs-4 form-control-label">
									<label for="email_address_2">Select Request Type</label>
								</div>
								<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
									<div class="form-group">
										<div class="form-line">
											<select class="form-control " style="align-items: center;" name="requestType" id="canceltype">
												<option value="Select">-Select-</option>
												<option value="FULL">FullCancellation</option>
												<option value="PARTIAL" ng-if="bookingcancel.passengerlist.length>1">PartialCancellation</option>
											</select>
										</div>
									</div>
								</div>
							</div>
						
							
							<div class="row clearfix">
								<div
									class="col-lg-5 col-md-5 col-sm-4 col-xs-4 form-control-label">
									<label for="email_address_2">Passenger Name*</label>
								</div>
								<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
									<div class="form-group">
											<h4 ng-repeat="passenger in bookingcancel.passengerlist"><input type="checkbox"
														id="passengers{{$index}}" name="passengers{{$index}}"
														data-ng-model="pasengrs.loc[$index]"
														ng-true-value="'{{passenger.ticketnumber}}'" ng-false-value="" /> {{passenger.title}} {{passenger.firstname}} {{passenger.lastname}}</h4>
										
									</div>
								</div>
							</div>
							
							<div class="row clearfix">
								<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 "></div>
								<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
									<div class="col-md-offset-3">
										<input type="submit" class="btn btn-primary waves-effect" value="Cancel" ng-click="cancelTicket(bookingcancel,pasengrs)">
									</div>
								</div>
							</div>
						</form>
						
					</div>

				</div>
			</div>
		</div>
		<!-------------- /Div41 ---------------------->
		
		<!-------------- div59 ---------------------->	
				<div id="div59" class="row clearfix" style="display: none">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 ">
						<div class="card">
							<div class="header">
								<h2>Edit Markup <span class="pull-right"><a href="flight" class="body"><i class="material-icons">arrow_back</i></a></span></h2>
							</div>
							<div class="body" >			
								<div class="row">
									<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 ">
										<div class="col-md-12 ">
                                          <div class="col-md-3 col-sm-6 col-xs-12 " style="text-align: right;">
                                              <label>All Domestic Airlines:*</label>
                                          </div>
                                          <div class="col-md-3 col-sm-6 col-xs-12 ">
                                              <select class="form-control" ng-model="domestic.markup_type">
                                                  <option value="PERCENTAGE">% of total fare</option>
                                                  <option value="RUPEE">Flat fee (Rs.)</option>
                                              </select>
                                          </div>
                                          <input type="hidden" ng-model="domestic.service_type" ng-init="domestic.service_type='Domestic Flight'">
                                          <div class="col-md-3 col-sm-6 col-xs-12 ">
                                              <input type="text" class="form-control" ng-model="domestic.markup_value">
                                          </div>
                                          <div class="col-md-3 col-sm-6 col-xs-12 ">
                                              <input type="submit" value="submit" ng-click="SaveMarkUpData(domestic)" class="btn btn-primary btn-md">
                                          </div>
                                      </div>
									</div>
								</div>
                                <div class="row">
                                   <div class="col-md-12 ">
                                        <div class="col-md-3 col-sm-6 col-xs-12 " style="text-align: right;">
                                            <label>Individual Markup Setup:*</label>
                                        </div>
	                                     <div class="col-md-6 col-sm-6 col-xs-12 ">
	                                         <a href="#" id="" class="btn btn-primary btn-md" ng-click="showAllDomesticMarkup()">Submit</a>
										</div>
                                    </div>
                                </div>
                               
                               <div class="row clearfix" id="sdiv5">
	                               <div class="row ">
	                                 <div class="col-md-12 ">
	                                     <div class="col-md-3 col-sm-6 col-xs-12 " style="text-align: right;">
	                                         <label>International Flight:*</label>
	                                     </div>
	                                     <div class="col-md-3 col-sm-6 col-xs-12 ">
	                                         <select class="form-control"  ng-model="International.markup_type">
	                                             <option value="PERCENTAGE">% of total fare</option>
	                                             <option value="RUPEE">Flat fee (Rs.)</option>
	                                         </select>
	
	                                     </div>
	                                     <input type="hidden" ng-model="International.service_type" ng-init="International.service_type='International Flight'">
	                                     <div class="col-md-3 col-sm-6 col-xs-12 ">
	                                         <input type="text" class="form-control" ng-model="International.markup_value">
	
	                                     </div>
	                                     <div class="col-md-3 col-sm-6 col-xs-12 ">
	                                         <input type="submit" value="submit" ng-click="SaveMarkUpData(International)"  class="btn btn-primary btn-md">
	                                     </div>
	                                 </div>
	                             </div>
	                             
	                         <!--     <div class="row ">
	                                <div class="col-md-12 ">
	                                    <div class="col-md-3 col-sm-6 col-xs-12 " style="text-align: right;">
	                                        <label>Select Service Type:*</label>
	                                    </div>
	                                    <div class="col-md-2 col-sm-6 col-xs-12 ">
	                                        <select class="form-control" ng-model="othermarkup.service">
	                                            <option value="Hotel">Hotel</option>
	                                            <option value="Holiday">Holiday</option>
	                                            <option value="Bus">Bus</option>
	                                            <option value="Car">Car</option>
	                                        </select>
	
	                                    </div>
	                                    <div class="col-md-2 col-sm-6 col-xs-12 ">
	                                        <input type="text" class="form-control"  ng-model="othermarkup.markup_value">
	
	                                    </div>
	                                    <div class="col-md-2 col-sm-6 col-xs-12 ">
	                                        <select class="form-control"  ng-model="othermarkup.markup_type">
	                                            <option value="PERCENTAGE">% of total fare</option>
	                                            <option value="RUPEE">Flat fee (Rs.)</option>
	                                        </select>
	
	                                    </div>
	                                    <div class="col-md-3 col-sm-6 col-xs-12 ">
	                                        <input id="" class="btn btn-primary btn-md" type="submit" value="submit" ng-click="othermarkupset(othermarkup)">
	                                    </div>
	                                </div>
	                            </div> -->
                            </div>
                            
                            
                            <div class="row clearfix visibility" id="sdiv601">
                            	
                            	<div class="row" ng-repeat="DomesticMarkup in DomesticMarkuplist">
                           			<div class="col-md-12">
                           				<div class="col-md-3 col-sm-6 col-xs-12" style="text-align:right;">
                           					<label>{{DomesticMarkup.airline_name}}:*</label>
                           				</div>
                           				<div class="col-md-3 col-sm-6 col-xs-12">  
                           					<select class="form-control" ng-model="DomesticMarkup.markup_type"> 
                           					    <option value="{{DomesticMarkup.markup_type}}">{{DomesticMarkup.markup_type}}</option>
                           						<option value="PERCENTAGE">% of total fare</option>
                           						<option value="RUPEE">Flat fee (Rs.)</option>
                           					</select>
                           				</div>
                           				<div class="col-md-3 col-sm-6 col-xs-12">
                           					<input type="hidden" ng-model="DomesticMarkup.airline_code" value="{{DomesticMarkup.airline_code}}">
                           					<input type="hidden" ng-model="DomesticMarkup.id" value="{{DomesticMarkup.id}}">
                           					<input type="text" class="form-control"  ng-model="DomesticMarkup.markup_value" value="{{DomesticMarkup.markup_value}}">
                           				</div>
                           				<div class="col-md-3 col-sm-6 col-xs-12">
                           					<input class="btn btn-primary btn-md" type="submit" value="submit" ng-click="savesingleData(DomesticMarkup)">
                           				</div>
                           			</div>
                            	</div>
                            	
                            </div>
									
							</div>
						</div>
					</div>
				</div>
		<!-------------- /div59 ---------------------->		
		
		
		<div id = "div21">
		<h1 style="font-size: 120px">dfgtdfghdhfdjgh</h1>
		</div>
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