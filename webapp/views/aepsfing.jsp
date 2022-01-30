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
<title>Admin</title>
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
<link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" crossorigin="anonymous">
<link href="assets/css/chosen.css" rel="stylesheet" />
<link href="assets/css/bootstrap-datepicker.min.css" rel="stylesheet" />
<link href="assets/css/responsive.css" rel="stylesheet" />
<link href="assets/css/sweetalert.css" rel="stylesheet" />
<link href="assets/css/newstyle.css" rel="stylesheet" />
<link href="assets/css/stellarnav.min.css" rel="stylesheet" />
<link href="assets/css/all.min.css" rel="stylesheet" />
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

</style>
<script type="text/javascript">

$(document).ready(function(){
	$("#withdrawDiv").val("WITHDRAWAL");
});

function checkMode(val){
	if(val==1){
		//$("#mydropdownlist").val("thevalue");
		$("#withdrawDiv").val("WITHDRAWAL");
		$("#balEnquiryDiv").val("");
		$("#miniEnquiryDiv").val("");
		
	}else if(val==2){
		$("#balEnquiryDiv").val("Balace Enquiry");
		$("#withdrawDiv").val("");
		$("#miniEnquiryDiv").val("");
	}else{
		$("#balEnquiryDiv").val("");
		$("#withdrawDiv").val("");
		$("#miniEnquiryDiv").val("Mini Enquiry");
	}
}

function checkWithdraw(val) {
	  //document.getElementById("answer").value=browser;
	if(val=='others'){
		$('#bankDiv').css("display","block");
		$('#balancebankDiv').css("display","none");
	}else{
		$('#bankDiv').css("display","none");
		$('#balancebankDiv').css("display","none");
	}
	}
	
function checkBalance(val) {
	  //document.getElementById("answer").value=browser;
	if(val=='others'){
		$('#balancebankDiv').css("display","block");
		$('#bankDiv').css("display","none");
	}else{
		$('#balancebankDiv').css("display","none");
		$('#bankDiv').css("display","none");
	}
	}

//var val=$("#radioDiv input[type='radio']:checked").val();



</script>
</head>
<body class="{{reseller.theme}}" ng-app = "app" ng-controller = "appController">
	<!-- Page Loader -->
	<div class="page-loader-wrapper" ng-show="loader" >
		<div class="loader">
			<div class="preloader"><img alt="" src="assets/images/l3.gif"></div>
		</div>
	</div>
	<div class="page-loader-wrapper" id="myloader" style="display: none;">
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
				<button type="button" class="btn btn-info waves-effect close-search1" onClick="showDiv('div12')" ng-click = "AdvancedSearchUser(searchUser)">Submit</button>
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
				<button type="button" class="btn btn-info waves-effect close-search1" ng-click = "AdvancedCustomerNo(searchUser)" onClick="showDiv('div29')">Submit</button>
			</div>
		</div>
		<div class="close-search">
			<i class="material-icons">close</i>
		</div>
	</div>	
	 
	 <!-- Top Bar -->
	<%-- <div class="container-fluid navbar1 navbar" style="box-shadow: 0 2px 10px rgba(0, 0, 0, 0.2);">
	<div class="row">
	<div class="col-md-1"> 
		<!-- <a class="navbar-brand " href="home"><img class="logo" alt="" src="assets/images/logo.png" ></a> -->
		<a class="navbar-brand " href="home"><img class="logo" id="logo" src = ""></a>
	</div>
	<div class="col-md-11">
		<div class="stellarnav">
			<ul>
			  	<li><a href="home"> <i class="material-icons">home</i><span>Home</span></a></li>
				
				<li><a onClick="showDiv('div109')" ng-click="viewuserbankdt();"><i class="material-icons">content_copy</i> <span>UserBank Details</span></a></li>
				<li><a href="flightsearch"><i class="fas fa-fighter-jet font_i"></i> <span>Flight</span></a></li>
				<li><a href="hotel"> <i class="fas fa-utensils font_i"></i> <span>Hotel</span></a></li>						
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
	</div> --%>
	<section class="content enco_content" style="margin: 15px 15px 0px 15px;background-image: url('assets/images/17.jpg');background-size: cover;background-repeat: no-repeat;">
		<div class="container-fluid" style="padding-left: 0px; padding-right: 0px;">
		<!-- Widgets -->
		<!----------------- Div0 ----------->
		
		<!-- CPU Usage -->
		<!-- <div class="row clearfix" style="margin-left: 0px; margin-right: 0px;"  id="div0" >
			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 customIcon">
				<div class="card">
					<div class="header">
						<div class="row clearfix">
							<div class="col-xs-12 col-sm-6">
								<h2>AEPS Service</h2>
							</div>
						</div>
					</div>
					<div class="body">
						<div class="body">
							<form class="form-horizontal">
								
								<div class="row clearfix">
									<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label text-right">
										<label for="email_address_2">Mobile Number</label>
									</div>
									<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
										<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 m-l-20" style="margin-left: 4%;">
											<div class="form-group">
												<div class="form-group form-float">
													<div class="form-line">
														<input type="text" ng-model="customer.mobile" class="form-control" placeholder="Enter Mobile Number" />
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
								
								<div class="row clearfix">
									<div class="text-center">
										<button type="button" class="btn btn-primary m-t-15 waves-effect" style="width: 20%" ng-click="searchCustomer(customer,yesbankdetail.yesbankaccesstoken)">Submit</button>
									</div>
								</div>	
							</form>
						</div>
					</div>
				</div>
			</div>
		</div> -->
		<!-- #END# CPU Usage -->

		<!-------------- Div2 ---------------------->
		<div id="div1" class="row clearfix" style="margin-left: 0px; margin-right: 0px;" >
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<div class="card_bg">
					<div class="header">
					</div>
					<div class="body">
						<div class="body my_aeps">
							<ul class="nav ad_tb_new nav-tabs">
							  <li class="active checktab" ><a data-toggle="tab" href="#mobile-recharge" onclick="checkMode(1)">Cash Withdrawal</a></li>
							  <li class="checktab"><a data-toggle="tab" href="#menu1" onclick="checkMode(2)">Balance Enquiry</a></li>
							  <!-- <li class="checkmini"><a data-toggle="tab" href="#menu2" onclick="checkMode(3)"> ICICI MINISTATEMENT</a></li> -->
							</ul>
							<div class="tab-content" style="background: none;">
								<div role="tabpanel" class="tab-pane  active" id="mobile-recharge">
									<h4 style="margin-bottom: 3%;color: #f39521;">
										<b>Cash Withdrawal</b>
									</h4>
									<div class="rowfdd clearfix">
										<form class="form-horizontal">
											<div class="row clearfix">
												<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 mt_20">
													<label class="lbl_text" for="email_address_2">Select Device Type</label>									
													<select class="form-control" ng-model="transactionReq.deviceType"  >
													<option value="0">Select Device Type</option>
														<option value="MORPHO_PROTOBUF">Morpho</option>
														<option value="MANTRA_PROTOBUF">Mantra</option>
														<option value="STARTEK_PROTOBUF">Startek</option>
													</select>
												</div>
												<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 mt_20">
													<label class="lbl_text" for="email_address_2">Select Mode</label>									
													<select class="form-control" ng-model="transactionReq.mode" ng-change="selecttransaction(transactionReq.mode)">
														<option value="0">Select Action</option>
														<option value="Balace Enquiry">Balance Enquiry</option>
														<option value="WITHDRAWAL">Withdrawal</option>
													</select>
												</div>
												<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 mt_20">	
													<label class="lbl_text" for="email_address_2">Select Your Bank</label>								
													<select class="form-control" ng-model="transactionReq.iin">
														<option value="0">Select Bank</option>
														<option value="{{aeps.iin}}" ng-repeat="aeps in yesbankdetail.AEPSBankList">{{aeps.bank_name}} </option>
														
													</select>
												</div>
											</div>	
											<input type="hidden" id="withdrawDiv"  ng-model="transactionReq.mode">
											<div class="row clearfix">
												<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 mt_20">
													<label class="lbl_text" for="email_address_2">Enter Mobile</label>
													<input type="text" class="form-control" placeholder="Enter Mobile" ng-model="transactionReq.mobile"/>
												</div>
												<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 mt_20">
													<label class="lbl_text" for="email_address_2">Enter Aadhar Number</label>
													<input type="text" class="form-control" placeholder="Enter Aadhar Number" ng-model="transactionReq.aadhar"/>
												</div>
												<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 mt_20">
													<label class="lbl_text" for="email_address_2">Enter Amount</label>
													<input type="text" class="form-control" placeholder="Enter Amount" id="transamnt" ng-model="transactionReq.amount"/>
												</div>
												
											</div>
											<input type="hidden" ng-model="transactionReq.pidData">
											<div class="row clearfix">
												<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 mt_20">
													<label class="lbl_text" for="email_address_2">Mobile</label>
													<input type="text" class="form-control" ng-model="transactionReq.mobile" placeholder="Enter Amount"/>
												</div>
												<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 mt_20">
													<label class="lbl_text" for="email_address_2">Select Type</label>
													<select class="form-control">
														<option value="">AEPS</option>
														<option value=""> AADHAR PAY</option>
													</select>
												</div>
											</div>
											
										<div class="row clearfix" id="capture" style="display: none;">	
											<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 col-lg-offset-2 col-md-offset-2 " style="margin-left: 12%;">							
												<div class='row-fluid'>
													<div class='span8'>
														<div class='control-group'>
															<div class='controls'>
																<div class='input-prepend'>
																	<h5 style='color:red;'>Fingure Captured successfully.</h5>
																</div>
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
										<div class="row clearfix" id="errInfo" style="display: none;">	
											<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 col-lg-offset-2 col-md-offset-2 " style="margin-left: 12%;">							
												<div class='row-fluid'>
													<div class='span8'>
														<div class='control-group'>
															<div class='controls'>
																<div class='input-prepend'>
																	<h5 style='color:red;'>Error Occurs</h5>
																</div>
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
										
										<div class="row clearfix" id="errnewInfo" style="display: none;">	
											<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 col-lg-offset-2 col-md-offset-2 " style="margin-left: 12%;">							
												<div class='row-fluid'>
													<div class='span8'>
														<div class='control-group'>
															<div class='controls'>
																<div class='input-prepend'>
																	<h5 style='color:red;'>ERR_CONNECTION_REFUSED</h5>
																</div>
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
											<div class="row clearfix">
												<div class="text-center">
													<button type="button" class="btn btn-primary m-t-15 waves-effect" style="width: 15%;" ng-click = "callDevice(transactionReq.deviceType)"><i class="material-icons" style="color: #fff !important;font-size: 40px">fingerprint</i><br> Capture Finger Print</button>
												</div>
											</div>
											<div class="row clearfix">
												<div class="text-center">
													<button type="button" class="btn btn-primary m-t-15 waves-effect" style="width: 20%;" ng-click = "iciciBankTransaction(transactionReq)">Submit</button>
												</div>
											</div>	
										</form>
									</div>
								</div>
								<div role="tabpanel" class="tab-pane" id="menu1">
									<h4 style="margin-bottom: 3%;color: #f39521;">
										<b>Balance Enquiry</b>
									</h4>
									<div class="rowdf clearfix">
										<form class="form-horizontal">
											<div class="row clearfix mt_20">
												<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
													<label for="email_address_2">Select Device Type</label>									
													<select class="form-control" ng-model="transactionReq.deviceType"  >
													<option value="0">Select Device Type</option>
														<option value="MORPHO_PROTOBUF">Morpho</option>
														<option value="MANTRA_PROTOBUF">Mantra</option>
														<option value="STARTEK_PROTOBUF">Startek</option>
													</select>
												</div>
												<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
													<label for="email_address_2">Select Your Bank</label>									
													<select class="form-control" ng-model="transactionReq.iin">
														<option value="0">Select Bank</option>
														<option value="{{aeps.iin}}" ng-repeat="aeps in yesbankdetail.AEPSBankList">{{aeps.bank_name}} </option>
														
													</select>
												</div>
												<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
													<label for="email_address_2">Enter Mobile</label>
													<input type="text" class="form-control" placeholder="Enter Mobile" ng-model="transactionReq.mobile"/>
												</div>
												
											</div>	
											<div class="row clearfix mt_20">
												<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
													<label for="email_address_2">Enter Aadhar Number</label>
													<input type="text" class="form-control" placeholder="Enter Aadhar Number" ng-model="transactionReq.aadhar"/>
												</div>
												<!-- <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
													<label for="email_address_2">Enter Amount</label>
													<input type="text" class="form-control" placeholder="Enter Amount" id="transamnt" ng-model="transactionReq.amount"/>
												</div> -->
											</div>
											<input type="hidden" ng-model="transactionReq.pidData">
											
										<div class="row clearfix" id="capture" style="display: none;">	
											<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 col-lg-offset-2 col-md-offset-2 " style="margin-left: 12%;">							
												<div class='row-fluid'>
													<div class='span8'>
														<div class='control-group'>
															<div class='controls'>
																<div class='input-prepend'>
																	<h5 style='color:red;'>Fingure Captured successfully.</h5>
																</div>
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
										<div class="row clearfix" id="errInfo" style="display: none;">	
											<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 col-lg-offset-2 col-md-offset-2 " style="margin-left: 12%;">							
												<div class='row-fluid'>
													<div class='span8'>
														<div class='control-group'>
															<div class='controls'>
																<div class='input-prepend'>
																	<h5 style='color:red;'>Error Occurs</h5>
																</div>
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
										
										<div class="row clearfix" id="errnewInfo" style="display: none;">	
											<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 col-lg-offset-2 col-md-offset-2 " style="margin-left: 12%;">							
												<div class='row-fluid'>
													<div class='span8'>
														<div class='control-group'>
															<div class='controls'>
																<div class='input-prepend'>
																	<h5 style='color:red;'>ERR_CONNECTION_REFUSED</h5>
																</div>
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
											<div class="row clearfix">
												<div class="text-center">
													<button type="button" class="btn btn-primary m-t-15 waves-effect" style="width: 15%;" ng-click = "callDevice(transactionReq.deviceType)"><i class="material-icons" style="color: #fff !important;font-size: 40px">fingerprint</i><br> Capture Finger Print</button>
												</div>
											</div>
											<div class="row clearfix">
												<div class="text-center">
													<button type="button" class="btn btn-primary m-t-15 waves-effect" style="width: 20%;" ng-click = "iciciaadharBankTransaction(transactionReq)">Submit</button>
												</div>
											</div>	
										</form>
									</div>
								</div>
							</div>
						</div>
					</div>
				
				</div>
			</div>
		</div>
		<!-------------- /Div2 ---------------------->

		
		<!-------------- Div2 ---------------------->
		<div id="div2" class="row clearfix visibility" style="margin-left: 0px; margin-right: 0px;" >
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<div class="card">
					<div class="header">
						<h2>AEPS Receipt <span class="pull-right" style="padding-right: 6%"><a href="javascript:void(0);" onclick="showDiv('div1');"> <i class="fa fa-reply"> </i> Back</a></span> </h2>
					</div>
					<div class="body">
						<div class="body">
							<div class="row" >
						        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" id="aepsrecipetdiv">
						            <div style="width: 100%; margin: 0 auto; border: 1px #999 solid; border-radius: 10px;padding: 2%;">
						                <table class="table table-bordered table-responsive " style="width: 100%;">
						
						                    <tbody>
						                        <tr>
						                            <td>Date & Time: </td>
						                            <td>{{EncoreAEPSResponse.date}} {{EncoreAEPSResponse.time}}</td>
						                        </tr>
						                       <!--  
						                        <tr>
						                            <td>Agent ID:</td>
						                            <td>{{EncoreAEPSResponse.agentcode}}</td>
						                        </tr> -->
						                        <tr>
						                            <td>BC Name:</td>
						                            <td><%=user.getName() %></td>
						                        </tr>
						                        <tr>
						                            <td>BC Location:</td>
						                            <td><%=user.getAddress() %></td>
						                        </tr>
						                       <!--  <tr>
						                            <td>Aadhaar No:</td>
						                            <td>{{EncoreAEPSResponse.Aadhar}}</td>
						                        </tr> -->
						                        <tr>
						                            <td>RRN:</td>
						                            <td>{{EncoreAEPSResponse.bankRRN}}</td>
						                        </tr>
						                     <!--    <tr>
						                            <td>STAN:</td>
						                            <td>{{yesBankResponse.STAN}}</td>
						                        </tr> -->
						                       
						                       
						                      <!--   <tr>
						                            <td>Response Message::</td>
						                            <td>{{yesBankResponse.RESPONSE}}</td>
						                        </tr> -->
						                        <tr>
						                            <td>Txn Amt:</td>
						                            <td>{{EncoreAEPSResponse.transactionAmount}}</td>
						                        </tr>
						                        <tr>
						                            <td>A/c bal:</td>
						                            <td>{{EncoreAEPSResponse.balanceAmount}}</td>
						                        </tr>
						                        
						                          <tr>
						                            <td>Reference No:</td>
						                            <td>{{EncoreAEPSResponse.EncoreTransactionId}}</td>
						                        </tr>
						                        
						                       
						                        <tr>
						                            <td>Response : </td>
						                            <td>{{EncoreAEPSResponse.message}}</td>
						                        </tr>
						                        
						                          <tr>
						                            <td>Status : </td>
						                            <td>{{EncoreAEPSResponse.transactionStatus}}</td>
						                        </tr>
						                    </tbody>
						                </table>
						            </div>
						        </div>
						        
						    </div>
						    
						    <div class="row" >
						        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center" >
						        	<button class="btn btn-primary" id="aepsrecipet" style="width: 20%"><i class="material-icons" style="color: #fff !important;font-size: 20px">print</i><br> Print Receipt </button>
						        </div>
						      </div>
						</div>
					</div>
				
				</div>
			</div>
		</div>
		
		<div id="div02" class="row clearfix visibility" style="margin-left: 0px; margin-right: 0px;" >
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<div class="card">
					<div class="header">
						<h2>AEPS Receipt <span class="pull-right" style="padding-right: 6%"><a href="javascript:void(0);" onclick="showDiv('div1');"> <i class="fa fa-reply"> </i> Back</a></span> </h2>
					</div>
					<div class="body">
						<div class="body">
							<div class="row" >
						        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" id="aepsrecipetdiv">
						            <div style="width: 100%; margin: 0 auto; border: 1px #999 solid; border-radius: 10px;padding: 2%;">
						                <table class="table table-bordered table-responsive " style="width: 100%;">
						
						                    <tbody>
						                        <tr>
						                            <td>Date & Time: </td>
						                            <td>{{EncoreAEPSResponse.date}} {{EncoreAEPSResponse.time}}</td>
						                        </tr>
						                       <!--  
						                        <tr>
						                            <td>Agent ID:</td>
						                            <td>{{EncoreAEPSResponse.agentcode}}</td>
						                        </tr> -->
						                        <tr>
						                            <td>BC Name:</td>
						                            <td><%=user.getName() %></td>
						                        </tr>
						                        <tr>
						                            <td>BC Location:</td>
						                            <td><%=user.getAddress() %></td>
						                        </tr>
						                       <!--  <tr>
						                            <td>Aadhaar No:</td>
						                            <td>{{EncoreAEPSResponse.Aadhar}}</td>
						                        </tr> -->
						                        <tr>
						                            <td>RRN:</td>
						                            <td>{{EncoreAEPSResponse.bankRRN}}</td>
						                        </tr>
						                     <!--    <tr>
						                            <td>STAN:</td>
						                            <td>{{yesBankResponse.STAN}}</td>
						                        </tr> -->
						                       
						                       
						                      <!--   <tr>
						                            <td>Response Message::</td>
						                            <td>{{yesBankResponse.RESPONSE}}</td>
						                        </tr> -->
						                        <tr>
						                            <td>Txn Amt:</td>
						                            <td>{{EncoreAEPSResponse.transactionAmount}}</td>
						                        </tr>
						                        <tr>
						                            <td>A/c bal:</td>
						                            <td>{{EncoreAEPSResponse.balanceAmount}}</td>
						                        </tr>
						                        
						                          <tr>
						                            <td>Reference No:</td>
						                            <td>{{EncoreAEPSResponse.EncoreTransactionId}}</td>
						                        </tr>
						                        
						                       
						                        <tr>
						                            <td>Response : </td>
						                            <td>{{EncoreAEPSResponse.message}}</td>
						                        </tr>
						                        
						                          <tr>
						                            <td>Status : </td>
						                            <td>{{EncoreAEPSResponse.transactionStatus}}</td>
						                        </tr>
						                    </tbody>
						                </table>
						            </div>
						        </div>
						        
						    </div>
						    
						    <div class="row" >
						        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center" >
						        	<button class="btn btn-primary" id="aepsrecipet" style="width: 20%"><i class="material-icons" style="color: #fff !important;font-size: 20px">print</i><br> Print Receipt </button>
						        </div>
						      </div>
						</div>
					</div>
				
				</div>
			</div>
		</div>
		
		<div class="row clearfix visibility" style="margin-left: 0px; margin-right: 0px;"  id="div03" >
			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 customIcon">
				<div class="card">
					<div class="header">
						<div class="row clearfix">
							<div class="col-xs-12 col-sm-6">
								<h2>AEPS Customer Registration</h2>
							</div>
						</div>
					</div>
					<div class="body">
						<div class="body">
							<form class="form-horizontal">
								
								<div class="row clearfix">
									<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label text-right">
										<label for="email_address_2">Mobile Number</label>
									</div>
									<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
										<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 m-l-20" style="margin-left: 4%;">
											<div class="form-group">
												<div class="form-group form-float">
													<div class="form-line">
														<input type="text" ng-model="registerCustomer.mobile" class="form-control" placeholder="Enter Mobile Number" readonly="readonly"/>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
								
								<div class="row clearfix">
									<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label text-right">
										<label for="email_address_2">Customer Name</label>
									</div>
									<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
										<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 m-l-20" style="margin-left: 4%;">
											<div class="form-group">
												<div class="form-group form-float">
													<div class="form-line">
														<input type="text" ng-model="registerCustomer.name" class="form-control" placeholder="Enter Customer Name" />
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
								
								<div class="row clearfix">
									<div class="text-center">
										<button type="button" class="btn btn-primary m-t-15 waves-effect" style="width: 20%" ng-click="Customerregister(registerCustomer)">Submit</button>
									</div>
								</div>	
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
		
			<div class="row clearfix visibility" style="margin-left: 0px; margin-right: 0px;"  id="div3" >
			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 customIcon">
				<div class="card">
					<div class="header">
						<div class="row clearfix">
							<div class="col-xs-12 col-sm-6">
								<h2>AEPS Customer Registration</h2>
							</div>
						</div>
					</div>
					<div class="body">
						<div class="body">
							<form class="form-horizontal">
								
								<div class="row clearfix">
									<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label text-right">
										<label for="email_address_2">Mobile Number</label>
									</div>
									<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
										<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 m-l-20" style="margin-left: 4%;">
											<div class="form-group">
												<div class="form-group form-float">
													<div class="form-line">
														<input type="text" ng-model="registerCustomer.mobile" class="form-control" placeholder="Enter Mobile Number" readonly="readonly"/>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
								
								<div class="row clearfix">
									<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label text-right">
										<label for="email_address_2">Customer Name</label>
									</div>
									<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
										<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 m-l-20" style="margin-left: 4%;">
											<div class="form-group">
												<div class="form-group form-float">
													<div class="form-line">
														<input type="text" ng-model="registerCustomer.name" class="form-control" placeholder="Enter Customer Name" />
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
								
								<div class="row clearfix">
									<div class="text-center">
										<button type="button" class="btn btn-primary m-t-15 waves-effect" style="width: 20%" ng-click="Customerregister(registerCustomer)">Submit</button>
									</div>
								</div>	
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-------------- /Div2 ---------------------->
		
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
	<script src="assets/scripts/angular_yesbank.min.js?version=1.0.5"></script>
	<script src="assets/js/admin.js"></script>
	<script src="assets/js/pages/index.js"></script>
	<script src="assets/js/demo.js"></script>
	<script src="assets/js/datepicker-date.js"></script>
	<script src="assets/js/stellarnav.min.js"></script>
	
	<script src="assets/js/print.js"></script>

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
	$(document).ready(function() {

		
		$("#aepsrecipet").click(function(){
			$("#aepsrecipetdiv").print({
		    	globalStyles: true,
		    	mediaPrint: false,
		    	stylesheet: null,
		    	noPrintSelector: ".no-print",
		    	iframe: true,
		    	append: null,
		    	prepend: null,
		    	manuallyCopyFormValues: true,
		    	deferred: $.Deferred(),
		    	timeout: 750,
		    	title: null,
		    	doctype: '<!doctype html>'
			});
		});
		
		$(".printDivInternational").click(function(){
			$("#sdiv101").print({
		    	globalStyles: true,
		    	mediaPrint: false,
		    	stylesheet: null,
		    	noPrintSelector: ".no-print",
		    	iframe: true,
		    	append: null,
		    	prepend: null,
		    	manuallyCopyFormValues: true,
		    	deferred: $.Deferred(),
		    	timeout: 750,
		    	title: null,
		    	doctype: '<!doctype html>'
			});
		});


	});
	</script>
	
	<script type="text/javascript">
	$(document).ready(function() {

		
		$("#aepsrecipet").click(function(){
			$("#aepsrecipetdiv").print({
		    	globalStyles: true,
		    	mediaPrint: false,
		    	stylesheet: null,
		    	noPrintSelector: ".no-print",
		    	iframe: true,
		    	append: null,
		    	prepend: null,
		    	manuallyCopyFormValues: true,
		    	deferred: $.Deferred(),
		    	timeout: 750,
		    	title: null,
		    	doctype: '<!doctype html>'
			});
		});
		
		$(".printDivInternational").click(function(){
			$("#sdiv101").print({
		    	globalStyles: true,
		    	mediaPrint: false,
		    	stylesheet: null,
		    	noPrintSelector: ".no-print",
		    	iframe: true,
		    	append: null,
		    	prepend: null,
		    	manuallyCopyFormValues: true,
		    	deferred: $.Deferred(),
		    	timeout: 750,
		    	title: null,
		    	doctype: '<!doctype html>'
			});
		});


	});
	</script>
	

</body>

</html>