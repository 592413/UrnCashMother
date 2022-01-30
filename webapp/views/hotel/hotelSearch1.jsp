<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@page import="com.recharge.model.User"%>
<%@page import="org.apache.log4j.Logger"%>
<%
	User user = (User) session.getAttribute("UserDetails");
%>
<!doctype html>
<html lang="zxx">

<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link href="https://fonts.googleapis.com/css?family=Poppins&display=swap" rel="stylesheet">
    <!-- Bootstrap Min CSS -->
    <link rel="stylesheet" href="assets/skyhotels/css/bootstrap.min.css">
    <!-- Font Awesome Min CSS -->
    <link rel="stylesheet" href="assets/skyhotels/css/fontawesome.min.css">
    <!-- Owl Carousel Min CSS -->
    <link rel="stylesheet" href="assets/skyhotels/css/owl.carousel.min.css">
    <link rel="stylesheet" href="assets/skyhotels/css/owl.theme.default.min.css">
    <!-- Owl Carousel Min CSS -->
    <link rel="stylesheet" href="assets/skyhotels/css/jquery-ui.min.css">
    <!-- Style CSS -->
    <link rel="stylesheet" href="assets/skyhotels/css/style.css">
    <!-- Responsive CSS -->
    <link rel="stylesheet" href="assets/skyhotels/css/responsive.css">
     <link href="https://rawgit.com/rzajac/angularjs-slider/master/dist/rzslider.css" rel="stylesheet">
    
    <title>Hotel</title>
</head>

<style>
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
    
    .alert {
	position: absolute;
	z-index: 999999;
	left: 40%;
	top: 29%;
	width: 27%;
}
    
   /* #############by Romita#################### */
.rating {
    color: #e2dfdf;
    margin: 0;
    padding: 0;
}
ul.rating {
    display: inline-block;
}
.rating li {
    list-style-type: none;
    display: inline-block;
    padding: 1px;
    text-align: center;
    font-weight: bold;
    cursor: pointer;
    font-size:20px;
}
.rating .filled {
    color: #098ce4;
}
.clear{
  margin-top:20px;
}


/* #############by Romita#################### */
#slides_control > div{
  height: 200px;
}

#slides_control {
  position:absolute;
  width: 400px;
  left:50%;
  top:20px;
 margin-left:-200px;
}
    
</style>

<body style="background-color: #FDFBFB" ng-app="app" ng-controller="appController">
<!-- Preloader -->
<div class="page-loader-wrapper" ng-show="loader">
    <div class="loader">
        <div class="preloader">
            <img alt="" src="assets/skyhotels/images/hotel/building_loader.gif">
        </div>
    </div>
</div>
 <alert-message alert="alertMessage"></alert-message> 

<!-- <div class="preloader">
    <div class="loader">
        <div class="loader-outter"></div>
        <div class="loader-inner"></div>
    </div>
</div> -->
<!-- End Preloader -->

<!--navigation Area-->
<div class="container-fluid">
    <nav class="navbar-default main-navbar-style pb-0px">
        <div class="container-fluid">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="" href="">
                    <img src="assets/skyhotels/images/logo/hlogo.png" style="width:28%">
                </a>
            </div>

            <!--display nav list right after nav logo -->
            <ul class="nav navbar-nav  nav-list" style="display: none">
                <li><a href="#">Flight Search</a></li>
                <li><a href="#">Booking Queues</a></li>
            </ul>
            <!---->

            <div id="navbar1" class="navbar-collapse collapse">
                <ul class="nav navbar-nav navbar-right nav-list">
                    <li><a href="#">Flight Search</a></li>
                    <li><a href="#">Booking Queues</a></li>
                    <li><a href="#">Tickets Queues</a></li>
                    <li><a href="#"> Change Request Queues</a></li>
                    <!--dropdown list (if it necessity )-->
                    <li class="dropdown" style="display: none">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Dropdown <span class="caret"></span></a>
                        <ul class="dropdown-menu" role="menu">
                            <li><a href="#">Action</a></li>
                            <li><a href="#">Another action</a></li>
                            <li><a href="#">Something else here</a></li>
                            <li><a href="#">Separated link</a></li>
                            <li><a href="#">One more separated link</a></li>
                        </ul>
                    </li>
                    <!--dropdown list (if it necessity )-->
                </ul>
            </div>
        </div>
    </nav>
</div>
<!--navigation Area-->

<div id="modisearch" class="flight-list-page-search fli_serch hidden-sm hidden-xs">
    <div class="container">
        <div class="row">
            <div class="flight-search-form serch_mid">
                <form>
                    <div class="form-group">
                        <div class="row">
                            <div class="col-md-4">
                                <label>Enter City name, Location or Specific hotel</label>
                                <input type="text" id="destination" class="form-control ui-widget" ng-model="hotelsearch.destination" ng-keyup="getCityAutoFill(hotelsearch.destination, 'destination');">
                            </div>

                            <div class="col-md-2">
                                <label>Check-in </label>
                                <input type="text" class="form-control" ng-model="hotelsearch.checkin" id="dp1" readonly>
                            </div>

                            <div class="col-md-2">
                                <label>Check-out</label>
                               <input type="text" class="form-control" ng-model="hotelsearch.checkout" id="dp2" readonly>
                            </div>

                            <div class="col-md-2 room_rl">
                                <label>Rooms & Guests</label>
                                <div class="form-control" data-toggle="collapse" data-target="#RoomGuests2">
                                	<span>{{hotelsearch.rooms}} Rooms,</span>
                                	<span>{{hotelsearch.adultno1 + hotelsearch.childno1 + hotelsearch.adultno2 + hotelsearch.childno2 + hotelsearch.adultno3 + hotelsearch.childno3 + hotelsearch.adultno4 + hotelsearch.childno4}} Guests</span>
                                </div>
					            <!-- Room Guests -->
					            <div class="collapse abs_mnu" id="RoomGuests2">
                                    <div class="card">
                                       
                                       <div id="room_c_1" style="display: block;" class="ad_rm" ng-show="hotelsearch.rooms>0">
                                           <span class="r_txt">Room 1 :</span>
                                               <div class="row">
                                                   <div class="col-md-7">
                                                       <p>Adult <span class="abov">(Above 12 years)</span></p>
                                                   </div>
                                                   <div class="col-md-5 mb-10">
                                                       <ul class="pls_mns">
                                                       		<li ng-if="hotelsearch.adultno1 === 0">-</li>
                                                           <li ng-if="hotelsearch.adultno1 > 0" ng-click="hotelsearch.adultno1 = hotelsearch.adultno1 - 1;">-</li>
                                                           <li><input class="val_f" type="text" name="" id="value2"  ng-model="hotelsearch.adultno1" ng-init="hotelsearch.adultno1 = 2"/></li>
                                                           <li ng-if="hotelsearch.adultno1 < 4" ng-click="hotelsearch.adultno1 =  hotelsearch.adultno1 + 1;">+</li>
                                                           <li ng-if="hotelsearch.adultno1 === 4">+</li>
                                                       </ul>
                                                   </div>
                                               </div>
                                               <div class="row">
                                                   <div class="col-md-7">
                                                       <p>Child <span class="abov">(Below 12 years)</span></p>
                                                   </div>
                                                   <div class="col-md-5">
                                                       <ul class="pls_mns">
                                                       		<li ng-if="hotelsearch.childno1 === 0">-</li>
                                                           <li ng-if="hotelsearch.childno1 > 0" ng-click="hotelsearch.childno1 = hotelsearch.childno1 - 1;" ng-min="0">-</li>
                                                           <li><input class="val_f" type="text" name="" id="value" value="0"  ng-model="hotelsearch.childno1" ng-init="hotelsearch.childno1=0"/></li>
                                                           <li ng-if="hotelsearch.childno1 < 2" ng-click="hotelsearch.childno1 = hotelsearch.childno1 + 1;">+</li>
                                                           <li ng-if="hotelsearch.childno1 === 2">+</li>
                                                       </ul>
                                                   </div>
                                               </div>
                                               <div class="row" ng-show="hotelsearch.childno1>0">
                                                   <div class="col-md-7">
                                                       <p>Age(s) <span class="abov">of Children</span></p>
                                                   </div>
                                               </div>
                                               <div class="row mb-20" ng-show="hotelsearch.childno1 > 0">
                                                   <div class="col-md-4">
                                                       <select class="form-control"  ng-model="hotelsearch.childage11" ng-init="hotelsearch.childage11='1'">
                                                           <option value="1">1</option>
							                               <option value="2">2</option>
							                               <option value="3">3</option>
							                               <option value="4">4</option>
							                               <option value="5">5</option>
							                               <option value="6">6</option>
							                               <option value="7">7</option>
							                               <option value="8">8</option>
							                               <option value="9">9</option>
							                               <option value="10">10</option>
							                               <option value="11">11</option>
							                               <option value="12">12</option>
                                                       </select>
                                                   </div>
                                                   <div class="col-md-4" ng-if="hotelsearch.childno1 === 2">
                                                       <select class="form-control" ng-model="hotelsearch.childage12" ng-init="hotelsearch.childage12='1'">
                                                            <option value="1">1</option>
								                               <option value="2">2</option>
								                               <option value="3">3</option>
								                               <option value="4">4</option>
								                               <option value="5">5</option>
								                               <option value="6">6</option>
								                               <option value="7">7</option>
								                               <option value="8">8</option>
								                               <option value="9">9</option>
								                               <option value="10">10</option>
								                               <option value="11">11</option>
								                               <option value="12">12</option>
                                                       </select>
                                                   </div>
                                               </div>
                                        </div>
                                        <!-- <div id="room_b_1" style="display: block;" class="row">
                                           <div class="col-md-5 hotel-desc mt-10">
                                               <button id="room_btna_1" class="addbtn" type="button" ng-click="hotelsearch.rooms = hotelsearch.rooms + 1;">Add room</button>
                                           </div>
                                           <div class="col-md-4 hotel-desc mt-10">
                                              <div class="row">
                                              </div>
                                           </div>
                                           <div class="col-md-3 price_m">
                                               <div class="row">
                                                 <a id="room_btnd_1" class="done_t" href="#">done</a>
                                               </div>
                                           </div>
                                       </div> -->

                                      <!-- room 2 -->
                                        <div id="room_c_2" class="ad_rm" ng-show="hotelsearch.rooms >= 2">
                                           <span class="r_txt">Room 2 :</span>
                                               <div class="row">
                                                   <div class="col-md-7">
                                                       <p>Adult <span class="abov">(Above 12 years)</span></p>
                                                   </div>
                                                   <div class="col-md-5 mb-10">
                                                       <ul class="pls_mns">
                                                       		<li ng-if="hotelsearch.adultno2 === 0">-</li>
                                                           <li ng-if="hotelsearch.adultno2 > 0" ng-click="hotelsearch.adultno2 = hotelsearch.adultno2 - 1;">-</li>
                                                           <li><input class="val_f" type="text" name="" id="value3" value="0" ng-model="hotelsearch.adultno2" ng-init="hotelsearch.adultno2=0" /></li>
                                                           <li ng-if="hotelsearch.adultno2 < 4" ng-click="hotelsearch.adultno2 = hotelsearch.adultno2 + 1;">+</li>
                                                           <li ng-if="hotelsearch.adultno2 === 4">+</li>
                                                       </ul>
                                                   </div>
                                               </div>
                                               <div class="row">
                                                   <div class="col-md-7">
                                                       <p>Child <span class="abov">(Below 12 years)</span></p>
                                                   </div>
                                                   <div class="col-md-5">
                                                       <ul class="pls_mns">
                                                       		<li ng-if="hotelsearch.childno2 === 0">-</li>
                                                           <li ng-if="hotelsearch.childno2 > 0" ng-click="hotelsearch.childno2 = hotelsearch.childno2 - 1;">-</li>
                                                           <li><input class="val_f" type="text" name="" id="value_c_1" value="0"   ng-model="hotelsearch.childno2" ng-init="hotelsearch.childno2=0"/></li>
                                                           <li ng-if="hotelsearch.childno2 < 2" ng-click="hotelsearch.childno2 = hotelsearch.childno2 + 1;">+</li>
                                                           <li ng-if="hotelsearch.childno2 === 2">+</li>
                                                       </ul>
                                                   </div>
                                               </div>
                                               <div class="row" ng-show="hotelsearch.childno2>0">
                                                   <div class="col-md-7">
                                                       <p>Age(s) <span class="abov">of Children</span></p>
                                                   </div>
                                               </div>
                                                 <div class="row mb-20" ng-show="hotelsearch.childno2>0">
                                                   <div class="col-md-4">
                                                       <select class="form-control"  ng-model="hotelsearch.childage21" ng-init="hotelsearch.childage21='1'">
                                                           <option value="1">1</option>
							                               <option value="2">2</option>
							                               <option value="3">3</option>
							                               <option value="4">4</option>
							                               <option value="5">5</option>
							                               <option value="6">6</option>
							                               <option value="7">7</option>
							                               <option value="8">8</option>
							                               <option value="9">9</option>
							                               <option value="10">10</option>
							                               <option value="11">11</option>
							                               <option value="12">12</option>
                                                       </select>
                                                   </div>
                                                   <div class="col-md-4" ng-if="hotelsearch.childno2===2">
                                                       <select class="form-control" ng-model="hotelsearch.childage22" ng-init="hotelsearch.childage22='1'">
                                                            <option value="1">1</option>
								                               <option value="2">2</option>
								                               <option value="3">3</option>
								                               <option value="4">4</option>
								                               <option value="5">5</option>
								                               <option value="6">6</option>
								                               <option value="7">7</option>
								                               <option value="8">8</option>
								                               <option value="9">9</option>
								                               <option value="10">10</option>
								                               <option value="11">11</option>
								                               <option value="12">12</option>
                                                       </select>
                                                   </div>
                                               </div>
                                        </div>
                                        <!-- <div id="room_b_2" style="display: none;" class="row">
                                           <div class="col-md-5 hotel-desc mt-10">
                                               <button id="room_btna_2" class="addbtn" type="button" ng-click="hotelsearch.rooms = hotelsearch.rooms + 1;">Add room</button>
                                           </div>
                                           <div class="col-md-4 hotel-desc mt-10">
                                              <div class="row">
                                                <button id="room_btnr_2" class="addbtn_rd" type="button" ng-click="hotelsearch.rooms = hotelsearch.rooms - 1;">Remove</button>
                                              </div>
                                           </div>
                                           <div class="col-md-3 price_m">
                                               <div class="row">
                                                 <a id="room_btnd_2" class="done_t" href="#">done</a>
                                               </div>
                                           </div>
                                        </div> -->
                                      <!-- room 2 -->

                                      <!-- room 3 -->
                                        <div id="room_c_3" class="ad_rm" ng-show="hotelsearch.rooms >= 3">
                                           <span class="r_txt">Room 3 :</span>
                                               <div class="row">
                                                   <div class="col-md-7">
                                                       <p>Adult <span class="abov">(Above 12 years)</span></p>
                                                   </div>
                                                   <div class="col-md-5 mb-10">
                                                       <ul class="pls_mns">
                                                       		<li ng-if="hotelsearch.adultno3 === 0">-</li>
                                                           <li ng-if="hotelsearch.adultno3 > 0" ng-click="hotelsearch.adultno3 = hotelsearch.adultno3 - 1;">-</li>
                                                           <li><input class="val_f" type="text" name="" id="value4" value="0" ng-model="hotelsearch.adultno3" ng-init="hotelsearch.adultno3=0" /></li>
                                                           <li ng-if="hotelsearch.adultno3 < 4" ng-click="hotelsearch.adultno3 = hotelsearch.adultno3 + 1;">+</li>
                                                           <li ng-if="hotelsearch.adultno3 === 4">+</li>
                                                       </ul>
                                                   </div>
                                               </div>
                                               <div class="row">
                                                   <div class="col-md-7">
                                                       <p>Child <span class="abov">(Below 12 years)</span></p>
                                                   </div>
                                                   <div class="col-md-5">
                                                       <ul class="pls_mns">
                                                       		<li ng-if="hotelsearch.childno3 === 0">-</li>
                                                           <li ng-if="hotelsearch.childno3 > 0" ng-click="hotelsearch.childno3 = hotelsearch.childno3 - 1;">-</li>
                                                           <li><input class="val_f" type="text" name="" id="value_c_2" value="0" ng-model="hotelsearch.childno3" ng-init="hotelsearch.childno3=0" /></li>
                                                           <li ng-if="hotelsearch.childno3 < 2" ng-click="hotelsearch.childno3 = hotelsearch.childno3 + 1;">+</li>
                                                           <li ng-if="hotelsearch.childno3 === 2">+</li>
                                                       </ul>
                                                   </div>
                                               </div>
                                               <div class="row" ng-show="hotelsearch.childno3>0">
                                                   <div class="col-md-7">
                                                       <p>Age(s) <span class="abov">of Children</span></p>
                                                   </div>
                                               </div>
                                                <div class="row mb-20" ng-show="hotelsearch.childno3>0">
                                                   <div class="col-md-4">
                                                       <select class="form-control"  ng-model="hotelsearch.childage31" ng-init="hotelsearch.childage31='1'">
                                                           <option value="1">1</option>
							                               <option value="2">2</option>
							                               <option value="3">3</option>
							                               <option value="4">4</option>
							                               <option value="5">5</option>
							                               <option value="6">6</option>
							                               <option value="7">7</option>
							                               <option value="8">8</option>
							                               <option value="9">9</option>
							                               <option value="10">10</option>
							                               <option value="11">11</option>
							                               <option value="12">12</option>
                                                       </select>
                                                   </div>
                                                   <div class="col-md-4" ng-if="hotelsearch.childno3===2">
                                                       <select class="form-control" ng-model="hotelsearch.childage32" ng-init="hotelsearch.childage32='1'">
                                                            <option value="1">1</option>
								                               <option value="2">2</option>
								                               <option value="3">3</option>
								                               <option value="4">4</option>
								                               <option value="5">5</option>
								                               <option value="6">6</option>
								                               <option value="7">7</option>
								                               <option value="8">8</option>
								                               <option value="9">9</option>
								                               <option value="10">10</option>
								                               <option value="11">11</option>
								                               <option value="12">12</option>
                                                       </select>
                                                   </div>
                                               </div>
                                        </div>
                                        <!-- <div id="room_b_3" style="display: none;" class="row">
                                           <div class="col-md-5 hotel-desc mt-10">
                                               <button id="room_btna_3" class="addbtn" type="button">Add room</button>
                                           </div>
                                           <div class="col-md-4 hotel-desc mt-10">
                                              <div class="row">
                                                <button id="room_btnr_3" class="addbtn_rd" type="button">Remove</button>
                                              </div>
                                           </div>
                                           <div class="col-md-3 price_m">
                                               <div class="row">
                                                 <a id="room_btnd_3" class="done_t" href="#">done</a>
                                               </div>
                                           </div>
                                        </div> -->
                                      <!-- room 3 -->

                                      <!-- room 4 -->
                                        <div id="room_c_4" class="ad_rm" ng-show="hotelsearch.rooms >= 4">
                                           <span class="r_txt">Room 4 :</span>
                                               <div class="row">
                                                   <div class="col-md-7">
                                                       <p>Adult <span class="abov">(Above 12 years)</span></p>
                                                   </div>
                                                   <div class="col-md-5 mb-10">
                                                       <ul class="pls_mns">
                                                       		<li ng-if="hotelsearch.adultno4 === 0">-</li>
                                                           <li ng-if="hotelsearch.adultno4 > 0" ng-click="hotelsearch.adultno4 = hotelsearch.adultno4 - 1;">-</li>
                                                           <li><input class="val_f" type="text" name="" id="value5" value="0" ng-model="hotelsearch.adultno4" ng-init="hotelsearch.adultno4=0"/></li>
                                                           <li ng-if="hotelsearch.adultno4 < 4" ng-click="hotelsearch.adultno4 = hotelsearch.adultno4 + 1;">+</li>
                                                           <li ng-if="hotelsearch.adultno4 === 4">+</li>
                                                       </ul>
                                                   </div>
                                               </div>
                                               <div class="row">
                                                   <div class="col-md-7">
                                                       <p>Child <span class="abov">(Below 12 years)</span></p>
                                                   </div>
                                                   <div class="col-md-5">
                                                       <ul class="pls_mns">
                                                       		<li ng-if="hotelsearch.childno4 === 0">-</li>
                                                           <li ng-if="hotelsearch.childno4 > 0" ng-click="hotelsearch.childno4 = hotelsearch.childno4 - 1;">-</li>
                                                           <li><input class="val_f" type="text" name="" id="value_c_3" value="0" ng-model="hotelsearch.childno4" ng-init="hotelsearch.childno4=0" /></li>
                                                           <li ng-if="hotelsearch.childno4 < 2" ng-click="hotelsearch.childno4 = hotelsearch.childno4 + 1;">+</li>
                                                           <li ng-if="hotelsearch.childno4 === 2">+</li>
                                                       </ul>
                                                   </div>
                                               </div>
                                               <div class="row" ng-show="hotelsearch.childno4>0">
                                                   <div class="col-md-7">
                                                       <p>Age(s) <span class="abov">of Children</span></p>
                                                   </div>
                                               </div>
                                              <div class="row mb-20" ng-show="hotelsearch.childno4>0">
                                                   <div class="col-md-4">
                                                       <select class="form-control"  ng-model="hotelsearch.childage41" ng-init="hotelsearch.childage41='1'">
                                                           <option value="1">1</option>
							                               <option value="2">2</option>
							                               <option value="3">3</option>
							                               <option value="4">4</option>
							                               <option value="5">5</option>
							                               <option value="6">6</option>
							                               <option value="7">7</option>
							                               <option value="8">8</option>
							                               <option value="9">9</option>
							                               <option value="10">10</option>
							                               <option value="11">11</option>
							                               <option value="12">12</option>
                                                       </select>
                                                   </div>
                                                   <div class="col-md-4" ng-if="hotelsearch.childno4===2">
                                                       <select class="form-control" ng-model="hotelsearch.childage42" ng-init="hotelsearch.childage42='1'">
                                                            <option value="1">1</option>
								                               <option value="2">2</option>
								                               <option value="3">3</option>
								                               <option value="4">4</option>
								                               <option value="5">5</option>
								                               <option value="6">6</option>
								                               <option value="7">7</option>
								                               <option value="8">8</option>
								                               <option value="9">9</option>
								                               <option value="10">10</option>
								                               <option value="11">11</option>
								                               <option value="12">12</option>
                                                       </select>
                                                   </div>
                                               </div>
                                        </div>
                                        <!-- <div id="room_b_4" style="display: none;" class="row">
                                           <div class="col-md-5 hotel-desc mt-10">
                                               <button id="room_btnr_4" class="addbtn_rd" type="button">Remove</button>
                                           </div>
                                           <div class="col-md-4 hotel-desc mt-10">
                                              <div class="row">
                                                
                                              </div>
                                           </div>
                                           <div class="col-md-3 price_m">
                                               <div class="row">
                                                 <a id="room_btnd_4" class="done_t" href="#">done</a>
                                               </div>
                                           </div>
                                        </div> -->
                                        <div id="room_b_2" class="row">
                                           <div class="col-md-5 hotel-desc mt-10" ng-show="hotelsearch.rooms != 4">
                                               <button id="room_btna_2" class="addbtn" type="button" ng-click="hotelsearch.rooms = hotelsearch.rooms + 1;">Add room</button>
                                           </div>
                                           <div class="col-md-4 hotel-desc mt-10"  ng-show="hotelsearch.rooms >= 2">
                                              <div class="row">
                                                <button ng-if="hotelsearch.rooms === 2" class="addbtn_rd" type="button" ng-click="hotelsearch.rooms = hotelsearch.rooms - 1; hotelsearch.adultno2 = 0; hotelsearch.childno2 = 0;">Remove</button>
                                                <button ng-if="hotelsearch.rooms === 3" class="addbtn_rd" type="button" ng-click="hotelsearch.rooms = hotelsearch.rooms - 1; hotelsearch.adultno3 = 0; hotelsearch.childno3 = 0;">Remove</button>
                                                <button ng-if="hotelsearch.rooms === 4" class="addbtn_rd" type="button" ng-click="hotelsearch.rooms = hotelsearch.rooms - 1; hotelsearch.adultno4 = 0; hotelsearch.childno4 = 0;">Remove</button>
                                              </div>
                                           </div>
                                           <div class="col-md-3 price_m pull-right">
                                               <div class="row">
                                                 <a id="room_btnd_2" class="done_t" href="#">done</a>
                                               </div>
                                           </div>
                                        </div>
                                      <!-- room 4 -->

                                    </div>
                                </div>
					            <!-- Room Guests -->
                            </div>

                            <div class="col-md-2">
                                <div class="row">
                                    <button id="Searchmodi" class="bookbtn mbtn mt-20" ng-click="searchHotal(hotelsearch);"><i class="fas fa-search"></i> Search</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<!-- hotel serch -->
<div id="div1" class="flight-list" style="display: none;">
    <div class="container">
        <div class="row">
            <!--flight Search Filter section-->
            <div class="col-md-3 hidden-sm hidden-xs">
                <div class="search-filter">
                    <div class="search-filter-head">
                        <h3>Filter</h3>
                        <div class="clear-filter">
                            <a href="#">
                                Clear all
                            </a>
                        </div>
                    </div>

                    <!--price filter-->
                    <div class="price-range">
                        <div class="flight-header-name range-header mb-30">
                            <h6>Price Range</h6>

                            <span class="collapse-icon">
                                <i class="fa fa-minus  mt-10 mb-10"></i>
                            </span>
                        </div>

                        <div class="checklist">
                             <span class="multi-range ">
                               <rzslider rz-slider-model="hotel_slider_translate.minValue" rz-slider-high="hotel_slider_translate.maxValue" rz-slider-options="hotel_slider_translate.options"></rzslider>
                             </span>
                        </div>
                    </div>

                    <!--stopeg filter-->
                    <div class="flight-stop">
                        <div class="flight-header-name flight-stop-header">
                            <h6>Star Rating</h6>

                            <span class="collapse-icon">
                                <i class="fa fa-minus  mt-10 mb-10"></i>
                            </span>
                        </div>

                        <div class="checklist flight-stop-list">
                            <ul>
                                <li>
                                    <div class="checkbox">
                                        <label>
                                            <input type="checkbox" value="">
                                            <i class="fa fa-star"></i>
                                        </label>
                                    </div>
                                </li>

                                <li>
                                    <div class="checkbox">
                                        <label><input type="checkbox" value="">
                                            <i class="fa fa-star"></i>
                                            <i class="fa fa-star"></i>
                                        </label>
                                    </div>
                                </li>

                                <li>
                                    <div class="checkbox">
                                        <label><input type="checkbox" value="">
                                            <i class="fa fa-star"></i>
                                            <i class="fa fa-star"></i>
                                            <i class="fa fa-star"></i>
                                        </label>
                                    </div>
                                </li>


                                <li>
                                    <div class="checkbox">
                                        <label><input type="checkbox" value="">
                                            <i class="fa fa-star"></i>
                                            <i class="fa fa-star"></i>
                                            <i class="fa fa-star"></i>
                                            <i class="fa fa-star"></i>

                                        </label>
                                    </div>
                                </li>

                                <li>
                                    <div class="checkbox">
                                        <label><input type="checkbox" value="">
                                            <i class="fa fa-star"></i>
                                            <i class="fa fa-star"></i>
                                            <i class="fa fa-star"></i>
                                            <i class="fa fa-star"></i>
                                            <i class="fa fa-star"></i>

                                        </label>
                                    </div>
                                </li>

                                <li>
                                    <div class="checkbox">
                                        <label><input type="checkbox" value="">
                                            Unrated

                                        </label>
                                    </div>
                                </li>
                            </ul>
                        </div>
                    </div>

                    <!--airloinesd name-->
                    <div class="airlines-name" style="display: none">
                        <div class="flight-header-name airlines-name-header ">
                            <h6>Amenities</h6>

                            <span class="collapse-icon">
                                <i class="fa fa-minus  mt-10 mb-10"></i>
                            </span>
                        </div>

                        <div class="checklist flight-stop-list">
                            <ul>
                                <li>
                                    <div class="checkbox">
                                        <label><input type="checkbox" value="">Spa</label>
                                    </div>
                                </li>

                                <li>
                                    <div class="checkbox">
                                        <label><input type="checkbox" value="">
                                            Swimming pool
                                        </label>
                                    </div>
                                </li>

                                <li>
                                    <div class="checkbox">
                                        <label><input type="checkbox" value="">
                                            parking
                                        </label>
                                    </div>
                                </li>

                                <li>
                                    <div class="checkbox">
                                        <label><input type="checkbox" value="">

                                            Restaurant

                                        </label>
                                    </div>
                                </li>

                                <li>
                                    <div class="checkbox">
                                        <label><input type="checkbox" value="">

                                            Air Condition

                                        </label>
                                    </div>
                                </li>

                                <li>
                                    <div class="checkbox">
                                        <label><input type="checkbox" value="">

                                            Mini bar

                                        </label>
                                    </div>
                                </li>

                                <li>
                                    <div class="checkbox">
                                        <label><input type="checkbox" value="">

                                            Wi-fi

                                        </label>
                                    </div>
                                </li>
                            </ul>
                        </div>
                    </div>

                    <!--fare type-->
                    <div class="fare-type" style="display: none">
                        <div class="flight-header-name fare-type-header ">
                            <h6>Fare Type</h6>
                            <span class="collapse-icon">
                                <i class="fa fa-minus  mt-10 mb-10"></i>
                            </span>
                        </div>

                        <div class="checklist flight-stop-list">
                            <ul>
                                <li>
                                    <div class="checkbox">
                                        <label><input type="checkbox" value="">Refundable</label>
                                    </div>
                                </li>

                                <li>
                                    <div class="checkbox">
                                        <label><input type="checkbox" value="">Non-Refundable</label>
                                    </div>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
            <!--/flight Search Filter section-->

            <!--flight search result list-->
            <div class="col-md-9">
                <div class="hotel-booking-list" ng-repeat="hotelresult in hotelList">
                    <div class="hotel-list-view">
                        <div class="wrapper">
                            <div class="col-md-4 col-sm-6 switch-img clear-padding">
                                <img src="{{hotelresult.HotelImage}}" alt="cruise">
                            </div>
                            <div class="col-md-6 col-sm-12 hotel-info">
                                <div>
                                    <div class="hotel-header">
                                        <h5>{{hotelresult.HotelName}}
                                        <div class="">
                                             <div star-rating rating-value="hotelresult.StarRating" max="5" style="display: inline-block;"></div><sapn class="ratings">( {{hotelresult.StarRating}}/5.0 )</sapn>
                                        
                                        </div>
                                        
                                           <!--  <span>
                                                <i class="fa fa-star colored"> </i>
                                                <i class="fa fa-star colored" ng-if="hotelresult.StarRating>=2"></i>
                                                <i class="fa fa-star colored" ng-if="hotelresult.StarRating>=3"></i>
                                                <i class="fa fa-star colored" ng-if="hotelresult.StarRating>=4"></i>
                                                <i class="fa fa-star colored" ng-if="hotelresult.StarRating=5"></i>
                                            </span> -->
                                        </h5>
                                        <p><i class="fa fa-map-marker"></i>{{hotelresult.HotelAddress}} </p>
                                        <p><i class="fa fa-phone"></i>(+91) {{hotelresult.HotelPhone}}</p>
                                        <p> {{hotelresult.HotelDescription}}</p>
                                    </div>
                                  <!--   <div class="hotel-facility">
                                        <p>
                                            <i class="fa fa-wifi" title="Free Wifi"></i>
                                            <i class="fa fa-bed" title="Luxury Bedroom"></i>
                                            <i class="fa fa-taxi" title="Transportation"></i>
                                            <i class="fa fa-id-card" title="Restaurant"></i>
                                        </p>
                                    </div> -->
                                 <!--    <div class="hotel-desc">
                                        <a href="#">Free cancellation</a>
                                    </div> -->
                                </div>
                            </div>
                            <div class="col-md-2 col-sm-12">
                               <div class="price_m text-center">
                                    <h3><i class="fa fa-rupee-sign icn-col"></i>{{hotelresult.HotelPrice.totalprice|number:2}}</h3>
                                    <p>Per Night</p>
                                    <a ng-click="searchHotalDetails(hotelresult);">Select Room</a>
                               </div>
                            </div>
                            <div class="clearfix visible-sm-block"></div>
                         <!-- <div class="col-md-8 rating-price-box text-center clear-padding">
                                <div class="rating-box">
                                    <div class="tripadvisor-rating">
                                        <img src="assets/images/tripadvisor.png" alt="cruise"><span>{{hotelresult.StarRating}}/5.0</span>
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
<!-- hotel serch -->

<!-- select roome Start -->
<div id="div2" class="flight-lisdt" style="display: none;">
    <div class="container">
        <div class="row">
            <div class="col-md-9 col-sm-12">
                <div class="hotel-name">
                    <h5>{{hoteldetailsresult.HotelName}}
                        <span>
                            <i class="fa fa-star colored"> </i>
                            <i class="fa fa-star colored"></i>
                            <i class="fa fa-star colored"></i>
                            <i class="fa fa-star colored"></i>
                            <i class="fa fa-star colored"></i>
                        </span>
                    </h5>
                    <p><i class="fa fa-map-marker"></i>{{hoteldetailsresult.HotelAddress}}</p>
                    <p><i class="fa fa-phone"></i>{{hoteldetailsresult.HotelPhone}}</p>
                </div>
            </div>
            <div class="col-md-3 col-sm-12">
                <ul class="pr_bc">
                    <li><i class="fas fa-rupee-sign"></i>5564</li>
                    <li>
                        <a href="#">Select Room</a>
                    </li>
                </ul>
            </div>
        </div>
        <div class="row">
            <!--flight search result list-->
            <div class="col-md-8">
                <div class="hotel-booking-list">
                    <div class="hotel-list-view">
                        <div class="wrapper">
                            <div class="col-md-12 col-sm-12 hotel-info">
                                <div class="">
										<carousel interval="myInterval"> 
											<slide ng-repeat="image in hotelImageList" active="slide.active"> 
												<img ng-src="{{image}}">
												<div class="carousel-caption">
												<h4>Slide {{$index+1}}</h4>
												</div>
											</slide> 
										</carousel>
									</div>
                                </div>
                            <div class="clearfix visible-sm-block"></div>
                    </div>
                </div>
            </div>
            <!--/flight search result list-->

            </div>
            <!--flight Search Filter section-->
            <div class="col-md-4">
                <div class="search-filter">
                    <div class="search-filter-head">
                        <h3>BOOKING SUMMAR</h3>
                    </div>

                    <!-- BOOKING SUMMARY -->
                    <div class="flight-stop">
                        <div class="row bok_su">
                            <div class="col-md-4 col-sm-12">
                                <i class="fa fa-hospital"></i>
                                <p>{{hotelresult.RoomsNo}} room</p>
                            </div>
                            <div class="col-md-4 col-sm-12">
                                <i class="fas fa-users"></i>
                                 <p>{{hotelresult.guestno}} GUEST</p>
                            </div>
                            <div class="col-md-4 col-sm-12">
                                <i class="fas fa-moon"></i>
                                <p>{{hotelresult.nightno}} NIGHT</p>
                            </div>
                        </div>
                    </div>

                    <!--airloinesd name-->
                    <div class="airlines-name">
                        <div class="flight-header-name airlines-name-header ">
                        </div>
                        <div class="checklist flight-stop-list">
                            <div class="row">
                                <div class="col-md-6 col-sm-12">
                                    <h4>Check-In</h4>
                                    <p>{{hotelsearch.checkin| date : "fullDate"}}</p>
                                </div>
                                <div class="col-md-6 col-sm-12">
                                    <h4>Check-Out</h4>
                                    <p>{{hotelsearch.checkout | date : "fullDate"}}</p>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!--fare type-->
                    <div class="fare-type">
                        <div class="flight-header-name fare-type-header ">
                        </div>
                        <div class="checklist flight-stop-list">
                            <h4>Room Type</h4>
                            <select class="form-control">
                                <option>Classic AC Room</option>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="to_btn">
                    <a onClick="showDiv('div3')" href="#" class="mt-30"> BOOK NOW </a>
                    <a href="#" class="view"> View Similar Hotel </a>
                </div>
                <div class="g_map">
                    
                </div>
            </div>
            <!--/flight Search Filter section-->
        </div>

        <div class="row mt-30">
            <div class="flight-stop">
                <div class="row bok_su">
                    <div class="col-md-1 col-sm-12">
                        <i class="fas fa-users"></i>
                        <p>Couple Friendly</p>
                    </div>
                    <div class="col-md-1 col-sm-12">
                        <i class="fas fa-id-card"></i>
                        <p>Local IDs Accepted</p>
                    </div>
                    <div class="col-md-1 col-sm-12">
                        <i class="fas fa-wifi"></i>
                        <p>Free wifi</p>
                    </div>
                </div>
            </div>
        </div>

        <div class="row room_tp mb-30">
            <h3>Room Type</h3>
            <div class="panel panel-default" ng-repeat="hoteldetailsresult in hoteldetailsresult.HotelRoomCombinations.HotelRoomCombination">
              <div class="panel-heading hed_in">
                <h3>{{hoteldetailsresult.HotelRoomsDetail[0].RoomTypeName}}<h3>
              </div>
              <div class="panel-body">
                  <div class="row">
                      <div class="col-md-3 col-sm-12 Classic">
                        <img src="assets/skyhotels/images/offer1.jpg" alt="cruise">
                        <ul>
                            <li ng-repeat="Amenities in hoteldetailsresult.HotelRoomsDetail[0].Amenities"><i class="fas fa-check"></i>{{Amenities}}</li>
                          <!--   <li><i class="fas fa-check"></i>Flat-screen TV</li>
                            <li><i class="fas fa-check"></i>Bathroom</li>
                            <li><i class="fas fa-check"></i>Cable channels</li> -->
                        </ul>
                      </div>
                      <div class="col-sm-12 col-md-9">
                          <div class="row">
                              <div class="col-md-4 incu">
                                    <h4>Inclusions</h4>
                                    <ul>
                                        <li ng-repeat="inclusion in hoteldetailsresult.HotelRoomsDetail[0].Inclusion"><i class="fas fa-check"></i>{{inclusion}}</li>
                                        
                                    </ul>
                              </div>
                              <div class="col-md-4 incu">
                                  <h4>Total Price</h4>
                                  <table class="table table-bordered table-hover ">
                                        <thead>
                                            <tr>
                                                <th colspan="5">Price for {{hoteldetailsresult.HotelRoomsDetail.length}} room {{hotelresult.nightno}} Night</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <td>Nights(s) {{hotelresult.nightno}}</td>
                                                <td>{{hoteldetailsresult.HotelRoomsDetail | totalCommission:'RoomPrice'|number:2}}</td>
                                            </tr>
                                       <!--      <tr>
                                                <td>Hotel Promo Discount</td>
                                                <td>0.000</td>
                                            </tr> -->
                                            <tr>
                                                <td>OtherCharges</td>
                                                <td>{{hoteldetailsresult.HotelRoomsDetail | totalCommission:'OtherCharges'|number:2}}</td>
                                            </tr>
                                            <tr>
                                                <td>Tax</td>
                                                <td>{{hoteldetailsresult.HotelRoomsDetail | totalCommission:'Tax'|number:2}}</td>
                                            </tr>
                                            <tr>
                                                <td>Total</td>
                                                <td>{{hoteldetailsresult.HotelRoomsDetail | totalCommission:'total'|number:2}}</td>
                                            </tr> 
                                        </tbody>
                                    </table>
                                    <div class="hotel-desc">
                                        <a href="#">cancellation</a>
                                    </div>
                              </div>
                              <div class="col-md-4">
                                  <a class="all_btn mt-140" href="#" ng-click="getroomblock(hoteldetailsresult);">Book Now</a>
                              </div>
                          </div>
                      </div>
                  </div>
              </div>
            </div>
        </div>

        <div class="row">
            <div class="panel panel-default">
              <div class="panel-heading hed_in">
                <h3>Amenities<h3>
              </div>
              <div class="panel-body">
                  <div class="row">
                      <div class="col-md-1 col-sm-6 ame_dis">
                        <i class="fas fa-wifi"></i>
                        <p>Free Wi-fi</p>
                      </div>
                      <div class="col-md-1 col-sm-6 ame_dis">
                        <i class="fas fa-concierge-bell"></i>
                        <p>Room Service</p>
                      </div>
                      <div class="col-md-1 col-sm-6 ame_dis">
                        <i class="fas fa-dumbbell"></i>
                        <p>Gym/Health Club</p>
                      </div>
                      <div class="col-md-1 col-sm-6 ame_dis">
                        <i class="fas fa-utensils"></i>
                        <p>Restaurant</p>
                      </div>
                      <div class="col-md-1 col-sm-6 ame_dis">
                        <i class="fas fa-mug-hot"></i>
                        <p>Breakfast Service</p>
                      </div>
                      <div class="col-md-1 col-sm-6 ame_dis">
                        <i class="fas fa-car"></i>
                        <p>Parking</p>
                      </div>
                  </div>
              </div>
            </div>
        </div>

        <div class="row">
            <div class="panel panel-default">
              <div class="panel-heading hed_in">
                <h3>Hotel Information<h3>
              </div>
              <div class="panel-body">
                  <div class="row">
                      <div class="col-md-12">
                          <p>
                            Felicity Inn EGL by Sky Stays features accommodation with a terrace. The property is located 8 km from Brigade Road and 8 km from The Heritage Centre & Aerospace Museum. The property is situated 9 km from Chinnaswamy Stadium. At the hotel, every room is equipped with a desk. Featuring a private bathroom, rooms at Felicity Inn EGL by Sky Stays also provide guests with free Wi-Fi. Breakfast is available every morning, and includes continental and vegetarian options. Speaking both English and Hindi, staff at the reception can help you plan your stay.
                          </p>
                      </div>
                  </div>
              </div>
            </div>
        </div>

        <!-- <div class="row">
            <div class="panel panel-default">
              <div class="panel-heading hed_in">
                <h3>Compare Similar Hotels<h3>
              </div>
              <div class="panel-body">
                  <div class="row">
                      <div class="col-md-12 hot_in">
                          <div class="owl-carousel owl-theme owl-3">
                                <div class="item">
                                    <img src="assets/skyhotels/images/offer1.jpg" alt="cruise">
                                    <div>
                                        <div class="hotel-header">
                                            <h5>Hotel Residency
                                                <span>
                                                    <i class="fa fa-star colored"> </i>
                                                    <i class="fa fa-star colored"></i>
                                                    <i class="fa fa-star colored"></i>
                                                    <i class="fa fa-star colored"></i>
                                                    <i class="fa fa-star colored"></i>
                                                </span>
                                            </h5>
                                            <p><i class="fa fa-map-marker"></i>Near Durian Estate, Goregaon East, Mumbai, Maharashtra 400097 </p>
                                            <p><i class="fa fa-phone"></i>(+91) 123456789</p>
                                        </div>
                                        <div class="hotel-facility">
                                            <p>
                                                <i class="fa fa-wifi" title="Free Wifi"></i>
                                                <i class="fa fa-bed" title="Luxury Bedroom"></i>
                                                <i class="fa fa-taxi" title="Transportation"></i>
                                                <i class="fa fa-id-card" title="Restaurant"></i>
                                            </p>
                                        </div>
                                        <div class="hotel-desc">
                                            <a href="#">Free cancellation</a>
                                        </div>
                                    </div>
                                </div>
                                <div class="item">
                                  <img src="assets/skyhotels/images/offer1.jpg" alt="cruise">
                                  <div>
                                        <div class="hotel-header">
                                            <h5>Hotel Residency
                                                <span>
                                                    <i class="fa fa-star colored"> </i>
                                                    <i class="fa fa-star colored"></i>
                                                    <i class="fa fa-star colored"></i>
                                                    <i class="fa fa-star colored"></i>
                                                    <i class="fa fa-star colored"></i>
                                                </span>
                                            </h5>
                                            <p><i class="fa fa-map-marker"></i>Near Durian Estate, Goregaon East, Mumbai, Maharashtra 400097 </p>
                                            <p><i class="fa fa-phone"></i>(+91) 123456789</p>
                                        </div>
                                        <div class="hotel-facility">
                                            <p>
                                                <i class="fa fa-wifi" title="Free Wifi"></i>
                                                <i class="fa fa-bed" title="Luxury Bedroom"></i>
                                                <i class="fa fa-taxi" title="Transportation"></i>
                                                <i class="fa fa-id-card" title="Restaurant"></i>
                                            </p>
                                        </div>
                                        <div class="hotel-desc">
                                            <a class="all_btn" href="#">Book Now</a>
                                        </div>
                                    </div>
                                </div>
                                <div class="item">
                                  <img src="assets/skyhotels/images/offer1.jpg" alt="cruise">
                                  <div>
                                        <div class="hotel-header">
                                            <h5>Hotel Residency
                                                <span>
                                                    <i class="fa fa-star colored"> </i>
                                                    <i class="fa fa-star colored"></i>
                                                    <i class="fa fa-star colored"></i>
                                                    <i class="fa fa-star colored"></i>
                                                    <i class="fa fa-star colored"></i>
                                                </span>
                                            </h5>
                                            <p><i class="fa fa-map-marker"></i>Near Durian Estate, Goregaon East, Mumbai, Maharashtra 400097 </p>
                                            <p><i class="fa fa-phone"></i>(+91) 123456789</p>
                                        </div>
                                        <div class="hotel-facility">
                                            <p>
                                                <i class="fa fa-wifi" title="Free Wifi"></i>
                                                <i class="fa fa-bed" title="Luxury Bedroom"></i>
                                                <i class="fa fa-taxi" title="Transportation"></i>
                                                <i class="fa fa-id-card" title="Restaurant"></i>
                                            </p>
                                        </div>
                                        <div class="hotel-desc">
                                            <a href="#">Free cancellation</a>
                                        </div>
                                    </div>
                                </div>
                                <div class="item">
                                  <img src="assets/skyhotels/images/offer1.jpg" alt="cruise">
                                  <div>
                                        <div class="hotel-header">
                                            <h5>Hotel Residency
                                                <span>
                                                    <i class="fa fa-star colored"> </i>
                                                    <i class="fa fa-star colored"></i>
                                                    <i class="fa fa-star colored"></i>
                                                    <i class="fa fa-star colored"></i>
                                                    <i class="fa fa-star colored"></i>
                                                </span>
                                            </h5>
                                            <p><i class="fa fa-map-marker"></i>Near Durian Estate, Goregaon East, Mumbai, Maharashtra 400097 </p>
                                            <p><i class="fa fa-phone"></i>(+91) 123456789</p>
                                        </div>
                                        <div class="hotel-facility">
                                            <p>
                                                <i class="fa fa-wifi" title="Free Wifi"></i>
                                                <i class="fa fa-bed" title="Luxury Bedroom"></i>
                                                <i class="fa fa-taxi" title="Transportation"></i>
                                                <i class="fa fa-id-card" title="Restaurant"></i>
                                            </p>
                                        </div>
                                        <div class="hotel-desc">
                                            <a href="#">Free cancellation</a>
                                        </div>
                                    </div>
                                </div>
                                <div class="item">
                                  <img src="assets/skyhotels/images/offer1.jpg" alt="cruise">
                                  <div>
                                        <div class="hotel-header">
                                            <h5>Hotel Residency
                                                <span>
                                                    <i class="fa fa-star colored"> </i>
                                                    <i class="fa fa-star colored"></i>
                                                    <i class="fa fa-star colored"></i>
                                                    <i class="fa fa-star colored"></i>
                                                    <i class="fa fa-star colored"></i>
                                                </span>
                                            </h5>
                                            <p><i class="fa fa-map-marker"></i>Near Durian Estate, Goregaon East, Mumbai, Maharashtra 400097 </p>
                                            <p><i class="fa fa-phone"></i>(+91) 123456789</p>
                                        </div>
                                        <div class="hotel-facility">
                                            <p>
                                                <i class="fa fa-wifi" title="Free Wifi"></i>
                                                <i class="fa fa-bed" title="Luxury Bedroom"></i>
                                                <i class="fa fa-taxi" title="Transportation"></i>
                                                <i class="fa fa-id-card" title="Restaurant"></i>
                                            </p>
                                        </div>
                                        <div class="hotel-desc">
                                            <a href="#">Free cancellation</a>
                                        </div>
                                    </div>
                                </div>
                                <div class="item">
                                  <img src="assets/skyhotels/images/offer1.jpg" alt="cruise">
                                  <div>
                                        <div class="hotel-header">
                                            <h5>Hotel Residency
                                                <span>
                                                    <i class="fa fa-star colored"> </i>
                                                    <i class="fa fa-star colored"></i>
                                                    <i class="fa fa-star colored"></i>
                                                    <i class="fa fa-star colored"></i>
                                                    <i class="fa fa-star colored"></i>
                                                </span>
                                            </h5>
                                            <p><i class="fa fa-map-marker"></i>Near Durian Estate, Goregaon East, Mumbai, Maharashtra 400097 </p>
                                            <p><i class="fa fa-phone"></i>(+91) 123456789</p>
                                        </div>
                                        <div class="hotel-facility">
                                            <p>
                                                <i class="fa fa-wifi" title="Free Wifi"></i>
                                                <i class="fa fa-bed" title="Luxury Bedroom"></i>
                                                <i class="fa fa-taxi" title="Transportation"></i>
                                                <i class="fa fa-id-card" title="Restaurant"></i>
                                            </p>
                                        </div>
                                        <div class="hotel-desc">
                                            <a href="#">Free cancellation</a>
                                        </div>
                                    </div>
                                </div>
                                <div class="item">
                                  <img src="assets/skyhotels/images/offer1.jpg" alt="cruise">
                                  <div>
                                        <div class="hotel-header">
                                            <h5>Hotel Residency
                                                <span>
                                                    <i class="fa fa-star colored"> </i>
                                                    <i class="fa fa-star colored"></i>
                                                    <i class="fa fa-star colored"></i>
                                                    <i class="fa fa-star colored"></i>
                                                    <i class="fa fa-star colored"></i>
                                                </span>
                                            </h5>
                                            <p><i class="fa fa-map-marker"></i>Near Durian Estate, Goregaon East, Mumbai, Maharashtra 400097 </p>
                                            <p><i class="fa fa-phone"></i>(+91) 123456789</p>
                                        </div>
                                        <div class="hotel-facility">
                                            <p>
                                                <i class="fa fa-wifi" title="Free Wifi"></i>
                                                <i class="fa fa-bed" title="Luxury Bedroom"></i>
                                                <i class="fa fa-taxi" title="Transportation"></i>
                                                <i class="fa fa-id-card" title="Restaurant"></i>
                                            </p>
                                        </div>
                                        <div class="hotel-desc">
                                            <a href="#">Free cancellation</a>
                                        </div>
                                    </div>
                                </div>
                                <div class="item">
                                  <img src="assets/skyhotels/images/offer1.jpg" alt="cruise">
                                  <div>
                                        <div class="hotel-header">
                                            <h5>Hotel Residency
                                                <span>
                                                    <i class="fa fa-star colored"> </i>
                                                    <i class="fa fa-star colored"></i>
                                                    <i class="fa fa-star colored"></i>
                                                    <i class="fa fa-star colored"></i>
                                                    <i class="fa fa-star colored"></i>
                                                </span>
                                            </h5>
                                            <p><i class="fa fa-map-marker"></i>Near Durian Estate, Goregaon East, Mumbai, Maharashtra 400097 </p>
                                            <p><i class="fa fa-phone"></i>(+91) 123456789</p>
                                        </div>
                                        <div class="hotel-facility">
                                            <p>
                                                <i class="fa fa-wifi" title="Free Wifi"></i>
                                                <i class="fa fa-bed" title="Luxury Bedroom"></i>
                                                <i class="fa fa-taxi" title="Transportation"></i>
                                                <i class="fa fa-id-card" title="Restaurant"></i>
                                            </p>
                                        </div>
                                        <div class="hotel-desc">
                                            <a href="#">Free cancellation</a>
                                        </div>
                                    </div>
                                </div>
                                <div class="item">
                                  <img src="assets/skyhotels/images/offer1.jpg" alt="cruise">
                                  <div>
                                        <div class="hotel-header">
                                            <h5>Hotel Residency
                                                <span>
                                                    <i class="fa fa-star colored"> </i>
                                                    <i class="fa fa-star colored"></i>
                                                    <i class="fa fa-star colored"></i>
                                                    <i class="fa fa-star colored"></i>
                                                    <i class="fa fa-star colored"></i>
                                                </span>
                                            </h5>
                                            <p><i class="fa fa-map-marker"></i>Near Durian Estate, Goregaon East, Mumbai, Maharashtra 400097 </p>
                                            <p><i class="fa fa-phone"></i>(+91) 123456789</p>
                                        </div>
                                        <div class="hotel-facility">
                                            <p>
                                                <i class="fa fa-wifi" title="Free Wifi"></i>
                                                <i class="fa fa-bed" title="Luxury Bedroom"></i>
                                                <i class="fa fa-taxi" title="Transportation"></i>
                                                <i class="fa fa-id-card" title="Restaurant"></i>
                                            </p>
                                        </div>
                                        <div class="hotel-desc">
                                            <a href="#">Free cancellation</a>
                                        </div>
                                    </div>
                                </div>
                                <div class="item">
                                  <img src="assets/skyhotels/images/offer1.jpg" alt="cruise">
                                  <div>
                                        <div class="hotel-header">
                                            <h5>Hotel Residency
                                                <span>
                                                    <i class="fa fa-star colored"> </i>
                                                    <i class="fa fa-star colored"></i>
                                                    <i class="fa fa-star colored"></i>
                                                    <i class="fa fa-star colored"></i>
                                                    <i class="fa fa-star colored"></i>
                                                </span>
                                            </h5>
                                            <p><i class="fa fa-map-marker"></i>Near Durian Estate, Goregaon East, Mumbai, Maharashtra 400097 </p>
                                            <p><i class="fa fa-phone"></i>(+91) 123456789</p>
                                        </div>
                                        <div class="hotel-facility">
                                            <p>
                                                <i class="fa fa-wifi" title="Free Wifi"></i>
                                                <i class="fa fa-bed" title="Luxury Bedroom"></i>
                                                <i class="fa fa-taxi" title="Transportation"></i>
                                                <i class="fa fa-id-card" title="Restaurant"></i>
                                            </p>
                                        </div>
                                        <div class="hotel-desc">
                                            <a href="#">Free cancellation</a>
                                        </div>
                                    </div>
                                </div>
                                <div class="item">
                                  <img src="assets/skyhotels/images/offer1.jpg" alt="cruise">
                                  <div>
                                        <div class="hotel-header">
                                            <h5>Hotel Residency
                                                <span>
                                                    <i class="fa fa-star colored"> </i>
                                                    <i class="fa fa-star colored"></i>
                                                    <i class="fa fa-star colored"></i>
                                                    <i class="fa fa-star colored"></i>
                                                    <i class="fa fa-star colored"></i>
                                                </span>
                                            </h5>
                                            <p><i class="fa fa-map-marker"></i>Near Durian Estate, Goregaon East, Mumbai, Maharashtra 400097 </p>
                                            <p><i class="fa fa-phone"></i>(+91) 123456789</p>
                                        </div>
                                        <div class="hotel-facility">
                                            <p>
                                                <i class="fa fa-wifi" title="Free Wifi"></i>
                                                <i class="fa fa-bed" title="Luxury Bedroom"></i>
                                                <i class="fa fa-taxi" title="Transportation"></i>
                                                <i class="fa fa-id-card" title="Restaurant"></i>
                                            </p>
                                        </div>
                                        <div class="hotel-desc">
                                            <a href="#">Free cancellation</a>
                                        </div>
                                    </div>
                                </div>
                                <div class="item">
                                  <img src="assets/skyhotels/images/offer1.jpg" alt="cruise">
                                  <div>
                                        <div class="hotel-header">
                                            <h5>Hotel Residency
                                                <span>
                                                    <i class="fa fa-star colored"> </i>
                                                    <i class="fa fa-star colored"></i>
                                                    <i class="fa fa-star colored"></i>
                                                    <i class="fa fa-star colored"></i>
                                                    <i class="fa fa-star colored"></i>
                                                </span>
                                            </h5>
                                            <p><i class="fa fa-map-marker"></i>Near Durian Estate, Goregaon East, Mumbai, Maharashtra 400097 </p>
                                            <p><i class="fa fa-phone"></i>(+91) 123456789</p>
                                        </div>
                                        <div class="hotel-facility">
                                            <p>
                                                <i class="fa fa-wifi" title="Free Wifi"></i>
                                                <i class="fa fa-bed" title="Luxury Bedroom"></i>
                                                <i class="fa fa-taxi" title="Transportation"></i>
                                                <i class="fa fa-id-card" title="Restaurant"></i>
                                            </p>
                                        </div>
                                        <div class="hotel-desc">
                                            <a href="#">Free cancellation</a>
                                        </div>
                                    </div>
                                </div>
                              </div>
                            </div>
                      </div>
                  </div>
              </div>
            </div>
        </div> -->

    </div>
</div>
<!-- select roome End -->

<!-- booking now Start -->
<div id="div3" class="flight-lisdt" style="display: none;">
    <div class="container">
        <div class="row">
            <!--flight search result list-->
            <div class="col-md-8">
                <div class="hotel-booking-list mb-20">
                    <div class="hotel-list-view">
                        <div class="wrapper">
                            <div class="col-md-12 col-sm-12 hotel-info">
                                <div class="hotel_book">
                                    <div class="row">
                                        <div class="col-md-5">
                                            <img src="{{hotelimage}}">
                                        </div>
                                        <div class="col-md-7">
                                            <div class="hotel-header">
                                                <h5>{{hoteldetailsresult.HotelName}}
                                                    <span>
                                                        <i class="fa fa-star colored"> </i>
                                                        <i class="fa fa-star colored"></i>
                                                        <i class="fa fa-star colored"></i>
                                                        <i class="fa fa-star colored"></i>
                                                        <i class="fa fa-star colored"></i>
                                                    </span>
                                                </h5>
                                                <p><i class="fa fa-map-marker"></i>{{hoteldetailsresult.HotelAddress}} </p>
                                            </div>
                                            <div class="hotel-header">
                                                <div class="row">
                                                    <div class="col-md-6 chak_de">
                                                        <h5>Check-In</h5>
                                                        <p>
                                                            <span>{{hotelsearch.checkin| date : "fullDate"}}</span>
                                                        </p>
                                                    </div>
                                                    <div class="col-md-6 chak_de">
                                                        <h5>Check-Out</h5>
                                                        <p>
                                                            <span>{{hotelsearch.checkout | date : "fullDate"}}</span>
                                                        </p>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="col-md-12">
                                                        <table class="table table-responsive">
                                                            <tbody>
                                                                <tr class="th_lig" ng-repeat="guests in guests">
                                                                    <td>Room {{$index+1}} :</td>
                                                                    <td>{{guests.AdultNo}} Adults,{{guests.ChildNo}} Child</td>
                                                                </tr>
                                                            </tbody>
                                                        </table>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    
                                    <div class="row">
	                                   <!--  <div class="col-md-7">
	                                    	<h5>Important Info : -</h5>
	                                        <table class="table table-responsive table-bordered">
	                                        	<thead>
	                                        		<tr class="th_lig">
	                                                	<td colspan="1"></td>
	                                                    <td>Date </td>
	                                                    <td>Charges</td>
	                                                </tr>
	                                        	</thead>
	                                            <tbody>
	                                                <tr>
	                                                    <td>From</td>
	                                                    <td>13-may-2020</td>
	                                                    <td>100%</td>
	                                                </tr>
	                                                <tr>
	                                                	<td>Before</td>
	                                                	<td>13-may-2020</td>
	                                                	<td>Free cancellation</td>
	                                                </tr>
	                                            </tbody>
	                                        </table>
	                                    </div> -->
	                                    <div class="col-md-5">
	                                        <h4 class="hotel-desc" style="text-align: right;margin-top: 25%;">
	                                        	<a style="font-size: 15px;" class="text-right" href="#" data-toggle="modal" data-target="#cencellpol">
	                                        		cancellation policy
	                                        	</a>
	                                        </h4>
	                                    </div>
	                                </div>
                                    
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="panel panel-default">
                                                <div class="panel-heading hed_in">
                                                    <h3>Travellers Details<h3>
                                                </div>
                                                  <div class="panel-body">
                                                    <form>
                                                      <div class="row">
                                                          <div class="col-md-2 col-sm-12 rom_im">
                                                            <img src="assets/skyhotels/images/hotel/rom.png">
                                                            <p class="co_rom">room 1</p>
                                                          </div>
                                                          <div class="subdiv">
                                                          	<div class="col-md-2 col-sm-12 Classic">
	                                                            <h3>Adult 1</h3>
	                                                          </div>
	                                                          <div class="col-sm-12 col-md-2">
	                                                                <span>Title</span>
	                                                                <select class="form-control" ng-model="bookhotel.adulttitel11">
	                                                                    <option>Mr</option>
	                                                                    <option>Miss</option>
	                                                                    <option>Mrs</option>
	                                                                </select>
	                                                          </div>
	                                                          <div class="col-sm-12 col-md-3">
	                                                                <span>First Name</span> 
	                                                                <input class="form-control" type="text" name="" ng-model="bookhotel.adultfname11">   
	                                                          </div>
	                                                          <div class="col-sm-12 col-md-3">
	                                                                <span>Last Name</span>
	                                                                <input class="form-control" type="text" name="" ng-model="bookhotel.adultlname11">
	                                                          </div>
                                                          </div>
                                                          <div class="subdiv" ng-if="hotelsearch.adultno1===2">
                                                          	<div class="col-md-2 col-sm-12 Classic">
	                                                            <h3>Adult 2</h3>
	                                                          </div>
	                                                          <div class="col-sm-12 col-md-2">
	                                                                <span>Title</span>
	                                                                <select class="form-control" ng-model="bookhotel.adulttitel12">
	                                                                    <option>Mr</option>
	                                                                    <option>Miss</option>
	                                                                    <option>Mrs</option>
	                                                                </select>
	                                                          </div>
	                                                          <div class="col-sm-12 col-md-3">
	                                                                <span>First Name</span> 
	                                                                <input class="form-control" type="text" name="" ng-model="bookhotel.adultfname12">   
	                                                          </div>
	                                                          <div class="col-sm-12 col-md-3">
	                                                                <span>Last Name</span>
	                                                                <input class="form-control" type="text" name="" ng-model="bookhotel.adultlname12">
	                                                          </div>
                                                          </div>
                                                          <div class="subdiv"  ng-if="hotelsearch.childno1!==0">
                                                          	<div class="col-md-2 col-sm-12 Classic">
	                                                            <h3>Child 1</h3>
	                                                          </div>
	                                                          <div class="col-sm-12 col-md-2">
	                                                                <span>Title</span>
	                                                                <select class="form-control" ng-model="bookhotel.childtitel11">
	                                                                    <option>Mr</option>
	                                                                    <option>Miss</option>
	                                                                    <option>Mrs</option>
	                                                                </select>
	                                                          </div>
	                                                          <div class="col-sm-12 col-md-3">
	                                                                <span>First Name</span> 
	                                                                <input class="form-control" type="text" name="" ng-model="bookhotel.childfname11">   
	                                                          </div>
	                                                          <div class="col-sm-12 col-md-3">
	                                                                <span>Last Name</span>
	                                                                <input class="form-control" type="text" name="" ng-model="bookhotel.childlname11">
	                                                          </div>
                                                          </div>
                                                          <div class="subdiv" ng-if="hotelsearch.childno1===2">
                                                          	<div class="col-md-2 col-sm-12 Classic">
	                                                            <h3>Adult 2</h3>
	                                                          </div>
	                                                          <div class="col-sm-12 col-md-2">
	                                                                <span>Title</span>
	                                                                <select class="form-control" ng-model="bookhotel.childtitel12">
	                                                                    <option>Mr</option>
	                                                                    <option>Miss</option>
	                                                                    <option>Mrs</option>
	                                                                </select>
	                                                          </div>
	                                                          <div class="col-sm-12 col-md-3">
	                                                                <span>First Name</span> 
	                                                                <input class="form-control" type="text" name="" ng-model="bookhotel.childfname12">   
	                                                          </div>
	                                                          <div class="col-sm-12 col-md-3">
	                                                                <span>Last Name</span>
	                                                                <input class="form-control" type="text" name="" ng-model="bookhotel.childlname12">
	                                                          </div>
                                                          </div>
                                                      </div>
                                                      <!-- ----------------room 2------------- -->
                                                      <div class="row" ng-if="guests.length>=2">
                                                          <div class="col-md-2 col-sm-12 rom_im">
                                                            <img src="assets/skyhotels/images/hotel/rom.png">
                                                            <p class="co_rom">room 2</p>
                                                          </div>
                                                          <div class="subdiv">
                                                          	<div class="col-md-2 col-sm-12 Classic">
	                                                            <h3>Adult 1</h3>
	                                                          </div>
	                                                          <div class="col-sm-12 col-md-2">
	                                                                <span>Title</span>
	                                                                <select class="form-control" ng-model="bookhotel.adulttitel21">
	                                                                    <option>Mr</option>
	                                                                    <option>Miss</option>
	                                                                    <option>Mrs</option>
	                                                                </select>
	                                                          </div>
	                                                          <div class="col-sm-12 col-md-3">
	                                                                <span>First Name</span> 
	                                                                <input class="form-control" type="text" name="" ng-model="bookhotel.adultfname21">   
	                                                          </div>
	                                                          <div class="col-sm-12 col-md-3">
	                                                                <span>Last Name</span>
	                                                                <input class="form-control" type="text" name="" ng-model="bookhotel.adultlname21">
	                                                          </div>
                                                          </div>
                                                          <div class="subdiv" ng-if="hotelsearch.adultno2===2">
                                                          	<div class="col-md-2 col-sm-12 Classic">
	                                                            <h3>Adult 2</h3>
	                                                          </div>
	                                                          <div class="col-sm-12 col-md-2">
	                                                                <span>Title</span>
	                                                                <select class="form-control" ng-model="bookhotel.adulttitel22">
	                                                                    <option>Mr</option>
	                                                                    <option>Miss</option>
	                                                                    <option>Mrs</option>
	                                                                </select>
	                                                          </div>
	                                                          <div class="col-sm-12 col-md-3">
	                                                                <span>First Name</span> 
	                                                                <input class="form-control" type="text" name="" ng-model="bookhotel.adultfname22">   
	                                                          </div>
	                                                          <div class="col-sm-12 col-md-3">
	                                                                <span>Last Name</span>
	                                                                <input class="form-control" type="text" name="" ng-model="bookhotel.adultlname22">
	                                                          </div>
                                                          </div>
                                                          <div class="subdiv"  ng-if="hotelsearch.childno2!==0">
                                                          	<div class="col-md-2 col-sm-12 Classic">
	                                                            <h3>Child 1</h3>
	                                                          </div>
	                                                          <div class="col-sm-12 col-md-2">
	                                                                <span>Title</span>
	                                                                <select class="form-control" ng-model="bookhotel.childtitel21">
	                                                                    <option>Mr</option>
	                                                                    <option>Miss</option>
	                                                                    <option>Mrs</option>
	                                                                </select>
	                                                          </div>
	                                                          <div class="col-sm-12 col-md-3">
	                                                                <span>First Name</span> 
	                                                                <input class="form-control" type="text" name="" ng-model="bookhotel.childfname21">   
	                                                          </div>
	                                                          <div class="col-sm-12 col-md-3">
	                                                                <span>Last Name</span>
	                                                                <input class="form-control" type="text" name="" ng-model="bookhotel.childlname21">
	                                                          </div>
                                                          </div>
                                                          <div class="subdiv" ng-if="hotelsearch.childno2===2">
                                                          	<div class="col-md-2 col-sm-12 Classic">
	                                                            <h3>Adult 2</h3>
	                                                          </div>
	                                                          <div class="col-sm-12 col-md-2">
	                                                                <span>Title</span>
	                                                                <select class="form-control" ng-model="bookhotel.childtitel22">
	                                                                    <option>Mr</option>
	                                                                    <option>Miss</option>
	                                                                    <option>Mrs</option>
	                                                                </select>
	                                                          </div>
	                                                          <div class="col-sm-12 col-md-3">
	                                                                <span>First Name</span> 
	                                                                <input class="form-control" type="text" name="" ng-model="bookhotel.childfname22">   
	                                                          </div>
	                                                          <div class="col-sm-12 col-md-3">
	                                                                <span>Last Name</span>
	                                                                <input class="form-control" type="text" name="" ng-model="bookhotel.childlname22">
	                                                          </div>
                                                          </div>
                                                      </div>
                                                      <!------------------------------------>
                                                      <!-- ----------------room 3------------- -->
                                                      <div class="row" ng-if="guests.length>=3">
                                                          <div class="col-md-2 col-sm-12 rom_im">
                                                            <img src="assets/skyhotels/images/hotel/rom.png">
                                                            <p class="co_rom">room 3</p>
                                                          </div>
                                                          <div class="subdiv">
                                                          	<div class="col-md-2 col-sm-12 Classic">
	                                                            <h3>Adult 1</h3>
	                                                          </div>
	                                                          <div class="col-sm-12 col-md-2">
	                                                                <span>Title</span>
	                                                                <select class="form-control" ng-model="bookhotel.adulttitel31">
	                                                                    <option>Mr</option>
	                                                                    <option>Miss</option>
	                                                                    <option>Mrs</option>
	                                                                </select>
	                                                          </div>
	                                                          <div class="col-sm-12 col-md-3">
	                                                                <span>First Name</span> 
	                                                                <input class="form-control" type="text" name="" ng-model="bookhotel.adultfname31">   
	                                                          </div>
	                                                          <div class="col-sm-12 col-md-3">
	                                                                <span>Last Name</span>
	                                                                <input class="form-control" type="text" name="" ng-model="bookhotel.adultlname31">
	                                                          </div>
                                                          </div>
                                                          <div class="subdiv" ng-if="hotelsearch.adultno3===2">
                                                          	<div class="col-md-2 col-sm-12 Classic">
	                                                            <h3>Adult 2</h3>
	                                                          </div>
	                                                          <div class="col-sm-12 col-md-2">
	                                                                <span>Title</span>
	                                                                <select class="form-control" ng-model="bookhotel.adulttitel32">
	                                                                    <option>Mr</option>
	                                                                    <option>Miss</option>
	                                                                    <option>Mrs</option>
	                                                                </select>
	                                                          </div>
	                                                          <div class="col-sm-12 col-md-3">
	                                                                <span>First Name</span> 
	                                                                <input class="form-control" type="text" name="" ng-model="bookhotel.adultfname32">   
	                                                          </div>
	                                                          <div class="col-sm-12 col-md-3">
	                                                                <span>Last Name</span>
	                                                                <input class="form-control" type="text" name="" ng-model="bookhotel.adultlname32">
	                                                          </div>
                                                          </div>
                                                          <div class="subdiv"  ng-if="hotelsearch.childno3!==0">
                                                          	<div class="col-md-2 col-sm-12 Classic">
	                                                            <h3>Child 1</h3>
	                                                          </div>
	                                                          <div class="col-sm-12 col-md-2">
	                                                                <span>Title</span>
	                                                                <select class="form-control" ng-model="bookhotel.childtitel31">
	                                                                    <option>Mr</option>
	                                                                    <option>Miss</option>
	                                                                    <option>Mrs</option>
	                                                                </select>
	                                                          </div>
	                                                          <div class="col-sm-12 col-md-3">
	                                                                <span>First Name</span> 
	                                                                <input class="form-control" type="text" name="" ng-model="bookhotel.childfname31">   
	                                                          </div>
	                                                          <div class="col-sm-12 col-md-3">
	                                                                <span>Last Name</span>
	                                                                <input class="form-control" type="text" name="" ng-model="bookhotel.childlname31">
	                                                          </div>
                                                          </div>
                                                          <div class="subdiv" ng-if="hotelsearch.childno3===2">
                                                          	<div class="col-md-2 col-sm-12 Classic">
	                                                            <h3>Adult 2</h3>
	                                                          </div>
	                                                          <div class="col-sm-12 col-md-2">
	                                                                <span>Title</span>
	                                                                <select class="form-control" ng-model="bookhotel.childtitel32">
	                                                                    <option>Mr</option>
	                                                                    <option>Miss</option>
	                                                                    <option>Mrs</option>
	                                                                </select>
	                                                          </div>
	                                                          <div class="col-sm-12 col-md-3">
	                                                                <span>First Name</span> 
	                                                                <input class="form-control" type="text" name="" ng-model="bookhotel.childfname32">   
	                                                          </div>
	                                                          <div class="col-sm-12 col-md-3">
	                                                                <span>Last Name</span>
	                                                                <input class="form-control" type="text" name="" ng-model="bookhotel.childlname32">
	                                                          </div>
                                                          </div>
                                                      </div>
                                                      <!------------------------------------>
                                                       <!-- ----------------room 4------------- -->
                                                      <div class="row" ng-if="guests.length===4">
                                                          <div class="col-md-2 col-sm-12 rom_im">
                                                            <img src="assets/skyhotels/images/hotel/rom.png">
                                                            <p class="co_rom">room 4</p>
                                                          </div>
                                                          <div class="subdiv">
                                                          	<div class="col-md-2 col-sm-12 Classic">
	                                                            <h3>Adult 1</h3>
	                                                          </div>
	                                                          <div class="col-sm-12 col-md-2">
	                                                                <span>Title</span>
	                                                                <select class="form-control" ng-model="bookhotel.adulttitel41">
	                                                                    <option>Mr</option>
	                                                                    <option>Miss</option>
	                                                                    <option>Mrs</option>
	                                                                </select>
	                                                          </div>
	                                                          <div class="col-sm-12 col-md-3">
	                                                                <span>First Name</span> 
	                                                                <input class="form-control" type="text" name="" ng-model="bookhotel.adultfname41">   
	                                                          </div>
	                                                          <div class="col-sm-12 col-md-3">
	                                                                <span>Last Name</span>
	                                                                <input class="form-control" type="text" name="" ng-model="bookhotel.adultlname41">
	                                                          </div>
                                                          </div>
                                                          <div class="subdiv" ng-if="hotelsearch.adultno4===2">
                                                          	<div class="col-md-2 col-sm-12 Classic">
	                                                            <h3>Adult 2</h3>
	                                                          </div>
	                                                          <div class="col-sm-12 col-md-2">
	                                                                <span>Title</span>
	                                                                <select class="form-control" ng-model="bookhotel.adulttitel42">
	                                                                    <option>Mr</option>
	                                                                    <option>Miss</option>
	                                                                    <option>Mrs</option>
	                                                                </select>
	                                                          </div>
	                                                          <div class="col-sm-12 col-md-3">
	                                                                <span>First Name</span> 
	                                                                <input class="form-control" type="text" name="" ng-model="bookhotel.adultfname42">   
	                                                          </div>
	                                                          <div class="col-sm-12 col-md-3">
	                                                                <span>Last Name</span>
	                                                                <input class="form-control" type="text" name="" ng-model="bookhotel.adultlname42">
	                                                          </div>
                                                          </div>
                                                          <div class="subdiv"  ng-if="hotelsearch.childno4!==0">
                                                          	<div class="col-md-2 col-sm-12 Classic">
	                                                            <h3>Child 1</h3>
	                                                          </div>
	                                                          <div class="col-sm-12 col-md-2">
	                                                                <span>Title</span>
	                                                                <select class="form-control" ng-model="bookhotel.childtitel41">
	                                                                    <option>Mr</option>
	                                                                    <option>Miss</option>
	                                                                    <option>Mrs</option>
	                                                                </select>
	                                                          </div>
	                                                          <div class="col-sm-12 col-md-3">
	                                                                <span>First Name</span> 
	                                                                <input class="form-control" type="text" name="" ng-model="bookhotel.childfname41">   
	                                                          </div>
	                                                          <div class="col-sm-12 col-md-3">
	                                                                <span>Last Name</span>
	                                                                <input class="form-control" type="text" name="" ng-model="bookhotel.childlname41">
	                                                          </div>
                                                          </div>
                                                          <div class="subdiv" ng-if="hotelsearch.childno4===2">
                                                          	<div class="col-md-2 col-sm-12 Classic">
	                                                            <h3>Adult 2</h3>
	                                                          </div>
	                                                          <div class="col-sm-12 col-md-2">
	                                                                <span>Title</span>
	                                                                <select class="form-control" ng-model="bookhotel.childtitel42">
	                                                                    <option>Mr</option>
	                                                                    <option>Miss</option>
	                                                                    <option>Mrs</option>
	                                                                </select>
	                                                          </div>
	                                                          <div class="col-sm-12 col-md-3">
	                                                                <span>First Name</span> 
	                                                                <input class="form-control" type="text" name="" ng-model="bookhotel.childfname42">   
	                                                          </div>
	                                                          <div class="col-sm-12 col-md-3">
	                                                                <span>Last Name</span>
	                                                                <input class="form-control" type="text" name="" ng-model="bookhotel.childlname42">
	                                                          </div>
                                                          </div>
                                                      </div>
                                                      <!------------------------------------>
                                                      <div class="row mt-20">
                                                          <div class="col-md-6">
                                                            <span>Contact Details</span>
                                                            <input class="form-control" placeholder="Enter your email address" type="text" name="" ng-model="bookhotel.email">
                                                          </div>
                                                          <div class="col-md-6 mt-20">
                                                              <input class="form-control" placeholder="Enter Mobile No" type="text" name="" ng-model="bookhotel.mobile" maxlength="10">
                                                          </div>
                                                          <div class="col-md-12">
                                                              <p class="yo_bk">
                                                                Your booking details will be sent to this email address and mobile number.
                                                              </p>
                                                          </div>
                                                      </div>
                                                      <div class="row mt-20">
                                                          <div class="col-md-12 text-center">
                                                              <a class="all_btn" href="#" ng-click="getroombook(bookhotel);">Continue to Payment</a>
                                                          </div>
                                                      </div>
                                                    </form>  
                                                  </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        <div class="clearfix visible-sm-block"></div>
                    </div>
                </div>
            </div>
            <!--/flight search result list-->

            </div>
            <!--flight Search Filter section-->
            <div class="col-md-4">
                <div class="search-filter">
                    <div class="search-filter-head">
                        <h3>Room Price Details</h3>
                    </div>

                    <!-- BOOKING SUMMARY -->
                    <div class="flight-stop">
                        <div class="row bok_su">
                            <table class="table table-bordered table-responsive">
                                <tbody>
                                    <tr>
                                        <td>1 Rooms x 1 Night(s)</td>
                                        <td>57656</td>
                                    </tr>
                                    <tr>
                                        <td>1 Rooms x 1 Night(s)</td>
                                        <td>57656</td>
                                    </tr>
                                    <tr>
                                        <td>1 Rooms x 1 Night(s)</td>
                                        <td>57656</td>
                                    </tr>
                                    <tr>
                                        <td>1 Rooms x 1 Night(s)</td>
                                        <td>57656</td>
                                    </tr>
                                    <tr>
                                        <td style="color: #3082ed;font-weight: 700;">Grand Total</td>
                                        <td style="color: #3082ed;font-weight: 700;">65545</td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
            <!--/flight Search Filter section-->
        </div>
    </div>
</div>


<!-- Cancellation policy  -->
<div class="modal fade" id="cencellpol" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="exampleModalLabel">Hotel Booking Terms and Conditions</h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body">
	        <p>
	        	{{hotelblockresult.HotelRoomsDetail[0].CancellationPolicies}}
	        </p>
	      </div>
	      <!-- <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
	        <button type="button" class="btn btn-primary">Save changes</button>
	      </div> -->
	    </div>
	  </div>
	</div>

<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<!-- jQuery Min JS -->
<script src="assets/skyhotels/js/j.js"></script>
<script src="assets/js/angular.min.js"></script>
	<script src="assets/js/angular-sanitize.min.js"></script>
	
	<script src="assets/js/jquery-ui.js"></script>
	<script src="assets/js/cookieStore.js"></script>
	<script src="assets/js/alasql.min.js?version=1.0.0.2"></script>
	<script src="assets/js/xlsx.core.min.js?version=1.0.0.2"></script>
<!-- Bootstrap Min JS -->
<script src="assets/skyhotels/js/bootstrap.min.js"></script>
	<script	src="https://rawgit.com/rzajac/angularjs-slider/master/dist/rzslider.js"></script>
	<script data-require="ui-bootstrap@*" data-semver="0.13.0" src="http://angular-ui.github.io/bootstrap/ui-bootstrap-tpls-0.13.0.min.js"></script>

	<script src="assets/scripts/angular_skyhotel.js?version=1.0.5"></script>
<!-- Owl Carousel Min JS -->
<script src="assets/skyhotels/js/owl.carousel.min.js"></script>
<!-- Nice Select Min JS -->
<script src="assets/skyhotels/js/jquery-ui.min.js"></script>
<!-- Main JS -->
<script src="assets/skyhotels/js/main.js"></script>
<script src="assets/skyhotels/js/custom.js"></script>
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

</body>
</html>