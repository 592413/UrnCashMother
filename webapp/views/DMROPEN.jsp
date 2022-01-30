<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@page import="com.recharge.serviceimpl.UserImpl"%>   
<%@page import="com.recharge.model.User"%>
<%@page import="org.apache.log4j.Logger"%>

<%
	User user = (User) session.getAttribute("UserDetails");		
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="keywords" content="HTML,CSS,XML,JavaScript">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>IMPS</title>
<link rel="icon" href="#" type="image/x-icon" />
<link href="https://fonts.googleapis.com/css?family=Roboto:400,700&subset=latin,cyrillic-ext" rel="stylesheet" type="text/css">
<link href="assets/css/material-icons.css" rel="stylesheet">
<link href="assets/css/bootstrap.css" rel="stylesheet">
<link href="assets/css/materialize1.css" rel="stylesheet" />
<link href="assets/css/jquery-ui.css" rel="stylesheet">
<link href="assets/css/jquery.scrolling-tabs.css" rel="stylesheet">
<link href="assets/css/bootstrap-select.css" rel="stylesheet">
<link href="assets/css/animate.css" rel="stylesheet" />
<link href="assets/css/style.css" rel="stylesheet">
<link href="assets/css/all-themes.css" rel="stylesheet" />
<link href="assets/css/bootstrap-multiselect.css" rel="stylesheet" />
<link href="assets/css/bootstrap-datepicker.min.css" rel="stylesheet" />
<link href="assets/css/font-awesome.min.css" rel="stylesheet" />
<link href="assets/css/chosen.css" rel="stylesheet" />
<link href="assets/css/waves.css" rel="stylesheet" />
<link href="assets/css/sweetalert.css" rel="stylesheet" />
<script src="assets/js/jquery.min.js"></script>
<script src="assets/js/jquery-1.11.3.min.js"></script>
<script src="assets/js/bootstrap-multiselect.js"></script>
<script src="assets/js/sweetalert.min.js"></script>
<link href="assets/css/newstyle.css" rel="stylesheet" />
<link href="assets/css/stellarnav.min.css" rel="stylesheet" />
<link href="assets/css/all.min.css" rel="stylesheet" />
<style type="text/css">

@media print {
    body * {
        visibility: hidden;
    }
    #print-content * {
        visibility: visible;
    }
    .modal{
        position: absolute;
        left: 0;
        top: 0;
        margin: 0;
        padding: 0;
        min-height:550px
    }
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
	background: #2a58f4;
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
.loader {
    float: left;
    z-index: 1000;
    height: 100%;
    width: 100%;
    background: #eee;
    opacity: 0.8;
    position: fixed;
    top: 0px;
}
.floatMessage {
	position: absolute;
	z-index: 999999;
	left: 40%;
	top: 29%;
	width: 27%;
}
.discounttext {
	width: 95%;
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
</style>
</head>
<body class="theme-blue" ng-app="app" ng-controller="appController">
<alert-message alert="alertMessage"></alert-message>
<div class="page-loader-wrapper" ng-show="loader" >
	<div class="loader">
		<div class="preloader">
			<img alt="" src="assets/images/l3.gif">
		</div>
		<p>Please wait...</p>
	</div>
</div>
	
	
	<!-- Top Bar -->
	<!-- <div class="container-fluid navbar1 navbar">
	<div class="row">
	<div class="col-md-1"> 
		<a class="navbar-brand " href="home"><img class="logo" alt="" src="assets/images/logo.png" ></a>
		<a class="navbar-brand " href="home"><img class="logo" id="logo" src = ""></a>
	</div>
	<div class="col-md-11" style="margin: 0px; padding: 0px;">
		<div class="stellarnav">
			<ul>
				<li><a href="home"> <i class="material-icons">home</i><span>Home</span></a></li>
				<li><a onClick="showDiv('div2')" href="#"> <i class="material-icons">content_copy</i> <span>Reports</span></a></li>
				<li class="right-site"><a href="javascript:void(0);">
					<span><input class="ure_pf" ng-model="userDetails.name" type="text"></span>
					</a>
					<ul class="usr_detl">
						<li><a href="javascript:void(0);">
								<div class="name" data-toggle="dropdown"
									aria-haspopup="true" aria-expanded="false">{{userDetails.name}}({{userDetails.firmName}})</div>
								<div class="email">{{userDetails.email}}</div>
							</a>
						</li>
						<li><a href="javascript:void(0);"
							onClick="showDiv('div2')"><i class="material-icons">person</i>Profile</a></li>
						<li><a href="logout"><i class="fas fa-rupee-sign"></i> Balance : {{userDetails.balance}}/-</a></li>
						<li role="seperator" class="divider"></li>
						<li><a href="logout"><i class="material-icons">input</i>Log out</a></li>
						
					</ul>
				</li>
			</ul>
		</div>
	</div>
	
	<div class="col-md-1 col-xs-hidden">
		<ul class="nav navbar-nav " >
					Call Search
					<li>
						<a href="javascript:void(0);" class="js-search" data-close="true" title="Advanced Search Option"><i	class="material-icons">search</i></a>
					</li>
					#END# Call Search
					Notifications
					<li class="dropdown">
						<a href="#;" class="dropdown-toggle" data-toggle="dropdown" role="button"> 
							<i class="material-icons">notifications</i> 
							<span class="label-count">{{notification.balRequest}}</span>
						</a>
						<ul class="dropdown-menu">
							<li class="header">NOTIFICATIONS</li>
							<li class="body">
								<ul class="menu">									
									<li ng-if = "notification.balRequest > 0">
										<a href="javascript:void(0);" ng-click="viewPendingBalanceRequest(viewBalanceRequest);" onClick="showDiv('div17')">
											<div class="icon-circle bg-light-green">
												<i class="material-icons">watch_later</i>
											</div>
											<div class="menu-info">
												<h4>{{notification.balRequest}} Balance Request</h4>
												
											</div>
										</a>
									</li>
									<li ng-if = "notification.balRequest == 0">
										<a href="javascript:void(0);">
											<div class="icon-circle bg-light-green">
												<i class="material-icons">watch_later</i>
											</div>
											<div class="menu-info">
												<h4>No Notification</h4>												
											</div>
										</a>
									</li>
								</ul>
							</li>
						</ul>
					</li>
				</ul>
	
		</div>
		</div>
	</div> -->
	
	<!-- ----------------------------------------End---------------------------------------------- -->
	<section class="content aepsadrpay">
	<div class="container-fluid" style="margin-top: 5%;">
		<!-------------- Div0 ---------------------->
		<div id="div0" class="row clearfix" >
			<div class="col-md-3"></div>
			<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
				<div class="new_dmr" style="background-image: url('assets/images/mt4.jpg');">
					<div class="header">
						<h2>IMPS Service</h2>
					</div>
					<div class="body">
						<div class="row clearfix">
							<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12 col-lg-offset-3 col-md-offset-3 ">
								<div class="form-group form-float">
									<div class="form-line">
										<label>Mobile Number</label>
										<input type="text" class="form-control" ng-model = "remitter.mobile" maxlength="10"> 
									</div>
								</div>
							</div>
							<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12 col-lg-offset-3 col-md-offset-3 ">
								<center><div class="form-group form-float">
									<button type="button" class="btn btn-lg new_dmr_bt" ng-click = "checkuserOPEN(remitter);">SUBMIT</button>
								</div></center>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-3"></div>
		</div>
		<!-------------- /Div0 ---------------------->
		
		<div id="div2" class="row clearfix" style="display: none">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<div class="card">
					<div class="header">
						<h2>DMR Report</h2>
					</div>
					<div class="body">
						<form class="form-horizontal">
							<div class="row clearfix">
								<div class="col-lg-5 col-md-5 col-sm-4 col-xs-4 form-control-label">
									<label for="email_address_2">Start Date</label>
								</div>
								<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
									<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 m-l-15">
										<div class="form-group">
											<div class="form-group form-float">
												<div class="form-line">
													<input type="text" ng-model="impsReport.start_date" class="form-control" id="dp27" placeholder="Enter Start Date" ng-model="viewPurchaseReport.startDate" readonly="readonly" />
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="row clearfix">
								<div class="col-lg-5 col-md-5 col-sm-4 col-xs-4 form-control-label">
									<label for="email_address_2">End Date</label>
								</div>
								<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
									<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 m-l-15">
										<div class="form-group">
											<div class="form-group form-float">
												<div class="form-line">
													<input type="text" class="form-control" ng-model="impsReport.end_date" id="dp30" placeholder="Enter End Date" readonly="readonly" />
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="row clearfix">
								<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 "></div>
								<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
									<div class="col-md-offset-3">
										<button type="button" class="btn btn-primary waves-effect"  ng-click="getImpsReport(impsReport,'getImpsReportUser')">submit</button>
									
									</div>
								</div>
							</div>
							<div class="row clearfix" ng-bind-html="getImpsReportUser" compile-template>
							</div>
						</form>
						<div class="row clearfix" style="margin-top: 2%">
							<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 table-responsive" ng-if = "impsReports.length > 0">
								<table id="mainTable" class="table table-striped">
									<thead>
										<tr style="font-size: 12px; font-weight: bold;">
											<th class="success text-center">Sl. No</th>
											<th class="success text-center">Username</th>
											<th class="success text-center">account_no</th>
											<th class="success text-center">Beneficiary Name</th>
											<th class="success text-center">Beneficiary Mobile</th>
											<th class="success text-center">Opening Balance</th>
											<th class="success text-center">amount</th>
											<th class="success text-center">charge</th>
											<th class="success text-center">cl_bal</th>
											<th class="success text-center">Date & Time</th>
											<th class="success text-center">recieptId</th>
											<th class="success text-center">Status</th>
											<th class="success text-center">BANK TRANSID</th>
											<th class="success text-center">Invoice</th>
										    <th class="success text-center">Action</th>	
										     	 
										</tr>
									</thead>
									<tbody>
										<tr ng-repeat="report in impsReports | startFrom:currentPage*pageSize | limitTo:pageSize">
											<td style="font-size: 12px;">{{$index + 1}}</td>
											<td style="font-size: 12px;">{{report.uname}}<br> ({{report.usermobile}})</td>
											<td style="font-size: 12px;">{{report.account_no}}</td>
											<td style="font-size: 12px;">{{report.bene_name}}</td>
											<td style="font-size: 12px;">{{report.bene_mobile}}</td>
											<td style="font-size: 12px;">{{report.op_bal}}</td>
											<td style="font-size: 12px;">{{report.amount}}</td>
											<td style="font-size: 12px;">{{report.charge}}</td>
											<td style="font-size: 12px;">{{report.cl_bal}}</td>
											<td style="font-size: 12px;">{{report.date}} <br>{{report.time}}</td>
											<td style="font-size: 12px;">{{report.recieptId}}</td>
											<td style="font-size: 12px;">{{report.status}}</td>
											<td style="font-size: 12px;">{{report.banktransactionId}}</td>
											<td style="font-size: 12px;"><button type="button" class="btn btn-primary waves-effect"  ng-click="printdmrReport(report)">submit</button></td>  
											<td style="font-size: 12px;"><button type="button" ng-if="report.status=='PENDING'" class="btn btn-primary waves-effect"  ng-click="checkImpsStatus(report)">submit</button></td>
											
											<td style="font-size: 12px;"><button type="button" ng-if="report.status=='FAILED'" class="btn btn-primary waves-effect"  ng-click="REFUNDImpsStatus(report)">REFUND</button></td>
											
										</tr>
									</tbody>
									</table>
							</div>
						 	<div data-ng-show="viewRechargeReportsPagination">
								<button ng-disabled="currentPage == 0" ng-click="currentPage=currentPage-1">Previous</button>
								{{currentPage+1}}/{{numberOfPages()}}
								<button ng-disabled="currentPage >= data.length/pageSize - 1" ng-click="currentPage=currentPage+1">Next</button>
							</div> 
							<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 table-responsive" ng-if="impsReports.length <= 0">
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
		
		<!-------------- Div1 ---------------------->
		<div id="div1" class="row clearfix visibility" >
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<div class="card">
					<div class="header">
						<h2>IMPS Service</h2>
					</div>
						<!------------------------- Remiter Registration -------------------------->
					<div class="body visibility" id="sdiv0">
						<div class="row clearfix" style="margin-top: 2%">
							<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 table-responsive" >
								<div class="header">
									<h2>Sender Details</h2>
								</div>
								<form class="form-horizontal">
									<div class="row clearfix">
										<div class="col-md-12" style="margin-top: 2%">
											<div
												class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
												<label for="email_address_2">Remitter Mobile Number </label>
											</div>
											<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
												<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
													<div class="form-group form-float"
														style="margin-left: 0px !important;">
														<div class="form-line">
															<input type="text" class="form-control" ng-model="remitter.customermobile" maxlength="10" readonly="readonly"/>
															
														</div>
													</div>
												</div>
											</div>
										</div>
										<div class="col-md-12">
											<div
												class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
												<label for="email_address_2">Remitter first Name</label>
											</div>
											<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
												<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
													<div class="form-group">
														<div class="form-group form-float" style="margin-left: 6%">
															<div class="form-line">
																<input type="text" class="form-control" ng-model="remitter.customerfname"/>
																<label class="form-label">Remiiter first name for the wallet</label>
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
										
										
										<div class="col-md-12">
											<div
												class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
												<label for="email_address_2">Remitter Last Name</label>
											</div>
											<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
												<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
													<div class="form-group">
														<div class="form-group form-float" style="margin-left: 6%">
															<div class="form-line">
																<input type="text" class="form-control" ng-model="remitter.customerlname"/>
																<label class="form-label">Remiiter last name for the wallet</label>
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
										<div class="col-md-12">
											<div class="col-lg-offset-6 col-md-offset-6 col-sm-offset-6 col-xs-offset-6">
												<button  type="button" class="btn btn-primary m-t-15 waves-effect" ng-click="RemitterRegisterOPEN(remitter);">Submit</button>
											</div>
										</div>
									</div>
								</form>
							</div>
						</div>
					</div>
					<!------------------------- Remiter Registration Activation -------------------------->
					 <div class="body visibility" id="sdiv1">
						<div class="row clearfix" style="margin-top: 2%">
							<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 table-responsive" >
								<div class="header">
									<h2>Activate User</h2>
								</div>
								<form class="form-horizontal">
									<div class="row clearfix">
										<div class="col-md-12" style="margin-top: 2%">
											<div
												class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
												<label for="email_address_2">Enter OTP </label>
											</div>
											<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
												<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
													<div class="form-group form-float"
														style="margin-left: 0px !important;">
														<div class="form-line">
															<input type="text" class="form-control" ng-model = "remDetails.OTP"/>
														
														</div>
													</div>
												</div>
											</div>
										</div>
										
										
										
										<div class="col-md-12">
											<div class="col-lg-offset-6 col-md-offset-6 col-sm-offset-6 col-xs-offset-6">
												<button  type="button" class="btn btn-primary m-t-15 waves-effect"  ng-click="otpverifyOPEN(remDetails)">Submit</button>
												<!-- <button  type="button" class="btn btn-primary m-t-15 waves-effect"  ng-click="resendOtp(remDetails2)">Resend Otp</button> -->
											</div>
										</div>
									</div>
								</form>
							</div>
						</div>
					</div>
					<!------------------------- Remiter Details -------------------------->
					<div class="body visibility" id="sdiv2" >
						<div class="row clearfix" style="margin-top: 2%">
							<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 table-responsive" >
								<div class="header">
									<h2>Sender Details</h2>
								</div>
								<table id="" class="table table-bordered table-hover">
									<thead>
										<tr style="font-size: 13px; font-weight: bold;background-color: #2196f3;">
											<th style="color: #fff;" class="text-center">REMITTER MOBILE NUMBER</th>
											 <th style="color: #fff;" class="text-center" >KYC STATUS</th>
										 <th class="text-center" ng-if="remitterDetails.otpverified==false">REMITTER VERIFY</th>											
											 
										</tr>
									</thead>
									<tbody>
										<tr>
											<td style="font-size: 15px; text-align: center;">{{remitterDetails.remmobile}}</td>		
											<td style="font-size: 15px; text-align: center;">{{remitterDetails.kycstatus}}</td>																					
											 <td style="font-size: 15px; text-align: center;" ng-if="remitterDetails.otpverified==true"><button ng-click="verifyremitter(remitterDetails);" class="btn ">VERIFY REMITTER</button></td> 
										
										</tr>
									</tbody>
								</table>
							</div>
							<div class="col-md-12 clearfix" style="margin-top: 0%">
								<div class="col-md-6"><h5 class="text-left">Available Limit is Rs {{remitterDetails.Limit}}</h5></div>
								<div class="col-md-6"><h4 class="text-right"><button ng-click="fetchbank();" class="btn ">New Beneficiary</button></h4>
								<!-- <button ng-click="viewdoopmebene();" class="btn ">View Beneficiary</button> -->
								</div>
							</div>
						</div>
						
						<div class="row clearfix" style="margin-top: 0%">
							<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 table-responsive" >
								<div class="header">
									<h2>Beneficiary Details</h2>
								</div>
								<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 table-responsive" ng-if="remitterbeneficiarylist.length > 0" >
									<table id="" class="table table-bordered table-hover">
										<thead>
											<tr style="font-size: 13px; font-weight: bold;background-color: #2196f3;">
												<th style="color: #fff;" class="text-center">BENEFICIARY ID</th>
												<th style="color: #fff;" class="text-center">ACCOUNT NAME</th>
												<th style="color: #fff;" class="text-center">ACCOUNT NUMBER</th>
												<th style="color: #fff;" class="text-center">IFSC CODE</th>
												<th style="color: #fff;" class="text-center">MOBILE</th>
												<th style="color: #fff;" class="text-center">Validation</th>
												<th style="color: #fff;" class="text-center">Delete</th>
												<th style="color: #fff;" class="text-center">Action</th>
											</tr>
										</thead>
										<tbody>
											<tr ng-repeat="benedetails in remitterbeneficiarylist">
												<td style="font-size: 15px; text-align: center;">{{benedetails.id}}</td>
												<td style="font-size: 15px; text-align: center;">{{benedetails.name}}</td>	
												<td style="font-size: 15px; text-align: center;">{{benedetails.account}}</td>
												<td style="font-size: 15px; text-align: center;">{{benedetails.ifsc}}</td>																					
												<td style="font-size: 15px; text-align: center;">{{benedetails.mobile}}</td>
											 	  <td style="font-size: 15px; text-align: center;" ng-if="benedetails.isVerified==false"><button class="btn" ng-click="ValidateBeneficiaryEncore(benedetails,remitterDetails.remitter);">Validate</button><br></td>
											 	<td style="font-size: 15px; text-align: center;" ng-if="benedetails.isVerified==true">Verified<br></td>  
												<!-- <td style="font-size: 15px; text-align: center;" ng-if="benedetails.otpverified==true"><h5>Validated</h5><br></td> -->
												<td style="font-size: 15px; text-align: center;"><button class="btn"  ng-click="ValidateBeneficiaryEncore(benedetails,remitterDetails.remitter);">Validate</button><br></td>
												<td style="font-size: 15px; text-align: center;"><button class="btn" ng-click="deletebeneOPEN(benedetails);">Delete</button><br></td>
												<td style="font-size: 15px; text-align: center;"><button class="btn" ng-click="doopmePaymentdetails(benedetails,remitterDetails.Limit,remitterDetails.kycstatus);">Payment</button><br><!-- <h5> Upto &#x20B9; 20,000/-</h5> --></td>
											</tr>
										</tbody>
									</table>
								</div>								
								<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 table-responsive" ng-if="remitterbeneficiarylist.length <= 0">
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
					
					<div  id="deleteotp" class="modal fade" role="dialog" tabindex="-1" aria-labelledby="modalLabel" aria-hidden="true">
											  <div class="modal-dialog">
											
											     <!-- Modal content-->
											    <div class="modal-content" style="width: 43%;left: 18%;">
											      <div class="modal-header">
											        <button type="button" class="close" data-dismiss="modal">&times;</button>
											      </div>
											      <div class="modal-body">
											      	<div class="row clearfix">
														<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
															<label for="email_address_2">OTP</label>
														</div>
														<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
															<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
																<div class="form-group form-float"
																	style="margin-left: 0px !important;">
																	<div class="form-line">
																		<input type="text" class="form-control" placeholder="Enter OTP" ng-model="popsettlestatus.OTP" />
																	</div>
																</div>
															</div>
														</div>
													</div>
													<div class="row clearfix">
														<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
															<label for="email_address_2">Request Code</label>
														</div>
														<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
															<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
																<div class="form-group form-float"
																	style="margin-left: 0px !important;">
																	<div class="form-line">
																		<input type="text" class="form-control"  ng-model="popsettlestatus.REQUESTNO" readonly="readonly" />
																	</div>
																</div>
															</div>
														</div>
													</div>
											      	<div class="row clearfix">
													<div class="" style="margin-top: 10%">
													<center>	
													  <a class="btn btn-info" ng-click="VerifyDeleteBane(popsettlestatus)"> Submit</a>
													 </center>
													</div>
											      </div>
											     </div>
											     
											    </div>
											
											  </div>
											</div>
		
				<!------------------------- Remiter Details -------------------------->
					<div class="body visibility" id="sdiv3" >
						<div class="row clearfix" style="margin-top: 2%">
							<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 table-responsive" >
								<div class="header">
									<h2>Sender Details</h2>
								</div>
								<div class="body">
									 
									 <div class="col-md-12">
									 	 <div class="widget-body"><div class="widget-main">
											<h4	style="background: #f3f3f3; padding: 10px; border-bottom: 1px solid #ccc;">Submit Verification Details</h4>
												<div class="row" style="margin-top: 15px;">
												<div class="col-md-4 text-center"><label>OTP :</label>
												</div><div class="col-md-5 text-center"><input type="text"  id = "verification_otp_dmr"/>
												<span><input type="button" name="#" value="Resend Otp" onclick = "resendOtp();" class="btn-success"></span>
												</div></div></div>
												<div id = "verificationMsg"></div><div class="row" style="margin-top: 15px; margin-bottom: 15px;">
												<div class="col-md-7"><div class="col-md-offset-6">
												</div><input type = "hidden" ng-model = "impsverify.sessionId" value ="{{impsverify.sessionId}}">
												<input type = "hidden" ng-model = "impsverify.dmr_amount" value ="{{impsverify.dmr_amount}}">
												<input type = "hidden" ng-model = "impsverify.transId" value ="{{impsverify.transId}}">
												<input type = "hidden" ng-model = "impsverify.consumerId" value ="{{impsverify.consumerId}}">
												<input type = "hidden" ng-model ="impsverify.beneficiaryId" value ="{{impsverify.beneficiaryId}}">
												<!-- <input type = "hidden" ng-model ="impsverify." value = ""> -->
												<input type="button" name="#" value="Submit" ng-click="confirmVerification(impsverify);" class="btn-primary">
						                        <input type="button" name="#" value="Cancel" ng-click="cancelbutton();" class="btn-danger">"
												</div></div></div></div>
									 
									 
									 	
									 </div>
								</div>
							</div>
						</div>
					
				<!------------------------- New Beneficiary -------------------------->
					<div class="body visibility" id="sdiv4">
						<div class="row clearfix" style="margin-top: 2%">
							<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 table-responsive" >
								<div class="header">
									<h2>Add New Beneficiary </h2>
								</div>
								<form class="form-horizontal">
									<div class="row clearfix">
										<div class="col-md-12" style="margin-top: 2%">
											<!-- <div
												class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
												<label for="email_address_2">Beneficiary Mobile No: </label>
											</div> -->
											<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
												<div class="form-group from-bord form-float"
													style="margin-left: 0px !important;">
													<label for="email_address_2">Beneficiary Mobile No: </label>
													<div class="form-line">
														<input type="text" class="form-control" ng-model = "bene.beneMobileNumber" />
													</div>
												</div>
											</div>
										
											<!-- <div
												class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
												<label for="email_address_2">Beneficiary Name :</label>
											</div> -->
											<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
												<div class="form-group">
													<div class="form-group from-bord form-float" style="margin-left: 6%">
														<label for="email_address_2">Beneficiary Name :</label>
														<div class="form-line">
															<input type="text" class="form-control" ng-model = "bene.bene_name" />
														</div>
													</div>
												</div>
											</div>
										</div>
										</div>
										<div class="row">
											<div class="col-md-12">
											<!-- <div
												class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
												<label for="email_address_2">Account Number :</label>
											</div> -->
											<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
												<div class="form-group">
													<div class="form-group from-bord form-float" style="margin-left: 15px;">
														<label for="email_address_2">Account Number :</label>
														<div class="form-line">
															<input type="text" class="form-control" id="accnm" ng-model = "bene.accountNumber" />
														</div>
													</div>
												</div>
											</div>
											<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
												<div class="form-group">
													<div class="form-group from-bord form-float" style="margin-left: 6%">
														<label for="email_address_2">Confirm Account Number :</label>
														<div class="form-line">
															<input type="text" class="form-control" id="accnm1" ng-model = "bene.confirmaccountNumber" />
														</div>
													</div>
												</div>
											</div>
											</div>
										</div>
										
										
										<!-- <div class="col-md-12">
											<div
												class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
												<label for="email_address_2">Confirm Account Number :</label>
											</div>
											
										</div> -->
										<div class="row">
										<div class="col-md-12">
											<!-- <div class="col-md-4 col-lg-4 col-sm-4 col-xs-4 form-control-label">
												<label for="rolename">Account Type:</label>
											</div> -->
										    <div class="col-md-6 col-lg-6 col-sm-6 col-xs-6">
									    		<div class="form-group">
													<div class="form-group from-bord form-float" style="margin-left: 15px;">
													<label for="rolename">Account Type:</label>
														<div class="form-line">
													       <select ng-model="bene.acc_type" class="form-control show-tick">
																	<option value="Savings">Savings</option>
																	<option value="Current">Current</option>
															</select>
													    </div>
													</div>
												</div>
										    </div>
										    <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
												<div class="form-group">
													<div class="form-group from-bord form-float" style="margin-left: 6%">
													<label for="email_address_2">Bank IFSC CODE :</label>
														<div class="form-line">
														<input type="text" class="form-control" ng-model = "bene.IFSC_CODE" />
														<!-- <select class="form-control" ng-model = "bene.IFSC_CODE" ng-options="a.IFSC_CODE as a.Bank_Name for a in beneIFSCCode"></select>-->			
														</div>
													</div>
												</div>
											</div>
										</div>
										</div>
										<div class="col-md-12">
											<!-- <div
												class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
												<label for="email_address_2">Bank IFSC CODE :</label>
											</div> -->
										</div>
										<div class="col-md-12">
											<div class="text-right">
												<button type="button" class="btn btn-primary m-t-15 waves-effect" ng-click = "BeneficiaryRegistrationOPEN(bene);">Submit</button>
												<button type="button" onclick="sshowDiv('sdiv2')" class="btn btn-primary m-t-15 waves-effect">Cancel</button>
											</div>
										</div>
									
								</form>
							</div>
						</div>
					</div>
					
				
				
					<!-- <div class="body visibility" id="sdiv4">
						<div class="row clearfix" style="margin-top: 2%">
							<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 table-responsive" >
								<div class="header">
									<h2>Add New Beneficiary </h2>
								</div>
								<form class="form-horizontal">
									<div class="row clearfix">
										<div class="col-md-12" style="margin-top: 2%">
											<div
												class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
												<label for="email_address_2">Beneficiary Mobile No: </label>
											</div>
											<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
												<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
													<div class="form-group form-float"
														style="margin-left: 0px !important;">
														<div class="form-line">
															<input type="text" class="form-control" ng-model = "bene.beneMobileNumber" />
														</div>
													</div>
												</div>
											</div>
										</div>
										<div class="col-md-12">
											<div
												class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
												<label for="email_address_2">Beneficiary Name :</label>
											</div>
											<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
												<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
													<div class="form-group">
														<div class="form-group form-float" style="margin-left: 6%">
															<div class="form-line">
																<input type="text" class="form-control" ng-model = "bene.bene_name" />
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
										<div class="col-md-12">
											<div
												class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
												<label for="email_address_2">Account Number :</label>
											</div>
											<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
												<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
													<div class="form-group">
														<div class="form-group form-float" style="margin-left: 6%">
															<div class="form-line">
																<input type="text" class="form-control" ng-model = "bene.accountNumber" />
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
										
										
										<div class="col-md-12">
											<div
												class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
												<label for="email_address_2">Confirm Account Number :</label>
											</div>
											<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
												<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
													<div class="form-group">
														<div class="form-group form-float" style="margin-left: 6%">
															<div class="form-line">
																<input type="text" class="form-control" ng-model = "bene.confirmaccountNumber" />
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
										<div class="col-md-12">
											<div class="col-md-4 col-lg-4 col-sm-4 col-xs-4 form-control-label">
												<label for="rolename">Account Type:</label>
											</div>
										    <div class="col-md-8 col-lg-8 col-sm-8 col-xs-8 ">
										    	<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
										    		<div class="form-group">
														<div class="form-group form-float" style="margin-left: 6%">
															<div class="form-line">
														       <select ng-model="bene.acc_type" class="form-control show-tick">
																		<option value="Savings">Savings</option>
																		<option value="Current">Current</option>
																</select>
														    </div>
														</div>
													</div>
											     </div>
										    </div>
										</div>
										<div class="col-md-12">
											<div
												class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
												<label for="email_address_2">IFSC CODE :</label>
											</div>
											<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
												<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
													<div class="form-group">
														<div class="form-group form-float" style="margin-left: 6%">
															<div class="form-line">
															<select class="form-control" ng-model = "bene.IFSC_CODE" ng-options="a.IFSC_CODE as a.Bank_Name for a in beneIFSCCode"></select>
															<input type="text" class="form-control" ng-model = "bene.IFSC_CODE" />
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
										<div class="col-md-12">
											<div class="col-lg-offset-6 col-md-offset-6 col-sm-offset-6 col-xs-offset-6">
												<button type="button" class="btn btn-primary m-t-15 waves-effect" ng-click = "addBeneficiaryEncore(bene);">Submit</button>
												<button type="button" onclick="sshowDiv('sdiv2')" class="btn btn-primary m-t-15 waves-effect">Cancel</button>
											</div>
										</div>
									</div>
								</form>
							</div>
						</div>
					</div> -->
					<!------------------------- Verify Beneficiary -------------------------->
					<div class="body visibility" id="sdiv5">
						<div class="row clearfix" style="margin-top: 2%">
							<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 table-responsive" >
								<div class="header">
									<h2>Verify Beneficiary </h2>
								</div>
								<form class="form-horizontal">
									<div class="row clearfix">
									<!-- 	<div class="col-md-12" style="margin-top: 2%">
											<div
												class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
												<label for="email_address_2">Beneficiary Mobile No: </label>
											</div>
											<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
												<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
													<div class="form-group form-float"
														style="margin-left: 0px !important;">
														<div class="form-line">
															<input type="text" class="form-control" ng-model = "beneDetails.MOBILENO" readonly="readonly"/>
														</div>
													</div>
												</div>
											</div>
										</div> -->
									<!-- 	<div class="col-md-12">
											<div
												class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
												<label for="email_address_2">Remitter Id :</label>
											</div>
											<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
												<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
													<div class="form-group">
														<div class="form-group form-float" style="margin-left: 6%">
															<div class="form-line">
																<input type="text" class="form-control" ng-model = "beneDetails.REMID" readonly="readonly"/>
															</div>
														</div>
													</div>
												</div>
											</div>
										</div> -->
										<div class="col-md-12">
											<div
												class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
												<label for="email_address_2">Request No :</label>
											</div>
											<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
												<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
													<div class="form-group">
														<div class="form-group form-float" style="margin-left: 6%">
															<div class="form-line">
																<input type="text" class="form-control" ng-model = "beneDetails.REQUESTNO" readonly="readonly"/>
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
										<div class="col-md-12">
											<div
												class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
												<label for="email_address_2">OTP :</label>
											</div>
											<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
												<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
													<div class="form-group">
														<div class="form-group form-float" style="margin-left: 6%">
															<div class="form-line">
																<input type="text" class="form-control"  ng-model = "beneDetails.OTP"/>
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
										<div class="col-md-12">
											<div class="col-lg-offset-6 col-md-offset-6 col-sm-offset-6 col-xs-offset-6">
												<button type="button" class="btn btn-primary m-t-15 waves-effect" ng-click = "yesBankVerifyBeneficiary(beneDetails);">Submit</button>
												<button type="button" onclick="sshowDiv('sdiv2')" class="btn btn-primary m-t-15 waves-effect">Cancel</button>
											</div>
										</div>
									</div>
								</form>
							</div>
						</div>
					</div>
					<!------------------------- Payment For Beneficiary -------------------------->
					
					<div class="body visibility" id="sdiv6">
						<div class="row clearfix" style="margin-top: 2%">
							<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 table-responsive" >
								<div class="header">
									<h2>Submit Payment Details </h2>
								</div>
								<form class="form-horizontal">
									<div class="row clearfix">
									
										 <div class="col-md-12 " style="margin-top: 2%;display: none;">
											<div
												class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
												<label for="email_address_2">Beneficiary Id : </label>
											</div>
											<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
												<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
													<div class="form-group form-float"
														style="margin-left: 0px !important;">
														<div class="form-line">
															<input type="text" class="form-control" ng-model="bundlepayDetails.id" id="" readonly="readonly"/>
														</div>
													</div>
												</div>
											</div>
										</div> 
									
									<div class="row mrt-20px">	
										 <div class="col-md-12">
											<!-- <div
												class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
												<label for="email_address_2">Mobile No:</label>
											</div> -->
											<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
												<div class="form-group">
													<div class="form-group from-bord form-float" style="margin-left: 15px;">
														<label for="email_address_2">Mobile No:</label>
														<div class="form-line pa-from">
															<input type="text" class="form-control" ng-model="bundlepayDetails.mobile" id="" readonly="readonly"/>
														</div>
													</div>
												</div>
											</div>
											<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
												<div class="form-group">
													<div class="form-group from-bord form-float" style="margin-left: 15px;">
													<label for="email_address_2">Beneficiary Name:</label>
														<div class="form-line pa-from">
															<input type="text" class="form-control" ng-model="bundlepayDetails.name" id="" readonly="readonly"/>
														</div>
													</div>
												</div>
											</div>
										</div> 
									</div>
									
										<div class="row">
							            	<div class="col-md-12">
											
												<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
													<div class="form-group">
														<div class="form-group from-bord form-float" style="margin-left: 15px;">
														<label for="email_address_2">Account Number :</label>
															<div class="form-line pa-from">
																<input type="text" class="form-control" ng-model="bundlepayDetails.account" id="" readonly="readonly"/>
															</div>
														</div>
													</div>
												</div>
												<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
													<div class="form-group">
														<div class="form-group from-bord form-float" style="margin-left: 15px;">
														<label for="email_address_2">Account Type :<sup class="col-red">*</sup></label>
															<div class="form-line pa-from">
																<select ng-model="bundlepayDetails.accountType" class="form-control show-tick">
																		<option value="">Select Account Type</option>
																		<option value="Savings">Savings</option>
																		<option value="Current">Current</option>
																</select>
															</div>
														</div>
													</div>
												</div>
											</div> 
										</div>
									
										<div class="row">
										 	<div class="col-md-12">
											
												<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
												<div class="form-group">
													<div class="form-group from-bord form-float" style="margin-left: 6%;">
													<label for="email_address_2">Transaction Amount:<sup class="col-red">*</sup></label>
														<div class="form-line pa-from">
															<input type="text" class="form-control" ng-model="bundlepayDetails.amount" id="" ng-keypress = "filterValue($event);"/>
														</div>
													</div>
												</div>
											</div>
												<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
													<div class="form-group">
														<div class="form-group from-bord form-float" style="margin-left: 15px;">
															<label for="email_address_2">IFSC Code :</label>
															<div class="form-line pa-from">
																<input type="text" ng-model="bundlepayDetails.ifsc" class="form-control" id="" readonly="readonly" />
															</div>
														</div>
													</div>
												</div>
											</div>
											</div> 
											
											<div class="row">
										 	<div class="col-md-12">
											
												<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
												<div class="form-group">
													<div class="form-group from-bord form-float" style="margin-left: 6%;">
													<label for="email_address_2">Transfer Type :<sup class="col-red">*</sup></label>
														<div class="form-line pa-from">
															<select ng-model="bundlepayDetails.sendType" class="form-control show-tick">
																		<option value="0">Select Transaction Type</option>
																		<option value="4">IMPS</option>
																		<option value="2">NEFT</option>
																</select>
														</div>
													</div>
												</div>
											</div>
											
											</div>
											</div> 
									
									
										
										<div class="col-md-12">
											<div class="col-lg-offset-8 col-md-offset-8 col-sm-offset-8 col-xs-offset-8">
												<button type="button" ng-click="MoneytransferOPEN(bundlepayDetails);" class="btn btn-primary m-t-15 waves-effect" >PAY</button>
<!-- 												<button type="button" ng-click="MoneytransferPAYTM(bundlepayDetails);" class="btn btn-primary m-t-15 waves-effect" >PAY DMT2</button> -->
												<button type="button" onclick="sshowDiv('sdiv2')" ng-click="doopmecancel();" class="btn btn-danger m-t-15 waves-effect">Cancel</button>
											
												<!-- " -->
											</div>
										</div>
										</div>
								</form>
							</div>
						</div>
					</div>
					
					
					<!-- <div class="body visibility" id="sdiv6">
						<div class="row clearfix" style="margin-top: 2%">
							<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 table-responsive" >
								<div class="header">
									<h2>Submit Payment Details </h2>
								</div>
								<form class="form-horizontal">
									<div class="row clearfix">
										<div class="col-md-12 " style="margin-top: 2%;display: none;">
											<div
												class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
												<label for="email_address_2">Beneficiary Id : </label>
											</div>
											<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
												<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
													<div class="form-group form-float"
														style="margin-left: 0px !important;">
														<div class="form-line">
															<input type="text" class="form-control" ng-model="bundlepayDetails.id" id="" readonly="readonly"/>
														</div>
													</div>
												</div>
											</div>
										</div>
										<div class="col-md-12">
											<div
												class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
												<label for="email_address_2">Mobile No:</label>
											</div>
											<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
												<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
													<div class="form-group">
														<div class="form-group form-float" style="margin-left: 6%">
															<div class="form-line">
																<input type="text" class="form-control" ng-model="bundlepayDetails.mobile" id="" readonly="readonly"/>
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
										<div class="col-md-12">
											<div
												class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
												<label for="email_address_2">Beneficiary Name:</label>
											</div>
											<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
												<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
													<div class="form-group">
														<div class="form-group form-float" style="margin-left: 6%">
															<div class="form-line">
																<input type="text" class="form-control" ng-model="bundlepayDetails.name" id="" readonly="readonly"/>
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
										<div class="col-md-12">
											<div
												class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
												<label for="email_address_2">Account Number :</label>
											</div>
											<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
												<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
													<div class="form-group">
														<div class="form-group form-float" style="margin-left: 6%">
															<div class="form-line">
																<input type="text" class="form-control" ng-model="bundlepayDetails.account" id="" readonly="readonly"/>
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
										<div class="col-md-12">
											<div
												class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
												<label for="email_address_2">Account Type :<sup class="col-red">*</sup></label>
											</div>
											<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
												<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
													<div class="form-group">
														<div class="form-group form-float" style="margin-left: 6%">
															<div class="form-line">
																<select ng-model="bundlepayDetails.accountType" class="form-control show-tick">
																		<option value="">Select Account Type</option>
																		<option value="Savings">Savings</option>
																		<option value="Current">Current</option>
																</select>
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
										<div class="col-md-12">
											<div
												class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
												<label for="email_address_2">IFSC Code :</label>
											</div>
											<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
												<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
													<div class="form-group">
														<div class="form-group form-float" style="margin-left: 6%">
															<div class="form-line">
																<input type="text" ng-model="bundlepayDetails.ifsc" class="form-control" id="" readonly="readonly" />
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
										<div class="col-md-12">
											<div
												class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
												<label for="email_address_2">Transaction Amount:<sup class="col-red">*</sup></label>
											</div>
											<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
												<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
													<div class="form-group">
														<div class="form-group form-float" style="margin-left: 6%">
															<div class="form-line">
																<input type="text" class="form-control" ng-model="bundlepayDetails.amount" id="" ng-keypress = "filterValue($event);"/>
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
										<div class="col-md-12">
											<div
												class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
												<label for="email_address_2">Transfer Type:<sup class="col-red">*</sup></label>
											</div>
											<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
												<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
													<div class="form-group">
														<div class="form-group form-float" style="margin-left: 6%">
															<div class="form-line">
																<select ng-model="bundlepayDetails.transfertype" class="form-control show-tick">
																		<option value="">Select Transfer Type</option>
																		<option value="IMPS">IMPS</option>
																		<option value="NEFT">NEFT</option>
																</select>
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
										
										<div class="col-md-12">
											<div class="col-lg-offset-6 col-md-offset-6 col-sm-offset-6 col-xs-offset-6">
												<button type="button" ng-click="impsMoneyTransferEncore(bundlepayDetails);" class="btn btn-primary m-t-15 waves-effect" >Submit</button>
												<button type="button" ng-click="doopmecancel();" class="btn btn-danger m-t-15 waves-effect">Cancel</button>
											
												"
											</div>
										</div>
									</div>
								</form>
							</div>
						</div>
					</div> -->
					
			<!-- --------------------------------- Payment Status Div------------------------------------ -->
				</div>
			</div>
		</div>
		<!-------------- /Div1 ---------------------->
		<!-------------- Div2 ---------------------->
		
		<!-------------- /Div34 ---------------------->
		
		
		<div  id="refundotp" class="modal fade" role="dialog" tabindex="-1" aria-labelledby="modalLabel" aria-hidden="true">
											  <div class="modal-dialog">
											
											     <!-- Modal content-->
											    <div class="modal-content" style="width: 43%;left: 18%;">
											      <div class="modal-header">
											        <button type="button" class="close" data-dismiss="modal">&times;</button>
											      </div>
											      <div class="modal-body">
											      	<div class="row clearfix">
														<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
															<label for="email_address_2">OTP</label>
														</div>
														<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
															<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
																<div class="form-group form-float"
																	style="margin-left: 0px !important;">
																	<div class="form-line">
																		<input type="text" class="form-control" placeholder="Enter OTP" ng-model="poprefund.OTP" />
																	</div>
																</div>
															</div>
														</div>
													</div>
													<div class="row clearfix">
														<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
															<label for="email_address_2">Request Code</label>
														</div>
														<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
															<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
																<div class="form-group form-float"
																	style="margin-left: 0px !important;">
																	<div class="form-line">
																		<input type="text" class="form-control"  ng-model="poprefund.REQUESTNO" readonly="readonly" />
																	</div>
																</div>
															</div>
														</div>
													</div>
											      	<div class="row clearfix">
													<div class="" style="margin-top: 10%">
													<center>	
													  <a class="btn btn-info" ng-click="refundOTP(refunddetails,poprefund)"> Submit</a>
													 </center>
													</div>
											      </div>
											     </div>
											     
											    </div>
											
											  </div>
											</div>
		
	
	</div>
	</section>
	
<script src="assets/js/angular.min.js"></script>
	<script src="assets/js/bootstrap.js"></script>
	<script src="assets/js/autocompletedata.js"></script>
	<script src="assets/js/jquery-ui.js"></script>
	
	<script src="assets/js/bootstrap-datepicker.min.js"></script>
	<script src="assets/js/chosen.jquery.js"></script>
	<script src="assets/js/jquery.slimscroll.js"></script>
	<script src="assets/js/jquery.countTo.js"></script>
	<script src="assets/js/raphael.min.js"></script>
	<script src="assets/js/basic-form-elements.js"></script>
	<script src="assets/js/jquery.scrolling-tabs.js"></script>
	<script src="assets/js/angular-sanitize.min.js"></script>
	<script src="assets/js/cookieStore.js"></script>
	<script src="assets/js/alasql.min.js?version=1.0.0.2"></script>
	<script src="assets/js/xlsx.core.min.js?version=1.0.0.2"></script>
	<script src="assets/scripts/angular_openDmr.min.js?version=1.1.0"></script>
	<script src="assets/js/admin.js"></script>
	<script src="assets/js/pages/index.js"></script>
	<script src="assets/js/demo.js"></script>
	<script src="assets/js/datepicker-date.js"></script>
	<script src="assets/js/stellarnav.min.js"></script>
	
	
	<script type="text/javascript">
		    jQuery(document).ready(function($) {
		      jQuery('.stellarnav').stellarNav({
		        theme: 'dark',
		        breakpoint: 960,
		        position: 'right',
		        phoneBtn: '18009997788',
		        locationBtn: 'https://www.google.com/maps'
		      });
		    });
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
		function showPancard(divId) {
			var div = "#" + divId;
			$('div[id^=div]').hide();
			$(div).slideToggle(600).show();
		}
	
	</script>
	
	<script type="text/javascript">
		$(function() {
		    $('.multiselect-ui').multiselect({
		        includeSelectAllOption: true
		    });
		    $(".multiselect-ui").multiselect('selectAll', false);
		    $(".multiselect-ui").multiselect('updateButtonText');
		});
	</script>
	
	<script type="text/javascript">
		$(document).ready(function() {
			
			$("#payLandline").click(function() {
				$("#mobile").hide();
				$("#landline").show();

			});
			$("#payMobile").click(function() {
				$("#landline").hide();
				$("#mobile").show();

			});

			$(".imps-triger").click(function() {
				$("#sideSticker").hide();
				$(".imps-div").removeClass("col-lg-8", "col-md-8");
				$(".imps-div").addClass("col-lg-12", "col-md-12");
				$(".scrtabs-tabs-fixed-container").css({
					"width" : "96%"
				});

			});

			$(".shortcut").click(function() {
				$("#sideSticker").show();
				$(".imps-div").removeClass("col-lg-12", "col-md-12");
				$(".imps-div").addClass("col-lg-8", "col-md-8");
				$(".scrtabs-tabs-fixed-container").css({
					"width" : "93%"
				});
			});
			
			$(".oneWay").change(function(){
				$(".dp21").attr("disabled", "disabled");
			});
			
			$(".roundTrip").change(function(){
				$(".dp21").removeAttr("disabled");
			});
			

		});
		function tabShowHide(firstTab, secondTab){
			
			$("#"+firstTab).hide();
			$("#"+secondTab).show();
		}
	</script>
	<script type="text/javascript">
		$(function toggleChevron(e) {
			$(e.target)
			.prev('.panel-heading')
			.find("i.indicator")
			.toggleClass('fa-caret-down fa-caret-right');
		$('#accordion').on('hidden.bs.collapse', toggleChevron);
		$('#accordion').on('shown.bs.collapse', toggleChevron);
		});
	</script>
	
	<script type="text/javascript">
		function showFairDetails(flightDetail) {
			
			var div = "."+flightDetail; 
			$(div).toggle();
			
		}
	</script>
	<script type="text/javascript">
	$.widget("ui.tooltip", $.ui.tooltip, {
	    options: {
	        content: function () {
	            return $(this).prop('title');
	        }
	    }
	});

	$(function () {
	    $('.item1').attr('title', function(){
	        return $(this).next('.statusRollup').remove().html()
	    })
	    $(document).tooltip();  
	});
	
	
	
	
	
	
	</script>
</body>
</html>