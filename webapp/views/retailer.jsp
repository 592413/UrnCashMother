
<%@page import="com.recharge.serviceimpl.UserImpl"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.recharge.model.User"%>
<%@page import="org.apache.log4j.Logger"%>
<%@page import="java.util.Date"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
	User user = (User) session.getAttribute("UserDetails");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
<META HTTP-EQUIV="Access-Control-Allow-Origin"
	CONTENT="http://localhost:8080/Hindreseller">
<title></title>
<link rel="icon" href="#" type="image/x-icon" />
<link
	href="https://fonts.googleapis.com/css?family=Roboto:400,700&subset=latin,cyrillic-ext"
	rel="stylesheet" type="text/css">
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
<link href="assets/css/newstyle.css" rel="stylesheet" />
<link href="assets/css/stellarnav.min.css" rel="stylesheet" />
<link href="assets/css/all.min.css" rel="stylesheet" />

<script src="assets/js/jquery.min.js"></script>
<script src="assets/js/jquery-1.11.3.min.js"></script>
<script src="assets/js/bootstrap-multiselect.js"></script>

<script src="assets/js/sweetalert.min.js"></script>
<style type="text/css">
@media print {
	body * {
		visibility: hidden;
	}
	#print-content * {
		visibility: visible;
	}
	.modal {
		position: absolute;
		left: 0;
		top: 0;
		margin: 0;
		padding: 0;
		min-height: 550px
	}
}

.myForm .form-group .form-line.focused .form-label {
	top: -10px;
	left: 0;
	font-size: 16px;
}

.content1 {
	padding: 6% 0% 0% 0% !important;
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

.navTabIconImgAgriAeps {
	padding-bottom: 0%;
	width: 46px;
	margin-left: 0%;
	margin-bottom: -18px;
	margin-top: 17px;
}
</style>
<style type="text/css">
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

.visiable-on-responsive {
	display: none !important;
}

@media ( max-width : 768px) {
	.visiable-on-responsive {
		display: block !important;
	}
}
</style>

<style>
#Loader {
	display: none;
}

#overlay {
	position: fixed;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	background-color: #000;
	filter: alpha(opacity = 70);
	-moz-opacity: 0.7;
	-khtml-opacity: 0.7;
	opacity: 0.7;
	z-index: 100;
	display: none;
}

.cnt223 a {
	text-decoration: none;
}

.popup {
	width: 100%;
	margin: 0 auto;
	display: none;
	position: fixed;
	z-index: 101;
	margin-top: 130px;
}

.cnt223 {
	width: 334px;
	min-height: 109px;
	margin: 0 auto;
	background: #f3f3f3 !important;
	position: relative;
	z-index: 1000;
	padding: 10px;
	border-radius: 5px;
	box-shadow: 0 2px 5px #000;
}

.cnt223 p {
	clear: both;
	color: #555555;
	text-align: justify;
}

.cnt223 p a {
	color: #d91900;
	font-weight: bold;
}

.cnt223 .x1 {
	float: right;
	height: 35px;
	left: 24px;
	position: relative;
	top: -5px;
	width: 34px;
}
</style>


</head>

<body class="{{reseller.theme}}" ng-app="app"
	ng-controller="appController">
	<c:if test="${not empty msg}">
		<script type='text/javascript'>
			$(function() {
				var overlay = $('<div id="overlay"></div>');
				overlay.show();
				overlay.appendTo(document.body);
				$('.popup').show();
				$('.close1').click(function() {
					$('.popup').hide();
					overlay.appendTo(document.body).remove();
					return false;
				});

				$('.x1').click(function() {
					$('.popup').hide();
					overlay.appendTo(document.body).remove();
					return false;
				});
			});
		</script>
		<div class='popup'>
			<div class='cnt223'>
				<h1 class='x1' id='x'
					style="font-size: 14px; cursor: pointer; color: black;">X</h1>
				<h1
					style="font-size: 15px; margin-left: 12px; color: #0063ce; line-height: 8px;">Message</h1>
				<hr>
				${msg}
			</div>
		</div>
	</c:if>


	<alert-message alert="alertMessage"></alert-message>

	<div class="page-loader-wrapper" ng-show="loader"
		ng-init="loader = true">
		<div class="loader">
			<div class="preloader">
				<img alt="" src="assets/images/l3.gif">
			</div>
		</div>
	</div>
	<!-- #END# Page Loader -->
	<!-- Overlay For Sidebars -->
	<div class="overlay"></div>


	<!-- #END# Overlay For Sidebars -->
	<!-- Search Bar -->
	<div class="search-bar col-xs-hidden">
		<%
			if (user.getRoleId() == 3 || user.getRoleId() == 4) {
		%>
		<div class="col-md-6" style="margin-top: 2%;">
			<div class="col-md-2">
				<div class="search-icon">
					<label>Search User:</label>
				</div>
			</div>
			<div class="col-md-8">
				<div class="form-group form-float">
					<div class="form-line input-group">
						<span class="input-group-addon" id="basic-addon1"><i
							class="fa fa-search"></i></span> <input type="text" class="form-control"
							aria-describedby="basic-addon1" ng-model="searchUser.mobile">
					</div>
				</div>
			</div>
			<div class="col-md-2">
				<button type="button"
					class="btn btn-info waves-effect close-search1"
					onClick="showDiv('div13')"
					ng-click="AdvancedSearchUser(searchUser)">Submit</button>
			</div>
		</div>
		<%
			}
		%>
		<div class="col-md-6" style="margin-top: 2%;">
			<div class="col-md-2">
				<div class="search-icon">
					<label>Customer No:</label>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group form-float">
					<div class="form-line input-group">
						<span class="input-group-addon" id="basic-addon2"><i
							class="fa fa-search"></i></span> <input type="text" class="form-control"
							aria-describedby="basic-addon1" ng-model="searchUser.customerNo">
					</div>
				</div>
			</div>
			<div class="col-md-2">
				<button type="button"
					class="btn btn-info waves-effect close-search1"
					ng-click="AdvancedCustomerNo(searchUser)"
					onClick="showDiv('div12')">Submit</button>
			</div>
		</div>
		<div class="close-search">
			<i class="material-icons">close</i>
		</div>

	</div>
	<!-- #END# Search Bar -->
	<!-- -----------------------------------------------20/11/17--------------------------------------------------------------------------------- -->
	<!-- Top Bar -->
	<div class="container-fluid navbar1 navbar">
		<div class="row">
			<div class="col-md-1" style="margin: 0px; padding: 0px;">
				<a class="navbar-brand " href="home"><img
					class="logo img-responsive" id="logo" src=""></a>
			</div>
			<div class="col-md-10" style="margin: 0px; padding: 0px;">
				<div class="stellarnav">
					<ul>
					  	<li><a href="home"> <i class="material-icons">home</i><span>Home</span></a></li>
					    <li><a href="javascript:void(0);"> <i
							class="material-icons">account_circle</i> <span> Profile</span></a>
					      <ul>
					        <li><a onClick="showDiv('div2')" href="#">View Profile</a></li>
							<li><a onClick="showDiv('div3')" href="#">Change Password</a>
							</li>
							 <li><a onClick="showDiv('div45')" href="#">Add Bank
										Details For AEPS TRansaction</a></li> 
							 <li><a href="#" onClick="showDiv('div108')"> P2A MONEY REGISTRATION</a></li>			
							<li><a onClick="showDiv('div34')" ng-click="fetchaepsbankdt();">View Bank</a></li> 
					      </ul>
					    </li>
					    <%
							if (user.getRoleId() == 3 || user.getRoleId() == 4) {
						%>
						<li><a href="javascript:void(0);"> <i
								class="material-icons">group</i> <span>User Management</span></a>
							<ul>
								<li><a onClick="showDiv('div4')" href="#">Add User</a></li>
								<li><a onClick="showDiv('div13')" href="#">View User</a></li>
								<li><a onClick="showDiv('div5')" href="#">Add Balance</a></li>
							</ul>
						</li>
						<%
							}
						%>
					    <li><a href="javascript:void(0);"> <i
							class="material-icons">widgets</i> <span>Discount Management</span></a>
							<ul>
								<%
									if (user.getRoleId() == 3 || user.getRoleId() == 4|| user.getRoleId() == 5) {
								%>
								 <li><a onClick="showDiv('div7')" href="#"><strong>Package
											Management</strong></a></li>
								<%
									}
								%>
							</ul>
						</li>
						<li><a href="javascript:void(0);"> <i
								class="material-icons">group</i> <span>Wallet Top Up</span></a>
							<ul>
								<li><a onClick="showDiv('div9')" href="#"
									ng-click="viewBankDetail();">Balance Request</a></li>
								 <li><a onclick="showDiv('div150')" href="#"><strong>View
											Settlement</strong></a></li> 
								<!-- <li><a onclick="showDiv('div150')" href="#"><strong>View
											Settlement</strong></a></li> -->
								 <li><a onclick="showDiv('div106')" href="#"><strong>P2A </strong></a></li> 		
								<li><a  onclick="showDiv('div170')" ><strong>Add Wallet</strong></a></li>				
							</ul>
						</li>
					    <li><a href="javascript:void(0);"> <i
								class="material-icons">forum</i> <span>Support</span></a>
							<ul>
								<li><a onClick="showDiv('div20')" href="#">Complain</a></li>
								<li><a onClick="showDiv('div19')" href="#"
									ng-click="viewlatestComplain(viewComplain);">View Complain</a></li>
							</ul>
						</li>
						<li><a onClick="showDiv('div10')" href="#"
							ng-click="viewBankDetail();"> <i class="material-icons">account_balance</i>
							<span>Company Bank Details</span></a>
						</li>
						<li><a onClick="showDiv('div101')" href="javascript:void(0);" target="admin_report"> <i class="material-icons">content_copy</i> <span>Reports</span></a></li>
						
						<!-- <li><a href="flightsearch"><i class="fas fa-fighter-jet font_i"></i> <span>Flight</span></a></li>
						<li><a href="BBPS"> <i class="fas fa-utensils font_i"></i> <span>Hotel</span></a></li> -->
						<!-- <li class="right-site">
							<h6>
								Balance Rs {{userDetails.balance}}/-   Aeps Wallet : {{totalData.aepsb}}/-
							</h6>
							<a href="javascript:void(0);"><span class="ure_pf">WELCOME : <input class="ure_pf_txt" ng-model="userDetails.name" type="text"></span></a>
						</li>	 -->
						<li class="right-site new_por"><a class="ure_pf" href="javascript:void(0);" style="color: #fff;text-transform: uppercase;">
							<i class="far fa-user-circle" style="margin-right: 5px;"></i><span>Profile</span>
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
						<li class="right-site">
							<a href="javascript:void(0);"><span class="ure_pf">WELCOME : <span>{{userDetails.name}}</span></span></a>
							<strong>Contact : <span>xxxxxxxxxxx</span>  </strong>
						</li>	
					  </ul>
				</div>
			</div>
			<div class="col-md-1 col-sm-12 col-xs-12 noti">
				<div class="new_por">
					<button class="ure_pf" type="button"><a href="logout"><i class="fas fa-sign-out-alt"></i> LOG OUT</a></button>
				</div>
			</div>
			<div class="news">
				<marquee>
					<a class="navbar-brand1" href="#" style="color: #fff">{{News.news}}</a>
				</marquee>
			</div>
		</div>
	</div>
	<!-- #Top Bar -->
	<!-- ----------------------------------------End---------------------------------------------- -->
	<section class="content enco_content" style="margin: 75px 15px 0px 15px;">
	<div class="container-fluid">
		<div id="div0" class="row clearfix" style="display: block">
				<!-- Tabs With Only Icon Title -->
				<div class="col-lg-12 en_new_ser col-md-12 col-sm-12 col-xs-12">
					<div class="cardsdd">
						<div class="bodysd">
							<!-- Nav tabs -->
						 	<%
							if (user.getRoleId() == 3 || user.getRoleId() == 5|| user.getRoleId() == 4) {
							%>
							<ul class="nav nav-tabs ser_im" role="tablist">
								<li role="presentation"><a href="#mobile-recharge"
									data-toggle="tab" role="tab" class="text-center shortcut">
										<img alt="" src="assets/images/icon2/rec.png">
										<br> Mobile
								</a></li>
								<li role="presentation"><a href="#dth" data-toggle="tab"
									role="tab" class="text-center shortcut"> 
									<img alt="" src="assets/images/icon2/dis.png"><br>
										DTH
								</a></li>
								<!-- <li role="presentation"><a href="#data-card"
									data-toggle="tab" role="tab" class="text-center shortcut">
										<i class="material-icons" style="font-size: 40px">signal_wifi_4_bar</i><br>Datacard
								</a></li> -->
								<!-- <li role="presentation"><a href="#bbpsPostPaid"
									data-toggle="tab" role="tab" class="text-center shortcut">
										<i class="material-icons" style="font-size: 40px">lightbulb_outline</i><br>BBPS POSTPAID
								</a></li> -->
<!-- 								<li role="presentation"><a href="#bbpsPostPaid" data-toggle="tab" -->
<!-- 									role="tab" class="text-center shortcut"> -->
								<li role="presentation"><a href="BBPS"
-									 class="text-center shortcut">
										<img alt="" src="assets/images/icon2/bbps.png"><br>
										BBPS
								</a></li>
								<li role="presentation" class="active"><a href="#aepsnew_fing" data-toggle="tab" role="tab" target="aepsfingpnt"
									 role="tab" class="text-center">
										<img alt="" src="assets/images/icon2/icici.png"><br>AEPS AADHAAR PAY
								</a></li>
								<li role="presentation" class="active"><a href="#aepsnew_easy" data-toggle="tab" role="tab" target="aepseasypay"
									 role="tab" class="text-center">
										<img alt="" src="assets/images/icon2/yes.png"><br>YES AADHAAR PAY
								</a></li>
								<!-- <li role="presentation"><a href="aepsaadharpay"
									 role="tab" class="text-center">
										<img alt="" src="assets/images/icon2/icici.png"><br>AAdharPay
								</a></li> -->
								<li role="presentation"><a href="#dmr_ifm" data-toggle="tab" role="tab" target="dmr_nwe" 
								class="text-center">
										<img alt="" src="assets/images/icon2/pos.png"><br>Money Transfer
								</a></li> 
								<!-- <li role="presentation"><a href="IMPSNEW" class="text-center">
										<img alt="" src="assets/images/icon2/dmr.png"><br>DMR 
								</a></li> -->
								<!-- <li role="presentation"><a href="#yesaeps"
									data-toggle="tab" role="tab" class="text-center shortcut">
										<img alt="" src="assets/images/icon2/yes.png"><br>YES BANK
								</a></li> -->
								<li role="presentation"><a href="#pancard"
									data-toggle="tab" role="tab" class="text-center shortcut">
										<img alt="" src="assets/images/icon2/pan.png"><br>
										PAN Card
								</a></li>
								<li role="presentation"><a href="#flight_ifm" data-toggle="tab" role="tab" target="iframe_a"
									class="text-center shortcut"> <img alt="" src="assets/images/icon/flight.png"><br>Flight
								</a></li>
								<!-- <li role="presentation"><a href="#gas" data-toggle="tab"
									role="tab" class="text-center shortcut"> <img alt="" src="assets/images/icon2/gas.png"><br>GAS
								</a></li> -->
								<!-- <li role="presentation"><a href="#electricity"
									data-toggle="tab" role="tab" class="text-center shortcut">
										<img alt="" src="assets/images/icon2/el.png"><br>Electricity
								</a></li> -->
								<li role="presentation"><a href="#hotel_ifm" data-toggle="tab" role="tab" target="iframe_b"
									class="text-center shortcut">
										<img alt="" src="assets/images/icon2/hotel.png"><br>Hotel
								</a></li>
								
								<li role="presentation"><a href="bussearch"
									class="text-center shortcut">
										<img alt="" src="assets/images/icon2/bus.png"><br>Bus
								</a></li>
								
							<!-- <li role="presentation"><a href="ministatement"
									 role="tab" class="text-center shortcut aeps-triger">
										<img
										src="assets/images/icici.png"
										class="img-responsive navTabIconImgAgriAeps"
										style="width: 78px"><br>Minisattement
								</a></li> -->
								<li role="presentation"><a target="blank" href="https://www.irctc.co.in/nget/train-search"
									 class="text-center shortcut">
										<img alt="" src="assets/images/icon2/rail.png"><br>
										irctc
								</a></li>
								<!-- <li><a class="text-center shortcut" href="SBIPay"> <img alt="" src="assets/images/icon2/sbi.png"><br>
										<span>SBI CARD APPLY</span></a></li> -->
								<li role="presentation">
									<a href="#insurec"
									data-toggle="tab" role="tab" class="text-center shortcut"> <img alt="" src="assets/images/icon2/insu.png"><br>
										<span>INSURANCE</span>
									</a>
								</li>
								<li role="presentation" style="width: 66px;">
									<a href="#insurecfg"
									data-toggle="tab" role="tab" class="text-center shortcut"
										 class="text-center shortcut">
									</a>
								</li>
								<li role="presentation" style="width: 66px;">
									<a href="#insurecfg"
									data-toggle="tab" role="tab" class="text-center shortcut"
										 class="text-center shortcut">
									</a>
								</li>
								<li role="presentation" style="width: 66px;">
									<a href="#insurecfg"
									data-toggle="tab" role="tab" class="text-center shortcut"
										 class="text-center shortcut">
									</a>
								</li>
								
								<li role="presentation" style="width: 66px;">
									<a href="#insurecfg"
									data-toggle="tab" role="tab" class="text-center shortcut"
										 class="text-center shortcut">
									</a>
								</li>
								
								
							</ul>
						<%} %> 

							<!-- Tab panes -->
							<div class="tab-content">
								<div role="tabpanel" class="tab-pane" id="mobile-recharge">
									<h4 style="margin-bottom: 3%;">
										<b>Recharge Your Prepaid Mobile</b>
									</h4>
									<div class="row clearfix">
										<div
											class="col-lg-12 col-md-12 col-sm-12 col-xs-12 col-lg-offset-0 col-md-offset-0 ">
											<div class="form-group form-float">
												<div class="form-line">
													<input type="text" class="form-control"
														ng-model="mobileRecharge.mobile"
														ng-keypress="filterValue($event);" maxlength="10"
														ng-blur="fetchOperator();"> <label
														class="form-label">Mobile Number</label>
												</div>
											</div>
										</div>
										<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
											<div class="form-group form-float">
												<div class="form-line">
													<input type="text" class="form-control"
														ng-model="mobileRecharge.amount"
														ng-change="checkAmount(mobileRecharge.amount,userDetails.balance);"
														ng-keypress="filterValue($event);"> <label
														class="form-label">Amount</label>
												</div>
											</div>
										</div>
										<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
											<div class="form-group form-float">
												<div class="form-line  text-center">
													<select class="form-control"
														ng-model="mobileRecharge.operator">
														<option value=""></option>
														<option ng-repeat="operator in operator"
															ng-if="operator.serviceType == '1'"
															value="{{operator.inCode}}">{{operator.serviceProvider}}</option>
													</select> <label class="form-label">Operator</label>
												</div>
											</div>
										</div>
										<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
											<div class="form-group form-float">
												<input type="hidden" ng-model="mobileRecharge.serviceType"
													ng-init="mobileRecharge.serviceType ='1'" />
												<button type="button"
													class="btn bg-cyan btn-block btn-lg waves-effect"
													ng-click="mobileWebRecharge(mobileRecharge);">SUBMIT</button>
											</div>
										</div>
									</div>
								</div>
								<div role="tabpanel" class="tab-pane fade" id="dth">
									<h4 style="margin-bottom: 3%;">
										<b>Recharge Your DTH</b>
									</h4>
									<div class="row clearfix">
										<div
											class="col-lg-12 col-md-12 col-sm-12 col-xs-12 col-lg-offset-0 col-md-offset-0 ">
											<div class="form-group form-float">
												<div class="form-line">
													<input type="text" class="form-control"
														ng-model="dthRecharge.mobile"
														ng-keypress="filterValue($event);"
														ng-blur="fetchOperator();"> <label
														class="form-label">Customer Id</label>
												</div>
											</div>
										</div>
										<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
											<div class="form-group form-float">
												<div class="form-line">
													<input type="text" class="form-control"
														ng-model="dthRecharge.amount"
														ng-change="checkAmount(dthRecharg.amount,userDetails.balance);"
														ng-keypress="filterValue($event);"> <label
														class="form-label">Amount</label>
												</div>
											</div>
										</div>
										<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
											<div class="form-group form-float">
												<div class="form-line  text-center">
													<select class="form-control"
														ng-model="dthRecharge.operator">
														<option value=""></option>
														<option ng-repeat="operator in operator"
															ng-if="operator.serviceType == '2'"
															value="{{operator.inCode}}">{{operator.serviceProvider}}</option>
													</select> <label class="form-label">Operator</label>
												</div>
											</div>
										</div>
										<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
											<div class="form-group form-float">
												<input type="hidden" ng-model="dthRecharge.serviceType"
													ng-init="dthRecharge.serviceType ='2'" />
												<button type="button"
													class="btn bg-cyan btn-block btn-lg waves-effect"
													ng-click="dthWebRecharge(dthRecharge);">SUBMIT</button>
											</div>
										</div>
									</div>

								</div>
								<div role="tabpanel" class="tab-pane fade" id="data-card">
									<h4 style="margin-bottom: 3%;">
										<b>Recharge Your Prepaid Mobile</b>
									</h4>
									<div class="row clearfix">
										<div
											class="col-lg-12 col-md-12 col-sm-12 col-xs-12 col-lg-offset-0 col-md-offset-0 ">
											<div class="form-group form-float">
												<div class="form-line">
													<input type="text" class="form-control"
														ng-model="dataCard.mobile"
														ng-keypress="filterValue($event);" maxlength="10"
														ng-blur="fetchOperator();"> <label
														class="form-label">Mobile Number</label>
												</div>
											</div>
										</div>
										<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
											<div class="form-group form-float">
												<div class="form-line">
													<input type="text" class="form-control"
														ng-model="dataCard.amount"
														ng-change="checkAmount(dataCard.amount,userDetails.balance);"
														ng-keypress="filterValue($event);"> <label
														class="form-label">Amount</label>
												</div>
											</div>
										</div>
										<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
											<div class="form-group form-float">
												<div class="form-line  text-center">
													<select class="form-control" ng-model="dataCard.operator">
														<option value=""></option>
														<option ng-repeat="operator in operator"
															ng-if="operator.serviceType == '3'"
															value="{{operator.inCode}}">{{operator.serviceProvider}}</option>
													</select> <label class="form-label">Operator</label>
												</div>
											</div>
										</div>
										<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
											<div class="form-group form-float">
												<input type="hidden" ng-model="dataCard.serviceType"
													ng-init="dataCard.serviceType ='3'" />
												<button type="button"
													class="btn bg-cyan btn-block btn-lg waves-effect"
													ng-click="dataCardWebRecharge(dataCard);">SUBMIT</button>
											</div>
										</div>
									</div>
								</div>
								<div role="tabpanel" class="tab-pane fade" id="post-paid">
									<div class="row clearfix">
										<div
											class="col-lg-12 col-md-12 col-sm-12 col-xs-12 col-lg-offset-2 col-md-offset-2 ">
											<div class="icon-and-text-button-demo">
												<button id="payMobile" type="button"
													class="btn btn-primary waves-effect"
													style="width: 32.666667%;">
													<i class="material-icons">settings_phone</i> <span>Mobile
														Bill Payment</span>
												</button>
												<button id="payLandline" type="button"
													class="btn btn-primary waves-effect"
													style="width: 32.666667%;">
													<i class="material-icons">phone_android</i> <span>Land
														Line Bill Payment</span>
												</button>
											</div>
										</div>
									</div>
									<!-- ----------POSTPAID MOBILE---------- -->
									<div class="row clearfix" id="mobile">
										<div
											class="col-lg-12 col-md-12 col-sm-12 col-xs-12 col-lg-offset-0 col-md-offset-0 ">
											<h5 style="margin-bottom: 1%;">
												<b>Pay Your Postpaid Mobile Bill</b>
											</h5>
											<div class="form-group form-float">
												<div class="form-line">
													<input type="text" class="form-control"
														ng-model="postPaid.mobile"
														ng-keypress="filterValue($event);" maxlength="10"
														ng-blur="fetchOperator();"> <label
														class="form-label">Mobile Number</label>
												</div>
											</div>
										</div>
										<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
											<div class="form-group form-float">
												<div class="form-line">
													<input type="text" class="form-control"
														ng-model="postPaid.amount"
														ng-change="checkAmount(postPaid.amount,userDetails.balance);"
														ng-keypress="filterValue($event);"> <label
														class="form-label">Amount</label>
												</div>
											</div>
										</div>
										<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
											<div class="form-group form-float">
												<div class="form-line  text-center">
													<select class="form-control" ng-model="postPaid.operator">
														<option ng-repeat="operator in operator"
															ng-if="operator.serviceType == '4'"
															value="{{operator.inCode}}">{{operator.serviceProvider}}</option>
													</select> <label class="form-label">Operator</label>
												</div>
											</div>
										</div>
										<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
											<div class="form-group form-float">
												<input type="hidden" ng-model="postPaid.serviceType"
													ng-init="postPaid.serviceType ='4'" />
												<button type="button"
													class="btn bg-cyan btn-block btn-lg waves-effect"
													ng-click="postPaidWebRecharge(postPaid);">SUBMIT</button>
											</div>
										</div>
									</div>
									<!-- -----------LAND LINE PHONE----- -->
									<div class="row clearfix" id="landline" style="display: none">
										<div
											class="col-lg-12 col-md-12 col-sm-12 col-xs-12 col-lg-offset-0 col-md-offset-0 ">
											<h5 style="margin-bottom: 1%;">
												<b>Pay Your Postpaid Landline Bill</b>
											</h5>
											<div class="form-group form-float">
												<div class="form-line">
													<input type="text" class="form-control"
														ng-model="postPaid.mobile"
														ng-keypress="filterValue($event);" maxlength="10"
														ng-blur="fetchOperator();"> <label
														class="form-label">Mobile Number</label>
												</div>
											</div>
										</div>
										<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
											<div class="form-group form-float">
												<div class="form-line">
													<input type="text" class="form-control"
														ng-model="postPaid.amount"
														ng-keypress="filterValue($event);" maxlength="10">
													<label class="form-label">Amount</label>
												</div>
											</div>
										</div>
										<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
											<div class="form-group form-float">
												<div class="form-line  text-center">
													<select class="form-control" ng-model="postPaid.operator">
														<option value=""></option>
														<option ng-repeat="operator in operator"
															ng-if="operator.serviceType == '4'"
															value="{{operator.inCode}}">{{operator.serviceProvider}}</option>
													</select> <label class="form-label">Operator</label>
												</div>
											</div>
										</div>
										<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
											<div class="form-group form-float">
												<input type="hidden" ng-model="postPaid.serviceType"
													ng-init="postPaid.serviceType ='4'" />
												<button type="button"
													class="btn bg-cyan btn-block btn-lg waves-effect"
													ng-click="postPaidWebRecharge(postPaid);">SUBMIT</button>
											</div>
										</div>
									</div>
								</div>
									<!-- ------------------------------------------------------------------- -->
								<div role="tabpanel" class="tab-pane fade" id="yesaeps">
									
									<div class="row clearfix">
									<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 col-lg-offset-0 col-md-offset-0 ">
									<h4 style="margin-bottom: 3%;">
										<b>AEPS Service</b>
									</h4>
									</div>
									<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 col-lg-offset-0 col-md-offset-0 ">
											<div class="form-group form-float">
												<div class="form-line">
													<input type="text" class="form-control" ng-model = "transactionReq.mobile" maxlength="10"> 
													<label class="form-label">Mobile Number</label>
												</div>
											</div>
										</div>
										
										<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 col-lg-offset-0 col-md-offset-0 ">
											<div class="form-group form-float">
												<div class="form-line">
													<select class="form-control" ng-model="transactionReq.mode" ng-change="selecttransaction(transactionReq.mode)">
														<option value="0">Select Action</option>
														<option value="BALANCEINFO">Balance Enquiry</option>
														<option value="WITHDRAWAL">Withdrawal</option>
													</select>
													<label class="form-label">Select Mode</label>
												</div>
											</div>
										</div>
										
										<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 col-lg-offset-0 col-md-offset-0 ">
											<div class="form-group form-float">
												<div class="form-line">
													<input type="text" class="form-control" id="transamnt" ng-model = "transactionReq.amount"> 
													<label class="form-label">Amount</label>
												</div>
											</div>
										</div>	
										<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 col-lg-offset-0 col-md-offset-0 ">
											<div class="form-group form-float">
												<div class="form-line">
													<select class="form-control" ng-model="transactionReq.servicetype" ng-init="transactionReq.servicetype='0'">
														<option value="0">Select Service</option>
														<option value="AEPS">YBL AEPS</option>
														<option value="FINO">FINO AEPS</option>
														<option value="MICROATM">MICROATM</option>
													</select>
													<label class="form-label">Service Type</label>
												</div>
											</div>
										</div>			
										<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
											<div class="form-group form-float">
												<button type="button" class="btn bg-cyan btn-block btn-lg waves-effect" ng-click = "aepstransaction(transactionReq)">SUBMIT</button>
											</div>
										</div>
									
									</div>
								</div>
								<!-- ------------------------------------------------------------------- -->
								<div role="tabpanel" class="tab-pane fade" id="bbpsPostPaid">
									
									<div class="row clearfix">
									<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 col-lg-offset-0 col-md-offset-0 ">
									<h4 style="margin-bottom: 3%;">
										<b>BBPS PostPaid Bill Payment</b>
									</h4>
									</div>
									<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 col-lg-offset-0 col-md-offset-0 ">
											<div class="form-group form-float">
												<div class="form-line">
													<input type="text" class="form-control" ng-model = "electricity.consumerNumber" ng-blur="fetchOperator();"> 
													<label class="form-label">Consumer Number</label>
												</div>
											</div>
										</div>
										
										<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 col-lg-offset-0 col-md-offset-0 ">
											<div class="form-group form-float">
												<div class="form-line">
													<input type="text" class="form-control" ng-model = "electricity.consumerMobile"> 
													<label class="form-label">Consumer Mobile</label>
												</div>
											</div>
										</div>
											<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
											<div class="form-group form-float">
												<div class="form-line">
													<select class="form-control" ng-model="electricity.operator">
														<option value=""></option>
														<option ng-repeat="operator in operator" ng-if="operator.serviceType == '4'" value="{{operator.inCode}}">{{operator.serviceProvider}}</option>
													</select>
													<label class="form-label">Operator</label>
												</div>
											</div>
										</div>
										<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 col-lg-offset-0 col-md-offset-0 ">
											<div class="form-group form-float">
												<div class="form-line">
													<input type="text" class="form-control" ng-model = "electricity.Dueamount"> 
													<label class="form-label">Amount</label>
												</div>
											</div>
										</div>				
										<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
											<div class="form-group form-float">
												<button type="button" class="btn bg-cyan btn-block btn-lg waves-effect" ng-click = "eBillPaymentPostPaid(electricity);">SUBMIT</button>
											</div>
										</div>
									
									</div>
								</div>
								
								<!---------------------Electricity Bill Payement --------------------------- -->
								<div role="tabpanel" class="tab-pane fade" id="electricity">
									<h4 style="margin-bottom: 3%;">
										<b>Electric Bill Payment</b>
									</h4>
									<div class="row clearfix">
										<div
											class="col-lg-12 col-md-12 col-sm-12 col-xs-12 col-lg-offset-0 col-md-offset-0 ">
											<div class="form-group form-float">
												<div class="form-line">
													<input type="text" class="form-control"
														ng-model="electricity.consumerNumber"
														ng-blur="fetchOperator();"> <label
														class="form-label">Consumer Number</label>
												</div>
											</div>
										</div>
										<!-- <div
											class="col-lg-12 col-md-12 col-sm-12 col-xs-12 col-lg-offset-0 col-md-offset-0 ">
											<div class="form-group form-float">
												<div class="form-line">
													<input type="text" class="form-control"
														ng-model="electricity.consumerName"> <label
														class="form-label">Consumer Name</label>
												</div>
											</div>
										</div> -->
										<div
											class="col-lg-12 col-md-12 col-sm-12 col-xs-12 col-lg-offset-0 col-md-offset-0 ">
											<div class="form-group form-float">
												<div class="form-line">
													<input type="text" class="form-control"
														ng-model="electricity.consumerMobile"
														ng-keypress="filterValue($event);"> <label
														class="form-label">Consumer Mobile</label>
												</div>
											</div>
										</div>
										<!-- <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
											<div class="form-group form-float">
												<div class="form-line">
													<input type="text" class="form-control"
														ng-model="electricity.amount"
														ng-change="checkAmount(electricity.amount,userDetails.balance);"
														ng-keypress="filterValue($event);"> <label
														class="form-label">Amount</label>
												</div>
											</div>
										</div> -->
										<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
											<div class="form-group form-float">
												<div class="form-line">
													<select class="form-control"
														ng-model="electricity.operator">
														<option value=""></option>
														<option ng-repeat="operator in operator"
															ng-if="operator.serviceType == '7'"
															value="{{operator.inCode}}">{{operator.serviceProvider}}</option>
													</select> <label class="form-label">Operator</label>
												</div>
											</div>
										</div>
										<!-- <div
											class="col-lg-12 col-md-12 col-sm-12 col-xs-12 col-lg-offset-0 col-md-offset-0 ">
											<div class="form-group form-float">
												<div class="form-line">
													<input type="text" id="dp24" class="form-control"
														ng-model="electricity.dueDate" readonly="readonly">
													<label class="form-label">Bill Due Date</label>

												</div>
												<div class="help-block with-errors "
													style="color: red; font-size: 12px; font-family: serif;">*
													Bill should be submit 2 days before due date!</div>
											</div>
										</div> -->

										<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
											<div class="form-group form-float">
												<button type="button"
													class="btn bg-cyan btn-block btn-lg waves-effect"
													ng-click="billFetchelectric(electricity);">SUBMIT</button>
											</div>
										</div>
									</div>
								</div>
								<!--------------------- --------------------------- -->

								<div class="modal fade" id="mdModal" tabindex="-1" role="dialog"
									style="display: none;">
									<div class="modal-dialog" role="document" style="width: 45%;margin: 16% auto;left: 0%;">
					                    <div class="modal-content modal-col-default">
					                        
					                        <div class="modal-body">
					                        	<div class="row">
					                        		<div class="col-md-12 col-lg-12 col-sm-12 col-xs-12">
					                        		
					                        			<table class="table table-responsive" style="width: 100%" ng-if="billpaymentFirstdata.status=='0'">
					                        				<tbody>
					                        					<tr>
					                        						<td>Consumer Id</td>
					                        						<td>{{billpaymentFirstdata.consumerNumber}}</td>
					                        					</tr>
					                        					<tr>
					                        						<td>Consumer Mobile</td>
					                        						<td>{{billpaymentFirstdata.consumerMobile}}</td>
					                        					</tr>
					                        				</tbody>
					                        			</table>
					                        			
					                        				<table class="table table-responsive" style="width: 100%" ng-if="billpaymentFirstdata.status=='1'">
					                        				<tbody>
					                        				      <tr>
					                        						<td>Bill NO</td>
					                        						<td>{{billpaymentFirstdata.BILLNO}}</td>
					                        					</tr>
					                        					<tr>
					                        						<td>Consumer Id</td>
					                        						<td>{{billpaymentFirstdata.consumerNumber}}</td>
					                        					</tr>
					                        					<tr>
					                        						<td>Bill Date</td>
					                        						<td>{{billpaymentFirstdata.BILLDATE}}</td>
					                        					</tr>
					                        					<tr>
					                        						<td>Bill Amount</td>
					                        						<td>{{billpaymentFirstdata.Dueamount}}</td>
					                        					</tr>
					                        					<tr>
					                        						<td>Consumer Name</td>
					                        						<td>{{billpaymentFirstdata.CUSTNAME}}</td>
					                        					</tr>
					                        					<tr>
					                        						<td>Consumer Mobile</td>
					                        						<td>{{billpaymentFirstdata.consumerMobile}}</td>
					                        					</tr>
					                        					<tr>
					                        						<td>Partial Payment</td>
					                        						<td>{{billpaymentFirstdata.PARTIALPAYALLOW}}</td>
					                        					</tr>
					                        				</tbody>
					                        			</table>
					                        		</div>
					                        	</div>
					                        
					                        </div>
					                        <div class="modal-footer">
					                            <button type="button" class="btn btn-blue waves-effect" ng-if="billpaymentFirstdata.status=='1'" ng-click="eBillPayment(billpaymentFirstdata)">Pay</button>
					                            <button type="button" class="btn btn-red waves-effect" data-dismiss="modal">CLOSE</button>
					                        </div>
					                    </div>
					                </div>
								</div>

								<div class="modal fade" id="mdModal1" tabindex="-1" role="dialog" style="display: none;">
					                <div class="modal-dialog" role="document" style="width: 45%;margin: 16% auto;left: 0%;">
					                    <div class="modal-content modal-col-default">
					                        
					                        <div class="modal-body">
					                        	<div class="row">
					                        		<div class="col-md-12 col-lg-12 col-sm-12 col-xs-12">
					                        		
					                        						                        			
					                        				<table class="table table-responsive" style="width: 100%" ng-if="billpaymentmanualdata.status=='2'">
					                        				<tbody>
					                        					<tr>
					                        						<td>Consumer Id</td>
					                        						<td>{{billpaymentmanualdata.consumerNumber}}</td>
					                        					</tr>
					                        					<tr>
					                        						<td>Consumer Mobile</td>
					                        						<td>{{billpaymentmanualdata.consumerMobile}}</td>
					                        					</tr>
					                        					<tr>
					                        						<td>Bill Date</td>
					                        						<td><input type="text" class="form-control" ng-model = "billpaymentmanualdata.BILLDATE"></td>
					                        					</tr>
					                        					<tr>
					                        						<td>Bill Amount</td>
					                        						<td><input type="text" class="form-control" ng-model = "billpaymentmanualdata.Dueamount"></td>
					                        					</tr>
					                        					<tr>
					                        						<td>Consumer Name</td>
					                        						<td><input type="text" class="form-control" ng-model = "billpaymentmanualdata.CUSTNAME"></td>
					                        					</tr>
					                        					
					                        				</tbody>
					                        			</table>
					                        		</div>
					                        	</div>
					                        
					                        </div>
					                        <div class="modal-footer">
					                            <button type="button" class="btn btn-blue waves-effect" ng-click="eBillPaymentmanual(billpaymentmanualdata)">Pay</button>
					                            <button type="button" class="btn btn-red waves-effect" data-dismiss="modal">CLOSE</button>
					                        </div>
					                    </div>
					                </div>
					            </div>
								<!---------------------Gas Bill Payement --------------------------- -->
								<div role="tabpanel" class="tab-pane fade" id="gas">
									<h4 style="margin-bottom: 3%;">
										<b>Gas Bill Payment</b>
									</h4>
									<div class="row clearfix">
										<div
											class="col-lg-12 col-md-12 col-sm-12 col-xs-12 col-lg-offset-0 col-md-offset-0 ">
											<div class="form-group form-float">
												<div class="form-line">
													<input type="text" class="form-control"
														ng-model="gasBill.consumerNumber"
														ng-blur="fetchOperator();"> <label
														class="form-label">Consumer Number</label>
												</div>
											</div>
										</div>
										<div
											class="col-lg-12 col-md-12 col-sm-12 col-xs-12 col-lg-offset-0 col-md-offset-0 ">
											<div class="form-group form-float">
												<div class="form-line">
													<input type="text" class="form-control"
														ng-model="gasBill.consumerName"> <label
														class="form-label">Consumer Name</label>
												</div>
											</div>
										</div>
										<div
											class="col-lg-12 col-md-12 col-sm-12 col-xs-12 col-lg-offset-0 col-md-offset-0 ">
											<div class="form-group form-float">
												<div class="form-line">
													<input type="text" class="form-control"
														ng-model="gasBill.consumerMobile"
														ng-keypress="filterValue($event);"> <label
														class="form-label">Consumer Mobile</label>
												</div>
											</div>
										</div>
										<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
											<div class="form-group form-float">
												<div class="form-line">
													<input type="text" class="form-control"
														ng-model="gasBill.amount"
														ng-change="checkAmount(gasBill.amount,userDetails.balance);"
														ng-keypress="filterValue($event);"> <label
														class="form-label">Amount</label>
												</div>
											</div>
										</div>
										<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
											<div class="form-group form-float">
												<div class="form-line">
													<select class="form-control" ng-model="gasBill.operator">
														<option value=""></option>
														<option ng-repeat="operator in operator"
															ng-if="operator.serviceType == '6'"
															value="{{operator.inCode}}">{{operator.serviceProvider}}</option>
													</select> <label class="form-label">Operator</label>
												</div>
											</div>
										</div>
										<div
											class="col-lg-12 col-md-12 col-sm-12 col-xs-12 col-lg-offset-0 col-md-offset-0 ">
											<div class="form-group form-float">
												<div class="form-line">
													<input type="text" id="dp28" class="form-control"
														ng-model="gasBill.dueDate" readonly="readonly"> <label
														class="form-label">Bill Due Date</label>

												</div>
												<div class="help-block with-errors "
													style="color: red; font-size: 12px; font-family: serif;">*
													Bill should be submit 2 days before due date!</div>
											</div>
										</div>

										<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
											<div class="form-group form-float">
												<button type="button"
													class="btn bg-cyan btn-block btn-lg waves-effect"
													ng-click="gasBillPayment(gasBill);">SUBMIT</button>
											</div>
										</div>
									</div>
								</div>
								<!-- -------------------------------------------------------------- -->
								
								<div role="tabpanel" class="tab-pane fade" id="flight_ifm">
									<div class="row clearfix">
										<iframe src="flightsearch" name="iframe_a" style="width:100%;height: 100vh;"></iframe>
									</div>
								</div>
								<div role="tabpanel" class="tab-pane fade" id="hotel_ifm">
									<div class="row clearfix">
										<iframe src="hotel" name="iframe_b" style="width:100%;height: 100vh;"></iframe>
									</div>
								</div>
								<div role="tabpanel" class="tab-pane active" id="aepsnew_fing">
									<div class="row clearfix">
										<iframe src="aepsnew" name="aepsfingpnt" style="width:100%;height: 100vh;"></iframe>
									</div>
								</div>
								<div role="tabpanel" class="tab-pane" id="aepsnew_easy">
									<div class="row clearfix">
										<iframe src="aepseasy" name="aepseasypay" style="width:100%;height: 100vh;"></iframe>
									</div>
								</div>
								<div role="tabpanel" class="tab-pane fade" id="dmr_ifm">
									<div class="row clearfix">
										<iframe src="DMROPEN" name="dmr_nwe" style="width:100%;height: 100vh;"></iframe>
									</div>
								</div>
								
								<div role="tabpanel" class="tab-pane fade" id="irctc">
									<div class="row clearfix">
										<iframe src="https://www.irctc.co.in/nget/train-search" name="iframe_train" style="width:100%;height: 100vh;"></iframe>
									</div>
								</div>
								
								<div role="tabpanel" class="tab-pane" id="insurec">
									<h4 style="margin-bottom: 3%;">
										<b>Register For Insurance</b>
									</h4>
									<div class="row clearfix">
										<div class="body">
											<div class="col-lg-7 col-md-7 col-sm-12 col-xs-12">
												<form class="form-horizontal">
													<div class="row clearfix">
														<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
															<label for="email_address_2">First Name</label>
														</div>
														<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
															<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
																<div class="form-group form-float"
																	style="margin-left: 0px !important;">
																	<div class="form-line">
																		<input type="text" class="form-control" placeholder="Enter First Name"  ng-model ="userDetails.firstname">
																	</div>
																</div>
															</div>
														</div>
													</div>
													
													<div class="row clearfix">
														<div
															class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
															<label for="email_address_2">Last Name</label>
														</div>
														<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
															<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
																<div class="form-group form-float"
																	style="margin-left: 0px !important;">
																	<div class="form-line">
																		<input type="text" class="form-control" placeholder="Enter Last Name"  ng-model ="userDetails.lastName">
						
																	</div>
																</div>
															</div>
														</div>
													</div>
													<div class="row clearfix" >
														<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
															<label for="email_address_2">Mobile Number</label>
														</div>
														<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
															<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
																<div class="form-group form-float"
																	style="margin-left: 0px !important;">
																	<div class="form-line">
																		<input type="text" ng-model="userDetails.mobile" ng-change="checkUniqueUser(userDetails.mobile);" class="form-control" ng-keypress="filterValue($event);" maxlength="10">											
																		</div>
																</div>
															</div>
														</div>
													</div>
													
													<div class="row clearfix">
														<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
															<label for="email_address_2">E-Mail</label>
														</div>
														<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
															<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
																<div class="form-group form-float"
																	style="margin-left: 0px !important;">
																	<div class="form-line">
																		<input type="text" class="form-control" placeholder="Enter E-Mail"  ng-model="userDetails.email">
																	</div>
																</div>
															</div>
														</div>
													</div>
													<div class="row clearfix">
														<div class="col-lg-offset-6 col-md-offset-6 col-sm-offset-6 col-xs-offset-6">
															<button type="button" class="btn btn-primary m-t-15 waves-effect" ng-click = "insurancegiblBillPaymentRegi(userDetails);">Submit</button>
														</div>
													</div>
												</form>
											</div>
											<div class="col-md-5">
												<form class="form-horizontal">
													<div class="row clearfix">
														<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
															<label for="email_address_2">Category</label>
														</div>
														<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
															<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
																<div class="form-group form-float"
																	style="margin-left: 0px !important;">
																	<div class="form-line">
																		<select class="form-control" ng-model="insurancePremium.category">
																			<option value="0">GIBL Home page</option>
																			<option value="1">Car insurance</option>
																			<option value="2">Wheeler insurance</option>
																		</select>
																	</div>
																</div>
															</div>
														</div>
													</div>	
												<div class="row clearfix">
														<div class="col-lg-offset-6 col-md-offset-6 col-sm-offset-6 col-xs-offset-6">
															<button type="submit" class="btn btn-primary" ng-click="insurancegiblBillPayment(insurancePremium);">Submit</button>
														</div>
													</div>
												</form>
											</div>
										
										</div>
									</div>
								</div>
	
							<!-- -------------------------------PAN Card Service------------------------------ -->
								<div role="tabpanel" class="tab-pane fade" id="pancard">
									<div class="row clearfix">
										<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
											<div class="header">
												<h2>Pan Card Service</h2>
											</div>
											<div class="body" id="sdiv119">
												<div class="clearfix row">
													<div class="col-xs-6 col-sm-4 col-md-4 col-lg-4 ">
														<a href="http://www.psaonline.utiitsl.com/psaonline/"
															onclick="OpenIframe('PAN Card Services');"
															target="getframe" data-original-title="Recruitment News">
															<div class="demo-color-box irctc">
																<div class="color-name">
																	<img alt="" class="img-responsive"
																		src="assets/images/pan_icon.png"
																		style="width: 74%; margin-left: 13%;">
																</div>
																<div class="color-code">UTIITSL Login</div>
																<div class="color-class-name bg-green">Check</div>
															</div>
														</a>
													</div>
													<%
														if (user.getRoleId() == 3 || user.getRoleId() == 4) {
													%>
													<div class="col-xs-6 col-sm-4 col-md-4 col-lg-4 ">
														<a onclick="sshowDiv('sdiv120');">
															<div class="demo-color-box irctc">
																<div class="color-name">
																	<img alt="" class="img-responsive"
																		src="assets/images/pan_icon.png"
																		style="width: 74%; margin-left: 13%;">
																</div>
																<div class="color-code">Agent Registration</div>
																<div class="color-class-name bg-green">Check</div>
															</div>
														</a>
													</div>
													<%
														}
													%>
													<div class="col-xs-6 col-sm-4 col-md-4 col-lg-4 ">
														<a onclick="sshowDiv('sdiv121');">
															<div class="demo-color-box irctc">
																<div class="color-name">
																	<img alt="" class="img-responsive"
																		src="assets/images/pan_icon.png"
																		style="width: 74%; margin-left: 13%;">
																</div>
																<div class="color-code">Agent Cupon Request</div>
																<div class="color-class-name bg-green">Check</div>
															</div>
														</a>
													</div>
												</div>
											</div>
											<div class="body visibility" id="sdiv120">
												<div class="clearfix row">
													<div class="header">
														<h2>
															Agent Registration <span class="pull-right"><a
																onclick="sshowDiv('sdiv119')"><i
																	class="fa fa-arrow-circle-left fa-2x"></i></a></span>
														</h2>
													</div>
													<div class="row clearfix">
														<div
															class="col-lg-12 col-md-12 col-sm-12 col-xs-12 col-lg-offset-0 col-md-offset-0 ">
															<div class="form-group form-float">
																<div class="form-line">
																	<input type="text" class="form-control"
																		ng-model="regis.psaname"
																		ng-change="regis.psaname=regis.psaname.toUpperCase();">
																	<label class="form-label">Name</label>
																</div>
															</div>
														</div>
														<div
															class="col-lg-12 col-md-12 col-sm-12 col-xs-12 col-lg-offset-0 col-md-offset-0 ">
															<div class="form-group form-float">
																<div class="form-line">
																	<input type="text" class="form-control"
																		ng-model="regis.location"
																		ng-change="regis.location=regis.location.toUpperCase();">
																	<label class="form-label">Address</label>
																</div>
															</div>
														</div>
														<div
															class="col-lg-12 col-md-12 col-sm-12 col-xs-12 col-lg-offset-0 col-md-offset-0 ">
															<div class="form-group form-float">
																<div class="form-line">
																	<input type="text" class="form-control"
																		ng-model="regis.pincode"> <label
																		class="form-label">Pin Code</label>
																</div>
															</div>
														</div>
														<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
															<div class="form-group form-float">
																<div class="form-line">
																	<input type="text" class="form-control"
																		ng-model="regis.district"
																		ng-change="regis.district=regis.district.toUpperCase();">
																	<label class="form-label">District</label>
																</div>
															</div>
														</div>
														<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
															<div class="form-group form-float">
																<div class="form-line">
																	<input type="text" class="form-control"
																		ng-model="regis.state"
																		ng-change="regis.state=regis.state.toUpperCase();">
																	<label class="form-label">State</label>
																</div>
															</div>
														</div>
														<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
															<div class="form-group form-float">
																<div class="form-line">
																	<input type="text" class="form-control"
																		ng-model="regis.phone1" maxlength="10"> <label
																		class="form-label">Mobile Number</label>
																</div>
															</div>
														</div>
														<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
															<div class="form-group form-float">
																<div class="form-line">
																	<input type="text" class="form-control"
																		ng-model="regis.phone2" maxlength="10"> <label
																		class="form-label">Alternate Mobile Number</label>
																</div>
															</div>
														</div>
														<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
															<div class="form-group form-float">
																<div class="form-line">
																	<input type="text" class="form-control"
																		ng-model="regis.emailid"> <label
																		class="form-label">Email ID</label>
																</div>
															</div>
														</div>
														<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
															<div class="form-group form-float">
																<div class="form-line">
																	<input type="text" class="form-control"
																		ng-model="regis.pan"
																		ng-change="regis.pan=regis.pan.toUpperCase();">
																	<label class="form-label">PAN Number</label>
																</div>
															</div>
														</div>
														<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
															<div class="form-group form-float">
																<div class="form-line">
																	<input type="text" class="form-control" id="dp25"
																		ng-model="regis.dob"> <label
																		class="form-label">Date Of Birth</label>
																</div>
															</div>
														</div>
														<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
															<div class="form-group form-float">
																<div class="form-line">
																	<input type="text" class="form-control"
																		ng-model="regis.aadhaar"
																		ng-change="regis.aadhaar=regis.aadhaar.toUpperCase();">
																	<label class="form-label">AAdhar Number</label>
																</div>
															</div>
														</div>
														<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
															<div class="form-group form-float">
																<button type="button"
																	class="btn bg-cyan btn-block btn-lg waves-effect"
																	ng-click="agencyrequest(regis);">SUBMIT</button>
															</div>
														</div>
													</div>
												</div>
											</div>

											<div class="body visibility" id="sdiv121">
												<div class="clearfix row">
													<div class="header">
														<h2>
															Agent Cupon Request <span class="pull-right"><a
																onclick="sshowDiv('sdiv119')"><i
																	class="fa fa-arrow-circle-left fa-2x"></i></a></span>
														</h2>
													</div>
													<div class="row clearfix">
														<div
															class="col-lg-12 col-md-12 col-sm-12 col-xs-12 col-lg-offset-0 col-md-offset-0 ">
															<div class="form-group form-float">
																<div class="form-line">
																	<input type="text" class="form-control"
																		ng-model="coupon.userid"> <label
																		class="form-label">UTIITSL User ID</label>
																</div>
															</div>
														</div>
														<!-- <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 col-lg-offset-0 col-md-offset-0 ">
															<div class="form-group form-float">
																<div class="form-line">
																	<input type="text" class="form-control" ng-model="coupon.agentPassword"> 
																	<label class="form-label">UTIITSL Password</label>
																</div>
															</div>
														</div> -->
														<div
															class="col-lg-12 col-md-12 col-sm-12 col-xs-12 col-lg-offset-0 col-md-offset-0 ">
															<div class="form-group form-float">
																<div class="form-line">
																	<input type="text" class="form-control"
																		ng-model="coupon.totalcoupan"> <label
																		class="form-label">Total Coupan</label>
																</div>
															</div>
														</div>

														<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
															<div class="form-group form-float">
																<button type="button"
																	class="btn bg-cyan btn-block btn-lg waves-effect"
																	ng-click="couponrequest(coupon);">SUBMIT</button>
															</div>
														</div>
													</div>
												</div>
											</div>

										</div>
									</div>

								</div>
								<!-- ================================================================================================ -->
								<!-- -------------------------------PAN Card Service------------------------------ -->
								<div role="tabpanel" class="tab-pane fade in " id="nsdlpancard">
									<div class="row clearfix">
										<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
											<!-- <div class="header">
												<h2>NSDL Pan Card Service</h2>
											</div> -->
											<div class="body" id="sdiv126">
												<div class="clearfix row">
													<div class="header">
														<h2>
															PAN Card Application <span class="pull-right"></span>
														</h2>
													</div>
													<div class="row clearfix">
														<div
															class="col-lg-12 col-md-12 col-sm-12 col-xs-12 col-lg-offset-0 col-md-offset-0 ">
															<div class="form-group form-float">
																<div class="form-line">
																	<input type="text" class="form-control"
																		ng-model="nsdl.mobile" maxlength="10"> <label
																		class="form-label">Mobile Number</label>
																</div>
															</div>
														</div>
														<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
															<div class="form-group form-float">
																<button type="button"
																	class="btn bg-cyan btn-block btn-lg waves-effect"
																	ng-click="nsdlNumber(nsdl);">SUBMIT</button>
															</div>
														</div>
													</div>

												</div>
											</div>
											<!-- ----  ----- -->
											<div class="body visibility" id="sdiv125">
												<div class="clearfix row">
													<div class="header">
														<h2>
															Enter Customer Details <span class="pull-right"><a
																onclick="sshowDiv('sdiv126')"><i
																	class="fa fa-arrow-circle-left fa-2x"></i></a></span>
														</h2>
													</div>
													<div class="row clearfix">
														<div
															class="col-lg-12 col-md-12 col-sm-12 col-xs-12 col-lg-offset-0 col-md-offset-0 ">
															<div class="form-group form-float">
																<div class="form-line">
																	<select class="form-control" ng-model="nsdl.type"
																		ng-init="nsdl.type='0'"
																		ng-change="getapplication(nsdl,'positionLeftRight');">
																		<option value="0">Select Application Type</option>
																		<option value="NEW">NEW</option>
																		<option value="CORRECTION">CORRECTION</option>
																	</select>

																</div>
															</div>
														</div>
														<div
															class="col-lg-12 col-md-12 col-sm-12 col-xs-12 col-lg-offset-0 col-md-offset-0 "
															ng-bind-html="positionLeftRight" compile-template>

														</div>


														<div
															class="col-lg-4 col-md-4 col-sm-12 col-xs-12 col-lg-offset-0 col-md-offset-0 ">
															<div class="form-group form-float">
																<div class="form-line">
																	<input type="text" class="form-control"
																		ng-model="nsdl.custonamel"
																		ng-change="nsdl.custonamel=nsdl.custonamel.toUpperCase();">
																	<label class="form-label">Customer Last Name</label>
																</div>
															</div>
														</div>


														<div
															class="col-lg-4 col-md-4 col-sm-12 col-xs-12 col-lg-offset-0 col-md-offset-0 ">
															<div class="form-group form-float">
																<div class="form-line">
																	<input type="text" class="form-control"
																		ng-model="nsdl.custonamef"
																		ng-change="nsdl.custonamef=nsdl.custonamef.toUpperCase();">
																	<label class="form-label">Customer First Name</label>
																</div>
															</div>
														</div>


														<div
															class="col-lg-4 col-md-4 col-sm-12 col-xs-12 col-lg-offset-0 col-md-offset-0 ">
															<div class="form-group form-float">
																<div class="form-line">
																	<input type="text" class="form-control"
																		ng-model="nsdl.custonamem"
																		ng-change="nsdl.custonamem=nsdl.custonamem.toUpperCase();">
																	<label class="form-label">Customer Middle Name</label>
																</div>
															</div>
														</div>


														<div
															class="col-lg-12 col-md-12 col-sm-12 col-xs-12 col-lg-offset-0 col-md-offset-0 ">
															<div class="form-group form-float">
																<div class="form-line">
																	<input type="text" class="form-control"
																		ng-model="nsdl.namecard" ng-change=""> <label
																		class="form-label">Name On Card</label>
																</div>
															</div>
														</div>


														<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
															<div class="form-group form-float">
																<div class="form-line">
																	<input type="text" class="form-control"
																		ng-model="nsdl.fathernamef"
																		ng-change="nsdl.fathernamef=nsdl.fathernamef.toUpperCase();">
																	<label class="form-label">Father's First Name</label>
																</div>
															</div>
														</div>
														<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
															<div class="form-group form-float">
																<div class="form-line">
																	<input type="text" class="form-control"
																		ng-model="nsdl.fathernamem"
																		ng-change="nsdl.fathernamem=nsdl.fathernamem.toUpperCase();">
																	<label class="form-label">Father's Middle Name</label>
																</div>
															</div>
														</div>
														<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
															<div class="form-group form-float">
																<div class="form-line">
																	<input type="text" class="form-control"
																		ng-model="nsdl.fathernamel"
																		ng-change="nsdl.fathernamel=nsdl.fathernamel.toUpperCase();">
																	<label class="form-label">Father's Last Name</label>
																</div>
															</div>
														</div>

														<div
															class="col-lg-6 col-md-6 col-sm-12 col-xs-12 col-lg-offset-0 col-md-offset-0 ">
															<div class="form-group form-float">
																<div class="form-line">
																	<input type="text" class="form-control"
																		ng-model="nsdl.mobile" readonly="readonly">
																	<!-- 																	<label class="form-label">Mobile number</label>
 -->
																</div>
															</div>
														</div>

														<div
															class="col-lg-6 col-md-6 col-sm-12 col-xs-12 col-lg-offset-0 col-md-offset-0 ">
															<div class="form-group form-float">
																<div class="form-line">
																	<select class="form-control" ng-model="nsdl.sex"
																		ng-init="nsdl.sex='0'">
																		<option value="0">Select Sex Type</option>
																		<option value="MALE">MALE</option>
																		<option value="FEMALE">FEMALE</option>
																	</select>

																</div>
															</div>
														</div>
														<div
															class="col-lg-6 col-md-6 col-sm-12 col-xs-12 col-lg-offset-0 col-md-offset-0 ">
															<div class="form-group form-float">
																<div class="form-line">
																	<input type="text" class="form-control"
																		ng-model="nsdl.email"
																		ng-change="nsdl.email=nsdl.email.toUpperCase();">
																	<label class="form-label">Customer Email</label>
																</div>
															</div>
														</div>
														<div
															class="col-lg-6 col-md-6 col-sm-12 col-xs-12 col-lg-offset-0 col-md-offset-0 ">
															<div class="form-group form-float">
																<label class="form-label">Date Of Birth</label>
																<div class="form-line">

																	<input type="text" class="form-control" id="dp26"
																		ng-model="nsdl.dob" readonly="readonly">

																</div>
															</div>
														</div>
														<div
															class="col-lg-6 col-md-6 col-sm-12 col-xs-12 col-lg-offset-0 col-md-offset-0 ">
															<div class="form-group form-float">
																<div class="form-line">
																	<input type="text" class="form-control"
																		ng-model="nsdl.idno"
																		ng-change="nsdl.idno=nsdl.idno.toUpperCase();">
																	<label class="form-label">Adhar Number</label>
																</div>
															</div>
														</div>
														<div
															class="col-lg-6 col-md-6 col-sm-12 col-xs-12 col-lg-offset-0 col-md-offset-0 ">
															<div class="form-group form-float">
																<div class="form-line">
																	<input type="text" class="form-control"
																		ng-model="nsdl.pincode"> <label
																		class="form-label">PIN Code</label>
																</div>
															</div>
														</div>

														<div
															class="col-lg-6 col-md-6 col-sm-12 col-xs-12 col-lg-offset-0 col-md-offset-0 ">
															<div class="form-group form-float">
																<div class="form-line">
																	<label class="form-label">District Name</label>
																	<textarea class="form-control" rows="2"
																		style="height: 50px;" ng-model="nsdl.district"
																		ng-change="nsdl.district=nsdl.district.toUpperCase();"
																		ng-change="4"> </textarea>

																</div>
															</div>
														</div>
														<div
															class="col-lg-6 col-md-6 col-sm-12 col-xs-12 col-lg-offset-0 col-md-offset-0 ">
															<div class="form-group form-float">
																<div class="form-line">
																	<label class="form-label">State</label>
																	<textarea class="form-control" rows="2"
																		style="height: 50px;" ng-model="nsdl.state"
																		ng-change="nsdl.state=nsdl.state.toUpperCase();"> </textarea>

																</div>
															</div>
														</div>

														<div
															class="col-lg-12 col-md-12 col-sm-12 col-xs-12 myForm">
															<div class="form-group form-float">
																<div class="form-line">
																	<label class="form-label">Address Line 1</label>
																	<textarea class="form-control" rows="2"
																		style="height: 50px;" ng-model="nsdl.add1"
																		ng-change="nsdl.add1=nsdl.add1.toUpperCase();"> </textarea>

																</div>
															</div>
														</div>
														<!-- <div
															class="col-lg-12 col-md-12 col-sm-12 col-xs-12 myForm">
															<div class="form-group form-float">
																<div class="form-line">
																	<label class="form-label">Address Line 2</label>
																	<textarea class="form-control" rows="2"
																		style="height: 50px;" ng-model="nsdl.add2"
																		ng-change="nsdl.add2=nsdl.add2.toUpperCase();"> </textarea>
																</div>
															</div>
														</div> -->



														<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
															<div class="form-group form-float">
																<div class="form-line">
																	<select class="form-control" ng-model="nsdl.idproof"
																		ng-init="nsdl.idproof='0'">
																		<option value="0">Proof Of Identity</option>

																		<option
																			value="Certificate of Identity signed by a Gazetted Officer">Certificate
																			of Identity signed by a Gazetted Officer</option>
																		<option
																			value="Certificate of Identity signed by a Member of Legislative Assembly">Certificate
																			of Identity signed by a Member of Legislative
																			Assembly</option>
																		<option
																			value="Certificate of Identity signed by a Member of Parliament">Certificate
																			of Identity signed by a Member of Parliament</option>
																		<option
																			value="Certificate of Identity signed by a Municipal Councillor">Certificate
																			of Identity signed by a Municipal Councillor</option>
																		<option value="Driving License">Driving
																			License</option>
																		<option value="Passport">Passport</option>
																		<option value="Arm's license">Arm's license</option>
																		<option value="Central Government Health Scheme Card">Central
																			Government Health Scheme Card</option>
																		<option
																			value="Ex-Servicemen Contributory Health Scheme photo card">Ex-Servicemen
																			Contributory Health Scheme photo card</option>
																		<option
																			value="Bank certificate in Original on letter head from the branch (along with name and stamp of the issuing officer) containing duly attested photograph and bank account number of the applicant">Bank
																			certificate in Original on letter head from the
																			branch (along with name and stamp of the issuing
																			officer) containing duly attested photograph and bank
																			account number of the applicant</option>
																		<option
																			value="Photo identity Card issued by the Central Government or State Government or Public Sector Undertaking">Photo
																			identity Card issued by the Central Government or
																			State Government or Public Sector Undertaking</option>
																		<option
																			value="Pensioner Card having photograph of the applicant">Pensioner
																			Card having photograph of the applicant</option>
																		<option value="Electors photo identity card">Elector's
																			photo identity card</option>
																		<option
																			value="Ration card having photograph of the applicant">Ration
																			card having photograph of the applicant</option>
																		<option
																			value="AADHAAR Card issued by the Unique Identification Authority of India">AADHAAR
																			Card issued by the Unique Identification Authority of
																			India</option>
																	</select>

																</div>
															</div>
														</div>



														<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
															<div class="form-group form-float">
																<div class="form-line">
																	<select class="form-control"
																		ng-model="nsdl.addressproof"
																		ng-init="nsdl.addressproof='0'">
																		<option value="0">Proof Of Address</option>


																		<option value="Latest property tax assessment order">Latest
																			property tax assessment order</option>
																		<option
																			value="Depository account statement (Not more than 3 months old from the date of application)">Depository
																			account statement (Not more than 3 months old from
																			the date of application)</option>
																		<option
																			value="Credit card statement (Not more than 3 months old from the date of application)">Credit
																			card statement (Not more than 3 months old from the
																			date of application)</option>
																		<option
																			value="Bank account statement/passbook (Not more than 3 months old from the date of application)">Bank
																			account statement/passbook (Not more than 3 months
																			old from the date of application)</option>
																		<option
																			value="Landline Telephone Bill (Not more than 3 months old from the date of application)">Landline
																			Telephone Bill (Not more than 3 months old from the
																			date of application)</option>
																		<option
																			value="Certificate of Address signed by a Municipal Councillor">Certificate
																			of Address signed by a Municipal Councillor</option>
																		<option value="Driving License">Driving
																			License</option>
																		<option value="Passport">Passport</option>
																		<option value="Property Registration Document">Property
																			Registration Document</option>
																		<option
																			value="Electricity Bill (Not more than 3 months old from the date of application)">Electricity
																			Bill (Not more than 3 months old from the date of
																			application)</option>
																		<option
																			value="Bank Account Statement in the country of residence (Not more than 3 months old from the date of application)">Bank
																			Account Statement in the country of residence (Not
																			more than 3 months old from the date of application)</option>
																		<option
																			value="NRE bank account statement (Not more than 3 months old from the date of application)">NRE
																			bank account statement (Not more than 3 months old
																			from the date of application)</option>
																		<option value="Employer certificate in original">Employer
																			certificate in original</option>
																		<option value="Electors photo identity card">Elector's
																			photo identity card</option>
																		<option
																			value="Certificate of Address signed by a Gazetted Officer">Certificate
																			of Address signed by a Gazetted Officer</option>
																		<option value="Passport of the spouse">Passport
																			of the spouse</option>
																		<option
																			value="Post office passbook having address of the applicant">Post
																			office passbook having address of the applicant</option>
																		<option
																			value="Domicile certificate issued by the Government">Domicile
																			certificate issued by the Government</option>
																		<option
																			value="Allotment letter of accommodation issued by Central or State Government of not more than three years old">Allotment
																			letter of accommodation issued by Central or State
																			Government of not more than three years old</option>
																		<option
																			value="Certificate of Address signed by a Member of Legislative Assembly">Certificate
																			of Address signed by a Member of Legislative Assembly</option>
																		<option
																			value="Certificate of Address signed by a Member of Parliament">Certificate
																			of Address signed by a Member of Parliament</option>
																		<option
																			value="AADHAAR Card issued by the Unique Identification Authority of India">AADHAAR
																			Card issued by the Unique Identification Authority of
																			India</option>
																		<option
																			value="Consumer gas connection card or book or piped gas bill(Not more than 3 months old from date of application)">Consumer
																			gas connection card or book or piped gas bill(Not
																			more than 3 months old from date of application)</option>
																		<option
																			value="Water Bill (Not more than 3 months old from the date of application)">Water
																			Bill (Not more than 3 months old from the date of
																			application)</option>
																		<option
																			value="Broadband Connection Bill (Not more than 3 months old from the date of application)">Broadband
																			Connection Bill (Not more than 3 months old from the
																			date of application)</option>

																	</select>
																</div>
															</div>
														</div>



														<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
															<div class="form-group form-float">
																<div class="form-line">
																	<select class="form-control" ng-model="nsdl.dobproof"
																		ng-init="nsdl.dobproof='0'">
																		<option value="0">Proof Of DOB</option>
																		<option
																			value="Birth Certificate issued by the Municipal Authority or any office authorized to issue Birth and Death Certificate by the Registrar of Birth and Death of the Indian Consulate">Birth
																			Certificate issued by the Municipal Authority or any
																			office authorized to issue Birth and Death
																			Certificate by the Registrar of Birth and Death of
																			the Indian Consulate</option>
																		<option value="Pension payment order">Pension
																			payment order</option>
																		<option
																			value="Marriage certificate issued by Registrar of Marriages">Marriage
																			certificate issued by Registrar of Marriages</option>
																		<option value="Matriculation certificate">Matriculation
																			certificate</option>
																		<option value="Passport">Passport</option>
																		<option value="Driving License">Driving
																			License</option>
																		<option
																			value="Domicile certificate issued by the Government">Domicile
																			certificate issued by the Government</option>
																		<option
																			value="Affidavit sworn before a magistrate stating the date of birth">Affidavit
																			sworn before a magistrate stating the date of birth</option>
																		<option
																			value="Matriculation Marksheet of recognised board">Matriculation
																			Marksheet of recognised board</option>
																		<option
																			value="AADHAAR Card issued by the Unique Identification Authority of India">AADHAAR
																			Card issued by the Unique Identification Authority of
																			India</option>
																		<option value="Electors photo identity card">Elector's
																			photo identity card</option>
																		<option
																			value="Photo identity Card issued by the Central Government or State Government or Public Sector Undertaking">Photo
																			identity Card issued by the Central Government or
																			State Government or Public Sector Undertaking</option>
																		<option value="Central Government Health Scheme Card">Central
																			Government Health Scheme Card</option>
																		<option
																			value="Ex-Servicemen Contributory Health Scheme photo card">Ex-Servicemen
																			Contributory Health Scheme photo card</option>


																	</select>

																</div>
															</div>
														</div>


														<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
															<div class="form-group form-float">
																<button type="button"
																	class="btn bg-cyan btn-block btn-lg waves-effect"
																	ng-click="nsdladd(nsdl);">SUBMIT</button>
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
								<!-- ==========================================Nav Tab End====================================================== -->
							</div>
						</div>
					</div>
				</div>
				<!-- #END# Tabs With Only Icon Title -->

			<%-- <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 p-l-0 p-r-50"
				id="sideSticker">
				<div class="row  card">
					<div class="header p-t-0 p-b-0 text-center">
						<h2>Bank Account Opening</h2>
					</div>
				<div class="row">
					<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
						<div class="link-card panel bg-lime">
							<div class="col-md-12">
								<img src="assets/images/Kotak_Mahindra_Bank_logo.png"
									class="img-responsive navTabIconImgGst"
									style="padding-top: 5%; padding-bottom: 0%; width: 106%; margin-left: 0%; margin-bottom: 0%;">
							</div>
							<div
								class="p-l-10 p-t-5 p-r-10 col-md-12 col-white m-t-20 card text-center"
								style="margin-top: 31px;">
								<a
									href="https://m.kotak.com/811/?Source=Website&Banner=herobanner&pubild=gaana#/login"
									target="_blank"> <b>Apply Now</b>
								</a>
							</div>
						</div>
					</div>

					<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
					
						<div class="link-card panel bg-blue">
							<div class="col-md-12">
								<img src="assets/images/RBL-logo.jpg"
									class="img-responsive navTabIconImgGst"
									style="width: 100%; margin-left: 0%;">
							</div>
							<div
								class="p-l-10 p-t-5 p-r-10 col-md-12 col-white m-t-20 card text-center"
								style="margin-top: 12px;">
								<a
									href="https://abacus.rblbank.com/rbl/DigiAqui/default/index.html#one"
									target=""> <b>Apply Now</b>
								</a>
							</div>
						</div>
					</div>
				</div>
				
				
				
				<div class="row">
					<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
						<div class="link-card panel bg-cyan">
							<div class="col-md-12">
								<img src="assets/images/yes_bank.png"
									class="img-responsive navTabIconImgGst"
									style="width: 100%; margin-left: 0%">
							</div>
							<div
								class="p-l-10 p-t-5 p-r-10 col-md-12 col-white m-t-20 card text-center"
								style="margin-top: 7px;">
								<a
									href="http://campaigns.yesbank.in/savings-account/?utm_source=affiliate&camp=mailer_Leadsurf"
									target=""> <b>Apply Now</b>
								</a>
							</div>
						</div>
					</div>


					<div class="col-lg-6  col-md-6 col-sm-12 col-xs-12">
						<div class="link-card panel bg-cyan">
							<div class="col-md-12">
								<img src="assets/images/HDFC_Bank_Logo.png"
									class="img-responsive navTabIconImgGst"
									style="width: 100%; margin-left: 0%">
							</div>
							<div
								class="p-l-10 p-t-5 p-r-10 col-md-12 col-white m-t-20 card text-center"
								style="margin-top: 20px;">
								<a
									href="https://apply.hdfcbank.com/SavingsAccount/Login?utm_campaign=P4_hp_AppOnl_SA&utm_content=Content&utm_medium=Medium&utm_source=sSource"
									target="_blank"> <b>Apply Now</b>
								</a>
							</div>
						</div>
					</div>
					</div>

					<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
						<div class="link-card panel bg-cyan">
							<div class="col-md-12">
								<img src="assets/images/adhar.png"
									class="img-responsive navTabIconImgGst"
									style="width: 100%; margin-left: 0%">
							</div>
							<div
								class="p-l-10 p-t-5 p-r-10 col-md-12 col-white m-t-20 card text-center"
								style="margin-top: 15x;">
								<a href="http://aadharhousing.com/apply-for-loan.php"
									target="_blank"> <b>Apply Now</b>
								</a>
							</div>
						</div>
					</div>

						<!-- <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
						<div class="link-card panel bg-cyan">
							<div class="col-md-12">
								<img src="assets/images/nsdl.png"
									class="img-responsive navTabIconImgGst"
									style="width: 100%; margin-left: 0%">
							</div>
							<div
								class="p-l-10 p-t-5 p-r-10 col-md-12 col-white m-t-20 card text-center"
								style="margin-top: 29px;">
								<a href="https://tin.tin.nsdl.com/pantan/StatusTrack.html"
									target="_blank"> <b>StatusTrack</b>
								</a>
							</div>
						</div>
					</div> -->
					
					<%
					if (user.getWlId().equalsIgnoreCase("DISTRIBUTOR")) {
					%>
					<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
						<div class="link-card panel bg-cyan">
							<div class="col-md-12">
								<img src="assets/images/axis-bank.png"
									class="img-responsive navTabIconImgGst" style="width: 48%;">
							</div>
							<div
								class="p-l-10 p-t-5 p-r-10 col-md-12 col-white m-t-20 card text-center"
								style="margin-top: 7px;">
								<a
									href="https://digital.axisbank.co.in/AxisDigitalBanking/html_pages/main.html"
									target="_blank"> <b>Apply Now</b>
								</a>
							</div>
						</div>
					</div>
					<%
						}
					%>


					<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
						<div class="link-card panel bg-cyan">
							<div class="col-md-12">
								<img src="assets/images/uti.png"
									class="img-responsive navTabIconImgGst"
									style="width: 100%; margin-left: 0%">
							</div>
							<div
								class="p-l-10 p-t-5 p-r-10 col-md-12 col-white m-t-20 card text-center"
								style="margin-top: 20px;">
								<a href="https://www.trackpan.utiitsl.com/PANONLINE/#forward"
									target="_blank"> <b>StatusTrack</b>
								</a>
							</div>
						</div>
					</div>


					<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
						<div class="link-card panel bg-cyan">
							<div class="col-md-12">
								<img src="assets/images/ind.png"
									class="img-responsive navTabIconImgGst"
									style="width: 100%; margin-left: 0%">
							</div>
							<div
								class="p-l-10 p-t-5 p-r-10 col-md-12 col-white m-t-20 card text-center"
								style="margin-top: 20px;">
								<a href="https://www.happyloans.net/apply.php" target="_blank">
									<b>happy loan </b>
								</a>
							</div>
						</div>
					</div>

					<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
						<div class="link-card panel bg-cyan">
							<div class="col-md-12">
								<img src="assets/images/ind.png"
									class="img-responsive navTabIconImgGst"
									style="width: 100%; margin-left: 0%">
							</div>
							<div
								class="p-l-10 p-t-5 p-r-10 col-md-12 col-white m-t-20 card text-center"
								style="margin-top: 20px;">
								<a href="https://www.indiashelter.in/" target="_blank"> <b>home
										loan</b>
								</a>
							</div>
						</div>
					</div>

					
					
					<!--<div class="col-lg-6  col-md-6 col-sm-12 col-xs-12">
						<div class="link-card panel bg-cyan">
							<div class="col-md-12">
								<img src="assets/images/Pay.png"
									class="img-responsive navTabIconImgGst"
									style="width: 100%; margin-left: 0%">
							</div>
							<div
								class="p-l-10 p-t-5 p-r-10 col-md-12 col-white m-t-20 card text-center"
								style="margin-top: 20px;">
								<a
									href="https://www.payumoney.com/paybypayumoney/#/9090411D9CAEA2757AA29583DE89B21F"
									target="_blank"> <b>Apply Now</b>
								</a>
							</div>
						</div>
					</div>-->
				</div>
			</div> --%>
		</div>

		<div id="div101" class="row clearfix" style="display: none;">
			<iframe src="report" name="admin_report" style="width:100%;height: 100vh;"></iframe>
		</div>


		<!-------------- Div2 ---------------------->
		<div id="div2" class="row clearfix" style="display: none">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<div class="card">
					<div class="header">
						<h2>VIEW PROFILE</h2>
					</div>
					<div class="body">
						<div class="body">
							<form class="form-horizontal">
								<div class="row clearfix">
									<div class="col-md-6 col-lg-6 col-sm-12 col-xs-12">
										<div
											class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
											<label for="email_address_2">Name</label>
										</div>
										<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
											<div class="form-group">
												<div class="form-line1">
													<input type="text" class="form-control"
														ng-model="userDetails.name" placeholder="Enter your Name">
												</div>
											</div>
										</div>
									</div>
									<div class="col-md-6 col-lg-6 col-sm-12 col-xs-12">
										<div
											class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
											<label for="password_2">Firm Name</label>
										</div>
										<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
											<div class="form-group">
												<div class="form-line1">
													<input type="text" class="form-control"
														ng-model="userDetails.firmName"
														placeholder="Enter your Firm Name">
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="row clearfix">
									<div class="col-md-6 col-lg-6 col-sm-12 col-xs-12">
										<div
											class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
											<label for="email_address_2">Mobile Number</label>
										</div>
										<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
											<div class="form-group">
												<div class="form-line1">
													<input type="text" ng-model="userDetails.mobile"
														class="form-control"
														placeholder="Enter your Mobile Number"
														ng-keypress="filterValue($event);" maxlength="10"
														disabled="disabled">
												</div>
											</div>
										</div>
									</div>
									<div class="col-md-6 col-lg-6 col-sm-12 col-xs-12">
										<div
											class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
											<label for="">E-Mail</label>
										</div>
										<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
											<div class="form-group">
												<div class="form-line1">
													<input type="text" class="form-control"
														ng-model="userDetails.email"
														placeholder="Enter your E-Mail">
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="row clearfix">
									<div class="col-md-6 col-lg-6 col-sm-12 col-xs-12">
										<div
											class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
											<label for="email_address_2">Address</label>
										</div>
										<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
											<div class="form-group">
												<div class="form-line1">
													<input type="text" ng-model="userDetails.address"
														class="form-control" placeholder="Enter your Address">
												</div>
											</div>
										</div>
									</div>
									<div class="col-md-6 col-lg-6 col-sm-12 col-xs-12">
										<div
											class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
											<label for="password_2">PIN Code</label>
										</div>
										<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
											<div class="form-group">
												<div class="form-line1">
													<input type="text" class="form-control"
														ng-model="userDetails.pinCode"
														placeholder="Enter your PIN Code"
														ng-keypress="filterValue($event);">
												</div>
											</div>
										</div>
									</div>
								</div>

								<div class="row clearfix">
									<div class="col-md-6 col-lg-6 col-sm-12 col-xs-12">
										<div
											class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
											<label for="password_2">State</label>
										</div>
										<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
											<div class="form-group">
												<div class="ui-widget form-line1">
													<input type="text" class=" tags form-control"
														ng-model="userDetails.state">
												</div>
											</div>
										</div>
									</div>
									<div class="col-md-6 col-lg-6 col-sm-12 col-xs-12">
										<div
											class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
											<label for="password_2">City</label>
										</div>
										<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
											<div class="form-group">
												<div class="form-line1">
													<input type="text" id="" class="form-control"
														ng-model="userDetails.city"
														placeholder="Enter your PIN Code">
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="row clearfix">
									<div
										class="col-lg-offset-2 col-md-offset-2 col-sm-offset-4 col-xs-offset-5">
										<button type="button"
											class="btn btn-primary m-t-15 waves-effect"
											ng-click="editProfile(userDetails);">UPDATE</button>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-------------- /Div2 ---------------------->
		
		<!-------------- Div3 ---------------------->
		<div id="div3" class="row clearfix" style="display: none">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<div class="card">
					<div class="header">
						<h2>CHANGE PASSWORD</h2>
					</div>
					<div class="body">
						<div class="body">
							<form class="form-horizontal">
								<div class="row clearfix">
									<div
										class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
										<label for="email_address_2">Old Password</label>
									</div>
									<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
										<div class="form-group">
											<div class="form-line">
												<input type="password" class="form-control"
													ng-model="changePass.oldPassword"
													placeholder="Enter your First Name">
											</div>
										</div>
									</div>
								</div>
								<div class="row clearfix">
									<div
										class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
										<label for="password_2">New Password</label>
									</div>
									<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
										<div class="form-group">
											<div class="form-line">
												<input type="password" ng-model="changePass.newPassword"
													class="form-control" placeholder="Enter your Last Name">
											</div>
										</div>
									</div>

								</div>
								<div class="row clearfix">
									<div
										class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
										<label for="email_address_2">Confirm Password</label>
									</div>
									<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
										<div class="form-group">
											<div class="form-line">
												<input type="password" ng-model="changePass.cnfrmPassword"
													class="form-control" placeholder="Enter your Mobile Number">
											</div>
										</div>
									</div>
								</div>

								<div class="row clearfix">
									<div
										class="col-lg-offset-5 col-md-offset-5 col-sm-offset-5 col-xs-offset-5">
										<button type="button"
											class="btn btn-primary m-t-15 waves-effect"
											ng-click="changePassWord();">Update</button>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-------------- /Div3 ---------------------->
		<!-------------- Div4 ---------------------->
		<div id="div4" class="row clearfix" style="display: none">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<div class="card">
					<div class="header">
						<h2>Add User</h2>
					</div>
					<div class="body">
						<div class="body">
							<form class="form-horizontal">
								<div class="row clearfix">
									<div class="col-md-6 col-lg-6 col-sm-12 col-xs-12">
										<div
											class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
											<label for="email_address_2">Name</label>
										</div>
										<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
											<div class="form-group">
												<div class="form-line1">
													<input type="text" ng-model="addUserDetails.name"
														class="form-control" placeholder="Enter your Name"
														ng-change="addUserDetails.name=addUserDetails.name.toUpperCase();">
												</div>
											</div>
										</div>
									</div>
									<div class="col-md-6 col-lg-6 col-sm-12 col-xs-12">
										<div
											class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
											<label for="password_2">Firm Name</label>
										</div>
										<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
											<div class="form-group">
												<div class="form-line1">
													<input type="text" class="form-control"
														ng-model="addUserDetails.firmName"
														placeholder="Enter your Firm Name"
														ng-change="addUserDetails.firmName=addUserDetails.firmName.toUpperCase();">
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="row clearfix">
									<div class="col-md-6 col-lg-6 col-sm-12 col-xs-12">
										<div
											class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
											<label for="email_address_2">Mobile Number</label>
										</div>
										<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
											<div class="form-group">
												<div class="form-line1">
													<input type="text" class="form-control"
														ng-model="addUserDetails.mobile"
														ng-change="checkUniqueUser(addUserDetails.mobile);"
														placeholder="Enter your Mobile Number"
														ng-keypress="filterValue($event);" ng-minlength="10"
														maxlength="10">
												</div>
											</div>
										</div>
									</div>
									<div class="col-md-6 col-lg-6 col-sm-12 col-xs-12">
										<div
											class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
											<label for="">E-Mail</label>
										</div>
										<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
											<div class="form-group">
												<div class="form-line1">
													<input type="text" class="form-control"
														ng-model="addUserDetails.email"
														ng-change="checkUniqueUser(addUserDetails.email);"
														placeholder="Enter your E-Mail"
														ng-change="addUserDetails.email=addUserDetails.email.toUpperCase();">
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="row clearfix">
									<div class="col-md-6 col-lg-6 col-sm-12 col-xs-12">
										<div
											class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
											<label for="email_address_2">Address</label>
										</div>
										<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
											<div class="form-group">
												<div class="form-line1">
													<input type="text" ng-model="addUserDetails.address"
														class="form-control" placeholder="Enter your Address"
														ng-change="addUserDetails.address=addUserDetails.address.toUpperCase();">
												</div>
											</div>
										</div>
									</div>
									<div class="col-md-6 col-lg-6 col-sm-12 col-xs-12">
										<div
											class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
											<label for="password_2">PIN Code</label>
										</div>
										<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
											<div class="form-group">
												<div class="form-line1">
													<input type="text" class="form-control"
														ng-model="addUserDetails.pinCode"
														placeholder="Enter your PIN Code"
														ng-keypress="filterValue($event);" maxlength="10">
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="row clearfix">
									<div class="col-md-6 col-lg-6 col-sm-12 col-xs-12">
										<div
											class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
											<label for="password_2">State</label>
										</div>
										<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
											<div class="form-group">
												<div class="ui-widget form-line1">
													<input type="text" ng-model="addUserDetails.state"
														class="tags form-control"
														ng-change="addUserDetails.state=addUserDetails.state.toUpperCase();">
												</div>
											</div>
										</div>
									</div>
									<div class="col-md-6 col-lg-6 col-sm-12 col-xs-12">
										<div
											class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
											<label for="password_2">City</label>
										</div>
										<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
											<div class="form-group">
												<div class="form-line1">
													<input type="text" ng-model="addUserDetails.city"
														class="form-control" placeholder="Enter  City"
														ng-change="addUserDetails.city=addUserDetails.city.toUpperCase();">
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="row clearfix">
									<div
										class="col-lg-offset-2 col-md-offset-2 col-sm-offset-4 col-xs-offset-5">
										<button type="button"
											class="btn btn-primary m-t-15 waves-effect"
											ng-click="addNewUser(addUserDetails);">Add User</button>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-------------- /Div4 ---------------------->
		<!-------------- Div5 ---------------------->
		<div id="div5" class="row clearfix" style="display: none">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<div class="card">
					<div class="header">
						<h2>Add Balance</h2>
					</div>
					<div class="body">
						<form class="form-horizontal">
							<div class="row clearfix">
								<div
									class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
									<label for="email_address_2">User Type </label>
								</div>
								<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
									<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
										<select class="form-control" ng-model="addBalance.userType"
											ng-change="getUserByUserType(addBalance.userType,'getUserForAddBalanceDiv','addBalance')">
											<option value="">-- Please select --</option>
											<option ng-repeat="userType in userType"
												ng-if="userType.type > userDetails.roleId"
												value="{{userType.type}}">{{userType.role}}</option>
										</select>
									</div>
								</div>
							</div>
							<div class="row clearfix">
								<div
									class="col-lg-8 col-md-8 col-sm-8 col-xs-8 col-lg-offset-2 col-md-offset-2 "
									style="margin-left: 12%;">
									<div ng-bind-html="getUserForAddBalanceDiv" compile-template></div>
								</div>
							</div>
							<div class="row clearfix">
								<div
									class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
									<label for="email_address_2">Amount</label>
								</div>
								<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
									<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 m-l-15">
										<div class="form-group form-float">
											<div class="form-line">
												<input type="text" class="form-control text-center"
													ng-model="addBalance.amount"
													ng-keypress="filterValue($event);" placeholder="0.0">
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="row clearfix">
								<div
									class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
									<label for="email_address_2">Remarks</label>
								</div>
								<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
									<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 m-l-15">
										<div class="form-group form-float">
											<div class="form-line">
												<input type="text" class="form-control text-center"
													ng-model="addBalance.remarks" placeholder="Remark">
											</div>
										</div>
									</div>
								</div>
							</div>

							<div class="row clearfix">
								<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 "></div>
								<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
									<div class="col-md-offset-3">
										<button type="button" class="btn btn-primary waves-effect"
											ng-click="addBalanceByUser(addBalance);">Update</button>
									</div>
								</div>
							</div>
						</form>
					</div>

				</div>
			</div>
		</div>
		<!-------------- /Div5 ---------------------->
		<!-------------- Div6 ---------------------->
		<div id="div6" class="row clearfix" style="display: none">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<div class="card">
					<div class="header">
						<h2>
							Add Package<span class="pull-right"><a href="#"
								onClick="showDiv('div7')" class="body"><i
									class="material-icons">arrow_back</i></a></span>
						</h2>
					</div>
					<div class="body">
						<div class="body">
							<form class="form-horizontal">
								<div class="row clearfix">
									<div
										class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label text-right">
										<label for="email_address_2">Select Service Type</label>
									</div>
									<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
										<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
											<select class="form-control" ng-model="package.type" ng-init="package.type='0'">
											<option value="0">Please Select Package Type</option>
												<option value="Recharge">Recharge</option>
												<option value="Flight">Flight</option>
												<option value="Bus">Bus</option>
												<option value="Pan">Pan</option>
												<option value="YesBank AEPS">YesBank AEPS</option>
												<option value="MicroATM">MicroATM</option>
												<option value="Encore AEPS">AEPS</option>
												<option value="AadharPay">AadharPay</option>
												<option value="DMR">DMR</option>
											</select>
										</div>
									</div>
								</div>
								<div class="row clearfix">
									<div
										class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label text-right">
										<label for="email_address_2">Package Name</label>
									</div>
									<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
										<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 m-l-20"
											style="margin-left: 4%;">
											<div class="form-group">
												<div class="form-group form-float">
													<div class="form-line">
														<input type="text" ng-model="package.name"
															class="form-control" placeholder="Enter Package Name" />
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="row clearfix">
									<div
										class="col-lg-offset-6 col-md-offset-6 col-sm-offset-6 col-xs-offset-6">
										<button type="button"
											class="btn btn-primary m-t-15 waves-effect"
											ng-click="packageCreate(package);">Submit</button>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-------------- /Div6 ---------------------->

		<!-------------- Div7 ---------------------->
		<div id="div7" class="row clearfix" style="display: none">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<div class="card">
					<div class="header">
						<h2>Package Management</h2>
					</div>
					<div class="body">
						<div class="row clearfix">
							<div
								class="col-lg-12 col-md-12 col-sm-12 col-xs-12 form-control-label">
								<div class="body1">
									<div class="row">
										<%
											if (user.getRoleId() != 5) {
										%>
										<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
											<!-- <button class="btn btn-info m-b-5 col-md-2"
												onclick="showDiv('div6');">
												<i class="fa fa-plus" style="color: #fff !important;"></i>&nbsp;
												<span>Add Package</span>
											</button>
											<button class="btn btn-info m-b-5 m-l-30 col-md-2"
												onclick="showDiv('div8');">
												<i class="fa fa-file-text " style="color: #fff !important;"></i>&nbsp;
												<span>Assign Package</span>
											</button>
											<button class="btn btn-info m-b-5 m-l-30 col-md-2"
												ng-click="viewMyPackage()">
												<i class="fa fa-file-text " style="color: #fff !important;"></i>&nbsp;
												<span>View My Package</span>
											</button>
											<button class="btn btn-info m-b-5 m-l-30 col-md-3"
												ng-click="viewAssignPackage()">
												<i class="fa fa-file-text " style="color: #fff !important;"></i>&nbsp;
												<span>View Assigned Package</span>
											</button> -->
											<%
												}
											%>
											<button class="btn btn-info m-b-5 m-l-30 col-md-2"
												ng-click="myassignedPackage()">
												<i class="fa fa-file-text " style="color: #fff !important;"></i>&nbsp;
												<span>Assign My Package</span>
											</button>
										</div>
									</div>

									<!-- ------------------- My Package ----------------- -->
									<div class="row">
										<div
											class="col-lg-12 col-md-12 col-sm-12 col-xs-12 visibility"
											id="sdiv50">
											<table id="datatable1"
												class="table table-striped table-bordered">
												<thead>
													<tr>
														<th class="success text-center">Package Id</th>
														<th class="success text-center">Service Type</th>
														<th class="success text-center">Package Name</th>
														<th class="success text-center">View</th>
														<th class="success text-center">Delete</th>
													</tr>
												</thead>


												<tbody>
													<tr ng-repeat="myPack in packageDetail">
														<td class="text-center">{{myPack.package_id}}</td>
														<td class="text-center">{{myPack.service_type}}</td>
														<td class="text-center">{{myPack.package_name}}</td>


														<td class="text-center">
															<button class="btn btn-success"
																ng-click="viewMyPackagechargeDetail(myPack)"
																style="background: #228B22 !important;">View</button>
														</td>
														<td class="text-center">
															<button class="btn btn-success"
																ng-click="DeleteMyPackageDetail(myPack)"
																style="background: #228B22 !important;">Delete</button>
														</td>
													</tr>
												</tbody>
											</table>
										</div>
										<!-- ------------------- Assign Package ----------------- -->
										<div
											class="col-lg-12 col-md-12 col-sm-12 col-xs-12 visibility"
											id="sdiv51">
											<table id="datatable1"
												class="table table-striped table-bordered">
												<thead>
													<tr>

														<th class="success text-center">Package Id</th>
														<th class="success text-center">Service Type</th>
														<th class="success text-center">View</th>
													</tr>
												</thead>


												<tbody>
													<tr ng-repeat="Assignedpack in Assignedpackdetails">
														<td class="text-center">{{Assignedpack.package_id}}</td>
														<td class="text-center" ng-if="Assignedpack.service_type!='Encore AEPS'">{{Assignedpack.service_type}}</td>
                                                           <td class="text-center" ng-if="Assignedpack.service_type==='Encore AEPS'">AEPS</td>
														<td class="text-center">
															<button class="btn btn-success"
																ng-click="viewAssignedPackage(Assignedpack)"
																style="background: #228B22 !important;">Open</button>
														</td>
													</tr>
												</tbody>
											</table>
										</div>
										<!-- ------------------- View Package Details ----------------- -->
										<div class="row visibility" id="sdiv52">
											<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 ">
												<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
													<div class="card">
														<div class="header" style="position: absolute;">
															<h2>View Assigned Package Details</h2>
														</div>
														<div class="body">
															<div class="row clearfix m-t-30">
																<div
																	class="col-lg-12 col-md-12 col-sm-12 col-xs-12 form-control-label">
																	<div class="body1">
																		<div class="row">
																			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
																				<table id="datatable1"
																					class="table table-striped table-bordered">
																					<thead>
																						<tr>
																							<th class="success text-center">Name</th>
																							<th class="success text-center">UserId</th>
																							<th class="success text-center">RoleId</th>
																							<th class="success text-center">Package</th>
																							<th class="success text-center">Edit</th>
																						</tr>
																					</thead>
																					<tbody>
																						<tr ng-repeat="packvw in ViewPackageDetail">
																							<td class="text-center">{{packvw.user_name}}<br>({{packvw.mobile}})
																							</td>
																							<td class="text-center">{{packvw.user_id}}</td>
																							<td class="text-center"><span
																								ng-if="packvw.role_id == 2">White Label</span> <span
																								ng-if="packvw.role_id == 3">SUPER
																									DISTRIBUTOR</span> <span ng-if="packvw.role_id == 4">DISTRIBUTOR</span>
																								<span ng-if="packvw.role_id == 5">RETAILER</span>
																								<span ng-if="packvw.role_id == 100">API
																									USER</span> ({{packvw.role_id}})</td>
																							<td class="text-center">{{packvw.package_name}}<br>({{packvw.package_id}})
																							</td>
																							<td class="text-center"><button
																									type="button" data-toggle="modal"
																									data-target="#modalEditPackage"
																									class="btn btn-primary m-t-15 waves-effect"
																									ng-click="editpk(packvw);">EDIT</button></td>
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
										<!-- ----------------------------------- Sdiv End----------------------------------------- -->
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-------------- /Div7 ---------------------->
		<!-- ------------------------------------- modalEditPackage-------------------------------------- -->

		<!-- Modal -->
		<div id="modalEditPackage" class="modal fade" role="dialog"
			tabindex="-1" aria-labelledby="modalLabel" aria-hidden="true">
			<div class="modal-dialog">

				<!-- Modal content-->
				<div class="modal-content" style="width: 43%; left: 18%;">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">Edit Assign Package</h4>
					</div>
					<div class="modal-body">
						<div class="row clearfix">
							<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
								<div
									class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label ">
									<label for="comment">User Name: </label>
								</div>
								<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
									<input type="text" ng-model="edpkg.user_name"
										class="form-control" placeholder="Enter Remarks">
								</div>
							</div>
							<div class="col-md-12">
								<input type="hidden" ng-model="edpkg.role_id"
									class="form-control" placeholder="Enter ptid"> <input
									type="hidden" ng-model="edpkg.user_id" class="form-control"
									placeholder="Enter tid">
							</div>
						</div>
						<div class="row clearfix m-t-20">
							<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 ">
								<div
									class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label ">
									<label for="email_address_2">Select Package</label>
								</div>
								<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
									<select class="form-control " ng-model="edpkg.package">
										<option value="">-- Please select Package Type--</option>
										<option ng-repeat="package in packageDetails"
											value="{{package.service_type}},{{package.package_id}}">{{package.package_name}}</option>
									</select>
								</div>
							</div>
						</div>
						<div class="row clearfix">
							<div class="" style="margin-top: 10%">
								<center>
									<input type="submit" class="btn btn-info" data-dismiss="modal"
										ng-click="assignEditPackage(edpkg);">
								</center>
							</div>
						</div>
					</div>
					<div class="modal-footer"></div>
				</div>

			</div>
		</div>

		<!-- ------------------------------------- /modalEditPackage -------------------------------------- -->
		<!-------------- Div8 ---------------------->
		<div id="div8" class="row clearfix" style="display: none">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<div class="card">
					<div class="header">
						<h2>
							Assign Package<span class="pull-right"><a href="#"
								onClick="showDiv('div7')" class="body"><i
									class="material-icons">arrow_back</i></a></span>
						</h2>
					</div>
					<div class="body">
						<div class="body">
							<form class="form-horizontal">
								<div class="row clearfix">
									<div
										class="col-lg-8 col-md-8 col-sm-8 col-xs-8 col-lg-offset-2 col-md-offset-2 "
										style="margin-left: 12%;">
										<div
											class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label ">
											<label for="email_address_2">Usertype </label>
										</div>
										<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
											<select class="form-control " ng-model="pack.userType"
												ng-change="getUserByUserType(pack.userType,'getUserForpack','pack')">
												<option value="">-- Please select User Type--</option>
												<%
													if (user.getRoleId() == 3) {
												%>
												<option ng-repeat="userType in userType"
													ng-if="userType.type == 4" value="{{userType.type}}">{{userType.role}}</option>
												<%
													} else {
												%>
												<option ng-repeat="userType in userType"
													ng-if="userType.type == 5" value="{{userType.type}}">{{userType.role}}</option>
												<%
													}
												%>
											</select>
										</div>
									</div>
								</div>
								<div class="row clearfix">
									<div
										class="col-lg-8 col-md-8 col-sm-8 col-xs-8 col-lg-offset-2 col-md-offset-2 "
										style="margin-left: 12%;">
										<div ng-bind-html="getUserForpack" compile-template></div>
									</div>
								</div>
								<div class="row clearfix">
									<div
										class="col-lg-8 col-md-8 col-sm-8 col-xs-8 col-lg-offset-2 col-md-offset-2 "
										style="margin-left: 12%;">
										<div
											class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label ">
											<label for="email_address_2">Select Package</label>
										</div>
										<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
											<select class="form-control " ng-model="pack.package">
												<option value="">-- Please select User Type--</option>
												<option ng-repeat="package in packageDetails"
													value="{{package.service_type}},{{package.package_id}}">{{package.package_name}}</option>
											</select>

										</div>
									</div>
								</div>
								<div class="row clearfix">
									<div
										class="col-lg-offset-6 col-md-offset-6 col-sm-offset-6 col-xs-offset-6">
										<button type="button"
											class="btn btn-primary m-t-15 waves-effect"
											ng-click="assignPackage(pack)">Submit</button>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-------------- /Div8 ---------------------->
		

		<!-------------- Div9 ---------------------->
		<div id="div9" class="row clearfix" style="display: none">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 ">
				<div class="card">
					<div class="header">
						<h2>Balance Request</h2>
					</div>
					<div class="body">
						<%
							if (user.getRoleId() != 3 && user.getRoleId() != 100) {
						%>
						<div class="row clearfix">
							<div
								class="col-lg-12 col-md-12 col-sm-12 col-xs-12 col-lg-offset-2 col-md-offset-2 ">
								<div class="icon-and-text-button-demo">
									<button id="payWallet" type="button"
										class="btn btn-primary waves-effect"
										style="width: 22.666667%;"
										onclick="tabShowHide('uplineDiv', 'walletDiv')">
										<i class="material-icons">settings_phone</i> <span>Wallet Refill</span>
									</button>
								
									<button id="payMobile" type="button"
										class="btn btn-primary waves-effect"
										style="width: 22.666667%;"
										onclick="tabShowHide('uplineDiv', 'adminDiv')">
										<i class="material-icons">settings_phone</i> <span>Balance
											Request To Admin</span>
									</button>
									<button id="payLandline" type="button"
										class="btn btn-primary waves-effect"
										style="width: 22.666667%;"
										onclick="tabShowHide('walletDiv', 'uplineDiv')">
										<i class="material-icons">phone_android</i> <span>Balance
											Request To Upline</span>
									</button>
								</div>
							</div>
						</div>
						<%
							}
						%>
						<!-- -------------------------Wallet Refill----------------------------- -->
						<div class="tab-pane fade in active" id="walletDiv">
							<div class="row clearfix">
								
								<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
									<div
										class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
										<label for="email_address_2">Amount</label>
									</div>
									<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
										<div class="form-group form-float">
											<div class="form-line">
												<input type="text" class="form-control"
													placeholder="Enter Amount" ng-model="walletRefill.amount"
													ng-keypress="filterValue($event);">
											</div>
										</div>
									</div>
								</div>			
								<div class="col-sm-12">
									<div class="form-group form-float">
										<button type="button"
											class="btn bg-cyan btn-block btn-lg waves-effect"
											ng-click="walletRefillRequest(walletRefill);">SUBMIT</button>
									</div>
								</div>
							</div>
						</div>
						<!-- ------------------------ Wallet refill End----------------------------- -->
						
						<!-- -------------------------Balance Request To Admin----------------------------- -->
						 <div class="tab-pane fade in active" id="adminDiv">
							<div class="row clearfix">
								<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
									<div
										class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
										<label for="email_address_2">Bank Name</label>
									</div>
									<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
										<div class="form-group form-float">
											<div class="form-line">
												<select class="form-control show-tick"
													ng-model="balReq1.bankName"
													ng-change="getBankDetails(balReq1.bankName);">
													<option value="">-- Select Bank --</option>
													<option ng-repeat="item in viewBankDetails"
														value="{{item.id}}">{{item.bankName}}</option>
												</select>
											</div>
										</div>
									</div>
								</div>
								<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
									<div
										class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
										<label for="email_address_2">Branch Name</label>
									</div>
									<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
										<div class="form-group form-float">
											<div class="form-line">
												<input type="text" class="form-control text-center"
													ng-model="balBank.branchName" disabled="disabled">
											</div>
										</div>
									</div>
								</div>
								<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
									<div
										class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
										<label for="email_address_2">Account Name</label>
									</div>
									<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
										<div class="form-group form-float">
											<div class="form-line">
												<input type="text" class="form-control text-center"
													ng-model="balBank.accName" disabled="disabled">
											</div>
										</div>
									</div>
								</div>
								<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
									<div
										class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
										<label for="email_address_2">Account Number</label>
									</div>
									<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
										<div class="form-group form-float">
											<div class="form-line">
												<input type="text" class="form-control text-center"
													ng-model="balBank.accNo" disabled="disabled">
											</div>
										</div>
									</div>
								</div>

								<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
									<div
										class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
										<label for="email_address_2">IFSC Code</label>
									</div>
									<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
										<div class="form-group form-float">
											<div class="form-line">
												<input type="text" class="form-control text-center"
													ng-model="balBank.ifscCode" disabled="disabled">
											</div>
										</div>
									</div>
								</div>
								<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
									<div
										class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
										<label for="email_address_2">Select Payment Mode</label>
									</div>
									<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
										<div class="form-group form-float">
											<div class="form-line">
												<select class="form-control" ng-model="balBank.paymentMode">
													<option value="">-- Select --</option>
													<option value="IMPS">IMPS</option>
													<option value="NEFT">NEFT</option>
													<option value="RTGS">RTGS</option>
													<option value="CASH">CASH</option>
													<option value="CHEQUE">CHEQUE</option>
												</select>
											</div>
										</div>
									</div>
								</div>

								<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
									<div
										class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
										<label for="email_address_2">Traansaction ID</label>
									</div>
									<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
										<div class="form-group form-float">
											<div class="form-line">
												<input type="text" class="form-control"
													placeholder="Enter Traansaction ID"
													ng-model="balBank.transactionId"
													ng-keypress="filterValue($event);" />
											</div>
										</div>
									</div>
								</div>

								<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
									<div
										class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
										<label for="email_address_2">Amount</label>
									</div>
									<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
										<div class="form-group form-float">
											<div class="form-line">
												<input type="text" class="form-control"
													placeholder="Enter Amount" ng-model="balBank.amount"
													ng-keypress="filterValue($event);">
											</div>
										</div>
									</div>
								</div>
								<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
									<div
										class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
										<label for="email_address_2">Remarks</label>
									</div>
									<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
										<div class="form-group form-float">
											<div class="form-line">
												<input type="text" class="form-control"
													placeholder="Remarks" ng-model="balBank.remarks">
											</div>
										</div>
									</div>
								</div>
								<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
									<div
										class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
										<label for="email_address_2">Payment Date</label>
									</div>
									<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
										<div class="form-group form-float">
											<div class="form-line">
												<input type="text" class="form-control" id="dp5"
													placeholder="Enter Start Date"
													ng-model="balBank.paymentDate" readonly="readonly" />
											</div>
										</div>
									</div>
								</div>
								<div class="col-sm-12">
									<div class="form-group form-float">
										<input type="hidden" />
										<button type="button"
											class="btn bg-cyan btn-block btn-lg waves-effect"
											ng-click="balanceRequest(balBank,'1');">SUBMIT</button>
									</div>
								</div>
							</div>
						</div>
						<!-- -------------------------Balance Request To Admin End----------------------------- -->
						<!-- -------------------------Balance Request To Upline----------------------------- -->

						<div class="tab-pane fade in active" id="uplineDiv"
							style="display: none;">
							<div class="row clearfix">
								<div
									class="col-lg-12 col-md-12 col-sm-12 col-xs-12 col-lg-offset-0 col-md-offset-0 ">
									<div class="form-group form-float">
										<div class="form-line">
											<input type="text" class="form-control"
												ng-model="balBank1.accName"> <label
												class="form-label">Account Name</label>
										</div>
									</div>
								</div>
								<div class="col-sm-12">
									<div class="form-group form-float">
										<div class="form-line">
											<input type="text" class="form-control"
												ng-model="balBank1.bankName"> <label
												class="form-label">Bank Name</label>
										</div>
									</div>
								</div>
								<div class="col-sm-12">
									<div class="form-group form-float">
										<div class="form-line">
											<input type="text" class="form-control"
												ng-model="balBank1.branchName"> <label
												class="form-label">Branch Name</label>
										</div>
									</div>
								</div>

								<div class="col-sm-12">
									<div class="form-group form-float">
										<div class="form-line">
											<input type="text" class="form-control"
												ng-model="balBank1.accNo"> <label class="form-label">Account
												Number</label>
										</div>
									</div>
								</div>

								<div class="col-sm-12">
									<div class="form-group form-float">
										<div class="form-line">
											<input type="text" class="form-control"
												ng-model="balBank1.ifscCode"> <label
												class="form-label">IFSC Code</label>
										</div>
									</div>
								</div>

								<div class="col-sm-12">
									<div class="form-group form-float">
										<div class="form-line">
											<input type="text" class="form-control"
												ng-model="balBank1.transactionId"
												ng-keypress="filterValue($event);"> <label
												class="form-label">Traansaction ID</label>
										</div>
									</div>
								</div>
								<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
									<select class="form-control" ng-model="balBank1.paymentMode">
										<option value="">-- Select --</option>
										<option value="IMPS">IMPS</option>
										<option value="NEFT">NEFT</option>
										<option value="RTGS">RTGS</option>
										<option value="CASH">CASH</option>
										<option value="CHEQUE">CHEQUE</option>
									</select>
								</div>
								<div class="col-sm-12">
									<div class="form-group form-float">
										<div class="form-line">
											<input type="text" class="form-control"
												ng-model="balBank1.amount"
												ng-keypress="filterValue($event);"> <label
												class="form-label">Amount</label>
										</div>
									</div>
								</div>
								<div class="col-sm-12">
									<div class="form-group form-float">
										<div class="form-line">
											<input type="text" class="form-control"
												ng-model="balBank1.remarks"> <label
												class="form-label">Remarks</label>
										</div>
									</div>
								</div>

								<div class="col-sm-12">
									<div class="form-group form-float">
										<div class="form-line">
											<input type="text" class="form-control" id="dp6"
												ng-model="balBank1.paymentDate" readonly="readonly" /> <label
												class="form-label">Payment Date</label>
										</div>
									</div>
								</div>
								<div class="col-sm-12">
									<div class="form-group form-float">
										<button type="button"
											class="btn bg-cyan btn-block btn-lg waves-effect"
											ng-click="balanceRequest(balBank1,'2');">SUBMIT</button>
									</div>
								</div>
							</div>
						</div>
						<!-- ------------------Upline End--------------------- -->
					</div>
					<!-- #END# Tabs With Only Icon Title -->
				</div>
			</div>
		</div>
		<!-------------- /Div9 ---------------------->
		<!-------------- Div10 ---------------------->
		<div id="div10" class="row clearfix" style="display: none">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<div class="card">
					<div class="header">
						<h2>View Bank Details</h2>
					</div>
					<div class="body">
						<div class="form-group" ng-if="viewBankDetails.length > 0">
							<table class="table table-striped">
								<thead>
									<tr>
										<th class="success text-center">Sl. No</th>
										<th class="success text-center">Account Name</th>
										<th class="success text-center">Account Number</th>
										<th class="success text-center">Branch Name</th>
										<th class="success text-center">Bank Name</th>
										<th class="success text-center">Ifsc Code</th>
										<th class="success text-center">Date &amp; Time</th>
									</tr>
								</thead>
								<tbody>
									<tr ng-repeat="item in viewBankDetails">
										<td>{{$index + 1}}</td>
										<td>{{item.accName}}</td>
										<td>{{item.accNo}}</td>
										<td>{{item.branchName}}</td>
										<td>{{item.bankName}}</td>
										<td>{{item.ifscCode}}</td>
										<td>{{item.date}}&nbsp;{{item.time}}</td>
									</tr>
								</tbody>
							</table>
						</div>
						<div class="table table-striped"
							ng-if="viewBankDetails.length <= 0">
							<table cellspacing="1"
								class="table table-striped table-bordered table-hover">
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
		<!-------------- /Div10 ---------------------->
		<!-------------- Div11 ---------------------->
		<div id="div11" class="row clearfix" style="display: none">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<div class="card">
					<div class="header">
						<h2>Transaction Report</h2>
					</div>
					<div class="body">
						<form class="form-horizontal">
							<div class="row clearfix">
								<div
									class="col-lg-5 col-md-5 col-sm-4 col-xs-4 form-control-label">
									<label for="email_address_2">Start Date</label>
								</div>
								<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
									<div class="form-group">
										<div class="form-line">
											<input type="text" class="form-control" id="dp1"
												placeholder="Enter Start Date"
												ng-model="tranReport.startDate" readonly="readonly" />
										</div>
									</div>
								</div>
							</div>
							<div class="row clearfix">
								<div
									class="col-lg-5 col-md-5 col-sm-4 col-xs-4 form-control-label">
									<label for="email_address_2">End Date</label>
								</div>
								<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
									<div class="form-group">
										<div class="form-line">
											<input type="text" class="form-control" id="dp2"
												placeholder="Enter Start Date" ng-model="tranReport.endDate"
												readonly="readonly" />
										</div>
									</div>
								</div>
							</div>
							<div class="row clearfix">
								<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 "></div>
								<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
									<div class="col-md-offset-3">
										<button type="button" class="btn btn-primary waves-effect"
											ng-click="getTransactionReport(tranReport);">Update</button>
										<button type="button" class="btn btn-primary waves-effect"
											onclick="showDiv('div24')">Back</button>
									</div>
								</div>
							</div>
							<div class="row clearfix">
								<div class="form-group">
									<div
										class="col-xs-12 col-sm-12 col-md-12 col-lg-12 table-responsive"
										ng-if="TransactioReport.length > 0">
										<table id="mainTable" class="table table-striped"
											style="margin-left: 1%;">
											<thead>
												<tr style="font-size: 12px; font-weight: bold;">
													<th class="success text-center">Sl. No</th>
													<th class="success text-center">Op Bal</th>
													<th class="success text-center">Credit</th>
													<th class="success text-center">Debit</th>
													<th class="success text-center">Cl Bal</th>
													<th class="success text-center">Narration</th>
													<th class="success text-center">Remark</th>
													<th class="success text-center">Date and Time</th>
												</tr>
											</thead>
											<tbody>
												<tr
													ng-repeat="TransactioReport in TransactioReport | startFrom:currentPage*pageSize | limitTo:pageSize"
													ng-if="TransactioReport.credit != 0 || TransactioReport.debit != 0">
													<td>{{$index + 1}}</td>
													<td>{{TransactioReport.openingBal}}</td>
													<td>{{TransactioReport.credit}}</td>
													<td>{{TransactioReport.debit}}</td>
													<td>{{TransactioReport.closingBal}}</td>
													<td>{{TransactioReport.narration}}</td>
													<td>{{TransactioReport.remarks}}</td>
													<td>{{TransactioReport.date}}&nbsp;&nbsp;{{TransactioReport.time}}</td>
												</tr>
											</tbody>
										</table>
									</div>
									<div data-ng-show="viewTransactioReportPagination"
										style="margin-left: 2%">
										<button ng-disabled="currentPage == 0"
											ng-click="currentPage=currentPage-1">Previous</button>
										{{currentPage+1}}/{{numberOfPages()}}
										<button ng-disabled="currentPage >= data.length/pageSize - 1"
											ng-click="currentPage=currentPage+1">Next</button>
										<button type="button" class="btn btn-primary"
											ng-click="getTransactionReportReportExcel(TransactioReport);"
											ng-if="TransactioReport.length > 0">Export</button>
									</div>
									<div
										class="col-xs-12 col-sm-12 col-md-12 col-lg-12 table-responsive"
										ng-if="TransactioReport.length <= 0">
										<table id="mainTable" class="table table-striped">
											<tbody>
												<tr>
													<th align="center" style="color: red;">No Records
														Found</th>
												</tr>
											</tbody>
										</table>
									</div>
								</div>
							</div>
						</form>
					</div>

				</div>
			</div>
		</div>
		<!-------------- /Div11 ---------------------->
		<!-------------- Div12 ---------------------->
		<div id="div12" class="row clearfix" style="display: none">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<div class="card">
					<div class="header">
						<h2>Recharge Report</h2>
					</div>
					<div class="body">
						<form class="form-horizontal">
							<%
								if (user.getRoleId() == 3 || user.getRoleId() == 4) {
							%>
							<div class="row clearfix">
								<div class="col-md-6 col-lg-6 col-sm-12 col-xs-12">
									<div
										class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
										<label for="email_address_2">Usertype</label>
									</div>
									<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
										<div class="form-group">
											<div class="form-line1">
												<select class="form-control"
													ng-model="rechargeReport.userType"
													ng-init="rechargeReport.userType = '0'"
													ng-change="getUserByUserType1(rechargeReport.userType,'getUserForRechargeReportDiv','rechargeReport')">
													<option value="0">All</option>
													<option value="10">Own Report</option>
													<option ng-repeat="userType in userType"
														ng-if="userType.type > userDetails.roleId && userType.type < 100"
														value="{{userType.type}}">{{userType.role}}</option>
												</select>
											</div>
										</div>
									</div>
								</div>
								<div class="col-md-6 col-lg-6 col-sm-12 col-xs-12">

									<div style="margin-left: 3%;"
										ng-init="rechargeReport.userId = '0'">
										<div ng-bind-html="getUserForRechargeReportDiv"
											compile-template></div>
									</div>
								</div>
							</div>
							<%
								}
							%>


							<div class="row clearfix">
								<div class="col-md-6 col-lg-6 col-sm-12 col-xs-12">
									<div
										class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
										<label for="email_address_2">Status</label>
									</div>
									<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
										<div class="form-group">
											<div class="form-line1">
												<select class="form-control"
													ng-model="rechargeReport.status"
													ng-init="rechargeReport.status = 'All'">
													<option value="All">All</option>
													<option value="SUCCESS">SUCCESS</option>
													<option value="PENDING">PENDING</option>
													<option value="FAILED">FAILED</option>
												</select>
											</div>
										</div>
									</div>
								</div>
								<div class="col-md-6 col-lg-6 col-sm-12 col-xs-12">
									<div
										class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
										<label for="password_2">Operator</label>
									</div>
									<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
										<div class="form-group">
											<div class="form-line1">
												<select class="form-control"
													ng-model="rechargeReport.operator"
													ng-init="rechargeReport.operator = '0'">
													<option value="0">ALL</option>
													<option ng-repeat="operator in operator"
														ng-if="operator.serviceType != '7'"
														value="{{operator.operatorId}}">{{operator.serviceProvider}}</option>
												</select>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="row clearfix">
								<div class="col-md-6 col-lg-6 col-sm-12 col-xs-12">
									<div
										class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
										<label for="email_address_2">Start Date</label>
									</div>
									<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
										<div class="form-group">
											<div class="form-line1">
												<input type="text" class="form-control" id="dp3"
													placeholder="Enter Start Date"
													ng-model="rechargeReport.startDate" readonly="readonly" />
											</div>
										</div>
									</div>
								</div>
								<div class="col-md-6 col-lg-6 col-sm-12 col-xs-12">
									<div
										class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
										<label for="password_2">End Date</label>
									</div>
									<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
										<div class="form-group">
											<div class="form-line1">
												<input type="text" class="form-control" id="dp4"
													placeholder="Enter Start Date"
													ng-model="rechargeReport.endDate" readonly="readonly" />
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="row clearfix">
								<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 "></div>
								<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
									<div class="col-md-offset-3">
										<button type="button" class="btn btn-primary waves-effect"
											ng-click="getRechargeReport(rechargeReport);">Update</button>
										<button type="button" class="btn btn-primary waves-effect"
											onclick="showDiv('div24')">Back</button>
									</div>
								</div>
							</div>
							<div class="row clearfix">
								<div class="form-group">
									<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12"
										ng-if="RechargeReports.length > 0"
										style="overflow: scroll; margin-left: 1%;">
										<table id="mainTable" class="table table-striped">
											<thead>
												<tr>
													<th class="success text-center">Sl. No</th>
													<%
														if (user.getRoleId() == 3 || user.getRoleId() == 4) {
													%>
													<th class="success text-center">Name</th>
													<%
														}
													%>
													<th class="success text-center">Mobile</th>
													<th class="success text-center">Operator</th>
													<th class="success text-center">Op Bal</th>
													<th class="success text-center">Amount</th>
													<th class="success text-center">Charge</th>
													<th class="success text-center">Comm</th>
													<th class="success text-center">Cl Bal</th>
													<th class="success text-center">T_ID</th>
													<th class="success text-center">Opt_ID</th>
													<th class="success text-center">Status</th>
													<th class="success text-center">Source</th>
													<th class="success text-center">Date and Time</th>
													<th class="success text-center">Complain</th>
													<th class="success text-center">Action</th>
												</tr>
											</thead>
											<tbody>
												<tr
													ng-repeat="report in RechargeReports | startFrom:currentPage*pageSize | limitTo:pageSize">
													<td>{{$index + 1}}</td>
													<%
														if (user.getRoleId() == 3 || user.getRoleId() == 4) {
													%>
													<td>{{report.name}}</td>
													<%
														}
													%>
													<td>{{report.mobile}}</td>
													<td>{{report.serviceProvider}}</td>
													<td>{{report.openBal}}</td>
													<td>{{report.amount}}</td>
													<td>{{report.charge}}</td>
													<td>{{report.comm}}</td>
													<td>{{report.closeBal}}</td>
													<td>{{report.tid}}</td>
													<td>{{report.oid}}</td>
													<td class="{{report.status}}">{{report.status}}</td>
													<td>{{report.source}}</td>
													<td>{{report.date}}&amp;{{report.time}}</td>
													<td class="text-center" style="font-size: 12px;">
														<div class="input-group" style="font-size: 30px"
															ng-if="report.status == 'SUCCESS'">
															<button data-toggle="modal"
																data-target="#submitComplainModal"
																ng-click="getRechargeComplainDetails(report.tid);"
																; style="border-radius: 100%;">
																<i class="glyphicon glyphicon-info-sign"></i>
															</button>
														</div>
													</td>
													<td><input type="button" value="Invoice"
														ng-click="printRecharegeDetails(report);" /></td>
												</tr>
												<tr>
													<td>Total:</td>
													<%
														if (user.getRoleId() == 3 || user.getRoleId() == 4) {
													%>
													<td></td>
													<%
														}
													%>
													<td></td>
													<td></td>
													<td></td>
													<td>{{RechargeReports | totalSuccess:'amount'}}</td>
													<td>{{RechargeReports | totalCommission:'charge'}}</td>
													<td>{{RechargeReports | totalCommission:'comm'}}</td>
													<td></td>
													<td></td>
													<td></td>
													<td></td>
													<td></td>
													<td></td>
													<td></td>
												</tr>
											</tbody>
										</table>
									</div>
									<div data-ng-show="viewRechargeReportsPagination"
										style="margin-left: 2%">
										<button ng-disabled="currentPage == 0"
											ng-click="currentPage=currentPage-1">Previous</button>
										{{currentPage+1}}/{{numberOfPages()}}
										<button ng-disabled="currentPage >= data.length/pageSize - 1"
											ng-click="currentPage=currentPage+1">Next</button>
										<button type="button" class="btn btn-primary"
											ng-click="getRechargeReportExcel(RechargeReports);"
											ng-if="RechargeReports.length > 0">Export</button>
									</div>
									<div class="table-responsive"
										ng-if="RechargeReports.length <= 0" style="margin-left: 30px;">
										<table cellspacing="1"
											class="table table-striped table-bordered table-hover">
											<tbody>
												<tr>
													<th align="center" style="color: red;">No Records
														Found</th>
												</tr>
											</tbody>
										</table>
									</div>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>

		<!-- ----------------------Complain MODAL ---------------- -->

		<div class="modal fade" id="submitComplainModal" tabindex="-1"
			role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">×¼/span><span class="sr-only">Close</span>
						</button>
						<h3 class="modal-title" id="lineModalLabel">Add Complain</h3>
					</div>
					<div class="modal-body">
						<div class="row clearfix">
							<div
								class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
								<label for="email_address_2">Complain Type</label>
							</div>
							<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
								<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
									<div class="form-group form-float"
										style="margin-left: 0px !important;">
										<div class="form-line">
											<select class="form-control"
												ng-model="rechargeComplain.complainType">
												<option value="0" disabled>Select Complain Type</option>
												<option value="General enquiry" disabled>General
													enquiry</option>
												<option value="Recharge Complain" selected="selected">Recharge
													Complain</option>
												<option value="Money Transfer Complain" disabled>Money
													Transfer Complain</option>
												<option value="Recharge Portal" disabled>Recharge
													Portal</option>
												<option value="Recharge Portal" disabled>Api
													(Integration)</option>
												<option value="Api (Integration)" disabled>Long
													Code</option>
												<option value="SMS" disabled>SMS</option>
												<option value="Payment Update" disabled>Payment
													Update</option>
												<option value="Others" disabled>Others</option>
											</select>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="row clearfix">
							<div
								class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
								<label for="email_address_2">Subject/Transaction Id</label>
							</div>
							<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
								<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
									<div class="form-group form-float"
										style="margin-left: 0px !important;">
										<div class="form-line">
											<input type="text" class="form-control"
												placeholder="Enter Subject"
												ng-model="rechargeComplain.subject" disabled="disabled">
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="row clearfix">
							<div
								class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
								<label for="email_address_2">Complain Details</label>
							</div>
							<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
								<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
									<div class="form-group form-float">
										<div class="form-line">
											<textarea ng-model="rechargeComplain.complain"
												class="form-control"
												placeholder="Explain how we can help you"></textarea>
										</div>
									</div>
								</div>
							</div>
						</div>

						<div class="row clearfix">
							<div
								class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
								<label for="email_address_2">Status</label>
							</div>
							<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
								<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
									<div class="form-group form-float"
										style="margin-left: 0px !important;">
										<div class="form-line">
											<input type="text" id="" class="form-control"
												placeholder="Enter Subject"
												ng-model="rechargeComplain.status" disabled="disabled">
										</div>
									</div>
								</div>
							</div>
						</div>

						<div class="row clearfix">
							<div
								class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
								<label for="email_address_2">Admin Message</label>
							</div>
							<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
								<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
									<div class="form-group form-float"
										style="margin-left: 0px !important;">
										<div class="form-line">
											<input type="text" id="" class="form-control"
												placeholder="Enter Subject"
												ng-model="rechargeComplain.adminMessage" disabled="disabled">
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="row clearfix">
							<div
								class="col-lg-offset-6 col-md-offset-6 col-sm-offset-6 col-xs-offset-6">
								<button type="button"
									class="btn btn-primary m-t-15 waves-effect"
									data-dismiss="modal"
									ng-click="updateRechargeComplains(rechargeComplain);">Submit</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>


		<!-------------- /Div12 ---------------------->
		<!-- -------------------------------------div13---------------------- -->
		<div id="div13" class="row clearfix" style="display: none">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<div class="card">
					<div class="header">
						<h2>View User</h2>
					</div>
					<div class="body">
						<div class="body">
							<form class="form-horizontal">
								<div class="row clearfix">
									<div class="col-md-6 col-lg-6 col-sm-12 col-xs-12">
										<div
											class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
											<label for="email_address_2">User Type</label>
										</div>
										<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 m-l--20">
											<select class="form-control" ng-model="viewUser.userType"
												ng-init="viewUser.userType = '0'">
												<option value="0">All</option>
												<option ng-repeat="userType in userType"
													ng-if="userType.type > userDetails.roleId && userType.type < 100"
													value="{{userType.type}}">{{userType.role}}</option>
											</select>
										</div>
									</div>
								</div>
								<div class="row clearfix">
									<div
										class="col-lg-offset-2 col-md-offset-2 col-sm-offset-4 col-xs-offset-5">
										<button type="button"
											class="btn btn-primary m-t-15 waves-effect"
											ng-click="getViewUser();">SUBMIT</button>
									</div>
								</div>
								<!-- ---VIEW USER---- -->
								<div class="row clearfix m-t-20">
									<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12"
										ng-if="viewUserDetails.length > 0">
										<table class="table table-striped">
											<thead>
												<tr>
													<th class="success text-center">Sl. No</th>
													<th class="success text-center">Name</th>
													<th class="success text-center">Mobile</th>
													<th class="success text-center">Email</th>
													<th class="success text-center">User Type</th>
													<th class="success text-center">Address</th>
													<th class="success text-center">Balance</th>
													<th class="success text-center">Upline Id</th>
													<th class="success text-center">Date and Time</th>
													<th class="success text-center">Status</th>
												</tr>
											</thead>
											<tbody>
												<tr
													ng-repeat="item in viewUserDetails | startFrom:currentPage*pageSize | limitTo:pageSize">
													<td style="font-size: 12px;">{{(pageSize*currentPage+1)+$index}}</td>
													<td style="font-size: 12px;">{{item.name}}</td>
													<td style="font-size: 12px;">{{item.mobile}}</td>
													<td style="font-size: 12px;">{{item.email}}</td>
													<td style="font-size: 12px;"><span
														ng-if="item.roleId == 2">RESELLER</span> <span
														ng-if="item.roleId == 3">SUPER DISTRIBUTOR</span> <span
														ng-if="item.roleId == 4">DISTRIBUTOR</span> <span
														ng-if="item.roleId == 5">RETAILER</span> <span
														ng-if="item.roleId == 100">API USER</span></td>
													<td style="font-size: 12px;">{{item.address}},&nbsp;{{item.city}}-{{item.pinCode}},&nbsp;{{item.state}}</td>
													<td style="font-size: 12px;">{{item.balance}}</td>
													<td style="font-size: 12px;">{{item.uplineId}}</td>
													<td style="font-size: 12px;">{{item.createdDate}}&nbsp;&nbsp;{{item.createdTime}}</td>
													<td style="font-size: 12px;">
														<p ng-if="item.status == 0" class="success">Active</p>
														<p ng-if="item.status == 1" class="danger">Inactive</p>
												</tr>
											</tbody>
										</table>
									</div>
									<div data-ng-show="viewUserPagination">
										<button ng-disabled="currentPage == 0"
											ng-click="currentPage=currentPage-1">Previous</button>
										{{currentPage+1}}/{{numberOfPages()}}
										<button ng-disabled="currentPage >= data.length/pageSize - 1"
											ng-click="currentPage=currentPage+1">Next</button>
										<button type="button" class="btn btn-primary"
											ng-click="getViewUserExcel(viewUserDetails);"
											ng-if="viewUserDetails.length > 0">Export</button>
									</div>
									<div class="table table-striped"
										ng-if="viewUserDetails.length <= 0">
										<table cellspacing="1"
											class="table table-striped table-bordered table-hover">
											<tbody>
												<tr>
													<th align="center" style="color: red;">No Records
														Found</th>
												</tr>
											</tbody>
										</table>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- ---------------------------/div13 -->

		<!-------------- Div14 ---------------------->
		<div id="div14" class="row clearfix" style="display: none">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<div class="card">
					<div class="header">
						<h2>Balance Transfer Report</h2>
					</div>
					<div class="body">
						<form class="form-horizontal">
							<div class="row clearfix">
								<div
									class="col-lg-5 col-md-5 col-sm-4 col-xs-4 form-control-label">
									<label for="email_address_2">Start Date</label>
								</div>
								<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
									<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 m-l-15">
										<div class="form-group">
											<div class="form-group form-float">
												<div class="form-line">
													<input type="text" class="form-control" id="dp11"
														placeholder="Enter Start Date"
														ng-model="viewBalanceTransferReport.startDate"
														readonly="readonly" />
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="row clearfix">
								<div
									class="col-lg-5 col-md-5 col-sm-4 col-xs-4 form-control-label">
									<label for="email_address_2">End Date</label>
								</div>
								<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
									<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 m-l-15">
										<div class="form-group">
											<div class="form-group form-float">
												<div class="form-line">
													<input type="text" class="form-control" id="dp12"
														placeholder="Enter Start Date"
														ng-model="viewBalanceTransferReport.endDate"
														readonly="readonly" />
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
										<button type="button" class="btn btn-primary waves-effect"
											ng-click="getBalanceTransferReport(viewBalanceTransferReport);">Update</button>
										<button type="button" class="btn btn-primary waves-effect"
											onclick="showDiv('div24')">Back</button>
									</div>
								</div>
							</div>
							<div class="row clearfix">

								<div class="row clearfix" style="margin-top: 2%">
									<div
										class="col-xs-12 col-sm-12 col-md-12 col-lg-12 table-responsive"
										ng-if="viewBalanceTransferDetails.length > 0">
										<table id="mainTable" class="table table-striped">
											<thead>
												<tr style="font-size: 13px; font-weight: bold;">
													<th class="success text-center">Sl. No</th>
													<th class="success text-center">Transfer To</th>
													<th class="success text-center">Op Bal</th>
													<th class="success text-center">Amount</th>
													<th class="success text-center">Cl Bal</th>
													<th class="success text-center">Narration</th>
													<th class="success text-center">remarks</th>

												</tr>
											</thead>
											<tbody>
												<tr ng-repeat="item in viewBalanceTransferDetails">
													<td style="font-size: 15px; text-align: center;">{{$index
														+ 1}}</td>
													<td style="font-size: 15px; text-align: center;">{{item.to_name}}</td>
													<td style="font-size: 15px; text-align: center;">{{item.fromOpenBal}}</td>
													<td style="font-size: 15px; text-align: center;">{{item.transferAmount}}</td>
													<td style="font-size: 12px; text-align: center;">{{item.fromClosingBal}}</td>
													<td style="font-size: 12px; text-align: center;">{{item.narration}}</td>
													<td style="font-size: 12px; text-align: center;">{{item.remarks}}</td>

												</tr>
											</tbody>
										</table>
									</div>
									<div data-ng-show="viewBalanceTransferPagination"
										style="margin-left: 20px;">
										<button ng-disabled="currentPage == 0"
											ng-click="currentPage=currentPage-1">Previous</button>
										{{currentPage+1}}/{{numberOfPages()}}
										<button ng-disabled="currentPage >= data.length/pageSize - 1"
											ng-click="currentPage=currentPage+1">Next</button>
										<!-- <button type="button" class="btn btn-primary" ng-click="getViewUserExcel(viewUserDetails);" ng-if="viewUserDetails.length > 0">Export</button> -->
									</div>
									<div class="table table-striped"
										ng-if="viewBalanceTransferDetails.length <= 0">
										<table cellspacing="1"
											class="table table-striped table-bordered table-hover">
											<tbody>
												<tr>
													<th align="center" style="color: red;">No Records
														Found</th>
												</tr>
											</tbody>
										</table>
									</div>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
		<!-------------- /Div14 ---------------------->

		<!-------------- Div16 ---------------------->
		<div id="div16" class="row clearfix" style="display: none">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<div class="card">
					<div class="header">
						<h2>Utility Report</h2>
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
													<input type="text" class="form-control" id="dp13"
														ng-model="viewUtilityRequest.startDate"
														readonly="readonly" />
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="col-md-4">
									<div
										class="col-lg-6 col-md-6 col-sm-6 col-xs-6 form-control-label">
										<label for="email_address_2">Start Date</label>
									</div>
									<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">

										<div class="form-group">
											<div class="form-group form-float" style="margin-left: 6%">
												<div class="form-line">
													<input type="text" class="form-control" id="dp14"
														ng-model="viewUtilityRequest.endDate" readonly="readonly" />
												</div>
											</div>
										</div>

									</div>
								</div>
								<div class="col-md-4">
									<div
										class="col-lg-offset-6 col-md-offset-6 col-sm-offset-6 col-xs-offset-6">
										<button type="button"
											class="btn btn-primary m-t-15 waves-effect"
											ng-click="viewUtilityReport(viewUtilityRequest);">Submit</button>
										<button type="button"
											class="btn btn-primary m-t-15 waves-effect"
											onclick="showDiv('div24')">Back</button>
									</div>
								</div>
							</div>
						</form>

						<div class="row clearfix" style="margin-top: 2%">
							<div
								class="col-xs-12 col-sm-12 col-md-12 col-lg-12 table-responsive"
								ng-if="viewUtilityDetails.length > 0">
								<table id="mainTable" class="table table-striped">
									<thead>
										<tr style="font-size: 13px; font-weight: bold;">
											<th class="success text-center">Sl. No</th>
											<th class="success text-center">USER</th>
											<th class="success text-center">Utility Type</th>
											<th class="success text-center">Operator Name</th>
											<th class="success text-center">Consumer Number</th>
											<th class="success text-center">Consumer Name</th>
											<th class="success text-center">Consumer Mobile</th>
											<th class="success text-center">Bill Due Date</th>
											<th class="success text-center">Amount</th>
											<th class="success text-center">STATUS</th>
											<th class="success text-center">Date &amp; Time</th>

										</tr>
									</thead>
									<tbody>
										<tr ng-repeat="utilityDetails in viewUtilityDetails ">
											<td style="font-size: 15px; text-align: center;">{{$index
												+ 1}}</td>
											<td style="font-size: 15px; text-align: center;">{{utilityDetails.name}}({{utilityDetails.mobile}})</td>
											<td style="font-size: 15px; text-align: center;"><span
												ng-if="utilityDetails.serviceType == 7">ELECTRIC BILL</span>
												<span ng-if="utilityDetails.serviceType == 6">GAS
													BILL</span></td>
											<td style="font-size: 15px; text-align: center;">{{utilityDetails.serviceName}}</td>
											<td style="font-size: 15px; text-align: center;">{{utilityDetails.accountNo}}</td>
											<td style="font-size: 15px; text-align: center;">{{utilityDetails.customerName}}</td>
											<td style="font-size: 15px; text-align: center;">{{utilityDetails.customerMobile}}
											</td>
											<td style="font-size: 15px; text-align: center;">{{utilityDetails.billDueDate}}</td>
											<td style="font-size: 15px; text-align: center;">{{utilityDetails.amount}}</td>
											<td style="font-size: 15px; text-align: center;">{{utilityDetails.status}}</td>
											<td style="font-size: 15px; text-align: center;">{{utilityDetails.submitDate}}
												{{utilityDetails.submitTime}}</td>
											<td><input type="button" value="Invoice"
												ng-click="printUtilityDetails(utilityDetails);" /></td>
										</tr>
									</tbody>
								</table>
							</div>

							<div data-ng-show="viewUtilityPagination">
								<button ng-disabled="currentPage == 0"
									ng-click="currentPage=currentPage-1">Previous</button>
								{{currentPage+1}}/{{numberOfPages()}}
								<button ng-disabled="currentPage >= data.length/pageSize - 1"
									ng-click="currentPage=currentPage+1">Next</button>
								<button type="button" class="btn btn-primary"
									ng-click="getExcelReport(viewUtilityDetails,'UtilityReport');"
									ng-if="viewUtilityDetails.length > 0">Export</button>
							</div>
							<div
								class="col-xs-12 col-sm-12 col-md-12 col-lg-12 table-responsive"
								ng-if="viewUtilityDetails.length <= 0">
								<table cellspacing="1"
									class="table table-striped table-bordered table-hover">
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

		<!-------------- /Div16 ---------------------->
		<!-------------- Div17 ---------------------->
		<div id="div17" class="row clearfix" style="display: none">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<div class="card">
					<div class="header">
						<h2>Balance Request Report</h2>
					</div>
					<div class="body">
						<form class="form-horizontal">
							<div class="row clearfix">
								<div
									class="col-lg-5 col-md-5 col-sm-4 col-xs-4 form-control-label">
									<label for="email_address_2">Start Date</label>
								</div>
								<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
									<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 m-l-15">
										<div class="form-group">
											<div class="form-group form-float">
												<div class="form-line">
													<input type="text" class="form-control" id="dp7"
														placeholder="Enter Start Date"
														ng-model="viewBalanceRequest.startDate"
														readonly="readonly" />
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="row clearfix">
								<div
									class="col-lg-5 col-md-5 col-sm-4 col-xs-4 form-control-label">
									<label for="email_address_2">End Date</label>
								</div>
								<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
									<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 m-l-15">
										<div class="form-group">
											<div class="form-group form-float">
												<div class="form-line">
													<input type="text" class="form-control" id="dp8"
														placeholder="Enter Start Date"
														ng-model="viewBalanceRequest.endDate" readonly="readonly" />
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
										<button type="button" class="btn btn-primary waves-effect"
											ng-click="getBalanceRequest(viewBalanceRequest);">Update</button>
										<button type="button" class="btn btn-primary waves-effect"
											onclick="showDiv('div24')">Back</button>
									</div>
								</div>
							</div>
							<div class="row clearfix">

								<div class="row clearfix" style="margin-top: 2%">
									<div
										class="col-xs-12 col-sm-12 col-md-12 col-lg-12 table-responsive"
										ng-if="viewBalanceRequestDetails.length > 0">
										<table id="mainTable" class="table table-striped">
											<thead>
												<tr style="font-size: 13px; font-weight: bold;">
													<th class="success text-center">Sl. No</th>
													<th class="success text-center">USER</th>
													<th class="success text-center">Bank Name</th>
													<th class="success text-center">Branch Name</th>
													<th class="success text-center">Account Name</th>
													<th class="success text-center">Bank TID</th>
													<th class="success text-center">Amount</th>
													<th class="success text-center">Payment Mode</th>
													<th class="success text-center">Payment Date</th>
													<th class="success text-center">Status</th>
													<th class="success text-center">Date&amp;Time</th>
													<th class="success text-center">Action</th>
												</tr>
											</thead>
											<tbody>
												<tr ng-repeat="item in viewBalanceRequestDetails">
													<td style="font-size: 15px; text-align: center;">{{$index
														+ 1}}</td>
													<td style="font-size: 15px; text-align: center;">{{item.name}}</td>
													<td style="font-size: 15px; text-align: center;">{{item.bankName}}</td>
													<td style="font-size: 15px; text-align: center;">{{item.branchName}}</td>
													<td style="font-size: 12px; text-align: center;">{{item.accName}}</td>
													<td style="font-size: 12px; text-align: center;">{{item.bankTransactionId}}</td>
													<td style="font-size: 12px; text-align: center;">{{item.amount}}</td>
													<td style="font-size: 12px; text-align: center;">{{item.paymentMode}}</td>
													<td style="font-size: 12px; text-align: center;">{{item.paymentDate}}</td>
													<td style="font-size: 12px; text-align: center;">{{item.status}}</td>
													<td
														style="font-size: 15px; text-align: center; color: green;">{{item.date}}
														{{item.time}}</td>
													<td class="in" style="font-size: 12px;"><span
														ng-if="item.status == 'PENDING'">
															<button class="edit-button"
																ng-click="updateBalanceRequest(item,'SUCCESS');">Success</button>
															<button class="delete-button" style="width: 73%;"
																ng-click="updateBalanceRequest(item,'FAILED');">Failed</button>
													</span></td>
												</tr>
											</tbody>
										</table>
									</div>
									<div data-ng-show="viewBalanceRequestPagination"
										style="margin-left: 20px;">
										<button ng-disabled="currentPage == 0"
											ng-click="currentPage=currentPage-1">Previous</button>
										{{currentPage+1}}/{{numberOfPages()}}
										<button ng-disabled="currentPage >= data.length/pageSize - 1"
											ng-click="currentPage=currentPage+1">Next</button>
										<!-- <button type="button" class="btn btn-primary" ng-click="getViewUserExcel(viewUserDetails);" ng-if="viewUserDetails.length > 0">Export</button> -->
									</div>
									<div class="table table-striped"
										ng-if="viewBalanceRequestDetails.length <= 0">
										<table cellspacing="1"
											class="table table-striped table-bordered table-hover">
											<tbody>
												<tr>
													<th align="center" style="color: red;">No Records
														Found</th>
												</tr>
											</tbody>
										</table>
									</div>
								</div>
							</div>
						</form>
					</div>

				</div>
			</div>
		</div>
		<!-------------- /Div17 ---------------------->

		<!-------------- Div18 ---------------------->
		<div id="div18" class="row clearfix" style="display: none">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<div class="card">
					<div class="header">
						<h2>Purchase Report</h2>
					</div>
					<div class="body">
						<form class="form-horizontal">
							<div class="row clearfix">
								<div
									class="col-lg-5 col-md-5 col-sm-4 col-xs-4 form-control-label">
									<label for="email_address_2">Start Date</label>
								</div>
								<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
									<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 m-l-15">
										<div class="form-group">
											<div class="form-group form-float">
												<div class="form-line">
													<input type="text" class="form-control" id="dp9"
														placeholder="Enter Start Date"
														ng-model="viewPurchaseReport.startDate"
														readonly="readonly" />
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="row clearfix">
								<div
									class="col-lg-5 col-md-5 col-sm-4 col-xs-4 form-control-label">
									<label for="email_address_2">End Date</label>
								</div>
								<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
									<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 m-l-15">
										<div class="form-group">
											<div class="form-group form-float">
												<div class="form-line">
													<input type="text" class="form-control" id="dp10"
														placeholder="Enter Start Date"
														ng-model="viewPurchaseReport.endDate" readonly="readonly" />
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
										<button type="button" class="btn btn-primary waves-effect"
											ng-click="getPurchaseReport(viewPurchaseReport);">Update</button>
										<button type="button" class="btn btn-primary waves-effect"
											onclick="showDiv('div24')">Back</button>
									</div>
								</div>
							</div>
							<div class="row clearfix">

								<div class="row clearfix" style="margin-top: 2%">
									<div
										class="col-xs-12 col-sm-12 col-md-12 col-lg-12 table-responsive"
										ng-if="viewPurchaseReportDetails.length > 0">
										<table id="mainTable" class="table table-striped">
											<thead>
												<tr style="font-size: 13px; font-weight: bold;">
													<th class="success text-center">Sl. No</th>
													<th class="success text-center">Purchase From</th>
													<th class="success text-center">Op Bal</th>
													<th class="success text-center">Amount</th>
													<th class="success text-center">Cl Bal</th>
													<th class="success text-center">Narration</th>
													<th class="success text-center">remarks</th>

												</tr>
											</thead>
											<tbody>
												<tr ng-repeat="item in viewPurchaseReportDetails">
													<td style="font-size: 15px; text-align: center;">{{$index
														+ 1}}</td>
													<td style="font-size: 15px; text-align: center;">{{item.from_name}}</td>
													<td style="font-size: 15px; text-align: center;">{{item.toOpenBal}}</td>
													<td style="font-size: 15px; text-align: center;">{{item.recievedAmount}}</td>
													<td style="font-size: 12px; text-align: center;">{{item.toclosingBal}}</td>
													<td style="font-size: 12px; text-align: center;">{{item.narration}}</td>
													<td style="font-size: 12px; text-align: center;">{{item.remarks}}</td>

												</tr>
											</tbody>
										</table>
									</div>
									<div data-ng-show="viewPurchaseReportPagination"
										style="margin-left: 20px;">
										<button ng-disabled="currentPage == 0"
											ng-click="currentPage=currentPage-1">Previous</button>
										{{currentPage+1}}/{{numberOfPages()}}
										<button ng-disabled="currentPage >= data.length/pageSize - 1"
											ng-click="currentPage=currentPage+1">Next</button>
										<!-- <button type="button" class="btn btn-primary" ng-click="getViewUserExcel(viewUserDetails);" ng-if="viewUserDetails.length > 0">Export</button> -->
									</div>
									<div class="table table-striped"
										ng-if="viewPurchaseReportDetails.length <= 0">
										<table cellspacing="1"
											class="table table-striped table-bordered table-hover">
											<tbody>
												<tr>
													<th align="center" style="color: red;">No Records
														Found</th>
												</tr>
											</tbody>
										</table>
									</div>
								</div>
							</div>
						</form>
					</div>

				</div>
			</div>
		</div>
		<!-------------- /Div18 ---------------------->

		<!-------------- Div19 ---------------------->
		<div id="div19" class="row clearfix" style="display: none">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<div class="card">
					<div class="header">
						<h2>View Complain</h2>
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
													<input type="text" class="form-control" id="dp13"
														placeholder="Enter Start Date"
														ng-model="viewComplain.startDate" readonly="readonly" />
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
													<input type="text" class="form-control" id="dp14"
														placeholder="Enter End Date"
														ng-model="viewComplain.endDate" readonly="readonly" />
												</div>
											</div>
										</div>

									</div>
								</div>
								<div class="col-md-4">
									<div
										class="col-lg-offset-6 col-md-offset-6 col-sm-offset-6 col-xs-offset-6">
										<button type="button"
											class="btn btn-primary m-t-15 waves-effect"
											ng-click="getComplainDetails(viewComplain)">Submit</button>
									</div>
								</div>
							</div>
						</form>

						<div class="row clearfix" style="margin-top: 2%">
							<div
								class="col-xs-12 col-sm-12 col-md-12 col-lg-12 table-responsive"
								ng-if="viewComplainDetails.length > 0">
								<table id="mainTable" class="table table-striped">
									<thead>
										<tr style="font-size: 13px; font-weight: bold;">
											<th class="success text-center">Sl. No</th>
											<th class="success text-center">Complain Type</th>
											<th class="success text-center">Subject</th>
											<th class="success text-center">Complain</th>
											<th class="success text-center">DATE & TIME</th>
											<th class="success text-center">Message</th>
											<th class="success text-center">Status</th>
										</tr>
									</thead>
									<tbody>
										<tr ng-repeat="complain in viewComplainDetails">
											<td style="font-size: 15px; text-align: center;">{{$index
												+ 1}}</td>
											<td style="font-size: 15px; text-align: center;">{{complain.complainType}}</td>
											<td style="font-size: 15px; text-align: center;">{{complain.subject}}</td>
											<td style="font-size: 12px; text-align: center;">{{complain.complain}}</td>
											<td
												style="font-size: 15px; text-align: center; color: green;">{{complain.date}}
												{{complain.time}}</td>
											<td
												style="font-size: 15px; text-align: center; color: green;">{{complain.adminMessage}}</td>
											<td style="font-size: 15px; text-align: center;">{{complain.status}}</td>
										</tr>
									</tbody>
								</table>
							</div>
							<div data-ng-show="viewComplainPagination">
								<button ng-disabled="currentPage == 0"
									ng-click="currentPage=currentPage-1">Previous</button>
								{{currentPage+1}}/{{numberOfPages()}}
								<button ng-disabled="currentPage >= data.length/pageSize - 1"
									ng-click="currentPage=currentPage+1">Next</button>
								<button type="button" class="btn btn-primary"
									ng-click="getViewUserExcel(viewUserDetails);"
									ng-if="viewUserDetails.length > 0">Export</button>
							</div>
							<div class="table table-striped"
								ng-if="viewComplainDetails.length <= 0">
								<table cellspacing="1"
									class="table table-striped table-bordered table-hover">
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

		<!-------------- /Div19 ---------------------->

		<!-------------- Div20 ---------------------->
		<div id="div20" class="row clearfix" style="display: none">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<div class="card">
					<div class="header">
						<h2>Add Complain</h2>
					</div>
					<div class="body">
						<form class="form-horizontal">
							<div class="row clearfix">
								<div
									class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
									<label for="email_address_2">Complain Type</label>
								</div>
								<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
									<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
										<div class="form-group form-float"
											style="margin-left: 0px !important;">
											<div class="form-line">
												<select class="form-control"
													ng-model="complain.complainType"
													ng-init="complain.complainType = '0'">
													<option value="0">Select Complain Type</option>
													<option value="General enquiry">General enquiry</option>
													<option value="Recharge Complain">Recharge
														Complain</option>
													<option value="Money Transfer Complain">Money
														Transfer Complain</option>
													<option value="Recharge Portal">Recharge Portal</option>
													<option value="Recharge Portal">Api (Integration)</option>
													<option value="Api (Integration)">Long Code</option>
													<option value="SMS">SMS</option>
													<option value="Payment Update">Payment Update</option>
													<option value="Others">Others</option>
												</select>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="row clearfix">
								<div
									class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
									<label for="email_address_2">Subject/Transaction Id</label>
								</div>
								<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
									<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
										<div class="form-group form-float"
											style="margin-left: 0px !important;">
											<div class="form-line">
												<input type="text" class="form-control"
													placeholder="Enter Subject" ng-model="complain.subject">
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="row clearfix">
								<div
									class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
									<label for="email_address_2">Complain Details</label>
								</div>
								<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
									<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
										<div class="form-group form-float">
											<div class="form-line">
												<textarea ng-model="complain.complain" class="form-control"
													placeholder="Explain how we can help you"></textarea>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="row clearfix">
								<div
									class="col-lg-offset-6 col-md-offset-6 col-sm-offset-6 col-xs-offset-6">
									<button type="button"
										class="btn btn-primary m-t-15 waves-effect"
										data-dismiss="modal" ng-click="addComplains(complain);">Submit</button>
								</div>
							</div>

						</form>
					</div>
				</div>
			</div>
		</div>

		<!-------------- /Div20 ---------------------->
		
		<div id="div106" class="row clearfix" style="display: none">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<div class="card">
					<div class="header">
						<h2>P2A Money transfer</h2>
					</div>
					<div class="body">
						<form class="form-horizontal">
							
							<div class="row clearfix">
								<div
									class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
									<label for="email_address_2">Enter Amount</label>
								</div>
								<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
									<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
										<div class="form-group form-float"
											style="margin-left: 0px !important;">
											<div class="form-line">
												<input type="text" class="form-control"
													placeholder="Enter Amount" ng-model="p2amoney.amount">
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="row clearfix">
								<div
									class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
									<label for="email_address_2">Enter Type</label>
								</div>
								<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
									<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
										<div class="form-group form-float"
											style="margin-left: 0px !important;">
											<div class="form-line">
												<select ng-model="p2amoney.trantype" class="form-control show-tick">
																		<option value="0">Select Transaction Type</option>
																		<option value="4">IMPS</option>
																		<option value="2">NEFT</option>
																</select>
											</div>
										</div>
									</div>
								</div>
							</div>
							
							<div class="row clearfix">
								<div
									class="col-lg-offset-6 col-md-offset-6 col-sm-offset-6 col-xs-offset-6">
									<button type="button"
										class="btn btn-primary m-t-15 waves-effect"
										data-dismiss="modal" ng-click="p2amoneytransfer(p2amoney);">Submit</button>
								</div>
							</div>

						</form>
					</div>
				</div>
			</div>
		</div>

		<!-------------- Div21 ---------------------->
		<div id="div21" class="row clearfix" style="display: none">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<div class="card">
					<div class="header">
						<h2>Insurance Report</h2>
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
													<input type="text" class="form-control" id="dp15"
														ng-model="viewInsuranceRequest.startDate"
														readonly="readonly" />
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
													<input type="text" class="form-control" id="dp16"
														ng-model="viewInsuranceRequest.endDate"
														readonly="readonly" />
												</div>
											</div>
										</div>

									</div>
								</div>
								<div class="col-md-4">
									<div
										class="col-lg-offset-6 col-md-offset-6 col-sm-offset-6 col-xs-offset-6">
										<button type="button"
											class="btn btn-primary m-t-15 waves-effect"
											ng-click="viewInsuranceReport(viewInsuranceRequest);">Submit</button>
										<button type="button"
											class="btn btn-primary m-t-15 waves-effect"
											onclick="showDiv('div24')">Back</button>
									</div>
								</div>
							</div>
						</form>

						<div class="row clearfix" style="margin-top: 2%">
							<div
								class="col-xs-12 col-sm-12 col-md-12 col-lg-12 table-responsive"
								ng-if="viewInsuranceDetails.length > 0">
								<table id="mainTable" class="table table-striped">
									<thead>
										<tr style="font-size: 13px; font-weight: bold;">
											<th class="success text-center">Sl. No</th>
											<th class="success text-center">USER</th>
											<th class="success text-center">Insurer Name</th>
											<th class="success text-center">Policy Number</th>
											<th class="success text-center">Policy Holder Name</th>
											<th class="success text-center">Customer Mobile</th>
											<th class="success text-center">DOB</th>
											<th class="success text-center">Amount</th>
											<th class="success text-center">STATUS</th>
											<th class="success text-center">Date &amp; Time</th>
											<th class="success text-center">Action</th>

										</tr>
									</thead>
									<tbody>
										<tr ng-repeat="item in viewInsuranceDetails">
											<td style="font-size: 15px; text-align: center;">{{$index
												+ 1}}</td>
											<td style="font-size: 15px; text-align: center;">{{item.serviceProvider}}</td>
											<td style="font-size: 15px; text-align: center;">{{item.policyNumber}}</td>
											<td style="font-size: 15px; text-align: center;">{{item.policyHolderName}}</td>
											<td style="font-size: 15px; text-align: center;">{{item.custMobile}}
											</td>
											<td style="font-size: 15px; text-align: center;">{{item.dob}}</td>
											<td style="font-size: 15px; text-align: center;">{{item.amount}}</td>
											<td style="font-size: 15px; text-align: center;">{{item.status}}</td>
											<td style="font-size: 15px; text-align: center;">{{item.submitDate}}
												{{item.submitTime}}</td>
										</tr>
									</tbody>
								</table>
							</div>

							<div ng-if="viewInsuranceDetails.length > 0">
								<button ng-disabled="currentPage == 0"
									ng-click="currentPage=currentPage-1">Previous</button>
								{{currentPage+1}}/{{numberOfPages()}}
								<button ng-disabled="currentPage >= data.length/pageSize - 1"
									ng-click="currentPage=currentPage+1">Next</button>
								<button type="button" class="btn btn-primary"
									ng-click="getExcelReport(viewUtilityDetails,'InsuranceReport');"
									ng-if="viewUtilityDetails.length > 0">Export</button>
							</div>
							<div
								class="col-xs-12 col-sm-12 col-md-12 col-lg-12 table-responsive"
								ng-if="viewInsuranceDetails.length <= 0">
								<table cellspacing="1"
									class="table table-striped table-bordered table-hover">
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

		<!-------------- /Div21 ---------------------->

		<!-------------- div23 ---------------------->
		<div id="div23" class="row clearfix" style="display: none">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<div class="card">
					<div class="header">
						<h2>All Report</h2>
					</div>
					<div class="body">
						<div class="row clearfix">
							<div
								class="col-lg-12 col-md-12 col-sm-12 col-xs-12 form-control-label">
								<div class="body1">
									<div class="row">
										<div class="col-xs-4 col-sm-4 col-md-2 col-lg-2">
											<div class="widget2 widget3">
												<a onClick="showDiv('div24')" href="#">
													<div class="stats-left ">
														<i class="fa fa-mobile col-black"></i>
														<h5>Recharge / Utility Report</h5>
													</div>
													<div class="clearfix"></div>
												</a>
											</div>
										</div>
										
									
										<div class="col-xs-4 col-sm-4 col-md-2 col-lg-2">
											<div class="widget2 widget3">
												<a onClick="showDiv('div31')" href="javascript:;">
													<div class="stats-left ">
														<i class="material-icons">swap_horiz</i>
														<h5>PAN Report</h5>
													</div>
													<div class="clearfix"></div>
												</a>
											</div>
										</div>
										<div class="col-xs-4 col-sm-4 col-md-2 col-lg-2">
											<div class="widget2 widget3">
												<a onclick="showDiv('div30')" href="javascript:;" id="">
													<div class="stats-left ">
														<i class="fa  fa-money col-black"></i>
														<h5>Earning Report</h5>
													</div>
													<div class="clearfix"></div>
												</a>
											</div>
										</div>
										<div class="col-xs-4 col-sm-4 col-md-2 col-lg-2">
											<div class="widget2 widget3">
												<a onclick="showDiv('div156')" href="javascript:;" id="">
													<div class="stats-left ">
														<i class="fa  fa-podcast col-black"></i>
														<h5>YesBank AEPS REPORT</h5>
													</div>
													<div class="clearfix"></div>
												</a>
											</div>
										</div>
										<div class="col-xs-4 col-sm-4 col-md-2 col-lg-2">
											<div class="widget2 widget3">
												<a onclick="showDiv('div157')" href="javascript:;" id="">
													<div class="stats-left ">
														<i class="fa  fa-podcast col-black"></i>
														<h5>SETTLEMENT REPORT</h5>
													</div>
													<div class="clearfix"></div>
												</a>
											</div>
										</div>
										<div class="col-xs-4 col-sm-4 col-md-2 col-lg-2">
											<div class="widget2 widget3">
												<a onclick="showDiv('div105')" href="javascript:;" id="">
													<div class="stats-left ">
														<i class="fa  fa-podcast col-black"></i>
														<h5>ICICI AEPS Report</h5>
													</div>
													<div class="clearfix"></div>
												</a>
											</div>
										</div>
										
										
									</div>
									<div class="row">
									<!-- 	<div class="col-xs-4 col-sm-4 col-md-2 col-lg-2">
										<div class="widget2 widget3">
											<a onclick="showDiv('div159')" href="javascript:;" id="">
												<div class="stats-left ">
													<i class="fa fa fa-clock-o"></i>
													<h5>PENDING AEPS STATUS CHANGE</h5>
												</div>
												<div class="clearfix"></div>
											</a>
										</div>
									</div> -->
									
									<div class="col-xs-4 col-sm-4 col-md-2 col-lg-2">
										<div class="widget2 widget3">
											<a onclick="showDiv('div107')" href="javascript:;" id="">
												<div class="stats-left ">
													<i class="fa fa fa-clock-o"></i>
													<h5>Pay2Money Transfer Report </h5>
												</div>
												<div class="clearfix"></div>
											</a>
										</div>
									</div>
									
									<div class="col-xs-4 col-sm-4 col-md-2 col-lg-2">
										<div class="widget2 widget3">
											<a onclick="showDiv('div208')" href="javascript:;" id="">
												<div class="stats-left ">
													<i class="fa fa fa-clock-o"></i>
													<h5>Micro atm REPORT</h5>
												</div>
												<div class="clearfix"></div>
											</a>
										</div>
									</div>
									<div class="col-xs-4 col-sm-4 col-md-2 col-lg-2">
										<div class="widget2 widget3">
											<a href="flight" id="">
												<div class="stats-left ">
													<i class="fa fa fa-clock-o"></i>
													<h5>Flight REPORT</h5>
												</div>
												<div class="clearfix"></div>
											</a>
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

		<!-------------- /div23 ---------------------->
		
		<!-------------- div24 ---------------------->
		<div id="div24" class="row clearfix" style="display: none">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<div class="card">
					<div class="header">
						<h2>
							Recharge / Utility Report <span class="pull-right"><a
								href="#" class="body" onclick="showDiv('div23')"><i
									class="material-icons">arrow_back</i></a></span>
						</h2>
					</div>
					<div class="body">
						<div class="row clearfix">
							<div
								class="col-lg-12 col-md-12 col-sm-12 col-xs-12 form-control-label">
								<div class="body1">
									<div class="row">
										<div class="col-xs-4 col-sm-4 col-md-2 col-lg-2">
											<div class="widget2 widget3">
												<a onClick="showDiv('div11')" href="javascript:;">
													<div class="stats-left ">
														<i class="material-icons">account_balance_wallet</i>
														<h5>Transaction Reports</h5>
													</div>
													<div class="clearfix"></div>
												</a>
											</div>
										</div>
										<div class="col-xs-4 col-sm-4 col-md-2 col-lg-2">
											<div class="widget2 widget3">
												<a onClick="showDiv('div12')" href="javascript:;">
													<div class="stats-left ">
														<i class="material-icons">swap_vert</i>
														<h5>Recharge Report</h5>
													</div>
													<div class="clearfix"></div>
												</a>
											</div>
										</div>
										<div class="col-xs-4 col-sm-4 col-md-2 col-lg-2">
											<div class="widget2 widget3">
												<a onClick="showDiv('div18')" href="javascript:;">
													<div class="stats-left ">
														<i class="material-icons">transform</i>
														<h5>Purchase Reports</h5>
													</div>
													<div class="clearfix"></div>
												</a>
											</div>
										</div>
										<div class="col-xs-4 col-sm-4 col-md-2 col-lg-2">
											<div class="widget2 widget3">
												<a onClick="showDiv('div15')" href="javascript:;">
													<div class="stats-left ">
														<i class="material-icons">swap_horiz</i>
														<h5>DMR Report</h5>
													</div>
													<div class="clearfix"></div>
												</a>
											</div>
										</div>
										<div class="col-xs-4 col-sm-4 col-md-2 col-lg-2">
											<div class="widget2 widget3">
												<a onClick="showDiv('div16')" href="javascript:;" id="">
													<div class="stats-left ">
														<i class="material-icons">system_update</i>
														<h5>Utility Report</h5>
													</div>
													<div class="clearfix"></div>
												</a>
											</div>
										</div>
										<div class="col-xs-4 col-sm-4 col-md-2 col-lg-2">
											<div class="widget2 widget3">
												<a onClick="showDiv('div21')" href="javascript:;" id="">
													<div class="stats-left ">
														<i class="material-icons">update</i>
														<h5>Insurance Reports</h5>
													</div>
													<div class="clearfix"></div>
												</a>
											</div>
										</div>
									</div>
									<div class="row">
										<%
											if (user.getRoleId() != 5) {
										%>

										<div class="col-xs-4 col-sm-4 col-md-2 col-lg-2">
											<div class="widget2 widget3">
												<a onClick="showDiv('div14')" href="javascript:;" id="">
													<div class="stats-left ">
														<i class="fa  fa-university"></i>
														<h5>Balance Transfer Report</h5>
													</div>
													<div class="clearfix"></div>
												</a>
											</div>
										</div>
										<div class="col-xs-4 col-sm-4 col-md-2 col-lg-2">
											<div class="widget2 widget3">
												<a onClick="showDiv('div17')" href="javascript:;" id="">
													<div class="stats-left ">
														<i class="material-icons">lightbulb_outline</i>
														<h5>Balance Request Report</h5>
													</div>
													<div class="clearfix"></div>
												</a>
											</div>
										</div>
										<div class="col-xs-4 col-sm-4 col-md-2 col-lg-2">
											<div class="widget2 widget3">
												<a onClick="showDiv('div29')" href="javascript:;" id="">
													<div class="stats-left ">
														<i class="material-icons">history</i>
														<h5>Refund History</h5>
													</div>
													<div class="clearfix"></div>
												</a>
											</div>
										</div>
										<%
											}
										%>

									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<!-------------- /div24 ---------------------->

		<!-------------- div25 ---------------------->
		<div id="div25" class="row clearfix" style="display: none">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<div class="card">
					<div class="header">
						<h2>
							Assigned Package Details <span class="pull-right"><a
								href="#" onClick="showDiv('div7')" class="body"><i
									class="material-icons">arrow_back</i></a></span>
						</h2>
						<button type="button" class="btn btn-primary"
							ng-click="getAssignedPackageExcel(packdetails);"
							ng-if="packdetails.length > 0">Export</button>
					</div>
					<div class="body">
						<div class="row clearfix">
							<div
								class="col-lg-12 col-md-12 col-sm-12 col-xs-12 form-control-label">
								<div class="body1">

									<div class="row">
										<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
											<table id="datatable1"
												class="table table-striped table-bordered">
												<thead>
													<tr>
														<th class="success text-center">Package Id</th>
														<th class="success text-center">Charge</th>
														<th class="success text-center">Charge Type</th>
														<th class="success text-center">Commission</th>
														<th class="success text-center">Commission Type</th>
														<th class="success text-center">Service Provider</th>

													</tr>
												</thead>
												<tbody>
													<tr ng-repeat="pack in packdetails">
														<td class="text-center">{{pack.package_id}}</td>
														<td class="text-center"><input type="text" name=""
															ng-value="{{pack.charge}}"
															class="form-control text-center" readonly="readonly"></td>
														<td class="text-center"><select id=""
															class="form-control">
																<option>{{pack.charge_type}}</option>
																<option ng-if="pack.charge_type=='PERCENTAGE'">RUPEE</option>
																<option ng-if="pack.charge_type=='RUPEE'">PERCENTAGE</option>
														</select></td>
														<td class="text-center"><input type="text" name=""
															ng-value="{{pack.comm}}" class="form-control text-center"
															readonly="readonly"></td>
														<td class="text-center"><select id=""
															class="form-control">

																<option>{{pack.comm_type}}</option>
																<option ng-if="pack.comm_type=='PERCENTAGE'">RUPEE</option>
																<option ng-if="pack.comm_type=='RUPEE'">PERCENTAGE</option>

														</select></td>
														<td ng-if="pack.serviceProvider==='Encore AEPS'">AEPS</td>
														<td ng-if="pack.serviceProvider!='Encore AEPS'">{{pack.serviceProvider}}</td>

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

		<!-------------- /div25 ---------------------->

		<!-------------- div27 ---------------------->
		<div id="div27" class="row clearfix" style="display: none">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<div class="card">
					<div class="header">
						<h2>
							View Package Details <span class="pull-right"><a href="#"
								onClick="showDiv('div7')" class="body"><i
									class="material-icons">arrow_back</i></a></span>
						</h2>
						<button type="button" class="btn btn-primary"
							ng-click="getPackageDetailExcel(MyPackageDetail);"
							ng-if="MyPackageDetail.length > 0">Export</button>
					</div>
					<div class="body">
						<div class="row clearfix">
							<div
								class="col-lg-12 col-md-12 col-sm-12 col-xs-12 form-control-label">
								<div class="body1">

									<div class="row">
										<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
											<table id="datatable1"
												class="table table-striped table-bordered">
												<thead>
													<tr>

														<th class="success text-center">Package Id</th>
														<th class="success text-center">Charge</th>
														<th class="success text-center">Charge Type</th>
														<th class="success text-center">Commission</th>
														<th class="success text-center">Commission Type</th>
														<th class="success text-center">Service Provider</th>
														<th class="success text-center">Update</th>
													</tr>
												</thead>


												<tbody>
													<tr ng-repeat="packch in MyPackageDetail">
														<td class="text-center">{{packch.package_id}}</td>
														<td class="text-center"><input type="text"
															ng-model="packch.charge" class="form-control text-center"></td>
														<td class="text-center"><select id=""
															ng-model="packch.charge_type" class="form-control">
																<option>{{packch.charge_type}}</option>
																<option ng-if="packch.charge_type=='PERCENTAGE'">RUPEE</option>
																<option ng-if="packch.charge_type=='RUPEE'">PERCENTAGE</option>
														</select></td>
														<td class="text-center"><input type="text"
															ng-model="packch.comm" class="form-control text-center"></td>
														<td class="text-center"><select id=""
															ng-model="packch.comm_type" class="form-control">

																<option>{{packch.comm_type}}</option>
																<option ng-if="packch.comm_type=='PERCENTAGE'">RUPEE</option>
																<option ng-if="packch.comm_type=='RUPEE'">PERCENTAGE</option>

														</select></td>
														<td>{{packch.serviceProvider}}</td>
														<td class="text-center">
															<button class="btn btn-success"
																ng-click="updatemyPackage(packch)"
																style="background: #228B22 !important;">Update</button>
														</td>
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
		<!-------------- /div27 ---------------------->

		<!-------------- Div29 ---------------------->
		<div id="div29" class="row clearfix" style="display: none">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<div class="card">
					<div class="header">
						<h2>Refund History</h2>
					</div>
					<div class="body">
						<form class="form-horizontal">
							<div class="row clearfix">
								<div
									class="col-lg-5 col-md-5 col-sm-4 col-xs-4 form-control-label">
									<label for="email_address_2">Start Date</label>
								</div>
								<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
									<div class="form-group">
										<div class="form-line">
											<input type="text" class="form-control" id="dp17"
												placeholder="Enter Start Date"
												ng-model="refundReport.startDate" readonly="readonly" />
										</div>
									</div>
								</div>
							</div>
							<div class="row clearfix">
								<div
									class="col-lg-5 col-md-5 col-sm-4 col-xs-4 form-control-label">
									<label for="email_address_2">End Date</label>
								</div>
								<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
									<div class="form-group">
										<div class="form-line">
											<input type="text" class="form-control" id="dp18"
												placeholder="Enter End Date" ng-model="refundReport.endDate"
												readonly="readonly" />
										</div>
									</div>
								</div>
							</div>
							<div class="row clearfix">
								<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 "></div>
								<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
									<div class="col-md-offset-3">
										<button type="button" class="btn btn-primary waves-effect"
											ng-click="getRefundReport(refundReport);">Update</button>
										<button type="button" class="btn btn-primary waves-effect"
											onclick="showDiv('div24')">Back</button>
									</div>
								</div>
							</div>
						</form>
						<div class="row clearfix">
							<div class="form-group">
								<div
									class="col-xs-12 col-sm-12 col-md-12 col-lg-12 table-responsive"
									ng-if="refundReportDetails.length > 0">
									<table id="mainTable" class="table table-striped"
										style="margin-left: 1%;">
										<thead>
											<tr style="font-size: 12px; font-weight: bold;">
												<th class="success text-center">Sl. No</th>
												<th class="success text-center">Op Bal</th>
												<th class="success text-center">Credit</th>
												<th class="success text-center">Debit</th>
												<th class="success text-center">Cl Bal</th>
												<th class="success text-center">Narration</th>
												<th class="success text-center">Remark</th>
												<th class="success text-center">Date and Time</th>
											</tr>
										</thead>
										<tbody>
											<tr
												ng-repeat="item in refundReportDetails | startFrom:currentPage*pageSize | limitTo:pageSize"
												ng-if="refundReportDetails.credit != 0 || refundReportDetails.debit != 0">
												<td>{{$index + 1}}</td>
												<td>{{item.openingBal}}</td>
												<td>{{item.credit}}</td>
												<td>{{item.debit}}</td>
												<td>{{item.closingBal}}</td>
												<td>{{item.narration}}</td>
												<td>{{item.remarks}}</td>
												<td>{{item.date}}&nbsp;&nbsp;{{item.time}}</td>
											</tr>
										</tbody>
									</table>
								</div>
								<div data-ng-show="viewRefundReportPagination"
									style="margin-left: 2%">
									<button ng-disabled="currentPage == 0"
										ng-click="currentPage=currentPage-1">Previous</button>
									{{currentPage+1}}/{{numberOfPages()}}
									<button ng-disabled="currentPage >= data.length/pageSize - 1"
										ng-click="currentPage=currentPage+1">Next</button>
									<button type="button" class="btn btn-primary"
										ng-click="getTransactionReportReportExcel(refundReportDetails);"
										ng-if="refundReportDetails.length > 0">Export</button>
								</div>
								<div
									class="col-xs-12 col-sm-12 col-md-12 col-lg-12 table-responsive"
									ng-if="refundReportDetails.length <= 0">
									<table id="mainTable" class="table table-striped">
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
		</div>
		<!-------------- /div29 ---------------------->

		<!-------------- div30 ---------------------->
		<div id="div30" class="row clearfix" style="display: none">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<div class="card">
					<div class="header">
						<h2>Earning Report</h2>
					</div>
					<div class="body">
						<form class="form-horizontal">
							<div class="row clearfix">
								<div
									class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
									<label for="email_address_2">Select User</label>
								</div>
								<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
									<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
										<select class="form-control "
											ng-model="earningReport.userType"
											ng-init="earningReport.userType = '10'">
											<option value="10">Own Earning</option>
											<option ng-repeat="userType in userType"
												ng-if="userType.type > userDetails.roleId && userType.type < 100"
												value="{{userType.type}}">{{userType.role}}</option>
										</select>
									</div>
								</div>
							</div>
							<div class="row clearfix">
								<div
									class="col-lg-5 col-md-5 col-sm-4 col-xs-4 form-control-label">
									<label for="email_address_2">Start Date</label>
								</div>
								<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
									<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 m-l-15">
										<div class="form-group">
											<div class="form-group form-float">
												<div class="form-line">
													<input type="text" class="form-control" id="dp19"
														placeholder="Enter Start Date"
														ng-model="earningReport.startDate" readonly="readonly" />
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="row clearfix">
								<div
									class="col-lg-5 col-md-5 col-sm-4 col-xs-4 form-control-label">
									<label for="email_address_2">End Date</label>
								</div>
								<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
									<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 m-l-15">
										<div class="form-group">
											<div class="form-group form-float">
												<div class="form-line">
													<input type="text" class="form-control" id="dp30"
														placeholder="Enter End Date"
														ng-model="earningReport.endDate" readonly="readonly" />
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="row clearfix">
								<div
									class="col-lg-offset-6 col-md-offset-6 col-sm-offset-6 col-xs-offset-6">
									<button type="button"
										class="btn btn-primary m-t-15 waves-effect"
										ng-click="getEarningReport(earningReport);">Submit</button>
								</div>
							</div>
						</form>

						<div class="row clearfix" style="margin-top: 2%">
							<div
								class="col-xs-12 col-sm-12 col-md-12 col-lg-12 table-responsive"
								ng-if="earningReportDetails.length > 0">
								<table id="mainTable" class="table table-striped">
									<thead>
										<tr style="font-size: 12px; font-weight: bold;">
											<th class="success text-center">Sl. No</th>
											<th class="success text-center">User Id</th>
											<th class="success text-center">Commission</th>
											<th class="success text-center">Surcharge</th>
											<th class="success text-center">Narration</th>
											<th class="success text-center">Date and Time</th>
										</tr>
									</thead>
									<tbody>
										<tr
											ng-repeat="item in earningReportDetails | startFrom:currentPage*pageSize | limitTo:pageSize">
											<td>{{$index + 1}}</td>
											<td>{{item.name}}<br />{{item.mobile}}
											</td>
											<td>{{item.comm}}</td>
											<td>{{item.charge}}</td>
											<td>{{item.narration}}</td>
											<td>{{item.date}}&nbsp;&nbsp;{{item.time}}</td>
										</tr>
										<tr>
											<td></td>
											<td></td>
											<td>{{earningReportDetails | totalCommission:'comm'}}</td>
											<td>{{earningReportDetails | totalCommission:'charge'}}</td>
											<td></td>
											<td></td>
										</tr>
									</tbody>
								</table>
							</div>
							<div data-ng-show="viewEarningReportPagination">
								<button ng-disabled="currentPage == 0"
									ng-click="currentPage=currentPage-1">Previous</button>
								{{currentPage+1}}/{{numberOfPages()}}
								<button ng-disabled="currentPage >= data.length/pageSize - 1"
									ng-click="currentPage=currentPage+1">Next</button>

							</div>
							<div
								class="col-xs-12 col-sm-12 col-md-12 col-lg-12 table-responsive"
								ng-if="earningReportDetails.length <= 0">
								<table id="mainTable" class="table table-striped">
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
		<!-------------- /div30 ---------------------->

		<!-------------- Div31 ---------------------->
		<div id="div31" class="row clearfix" style="display: none">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<div class="card">
					<div class="header">
						<h2>PAN Report</h2>
					</div>
					<div class="body">
						<form class="form-horizontal">
							<div class="row clearfix">
								<div
									class="col-lg-5 col-md-5 col-sm-4 col-xs-4 form-control-label">
									<label for="email_address_2">Start Date</label>
								</div>
								<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
									<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 m-l-15">
										<div class="form-group">
											<div class="form-group form-float">
												<div class="form-line">
													<input type="text" ng-model="panReport.startDate"
														class="form-control" id="dp31"
														placeholder="Enter Start Date" readonly="readonly" />
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="row clearfix">
								<div
									class="col-lg-5 col-md-5 col-sm-4 col-xs-4 form-control-label">
									<label for="email_address_2">End Date</label>
								</div>
								<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
									<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 m-l-15">
										<div class="form-group">
											<div class="form-group form-float">
												<div class="form-line">
													<input type="text" class="form-control"
														ng-model="panReport.endDate" id="dp32"
														placeholder="Enter End Date" readonly="readonly" />
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
										<button type="button" class="btn btn-primary waves-effect"
											ng-click="getpanReport(panReport)">Agent Report</button>
										<button type="button" class="btn btn-primary waves-effect"
											ng-click="getpanCouponReport(panReport)">Coupon
											Report</button>
									</div>
								</div>
							</div>
						</form>
						<div class="row clearfix" id="sdiv19" style="margin-top: 2%">
							<div
								class="col-xs-12 col-sm-12 col-md-12 col-lg-12 table-responsive"
								ng-if="panReports.length > 0">
								<table id="mainTable" class="table table-striped">
									<thead>
										<tr style="font-size: 12px; font-weight: bold;">
											<th class="success text-center">Sl. No</th>
											<th class="success text-center">Username</th>
											<th class="success text-center">psaname</th>
											<th class="success text-center">location</th>
											<th class="success text-center">phone</th>
											<th class="success text-center">emailid</th>
											<th class="success text-center">pan</th>
											<th class="success text-center">dob</th>
											<th class="success text-center">aadhaar</th>
											<th class="success text-center">Date & Time</th>
											<th class="success text-center">panuser_id</th>
											<th class="success text-center">Status</th>
											<th class="success text-center">requestId</th>


										</tr>
									</thead>
									<tbody>
										<tr
											ng-repeat="report in panReports | startFrom:currentPage*pageSize | limitTo:pageSize">
											<td style="font-size: 12px;">{{$index + 1}}</td>
											<td style="font-size: 12px;">{{report.usernm}}<br>
												({{report.usermbl}})
											</td>
											<td style="font-size: 12px;">{{report.psaname}}</td>
											<td style="font-size: 12px;">{{report.location}}</td>
											<td style="font-size: 12px;">{{report.phone1}}</td>
											<td style="font-size: 12px;">{{report.emailid}}</td>
											<td style="font-size: 12px;">{{report.pan}}</td>
											<td style="font-size: 12px;">{{report.dob}}</td>
											<td style="font-size: 12px;">{{report.aadhaar}}</td>
											<td style="font-size: 12px;">{{report.date}} <br>{{report.time}}
											</td>
											<td style="font-size: 12px;">{{report.panuser_id}}</td>
											<td style="font-size: 12px;">{{report.status}}</td>
											<td style="font-size: 12px;">{{report.requestId}}</td>

										</tr>
									</tbody>
								</table>
							</div>
							<div data-ng-show="viewRechargeReportsPagination">
								<button ng-disabled="currentPage == 0"
									ng-click="currentPage=currentPage-1">Previous</button>
								{{currentPage+1}}/{{numberOfPages()}}
								<button ng-disabled="currentPage >= data.length/pageSize - 1"
									ng-click="currentPage=currentPage+1">Next</button>
							</div>
							<div
								class="col-xs-12 col-sm-12 col-md-12 col-lg-12 table-responsive"
								ng-if="panReports.length <= 0">
								<table cellspacing="1"
									class="table table-striped table-bordered table-hover">
									<tbody>
										<tr>
											<th align="center" style="color: red;">No Records Found</th>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
						<div class="row clearfix visibility" id="sdiv20">
							<div
								class="col-xs-12 col-sm-12 col-md-12 col-lg-12 table-responsive"
								ng-if="panCouponReports.length > 0">
								<table id="mainTable" class="table table-striped">
									<thead>
										<tr style="font-size: 12px; font-weight: bold;">
											<th class="success text-center">Sl. No</th>
											<th class="success text-center">User Name</th>
											<th class="success text-center">Pan UserId</th>
											<th class="success text-center">Total Coupon</th>
											<th class="success text-center">Date & Time</th>
											<th class="success text-center">Status</th>
											<th class="success text-center">requestId</th>


										</tr>
									</thead>
									<tbody>
										<tr
											ng-repeat="report in panCouponReports | startFrom:currentPage*pageSize | limitTo:pageSize">
											<td style="font-size: 12px;">{{$index + 1}}</td>
											<td style="font-size: 12px;">{{report.usernm}}<br>
												({{report.usermbl}})
											</td>
											<td style="font-size: 12px;">{{report.panuser_id}}</td>
											<td style="font-size: 12px;">{{report.totalCoupon}}</td>
											<td style="font-size: 12px;">{{report.date}} <br>{{report.time}}
											</td>
											<td style="font-size: 12px;">{{report.status}}</td>
											<td style="font-size: 12px;">{{report.requestId}}</td>

										</tr>
									</tbody>
								</table>
							</div>
							<div
								class="col-xs-12 col-sm-12 col-md-12 col-lg-12 table-responsive m-l-20">
								<div data-ng-show="viewRechargeReportsPagination">
									<button ng-disabled="currentPage == 0"
										ng-click="currentPage=currentPage-1">Previous</button>
									{{currentPage+1}}/{{numberOfPages()}}
									<button ng-disabled="currentPage >= data.length/pageSize - 1"
										ng-click="currentPage=currentPage+1">Next</button>
								</div>
							</div>
							<div
								class="col-xs-12 col-sm-12 col-md-12 col-lg-12 table-responsive"
								ng-if="panCouponReports.length <= 0">
								<table cellspacing="1"
									class="table table-striped table-bordered table-hover">
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
		<!-------------- /Div31 ---------------------->
<!-- ----------------------- div33 ------------------------------- -->
		<div id="div33" class="row clearfix" style="display: none">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<div class="card">
					<div class="header">
						<h2>Latest Transaction Report</h2>
					</div>
					<div class="body">
						<form class="form-horizontal">
							<div class="row clearfix">
								<div
									class="col-xs-12 col-sm-12 col-md-12 col-lg-12 table-responsive">

									<table id="mainTable" class="table table-striped">
										<thead>
											<tr style="font-size: 12px; font-weight: bold;">
												<th class="success text-center">Sl. No</th>
												<th class="success text-center">Name</th>
												<th class="success text-center">Mobile</th>
												<th class="success text-center">Op Bal</th>
												<th class="success text-center">Amount</th>
												<th class="success text-center">Charge</th>
												<th class="success text-center">Comm</th>
												<th class="success text-center">Cl Bal</th>
												<th class="success text-center">Status</th>
												<th class="success text-center">Opt Tid</th>
												<th class="success text-center">Tid</th>
												<th class="success text-center">Source</th>
												<th class="success text-center">Operator</th>
												<th class="success text-center">Date&Time</th>
											</tr>
										</thead>
										<tbody>
											<tr ng-repeat="drr in latestTransactionReport ">
												<td style="font-size: 12px;">{{$index + 1}}</td>
												<td style="font-size: 12px;">{{drr.name}}
													({{drr.usermobile}})</td>
												<td style="font-size: 12px;">{{drr.mobile}}</td>
												<td style="font-size: 12px;">{{drr.openBal}}</td>
												<td style="font-size: 12px;">{{drr.amount}}</td>
												<td style="font-size: 12px;">{{drr.charge}}</td>
												<td style="font-size: 12px;">{{drr.comm}}</td>
												<td style="font-size: 12px;">{{drr.closeBal}}</td>
												<td style="font-size: 12px;">{{drr.validation}}</td>
												<td style="font-size: 12px;">{{drr.oid}}</td>
												<td style="font-size: 12px;">{{drr.tid}}</td>
												<td style="font-size: 12px;">{{drr.source}}</td>
												<td style="font-size: 12px;">{{drr.serviceProvider}}</td>
												<td style="font-size: 12px;">{{drr.date}} {{drr.time}}</td>

											</tr>
										</tbody>
									</table>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
		<!-- ----------------------- /div33 ------------------------------- -->


		<!-------------- Div34 ---------------------->
	<div id="div34" class="row clearfix" style="display: none">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<div class="card">
					<div class="header">
						<h2>View Bank Details</h2>
					</div>
					<div class="body">
						<form class="form-horizontal ng-pristine ng-valid">
											
						
							<div class="row clearfix">
								<div class="form-group">
									<div
										class="col-xs-12 col-sm-12 col-md-12 col-lg-12 table-responsive"
										ng-if="bankdt.length > 0">
										<table id="mainTable" class="table table-striped"
											style="margin-left: 1%;">
											<thead>
												<tr>
														<th>Sl. No</th>
														<th>Name</th>
														<th>Mobile</th>
														<th>Email</th>
														<th>Bank</th>
														<th>Branch</th>
														<th>IFSC</th>
														<th>Account</th>
														<th>status</th>
													</tr>
											</thead>
											<tbody>
												<tr ng-repeat="item in bankdt">
														<td>{{$index + 1}}</td>
														<td>{{item.name}}</td>
														<td>{{item.mobile}}</td>
														<td>{{item.email}}</td>
														<td>{{item.bank}}</td>
														<td>{{item.branch}}</td>
														<td>{{item.ifsc}}</td>
														<td>{{item.account}}</td>
														<td>{{item.status}}</td>
													</tr>
											</tbody>
										</table>
									</div>


								</div>
							</div>
						</form>
					</div>

				</div>
			</div>
		</div>

		<!-------------- /div34 ---------------------->


		<!-------------- div35 ---------------------->
		<div id="div35" class="row clearfix m-l-0 m-r-0"
			style="display: none;">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<div class="card">
					<div class="header">
						<h2>
							<span id="titel"></span><span class="pull-right"><a
								href="#" class="body" onclick="aepsclose();"><i
									class="material-icons">arrow_back</i></a></span>
						</h2>
					</div>
					<div class="body">
						<div class="row clearfix">
							<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 "
								style="border: 0px none; height: 100%; margin-top: 0px; overflow: hidden; width: 100%;">
								<iframe
									sandbox="allow-forms allow-pointer-lock allow-popups allow-scripts allow-top-navigation"
									id="myIframe" src="" name="getframe"
									style="border: 0px none; height: 100vh; margin-top: 0px; overflow: hidden; width: 100%;"></iframe>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-------------- /div35 ---------------------->
		<div id="div135" class="row clearfix m-l-0 m-r-0"
			style="display: none;">
			<iframe
				srcdoc="<div class="wrapper wrapper-content animated fadeInRight">
<button id="rzp-button1" style="display: none">Pay</button>
<script src="https://checkout.razorpay.com/v1/checkout.js"></script>
<script>
BASE_URL="http://bijoukart.in/";
    //test mode = rzp_test_7jh3UZMPPjJPro
    //live mode= rzp_live_Km08Vw110f6RqQ
</script>
    <script>
var options = {
    //"key": "rzp_test_5NisuTFcowZwoV", // Enter the Key ID generated from the Dashboard
    "key":"rzp_test_fozGAJLdv9ExIH",
    "amount": 10000, // Amount is in currency subunits. Default currency is INR. Hence, 50000 refers to 50000 paise
    "currency": "INR",
    "name": "Encore Digitech Pvt Ltd",
    "order_id": "order_HBqsJvK7dUkUTz",
	"receipt":"xyz",
    "description": "",
     "image": "https://www.payvendors.in/public/front/images/LOGO.png",
    "handler": function (response){
        var payment_id = response.razorpay_payment_id;
       /* $.ajax({
                headers: {
                    'X-CSRF-TOKEN': $('meta[name="csrf-token"]').attr('content')
                },
                url: BASE_URL+'/vendor/get-response-razorpay',
                type: 'POST',
                data: {payment_id},
                success: function (data) {
                 var response = JSON.parse(data);
                 if(response.status){
                     window.location.href = BASE_URL + '/vendor/thank-you' 
                 }
                },
                error: function () {
                    console.log('There is some error in finding state. Please try again.');
                }
            });*/
             window.location.href = "https://bijoukart.in" + '/get-response-razorpay?id='+'359'+'&payment_id='+payment_id+'&order_id='+'order_HBqsJvK7dUkUTz'; 
		
    },
    "modal": {
    "ondismiss": function(){
         window.location.href ='https://bijoukart.in/failed';
     }
    },
    "prefill": {
        "name": "Encore Digitech Pvt Ltd",
        "email": "tusharkanti.dutta@edpl.info",
        "contact": "8240593169"
    },
    "theme": {
        "color": "#3399cc"
    }
};
var rzp1 = new Razorpay(options);
rzp1.on('payment.failed', function (response){
	  window.location.href ='https://bijoukart.in/failed';
});
document.getElementById('rzp-button1').onclick = function(e){
    rzp1.open();
    e.preventDefault();
}
</script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
$(document).ready(function(){
    $('#rzp-button1').click();
})
</script>
</div>"
				title="Razorpay Response"></iframe>
		</div>
		<!-------------- div36 ---------------------->
		<div id="div36" class="row clearfix" style="display: none">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<div class="card">
					<div class="header">
						<h2>NSDL Pan Report</h2>
					</div>
					<div class="body">
						<form class="form-horizontal">
							<div class="row clearfix">
								<div class="col-md-6 col-lg-6 col-sm-12 col-xs-12">
									<div
										class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
										<label for="email_address_2">Status</label>
									</div>
									<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
										<div class="form-group">
											<div class="form-line1">
												<select class="form-control" ng-model="nsdlReportt.status"
													ng-init="nsdlReportt.status = 'All'">
													<option value="All">All</option>
													<option value="SUCCESS">SUCCESS</option>
													<option value="PENDING">PENDING</option>
													<option value="FAILED">FAILED</option>
													<option value="HOLD">HOLD</option>
												</select>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="row clearfix">
								<div class="col-md-6 col-lg-6 col-sm-12 col-xs-12">
									<div
										class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
										<label for="email_address_2">Start Date</label>
									</div>
									<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
										<div class="form-group">
											<div class="form-line1">
												<input type="text" class="form-control" id="dp38"
													placeholder="Enter Start Date"
													ng-model="nsdlReportt.startDate" readonly="readonly" />
											</div>
										</div>
									</div>
								</div>
								<div class="col-md-6 col-lg-6 col-sm-12 col-xs-12">
									<div
										class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
										<label for="password_2">End Date</label>
									</div>
									<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
										<div class="form-group">
											<div class="form-line1">
												<input type="text" class="form-control" id="dp39"
													placeholder="Enter End Date" ng-model="nsdlReportt.endDate"
													readonly="readonly" />
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="row clearfix">
								<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 "></div>
								<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
									<div class="col-md-offset-3">
										<button type="button" class="btn btn-primary waves-effect"
											ng-click="getnsdlReport(nsdlReportt);">Update</button>
										<button type="button" class="btn btn-primary waves-effect"
											onclick="showDiv('div24')">Back</button>
									</div>
								</div>
							</div>
							<div class="row clearfix">
								<div class="form-group">
									<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12"
										ng-if="nsdlReport.length > 0"
										style="overflow: scroll; margin-left: 1%;">
										<table id="mainTable" class="table table-striped">
											<thead>
												<tr>
													<th class="success text-center">Sl. No</th>
													<th class="success text-center">Name</th>
													<th class="success text-center">Mobile</th>
													<th class="success text-center">Email</th>
													<th class="success text-center">DOB</th>
													<th class="success text-center">Sex</th>
													<th class="success text-center">FatherName</th>
													<th class="success text-center">Address</th>
													<th class="success text-center">Pin</th>
													<!-- <th class="success text-center">ID</th> -->
													<th class="success text-center">Status</th>
													<th class="success text-center">Date and Time</th>
													<th class="success text-center">ACK NO</th>
													<!-- <th class="success text-center">ACK SLIP</th> -->
													<th class="success text-center">Invoice No</th>
													<th class="success text-center">Remark</th>
													<th class="success text-center">Type</th>
													<th class="success text-center">Action</th>
												</tr>
											</thead>
											<tbody>
												<tr
													ng-repeat="report in nsdlReport | startFrom:currentPage*pageSize | limitTo:pageSize">
													<td>{{$index + 1}}</td>
													<td>{{report.name}} {{report.middlename}}
														{{report.lastname}}</td>
													<td>{{report.mobile}}</td>
													<td>{{report.email}}</td>
													<td>{{report.dob}}</td>
													<td>{{report.sex}}</td>
													<td>{{report.fathername}} {{report.fathernamem}}
														{{report.fathernamel}}</td>
													<td>{{report.add1}} {{report.add2}}</td>
													<td>{{report.pin}}</td>
												
													<td class="{{report.status}}">{{report.status}}</td>
													<td>{{report.date}}&amp;{{report.time}}</td>
													<td>{{report.ackno}}</td>
												
													<td>{{report.invoiceno}}</td>
													<td>{{report.remark}}</td>
													<td>{{report.applicationType}}</td>
													<td class="text-center" style="font-size: 12px;">
														<div class="input-group" style="font-size: 30px"
															ng-if="report.status == 'PENDING'||report.status == 'HOLD'">
															<button data-toggle="modal"
																data-target="#nsdlPendingModal"
																ng-click="getNSDLDetails(report);"
																; style="border-radius: 100%;">Edit</button>
														</div>
														<div class="input-group" style="font-size: 30px"
															ng-if="report.status === 'SUCCESS'">
															<a
																href="http://doc.safegoal.co.in/{{report.ackfilename}}"
																target="_blank">download</a>
															</button>
														</div>
													</td>
												</tr>
											</tbody>
										</table>
									</div>
									<div data-ng-show="viewnsdlReportPagination"
										style="margin-left: 2%">
										<button ng-disabled="currentPage == 0"
											ng-click="currentPage=currentPage-1">Previous</button>
										{{currentPage+1}}/{{numberOfPages()}}
										<button ng-disabled="currentPage >= data.length/pageSize - 1"
											ng-click="currentPage=currentPage+1">Next</button>
										<!-- <button type="button" class="btn btn-primary" ng-click="getRechargeReportExcel(RechargeReports);" ng-if = "RechargeReports.length > 0">Export</button> -->
									</div>
									<div class="table-responsive" ng-if="nsdlReport.length <= 0"
										style="margin-left: 30px;">
										<table cellspacing="1"
											class="table table-striped table-bordered table-hover">
											<tbody>
												<tr>
													<th align="center" style="color: red;">No Records
														Found</th>
												</tr>
											</tbody>
										</table>
									</div>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
		<!-- Modal -->
		<div id="nsdlAkdownload" class="modal fade" role="dialog"
			tabindex="-1" aria-labelledby="modalLabel" aria-hidden="true">
			<div class="modal-dialog" style="margin: 8% auto; width: 90%;">

				<!-- Modal content-->
				<div class="modal-content" style="width: 43%; left: 18%;">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">Download Documents</h4>
					</div>
					<div class="modal-body">
						<div class="row" style="margin: 0px;">
							<div class="col-md-12 col-lg-12 col-sm-12 col-xs-12">
								<div class="col-md-6" style="margin-bottom: 20px;">
									<a href="{{fileurlImage}}" download="{{filename}}">
										Download Image </a>
								</div>
							</div>
						</div>
					</div>
				</div>

			</div>
		</div>

		<!-- ------------------ /div36 ----------------- -->

		<!-------------- div37 ---------------------->
		<div id="div37" class="row clearfix"
			style="display: none; margin: 0px">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<div class="card">
					<div class="header">
						<h2>
							Edit NSDL PAN User Detail <span class="pull-right"><a
								onclick="showDiv('div36')"><i
									class="fa fa-arrow-circle-left fa-2x"></i></a></span>
						</h2>
					</div>
					<div class="body">
						<div class="body"
							style="margin: 0px; margin: 0 auto; padding: 5%; width: 60%;">
							<form class="form-horizontal">
								<div class="clearfix row">

									<div class="row clearfix">
										<div
											class="col-lg-12 col-md-12 col-sm-12 col-xs-12 col-lg-offset-0 col-md-offset-0 ">
											<div class="form-group form-float">
												<div class="form-line">
													<select class="form-control show-tick"
														ng-model="nsdl.applicationType">
														<option value="0">Select Application Type</option>
														<option value="NEW">NEW</option>
														<option value="CORRECTION">CORRECTION</option>
													</select>

												</div>
											</div>
										</div>
										<div
											class="col-lg-12 col-md-12 col-sm-12 col-xs-12 col-lg-offset-0 col-md-offset-0 ">
											<div class="form-group form-float">
												<label class="form-label">Old Pan NO</label>
												<div class="form-line">
													<input type="text" class="form-control"
														ng-model="nsdl.oldpan"
														ng-change="nsdl.oldpan=nsdl.oldpan.toUpperCase();">

												</div>
											</div>
										</div>
										<div
											class="col-lg-12 col-md-12 col-sm-12 col-xs-12 col-lg-offset-0 col-md-offset-0 ">
											<div class="form-group form-float">
												<label class="form-label">Customer Name</label>
												<div class="form-line">
													<input type="text" class="form-control"
														ng-model="nsdl.name"
														ng-change="nsdl.name=nsdl.name.toUpperCase();">

												</div>
											</div>
										</div>
										<div
											class="col-lg-12 col-md-12 col-sm-12 col-xs-12 col-lg-offset-0 col-md-offset-0 ">
											<div class="form-group form-float">
												<label class="form-label">Customer Middle Name</label>
												<div class="form-line">
													<input type="text" class="form-control"
														ng-model="nsdl.middlename"
														ng-change="nsdl.middlename=nsdl.middlename.toUpperCase();">

												</div>
											</div>
										</div>
										<div
											class="col-lg-12 col-md-12 col-sm-12 col-xs-12 col-lg-offset-0 col-md-offset-0 ">
											<div class="form-group form-float">
												<label class="form-label">Customer Last Name</label>
												<div class="form-line">
													<input type="text" class="form-control"
														ng-model="nsdl.lastname"
														ng-change="nsdl.lastname=nsdl.lastname.toUpperCase();">

												</div>
											</div>
										</div>
										<div
											class="col-lg-12 col-md-12 col-sm-12 col-xs-12 col-lg-offset-0 col-md-offset-0 ">
											<div class="form-group form-float">
												<label class="form-label">Mobile number</label>
												<div class="form-line">
													<input type="text" class="form-control"
														ng-model="nsdl.mobile" readonly="readonly">

												</div>
											</div>
										</div>
										<div
											class="col-lg-12 col-md-12 col-sm-12 col-xs-12 col-lg-offset-0 col-md-offset-0 ">
											<div class="form-group form-float">
												<label class="form-label">Customer Email</label>
												<div class="form-line">
													<input type="text" class="form-control"
														ng-model="nsdl.email">

												</div>
											</div>
										</div>
										<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
											<div class="form-group form-float">
												<label class="form-label">Date Of Birth</label>
												<div class="form-line">
													<input type="text" class="form-control" id="dp27"
														ng-model="nsdl.dob">

												</div>
											</div>
										</div>
										<div
											class="col-lg-12 col-md-12 col-sm-12 col-xs-12 col-lg-offset-0 col-md-offset-0 ">
											<div class="form-group form-float">
												<label class="form-label">Sex</label>
												<div class="form-line">
													<select class="form-control show-tick" ng-model="nsdl.sex">
														<option value="0">Select Sex Type</option>
														<option value="MALE">MALE</option>
														<option value="FEMALE">FEMALE</option>
													</select>

												</div>
											</div>
										</div>
										<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
											<div class="form-group form-float">
												<label class="form-label">Father's Name</label>
												<div class="form-line">
													<input type="text" class="form-control"
														ng-model="nsdl.fathername"
														ng-change="nsdl.fathername=nsdl.fathername.toUpperCase();">

												</div>
											</div>
										</div>
										<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
											<div class="form-group form-float">
												<label class="form-label">Father's Middle Name</label>
												<div class="form-line">
													<input type="text" class="form-control"
														ng-model="nsdl.fathernamem"
														ng-change="nsdl.fathernamem=nsdl.fathernamem.toUpperCase();">

												</div>
											</div>
										</div>
										<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
											<div class="form-group form-float">
												<label class="form-label">Father's Last Name</label>
												<div class="form-line">
													<input type="text" class="form-control"
														ng-model="nsdl.fathernamel"
														ng-change="nsdl.fathernamel=nsdl.fathernamel.toUpperCase();">

												</div>
											</div>
										</div>
										<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 myForm">
											<div class="form-group form-float">
												<label class="form-label">Address Line 1</label>
												<div class="form-line">

													<textarea class="form-control" rows="2"
														style="height: 50px;" ng-model="nsdl.add1"
														ng-change="nsdl.add1=nsdl.add1.toUpperCase();"> </textarea>

												</div>
											</div>
										</div>
										<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
											<div class="form-group form-float">
												<label class="form-label">PIN Code</label>
												<div class="form-line">
													<input type="text" class="form-control" ng-model="nsdl.pin">

												</div>
											</div>
										</div>
										<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
											<div class="form-group form-float">
												<label class="form-label">District</label>
												<div class="form-line">
													<input type="text" class="form-control"
														ng-model="nsdl.district">

												</div>
											</div>
										</div>
										<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
											<div class="form-group form-float">
												<label class="form-label">State</label>
												<div class="form-line">
													<input type="text" class="form-control"
														ng-model="nsdl.state">

												</div>
											</div>
										</div>
										<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
											<div class="form-group form-float">
												<label class="form-label">Name On Card</label>
												<div class="form-line">
													<input type="text" class="form-control"
														ng-model="nsdl.namecard">

												</div>
											</div>
										</div>
										<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
											<div class="form-group form-float">
												<div class="form-line">
													<input type="text" class="form-control"
														ng-model="nsdl.prooftype">
												</div>
											</div>
										</div>
										<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
											<div class="form-group form-float">
												<label class="form-label">Proof of Identity Number</label>
												<div class="form-line">
													<input type="text" class="form-control"
														ng-model="nsdl.idno"
														ng-change="nsdl.idno=nsdl.idno.toUpperCase();">

												</div>
											</div>
										</div>
										<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
											<div class="form-group form-float">
												<label class="form-label">Proof of Address</label>
												<div class="form-line">
													<input type="text" class="form-control"
														ng-model="nsdl.addressproof"
														ng-change="nsdl.addressproof=nsdl.addressproof.toUpperCase();">

												</div>
											</div>
										</div>
										<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
											<div class="form-group form-float">
												<label class="form-label">Proof of DOB</label>
												<div class="form-line">
													<input type="text" class="form-control"
														ng-model="nsdl.dobproof"
														ng-change="nsdl.dobproof=nsdl.dobproof.toUpperCase();">

												</div>
											</div>
										</div>
									
										<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12  ">
											<div class="col-lg-8 col-md-8 col-sm-12 col-xs-12  ">
												<div class="">
													<label class="form-label"> Proof Of Identity </label> <input
														type="file" class="form-control"
														file-model="ProofOfIdentity">
													<div>
														<font color="{{Identityclr}}">{{Identitymsg}}</font>
													</div>
												</div>
											</div>

											<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12  ">
												<button type="button"
													class="btn bg-cyan btn-block btn-lg waves-effect"
													ng-click="updateidproof(nsdl);" style="margin-top: 9%">SUBMIT</button>

											</div>

										</div>

										<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
											<div class="form-group form-float">
												<button type="button"
													class="btn bg-cyan btn-block btn-lg waves-effect"
													ng-click="editnsdlpandt(nsdl);">SUBMIT</button>
												<!-- <button type="button"
													class="btn bg-cyan btn-block btn-lg waves-effect"
													onclick="showDiv('div36')" ng-click="fetchnsdlpan();">SUBMIT</button> -->
											</div>
										</div>

									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-------------- /div37 ---------------------->

		<!-------------- Div7 ---------------------->
		<div id="div7" class="row clearfix" style="display: none">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<div class="card">
					<div class="header">
						<h2>Allot No Report</h2>
					</div>
					<div class="body">
						<form class="form-horizontal">
							<div class="row clearfix">
								<div
									class="col-lg-5 col-md-5 col-sm-4 col-xs-4 form-control-label">
									<label for="email_address_2">Start Date</label>
								</div>
								<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
									<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 m-l-15">
										<div class="form-group">
											<div class="form-group form-float">
												<div class="form-line">
													<input type="text" ng-model="allotReport.startDate"
														class="form-control" id="dp33"
														placeholder="Enter Start Date" readonly="readonly" />
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="row clearfix">
								<div
									class="col-lg-5 col-md-5 col-sm-4 col-xs-4 form-control-label">
									<label for="email_address_2">End Date</label>
								</div>
								<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
									<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 m-l-15">
										<div class="form-group">
											<div class="form-group form-float">
												<div class="form-line">
													<input type="text" class="form-control"
														ng-model="allotReport.endDate" id="dp34"
														placeholder="Enter End Date" readonly="readonly" />
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
										<button type="button" class="btn btn-primary waves-effect"
											ng-click="getallotReport(allotReport)">Allot No
											Report</button>
									</div>
								</div>
							</div>
						</form>

						<div class="row clearfix " style="margin-top: 2%">
							<div
								class="col-xs-12 col-sm-12 col-md-12 col-lg-12 table-responsive"
								ng-if="allotNoreport.length > 0">
								<table id="mainTable" class="table table-striped">
									<thead>
										<tr style="font-size: 12px; font-weight: bold;">
											<th class="success text-center">Sl. No</th>
											<th class="success text-center">Username</th>
											<th class="success text-center">Previous AllotNo</th>
											<th class="success text-center">Debit</th>
											<th class="success text-center">Credit</th>
											<th class="success text-center">Closed AllotNo</th>
											<th class="success text-center">Date & Time</th>
										</tr>
									</thead>
									<tbody>
										<tr
											ng-repeat="report in allotNoreport | startFrom:currentPage*pageSize | limitTo:pageSize">
											<td style="font-size: 12px;">{{$index + 1}}</td>
											<td style="font-size: 12px;">{{report.usernm}}<br>
												({{report.usermbl}})
											</td>
											<td style="font-size: 12px;">{{report.prevallotNo}}</td>
											<td style="font-size: 12px;">{{report.debit}}</td>
											<td style="font-size: 12px;">{{report.credit}}</td>
											<td style="font-size: 12px;">{{report.emailid}}</td>
											<td style="font-size: 12px;">{{report.closeAllot}}</td>
											<td style="font-size: 12px;">{{report.date}} <br>{{report.time}}
											</td>
										</tr>
									</tbody>
								</table>
							</div>
							<div data-ng-show="viewRechargeReportsPagination">
								<button ng-disabled="currentPage == 0"
									ng-click="currentPage=currentPage-1">Previous</button>
								{{currentPage+1}}/{{numberOfPages()}}
								<button ng-disabled="currentPage >= data.length/pageSize - 1"
									ng-click="currentPage=currentPage+1">Next</button>
							</div>
							<div
								class="col-xs-12 col-sm-12 col-md-12 col-lg-12 table-responsive"
								ng-if="allotNoreport.length <= 0">
								<table cellspacing="1"
									class="table table-striped table-bordered table-hover">
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
		<!-------------- /Div7 ---------------------->
		<div id="div45" class="row clearfix"
			style="display: none; margin: 0px;">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<div class="card">
					<div class="header">
						<h2>Add Rbl AEPS Bank Details</h2>
					</div>
					<div class="body">
						<div class="body">

							<div class="row clearfix">
								<div
									class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
									<label for="email_address_2">Mobile</label>
								</div>
								<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
									<div class="form-group">
										<div class="form-line">
											<input type="text" class="form-control"
												ng-model="rblBank.mobile">
										</div>
									</div>
								</div>
							</div>
							<div class="row clearfix">
								<div
									class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
									<label for="password_2">Email</label>
								</div>
								<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
									<div class="form-group">
										<div class="form-line">
											<input type="text" ng-model="rblBank.email"
												class="form-control">
										</div>
									</div>
								</div>

							</div>
							<div class="row clearfix">
								<div
									class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
									<label for="email_address_2">Name</label>
								</div>
								<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
									<div class="form-group">
										<div class="form-line">
											<input type="text" ng-model="rblBank.name"
												class="form-control">
										</div>
									</div>
								</div>
							</div>

							<div class="row clearfix">
								<div
									class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
									<label for="email_address_2">Bank</label>
								</div>
								<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
									<div class="form-group">
										<div class="form-line">
											<input type="text" ng-model="rblBank.bank"
												class="form-control">
										</div>
									</div>
								</div>
							</div>

							<div class="row clearfix">
								<div
									class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
									<label for="email_address_2">Branch</label>
								</div>
								<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
									<div class="form-group">
										<div class="form-line">
											<input type="text" ng-model="rblBank.branch"
												class="form-control">
										</div>
									</div>
								</div>
							</div>

							<div class="row clearfix">
								<div
									class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
									<label for="email_address_2">Account</label>
								</div>
								<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
									<div class="form-group">
										<div class="form-line">
											<input type="text" ng-model="rblBank.account"
												class="form-control">
										</div>
									</div>
								</div>
							</div>

							<div class="row clearfix">
								<div
									class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
									<label for="email_address_2">IFC</label>
								</div>
								<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
									<div class="form-group">
										<div class="form-line">
											<input type="text" ng-model="rblBank.ifsc"
												class="form-control">
										</div>
									</div>
								</div>
							</div>

							<div class="row clearfix">
								<div
									class="col-lg-offset-5 col-md-offset-5 col-sm-offset-5 col-xs-offset-5">
									<button type="button"
										class="btn btn-primary m-t-15 waves-effect"
										ng-click="addRBLBank(rblBank);">Update</button>
								</div>
							</div>

						</div>
					</div>
				</div>
			</div>
		</div>
		
		
		<div id="div108" class="row clearfix"
			style="display: none; margin: 0px;">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<div class="card">
					<div class="header">
						<h2>P2A MONEY REGISTRATION</h2>
					</div>
					<div class="body">
						<div class="body">

							<div class="row clearfix">
								<div
									class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
									<label for="email_address_2">Mobile</label>
								</div>
								<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
									<div class="form-group">
										<div class="form-line">
											<input type="text" class="form-control"
												ng-model="p2aregis.RemMobile">
										</div>
									</div>
								</div>
							</div>
							<div class="row clearfix">
								<div
									class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
									<label for="password_2">Name</label>
								</div>
								<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
									<div class="form-group">
										<div class="form-line">
											<input type="text" ng-model="p2aregis.RemName"
												class="form-control">
										</div>
									</div>
								</div>

							</div>
						
							

							

							<div class="row clearfix">
								<div
									class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
									<label for="email_address_2">BeneAccNo</label>
								</div>
								<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
									<div class="form-group">
										<div class="form-line">
											<input type="text" ng-model="p2aregis.BeneAccNo"
												class="form-control">
										</div>
									</div>
								</div>
							</div>

							<div class="row clearfix">
								<div
									class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
									<label for="email_address_2">BeneIFSC</label>
								</div>
								<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
									<div class="form-group">
										<div class="form-line">
											<input type="text" ng-model="p2aregis.BeneIFSC"
												class="form-control">
										</div>
									</div>
								</div>
							</div>

							<div class="row clearfix">
								<div
									class="col-lg-offset-5 col-md-offset-5 col-sm-offset-5 col-xs-offset-5">
									<button type="button"
										class="btn btn-primary m-t-15 waves-effect"
										ng-click="p2aregistration(p2aregis);">Update</button>
								</div>
							</div>

						</div>
					</div>
				</div>
			</div>
		</div>


	
		<!-------------- div150 ---------------------->

		<div id="div150" class="row clearfix" style="display: none">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<div class="card">
					<div class="header">
						<h2>
							View Settlement <span class="pull-right"
								onclick="sshowDiv('sdiv151');"><b> Back</b></span>
						</h2>
					</div>
					<div class="body">
						<div class="row" id="sdiv151">
							<form class="form-horizontal">
								<div class="row clearfix">
									<div
										class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
										<label for="email_address_2">View Settlement</label>
									</div>
									<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
										<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
											<div class="form-group form-float">
												<button class="btn btn-info"
													ng-click="getSettlementBalance()">Click to</button>
												<!-- <a href="javascript:void(0); " class="btn btn-info" onclick="sshowDiv('sdiv150');">Click to </a> -->
											</div>
										</div>
									</div>
								</div>
							</form>
						</div>

						<div class="row" id="sdiv150" style="display: none;">

							<form class="form-horizontal">
								<div class="row clearfix">
									<div
										class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
										<label for="email_address_2">Total Amount</label>
									</div>
									<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
										<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
											<div class="form-group form-float"
												style="margin-left: 0px !important;">
												<div class="form-line">
													<input type="text" class="form-control"
														placeholder="Enter Account Name"
														ng-model="settlement.totalamount" readonly="readonly">
												</div>
											</div>
										</div>
									</div>
								</div>



								<div class="row clearfix">
									<div
										class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
										<label for="email_address_2">Settlement Type</label>
									</div>
									<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
										<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
											<div class="form-group form-float"
												style="margin-left: 0px !important;">
												<div class="form-line">
													<select class="form-control" ng-model="settlement.type"
														ng-change="changeSettlementtype(settlement.type)">
														<option value="Settle to Wallet">Settle to Wallet</option>
														<option value="Settle to Bank">Settle to Bank</option>

													</select>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="row clearfix" style="display: none;" id="div152">
									<div
										class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
										<label for="email_address_2">Eligible Amount FOR
											wallet</label>
									</div>
									<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
										<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
											<div class="form-group form-float"
												style="margin-left: 0px !important;">
												<div class="form-line">
													<input type="text" class="form-control"
														placeholder="Enter Account Name"
														ng-model="settlement.eligiblewallet" readonly="readonly">
												</div>
											</div>
										</div>
									</div>
								</div>

								<div class="row clearfix" style="display: none;" id="div153">
									<div
										class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
										<label for="email_address_2">Eligible Amount For Bank</label>
									</div>
									<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
										<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
											<div class="form-group form-float"
												style="margin-left: 0px !important;">
												<div class="form-line">
													<input type="text" class="form-control"
														placeholder="Enter Account Name"
														ng-model="settlement.eligiblebank" readonly="readonly">
												</div>
											</div>
										</div>
									</div>
								</div>

								<div class="row clearfix" style="display: none;" id="div154">
									<div
										class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
										<label for="email_address_2">Settlement Amount</label>
									</div>
									<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
										<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
											<div class="form-group form-float"
												style="margin-left: 0px !important;">
												<div class="form-line">
													<input type="text" class="form-control" placeholder=""
														ng-model="settlement.amount">
												</div>
											</div>
										</div>
									</div>
								</div>


								<div class="row clearfix">
									<div
										class="col-lg-offset-6 col-md-offset-6 col-sm-offset-6 col-xs-offset-6">
										<button type="button"
											class="btn btn-primary m-t-15 waves-effect"
											ng-click="settlerequest(settlement);">Submit</button>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>

		<!-------------- /div35 ---------------------->

		<!-------------- div26 ---------------------->
		<div id="div156" class="row clearfix" style="display: none">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<div class="card">
					<div class="header">
						<h2> AEPS Report</h2>
					</div>
					<div class="body">
						<form class="form-horizontal ng-pristine ng-valid">
							<div class="row clearfix">
								<div
									class="col-lg-5 col-md-5 col-sm-4 col-xs-4 form-control-label">
									<label for="email_address_2">Start Date</label>
								</div>
								<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
									<div class="form-group">
										<div class="form-line focused">
											<input type="text" class="form-control" id="dp48"
												placeholder="Enter Start Date"
												ng-model="aepsReport.startDate" readonly="readonly">
										</div>
									</div>
								</div>
							</div>
							<div class="row clearfix">
								<div
									class="col-lg-5 col-md-5 col-sm-4 col-xs-4 form-control-label">
									<label for="email_address_2">End Date</label>
								</div>
								<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
									<div class="form-group">
										<div class="form-line focused">
											<input type="text" class="form-control" id="dp49"
												placeholder="Enter End Date" ng-model="aepsReport.endDate"
												readonly="readonly">
										</div>
									</div>
								</div>
							</div>
							<div class="row clearfix">
								<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 "></div>
								<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
									<div class="col-md-offset-3">
										<!-- <button type="button" class="btn btn-primary waves-effect"
											ng-click="getRBLAEPSReport(aepsReport);">Update</button> -->
										<button type="button" class="btn btn-primary waves-effect"
											ng-click="getYesBankAEPSReport(aepsReport);">Update</button>
										<button type="button" class="btn btn-primary waves-effect"
											onclick="showDiv('div23')">Back</button>
									</div>
								</div>
							</div>
							<div class="row clearfix">
								<div class="form-group">
									<div
										class="col-xs-12 col-sm-12 col-md-12 col-lg-12 table-responsive"
										ng-if="rblReport.length > 0">
										<table id="mainTable" class="table table-striped"
											style="margin-left: 1%;">
											<thead>
												<tr style="font-size: 12px; font-weight: bold;">
													<th class="success text-center">Sl. No</th>
													<th class="success text-center">aadharNumber</th>
													<!-- <th class="success text-center">agentId</th> -->
													<th class="success text-center">amount</th>

													<!-- 	
												<th class="success text-center">bankResponseMsg</th>
												<th class="success text-center">commissionAmt</th> -->
													<th class="success text-center">date</th>
													<th class="success text-center">orderId</th>
													<th class="success text-center">Status</th>
													<!-- 
													<th class="success text-center">gstAmt</th> -->
													<th class="success text-center">type</th>
													<!-- 	<th class="success text-center">requestId</th> -->
													<th class="success text-center">rrn</th>
													<th class="success text-center">stan</th>
													<th class="success text-center">statusCode</th>
													<!-- 
													<th class="success text-center">statusMessage</th>
													<th class="success text-center">tdsAmt</th>
													<th class="success text-center">terminalId</th> -->
													<th class="success text-center">time</th>
												</tr>
											</thead>
											<tbody>
												<tr ng-repeat="rblRepo in rblReport">
													<td>{{$index + 1}}</td>
													<td>{{rblRepo.Aadhar}}</td>
													<!-- <td>{{rblRepo.agentcode}}</td> -->
													<td>{{rblRepo.TxnAmount}}</td>
													<!-- <td>{{rblRepo.bankResponseMsg}}</td>
													<td>{{rblRepo.commissionAmt}}</td> -->
													<td>{{rblRepo.date}}</td>
													<td>{{rblRepo.referenceNo}}</td>
													<td>{{rblRepo.Status}}</td>
													<!-- <td>{{rblRepo.gstAmt}}</td> -->
													<td>{{rblRepo.paymentStatus}}</td>
													<td ng-if="rblRepo.processingCode=='011'">Balance
														Withdrawal</td>
													<td ng-if="rblRepo.processingCode=='210000'">Balance
														Deposit</td>
													<td ng-if="rblRepo.processingCode=='010'">Balance
														Enquiry</td>
													<!-- <td>{{rblRepo.requestId}}</td> -->
													<td>{{rblRepo.RRN}}</td>
													<td>{{rblRepo.STAN}}</td>
													<td>{{rblRepo.Status}}</td>
													<!-- <td>{{rblRepo.statusMessage}}</td> -->
													<!-- <td>{{rblRepo.tdsAmt}}</td>
													<td>{{rblRepo.terminalId}}</td> -->
													<td>{{rblRepo.time}}</td>
												</tr>
											</tbody>
										</table>
									</div>


								</div>
							</div>
						</form>
					</div>

				</div>
			</div>
		</div>

		<!-------------- /div26 ---------------------->
		<!-------------- div157 ---------------------->
		<div id="div157" class="row clearfix" style="display: none">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<div class="card">
					<div class="header">
						<h2> SETTLEMENT Report</h2>
					</div>
					<div class="body">
						<form class="form-horizontal ng-pristine ng-valid">
							<div class="row clearfix">
								<div
									class="col-lg-5 col-md-5 col-sm-4 col-xs-4 form-control-label">
									<label for="email_address_2">Start Date</label>
								</div>
								<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
									<div class="form-group">
										<div class="form-line focused">
											<input type="text" class="form-control" id="dp50"
												placeholder="Enter Start Date"
												ng-model="settleReport.startDate" readonly="readonly">
										</div>
									</div>
								</div>
							</div>
							<div class="row clearfix">
								<div
									class="col-lg-5 col-md-5 col-sm-4 col-xs-4 form-control-label">
									<label for="email_address_2">End Date</label>
								</div>
								<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
									<div class="form-group">
										<div class="form-line focused">
											<input type="text" class="form-control" id="dp51"
												placeholder="Enter Start Date"
												ng-model="settleReport.endDate" readonly="readonly">
										</div>
									</div>
								</div>
							</div>
							<div class="row clearfix">
								<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 "></div>
								<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
									<div class="col-md-offset-3">
										<button type="button" class="btn btn-primary waves-effect"
											ng-click="getRBLSETTLEMENTReport(settleReport);">Update</button>
										<button type="button" class="btn btn-primary waves-effect"
											onclick="showDiv('div23')">Back</button>
									</div>
									<div class="col-md-4" style="margin-top: 20px">
								<label>Status</label>
									<select ng-model="SearchtransactionType.type" class="form-control"><option value="">ALL</option>
									<option value="Settle to Wallet">Settle to Wallet</option>
													<option value="Settle to Bank">Settle to Bank</option>
													<option value="Transaction">Transaction</option>
													<option value="REFUND">REFUND</option></select>			
								</div>
								</div>
							</div>
							<div class="row clearfix">
								<div class="form-group">
									<div
										class="col-xs-12 col-sm-12 col-md-12 col-lg-12 table-responsive"
										ng-if="settlementreport.length > 0">
										<table id="mainTable" class="table table-striped"
											style="margin-left: 1%;">
											<thead>
												<tr style="font-size: 12px; font-weight: bold;">
													<th class="success text-center">Sl. No</th>
													<th class="success text-center">settleopbal</th>
													<th class="success text-center">settleclbal</th>
													<th class="success text-center">amount</th>
													<th class="success text-center">date</th>
													<th class="success text-center">time</th>
													<th class="success text-center">type</th>
												<th class="success text-center">status</th>
												</tr>
											</thead>
											<tbody>
												<tr ng-repeat="rblsettle in settlementreport |filter:SearchtransactionType">
													<td>{{$index + 1}}</td>
													<td>{{rblsettle.settleopbal}}</td>
													<td>{{rblsettle.settleclbal}}</td>
													<td>{{rblsettle.amount}}</td>
													<td>{{rblsettle.date}}</td>
													<td>{{rblsettle.time}}</td>
													<td>{{rblsettle.type}}</td>
											<td>{{rblsettle.status}}</td>
												</tr>
											</tbody>
										</table>
									</div>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>


		<!-------------- /div28 ---------------------->
		<div id="div159" class="row clearfix" style="display: none;margin: 0px;">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<div class="card">
					<div class="header">
						<h2>PENDING AEPS STATUS CHANGE</h2>
					</div>
					<div class="body">
						<form class="form-horizontal ng-pristine ng-valid">
							<div class="row clearfix">
								<div class="col-lg-5 col-md-5 col-sm-4 col-xs-4 form-control-label">
									<label for="email_address_2">Start Date</label>
								</div>
								<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
									<div class="form-group">
										<div class="form-line focused">
											<input type="text" class="form-control" id="dp54" placeholder="Enter Start Date" ng-model="aepslogreport.startDate" readonly="readonly">
										</div>
									</div>
								</div>
							</div>
							<div class="row clearfix">
								<div class="col-lg-5 col-md-5 col-sm-4 col-xs-4 form-control-label">
									<label for="email_address_2">End Date</label>
								</div>
								<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
									<div class="form-group">
										<div class="form-line focused">
											<input type="text" class="form-control" id="dp55" placeholder="Enter End Date" ng-model="aepslogreport.endDate" readonly="readonly">
										</div>
									</div>
								</div>
							</div>
							
							
							<div class="row clearfix">
								<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 "></div>
								<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
									<div class="col-md-offset-3">
										<button type="button" class="btn btn-primary waves-effect" ng-click="getaepslogreport(aepslogreport);">Update</button>
										<!-- <button type="button" class="btn btn-primary waves-effect" onclick="showDiv('div23')">Back</button> -->
									</div>
								</div>
							</div>
							<div class="row clearfix">
								<div class="form-group">
									<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 table-responsive" ng-if = "aepslogreport.length > 0">
										
									<table id="mainTable" class="table table-striped" style="    margin-left: 1%;">
										<thead>
											<tr style="font-size: 12px; font-weight: bold;">
											<!-- <th class="success text-center">Action</th> -->
												<th class="success text-center">Sl. No</th>	
												<!-- <th class="success text-center">User Mobile</th> -->
												<!-- <th class="success text-center">name</th> -->
																					
												<th class="success text-center">referenceno</th>
												<th class="success text-center">agentcode</th>
												<th class="success text-center">status</th>
												<th class="success text-center">date</th>
												<!-- <th class="success text-center">apiremarks</th> -->
												<th class="success text-center">type</th>
												<th class="success text-center">Update</th>
												
												
											</tr>
										</thead>
										<tbody>
											<tr	ng-repeat="aepslog in aepslogreport"  >
											<!-- <td  > <a  href="javascript:void(0);" class="btn btn-danger"  ng-click="aepslogmodal(aepslog)"> Send</a></td> -->
											<td>{{$index + 1}}</td>	
											<td>{{aepslog.referenceno}}</td>
											<!-- <td>{{rblsettle.name}}</td> -->
											<!-- <td>{{rblsettle.mobile}}</td> -->
											<td>{{aepslog.agentcode}}</td>
											<td>{{aepslog.status}}</td>
											<td>{{aepslog.date}}</td>
											<!-- <td>{{aepslog.apiremarks}}</td> -->
											<!-- <td>{{aepslog.time}}</td> -->
											<td>{{aepslog.type}}</td>
											
											<td >
											<a class="btn btn-info" ng-click="aepsstatuscheck(aepslog)" ng-if="aepslog.status==='PENDING'"> Submit</a>
											
											</td>
											</tr>
									</tbody>
								</table>
							</div>
													
						
								</div>
							</div>
						</form>
					</div>

				</div>
			</div>
		</div>
		
		
		<div id="div107" class="row clearfix" style="display: none; margin: 0px;">
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
							<div class="card">
								<div class="header">
									<h2>Pay2Money Transfer Report</h2>
								</div>
								<div class="body">
									<form class="form-horizontal ng-pristine ng-valid">
										<div class="row clearfix">
											<div class="col-lg-5 col-md-5 col-sm-4 col-xs-4 form-control-label">
												<label for="email_address_2">Start Date</label>
											</div>
											<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
												<div class="form-group">
													<div class="form-line focused">
														<input type="text" class="form-control" id="dp57" placeholder="Enter Start Date" ng-model="p2aReport.startDate" readonly="readonly">
													</div>
												</div>
											</div>
										</div>
										<div class="row clearfix">
											<div class="col-lg-5 col-md-5 col-sm-4 col-xs-4 form-control-label">
												<label for="email_address_2">End Date</label>
											</div>
											<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
												<div class="form-group">
													<div class="form-line focused">
														<input type="text" class="form-control" id="dp58" placeholder="Enter End Date" ng-model="p2aReport.endDate" readonly="readonly">
													</div>
												</div>
											</div>
										</div>
										
										
										<div class="row clearfix">
											<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 "></div>
											<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
												<div class="col-md-offset-3">
													<button type="button" class="btn btn-primary waves-effect" ng-click="getp2aReport(p2aReport);">Update</button>
													<button type="button" class="btn btn-primary waves-effect" onclick="showDiv('div43')">Back</button>
													<button type="button" class="btn btn-primary" ng-click="getp2aimpsReportsExcel(p2aimpsReports);" ng-if="p2aimpsReports.length > 0">Export</button>
												</div>
											</div>
										</div>
										<div class="row clearfix">
											<div class="form-group">
												<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 table-responsive" ng-if = "p2aimpsReports.length > 0">
													
												<table id="mainTable" class="table table-striped" style="    margin-left: 1%;">
													<thead>
														<tr style="font-size: 12px; font-weight: bold;">
														<th class="success text-center">Action</th>
															
														     <th class="success text-center">Name</th> 	
															<th class="success text-center">User Mobile</th>
															<th class="success text-center">BankRRN</th>
															<th class="success text-center">TranRefNo</th>
															<th class="success text-center">BeneAccNo</th>
															<th class="success text-center">BeneIFSC</th>
															<th class="success text-center">RemName</th>
															<th class="success text-center">RemMobile</th>
															<th class="success text-center">RetailerCode</th>
															<th class="success text-center">amount</th>
															<th class="success text-center">Date & Time</th>
															<th class="success text-center">Status</th>
															
															
														</tr>
													</thead>
													<tbody>
														<tr	ng-repeat="report in p2aimpsReports"  >
														
															<td>{{$index + 1}}</td>
																			<td>{{report.uname}}<br> ({{report.uname}})
																			</td>
																			<td>{{report.mobile}}</td>
																			<td>{{report.BankRRN}}</td>
																			<td>{{report.TranRefNo}}</td>
																			<td>{{report.BeneAccNo}}</td>
																			<td>{{report.BeneIFSC}}</td>
																			<td>{{report.RemName}}</td>
																			<td>{{report.RemMobile}}</td>
																			<td>{{report.RetailerCode}}</td>
																			<td>{{report.amount}}</td>
																			
																			
																			
																			<td>{{report.date}} <br>{{report.time}}
																			</td>
																		
																			<td>{{report.status}}</td>
														</tr>
												</tbody>
											</table>
										</div>
																
									
											</div>
										</div>
									</form>
								</div>
			
							</div>
						</div>
						</div>
						
						<div id="div208" class="row clearfix" style="display: none; margin: 0px;">
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
							<div class="card">
								<div class="header">
									<h2>Micro Atm Report</h2>
								</div>
								<div class="body">
									<form class="form-horizontal ng-pristine ng-valid">
										<div class="row clearfix">
											<div class="col-lg-5 col-md-5 col-sm-4 col-xs-4 form-control-label">
												<label for="email_address_2">Start Date</label>
											</div>
											<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
												<div class="form-group">
													<div class="form-line focused">
														<input type="text" class="form-control" id="dp63" placeholder="Enter Start Date" ng-model="p2aReport.startDate" readonly="readonly">
													</div>
												</div>
											</div>
										</div>
										<div class="row clearfix">
											<div class="col-lg-5 col-md-5 col-sm-4 col-xs-4 form-control-label">
												<label for="email_address_2">End Date</label>
											</div>
											<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
												<div class="form-group">
													<div class="form-line focused">
														<input type="text" class="form-control" id="dp64" placeholder="Enter End Date" ng-model="p2aReport.endDate" readonly="readonly">
													</div>
												</div>
											</div>
										</div>
										
										
										<div class="row clearfix">
											<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 "></div>
											<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
												<div class="col-md-offset-3">
													<button type="button" class="btn btn-primary waves-effect" ng-click="getMicroATMReport(p2aReport);">Update</button>
													<button type="button" class="btn btn-primary waves-effect" onclick="showDiv('div43')">Back</button>
													<button type="button" class="btn btn-primary" ng-click="getp2aimpsReportsExcel(microatmReports);" ng-if="microatmReports.length > 0">Export</button>
												</div>
											</div>
										</div>
										<div class="row clearfix">
											<div class="form-group">
												<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 table-responsive" ng-if = "microatmReports.length > 0">
													
												<table id="mainTable" class="table table-striped" style="    margin-left: 1%;">
													<thead>
														<tr style="font-size: 12px; font-weight: bold;">
														
															
														      <th class="success text-center">SL NO</th> 	
															<th class="success text-center">Cardno</th>
															<th class="success text-center">Date</th>
															<th class="success text-center">Time</th>
															<th class="success text-center">InvoiceNumber</th>
															<th class="success text-center">RespCode</th>
															<th class="success text-center">RRN</th>
															<th class="success text-center">ReferenceNo</th>
															<th class="success text-center">UserName</th>
															<th class="success text-center">amount</th>
															<th class="success text-center">Type</th>
															<!-- <th class="success text-center">Name</th>
															<th class="success text-center">Mobile</th> -->
															
															
														</tr>
													</thead>
													<tbody>
														<tr	ng-repeat="report in microatmReports"  >
														
															<td>{{$index + 1}}</td>
																			<td>{{report.cardno}}</td>
																			<td>{{report.date}}</td>
																			<td>{{report.time}}</td>
																			<td>{{report.invoiceNumber}}</td>
																			<td>{{report.respCode}}</td>
																			<td>{{report.rrn}}</td>
																			<td>{{report.referenceno}}</td>
																			<td>{{report.username}}</td>
																			<td>{{report.amount}}</td>
																			<td>{{report.type}}</td>
																			<!-- <td>{{report.name}}</td>
																			<td>{{report.mobile}}</td> -->
																			
																			
																			
																			
																			
														</tr>
												</tbody>
											</table>
										</div>
																
									
											</div>
										</div>
									</form>
								</div>
			
							</div>
						</div>
						</div>
	
	<!-------------- /div162 ---------------------->
	<div id="div162" class="row clearfix" style="display: none">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<div class="card">
					<div class="header">
						<h2>Register For Insurance </h2>
					</div>
					<div class="body">
					
					
						<form class="form-horizontal">
							<div class="row clearfix">
								<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
									<label for="email_address_2">First Name</label>
								</div>
								<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
									<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
										<div class="form-group form-float"
											style="margin-left: 0px !important;">
											<div class="form-line">
												<input type="text" class="form-control" placeholder="Enter First Name"  ng-model ="userDetails.firstname">
											</div>
										</div>
									</div>
								</div>
							</div>
							
							
						
							<div class="row clearfix">
								<div
									class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
									<label for="email_address_2">Last Name</label>
								</div>
								<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
									<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
										<div class="form-group form-float"
											style="margin-left: 0px !important;">
											<div class="form-line">
												<input type="text" class="form-control" placeholder="Enter Last Name"  ng-model ="userDetails.lastName">

											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="row clearfix" >
								<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
									<label for="email_address_2">Mobile Number</label>
								</div>
								<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
									<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
										<div class="form-group form-float"
											style="margin-left: 0px !important;">
											<div class="form-line">
												<input type="text" ng-model="userDetails.mobile" ng-change="checkUniqueUser(userDetails.mobile);" class="form-control" ng-keypress="filterValue($event);" maxlength="10">											
												</div>
										</div>
									</div>
								</div>
							</div>
							
							<div class="row clearfix">
								<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
									<label for="email_address_2">E-Mail</label>
								</div>
								<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
									<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
										<div class="form-group form-float"
											style="margin-left: 0px !important;">
											<div class="form-line">
												<input type="text" class="form-control" placeholder="Enter E-Mail"  ng-model="userDetails.email">
											</div>
										</div>
									</div>
								</div>
							</div>
						
							
							
						<div class="row clearfix">
								<div class="col-lg-offset-6 col-md-offset-6 col-sm-offset-6 col-xs-offset-6">
									<button type="button" class="btn btn-primary m-t-15 waves-effect" ng-click = "insurancegiblBillPaymentRegi(userDetails);">Submit</button>
								</div>
							</div>
						
							
						</form>
						
					</div>
				</div>
			</div>
		</div>
	
		<div id="div151" class="row clearfix" style="display: none">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<div class="card">
					<div class="header">
						<h2>Insurance </h2>
					</div>
					<div class="body">
					
					
						<form class="form-horizontal">
							<div class="row clearfix">
								<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
									<label for="email_address_2">Category</label>
								</div>
								<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
									<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
										<div class="form-group form-float"
											style="margin-left: 0px !important;">
											<div class="form-line">
												<select class="form-control" ng-model="insurancePremium.category">
																	<option value="0">GIBL Home page</option>
																	<option value="1">Car insurance</option>
																	<option value="2">Wheeler insurance</option>
																</select>
											</div>
										</div>
									</div>
								</div>
							</div>
							
							
						<div class="row clearfix">
								<div class="col-lg-offset-6 col-md-offset-6 col-sm-offset-6 col-xs-offset-6">
									<button type="submit" class="btn btn-primary" ng-click="insurancegiblBillPayment(insurancePremium);">Submit</button>
								</div>
							</div>
						
							
						</form>
						
					</div>
				</div>
			</div>
		</div>
		
		 <div id="div170" class="row clearfix" style="display: none">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<div class="card">
					<div class="header">
						<h2>Add Wallet</h2>
					</div>
					<div class="body">
						<form class="form-horizontal ng-pristine ng-valid">
							<div class="row clearfix">
								<div class="col-lg-5 col-md-5 col-sm-4 col-xs-4 form-control-label">
									<label for="email_address_2">Enter Amount</label>
								</div>
								<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
									<div class="form-group">
										<div class="form-line focused">
											<input type="text" class="form-control"  placeholder="Enter Amount" ng-model="paytmwall.amount" >
										</div>
									</div>
								</div>
							</div>
							
							<div class="row clearfix">
								<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 "></div>
								<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
									<div class="col-md-offset-3">
										<button type="button" class="btn btn-primary waves-effect" ng-click="payWallet(paytmwall);">Update</button>
										<button type="button" class="btn btn-primary waves-effect" onclick="showDiv('div23')">Back</button>
									</div>
								</div>
							</div>
							
						</form>
					</div>

				</div>
			</div>
		</div>
		
				<!-------------- div105 ---------------------->
		<div id="div105" class="row clearfix" style="display: none; margin: 0px;">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<div class="card">
					<div class="header">
						<h2> AEPS Report</h2>
					</div>
					<div class="body">
						<form class="form-horizontal ng-pristine ng-valid">
							<div class="row clearfix">
								<div class="col-lg-5 col-md-5 col-sm-4 col-xs-4 form-control-label">
									<label for="email_address_2">Start Date</label>
								</div>
								<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
									<div class="form-group">
										<div class="form-line focused">
											<input type="text" class="form-control" id="dp21" placeholder="Enter Start Date" ng-model="iciciaepsReport.startDate" readonly="readonly">
										</div>
									</div>
								</div>
							</div>
							<div class="row clearfix">
								<div class="col-lg-5 col-md-5 col-sm-4 col-xs-4 form-control-label">
									<label for="email_address_2">End Date</label>
								</div>
								<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
									<div class="form-group">
										<div class="form-line focused">
											<input type="text" class="form-control" id="dp22" placeholder="Enter End Date" ng-model="iciciaepsReport.endDate" readonly="readonly">
										</div>
									</div>
								</div>
							</div>
							<div class="row clearfix">
								<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 "></div>
								<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
									<div class="col-md-offset-3">
									
										<!-- <button type="button" class="btn btn-primary waves-effect" ng-click="getRBLAEPSReport(aepsReport);">Update</button> -->
										<button type="button" class="btn btn-primary waves-effect" ng-click="getICICIBankAEPSReport(iciciaepsReport);">Update</button>
										<button type="button" class="btn btn-primary waves-effect" onclick="showDiv('div43')">Back</button>
										<button type="button" class="btn btn-primary" ng-click="getrblReportExcel(fingeraepsReport);" ng-if="fingeraepsReport.length > 0">Export</button>
									</div>
									<div class="col-md-4" style="margin-top: 20px">
												<input class="form-control" type="text" step="1"
													placeholder=" ENTER SEARCH PARTICULAR USER  " ng-model="Searchname4">
												</div>
									<div class="col-md-4" style="margin-top: 20px">
								<label>Status</label>
									<select ng-model="Searchstatus.Status" class="form-control"><option value="">ALL</option>
									<option value="successful">successful</option>
									<option value="failed">failed</option></select>			
								</div>
								<div class="col-md-4" style="margin-top: 20px">
								<label>Type</label>
									<select ng-model="SearchtransactionType.transactionType" class="form-control"><option value="">ALL</option>
									<option value="WITHDRAWAL">WITHDRAWAL</option>
									<option value="BALANCEINFO">BALANCEINFO</option></select>			
								</div>			
								</div>
							</div>
							<div class="row clearfix">
								<div class="form-group">
									<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 table-responsive" ng-if = "fingeraepsReport.length > 0">
									<table id="mainTable" class="table table-striped" style="    margin-left: 1%;">
										<thead>
											<tr style="font-size: 12px; font-weight: bold;">
												<th class="success text-center">Sl. No</th>	
												<th class="success text-center">username</th>
												<!-- <th class="success text-center">name</th>
												<th class="success text-center">mobile</th> -->
												<th class="success text-center">Transaction Id</th>
												<th class="success text-center">Marchend Transaction Id</th>
												<th class="success text-center">amount</th>
												<th class="success text-center">Balance amount</th>
												<th class="success text-center">RRN</th>
												<th class="success text-center">paymentStatus</th>
												<th class="success text-center">type</th>
												<th class="success text-center">date</th>
												<th class="success text-center">time</th>
											</tr>
										</thead>
										<tbody>
											<tr	ng-repeat="rblRepo in fingeraepsReport | filter:Searchname4| filter:Searchstatus | filter:SearchtransactionType"  >
											<td>{{$index + 1}}</td>	
											<td>{{rblRepo.username}}</td>
											<!-- <td>{{rblRepo.name}}</td>
											<td>{{rblRepo.mobile}}</td> -->
											<td>{{rblRepo.EncoreTransactionId}}</td>
											<td>{{rblRepo.merchantTxnId}}</td>
											<td>{{rblRepo.transactionAmount}}</td>
											<td>{{rblRepo.balanceAmount}}</td>
											<td>{{rblRepo.bankRRN}}</td>
											<td>{{rblRepo.Status}}</td>
											<td>{{rblRepo.transactionType}}</td>
												<td>{{rblRepo.date}}</td>
											<td>{{rblRepo.time}}</td>
											</tr>
									</tbody>
								</table>
							</div>
													
						
								</div>
							</div>
						</form>
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
	<script src="assets/scripts/angular_reV2.min.js?version=1.1.0"></script>
	<script src="assets/js/admin.js"></script>
	<script src="assets/js/pages/index.js"></script>
	<script src="assets/js/demo.js"></script>
	<script src="assets/js/datepicker-date.js"></script>
	<script src="assets/js/stellarnav.min.js"></script>

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

		function aepsclose() {

			showDiv('div0');
			$("body").removeClass("ls-closed");
			$(".content1").addClass("content");
			$(".content").removeClass("content1");

		}
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
		function showPancard(divId) {
			var div = "#" + divId;
			$('div[id^=div]').hide();
			$(div).slideToggle(600).show();
		}
	</script>

	<script type="text/javascript">
		$(function() {
			$('.multiselect-ui').multiselect({
				includeSelectAllOption : true
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

			$(".aeps-triger").click(function() {

				$("body").removeClass("ls-closed");
				$("body").addClass("ls-closed");

				$(".content").addClass("content1");
				$("section").removeClass("content");

				/* ----- AEPS ------ */

				var iframecont = $("#myIframe").contents();

				iframecont.find(".splash-container").hide();
			});

			$(".oneWay").change(function() {
				$(".dp21").attr("disabled", "disabled");
			});

			$(".roundTrip").change(function() {
				$(".dp21").removeAttr("disabled");
			});

		});
		function tabShowHide(firstTab, secondTab) {

			$("#" + firstTab).hide();
			$("#" + secondTab).show();
		}
	</script>

	<script type="text/javascript">
		$(function toggleChevron(e) {
			$(e.target).prev('.panel-heading').find("i.indicator").toggleClass(
					'fa-caret-down fa-caret-right');
			$('#accordion').on('hidden.bs.collapse', toggleChevron);
			$('#accordion').on('shown.bs.collapse', toggleChevron);
		});
	</script>

	<script type="text/javascript">
		function showFairDetails(flightDetail) {

			var div = "." + flightDetail;
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
	
	function OpenIframe(titleText){
		
		$("#titel").html(titleText);
		showDiv('div35');
		$("#MyIframe").show();
		$('#myIframe').contents().find('img').hide();
	}

	
	</script>
	<script type="text/javascript">
		
			$(document).ready(function() {
			    
			})
	</script>
	<script>
function walletRefillRequest() {
  document.getElementById("myframe");
}
</script>

</body>

</html>