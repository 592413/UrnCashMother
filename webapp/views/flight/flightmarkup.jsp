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

.fbtn{
display: inline-block;
    padding: 10px 16px;
    margin-bottom: 0;
    font-size: 32px;
    font-weight: 800;
    line-height: 1.42857143;
    text-align: center;
    white-space: nowrap;
    vertical-align: middle;
    box-shadow: 10px 7px 5px #8a0a008f;
    -ms-touch-action: manipulation;
    touch-action: manipulation;
    cursor: pointer;
    -webkit-user-select: none;
    -moz-user-select: none;
    -ms-user-select: none;
    user-select: none;
    background-image: none;
    border: 1px solid transparent;
    border-radius: 0px;

}

.fbtn-danger{
    background-color: #c30e00 !important;
    color: #f2f2f2;
}

.fbtn-danger:hover{
    box-shadow: 10px 10px 10px #8a0a008f;
}
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
<body class="{{reseller.theme}}" ng-app = "app" ng-controller = "appController" style="background-color: #f6f6f6">
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
				<li class="header">MAIN NAVIGATION</li>
				<li class="active"><a href="home"> <i class="material-icons">home</i><span>Home</span></a></li>
				<li><a  onClick="showDiv('div59')" > <i class="material-icons">widgets</i><span>Flight Markup Management</span></a></li>
			
			<li><a href="flightsearch"> <i class="material-icons">content_copy</i> <span>Flight</span></a></li>
			<li><a  onClick="showDiv('div2')" > <i class="material-icons">widgets</i><span>Flight GST Details</span></a></li>
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
						<h2>My Booking</h2>
					</div>
					<div class="body">
						<form class="form-horizontal">
							<div class="row clearfix">
								<div class="col-md-4">
									<label for="email_address_2">Start Date</label>
									<input type="text" class="form-control" id="dp13" ng-model="bookingRequest.startDate" readonly="readonly" />
								</div>
								<div class="col-md-4">
									<label for="email_address_2">End Date</label>
									<input type="text" class="form-control" id="dp14" ng-model="bookingRequest.endDate" readonly="readonly" />
								</div>
								<div class="col-md-4">
									<label for="email_address_2">Select</label>
									<select class="form-control" ng-model="bookingRequest.status" ng-init="bookingRequest.status='ALL'">
										<option value="ALL">ALL</option>
										<option value="Ticketed">Ticketed</option>
										<option value="canceled">canceled</option>
									</select>
								</div>
								
								</div>
								<div class="row clearfix">
									<div class="col-md-4">
										<div class="col-lg-offset-2 col-md-offset-2 col-sm-offset-2 col-xs-offset-2">
										<button type="button" class="btn btn-primary m-t-15 waves-effect" ng-click = "viewBooikngReport(bookingRequest);">Submit</button>
										<button type="button" class="btn btn-primary m-t-15 waves-effect" ng-click = "getBooikngReportExcel(bookingreport);">Export</button>
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
									    	<div class="fticketdetails ticket_new">
									    		<div class="row">
									    			<div class="col-md-7">
									    				<div class="table-responsive">          
														  <table class="table table-bordered">
															    <thead>
																      <tr>
																        <th>Traveler Detail</th>
																        <th>Booking Status</th>
																      </tr>
															    </thead>
															    <tbody>
																      <tr ng-repeat="passenger in ticket.passengerlist">
																        <td>{{passenger.title}}. {{passenger.firstname}} {{passenger.lastname}}</td>
																        <td>{{ticket.booking_status}}</td>
																      </tr>
																      
																       <tr>
																       <td><h4>Booked On :</h4></td>
																        <td>{{ticket.date}} {{ticket.time}}</td>
																      </tr>
																       <tr>
																       <td>Opening-Closing :</td>
																        <td>{{ticket.openbl}} - {{ticket.closebl}}</td>
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
														      <th><b>User Details</b></th>
														        <th><b>PNR Number</b></th>
														        <th><b>Ref Number</b></th>
														      </tr>
														    </thead>
														    <tbody>
														      <tr>
														      <th><b>{{ticket.uname}}({{ticket.usermobile}})</b></th>
														        <td>{{ticket.airlinepnr}}</td>
														        <td>{{ticket.booking_id}}</td>
														      </tr>
														    </tbody>
														  </table>
														  </div>
									    			</div>							    			
									    		</div>
									    		
									    		<div class="row new_org">
									    			<div class="col-md-2">
									    				<button type="button" class="btn btn-info" ng-click="ViewTickets(ticket);">view ticket</button>
									    			</div>
									    			<div class="col-md-2">
									    				<button type="button" class="btn btn-info" ng-click="cancelRequest(ticket)">Change Request</button>
									    			</div>
									    			<div class="col-md-2">
									    				<button type="button" class="btn btn-info " ng-click = "ViewInvoice(ticket);">View Invoice</button>
									    			</div>
									    			<div class="col-md-2">
														<button type="button" class="btn btn-info ">Open</button>		    				
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
		<!-- ---------------------------------- -->
		<!-- ------------div2----------------------- -->
		<div id="div2" class="row clearfix" style="display: none; margin: 0px;">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<div class="card">
					<div class="header">
						<h2>Flight GST Details</h2>
					</div>
					<div class="body">
						<div class="body">
							<form class="form-horizontal">
								<div class="row clearfix">
									<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label text-right">
										<label for="email_address_2">Company Name</label>
									</div>
									<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
										<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">									
											<input type="text" ng-model="gstdt.companyname" class="form-control" placeholder="Enter Company Name" />
										</div>
									</div>
								</div>	
								<div class="row clearfix">
									<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label text-right">
										<label for="email_address_2">Company Address</label>
									</div>
									<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
										<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 m-l-20" style="margin-left: 4%;">
											<div class="form-group">
												<div class="form-group form-float">
													<div class="form-line">
														<input type="text" ng-model="gstdt.companyaddress" class="form-control" placeholder="Enter Company Address" />
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="row clearfix">
									<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label text-right">
										<label for="email_address_2">Gst NO</label>
									</div>
									<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
										<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 m-l-20" style="margin-left: 4%;">
											<div class="form-group">
												<div class="form-group form-float">
													<div class="form-line">
														<input type="text" ng-model="gstdt.gstno" class="form-control" placeholder="Enter Gst NO" />
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="row clearfix">
									<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label text-right">
										<label for="email_address_2">Pan NO</label>
									</div>
									<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
										<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 m-l-20" style="margin-left: 4%;">
											<div class="form-group">
												<div class="form-group form-float">
													<div class="form-line">
														<input type="text" ng-model="gstdt.panno" class="form-control" placeholder="Enter PAn NO" maxlength="10"/>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="row clearfix">
									<div class="col-lg-offset-6 col-md-offset-6 col-sm-offset-6 col-xs-offset-6">
										<button type="button" class="btn btn-primary m-t-15 waves-effect" ng-click = "addgstdt(gstdt);">Submit</button>
									</div>
								</div>	
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
		
		<!-- ----------------------------------- -->
		<!-------------- Div41 ---------------------->
		<div id="div41" class="row clearfix"  style="display: none;">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<div class="card">
					<div class="header">
						<h2>Change Request for PNR : <i>{{bookingcancel.airlinepnr}}</i> <span class="pull-right"><a href="#"  class="body" onclick="showDiv('div1')"><i class="material-icons">arrow_back</i></a></span></h2>
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
														ng-true-value="'{{passenger.ticketid}}'" ng-false-value="" /> {{passenger.title}} {{passenger.firstname}} {{passenger.lastname}}</h4>
										
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
						<h2>Edit Markup<span class="pull-right"><a href="flight" class="body"><i class="material-icons">arrow_back</i></a></span></h2>
					</div>
					<div class="body" >			
						<div class="row">
							<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 ">
								<div class="col-md-12 ">
                                        <div class="col-md-3 col-sm-6 col-xs-12 " style="text-align: right;">
                                            <label>All Domestic Airlines:*</label>
                                        </div>
                                        <div class="col-md-3 col-sm-6 col-xs-12 ">
                                            <select class="form-control" ng-model="domestic.markup_type" ng-init="domestic.markup_type='PERCENTAGE'">
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
                         					<select class="form-control show-tick" ng-model="DomesticMarkup.markup_type"> 
                         					    <!-- <option value="{{DomesticMarkup.markup_type}}">{{DomesticMarkup.markup_type}}</option> -->
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
		
		<div class="rows content" style="display: none;" id="div1011">
			<div class="col-md-9"></div>
			<div class="col-md-2">
                <div class="section-title aos-init aos-animate" data-aos="fade-up">
                  	<a href="javascript:void(0);" class="btn btn-primary" onclick="showDiv('div1');">
              			<i class="fa fa-arrow-left"></i> Back to My Booking
              		</a>
                </div>
            </div>
            <div class="col-md-1">
                <div class="section-title aos-init aos-animate" data-aos="fade-up">
                	<!-- <button onclick="getPDF()" id="download" class="n_prnt btn btn-primary">Download</button> -->
                  <button type="button" class="btn btn-info" ng-click="printInvoice('myInvoice');"><i class="fa fa-print"></i> Print</button>
                </div>
            </div>
           
            
            <div class="col-md-8gg" id="con" style="width: 66%;float: left;">
            	<div class="rowres" style="margin-top: 5%; float: left;" id="myInvoice">
              <form>
          		<div class="rowdd" style="margin-right: -15px;margin-left: -15px;float: left;margin-bottom: 20px;">
                      <div class="col-md-6dd" style="width: 50%;padding: 0 15px;float: left;">
                          <img class="logo img-responsive" id="logo2" src = "">
                          <strong>Below Agent Name : <span> {{flightuser.name}}</span></strong><br>
                          <strong>Address : <span> {{flightuser.address}}</span></strong>
                           <strong>Email : <span> {{flightuser.email}}</span></strong>
                      </div>
                  </div>
                  <div class="rowdd" style="margin-right: -15px;margin-left: -15px;">
                      <div class="col-md-6dd" style="width: 50%;padding: 0 15px;float: left;">
                          <table class="table table-responsive">
                              <thead class="darkd" style="background-color: #007bff;color: #fff;text-transform: capitalize;font-size: 14px;">
                                <tr>
                                  <th scope="col">Booking Date :</th>
                                  <th scope="col">{{requestticket.date| date : "MMMM d, y"}}</th>
                                </tr>
                              </thead>
                          </table>
                      </div>
                      <div class="col-md-6hgh" style="width: 50%;padding: 0 15px;float: left;">
                          <table class="table table-responsive">
                              <thead class="darkgr" style="float: right;background-color: #007bff;color: #fff;text-transform: capitalize;font-size: 14px;">
                                <tr>
                                  <th scope="col">Booking Reference :</th>
                                  <th scope="col">{{requestticket.booking_id}}</th>
                                </tr>
                              </thead>
                          </table>
                      </div>
                  </div>

                  <div class="rowssd" style="margin-top: 20px;margin-right: -15px;margin-left: -15px;">
                      <div class="col-md-12gffg" style="width: 100%;padding: 0 15px;">
                          <div class="panele panel-primarydd pan_custss" style="margin-bottom: 20px;padding: 0px 0px 10px 0px;border: 1px solid #007bff;float: left;width: 100%;">
                              <div class="panel-headindsg" style="padding: 10px 15px;background-color: #007bff;"> 
                                  <h3 class="panel-titlde darksd" style="padding: 5px 10px;font-size: 20px;margin: auto;color: #fff;">Reservation Lookup {{requestticket.sourcescity}} TO {{requestticket.destinationcity}}  </h3> 
                              </div> 
                              <div class="panel-dbody" style="padding: 15px;">
                                  <div class="rowss" style="margin-right: -15px;margin-left: -15px;">
                                      <div class="col-md-4ss" style="width: 33%;padding: 0px 15px; float: left;">
                                          <div class="air_boxee" style="padding: 10px;text-align: center;float: left;width: 100%;">
                                              <div class="rowhf" style="margin-right: -15px;margin-left: -15px;">
                                                  <div class="col-md-4sd" style="width: 33%;padding: 0px 15px; float: left;">
                                                      <img src="assets/skyflight/images/flightIcon/{{requestticket.airlinecd}}.png" style="max-width: 100%;">
                                                  </div>
                                                  <div class="col-md-8fed" style="width: 66%;float: left;">
                                                      <h5>{{requestticket.airlinename}}</h5>
                                                      <!-- <p>BookingId-{{requestticket.booking_id}}</p> -->
                                                  </div>
                                              </div>
                                          </div>
                                      </div>
                                      <div class="col-md-4sdd" style="width: 33%;padding: 0px 15px; float: left;">
                                          <div class="air_boxsds" style="padding: 10px;text-align: center;float: left;width: 100%;">
                                              <div class="rowfedf" style="margin-right: -15px;margin-left: -15px;">
                                                  <div class="col-md-12ss" style="width: 100%;float: left;padding: 0px 15px;">
                                                      <strong>{{requestticket.airlinecd}} - Confirmed</strong>
                                                  </div>
                                              </div>
                                          </div>
                                      </div>
                                      <div class="col-mdv4s" style="width: 33%;padding: 0px 15px; float: left;">
                                          <div class="air_boxss" style=";padding: 10px;text-align: center;float: left;width: 100%;">
                                              <div class="rowdfs" style="margin-right: -15px;margin-left: -15px;">
                                                  <div class="col-md-12vs" style="width: 100%;float: left;padding: 0px 15px;">
                                                      <h5>AIRLINE PNR</h5>
                                                      <strong>{{requestticket.airlinepnr}}</strong>
                                                      <p>PNR : <strong>{{requestticket.PNR}}</strong></p>
                                                  </div>
                                              </div>
                                          </div>
                                      </div>
                                  </div>
                              </div>
                          </div>
                      </div>
                  </div>

                   <div class="rowss" style="margin-top: 20px;margin-right: -15px;margin-left: -15px;" ng-repeat="ftdt in requestticket.flighdetail">
                      <div class="col-md-12ss" style="width: 100%;padding: 0 15px;float: left;">
                          <div class="panele panel-primarydd pan_custss" style="margin-bottom: 20px;padding: 0px 0px 10px 0px;border: 1px solid #007bff;float: left;width: 100%;">
                              <div class="panel-headindsg" style="padding: 10px 15px;background-color: #007bff;"> 
                                  <h3 class="panel-titles darkds" style="padding: 5px 10px;font-size: 20px;margin: auto;color: #fff;">Onward Flight Details ({{ftdt.sourceterminal}} to {{ftdt.destinationterminal}})</h3> 
                              </div> 
                              <div class="panel-bodydsd" style="padding: 15px;">
                                  <div class="rosw" style="margin-right: -15px;margin-left: -15px;">
                                      <div class="col-md-3ddd" style="width: 25%;float: left;padding: 0px 15px;">
                                          <div class="air_boxss" style="padding: 10px;text-align: center;float: left;width: 100%;">
                                              <div class="rowsds">
                                                  <div class="col-md-4ss" style="width: 33%;float: left;">
                                                      <img src="assets/skyflight/images/flightIcon/{{ftdt.flightid}}.png" style="max-width: 100%;">
                                                  </div>
                                                  <div class="col-md-7ss" style="width: 58%;float: left;">
                                                      <h5>({{ftdt.flightid}} {{ftdt.flight_number}})</h5>
                                                      <!-- <p>Cabin: ECO</p> -->
                                                  </div>
                                              </div>
                                          </div>
                                      </div>
                                      <div class="col-md-3dd" style="width: 25%;float: left;padding: 0px 15px;">
                                          <div class="air_boxss" style="padding: 10px;text-align: center;float: left;width: 100%;">
                                              <div class="rowdd">
                                                  <div class="col-md-12ss" style="width: 100%;float: left;">
                                                      <strong><h4 style="font-size: 13px;background-color: #00a986;color: #ffff;text-transform: uppercase;margin: auto;">Departure</h4></strong>
                                                      <strong>{{ftdt.origin}} ({{ftdt.sourceterminal}})</strong>
                                                      <p><!-- Tue, 04th Feb 2020<br> --> <span>{{ftdt.depttime}}</span></p>
                                                  </div>
                                              </div>
                                          </div>
                                      </div>
                                      <div class="col-md-2dd" style="width: 16%;float: left;padding: 0px 15px;">
                                          <div class="air_boddx" style="padding: 10px;text-align: center;float: left;width: 100%;">
                                              <div class="rowdd">
                                                  <div class="col-md-12ss" style="width: 100%;float: left;">
                                                      <strong><h4 style="font-size: 13px;background-color: #00a986;color: #ffff;text-transform: uppercase;margin: auto;">Arrival </h4></strong>
                                                      <strong>{{ftdt.destination}} ({{ftdt.destinationterminal}})</strong>
                                                      <p><!-- Tue, 04th Feb 2020<br>  --><span>{{ftdt.arrtime}}</span></p>
                                                  </div>
                                              </div>
                                          </div>
                                      </div>
                                      <div class="col-md-2dd" style="width: 16%;float: left;padding: 0px 15px;">
                                          <div class="air_boxss" style="padding: 10px;text-align: center;float: left;width: 100%;">
                                              <div class="rowdd">
                                                  <div class="col-md-12dd" style="width: 100%;float: left;">
                                                      <strong><h4 style="font-size: 13px;background-color: #00a986;color: #ffff;text-transform: uppercase;margin: auto;">AIRLINE PNR</h4></strong>
                                                      <strong>{{ftdt.PNR}}</strong>
                                                  </div>
                                              </div>
                                          </div>
                                      </div>
                                      <div class="col-md-2ss" style="width: 16%;float: left;padding: 0px 15px;">
                                          <div class="air_boxss" style="padding:10px;text-align: center;float: left;width: 100%;">
                                              <div class="rowss" style="">
                                                  <div class="col-md-12ss" style="float: left;">
                                                      <strong><h4 style="font-size: 13px;background-color: #00a986;color: #ffff;text-transform: uppercase;margin: auto;">Duration</h4></strong>
                                                      <strong>{{ftdt.duration}}</strong>
                                                  </div>
                                              </div>
                                          </div>
                                      </div>
                                  </div>
                              </div>
                          </div>
                      </div>
                   </div>

                    <div class="rowdd" style="margin-top: 20px;">
                      <div class="col-md-12sss" style="width: 100%;float: left;">
                          <div class="panele panel-primarydd pan_custss" style="margin-bottom: 20px;padding: 0px 0px 10px 0px;border: 1px solid #007bff;float: left;width: 100%;">
                              <div class="panel-headindsg" style="padding: 10px 15px;background-color: #007bff;"> 
                                  <h3 class="panel-titless darkss" style="padding: 5px 10px;font-size: 20px;margin: auto;color: #fff;">Passenger(s) Details</h3> 
                              </div> 
                              <div class="panel-bodyss" style="padding: 15px;">
                                  <div class="rowxsd" style="margin-right: -15px;margin-left: -15px;">
                                      <div class="col-md-7dff" style="width: 58%;float: left;padding: 0px 15px;">
                                          <table class="table table-responsive">
                                              <thead class="darkdd" style="background-color: #007bff;color: #fff;text-transform: capitalize;font-size: 14px;"> 
                                                <tr>
                                                  <th scope="col">Sr No.</th>
                                                  <th scope="col">Passenger(s) Name</th>
                                                  <th scope="col">Type</th>
                                                  <th scope="col">FF No.</th>
                                                  <th scope="col">Gender</th>
                                                  <th scope="col">Ticket No.</th>
                                                </tr>
                                              </thead>
                                              <tbody>
                                                <tr ng-repeat="pasan in requestticket.passengerlist">
                                                  <th scope="row">1</th>
                                                  <td>{{pasan.firstname}} {{pasan.lastname}}</td>
                                                  <td>{{pasan.category}}</td>
                                                  <td>-</td>
                                                  <td>{{pasan.title}}</td>
                                                  <td>{{pasan.ticketid}}</td>
                                                </tr>
                                              </tbody>
                                          </table>
                                      </div>
                                      <div class="col-md-5tgdr" style="width: 41%;float: left;padding: 0px 15px;">
                                          <table class="table table-responsive">
                                              <thead class="darkss" style="background-color: #007bff;color: #fff;text-transform: capitalize;font-size: 14px;"> 
                                                <tr>
                                                  <th colspan="100" scope="col">Payment Details</th>
                                                  <th scope="col">Amount ( INR )</th>
                                                </tr>
                                              </thead>
                                              <tbody>
                                                <tr>
                                                  <td colspan="100">Air Fare</td>
                                                  <td>{{listFlightFare.totalamount|number:2}}</td>
                                                </tr>
                                                
                                                <tr>
                                                  <td colspan="100">Fees & Surcharge (+)</td>
                                                  <td>{{listFlightFare.fees+listFlightFare.usermarkup+listFlightFare.adminmarkup|number:2}}</td>
                                                </tr>
                                                <tr ng-if="edittran==='1'">
                                                  <td colspan="100">Fees (+)</td>
                                                  <td><input type="text" ng-model="fees"><button type="button" class="btn btn-info" ng-click="donetran(fees);"><i class="fa fa-edit"></i>GO</button></td>
                                                </tr>
                                                <tr ng-if="editdis==='1'">
                                                  <td colspan="100">Discount (-)</td>
                                                  <td><input type="text" ng-model="disfees"><button type="button" class="btn btn-info" ng-click="donedisfees(disfees);"><i class="fa fa-edit"></i>GO</button></td>
                                                </tr>
                                                <tr ng-if="listFlightFare.discount!==0||editdis==='2'">
                                                  <td colspan="100">Discount (-)</td>
                                                  <td>{{listFlightFare.discount}}</td>
                                                </tr>
                                                <tr>
                                                  <td colspan="100">Total Amount</td>
                                                  <td>{{listFlightFare.totalamount-listFlightFare.discount+listFlightFare.fees+listFlightFare.usermarkup+listFlightFare.adminmarkup|number:2}}</td>
                                                </tr>
                                                <!-- <tr>
                                                  <td colspan="100">Air Fare</td>
                                                  <td>6,000.00</td>
                                                </tr> -->
                                              </tbody>
                                          </table>
                                      </div>
                                  </div>
                              </div>
                          </div>
                      </div>
                   </div>

                   <div class="rowed" style="margin-top: 20px;">
                      <div class="col-md-6sdd" style="width: 50%;float: left;">
                          <table class="table table-responsive">
                              <thead class="darksss" style="background-color: #007bff;color: #fff;text-transform: capitalize;font-size: 14px;">
                                <tr>
                                  <th scope="col">Customer Contact Details :</th>
                                  <th scope="col">{{requestticket.passengerlist[0].contact}}</th>
                                </tr>
                              </thead>
                          </table>
                      </div>
                      <div class="col-md-6sdd" style="width: 50%;float: left;">
                          <table class="table table-responsive">
                              <thead class="darkss" style="background-color: #007bff;color: #fff;text-transform: capitalize;font-size: 14px;float: right;">
                                <tr>
                                  <th scope="col">E-mail :</th>
                                  <th scope="col">{{requestticket.passengerlist[0].email}}</th>
                                </tr>
                              </thead>
                          </table>
                      </div>
                  </div>

                  <div class="rowgsdf" style="margin-top: 20px;margin-left: -15px;margin-right: -15px;">
                      <div class="col-md-12fg" style="width: 100%;float: left;padding: 0px 15px;">
                          <div class="panele panel-primarydd pan_custss" style="margin-bottom: 20px;padding: 0px 0px 10px 0px;border: 1px solid #007bff;float: left;width: 100%;">
                              <div class="panel-headindsg" style="padding: 10px 15px;background-color: #007bff;"> 
                                  <h3 class="panel-titless darkss" style="padding: 5px 10px;font-size: 20px;margin: auto;color: #fff;">Terms and Conditions</h3> 
                              </div> 
                              <div class="panel-bodyss" style="padding: 10px;">
                                  <p>
                                    Travelers including children and infants must present valid photo identification issued by Government
                                    of India (Aadhar card, Driving License, Pan Card, etc) at entry point of the airport and at the airline
                                    check-in counter.
                                    Check-in counter starts 2 hours prior and closes 45 minutes before your scheduled flight departure
                                    time.
                                  </p>
                                  <p>
                                    Travelers including children and infants must present valid photo identification issued by Government .                                  
                                  </p>
                                  <p>
                                    Travelers including children and infants must present valid photo identification issued by Government
                                    
                                    Check-in counter starts 2 hours prior and closes 45 minutes before your scheduled flight departure
                                    time.
                                  </p>
                                  <p>
                                    Travelers including children and infants must present valid photo identification issued by Government
                                    of India (Aadhar card, Driving License, Pan Card, etc) at entry point of the airport and at the airline
                                    check-in counter.
                                    Check-in counter starts 2 hours prior and closes 45 minutes before your scheduled flight departure
                                    time.
                                  </p>

                              </div>
                              <div class="panel-footerdfd" style="padding: 10px 15px;background-color: #f5f5f5;border-top: 1px solid #ddd;border-bottom-right-radius: 3px;border-bottom-left-radius: 3px;float: left;">
                                  <div class="rowff">
                                    <div class="col-md-12dsd" style="width: 100%;float: left;padding: 0px 15px;">
                                      <strong><h3 style="float: right;">Encore Digitech Pvt Ltd</h3></strong>
                                    </div>
                                    <div class="col-md-12sds" style="width: 100%;float: left;padding: 0px 15px;">
                                      <strong><p> Thanking you for choosing us as your ticket service provider and we wish you a happy journey.</p></strong>
                                    </div>
                                  </div>
                              </div>
                          </div>
                      </div>
                   </div>

              </form>
          </div>
            </div>
            <div class="rowdd" style="width: 50%;float: left;">
	        	<div class="col-md-5" style="margin-bottom: 10px;">
	                <div class="section-title aos-init aos-animate" data-aos="fade-up">
	                	<!-- <button onclick="getPDF()" id="download" class="n_prnt btn btn-primary">Download</button> -->
	                  <button type="button" class="btn btn-info" ng-click="edittransaction('myInvoice');"><i class="fa fa-edit"></i> Edit Transacyion Fees</button>
	                </div>
	            </div>
	            <div class="col-md-5" style="margin-bottom: 10px;">
	                <div class="section-title aos-init aos-animate" data-aos="fade-up">
	                	<!-- <button onclick="getPDF()" id="download" class="n_prnt btn btn-primary">Download</button> -->
	                  <button type="button" class="btn btn-info"><i class="fa fa-edit"></i> print ticket without ads</button>
	                </div>
	            </div>
	            <div class="col-md-4" style="margin-bottom: 10px;">
	                <div class="section-title aos-init aos-animate" data-aos="fade-up">
	                	<!-- <button onclick="getPDF()" id="download" class="n_prnt btn btn-primary">Download</button> -->
	                  <button type="button" class="btn btn-info"><i class="fa fa-edit"></i>Email ticket</button>
	                </div>
	            </div>
	            <div class="col-md-4">
	                <div class="section-title aos-init aos-animate" data-aos="fade-up">
	                	<button onclick="getPDF()" id="download" class="n_prnt btn btn-primary">Generate Pdf</button>
	                  <!-- <button type="button" class="btn btn-info"><i class="fa fa-edit"></i>generate pdf</button> -->
	                </div>
	            </div>
	            <div class="col-md-1">
	                <div class="section-title aos-init aos-animate" data-aos="fade-up">
	                	<!-- <button onclick="getPDF()" id="download" class="n_prnt btn btn-primary">Download</button> -->
	                  <button type="button" class="btn btn-info" ng-click="editdiscount('myInvoice');"><i class="fa fa-print"></i> Discount</button>
	                </div>
	            </div>
	        </div>
            
        </div>
        
		
		
		<!-- div1011  -TICKET---->
		
		<div id="div10120" class="new_flight-detals" style="background-color: white;display: none">
       	 <div class="container">
       		 <div class="it-row" style="margin-right: -15px;margin-left: -15px;">
                        <div class="page-header-title">
                            <h4 class="pull-left page-title">View TICKET</h4>
                            
                            <ol class="breadcrumb pull-right">
                            	<li>
                            		<a href="javascript:void(0);" class="btn btn-primary" onclick="showDiv('div1');">
                            			<i class="fa fa-arrow-left"></i> Back to My Booking
                            		</a>
                            	</li>
                            	<li><!-- <button type="button" class="btn btn-info" ng-click="printInvoice('myInvoice');"><i class="fa fa-print"></i> Print</button> --></li>
	                            
                            </ol>
                            <div class="clearfix"></div>
                        </div>
                   	</div>
            <div class="it-row" style="margin-right: -15px;margin-left: -15px;" >
                <div class="col-md-8">
                    <div class="tc-panel-group" style="margin-bottom: 20px;">
                        <div class="tc-panel tc-panel-primary" style="margin-bottom: 0;border-radius: 4px;border: 1px solid #337ab7;">
                            <div class="tc-panel-heading" style="background-color: #337ab7;color: #fff;padding: 5px 15px;">
                                <div class="col-md-3"> 
									<a class="navbar-brand " href="home"><img class="logo img-responsive" id="logo1" src = ""></a>
								</div>
                                <h4 style="font-size: 15px;font-weight: 700;display: inline-block;">TICKET -<span ng-if="requestticket.booking_status==='Ticketed'">Confirmed</span><span ng-if="requestticket.booking_status!=='Ticketed'">CANCEL</span> </h4>
                                <p style="color: #fff;font-size: 12px;display: inline-block;float: right;margin-top: 10px;">Booking Id: {{requestticket.booking_id}}</p>
                            </div>
                            <div class="tc-panel-body" style="padding: 15px;display:flow-root;">
                                <ul style="background-color: #a6c8ff;padding: 4px 10px;">
                                    <li style="margin-right: 50px;display: inline-block;color: #3909ff;font-weight: 700;">{{traveldate| date : "MMMM d, y"}}
                                    </li>
                                    <li style="margin-right: 50px;display: inline-block;color: #3909ff;font-weight: 700;"> {{requestticket.sourcescity}} TO {{requestticket.destinationcity}}  
                                    </li>
                                    <li style="margin-right: 50px;display: inline-block;color: #3909ff;font-weight: 700;float: right;"> {{requestticket.duration}}
                                    </li>
                                </ul>

                                <div class="wrwa">
                                    <div class="colf-md-4" style="width:33%;float:left;padding-right: 15px;padding-left: 15px;">
                                        <img class="pr_img" src="assets/skyflight/images/flightIcon/{{requestticket.airlinecd}}.png">
                                        <h5>Air India</h5>
                                    </div>
                                    <div class="ff-md-3" style="width: 25%;float: left;margin-bottom:20px;">
                                        <h3 style="font-weight: 700;margin: 10px 0 2px;">{{requestticket.source}}</h3>
                                        <p style="border-bottom: 2px solid #ccc;padding-bottom: 10px;">{{requestticket.sourcescity}} </p> 
                                        <p style="margin-bottom: 0;">{{requestticket.depart}}</p>
                                        <!-- <p style="margin-bottom: 0;font-size: 13px;color: #8a8a8a;">Nscbi Airport</p>
                                        <p style="margin-bottom: 0;color: blue;">Terminal 2</p>    -->
                                    </div>
                                    <div class="ff-md-2" style="width:16%;float: left;">
                                        <p style="margin-bottom: 0px;margin-top: 12px;"><img src="assets/images/asdi.jpeg"></p>
                                        <h5 style="border-bottom: 2px solid #ccc;padding-bottom: 10px;">{{requestticket.duration}}</h5>
                                        <p style="font-size: 13px;color: #5f5f5f;">Economy</p>
                                    </div>
                                    <div class="ff-md-3" style="width: 25%;float: left;margin-bottom:20px;">
                                        <h3 style="font-weight: 700;margin: 10px 0 2px;">{{requestticket.destination}}</h3>
                                        <p style="border-bottom: 2px solid #ccc;padding-bottom: 10px;">{{requestticket.destinationcity}}</p> 
                                        <p style="margin-bottom: 0;">{{requestticket.arrivtime}}</p>
                                        <!-- <p style="margin-bottom: 0;font-size: 13px;color: #8a8a8a;">Nscbi Airport</p>
                                        <p style="margin-bottom: 0;color: blue;">Terminal 2</p> -->
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
                                                <tr ng-repeat="passen in requestticket.passengerlist">
                                                    <td>{{passen.title}}. {{passen.firstname}} {{passen.lastname}}, {{passen.category}}</td>
                                                    <td>{{passen.PNR}}</td>
                                                    <td>{{passen.ticketnumber}}</td>
                                                    <td>1</td>
                                                </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                                <div class="bild-e" style="border: 1px solid #ccc;float: left;padding: 5px;">
                                <h4>Total Fair : {{listFlightFare.totalamount+listFlightFare.adminmarkup+listFlightFare.usermarkup}}</h4>
                                </div> 

                                <div class="bild-e" style="border: 1px solid #ccc;float: left;padding: 5px;">
                                    <h4>IMPORTANT INFORMATION</h4>
                                    <ul>
                                        <li>* This is computer generated invoice signature not required.</li>
                                        <li>* All Cases Disputes are subject to Kolkata Jurisdiction.</li>
                                        <li>* Refunds Cancellations are subject to Airline's approval.</li>
                                        <li>* Kindly check all details carefully to avoid unnecessary complications.</li>
                                        <li>* CHEQUE : Must be drawn in favour of Encore Digitech Pvt Ltd.</li>
                                        <li>* LATE PAYMENT : Interest per annum will be charged on all outstanding bills after due date.</li>
                                    </ul>
                                </div>

                            <!--     <div class="fr-l">
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
                                </div> -->

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
                                                        <th colspan="5">Encore Digitech Pvt Ltd</th>
                                                    </tr>
                                                    </thead>
                                                    <tbody>
                                                        <tr>
                                                            <td>Tel</td>
                                                            <td>+91-9811253330/6289812191</td>
                                                        </tr>
                                                    </tbody>
                                                </table>
                                            </div>
                                           <!--  <div class="fdfg" style="width: 50%;float: left;padding-left: 15px;">
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
                                            </div> -->
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
		
	<!-- <div class="rowfd content" style="margin-top: 5%;" id="myInvoice1">
         <div class="rowres">
               <div class="row" style="margin-top: 20px;">
                   <div class="col-md-12">
                       <div class="panel panel-primary pan_cust">
                           <div class="panel-heading"> 
                               <h3 class="panel-title dark">Invoice</h3> 
                           </div> 
                           <div class="panel-body">
                           		<div class="row">
					                   <div class="col-md-4">
					                       <table class="table table-responsive">
					                           <thead class="dark">
					                             <tr>
					                               <th scope="col">Invoice No :</th>
					                               <th scope="col">DW/2021/1458562 </th>
					                             </tr>
					                           </thead>
					                       </table>
					                   </div>
					                   <div class="col-md-4">
					                       <table class="table table-responsive">
					                           <thead class="dark">
					                             <tr>
					                               <th scope="col">Invoice Date :</th>
					                               <th scope="col"><span>  {{requestticket.date|date : "MMMM d, y"}}</span></th>
					                             </tr>
					                           </thead>
					                       </table>
					                   </div>
					                   <div class="col-md-4">
					                       <table class="table table-responsive">
					                           <thead class="dark" style="float: right;">
					                             <tr>
					                               <th scope="col">PNR :</th>
					                               <th scope="col">{{requestticket.PNR}}</th>
					                             </tr>
					                           </thead>
					                       </table>
					                   </div>
					               </div>
                               <div class="row" style="margin-top: 5%;">
                                   <div class="col-md-6">
                                       <div class="air_box" style="text-align: left;">
                                           <div class="row">
                                               <div class="col-md-12">
                                                   <strong>
                                                       Travel Boutique Online (A unit of Tek Travels Pvt Ltd)
                                                   </strong>
                                                   <ul>
                                                       <li>Regd Office: E-78, South Extn Part– I, New Delhi 110049 </li>
                                                       <li>Corp Off: Plot No 728, Udyog Vihar Phase-V,Gurgaon 122016</li>
                                                       <li>Email: info@travelboutiqueonline.com</li>
                                                       <li>Web: www.travelboutiqueonline.com</li>
                                                       <li>Phone: 0124-4998999</li>
                                                       <li>State: Haryana</li>
                                                       <li>GSTIN: 06AACCT6259K1ZZ</li>
                                                       <li>CI Number: U74999DL2006PTC155233</li>
                                                       <li>PAN : AACCT6259K</li>
                                                   </ul>
                                               </div>
                                           </div>
                                       </div>
                                   </div>
                                   <div class="col-md-6" style="float: right;">
                                       <div class="air_box" style="text-align: right;">
                                           <div class="row">
                                               <div class="col-md-12">
                                                   <strong>
                                                       EDPL HOLIDAYS
                                                   </strong>
                                                   <ul>
                                                       <li>Office Address: 14 RAMSITA GHAT STREET UTTARPARA712232</li>
                                                       <li>Email: edploperations@gmail.com</li>
                                                       <li>Web: www.travelboutiqueonline.com</li>
                                                       <li>State: West Bengal</li>
                                                       <li>GSTIN: 19ACPPD1851M1Z8</li>
                                                       <li>PAN : acppd1851m</li>
                                                   </ul>
                                               </div>
                                           </div>
                                       </div>
                                   </div>
                               </div>

                               <div class="row" style="margin-top: 20px;">
                                   <div class="col-md-12">
                                       <div class="my_table table-responsive">
                                       		<table class="table table-responsive">
		                                           <thead class="dark"> 
		                                             <tr>
		                                               <th scope="col">Sr No.</th>
		                                               <th scope="col">Ticket No</th>
		                                               <th scope="col">Sectors</th>
		                                               <th scope="col">Flight</th>
		                                               <th scope="col">PAX Name</th>
		                                               <th scope="col">Type</th>
		                                               <th scope="col">Class</th>
		                                               <th scope="col">Fare</th>
		                                               <th scope="col">OT Tax</th>
		                                               <th scope="col">K3/GST</th>
		                                               <th scope="col">YQ Tax</th>
		                                               <th scope="col">YR Tax</th>
		                                               <th scope="col">Bag.Ch.</th>
		                                               <th scope="col">Meal Ch.</th>
		                                               <th scope="col">Seat Ch.</th>
		                                               <th scope="col">Sp Service Ch.</th>
		                                               <th scope="col">Service Charges</th>
		                                             </tr>
		                                           </thead>
		                                           <tbody>
		                                             <tr>
		                                               <th scope="row">1</th>
		                                               <td>PT2014852674</td>
		                                               <td>27/06/2020</td>
		                                               <td>PT1658749850</td>
		                                               <td>Electricity Bill</td>
		                                               <td>Regular</td>
		                                               <td>PT1658749850</td>
		                                               <td>PT1658749850</td>
		                                               <td>PT1658749850</td>
		                                               <td>PT1658749850</td>
		                                               <td>PT1658749850</td>
		                                               <td>PT1658749850</td>
		                                               <td>PT1658749850</td>
		                                               <td>PT1658749850</td>
		                                               <td>PT1658749850</td>
		                                               <td>PT1658749850</td>
		                                               <td>PT1658749850</td>
		                                             </tr>
		                                           </tbody>
		                                       </table>
                                       </div>
                                   </div>
                               </div>

                               <div class="row" style="margin-top: 20px;">
                                   <div class="col-md-6">
                                       <div class="air_box" style="text-align: left;">
                                           <div class="row">
                                               <div class="col-md-12">
                                                   <strong style="margin-bottom: 30px;display: block;">
                                                       Note: * Voidation and Refund as per fare rules
                                                   </strong>
                                                   <ul>
                                                       <li>Billed by :   Travel Boutique Online</li>
                                                       <li>Ticketed By : Tushar kanti Dutta</li>
                                                       <li>Invoice Status : Paid</li>
                                                       <li>Agent Remarks : </li>
                                                   </ul>
                                               </div>
                                           </div>
                                       </div>
                                   </div>
                                   <div class="col-md-6" style="float: right;">
                                       <div class="air_box">
                                           <div class="row">
                                               <div class="col-md-12">
                                                   <table class="table table-responsive">
                                                       <thead class="dark"> 
                                                         <tr>
                                                           <th colspan="5">Payment Details</th>
                                                           <th>Amount ( INR )</th>
                                                         </tr>
                                                       </thead>
                                                       <tbody>
                                                         <tr>
                                                           <td colspan="5">Commission Earned</td>
                                                           <td>6,000.00</td>
                                                         </tr>
                                                         <tr>
                                                           <td colspan="5">Tra Fee</td>
                                                           <td>6,000.00</td>
                                                         </tr>
                                                         <tr>
                                                           <td colspan="5">TDS Deducted</td>
                                                           <td>6,000.00</td>
                                                         </tr>
                                                         <tr>
                                                           <td colspan="5">CGST @0%</td>
                                                           <td>6,000.00</td>
                                                         </tr>
                                                         <tr>
                                                           <td colspan="5">SGST @0%</td>
                                                           <td>6,000.00</td>
                                                         </tr>
                                                         <tr>
                                                           <td colspan="5">IGST @18%</td>
                                                           <td>6,000.00</td>
                                                         </tr>
                                                         <tr>
                                                           <td colspan="5">Net Amount</td>
                                                           <td>19574.00</td>
                                                         </tr>
                                                         <tr>
                                                           <th colspan="5">Net Receivable</th>
                                                           <th>19574.00</th>
                                                         </tr>
                                                       </tbody>
                                                   </table>
                                               </div>
                                           </div>
                                       </div>
                                   </div>
                               </div>

                               <div class="row" style="margin-top: 20px;">
                                   <div class="col-md-12">
                                       <strong>GST Details :</strong>
                                       <table class="table table-responsive">
                                           <thead class="dark"> 
                                             <tr>
                                               <th scope="col">Service Description</th>
                                               <th scope="col">SAC</th>
                                               <th scope="col">Taxable Value</th>
                                               <th scope="col">CGST @ 0%</th>
                                               <th scope="col">SGST @ 0%</th>
                                               <th scope="col">IGST @ 18%</th>
                                               <th scope="col">Total</th>
                                             </tr>
                                           </thead>
                                           <tbody>
                                             <tr>
                                               <th>PT1658749850</th>
                                               <td>PT2014852674</td>
                                               <td>27/06/2020</td>
                                               <td>PT1658749850</td>
                                               <td>Electricity Bill</td>
                                               <td>Regular</td>
                                               <td>PT1658749850</td>
                                             </tr>
                                           </tbody>
                                       </table>
                                   </div>
                               </div>

                               <div class="row" style="margin-top: 20px;">
                                   <div class="col-md-12">
                                       <strong>Passenger GST Details :</strong>
                                       <table class="table table-responsive">
                                           <thead class="dark"> 
                                             <tr>
                                               <th scope="col">Lead Pax Name</th>
                                               <th scope="col">GST Number</th>
                                               <th scope="col">GST Company Contact Number</th>
                                               <th scope="col">GST Company Address</th>
                                               <th scope="col">GST Company Email</th>
                                               <th scope="col">GST Company Name</th>
                                             </tr>
                                           </thead>
                                           <tbody>
                                             <tr>
                                               <td>PT2014852674</td>
                                               <td>27/06/2020</td>
                                               <td>PT1658749850</td>
                                               <td>Electricity Bill</td>
                                               <td>Regular</td>
                                               <td>PT1658749850</td>
                                             </tr>
                                           </tbody>
                                       </table>
                                   </div>
                               </div>

                               <div class="row" style="margin-top: 20px;">
                                   <div class="col-md-12">
                                       <div class="panel panel-primary pan_cust">
                                           <div class="panel-heading"> 
                                               <h3 class="panel-title dark">Terms and Conditions</h3> 
                                           </div> 
                                           <div class="panel-body">
                                               <p>
                                                 Travelers including children and infants must present valid photo identification issued by Government
                                                 of India (Aadhar card, Driving License, Pan Card, etc) at entry point of the airport and at the airline
                                                 check-in counter.
                                                 Check-in counter starts 2 hours prior and closes 45 minutes before your scheduled flight departure
                                                 time.
                                               </p>
                                               <p>
                                                 Travelers including children and infants must present valid photo identification issued by Government .                                  
                                               </p>
                                               <p>
                                                 Travelers including children and infants must present valid photo identification issued by Government
                                                 
                                                 Check-in counter starts 2 hours prior and closes 45 minutes before your scheduled flight departure
                                                 time.
                                               </p>
                                               <p>
                                                 Travelers including children and infants must present valid photo identification issued by Government
                                                 of India (Aadhar card, Driving License, Pan Card, etc) at entry point of the airport and at the airline
                                                 check-in counter.
                                                 Check-in counter starts 2 hours prior and closes 45 minutes before your scheduled flight departure
                                                 time.
                                               </p>

                                           </div>
                                           <div class="panel-footer">
                                               <div class="rowff">
                                                 <div class="col-md-12">
                                                   <strong><h3 style="float: right;">S K Sharma</h3></strong>
                                                 </div>
                                                 <div class="col-md-12">
                                                   <strong><p> Thanking you for choosing us as your ticket service provider and we wish you a happy journey.</p></strong>
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
    	 </div>	 -->
		
		
		
		
		
		<!-- div1012  invoice-->
	
		<div class="invoice" style="padding: 20px 0;background-color: #fff;display: none" id="div1012">
        <div class="in-container" style="width:100%;padding-right: 15px;padding-left: 15px;margin-right: auto;margin-left: auto;">
        <div class="it-row" style="margin-right: 195px;margin-left: -11px;">
                        <div class="page-header-title">
                            <h4 class="pull-left page-title">View Invoice</h4>
                            
                            <ol class="breadcrumb pull-right">
                            	<li>
                            		<a href="javascript:void(0);" class="btn btn-primary" onclick="showDiv('div1');">
                            			<i class="fa fa-arrow-left"></i> Back to My Booking
                            		</a>
                            	</li>
                            	<li><button type="button" class="btn btn-info" ng-click="printInvoice('myInvoice1');"><i class="fa fa-print"></i> Print</button></li>
	                            
                            </ol>
                            <div class="clearfix"></div>
                        </div>
                   	</div>
                   	
            <div class="inv-row" style="margin-right: -15px;margin-left: -15px;" id="myInvoice1">
                <div class="in-col-md-10" style="padding-right: 15px;padding-left: 15px;width: 100%;">
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
                                            	<h3 style="margin: 5px;font-size: 15px;font-weight: 600;">Agent Name : <strong>{{flightuser.name}}</strong> </h3>
		                                        <h3 style="margin: 5px;font-size: 15px;font-weight: 600;">Address : <strong> {{flightuser.address}}</strong></h3>
		                                        <h3 style="margin: 5px;font-size: 15px;font-weight: 600;">Email : <strong> {{flightuser.email}}</strong></h3>
		                                        <!-- <ul>
	                                                <li style="margin-bottom: 8px;font-weight: 600;"></li>
	                                                <li style="margin-bottom: 8px;">Invoice No. : <span>4646614</span></li>
	                                                <li style="margin-bottom: 8px;">Invoice Date : <span>  {{requestticket.date|date : "MMMM d, y"}}</span></li>
	                                                <li style="margin-bottom: 8px;">PNR No. : <span>{{requestticket.airlinepnr}}</span></li>
	                                                <li style="margin-bottom: 8px;">PAN No : <span>AADCM5146R</span></li>
                                            	</ul> -->
                                        </div>
                                    </div>
                                    <div class="col-md-4-inv" style="width:33%;position: relative;min-height: 1px;padding-right: 15px;padding-left: 15px;float: left;">
                                        <div class="img-responsive-in">
											<a class="oh"><img class="logo img-responsive" id="logo3" src = ""></a>
                                        </div>
                                        <h3 style="margin: 5px 5px 10px 5px;font-size: 15px;font-weight: 600;">Name : <strong>Encore Digitech Pvt Ltd</strong> </h3>
                                        <h3 style="margin: 5px 5px 10px 5px;font-size: 15px;font-weight: 600;">Address : <strong> Office: 3, Canal Street, Loknath Apartment, 1st Floor, Kolkata - 700014 (India) </strong></h3>
                                        
                                    </div>
                                </div>
                                <div class="in-tebil table-responsive-in" style="min-height: .01%;overflow-x: auto;">
                                 	 <h5 style="font-size: 15px;font-weight: 600;">Invoice Details</h5>
                                    <table class="table-in in-table-bordered" style="width: 100%;max-width: 100%;margin-bottom: 20px;border: 1px solid #ddd;">
                                        <thead>
                                        <tr style="border-bottom: 1px solid #ccc;">
                                            <th style="border-right: 1px solid #ccc;font-size: 12px;font-weight: 500;padding: 10px;">Invoice No.</th>
                                            <th style="border-right: 1px solid #ccc;font-size: 12px;font-weight: 500;padding: 10px;">Invoice Date :</th>
                                            <th style="border-right: 1px solid #ccc;font-size: 12px;font-weight: 500;padding: 10px;"   style="border-right: 1px solid #ccc;">PNR No. : </th>
                                            <th style="border-right: 1px solid #ccc;font-size: 12px;font-weight: 500;padding: 10px;"   style="border-right: 1px solid #ccc;">PAN No</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <td style="border-right: 1px solid #ccc;padding: 10px;">4646614}</td>
                                                <td style="border-right: 1px solid #ccc;padding: 10px;">{{requestticket.date|date : "MMMM d, y"}}</td>
                                                <td style="border-right: 1px solid #ccc;padding: 10px;">{{requestticket.airlinepnr}}</td>
                                                <td style="border-right: 1px solid #ccc;padding: 10px;">AADCM5146R</td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                                 <div class="in-tebil table-responsive-in" style="min-height: .01%;overflow-x: auto;">
                                    <table class="table-in in-table-bordered" style="width: 100%;max-width: 100%;margin-bottom: 20px;border: 1px solid #ddd;">
                                        <thead>
                                        <tr style="border-bottom: 1px solid #ccc;">
                                            <th style="border-right: 1px solid #ccc;font-size: 12px;font-weight: 500;padding: 10px;">Booked by</th>
                                            <th style="border-right: 1px solid #ccc;font-size: 12px;font-weight: 500;padding: 10px;">Contact</th>
                                            <th style="border-right: 1px solid #ccc;font-size: 12px;font-weight: 500;padding: 10px;"   style="border-right: 1px solid #ccc;">Booked ID </th>
                                            <th style="border-right: 1px solid #ccc;font-size: 12px;font-weight: 500;padding: 10px;"   style="border-right: 1px solid #ccc;">Booked Date</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                            <tr ng-repeat="passen in requestticket.passengerlist" ng-if="passen.isleadpassenger===true">
                                                <td style="border-right: 1px solid #ccc;padding: 10px;">{{passen.title}}. {{passen.firstname}} {{passen.lastname}}, {{passen.category}}</td>
                                                <td style="border-right: 1px solid #ccc;padding: 10px;">{{passen.contact}}<br>{{passen.email}}</td>
                                                <td style="border-right: 1px solid #ccc;padding: 10px;">{{passen.ticketnumber}}</td>
                                                <td style="border-right: 1px solid #ccc;padding: 10px;">{{traveldate|date : "MMMM d, y"}}</td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                                
                                 

                                <div class="in-tebil table-responsive-in" style="min-height: .01%;overflow-x: auto;">
                                    <h5 style="font-size: 15px;font-weight: 600;">Flight Details</h5>
                                    <table class="table-in in-table-bordered" style="width: 100%;max-width: 100%;margin-bottom: 20px;border: 1px solid #ddd;">
                                        <thead>
                                        <tr style="border-bottom: 1px solid #ccc;" ng-repeat="fligh in requestticket.flighdetail">
                                            <th style="border-right: 1px solid #ccc;font-size: 12px;font-weight: 500;padding: 10px;">{{fligh.flightid}}-{{fligh.flight_number}}</th>
                                            <th style="border-right: 1px solid #ccc;font-size: 12px;font-weight: 500;padding: 10px;"   style="border-right: 1px solid #ccc;">{{fligh.sourceterminal}}</th>
                                            <th style="border-right: 1px solid #ccc;font-size: 12px;font-weight: 500;padding: 10px;"   style="border-right: 1px solid #ccc;">{{fligh.destinationterminal}}</th>
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
                                        <tr style="border-bottom: 1px solid #ccc;" ng-repeat="passen in requestticket.passengerlist">
                                            <th style="border-right: 1px solid #ccc;font-size: 12px;font-weight: 500;padding: 10px;">{{$index+1}}) {{passen.title}}. {{passen.firstname}} {{passen.lastname}}, {{passen.category}}
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
                                            <th style="border-right: 1px solid #ccc;font-size: 12px;font-weight: 500;padding: 10px;">Passenger
                                            </th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                            <tr ng-repeat="istPassengerfare in istPassengerfare">
                                                <td style="border-right: 1px solid #ccc;padding: 10px;">Base Fare</td>
                                                <td style="border-right: 1px solid #ccc;padding: 10px;">{{istPassengerfare.basicamount}}</td>
                                            </tr>
                                        </tbody>
                                    </table>
                                    </div>
                                    <div class="gs-md-6" style="width:50%;position: relative;min-height: 1px;padding-right: 15px;float: left;">
                                        <h5 style="font-size: 15px;font-weight: 600;">Tax and Other Charges:</h5>
                                        <table class="table-in in-table-bordered" style="width: 100%;max-width: 100%;margin-bottom: 20px;border: 1px solid #ddd;">
                                        <thead>
                                        <tr style="border-bottom: 1px solid #ccc;" ng-repeat="istPassengerfare in istPassengerfare">
                                            <th style="border-right: 1px solid #ccc;font-size: 12px;font-weight: 500;padding: 10px;">Other Surcharge
                                            </th>
                                            <th style="border-right: 1px solid #ccc;font-size: 12px;font-weight: 500;padding: 10px;">{{istPassengerfare.totaltax}}
                                            </th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <td style="border-right: 1px solid #ccc;padding: 10px;">Total Fare</td>
                                                <td style="border-right: 1px solid #ccc;padding: 10px;">{{listFlightFare.totalamount+listFlightFare.usermarkup+listFlightFare.adminmarkup+listFlightFare.fees-listFlightFare.discount|number:2}}</td>
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
                                            <th style="border-right: 1px solid #ccc;font-size: 12px;font-weight: 500;padding: 10px;"   style="border-right: 1px solid #ccc;">INR {{listFlightFare.totalamount+listFlightFare.usermarkup+listFlightFare.adminmarkup+listFlightFare.fees-listFlightFare.discount|number:2}}</th>
                                        </tr>
                                        </thead>
                                     <!--    <tbody>
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
                                        </tbody> -->
                                    </table>
                                </div>

  								 <div class="in-tebil table-responsive-in" style="min-height: .01%;overflow-x: auto;">
                                    <div class="in-row" style="margin-right: 15px;margin-left:15px;">
                                        <div class="serf-md-6" style="width:50%;position: relative;min-height: 1px;padding-right: 15px;float: left;">
                                        <h5 style="font-size: 15px;font-weight: 600;">GST Details:</h5>
                                        <table class="table-in in-table-bordered" style="width: 100%;max-width: 100%;margin-bottom: 20px;border: 1px solid #ddd;">
                                        <thead>
                                        <tr style="border-bottom: 1px solid #ccc;">
                                            <th style="border-right: 1px solid #ccc;font-size: 12px;font-weight: 500;padding: 10px;">Company Name
                                            </th>
                                            <th style="border-right: 1px solid #ccc;font-size: 12px;font-weight: 500;padding: 10px;">{{GstFlight.companyNAme}}
                                            </th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <td style="border-right: 1px solid #ccc;padding: 10px;">Company Address</td>
                                                <td style="border-right: 1px solid #ccc;padding: 10px;">{{GstFlight.companyAddress}}</td>
                                            </tr>
                                        </tbody>
                                    </table>
                                    </div>
                                    <div class="gs-md-6" style="width:50%;position: relative;min-height: 1px;padding-right: 15px;float: left;">
                                        <h5 style="font-size: 15px;font-weight: 600;"></h5>
                                        <table class="table-in in-table-bordered" style="width: 100%;max-width: 100%;margin-bottom: 20px;border: 1px solid #ddd;margin-top: 37px;">
                                        <thead>
                                        <tr style="border-bottom: 1px solid #ccc;">
                                            <th style="border-right: 1px solid #ccc;font-size: 12px;font-weight: 500;padding: 10px;">GST NO
                                            </th>
                                            <th style="border-right: 1px solid #ccc;font-size: 12px;font-weight: 500;padding: 10px;">{{GstFlight.gst}}
                                            </th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <td style="border-right: 1px solid #ccc;padding: 10px;">Pan No</td>
                                                <td style="border-right: 1px solid #ccc;padding: 10px;">{{GstFlight.pan}}</td>
                                            </tr>
                                        </tbody>
                                    </table>
                                    </div>
                                    </div>
                                </div>

                                <!-- <div class="confi-bod-in" style="border: 2px dashed #337ab7;padding: 20px;">
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
                                                        <td style="border-right:1px solid #ccc; border-bottom: 1px solid #ccc; padding: 10px;">Office: 3, Canal Street, Loknath Apartment, 1st Floor, Kolkata - 700014 (India)</td>
                                                        <td style="border-right:1px solid #ccc; border-bottom: 1px solid #ccc; padding: 10px;">+91-9811253330/6289812191</td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div> -->

                                <!-- <div class="gst" style="margin-top: 20px;">
                                    <div class="inv-row" style="margin-right: -15px;margin-left: -15px;display: flex;">
                                        <div class="col-md-6-in table-responsive-in" style="width: 100%; padding-right: 15px;padding-left: 15px; display: inline-block; min-height: .01%;overflow-x: auto;">
                                            <h2 style="font-size: 16px;font-weight: 600;">Passenger GST Details:</h2>
                                            <table class="table-in" style="width: 100%;max-width: 100%;margin-bottom: 20px;border: 1px solid #ddd;">
                                                <thead>
                                                    <tr style="border-bottom: 1px solid #ccc;">
                                                        <th style="font-size: 13px; font-weight: normal; padding: 10px; border-right: 1px solid #ccc;">Name</th>
                                                        <th style="font-size: 13px; font-weight: normal; padding: 10px; border-right: 1px solid #ccc;">Encore Digitech Pvt Ltd</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <tr>
                                                        <td style="border-right:1px solid #ccc; border-bottom: 1px solid #ccc; padding: 10px;font-size: 13px;font-weight: normal;">Registered Office</td>
                                                        <td style="border-right:1px solid #ccc; border-bottom: 1px solid #ccc; padding: 10px;font-size: 13px;font-weight: normal;">Office: 3, Canal Street, Loknath Apartment, 1st Floor, Kolkata - 700014 (India)</td>
                                                    </tr>
                                                    <tr>
                                                        <td style="border-right:1px solid #ccc; border-bottom: 1px solid #ccc; padding: 10px;font-size: 13px;font-weight: normal;">Tel No.</td>
                                                        <td style="border-right:1px solid #ccc; border-bottom: 1px solid #ccc; padding: 10px;font-size: 13px;font-weight: normal;">+91-9811253330/6289812191</td>
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
                                                        <td style="border-right:1px solid #ccc; border-bottom: 1px solid #ccc; padding: 10px;font-size: 13px;font-weight: normal;">http://encodigi.net.in/</td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div> -->
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
		

		<!------------ Main Div End ------------------->
		
		<div class="divef" id="div0" style="display: block;">
			<div class="row">
				<div class="col-md-12" style="background-image: url(assets/skyflight/images/flightbook/flight2.jpg);height: 100vh;
					    background-position: top;    background-size: cover;">
					<!-- <img src="assets/skyflight/images/flightbook/flight2.jpg" style="width: 100%"> -->
					<div class="row">
						<div class="col-md-offset-1 col-md-10">
							<a  href="flightsearch">
							<button type="button"  class="fbtn fbtn-danger" style="margin-top: 18%">Book Flight</button></a>
						</div>
					</div>
				</div>
			</div>
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
	<script src="assets/js/dir-pagination.js"></script>
	
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.3.3/jspdf.min.js"></script>
	<script src="https://html2canvas.hertzen.com/dist/html2canvas.js"></script>
	
	<script type="text/javascript">

	function getPDF(){
		$(window).scrollTop(0);
	    //$('#con').scrollLeft(0);
		var HTML_Width = $("#con").width();
		var HTML_Height = $("#con").height();
		
		html2canvas(document.querySelector("#con")).then(canvas => {
            //$("#previewBeforeDownload").html(canvas);
            var imgData = canvas.toDataURL("image/jpeg",1);
            var pdf = new jsPDF("p", "mm", "a4");
            var pageWidth = pdf.internal.pageSize.width;
            var pageHeight = pdf.internal.pageSize.height;
         
            var imageWidth = canvas.width;
            var imageHeight = canvas.height;
            
            var ratio = imageWidth/imageHeight >= pageWidth/pageHeight ? pageWidth/imageWidth : pageHeight/imageHeight;
            //pdf = new jsPDF(this.state.orientation, undefined, format);
            pdf.addImage(imgData, 'JPEG', 0, 0, pageWidth , pageHeight);
            pdf.save("invoice.pdf");
            //$("#previewBeforeDownload").hide();
           // $('#c-invoice').modal('hide');
        });
		
		//var totalPDFPages = Math.ceil(HTML_Height/PDF_Height)-1;
		

	/* 	html2canvas($("#con")[0],{allowTaint:true}).then(function(canvas) {
			canvas.getContext('2d');
			
			console.log(canvas.height+"  "+canvas.width);
			
			
			var imgData = canvas.toDataURL("assets/img/cartf.png", 1.5);
			var pdf = new jsPDF('p', 'mm', 'a4' );
		    pdf.addImage(imgData, 'JPG', top_left_margin, top_left_margin,canvas_image_width,canvas_image_height);
			
			for (var i = 1; i <= totalPDFPages; i++) { 
				pdf.addPage(PDF_Width, PDF_Height);
				pdf.addImage(imgData, 'JPG', top_left_margin, -(PDF_Height*i)+(top_left_margin*4),canvas_image_width,canvas_image_height);
			}
			
		    pdf.save("Ticket.pdf");
        }); */
	}
	
	
	</script>

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