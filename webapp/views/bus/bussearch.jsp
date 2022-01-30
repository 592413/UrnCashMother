<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@page import="com.recharge.model.User"%>
<%@page import="org.apache.log4j.Logger"%>
<%
	User user = (User) session.getAttribute("UserDetails");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zxx">

<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link
	href="https://fonts.googleapis.com/css?family=Poppins&display=swap"
	rel="stylesheet">
<!-- Bootstrap Min CSS -->
<link rel="stylesheet" href="assets/skybus/css/bootstrap.min.css">

<!-- Bootstrap Min CSS -->
<!-- Font Awesome Min CSS -->
<link rel="stylesheet" href="assets/skybus/css/fontawesome.min.css">
<!-- Owl Carousel Min CSS -->
<link rel="stylesheet" href="assets/skybus/css/owl.carousel.min.css">

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/owl-carousel/1.3.3/owl.carousel.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/owl-carousel/1.3.3/owl.theme.css">
<!-- Owl jquery-ui Min CSS -->
<link rel="stylesheet" href="assets/skybus/css/jquery-ui.min.css">
<!-- <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css"> -->
<!-- Style CSS -->
<link rel="stylesheet" href="assets/skybus/css/style.css">
<!-- Responsive CSS -->
<link rel="stylesheet" href="assets/skybus/css/responsive.css">

<link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet"
          integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous"><base>
 <link href="https://rawgit.com/rzajac/angularjs-slider/master/dist/rzslider.css" rel="stylesheet">

<title>Bus</title>
<style type="text/css">

.floatMessage {
	position: absolute;
	z-index: 999999;
	left: 40%;
	top: 29%;
	width: 27%;
}

.owl-carousel .owl-wrapper-outer {
    overflow: hidden;
    position: relative;
    width: 100%;
    border-left: 1px #eee solid;
    border-right: 1px #eee solid;
}
.pt_b {
	padding: 0px 0px 10px !important;
}

.bod {
	position: relative;
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
	background: #f3f2f3;
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
   	width: 15%;
    margin-top: 10%;
}

.page-loader-wrapper .loader {
	position: relative;
	top: calc(20% - 30px);
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
.fta_news{
	width: 100%;
    background-color: #4082c0;
    color: #fff;
    padding: 10px 5px;
}

/* .collapsible {
	color: #000;
	cursor: pointer;
	padding: 18px 0 0 0;
	width: 100%;
	border: none;
	text-align: left;
	outline: none;
	font-size: 15px;
}

.collapsible:after {
	content: '\002B';
	color: #000;
	font-weight: bold;
	float: right;
	margin-left: 5px;
}

/* .active:after {
	content: "\2212";
} */

.content {
	padding: 0 5px;
	overflow: visible;
	transition: max-height 0.2s ease-out;
}
.sticky {
  position: fixed;
  top: 0;
}
/* .visiable-on-responsive {
	display: none !important;
}
 */
@media ( max-width : 768px) {
	.visiable-on-responsive {
		display: block !important;
	}
}
</style>
</head>

<body ng-app="app" ng-controller="appController">
	<alert-message alert="alertMessage"></alert-message>
	<!-- Preloader -->
	<div class="page-loader-wrapper" ng-show="loader">
		<div class="loader">
			<div class="preloader">
				<img alt="" src="assets/skybus/images/bus.gif">
			</div>
			<!-- 	<p>Please wait...</p> -->
		</div>
	</div>
	<!-- End Preloader -->




	<!--navigation Area-->
	<!-- <div class="container-fluid">
		<nav class="navbar-default main-navbar-style pb-0px">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar1">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="" href=""> <img src="" id="logo" style="width: 21%; padding: 4px;">
				</a>
			</div>

			display nav list right after nav logo
			<ul class="nav navbar-nav  nav-list" style="display: none">
				<li><a href="#">Flight Search</a></li>
				<li><a href="flight">Booking Queues</a></li>
			</ul>
			

			<div id="navbar1" class="navbar-collapse collapse">
				<ul class="nav navbar-nav navbar-right nav-list">
					<li><a onClick="showDiv('div0')">Flight Search</a></li>
					<li><a href="flight">Booking Queues</a></li>
					<li><a href="#">Tickets Queues</a></li>
					<li><a href="#"> Change Request Queues</a></li>
					dropdown list (if it necessity )
					<li class="dropdown" ><a href="#"
						class="dropdown-toggle" data-toggle="dropdown" role="button"
						aria-expanded="false">Dropdown <span class="caret"></span></a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="#">Action</a></li>
							<li><a href="#">Another action</a></li>
							<li><a href="#">Something else here</a></li>
							<li><a href="#">Separated link</a></li>
							<li><a href="#">One more separated link</a></li>
						</ul></li>
					dropdown list (if it necessity )
				</ul>
			</div>
		</div>
		</nav>
	</div> -->
	<!--navigation Area-->

	<div class="showdiv_0dd">
		<div id="bus_search" class="flight-booking-form" style="background-image: url('assets/images/bubg.jpg');">
			<div class="container">
				<!-- <div class="row">
					<div class="col-md-12 fta_news">
						<marquee>For Flight Booking issue Sk Sharma mob +91-9811253330. +91-6289812191 | skshama. Edpl@gmail.com</marquee>
					</div>
				</div> -->
				<div class="row">
					<div class="sectionarea col-md-12">
						<div class="booking-form">	
							<form>
								<div class="form-head">
									<div class="form-group form-first-section">
										<div class="row">
											<div class="col-md-12"><div class="flightBooking-heading-style">ONLINE BUS BOOKING</div></div>
										</div>
									</div>
									<div class="row first-page-searching-section">
										<div class="col-md-3">
											<div class="row">
												<label>Source City</label>
												<input type="text" class="form-control ui-widget" placeholder="Source City">
											</div>
										</div>
										<div class="col-md-3">
											<div class="row">
												<label>Destination City</label>
												<input type="text" class="form-control ui-widget" placeholder="Destination City">
											</div>
										</div>
										<div class="col-md-3">
											<div class="row">
												<label>Travel Date</label>
												<input type="text" class="form-control" id="dp1" placeholder="Travel Date" readonly>
											</div>
										</div>
										<div class="col-md-3">
											<div class="row">
												<button id="bus_search_hb" type="button" class="btn-search" onclick="showDiv('div2')"><i class="fas fa-search" style="font-size: 20px;"></i> Search Bus</button>
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
	
	<div class="rowt" id="div2" style="display: none;">
		<div class="col-md-12">
			<div class="flight-list">
				<div class="container">
					<div class="row">
						<!--flight Search Filter section-->
						<div class="col-md-3 hidden-sm hidden-xs">
							<div class="search-filter" id="myHeader-e">
								<div class="search-filter-head">
									<h3>Filter</h3>
									<div class="clear-filter">
										<a href="#"> Clear all </a>
									</div>
								</div>
								
								<div class="panel-group" id="accordion1" role="tablist" aria-multiselectable="true">
								    <div class="panel panel-default">
								        <div class="panel-heading" role="tab" id="headingOne1">
								             <h4 class="panel-title">
										        <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne1" aria-expanded="true" aria-controls="collapseOne1">
										          	Price Range
										        </a>
								      		</h4>
								        </div>
								        <div id="collapseOne1" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne1">
								            <div class="panel-body">
								            	<rzslider rz-slider-model="slider_translate.minValue" rz-slider-high="slider_translate.maxValue" rz-slider-options="slider_translate.options"></rzslider>
								            </div>
								        </div>
								    </div>
								    <div class="panel panel-default">
								        <div class="panel-heading" role="tab" id="headingTwo">
								             <h4 class="panel-title">
										        <a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
										          	DEPARTURE TIME
										        </a>
										      </h4>
								        </div>
								        <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
								            <div class="panel-body">
								            	<ul class="dep_time">
								            		<li>
								            			<input type="radio">
							            				Before 6AM
								            		</li>
								            		<li>
								            			<input type="radio">
							            				6AM-12 Noon
								            		</li>
								            		<li>
								            			<input type="radio">
							            				12 Noon-6PM
								            		</li>
								            		<li>
								            			<input type="radio">
							            				After 6PM
								            		</li>
								            	</ul>
								            </div>
								        </div>
								    </div>
								    <div class="panel panel-default">
								        <div class="panel-heading" role="tab" id="headingThree">
								             <h4 class="panel-title">
											        <a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
											          BUS TYPE
											        </a>
										      </h4>
								        </div>
								        <div id="collapseThree" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree">
								            <div class="panel-body">
								            	<ul class="dep_time">
								            		<li>
								            			<input type="radio">
							            				AC
								            		</li>
								            		<li>
								            			<input type="radio">
							            				NON AC
								            		</li>
								            		<li>
								            			<input type="radio">
							            				Sleeper
								            		</li>
								            		<li>
								            			<input type="radio">
							            				Seater
								            		</li>
								            	</ul>
								            </div>
								        </div>
								    </div>
								    <div class="panel panel-default">
								        <div class="panel-heading" role="tab" id="headingfour">
								             <h4 class="panel-title">
											        <a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapsefour" aria-expanded="false" aria-controls="collapsefour">
											          BOARDING POINTS
											        </a>
										      </h4>
								        </div>
								        <div id="collapsefour" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingfour">
								            <div class="panel-body">BOARDING POINTS Comeing soon </div>
								        </div>
								    </div>
								    <div class="panel panel-default">
								        <div class="panel-heading" role="tab" id="headingfive">
								             <h4 class="panel-title">
											        <a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapsefive" aria-expanded="false" aria-controls="collapsefive">
											          DROPPING POINTS
											        </a>
										      </h4>
								        </div>
								        <div id="collapsefive" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingfive">
								            <div class="panel-body">DROPPING Comeing soon</div>
								        </div>
								    </div>
								    <div class="panel panel-default">
								        <div class="panel-heading" role="tab" id="headingsix">
								             <h4 class="panel-title">
											        <a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapsesix" aria-expanded="false" aria-controls="collapsesix">
											          BUS OPERATOR
											        </a>
										      </h4>
								        </div>
								        <div id="collapsesix" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingsix">
								            <div class="panel-body">
								            	<ul class="dep_time">
								            		<li>
								            			<input type="radio">
							            				BUS OPERATOR
								            		</li>
								            		<li>
								            			<input type="radio">
							            				BUS OPERATOR
								            		</li>
								            		<li>
								            			<input type="radio">
							            				BUS OPERATOR
								            		</li>
								            		<li>
								            			<input type="radio">
							            				BUS OPERATOR
								            		</li>
								            	</ul>
								            </div>
								        </div>
								    </div>
								</div>
							</div>
						</div>
						<!--/flight Search Filter section-->
	
	
						<!--flight search result list-->
						<div class="col-md-9">
							<!--owl date slider-->
							
							<!-- <div class="row pl-14 pr-14 hidden-sm hidden-xs">
								 <div class="price-slider mt-40">
									<div class="owl-carousel owl-theme">
										<div class="item pt_b" ng-repeat="chcalenderfare in chcalenderfare">
											<div class="bod"
												ng-click="fetchcalenderflight(chcalenderfare.DepartureDate);">
												<h4 class="n-margin item-date">{{chcalenderfare.DepartureDate|
													date:'MMM dd'}}</h4>
												<p class="n-margin item-price">Rs {{chcalenderfare.Fare}}</p>
											</div>
										</div>																	
									</div>
								</div>
							</div> -->
							
							<!--/owl date slider-->
	
							<div class="flight-list">
								<div class="flight-list-heading mt-20 mb-20  hidden-sm hidden-xs">
									<div class="row list-heading-row">
										<div class="col-md-7 col-sm-12">
											<p>Bangalore <i class="fas fa-arrow-right" style="font-size: 25px;"></i> Coimbatore</p>
										</div>
										<div class="col-md-5 col-sm-12">
											<span>Wednesday, 27 January 2021</span>
											<!-- <p>Bangalore <i class="fas fa-arrow-right" style="font-size: 30px;"></i> Coimbatore</p> -->
										</div>
									</div>
								</div>
	
								<div class="flight-list-heading mt-20 mb-20">
									<div class="row flight-result-list">
										<div class="col-md-12 flight-list-v2" style="padding: 0;">
											<div class="col-md-12">
												<div class="flight-list-main">
													<div class="row">
														<div class="col-md-12">
															<h4>Jihan luxury travels</h4>
															<p>A/C Sleeper (2+1)</p>
														</div>
													</div>
													<div class="row">
														<div class="col-md-3 col-sm-12 text-center airline">
															<strong>22:15</strong>
															<p>Bangalore</p>
														</div>
														<div class="col-md-1 col-sm-2 col-xs-4 departure text-center pt-20">
															<div class="row">
																<h3>
																	<i class="fas fa-arrow-right" style="font-size: 20px;"></i>
																	<span class="ft">06h 00m</span>
																</h3>
															</div>
														</div>
														<div class="col-md-3 col-sm-3 col-xs-4 airline text-center pt-20">
															<strong>05:15</strong>
															<p>Coimbatore</p>
														</div>
														<div class="col-md-2 col-sm-2 col-xs-4 destination text-center pt-30">
															<div class="row">
																<h3>
																	<i class="fas fa-rupee-sign" style="font-size: 20px;"></i>
																	<span class="ft">500</span>
																</h3>
															</div>
														</div>
														<div class="col-md-3 col-sm-3  col-xs-12  text-center pt-20">
															<div class="booking-btn">
																<button onclick="showDiv('div3')" class="bookbtn">Select Seats</button>
															</div>
														</div>
													</div>
												</div>
											</div>
											<div class="col-md-12 col-xs-12">
												<div class="flight-list-footer">
													<div class="col-md-12 col-sm-12">
														<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
													        <div class="panel panel-default">
													            <div class="panel-heading" role="tab" id="headingOne">
													                <h4 class="panel-title">
													                    <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
													                        Show Details
													                    </a>
													                </h4>
													            </div>
													            <div id="collapseOne" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne">
													                <div class="panel-body">
													                       <div class="col-md-12 col-sm-12 sm-invisible flight-list-footer_tab">
																				<ul class="nav nav-tabs">
																				    <li class="active"><a class="refund" data-toggle="tab" href="#amenities_dv">Amenities</a></li>
																				    <li><a class="refund" data-toggle="tab" href="#bdpoint_dv"> Boarding & Dropping Points </a></li>
																				    <!-- <li><a class="refund" data-toggle="tab" href="#canellation"> Canellation Policy </a></li> -->
																				 </ul>
																				 <div class="tab-content">
																				    <div id="amenities_dv" class="tab-pane fade in active">
																				      	<div class="row">
																				      		<div class="col-md-3">
																				      			<i class="fas fa-crosshairs" style="font-size: 20px;"></i>
																				      			<strong>GPS</strong>
																				      		</div>
																				      		<div class="col-md-3">
																				      			<i class="fas fa-prescription-bottle" style="font-size: 20px;"></i>
																				      			<strong>Water Bottle</strong>
																				      		</div>
																				      		<div class="col-md-3">
																				      			<i class="fas fa-charging-station" style="font-size: 20px;"></i>
																				      			<strong>Charging Point</strong>
																				      		</div>
																				      		<div class="col-md-3">
																				      			<i class="fas fa-band-aid" style="font-size: 20px;"></i>
																				      			<strong>Blanket</strong>
																				      		</div>
																				      	</div>
																				    </div>
																				    <div id="bdpoint_dv" class="tab-pane fade">
																				      	<div class="row">
																				      		<div class="col-md-6">
																				      			<h4>Boarding Points</h4>
																				      			<ul>
																				      				<li>(21:50) Opp To Innovative Multiplex, Multiplex Bus Stop, Towards SilkBoard Road</li>
																				      				<li>(21:50) Opp To Innovative Multiplex, Multiplex Bus Stop, Towards SilkBoard Road</li>
																				      				<li>(21:50) Opp To Innovative Multiplex, Multiplex Bus Stop, Towards SilkBoard Road</li>
																				      				<li>(21:50) Opp To Innovative Multiplex, Multiplex Bus Stop, Towards SilkBoard Road</li>
																				      				<li>(21:50) Opp To Innovative Multiplex, Multiplex Bus Stop, Towards SilkBoard Road</li>
																				      				<li>(21:50) Opp To Innovative Multiplex, Multiplex Bus Stop, Towards SilkBoard Road</li>
																				      			</ul>
																				      		</div>
																				      		<div class="col-md-6">
																				      			<h4>Droping Points</h4>
																				      			<ul>
																				      				<li>(21:50) Opp To Innovative Multiplex, Multiplex Bus Stop, Towards SilkBoard Road</li>
																				      				<li>(21:50) Opp To Innovative Multiplex, Multiplex Bus Stop, Towards SilkBoard Road</li>
																				      				<li>(21:50) Opp To Innovative Multiplex, Multiplex Bus Stop, Towards SilkBoard Road</li>
																				      				<li>(21:50) Opp To Innovative Multiplex, Multiplex Bus Stop, Towards SilkBoard Road</li>
																				      				<li>(21:50) Opp To Innovative Multiplex, Multiplex Bus Stop, Towards SilkBoard Road</li>
																				      				<li>(21:50) Opp To Innovative Multiplex, Multiplex Bus Stop, Towards SilkBoard Road</li>
																				      			</ul>
																				      		</div>
																				      	</div>
																				    </div>
																				    <!-- <div id="canellation" class="tab-pane fade">
																				      <h3>Menu 2</h3>
																				      <p>Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam.</p>
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
									</div>
								</div>
							</div>
						</div>
						<!--/Bus search result list-->
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<div class="rowt" id="div3" style="display: none;">
		<div class="col-md-12">
			<div class="flight-list">
				<div class="container">
					<div class="row">
						<!--flight search result list-->
						<div class="col-md-8">
							<div class="flight-list">
								<div class="flight-list-heading mt-20 mb-20  hidden-sm hidden-xs">
									<div class="row list-heading-row">
										<div class="col-md-7 col-sm-12">
											<p>Bangalore <i class="fas fa-arrow-right" style="font-size: 25px;"></i> Coimbatore</p>
										</div>
										<div class="col-md-5 col-sm-12">
											<span>Wednesday, 27 January 2021</span>
											<!-- <p>Bangalore <i class="fas fa-arrow-right" style="font-size: 30px;"></i> Coimbatore</p> -->
										</div>
									</div>
								</div>
	
								<div class="flight-list-heading mt-20 mb-20">
									<div class="row flight-result-list">
										<div class="col-md-12 flight-list-v2" style="padding: 0;">
											<div class="col-md-12">
												<div class="flight-list-main">
													<div class="row">
														<div class="col-md-12">
															<ul class="bus_seat">
																<li class="booked">
																	<img alt="" src="assets/images/busimg/bus1.png">
																	<strong>Booked</strong>
																</li>
																<li class="available">
																	<img alt="" src="assets/images/busimg/bus2.png">
																	<strong>Available</strong>
																</li>
																<li class="selected">
																	<img alt="" src="assets/images/busimg/bus5.png">
																	<strong>Selected</strong>
																</li>
																<li class="reserved">
																	<img alt="" src="assets/images/busimg/bus3.png">
																	<strong>Reserved for Ladies</strong>
																</li>
															</ul>
														</div>
													</div>
													<div class="row">
														<div class="col-md-12 col-sm-12">
															<div class="bus_car">
																<div class="col-md-2">
																	<img src="assets/images/busimg/bus_str.png">
																</div>
																<div class="col-md-10">
																	<strong class="upaar">UPPER</strong>
																	<ul class="bus_upper">
																		<li><a href="#">U1</a></li>
																		<li><a href="#">U3</a></li>
																		<li><a href="#">U5</a></li>
																		<li><a href="#">U7</a></li>
																		<li><a href="#">U9</a></li>
																		<li><a href="#">U11</a></li>
																		<li><a href="#">U2</a></li>
																		<li><a href="#">U4</a></li>
																		<li><a href="#">U6</a></li>
																		<li><a href="#">U8</a></li>
																		<li><a href="#">U10</a></li>
																		<li><a href="#">U12</a></li>
																	</ul>
																	<ul class="bus_upper m-t-30">
																		<li><a href="#">U13</a></li>
																		<li><a href="#">U14</a></li>
																		<li><a href="#">U15</a></li>
																		<li><a href="#">U16</a></li>
																		<li><a href="#">U17</a></li>
																		<li><a href="#">U18</a></li>
																	</ul>
																</div>
															</div>
														</div>
													</div>
													<div class="row">
														<div class="col-md-12 col-sm-12">
															<div class="bus_car">
																<div class="col-md-2">
																	<img src="assets/images/busimg/bus_str.png">
																</div>
																<div class="col-md-10">
																	<strong class="upaar">LOWER</strong>
																	<ul class="bus_upper">
																		<li><a href="#">L1</a></li>
																		<li><a href="#">L3</a></li>
																		<li><a href="#">L5</a></li>
																		<li><a href="#">L7</a></li>
																		<li><a href="#">L9</a></li>
																		<li><a href="#">L11</a></li>
																		<li><a href="#">L2</a></li>
																		<li><a href="#">L4</a></li>
																		<li><a href="#">L6</a></li>
																		<li><a href="#">L8</a></li>
																		<li><a href="#">L10</a></li>
																		<li><a href="#">L12</a></li>
																	</ul>
																	<ul class="bus_upper m-t-30">
																		<li><a href="#">L13</a></li>
																		<li><a href="#">L14</a></li>
																		<li><a href="#">L15</a></li>
																		<li><a href="#">L16</a></li>
																		<li><a href="#">L17</a></li>
																		<li><a href="#">L18</a></li>
																	</ul>
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
						<!--/Bus search result list-->
						
						<!--flight search result list-->
						<div class="col-md-4">
							<div class="flight-list">
								<div class="flight-list-heading mt-20 mb-20">
									<strong>
										<a onClick="showDiv('div2')" href="#" style="float: right;"><i class="fas fa-arrow-left"></i> Back</a>
									</strong>
									<div class="rowhfg">
										<div class="flight-result-list mt-70" style="float: left;">
											<div class="col-md-12 flight-list-v2">
												<div class="col-md-12">
													<div class="flight-list-main">
														<div class="row">
															<div class="col-md-12 mt-30">
																<label> Select a Boarding Point </label>
																<select class="form-control">
																	<option>--- Select ---</option>
																</select>
															</div>
															<div class="col-md-12 mt-30">
																<label> Select a Dropping Point</label>
																<select class="form-control">
																	<option>--- Select ---</option>
																</select>
															</div>
															<div class="col-md-12 mt-30">
																<label> Select Seats</label>
															</div>
															<div class="col-md-12 col-xs-12">
																<table class="table">
																	  <thead>
																	    <tr>
																	      <th scope="col">Total Amount</th>
																	      <th scope="col">RS/ 450</th>
																	    </tr>
																	  </thead>
																</table>
															</div>
															<div class="col-md-12 col-xs-12">
																<div class="flight-list-footer">
																	<div class="col-md-12 col-sm-12">
																		<a onclick="showDiv('div4')" class="conti" href="#">Continue</a>
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
						</div>
						<!--/Bus search result list-->
						
					</div>
				</div>
			</div>
		</div>
	</div>
	
	
	<div id="div4" style="display: none">
		<div class="container">
			<div class="row">
				<div class="ticket-booking-process-nav">
					 <ul class="breadcrumb booking-process">
						<li><a  onClick="showDiv('div0')">1. Search</a></li>
						<li class="active">2. Payment</li>
						<li style="float: right;"><a  onClick="showDiv('div3')" style="cursor: pointer;"><i class="fas fa-arrow-left"></i> Back</a></li>
					</ul>
				</div>
			</div>

			<div class="row">
				<!-- flight-ticket-details -->
				<div class="col-md-9 new_fdetails">
					<div class="panel panel-default flight-ticket-card">
						<div class="panel-heading flight-ticket-details-head">
							<div class="flight-card-icon">
								<i class="fa fa-bus"></i> 
								<span class="icon-details"> Bus Details</span>
							</div>
						</div>

						<div class="panel-body" style="border-bottom: 1px solid #ddd;">
							<div class="row">
								<div class="col-md-7">
									<div class="flight-path">
										<i class="fa fa-bus"></i> 
										<span class="bus_dop">Bangalore <i class="fas fa-arrow-right" style="font-size: 15px;margin: 0 10px;"></i> Coimbatore</span>
										<p class="date_bus">30-01-2021 </p>
									</div>
								</div>
								<div class="col-md-5">
									<span class="bus_oparetar">
										Operator: A1 Travels 2+1 A/C Capella Sleeper
									</span>
								</div>
							</div>
							<div class="row">
								<div class="col-md-2 text-center bus_details_1">
									<span>Boarding Time</span>
									<strong>14:00</strong>
								</div>
								<div class="col-md-3 text-center bus_details_1">
									<span>
										<img alt="" src="assets/ images/busimg/bus-rou-map.png">
									</span>
									<strong>07h 15m</strong>
								</div>
								<div class="col-md-2 text-center bus_details_1">
									<span>Dropping Time</span>
									<strong>14:00</strong>
								</div>
								<div class="col-md-2 text-center bus_details_1">
									<span>Seat no(s)</span>
									<strong>U12</strong>
								</div>
								<div class="col-md-3 text-center bus_details_1">
									<span>Passengers(s)</span>
									<strong>3</strong>
								</div>
							</div>
						</div>
					</div>
				<!-- -------passenger meal -->
					<div class="panel panel-default flight-ticket-card travellers-information-details">
						<div class="panel-heading travellers-information-head">
							<div class="row">
								<div class="col-md-12">
									<div class="flight-card-icon">
										<p class="n-margin">
											<i class="fa fa-user"></i> <span>Travellers Details</span>
										</p>
									</div>
								</div>
							</div>
						</div>
						<div class="panel-body travellers-count">
							<ul>
								<li>
									<div class="panel panel-warning passenger-details-form">

										<div class="panel-body passenger-details-form-body">
											<div class="single-passenger-details">

												<form>
													<div class="form-group">
														<div class="row">
															<div class="col-md-6">
																<label class="passenger-form-label">Mobile No</label> <input
																	type="text" class="form-control passenger-form-control"
																	ng-model="ticket.mobileNo" maxlength="10">
															</div>
															<div class="col-md-6">
																<label class="passenger-form-label">Email:</label> <input
																	type="text" class="form-control passenger-form-control"
																	ng-model="ticket.emailid">
															</div>
														</div>
														<div class="row">
															<div class="col-md-6">
																<label class="passenger-form-label">City</label> <input
																	type="text" class="form-control passenger-form-control"
																	ng-model="ticket.city">
															</div>
															 <div class="col-md-6">
																<button type="button" class="btn btn-info waves-effect" data-toggle="modal" data-target="#squarespaceModal"
																	ng-click="bookingFlightmodal(ticket)"
																	style="margin-top: 18px;">Continue Booking</button>
															</div> 
														</div>
													</div>
												</form>
											</div>
										</div>
									</div>
								</li>
							</ul>
						</div>
					</div>
				</div>
				<!-- /flight-ticket-details -->

				<!-- ticket-price-details -->
				<div class="col-md-3 new_fdetails">
					<div class="ticket-price-details">
						<div class="panel panel-default flight-ticket-card">
							<div class="panel-heading flight-ticket-details-head">
								<div class="flight-card-icon">
									<i class="fas fa-money-bill-wave-alt" style="font-size: 20px;"></i> <span
										class="icon-details"> Price Summary</span>
								</div>
							</div>
							<div class="panel-body">
								<div class="price-panel-body">
									<table class="table price-panel-body-table">
										<tbody>
											<tr>
												<td class="tb-boder">BaseFare x 3</td>
												<td class="text-right tb-boder">INR.1800</td>
											</tr>
											<tr>
												<td class="tb-boder">GST & Operator Fees</td>
												<td class="text-right tb-boder">INR.
													80</td>
											</tr>
											<th>Grand Total</th>
											<th>1880</th>
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
	
	<!-- angular link -->
	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<!-- <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script> -->
	<!-- <script src="assets/skyflight/js/j.js"></script> -->
	<script src="assets/js/angular.min.js"></script>
	<script src="assets/js/angular-sanitize.min.js"></script>
	<script src="assets/js/jquery-ui.js"></script>
	<script src="assets/js/cookieStore.js"></script>
	<script src="assets/js/alasql.min.js?version=1.0.0.2"></script>
	<script src="assets/js/xlsx.core.min.js?version=1.0.0.2"></script>
	<script src="assets/skybus/js/bootstrap.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/angular-ui-bootstrap/0.14.3/ui-bootstrap-tpls.js"></script>
	<script src="https://rawgit.com/rzajac/angularjs-slider/master/dist/rzslider.js"></script>
	<script src="assets/scripts/angular_skybus.js?version=1.0.5"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/owl-carousel/1.3.3/owl.carousel.js"></script>
	<!-- Owl Carousel Min JS -->
	<script src="assets/skybus/js/owl.carousel.min.js"></script>
	<!-- Nice Select Min JS -->
	<script src="assets/skybus/js/jquery-ui.min.js"></script>
	<!-- Main JS -->
	<script src="assets/skybus/js/custom.js"></script>
	<script src="assets/skybus/js/main.js"></script>

	
	<script>
		$(document).ready(function(){
		  $("#bus_search_hb").click(function(){
		    $("#bus_search").addClass("bus_search_height");
		  });
		});
	</script>
	
	
	<script type="text/javascript">
		/*$(document).ready(function() {			
				 var minDate = new Date();
				$("#dp1").datepicker({
					showAnim : 'fold',
					minDate : minDate,
					dateFormat : 'yy-mm-dd',
					onClose : function(selectedDate) {
						$('#dp2').datepicker('option', 'minDate', selectedDate)
					}
				});

				$("#dp2").datepicker({
					showAnim : 'fold',
					dateFormat : 'yy-mm-dd',
					minDate : minDate,
				});

			});*/
	</script>
	<script>
		window.onscroll = function() {myFunction()};
		
		var header = document.getElementById("myHeader");
		var sticky = header.offsetTop;
		
		function myFunction() {
		  if (window.pageYOffset > sticky) {
		    header.classList.add("sticky");
		  } else {
		    header.classList.remove("sticky");
		  }
		}
</script>


	<script type="text/javascript">
		$(document).ready(function() {
			var minDate = new Date();
			$("#dp1").datepicker({
				dateFormat : 'yy-mm-dd',
				minDate : minDate,
				onClose : function(selectedDate) {
					$('#dp2').datepicker('option', 'minDate', selectedDate)
				}
			});

			$("#dp2").datepicker({
				dateFormat : 'yy-mm-dd',
			});

			$("#oneway").change(function() {
				$('#dp2').prop('disabled', true);
			});

			$("#roundtrip").change(function() {
				$('#dp2').removeProp('disabled');
			});

			$("#dp3").datepicker({
				dateFormat : 'yy-mm-dd',
			});

			$("#dp4").datepicker({
				dateFormat : 'yy-mm-dd',
			});
			
			$("#dp5").datepicker({
				dateFormat : 'yy-mm-dd',
			});

			$("#dp6").datepicker({
				dateFormat : 'yy-mm-dd',
			});
			
			
			$("#adob1").datepicker({
				dateFormat : 'yy-mm-dd',
			});
			
			
			$("#sample").datepicker({
				dateFormat : 'yy-mm-dd',
			});
			
			
			$("#adultdob3").datepicker({
				dateFormat : 'yy-mm-dd',
			});
			
			
			$("#adultdob4").datepicker({
				dateFormat : 'yy-mm-dd',
			});
			
			
			$("#adultdob5").datepicker({
				dateFormat : 'yy-mm-dd',
			});
			
			
			$("#adultdob6").datepicker({
				dateFormat : 'yy-mm-dd',
			});
			
			
			$("#adultdob7").datepicker({
				dateFormat : 'yy-mm-dd',
			});
			
			
		
			
			/* function (id) {
				var dateid = "#adultdob"+id;
				$("dateid[id^=adultdob]").datepicker({
					dateFormat : 'yy-mm-dd',
				});
			}; */
			
			 /* var id = {};
			 var dateid = "#adultdob"+id;
			
			$("dateid[id^=adultdob]").datepicker({
				dateFormat : 'yy-mm-dd',
			});  */
		})
	</script>

	<script type="text/javascript">
		$(document).ready(function() {
			$('.myDate').datepicker({
				startDate : '-2m',
				endDate : '+0d'
			});
		})
	</script>



	<script type="text/javascript">	
		$('#seatsCol').hide();
		$(function seatsCollapes(){
			$(".flight-seat-collapes-button button").click(function(){
			    $("#seatsCol").toggle(1000);
			  });

			});	
	</script>
	
	<!-- <script type="text/javascript">
		$('.owl-2').owlCarousel({
		    loop:true,
		    margin:10,
		    responsiveClass:true,
		    responsive:{
		        0:{
		            items:1,
		            nav:true
		        },
		        600:{
		            items:3,
		            nav:true
		        },
		        1000:{
		            items:7,
		            nav:true,
		            loop:false,
		            dots: false,
		        }
		    }
		})
	</script> -->
	
	<script type="text/javascript">
	$(function() {
		   var $slider = $("#slider-range");
		   //Get min and max values
		   var priceMin = $slider.attr("data-min"),
		      priceMax = $slider.attr("data-max");

		   //Set min and max values where relevant
		   $("#filter-min, #filter-max").map(function(){
				$(this).attr({
					"min": priceMin,
					"max": priceMax
				});
			});
			$("#filter-min").attr({
				"placeholder": "min " + priceMin,
				"value": priceMin
			});
			$("#filter-max").attr({
				"placeholder": "max " + priceMax,
				"value": priceMax
			});

		   $slider.slider({
		      range: true,
		      min: Math.max(priceMin, 0),
		      max: priceMax,
		      values: [priceMin, priceMax],
		      slide: function(event, ui) {
		         $("#filter-min").val(ui.values[0]);
		         $("#filter-max").val(ui.values[1]);
		      }
		   });
			$("#filter-min, #filter-max").map(function(){
				$(this).on("input", function() {
					updateSlider();
				});
			});
			function updateSlider(){
				$slider.slider("values", [$("#filter-min").val(), $("#filter-max").val()]);
			}
			
		});	
	</script>

<!--  <script type="text/javascript">
		const toggle = document.querySelector('.collapse-icon');
		
		toggle.addEventListener('click', function (e) {
			 console.log(e.target)
			 
		});
		
		
    </script> -->
    
    <!-- <script>
     $(document).ready(function(){
        $('.dropdown-toggle').dropdown()
    });
</script> -->
	<!-- my css  -->

</body>

</html>