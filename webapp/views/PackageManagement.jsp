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
<title>PackageManagement</title>
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

</head>
<body class="{{reseller.theme}}" ng-app = "app" ng-controller = "appController">
	<!-- Page Loader -->
	<div class="page-loader-wrapper" ng-show="loader" >
		<div class="loader">
			<div class="preloader"><img alt="" src="assets/images/l3.gif"></div>
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
		<div class="stellarnav">
			<ul>
			  	<li><a href="home"> <i class="material-icons">home</i><span>Home</span></a></li>
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

	<section class="content enco_content">
		<div class="container-fluid" style="margin-top: 5%;">


		<!-- Widgets -->
		<!----------------- Div0 ----------->
		<div class="row clearfix" id="div0">
			<div class="block-header">
				<h2 class="dash" style="color: #000 !important;">DASHBOARD
					<span class="pull-right " style="margin-right: 2%;color: #000;"><strong><%if(user.getRoleId() == 2){ %>Total Earning : {{totalData.totalUser}} ||<%} %> Total User : {{totalData.totalUser}}</strong> </span>
					
				</h2>
				
			</div>
               		
		</div>
		<!-------------- /Div5 ---------------------->
		<!-------------- Div6 ---------------------->
		<div id="div6" class="row clearfix" style="display: none; margin: 0px;">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<div class="card">
					<div class="header">
						<h2>Add Package<span class="pull-right"><a href="#" onClick="showDiv('div7')"  class="body"><i class="material-icons">arrow_back</i></a></span></h2>
					</div>
					<div class="body">
						<div class="body">
							<form class="form-horizontal">
								<div class="row clearfix">
									<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label text-right">
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
									<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label text-right">
										<label for="email_address_2">Package Name</label>
									</div>
									<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
										<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 m-l-20" style="margin-left: 4%;">
											<div class="form-group">
												<div class="form-group form-float">
													<div class="form-line">
														<input type="text" ng-model="package.name" class="form-control" placeholder="Enter Package Name" />
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="row clearfix">
									<div class="col-lg-offset-6 col-md-offset-6 col-sm-offset-6 col-xs-offset-6">
										<button type="button" class="btn btn-primary m-t-15 waves-effect" ng-click = "packageCreate(package);">Submit</button>
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
		<div id="div7" class="row clearfix" style="display: block; margin: 0px;">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<div class="card">
					<div class="header">
						<h2>Package Management</h2>
					</div>
					<div class="body">
						<div class="row clearfix">
							<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 form-control-label">
							    <div class="body1">
								    <div class="row">
										<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
												<button class="btn btn-info m-b-5 col-md-2" onclick="showDiv('div6');"> 
													<i class="fa fa-plus" style="color: #fff !important;"></i>&nbsp; <span>Add Package</span> 
												</button>
												<button class="btn btn-info m-b-5 m-l-30 col-md-2" onclick="showDiv('div9');"> 
													<i class="fa fa-file-text " style="color: #fff !important;"></i>&nbsp; <span>Assign Package</span> 
												</button>
												<button class="btn btn-info m-b-5 m-l-30 col-md-2" ng-click="viewMyPackage()" > 
													<i class="fa fa-file-text " style="color: #fff !important;"></i>&nbsp; <span>View My Package</span> 
												</button>
											<!-- 	<button class="btn btn-info m-b-5 m-l-30 col-md-2" onclick="showDiv('div5');" > 
													<i class="fa fa-file-text " style="color: #fff !important;"></i>&nbsp; <span>Change Assign Package</span> 
												</button> -->
												<%if(user.getRoleId()==2) {%>
												<button class="btn btn-info m-b-5 m-l-30 col-md-2" ng-click="myassignedPackage()" > 
													<i class="fa fa-file-text " style="color: #fff !important;"></i>&nbsp; <span>Assign My Package</span> 
												</button>
												<%} %>
												<button class="btn btn-info m-b-5 m-l-30 col-md-3" ng-click="viewAssignPackage()" > 
													<i class="fa fa-file-text " style="color: #fff !important;"></i>&nbsp; <span>View Assigned Package</span> 
												</button>
										</div>
									</div>
								
							    <!-- ------------------- My Package ----------------- -->
									<div class="row" >
										<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 visibility" id="sdiv50">
											<table id="datatable1" class="table table-striped table-bordered">
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
                                                           <td class="text-center" ng-if="myPack.service_type==='Encore AEPS'">AEPS</td>
                                                            <td class="text-center" ng-if="myPack.service_type!='Encore AEPS'">{{myPack.service_type}}</td>
                                                          <td class="text-center">{{myPack.package_name}}</td>
                                                         
                                                         
                                                          <td class="text-center">
                                                          	<button class="btn btn-success"  ng-click="viewMyPackagechargeDetail(myPack)" style="background: #228B22 !important;">View</button>
                                                          </td>
                                                           <td class="text-center">
                                                          	<button class="btn btn-success"  ng-click="DeleteMyPackageDetail(myPack)" style="background: #228B22 !important;">Delete</button>
                                                          </td>
                                                      </tr>
                                                  </tbody>
                                              </table>
										</div>
										<!-- ------------------- Assign Package ----------------- -->
										<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 visibility" id="sdiv51">
											<table id="datatable1" class="table table-striped table-bordered">
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
                                                          	<button class="btn btn-success" ng-click="viewAssignedPackage(Assignedpack)"  style="background: #228B22 !important;">Update</button>
                                                          </td>
                                                      </tr>
                                                  </tbody>
                                              </table>
										</div>
									</div>
									<!-- ---------------------------------------------------------------------------- -->
									<!-- ------------------- View Package Details ----------------- -->
										<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 visibility" id="sdiv52">
											<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
												<div class="card">
													<div class="header" style="position: absolute;">
														<h2>View Assigned Package Details</h2>
													</div>
													<div class="body">
														<div class="row clearfix m-t-30">
															<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 form-control-label">
															    <div class="body1">
																	<div class="row">
																		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 overFlow">
																			<table id="datatable1" class="table table-striped table-bordered">
								                                                 <thead>
								                                                     <tr>
								                                                         <th class="success text-center">Name</th>
								                                                         <th class="success text-center">UserId</th>
								                                                         <th class="success text-center">RoleId</th>
								                                                         <th class="success text-center">Package</th>
								                                                         <th class="success text-center">Edit </th>
								                                               		</tr>
								                                                 </thead>
								                                                 <tbody>
								                                                     <tr ng-repeat="packvw in ViewPackageDetail">
								                                                         <td class="text-center">{{packvw.user_name}}<br>({{packvw.mobile}})</td>
								                                                         <td class="text-center">{{packvw.user_id}}</td>
								                                                         <td class="text-center">
								                                                            <span ng-if = "packvw.role_id == 2">White Label</span>
																							<span ng-if = "packvw.role_id == 3">SUPER DISTRIBUTOR</span>
																							<span ng-if = "packvw.role_id == 4">DISTRIBUTOR</span>
																							<span ng-if = "packvw.role_id == 5">RETAILER</span>
																							<span ng-if = "packvw.role_id == 100">API USER</span>
																						({{packvw.role_id}})
								                                                         </td>
								                                                         <td class="text-center">{{packvw.package_name}}<br>({{packvw.package_id}})</td>
								                                                      	<td class="text-center"><button type="button" data-toggle="modal" data-target="#modalEditPackage" class="btn btn-primary m-t-15 waves-effect" ng-click="editpk(packvw);" >EDIT</button></td>  
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
										<!-- ----------------------------------- Sdiv End----------------------------------------- -->
										
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
		<div id="modalEditPackage" class="modal fade" role="dialog" tabindex="-1" aria-labelledby="modalLabel" aria-hidden="true">
		  <div class="modal-dialog">
		
		     <!-- Modal content-->
		    <div class="modal-content" style="width: 43%;left: 18%;">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal">&times;</button>
		        <h4 class="modal-title">Edit Assign Package</h4>
		      </div>
		      <div class="modal-body">
		      	<div class="row clearfix">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">							
						<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label ">
							<label for="comment">User Name: </label>
						</div>
						<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
							<input type="text" ng-model="edpkg.user_name" class="form-control" placeholder="Enter Remarks">
						</div>
					</div>
					<div class="col-md-12">
						<input type="hidden" ng-model="edpkg.role_id" class="form-control" placeholder="Enter ptid">
						<input type="hidden" ng-model="edpkg.user_id" class="form-control" placeholder="Enter tid">
					</div>
				</div>
				<div class="row clearfix m-t-20">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 " >							
						<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label ">
							<label for="email_address_2">Select Package</label>
						</div>
						<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
							<select class="form-control " ng-model="edpkg.package" >
								<option value="">-- Please select Package Type--</option>
								<option ng-repeat="package in packageDetails" value="{{package.service_type}},{{package.package_id}}">{{package.package_name}}</option>
							</select>
						</div>
					</div>
				</div>
				<div class="row clearfix">
				<div class="" style="margin-top: 10%">
				<center>	
				  <input type="submit" class="btn btn-info" data-dismiss="modal" ng-click = "assignEditPackage(edpkg);">
				 </center>
				</div>
		      </div>
		     </div>
		      <div class="modal-footer">
		        
		      </div>
		    </div>
		
		  </div>
		</div>
	
	<!-- ------------------------------------- /modalEditPackage -------------------------------------- -->
		<!-------------- Div8 ---------------------->
		<div id="div8" class="row clearfix" style="display: none; margin: 0px;">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<div class="card">
					<div class="header">
						<h2>IMPS Charge Setting</h2>
					</div>
					<div class="body">
						<form class="form-horizontal">
							<div class="row clearfix">
								<div
									class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
									<label for="email_address_2">Select Super Franchaise </label>
								</div>
								<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
									<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
										<select class="form-control" ng-model="imps.userType"  ng-change="getUserByUserType(imps.userType, 'getUserForDMRDiv', 'imps')">
											<option value="">-- Please select User Type--</option>
											<option ng-repeat="userType in userType" ng-if="userType.type == 3 || userType.type == 2" value="{{userType.type}}">{{userType.role}}</option>
											
										</select>
									</div>
								</div>
							</div>
							<div class="row clearfix m-t-20"  >
							<div class="col-lg-9 col-md-9 col-sm-8 col-xs-8 ">
							<div ng-bind-html="getUserForDMRDiv" compile-template   class="row clearfix col-md-offset-2">
								
							</div>
							<div class="row clearfix m-t-20" >
								<div class="col-lg-offset-6 col-md-offset-6 col-sm-offset-6 col-xs-offset-6">
									<button type="button" class="btn btn-primary m-t-15 waves-effect" ng-click="getIMPScharge(imps)">IMPS charge</button>
								</div>
							</div>
							</div>
							</div>
							<div id="sdiv90" class="row clearfix m-t-20" style="display: none;" >
								<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 discTable" ng-if = "viewIMPSchargeDetails.length > 0">
									<table  class="table table-striped" >
										<thead>
											<tr>
												<th class="success text-center">Sl No</th>
												<th class="success text-center">Range</th>
												<th class="success text-center">Charge</th>
											</tr>											
										</thead>
										<tbody>
											<tr  ng-repeat="IMPScharge in viewIMPSchargeDetails">
												<td>1.</td>
												<td>{{IMPScharge.slab1}}-{{IMPScharge.slab2}}</td>
												<td><input type="text" ng-model="IMPScharge.charge" class="form-control text-center" ></td>
											</tr>
										</tbody>
									</table>
								</div>
							</div>
							<div class="row clearfix m-t-20" >
								<div class="col-lg-offset-6 col-md-offset-6 col-sm-offset-6 col-xs-offset-6">
									<button type="button" class="btn btn-primary m-t-15 waves-effect" ng-click="setImpscharge(viewIMPSchargeDetails)">Update</button>
								</div>
							</div>
						</form>
					</div>

				</div>
			</div>
		</div>
		<!-------------- /Div8 ---------------------->
		<!-------------- Div9 ---------------------->
		<div id="div9" class="row clearfix" style="display: none; margin: 0px;">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<div class="card">
					<div class="header">
						<h2>Assign Package<span class="pull-right"><a href="#" onClick="showDiv('div7')"  class="body"><i class="material-icons">arrow_back</i></a></span></h2>
					</div>
					<div class="body">
						<div class="body">
							<form class="form-horizontal">
								<div class="row clearfix">
								<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 col-lg-offset-2 col-md-offset-2 " style="margin-left: 12%;">							
									<div
										class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label ">
										<label for="email_address_2">Usertype </label>
									</div>
									<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
										<select class="form-control " ng-model="pack.userType" ng-change = "getUserByUserType(pack.userType,'getUserForpack','pack')">
											 <option value="">-- Please select User Type--</option> 
											<% if (user.getRoleId() == 1) {	%>
											<option ng-repeat="userType in userType" ng-if="userType.type > 1 && userType.type < 101" value="{{userType.type}}">{{userType.role}}</option>
											<%}else{ %>
											<option ng-repeat="userType in userType" ng-if="userType.type > 2 && userType.type < 101" value="{{userType.type}}">{{userType.role}}</option>
											<%} %>
										</select>
									</div>
									</div>
								</div>
								<div class="row clearfix">	
								<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 col-lg-offset-2 col-md-offset-2 " style="margin-left: 12%;">							
									<div ng-bind-html="getUserForpack" compile-template></div>
									</div>
								</div>
								<div class="row clearfix">
								<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 col-lg-offset-2 col-md-offset-2 " style="margin-left: 12%;">							
									<div
										class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label ">
										<label for="email_address_2">Select Package</label>
									</div>
									<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
										<select class="form-control " ng-model="pack.package" >
											<option value="">-- Please select User Type--</option>
											<option ng-repeat="package in packageDetails" value="{{package.service_type}},{{package.package_id}}">{{package.package_name}}</option>
										</select>
										
									</div>
									</div>
								</div>
								<div class="row clearfix">
									<div class="col-lg-offset-6 col-md-offset-6 col-sm-offset-6 col-xs-offset-6">
										<button type="button" class="btn btn-primary m-t-15 waves-effect"  ng-click="assignPackage(pack)">Submit</button>
									</div>
								</div>	
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-------------- /Div9 ---------------------->
		<!-------------- Div10 ---------------------->
		<div id="div10" class="row clearfix" style="display: none; margin: 0px;">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<div class="card">
					<div class="header">
						<h2>IMPS Commission Setting</h2>
					</div>
					<div class="body">
						<form class="form-horizontal">
							<div class="row clearfix">
								<div
									class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
									<label for="email_address_2">Super Distributer's
										Commission </label>
								</div>
								<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
									<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
										<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 m-l-15">
											<div class="form-group form-float">
												<div class="form-line">
													<input type="text" class="form-control text-center"
														placeholder="0.0">
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="row clearfix">
								<div
									class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
									<label for="email_address_2">Select User </label>
								</div>
								<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
									<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
										<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 m-l-15">
											<div class="form-group form-float">
												<div class="form-line">
													<input type="text" class="form-control text-center"
														placeholder="0.0">
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="row clearfix">
								<div
									class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
									<label for="email_address_2">Select User Type </label>
								</div>
								<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
									<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
										<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 m-l-15">
											<select class="form-control">
												<option value="">-- Please select User --</option>
												<option value="10">Super Distributor</option>
												<option value="20">Distributor</option>
												<option value="30">Retailer</option>
											</select>
										</div>
									</div>
								</div>
							</div>
							<div class="row clearfix">
								<div
									class="col-lg-offset-6 col-md-offset-6 col-sm-offset-6 col-xs-offset-6">
									<button type="button"
										class="btn btn-primary m-t-15 waves-effect">Submit</button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
		<!-------------- /Div10 ---------------------->
		<!-------------- Div11 ---------------------->
		<div id="div11" class="row clearfix" style="display: none; margin: 0px;">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<div class="card">
					<div class="header">
						<h2>Add User</h2>
					</div>
					<div class="body">
						<div class="body">
							<form class="form-horizontal" >
								<div class="row clearfix">
									<div class="col-md-6 col-lg-6 col-sm-12 col-xs-12">
										<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
											<label for="email_address_2">First Name</label>
										</div>
										<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
											<div class="form-group form-float">
												<div class="form-line">
													<input type="text" class="form-control" ng-model = "addUserDetails.name" placeholder="Enter your Name" ng-change="addUserDetails.name=addUserDetails.name.toUpperCase();">
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
											<div class="form-group form-float">
												<div class="form-line">
													<input type="text" class="form-control" ng-model = "addUserDetails.firmName" placeholder="Enter your Firm Name" ng-change="addUserDetails.firmName=addUserDetails.firmName.toUpperCase();">
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
											<div class="form-group form-float">
												<div class="form-line">
													<input type="text" ng-model = "addUserDetails.mobile" ng-change = "checkUniqueUser(addUserDetails.mobile);" class="form-control" placeholder="Enter your Mobile Number" ng-keypress = "filterValue($event);" ng-minlength="10" maxlength="10">
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
											<div class="form-group form-float">
												<div class="form-line">
													<input type="text" class="form-control" ng-model = "addUserDetails.email" ng-change = "checkUniqueUser(addUserDetails.email);" placeholder="Enter your E-Mali" ng-change="addUserDetails.email=addUserDetails.email.toUpperCase();">
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
											<div class="form-group form-float">
												<div class="form-line">
													<input type="text" ng-model = "addUserDetails.address" class="form-control" placeholder="Enter your Address" ng-change="addUserDetails.address=addUserDetails.address.toUpperCase();">
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
											<div class="form-group form-float">
												<div class="form-line">
													<input type="text" ng-model = "addUserDetails.pinCode" class="form-control" placeholder="Enter your PIN Code" ng-keypress = "filterValue($event);">
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
											<div class="form-group form-float">
												<div class="ui-widget form-line">
													<input type="text" id="" class="tags form-control" ng-model="addUserDetails.state" placeholder="Enter State" ng-change="addUserDetails.state=addUserDetails.state.toUpperCase();">
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
											<div class="form-group form-float">
												<div class="form-line">
													<input type="text" ng-model = "addUserDetails.city" class="form-control" placeholder="Enter your City" ng-change="addUserDetails.city=addUserDetails.city.toUpperCase();">
												</div>
											</div>
										</div>
									</div>
								</div>	
								<div class="row clearfix">									
									<div class="col-md-6 col-lg-6 col-sm-12 col-xs-12">
										<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
											<label for="email_address_2">User Type</label>
										</div>
										<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 m-l--20">
											<select class="form-control" ng-model ="addUserDetails.userType" ng-change = "_GetUpline(addUserDetails.userType,'getUplineIdForAddUserDiv','addUserDetails')">
												<option value="">-- Please select User --</option>
												<%if(user.getRoleId() == 1){ %>
													<option ng-repeat="userType in userType" ng-if="userType.type > 2" value="{{userType.type}}">{{userType.role}}</option>
												<%} else { %>
													<option ng-repeat="userType in userType" ng-if="userType.type > 2  && userType.type < 6" value="{{userType.type}}" >{{userType.role}}</option>
												<%} %>
											</select>
										</div>
									</div>
								</div>	
								<div class="row clearfix">
									<div class="col-md-6 col-lg-6 col-sm-12 col-xs-12" ng-if = "addUserDetails.userType == 5 || addUserDetails.userType == 4">
										<div ng-bind-html="getUplineIdForAddUserDiv" compile-template></div>
									</div>
								</div>				
								<div class="row clearfix">
									<div class="col-lg-offset-2 col-md-offset-2 col-sm-offset-4 col-xs-offset-5">
										<button type="button" class="btn btn-primary m-t-15 waves-effect" ng-click = "addNewUser(addUserDetails);">ADD USER</button>										
									
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-------------- /Div11 ---------------------->
		
		<!-------------- Div12 ---------------------->
		<div id="div12" class="row clearfix" style="display: none; margin: 0px;">
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
											<select class="form-control" ng-model="viewUser.userType"  ng-init="viewUser.userType = '0'">
												<option value="0">All</option>
												<%if(user.getRoleId() == 1){ %>
													<option ng-repeat="userType in userType" ng-if="userType.type > 2" value="{{userType.type}}">{{userType.role}}</option>
												<%} else { %>
													<option ng-repeat="userType in userType" ng-if="userType.type > 2" value="{{userType.type}}" >{{userType.role}}</option>
												<%} %>												
											</select>
										</div>
									</div>									
								</div>								
								<div class="row clearfix">
									<div class="col-lg-offset-2 col-md-offset-2 col-sm-offset-4 col-xs-offset-5">
										<button type="button" class="btn btn-primary m-t-15 waves-effect" ng-click = "getViewUser(viewUser);">SUBMIT</button>										
									</div>
									<div class="col-lg-offset-2 col-md-offset-2 col-sm-offset-4 col-xs-offset-5">
										<button type="button" class="btn btn-primary m-t-15 waves-effect" ng-click = "getViewInactiveUser(viewUser);">InActive User</button>										
									</div>
								</div>
								<!-- ---VIEW USER---- -->
								<div class="row clearfix m-t-20"  ng-if = "viewUserDetails.length > 0">
								<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 hscroll">
									<table class="table table-striped">
										<thead>
											<tr>
												<th class="success text-center">Sl. No</th>
												<th class="success text-center">Name</th>
												<th class="success text-center">Mobile</th>
												<th class="success text-center">Email</th>
												<th class="success text-center">User Type</th>
												<th class="success text-center">Address</th>
												<%
													if (user.getRoleId() == 1) {
												%>
												<th class="success text-center">Password</th>
												<%
													}
												%> 
												<th class="success text-center">Balance</th>
												<th class="success text-center">Upline Id</th>
												<th class="success text-center">Lock Amount</th>
												<th class="success text-center">Date and Time</th>
												<th class="success text-center">Status</th>
												<%
													if (user.getRoleId() == 1) {
												%>
												<th class="success text-center">Actions</th>
												<%
													}
												%>
											</tr>																							
										</thead>
										<tbody>
											<tr	ng-repeat="item in viewUserDetails | startFrom:currentPage*pageSize | limitTo:pageSize">
												<td style="font-size: 12px;">{{(pageSize*currentPage+1)+$index}}</td>
												<td style="font-size: 12px;">{{item.name}}</td>
												<td style="font-size: 12px;">{{item.mobile}}</td>
												<td style="font-size: 12px;">{{item.email}}</td>
												<td style="font-size: 12px;">
													<span ng-if = "item.roleId == 2">White Label</span>
													<span ng-if = "item.roleId == 3">SUPER DISTRIBUTOR</span>
													<span ng-if = "item.roleId == 4">DISTRIBUTOR</span>
													<span ng-if = "item.roleId == 5">RETAILER</span>
													<span ng-if = "item.roleId == 100">API USER</span>
												</td>
												<td style="font-size: 12px;">{{item.address}},&nbsp;{{item.city}}-{{item.pinCode}},&nbsp;{{item.state}}</td>
												<%
													if (user.getRoleId() == 1) {
												%>
												<td style="font-size: 12px;">{{item.password}}</td>
												<%
													}
												%> 
												<td style="font-size: 12px;">{{item.balance}}</td>
												<td style="font-size: 12px;">{{item.uplineFirmName}}<br>({{item.uplineMobile}})</td>
												<td style="font-size: 12px;">{{item.lockedAmount}}</td>
												<td style="font-size: 12px;">{{item.createdDate}}&nbsp;&nbsp;{{item.createdTime}}</td>
												<td style="font-size: 12px;" >
												<a ng-if = "item.status == 0" class = "success" ng-click = "changeStatus(item);">Active</a>
												<a ng-if = "item.status == 1" class = "danger" ng-click = "changeStatus(item);">Inactive</a>												
												<%
													if (user.getRoleId() == 1) {
												%>
												<td>												
												<table >
													<tr>
														 <td><a ng-click = "signInByAdmin(item);"><img class="" title="Sign In" alt="" src="assets/images/sign-in.png" style="width: 24px;"></a></td>
														<td><a href="#" data-toggle="modal" data-target="#squarespaceModal" ng-click="getAddBalanceModal(item);"><img class="" title="Add Balance"  alt=""	src="assets/images/add-balance.png" style="width: 24px;"></a></td>
														<td><a ng-click="sendPassword(item);"><img	class="" title="Send Password"  alt="" src="assets/images/send-pass.png" style="width: 24px;"></a></td>
														<td><a ng-click="deleteUser(item);"><img	class="" title="Delete User"  alt="" src="assets/images/delete-user.png" style="width: 24px;"></a></td>
													</tr>
												</table>
												</td>
												<%
													}
												%>
											</tr>
										</tbody>
									</table>
								</div>
								<div data-ng-show="viewUserPagination">
									<button ng-disabled="currentPage == 0" ng-click="currentPage=currentPage-1">Previous</button>
									{{currentPage+1}}/{{numberOfPages()}}
									<button ng-disabled="currentPage >= data.length/pageSize - 1" ng-click="currentPage=currentPage+1">Next</button>
									<button type="button" class="btn btn-primary" ng-click="getViewUserExcel(viewUserDetails);" ng-if = "viewUserDetails.length > 0">Export</button>
								</div>
								<div class="table table-striped" ng-if="viewUserDetails.length <= 0">
									<table cellspacing="1" class="table table-striped table-bordered table-hover">
										<tbody>
											<tr>
												<th align="center" style="color: red;">No Records Found</th>
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
		<!-- Modal Elements -->
		<% if (user.getRoleId() == 1) {	%>
		<div class="modal fade" id="squarespaceModal" tabindex="-1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">×</span><span class="sr-only">Close</span>
						</button>
						<h3 class="modal-title" id="lineModalLabel">Add Balance</h3>
					</div>
					<div class="modal-body">
						<div class="row clearfix">
							<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
								<label for="email_address_2">Name: </label>
							</div>
							<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
								<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
									<div class="form-group form-float" style="margin-left: 0px !important;">
										<div class="form-line">
											<input type="text" id="" class="form-control" ng-model="userAddBal.name" readonly="readonly">
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="row clearfix">
							<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
								<label for="email_address_2">Amount: </label>
							</div>
							<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
								<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
									<div class="form-group form-float" style="margin-left: 0px !important;">
										<div class="form-line">
											<input type="text" id="" class="form-control" ng-model="userAddBal.amount"  placeholder="Enter Amount" ng-keypress = "filterValue($event);">
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="row clearfix">
							<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
								<label for="email_address_2">Remark: </label>
							</div>
							<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
								<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
									<div class="form-group form-float" style="margin-left: 0px !important;">
										<div class="form-line">
											<input type="text" class="form-control" placeholder="Enter Remark" ng-model="userAddBal.remarks" >
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="row clearfix"></div>
						<div class="row clearfix">
							<div class="col-lg-offset-6 col-md-offset-6 col-sm-offset-6 col-xs-offset-6">
								<button type="button" class="btn btn-primary m-t-15 waves-effect" data-dismiss="modal"ng-click="addBalanceByAdmin(userAddBal);">Submit</button>
							</div>
						</div>
					</div>					
				</div>
			</div>
		</div>
		<%} %>
		<!-------------- /Div12 ---------------------->
		
		<!-------------- Div13 ---------------------->
		<div id="div13" class="row clearfix" style="display: none; margin: 0px;">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<div class="card">
					<div class="header">
						<h2>Lock User Account</h2>
					</div>
					<div class="body">
						<form class="form-horizontal">
							<div class="row clearfix">
								<div
									class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
									<label for="email_address_2">Select User Type </label>
								</div>
								<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
									<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
										<select class="form-control" ng-model="lockedAmount.userType" ng-change = "getUserByUserType(lockedAmount.userType,'getUserForlockedAmountDiv','lockedAmount')">
											<option value="">-- Please select User Type--</option>
											<option ng-repeat="userType in userType" ng-if="userType.type > 2" value="{{userType.type}}">{{userType.role}}</option>
										</select>
									</div>
								</div>
							</div>
							<div class="row clearfix">
							<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 col-lg-offset-2 col-md-offset-2 " style="margin-left: 12%;">								
								<div ng-bind-html="getUserForlockedAmountDiv" compile-template></div>
							</div>
							</div>
							<div class="row clearfix">
								<div
									class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
									<label for="email_address_2">Enter Amount </label>
								</div>
								<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
									<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
										<div class="form-group form-float"
											style="margin-left: 0px !important;">
											<div class="form-line">
												<input type="text" class="form-control" ng-model="lockedAmount.amount" placeholder="Enter Amount" ng-keypress = "filterValue($event);">
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="row clearfix"></div>
							<div class="row clearfix">
								<div
									class="col-lg-offset-6 col-md-offset-6 col-sm-offset-6 col-xs-offset-6">
									<button type="button" class="btn btn-primary m-t-15 waves-effect" ng-click = "setUserLockedAmount(lockedAmount);">Submit</button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
		<!-------------- /Div13 ---------------------->
		<!-------------- Div14 ---------------------->
		<div id="div14" class="row clearfix" style="display: none; margin: 0px;">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<div class="card">
					<div class="header">
						<h2>Assign Package<span class="pull-right"><a href="#" onClick="showDiv('div7')"  class="body"><i class="material-icons">arrow_back</i></a></span></h2>
					</div>
					<div class="body">
						<div class="body">
							<form class="form-horizontal">
								<div class="row clearfix">
								<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 col-lg-offset-2 col-md-offset-2 " style="margin-left: 12%;">							
									<div
										class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label ">
										<label for="email_address_2">Usertype </label>
									</div>
									<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
										<select class="form-control " ng-model="pack.userType" ng-change = "getUserByUserType(pack.userType,'getUserForpack','pack')">
											<option value="">-- Please select User Type--</option>
											<% if (user.getRoleId() == 1) {	%>
											<option ng-repeat="userType in userType" ng-if="userType.type > 1 && userType.type < 101" value="{{userType.type}}">{{userType.role}}</option>
											<%}else{ %>
											<option ng-repeat="userType in userType" ng-if="userType.type > 2 && userType.type < 101" value="{{userType.type}}">{{userType.role}}</option>
											<%} %>
										</select>
									</div>
									</div>
								</div>
								<div class="row clearfix">	
								<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 col-lg-offset-2 col-md-offset-2 " style="margin-left: 12%;">							
									<div ng-bind-html="getUserForpack" compile-template></div>
									</div>
								</div>
								<div class="row clearfix">
								<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 col-lg-offset-2 col-md-offset-2 " style="margin-left: 12%;">							
									<div
										class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label ">
										<label for="email_address_2">Select Package</label>
									</div>
									<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
										<select class="form-control " ng-model="pack.package" >
											<option value="">-- Please select User Type--</option>
											<option ng-repeat="package in packageDetails" value="{{package.service_type}},{{package.package_id}}">{{package.package_name}}</option>
										</select>
										
									</div>
									</div>
								</div>
								<div class="row clearfix">
									<div class="col-lg-offset-6 col-md-offset-6 col-sm-offset-6 col-xs-offset-6">
										<button type="button" class="btn btn-primary m-t-15 waves-effect"  ng-click="assignPackage(pack)">Submit</button>
									</div>
								</div>	
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
		
		<!-------------- /Div14 ---------------------->
		<!-------------- Div15 ---------------------->
		<div id="div15" class="row clearfix" style="display: none; margin: 0px;">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<div class="card">
					<div class="header">
						<h2>User Mapping</h2>
					</div>
					<div class="body">
						<form class="form-horizontal">
							<div class="row clearfix">
								<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
									<label for="email_address_2">Select User Type for Mapping</label>
								</div>
								<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
									<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
										<select class="form-control " ng-model="userMapping.mappuserType" ng-change = "getUserByUserType(userMapping.mappuserType,'getUserForuserMappingDiv','userMapping')">
											<option value="">-- Please select User Type--</option>
											<option ng-repeat="userType in userType" ng-if="userType.type > 3 && userType.type < 100" value="{{userType.type}}">{{userType.role}}</option>
										</select>
									</div>
								</div>
							</div>
							<div class="row clearfix">	
							<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 col-lg-offset-2 col-md-offset-2 " style="margin-left: 12%;">							
								<div ng-bind-html="getUserForuserMappingDiv" compile-template></div>
								</div>
							</div>
							
							<div class="row clearfix">
								<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
									<label for="email_address_2">Select User Type </label>
								</div>
								<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
									<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
										<select class="form-control " ng-model="userMappingTo.userType" ng-change = "getUserByUserType(userMappingTo.userType,'getUserForuserMappingToDiv','userMappingTo')">
											<option value="">-- Please select User Type--</option>
											<option ng-repeat="userType in userType" ng-if="userType.type >= 3 && userType.type < 100" value="{{userType.type}}">{{userType.role}}</option>
										</select>
									</div>
								</div>
							</div>
							<div class="row clearfix">	
							<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 col-lg-offset-2 col-md-offset-2 " style="margin-left: 12%;">							
								<div ng-bind-html="getUserForuserMappingToDiv" compile-template></div>
								</div>
							</div>
							
							<div class="row clearfix">
								<div
									class="col-lg-offset-6 col-md-offset-6 col-sm-offset-6 col-xs-offset-6">
									<button type="button"
										class="btn btn-primary m-t-15 waves-effect" ng-click = "userMappingFunc(userMapping,userMappingTo);">User Search</button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
		<!-------------- /Div15 ---------------------->
		<!-------------- Div16 ---------------------->
		<div id="div16" class="row clearfix" style="display: none; margin: 0px;">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<div class="card">
					<div class="header">
						<h2>White Label Discount</h2>
					</div>
					<div class="body">
						<form class="form-horizontal">
							<div class="row clearfix">
								<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
									<label for="email_address_2">Select White Label </label>
								</div>
								<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
									<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
										<select class="form-control"  ng-model="resDiscount.userType" ng-change = "getUserByUserType(resDiscount.userType,'getUserForresDiscountDiv','resDiscount')">
											<option value="">-- Select --</option>
											<option ng-repeat="userType in userType" ng-if="userType.type == 2" value="{{userType.type}}">{{userType.role}}</option>
										</select>
									</div>
								</div>
							</div>
							<div class="row clearfix">
							<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 col-lg-offset-2 col-md-offset-2 " style="margin-left: 12%;">
								<div ng-bind-html="getUserForresDiscountDiv" compile-template></div>
								</div>
							</div>
							<div class="row">
								<div class="col-xs-4 col-sm-4 col-md-2 col-lg-2">
									<div class="widget2 widget3">
										<a onClick="sshowDiv('sdiv1')" href="#"  ng-click = "getResellerDiscount(resDiscount.userId);">
											<div class="stats-left ">
												<i class="fa fa-mobile col-black"></i>
												<h5>Recharge / Utility</h5>
											</div>
											<div class="clearfix"></div>
										</a>
									</div>
								</div>
								<div class="col-xs-4 col-sm-4 col-md-2 col-lg-2">
									<div class="widget2 widget3">
										<a onclick="sshowDiv('sdiv2')" href="javascript:;" id="">
											<div class="stats-left ">
												<i class="fa  fa-plane col-black"></i>
												<h5>Flight Booking</h5>
											</div>
											<div class="clearfix"></div>
										</a>
									</div>
								</div>
								<div class="col-xs-4 col-sm-4 col-md-2 col-lg-2">
									<div class="widget2 widget3">
										<a onclick="sshowDiv('sdiv3')" href="javascript:;" id="">
											<div class="stats-left ">
												<i class="fa fa-bus col-black"></i>
												<h5>Bus Booking</h5>
											</div>
											<div class="clearfix"></div>
										</a>
									</div>
								</div>
								<div class="col-xs-4 col-sm-4 col-md-2 col-lg-2">
									<div class="widget2 widget3">
										<a onclick="sshowDiv('sdiv4')" href="javascript:;" id="">
											<div class="stats-left ">
												<i class="fa fa-bed col-black"></i>
												<h5>Hotel Booking</h5>
											</div>
											<div class="clearfix"></div>
										</a>
									</div>
								</div>
								<div class="col-xs-4 col-sm-4 col-md-2 col-lg-2">
									<div class="widget2 widget3">
										<a onclick="sshowDiv('sdiv5')" href="javascript:;" id="">
											<div class="stats-left ">
												<i class="fa  fa-train col-black"></i>
												<h5>Train Ticket Booking</h5>
											</div>
											<div class="clearfix"></div>
										</a>
									</div>
								</div>
								<div class="col-xs-4 col-sm-4 col-md-2 col-lg-2">
									<div class="widget2 widget3">
										<a onclick="sshowDiv('sdiv6')" href="javascript:;" id="">
											<div class="stats-left ">
												<i class="fa  fa-podcast col-black"></i>
												<h5>DTH Connection</h5>
											</div>
											<div class="clearfix"></div>
										</a>
									</div>
								</div>
							</div>
						</form>
						<div class="body" id="sdiv1" style="display: none;">
							<div class="row clearfix">
								<div class="col-md-12">
									<!--------- Individual Discount Table ------------>
									<div class="row clearfix m-t-20"  ng-if = "resellerDiscount.length > 0">
										<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 discTable">
											<table id="mainTable" class="table table-striped">
												<thead>
													<tr>
														<th class="success text-center">Operator Names</th>
														<th class="success text-center">Commission</th>
													</tr>
												</thead>
												<tbody>
													<tr ng-repeat="item in resellerDiscount">
														<td>{{item.serviceProvider}}</td>
														<td><input type="text" name="r_comm" ng-model="item.commission" class="form-control text-center"></td>
													</tr>
												</tbody>
											</table>
										</div>
									</div>
									<div class="row clearfix"  ng-if = "resellerDiscount.length > 0">
										<div class="col-lg-offset-6 col-md-offset-6 col-sm-offset-6 col-xs-offset-6">
											<button type="button" class="btn btn-primary m-t-15 waves-effect" ng-click="setIndividualDiscount(resellerDiscount);">Update</button>
										</div>
									</div>
						<!--------- Individual Discount Table ------------>
								</div>
							</div>
						</div>
						<div class="body" id="sdiv2" style="display: none;">
							<div class="row clearfix">
								<div class="col-md-12">
									<div class="jumbotorn">
										This Service will be Comeing Soon......
									</div>
								</div>
							</div>
						</div>
						<div class="body" id="sdiv3" style="display: none;">
							<div class="row clearfix">
								<div class="col-md-12">
									<div class="jumbotorn">
										This Service will be Comeing Soon......
									</div>
								</div>
							</div>
						</div>
						
						<div class="body" id="sdiv4" style="display: none;">
							<div class="row clearfix">
								<div class="col-md-12">
									<div class="jumbotorn">
										This Service will be Comeing Soon......
									</div>
								</div>
							</div>
						</div>
						
						<div class="body" id="sdiv5" style="display: none;">
							<div class="row clearfix">
								<div class="col-md-12">
									<div class="jumbotorn">
										This Service will be Comeing Soon......
									</div>
								</div>
							</div>
						</div>
						
						<div class="body" id="sdiv6" style="display: none;">
							<div class="row clearfix">
								<div class="col-md-12">
									<div class="jumbotorn">
										This Service will be Comeing Soon......
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-------------- /Div16 ---------------------->
		<!-------------- Div17 ---------------------->
		<div id="div17" class="row clearfix" style="display: none; margin: 0px;">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<div class="card">
					<div class="header">
						<h2>Edit White Label</h2>
					</div>
					<div class="body">
						<form class="form-horizontal">
							<div class="row clearfix">
								<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
									<label for="email_address_2">Select Type</label>
								</div>
								<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
									<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
										<select class="form-control" ng-model = "editReseller.userType" ng-change = "getUserByUserType1(editReseller.userType,'getUserForeditResellerDiv','editReseller')">											
											<option value="">Select White Label</option>											
											<option ng-repeat="userType in userType" ng-if="userType.type == 2" value="{{userType.type}}">{{userType.role}}</option>
										</select>
									</div>
								</div>
							</div>
							<div class="row clearfix">
								<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 col-lg-offset-2 col-md-offset-2 " style="margin-left: 12%;">
									<div ng-bind-html="getUserForeditResellerDiv" compile-template></div>
								</div>
							</div>
							<div class="row clearfix">
								<div class="col-lg-offset-6 col-md-offset-6 col-sm-offset-6 col-xs-offset-6">
									<button type="button" class="btn btn-primary m-t-15 waves-effect" ng-click = "getResellerDetails(editReseller);">Submit</button>
								</div>
							</div>
						</form>
					</div>
					
					<div class="body" ng-if = "resellerDetails != undefined">
						<form class="form-horizontal">
							<div class="row clearfix">
								<div class="col-md-6 col-lg-6 col-sm-12 col-xs-12">
									<div
										class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
										<label for="email_address_2">Name</label>
									</div>
									<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
										<div class="form-group form-float">
											<div class="form-line">
												<input type="text" class="form-control" ng-model = "resellerDetails.name" placeholder="Enter your Name">
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
										<div class="form-group form-float">
											<div class="form-line">
												<input type="text" class="form-control" ng-model = "resellerDetails.firmName" placeholder="Enter your Firm Name">
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
										<div class="form-group form-float">
											<div class="form-line">
												<input type="text" ng-model = "resellerDetails.mobile" ng-change = "checkUniqueUser(resellerDetails.mobile);" class="form-control" placeholder="Enter your Mobile Number" ng-keypress = "filterValue($event);">
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
										<div class="form-group form-float">
											<div class="form-line">
												<input type="text" class="form-control" ng-model = "resellerDetails.email" ng-change = "checkUniqueUser(resellerDetails.email);" placeholder="Enter your E-Mali">
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
										<div class="form-group form-float">
											<div class="form-line">
												<input type="text" ng-model = "resellerDetails.address" class="form-control" placeholder="Enter your Address">
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
										<div class="form-group form-float">
											<div class="form-line">
												<input type="text" ng-model = "resellerDetails.pinCode" class="form-control" placeholder="Enter your PIN Code" ng-keypress = "filterValue($event);">
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
										<div class="form-group form-float">
											<div class="ui-widget form-line">
												<input type="text" id="" class="tags form-control" ng-model="resellerDetails.state" placeholder="Enter State">
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
										<div class="form-group form-float">
											<div class="form-line">
												<input type="text" ng-model = "resellerDetails.city" class="form-control" placeholder="Enter your City">
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="row clearfix">								
								<div class="col-md-6 col-lg-6 col-sm-12 col-xs-12">
									<div
										class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
										<label for="password_2">Powered by</label>
									</div>
									<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
										<div class="form-group form-float">
											<div class="form-line">
												<input type="text" ng-model = "resellerDetails.poweredBy" class="form-control" placeholder="Enter poweredBy">
											</div>
										</div>
									</div>
								</div>
								<div class="col-md-6 col-lg-6 col-sm-12 col-xs-12">
									<div
										class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
										<label for="password_2">powered by Link</label>
									</div>
									<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
										<div class="form-group form-float">
											<div class="ui-widget form-line">
												<input type="text" class="tags form-control" ng-model="resellerDetails.poweredByLink" placeholder="Enter poweredByLink">
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="row clearfix">
								
								<div class="col-md-6 col-lg-6 col-sm-12 col-xs-12">
									<div
										class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
										<label for="password_2">Page title</label>
									</div>
									<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
										<div class="form-group form-float">
											<div class="form-line">
												<input type="text" ng-model = "resellerDetails.pageTitle" class="form-control" placeholder="Enter pageTitle">
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="row clearfix">
								<div class="col-lg-offset-2 col-md-offset-2 col-sm-offset-4 col-xs-offset-5 pull-right">
									<button type="submit" class="btn btn-primary m-t-15 waves-effect">Update</button>										
								</div>
							</div>
						</form>
					</div>					
				</div>
			</div>
		</div>
		<!-------------- /Div17 ---------------------->
		<!-------------- Div18 ---------------------->
		 <div id="div18" class="row clearfix" style="display: none; margin: 0px;">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<div class="card">
					<div class="header">
						<h2>Edit White Label Logo</h2>
					</div>
					<div class="body">
						<form class="form-horizontal">
							<div class="row clearfix">
								<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
									<label for="email_address_2">Select Type</label>
								</div>
								<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
									<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
										<select class="form-control" ng-model = "ResellerLogo.userType" ng-change = "getUserByUserType1(ResellerLogo.userType,'getUserForResellerLogoDiv','ResellerLogo')" ng-init = "ResellerLogo.userType = '10'">
											<option value="10">Own Theme</option>		
											<option ng-repeat="userType in userType" ng-if="userType.type == 2" value="{{userType.type}}">{{userType.role}}</option>
										</select>
									</div>
								</div>
							</div>
							<div class="row clearfix">
								<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 col-lg-offset-2 col-md-offset-2 " style="margin-left: 12%;">
									<div ng-bind-html="getUserForResellerLogoDiv" compile-template></div>
								</div>
							</div>
							<div class="row clearfix">
								<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
									<label for="email_address_2">Logo</label>
								</div>
								<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
									<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8" style="padding-left: 4%">
										<div class="form-group ">
											<input type="file" file-model="resLogo"/>
										</div>
									</div>
								</div>
							</div>
							<div class="row clearfix">
								<div class="col-lg-offset-6 col-md-offset-6 col-sm-offset-6 col-xs-offset-6">
									<button type="button" class="btn btn-primary m-t-15 waves-effect" ng-click = "setResellerLogo(ResellerLogo);">Submit</button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div> 
		<!-------------- /Div18 ---------------------->
		<!-------------- Div19 ---------------------->
		<div id="div19" class="row clearfix" style="display: none; margin: 0px;">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<div class="card">
					<div class="header">
						<h2>White Label Color Management</h2>
					</div>
					<div class="body">
						<form class="form-horizontal">
							<div class="row clearfix">
								<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
									<label for="email_address_2">Select Theme For</label>
								</div>
								<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
									<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
										<select class="form-control" ng-model = "colorManage.userType" ng-init="colorManage.userType = '10'" ng-change = "getUserByUserType1(colorManage.userType,'getUserForcolorManageDiv','colorManage')">											
											<option value="10">Own Theme</option>											
											<option ng-repeat="userType in userType" ng-if="userType.type == 2" value="{{userType.type}}">{{userType.role}}</option>
										</select>
									</div>
								</div>
							</div>
							<div class="row clearfix">
								<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 col-lg-offset-2 col-md-offset-2 " style="margin-left: 12%;">
									<div ng-bind-html="getUserForcolorManageDiv" compile-template></div>
								</div>
							</div>
							<div class="row clearfix">
								<div  class="tab-pane fade in active in active" >
				                    <ul class="demo-choose-skin list-inline">
				                        <li class="theme" ng-click="getThemeName('theme-red')">
				                            <div class="red"></div>
				                            <span>Red</span>
				                        </li>
				                        <li class="theme" ng-click="getThemeName('theme-pink')">
				                            <div class="pink"></div>
				                            <span>Pink</span>
				                        </li>
				                        <li class="theme" ng-click="getThemeName('theme-purple')">
				                            <div class="purple"></div>
				                            <span>Purple</span>
				                        </li>
				                        <li class="theme" ng-click="getThemeName('theme-deep-purple')">
				                            <div class="deep-purple"></div>
				                            <span>Deep Purple</span>
				                        </li>
				                        <li class="theme" ng-click="getThemeName('theme-indigo')">
				                            <div class="indigo"></div>
				                            <span>Indigo</span>
				                        </li>
				                        <li class="theme" ng-click="getThemeName('theme-blue')">
				                            <div class="blue"></div>
				                            <span>Blue</span>
				                        </li>
				                        <li class="theme" ng-click="getThemeName('theme-light-blue')">
				                            <div class="light-blue"></div>
				                            <span>Light Blue</span>
				                        </li>
				                        <li class="theme" ng-click="getThemeName('theme-cyan')">
				                            <div class="cyan"></div>
				                            <span>Cyan</span>
				                        </li>
				                        <li class="theme" ng-click="getThemeName('theme-teal')">
				                            <div class="teal"></div>
				                            <span>Teal</span>
				                        </li>
				                        <li class="theme" ng-click="getThemeName('theme-green')">
				                            <div class="green"></div>
				                            <span>Green</span>
				                        </li>
				                        <li class="theme" ng-click="getThemeName('theme-light-green')">
				                            <div class="light-green"></div>
				                            <span>Light Green</span>
				                        </li>
				                        <li class="theme" ng-click="getThemeName('theme-lime')">
				                            <div class="lime"></div>
				                            <span>Lime</span>
				                        </li>
				                        <li class="theme" ng-click="getThemeName('theme-yellow')">
				                            <div class="yellow"></div>
				                            <span>Yellow</span>
				                        </li>
				                        <li class="theme" ng-click="getThemeName('theme-amber')">
				                            <div class="amber"></div>
				                            <span>Amber</span>
				                        </li>
				                        <li class="theme" ng-click="getThemeName('theme-orange')">
				                            <div class="orange"></div>
				                            <span>Orange</span>
				                        </li>
				                        <li class="theme" ng-click="getThemeName('theme-deep-orange')">
				                            <div class="deep-orange"></div>
				                            <span>Deep Orange</span>
				                        </li>
				                        <li class="theme" ng-click="getThemeName('theme-brown')">
				                            <div class="brown"></div>
				                            <span>Brown</span>
				                        </li>
				                        <li class="theme" ng-click="getThemeName('theme-grey')">
				                            <div class="grey"></div>
				                            <span>Grey</span>
				                        </li>
				                        <li class="theme" ng-click="getThemeName('theme-blue-grey')">
				                            <div class="blue-grey"></div>
				                            <span>Blue Grey</span>
				                        </li>
				                        <li class="theme" ng-click="getThemeName('theme-black')">
				                            <div class="black"></div>
				                            <span>Black</span>
				                        </li>
				                    </ul>
				                </div>
							</div>
							<div class="row clearfix">
								<div class="col-lg-offset-6 col-md-offset-6 col-sm-offset-6 col-xs-offset-6">
									<button type="button" class="btn btn-primary m-t-15 waves-effect" ng-click = "setResellerTheme(colorManage);">Submit</button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
		<!-------------- /Div19 ---------------------->
		<!-------------- Div20 ---------------------->
		<div id="div20" class="row clearfix" style="display: none; margin: 0px;">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<div class="card">
					<div class="header">
						<h2>Insert Bank Details</h2>
					</div>
					<div class="body">
						<form class="form-horizontal">
							<div class="row clearfix">
								<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
									<label for="email_address_2">Bank A/C Name</label>
								</div>
								<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
									<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
										<div class="form-group form-float"
											style="margin-left: 0px !important;">
											<div class="form-line">
												<input type="text" class="form-control" placeholder="Enter Account Name"  ng-model = "bankDetails.accName">
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="row clearfix">
								<div
									class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
									<label for="email_address_2">Bank A/C No </label>
								</div>
								<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
									<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
										<div class="form-group form-float"
											style="margin-left: 0px !important;">
											<div class="form-line">
												<input type="text" id="" class="form-control" placeholder="Enter A/C No"  ng-model = "bankDetails.accNo">
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="row clearfix">
								<div
									class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
									<label for="email_address_2">IFSC Code</label>
								</div>
								<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
									<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
										<div class="form-group form-float"
											style="margin-left: 0px !important;">
											<div class="form-line">
												<input type="text" id="" class="form-control" placeholder="Enter IFSC Code" ng-model = "bankDetails.ifscCode">
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="row clearfix">
								<div
									class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
									<label for="email_address_2">Branch Name</label>
								</div>
								<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
									<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
										<div class="form-group form-float"
											style="margin-left: 0px !important;">
											<div class="form-line">
												<input type="text" id="" class="form-control"placeholder="Enter Branch Name" ng-model = "bankDetails.branchName">
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="row clearfix">
								<div
									class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
									<label for="email_address_2">Bank Name</label>
								</div>
								<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
									<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
										<div class="form-group form-float"
											style="margin-left: 0px !important;">
											<div class="form-line">
												<input type="text" id="" class="form-control" placeholder="Enter Bank Name" ng-model = "bankDetails.bankName">
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="row clearfix">
								<div class="col-lg-offset-6 col-md-offset-6 col-sm-offset-6 col-xs-offset-6">
									<button type="button" class="btn btn-primary m-t-15 waves-effect" ng-click = "insertBankDetails(bankDetails);">Submit</button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
		<!-------------- /Div20 ---------------------->
		<!-------------- Div21 ---------------------->
		<div id="div21" class="row clearfix" style="display: none; margin: 0px;">
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
										<th class="success text-center">Action</th>
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
										<td >
											<button data-toggle="modal" data-target="#editBankDetailsModal" class="edit-button" ng-click = "getEditBankDetailsModal(item);">Edit</button>
											<button class="delete-button" ng-click = "deleteBankDetails(item);">Delete</button>
										</td>
									</tr>
								</tbody>
							</table>
						</div>
						<div class="table table-striped" ng-if="viewBankDetails.length <= 0">
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
		
		<!-- ----------------------EDIT BANK DETAILS MODAL ---------------- -->
		
		<div class="modal fade" id="editBankDetailsModal" tabindex="-1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">×</span><span class="sr-only">Close</span>
						</button>
						<h3 class="modal-title" id="lineModalLabel">Edit Bank Details</h3>
					</div>
					<div class="modal-body">
						<div class="row clearfix">
							<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
								<label for="email_address_2">Bank A/C Name</label>
							</div>
							<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
								<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
									<div class="form-group form-float"
										style="margin-left: 0px !important;">
										<div class="form-line">
											<input type="text" class="form-control" placeholder="Enter Account Name"  ng-model = "bankDetails1.accName">
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="row clearfix">
							<div
								class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
								<label for="email_address_2">Bank A/C No </label>
							</div>
							<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
								<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
									<div class="form-group form-float"
										style="margin-left: 0px !important;">
										<div class="form-line">
											<input type="text" id="" class="form-control" placeholder="Enter A/C No"  ng-model = "bankDetails1.accNo">
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="row clearfix">
							<div
								class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
								<label for="email_address_2">IFSC Code</label>
							</div>
							<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
								<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
									<div class="form-group form-float"
										style="margin-left: 0px !important;">
										<div class="form-line">
											<input type="text" id="" class="form-control" placeholder="Enter IFSC Code" ng-model = "bankDetails1.ifscCode">
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="row clearfix">
							<div
								class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
								<label for="email_address_2">Branch Name</label>
							</div>
							<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
								<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
									<div class="form-group form-float"
										style="margin-left: 0px !important;">
										<div class="form-line">
											<input type="text" id="" class="form-control"placeholder="Enter Branch Name" ng-model = "bankDetails1.branchName">
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="row clearfix">
							<div
								class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
								<label for="email_address_2">Bank Name</label>
							</div>
							<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
								<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
									<div class="form-group form-float"
										style="margin-left: 0px !important;">
										<div class="form-line">
											<input type="text" id="" class="form-control" placeholder="Enter Bank Name" ng-model = "bankDetails1.bankName">
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="row clearfix">
							<div class="col-lg-offset-6 col-md-offset-6 col-sm-offset-6 col-xs-offset-6">
								<button type="button" class="btn btn-primary m-t-15 waves-effect" data-dismiss="modal" ng-click = "updateBankDetails(bankDetails1);">Submit</button>
							</div>
						</div>
					</div>					
				</div>
			</div>
		</div>
		
		<!-------------- /Div21 ---------------------->
		<!-------------- Div22 ---------------------->
		<div id="div22" class="row clearfix" style="display: none; margin: 0px;">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<div class="card">
					<div class="header">
						<h2>Add Balance</h2>
					</div>
					<div class="body">
						<form class="form-horizontal">
							<div class="row clearfix">
								<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
									<label for="email_address_2">Select User Type </label>
								</div>
								<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8"> 
									<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
										<select class="form-control" ng-model="addBalance.userType" ng-change = "getUserByUserType(addBalance.userType,'getUserForAddBalanceDiv','addBalance')">
											<option value="">-- Please select --</option>
											<% if(user.getRoleId() == 1){ %>	
											<option ng-repeat="userType in userType" value="{{userType.type}}">{{userType.role}}</option>
											<%} else { %>
											<option ng-repeat="userType in userType" ng-if="userType.type > 2" value="{{userType.type}}">{{userType.role}}</option>
											<%} %>
										</select>
									</div>
								</div>
							</div>
							<div class="row clearfix">
							<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 col-lg-offset-2 col-md-offset-2 " style="margin-left: 12%;">
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
												<input type="text" class="form-control text-center" ng-model = "addBalance.amount" ng-keypress = "filterValue($event);"	placeholder="Enter Amount">
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="row clearfix">
								<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
									<label for="email_address_2">Remarks</label>
								</div>
								<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
									<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 m-l-15">
										<div class="form-group form-float">
											<div class="form-line">
												<input type="text" class="form-control text-center" ng-model = "addBalance.remarks" placeholder="Remark">
											</div>
										</div>
									</div>
								</div>
							</div>							
							<div class="row clearfix">
								<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 "></div>
								<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
									<div class="col-md-offset-3">
										<button type="button" class="btn btn-primary waves-effect" ng-click="addBalanceByAdmin(addBalance);" >Update</button>
									</div>
								</div>
							</div>
						</form>
					</div>

				</div>
			</div>
		</div>
		<!-------------- /Div22 ---------------------->
		<!-------------- Div23 ---------------------->
		<div id="div23" class="row clearfix" style="display: none; margin: 0px;">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<div class="card">
					<div class="header">
						<h2>Revert Balance</h2>
					</div>
					<div class="body">
						<form class="form-horizontal">
							<div class="row clearfix">
								<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
									<label for="email_address_2">Select User Type </label>
								</div>
								<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
									<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
										<select class="form-control" ng-model="revertBalance.userType" ng-change = "getUserByUserType(revertBalance.userType,'getUserForrevertBalanceDiv','revertBalance')">
											<option value="">-- Please select --</option>
											<% if(user.getRoleId() == 1){ %>	
											<option ng-repeat="userType in userType" ng-if="userType.type > 1" value="{{userType.type}}">{{userType.role}}</option>
											<%} else { %>
											<option ng-repeat="userType in userType" ng-if="userType.type > 2" value="{{userType.type}}">{{userType.role}}</option>
											<%} %>
										</select>
									</div>
								</div>
							</div>
							<div class="row clearfix">
							<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 col-lg-offset-2 col-md-offset-2 " style="margin-left: 12%;">
								<div ng-bind-html="getUserForrevertBalanceDiv" compile-template></div>
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
												<input type="text" class="form-control text-center" ng-model="revertBalance.amount"	placeholder="Enter Amount" ng-keypress = "filterValue($event);" />
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
												<input type="text" class="form-control text-center" placeholder="Remark" ng-model="revertBalance.remark" />
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="row clearfix">
								<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 "></div>
								<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
									<div class="col-md-offset-3">
										<button type="button" class="btn btn-primary waves-effect" ng-click = "revertUserBalance(revertBalance);">Update</button>
									</div>
								</div>
							</div>
						</form>
					</div>

				</div>
			</div>
		</div>
		<!-------------- /Div23 ---------------------->
		<!-------------- Div24 ---------------------->
		<div id="div24" class="row clearfix" style="display: none; margin: 0px;">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<div class="card">
					<div class="header">
						<h2>Web Equery</h2>
					</div>
				<div class="body">
						<form class="form-horizontal">
							<div class="row clearfix">
								<div class="col-md-4">
									<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
										<label for="email_address_2">Start Date</label>
									</div>
									<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
										<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
											<div class="form-group form-float" style="margin-left: 0px !important;">
												<div class="form-line">
													<input type="text" class="form-control" id="dp23" placeholder="Enter Start Date" ng-model="viewenqury.startDate" readonly="readonly" />
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
													<input type="text" class="form-control" id="dp24" placeholder="Enter End Date" ng-model="viewenqury.endDate" readonly="readonly" />
												</div>
											</div>
										</div>

									</div>
								</div>
								<div class="col-md-4">
									<div class="col-lg-offset-6 col-md-offset-6 col-sm-offset-6 col-xs-offset-6">
									<button type="button"
										class="btn btn-primary m-t-15 waves-effect" ng-click = "viewWebEnquery(viewenqury)">Submit</button>
										
									</div>
								</div>
							</div>
							</form>
							<div class="row clearfix">
								<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 table-responsive" ng-if = "viewEnqueryReport.length > 0">

									<table id="mainTable" class="table table-striped">
										<thead>
											<tr style="font-size: 12px; font-weight: bold;">
												<th class="success text-center">Sl. No</th>
												<th class="success text-center">User Name</th>
												<th class="success text-center">Address</th>
												<th class="success text-center">Mail Id</th>
												<th class="success text-center">Mobile</th>
												<th class="success text-center">Remark</th>
												<th class="success text-center">Date</th>
											</tr>
										</thead>
										<tbody >
											<tr ng-repeat="drr in viewEnqueryReport ">
												<td style="font-size: 12px;">{{$index + 1}}</td>
												<td style="font-size: 12px;">{{drr.UserName}}</td>
												<td style="font-size: 12px;">{{drr.Address}}</td>
												<td style="font-size: 12px;">{{drr.mail_id}}</td>
												<td style="font-size: 12px;">{{drr.mobile}}</td>
												<td style="font-size: 12px;">{{drr.remark}}</td>
												<td style="font-size: 12px;">{{drr.date}}</td>
											</tr>
										</tbody>
									</table>
								</div>
							<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 table-responsive" ng-if="viewEnqueryReport.length == 0">
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
		
		<!-------------- /Div24 ---------------------->
		<!-------------- Div25 ---------------------->
		<div id="div25" class="row clearfix" style="display: none; margin: 0px;">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<div class="card">
					<div class="header">
						<h2>API Switching</h2>
					</div>
					<div class="body">
						<div class="form-group">
							<table id="mainTable" class="table table-striped">
								<thead>
									<tr>
										<th class="success text-center">Operator Name</th>
										<th class="success text-center">In Code</th>
										<th class="success text-center">Out Code</th>
										<th class="success text-center">Api Name</th>
										<th class="success text-center">Status</th>
									</tr>
								</thead>
								<tbody>
								<tr ng-repeat = "op in operator">
									<td class="text-center">{{op.serviceProvider}}</td>
									<td class="text-center"><input type="text" name="sd_comm" ng-model = "op.inCode" class="form-control text-center" readonly="readonly"></td>
									<td class="text-center"><input type="text" name="d_comm" ng-model = "op.outCode" class="form-control text-center"></td>
									<td class="text-center">
										<select class="form-control" ng-model = "op.apiId" ng-options="a.apiId as a.apiName for a in api"></select>
									</td>
									<td class="text-center">
										<select ng-model="op.status" class="form-control show-tick">
											<option value="available">available</option>
											<option value="unavailable">unavailable</option>
										</select>
									</td>
									</tr>
								</tbody>
							</table>
						</div>
						<div class="row clearfix">
							<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 "></div>
							<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
								<div class="col-md-offset-3">
									<button type="button" class="btn btn-primary waves-effect" ng-click = "apiSwitching(operator);">Update</button>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-------------- /Div25 ---------------------->
		<!-------------- Div26 ---------------------->
		<div id="div26" class="row clearfix" style="display: none; margin: 0px;">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<div class="card">
					<div class="header">
						<h2>Balance Request Report</h2>
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
													<input type="text" class="form-control" id="dp11" placeholder="Enter Start Date" ng-model="viewBalanceRequest.startDate" readonly="readonly" />
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
													<input type="text" class="form-control" id="dp12" placeholder="Enter Start Date" ng-model="viewBalanceRequest.endDate" readonly="readonly" />
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
										<button type="button" class="btn btn-primary waves-effect" ng-click = "getBalanceRequest(viewBalanceRequest);">Update</button>
										<button type="button" class="btn btn-primary waves-effect" onclick="showDiv('div44')">Back</button>
									</div>
								</div>
							</div>
							<div class="row clearfix">

								<div class="row clearfix" style="margin-top: 2%">
									<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 table-responsive" ng-if="viewBalanceRequestDetails.length > 0">
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
													<td style="font-size: 15px; text-align: center;">{{$index + 1}}</td>
													<td style="font-size: 15px; text-align: center;">{{item.name}}</td>
													<td style="font-size: 15px; text-align: center;">{{item.bankName}}</td>
													<td style="font-size: 15px; text-align: center;">{{item.branchName}}</td>
													<td style="font-size: 12px; text-align: center;">{{item.accName}}</td>
													<td style="font-size: 12px; text-align: center;">{{item.bankTransactionId}}</td>
													<td style="font-size: 12px; text-align: center;">{{item.amount}}</td>
													<td style="font-size: 12px; text-align: center;">{{item.paymentMode}}</td>
													<td style="font-size: 12px; text-align: center;">{{item.paymentDate}}</td>
													<td style="font-size: 12px; text-align: center;">{{item.status}}</td>
													<td style="font-size: 15px; text-align: center; color: green;">{{item.date}} {{item.time}}</td>
													<td class="in" style="font-size: 12px;">
														<span ng-if = "item.status == 'PENDING'">
															<button class="edit-button" ng-click = "updateBalanceRequest(item,'SUCCESS');">Success</button>
															<button class="delete-button" style="width: 95%;" ng-click = "updateBalanceRequest(item,'FAILED');">Failed</button>
														</span>
													</td>											
												</tr>
											</tbody>
										</table>
									</div>
									<div data-ng-show="viewBalanceRequestPagination" style="margin-left: 20px;">
										<button ng-disabled="currentPage == 0" ng-click="currentPage=currentPage-1">Previous</button>
										{{currentPage+1}}/{{numberOfPages()}}
										<button ng-disabled="currentPage >= data.length/pageSize - 1" ng-click="currentPage=currentPage+1">Next</button>
										<!-- <button type="button" class="btn btn-primary" ng-click="getViewUserExcel(viewUserDetails);" ng-if="viewUserDetails.length > 0">Export</button> -->
									</div>
									<div class="table table-striped" ng-if="viewBalanceRequestDetails.length <= 0">
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
						</form>
					</div>

				</div>
			</div>
		</div>
		<!-------------- /Div26 ---------------------->
		<!-------------- Div27 ---------------------->
		<div id="div27" class="row clearfix" style="display: none; margin: 0px;">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<div class="card">
					<div class="header">
						<h2>Revert Balance Report</h2>
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
													<input type="text" class="form-control" id="dp3" placeholder="Enter Start Date" ng-model="balRevReport.startDate" readonly="readonly" />
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
													<input type="text" class="form-control" id="dp4" placeholder="Enter Start Date" ng-model="balRevReport.endDate" readonly="readonly" />
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
										<button type="button" class="btn btn-primary waves-effect" ng-click = "viewBalRevReport(balRevReport);">Update</button>
										<button type="button" class="btn btn-primary waves-effect" onclick="showDiv('div44')">Back</button>
									</div>
								</div>
							</div>
							<div class="row clearfix">
								<div class="form-group" style="margin-left: 0%;">
									<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" ng-if="balRevTransactioReport.length > 0">
										<table id="mainTable" class="table table-striped">
											<thead>
												<tr>
													<th class="success text-center">Sl. No</th>
													<th class="success text-center">User Id</th>
													<th class="success text-center">Admin Op Bal</th>
													<th class="success text-center">Op Bal</th>
													<th class="success text-center">Credit</th>
													<th class="success text-center">Debit</th>
													<th class="success text-center">Cl Bal</th>
													<th class="success text-center">Admin Cl Bal</th>
													<th class="success text-center">Narration</th>
													<th class="success text-center">Remark</th>
													<th class="success text-center">Date and Time</th>

												</tr>
											</thead>
											<tbody>
												<tr ng-repeat="TransactioReport in balRevTransactioReport | startFrom:currentPage*pageSize | limitTo:pageSize">
													<td class="text-center">{{$index + 1}}</td>
													<td class="text-center">{{TransactioReport.name}}</td>
													<td class="text-center">{{TransactioReport.adopbal}}</td>
													<td class="text-center">{{TransactioReport.openingBal}}</td>
													<td class="text-center">{{TransactioReport.credit}}</td>
													<td class="text-center">{{TransactioReport.debit}}</td>
													<td class="text-center">{{TransactioReport.closingBal}}</td>
													<td class="text-center">{{TransactioReport.adclbal}}</td>
													<td class="text-center">{{TransactioReport.narration}}</td>
													<td class="text-center">{{TransactioReport.remarks}}</td>
													<td class="text-center">{{TransactioReport.date}}&nbsp;&nbsp;{{TransactioReport.time}}</td>
												</tr>
												<tr>
													<td class="text-center">Total</td>
													<td class="text-center"></td>
													<td class="text-center"></td>
													<td class="text-center"></td>
													<td class="text-center">{{balRevTransactioReport | totalCommission:'credit'}}</td>
													<td class="text-center">{{balRevTransactioReport | totalCommission:'debit'}}</td>
													<td class="text-center"></td>
													<td class="text-center"></td>
													<td class="text-center"></td>
													<td class="text-center"></td>
													<td class="text-center"></td>
												</tr>
											</tbody>
										</table>
									</div>
									<div data-ng-show="viewBalRevReportPagination">
										<button ng-disabled="currentPage == 0" ng-click="currentPage=currentPage-1">Previous</button>
										{{currentPage+1}}/{{numberOfPages()}}
										<button ng-disabled="currentPage >= data.length/pageSize - 1" ng-click="currentPage=currentPage+1">Next</button>
										<button type="button" class="btn btn-primary" ng-click="viewBalRevReportReportExcel(balRevTransactioReport);" ng-if="balRevTransactioReport.length > 0">Export</button>
									</div>
									<div class="table-responsive" ng-if="balRevTransactioReport.length <= 0">
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
						</form>
					</div>
				</div>
			</div>
		</div>
		<!-------------- /Div27 ---------------------->
		<!-------------- Div28 ---------------------->
		<div id="div28" class="row clearfix" style="display: none; margin: 0px;">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<div class="card">
					<div class="header">
						<h2>Transaction Report</h2>
					</div>
					<div class="body">
						<form class="form-horizontal">
							<div class="row clearfix">
								<div class="col-md-6">
									<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6 form-control-label">
										<label for="email_address_2">Start Date</label>
									</div>
									<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">

										<div class="form-group">
											<div class="form-group form-float" style="margin-left: 6%">
												<div class="form-line">
													<input type="text" class="form-control" id="dp5" placeholder="Enter Start Date" ng-model="tranReport.startDate" readonly="readonly" />
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="col-md-6">
									<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6 form-control-label">
										<label for="email_address_2">End Date</label>
									</div>
									<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">

										<div class="form-group">
											<div class="form-group form-float" style="margin-left: 6%">
												<div class="form-line">
													<input type="text" class="form-control" id="dp6" placeholder="Enter Start Date" ng-model="tranReport.endDate" readonly="readonly" />
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="row clearfix">
								<div class="col-md-6">
									<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6 form-control-label">
										<label for="email_address_2">User Type :</label>
									</div>
									<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
										<select class="form-control" ng-model="tranReport.userType" ng-init="tranReport.userType = '0'" ng-change = "getUserByUserType(tranReport.userType,'getUserFortranReportDiv','tranReport')">
											<option value = "0">All</option>
											<%if(user.getRoleId() == 1){ %>
												<option ng-repeat="userType in userType" ng-if="userType.type > 2" value="{{userType.type}}">{{userType.role}}</option>
											<%} else { %>
												<option ng-repeat="userType in userType" ng-if="userType.type > 2  && userType.type < 6" value="{{userType.type}}" >{{userType.role}}</option>
											<%} %>
										</select>
									</div>
								</div>
								<div class="col-md-6">
									<div ng-bind-html="getUserFortranReportDiv" compile-template></div>									
								</div>
							</div>

							<div class="row clearfix">
								<div class="col-lg-offset-5 col-md-offset-5 col-sm-offset-5 col-xs-offset-5">
									<button type="button" class="btn btn-primary m-t-15 waves-effect" ng-click = "getTransactionReport(tranReport);" >Submit</button>
									<button type="button" class="btn btn-primary m-t-15 waves-effect" onclick="showDiv('div44')">Back</button>
								</div>
							</div>
						</form>
						
							<div class="row clearfix" style="margin-top: 2%">
								<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 table-responsive" ng-if = "TransactioReport.length > 0">
									<table id="mainTable" class="table table-striped">
										<thead>
											<tr style="font-size: 12px; font-weight: bold;">
												<th class="success text-center">Sl. No</th>
												<th class="success text-center">User Id</th>											
												<%if(user.getRoleId() == 1){ %>
												<th class="success text-center">Admin Op Bal</th>
												<%} %>
												<th class="success text-center">Op Bal</th>
												<th class="success text-center">Credit</th>
												<th class="success text-center">Debit</th>
												<th class="success text-center">Cl Bal</th>
												<%if(user.getRoleId() == 1){ %>
												<th class="success text-center">Admin Cl Bal</th>
												<%} %>
												<th class="success text-center">Narration</th>
												<th class="success text-center">Remark</th>
												<th class="success text-center">Date and Time</th>
											</tr>
										</thead>
										<tbody>
											<tr	ng-repeat="TransactioReport in TransactioReport | startFrom:currentPage*pageSize | limitTo:pageSize" ng-if = "TransactioReport.credit != 0 || TransactioReport.debit != 0">
											<td>{{$index + 1}}</td>
											<td>{{TransactioReport.name}}</td>
											<%if(user.getRoleId() == 1){ %>
											<td>{{TransactioReport.adopbal}}</td>
											<%} %>
											<td>{{TransactioReport.openingBal}}</td>
											<td>{{TransactioReport.credit}}</td>
											<td>{{TransactioReport.debit}}</td>
											<td>{{TransactioReport.closingBal}}</td>
											<%if(user.getRoleId() == 1){ %>
											<td>{{TransactioReport.adclbal}}</td>
											<%} %>
											<td>{{TransactioReport.narration}}</td>
											<td>{{TransactioReport.remarks}}</td>
											<td>{{TransactioReport.date}}&nbsp;&nbsp;{{TransactioReport.time}}</td>
											</tr>
									</tbody>
								</table>
							</div>
							<div data-ng-show="viewTransactioReportPagination">
								<button ng-disabled="currentPage == 0" ng-click="currentPage=currentPage-1">Previous</button>
								{{currentPage+1}}/{{numberOfPages()}}
								<button ng-disabled="currentPage >= data.length/pageSize - 1" ng-click="currentPage=currentPage+1">Next</button>
								<button type="button" class="btn btn-primary" ng-click="getTransactionReportReportExcel(TransactioReport);" ng-if = "TransactioReport.length > 0">Export</button>
							</div>							
							<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 table-responsive" ng-if = "TransactioReport.length <= 0">
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
		
		<!-------------- /Div28 ---------------------->
		<!-------------- Div29 ---------------------->
		<div id="div29" class="row clearfix" style="display: none; margin: 0px;">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<div class="card">
					<div class="header">
						<h2>Recharge Report</h2>
					</div>
					<div class="body">
						<form class="form-horizontal">
							<div class="row clearfix">
								<div class="col-md-4">
									<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
										<label for="email_address_2">Status : </label>
									</div>
									<div class='col-lg-8 col-md-8 col-sm-8 col-xs-8'>
											<select class="form-control" ng-model="rechargeReport.status" ng-init="rechargeReport.status = 'All'">
												<option value="All">All</option>
												<option value="SUCCESS">SUCCESS</option>
												<option value="PENDING">PENDING</option>
												<option value="FAILED">FAILED</option>
											</select>
										
									</div>
								</div>
								<div class="col-md-4">
									<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
										<label for="email_address_2">User Type :</label>
									</div>
									<div class='col-lg-8 col-md-8 col-sm-8 col-xs-8'>
										
											<select class="form-control" ng-model="rechargeReport.userType" ng-init="rechargeReport.userType = '0'" ng-change = "getUserByUserType(rechargeReport.userType,'getUserForRechargeReportDiv','rechargeReport')">																
												<option value = "0">All</option>
												<option ng-repeat="userType in userType" ng-if="userType.type > 2 && userType.type < 101" value="{{userType.type}}">{{userType.role}}</option>
											</select>
										
									</div>
								</div>
								<div class="col-md-4">
									<div ng-bind-html ="getUserForRechargeReportDiv" compile-template></div>									
								</div>
							</div>
							<div class="row clearfix">
								<div class="col-md-4">
									<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
										<label for="email_address_2">Opeartor:</label>
									</div>
									<div class='col-lg-8 col-md-8 col-sm-8 col-xs-8'>
											<select ng-model="rechargeReport.operator" ng-init="rechargeReport.operator = '0'" class="form-control">
												<option value="0">ALL</option>
												<option ng-repeat="operator in operator" value="{{operator.operatorId}}">{{operator.serviceProvider}}</option>
											</select>
									</div>
								</div>
								<div class="col-md-4">
									<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
										<label for="email_address_2">Start Date</label>
									</div>
									<div class='col-lg-8 col-md-8 col-sm-8 col-xs-8'>
										<div class="form-group">
											<div class="form-group form-float" style="margin-left: 6%">
												<div class="form-line">
													<input type="text" class="form-control" id="dp7" placeholder="Enter Start Date" ng-model="rechargeReport.startDate" readonly="readonly"/>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="col-md-4">
									<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
										<label for="email_address_2">End Date</label>
									</div>
									<div class='col-lg-8 col-md-8 col-sm-8 col-xs-8'>
										<div class="form-group">
											<div class="form-group form-float" style="margin-left: 6%">
												<div class="form-line">
													<input type="text" class="form-control" id="dp8" placeholder="Enter Start Date" ng-model="rechargeReport.endDate" readonly="readonly" />
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="row clearfix">
								<div class="col-lg-offset-5 col-md-offset-5 col-sm-offset-5 col-xs-offset-5">
									<button type="button" class="btn btn-primary m-t-15 waves-effect" ng-click = "getRechargeReport(rechargeReport);">Submit</button>
									<button type="button" class="btn btn-primary m-t-15 waves-effect" onclick="showDiv('div44')">Back</button>
								</div>
							</div>
						</form>
						<div class="row clearfix" style="margin-top: 2%">
							<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 table-responsive" ng-if = "RechargeReports.length > 0">
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
											<th class="success text-center">Api</th>
											<th class="success text-center">Operator</th>
											<th class="success text-center">WhiteLebel</th>
											<th class="success text-center">Date&Time</th>	
											<%if(user.getRoleId() == 1){ %>										
											<th class="success text-center">Action</th>
											<%} %>
										</tr>
									</thead>
									<tbody>
										<tr ng-repeat="report in RechargeReports | startFrom:currentPage*pageSize | limitTo:pageSize">
											<td style="font-size: 12px;">{{$index + 1}}</td>
											<td style="font-size: 12px;">{{report.name}}<br> ({{report.usermobile}})</td>
											<td style="font-size: 12px;">{{report.mobile}}</td>
											<td style="font-size: 12px;">{{report.openBal}}</td>
											<td style="font-size: 12px;">{{report.amount}}</td>
											<td style="font-size: 12px;">{{report.charge}}</td>
											<td style="font-size: 12px;">{{report.comm}}</td>
											<td style="font-size: 12px;">{{report.closeBal}}</td>
											<td style="font-size: 12px;" class="{{report.validation}}">{{report.validation}}</td>
											<td style="font-size: 12px;">{{report.oid}}</td>
											<td style="font-size: 12px;">{{report.tid}}</td>
											<td style="font-size: 12px;">{{report.source}}</td>
											<td style="font-size: 12px;">{{report.apiName}}</td>
											<td style="font-size: 12px;">{{report.serviceProvider}}</td>
											<td style="font-size: 12px;">{{report.whiteLebel}}</td>
											<td style="font-size: 12px;">{{report.date}} {{report.time}}</td>
											<%if(user.getRoleId() == 1){ %>		
											<td class="in" style="font-size: 12px;">
												<button class="edit-button" data-toggle="modal" data-target="#modalSuccessRemark" ng-click="getmodalSuccessRemark(report,'SUCCESS');" ng-if = "report.validation == 'PENDING' || report.validation == 'FAILED'">Success</button>
												<button class="delete-button" data-toggle="modal" data-target="#modalSuccessRemark" style="width: 101%;" ng-click = "getmodalSuccessRemark(report,'FAILED');" ng-if = "report.validation == 'PENDING' || report.validation == 'SUCCESS'">Failed</button>
									
											</td>
											<%} %>
										</tr>
										<tr>
											<td></td>
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
											<td></td>
										</tr>	
									</tbody>
								</table>
								
								
								
							</div>
							<div data-ng-show="viewRechargeReportsPagination">
								<button ng-disabled="currentPage == 0" ng-click="currentPage=currentPage-1">Previous</button>
								{{currentPage+1}}/{{numberOfPages()}}
								<button ng-disabled="currentPage >= data.length/pageSize - 1" ng-click="currentPage=currentPage+1">Next</button>
								<button type="button" class="btn btn-primary" ng-click="getRechargeReportExcel(RechargeReports);" ng-if="RechargeReports.length > 0">Export</button>
							</div>
							<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 table-responsive" ng-if="RechargeReports.length <= 0">
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
	<!-- ------------------------------------- Recharge Success Modal-------------------------------------- -->
	
		<!-- Modal -->
		<div id="modalSuccessRemark" class="modal fade" role="dialog" tabindex="-1" aria-labelledby="modalLabel" aria-hidden="true">
		  <div class="modal-dialog">
		
		     <!-- Modal content-->
		    <div class="modal-content" style="width: 43%;left: 18%;">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal">&times;</button>
		        <h4 class="modal-title">Put Some Remark</h4>
		      </div>
		      <div class="modal-body">
		       	<div class="">
		       	<div class="col-md-2 text-right"> <label for="comment">Remark:</label></div>
				<div class="col-md-8"><input type="text" ng-model="popremark.oid" class="form-control" placeholder="Enter Remarks"></div> 
				</div>
					<div class="">
				<div class="col-md-8">
				<input type="hidden" ng-model="popremark.ptid" class="form-control" placeholder="Enter ptid">
				<input type="hidden" ng-model="popremark.tid" class="form-control" placeholder="Enter tid">
				<input type="hidden" ng-model="popremark.status" class="form-control" placeholder="Enter status"></div> 
				</div>
				<div class="" style="margin-top: 10%">
				<center>	
				  <input type="submit" class="btn btn-info" data-dismiss="modal" ng-click = "updateRechargeReport(popremark);">
				 </center>
				</div>
		      </div>
		      <div class="modal-footer">
		        
		      </div>
		    </div>
		
		  </div>
		</div>
	
	<!-- ------------------------------------- /Recharge Success -------------------------------------- -->

		<!-------------- /Div29 ---------------------->
		<!-------------- Div30 ---------------------->
		<div id="div30" class="row clearfix" style="display: none; margin: 0px;">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<div class="card">
					<div class="header">
						<h2>Refund History</h2>
					</div>
					<div class="body">
						<form class="form-horizontal">
							<div class="row clearfix">								
								<div class="col-md-6">
									<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6 form-control-label">
										<label for="email_address_2">Start Date</label>
									</div>
									<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
										<div class="form-group">
											<div class="form-group form-float" style="margin-left: 6%">
												<div class="form-line">
													<input type="text" id="dp1" class="form-control" placeholder="Enter Start Date"  readonly="readonly" ng-model="refundReport.startDate" />
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="col-md-6">
									<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6 form-control-label">
										<label for="email_address_2">End Date</label>
									</div>
									<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
										<div class="form-group">
											<div class="form-group form-float" style="margin-left: 6%">
												<div class="form-line">
													<input type="text" class="form-control" id="dp2" placeholder="Enter Start Date" ng-model="refundReport.endDate" readonly="readonly" />
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							
							<div class="row clearfix">
								<div class="col-md-6">
									<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6 form-control-label">
										<label for="email_address_2">User Type :</label>
									</div>
									<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
										<select class="form-control" ng-model="refundReport.userType" ng-init="refundReport.userType = '0'" ng-change = "getUserByUserType(refundReport.userType,'getUserForrefundReportDiv','refundReport')">
											<option value = "0">All</option>
											<%if(user.getRoleId() == 1){ %>
												<option ng-repeat="userType in userType" ng-if="userType.type > 2" value="{{userType.type}}">{{userType.role}}</option>
											<%} else { %>
												<option ng-repeat="userType in userType" ng-if="userType.type > 2  && userType.type < 6" value="{{userType.type}}" >{{userType.role}}</option>
											<%} %>
										</select>
									</div>
								</div>
								<div class="col-md-6">
									<div ng-bind-html="getUserForrefundReportDiv" compile-template></div>									
								</div>
							</div>
							<div class="row clearfix" style="margin-top: -2%">
								<div class="col-lg-offset-5 col-md-offset-5 col-sm-offset-5 col-xs-offset-5">
									<button type="button" class="btn btn-primary m-t-15 waves-effect" ng-click = "getRefundReport(refundReport);">Submit</button>
									<button type="button" class="btn btn-primary m-t-15 waves-effect" onclick="showDiv('div44')">Back</button>
								</div>
							</div>
						</form>

							<div class="row clearfix" style="margin-top: 2%">
								<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 table-responsive" ng-if = "refundReportDetails.length > 0">
									<table id="mainTable" class="table table-striped">
										<thead>
											<tr style="font-size: 12px; font-weight: bold;">
												<th class="success text-center">Sl. No</th>
												<th class="success text-center">User Id</th>
												<th class="success text-center">Admin Op Bal</th>
												<th class="success text-center">Op Bal</th>
												<th class="success text-center">Credit</th>
												<th class="success text-center">Debit</th>
												<th class="success text-center">Cl Bal</th>
												<th class="success text-center">Admin Cl Bal</th>
												<th class="success text-center">Narration</th>
												<th class="success text-center">Remark</th>
												<th class="success text-center">Date and Time</th>
											</tr>
										</thead>
										<tbody>
											<tr	ng-repeat="item in refundReportDetails | startFrom:currentPage*pageSize | limitTo:pageSize" ng-if = "refundReportDetails.credit != 0 || refundReportDetails.debit != 0">
											<td>{{$index + 1}}</td>
											<td>{{item.name}}</td>
											<td>{{item.adopbal}}</td>
											<td>{{item.openingBal}}</td>
											<td>{{item.credit}}</td>
											<td>{{item.debit}}</td>
											<td>{{item.closingBal}}</td>
											<td>{{item.adclbal}}</td>
											<td>{{item.narration}}</td>
											<td>{{item.remarks}}</td>
											<td>{{item.date}}&nbsp;&nbsp;{{item.time}}</td>
											</tr>
									</tbody>
								</table>
							</div>
							<div data-ng-show="viewRefundReportPagination">
								<button ng-disabled="currentPage == 0" ng-click="currentPage=currentPage-1">Previous</button>
								{{currentPage+1}}/{{numberOfPages()}}
								<button ng-disabled="currentPage >= data.length/pageSize - 1" ng-click="currentPage=currentPage+1">Next</button>
								<button type="button" class="btn btn-primary" ng-click="getTransactionReportReportExcel(refundReportDetails);" ng-if = "refundReportDetails.length > 0">Export</button>
							</div>							
							<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 table-responsive" ng-if = "refundReportDetails.length <= 0">
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

		<!-------------- /Div30 ---------------------->
		<!-------------- Div31 ---------------------->
		<div id="div31" class="row clearfix" style="display: none; margin: 0px;">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<div class="card">
					<div class="header">
						<h2>Search Nsdl Report</h2>
					</div>
					<div class="body">
						<form class="form-horizontal">
							<div class="row clearfix">
								<div
									class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
									<label for="email_address_2">Ack no </label>
								</div>
								<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
									<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
										<input type="text" class="form-control" ng-model="searchack.ackno" placeholder="Enter Ack No">
									</div>
								</div>
							</div>
							
							<div class="row clearfix">
								<div
									class="col-lg-offset-6 col-md-offset-6 col-sm-offset-6 col-xs-offset-6">
									<button type="button" class="btn btn-primary m-t-15 waves-effect" ng-click = "searchackno(searchack);">Submit</button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
		<!-------------- /Div31 ---------------------->
		<!-------------- Div32 ---------------------->
		<div id="div32" class="row clearfix" style="display: none; margin: 0px;">
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
													<input type="text" class="form-control" id="dp13" ng-model="viewUtilityRequest.startDate" readonly="readonly" />
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
													<input type="text" class="form-control" id="dp14" ng-model="viewUtilityRequest.endDate" readonly="readonly" />
												</div>
											</div>
										</div>

									</div>
								</div>
								<div class="col-md-4">
									<div class="col-lg-offset-2 col-md-offset-2 col-sm-offset-2 col-xs-offset-2">
									<button type="button" class="btn btn-primary m-t-15 waves-effect" ng-click = "viewUtilityReport(viewUtilityRequest);">Submit</button>
									<button type="button" class="btn btn-primary m-t-15 waves-effect" onclick="showDiv('div44')">Back</button>
								</div>
								</div>
								</div>
						</form>
							
						<div class="row clearfix" style="margin-top: 2%">
							<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 table-responsive"  ng-if="viewUtilityDetails.length > 0">
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
											<%if(user.getRoleId() == 1){ %>											
											<th class="success text-center">Action</th>
											<%} %>
										</tr>
									</thead>
									<tbody>
										<tr ng-repeat="utilityDetails in viewUtilityDetails ">
											<td style="font-size: 15px; text-align: center;">{{$index + 1}}</td>
											<td style="font-size: 15px; text-align: center;">{{utilityDetails.name}}({{utilityDetails.mobile}})</td>
											<td style="font-size: 15px; text-align: center;">
												<span ng-if = "utilityDetails.serviceType == 7">ELECTRIC BILL</span>
												<span ng-if = "utilityDetails.serviceType == 6">GAS BILL</span>
											</td>
											<td style="font-size: 15px; text-align: center;">{{utilityDetails.serviceName}}</td>
											<td style="font-size: 15px; text-align: center;">{{utilityDetails.accountNo}}</td>
											<td style="font-size: 15px; text-align: center;">{{utilityDetails.customerName}}</td>
											<td style="font-size: 15px; text-align: center;">{{utilityDetails.customerMobile}}	</td>
											<td style="font-size: 15px; text-align: center;">{{utilityDetails.billDueDate}}</td>
											<td style="font-size: 15px; text-align: center;">{{utilityDetails.amount}}</td>
											<td style="font-size: 15px; text-align: center;">{{utilityDetails.status}}</td>
											<td style="font-size: 15px; text-align: center;">{{utilityDetails.submitDate}} {{utilityDetails.submitTime}}</td>
											<%if(user.getRoleId() == 1){ %>		
											<td style="font-size: 15px; text-align: center;">
												<span ng-if = "utilityDetails.status == 'PENDING'">
													<button class="edit-button" ng-click = "updateUtilityReport(utilityDetails,'SUCCESS');" ng-if = "utilityDetails.status == 'PENDING' || utilityDetails.status == 'FAILED'">Success</button>
													<button class="delete-button" style="width: 95%;" ng-click = "updateUtilityReport(utilityDetails,'FAILED');" ng-if = "utilityDetails.status == 'PENDING' || utilityDetails.status == 'SUCCESS'">Failed</button>
												</span>
											</td>
											<%} %>
										</tr>
									</tbody>
								</table>
							</div>
							
							<div data-ng-show="viewUtilityPagination">
								<button ng-disabled="currentPage == 0" ng-click="currentPage=currentPage-1">Previous</button>
								{{currentPage+1}}/{{numberOfPages()}}
								<button ng-disabled="currentPage >= data.length/pageSize - 1" ng-click="currentPage=currentPage+1">Next</button>
								<button type="button" class="btn btn-primary" ng-click="getExcelReport(viewUtilityDetails,'UtilityReport');" ng-if="viewUtilityDetails.length > 0">Export</button>
							</div>
							<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 table-responsive" ng-if="viewUtilityDetails.length <= 0">
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

		<!-------------- /Div32 ---------------------->
		
		<!-------------- Div33 ---------------------->
		<div id="div33" class="row clearfix" style="display: none; margin: 0px;">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<div class="card">
					<div class="header">
						<h2>View Complain</h2>
					</div>
					<div class="body">
						<form class="form-horizontal">
							<div class="row clearfix">
								<div class="col-md-4">
									<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
										<label for="email_address_2">Start Date</label>
									</div>
									<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
										<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
											<div class="form-group form-float" style="margin-left: 0px !important;">
												<div class="form-line">
													<input type="text" class="form-control" id="dp9" placeholder="Enter Start Date" ng-model="viewComplain.startDate" readonly="readonly" />
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
													<input type="text" class="form-control" id="dp10" placeholder="Enter End Date" ng-model="viewComplain.endDate" readonly="readonly" />
												</div>
											</div>
										</div>

									</div>
								</div>
								<div class="col-md-4">
									<div
									class="col-lg-offset-6 col-md-offset-6 col-sm-offset-6 col-xs-offset-6">
									<button type="button"
										class="btn btn-primary m-t-15 waves-effect" ng-click = "getComplainDetails(viewComplain)">Submit</button>
										
								</div>
								</div>
								</div>
						</form>
							
						<div class="row clearfix" style="margin-top: 2%">
							<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 table-responsive" ng-if="viewComplainDetails.length > 0">
								<table id="mainTable" class="table table-striped">
									<thead>
										<tr style="font-size: 13px; font-weight: bold;">
											<th class="success text-center">Sl. No</th>
											<th class="success text-center">USER</th>
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
											<td style="font-size: 15px; text-align: center;">{{$index + 1}}</td>
											<td style="font-size: 15px; text-align: center;">{{complain.name}}({{complain.mobile}})</td>
											<td style="font-size: 15px; text-align: center;">{{complain.complainType}}</td>
											<td style="font-size: 15px; text-align: center;">{{complain.subject}}</td>
											<td style="font-size: 12px; text-align: center;">{{complain.complain}}</td>
											<td style="font-size: 15px; text-align: center; color: green;">{{complain.date}} {{complain.time}}</td>
											<td style="font-size: 15px; text-align: center; color: green;">
												<input type = "text" ng-model = "complain.adminMessage" ng-disabled="complain.status != 'PENDING'" />
											</td>
											<td style="font-size: 15px; text-align: center;">
												<span ng-if="complain.status != 'PENDING'">{{complain.status}}</span>
												<span ng-if="complain.status == 'PENDING'">
													<select ng-model = "complain.status" ng-change = "updateComplainDetails(complain)">
														<option value="PENDING">PENDING</option>
														<option value="SUCCESS">SUCCESS</option>
														<option value="FAILED">FAILED</option>
													</select>
												</span>
											</td>											
										</tr>
									</tbody>
								</table>
							</div>
							<div data-ng-show="viewComplainPagination">
								<button ng-disabled="currentPage == 0" ng-click="currentPage=currentPage-1">Previous</button>
								{{currentPage+1}}/{{numberOfPages()}}
								<button ng-disabled="currentPage >= data.length/pageSize - 1" ng-click="currentPage=currentPage+1">Next</button>
								<button type="button" class="btn btn-primary" ng-click="getViewUserExcel(viewUserDetails);" ng-if="viewUserDetails.length > 0">Export</button>
							</div>
							<div class="table table-striped" ng-if="viewComplainDetails.length <= 0">
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

		<!-------------- /Div33 ---------------------->
		
		<!-------------- Div34 ---------------------->
		<div id="div34" class="row clearfix" style="display: none; margin: 0px;">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<div class="card">
					<div class="header">
						<h2>Edit User Profile</h2>
					</div>
					<div class="body">
						<div class="row clearfix">
								<div
									class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
									<label for="email_address_2">Usertype </label>
								</div>
								<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
									<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
										<select class="form-control " ng-model="editUser.userType" ng-change = "getUserByUserType(editUser.userType,'getUserForEditUserDiv','editUser')">
											<option value="">-- Please select User Type--</option>
											<option ng-repeat="userType in userType" ng-if="userType.type >= 3" value="{{userType.type}}">{{userType.role}}</option>
										</select>
									</div>
								</div>
							</div>
							<div class="row clearfix">	
							<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 col-lg-offset-2 col-md-offset-2 " style="margin-left: 12%;">							
								<div ng-bind-html="getUserForEditUserDiv" compile-template></div>
								</div>
							</div>
							<div class="row clearfix">
								<div class="col-lg-offset-6 col-md-offset-6 col-sm-offset-6 col-xs-offset-6">
									<button type="button" class="btn btn-primary m-t-15 waves-effect" ng-click = "getUserDetailsForEdit(editUser);" >Submit</button>
								</div>
							</div>
							<div class = "card" style="padding: 3%;margin-top: 5%;" ng-if = "editUserProfile.status == '1'">
							<div class="row clearfix">
							<form class="form-horizontal">
								<div class="row clearfix">
									<div class="col-md-6 col-lg-6 col-sm-12 col-xs-12">
										<div
											class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
											<label for="email_address_2">Name</label>
										</div>
										<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
											<div class="form-group form-float">
												<div class="form-line">
													<input type="text" id="" class="form-control" ng-model="userProfile.name" placeholder="Enter your Name">
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
											<div class="form-group form-float">
												<div class="form-line">
													<input type="text" id="" class="form-control" ng-model="userProfile.firmName" placeholder="Enter your Firm Name">
												</div>
											</div>
										</div>
									</div>
								</div>
								
								<div class="row clearfix">
									<div class="col-md-6 col-lg-6 col-sm-12 col-xs-12">
										<div
											class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
											<label for="email_address_2">Password</label>
										</div>
										<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
											<div class="form-group form-float">
												<div class="form-line">
													<input type="text" id="" class="form-control" ng-model="userProfile.password" placeholder="Enter password">
												</div>
											</div>
										</div>
									</div>
									<div class="col-md-6 col-lg-6 col-sm-12 col-xs-12">
										<div
											class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
											<label for="password_2">Confirm Password</label>
										</div>
										<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
											<div class="form-group form-float">
												<div class="form-line">
													<input type="text" id="" class="form-control" ng-model="userProfile.cPassword" placeholder="Confirm Password">
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
											<div class="form-group form-float">
												<div class="form-line">
													<input type="text" ng-model="userProfile.mobile" class="form-control" placeholder="Enter your Mobile Number" ng-keypress = "filterValue($event);" maxlength="10" ng-change = "checkUniqueUser(userProfile.mobile);">
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
											<div class="form-group form-float">
												<div class="form-line">
													<input type="text" class="form-control" ng-model="userProfile.email" placeholder="Enter your E-Mail" ng-change = "checkUniqueUser(userProfile.email);">
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
											<div class="form-group form-float">
												<div class="form-line">
													<input type="text" id="email_address_2" ng-model="userProfile.address" class="form-control" placeholder="Enter your Address">
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
											<div class="form-group form-float">
												<div class="form-line">
													<input type="text" class="form-control" ng-model="userProfile.pinCode" placeholder="Enter your PIN Code" ng-keypress = "filterValue($event);">
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
											<div class="form-group form-float">
												<div class="ui-widget form-line">
													<input type="text" id="" class=" tags form-control" ng-model="userProfile.state">
												</div>
											</div>
										</div>
									</div>
									<div class="col-md-6 col-lg-6 col-sm-12 col-xs-12">
										<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
											<label for="password_2">City</label>
										</div>
										<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
											<div class="form-group form-float">
												<div class="form-line">
													<input type="text" id="" class="form-control" ng-model="userProfile.city" placeholder="Enter your PIN Code">
												</div>
											</div>
										</div>
									</div>
								</div>


								<div class="row clearfix">
									<div class="col-lg-offset-2 col-md-offset-2 col-sm-offset-4 col-xs-offset-5">
										<button type="button" class="btn btn-primary m-t-15 waves-effect" ng-click="editProfileForUser(userProfile);" >UPDATE</button>										
									</div>
								</div>
							</form>
						</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-------------- /Div34 ---------------------->
		<!-------------- Div35 ---------------------->
		<div id="div35" class="row clearfix" style="display: none; margin: 0px;">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<div class="card">
					<div class="header">
						<h2>Balance Transfer Report</h2>
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
												<div class="fo 	rm-line">
													<input type="text" class="form-control" id="dp17" placeholder="Enter Start Date" ng-model="viewBalanceTransferReport.startDate" readonly="readonly" />
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
													<input type="text" class="form-control" id="dp18" placeholder="Enter Start Date" ng-model="viewBalanceTransferReport.endDate" readonly="readonly" />
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
										<button type="button" class="btn btn-primary waves-effect" ng-click = "getBalanceTransferReport(viewBalanceTransferReport);">Update</button>
										<button type="button" class="btn btn-primary waves-effect" onclick="showDiv('div44')">Back</button>
									</div>
								</div>
							</div>
							<div class="row clearfix">

								<div class="row clearfix" style="margin-top: 2%">
									<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 table-responsive" ng-if="viewBalanceTransferDetails.length > 0">
										<table id="mainTable" class="table table-striped">
											<thead>
												<tr style="font-size: 13px; font-weight: bold;">
													<th class="success text-center">Sl. No</th>
													<th class="success text-center">Transfer To</th>
													<th class="success text-center"> To Op Bal</th>
													<th class="success text-center">Amount</th>																							
													<th class="success text-center">To Cl Bal</th>													
													<th class="success text-center">remarks</th>
													
												</tr>
											</thead>
											<tbody>
												<tr ng-repeat="item in viewBalanceTransferDetails">
													<td style="font-size: 15px; text-align: center;">{{$index + 1}}</td>
													<td style="font-size: 15px; text-align: center;">{{item.to_name}}</td>
													<td style="font-size: 15px; text-align: center;">{{item.toOpenBal}}</td>
													<td style="font-size: 15px; text-align: center;">{{item.transferAmount}}</td>
													<td style="font-size: 12px; text-align: center;">{{item.toclosingBal}}</td>													
													<td style="font-size: 12px; text-align: center;">{{item.remarks}}</td>													
																								
												</tr>
												<tr>
													<td style="font-size: 15px; text-align: center;"></td>
													<td style="font-size: 15px; text-align: center;"></td>
													<td style="font-size: 15px; text-align: center;"></td>
													<td style="font-size: 15px; text-align: center;">{{viewBalanceTransferDetails | totalCommission:'transferAmount'}}</td>
													<td style="font-size: 12px; text-align: center;"></td>													
													<td style="font-size: 12px; text-align: center;"></td>													
																								
												</tr>
											</tbody>
										</table>
									</div>
									<div data-ng-show="viewBalanceTransferPagination" style="margin-left: 20px;">
										<button ng-disabled="currentPage == 0" ng-click="currentPage=currentPage-1">Previous</button>
										{{currentPage+1}}/{{numberOfPages()}}
										<button ng-disabled="currentPage >= data.length/pageSize - 1" ng-click="currentPage=currentPage+1">Next</button>
										<!-- <button type="button" class="btn btn-primary" ng-click="getViewUserExcel(viewUserDetails);" ng-if="viewUserDetails.length > 0">Export</button> -->
									</div>
									<div class="table table-striped" ng-if="viewBalanceTransferDetails.length <= 0">
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
						</form>
					</div>

				</div>
			</div>
		</div>
		<!-------------- /Div35 ---------------------->
		
		<!-------------- Div36 ---------------------->
		<div id="div36" class="row clearfix" style="display: none; margin: 0px;">
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
													<input type="text" class="form-control" id="dp15" ng-model="viewInsuranceRequest.startDate" readonly="readonly" />
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
													<input type="text" class="form-control" id="dp16" ng-model="viewInsuranceRequest.endDate" readonly="readonly" />
												</div>
											</div>
										</div>

									</div>
								</div>
								<div class="col-md-4">
									<div class="col-lg-offset-2 col-md-offset-2 col-sm-offset-2 col-xs-offset-2">
									<button type="button" class="btn btn-primary m-t-15 waves-effect" ng-click = "viewInsuranceReport(viewInsuranceRequest);">Submit</button>
									<button type="button" class="btn btn-primary m-t-15 waves-effect" onclick="showDiv('div44')">Back</button>
								</div>
								</div>
								</div>
						</form>
							
						<div class="row clearfix" style="margin-top: 2%">
							<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 table-responsive"  ng-if="viewInsuranceDetails.length > 0">
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
											<%if(user.getRoleId() == 1){ %>												
											<th class="success text-center">Action</th>
											<%} %>
										</tr>
									</thead>
									<tbody>
										<tr ng-repeat="item in viewInsuranceDetails">
											<td style="font-size: 15px; text-align: center;">{{$index + 1}}</td>
											<td style="font-size: 15px; text-align: center;">{{item.name}}({{item.mobile}})</td>											
											<td style="font-size: 15px; text-align: center;">{{item.serviceProvider}}</td>
											<td style="font-size: 15px; text-align: center;">{{item.policyNumber}}</td>
											<td style="font-size: 15px; text-align: center;">{{item.policyHolderName}}</td>
											<td style="font-size: 15px; text-align: center;">{{item.custMobile}}	</td>
											<td style="font-size: 15px; text-align: center;">{{item.dob}}</td>
											<td style="font-size: 15px; text-align: center;">{{item.amount}}</td>
											<td style="font-size: 15px; text-align: center;">{{item.status}}</td>
											<td style="font-size: 15px; text-align: center;">{{item.submitDate}} {{item.submitTime}}</td>
											<%if(user.getRoleId() == 1){ %>		
											<td style="font-size: 15px; text-align: center;">
												<span ng-if = "item.status == 'PENDING'">
													<button class="edit-button" ng-click = "updateInsuranceReport(item,'SUCCESS');">Success</button>
													<button class="delete-button" style="width: 95%;" ng-click = "updateInsuranceReport(item,'FAILED');">Failed</button>
												</span>
											</td>
											<%} %>
										</tr>
									</tbody>
								</table>
							</div>
							
							<div ng-if="viewInsuranceDetails.length > 0">
								<button ng-disabled="currentPage == 0" ng-click="currentPage=currentPage-1">Previous</button>
								{{currentPage+1}}/{{numberOfPages()}}
								<button ng-disabled="currentPage >= data.length/pageSize - 1" ng-click="currentPage=currentPage+1">Next</button>
								<button type="button" class="btn btn-primary" ng-click="getExcelReport(viewUtilityDetails,'InsuranceReport');" ng-if="viewUtilityDetails.length > 0">Export</button>
							</div>
							<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 table-responsive" ng-if="viewInsuranceDetails.length <= 0">
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

		<!-------------- /Div36 ---------------------->
		
		<!-- ========================================Added on 12/11/17============================================ -->
		<!-------------- div37 ---------------------->
		
		<div id="div37" class="row clearfix" style="display: none; margin: 0px;">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<div class="card">
					<div class="header">
						<h2>Add Operator</h2>
					</div>
					<div class="body">
						<div class="body">
							<form class="form-horizontal" >
							<div class="row clearfix">
								<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 col-lg-offset-2 col-md-offset-2 " style="margin-left: 12%;">							
									<div
										class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label ">
										<label for="email_address_2">Service</label>
									</div>
									<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
										<select class="form-control"  ng-model="operatorADD.service">
											<option value="">-- Please select User Type--</option>
											<option value="Mobile Recharge">Mobile Recharge</option>
											<option value="DTH Recharge">DTH Recharge</option>
											<option value="DATACARD Recharge">DATACARD Recharge</option>
											<option value="POSTPAID">POSTPAID</option>
											<option value="INSURANCE">INSURANCE</option>
											<option value="ELECTRIC">ELECTRIC</option>
											<option value="GAS">GAS</option>
											<option value="PAN">PAN</option>
										</select>
									</div>
									</div>
								</div>
								<div class="row clearfix">
								<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 col-lg-offset-2 col-md-offset-2" style="margin-left: 12%;">
									<div
										class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
										<label for="password_2">Select Api</label>
									</div>
									<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
										<div class="form-group form-float" style="margin-left: 1.5%">
											<div class="form-line">
											<select class="form-control"  ng-model="operatorADD.apiname">
											
											<option value="{{apiname.apiId}}" ng-repeat="apiname in api">{{apiname.apiName}}</option>
										</select>
												
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="row clearfix">
								<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 col-lg-offset-2 col-md-offset-2" style="margin-left: 12%;">
									<div
										class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
										<label for="password_2">OPERATOR</label>
									</div>
									<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
										<div class="form-group form-float" style="margin-left: 1.5%">
											<div class="form-line">
												<input type="text" ng-model = "operatorADD.operatorname" class="form-control" placeholder="Enter Operator Name">
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="row clearfix">
								<div class="col-lg-offset-6 col-md-offset-6 col-sm-offset-6 col-xs-offset-6">
									<button type="button" class="btn btn-primary m-t-15 waves-effect" ng-click="addOperator(operatorADD)" >Submit</button>
								</div>
							</div>
							</form>	
						</div>
					</div>
				</div>
			</div>
		</div>

		<!-------------- /div37 ---------------------->
		
		<!-------------- div38 ---------------------->
		<div id="div38" class="row clearfix" style="display: none; margin: 0px;">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<div class="card">
					<div class="header">
						<h2>PAN Report</h2>
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
													<input type="text" ng-model="panReport.startDate" class="form-control" id="dp31" placeholder="Enter Start Date" readonly="readonly" />
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
													<input type="text" class="form-control" ng-model="panReport.endDate" id="dp32" placeholder="Enter End Date" readonly="readonly" />
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
										<button type="button" class="btn btn-primary waves-effect"  ng-click="getpanReport(panReport)">Agent Report</button>
										<button type="button" class="btn btn-primary waves-effect"  ng-click="getpanCouponReport(panReport)">Coupon Report</button>
									</div>
								</div>
							</div>
						</form>
						
						<div class="row clearfix visibility" id="sdiv19" style="margin-top: 2%">	
							<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 table-responsive" ng-if = "panReports.length > 0">
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
											<th class="success text-center">Action</th>
											
										</tr>
									</thead>
									<tbody>
										<tr ng-repeat="report in panReports | startFrom:currentPage*pageSize | limitTo:pageSize">
											<td style="font-size: 12px;">{{$index + 1}}</td>
											<td style="font-size: 12px;">{{report.usernm}}<br> ({{report.usermbl}})</td>
											<td style="font-size: 12px;">{{report.psaname}}</td>
											<td style="font-size: 12px;">{{report.location}}</td>
											<td style="font-size: 12px;">{{report.phone1}}</td>
											<td style="font-size: 12px;">{{report.emailid}}</td>
											<td style="font-size: 12px;">{{report.pan}}</td>
											<td style="font-size: 12px;">{{report.dob}}</td>
											<td style="font-size: 12px;">{{report.aadhaar}}</td>
											<td style="font-size: 12px;">{{report.date}} <br>{{report.time}}</td>
											<td style="font-size: 12px;">{{report.panuser_id}}</td>
											<td style="font-size: 12px;">{{report.status}}</td>
											<td style="font-size: 12px;">{{report.requestId}}</td>
											<td class="in" style="font-size: 12px;">
												<button class="edit-button" data-toggle="modal" data-target="#modalStatusRemark" ng-click="agentstatusmodal(report,'SUCCESS');" ng-if = "report.status == 'PENDING' || report.status == 'FAILED'">Accept</button>
												<button class="edit-button" data-toggle="modal" data-target="#modalStatusRemark" ng-click="agentstatusmodal(report,'FAILED');" ng-if = "report.status == 'PENDING' || report.status == 'SUCCESS'">Decline</button>
											</td>
										</tr>
									</tbody>
									</table>
							</div>
						 	<div data-ng-show="viewRechargeReportsPagination">
								<button ng-disabled="currentPage == 0" ng-click="currentPage=currentPage-1">Previous</button>
								{{currentPage+1}}/{{numberOfPages()}}
								<button ng-disabled="currentPage >= data.length/pageSize - 1" ng-click="currentPage=currentPage+1">Next</button>
							</div> 
							<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 table-responsive" ng-if="panReports.length <= 0">
								<table cellspacing="1" class="table table-striped table-bordered table-hover">
									<tbody>
										<tr>
											<th align="center" style="color: red;">No Records Found</th>
										</tr>
									</tbody>
								</table>
							</div>
					</div>
					
					<div class="row clearfix visibility" id="sdiv20">
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 table-responsive"  ng-if = "panCouponReports.length > 0">
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
										<th class="success text-center">Action</th>	
											
										</tr>
									</thead>
									<tbody>
										<tr ng-repeat="report in panCouponReports | startFrom:currentPage*pageSize | limitTo:pageSize">
											<td style="font-size: 12px;">{{$index + 1}}</td>
											<td style="font-size: 12px;">{{report.usernm}}<br> ({{report.usermbl}})</td>
											<td style="font-size: 12px;">{{report.panuser_id}}</td>
											<td style="font-size: 12px;">{{report.totalCoupon}}</td>
											<td style="font-size: 12px;">{{report.date}} <br>{{report.time}}</td>
											<td style="font-size: 12px;">{{report.status}}</td>
											<td style="font-size: 12px;">{{report.requestId}}</td>
											<td class="in" style="font-size: 12px;">
												<button class="edit-button" data-toggle="modal" data-target="#modalcouponStatusRemark" ng-click="couponstatusmodal(report,'SUCCESS');" ng-if = "report.status == 'PENDING' || report.status == 'FAILED'">Accept</button>
												<button class="edit-button" data-toggle="modal" data-target="#modalcouponStatusRemark" ng-click="couponstatusmodal(report,'FAILED');" ng-if = "report.status == 'PENDING' || report.status == 'SUCCESS'">Decline</button>
											</td>
										</tr>
									</tbody>
									</table>
						</div>
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 table-responsive m-l-20" >
						<div data-ng-show="viewRechargeReportsPagination">
								<button ng-disabled="currentPage == 0" ng-click="currentPage=currentPage-1">Previous</button>
								{{currentPage+1}}/{{numberOfPages()}}
								<button ng-disabled="currentPage >= data.length/pageSize - 1" ng-click="currentPage=currentPage+1">Next</button>
							</div>
						</div> 
								<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 table-responsive" ng-if="panCouponReports.length <= 0">
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

		<!-------------- /div38 ---------------------->
		<!-- ------------------------------------- modalStatusRemark Modal-------------------------------------- -->
	
		<!-- Modal -->
		<div id="modalStatusRemark" class="modal fade" role="dialog" tabindex="-1" aria-labelledby="modalLabel" aria-hidden="true">
		  <div class="modal-dialog">
		
		     <!-- Modal content-->
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal">&times;</button>
		        <h4 class="modal-title">Enter Details</h4>
		      </div>
		      <div class="modal-body">
		       	<div class="row clearfix">
					<div
						class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label text-center">
						<label for="email_address_2"><h3>Are You Sure:</h3></label>
					</div>
					
					<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8  text-center">
						<input type="submit" class="btn btn-info col-lg-4 col-md-4 col-sm-4 col-xs-4 m-l-10 m-r-10" data-dismiss="modal" ng-click = "agentstatusupdate(popstatus);" value="OK">
						<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2"></div>
						<input type="submit" class="btn btn-info col-lg-4 col-md-4 col-sm-4 col-xs-4 m-l-10" data-dismiss="modal" value="CANCEL">
					</div>
					
				</div>
				<div class="row clearfix">
				<input type="hidden" ng-model="popstatus.status" class="form-control" placeholder="Enter status">
				</div>
		      </div>
		      <div class="modal-footer">
		        
		      </div>
		    </div>
		
		  </div>
		</div>
	
	<!-- ------------------------------------- /modalStatusRemark Success -------------------------------------- -->
		<!-- ------------------------------------- modalcouponStatusRemark Modal-------------------------------------- -->
	
		<!-- Modal -->
		<div id="modalcouponStatusRemark" class="modal fade" role="dialog" tabindex="-1" aria-labelledby="modalLabel" aria-hidden="true">
		  <div class="modal-dialog">
		
		     <!-- Modal content-->
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal">&times;</button>
		        <h4 class="modal-title">Enter Details</h4>
		      </div>
		      <div class="modal-body">
		       	<div class="row clearfix">
					<div
						class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label text-center">
						<label for="email_address_2">Remark:</label>
					</div>
					<div class="col-lg-6 col-md-6 col-sm-8 col-xs-8">
						<div class="form-group form-float">
							<div class="form-line">
								<input type="text" class="form-control text-center" ng-model="popcouponstatus.remark"
									 placeholder="Enter remark">
							</div>
						</div>
					</div>
				</div>
				<div class="row clearfix">
					<input type="hidden" ng-model="popcouponstatus.requestNo" class="form-control" placeholder="Enter requestId">
				<input type="hidden" ng-model="popcouponstatus.status" class="form-control" placeholder="Enter status">
				</div>
				<div class="row clearfix">
					<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2  text-center"></div>
					<div class="col-lg-10 col-md-10 col-sm-10 col-xs-10 m-l-20 text-center">
						<input type="submit" class="btn btn-info" data-dismiss="modal" ng-click = "couponstatusupdate(popcouponstatus);">
					</div>
				</div>
		      </div>
		      <div class="modal-footer">
		        
		      </div>
		    </div>
		
		  </div>
		</div>
	
	<!-- ------------------------------------- /modalStatusRemark Success -------------------------------------- -->
		<!-------------- div39 ---------------------->
	<div id="div39" class="row clearfix" style="display: none; margin: 0px;">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<div class="card">
					<div class="header">
						<h2>View Non Package User</h2>
					</div>
					<div class="body">
						<div class="body">
							<form class="form-horizontal">
								<!-- ---VIEW USER---- -->
								<div class="row clearfix m-t-20"  ng-if = "viewUserDetails.length > 0">
								<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 hscroll">
									<table class="table table-striped">
										<thead>
											<tr>
												<th class="success text-center">Sl. No</th>
												<th class="success text-center">Name</th>
												<th class="success text-center">Mobile</th>
												<th class="success text-center">Email</th>
												<th class="success text-center">User Type</th>
												<th class="success text-center">Address</th>
												<%
													if (user.getRoleId() == 1) {
												%>
												<th class="success text-center">Password</th>
												<%
													}
												%> 
												<th class="success text-center">Balance</th>
												<th class="success text-center">Upline Id</th>
												<th class="success text-center">Date and Time</th>
												<th class="success text-center">Status</th>
												<%
													if (user.getRoleId() == 1) {
												%>
												<th class="success text-center">Actions</th>
												<%
													}
												%>
											</tr>																							
										</thead>
										<tbody>
											<tr	ng-repeat="item in viewUserDetails | startFrom:currentPage*pageSize | limitTo:pageSize">
												<td style="font-size: 12px;">{{(pageSize*currentPage+1)+$index}}</td>
												<td style="font-size: 12px;">{{item.name}}</td>
												<td style="font-size: 12px;">{{item.mobile}}</td>
												<td style="font-size: 12px;">{{item.email}}</td>
												<td style="font-size: 12px;">
													<span ng-if = "item.roleId == 2">White Label</span>
													<span ng-if = "item.roleId == 3">SUPER DISTRIBUTOR</span>
													<span ng-if = "item.roleId == 4">DISTRIBUTOR</span>
													<span ng-if = "item.roleId == 5">RETAILER</span>
													<span ng-if = "item.roleId == 100">API USER</span>
												</td>
												<td style="font-size: 12px;">{{item.address}},&nbsp;{{item.city}}-{{item.pinCode}},&nbsp;{{item.state}}</td>
												<%
													if (user.getRoleId() == 1) {
												%>
												<td style="font-size: 12px;">{{item.password}}</td>
												<%
													}
												%> 
												<td style="font-size: 12px;">{{item.balance}}</td>
												<td style="font-size: 12px;">{{item.uplineFirmName}}<br>({{item.uplineMobile}})</td>
												<td style="font-size: 12px;">{{item.createdDate}}&nbsp;&nbsp;{{item.createdTime}}</td>
												<td style="font-size: 12px;" >
												<a ng-if = "item.status == 0" class = "success" ng-click = "changeStatus(item);">Active</a>
												<a ng-if = "item.status == 1" class = "danger" ng-click = "changeStatus(item);">Inactive</a>												
												<%
													if (user.getRoleId() == 1) {
												%>
												<td>												
												<table >
													<tr>
														 <td><a ng-click = "signInByAdmin(item);"><img class="" title="Sign In" alt="" src="assets/images/sign-in.png" style="width: 24px;"></a></td>
														<td><a href="#" data-toggle="modal" data-target="#squarespaceModal" ng-click="getAddBalanceModal(item);"><img class="" title="Add Balance"  alt=""	src="assets/images/add-balance.png" style="width: 24px;"></a></td>
														<td><a ng-click="sendPassword(item);"><img	class="" title="Send Password"  alt="" src="assets/images/send-pass.png" style="width: 24px;"></a></td>
														<td><a ng-click="deleteUser(item);"><img	class="" title="Delete User"  alt="" src="assets/images/delete-user.png" style="width: 24px;"></a></td>
													</tr>
												</table>
												</td>
												<%
													}
												%>
											</tr>
										</tbody>
									</table>
								</div>
								<div data-ng-show="viewUserPagination">
									<button ng-disabled="currentPage == 0" ng-click="currentPage=currentPage-1">Previous</button>
									{{currentPage+1}}/{{numberOfPages()}}
									<button ng-disabled="currentPage >= data.length/pageSize - 1" ng-click="currentPage=currentPage+1">Next</button>
									<button type="button" class="btn btn-primary" ng-click="getViewUserExcel(viewUserDetails);" ng-if = "viewUserDetails.length > 0">Export</button>
								</div>
								<div class="table table-striped" ng-if="viewUserDetails.length <= 0">
									<table cellspacing="1" class="table table-striped table-bordered table-hover">
										<tbody>
											<tr>
												<th align="center" style="color: red;">No Records Found</th>
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

		<!-------------- /div39 ---------------------->
		
		<!-------------- div40 ---------------------->
		<div id="div40" class="row clearfix" style="display: none; margin: 0px;">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<div class="card">
					<div class="header">
						<h2>View White Label</h2>
					</div>
					<div class="body">
						<div class="body">
							<form class="form-horizontal">							
								<!-- ---VIEW USER---- -->
								<div class="row clearfix m-t-20"  ng-if = "viewResellerDetails.length > 0">
								<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
									<table class="table table-striped">
										<thead>
											<tr>
												<th class="success text-center">Sl. No</th>
												<th class="success text-center">Name</th>
												<th class="success text-center">Mobile</th>
												<th class="success text-center">Email</th>
												<th class="success text-center">User Type</th>
												<th class="success text-center">Address</th>
												<%
													if (user.getRoleId() == 1) {
												%>
												<th class="success text-center">Password</th>
												<%
													}
												%>
												<th class="success text-center">Balance</th>
												<th class="success text-center">Upline Id</th>
												<th class="success text-center">Date and Time</th>
												<th class="success text-center">Status</th>
												<%
													if (user.getRoleId() == 1) {
												%>
												<th class="success text-center">Actions</th>
												<%
													}
												%>
											</tr>																							
										</thead>
										<tbody>
											<tr	ng-repeat="item in viewResellerDetails | startFrom:currentPage*pageSize | limitTo:pageSize">
												<td style="font-size: 12px;">{{$index + 1}}</td>
												<td style="font-size: 12px;">{{item.name}}</td>
												<td style="font-size: 12px;">{{item.mobile}}</td>
												<td style="font-size: 12px;">{{item.email}}</td>
												<td style="font-size: 12px;">
													<span>WHITE LABEL</span>													
												</td>
												<td style="font-size: 12px;">{{item.address}},&nbsp;{{item.city}}-{{item.pinCode}},&nbsp;{{item.state}}</td>
												<%
													if (user.getRoleId() == 1) {
												%>
												<td style="font-size: 12px;">{{item.password}}</td>
												<%
													}
												%>
												<td style="font-size: 12px;">{{item.balance}}</td>
												<td style="font-size: 12px;">{{item.uplineId}}</td>
												<td style="font-size: 12px;">{{item.createdDate}}&nbsp;&nbsp;{{item.createdTime}}</td>
												<td style="font-size: 12px;" >
												<a ng-if = "item.status == 0" class = "success" ng-click = "changeStatus(item);">Active</a>
												<a ng-if = "item.status == 1" class = "danger" ng-click = "changeStatus(item);">Inactive</a>												
												<%
													if (user.getRoleId() == 1) {
												%>
												<td>												
												<table >
													<tr>
														<td><a ng-click = "signInByAdmin(item);"><img class="" title="Sign In" alt="" src="assets/images/sign-in.png" style="width: 24px;"></a></td>
														<td><a href="#" data-toggle="modal" data-target="#squarespaceModal" ng-click="getAddBalanceModal(item);"><img class="" title="Add Balance"  alt=""	src="assets/images/add-balance.png" style="width: 24px;"></a></td>
														<td><a ng-click="sendPassword(item);"><img	class="" title="Send Password"  alt="" src="assets/images/send-pass.png" style="width: 24px;"></a></td>
														<td><a ng-click="deleteUser(item);"><img	class="" title="Delete User"  alt="" src="assets/images/delete-user.png" style="width: 24px;"></a></td>
													</tr>
												</table>
												</td>
												<%
													}
												%>
											</tr>
										</tbody>
									</table>
								</div>
								<div data-ng-show="viewResellerPagination">
									<button ng-disabled="currentPage == 0" ng-click="currentPage=currentPage-1">Previous</button>
									{{currentPage+1}}/{{numberOfPages()}}
									<button ng-disabled="currentPage >= data.length/pageSize - 1" ng-click="currentPage=currentPage+1">Next</button>
									<button type="button" class="btn btn-primary" ng-click="getViewUserExcel(viewUserDetails);" ng-if = "viewResellerDetails.length > 0">Export</button>
								</div>
								<div class="table table-striped" ng-if="viewResellerDetails.length <= 0">
									<table cellspacing="1" class="table table-striped table-bordered table-hover">
										<tbody>
											<tr>
												<th align="center" style="color: red;">No Records Found</th>
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
		<!-- ---------------------- /div40 -------------------------- -->
		
		<!-------------- Div41 ---------------------->
		<div id="div41" class="row clearfix" style="display: none; margin: 0px;">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<div class="card">
					<div class="header">
						<h2>Add White Label</h2>
					</div>
					<div class="body">
						<div class="body">
							<form class="form-horizontal" ng-submit = "addResellerDetails(addReseller);">
								<div class="row clearfix">
									<div class="col-md-6 col-lg-6 col-sm-12 col-xs-12">
										<div
											class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
											<label for="email_address_2">Name</label>
										</div>
										<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
											<div class="form-group form-float">
												<div class="form-line">
													<input type="text" class="form-control" ng-model = "addReseller.name" placeholder="Enter your Name">
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
											<div class="form-group form-float">
												<div class="form-line">
													<input type="text" class="form-control" ng-model = "addReseller.firmName" placeholder="Enter your Firm Name">
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
											<div class="form-group form-float">
												<div class="form-line">
													<input type="text" ng-model = "addReseller.mobile" ng-change = "checkUniqueUser(addReseller.mobile);" class="form-control" placeholder="Enter your Mobile Number" ng-keypress = "filterValue($event);" maxlength="10">
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
											<div class="form-group form-float">
												<div class="form-line">
													<input type="text" class="form-control" ng-model = "addReseller.email" ng-change = "checkUniqueUser(addReseller.email);" placeholder="Enter your E-Mali">
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
											<div class="form-group form-float">
												<div class="form-line">
													<input type="text" ng-model = "addReseller.address" class="form-control" placeholder="Enter your Address">
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
											<div class="form-group form-float">
												<div class="form-line">
													<input type="text" ng-model = "addReseller.pinCode" class="form-control" placeholder="Enter your PIN Code" ng-keypress = "filterValue($event);">
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
											<div class="form-group form-float">
												<div class="ui-widget form-line">
													<input type="text" id="" class="tags form-control" ng-model="addReseller.state" placeholder="Enter State">
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
											<div class="form-group form-float">
												<div class="form-line">
													<input type="text" ng-model = "addReseller.city" class="form-control" placeholder="Enter your City">
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="row clearfix">
									<div class="col-md-6 col-lg-6 col-sm-12 col-xs-12">
										<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
											<label for="email_address_2">Logo</label>
										</div>
										<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
											<div class="form-group form-float">
												<input type="file" file-model="myFile"/>
											</div>
										</div>
									</div>
									<div class="col-md-6 col-lg-6 col-sm-12 col-xs-12">
										<div
											class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
											<label for="password_2">Powered by</label>
										</div>
										<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
											<div class="form-group form-float">
												<div class="form-line">
													<input type="text" ng-model = "addReseller.poweredBy" class="form-control" placeholder="Enter poweredBy">
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="row clearfix">
									<div class="col-md-6 col-lg-6 col-sm-12 col-xs-12">
										<div
											class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
											<label for="password_2">powered by Link</label>
										</div>
										<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
											<div class="form-group form-float">
												<div class="ui-widget form-line">
													<input type="text" class="tags form-control" ng-model="addReseller.poweredByLink" placeholder="Enter poweredByLink">
												</div>
											</div>
										</div>
									</div>
									<div class="col-md-6 col-lg-6 col-sm-12 col-xs-12">
										<div
											class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
											<label for="password_2">Page title</label>
										</div>
										<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
											<div class="form-group form-float">
												<div class="form-line">
													<input type="text" ng-model = "addReseller.pageTitle" class="form-control" placeholder="Enter pageTitle">
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="row clearfix">
									<div class="col-md-6 col-lg-6 col-sm-12 col-xs-12">
										<div
											class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
											<label for="password_2">Domain</label>
										</div>
										<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
											<div class="form-group form-float">
												<div class="ui-widget form-line">
													<input type="text" class="tags form-control" ng-model="addReseller.domain" placeholder="Enter Domain Name">
												</div>
											</div>
										</div>
									</div>
									</div>
								<div class="row clearfix">
									<div class="col-lg-offset-2 col-md-offset-2 col-sm-offset-4 col-xs-offset-5">
										<button type="submit" class="btn btn-primary m-t-15 waves-effect">ADD White Label</button>										
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-------------- /Div41 ---------------------->
		
		<!-------------- Div42 ---------------------->
		<div id="div42" class="row clearfix" style="display: none; margin: 0px;">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<div class="card">
					<div class="header">
						<h2>White Label Charges / Markup</h2>
					</div>
					<div class="body">
						<form class="form-horizontal">
							<div class="row clearfix">
								<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
									<label for="email_address_2">Select White Label </label>
								</div>
								<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
									<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
										<select class="form-control"  ng-model="resCharge.userType" ng-change = "getUserByUserType(resCharge.userType,'getUserForresChargeDiv','resCharge')">
											<option value="">-- Select --</option>
											<option ng-repeat="userType in userType" ng-if="userType.type == 2" value="{{userType.type}}">{{userType.role}}</option>
										</select>
									</div>
								</div>
							</div>
							<div class="row clearfix">
							<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 col-lg-offset-2 col-md-offset-2 " style="margin-left: 12%;">
								<div ng-bind-html="getUserForresChargeDiv" compile-template></div>
								</div>
							</div>
							<div class="row">
								<div class="col-xs-4 col-sm-4 col-md-2 col-lg-2">
									<div class="widget2 widget3">
										<a onClick="sshowDiv('sdiv7')" href="#" ng-click = "getResellerlCharge(resCharge)">
											<div class="stats-left ">
												<i class="fa fa-mobile col-black"></i>
												<h5>Recharge / Utility</h5>
											</div>
											<div class="clearfix"></div>
										</a>
									</div>
								</div>
								<div class="col-xs-4 col-sm-4 col-md-2 col-lg-2">
									<div class="widget2 widget3">
										<a onClick="sshowDiv('sdiv8')" href="javascript:;" id="">
											<div class="stats-left ">
												<i class="fa  fa-plane col-black"></i>
												<h5>Flight Booking</h5>
											</div>
											<div class="clearfix"></div>
										</a>
									</div>
								</div>
								<div class="col-xs-4 col-sm-4 col-md-2 col-lg-2">
									<div class="widget2 widget3">
										<a onClick="sshowDiv('sdiv9')" href="javascript:;" id="">
											<div class="stats-left ">
												<i class="fa fa-bus col-black"></i>
												<h5>Bus Booking</h5>
											</div>
											<div class="clearfix"></div>
										</a>
									</div>
								</div>
								<div class="col-xs-4 col-sm-4 col-md-2 col-lg-2">
									<div class="widget2 widget3">
										<a onClick="sshowDiv('sdiv10')" href="javascript:;" id="">
											<div class="stats-left ">
												<i class="fa fa-bed col-black"></i>
												<h5>Hotel Booking</h5>
											</div>
											<div class="clearfix"></div>
										</a>
									</div>
								</div>
								<div class="col-xs-4 col-sm-4 col-md-2 col-lg-2">
									<div class="widget2 widget3">
										<a onClick="sshowDiv('sdiv11')" href="javascript:;" id="">
											<div class="stats-left ">
												<i class="fa  fa-train col-black"></i>
												<h5>Train Ticket Booking</h5>
											</div>
											<div class="clearfix"></div>
										</a>
									</div>
								</div>
								<div class="col-xs-4 col-sm-4 col-md-2 col-lg-2">
									<div class="widget2 widget3">
										<a onClick="sshowDiv('sdiv12')" href="javascript:;" id="">
											<div class="stats-left ">
												<i class="fa  fa-podcast col-black"></i>
												<h5>DTH Connection</h5>
											</div>
											<div class="clearfix"></div>
										</a>
									</div>
								</div>
							</div>
						</form>
						<div class="body" id="sdiv7" style="display: none;">
							<div class="row clearfix">
								<div class="col-md-12">
									<!--------- Reseller Charge Table ------------>
									<div class="row clearfix m-t-20"  ng-if = "resellerCharge.length > 0">
										<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 discTable">
											<table id="mainTable" class="table table-striped">
												<thead>
													<tr>
														<th class="success text-center">Operator Names</th>
														<th class="success text-center">Commission(in Rupees)</th>
														<th class="success text-center">Commission(in %)</th>
														<th class="success text-center">Action</th>
													</tr>											
												</thead>
												<tbody>
													<tr ng-repeat="item in resellerCharge">
														<td>{{item.serviceProvider}}</td>
														<td><input type="text" name="r_comm" ng-model="item.rupees"	class="form-control text-center"></td>
														<td><input type="text" name="r_comm" ng-model="item.percentage" class="form-control text-center"></td>
														<td>
															<select ng-model="item.flag" class="form-control">
																<option value="0">RUPEES</option>
																<option value="1">PERCENTAGE</option>
															</select>
														</td>
													</tr>
												</tbody>
											</table>
										</div>
										<div class="row clearfix" ng-if = "resellerCharge.length > 0">
											<div class="col-lg-offset-6 col-md-offset-6 col-sm-offset-6 col-xs-offset-6">
												<button type="button" class="btn btn-primary m-t-15 waves-effect" ng-click="setIndividualCharge(resellerCharge);">Update</button>
											</div>
										</div>
									</div>
									
						<!--------- Individual Discount Table ------------>
								</div>
							</div>
						</div>
						<div class="body" id="sdiv8" style="display: none;">
							<div class="row clearfix">
								<div class="col-md-12">
									<div class="jumbotorn">
										This Service will be Coming Soon......
									</div>
								</div>
							</div>
						</div>
						<div class="body" id="sdiv9" style="display: none;">
							<div class="row clearfix">
								<div class="col-md-12">
									<div class="jumbotorn">
										This Service will be Coming Soon......
									</div>
								</div>
							</div>
						</div>
						
						<div class="body" id="sdiv10" style="display: none;">
							<div class="row clearfix">
								<div class="col-md-12">
									<div class="jumbotorn">
										This Service will be Coming Soon......
									</div>
								</div>
							</div>
						</div>
						
						<div class="body" id="sdiv55" style="display: none;">
							<div class="row clearfix">
								<div class="col-md-11">
									<div class="jumbotorn">
										This Service will be Comeing Soon......
									</div>
								</div>
							</div>
						</div>
						
						<div class="body" id="sdiv12" style="display: none;">
							<div class="row clearfix">
								<div class="col-md-12">
									<div class="jumbotorn">
										This Service will be Coming Soon......
									</div>
								</div>
							</div>
						</div>
						
					</div>
				</div>
			</div>
		</div>
		<!-------------- /Div42 ---------------------->
		
		<!-------------- div43 ---------------------->
		<div id="div43" class="row clearfix" style="display: none; margin: 0px;">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<div class="card">
					<div class="header">
						<h2>All Report </h2>
					</div>
					<div class="body">
						<div class="row clearfix">
							<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 form-control-label">
							    <div class="body1">
							    <div class="row">
									<div class="col-xs-4 col-sm-4 col-md-2 col-lg-2">
										<div class="widget2 widget3">
											<a onClick="showDiv('div44')" href="#" >
												<div class="stats-left ">
													<i class="fa fa-mobile col-black"></i>
													<h5>Recharge / Utility Report</h5>
												</div>
												<div class="clearfix"></div>
											</a>
										</div>
									</div>
								<!--<div class="col-xs-4 col-sm-4 col-md-2 col-lg-2">
										<div class="widget2 widget3">
											<a onclick="showDiv('div38')" href="javascript:;" id="">
												<div class="stats-left ">
													<i class="fa  fa-plane col-black"></i>
													<h5>Flight Booking</h5>
												</div>
												<div class="clearfix"></div>
											</a>
										</div>
									</div> -->
									<!-- <div class="col-xs-4 col-sm-4 col-md-2 col-lg-2">
										<div class="widget2 widget3">
											<a onclick="showDiv('div38')" href="javascript:;" id="">
												<div class="stats-left ">
													<i class="fa fa-bus col-black"></i>
													<h5>Bus Booking</h5>
												</div>
												<div class="clearfix"></div>
											</a>
										</div>
									</div> -->
									<!-- <div class="col-xs-4 col-sm-4 col-md-2 col-lg-2">
										<div class="widget2 widget3">
											<a onclick="showDiv('div38')" href="javascript:;" id="">
												<div class="stats-left ">
													<i class="fa fa-bed col-black"></i>
													<h5>Hotel Booking</h5>
												</div>
												<div class="clearfix"></div>
											</a>
										</div>
									</div> -->
								<!-- <div class="col-xs-4 col-sm-4 col-md-2 col-lg-2">
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


									<!-- <div class="col-xs-4 col-sm-4 col-md-2 col-lg-2">
										<div class="widget2 widget3">
											<a onclick="showDiv('div38')" href="javascript:;" id="">
												<div class="stats-left ">
													<i class="fa  fa-podcast col-black"></i>
													<h5>DTH Connection</h5>
												</div>
												<div class="clearfix"></div>
											</a>
										</div>
									</div>
									</div> -->
									<div class="row">
										<div class="col-xs-4 col-sm-4 col-md-2 col-lg-2">
											<div class="widget2 widget3">
												<a onclick="showDiv('div49')" href="javascript:;" id="">
													<div class="stats-left ">
														<i class="fa  fa-money col-black"></i>
														<h5>Earning Report</h5>
													</div>
													<div class="clearfix"> </div>
												</a>
											</div>
										</div>
										<div class="col-xs-4 col-sm-4 col-md-2 col-lg-2">
											<div class="widget2 widget3">
												<a onClick="showDiv('div51')" href="javascript:;" id="">
													<div class="stats-left">
														<i class="fa fa-university"></i>
														<h5>IMPS Report</h5>
													</div>
													<div class="clearfix"></div>
												</a>
											</div>
										</div>
											<div class="col-xs-4 col-sm-4 col-md-2 col-lg-2">
											<div class="widget2 widget3">
												<a onClick="showDiv('div38')" href="javascript:;" id="">
													<div class="stats-left ">
														<i class="fa fa-address-card-o"></i>
														<h5>PAN Report</h5>
													</div>
													<div class="clearfix"></div>
												</a>
											</div>
										</div>
										
										<div class="col-xs-4 col-sm-4 col-md-2 col-lg-2">
											<div class="widget2 widget3">
												<a onClick="showDiv('div105')" href="javascript:;" id="">
													<div class="stats-left ">
														<i class="fa fa-address-card-o"></i>
														<h5>PAN Report</h5>
													</div>
													<div class="clearfix"></div>
												</a>
											</div>
										</div>
										<!-- <div class="col-xs-4 col-sm-4 col-md-2 col-lg-2">
											<div class="widget2 widget3">
												<a onClick="showDiv('div50')" ng-click="fetchnsdlpan();" href="javascript:;" id="">
													<div class="stats-left ">
														<i class="fa fa-address-card-o"></i>
														<h5>NSDL PAN Report</h5>
													</div>
													<div class="clearfix"></div>
												</a>
											</div>
										</div> -->
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
									</div>
								</div> 
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		</div>

		<!-------------- /div43 ---------------------->
		
		<!-------------- div44 ---------------------->
		<div id="div44" class="row clearfix" style="display: none; margin: 0px;">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<div class="card">
					<div class="header">
						<h2>Recharge / Utility Report <span class="pull-right"><a href="#"  class="body" onclick="showDiv('div43')"><i class="material-icons">arrow_back</i></a></span></h2>
					</div>
					<div class="body">
						<div class="row clearfix">
							<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 form-control-label">
							    <div class="body1">
							    <div class="row">
									<div class="col-xs-4 col-sm-4 col-md-2 col-lg-2">
										<div class="widget2 widget3">
											<a onClick="showDiv('div26')" href="javascript:;"  >
												<div class="stats-left ">
													<i class="material-icons">account_balance_wallet</i>
													<h5>Balance Request Report</h5>
												</div>
												<div class="clearfix"></div>
											</a>
										</div>
									</div>
									<div class="col-xs-4 col-sm-4 col-md-2 col-lg-2">
										<div class="widget2 widget3">
											<a onClick="showDiv('div27')" href="javascript:;" >
												<div class="stats-left ">
													<i class="material-icons">swap_vert</i>
													<h5>Revert Balance Report</h5>
												</div>
												<div class="clearfix"></div>
											</a>
										</div>
									</div>
									<div class="col-xs-4 col-sm-4 col-md-2 col-lg-2">
										<div class="widget2 widget3">
											<a onClick="showDiv('div35')" href="javascript:;" >
												<div class="stats-left ">
													<i class="material-icons">transform</i>
													<h5>Balance Transfer Reports</h5>
												</div>
												<div class="clearfix"></div>
											</a>
										</div>
									</div>
									<div class="col-xs-4 col-sm-4 col-md-2 col-lg-2">
										<div class="widget2 widget3">
											<a onClick="showDiv('div28')" href="javascript:;" >
												<div class="stats-left ">
													<i class="material-icons">swap_horiz</i>
													<h5>Transaction Reports</h5>
												</div>
												<div class="clearfix"></div>
											</a>
										</div>
									</div>
									<div class="col-xs-4 col-sm-4 col-md-2 col-lg-2">
										<div class="widget2 widget3">
											<a onClick="showDiv('div29')" href="javascript:;" id="">
												<div class="stats-left ">
													<i class="material-icons">system_update</i>
													<h5>Recharge Report</h5>
												</div>
												<div class="clearfix"></div>
											</a>
										</div>
									</div>
									<div class="col-xs-4 col-sm-4 col-md-2 col-lg-2">
										<div class="widget2 widget3">
											<a onClick="showDiv('div30')" href="javascript:;" id="">
												<div class="stats-left ">
													<i class="material-icons">update</i>
													<h5>Refund History</h5>
												</div>
												<div class="clearfix"></div>
											</a>
										</div>
									</div>
									</div>
									<div class="row">
									<div class="col-xs-4 col-sm-4 col-md-2 col-lg-2">
										<div class="widget2 widget3">
											<a onClick="showDiv('div32')" href="javascript:;" id="">
												<div class="stats-left ">
													<i class="material-icons">lightbulb_outline</i>
													<h5>Utility Report</h5>
												</div>
												<div class="clearfix"></div>
											</a>
										</div>
									</div>
									<div class="col-xs-4 col-sm-4 col-md-2 col-lg-2">
										<div class="widget2 widget3">
											<a onClick="showDiv('div36')" href="javascript:;" id="">
												<div class="stats-left ">
													<i class="material-icons">verified_user</i>
													<h5>Insurance Report</h5>
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

		<!-------------- /div44 ---------------------->
		
		<!-------------- div45 ---------------------->
		 <div id="div45" class="row clearfix" style="display: none; margin: 0px;">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<div class="card">
					<div class="header">
						<h2>Logo Manage</h2>
					</div>
					<div class="body">
						<form class="form-horizontal">
							<div class="row clearfix">
								<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
									<label for="email_address_2">Select Type</label>
								</div>
								<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
									<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
										<select class="form-control" ng-model = "ResellerLogo.userType" ng-change = "getUserByUserType1(ResellerLogo.userType,'getUserForResellerLogoDiv','ResellerLogo')" ng-init = "ResellerLogo.userType = '10'">
											<option value="10">Own Theme</option>
										</select>
									</div>
								</div>
							</div>
							<div class="row clearfix">
								<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 col-lg-offset-2 col-md-offset-2 " style="margin-left: 12%;">
									<div ng-bind-html="getUserForResellerLogoDiv" compile-template></div>
								</div>
							</div>
							<div class="row clearfix">
								<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
									<label for="email_address_2">Logo</label>
								</div>
								<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
									<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8" style="padding-left: 4%">
										<div class="form-group ">
											<input type="file" file-model="resLogo"/>
										</div>
									</div>
								</div>
							</div>
							<div class="row clearfix">
								<div class="col-lg-offset-6 col-md-offset-6 col-sm-offset-6 col-xs-offset-6">								
									<button type="button" class="btn btn-primary m-t-15 waves-effect" ng-click = "setResellerLogo(ResellerLogo);">Submit</button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div> 
		<!-------------- /div45 ---------------------->
		<!-------------- Div46 ---------------------->
		<div id="div46" class="row clearfix" style="display: none; margin: 0px;">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<div class="card">
					<div class="header">
						<h2>Theme Manage</h2>
					</div>
					<div class="body">
						<form class="form-horizontal">
							<div class="row clearfix">
								<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
									<label for="email_address_2">Select Theme For</label>
								</div>
								<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
									<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
										<select class="form-control" ng-model = "colorManage.userType" ng-init="colorManage.userType = '10'" ng-change = "getUserByUserType1(colorManage.userType,'getUserForcolorManageDiv','colorManage')">											
											<option value="10">Own Theme</option>
										</select>
									</div>
								</div>
							</div>
							<div class="row clearfix">
								<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 col-lg-offset-2 col-md-offset-2 " style="margin-left: 12%;">
									<div ng-bind-html="getUserForcolorManageDiv" compile-template></div>
								</div>
							</div>
							<div class="row clearfix">
								<div  class="tab-pane fade in active in active" >
				                    <ul class="demo-choose-skin list-inline">
				                        <li class="theme" ng-click="getThemeName('theme-red')">
				                            <div class="red"></div>
				                            <span>Red</span>
				                        </li>
				                        <li class="theme" ng-click="getThemeName('theme-pink')">
				                            <div class="pink"></div>
				                            <span>Pink</span>
				                        </li>
				                        <li class="theme" ng-click="getThemeName('theme-purple')">
				                            <div class="purple"></div>
				                            <span>Purple</span>
				                        </li>
				                        <li class="theme" ng-click="getThemeName('theme-deep-purple')">
				                            <div class="deep-purple"></div>
				                            <span>Deep Purple</span>
				                        </li>
				                        <li class="theme" ng-click="getThemeName('theme-indigo')">
				                            <div class="indigo"></div>
				                            <span>Indigo</span>
				                        </li>
				                        <li class="theme" ng-click="getThemeName('theme-blue')">
				                            <div class="blue"></div>
				                            <span>Blue</span>
				                        </li>
				                        <li class="theme" ng-click="getThemeName('theme-light-blue')">
				                            <div class="light-blue"></div>
				                            <span>Light Blue</span>
				                        </li>
				                        <li class="theme" ng-click="getThemeName('theme-cyan')">
				                            <div class="cyan"></div>
				                            <span>Cyan</span>
				                        </li>
				                        <li class="theme" ng-click="getThemeName('theme-teal')">
				                            <div class="teal"></div>
				                            <span>Teal</span>
				                        </li>
				                        <li class="theme" ng-click="getThemeName('theme-green')">
				                            <div class="green"></div>
				                            <span>Green</span>
				                        </li>
				                        <li class="theme" ng-click="getThemeName('theme-light-green')">
				                            <div class="light-green"></div>
				                            <span>Light Green</span>
				                        </li>
				                        <li class="theme" ng-click="getThemeName('theme-lime')">
				                            <div class="lime"></div>
				                            <span>Lime</span>
				                        </li>
				                        <li class="theme" ng-click="getThemeName('theme-yellow')">
				                            <div class="yellow"></div>
				                            <span>Yellow</span>
				                        </li>
				                        <li class="theme" ng-click="getThemeName('theme-amber')">
				                            <div class="amber"></div>
				                            <span>Amber</span>
				                        </li>
				                        <li class="theme" ng-click="getThemeName('theme-orange')">
				                            <div class="orange"></div>
				                            <span>Orange</span>
				                        </li>
				                        <li class="theme" ng-click="getThemeName('theme-deep-orange')">
				                            <div class="deep-orange"></div>
				                            <span>Deep Orange</span>
				                        </li>
				                        <li class="theme" ng-click="getThemeName('theme-brown')">
				                            <div class="brown"></div>
				                            <span>Brown</span>
				                        </li>
				                        <li class="theme" ng-click="getThemeName('theme-grey')">
				                            <div class="grey"></div>
				                            <span>Grey</span>
				                        </li>
				                        <li class="theme" ng-click="getThemeName('theme-blue-grey')">
				                            <div class="blue-grey"></div>
				                            <span>Blue Grey</span>
				                        </li>
				                        <li class="theme" ng-click="getThemeName('theme-black')">
				                            <div class="black"></div>
				                            <span>Black</span>
				                        </li>
				                    </ul>
				                </div>
							</div>
							<div class="row clearfix">
								<div class="col-lg-offset-6 col-md-offset-6 col-sm-offset-6 col-xs-offset-6">
									<button type="button" class="btn btn-primary m-t-15 waves-effect" ng-click = "setResellerTheme(colorManage);">Submit</button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
		<!-------------- /Div46 ---------------------->
		
		<!-------------- Div47 ---------------------->
		<div id="div47" class="row clearfix" style="display: none; margin: 0px;">
			
		</div>
		<!-------------- /Div47 ---------------------->
		
		<!-------------- Div48 ---------------------->
		<div id="div48" class="row clearfix" style="display: none; margin: 0px;">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<div class="card">
					<div class="header">
						<h2>DownLoad Files</h2>
					</div>
					<div class="body">
						<form class="form-horizontal">
							<div class="row clearfix">
								<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
									<label for="email_address_2">Select User</label>
								</div>
								<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
									<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
										<!-- <select class="form-control " ng-model="dnfile.userType" ng-change = "getUserByUserType(dnfile.userType,'getUserFordnfileDiv','dnfile')"> -->
										<select class="form-control " ng-model="dnfile.userType" >
											<option value="">-- Please select User Type--</option>
											<option ng-repeat="userType in userType" value="{{userType.type}}">{{userType.role}}</option>
										</select>
									</div>
								</div>
							</div>
							<!-- <div class="row clearfix">
								<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 col-lg-offset-2 col-md-offset-2 " style="margin-left: 12%;">
									<div ng-bind-html="getUserFordnfileDiv" compile-template></div>
								</div>
							</div> -->
							<div class="row clearfix">
								<div class="col-lg-offset-6 col-md-offset-6 col-sm-offset-6 col-xs-offset-6">
									<button type="button" class="btn btn-primary m-t-15 waves-effect" ng-click = "setResellerTheme(colorManage);">Submit</button>
								</div>
							</div>	
						</form>
					</div>
				</div>
			</div>
		</div>
		<!-------------- /Div48 ---------------------->
		
		<!-------------- div49 ---------------------->
		<div id="div49" class="row clearfix" style="display: none; margin: 0px;">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<div class="card">
					<div class="header">
						<h2>Earning Report</h2>
					</div>
					<div class="body">
						<form class="form-horizontal">
							<div class="row clearfix">
								<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
									<label for="email_address_2">Select User</label>
								</div>
								<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
									<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">									
										<select class="form-control " ng-model="earningReport.userType" ng-init = "earningReport.userType = '0'" >
											<option value="0">All</option>
											<%if(user.getRoleId() == 2){ %>
											<option value="10">Own Earning</option>
											<option ng-repeat="userType in userType" ng-if = "userType.type > userDetails.roleId && userType.type < 100" value="{{userType.type}}">{{userType.role}}</option>
											<%} else { %>
											<option ng-repeat="userType in userType" ng-if = "userType.type > userDetails.roleId" value="{{userType.type}}">{{userType.role}}</option>
											<%} %>
										</select>
									</div>
								</div>
							</div>	
							<div class="row clearfix">
								<div class="col-lg-5 col-md-5 col-sm-4 col-xs-4 form-control-label">
									<label for="email_address_2">Start Date</label>
								</div>
								<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
									<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 m-l-15">
										<div class="form-group">
											<div class="form-group form-float">
												<div class="form-line">
													<input type="text" class="form-control" id="dp19" placeholder="Enter Start Date" ng-model="earningReport.startDate" readonly="readonly" />
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
													<input type="text" class="form-control" id="dp20" placeholder="Enter Start Date" ng-model="earningReport.endDate" readonly="readonly" />
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>					
							<div class="row clearfix">
								<div class="col-lg-offset-6 col-md-offset-6 col-sm-offset-6 col-xs-offset-6">
									<button type="button" class="btn btn-primary m-t-15 waves-effect" ng-click = "getEarningReport(earningReport);">Submit</button>
								</div>
							</div>	
						</form>
						
						<div class="row clearfix" style="margin-top: 2%">
							<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 table-responsive" ng-if = "earningReportDetails.length > 0">
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
										<tr	ng-repeat="item in earningReportDetails | startFrom:currentPage*pageSize | limitTo:pageSize">
											<td>{{$index + 1}}</td>
											<td>{{item.name}}<br />{{item.mobile}}</td>
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
								<button ng-disabled="currentPage == 0" ng-click="currentPage=currentPage-1">Previous</button>
								{{currentPage+1}}/{{numberOfPages()}}
								<button ng-disabled="currentPage >= data.length/pageSize - 1" ng-click="currentPage=currentPage+1">Next</button>
								
							</div>							
							<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 table-responsive" ng-if = "earningReportDetails.length <= 0">
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
		<!-------------- /div49 ---------------------->
		
		<!-------------- Div50 ---------------------->
		<div id="div50" class="row clearfix" style="display: none; margin: 0px;">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<div class="card">
					<div class="header">
						<h2>NSDL Pan Report</h2>
					</div>
					<div class="body">
						<form class="form-horizontal">
							<div class="row clearfix">
								<div class="col-md-6 col-lg-6 col-sm-12 col-xs-12">
									<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
										<label for="email_address_2">Status</label>
									</div>
									<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
										<div class="form-group">
											<div class="form-line1">
												<select class="form-control" ng-model = "nsdlReportt.status" ng-init="nsdlReportt.status = 'All'">
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
												<input type="text" class="form-control" id="dp38" placeholder="Enter Start Date" ng-model="nsdlReportt.startDate" readonly="readonly" />
											</div>
										</div>
									</div>
								</div>
								<div class="col-md-6 col-lg-6 col-sm-12 col-xs-12">
									<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
										<label for="password_2">End Date</label>
									</div>
									<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
										<div class="form-group">
											<div class="form-line1">
												<input type="text" class="form-control" id="dp39" placeholder="Enter End Date" ng-model="nsdlReportt.endDate" readonly="readonly" />
											</div>
										</div>
									</div>
								</div>
							</div>							
							<div class="row clearfix">
								<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 "></div>
								<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
									<div class="col-md-offset-3">
										<button type="button" class="btn btn-primary waves-effect" ng-click = "getnsdlReport(nsdlReportt);">Update</button>
										<button type="button" class="btn btn-primary waves-effect" onclick="showDiv('div24')">Back</button>
									</div>
								</div>
							</div>
							<div class="row clearfix">
								<div class="form-group">
									<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" ng-if = "nsdlReport.length > 0" style="overflow: scroll;margin-left: 1%;">
										<table id="mainTable" class="table table-striped" >
											<thead>
												<tr>
													<th class="success text-center">Sl. No</th>	
													<th class="success text-center">User Name</th>
													<th class="success text-center">OP BL</th>
													<th class="success text-center">CL BL</th>
													<th class="success text-center">Status</th>
													<th class="success text-center">Date and Time</th>
													<th class="success text-center">ACK NO</th>
													<th class="success text-center">ACK SLIP</th>
													<th class="success text-center">Invoice No</th>
													<th class="success text-center">Remark</th>
													<th class="success text-center">Type</th>
													<th class="success text-center">Action</th>
													<th class="success text-center">Download</th>
													<th class="success text-center">View</th>
												</tr>
											</thead>
											<tbody>
												<tr	ng-repeat="report in nsdlReport | startFrom:currentPage*pageSize | limitTo:pageSize">
													<td>{{$index + 1}}</td>		
													<td>{{report.uname}}({{report.umobile}})</td>
													<td>{{report.opnbl}}</td>
													<td>{{report.clbl}}</td>
													<td class="{{report.status}}">{{report.status}}</td>
													<td>{{report.date}}&amp;{{report.time}}</td>
													<td>{{report.ackno}}</td>
													<td ng-if="report.status==='SUCCESS'"><a href="http://doc.safegoal.co.in/{{report.ackfilename}}" target="_blank">
															download</a></td>
													<td ng-if="report.status!=='SUCCESS'">{{report.ackslip}}</td>
													<td>{{report.invoiceno}}</td>
													<td>{{report.remark}}</td>
													<td>{{report.applicationType}}</td>
													<td class="text-center" style="font-size: 12px;" >
															<button data-toggle="modal" data-target="#nsdlPendingModal" ng-if = "report.status == 'PENDING'||report.status == 'HOLD'" ng-click = "getNSDLDetails(report,'SUCCESS');" style="border-radius: 100%;">
															Success</button>
															<button data-toggle="modal" data-target="#nsdlPendingFailedModal" ng-if = "report.status == 'PENDING'||report.status == 'HOLD'" ng-click = "getNSDLDetails(report,'FAILED');" style="border-radius: 100%;">
															FAILED</button>
															<button data-toggle="modal" data-target="#nsdlPendingHoldModal" ng-if = "report.status == 'PENDING'" ng-click = "getNSDLDetails(report,'HOLD');" style="border-radius: 100%;">
															Hold</button>
													</td>
													<!-- <td><button ng-click = "download(report);" style="border-radius: 100%;">
															download</button></td> -->
													<td><a href="http://doc.safegoal.co.in/{{report.filename}}" target="_blank">
															download</a></td>
															<!-- <td><a href="">download</a></td> -->
													<td><button data-toggle="modal" data-target="#nsdlViewModal" ng-click = "getNSDLAttachmentView(report);" style="border-radius: 100%;">
															View</button></td>
												</tr>
											</tbody>
										</table>
									</div>
									<div data-ng-show="viewnsdlReportPagination" style="margin-left: 2%">
										<button ng-disabled="currentPage == 0" ng-click="currentPage=currentPage-1">Previous</button>
										{{currentPage+1}}/{{numberOfPages()}}
										<button ng-disabled="currentPage >= data.length/pageSize - 1" ng-click="currentPage=currentPage+1">Next</button>
										<!-- <button type="button" class="btn btn-primary" ng-click="getRechargeReportExcel(RechargeReports);" ng-if = "RechargeReports.length > 0">Export</button> -->
									</div>
									<div class="table-responsive" ng-if="nsdlReport.length <= 0" style="margin-left: 30px;">
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
						</form>
					</div>
				</div>
			</div>
		
		</div>
			<!-- Modal -->
		<div id="nsdlPendingModal" class="modal fade" role="dialog" tabindex="-1" aria-labelledby="modalLabel" aria-hidden="true">
		  <div class="modal-dialog">
		
		     <!-- Modal content-->
		    <div class="modal-content" style="width: 43%;left: 18%;">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal">&times;</button>
		        <h4 class="modal-title">Put Some Remark</h4>
		      </div>
		      <div class="modal-body">
		       	<div class="row" style="margin: 0px;">
		       	
		       	<div class="col-md-4 text-right"> <label for="comment">ACK NO:</label></div>
				<div class="col-md-8"><input type="text" ng-model="nsdl.ackno" class="form-control" placeholder="Enter ACkK NO"></div> 
				
		       	<div class="col-md-4 text-right"> <label for="comment">Remark:</label></div>
				<div class="col-md-8"><input type="text" ng-model="nsdl.remark" class="form-control" placeholder="Enter Remarks"></div> 
				
				<div class="col-md-4 text-right"> <label for="comment">ACK Slip:</label></div>
				<div class="col-md-8"><input type="file" class="form-control" file-model = "ackslip"></div> 
				</div>
				<div class="" style="margin: 5%">
				<center>	
				  <input type="submit" class="btn btn-info" data-dismiss="modal" ng-click = "updateNSDLReport(nsdl);">
				 </center>
				</div>
		      </div>
		      <div class="modal-footer">
		        
		      </div>
		    </div>
		
		  </div>
		</div>
			<!-- Modal -->
		<div id="nsdlPendingFailedModal" class="modal fade" role="dialog" tabindex="-1" aria-labelledby="modalLabel" aria-hidden="true">
		  <div class="modal-dialog">
		
		     <!-- Modal content-->
		    <div class="modal-content" style="width: 43%;left: 18%;">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal">&times;</button>
		        <h4 class="modal-title">Put Some Remark</h4>
		      </div>
		      <div class="modal-body">
		       	<div class="row" style="margin: 0px;">
		       	
		       	<div class="col-md-4 text-right"> <label for="comment">ACK NO:</label></div>
				<div class="col-md-8"><input type="text" ng-model="nsdl.ackno" class="form-control" placeholder="Enter ACkK NO"></div> 
				
		       	<div class="col-md-4 text-right"> <label for="comment">Remark:</label></div>
				<div class="col-md-8"><input type="text" ng-model="nsdl.remark" class="form-control" placeholder="Enter Remarks"></div> 
				</div>
				<div class="" style="margin: 5%">
				<center>	
				  <input type="submit" class="btn btn-info" data-dismiss="modal" ng-click = "updateNSDLReportFailed(nsdl);">
				 </center>
				</div>
		      </div>
		      <div class="modal-footer">
		        
		      </div>
		    </div>
		
		  </div>
		</div>
		
			<!-- Modal -->
		<div id="nsdlPendingHoldModal" class="modal fade" role="dialog" tabindex="-1" aria-labelledby="modalLabel" aria-hidden="true">
		  <div class="modal-dialog">
		
		     <!-- Modal content-->
		    <div class="modal-content" style="width: 43%;left: 18%;">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal">&times;</button>
		        <h4 class="modal-title">Put Some Remark</h4>
		      </div>
		      <div class="modal-body">
		       	<div class="row" style="margin: 0px;">
		       	
		       	<!-- <div class="col-md-4 text-right"> <label for="comment">ACK NO:</label></div>
				<div class="col-md-8"><input type="text" ng-model="nsdl.ackno" class="form-control" placeholder="Enter ACkK NO"></div> 
				 -->
		       	<div class="col-md-4 text-right"> <label for="comment">Remark:</label></div>
				<div class="col-md-8"><input type="text" ng-model="nsdl.remark" class="form-control" placeholder="Enter Remarks"></div> 
				
				<!-- <div class="col-md-4 text-right"> <label for="comment">ACK Slip:</label></div>
				<div class="col-md-8"><input type="file" class="form-control" file-model = "ackslip"></div>  -->
				</div>
				<div class="" style="margin: 5%">
				<center>	
				  <input type="submit" class="btn btn-info" data-dismiss="modal" ng-click = "updateNSDLHoldReport(nsdl);">
				 </center>
				</div>
		      </div>
		      <div class="modal-footer">
		        
		      </div>
		    </div>
		
		  </div>
		</div>
		<!-- Modal -->
	<div id="nsdlDownloadModal" class="modal fade" role="dialog" tabindex="-1" aria-labelledby="modalLabel" aria-hidden="true">
		<div class="modal-dialog" style="margin: 8% auto;width: 90%;">

			<!-- Modal content-->
			<div class="modal-content" style="width: 43%; left: 18%;">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Download Documents</h4>
				</div>
				<div class="modal-body">
				<div class="row" style="margin: 0px;">
				<div class="col-md-12 col-lg-12 col-sm-12 col-xs-12">
					<%-- <div class="col-md-6" style="margin-bottom: 20px;" ng-show="documentdetail.documentImage!=='data:undefined;base64,'">
						<a href="{{documentdetail.fileurlImage}}"
							download="{{imagename}}"> Download
							Image
						</a>
							<a href="${pageContext.request.contextPath}/download/Image.html"><img 
				src="${pageContext.request.contextPath}/img/save_icon.gif" border="0" 
				title="Download this document"/></a>
					</div> --%>
				<!-- 	 <div class="col-md-6" style="margin-bottom: 20px;" ng-show="documentdetail.documentSignature!=='data:undefined;base64,'">
						<a href="{{documentdetail.fileurlSignature}}"
							download="{{signaturename}}">
						<img src="{{document.documentSignature}}" style="width: 12%;  margin-top: -1%;" download>Download
							Signature
						</a>
					</div> -->
				<!-- 	<div class="col-md-6" style="margin-bottom: 20px;" ng-show="documentdetail.documentFormFont!=='data:undefined;base64,'">
						<a href="{{documentdetail.fileurlFormFont}}"
							download="{{Formfontname}}"><img src="{{document.documentFormFont}}" style="width: 12%;  margin-top: -1%;" download>Download
							FormFont
						</a>
					</div> -->
					<!-- <div class="col-md-6" style="margin-bottom: 20px;" ng-show="documentdetail.documentFormBack!=='data:undefined;base64,'">
						<a href="{{documentdetail.fileurlFormBack}}"
							download="{{Formbackname}}">
						<img src="{{document.documentFormBack}}" style="width: 12%;  margin-top: -1%;" download>Download
							FormBack
						</a>
					</div> -->
					<div class="col-md-6" style="margin-bottom: 20px;">
						<!-- <a href="{{documentdetail.fileurlProofOfIdentity}}"
							download="{{Idproofname}}">
						<img src="{{document.documentProofOfIdentity}}" style="width: 12%;  margin-top: -1%;" download>Download
							ID Proof
						</a> -->
					 <a href="{{fileurl}}" target="_blank">download</a> 
					</div> 
				</div>
				</div>
				</div>
			</div>

		</div>
	</div>

	<!-- Modal -->
		<div id="nsdlViewModal" class="modal fade" role="dialog" tabindex="-1" aria-labelledby="modalLabel" aria-hidden="true">
		  <div class="modal-dialog" style="width: 100%;margin: 2% auto;left: 21%;">
		
		     <!-- Modal content-->
		    <div class="modal-content" style="width: 68%;left: 0%;">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal">&times;</button>
		        <h4 class="modal-title" style="font-size: 23px;text-align: center;">NSDL Pan</h4>
		      </div>
				<div class="modal-body">
					<div class="row" style="margin: 0px;">
						<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
							<div class="col-md-4 text-right">
								<label for="comment">First Name:</label>
							</div>
							<div class="col-md-8">
								<input type="text" ng-model="viewnsdl.name" class="form-control"
									placeholder="Enter ACkK NO" readonly="readonly">
							</div>
						</div>
						<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
							<div class="col-md-4 text-right">
								<label for="comment">Middle Name:</label>
							</div>
							<div class="col-md-8">
								<input type="text" ng-model="viewnsdl.middlename"
									class="form-control" placeholder="Enter Remarks" readonly="readonly">
							</div>
						</div>
					</div>
					<div class="row" style="margin: 0px;">
						<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
						<div class="col-md-4 text-right">
							<label for="comment">Last Name:</label>
						</div>
						<div class="col-md-8">
							<input type="text" ng-model="viewnsdl.lastname"
								class="form-control" placeholder="Enter ACkK NO" readonly="readonly">
						</div>
						</div>
						<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
						<div class="col-md-4 text-right">
							<label for="comment">Father First Name:</label>
						</div>
						<div class="col-md-8">
							<input type="text" ng-model="viewnsdl.fathername"
								class="form-control" placeholder="Enter Remarks" readonly="readonly">
						</div>
					</div>
					<div class="row" style="margin: 0px;">
						<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
						<div class="col-md-4 text-right">
							<label for="comment">Father Middle Name:</label>
						</div>
						<div class="col-md-8">
							<input type="text" ng-model="viewnsdl.fathernamem"
								class="form-control" placeholder="Enter ACkK NO" readonly="readonly">
						</div>
						</div>
						<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
						<div class="col-md-4 text-right">
							<label for="comment">Father Last Name:</label>
						</div>
						<div class="col-md-8">
							<input type="text" ng-model="viewnsdl.fathernamel"
								class="form-control" placeholder="Enter Remarks" readonly="readonly">
						</div>
						</div>
					</div>
					<div class="row" style="margin: 0px;">
						<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
						<div class="col-md-4 text-right">
							<label for="comment">Mobile No:</label>
						</div>
						<div class="col-md-8">
							<input type="text" ng-model="viewnsdl.mobile"
								class="form-control" placeholder="Enter ACkK NO" readonly="readonly">
						</div>
						</div>
						<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
						<div class="col-md-4 text-right">
							<label for="comment">Email:</label>
						</div>
						<div class="col-md-8">
							<input type="text" ng-model="viewnsdl.email" class="form-control"
								placeholder="Enter Remarks" readonly="readonly">
						</div>
						</div>
					</div>
					<div class="row" style="margin: 0px;">
						<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
						<div class="col-md-4 text-right">
							<label for="comment">DOB:</label>
						</div>
						<div class="col-md-8">
							<input type="text" ng-model="viewnsdl.dob" class="form-control"
								placeholder="Enter ACkK NO" readonly="readonly">
						</div>
						</div>
						<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
						<div class="col-md-4 text-right">
							<label for="comment">Sex:</label>
						</div>
						<div class="col-md-8">
							<input type="text" ng-model="viewnsdl.sex" class="form-control"
								placeholder="Enter Remarks" readonly="readonly">
						</div>
						</div>
					</div>
					<div class="row" style="margin: 0px;">
						<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
						<div class="col-md-4 text-right">
							<label for="comment">Address1:</label>
						</div>
						<div class="col-md-8">
							<input type="text" ng-model="viewnsdl.add1" class="form-control"
								placeholder="Enter ACkK NO" readonly="readonly">
						</div>
						</div>
						<!-- <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
						<div class="col-md-4 text-right">
							<label for="comment">Address2:</label>
						</div>
						<div class="col-md-8">
							<input type="text" ng-model="viewnsdl.add2" class="form-control"
								placeholder="Enter Remarks" readonly="readonly">
						</div>
						</div> -->
					</div>
					<div class="row" style="margin: 0px;">
						<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
						<div class="col-md-4 text-right">
							<label for="comment">Pin:</label>
						</div>
						<div class="col-md-8">
							<input type="text" ng-model="viewnsdl.pin" class="form-control"
								placeholder="Enter ACkK NO" readonly="readonly">
						</div>
						</div>
						<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
						<div class="col-md-4 text-right">
							<label for="comment">State:</label>
						</div>
						<div class="col-md-8">
							<input type="text" ng-model="viewnsdl.state" class="form-control"
								placeholder="Enter Remarks" readonly="readonly">
						</div>
						</div>
					</div>
					<div class="row" style="margin: 0px;">
						<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
						<div class="col-md-4 text-right">
							<label for="comment">Proof type:</label>
						</div>
						<div class="col-md-8">
							<input type="text" ng-model="viewnsdl.prooftype"
								class="form-control" placeholder="Enter ACkK NO" readonly="readonly">
						</div>
						</div>
						<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
						<div class="col-md-4 text-right">
							<label for="comment">Id No:</label>
						</div>
						<div class="col-md-8">
							<input type="text" ng-model="viewnsdl.idno" class="form-control"
								placeholder="Enter Remarks" readonly="readonly">
						</div>
						</div>
					</div>
					<div class="row" style="margin: 0px;">
						<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
						<div class="col-md-4 text-right">
							<label for="comment">AddressProof:</label>
						</div>
						<div class="col-md-8">
							<input type="text" ng-model="viewnsdl.addressproof"
								class="form-control" readonly="readonly">
						</div>
						</div>
					
						<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12" >
						<div class="col-md-4 text-right">
							<label for="comment">DOB Proof:</label>
						</div>
						<div class="col-md-8">
							<input type="text" ng-model="viewnsdl.dobproof"
								class="form-control" placeholder="Enter oldpan" readonly="readonly">
						</div>
						</div>

					</div>
					<div class="row" style="margin: 0px;">
						<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
						<div class="col-md-4 text-right">
							<label for="comment">District:</label>
						</div>
						<div class="col-md-8">
							<input type="text" ng-model="viewnsdl.district"
								class="form-control" readonly="readonly">
						</div>
						</div>
					
						<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12" >
						<div class="col-md-4 text-right">
							<label for="comment">Card Name:</label>
						</div>
						<div class="col-md-8">
							<input type="text" ng-model="viewnsdl.namecard"
								class="form-control" placeholder="Enter oldpan" readonly="readonly">
						</div>
						</div>

					</div>
					<div class="row" style="margin: 0px;">
						<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
						<div class="col-md-4 text-right">
							<label for="comment">Application Type:</label>
						</div>
						<div class="col-md-8">
							<input type="text" ng-model="viewnsdl.applicationType"
								class="form-control" placeholder="Enter ACkK NO" readonly="readonly">
						</div>
						</div>
					
						<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12" ng-if="viewnsdl.oldpan!='-'">
						<div class="col-md-4 text-right">
							<label for="comment">Old Pan No:</label>
						</div>
						<div class="col-md-8">
							<input type="text" ng-model="viewnsdl.oldpan"
								class="form-control" placeholder="Enter oldpan" readonly="readonly">
						</div>
						</div>

					</div>
					
					<!-- 	<div class="" style="margin: 5%">
				<center>	
				  <input type="submit" class="btn btn-info" data-dismiss="modal" ng-click = "updateNSDLReport(nsdl);">
				 </center>
				</div> -->
				</div>
				
		    </div>
		
		  </div>
		</div>
		</div>
				<!-- Modal -->
	<div id="nsdlAkdownload" class="modal fade" role="dialog" tabindex="-1" aria-labelledby="modalLabel" aria-hidden="true">
		<div class="modal-dialog" style="margin: 8% auto;width: 90%;">

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
						<a href="{{fileurlImage}}"
							download="{{filename}}"> Download
							Image
						</a>
					</div>
				</div>
				</div>
				</div>
			</div>

		</div>
	</div>
	<!-------------- /Div50 ---------------------->
		
		
		<!-------------- Div51 ---------------------->
		<div id="div51" class="row clearfix" style="display: none; margin: 0px;">
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
													<input type="text" class="form-control" ng-model="impsReport.start_date" id="dp42" placeholder="Enter Start Date" ng-model="viewPurchaseReport.startDate" readonly="readonly" />
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
													<input type="text" class="form-control" ng-model="impsReport.end_date" id="dp43" placeholder="Enter Start Date" readonly="readonly" />
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
										<button type="button" class="btn btn-primary waves-effect" ng-click="getImpsReport(impsReport,'getImpsReportDetail')">submit</button>
										
									</div>
								</div>
							</div>
							<div class="row clearfix" ng-bind-html="getImpsReportDetail" compile-template>
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
											<%if(user.getRoleId() == 1){ %>										
											<th class="success text-center">Action</th>
											<%} %>
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
											<%if(user.getRoleId() == 1){ %>		
											<td class="in" style="font-size: 12px;">
												<button class="edit-button" data-toggle="modal" data-target="#modalImpsRemark" ng-click="getmodalImpsRemark(report,'SUCCESS');" ng-if = "report.status == 'PENDING' || report.status == 'FAILED'">Success</button>
												<button class="delete-button" data-toggle="modal" data-target="#modalImpsRemark" style="width: 101%;" ng-click = "getmodalImpsRemark(report,'FAILED');" ng-if = "report.status == 'PENDING' || report.status == 'SUCCESS'">Failed</button>
											</td>
											<%} %>
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
		
				<!-------------- div156 ---------------------->
		<div id="div156" class="row clearfix" style="display: none; margin: 0px;">
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
											<input type="text" class="form-control" id="dp48" placeholder="Enter Start Date" ng-model="aepsReport.startDate" readonly="readonly">
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
											<input type="text" class="form-control" id="dp49" placeholder="Enter Start Date" ng-model="aepsReport.endDate" readonly="readonly">
										</div>
									</div>
								</div>
							</div>
							<div class="row clearfix">
								<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 "></div>
								<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
									<div class="col-md-offset-3">
									
										<!-- <button type="button" class="btn btn-primary waves-effect" ng-click="getRBLAEPSReport(aepsReport);">Update</button> -->
										<button type="button" class="btn btn-primary waves-effect" ng-click="getYesBankAEPSReport(aepsReport);">Update</button>
										<button type="button" class="btn btn-primary waves-effect" onclick="showDiv('div43')">Back</button>
									</div>
								</div>
							</div>
							<div class="row clearfix">
								<div class="form-group">
									<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 table-responsive" ng-if = "rblReport.length > 0">
									<table id="mainTable" class="table table-striped" style="    margin-left: 1%;">
										<thead>
											<tr style="font-size: 12px; font-weight: bold;">
												<th class="success text-center">Sl. No</th>	
												<th class="success text-center">username</th>
												<th class="success text-center">name</th>
												<th class="success text-center">mobile</th>
												<th class="success text-center">aadharNumber</th>
												<th class="success text-center">agentcode</th>
												<th class="success text-center">amount</th>
											<!-- 	<th class="success text-center">bankResponseMsg</th>	 -->
												<th class="success text-center">orderId</th>
												<!-- <th class="success text-center">orderStatus</th>
												<th class="success text-center">gstAmt</th> -->
												<th class="success text-center">paymentStatus</th>
												<th class="success text-center">type</th>
												<!-- <th class="success text-center">requestId</th> -->
												<th class="success text-center">rrn</th>
												<th class="success text-center">stan</th>
												<th class="success text-center">statusCode</th>
												
												<!-- 
												<th class="success text-center">statusMessage</th>
												<th class="success text-center">tdsAmt</th>
												<th class="success text-center">terminalId</th> -->
												<th class="success text-center">date</th>
												<th class="success text-center">time</th>
											</tr>
										</thead>
										<tbody>
											<tr	ng-repeat="rblRepo in rblReport"  >
											<td>{{$index + 1}}</td>	
											<td>{{rblRepo.username}}</td>
											<td>{{rblRepo.name}}</td>
											<td>{{rblRepo.mobile}}</td>
											<td>{{rblRepo.Aadhar}}</td>
											<td>{{rblRepo.agentcode}}</td>
											<td>{{rblRepo.TxnAmount}}</td>
											<td>{{rblRepo.referenceNo}}</td>
											<td>SUCCESS</td>
											<td ng-if="rblRepo.processingCode=='010000'">Balance Withdrawal</td>
											<td ng-if="rblRepo.processingCode=='210000'">Balance Deposit</td>
											<td ng-if="rblRepo.processingCode=='310000'">Balance Enquiry</td>
											<!-- <td>{{rblRepo.requestId}}</td> -->
											<td>{{rblRepo.RRN}}</td>
											<td>{{rblRepo.STAN}}</td>
											<td>{{rblRepo.Status}}</td>
											<!-- <td>{{rblRepo.statusMessage}}</td>
											<td>{{rblRepo.tdsAmt}}</td>
											<td>{{rblRepo.terminalId}}</td>
 -->											<td>{{rblRepo.date}}</td>
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
		
		<!-------------- /Div52 ---------------------->
		
		<div id="div157" class="row clearfix" style="display: none; margin: 0px;">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<div class="card">
					<div class="header">
						<h2>SETTLEMENT Report</h2>
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
											<input type="text" class="form-control" id="dp50" placeholder="Enter Start Date" ng-model="settleReport.startDate" readonly="readonly">
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
											<input type="text" class="form-control" id="dp51" placeholder="Enter Start Date" ng-model="settleReport.endDate" readonly="readonly">
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
												<select class="form-control" ng-model="settleReport.type" >
													<option value="Settle to Wallet">Settle to Wallet</option>
													<option value="Settle to Bank">Settle to Bank</option>
													<option value="Transaction">Transaction</option>
													
												</select>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="row clearfix">
								<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 "></div>
								<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
									<div class="col-md-offset-3">
										<button type="button" class="btn btn-primary waves-effect" ng-click="getRBLSETTLEMENTReport(settleReport);">Update</button>
										<button type="button" class="btn btn-primary waves-effect" onclick="showDiv('div43')">Back</button>
									</div>
								</div>
							</div>
							<div class="row clearfix">
								<div class="form-group">
									<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 table-responsive" ng-if = "settlementreport.length > 0">
										<div class="header" align="center">
											<h2 style="font-size: 30px;">Total Balance(&#8377;): {{TotalSettleBalance}}/-</h2>
										</div>
									<table id="mainTable" class="table table-striped" style="    margin-left: 1%;">
										<thead>
											<tr style="font-size: 12px; font-weight: bold;">
											<th class="success text-center">Action</th>
												<th class="success text-center">Sl. No</th>	
												<th class="success text-center">User Mobile</th>
												<!-- <th class="success text-center">name</th> -->
																					
												<th class="success text-center">settleopbal</th>
												<th class="success text-center">settleclbal</th>
												<th class="success text-center">amount</th>
												<th class="success text-center">date</th>
												<th class="success text-center">time</th>
												<th class="success text-center">type</th>
												<th class="success text-center" ng-if="settletype=='Settle to Bank'">Bank</th>
												<th class="success text-center" ng-if="settletype=='Settle to Bank'">IFSC</th>
												<th class="success text-center" ng-if="settletype=='Settle to Bank'">Account</th>
												<th class="success text-center" ng-if="settletype=='Settle to Bank'">Branch</th>
												
											</tr>
										</thead>
										<tbody>
											<tr	ng-repeat="rblsettle in settlementreport"  >
											<td ng-if="rblsettle.status=='PENDING'"><a href="javascript:void(0);" class="btn btn-success"   ng-click="settlestatusmodal(rblsettle,'SUCCESS')" > Success</a> <a  href="javascript:void(0);" class="btn btn-danger"  ng-click="settlestatusmodal(rblsettle,'FAILED')"> Failed</a></td>
											<td>{{$index + 1}}</td>	
											<td>{{rblsettle.mobile}}</td>
											<!-- <td>{{rblsettle.name}}</td> -->
											<!-- <td>{{rblsettle.mobile}}</td> -->
											<td>{{rblsettle.settleopbal}}</td>
											<td>{{rblsettle.settleclbal}}</td>
											<td>{{rblsettle.amount}}</td>
											<td>{{rblsettle.date}}</td>
											<td>{{rblsettle.time}}</td>
											<td>{{rblsettle.type}}</td>
											<td ng-if="rblsettle.type=='Settle to Bank'">{{rblsettle.bank}}</td>
											<td ng-if="rblsettle.type=='Settle to Bank'">{{rblsettle.ifsc}}</td>
											<td ng-if="rblsettle.type=='Settle to Bank'">{{rblsettle.account}}</td>
											<td ng-if="rblsettle.type=='Settle to Bank'">{{rblsettle.branch}}</td>
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
		
			<div  id="rblSetStatus" class="modal fade" role="dialog" tabindex="-1" aria-labelledby="modalLabel" aria-hidden="true">
			  	<div class="modal-dialog">
			
			     <!-- Modal content-->
			    <div class="modal-content" style="width: 43%;left: 18%;">
			      <div class="modal-header">
			        <button type="button" class="close" data-dismiss="modal">&times;</button>
			      </div>
			      <div class="modal-body">
			      	<div class="row clearfix">
						<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
							<label for="email_address_2">Remarks</label>
						</div>
						<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
							<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
								<div class="form-group form-float"
									style="margin-left: 0px !important;">
									<div class="form-line">
										<textarea style="height: 61px;" cols="20" rows="1" type="text" class="form-control" placeholder="Enter Remarks" ng-model="popsettlestatus.remarks" ></textarea>
									</div>
								</div>
							</div>
						</div>
					</div>
			      	<div class="row clearfix">
					<div class="" style="margin-top: 10%">
					<center>	
					  <a class="btn btn-info" ng-click="aepsSettleToBank(popsettlestatus)"> Submit</a>
					 </center>
					</div>
			      </div>
			     </div>
			     
			    </div>
			
			  </div>
			</div>
											
		
		<!-- ------------------------------------- IMPS Success Modal-------------------------------------- -->
	<!-- Modal -->
		<div id="modalImpsRemark" class="modal fade" role="dialog" tabindex="-1" aria-labelledby="modalLabel" aria-hidden="true">
		  <div class="modal-dialog">
		 <!-- Modal content-->
		    <div class="modal-content" style="width: 43%;left: 18%;">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal">&times;</button>
		        <h4 class="modal-title">Put Some Remark</h4>
		      </div>
		      <div class="modal-body">
		       	<div class="">
		       	<div class="col-md-2 text-right"> <label for="comment">Remark:</label></div>
				<div class="col-md-8"><input type="text" ng-model="popremark.banktransactionId" class="form-control" placeholder="Enter Remarks"></div> 
				</div>
				<div class="">
					<div class="col-md-8">
						<input type="hidden" ng-model="popremark.recieptId" class="form-control" placeholder="Enter recieptId">
						<input type="hidden" ng-model="popremark.status" class="form-control" placeholder="Enter status">
					</div> 
				</div>
				<div class="" style="margin-top: 10%">
				<center>	
				  <input type="submit" class="btn btn-info" data-dismiss="modal" ng-click = "updateImpsReport(popremark);">
				 </center>
				</div>
		      </div>
		      <div class="modal-footer">
		      </div>
		    </div>
		 </div>
		</div>
	
	<!-- ------------------------------------- /IMPS Success -------------------------------------- -->
		<!-------------- /Div51 ---------------------->
		<!-------------- Div52 ---------------------->
		<div id="div52" class="row clearfix" style="display: none; margin: 0px;" >
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<div class="card">
					<div class="header">
						<h2>View Package Details <span class="pull-right"><a href="#" onClick="showDiv('div7')"  class="body"><i class="material-icons">arrow_back</i></a></span></h2>
					</div>
					<div class="body">
						<div class="row clearfix">
							<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 form-control-label">
							    <div class="body1">
									<div class="row">
										<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
											<table id="datatable1" class="table table-striped table-bordered">
                                                  <thead>
                                                       <tr ng-if="servicetype=='Encore AEPS'||servicetype=='DMR' || servicetype=='YesBank AEPS' || servicetype=='MicroATM'">
                                                         
                                                          <th class="success text-center">Package Id</th>
                                                          <th class="success text-center">Charge</th>
                                                          <th class="success text-center">Charge Type</th>
                                                          <th class="success text-center">Commission</th>
                                                          <th class="success text-center">Commission Type</th>
                                                           <th class="success text-center">Service Provider</th>
                                                            <th class="success text-center">Slab</th>
                                                         <th class="success text-center">Update</th>
                                                      </tr>
                                                       <tr ng-if="(servicetype!='YesBank AEPS' && servicetype!='DMR') && (servicetype!='Encore AEPS' && servicetype!='MicroATM' )">
                                                       
                                                    <!--      <tr ng-if="servicetype!='RBL AEPS' && servicetype!='DMR'"> -->
                                                         
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
                                                          <td class="text-center"><input type="text"   ng-model="packch.charge" class="form-control text-center" ></td>
                                                          <td class="text-center">
	                                                        <select id="" ng-model="packch.charge_type"  class="form-control" >
													            <option >{{packch.charge_type}}</option>
													            <option ng-if="packch.charge_type=='PERCENTAGE'">RUPEE</option>
													             <option ng-if="packch.charge_type=='RUPEE'">PERCENTAGE</option>
													        </select>
														  </td>
                                                          <td class="text-center"><input type="text" ng-model="packch.comm"  class="form-control text-center"  ></td>
                                                          <td class="text-center">
	                                                        <select id="" ng-model="packch.comm_type"  class="form-control" >
													         
													            <option>{{packch.comm_type}}</option>
													            <option ng-if="packch.comm_type=='PERCENTAGE'">RUPEE</option>
													             <option ng-if="packch.comm_type=='RUPEE'">PERCENTAGE</option>
													            
													        </select>
														  </td>
														  
														    <td class="text-center" ng-if="packch.serviceProvider=='YesBank AEPS'||packch.serviceProvider=='DMR' || packch.serviceProvider=='Encore AEPS' || packch.serviceProvider=='MicroATM'">
														    
														  <!--   <td class="text-center" ng-if="packch.serviceProvider=='RBL AEPS'||packch.serviceProvider=='DMR'"> -->
	                                                        {{packch.slab1}}-{{packch.slab2}}
														  </td>
														  <td ng-if="packch.serviceProvider!='Encore AEPS'">{{packch.serviceProvider}}</td>
														   <td ng-if="packch.serviceProvider==='Encore AEPS'">AEPS</td>
                                                         <td class="text-center">
                                                          	<button class="btn btn-success" ng-click="updatemyPackage(packch)"  style="background: #228B22 !important;">Update</button>
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
		
		<!-------------- /Div52 ---------------------->
		<!-------------- Div53 ---------------------->
		<div id="div53" class="row clearfix" style="display: none; margin: 0px;">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<div class="card">
					<div class="header">
						<h2>Assigned Package Details <span class="pull-right"><a href="#" onClick="showDiv('div7')"  class="body"><i class="material-icons">arrow_back</i></a></span></h2>
					</div>
					<div class="body">
						<div class="row clearfix">
							<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 form-control-label">
							    <div class="body1">
									
									<div class="row">
										<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
											<table id="datatable1" class="table table-striped table-bordered">
                                                  <thead>
                                                      <tr>
                                                         
                                                          <th class="success text-center">Package Id</th>
                                                          <th class="success text-center">Charge</th>
                                                          <th class="success text-center">Charge Type</th>
                                                          <th class="success text-center">Commission</th>
                                                          <th class="success text-center">Commission Type</th>
                                                          <th class="success text-center">Slab</th>
                                                           <th class="success text-center">Service Provider</th>
                                                         
                                                      </tr>
                                                  </thead>


                                                  <tbody>
                                                      <tr ng-repeat="pack in packdetails">
                                                          <td class="text-center">{{pack.package_id}}</td>
                                                          <td class="text-center"><input type="text" name="" ng-value="{{pack.charge}}" class="form-control text-center" readonly="readonly" ></td>
                                                          <td class="text-center">
	                                                        <select id=""  class="form-control" >
													            <option >{{pack.charge_type}}</option>
													            <option ng-if="pack.charge_type=='PERCENTAGE'">RUPEE</option>
													             <option ng-if="pack.charge_type=='RUPEE'">PERCENTAGE</option>
													        </select>
														  </td>
                                                          <td class="text-center"><input type="text" name="" ng-value="{{pack.comm}}" class="form-control text-center" readonly="readonly" ></td>
                                                          <td class="text-center">
	                                                        <select id="" class="form-control" >
													         
													            <option>{{pack.comm_type}}</option>
													            <option ng-if="pack.comm_type=='PERCENTAGE'">RUPEE</option>
													             <option ng-if="pack.comm_type=='RUPEE'">PERCENTAGE</option>
													            
													        </select>
														  </td>
														  
														  <td class="text-center" ng-if="pack.serviceProvider=='YesBank AEPS'||pack.serviceProvider=='DMR' || pack.serviceProvider=='Encore AEPS'">
														    
														  <!--   <td class="text-center" ng-if="packch.serviceProvider=='RBL AEPS'||packch.serviceProvider=='DMR'"> -->
	                                                        {{pack.slab1}}-{{pack.slab2}}
														  </td>
														  <td>{{pack.serviceProvider}}</td>
														   <td ng-if="pack.serviceProvider=='Encore AEPS'">AEPS</td>
                                                          <!-- <td class="text-center">
                                                          	<button class="btn btn-success" ng-click="" onClick="showDiv('div35')" style="background: #228B22 !important;">Update</button>
                                                          </td> -->
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
		<!-------------- /Div53 ---------------------->
		
		<!-------------- Div54 ---------------------->
		<div id="div54" class="row clearfix" style="display: none; margin: 0px;">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<div class="card">
					<div class="header">
						<h2>Add API Parameters</h2>
					</div>
					<div class="body">
						<div class="body">
							<div class="row clearfix">
								<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 col-lg-offset-2 col-md-offset-2" style="margin-left: 12%;">
									<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
										<label for="password_2">API Name</label>
									</div>
									<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
										<div class="form-group form-float" style="margin-left: 1.5%">
											<div class="form-line">
											<select class="form-control ng-pristine ng-valid ng-not-empty ng-touched" ng-model="apiparam.apiId">
											<option ng-repeat="ap in api" value="{{ap.apiId}}">{{ap.apiName}}</option>
												</select>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="row clearfix">
								<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 col-lg-offset-2 col-md-offset-2" style="margin-left: 12%;">
									<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
										<label for="password_2">Service Type</label>
									</div>
									<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
										<div class="form-group form-float" style="margin-left: 1.5%">
											<div class="form-line">
											<select class="form-control ng-pristine ng-valid ng-not-empty ng-touched" ng-model="apiparam.service">
											<option value="ALL">ALL</option>
											<option value="Mobile Recharge">Mobile Recharge</option>
											<option value="DTH Recharge">DTH Recharge</option>
											<option value="DATACARD Recharge">DATACARD Recharge</option>
											<option value="POSTPAID">POSTPAID</option>
											<option value="INSURANCE">INSURANCE</option>
											<option value="ELECTRIC">ELECTRIC</option>
											<option value="GAS">GAS</option>
											</select>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="row clearfix">
								<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 col-lg-offset-2 col-md-offset-2" style="margin-left: 12%;">
									<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
										<label for="password_2">Static Parameter names separated with Comma</label>
									</div>
									<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
										<div class="form-group form-float" style="margin-left: 1.5%">
											<div class="form-line">
												<input type="text" ng-model="apiparam.apiparameternames" class="form-control ng-pristine ng-valid ng-empty ng-touched" placeholder="Enter Parameter names separated with Comma">
											</div>
										</div>
									</div>
								</div>
							</div>
							
							<div class="row clearfix">
								<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 col-lg-offset-2 col-md-offset-2" style="margin-left: 12%;">
									<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
										<label for="password_2">Static Corresponding Parameter Values separated with Comma</label>
									</div>
									<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
										<div class="form-group form-float" style="margin-left: 1.5%">
											<div class="form-line">
												<input type="text" ng-model="apiparam.apiparametervalues" class="form-control ng-pristine ng-valid ng-empty ng-touched" placeholder="Enter Corresponding Parameter Values separated with Comma">
											</div>
										</div>
									</div>
								</div>
							</div>
							
							<div class="row clearfix">
								<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 col-lg-offset-2 col-md-offset-2" style="margin-left: 12%;">
									<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
										<label for="password_2">number param name</label>
									</div>
									<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
										<div class="form-group form-float" style="margin-left: 1.5%">
											<div class="form-line">
												<input type="text" ng-model="apiparam.number" class="form-control ng-pristine ng-valid ng-empty ng-touched" placeholder="Enter Corresponding Parameter Values separated with Comma">
											</div>
										</div>
									</div>
								</div>
							</div>
							
							<div class="row clearfix">
								<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 col-lg-offset-2 col-md-offset-2" style="margin-left: 12%;">
									<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
										<label for="password_2">opcode  param name</label>
									</div>
									<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
										<div class="form-group form-float" style="margin-left: 1.5%">
											<div class="form-line">
												<input type="text" ng-model="apiparam.opcode" class="form-control ng-pristine ng-valid ng-empty ng-touched" placeholder="Enter Corresponding Parameter Values separated with Comma">
											</div>
										</div>
									</div>
								</div>
							</div>
							
							<div class="row clearfix">
								<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 col-lg-offset-2 col-md-offset-2" style="margin-left: 12%;">
									<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
										<label for="password_2">amount param name</label>
									</div>
									<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
										<div class="form-group form-float" style="margin-left: 1.5%">
											<div class="form-line">
												<input type="text" ng-model="apiparam.amount" class="form-control ng-pristine ng-valid ng-empty ng-touched" placeholder="Enter Corresponding Parameter Values separated with Comma">
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="row clearfix">
								<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 col-lg-offset-2 col-md-offset-2" style="margin-left: 12%;">
									<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
										<label for="password_2">request_id param name</label>
									</div>
									<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
										<div class="form-group form-float" style="margin-left: 1.5%">
											<div class="form-line">
												<input type="text" ng-model="apiparam.request_id" class="form-control ng-pristine ng-valid ng-empty ng-touched" placeholder="Enter Corresponding Parameter Values separated with Comma">
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="row clearfix">
								<div class="col-lg-offset-6 col-md-offset-6 col-sm-offset-6 col-xs-offset-6">
									<button type="button" class="btn btn-primary m-t-15 waves-effect" ng-click="addApiParameters(apiparam)">Submit</button>
								</div>
							</div>
							
						</div>
					</div>
				</div>
			</div>
		</div>
		
		<!-------------- /Div54 ---------------------->
		
		
		<div id="div55" class="row clearfix" style="display: none; margin: 0px;">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<div class="card">
					<div class="header">
						<h2>Add API </h2>
					</div>
					<div class="body">
						<div class="body">
							<div class="row clearfix">
								<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 col-lg-offset-2 col-md-offset-2" style="margin-left: 12%;">
									<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
										<label for="password_2">API Name</label>
									</div>
									<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
										<div class="form-group form-float" style="margin-left: 1.5%">
											<div class="form-line">
												<input type="text" ng-model="apinew.apiname" class="form-control ng-pristine ng-valid ng-empty ng-touched" placeholder="Enter API Name">
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="row clearfix">
								<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 col-lg-offset-2 col-md-offset-2" style="margin-left: 12%;">
									<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
										<label for="password_2">API URL</label>
									</div>
									<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
										<div class="form-group form-float" style="margin-left: 1.5%">
											<div class="form-line">
												<input type="text" ng-model="apinew.apiurl" class="form-control ng-pristine ng-valid ng-empty ng-touched" placeholder="Enter API URL">
											</div>
										</div>
									</div>
								</div>
							</div>
							
							
							<div class="row clearfix">
								<div class="col-lg-offset-6 col-md-offset-6 col-sm-offset-6 col-xs-offset-6">
									<button type="button" class="btn btn-primary m-t-15 waves-effect" ng-click="addApi(apinew)">Submit</button>
								</div>
							</div>
							
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-------------- /Div56 ---------------------->
		<div id="div56" class="row clearfix" style="display: none; margin: 0px;">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<div class="card">
					<div class="header">
						<h2>Add SMS API PARAMETERS AND URL</h2>
					</div>
					<div class="body">
						<div class="body">
						    <div class="row clearfix">
								<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 col-lg-offset-2 col-md-offset-2" style="margin-left: 12%;">
									<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
										<label for="password_2">Base URL</label>
									</div>
									<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
										<div class="form-group form-float" style="margin-left: 1.5%">
											<div class="form-line">
												<input type="text" ng-model="smsapinew.baseurl" class="form-control" placeholder="Enter BaseURl">
											</div>
										</div>
									</div>
								</div>
							</div>
						
							<div class="row clearfix">
								<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 col-lg-offset-2 col-md-offset-2" style="margin-left: 12%;">
									<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
										<label for="password_2">username param name</label>
									</div>
									<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
										<div class="form-group form-float" style="margin-left: 1.5%">
											<div class="form-line">
												<input type="text" ng-model="smsapinew.username" class="form-control" placeholder="">
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="row clearfix">
								<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 col-lg-offset-2 col-md-offset-2" style="margin-left: 12%;">
									<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
										<label for="password_2">Password param name</label>
									</div>
									<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
										<div class="form-group form-float" style="margin-left: 1.5%">
											<div class="form-line">
												<input type="text" ng-model="smsapinew.password" class="form-control" placeholder="">
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="row clearfix">
								<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 col-lg-offset-2 col-md-offset-2" style="margin-left: 12%;">
									<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
										<label for="password_2">destination param name</label>
									</div>
									<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
										<div class="form-group form-float" style="margin-left: 1.5%">
											<div class="form-line">
												<input type="text" ng-model="smsapinew.destination" class="form-control" placeholder="">
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="row clearfix">
								<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 col-lg-offset-2 col-md-offset-2" style="margin-left: 12%;">
									<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
										<label for="password_2">source param name</label>
									</div>
									<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
										<div class="form-group form-float" style="margin-left: 1.5%">
											<div class="form-line">
												<input type="text" ng-model="smsapinew.source" class="form-control" placeholder="">
											</div>
										</div>
									</div>
								</div>
							</div>
							
							<div class="row clearfix">
								<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 col-lg-offset-2 col-md-offset-2" style="margin-left: 12%;">
									<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
										<label for="password_2">message param name</label>
									</div>
									<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
										<div class="form-group form-float" style="margin-left: 1.5%">
											<div class="form-line">
												<input type="text" ng-model="smsapinew.message" class="form-control" placeholder="">
											</div>
										</div>
									</div>
								</div>
							</div>
							
							
							<div class="row clearfix">
								<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 col-lg-offset-2 col-md-offset-2" style="margin-left: 12%;">
									<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
										<label for="password_2">static parmeter names in comma separated ways </label>
									</div>
									<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
										<div class="form-group form-float" style="margin-left: 1.5%">
											<div class="form-line">
												<input type="text" ng-model="smsapinew.staticparameters" class="form-control" placeholder="Enter Company">
											</div>
										</div>
									</div>
								</div>
							</div>
							
							<div class="row clearfix">
								<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 col-lg-offset-2 col-md-offset-2" style="margin-left: 12%;">
									<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
										<label for="password_2">corresponding static parmeter values in comma separated ways </label>
									</div>
									<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
										<div class="form-group form-float" style="margin-left: 1.5%">
											<div class="form-line">
												<input type="text" ng-model="smsapinew.staticparametervalues" class="form-control" placeholder="Enter Company">
											</div>
										</div>
									</div>
								</div>
							</div>
							
							<div class="row clearfix">
								<div class="col-lg-offset-6 col-md-offset-6 col-sm-offset-6 col-xs-offset-6">
									<button type="button" class="btn btn-primary m-t-15 waves-effect" ng-click="addsmsApi(smsapinew)">Submit</button>
								</div>
							</div>
							
						</div>
					</div>
				</div>
			</div>
		</div>
		
		
		<!-------------- div64 ---------------------->
				<div id="div64" class="row clearfix" style="display: none; margin: 0px;">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<div class="card">
					<div class="header">
						<h2>Add AEPS User</h2>
					</div>
					<div class="body">
						<div class="body">
							<form class="form-horizontal ng-pristine ng-valid">
							<div class="row clearfix">
								<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 col-lg-offset-2 col-md-offset-2 " style="margin-left: 12%;">							
									<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label ">
										<label for="email_address_2">Service</label></div>
									<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 focused">
										<select class="form-control" ng-model="aepsUser.userType" ng-change="getUserByUserType(aepsUser.userType,'getAEPSUserDiv','aepsUser')">
											<option value="">-- Please select --</option>
											<option ng-repeat="userType in userType" value="{{userType.type}}" class="ng-binding ng-scope">{{userType.role}}</option>
										</select>
									</div>
									<div ng-bind-html="getAEPSUserDiv" compile-template="" class="ng-binding ng-scope"></div>
									</div>
								</div>
								<div class="row clearfix">
								<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 col-lg-offset-2 col-md-offset-2" style="margin-left: 12%;">
									<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
										<label for="password_2">Select Api</label>
									</div>
									<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
										<div class="form-group form-float" style="margin-left: 1.5%">
											<div class="form-line focused">
											<select class="form-control " ng-model="aepsUser.apiname">
											<option value="">--- Select AEPS API --</option>
											<option value="YesBank">YesBank</option>
											<!-- <option value="RBL">RBL</option> -->
											<!-- <option value="ICICI">ICICI</option> -->
										</select>
											</div>
										</div>
									</div>
								</div>
							</div>
						
							
								<div class="row clearfix">
								<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 col-lg-offset-2 col-md-offset-2" style="margin-left: 12%;">
									<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label">
										<label for="password_2">Enter Agent Code</label>
									</div>
									<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
										<div class="form-group form-float" style="margin-left: 1.5%">
											<div class="form-line">
												<input type="text" ng-model="aepsUser.agentcode" class="form-control" placeholder="Enter  Id">
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="row clearfix">
								<div class="col-lg-offset-6 col-md-offset-6 col-sm-offset-6 col-xs-offset-6">
									<button type="button" class="btn btn-primary m-t-15 waves-effect" ng-click="aepsUseradd(aepsUser)">Submit</button>
								</div>
							</div>
							</form>	
						</div>
					</div>
				</div>
			</div>
		</div>	
				
				<!-------------- /div64 ---------------------->	
				
		
		<div id="div158" class="row clearfix" style="display: none; margin: 0px;">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<div class="card">
					<div class="header">
						<h2>View AEPS User</h2>
					</div>
					<div class="body">
						<div class="body">
							<form class="form-horizontal">
													
								<div class="row clearfix">
									<div class="col-lg-offset-2 col-md-offset-2 col-sm-offset-4 col-xs-offset-5">
										<button type="button" class="btn btn-primary m-t-15 waves-effect" ng-click = "getAEPSViewUser();">SUBMIT</button>										
									</div>
								</div>
								<!-- ---VIEW USER---- -->
								<div class="row clearfix m-t-20"  ng-if = "aepsuser.length > 0">
								<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 hscroll">
									<table class="table table-striped">
										<thead>
											<tr>
												<th class="success text-center">Sl. No</th>
												<th class="success text-center">AGENT CODE</th>
												<th class="success text-center">USERNAME</th>
												<th class="success text-center">NAME</th>
												<th class="success text-center">Mobile</th>
												<th class="success text-center">Email</th>
												<th class="success text-center">User Type</th>
												<th class="success text-center">Address</th>
												
											</tr>																							
										</thead>
										<tbody>
											<tr	ng-repeat="item in aepsuser">
												<td style="font-size: 12px;">{{$index + 1}}</td>
												<td style="font-size: 12px;">{{item.agentcode}}</td>
												<td style="font-size: 12px;">{{item.userId}}</td>
												<td style="font-size: 12px;">{{item.name}}</td>
												<td style="font-size: 12px;">{{item.mobile}}</td>
												<td style="font-size: 12px;">{{item.email}}</td>
												<td style="font-size: 12px;">
													<span ng-if = "item.roleId == 2">White Label</span>
													<span ng-if = "item.roleId == 3">SUPER DISTRIBUTOR</span>
													<span ng-if = "item.roleId == 4">DISTRIBUTOR</span>
													<span ng-if = "item.roleId == 5">RETAILER</span>
													<span ng-if = "item.roleId == 100">API USER</span>
												</td>
												<td style="font-size: 12px;">{{item.address}},&nbsp;{{item.city}}-{{item.pinCode}},&nbsp;{{item.state}}</td>
												
												
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
											<input type="text" class="form-control" id="dp55" placeholder="Enter Start Date" ng-model="aepslogreport.endDate" readonly="readonly">
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
												<th class="success text-center">apiremarks</th>
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
											<td>{{aepslog.apiremarks}}</td>
											<!-- <td>{{aepslog.time}}</td> -->
											<td>{{aepslog.type}}</td>
											
											<td>
											<a class="btn btn-info" ng-click="aepsstatuscheck(aepslog)"> Submit</a>
											
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
		
			<!-------------- div59 ---------------------->	
				<div id="div59" class="row clearfix" style="display: none">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 ">
						<div class="card">
							<div class="header">
								<h2>Edit Markup<span class="pull-right"><a href="home" class="body"><i class="material-icons">arrow_back</i></a></span></h2>
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
	<script src="assets/js/stellarnav.min.js"></script>
	<script src="assets/js/dir-pagination.js"></script>

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

</body>

</html>