<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@page import="com.recharge.model.User"%>
<%@page import="org.apache.log4j.Logger"%>

<%
	User user = (User) session.getAttribute("UserDetails");
%>


<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
<title></title>
<!-- Tell the browser to be responsive to screen width -->
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
<!-- Bootstrap 3.3.7 -->
<link rel="stylesheet"
	href="assets/home/bower_components/bootstrap/dist/css/bootstrap.min.css">
<!-- Font Awesome -->
<link rel="stylesheet"
	href="assets/home/bower_components/font-awesome/css/font-awesome.min.css">
<!-- Ionicons -->
<link rel="stylesheet"
	href="assets/home/bower_components/Ionicons/css/ionicons.min.css">
<!-- jvectormap -->
<link rel="stylesheet"
	href="assets/home/bower_components/jvectormap/jquery-jvectormap.css">
<!-- Theme style -->
<link rel="stylesheet" href="assets/home/dist/css/AdminLTE.css">
<!-- AdminLTE Skins. Choose a skin from the css/skins
     folder instead of downloading all of them to reduce the load. -->
<link rel="stylesheet"
	href="assets/home/dist/css/skins/_all-skins.min.css">

<link rel="stylesheet" href="assets/home/dist/css/sweetalert2.css">


<link href="assets/css/bootstrap-datepicker.min.css" rel="stylesheet" />



<!-- Google Font -->
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">

<style type="text/css">
.btn-app {
	border-radius: 0px;
	position: relative;
	padding: 6px 0px;
	margin: 0 0 1px 0px;
	min-width: 36px;
	height: 31px;
	text-align: center;
	color: #ffffff;
	border: none;
	background-color: #ffffff;
	font-size: 10px;
}

.nav>li>a {
	position: relative;
	display: block;
	padding: 10px 5px;
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

.visiable-on-responsive {
	display: none !important;
}

@media ( max-width : 768px) {
	.visiable-on-responsive {
		display: block !important;
	}
	.skin-purple-light .content-header {
		margin-top: 10%;
	}
	.news-div {
		margin-top: 10%;
	}
}
</style>

</head>

<body class="hold-transition skin-purple-light fixed sidebar-mini">

	<div class="{{reseller.theme}}" ng-app="app"	ng-controller="appController">

		<!-- Page Loader -->
		<div class="page-loader-wrapper" ng-show="loader">
			<div class="loader">
				<div class="preloader">
					<img alt="" src="assets/images/sof.gif">
					<h3>please wait . . .</h3>
				</div>
			</div>
		</div>
		<alert-message alert="alertMessage"></alert-message>



		<div class="hold-transition skin-blue fixed sidebar-mini">
			<div class="wrapper">
				<alert-message alert="alertMessage"></alert-message>

				<header class="main-header">
					<!-- Logo -->
					<a href="home" class="logo"> <img id="logo" src="">

					</a>
					<nav class="navbar navbar-static-top">
						<!-- Sidebar toggle button-->
						<a href="#" class="sidebar-toggle" data-toggle="push-menu"
							role="button"> <span class="sr-only">Toggle navigation</span>
						</a>

						<div class="navbar-custom-menu">

							<ul class="nav navbar-nav">

								<% if (user.getRoleId() == 2) {	%>
								<li class="wallet-style">Main Wallet :
									{{userDetails.balance}}</li>
								<!-- <li class="wallet-style">Pan Wallet :
									{{userDetails.panbalance}}</li> -->
								<li class="wallet-style">Aeps Wallet : {{totalData.aepsb}}</li>
								<% } %>

								<li class="dropdown messages-menu"><a href="#"
									class="dropdown-toggle" data-toggle="dropdown"> <i
										class="fa fa-search"></i>
								</a>
									<ul class="dropdown-menu"
										style="background: #fff0; border: none; margin-top: 5px;">
										<li>
											<!-- search form -->
											<form action="#" method="get" class="sidebar-form">
												<div class="input-group">
													<input type="text" class="form-control"
														ng-model="searchUser.customerNo"
														placeholder="Search Cutomer Mobile No..."> <span
														class="input-group-btn">
														<button type="submit" name="search" id="search-btn"
															class="btn btn-flat" onClick="showDiv('div135')"
															ng-click="AdvancedCustomerNo(searchUser)">
															<i class="fa fa-search"></i>
														</button>
													</span>
												</div>
											</form>

										</li>
									</ul></li>
								<li class="dropdown notifications-menu"><a href="#"
									class="dropdown-toggle" data-toggle="dropdown"> <i
										class="fa fa-bell-o"></i> <%
 										if (user.getRoleId() == 1) {
										 %> <span class="label label-warning">{{notification.complain
											+ notification.balRequest + notification.utility +
											notification.insurance}}</span> <%
 										} else {
										 %> <span class="label label-warning">{{notification.complain
											+ notification.balRequest}}</span> <%
 											}
 										%>
								</a>
									<ul class="dropdown-menu">
										<li>
											<ul class="menu">
												<%
													if (user.getRoleId() == 1) {
												%>
												<li><a onClick="showDiv('div26')"
													ng-click="viewPendingComplain(viewComplain);"> <i
														class="fa fa-users text-aqua"></i>
														{{notification.complain}} Complains
												</a></li>
												<%
													}
												%>

												<li><a onClick="showDiv('div131')"
													ng-click="viewPendingBalanceRequest(viewBalanceRequest);">
														<i class="fa fa-users text-red"></i>
														{{notification.balRequest}} Balance Request
												</a></li>
												<%
													if (user.getRoleId() == 1) {
												%>
												<li><a onClick="showDiv('div137')"
													ng-click="viewPendingUtilityRequest(viewUtilityRequest);">
														<i class="fa fa-shopping-cart text-green"></i>{{notification.utility}}
														Utility Request
												</a></li>
												<li><a onClick="showDiv('div138')"
													ng-click="viewPendingInsuranceRequest(viewInsuranceRequest);">
														<i class="fa fa-user text-red"></i>
														{{notification.insurance}} Insurance Request
												</a></li>
												<%
													}
												%>
											</ul>
										</li>
									</ul></li>
								<!-- Tasks: style can be found in dropdown.less -->
								<li class="dropdown tasks-menu" style="display: none"><a
									href="#" class="dropdown-toggle" data-toggle="dropdown"> <i
										class="fa fa-flag-o"></i> <span class="label label-danger">9</span>
								</a>
									<ul class="dropdown-menu">
										<li class="header">You have 9 tasks</li>
										<li>
											<!-- inner menu: contains the actual data -->
											<ul class="menu">
												<li>
													<!-- Task item --> <a href="#">
														<h3>
															Design some buttons <small class="pull-right">20%</small>
														</h3>
														<div class="progress xs">
															<div class="progress-bar progress-bar-aqua"
																style="width: 20%" role="progressbar" aria-valuenow="20"
																aria-valuemin="0" aria-valuemax="100">
																<span class="sr-only">20% Complete</span>
															</div>
														</div>
												</a>
												</li>
												<!-- end task item -->
												<li>
													<!-- Task item --> <a href="#">
														<h3>
															Create a nice theme <small class="pull-right">40%</small>
														</h3>
														<div class="progress xs">
															<div class="progress-bar progress-bar-green"
																style="width: 40%" role="progressbar" aria-valuenow="20"
																aria-valuemin="0" aria-valuemax="100">
																<span class="sr-only">40% Complete</span>
															</div>
														</div>
												</a>
												</li>
												<!-- end task item -->
												<li>
													<!-- Task item --> <a href="#">
														<h3>
															Some task I need to do <small class="pull-right">60%</small>
														</h3>
														<div class="progress xs">
															<div class="progress-bar progress-bar-red"
																style="width: 60%" role="progressbar" aria-valuenow="20"
																aria-valuemin="0" aria-valuemax="100">
																<span class="sr-only">60% Complete</span>
															</div>
														</div>
												</a>
												</li>
												<!-- end task item -->
												<li><a href="#">
														<h3>
															Make beautiful transitions <small class="pull-right">80%</small>
														</h3>
														<div class="progress xs">
															<div class="progress-bar progress-bar-yellow"
																style="width: 80%" role="progressbar" aria-valuenow="20"
																aria-valuemin="0" aria-valuemax="100">
																<span class="sr-only">80% Complete</span>
															</div>
														</div>
												</a></li>
											</ul>
										</li>
										<li class="footer"><a href="#">View all tasks</a></li>
									</ul></li>


								<!-- User Account: style can be found in dropdown.less -->
								<li class="dropdown user user-menu"><a href="#"
									class="dropdown-toggle" data-toggle="dropdown"> <span
										class="">{{userDetails.name}}</span>
								</a>


									<ul class="dropdown-menu">
										<!-- User image -->
										<li class="user-header">

											<p>
												{{userDetails.name}} <small>({{userDetails.firmName}})</small>
											</p>

											<p>{{userDetails.email}}</p>

										</li>
										<!-- Menu Body -->

										<!-- Menu Footer-->
										<li class="user-footer">
											<div class="pull-left">
												<a href="#" onClick="showDiv('div1')"
													class="btn btn-default btn-flat">Profile</a>
											</div>
											<div class="pull-right">
												<a href="logout" class="btn btn-default btn-flat">Sign
													out</a>
											</div>
										</li>
									</ul></li>

							</ul>
						</div>
						<div class="news-row">
							<div class="news-div">
								<div class="col-md-12" style="background-color: white;">
									<marquee>
										<%
										if (user.getRoleId() == 1) {
									%>
										<a href="#" style="color: #0f0798">{{News.news}}</a>
										<%
										} else {
									%><a href="#" style="color: #0f0798">{{NewsReseller}}</a>
										<%
										}
									%>
									</marquee>
								</div>
							</div>




						</div>
					</nav>
				</header>

				<!-- Left side column. contains the logo and sidebar -->
				<aside class="main-sidebar">
				<!-- sidebar: style can be found in sidebar.less -->
				<section class="sidebar">
					<!-- Sidebar user panel -->
					<div class="user-panel">
						<div class="pull-left image">
							<img id="logo" src="">
						</div>

					</div>
					<!-- search form -->
					<form action="#" method="get" class="sidebar-form">
						<div class="input-group">
							<input type="text" class="form-control"
								ng-model="searchUser.mobile" placeholder="Search..."> <span
								class="input-group-btn">
								<button type="submit" name="search" id="search-btn"
									class="btn btn-flat" onClick="showDiv('div13')"
									ng-click="AdvancedSearchUser(searchUser)">
									<i class="fa fa-search"></i>
								</button>
							</span>
						</div>
					</form>
					<!-- /.search form -->
					<!-- sidebar menu: : style can be found in sidebar.less -->
					<ul class="sidebar-menu" data-widget="tree">
						<li class="header" style="display: none;">MAIN NAVIGATION</li>

						<li><a href="home"> <i
								class="fa fa-home"></i> <span>Home</span>
						</a></li>
						
						<li class="treeview"><a href="#"> <i class="fa fa-folder"></i>
								<span>Reports</span> <span class="pull-right-container">
									<i class="fa fa-angle-left pull-right"></i>
							</span>
						</a>

							<ul class="treeview-menu">
								<li><a href="#" onClick="showDiv('div135')"><i
										class="fa fa-circle-o"></i> Recharge Reports</a></li>

								<li><a href="#" onClick="showDiv('div100')"><i
										class="fa fa-circle-o"></i> Earning Reports </a></li>

								<li><a href="#" onClick="showDiv('div101')"><i
										class="fa fa-circle-o"></i> SETTLEMENT Report</a></li>
								<li><a href="#" onClick="showDiv('div311')"><i
									class="fa fa-circle-o"></i> AEPS LOG Reports </a></li>


								<li><a href="#" onClick="showDiv('div102')"><i
										class="fa fa-circle-o"></i>AEPS REPORT</a></li>
								<li><a href="#" onClick="showDiv('div312')"><i
									class="fa fa-circle-o"></i> AEPS NEW Reports </a></li>

								<li><a href="#" onClick="showDiv('div103')"><i
										class="fa fa-circle-o"></i>IMPS REPORT</a></li>
										<li><a href="#" onClick="showDiv('div104')"><i
										class="fa fa-circle-o"></i>Enquiry REPORT</a></li>

								<li><a href="#" onClick="showDiv('div37')"><i
										class="fa fa-circle-o"></i> Pan Reports </a></li>
								<li><a href="#" onClick="showDiv('div51')"><i
										class="fa fa-circle-o"></i> Other Reports</a></li>
								<li><a href="#" onClick="showDiv('div105')"><i
									class="fa fa-circle-o"></i> P2AMONEY Transfer Reports</a></li>

							</ul></li>

					</ul>
				</section>
				<!-- /.sidebar -->
			</aside>




				<div class="content-wrapper">
					<!-- Content Header (Page header) -->
					<section class="content-header">
						<h1>Dashboard</h1>
						<ol class="breadcrumb">
							<li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
							<li class="active">Dashboard</li>
						</ol>
					</section>

					<!-- Main content -->
					
					
					<!--div30-->
					<div id="div30">
						<section class="content">
							<div class="row">
								<!-- left column -->
								<div class=" col-md-12">
									<!-- general form elements -->
									<div class="box box-primary">
										<div class="box-header with-border">
											<h3 class="box-title"> Bank Details</h3>
										</div>
										<!-- /.box-header -->
										<!-- form start -->
										<form role="form">
											<div class="box-body">
												<div class="row">
													<div class="form-group">
													<div class="row">
													<div class="form-group">
														<div class="row">
															<div class="col-md-6">
																<label>Select Device Type</label>
																<select class="form-control" ng-model="transactionReq.deviceType">
																<option value="0">Select Device Type</option>
												<option value="MORPHO_PROTOBUF">Morpho</option>
												<option value="MANTRA_PROTOBUF">Mantra</option>
												<option value="STARTEK_PROTOBUF">Startek</option>
																</select>
															</div>

															<div class="col-md-6">
																<label>Select Your Bank</label> 
																<select class="form-control" ng-model="transactionReq.iin" ng-change="selecttransaction(transactionReq.mode)">
																<option value="0">Select Action</option>
												<option value="{{aeps.iin}},{{aeps.bank_name}}" ng-repeat="aeps in yesbankdetail.AEPSBankList">{{aeps.bank_name}}</option>
												
																</select>
															</div>
														</div>
													</div>
														<div class="row">
															<div class="col-md-6">
																<label>Enter Mobile</label> <input type="text"
																	class="form-control" ng-model="transactionReq.mobile">
															</div>


															<div class="col-md-6">
																<label>Enter Aadhar</label> <input type="text"
																	ng-model="transactionReq.aadhar" class="form-control">
															</div>
														</div>
													</div>

                                                <div class="form-group" id="capture" style="display: none;">
													<div class="row">
													<div class="col-md-6">
													<h5 style='color:red;'>Fingure Captured successfully.</h5>
													</div>
													</div>
													</div>
													
														
													<div class="form-group" id="errInfo" style="display: none;">
													<div class="row">
													<div class="col-md-6">
													<h5 style='color:red;'>Error Occurs</h5>
													</div>
													</div>
													</div>
													
													<div class="form-group" id="errnewInfo" style="display: none;">
													<div class="row">
													<div class="col-md-6">
													<h5 style='color:red;'>ERR_CONNECTION_REFUSED</h5>
													</div>
													</div>
													</div>
													
												</div>
											</div>
											<!-- /.box-body -->

											<div class="box-footer">
												<div class="row">
													<div class="col-md-3"></div>
													<div class="col-md-6 text-center">
														<button type="submit" class="btn btn-primary" ng-click = "callDevice(transactionReq.deviceType)"><i class="fas fa-fingerprint" style="display: block;font-size: 50px;margin: auto;" ></i>Finger Capture</button>
													</div>
													<div class="col-md-3"></div>
												</div>
												<div class="clear" style="clear: both;"></div>
												<div class="row" style="margin-top: 20px;">
													<div class="col-md-3"></div>
													<div class="col-md-6 text-center">
														<button type="submit" class="btn btn-primary"
														 ng-click = "iciciMinitransaction(transactionReq)">Submit</button>
													</div>
													<div class="col-md-3"></div>
												</div>
											</div>
										</form>
									</div>
								</div>
							</div>
						</section>
					</div>



	<div id="div103" style="display: none;">
		<div class="content">
			<div class="row">
				<div class=" col-md-12">
					<!-- general form elements -->
					<div class="box box-primary">
						<div class="box-header with-border">
							<h3 class="box-title">Mini Statement Report</h3>
						</div>
						<!-- /.box-header -->
						<!-- form start -->
						<div class="box-body">
						
						
							<div class="row">
								<div class="col-md-6">
									<img alt="#" src="assets/images/logo.png" style="width: 200px;">
									
								</div>
								<div class="col-md-6">
									<img alt="" src="assets/images/icici_bank.png" style="width: 200px;">
								</div>
							</div>
							<div class="row" style="clear: both;">
								<div class="col-md-6">
									<h2>Agent ID</h2>
									<p>{{AgentId}}</p>
								</div>
								<div class="col-md-6">
									<h2>Agent Name</h2>
									<p><%=user.getName() %></p>
								</div>
							</div>
							<div class="row">
								<div class="col-md-6">
									<h2>Bank Name</h2>
									<p>{{MinistatementResponse.bank}}</p>
								</div>
								<div class="col-md-6">
									<h2>Transaction Status</h2>
									<p>{{MinistatementResponse.transactionStatus}}</p>
								</div>
							</div>
							<div class="row">
								<div class="col-md-12">
									<h2>AVAILABLE BALANCE (INR)</h2>
									<h4>Rs. {{MinistatementResponse.balanceAmount}}</h4>
								</div>
							</div>
							
							<div class="row">
								<div class="col-md-4">
									<h2>Transaction ID</h2>
									<h4>{{MinistatementResponse.TransactionId}}</h4>
								</div>
								<div class="col-md-4">
									<h2>Aadhaar Number/VID</h2>
									<h4>{{MinistatementResponse.aadhar}}</h4>
								</div>
								<div class="col-md-4">
									<h2>BankRRn</h2>
									<h4>{{MinistatementResponse.bankRRN}}</h4>
								</div>
								<div class="col-md-4">
									<h2>Date & Time</h2>
									<h4>{{MinistatementResponse.TransactionTime}}</h4>
								</div>
							</div>
							
							<div class="row">
								<table class="table table-striped">
									  <thead>
									    <tr>
									    <th>Sl.&nbsp;No</th>
												<th scope="col">Amount</th>
												<th scope="col">Date</th>
												<th scope="col">Narration</th>
												<th scope="col">TXN Type</th>
									     
									    </tr>
									  </thead>
									  <tbody>
									   <tr ng-repeat="mini in   MinistatementResponse.Ministatement">
												<td>{{$index + 1}}</td>											
											
												<td>{{mini.amount}}</td>
												<td>{{mini.date}}</td>
												<td>{{mini.narration}}</td>
												<td>{{mini.txnType}}</td>
												
												
											</tr>
									  </tbody>
									</table>
							</div>
							
						
							<!-- <div class="row">
								<div class="col-md-12 col-lg-12 col-xs-12 table-responsive">
									<table class="table table-hover table-bordered">
										<tbody>
											<tr>
												<th>Available balance</th>
												<td>{{MinistatementResponse.balanceAmount}}</td>	
											</tr>


											<tr>
												<th>BankRRn</th>
												<td>{{MinistatementResponse.bankRRN}}</td>	
											</tr>
											
											
											
											<tr>
												<th>Status</th>
												<td>{{MinistatementResponse.transactionStatus}}</td>	
											</tr>
											
											<tr>
												<th>Aadhar </th>
												<td>{{MinistatementResponse.aadhar}}</td>	
											</tr>
											
											<tr>
												<th>Bank </th>
												<td>{{MinistatementResponse.bank}}</td>	
											</tr>
											
											
											
										   <tr>
												<th>AgentId </th>
												<td>{{AgentId}}</td>	
											</tr>
											
											
											<tr>
												<th>TransactionId </th>
												<td>{{MinistatementResponse.TransactionId}}</td>	
											</tr>
											
											
											<tr>
												<th>TransactionTime </th>
												<td>{{MinistatementResponse.TransactionTime}}</td>	
											</tr>
											
											
											
										</tbody>
									</table>
								</div>
							</div> -->
						</div>
<br>
<br>

						<!-- <div class="box-body  no-padding">
						
							<div class="row">
								<div class="col-md-12 col-lg-12 col-xs-12 table-responsive">
									<table class="table table-hover table-bordered">
										<tbody>
											<tr>
												<th>Sl.&nbsp;No</th>
												<th>Amount</th>
												<th>Date</th>
												<th>Narration</th>
												<th>TXN Typet</th>
												
											</tr>

											<tr ng-repeat="mini in   MinistatementResponse.Ministatement">
												<td>{{$index + 1}}</td>											
											
												<td>{{mini.amount}}</td>
												<td>{{mini.date}}</td>
												<td>{{mini.narration}}</td>
												<td>{{mini.txnType}}</td>
												
												
											</tr>
											
										</tbody>
									</table>
								</div>
							</div>
						</div> -->
						
						<button class="btn btn-primary">print</button>



					</div>
					<!-- /.box -->
				</div>
			</div>
		</div>

	</div>


				</div>


				

	


	<!--div311-->


	</div>
	</div>
	<!-- /.content-wrapper -->

	<footer class="main-footer"> </footer>
	</div>




	<!-- jQuery 3 -->
	<script src="assets/home/bower_components/jquery/dist/jquery.min.js"></script>

	<script src="assets/js/angular.min.js"></script>
	<script src="assets/js/angular-sanitize.min.js"></script>
	<script src="assets/js/cookieStore.js"></script>
	<script src="assets/js/alasql.min.js?version=1.0.0.2"></script>
	<script src="assets/js/xlsx.core.min.js?version=1.0.0.2"></script>
	<script src="assets/js/dir-pagination.js?version=1.0.5"></script>
	<script src="assets/scripts/angular_yesbank.min.js?version=1.0.5"></script>
	<script src="assets/js/datepicker-date.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/3.1.3/js/bootstrap-datetimepicker.min.js"></script>

	<script src="assets/js/bootstrap-datepicker.min.js"></script>

	<!-- Bootstrap 3.3.7 -->
	<script
		src="assets/home/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
	<!-- FastClick -->
	<script src="assets/home/bower_components/fastclick/lib/fastclick.js"></script>
	<!-- AdminLTE App -->
	<script src="assets/home/dist/js/adminlte.min.js"></script>
	<!-- Sparkline -->
	<script
		src="assets/home/bower_components/jquery-sparkline/dist/jquery.sparkline.min.js"></script>
	<!-- jvectormap  -->
	<script
		src="assets/home/plugins/jvectormap/jquery-jvectormap-1.2.2.min.js"></script>
	<script
		src="assets/home/plugins/jvectormap/jquery-jvectormap-world-mill-en.js"></script>
	<!-- SlimScroll -->
	<script
		src="assets/home/bower_components/jquery-slimscroll/jquery.slimscroll.min.js"></script>
	<!-- ChartJS -->
	<script src="assets/home/bower_components/chart.js/Chart.js"></script>
	<!-- AdminLTE dashboard demo (This is only for demo purposes) -->
	<script src="assets/home/dist/js/pages/dashboard2.js"></script>
	<!-- AdminLTE for demo purposes -->
	<script src="assets/home/dist/js/demo.js"></script>
	<script src="assets/home/dist/js/sweetalert2.js"></script>

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


</body>

</html>