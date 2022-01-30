
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
<title>SBI</title>
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

<script src="assets/js/jquery.min.js"></script>
<script src="assets/js/jquery-1.11.3.min.js"></script>
<script src="assets/js/bootstrap-multiselect.js"></script>
<link href="assets/css/newstyle.css" rel="stylesheet" />
<link href="assets/css/stellarnav.min.css" rel="stylesheet" />
<link href="assets/css/all.min.css" rel="stylesheet" />

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
	
	<div class="container-fluid navbar1 navbar" style="box-shadow: 0 2px 10px rgba(0, 0, 0, 0.2);">
	<div class="row">
	<div class="col-md-1"> 
		<!-- <a class="navbar-brand " href="home"><img class="logo" alt="" src="assets/images/logo.png" ></a> -->
		<a class="navbar-brand " href="home"><img class="logo" id="logo" src = ""></a>
	</div>
	<div class="col-md-11">
		<div class="stellarnav">
			<ul>
				<li class="active"><a href="home"> <i class="material-icons">home</i><span>Home</span></a></li>
					
					<li><a onClick="showDiv('div0')" href="#"> <i class="material-icons">content_copy</i>
							<span>SBI CARD FORM APPLY</span></a></li>
							
							<li><a onClick="showDiv('div903')" href="#"> <i class="material-icons">content_copy</i>
							<span> SBI CARD for print</span></a></li>
							
							<li><a onClick="showDiv('div900')" href="#"> <i class="material-icons">content_copy</i>
							<span> SBI card  Application Report</span></a></li>
							<%if(user.getRoleId()>2) {%>
							<li><a onClick="showDiv('div270')" href="#" ng-click="mycardlist()"> <i class="material-icons">content_copy</i>
				<span>Add wallet to card</span></a></li>
				<%} %>
				
				<li><a onClick="showDiv('div271')" href="#" > <i class="material-icons">content_copy</i>
				<span>User Wallet Report</span></a></li>
				<li><a href="flightsearch"><i class="fas fa-fighter-jet font_i"></i> <span>Flight</span></a></li>
				<li><a href="BBPS"> <i class="fas fa-utensils font_i"></i> <span>Hotel</span></a></li>						
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
	
	<%-- <div class="col-md-1">
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
	
	</div> --%>
	</div>
	</div>
	<!-- ----------------------------------------End---------------------------------------------- -->

	<section class="content">
	<div class="container-fluid">
		<!-- Widgets -->
		<!----------------- Div0 ----------->
	<%if( user.getRoleId()>2) {%>
		<div id="div0" class="row clearfix" style="display: block;">
			
			<div class="dd" style="width: 100%; padding: 50px 0;display: inline-block;">
					<div class="fo-container" style="width: 1170px;padding-right: 15px;padding-left: 15px;margin-right: auto;margin-left: auto;">
						<div class="fo-row" style="margin-right: -15px;margin-left: -15px;">
							<div class="fo-col-md-10" style="width:83%; padding-right: 15px;padding-left: 15px;">
								<div class="sfdg" style="background-color: #ffffff;padding: 20px;
									box-shadow: 2px 3px 20px #c6c6c6;">
									<div class="fo-row" style="margin-right: -15px;margin-left: -15px;display: flex;">
										<div class="fo-col-md-12" style="margin-right:15px;margin-left:15px; width: 100%;">
											<h4 style="font-size: 17px; font-weight: 700; margin: 10px 0;">Name of the Applicant :</h4>
											<form>
												<label style="font-size: 13px;">First Name :</label>
												<input ng-model = "uploaddetail.firstname"  style="display: block;width: 100%;height: 34px;padding: 6px 12px;font-size: 14px;line-height: 1.42857143;color: #555;background-color: #fff;background-image: none;border: 1px solid #ccc;border-radius: 4px;box-shadow: inset 0 1px 1px rgba(0,0,0,.075);" type="text" name="">
												<label style="font-size: 13px;">Middle Name :</label>
												<input ng-model = "uploaddetail.middlename"style="display: block;width: 100%;height: 34px;padding: 6px 12px;font-size: 14px;line-height: 1.42857143;color: #555;background-color: #fff;background-image: none;border: 1px solid #ccc;border-radius: 4px;box-shadow: inset 0 1px 1px rgba(0,0,0,.075);" type="text" name="">
												<label style="font-size: 13px;">Last Name :</label>
												<input ng-model = "uploaddetail.lastname" style="display: block;width: 100%;height: 34px;padding: 6px 12px;font-size: 14px;line-height: 1.42857143;color: #555;background-color: #fff;background-image: none;border: 1px solid #ccc;border-radius: 4px;box-shadow: inset 0 1px 1px rgba(0,0,0,.075);" type="text" name="">
												<h4 style="font-size: 17px; font-weight: 700; margin: 10px 0;">Address of the Applicant :</h4>
												<label style="font-size: 13px;">Address-1 :</label>
												<input ng-model = "uploaddetail.address1" style="display: block;width: 100%;height: 34px;padding: 6px 12px;font-size: 14px;line-height: 1.42857143;color: #555;background-color: #fff;background-image: none;border: 1px solid #ccc;border-radius: 4px;box-shadow: inset 0 1px 1px rgba(0,0,0,.075);" type="text" name="">
												<label style="font-size: 13px;">Address-2 :</label>
												<input ng-model = "uploaddetail.address2" style="display: block;width: 100%;height: 34px;padding: 6px 12px;font-size: 14px;line-height: 1.42857143;color: #555;background-color: #fff;background-image: none;border: 1px solid #ccc;border-radius: 4px;box-shadow: inset 0 1px 1px rgba(0,0,0,.075);" type="text" name="">
												<div class="fo-row" style="margin-right: -15px;margin-left: -15px;">
													<div class="fo-col-md-6" style="margin-left: 15px;margin-right: 15px">
														<label style="font-size: 13px;">City :</label>
														<input ng-model = "uploaddetail.city" style="display: block;width: 100%;height: 34px;padding: 6px 12px;font-size: 14px;line-height: 1.42857143;color: #555;background-color: #fff;background-image: none;border: 1px solid #ccc;border-radius: 4px;box-shadow: inset 0 1px 1px rgba(0,0,0,.075);" type="text" name="">
													</div>
													<div class="fo-col-md-6" style="margin-left: 15px;margin-right: 15px">
														<label style="font-size: 13px;">State :</label>
														<input ng-model = "uploaddetail.state" style="display: block;width: 100%;height: 34px;padding: 6px 12px;font-size: 14px;line-height: 1.42857143;color: #555;background-color: #fff;background-image: none;border: 1px solid #ccc;border-radius: 4px;box-shadow: inset 0 1px 1px rgba(0,0,0,.075);" type="text" name="">
													</div>
												</div>
												<div class="fo-row" style="margin-right: -15px;margin-left: -15px; display: flex;">
													<div class="fo-col-md-6" style="margin-left: 15px;margin-right: 15px">
														<label style="font-size: 13px;">PIN :</label>
														<input ng-model = "uploaddetail.pin" style="display: block;width: 100%;height: 34px;padding: 6px 12px;font-size: 14px;line-height: 1.42857143;color: #555;background-color: #fff;background-image: none;border: 1px solid #ccc;border-radius: 4px;box-shadow: inset 0 1px 1px rgba(0,0,0,.075);" type="number" name="">
													</div>
												</div>
												<h4 style="font-size: 17px; font-weight: 700; margin: 10px 0;">Contact Details :</h4>
												<label style="font-size: 13px;"style="font-size: 13px;"style="font-size: 13px;">Mobile No/ Telephone No (Mandatory) :</label>
												<input ng-model = "uploaddetail.applicantmobileno" style="display: block;width: 100%;height: 34px;padding: 6px 12px;font-size: 14px;line-height: 1.42857143;color: #555;background-color: #fff;background-image: none;border: 1px solid #ccc;border-radius: 4px;box-shadow: inset 0 1px 1px rgba(0,0,0,.075);" type="text" name="">
												<label style="font-size: 13px;"style="font-size: 13px;"style="font-size: 13px;">E-Mail ID :</label>
												<input ng-model = "uploaddetail.applicantemail" style="display: block;width: 100%;height: 34px;padding: 6px 12px;font-size: 14px;line-height: 1.42857143;color: #555;background-color: #fff;background-image: none;border: 1px solid #ccc;border-radius: 4px;box-shadow: inset 0 1px 1px rgba(0,0,0,.075);" type="text" name="">
			
												<h4 style="font-size: 17px; font-weight: 700; margin: 10px 0;">Other Mandatory Details :</h4>
												<label style="font-size: 13px;"style="font-size: 13px;"style="font-size: 13px;">Mothers Maiden Name (Mandatory) :</label>
												<input ng-model = "uploaddetail.mothersname" style="display: block;width: 100%;height: 34px;padding: 6px 12px;font-size: 14px;line-height: 1.42857143;color: #555;background-color: #fff;background-image: none;border: 1px solid #ccc;border-radius: 4px;box-shadow: inset 0 1px 1px rgba(0,0,0,.075);" type="text" name="">
			
												<div class="fo-row" style="margin-right: -15px;margin-left: -15px;display: flex;">
													<div class="fo-col-md-4" style="margin-left: 15px;margin-right: 15px">
														<label style="font-size: 13px;"style="font-size: 13px;"style="font-size: 13px;">Date of Birth (Mandatory) :</label>
														<input ng-model = "uploaddetail.mothersdateofbirth" style="display: block;width: 100%;height: 34px;padding: 6px 12px;font-size: 14px;line-height: 1.42857143;color: #555;background-color: #fff;background-image: none;border: 1px solid #ccc;border-radius: 4px;box-shadow: inset 0 1px 1px rgba(0,0,0,.075);" type="Date" name="">
													</div>
												</div>
												<div class="fo-row" style="margin-right: -15px;margin-left: -15px;display: flex;">
													<div class="fo-col-md-3" style="margin-left: 15px;margin-right: 15px">
														<label style="font-size: 13px;"style="font-size: 13px;">Year of Marriage :</label>
														<input ng-model = "uploaddetail.yearofmarriage" style="display: block;width: 100%;height: 34px;padding: 6px 12px;font-size: 14px;line-height: 1.42857143;color: #555;background-color: #fff;background-image: none;border: 1px solid #ccc;border-radius: 4px;box-shadow: inset 0 1px 1px rgba(0,0,0,.075);" type="text" name="">
													</div>
												</div>
												<div class="fo-row" style="margin-right: -15px;margin-left: -15px;display: flex;">
													<div class="fo-col-md-3" style="margin-left: 15px;margin-right: 15px">
														<label style="font-size: 13px;"style="font-size: 13px;">Year of Passing SSC :</label>
														<input ng-model = "uploaddetail.yearofpassingssc" style="display: block;width: 100%;height: 34px;padding: 6px 12px;font-size: 14px;line-height: 1.42857143;color: #555;background-color: #fff;background-image: none;border: 1px solid #ccc;border-radius: 4px;box-shadow: inset 0 1px 1px rgba(0,0,0,.075);" type="text" name="">
													</div>
												</div>
												
												<div class="fo-row" style="margin-right: -15px;margin-left: -15px;display: flex;">
													<div class="fo-col-md-3" style="margin-left: 15px;margin-right: 15px">
														<label style="font-size: 13px;"style="font-size: 13px;">Core Banking Branch Reference Number :</label>
														<input ng-model = "uploaddetail.referencenumber" style="display: block;width: 100%;height: 34px;padding: 6px 12px;font-size: 14px;line-height: 1.42857143;color: #555;background-color: #fff;background-image: none;border: 1px solid #ccc;border-radius: 4px;box-shadow: inset 0 1px 1px rgba(0,0,0,.075);" type="text" name="">
													</div>
												</div>
												<div class="fo-row" style="margin-right: -15px;margin-left: -15px;display: flex;">
													<div class="fo-col-md-3" style="margin-left: 15px;margin-right: 15px">
														<label style="font-size: 13px;"style="font-size: 13px;">State Bank eZ-Pay Card Reference number (12 digits) :</label>
														<input ng-model = "uploaddetail.cardreferencenumber" style="display: block;width: 100%;height: 34px;padding: 6px 12px;font-size: 14px;line-height: 1.42857143;color: #555;background-color: #fff;background-image: none;border: 1px solid #ccc;border-radius: 4px;box-shadow: inset 0 1px 1px rgba(0,0,0,.075);" type="text" name="">
													</div>
												</div>
												<div class="fo-row" style="margin-right: -15px;margin-left: -15px;display: flex;">
													<div class="fo-col-md-3" style="margin-left: 15px;margin-right: 15px">
														<label style="font-size: 13px;"style="font-size: 13px;">State Bank eZ-Pay Card Number (16 digits) :</label>
														<input ng-model = "uploaddetail.cardnumber" style="display: block;width: 100%;height: 34px;padding: 6px 12px;font-size: 14px;line-height: 1.42857143;color: #555;background-color: #fff;background-image: none;border: 1px solid #ccc;border-radius: 4px;box-shadow: inset 0 1px 1px rgba(0,0,0,.075);" type="text" name="">
													</div>
												</div>
												<div class="fo-row" style="margin-right: -15px;margin-left: -15px;display: flex;">
													<div class="fo-col-md-3" style="margin-left: 15px;margin-right: 15px">
														<label style="font-size: 13px;"style="font-size: 13px;">Card Valid till :</label>
														<input ng-model = "uploaddetail.cardvalidtill" style="display: block;width: 100%;height: 34px;padding: 6px 12px;font-size: 14px;line-height: 1.42857143;color: #555;background-color: #fff;background-image: none;border: 1px solid #ccc;border-radius: 4px;box-shadow: inset 0 1px 1px rgba(0,0,0,.075);" type="text" name="">
													</div>
												</div>
												<div class="fo-row" style="margin-right: -15px;margin-left: -15px;display: flex;">
													<div class="fo-col-md-3" style="margin-left: 15px;margin-right: 15px">
														<label style="font-size: 13px;"style="font-size: 13px;">Name of Authorised official :</label>
														<input ng-model = "uploaddetail.officialname" style="display: block;width: 100%;height: 34px;padding: 6px 12px;font-size: 14px;line-height: 1.42857143;color: #555;background-color: #fff;background-image: none;border: 1px solid #ccc;border-radius: 4px;box-shadow: inset 0 1px 1px rgba(0,0,0,.075);" type="text" name="">
													</div>
												</div>
												
												<div class="fo-row" style="margin-right: -15px;margin-left: -15px;display: flex;">
													<div class="fo-col-md-3" style="margin-left: 15px;margin-right: 15px">
														<label style="font-size: 13px;"style="font-size: 13px;">Pan :</label>
														<input style="display: block;width: 100%;height: 34px;padding: 6px 12px;font-size: 14px;line-height: 1.42857143;color: #555;background-color: #fff;background-image: none;border: 1px solid #ccc;border-radius: 4px;box-shadow: inset 0 1px 1px rgba(0,0,0,.075);" type="file" file-model ="panfile">
													</div>
												</div>
												
												<div class="fo-row" style="margin-right: -15px;margin-left: -15px;display: flex;">
													<div class="fo-col-md-3" style="margin-left: 15px;margin-right: 15px">
														<label style="font-size: 13px;"style="font-size: 13px;">Photo :</label>
														<input style="display: block;width: 100%;height: 34px;padding: 6px 12px;font-size: 14px;line-height: 1.42857143;color: #555;background-color: #fff;background-image: none;border: 1px solid #ccc;border-radius: 4px;box-shadow: inset 0 1px 1px rgba(0,0,0,.075);" type="file" file-model ="photofile">
													</div>
												</div>
												
												
												<div class="fo-row" style="margin-right: -15px;margin-left: -15px;display: flex;">
													<div class="fo-col-md-3" style="margin-left: 15px;margin-right: 15px">
														<label style="font-size: 13px;"style="font-size: 13px;">Aadhar :</label>
														<input style="display: block;width: 100%;height: 34px;padding: 6px 12px;font-size: 14px;line-height: 1.42857143;color: #555;background-color: #fff;background-image: none;border: 1px solid #ccc;border-radius: 4px;box-shadow: inset 0 1px 1px rgba(0,0,0,.075);" type="file" file-model ="aadharfile">
													</div>
												</div>
												
												<div class="fo-row" style="margin-right: -15px;margin-left: -15px;display: flex;">
													<div class="fo-col-md-3" style="margin-left: 15px;margin-right: 15px">
														<label style="font-size: 13px;"style="font-size: 13px;">Voter Card :</label>
														<input style="display: block;width: 100%;height: 34px;padding: 6px 12px;font-size: 14px;line-height: 1.42857143;color: #555;background-color: #fff;background-image: none;border: 1px solid #ccc;border-radius: 4px;box-shadow: inset 0 1px 1px rgba(0,0,0,.075);" type="file" file-model ="voterfile">
													</div>
												</div>
												
												
												
												<button style="border: none;background-color: green;padding: 5px 10px; color: #fff; margin-top: 20px;" type="submit" ng-click = "applicationform(uploaddetail);">submit</button>
												
											</form>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			
			
		</div>
		
		<%} %>
		
		<!-- div903  -->
		<div id="div903" style="display: none;">
			<div class="dd" style="width: 100%; padding: 50px 0;display: inline-block;">
				<div class="fo-container" style="width: 1170px;padding-right: 15px;padding-left: 15px;margin-right: auto;margin-left: auto;">
					<div class="fo-row" style="margin-right: -15px;margin-left: -15px;">
						<div class="fo-col-md-10" style="width:83%; padding-right: 15px;padding-left: 15px;">
							<div class="sfdg" style="background-color: #ffffff;padding: 20px;
								box-shadow: 2px 3px 20px #c6c6c6; width: 700px;">
								<div class="fo-row" style="margin-right: -15px;margin-left: -15px;">
									<div class="fo-col-md-7" style="width: 58%;padding-right: 15px;padding-left: 15px;">
										
									</div>
									<div class="fo-col-md-5" style="width: 41%;padding-right: 15px;padding-left: 15px;">
										<img src="assets/images/sbi.jpg" style="height: 80px;">
									</div>
									<hr style="width:100%;border-bottom: 2px solid #c6c6c6">
								</div>
								<div class="fo_row" style="margin-right: -15px;margin-left: -15px;display: flex;">
									<div class="fo-col-md-2" style="padding-right: 15px;padding-left: 15px;">
										<div class="img" style="padding: 10px;border: 1px solid #d6d4d4;text-align: center;">
											Photograph <br> of the <br> Applicant
										</div>
									</div>
									<div class="fo-col-md-7" style="margin-right: 15px;margin-left: 15px;display: flex;width: 58%;">
										<h4 style="font-size: 20px;font-weight: 700;margin: auto;">Application for State Bank eZ-Pay Card</h4>
									</div>
									<div class="fo-col-md-3" style="margin-right: 15px;margin-left: 15px;width: 25%;">
										<form>
											<label>Branch :</label>
											<input style="display: block;width: 100%;height: 34px;padding: 6px 12px;font-size: 14px;line-height: 1.42857143;color: #555;background-color: #fff;background-image: none;border: 1px solid #ccc;border-radius: 4px;box-shadow: inset 0 1px 1px rgba(0,0,0,.075);" type="text" name="">
											<label>Date :</label>
											<input style="display: block;width: 100%;height: 34px;padding: 6px 12px;font-size: 14px;line-height: 1.42857143;color: #555;background-color: #fff;background-image: none;border: 1px solid #ccc;border-radius: 4px;box-shadow: inset 0 1px 1px rgba(0,0,0,.075);" type="text" name="" placeholder="DD/MM/YYYY">
										</form>
									</div>
								</div>
								<div class="fo-row" style="margin-right: -15px;margin-left: -15px;display: flex;">
									<div class="fo-col-md-12" style="margin-right:15px;margin-left:15px;">
										<h4 style="font-size: 17px; font-weight: 700; margin: 10px 0;">Name of the Applicant :</h4>
										<form>
											<label style="font-size: 13px;">First Name :</label>
											<input ng-model="appformdata.firstname" style="display: block;width: 100%;height: 34px;padding: 6px 12px;font-size: 14px;line-height: 1.42857143;color: #555;background-color: #fff;background-image: none;border: 1px solid #ccc;border-radius: 4px;box-shadow: inset 0 1px 1px rgba(0,0,0,.075);" type="text" readonly="readonly">
											<label style="font-size: 13px;">Middle Name :</label>
											<input ng-model="appformdata.middlename" readonly="readonly" style="display: block;width: 100%;height: 34px;padding: 6px 12px;font-size: 14px;line-height: 1.42857143;color: #555;background-color: #fff;background-image: none;border: 1px solid #ccc;border-radius: 4px;box-shadow: inset 0 1px 1px rgba(0,0,0,.075);" type="text" name="">
											<label  style="font-size: 13px;">Last Name :</label>
											<input ng-model="appformdata.lastname" readonly="readonly" style="display: block;width: 100%;height: 34px;padding: 6px 12px;font-size: 14px;line-height: 1.42857143;color: #555;background-color: #fff;background-image: none;border: 1px solid #ccc;border-radius: 4px;box-shadow: inset 0 1px 1px rgba(0,0,0,.075);" type="text" name="">
											<h4 style="font-size: 17px; font-weight: 700; margin: 10px 0;">Address of the Applicant :</h4>
											<label style="font-size: 13px;">Address-1 :</label>
											<input ng-model="appformdata.address1" readonly="readonly" style="display: block;width: 100%;height: 34px;padding: 6px 12px;font-size: 14px;line-height: 1.42857143;color: #555;background-color: #fff;background-image: none;border: 1px solid #ccc;border-radius: 4px;box-shadow: inset 0 1px 1px rgba(0,0,0,.075);" type="text" name="">
											<label style="font-size: 13px;">Address-1 :</label>
											<input ng-model="appformdata.address2" readonly="readonly" style="display: block;width: 100%;height: 34px;padding: 6px 12px;font-size: 14px;line-height: 1.42857143;color: #555;background-color: #fff;background-image: none;border: 1px solid #ccc;border-radius: 4px;box-shadow: inset 0 1px 1px rgba(0,0,0,.075);" type="text" name="">
											<div class="fo-row" style="margin-right: -15px;margin-left: -15px;">
												<div class="fo-col-md-6" style="margin-left: 15px;margin-right: 15px">
													<label style="font-size: 13px;">City :</label>
													<input ng-model="appformdata.city" readonly="readonly" style="display: block;width: 100%;height: 34px;padding: 6px 12px;font-size: 14px;line-height: 1.42857143;color: #555;background-color: #fff;background-image: none;border: 1px solid #ccc;border-radius: 4px;box-shadow: inset 0 1px 1px rgba(0,0,0,.075);" type="text" name="">
												</div>
												<div class="fo-col-md-6" style="margin-left: 15px;margin-right: 15px">
													<label style="font-size: 13px;">State :</label>
													<input ng-model="appformdata.state" readonly="readonly" style="display: block;width: 100%;height: 34px;padding: 6px 12px;font-size: 14px;line-height: 1.42857143;color: #555;background-color: #fff;background-image: none;border: 1px solid #ccc;border-radius: 4px;box-shadow: inset 0 1px 1px rgba(0,0,0,.075);" type="text" name="">
												</div>
											</div>
											<div class="fo-row" style="margin-right: -15px;margin-left: -15px; display: flex;">
												<div class="fo-col-md-6" style="margin-left: 15px;margin-right: 15px">
													<label style="font-size: 13px;">PIN :</label>
													<input ng-model="appformdata.pin" readonly="readonly" style="display: block;width: 100%;height: 34px;padding: 6px 12px;font-size: 14px;line-height: 1.42857143;color: #555;background-color: #fff;background-image: none;border: 1px solid #ccc;border-radius: 4px;box-shadow: inset 0 1px 1px rgba(0,0,0,.075);" type="text" name="">
												</div>
											</div>
											<h4 style="font-size: 17px; font-weight: 700; margin: 10px 0;">Contact Details :</h4>
											<label style="font-size: 13px;"style="font-size: 13px;"style="font-size: 13px;">Mobile No/ Telephone No (Mandatory) :</label>
											<input ng-model="appformdata.applicantmobileno" readonly="readonly" style="display: block;width: 100%;height: 34px;padding: 6px 12px;font-size: 14px;line-height: 1.42857143;color: #555;background-color: #fff;background-image: none;border: 1px solid #ccc;border-radius: 4px;box-shadow: inset 0 1px 1px rgba(0,0,0,.075);" type="text" name="">
											<label style="font-size: 13px;"style="font-size: 13px;"style="font-size: 13px;">E-Mail ID :</label>
											<input ng-model="appformdata.applicantemail" readonly="readonly" style="display: block;width: 100%;height: 34px;padding: 6px 12px;font-size: 14px;line-height: 1.42857143;color: #555;background-color: #fff;background-image: none;border: 1px solid #ccc;border-radius: 4px;box-shadow: inset 0 1px 1px rgba(0,0,0,.075);" type="text" name="">
		
											<h4 style="font-size: 17px; font-weight: 700; margin: 10px 0;">Other Mandatory Details :</h4>
											<label style="font-size: 13px;"style="font-size: 13px;"style="font-size: 13px;">Mothers Maiden Name (Mandatory) :</label>
											<input ng-model="appformdata.mothersname" readonly="readonly" style="display: block;width: 100%;height: 34px;padding: 6px 12px;font-size: 14px;line-height: 1.42857143;color: #555;background-color: #fff;background-image: none;border: 1px solid #ccc;border-radius: 4px;box-shadow: inset 0 1px 1px rgba(0,0,0,.075);" type="text" name="">
		
											<div class="fo-row" style="margin-right: -15px;margin-left: -15px;display: flex;">
												<div class="fo-col-md-4" style="margin-left: 15px;margin-right: 15px">
													<label style="font-size: 13px;"style="font-size: 13px;"style="font-size: 13px;">Date of Birth (Mandatory) :</label>
													<input ng-model="appformdata.mothersdateofbirth" readonly="readonly" style="display: block;width: 100%;height: 34px;padding: 6px 12px;font-size: 14px;line-height: 1.42857143;color: #555;background-color: #fff;background-image: none;border: 1px solid #ccc;border-radius: 4px;box-shadow: inset 0 1px 1px rgba(0,0,0,.075);" type="text" name="">
												</div>
											</div>
											<div class="fo-row" style="margin-right: -15px;margin-left: -15px;display: flex;">
												<div class="fo-col-md-3" style="margin-left: 15px;margin-right: 15px">
													<label style="font-size: 13px;"style="font-size: 13px;">Year of Marriage :</label>
													<input ng-model="appformdata.yearofmarriage" readonly="readonly" style="display: block;width: 100%;height: 34px;padding: 6px 12px;font-size: 14px;line-height: 1.42857143;color: #555;background-color: #fff;background-image: none;border: 1px solid #ccc;border-radius: 4px;box-shadow: inset 0 1px 1px rgba(0,0,0,.075);" type="text" name="">
												</div>
											</div>
											<div class="fo-row" style="margin-right: -15px;margin-left: -15px;display: flex;">
												<div class="fo-col-md-3" style="margin-left: 15px;margin-right: 15px">
													<label style="font-size: 13px;"style="font-size: 13px;">Year of Passing SSC :</label>
													<input ng-model="appformdata.yearofpassingssc" readonly="readonly" style="display: block;width: 100%;height: 34px;padding: 6px 12px;font-size: 14px;line-height: 1.42857143;color: #555;background-color: #fff;background-image: none;border: 1px solid #ccc;border-radius: 4px;box-shadow: inset 0 1px 1px rgba(0,0,0,.075);" type="text" name="">
												</div>
											</div>
											<div class="fo-row" style="margin-right: -15px;margin-left: -15px;display: flex;margin-top: 10px;">
												<div class="fo-col-md-12" style="margin-left: 15px;margin-right: 15px;width: 100%;">
													<table class="table table-responsive table-hover table-bordered">
														<tbody>
															<tr>
																<th>I would like to apply for eZ-Pay Card for (Minimum: Rs.100/- / Maximum: Rs. 50,000/-): </th>
																<th>RS .</th>
															</tr>
		
															<tr>
																<td>eZ-Pay Issuance Fees (for Bank's use) </td>
																<td>RS .</td>
															</tr>
															<tr>
																<td>Total</td>
																<td>RS .</td>
															</tr>
														</tbody>
													</table>
												</div>
											</div>
											<div class="fo-row" style="margin-right: -15px;margin-left: -15px;display: flex;">
												<div class="fo-col-md-12" style="margin-left: 15px;margin-right: 15px;width: 100%;">
													<h4 style="font-size: 17px; font-weight: 700; margin: 10px 0;">KYC Documents :</h4>
													<table class="table table-responsive table-hover table-bordered">
														<tbody>
															<tr>
																<th> Proof of Identity </th>
																<th>Proof of Residence</th>
															</tr>
		
															<tr>
																<td></td>
																<td></td>
															</tr>
														</tbody>
													</table>
												</div>
											</div>
											<div class="fo-row" style="margin-right: -15px;margin-left: -15px;display: flex;">
												<div class="fo-col-md-12" style="margin-left: 15px;margin-right: 15px;width: 100%;">
													<h4 style="font-size: 17px; font-weight: 700; margin: 10px 0;">Declaration :</h4>
													<p style="text-transform: capitalize;font-size: 11px;">
														I hereby apply for the issue of State Bank eZ Pay Card to me and declare that the information provided by me is true and correct and that I am a Resident Indian and
														that I am eligible to apply for this card. I accept that State Bank is entitled at its discretion to accept or reject this application without assigning any reason whatsoever. I
														have read the terms and conditions applicable to State Bank eZ Pay Card. I agree to be bound by these terms and conditions as may be in force from time to time. Upon
														any use at ATM, purchase via a point-of-sale device or online the amount available on the Card will be reduced by the amount of such withdrawal I purchase plus service
														charges, if any. I undertake to sign on the signature panel at the back of the card immediately on receipt. 
													</p>
												</div>
											</div>
											<div class="fo-row sing_bod mt-10" style="margin-right: -15px;margin-left: -15px;display: flex;">
												<div class="fo-col-md-6" style="margin-left: 15px;margin-right: 15px;width: 50%;">
													<h4 style="font-size: 17px; font-weight: 700; margin: 10px 0;">Date :</h4>
												</div>
												<div class="fo-col-md-6" style="margin-left: 15px;margin-right: 15px;width:50%">
													<h4 class="text text-right">(Signature of Applicant)</h4>
												</div>
												<hr>
											</div>
											<div class="fo-row br_bod" style="margin-right: -15px;display: inline-block;width: 100%;border: 1px solid #ccc;">
												<div class="fo-col-md-12" style="margin-left: 15px;margin-right: 15px;">
													<h4 style="font-size: 17px; font-weight: 700; margin: 10px 0;">For Branch Use :</h4>
													<label style="font-size: 13px;">Core Banking Branch Reference Number :):</label>
													<input ng-model="appformdata.referencenumber" readonly="readonly" style="display: block;width: 100%;height: 34px;padding: 6px 12px;font-size: 14px;line-height: 1.42857143;color: #555;background-color: #fff;background-image: none;border: 1px solid #ccc;border-radius: 4px;box-shadow: inset 0 1px 1px rgba(0,0,0,.075);" type="text" name="">
													<label style="font-size: 13px;">State Bank eZ-Pay Card Reference number (11 digits) :</label>
													<input ng-model="appformdata.cardreferencenumber" readonly="readonly" style="display: block;width: 100%;height: 34px;padding: 6px 12px;font-size: 14px;line-height: 1.42857143;color: #555;background-color: #fff;background-image: none;border: 1px solid #ccc;border-radius: 4px;box-shadow: inset 0 1px 1px rgba(0,0,0,.075);" type="text" name="">
													<label style="font-size: 13px;">State Bank eZ-Pay Card Number (16 digits) :</label>
													<input ng-model="appformdata.cardnumber" readonly="readonly" style="display: block;width: 100%;height: 34px;padding: 6px 12px;font-size: 14px;line-height: 1.42857143;color: #555;background-color: #fff;background-image: none;border: 1px solid #ccc;border-radius: 4px;box-shadow: inset 0 1px 1px rgba(0,0,0,.075);" type="text" name="">
												</div>
												<div class="fo-col-md-4" style="margin-left: 15px;margin-right: 15px">
													<label style="font-size: 13px;">Card Valid till :</label>
													<input ng-model="appformdata.cardvalidtill" readonly="readonly" class="form-control" type="text" name="" placeholder="MM/YYYY">
													<h4 style="font-size: 13px;">Issued above Card to the applicant :</h4>
													<label style="font-size: 13px;">Name of Authorised official :</label>
													<input ng-model="appformdata.officialname" readonly="readonly" style="display: block;width: 100%;height: 34px;padding: 6px 12px;font-size: 14px;line-height: 1.42857143;color: #555;background-color: #fff;background-image: none;border: 1px solid #ccc;border-radius: 4px;box-shadow: inset 0 1px 1px rgba(0,0,0,.075);" type="text" name="">
													
												</div>
												<div class="fo-row" style="margin-right: -15px;margin-left: -15px;display: inline;">
													<div class="fo-col-md-6" style="margin-left: 15px;margin-right: 15px">
														<label style="font-size: 13px;">Signature of Authorised official :</label>
														<hr>
													</div>
													<div class="fo-col-md-6" style="margin-left: 15px;margin-right: 15px">
														<label style="font-size: 13px;">Date :</label>
														<hr>
													</div>
												</div>
											</div>
										</form>
									</div>
								</div>
								
								<button class="btn btn-primary" type="submit" style="margin-top: 10px;" onclick="generatePDF()">print</button>
								
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- div903  -->
		
		<!-- div900  -->
		<div id="div900" style="display: none;">
			<div class="dd" style="width: 100%; padding: 50px 0;display: inline-block;">
				<div class="fo-container" style="width: 1170px;padding-right: 15px;padding-left: 15px;margin-right: auto;margin-left: auto;">
					<div class="fo-row" style="margin-right: -15px;margin-left: -15px;">
						<div class="fo-col-md-8" style="width:83%; padding-right: 15px;padding-left: 15px;">
							<div class="sfdg" style="background-color: #ffffff;padding: 20px;
								box-shadow: 2px 3px 20px #c6c6c6;">
								
								<div class="fo-row table-responsive" style="margin-right: -15px;margin-left: -15px;display: flex;">
									<div class="fo-col-md-8" style="margin-right:15px;margin-left:15px; width: 100%;">
										<form>
											<label style="font-size: 13px;">Start Date :</label>
											<input id="dp59" ng-model="app.start_date" style="display: block;width: 100%;height: 34px;padding: 6px 12px;font-size: 14px;line-height: 1.42857143;color: #555;background-color: #fff;background-image: none;border: 1px solid #ccc;border-radius: 4px;box-shadow: inset 0 1px 1px rgba(0,0,0,.075);" type="date" name="">
											<label style="font-size: 13px;">End Date :</label>
											<input id="dp60" ng-model="app.end_date" style="display: block;width: 100%;height: 34px;padding: 6px 12px;font-size: 14px;line-height: 1.42857143;color: #555;background-color: #fff;background-image: none;border: 1px solid #ccc;border-radius: 4px;box-shadow: inset 0 1px 1px rgba(0,0,0,.075);" type="date" name="">
											<div class="fo-row" style="margin-right: -15px;margin-left: -15px;display: flex;margin-top: 10px;">
												<div class="fo-col-md-12" ng-if="appsReports.length > 0">
													<table class="table table-hover table-bordered" style="margin-left: 15px;">
														<tbody>
															<tr>
															    <th>SL NO</th>
																<th>Firstname</th>
																<th>Middlename</th>
																<th>Lastname</th>
																<th>Address1</th>
																<th>Address2</th>
																<th>City</th>
																<th>State</th>
																<th>pin</th>
																<th>Mothersname</th>
																<th>Mothersdateofbirth</th>
																<th>Yearofmarriage</th>
																<th>Yearofpassingssc</th>
																<th>Applicationid</th>
																<th>Applicantmobileno</th>
																<th>Applicantemail</th>
																<th>UserId</th>
																<th>Referencenumber</th>
																<th>Cardreferencenumber</th>
																<th>Cardnumber</th>
																<th>Cardvalidtill</th>
																<th>Officialname</th>
																<th>Status</th>
																<th>Remarks</th>
																<th>Date</th>
																<th>Time</th>
																<th>Uname</th>
																<th>Umobile</th>
																<th>Download Aadhar</th>
																<th>Download Photo</th>
																<th>Download Pan</th>
																<th>Download Voter</th>
																<%if(user.getRoleId() == 1){ %>
																<th>Print Form
																</th>
																<th>Approve
																</th>
																<th>Reject
																</th>
																
																
																<%} %>
															</tr>
		
															<tr ng-repeat="drr in appsReports">
																<td >{{$index + 1}}</td>
																<td >{{drr.firstname}}</td>
																<td >{{drr.middlename}}</td>
																<td >{{drr.lastname}}</td>
																<td >{{drr.address1}}</td>
																<td >{{drr.address2}}</td>
																<td >{{drr.city}}</td>
																<td >{{drr.state}}</td>
																<td >{{drr.pin}}</td>
																<td >{{drr.mothersname}}</td>
																<td >{{drr.mothersdateofbirth}}</td>
																<td >{{drr.yearofmarriage}}</td>
																<td >{{drr.yearofpassingssc}}</td>
																<td>{{drr.applicationid}}</td>
																<td >{{drr.applicantmobileno}}</td>
																<td >{{drr.applicantemail}}</td>
																<td >{{drr.userId}}</td>
																<td >{{drr.referencenumber}}</td>
																<td >{{drr.cardreferencenumber}}</td>
																<td >{{drr.cardnumber}}</td>
																<td >{{drr.cardvalidtill}}</td>
																<td >{{drr.officialname}}</td>
																<td >{{drr.status}}</td>
																<td >{{drr.remarks}}</td>
																<td >{{drr.date}}</td>
																<td >{{drr.time}}</td>
																<td >{{drr.uname}}</td>
																<td >{{drr.umobile}}</td>
																<td><a target="blank" class="btn btn-success" href="https://encodigi.net.in/imagesencore/{{drr.aadharimagename}}.jpeg" download>
																<h4 style="font-size: 15px;margin: 5px 0px">Download Aadhar </h4></a></td>	
																<td><a target="blank" class="btn btn-success" href="https://encodigi.net.in/imagesencore/{{drr.photoimagename}}.jpeg" download>
																<h4 style="font-size: 15px;margin: 5px 0px">Download  Photo</h4></a></td>
																<td><a target="blank" class="btn btn-success" href="https://encodigi.net.in/imagesencore/{{drr.panimagename}}.jpeg" download>
																<h4 style="font-size: 15px;margin: 5px 0px">Download Pan </h4></a></td>
																<td><a target="blank" class="btn btn-success" href="https://encodigi.net.in/imagesencore/{{drr.panimagename}}.jpeg" download>
																<h4 style="font-size: 15px;margin: 5px 0px">Download Voter </h4></a></td>
																	<%if(user.getRoleId() == 1){ %>
																<td ><button ng-click="printForm(drr);" >submit</button></td>
																	
																<td >
																	<button class="btn-success" 
																		ng-if="drr.status==='PENDING'" data-toggle="modal" data-target="#exampleModal" ng-click="statuschangesbi(drr)">APPROVAL
																	</button>
															<!-- 		ng-click="statuschange(drr,'SUCCESS');"  -->
																</td>
																<td >
																	<button class="btn-danger" data-toggle="modal" data-target="#faile">REJECTED
																	</button>
																</td>
																<%} %>														
															</tr>
															
														</tbody>
													</table>
												</div>
												<!-- Modal -->
												<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
												  <div class="modal-dialog modal-sm" role="document">
												    <div class="modal-content modal-sm">
												      <div class="modal-header">
												        <h5 class="modal-title" id="exampleModalLabel">Remarks SUCCESS</h5>
												        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
												          <span aria-hidden="true">&times;</span>
												        </button>
												      </div>
												      <div class="modal-body">
												        <input type="text" ng-model="sbidata.remarks" class="form-control">
												      </div>
												      <div class="modal-footer">
												        <button type="button" class="btn btn-secondary" data-dismiss="modal" style="margin-bottom: 15px;">Close
												        </button>
												        <button type="button" class="btn btn-primary" 
												        	ng-click="statuschange(sbidata,'SUCCESS')" style="margin-bottom: 15px; margin-right: 10px;">SUCCESS
												        </button>
												      </div>
												    </div>
												  </div>
												</div>
												
												<!-- Modal -->
												<div class="modal fade" id="faile" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
												  <div class="modal-dialog modal-sm" role="document">
												    <div class="modal-content modal-sm">
												      <div class="modal-header">
												        <h5 class="modal-title" id="exampleModalLabel">Remarks failed</h5>
												        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
												          <span aria-hidden="true">&times;</span>
												        </button>
												      </div>
												      <div class="modal-body">
												        <input type="text" ng-model="sbidata.remarks" class="form-control">
												      </div>
												      <div class="modal-footer">
												        <button type="button" class="btn btn-secondary" data-dismiss="modal" style="margin-bottom: 15px;">Close
												        </button>
												        <button type="button" class="btn btn-primary" 
												        	ng-click="statuschange(sbidata,'FAILED');" 
												        	style="margin-bottom: 15px; margin-right: 10px;">FAILE
												        </button>
												      </div>
												    </div>
												  </div>
												</div>
												
												
											</div>
											<button ng-click="getAPPReport(app);" style="border: none;    background-color: #34e224;color: #fff;padding: 5px 10px;border-radius: 2px;margin-left: 15px; type="submit">submit</button>
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
		<!-- div904  -->
		
		<!-- div271  -->
		<div id="div271" style="display: none;">
			<div class="dd" style="width: 100%; padding: 50px 0;display: inline-block;">
				<div class="fo-container" style="width: 1170px;padding-right: 15px;padding-left: 15px;margin-right: auto;margin-left: auto;">
					<div class="fo-row" style="margin-right: -15px;margin-left: -15px;">
						<div class="fo-col-md-8" style="width:83%; padding-right: 15px;padding-left: 15px;">
							<div class="sfdg" style="background-color: #ffffff;padding: 20px;
								box-shadow: 2px 3px 20px #c6c6c6;">
								
								<div class="fo-row table-responsive" style="margin-right: -15px;margin-left: -15px;display: flex;">
									<div class="fo-col-md-8" style="margin-right:15px;margin-left:15px; width: 100%;">
										<h3>Card Wallet Report</h3>
										<form>
											<label style="font-size: 13px;">Start Date :</label>
											<input id="dp59" ng-model="wall.start_date" style="display: block;width: 100%;height: 34px;padding: 6px 12px;font-size: 14px;line-height: 1.42857143;color: #555;background-color: #fff;background-image: none;border: 1px solid #ccc;border-radius: 4px;box-shadow: inset 0 1px 1px rgba(0,0,0,.075);" type="date" name="">
											<label style="font-size: 13px;">End Date :</label>
											<input id="dp60" ng-model="wall.end_date" style="display: block;width: 100%;height: 34px;padding: 6px 12px;font-size: 14px;line-height: 1.42857143;color: #555;background-color: #fff;background-image: none;border: 1px solid #ccc;border-radius: 4px;box-shadow: inset 0 1px 1px rgba(0,0,0,.075);" type="date" name="">
											<button ng-click="getWALLETReport(wall);" style="border: none;background-color: #34e224;color: #fff;padding: 5px 10px;border-radius: 2px;margin-top: 15px; type="submit">submit</button>
											<div class="fo-row" style="margin-right: -15px;margin-left: -15px;display: flex;margin-top: 10px;">
												<div class="fo-col-md-12">
													<table class="table table-hover table-bordered" style="margin-left: 15px;">
														<tbody>
															<tr>
															    <th>SL NO</th>
																<th>Cardnumber</th>
																<th>Cardholdernme</th>
																<th>UserId</th>
																<%if(user.getRoleId()> 2){ %>
															 <th>Amount</th>
															 <%} %>
														
																<th>Charge</th>
															
																<th>Status</th>
															
																<th>Remarks</th>
																<th>Date</th>
																<th>Time</th>
																<th>USENAME</th>
																<th>USERMOBILE</th>
															
																<!-- <th>Yearofmarriage</th> -->
																
																<%if(user.getRoleId() == 1){ %>
																	<th>ACTUALWALLET</th>
																<th>Approve
																</th>
																<th>Reject
																</th>
																<th>
																	<button class="btn"  data-toggle="modal" data-target="#walat2" ng-click="printcardwallet(WALLETReports)">print
																	</button>
																</th>
																
																
																<%} %>
															</tr>
		
															<tr ng-repeat="drr in WALLETReports">
																<td >{{$index + 1}}</td>
																<td >{{drr.cardnumber}}</td>
																<td >{{drr.cardholdernme}}</td>
																<td >{{drr.userId}}</td>
																<%if(user.getRoleId()> 2){ %>
																 <td >{{drr.amount}}</td> 
																 <%} %>
															 <td >{{drr.charge}}</td> 
																<td >{{drr.status}}</td>
																<td >{{drr.remarks}}</td>
																<td >{{drr.date}}</td>
																<td >{{drr.time}}</td>
																<td >{{drr.uname}}</td>
																<td >{{drr.umobile}}</td>
															
																	<%if(user.getRoleId() == 1){ %>
															
																	<td >{{drr.actualwallet}}</td>
																<td >
																	<button class="btn-success" 
																		ng-if="drr.status==='PENDING'" data-toggle="modal" data-target="#walat" ng-click="statuschangecardwallet(drr)">Approval
																	</button>
															<!-- 		ng-click="statuschange(drr,'SUCCESS');"  -->
																</td>
																
																
																	<td >
																	<button class="btn-danger" ng-if="drr.status==='PENDING'" data-toggle="modal" data-target="#rejecte" ng-click="statuschangecardwallet(drr)">Reject
																	</button>
																</td>
																<%} %>														
															</tr>
															
														</tbody>
													</table>
												</div>
												
												<div class="modal fade" id="walat2" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
												  <div class="modal-dialog modal-sm" role="document">
												    <div class="modal-content modal-sm">
												      <div class="modal-header">
												        <h5 class="modal-title" id="exampleModalLabel">Download Text</h5>
												        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
												          <span aria-hidden="true">&times;</span>
												        </button>
												      </div>
												      <div class="modal-body">
												        <a download="content.txt" ng-href="{{sbiurl}}">download</a>
												      </div>
												      <div class="modal-footer">
												       
												      </div>
												    </div>
												  </div>
												</div>
												<!-- Modal -->
												<div class="modal fade" id="walat" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
												  <div class="modal-dialog modal-sm" role="document">
												    <div class="modal-content modal-sm">
												      <div class="modal-header">
												        <h5 class="modal-title" id="exampleModalLabel">Remarks Approval</h5>
												        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
												          <span aria-hidden="true">&times;</span>
												        </button>
												      </div>
												      <div class="modal-body">
												        <input type="text" ng-model="sbicardwallet.remarks" class="form-control">
												      </div>
												      <div class="modal-footer">
												        <button type="button" class="btn btn-secondary" data-dismiss="modal" style="margin-bottom: 15px;">Close
												        </button>
												        <button type="button" class="btn btn-primary" 
												        	ng-click="statuschangeforcard(sbicardwallet,'SUCCESS')" style="margin-bottom: 15px; margin-right: 10px;">SUCCESS
												        </button>
												      </div>
												    </div>
												  </div>
												</div>
												
												<!-- Modal -->
												<div class="modal fade" id="rejecte" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
												  <div class="modal-dialog modal-sm" role="document">
												    <div class="modal-content modal-sm">
												      <div class="modal-header">
												        <h5 class="modal-title" id="exampleModalLabel">Remarks reject</h5>
												        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
												          <span aria-hidden="true">&times;</span>
												        </button>
												      </div>
												      <div class="modal-body">
												        <input type="text" ng-model="sbicardwallet.remarks" class="form-control">
												      </div>
												      <div class="modal-footer">
												        <button type="button" class="btn btn-secondary" data-dismiss="modal" style="margin-bottom: 15px;">Close
												        </button>
												        <button type="button" class="btn btn-primary" 
												        	ng-click="statuschangeforcard(sbicardwallet,'FAILED');" 
												        	style="margin-bottom: 15px; margin-right: 10px;">FAILE
												        </button>
												      </div>
												    </div>
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
				</div>
			</div>
		</div>
		<!-- div904  -->
		
		
		<!-------------- Div270 ---------------------->
		<div id="div270" class="row clearfix" style="display: none">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<div class="card">
					<div class="header">
						<h2>Add wallet to card</h2>
					</div>
					<div class="body">
						<div class="body">
							<form class="form-horizontal">
								<div class="row clearfix">
									<div class="col-md-6 col-lg-6 col-sm-12 col-xs-12">
										<div
											class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
											<label for="email_address_2">Card number</label>
										</div>
										<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
											<div class="form-group">
												<div class="form-line1">
													<select class="form-control js-example-basic-single" ng-model="Walletadd.carddetail">
														<option ng-repeat="mycard in mycardlist" value="{{mycard.firstname}} {{mycard.middlename}} {{mycard.lastname}},{{mycard.cardnumber}},{{mycard.applicationid}}">({{mycard.firstname}} {{mycard.middlename}} {{mycard.lastname}}){{mycard.cardnumber}}</option>
														
													</select>
												</div>
											</div>
										</div>
									</div>
									<div class="col-md-6 col-lg-6 col-sm-12 col-xs-12">
										<div
											class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
											<label for="password_2">Amount</label>
										</div>
										<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
											<div class="form-group">
												<div class="form-line1">
													<input ng-model="Walletadd.amount" type="text" class="form-control"
														placeholder="Enter your Amount">
												</div>
											</div>
										</div>
									</div>
								</div>
							
								<div class="row clearfix">
									<div
										class="col-lg-offset-2 col-md-offset-2 col-sm-offset-4 col-xs-offset-5">
										<button type="button"
											class="btn btn-primary m-t-15 waves-effect" ng-click="cardwalletrequest(Walletadd)">submit</button>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-------------- /Div270 ---------------------->
		
		
		
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
	<script src="assets/js/html2pdf.bundle.js"></script>
	<script src="assets/js/stellarnav.min.js"></script>
	
	
	<script>
    function generatePDF() {
        // Choose the element that our invoice is rendered in.
        const element = document.getElementById("div903");
        // Choose the element and save the PDF for our user.
        html2pdf()
            .from(element)
            .save();
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
                $('.js-example-basic-single').select2();
        });
     </script>
	<script type="text/javascript">
		
			$(document).ready(function() {
			    
			})
	</script>

</body>

</html>