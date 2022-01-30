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
<link rel="stylesheet" href="assets/skyflight/css/bootstrap.min.css">

<!-- Bootstrap Min CSS -->
<!-- Font Awesome Min CSS -->
<link rel="stylesheet" href="assets/skyflight/css/fontawesome.min.css">
<!-- Owl Carousel Min CSS -->
<link rel="stylesheet" href="assets/skyflight/css/owl.carousel.min.css">

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/owl-carousel/1.3.3/owl.carousel.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/owl-carousel/1.3.3/owl.theme.css">
<!-- Owl jquery-ui Min CSS -->
<link rel="stylesheet" href="assets/skyflight/css/jquery-ui.min.css">
<!-- <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css"> -->
<!-- Style CSS -->
<link rel="stylesheet" href="assets/skyflight/css/style.css">
<!-- Responsive CSS -->
<link rel="stylesheet" href="assets/skyflight/css/responsive.css">

<link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet"
          integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous"><base>
 <link href="https://rawgit.com/rzajac/angularjs-slider/master/dist/rzslider.css" rel="stylesheet">

<title>Flight</title>
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
	width: 43%;
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
				<img alt="" src="assets/skyflight/images/plane.gif">
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

	<div id="div0">
		<div class="flight-booking-form" style="background-image: url('assets/images/fbg.png');">

			<div class="container">
				<div class="row">
					<div class="col-md-12 fta_news">
						<marquee>For Flight Booking issue Sk Sharma mob +91-9811253330. +91-6289812191 | skshama. Edpl@gmail.com</marquee>
					</div>
				</div>
				<div class="row">
					<div class="sectionarea col-md-12">
						<div class="col-lg-6">
							<div class="booking-form">	
								<form>
									<div class="form-head">
										<div class="form-group form-first-section">
											<div class="row">
												<div class="col-md-6"><div class="flightBooking-heading-style">FLIGHT BOOKING</div></div>
												<div class="col-md-6">

													<div class="styled-radio styled-radio2">
														<!--   <input type="radio" id="radio_9" class="radio-col oneWay" checked ng-if="flight.type1!=null"/> 
 -->
														<input type="radio" ng-model="flight.type" value="O"
															id="oneway" name="selector1" ng-click="myOneWay(flight)">
														<label for="oneway">OneWay</label>
													</div>

													<div class="styled-radio styled-radio2">
														<!--    <input name="group5" ng-type="radio" id="radio_10" class="radio-col roundTrip" checked ng-if="flight.type2!=null"/> -->
														<input type="radio" ng-model="flight.type" id="roundtrip"
															value="R" name="selector1" ng-click="myRoundTrip(flight)">
														<label for="roundtrip">Round Trip</label>
													</div>

													<!--<label class="radio-inline"><input type="radio" name="optradio" checked>OneWay</label>
                                            <label class="radio-inline"><input type="radio" name="optradio">Round Trip</label>-->
												</div>
											</div>
										</div>
									</div>

									<div class="form-group">
										<div class="row">
											<div class="col-md-6">
												<div class="first-page-searching-section">
													<label>Depart From</label><input type="text"
														id="sourceCity" ng-model="flight.source"
														class="form-control ui-widget"
														ng-keyup="getCityAutoFill(flight.source, 'sourceCity');">
												</div>
											</div>

											<div class="col-md-6">
												<div class="first-page-searching-section">
													<label>Going To</label> <input type="text" id="destiCity"
														class="form-control ui-widget"
														ng-keyup="getCityAutoFill(flight.destination, 'destiCity');"
														ng-model="flight.destination">
												</div>
											</div>
										</div>
									</div>
									
									

									<div class="form-group">
										<div class="row">
											<div class="col-md-6">
												<div class="first-page-searching-section">
													<label>Depart Date</label> <input type="text"
														class="form-control" ng-model="flight.depart" id="dp1"
														readonly>
												</div>
											</div>

											<div class="col-md-6">
												<div class="first-page-searching-section">
													<label>Return Date</label> <input type="text"
														class="form-control" ng-model="flight.return" id="dp2"
														readonly>
												</div>
											</div>
										</div>
									</div>


									<div class="form-group">
										<div class="row">
											<div class="col-md-4">
												<div class="first-page-searching-section">
													<label>Adults<small> (12+ Years)</small></label>
													<div class="input-group">
														<span class="input-group-btn">
															<button type="button" id="adultMinus" ng-if="flight.adult >=1"
																class="adult-left-minus btn btn-danger btn-number"
																data-type="minus" data-field=""
																ng-click="flight.adult = flight.adult - 1; checkAdultCountMinus(flight.adult); checkTotalAdultChildCount(flight.adult, flight.child);">
																<span class="glyphicon glyphicon-minus"></span>
															</button>
														</span> <input type="text" class="form-control input-number adt"
															id="adultcount" ng-model="flight.adult" value="1" min="1"
															ng-init="flight.adult=1"> <span
															class="input-group-btn">
															<button type="button" id="adultPlas"
																class="adult-right-plus btn btn-danger btn-number"
																data-type="plus" data-field=""
																ng-click="flight.adult = flight.adult + 1; checkAdultCountPlus(flight.adult); checkTotalAdultChildCount(flight.adult, flight.child);">
																<span class="glyphicon glyphicon-plus"></span>
															</button>
														</span>
													</div>

													<!-- <label>Adults<small> (12+ Years)</small></label> <input
																			type="text" class="form-control"
																			ng-model="flight.adult" min="1"> -->
													<!-- <input type="text" ng-model="flight.adult" class="form-control input-number" style="width: 50px;text-align: center;height: auto;border: none;" min="1"> -->
												</div>
											</div>

											<div class="col-md-4">
												<div class="first-page-searching-section">
													<label>Child <small>( 2-12 YRS )</small></label>
													<div class="input-group">
														<span class="input-group-btn">
															<button type="button" ng-if="flight.child >0"
																class="child-left-minus btn btn-danger btn-number"
																data-type="minus" data-field=""
																ng-click="flight.child = flight.child - 1; checkChildCountMinus(flight.child); checkTotalAdultChildCount(flight.adult, flight.child);">
																<span class="glyphicon glyphicon-minus"></span>
															</button>
														</span> <input type="text" class="form-control input-number cld"
															value="0" id="childcount" ng-model="flight.child">
														<span class="input-group-btn" ng-init="flight.child=0">
															<button type="button"
																class="child-right-plus btn btn-danger btn-number"
																data-type="plus" data-field=""
																ng-click="flight.child = flight.child + 1;  checkChildCountPlus(flight.child); checkTotalAdultChildCount(flight.adult, flight.child);">
																<span class="glyphicon glyphicon-plus"></span>
															</button>
														</span>
													</div>
													<!-- <input	type="text" class="form-control" ng-model="flight.child"> -->
												</div>
											</div>

											<div class="col-md-4">
												<div class="first-page-searching-section">
													<label>Infants <small>( Below 2 YRS )</small></label>
													<div class="input-group">
														<span class="input-group-btn">
															<button type="button" ng-if="flight.infant >0"
																class="infant-left-minus btn btn-danger btn-number"
																data-type="minus" data-field=""
																ng-click="flight.infant = flight.infant - 1;">
																<span class="glyphicon glyphicon-minus"></span>
															</button>
														</span> <input type="text" class="form-control input-number inf"
															value="0" id="infantcount" ng-model="flight.infant"
															ng-init="flight.infant=0"> <span
															class="input-group-btn">
															<button type="button"
																class="infant-right-plus btn btn-danger btn-number"
																data-type="plus" data-field=""
																ng-click="flight.infant = flight.infant + 1;">
																<span class="glyphicon glyphicon-plus"></span>
															</button>
														</span>
													</div>
													<!-- <input type="text" class="form-control"
																			ng-model="flight.infant"> -->
													<div class="first-page-searching-section"></div>
												</div>
											</div>
										</div>
									</div>





									<div class="form-group">
										<div class="row">
											<div class="col-md-12">
												<div class="first-page-searching-section">
													<label>Travel Class</label> <select class="form-control"
														ng-model="flight.class" ng-init="flight.class='ECONOMY'">
														<!--   <option ng-if="requestDetails.class != null" value="{{modify.class}}">{{modify.class}}</option>
 -->
														<option value="ECONOMY">ECONOMY</option>
														<option value="BUSINESS">BUSINESS</option>
														<option value="FIRSTCLASS">FIRSTCLASS</option>
														<option value="PREMIUMECONOMY">PREMIUMECONOMY</option>
													</select>
												</div>
											</div>
										</div>
									</div>



									<div class="form-group">
										<div class="row">
											<div class="col-md-12">
												<div class="first-page-searching-section">
													<button type="button"
														class="btn btn-info waves-effect pull-right"
														ng-click="searchFlight(flight)">Search Flight</button>
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

	
	<div id="div2" style="display: none">
		<div class="flight-list-page-search">
			<div class="container">
				<div class="row">
					<div class="flight-search-form">
						<form>
							<div class="form-group">
								<div class="row">
									<div class="col-md-2">
										<input type="radio" ng-model="flight.type" value="O"
											id="roun" name="selector1" ng-click="myOneWay()"> <label
											for="roun">OneWay</label>
									</div>

									<div class="col-md-2">
										<!--    <input name="group5" ng-type="radio" id="radio_10" class="radio-col roundTrip" checked ng-if="flight.type2!=null"/> -->
										<input type="radio" ng-model="flight.type" id="roundtrip"
											value="R" name="selector1" ng-click="myRoundTrip(flight)">
										<label for="roundtrip">Round Trip</label>
									</div>
								</div>

								<div class="row">
									<div class="col-md-2">
										<label>Depart From</label> <input type="text"
											class="form-control" ng-model="flight.source">
									</div>

									<div class="col-md-2">
										<label>Going To</label> <input type="text"
											class="form-control" ng-model="flight.destination">
									</div>

									<div class="col-md-2">
										<label>Depart Date</label> <input type="text"
											class="form-control" ng-model="flight.depart" id="dp5"
											readonly="readonly">
									</div>

									<div class="col-md-2">
										<label>Return Date</label> <input type="text"
											class="form-control" ng-model="flight.return" id="dp6"
											readonly="readonly">
									</div>

									<div class="col-md-2">
										<label>Adults<small> (12+ Years)</small></label> 
											<div class="row">
												<div class="col-md-3">
													<span class="input-group-btn">
														<button type="button"
															class="infant-right-plus btn btn-danger btn2-number"
															data-type="plus" ng-if="flight.adult >1" ng-click="flight.adult = flight.adult - 1;">
															<span class="glyphicon glyphicon-minus"></span>
														</button>
													</span>
												</div>
												<div class="col-md-6">
													<input type="text" class="form-control"
												    readonly="readonly" ng-model="flight.adult">
												</div>
												<div class="col-md-3">
													<span class="input-group-btn">
														<button type="button"
															class="infant-right-plus btn btn-danger btn2-number"
															data-type="plus" ng-click="flight.adult = flight.adult + 1;">
															<span class="glyphicon glyphicon-plus"></span>
														</button>
													</span>
												</div>
											</div>
											
									</div>

									<div class="col-md-2">
										<label>Child <small>( 2-12 YRS )</small></label>
											<div class="row">
												<div class="col-md-3">
													<span class="input-group-btn">
														<button type="button"
															class="infant-right-plus btn btn-danger btn2-number"
															data-type="plus" ng-if="flight.child >0" ng-click="flight.child = flight.child - 1;">
															<span class="glyphicon glyphicon-minus"></span>
														</button>
													</span>
												</div>
												<div class="col-md-6">
													<input type="text" class="form-control"
												    readonly="readonly" ng-model="flight.child">
												</div>
												<div class="col-md-3">
													<span class="input-group-btn">
														<button type="button"
															class="infant-right-plus btn btn-danger btn2-number"
															data-type="plus" ng-click="flight.child = flight.child + 1;">
															<span class="glyphicon glyphicon-plus"></span>
														</button>
													</span>
												</div>
											</div>
											
									</div>

								</div>

								<div class="row">
									<div class="col-md-2">
										<label>Infants <small>( Below 2 YRS )</small></label>
											<div class="row">
												<div class="col-md-3">
													<span class="input-group-btn">
														<button type="button"
															class="infant-right-plus btn btn-danger btn2-number"
															data-type="plus" ng-if="flight.infant >0" ng-click="flight.infant = flight.infant - 1;">
															<span class="glyphicon glyphicon-minus"></span>
														</button>
													</span>
												</div>
												<div class="col-md-6">
													<input type="text" class="form-control"
												    readonly="readonly" ng-model="flight.infant">
												</div>
												<div class="col-md-3">
													<span class="input-group-btn">
														<button type="button"
															class="infant-right-plus btn btn-danger btn2-number"
															data-type="plus" ng-click="flight.infant = flight.infant + 1;">
															<span class="glyphicon glyphicon-plus"></span>
														</button>
													</span>
												</div>
											</div> 
											
									</div>

									<div class="col-md-3">
										<label>Traveller(s), Class</label> <input type="text"
											class="form-control" ng-model="flight.class">
									</div>
									<div class="col-md-3">
										<button class="bookbtn mt-20" ng-click="searchFlight(flight)">Searching</button>
									</div>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>

		<!-- round trip filter  -->
		<div class="round-trip-filter-div s-filter">
			<div class="container">
				<div class="row">
					<div class="col-md-12">
						<div class="rtflight-list-info mr_tp">
							<div class="row">
								<div class="col-md-2">
									<center>Filter:</center>
								</div>
								<div class="col-md-10">
									<ul class="list-inline roundTrip-ul">
										
										 <li class="bord ghjgvj">
											
										<div class="dropdown roundTrip">
												<div class="dropdown-toggle" type="button"
													data-toggle="dropdown">
													Price <span class="caret"></span>
												</div>
												 <ul class="dropdown-menu" style="padding: 25px 14px;">
												 <li>
												 	 <rzslider rz-slider-model="roundtrip_slider_translate.minValue" rz-slider-high="roundtrip_slider_translate.maxValue" rz-slider-options="roundtrip_slider_translate.options"></rzslider>
												 </li>
												 
											 	 <li>
											 	 	 <rzslider rz-slider-model="roundtrip_down_slider_translate.minValue" rz-slider-high="roundtrip_down_slider_translate.maxValue" rz-slider-options="roundtrip_down_slider_translate.options"></rzslider>
											 	 
											 	 </li>
												 
													<!-- <li ng-repeat="empLnm in filtername"><span
														class="multi-range"> <input class="in_tp"
															type="range" min="{{min}}" max="{{max}}"
															ng-model="pricerangemin" value="{{min}}" id="lower">

															<input class="in_tp" type="range" min="{{min}}"
															max="{{max}}" value="{{max}}" ng-model="pricerangemax"
															id="upper">
													</span>

														<div class="mb-10">Rs. {{pricerangemin}}Rs. {{pricerangemax}}</div></li> -->
												</ul> 
											</div>

										</li>
										<li class="bord">
											<div class="dropdown roundTrip">
												<div class="dropdown-toggle" type="button"
													data-toggle="dropdown">
													Airlines <span class="caret"></span>
												</div>
												<ul class="dropdown-menu">
													<li ng-repeat="empLnm in filtername">
														<div class="checkbox">
															<label><input type="checkbox"
																name="flightname{{$index}}"
																data-ng-model="nameChkBox.loc[$index]"
																ng-true-value="'{{empLnm}}'" ng-false-value="" />
																{{empLnm}}</label>
														</div>
													</li>

												</ul>
											</div>
										</li>

										<li class="bord">
											<div class="dropdown roundTrip">
												<div class="dropdown-toggle" type="button"
													data-toggle="dropdown">
													Stops <span class="caret"></span>
												</div>
												<ul class="dropdown-menu d-wid">
													<li ng-repeat="empL in filterstop" style="width: 100%;">
														<div class="checkbox">
															<label class="dis_c"><input type="checkbox"
																name="locations{{$index}}"
																data-ng-model="locChkBox.loc[$index]"
																ng-true-value="'{{empL}}'" ng-false-value=""
																ng-change="repopulate()" /> {{empL}} Stops</label>

														</div>
													</li>

												</ul>
											</div>
										</li>

										<li>
											<div class="dropdown roundTrip">
												<div class="dropdown-toggle" type="button"
													data-toggle="dropdown">
													Arrival <span class="caret"></span>
												</div>
												<ul class="dropdown-menu">
													<li style="width: 100%;">
														<p>Arrival</p>
														<div class="checkbox">
															<label class="dis_c"> <input type="checkbox"
																name="" />Before 6AM
															</label>
														</div>
														<p class="bo_top">Depart</p>
														<div class="checkbox">
															<label class="dis_c"> <input type="checkbox"
																name="" />Before 6AM
															</label>
														</div>
													</li>

												</ul>
											</div>
										</li> 

									</ul>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!--  -->


		<div class="roundtrip-flight-list">
			<div class="container">
				<div class="row">
					<div class="col-md-6" style="margin-bottom: 13%;">
						<!--roundtrip-flight-list-info-->
						<div class="rtflight-list-info mt-30">
							<div class="row">
								<div class="col-md-4">
									<div class="travel-path-head">
										<p class="n-margin">{{flight.source}} -
											{{flight.destination}}</p>
									</div>
								</div>

								<div class="col-md-4">
									<div class="travel-duration-head">
										<p class="n-margin">{{ flight.depart | date : "fullDate"
											}}</p>
									</div>
								</div>

								<div class="col-md-4">
									<div class="travel-date-head">
										<span> <a href="#">Previous Day</a> | <a href="#">Next
												Day</a>
										</span>
									</div>
								</div>
							</div>
						</div>
						<!--/roundtrip-flight-list-info-->

						<!--roundtrip-flight-list-heading-->
						<div class="rtflight-list-haed mt-20">
							<div class="row">
								<div class="col-md-2 col-sm-2 text-center">
									<p class="n-margin">Airline</p>
								</div>
								<div class="col-md-2 col-sm-2 text-center">
									<p class="n-margin">Departs</p>
								</div>
								<div class="col-md-3 col-sm-3 text-center">
									<p class="n-margin">Duration</p>
								</div>
								<div class="col-md-2 col-sm-2 text-center">
									<p class="n-margin">Arrives</p>
								</div>
							</div>
						</div>
						<!--/roundtrip-flight-list-heading-->

						<!--roundtrip-flight-result-list-heading-->
						<div class="rtflight-result-list mt-20"
							ng-repeat="flights1 in upList| filter:pricerange  | filter :stopFilter | filter:fnameFilter"
							ng-show="flights1.FareBreakdown[0].TripIndicator==='1'">
							<div class="row">
								<div class="flight-list-v2">

									<div class="col-md-12">
										<div class="flight-list-main">
											<div class="col-md-2 col-sm-2 text-center airline">
												<img
													src="assets/skyflight/images/flightIcon/{{flights1.AirlineId}}.png">
												<h6>{{flights1.FlightSegments[0].AirlineCode}}-{{flights1.FlightSegments[0].FlightNumber}}</h6>
											</div>
											<div class="col-md-2 col-sm-2 departure text-center pt-20">
												<h3>
													<i class="fa fa-plane"></i>{{sourceDetail[0]}}
													{{flights1.FlightSegments[0].DepartureTime}}
												</h3>
												<h5 class="bold">{{flights1.FlightSegments[0].DepartureDate}}</h5>
												<h5></h5>
											</div>
											<div
												class="col-md-3 col-sm-3 stop-duration text-center pt-20">
												<div class="flight-direction"></div>
												<div class="stop"></div>
												<div class="stop-box">
													<span>{{flights1.FlightSegments.length-1}} Stop</span>
												</div>
												<div class="duration text-center">
													<span><i class="fa fa-clock-o"></i>
														{{flights1.totalduration}}</span>
												</div>
											</div>
											<div class="col-md-2 col-sm-2 destination text-center pt-20">
												<h3>
													<i class="fa fa-plane"></i>{{destinationDetail[0]}}
													{{flights1.FlightSegments[0].ArrivalTime}}
												</h3>
												<h5 class="bold">{{flights1.FlightSegments[0].ArrivalDate}}</h5>
												<h5></h5>
											</div>

											<div class="col-md-3 col-sm-3  text-center pt-20">
												<div class="booking-Price">
													<h3>INR {{flights1.Fare.TotalAmount}}</h3>
												</div>
												<div class="booking-btn pt-10">
													<input name="group5" type="radio" id="radio_30"
														class="with-gap radio-col-red" checked=""
														ng-click="flightsection1change(flights1,flights,flight)">
												</div>
											</div>
										</div>
									</div>


									<div class="col-md-12">
										<div class="flight-list-footer">
											<div class="col-md-6 col-sm-6 sm-invisible">
												<span><i class="fa fa-plane"></i> {{flight.class}}
													CLASS</span><span class="refund" ng-if="flights1.IsLCC===true" tooltip="LOW COST CARRIER"><i class="fa fa-suitcase"></i>
														LCC</span> <span class="refund" ng-if="flights1.IsLCC===false" tooltip="NON LOW COST CARRIER"><i class="fa fa-suitcase"></i>
														NON LCC</span>
												<!-- <span
													class="refund"><i class="fa fa-undo"></i> Refundable</span> -->

											</div>
											<div class="col-md-6 col-sm-6 col-xs-12 clear-padding">
												<div class="pull-right">
													<a id="rounddetail{{$index}}" href="javascript:"
														ng-click="roundFlightDetailTabs($index);">Show Details
														+</a>
												</div>


											</div>
										</div>
									</div>


								</div>
								<div class="flight-details-tab" id="roundtabDetail{{$index}}"
									style="display: none;">
									<div class="col-md-12">
										<ul class="nav nav-tabs">
											<li class="active"><a data-toggle="tab"
												href="#roundhome{{$index}}">Flight Information</a></li>
											<li><a data-toggle="tab" href="#roundmenu1{{$index}}">Fare
													Details</a></li>
											<li><a data-toggle="tab" href="#roundmenu2{{$index}}">Baggage
													Information</a></li>
											<!-- <li><a data-toggle="tab" href="#roundmenu3{{$index}}">My Discount
											</a></li> -->
										</ul>

										<div class="tab-content">
											<div id="roundhome{{$index}}" class="tab-pane fade in active">
												<div class="table-responsive">
													<table class="table table-bordered">
														<thead ng-repeat="segment in  flights1.FlightSegments">
															<tr>
																<th>
																	<div class="tabp-finfo-fimg text-center">
																		<img
																			src="assets/skyflight/images/flightIcon/{{segment.AirlineCode}}.png" style="width: 50%">
																	</div>
																</th>
																<th>
																	<div class="tabp-finfo-fname text-center">
																		<p>
																			<small>{{segment.AirlineCode}}-{{segment.FlightNumber}}</small>
																		</p>
																	</div>
																</th>
																<th>
																	<div class="tabp-finfo-fname text-center">
																		<p>
																			<b>{{segment.Origin}} {{segment.DepartureTime}}</b><br>
																			<small>({{segment.DepartureDate}})</small>
																		</p>
																	</div>
																</th>
																<th>
																	<div class="tabp-finfo-fdur text-center">
																		<i class="fa fa-clock"></i><br>
																		<p>
																			<b> {{segment.Duration}}</b>
																		</p>
																	</div>
																</th>
																<th>
																	<div class="tabp-finfo-fname text-center">
																		<p>
																			<b>{{segment.Destination}}
																				{{segment.ArrivalTime}}</b><br> <small>({{segment.ArrivalDate}})</small>
																		</p>
																	</div>
																</th>
															</tr>
														</thead>
													</table>
												</div>
											</div>
											<div id="roundmenu1{{$index}}" class="tab-pane fade">
												<div class="table-responsive">
													<table class="table table-bordered">
														<tr>
															<td class="text-center">Passenger</td>
															<td class="text-center">Total (Base Fare)</td>
															<td class="text-center">Total Tax</td>
															<td class="text-center">Grand Total</td>
															<td class="text-center">Net Total</td>
														</tr>
														<tr ng-repeat="faredown in flights1.FareBreakdown"
															ng-if="faredown.PassengerType==='1'">
															<td class="text-center">{{flight.adult}} x Adult</td>
															<td class="text-center">Rs. {{faredown.BaseFare +
																flights1.Fare.MarkUp}}</td>
															<td class="text-center">Rs. {{faredown.Tax}}</td>
															<td class="text-center"><b>Rs.
																	{{faredown.GrossAmount + flights1.Fare.MarkUp}}</b></td>
															<td class="text-center"><b>Rs.
																	{{faredown.GrossAmount + flights1.Fare.MarkUp}}</b></td>		
														</tr>
														<tr ng-repeat="faredown in flights1.FareBreakdown"
															ng-if="faredown.PassengerType==='2'">
															<td class="text-center">{{flight.child}} x Child</td>
															<td class="text-center">Rs. {{faredown.BaseFare}}</td>
															<td class="text-center">Rs. {{faredown.Tax}}</td>
															<td class="text-center"><b>Rs.
																	{{faredown.GrossAmount}}</b></td>
															<td class="text-center"><b>Rs.
																	{{faredown.GrossAmount }}</b></td>		
														</tr>
														<tr ng-repeat="faredown in flights1.FareBreakdown"
															ng-if="faredown.PassengerType==='3'">
															<td class="text-center">{{flight.infant}} x Infant</td>
															<td class="text-center">Rs. {{faredown.BaseFare}}</td>
															<td class="text-center">Rs. {{faredown.Tax}}</td>
															<td class="text-center"><b>Rs.
																	{{faredown.GrossAmount}}</b></td>
															<td class="text-center"><b>Rs.
																	{{faredown.GrossAmount}}</b></td>		
														</tr>
													</table>
												</div>
											</div>

											<div id="roundmenu2{{$index}}" class="tab-pane fade">
												<div class="table-responsive">
													<table class="table table-bordered">
														<tr>
															<td class="text-center">Airline</td>
															<td class="text-center">Check-in Baggage</td>
															<td class="text-center">Cabin Baggage</td>
														</tr>
														<tr>
															<td class="text-center">{{flights1.FlightSegments[0].AirlineCode}}-{{flights1.FlightSegments[0].FlightNumber}}</td>
															<td class="text-center">15kg</td>
															<td class="text-center">7kg</td>
														</tr>
													</table>
												</div>
											</div>

											<div id="roundmenu3{{$index}}" class="tab-pane fade">
												<div class="fare-rules">
													<div class="row">
														<div class="col-md-6">
															<div class="table-responsive">
																<table class="table table-bordered">
																	<tr>
																		<td class="text-center">Before 4 hours Departure</td>
																		<td class="text-center">Rs. 3500</td>
																	</tr>
																	<tr>
																		<td class="text-center">EaseMyTrip Fee</td>
																		<td class="text-center">Rs. 3500</td>
																	</tr>
																</table>
															</div>
														</div>
														<div class="col-md-6">
															<div class="table-responsive">
																<table class="table table-bordered">
																	<tr>
																		<td class="text-center">Before 4 hours Departure</td>
																		<td class="text-center">Rs. 3500</td>
																	</tr>
																	<tr>
																		<td class="text-center">EaseMyTrip Fee</td>
																		<td class="text-center">Rs. 3500</td>
																	</tr>
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
						<!--/roundtrip-flight-result-list-heading-->


					</div>


					<!--flight search result list-->
					<div class="col-md-6" style="margin-bottom: 13%;">
						<!--roundtrip-flight-list-info-->
						<div class="rtflight-list-info mt-30">
							<div class="row">
								<div class="col-md-4">
									<div class="travel-path-head">
										<p class="n-margin">{{flight.destination}} -
											{{flight.source}}</p>
									</div>
								</div>

								<div class="col-md-4">
									<div class="travel-duration-head">
										<p class="n-margin">{{ flight.return | date : "fullDate"
											}}</p>
									</div>
								</div>

								<div class="col-md-4">
									<div class="travel-date-head">
										<span> <a href="#">Previous Day</a> | <a href="#">Next
												Day</a>
										</span>
									</div>
								</div>
							</div>
						</div>
						<!--/roundtrip-flight-list-info-->

						<!--roundtrip-flight-list-heading-->
						<div class="rtflight-list-haed mt-20">
							<div class="row">
								<div class="col-md-2 col-sm-2 text-center">
									<p class="n-margin">Airline</p>
								</div>
								<div class="col-md-2 col-sm-2 text-center">
									<p class="n-margin">Departs</p>
								</div>
								<div class="col-md-3 col-sm-3 text-center">
									<p class="n-margin">Duration</p>
								</div>
								<div class="col-md-2 col-sm-2 text-center">
									<p class="n-margin">Arrives</p>
								</div>
							</div>
						</div>
						<!--/roundtrip-flight-list-heading-->

						<!--roundtrip-flight-result-list-heading-->
						<div class="rtflight-result-list mt-20"
							ng-repeat="flights2 in downList| filter:pricerange | filter :stopFilter | filter:fnameFilter"
							ng-show="flights2.FareBreakdown[0].TripIndicator==='2'">
							<div class="row">
								<div class="flight-list-v2">
									<div class="col-md-12">
										<div class="flight-list-main">
											<div class="col-md-2 col-sm-2 text-center airline">
												<img
													src="assets/skyflight/images/flightIcon/{{flights2.AirlineId}}.png">
												<h6>{{flights2.FlightSegments[0].AirlineCode}}-{{flights2.FlightSegments[0].FlightNumber}}</h6>
											</div>
											<div class="col-md-2 col-sm-2 departure text-center pt-20">
												<h3>
													<i class="fa fa-plane"></i>{{destinationDetail[0]}}
													{{flights2.FlightSegments[0].DepartureTime}}
												</h3>
												<h5 class="bold">{{flights2.FlightSegments[0].DepartureDate}}</h5>
												<h5></h5>
											</div>
											<div
												class="col-md-3 col-sm-3 stop-duration text-center pt-20">
												<div class="flight-direction"></div>
												<div class="stop"></div>
												<div class="stop-box">
													<span>{{flights2.FlightSegments.length-1}} Stop</span>
												</div>
												<div class="duration text-center">
													<span><i class="fa fa-clock-o"></i>{{flights2.totalduration}}</span>
												</div>
											</div>
											<div class="col-md-2 col-sm-2 destination text-center pt-20">
												<h3>
													<i class="fa fa-plane"></i>{{sourceDetail[0]}}
													{{flights2.FlightSegments[0].ArrivalTime}}
												</h3>
												<h5 class="bold">{{flights2.FlightSegments[0].ArrivalDate}}</h5>
												<h5></h5>
											</div>

											<div class="col-md-3 col-sm-3  text-center pt-20">
												<div class="booking-Price">
													<h3>INR {{flights2.Fare.TotalAmount}}</h3>
												</div>
												<div class="booking-btn pt-10">
													<input name="group6" type="radio" id="radio_30"
														class="with-gap radio-col-red" checked=""
														ng-click="flightsection2change(flights2,flights,flight)">
													<label for="radio_30"></label>
												</div>


											</div>
										</div>
									</div>
									<div class="col-md-12">
										<div class="flight-list-footer">
											<div class="col-md-6 col-sm-6 sm-invisible">
												<span><i class="fa fa-plane"></i> {{flight.class}}
													CLASS</span> <span class="refund" ng-if="flights2.IsLCC===true" tooltip="LOW COST CARRIER"><i class="fa fa-suitcase"></i>
														LCC</span> <span class="refund" ng-if="flights2.IsLCC===false" tooltip="NON LOW COST CARRIER"><i class="fa fa-suitcase"></i>
														NON LCC</span>
												<!-- <span
													class="refund"><i class="fa fa-undo"></i> Refundable</span> -->

											</div>
											<div class="col-md-6 col-sm-6 col-xs-12 clear-padding">
												<div class="pull-right">
													<a id="roundtdetail{{$index}}" href="javascript:"
														ng-click="roundtFlightDetailTabs($index);">Show
														Details +</a>
												</div>
											</div>
										</div>
									</div>
								</div>

								<div class="flight-details-tab" id="roundttabDetail{{$index}}"
									style="display: none;">
									<div class="col-md-12">
										<ul class="nav nav-tabs">
											<li class="active"><a data-toggle="tab"
												href="#roundthome{{$index}}">Flight Information</a></li>
											<li><a data-toggle="tab" href="#roundtmenu1{{$index}}">Fare
													Details</a></li>
											<li><a data-toggle="tab" href="#roundtmenu2{{$index}}">Baggage
													Information</a></li>
											<!-- <li><a data-toggle="tab" href="#roundtmenu3{{$index}}">Cancellation
													Rules</a></li> -->
										</ul>

										<div class="tab-content">
											<div id="roundthome{{$index}}"
												class="tab-pane fade in active">
												<div class="table-responsive">
													<table class="table table-bordered">
														<thead ng-repeat="segment in  flights2.FlightSegments">
															<tr>
																<th>
																	<div class="tabp-finfo-fimg text-center">
																		<img
																			src="assets/skyflight/images/flightIcon/{{segment.AirlineCode}}.png" style="width: 50%">
																	</div>
																</th>
																<th>
																	<div class="tabp-finfo-fname text-center">
																		<p>
																			<small>{{segment.AirlineCode}}-{{segment.FlightNumber}}</small>
																		</p>
																	</div>
																</th>
																<th>
																	<div class="tabp-finfo-fname text-center">
																		<p>
																			<b>{{segment.Origin}} {{segment.DepartureTime}}</b><br>
																			<small>({{segment.DepartureDate}})</small>
																		</p>
																	</div>
																</th>
																<th>
																	<div class="tabp-finfo-fdur text-center">
																		<i class="fa fa-clock"></i><br>
																		<p>
																			<b> {{segment.Duration}}</b>
																		</p>
																	</div>
																</th>
																<th>
																	<div class="tabp-finfo-fname text-center">
																		<p>
																			<b>{{segment.Destination}}
																				{{segment.ArrivalTime}}</b><br> <small>({{segment.ArrivalDate}})</small>
																		</p>
																	</div>
																</th>
															</tr>
														</thead>
													</table>
												</div>
											</div>
											<div id="roundtmenu1{{$index}}" class="tab-pane fade">
												<div class="table-responsive">
													<table class="table table-bordered">
														<tr>
															<td class="text-center">Passenger</td>
															<td class="text-center">Total (Base Fare)</td>
															<td class="text-center">Total Tax</td>
															<td class="text-center">Grand Total</td>
															<td class="text-center">Net Total</td>
														</tr>
														<tr ng-repeat="faredown in flights2.FareBreakdown"
															ng-if="faredown.PassengerType==='1'">
															<td class="text-center">{{flight.adult}} x Adult</td>
															<td class="text-center">Rs. {{faredown.BaseFare +
																flights2.Fare.MarkUp}}</td>
															<td class="text-center">Rs. {{faredown.Tax}}</td>
															<td class="text-center"><b>Rs.
																	{{faredown.GrossAmount + flights2.Fare.MarkUp}}</b></td>
															<td class="text-center"><b>Rs.
																	{{faredown.GrossAmount + flights2.Fare.MarkUp}}</b></td>		
														</tr>
														<tr ng-repeat="faredown in flights2.FareBreakdown"
															ng-if="faredown.PassengerType==='2'">
															<td class="text-center">{{flight.child}} x Child</td>
															<td class="text-center">Rs. {{faredown.BaseFare}}</td>
															<td class="text-center">Rs. {{faredown.Tax}}</td>
															<td class="text-center"><b>Rs.
																	{{faredown.GrossAmount }}</b></td>
															<td class="text-center"><b>Rs.
																	{{faredown.GrossAmount}}</b></td>		
														</tr>
														<tr ng-repeat="faredown in flights2.FareBreakdown"
															ng-if="faredown.PassengerType==='3'">
															<td class="text-center">{{flight.infant}} x Infant</td>
															<td class="text-center">Rs. {{faredown.BaseFare}}</td>
															<td class="text-center">Rs. {{faredown.Tax}}</td>
															<td class="text-center"><b>Rs.
																	{{faredown.GrossAmount}}</b></td>
															<td class="text-center"><b>Rs.
																	{{faredown.GrossAmount}}</b></td>		
														</tr>
													</table>
												</div>
											</div>

											<div id="roundtmenu2{{$index}}" class="tab-pane fade">
												<div class="table-responsive">
													<table class="table table-bordered">
														<tr>
															<td class="text-center">Airline</td>
															<td class="text-center">Check-in Baggage</td>
															<td class="text-center">Cabin Baggage</td>
														</tr>
														<tr>
															<td class="text-center">{{flights2.FlightSegments[0].AirlineCode}}-{{flights2.FlightSegments[0].FlightNumber}}</td>
															<td class="text-center">15kg</td>
															<td class="text-center">7kg</td>
														</tr>
													</table>
												</div>
											</div>

											<div id="roundtmenu3{{$index}}" class="tab-pane fade">
												<div class="fare-rules">
													<div class="row">
														<div class="col-md-6">
															<div class="table-responsive">
																<table class="table table-bordered">
																	<tr>
																		<td class="text-center">Before 4 hours Departure</td>
																		<td class="text-center">Rs. 3500</td>
																	</tr>
																	<tr>
																		<td class="text-center">EaseMyTrip Fee</td>
																		<td class="text-center">Rs. 3500</td>
																	</tr>
																</table>
															</div>
														</div>
														<div class="col-md-6">
															<div class="table-responsive">
																<table class="table table-bordered">
																	<tr>
																		<td class="text-center">Before 4 hours Departure</td>
																		<td class="text-center">Rs. 3500</td>
																	</tr>
																	<tr>
																		<td class="text-center">EaseMyTrip Fee</td>
																		<td class="text-center">Rs. 3500</td>
																	</tr>
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
						<!--/roundtrip-flight-result-list-heading-->

					</div>
					<!--/flight search result list-->
				</div>
			</div>
		</div>
		<!-- menu-container -->


		<!--roundtrip flight ticket-->
		<div class="roundtriflight-ticket-section">
			<div class="container">
				<div class="rtflight-ticket-warp">
					<div class="row">
						<div class="col-md-12">
							<div class="table-responsive">
								<table class="table table-bordered">
									<thead>
										<tr>
											<th colspan="5">Departure | {{flight.source}} -->
												{{flight.destination}}</th>
											<th colspan="5">Return | {{flight.destination}} -->
												{{flight.source}}</th>
											<th colspan="2">Grand Total</th>
										</tr>
									</thead>

									<tbody>
										<tr>
											<td><img
												src="assets/skyflight/images/flightIcon/{{flights[0].AirlineId}}.png"
												style="width: 32px;" ng-if="flightsection==null"> <img
												src="assets/skyflight/images/flightIcon/{{flightsection.AirlineId}}.png"
												style="width: 32px;" ng-if="flightsection!=null">
												{{flightsection.AirlineName}}</td>
											<td ng-if="flightsection==null">{{flights[0].FlightSegments[0].DepartureTime}}</td>
											<td ng-if="flightsection!=null">{{flightsection.FlightSegments[0].DepartureTime}}</td>
											<td ng-if="flightsection==null">----></td>
											<td ng-if="flightsection!=null">----><br>{{flightsection.FlightSegments.length-1}}
												Stop
											</td>
											<td ng-if="flightsection==null">{{flights[0].FlightSegments[0].ArrivalTime}}</td>
											<td ng-if="flightsection!=null">{{flightsection.FlightSegments[0].ArrivalTime}}</td>
											<td ng-if="flightsection==null">{{flights[0].Fare.TotalAmount}}</td>
											<td ng-if="flightsection!=null">{{flightsection.Fare.TotalAmount}}</td>
											<td><img
												src="assets/skyflight/images/flightIcon/{{flights.AirlineId}}.png"
												style="width: 32px;" ng-if="flightsection2==null"> <img
												src="assets/skyflight/images/flightIcon/{{flightsection2.AirlineId}}.png"
												style="width: 32px;" ng-if="flightsection2!=null">
												{{flightsection2.AirlineName}}</td>
											<td ng-if="flightsection2==null">00:00</td>
											<td ng-if="flightsection2!=null">{{flightsection2.FlightSegments[0].DepartureTime}}</td>
											<td ng-if="flightsection2==null">----></td>
											<td ng-if="flightsection2!=null">----><br>{{flightsection2.FlightSegments.length-1}}
												Stop
											</td>
											<td ng-if="flightsection2==null">{{flights2.FlightSegments[0].ArrivalTime}}</td>
											<td ng-if="flightsection2!=null">{{flightsection2.FlightSegments[0].ArrivalTime}}</td>
											<td ng-if="flightsection2==null">{{flights2.Fare.TotalAmount}}</td>
											<td ng-if="flightsection2!=null">{{flightsection2.Fare.TotalAmount}}</td>

											<td>{{flightsection.Fare.TotalAmount+flightsection2.Fare.TotalAmount
												|number :2}}</td>
											<td><button class="btn-danger"
													ng-click="getTaxround(flightsection,flightsection2,flight);">Book
													Now</button></td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<!--/roundtrip flight ticket-->


	</div>
	
	
	<div id="div1" style="display: none; margin: 0px;">
		<div class="flight-list-page-search hidden-sm hidden-xs">
			<div class="container">
				<div class="row">
					<div class="col-md-10"></div>
					<div class="col-md-2">
						<a onClick="showDiv('div0')" class="back_btn" href="#"><i class="fas fa-arrow-left"></i> Back</a>
					</div>
				</div>
				<div class="row">
					<div class="flight-search-form">
						<form>
							<div class="form-group">

								<div class="row">
									<div class="col-md-2">
										<input type="radio" ng-model="flight.type" value="O"
											id="oneway1" name="selector1" ng-click="myOneWay(flight)">
										<label for="oneway">OneWay</label>
									</div>

									<div class="col-md-2">
										<input type="radio" ng-model="flight.type" id="roundtrip"
											value="R" name="selector1" ng-click="myRoundTrip(flight)">
										<label for="roundtrip">Round Trip</label>
									</div>
								</div>



								<div class="row">
									<div class="col-md-2">
										<label>Depart From</label> <input type="text"
											id="sourceCityAdv" class="form-control ui-widget"
											ng-model="flight.source"
											ng-keyup="getCityAutoFill(flight.source, 'sourceCityAdv');">
									</div>

									<div class="col-md-2">
										<label>Going To</label> <input type="text" id="destiCityAdv"
											class="form-control ui-widget" ng-model="flight.destination"
											ng-keyup="getCityAutoFill(flight.destination, 'destiCityAdv');">
									</div>

									<div class="col-md-2">
										<label>Depart Date</label> <input type="text"
											class="form-control" ng-model="flight.depart" id="dp3"
											readonly="readonly">
									</div>

									<div class="col-md-2">
										<label>Return Date</label> <input type="text"
											class="form-control" ng-model="flight.return" id="dp4"
											readonly="readonly">
									</div>

									<div class="col-md-2">
										<label>Adults<small> (12+ Years)</small></label>
										<div class="input-group">
										 <span class="input-group-btn">
												<button type="button"
													class="adult-left-minus btn btn btn-danger btn2-number"
													ng-if="flight.adult >1"	data-type="minus" data-field=""
													ng-click="flight.adult = flight.adult - 1;">
													<span class="glyphicon glyphicon-minus"></span>
												</button>
											</span>
											<input type="text" class="form-control"
												    readonly="readonly" ng-model="flight.adult">
										   
											
											<span class="input-group-btn">
												<button type="button"
													class="adult-right-plus btn btn btn-danger btn2-number"
													data-type="plus" data-field="" ng-click="flight.adult = flight.adult + 1;"
													data-type="plus">
													<span class="glyphicon glyphicon-plus"></span>
												</button>
											</span>
										</div>
										
									</div>

									<div class="col-md-2">
									<label>Child <small>( 2-12 YRS )</small></label>
									<div class="input-group">
										 <span class="input-group-btn">
												<button type="button"
													class="infant-right-plus btn btn-danger btn2-number"
													data-type="plus" ng-if="flight.child >0" ng-click="flight.child = flight.child - 1;">
													<span class="glyphicon glyphicon-minus"></span>
												</button>
											</span>
											<input type="text" class="form-control"
												    readonly="readonly" ng-model="flight.child">
										   
											
											<span class="input-group-btn">
												<button type="button"
													class="infant-right-plus btn btn-danger btn2-number"
													data-type="plus" ng-click="flight.child = flight.child + 1;">
													<span class="glyphicon glyphicon-plus"></span>
												</button>
											</span>
										</div>
									
									
										<!-- <label>Child <small>( 2-12 YRS )</small></label> <input
											type="text" class="form-control" ng-model="flight.child"> -->
									</div>

								</div>

								<div class="row">
									<div class="col-md-2">
									<label>Infants <small>( Below 2 YRS )</small></label>
									
									<div class="input-group">
										 <span class="input-group-btn">
												<button type="button"
													class="infant-right-plus btn btn-danger btn2-number"
													data-type="plus"ng-if="flight.infant >0" ng-click="flight.infant = flight.infant - 1;">
													<span class="glyphicon glyphicon-minus"></span>
												</button>
											</span>
											<input type="text" class="form-control"
												    readonly="readonly" ng-model="flight.infant">
										   
											
											<span class="input-group-btn">
												<button type="button"
													class="infant-right-plus btn btn-danger btn2-number"
													data-type="plus" ng-click="flight.infant = flight.infant + 1;">
													<span class="glyphicon glyphicon-plus"></span>
												</button>
											</span>
										</div>
									
									
									</div>

									<div class="col-md-3">
										<label>Traveller(s), Class</label> <input type="text"
											class="form-control" ng-model="flight.class">
									</div>
									<div class="col-md-3">
										<button class="bookbtn mt-20" ng-click="searchFlight(flight)">Searching</button>
									</div>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
<!-- id="sdiv1" style="display: none;"  -->

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

							<!--price filter-->
							<div class="filters price-range">
								<div class="flight-header-name range-header">
									<h6>Price Range</h6>

									<span class="collapse-icon"> 
								    <i class="fa fa-chevron-down"></i>
									</span> 
								</div>
								<div class="content" style="margin-top: 12px;">
								
								<!-- <input type="hidden" ng-model="price_min" ng-change="priceRangeFilter(price_min, price_max);"/>
							    <input type="hidden" ng-model="price_max" ng-change="priceRangeFilter(price_min, price_max);"/> -->
							    <!-- <slider-range min="{{price_min}}" max="{{price_max}}" value-min="price_min" value-max="price_max"></slider-range> -->
							    <!-- <rzslider rz-slider-model="minRangeSlider.minValue" rz-slider-high="minRangeSlider.maxValue" rz-slider-options="minRangeSlider.options"></rzslider> -->
							     <rzslider rz-slider-model="slider_translate.minValue" rz-slider-high="slider_translate.maxValue" rz-slider-options="slider_translate.options"></rzslider>
								
									<!-- <div class="price-filter">&#x20B9;.{{price_min}} -> &#x20B9;.{{price_max}}</div> -->
								</div>

							</div>

							

							<!--price filter-->
							<!-- <div class="filters price-range">
								<div class="flight-header-name range-header">
									<h6>Price Range</h6>

									<span class="collapse-icon"> 
								    <i class="fa fa-chevron-down"></i>
									</span> 
								</div>
								<div class="content">
									<div class="checklist">

										<span class="multi-range">
										 <input type="range" min="{{min}}" max="{{max}}" ng-model="pricerangemin" value="{{min}}" id="lower">
										  <input type="range" min="{{min}}" max="{{max}}" value="{{max}}"	ng-model="pricerangemax" id="upper">
										</span>

										<span class="multi-range "> <input type="range" ng-model="pricerange" min="{{min}}"  max="{{max}}" value="{{pricerange}}">
										</span>
									</div>
									<div class="price-filter">rs.{{pricerangemin}} -> rs.{{pricerangemax}}</div>
								</div>

							</div> -->

							
							<!--stopeg filter-->
							<div class="filters flight-stop">
								<div class="flight-header-name flight-stop-header ">
									<h6>Stops</h6>

									<span class="collapse-icon"> 
								    <i class="fa fa-chevron-down"></i>
									</span> 
								</div>
								<div class="content">
									<div class="checklist flight-stop-list">
										<ul>
											<li ng-repeat="empL in filterstop">
												<div class="checkbox">
													<label><input type="checkbox"
														name="locations{{$index}}"
														data-ng-model="locChkBox.loc[$index]"
														ng-true-value="'{{empL}}'" ng-false-value=""
														ng-change="repopulate()" /> {{empL}} Stops</label>
													<!-- <label><input type="checkbox" name="locations{{$index}}" ng-model="locChkBox.loc[$index]" ng-true-value="'{{empL}}'" ng-false-value="" ng-change="toggleCurrency($index)"/>
                              {{empL}} Stops</label> -->
												</div>
											</li>


										</ul>
									</div>
								</div>

							</div>

							<!--airloinesd name-->
							<div class="airlines-name">
								<div class="flight-header-name airlines-name-header ">
									<h6>Airlines</h6>

									<span class="collapse-icon"> 
								    <i class="fa fa-chevron-down"></i>
									</span> 
								</div>

								<div class="content">
									<div class="checklist flight-stop-list">
										<ul>
											<li ng-repeat="empLnm in filtername">
												<div class="checkbox">
													<label><input type="checkbox"
														name="flightname{{$index}}"
														data-ng-model="nameChkBox.loc[$index]"
														ng-true-value="'{{empLnm}}'" ng-false-value="" />
														{{empLnm}}</label>
												</div>
											</li>
										</ul>
									</div>
								</div>

							</div>
							<!--Departure time-->
							<div class="airlines-name">
								<div class="flight-header-name airlines-name-header collapsible">
									<h6>Departure Time From {{sourceDetail[1]}}</h6>

								    <span class="collapse-icon"> 
								    <i class="fa fa-chevron-down"></i>
									</span> 
								</div>

								<div class="content">
									<div class="checklist flight-stop-list">
										<ul>
											<li ng-repeat="timea in timelist">
												<div class="checkbox">
													<label><input type="checkbox"
														name="timelist{{$index}}"
														data-ng-model="detimeChkBox.loc[$index]"
														ng-true-value="'{{timea}}'" ng-false-value="" />
														{{timea}}</label>
												</div>
											</li>
										</ul>
									</div>
								</div>

							</div>
							<!--Arrival time-->
							<div class="airlines-name">
								<div class="flight-header-name airlines-name-header collapsible">
									<h6>Arrival Time From {{destinationDetail[1]}}</h6>

									<span class="collapse-icon"> 
								    <i class="fa fa-chevron-down"></i>
									</span> 
								</div>

								<div class="content">
									<div class="checklist flight-stop-list">
										<ul>
											<li ng-repeat="timea in timelist">
												<div class="checkbox">
													<label><input type="checkbox"
														name="timelist{{$index}}"
														data-ng-model="atimeChkBox.loc[$index]"
														ng-true-value="'{{timea}}'" ng-false-value="" />
														{{timea}}</label>
												</div>
											</li>
										</ul>
									</div>
								</div>
							</div>
						</div>
					</div>
					<!--/flight Search Filter section-->


					<!--flight search result list-->
					<div class="col-md-9">
						<!--owl date slider-->
						
						<div class="row pl-14 pr-14 hidden-sm hidden-xs">
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
						</div>
						
						<!--/owl date slider-->

						<div class="flight-list">
							<div class="flight-list-heading mt-20 mb-20  hidden-sm hidden-xs">
								<div class="row list-heading-row">
									<div class="col-md-2 col-sm-2 text-center">
										<p>Airline</p>
									</div>
									<div class="col-md-2 col-sm-2 text-center">
										<p>Departs</p>
									</div>
									<div class="col-md-3 col-sm-3 text-center">
										<p>Duration</p>
									</div>
									<div class="col-md-2 col-sm-2 text-center">
										<p>Arrives</p>
									</div>
								</div>
							</div>

							<div class="flight-list-heading mt-20 mb-20">
								<div class="row flight-result-list"
									ng-repeat="flightone in flights | filter:pricerange | filter :stopFilter | filter:fnameFilter | filter:depertimeFilter | filter:arrivtimeFilter">
									<div class="col-md-12 flight-list-v2" style="padding: 0;">
										<div class="col-md-12">
											<div class="flight-list-main">
												<div class="col-md-2 col-sm-2 text-center airline">
													<img
														src="assets/skyflight/images/flightIcon/{{flightone.AirlineId}}.png" style="width: 50%">
													<h6>{{flightone.AirlineName}}-{{flightone.FlightSegments[0].FlightNumber}}</h6>
												</div>
												<div
													class="col-md-2 col-sm-2 col-xs-4 departure text-center pt-20">
													<h3>
														<i class="fa fa-plane"></i> {{sourceDetail[0] }} <span
															class="ft">({{flightone.FlightSegments[0].DepartureTime}})</span>
													</h3>
													<h5 class="bold">{{flightone.FlightSegments[0].DepartureDate}}</h5>
													<!-- <h5 class="bold">{{flightone.FlightSegments[0].DepartureTime}}</h5> -->
													<!-- <h5>{{flightone.FlightSegments[0].Origin}}</h5> -->
												</div>
												<div
													class="col-md-3 col-sm-3 col-xs-4 stop-duration text-center pt-20">
													<div class="flight-direction"></div>
													<div class="stop"></div>
													<div class="stop-box">
														<span>{{flightone.FlightSegments.length-1}} Stop</span>
													</div>
													<div class="duration text-center">
														<span><i class="fa fa-clock-o"></i>
															{{flightone.totalduration}}</span>
													</div>
												</div>
												<div
													class="col-md-2 col-sm-2 col-xs-4 destination text-center pt-20">
													<h3>
														<i class="fa fa-plane"></i> {{destinationDetail[0]}} <span
															class="ft">({{flightone.FlightSegments[0].ArrivalTime}})</span>
													</h3>
													<h5 class="bold">{{flightone.FlightSegments[0].ArrivalDate}}</h5>
													<!-- <h5 class="bold">{{flightone.FlightSegments[0].ArrivalTime}}</h5> -->
													<!--  <h5>{{flights.FlightSegments[0].Destination}}</h5> -->
												</div>

												<div class="col-md-3 col-sm-3  col-xs-12  text-center pt-20">
													<div class="booking-Price">
														<h3>INR {{flightone.Fare.TotalAmount}}</h3>
													</div>
													<div class="booking-btn pt-10">
														<button class="bookbtn"
															ng-click="getTax(flightone,flight);">Booking</button>
													</div>


												</div>
											</div>
										</div>
										<div class="col-md-12 col-xs-12">
											<div class="flight-list-footer">
												<div class="col-md-6 col-sm-6 sm-invisible">
													<span><i class="fa fa-plane"></i> {{flight.class}}
														CLASS</span> <span class="refund" ng-if="flightone.IsLCC===true" tooltip="LOW COST CARRIER"><i class="fa fa-suitcase"></i>
														LCC</span> <span class="refund" ng-if="flightone.IsLCC===false" tooltip="NON LOW COST CARRIER"><i class="fa fa-suitcase"></i>
														NON LCC</span>

												</div>
												<div class="col-md-6 col-sm-6 col-xs-12 clear-padding">
													<div class="pull-right">
														<a id="detail{{$index}}" href="javascript:"
															ng-click="showFlightDetailTabs($index);">Show Details
															+</a>
													</div>
												</div>
											</div>
										</div>
									</div>
									<div class="col-md-12 flight-details-tab"
										id="tabDetail{{$index}}" style="display: none;">
										<div class="row" style="margin: 0; padding: 10px 0px 0px 0px;">
											<div class="col-md-12">
												<ul class="nav nav-tabs">
													<li class="active"><a data-toggle="tab"
														href="#home{{$index}}">Flight Information</a></li>
													<li><a data-toggle="tab" href="#menu1{{$index}}">Fare
															Details</a></li>
													<li><a data-toggle="tab" href="#menu2{{$index}}">Baggage
															Information</a></li>
													<!-- <li><a data-toggle="tab" href="#menu3{{$index}}">My Discount</a></li> -->
												</ul>

												<div class="tab-content">
													<div id="home{{$index}}" class="tab-pane fade in active">
														<div class="table-responsive">

															<table class="table table-bordered">
																<thead ng-repeat="segment in  flightone.FlightSegments">
																	<tr>
																		<th>
																			<div class="tabp-finfo-fimg text-center">
																				<img
																					src="assets/skyflight/images/flightIcon/{{segment.AirlineCode}}.png">
																			</div>
																		</th>
																		<th>
																			<div class="tabp-finfo-fname text-center">
																				<p>
																					{{flightone.AirlineName}}<br> <small>{{segment.AirlineCode}}-{{segment.FlightNumber}}</small>
																				</p>
																			</div>
																		</th>
																		<th>
																			<div class="tabp-finfo-fname text-center">
																				<p>
																					<b>{{segment.Origin}} {{segment.DepartureTime}}</b><br>
																					<small>({{segment.DepartureDate}})</small>
																				</p>
																			</div>
																		</th>
																		<th>
																			<div class="tabp-finfo-fdur text-center">
																				<i class="fa fa-clock"></i><br>
																				<p>
																					<b> {{segment.Duration}}</b>
																				</p>
																			</div>
																		</th>
																		<th>
																			<div class="tabp-finfo-fname text-center">
																				<p>
																					<b>{{segment.Destination}}
																						{{segment.ArrivalTime}}</b><br> <small>({{segment.ArrivalDate}})</small>
																				</p>
																			</div>
																		</th>
																	</tr>
																</thead>
															</table>
														</div>
													</div>

													<div id="menu1{{$index}}" class="tab-pane fade">
														<div class="table-responsive">
															<table class="table table-bordered">
																<tr>
																	<td class="text-center">Passenger</td>
																	<td class="text-center">Total (Base Fare)</td>
																	<td class="text-center">Total Tax</td>
																	<td class="text-center">Grand Total</td>
																	<td class="text-center">Net Total</td>
																</tr>

																<tr ng-repeat="faredown in flightone.FareBreakdown"
																	ng-if="faredown.PassengerType==='1'">
																	<td class="text-center">{{flight.adult}} x Adult</td>
																	<td class="text-center">Rs. {{faredown.BaseFare +
																		flightone.Fare.MarkUp}}</td>
																	<td class="text-center">Rs. {{faredown.Tax}}</td>
																	<td class="text-center"><b>Rs.
																			{{faredown.GrossAmount + flightone.Fare.MarkUp}}</b></td>
																	<td class="text-center"><b>Rs.
																	{{faredown.GrossAmount + flightone.Fare.MarkUp}}</b></td>		
																</tr>
																<tr ng-repeat="faredown in flightone.FareBreakdown"
																	ng-if="faredown.PassengerType==='2'">
																	<td class="text-center">{{flight.child}} x Child</td>
																	<td class="text-center">Rs. {{faredown.BaseFare}}</td>
																	<td class="text-center">Rs. {{faredown.Tax}}</td>
																	<td class="text-center"><b>Rs.
																			{{faredown.GrossAmount}}</b></td>
																	<td class="text-center"><b>Rs.
																	{{faredown.GrossAmount }}</b></td>		
																</tr>
																<tr ng-repeat="faredown in flightone.FareBreakdown"
																	ng-if="faredown.PassengerType==='3'">
																	<td class="text-center">{{flight.infant}} x Infant</td>
																	<td class="text-center">Rs. {{faredown.BaseFare}}</td>
																	<td class="text-center">Rs. {{faredown.Tax}}</td>
																	<td class="text-center"><b>Rs.
																			{{faredown.GrossAmount}}</b></td>
																	<td class="text-center"><b>Rs.
																	{{faredown.GrossAmount}}</b></td>				
																</tr>
															</table>
														</div>
													</div>

													<div id="menu2{{$index}}" class="tab-pane fade">
														<div class="table-responsive">
															<table class="table table-bordered">
																<tr>
																	<td class="text-center">Airline</td>
																	<td class="text-center">Check-in Baggage</td>
																	<td class="text-center">Cabin Baggage</td>
																</tr>
																<tr>
																	<td class="text-center">{{flightone.FlightSegments[0].AirlineCode}}
																		- {{flightone.FlightSegments[0].FlightNumber}}</td>
																	<td class="text-center">15kg</td>
																	<td class="text-center">7kg</td>
																</tr>
															</table>
														</div>
													</div>

													<!-- <div id="menu3{{$index}}" class="tab-pane fade">
														<div class="fare-rules">
															<div class="row">
																<div class="col-md-6">
																	<div class="table-responsive">
																		<table class="table table-bordered">
																			<tr>
																				<td class="text-center">Discount</td>
																				<td class="text-center">Markup</td>
																			</tr>
																			<tr>
																				<td class="text-center">Marfgdrkup</td>
																				<td class="text-center">jdhjf</td>
																			</tr>
																		</table>
																	</div>
																</div>
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
					</div>
					<!--/flight search result list-->
				</div>
			</div>
		</div>
	
	
	
	</div>


	<div id="div3" style="display: none">
		<div class="container">
			<div class="row">
				<div class="ticket-booking-process-nav">
					 <ul class="breadcrumb booking-process">
						<li><a  onClick="showDiv('div0')">1. Search</a></li>
						<li class="active">2. Payment</li>
						<li style="float: right;"><a  onClick="showDiv('div1')" style="cursor: pointer;"><i class="fas fa-arrow-left"></i> Back</a></li>
					</ul>
				</div>
			</div>

			<div class="row">
				<!-- flight-ticket-details -->
				<div class="col-md-8 new_fdetails">
					<div class="panel panel-default flight-ticket-card">
						<div class="panel-heading flight-ticket-details-head">
							<div class="flight-card-icon">
								<i class="fa fa-plane"></i> <span class="icon-details">
									Flight Details</span>
							</div>
						</div>

						<div class="panel-body" style="border-bottom: 1px solid #ddd;">
							<div class="row">
								<div class="col-md-12">
									<div class="flight-path">
										<i class="fa fa-plane fa-rotate-270"></i> <span class="fpath">{{sourceDetail[1]}}
											- {{destinationDetail[1]}} | <small>{{result.searchDetails.FlightSegments[0].DepartureDate}}
										</small> | {{result.searchDetails.stops}} Stop
										</span>
									</div>
								</div>
							</div>

							<!--ftravel-duration-details-->
							<div class="ftravel-duration-details"
								ng-repeat="returnseg in result.searchDetails.FlightSegments">
								<div class="row">
									<div class="col-md-3">
										<div class="ftravel-flight-img text-center">
											<img
												src="assets/skyflight/images/flightIcon/{{returnseg.AirlineCode}}.png">
											<div>
												{{result.searchDetails.AirlineName}} <small>-{{returnseg.FlightNumber}}</small>
											</div>
										</div>
									</div>
									<div class="col-md-3 col-center text-center">
										<h3>{{returnseg.Origin}}</h3>
										<small>( {{returnseg.DepartureTime}} )</small>
										<h6 class="flight-date">{{returnseg.DepartureDate}}</h6>
									</div>

									<div class="col-md-3 col-center text-center mt-10">
										<div class="fstope-duration-details">
											{{returnseg.Duration}}</div>
										<div class="ftravel-duration-img"></div>
										<div class="refund-details">
											<p>Refundable</p>
										</div>
									</div>

									<div class="col-md-3 col-center text-center">
										<h3>{{returnseg.Destination}}</h3>
										<small>( {{returnseg.ArrivalTime}} )</small>
										<h6 class="flight-date">{{returnseg.ArrivalDate}}</h6>
									</div>
								</div>
							</div>
						</div>
						<div class="panel-body" ng-if="result.type==='R'">
							<div class="row">
								<div class="col-md-12">
									<div class="flight-path">
										<i class="fa fa-plane fa-rotate-270"></i> <span class="fpath">{{destinationDetail[1]}}
											- {{sourceDetail[1]}} | <small>{{result.returnDetail.FlightSegments[0].DepartureDate}}</small>
											| {{result.returnDetail.stops}} Stop
										</span>
									</div>
								</div>
							</div>

							<!--ftravel-duration-details-->
							<div class="ftravel-duration-details"
								ng-repeat="returnseg in result.returnDetail.FlightSegments">
								<div class="row">
									<div class="col-md-3">
										<div class="ftravel-flight-img text-center">
											<img
												src="assets/skyflight/images/flightIcon/{{returnseg.AirlineCode}}.png">
											<div>
												{{result.returnDetail.AirlineName}} <small>-{{returnseg.FlightNumber}}</small>
											</div>
										</div>
									</div>
									<div class="col-md-3 col-center text-center">
										<h3>{{returnseg.Origin}}</h3>
										<small>( {{returnseg.DepartureTime}} )</small>
										<h6 class="flight-date">{{returnseg.DepartureDate}}</h6>
									</div>

									<div class="col-md-3 col-center text-center mt-10">
										<div class="fstope-duration-details">
											{{returnseg.Duration}}</div>
										<div class="ftravel-duration-img"></div>
										<div class="refund-details">
											<p>Refundable</p>
										</div>
									</div>

									<div class="col-md-3 col-center text-center">
										<h3>{{returnseg.Destination}}</h3>
										<small>( {{returnseg.ArrivalTime}} )</small>
										<h6 class="flight-date">{{returnseg.ArrivalDate}}</h6>
									</div>
								</div>
							</div>
						</div>
					</div>


					<div
						class="panel panel-info flight-ticket-card travel-informative-knowledge">
						<div class="panel-heading informative-head">
							<div class="flight-card-icon">
								<p class="n-margin">
									<i class="fa fa-thumbs-up"></i> <span>Information you
										should know</span>
								</p>
							</div>
						</div>
						<div class="panel-body informative-body">
							<ul>
								<li>25 KG per passenger Check-in Baggage included for your
									selected flight on the sector {{sourceDetail[1]}}
											to {{destinationDetail[1]}}</li>

								<!-- <li>Airline Cancellation Fee is Rs. 5300 per passenger for
									your selected flight on the sector {{sourceDetail[1]}}
											to {{destinationDetail[1]}}</li> -->
							</ul>
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

									<div class="impo-notice-div">
										<span class="span-impo-notice"> Name should be same as
											in Goverment ID proof </span>

									</div>
								</div>
							</div>
						</div>

						<div class="panel-body travellers-count">
							<ul>
								<li ng-repeat="i in adultNumber">
									<div class="panel panel-warning passenger-details-form">
										<div class="panel-heading passenger-details-form-heading">

											<div class="row">
												<div class="col-md-4">
													<p class="single-passenger-name">
														<b>Adult <span>{{i}}</span></b> :
													</p>
												</div>

											</div>
										</div>

										<div class="panel-body passenger-details-form-body">
											<div class="single-passenger-details">
												<form>
													<div class="form-group">
														<div class="row">
															<div class="col-md-2">
																<label class="passenger-form-label">Title:</label> <select
																	class="form-control passenger-form-control"
																	id="adulttitle{{i}}">
																	<option>Mr</option>
																	<option>Ms</option>
																	<option>Mrs</option>
																</select>
															</div>
															<div class="col-md-5">
																<label class="passenger-form-label">First Name &
																	(Middle name, if any)</label> <input type="text"
																	class="form-control passenger-form-control"
																	id="adultfname{{i}}">
															</div>

															<div class="col-md-5">
																<label class="passenger-form-label">Last Name:</label> <input
																	type="text" class="form-control passenger-form-control"
																	id="adultlname{{i}}">
															</div>
														</div>														
													</div>

													<div class="form-group">
														<div class="row">
															<div class="col-md-5">
																<label class="passenger-form-label">DOB:</label> <input
																	type="date"
																	class="form-control  passenger-form-control"
																	id="adultdob{{i}}">
															</div>
														</div>
													</div>
												</form>
											</div>
										</div>
										<!-- Extra addition div (food, bages)  -->
									<!-- 	<div class="panel-footer travellers-f-b-details">
											<ul class="nav nav-pills travellers-f-b-pills">
												<li class="active"><a data-toggle="pill" href="#menua{{i}}1">Add
														Meal</a></li>
												<li><a data-toggle="pill" href="#menua{{i}}2">Add Baggage
												</a></li>
											</ul>
											<div class="tab-content">
												
												<div id="menua{{i}}1" class="tab-pane fade in active">
													<h3>Meal</h3>
													food Tabes 
													<div class="row">
														<div class="col-md-12">

															<ul class="nav nav-tabs travellers-f-b-tabs">
																<li class="active"><a data-toggle="tab"
																	href="#foodonewaya{{i}}">{{sourceDetail[1]}}- {{destinationDetail[1]}}</a></li>
																<li><a data-toggle="tab" href="#foodrounda{{i}}" ng-if="result.type==='R'">{{destinationDetail[1]}} - {{sourceDetail[1]}}
																		</a></li>
															</ul>


															<div class="tab-content">
																<div id="foodonewaya{{i}}" class="tab-pane fade in active">
																	<h3>{{result.searchDetails.AirlineName}}-{{result.searchDetails.FlightSegments[0].FlightNumber}}</h3>
																	<div class="row">
																		<div class="col-md-8">
																			<div class="form-group">
																				<label for="sel1">Select Meal:</label> <select
																					class="form-control" id="adult{{i}}meal1">
																					<option ng-repeat="mealtrip1 in mealtrip1" ng-value="{{mealtrip1.code}}">
																						{{mealtrip1.AirlineDescription}}~{{mealtrip1.Amount}}~{{mealtrip1.Code}}
																					</option>
																				</select>
																			</div>
																		</div>
																	</div>
																</div>
																<div id="foodrounda{{i}}" class="tab-pane fade" ng-if="result.type==='R'">
																	<h3>{{result.returnDetail.AirlineName}}-{{result.returnDetail.FlightSegments[0].FlightNumber}}</h3>
																	<div class="row">
																		<div class="col-md-8">
																			<div class="form-group">
																				<label for="sel1">Select Meal:</label> <select
																					class="form-control" id="adult{{i}}meal2">
																					<option ng-repeat="mealtrip2 in mealtrip2" ng-value="{{mealtrip2.code}}">
																						{{mealtrip2.AirlineDescription}}~{{mealtrip2.Amount}}~{{mealtrip2.Code}} 
																					</option>
																				</select>
																			</div>
																		</div>
																	</div>
																</div>
															</div>

														</div>
													</div>
													/food Tabes 
												</div>
												<div id="menua{{i}}2" class="tab-pane fade">
													   <h3>Baggage</h3>


													<div class="row">
														<div class="col-md-12">
															<ul class="nav nav-tabs travellers-f-b-tabs">
																<li class="active"><a data-toggle="tab"
																	href="#bagonewaya{{i}}">{{sourceDetail[1]}}- {{destinationDetail[1]}}</a></li>
																<li><a data-toggle="tab" href="#bagrounda{{i}}" ng-if="result.type==='R'">{{destinationDetail[1]}} - {{sourceDetail[1]}}</a></li>
															</ul>


															<div class="tab-content">
																<div id="bagonewaya{{i}}" class="tab-pane fade in active">
																<h3>{{result.searchDetails.AirlineName}}-{{result.searchDetails.FlightSegments[0].FlightNumber}}</h3>
																	
																	<div class="row">
																		<div class="col-md-8">
																			<div class="form-group">
																				<label for="sel1">Select Baggage:</label> <select
																					class="form-control" id="adult{{i}}baggage">
																					<option ng-repeat="baggagetrip1 in baggagetrip1" ng-value="{{baggagetrip1.code}}">
																						{{baggagetrip1.description}}~{{baggagetrip1.amount}}~{{baggagetrip1.code}}</option>
																				</select>
																			</div>
																		</div>
																	</div>
																</div>
																<div id="bagrounda{{i}}" class="tab-pane fade" ng-if="result.type==='R'">
																	<h3>{{result.returnDetail.AirlineName}}-{{result.returnDetail.FlightSegments[0].FlightNumber}}</h3>
																	<div class="row">
																		<div class="col-md-8">
																			<div class="form-group">
																				<label for="sel1">Select Baggage:</label> <select
																					class="form-control" id="adult{{i}}baggage2">
																					<option ng-repeat="baggagetrip2 in baggagetrip2" ng-value="{{baggagetrip2.code}}">
																						{{baggagetrip2.description}}~{{baggagetrip2.amount}}~{{baggagetrip2.code}}</option>
																				</select>
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
 -->
										<!-- /(food,Bages) -->
									</div>
								</li>
							</ul>
						</div>



						<div class="panel-body travellers-count">
							<ul>
								<li ng-repeat="i in childNumber">
									<div class="panel panel-warning passenger-details-form">
										<div class="panel-heading passenger-details-form-heading">

											<div class="row">
												<div class="col-md-4">
													<p class="single-passenger-name">
														<b>Child <span>{{i}}</span></b> :
													</p>
												</div>
											</div>


										</div>
										<div class="panel-body passenger-details-form-body">
											<div class="single-passenger-details">
												<!-- <form> -->
												<div class="form-group">
													<div class="row">
														<div class="col-md-2">
															<label class="passenger-form-label">Title:</label> <select
																class="form-control passenger-form-control"
																id="childtitle{{i}}">
																<option>Miss</option>
																<option>Master</option>
															</select>
														</div>
														<div class="col-md-5">
															<label class="passenger-form-label">First Name &
																(Middle name, if any)</label> <input type="text"
																class="form-control passenger-form-control"
																id="childfname{{i}}">
														</div>

														<div class="col-md-5">
															<label class="passenger-form-label">Last Name:</label> <input
																type="text" class="form-control passenger-form-control "
																id="childlname{{i}}">
														</div>
													</div>
													<div class="row">
														<div class="col-md-5">
															<label class="passenger-form-label">DOB:</label> 
															<input type="date" class="form-control  passenger-form-control" id="childdob{{i}}">
														</div>
													</div>
												</div>

												<!-- </form> -->
											</div>
										</div>
										<!-- Extra addition div (food, bages)  -->
									<!-- 	<div class="panel-footer travellers-f-b-details">
											<ul class="nav nav-pills travellers-f-b-pills">
												<li class="active"><a data-toggle="pill" href="#menuc{{i}}1">Add
														Meal</a></li>
												<li><a data-toggle="pill" href="#menuc{{i}}2">Add Baggage
												</a></li>
											</ul>
											<div class="tab-content">
												
												<div id="menuc{{i}}1" class="tab-pane fade in active">
													<h3>Meal</h3>
													food Tabes 
													<div class="row">
														<div class="col-md-12">

															<ul class="nav nav-tabs travellers-f-b-tabs">
																<li class="active"><a data-toggle="tab"
																	href="#foodonewayc{{i}}">{{sourceDetail[1]}}- {{destinationDetail[1]}}</a></li>
																<li><a data-toggle="tab" href="#foodroundc{{i}}" ng-if="result.type==='R'">{{destinationDetail[1]}} - {{sourceDetail[1]}}
																		</a></li>
															</ul>


															<div class="tab-content">
																<div id="foodonewayc{{i}}" class="tab-pane fade in active">
																	<h3>{{result.searchDetails.AirlineName}}-{{result.searchDetails.FlightSegments[0].FlightNumber}}</h3>
																	<div class="row">
																		<div class="col-md-8">
																			<div class="form-group">
																				<label for="sel1">Select Meal:</label> <select
																					class="form-control" id="child{{i}}meal">
																					<option ng-repeat="mealtrip1 in mealtrip1" ng-value="{{mealtrip1.code}}">
																						{{mealtrip1.AirlineDescription}}~{{mealtrip1.Amount}}~{{mealtrip1.Code}}
																					</option>
																				</select>
																			</div>
																		</div>
																	</div>
																</div>
																<div id="foodroundc{{i}}" class="tab-pane fade" ng-if="result.type==='R'">
																	<h3>{{result.returnDetail.AirlineName}}-{{result.returnDetail.FlightSegments[0].FlightNumber}}</h3>
																	<div class="row">
																		<div class="col-md-8">
																			<div class="form-group">
																				<label for="sel1">Select Meal:</label> <select
																					class="form-control" id="child{{i}}meal2">
																					<option ng-repeat="mealtrip2 in mealtrip2" ng-value="{{mealtrip2.code}}">
																						{{mealtrip2.AirlineDescription}}~{{mealtrip2.Amount}}~{{mealtrip2.Code}} 
																					</option>
																				</select>
																			</div>
																		</div>
																	</div>
																</div>
															</div>

														</div>
													</div>
													/food Tabes 
												</div>
												<div id="menuc{{i}}2" class="tab-pane fade">
													   <h3>Baggage</h3>


													<div class="row">
														<div class="col-md-12">
															<ul class="nav nav-tabs travellers-f-b-tabs">
																<li class="active"><a data-toggle="tab"
																	href="#bagonewayc{{i}}">{{sourceDetail[1]}}- {{destinationDetail[1]}}</a></li>
																<li><a data-toggle="tab" href="#bagroundc{{i}}" ng-if="result.type==='R'">{{destinationDetail[1]}} - {{sourceDetail[1]}}</a></li>
															</ul>


															<div class="tab-content">
																<div id="bagonewayc{{i}}" class="tab-pane fade in active">
																<h3>{{result.searchDetails.AirlineName}}-{{result.searchDetails.FlightSegments[0].FlightNumber}}</h3>
																	
																	<div class="row">
																		<div class="col-md-8">
																			<div class="form-group">
																				<label for="sel1">Select Baggage:</label> <select
																					class="form-control" id="child{{i}}baggage">
																					<option ng-repeat="baggagetrip1 in baggagetrip1" ng-value="{{baggagetrip1.code}}">
																						{{baggagetrip1.description}}~{{baggagetrip1.amount}}~{{baggagetrip1.code}}</option>
																				</select>
																			</div>
																		</div>
																	</div>
																</div>
																<div id="bagroundc{{i}}" class="tab-pane fade" ng-if="result.type==='R'">
																	<h3>{{result.returnDetail.AirlineName}}-{{result.returnDetail.FlightSegments[0].FlightNumber}}</h3>
																	<div class="row">
																		<div class="col-md-8">
																			<div class="form-group">
																				<label for="sel1">Select Baggage:</label> <select
																					class="form-control" id="child{{i}}baggage2">
																					<option ng-repeat="baggagetrip2 in baggagetrip2" ng-value="{{baggagetrip2.code}}">
																						{{baggagetrip2.description}}~{{baggagetrip2.amount}}~{{baggagetrip2.code}}</option>
																				</select>
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
 -->
										<!-- /(food,Bages) -->
									</div>
								</li>

							</ul>

						</div>

						<div class="panel-body travellers-count">
							<ul>
								<li ng-repeat="i in infantNumber">
									<div class="panel panel-warning passenger-details-form">
										<div class="panel-heading passenger-details-form-heading">

											<div class="row">
												<div class="col-md-4">
													<p class="single-passenger-name">
														<b>Infant <span>{{i}}</span></b> :
													</p>
												</div>


											</div>


										</div>
										<div class="panel-body passenger-details-form-body">
											<div class="single-passenger-details">
												<form>
													<div class="form-group">
														<div class="row">
															<div class="col-md-2">
																<label class="passenger-form-label">Title:</label> <select
																	class="form-control passenger-form-control"
																	id="infanttitle{{i}}">
																	<option>Miss</option>
																	<option>Master</option>
																</select>
															</div>
															<div class="col-md-5">
																<label class="passenger-form-label">First Name &
																	(Middle name, if any)</label> <input type="text"
																	class="form-control passenger-form-control"
																	id="infantfname{{i}}">
															</div>

															<div class="col-md-5">
																<label class="passenger-form-label">Last Name:</label> <input
																	type="text" class="form-control passenger-form-control"
																	id="infantlname{{i}}">
															</div>
														</div>
														<div class="row">
														<div class="col-md-5">
															<label class="passenger-form-label">DOB:</label> 
																<input type="date" class="form-control  passenger-form-control" id="infantdob{{i}}">
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
					<!--seat maping-->
				<!-- 	<div
						class="panel panel-default flight-ticket-card travellers-information-details">
						<div class="panel-heading travellers-information-head">
							<div class="row">
								<div class="col-md-8">
									<div class="flight-card-icon">
										<p class="n-margin">
											<i class="fa fa-chair"></i> <span>Seat Map - Choose
												your seats</span>
										</p>
									</div>
								</div>
								
								<div class="col-md-4">
									<div class="flight-seat-collapes-button">
										<div class="n-margin">
											<button class="btn-info" onclick="seatsCollapes()">Add Seats</button>
										</div>
									</div>
								</div>
							</div>
						</div>


						<div class="panel-body travellers-count" id="seatsCol">
							<div class="row">
								<div class="col-md-12">
									<ul class="nav nav-pills">
									    <li class="active">
									   		 <a data-toggle="pill" href="#foneway">{{sourceDetail[1]}} - {{destinationDetail[1]}}</a>
									    </li>
									    <li>
									    	<a data-toggle="pill" href="#froundway" ng-if="result.type==='R'">{{destinationDetail[1]}} - {{sourceDetail[1]}}</a>
									    </li>									    
									 </ul>
									  
									  
									 <div class="tab-content">
									    <div id="foneway" class="tab-pane fade in active">
											<div class="one-way-seat-map">
												<div class="row">
													<div class="col-md-4">
														<div class="seats-booking-list">
															<div class="panel panel-default">
															  <div class="panel-heading">
															  	<ul class="Indigo list-inline">
															  		<li><img alt="flight-inges" src=""></li>
															  		<li><small>Indigo</small></li>
															  	</ul>
															  </div>
															  <div class="panel-body">
															  	<div class="booking-seats-list">
															  		<li>gfdhbgfhnghnhhg</li>
															  		
															  	</div>
															 </div>
															</div>
														</div>
													</div>

													<div class="col-md-8">
														<div class="scroll">
															<div class="sky-plane">
																<div class="cockpit">
																	<div class="cockpit-top">
																		<img src="assets/skyflight/images/cockpit-top.png">
																	</div>
																</div>
																<div class="front" style="display: none">front</div>
																<div class="exit exit--front fuselage"></div>
																<div class="wingsLeft">
																	<img src="assets/skyflight/images/left-wings.png" alt="Wings">
																</div>
																<div class="wingsRight">
																	<img src="assets/skyflight/images/right-wings.png" alt="Wings">
																</div>
																<ol class="cabin fuselage">

																	<li class="row--1" ng-repeat="seatreq in seatreq[0].SeatLayoutDetail">
																		<ol class="seats" type="a">																			
																			<li class="seat" ng-repeat="seat in seatreq.Seat">
																			
																			<label class="seat-single ssingle" ng-if="seat.status=='1'&&!selectedseats.includes(seat.SeatCode)"	title="Seat Price Rs.{{seat.SeatAmount}}"
																				id="{{seat.SeatCode}}" for="{{seat.SeatCode}}"
																				ng-click="clickseat(seat);">
																						{{seat.SeatCode }}
																			</label>
																			<label class="seat-single sselect" ng-if="seat.status=='1'&&selectedseats.includes(seat.SeatCode)"	title="Seat Price Rs.{{seat.SeatAmount}}"
																				id="{{seat.SeatCode}}" for="{{seat.SeatCode}}"
																				ng-click="clickseat(seat);">
																						{{seat.SeatCode }}
																			</label>
																			
																			 <label class="seat-single occupied" ng-if="seat.status!='1'">{{seat.SeatCode}}</label> 
																			
																			
																			</li>
																		</ol>
																	</li>
																 </ol>
																<div class="exit exit--back fuselage"></div>
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
									    <div id="froundway" class="tab-pane fade">
									    
											<div class="round-way-seat-map">
												<div class="row">
													<div class="col-md-4">
														<h1></h1>
													</div>

													<div class="col-md-8">
														<div class="scroll">
															<div class="sky-plane">
																<div class="cockpit">
																	<div class="cockpit-top">
																		<img src="images/cockpit-top.png">
																	</div>
																</div>
																<div class="front" style="display: none">front</div>
																<div class="exit exit--front fuselage"></div>
																<div class="wingsLeft">
																	<img src="images/left-wings.png" alt="Wings">
																</div>
																<div class="wingsRight">
																	<img src="images/right-wings.png" alt="Wings">
																</div>
																<ol class="cabin fuselage">

																	<li class="row--1">
																		<ol class="seats" type="a">
																			<li class="seat"><label class="seat-single">2A</label>
																			</li>
																			
																		</ol>
																	</li>



																	<li class="row--1">
																		<ol class="seats" type="a">
																			
																			
																		</ol>
																	</li>

																	<li class="row--1">
																		<ol class="seats" type="a">
																			
																		</ol>
																	</li>

																	<li class="row--1">
																		<ol class="seats" type="a">
																			
																		</ol>
																	</li>

																	<li class="row--1">
																		<ol class="seats" type="a">
																			
																		</ol>
																	</li>

																	<li class="row--1">
																		<ol class="seats" type="a">
																			
																		</ol>
																	</li>

																	<li class="row--1">
																		<ol class="seats" type="a">
																			
																		</ol>
																	</li>

																</ol>
																<div class="exit exit--back fuselage"></div>
																
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
 -->
				</div>
				<!-- /flight-ticket-details -->

				<!-- ticket-price-details -->
				<div class="col-md-4 new_fdetails">
					<div class="ticket-price-details">
						<div class="panel panel-default flight-ticket-card">
							<div class="panel-heading flight-ticket-details-head">
								<div class="flight-card-icon">
									<i class="fa fa-money-bill-wave"></i> <span
										class="icon-details"> Price Summary</span>
								</div>
							</div>
							<div class="panel-body">
								<div class="price-panel-body">
									<table class="table price-panel-body-table">
										<tbody>
											<tr>
												<td class="tb-boder">Adult x
													{{result.requestDetails.adult}}</td>
												<td class="text-right tb-boder">INR.
													{{result.Taxoutput.totaladult +
													result.Taxoutput.adminmarkup +
													result.Taxoutput.usermarkup|number:2}}</td>
											</tr>
											<tr ng-if="result.Taxoutput.totalchild!==0">
												<td class="tb-boder">Child x
													{{result.requestDetails.child}}</td>
												<td class="text-right tb-boder">INR.
													{{result.Taxoutput.totalchild}}</td>
											</tr>
											<tr ng-if="result.Taxoutput.totalinfant!==0">
												<td class="tb-boder">Infant x
													{{result.requestDetails.infant}}</td>
												<td class="text-right tb-boder">INR.
													{{result.Taxoutput.totalinfant}}</td>
											</tr>
											<tr>
												<td class="tb-boder">PHF</td>
												<td class="text-right tb-boder">INR. 0</td>
											</tr>
											<tr>
												<td class="tb-boder">RCS</td>
												<td class="text-right tb-boder">INR. 0</td>
											</tr>
											<tr>
												<td class="tb-boder">YQ</td>
												<td class="text-right tb-boder">INR. 0</td>
											</tr>
											<tr>
												<td class="tb-boder">TAX & CHARGE</td>
												<td class="text-right tb-boder">INR.
													{{result.Taxoutput.totaltax}}</td>
											</tr>
											<tr>
												<td class="tb-boder">ADF</td>
												<td class="text-right tb-boder">INR. 0</td>
											</tr>
											<tr ng-if="tatalFare!='0'">
												<td class="tb-boder">Seat</td>
												<td class="text-right tb-boder">INR. {{tatalFare}}</td>
											</tr>
											<tr>
												<td class="tb-boder"><b>Grand Total</b></td>
												<td class="text-right tb-boder"><b>INR.
														{{result.Taxoutput.totalgross +
														result.Taxoutput.adminmarkup +
														result.Taxoutput.usermarkup+tatalFare|number:2}}</b></td>
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

<div class="modal fade" id="squarespaceModal" tabindex="-1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">×</span><span class="sr-only">Close</span>
						</button>
						<h3 class="modal-title" id="lineModalLabel">Are You confirm For Booking Flight</h3>
					</div>
					<div class="modal-body">
						
						<div class="row clearfix"></div>
						<div class="row clearfix">
							<div class="col-lg-offset-6 col-md-offset-6 col-sm-offset-6 col-xs-offset-6">
								<button type="button" class="btn btn-primary m-t-15 waves-effect" data-dismiss="modal" ng-click="bookingFlight(tatalflt)">BOOK</button>
							</div>
						</div>
					</div>					
				</div>
			</div>
		</div>
	<!-- div1012  invoice-->
	
	<div class="invoice" style="padding: 20px 0;background-color: #fff;display: none" id="div1012">
       	 	<div class="in-container" style="width:1170px;padding-right: 15px;padding-left: 15px;margin-right: auto;margin-left: auto;">
       			 <div class="it-row" style="margin-right: 195px;margin-left: -11px;">
                        <div class="page-header-title">
                            <h4 class="pull-left page-title">View Invoice</h4>
                            
                            <ol class="breadcrumb pull-right">
                            	<!-- <li>
                            		<a href="flightsearch" class="btn btn-primary">
                            			<i class="fa fa-arrow-left"></i> Back to Search
                            		</a>
                            	</li> -->
                            	<li><button type="button" class="btn btn-info" ng-click="printInvoice('myInvoice1');"><i class="fa fa-print"></i> Print</button></li>
	                            
                            </ol>
                            <div class="clearfix"></div>
                        </div>
                   	</div>
            <div class="inv-row" style="margin-right: -15px;margin-left: -15px;" id="myInvoice1">
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
                                                <li style="margin-bottom: 8px;">Invoice Date : <span> {{ticketdt[0].IssueDateTime|date : "MMMM d, y"}}</span></li>
                                                <!-- <li style="margin-bottom: 8px;">PAN No : <span>AADCM5146R</span></li>
                                                <li style="margin-bottom: 8px;">GSTIN No : <span>  06AADCM5146R1ZZ</span></li>
                                                <li style="margin-bottom: 8px;">Service Category : <span>  Reservation services for air transportation. SAC Code : 998551</span></li> -->
                                            </ul>
                                        </div>
                                    </div>
                                    <div class="col-md-4-inv" style="width:33%;position: relative;min-height: 1px;padding-right: 15px;padding-left: 15px;float: left;">
                                        <div class="img-responsive-in">
									<a class="navbar-brand "><img class="logo img-responsive" id="logo2" src = ""></a>
                                        </div>
                                        <!-- <h3 style="margin: 5px;font-size: 15px;font-weight: 600;">Customer Details</h3>
                                        <p>Place of Supply: Kolkata</p> -->
                                    </div>
                                </div>
                                 <div class="in-tebil table-responsive-in" style="min-height: .01%;overflow-x: auto;">
                                    <table class="table-in in-table-bordered" style="width: 100%;max-width: 100%;margin-bottom: 20px;border: 1px solid #ddd;">
                                        <thead>
                                        <tr style="border-bottom: 1px solid #ccc;">
                                            <th style="border-right: 1px solid #ccc;font-size: 12px;font-weight: 500;padding: 10px;">Booked by</th>
                                            <!-- <th style="border-right: 1px solid #ccc;font-size: 12px;font-weight: 500;padding: 10px;">Contact</th> -->
                                            <th style="border-right: 1px solid #ccc;font-size: 12px;font-weight: 500;padding: 10px;"   style="border-right: 1px solid #ccc;">Booked ID </th>
                                            <th style="border-right: 1px solid #ccc;font-size: 12px;font-weight: 500;padding: 10px;"   style="border-right: 1px solid #ccc;">Booked Date</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                            <tr >
                                                <td style="border-right: 1px solid #ccc;padding: 10px;">{{ticketdt[0].Passenger[0].Title}}. {{ticketdt[0].Passenger[0].FirstName}} {{ticketdt[0].Passenger[0].LastName}}, {{ticketdt[0].Passenger[0].PaxType}}</td>
                                               <!--  <td style="border-right: 1px solid #ccc;padding: 10px;">{{passen.contact}}<br>{{passen.email}}</td> -->
                                                <td style="border-right: 1px solid #ccc;padding: 10px;">{{ticketdt[0].Passenger[0].TicketNumber}}</td>
                                                <td style="border-right: 1px solid #ccc;padding: 10px;">{{ticketdt[0].Passenger[0].BookedSegments[0].DepartureDateTime|date : "MMMM d, y"}}</td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>

                                <div class="in-tebil table-responsive-in" style="min-height: .01%;overflow-x: auto;">
                                    <h5 style="font-size: 15px;font-weight: 600;">Flight Details</h5>
                                    <table class="table-in in-table-bordered" style="width: 100%;max-width: 100%;margin-bottom: 20px;border: 1px solid #ddd;">
                                        <thead>
                                        <tr style="border-bottom: 1px solid #ccc;" ng-repeat="fligh in ticketdt[0].Passenger[0].BookedSegments">
                                            <th style="border-right: 1px solid #ccc;font-size: 12px;font-weight: 500;padding: 10px;">{{fligh.AirlineCode}}-{{fligh.FlightNumber}}</th>
                                            <th style="border-right: 1px solid #ccc;font-size: 12px;font-weight: 500;padding: 10px;"   style="border-right: 1px solid #ccc;">{{fligh.Origin}}({{fligh.OriginAirport}})</th>
                                            <th style="border-right: 1px solid #ccc;font-size: 12px;font-weight: 500;padding: 10px;"   style="border-right: 1px solid #ccc;">DEPT : {{fligh.DepartureDateTime}}</th>
                                             <th style="border-right: 1px solid #ccc;font-size: 12px;font-weight: 500;padding: 10px;"   style="border-right: 1px solid #ccc;">ARRI : {{fligh.Arrivaldatetime}}</th>
                                            <th style="border-right: 1px solid #ccc;font-size: 12px;font-weight: 500;padding: 10px;"   style="border-right: 1px solid #ccc;">{{fligh.Destination}}({{fligh.DestinationAirport}})</th>
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
                                        <tr style="border-bottom: 1px solid #ccc;" ng-repeat="passen in ticketdt[0].Passenger">
                                            <th style="border-right: 1px solid #ccc;font-size: 12px;font-weight: 500;padding: 10px;">{{$index+1}}) {{passen.Title}}. {{passen.FirstName}} {{passen.LastName}}, {{passen.PaxType}}
                                            </th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        </tbody>
                                    </table>
                                </div>
<!-- 
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
                                            <tr ng-repeat="istPassengerfare in ticketdt.">
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
                                                <td style="border-right: 1px solid #ccc;padding: 10px;">{{listFlightFare.totalamount+listFlightFare.usermarkup+listFlightFare.adminmarkup|number:2}}</td>
                                            </tr>
                                        </tbody>
                                    </table>
                                    </div>
                                    </div>
                                </div> -->

                                <div class="in-tebil table-responsive-in" style="min-height: .01%;overflow-x: auto;">
                                    <table class="table-in in-table-bordered" style="width: 100%;max-width: 100%;margin-bottom: 20px;border: 1px solid #ddd;">
                                        <thead>
                                        <tr style="border-bottom: 1px solid #ccc;">
                                            <th style="border-right: 1px solid #ccc;font-size: 12px;font-weight: 500;padding: 10px;">*Total Fare (All Passenger):</th>
                                            <th style="border-right: 1px solid #ccc;font-size: 12px;font-weight: 500;padding: 10px;"   style="border-right: 1px solid #ccc;">INR {{ticketdt[0].TotalAmount+adminmarkup+usermarkup|number:2}}</th>
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
                                                        <td style="border-right:1px solid #ccc; border-bottom: 1px solid #ccc; padding: 10px;">Office: 3, Canal Street, Loknath Apartment, 1st Floor, Kolkata - 700014 (India)</td>
                                                        <td style="border-right:1px solid #ccc; border-bottom: 1px solid #ccc; padding: 10px;">+91-9811253330/6289812191</td>
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
                                                    <!-- <tr>
                                                        <td style="border-right:1px solid #ccc; border-bottom: 1px solid #ccc; padding: 10px;font-size: 13px;font-weight: normal;">Fax No.</td>
                                                        <td style="border-right:1px solid #ccc; border-bottom: 1px solid #ccc; padding: 10px;font-size: 13px;font-weight: normal;">address</td>
                                                    </tr>
                                                    <tr>
                                                        <td style="border-right:1px solid #ccc; border-bottom: 1px solid #ccc; padding: 10px;font-size: 13px;font-weight: normal;">Corporate Identity No.(CIN)</td>
                                                        <td style="border-right:1px solid #ccc; border-bottom: 1px solid #ccc; padding: 10px;font-size: 13px;font-weight: normal;">address</td>
                                                    </tr> -->
                                                    <tr>
                                                        <td style="border-right:1px solid #ccc; border-bottom: 1px solid #ccc; padding: 10px;font-size: 13px;font-weight: normal;">Website Address</td>
                                                        <td style="border-right:1px solid #ccc; border-bottom: 1px solid #ccc; padding: 10px;font-size: 13px;font-weight: normal;">http://encodigi.net.in/</td>
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
	<script src="assets/skyflight/js/bootstrap.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/angular-ui-bootstrap/0.14.3/ui-bootstrap-tpls.js"></script>
	<script src="https://rawgit.com/rzajac/angularjs-slider/master/dist/rzslider.js"></script>
	<script src="assets/scripts/angular_skyflight.js?version=1.0.5"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/owl-carousel/1.3.3/owl.carousel.js"></script>
	<!-- Owl Carousel Min JS -->
	<script src="assets/skyflight/js/owl.carousel.min.js"></script>
	<!-- Nice Select Min JS -->
	<script src="assets/skyflight/js/jquery-ui.min.js"></script>
	<!-- Main JS -->
	<script src="assets/skyflight/js/main.js"></script>


	<!-- flight date -->
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
//	var sticky = header.offsetTop;
		
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