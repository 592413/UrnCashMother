<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@page import="java.util.Map"%>
    <%@page import="java.util.List"%>
    <%@page import="com.skyflight.model.Flightdetail"%>
    <%@page import="com.skyflight.model.PassengerDetail"%>
     <%@page import="com.skyflight.model.Passengerfare"%>
    <%	
    Map<String,Object> flightbooking=(Map<String,Object>)session.getAttribute("flightbooking");
    
    List<Flightdetail> Flightdetail=( List<Flightdetail>)session.getAttribute("flightdetails");
    
    List<PassengerDetail> passengerlist=(List<PassengerDetail>)session.getAttribute("passengerlist");
    
    List<Passengerfare> listPassengerfare=(List<Passengerfare>)session.getAttribute("listPassengerfare");
%>
<!doctype html>
<html lang="zxx">

<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link href="https://fonts.googleapis.com/css?family=Poppins&display=swap" rel="stylesheet">
    <!-- Bootstrap Min CSS -->
    <!-- <link rel="stylesheet" href="css/bootstrap.min.css"> -->
    <!-- Font Awesome Min CSS -->
   <!--  <link rel="stylesheet" href="css/fontawesome.min.css"> -->
    <!-- Owl Carousel Min CSS -->
    <!-- <link rel="stylesheet" href="css/owl.carousel.min.css"> -->
    <!-- Owl Carousel Min CSS -->
    <!-- <link rel="stylesheet" href="css/jquery-ui.min.css"> -->
    <!-- Style CSS -->
  <!--   <link rel="stylesheet" href="css/style.css"> -->
    <!-- Responsive CSS -->
    <!-- <link rel="stylesheet" href="css/responsive.css"> -->
    <title>Flight</title>
</head>

<body style="background-color: #FDFBFB">
<input type="hidden" id="flightbooking" value="${flightbooking}">
<input type="hidden" id="flightdetails" value="${flightdetails}">
<input type="hidden" id="listPassengerfare" value="${listPassengerfare}">
<input type="hidden" id="passengerlist" value="${passengerlist}">
<!-- Preloader -->
<div class="preloader">
    <div class="loader">
        <div class="loader-outter"></div>
        <div class="loader-inner"></div>
    </div>
</div>

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
                    <img src="images/logo/LOGO.png" style="width: 21%; padding: 4px;">
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
                    <li><a href="skyflightsearch">Flight Search</a></li>
                    <li><a href="flight">Booking Queues</a></li>
                    <li><a href="flight">Tickets Queues</a></li>
                    <li><a href="flight"> Change Request Queues</a></li>
                    <!--dropdown list (if it necessity )-->
                    <!-- <li class="dropdown" style="display: none">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Dropdown <span class="caret"></span></a>
                        <ul class="dropdown-menu" role="menu">
                            <li><a href="#">Action</a></li>
                            <li><a href="#">Another action</a></li>
                            <li><a href="#">Something else here</a></li>
                            <li><a href="#">Separated link</a></li>
                            <li><a href="#">One more separated link</a></li>
                        </ul>
                    </li> -->
                    <!--dropdown list (if it necessity )-->
                </ul>
            </div>
        </div>
    </nav>
</div>
<!--navigation Area-->


<!-- End Preloader -->
<div class="container">
<div class="row">
<div class="invoice" style="padding: 20px 0;">
        <div class="container" style="width:1170px;padding-right: 15px;padding-left: 15px;margin-right: auto;margin-left: auto;">
            <div class="inv-row" style="margin-right: -15px;margin-left: -15px;">
                <div class="col-md-12-inv" style="padding-right: 15px;padding-left: 15px;">
                    <div class="inv-panel-group" style="margin-bottom: 20px;">
                        <div class="inv-panel inv-panel-primary" style="margin-bottom: 0;border-radius: 4px;border: 1px solid #337ab7;">
                            <div class="inv-panel-heading" style="background-color: #337ab7;color: #fff;padding: 10px 15px;">
                                <div class="inv-row" style="margin-right: -15px;margin-left: -15px;">
                                    <div class="col-md-12-inv" style="padding-right: 15px;padding-left: 15px;">
                                        <h4 style="font-size:25px;font-weight:600;text-transform: uppercase;margin: 0;">Invoice :-</h4>
                                    </div>
                                </div>
                            </div>
                            <div class="inv-panel-body" style="padding: 15px;display: table;">
                                <div class="inv-row" style="margin-right: -15px;margin-left: -15px;    display: flex;margin-bottom:50px;">
                                    <div class="col-md-4-inv" style="width:33%;position: relative;min-height: 1px;padding-right: 15px;padding-left: 15px;float: left;">
                                        <div class="img-responsive-in" style="background-color: #e2e2e2;border: 2px dashed #337ab7;padding: 20px; text-align: center;">
                                            <h2 style="margin: 0;"><img class="logo img-responsive" id="logo" src = ""></h2>
                                        </div>
                                    </div>
                                    <div class="col-md-3-inv" style="width: 25%; display: inline-block;padding-right: 15px;padding-left: 15px;">
                                        <h4 style="background-color: #337ab7;color: #fff;padding: 4px;text-align: center; font-size: 15px;">Invoice-DW/1920/3635517
                                        </h4>
                                    </div>
                                    <div class="col-md-3-inv" style="width: 25%; display: inline-block;padding-right: 15px;padding-left: 15px;">
                                        <h4 style="background-color: #337ab7;color: #fff;padding: 4px;text-align: center; font-size: 15px;">Invoice Date : <%=flightbooking.get("date") %>
                                        </h4>
                                    </div>
                                    <div class="col-md-3-inv" style="width: 25%; display: inline-block;padding-right: 15px;padding-left: 15px;">
                                        <h4 style="background-color: #337ab7;color: #fff;padding: 4px;text-align: center; font-size: 16px;">PNR:<%=flightbooking.get("PNR") %>
                                        </h4>
                                    </div>
                                </div>
                                <div class="inv-row" style="margin-right: -15px;margin-left: -15px;display: flex;">
                                    <div class="inv-col-md-6" style="width: 50%;display: inline-block;padding-right: 15px;padding-left: 15px;">
                                            <div class="invo-le" style="background-color: #e2e2e2;padding: 20px;margin-bottom: 30px;border-radius: 2px;">
                                            <ul>
                                                <li style="margin-bottom: 8px;">Travel company name (A Unit of Tek Travels Pvt Ltd)</li>
                                                <li style="margin-bottom: 8px;">Regd Office : <span> E-78, South Extn Part– I, New Delhi 110049 </span></li>
                                                <li style="margin-bottom: 8px;">Corp Off : <span> Plot No 728, Udyog vihar Phase-V,Gurugram 122016 </span></li>
                                                <li style="margin-bottom: 8px;">Email : <span>  info@gmail.com </span></li>
                                                <li style="margin-bottom: 8px;">Web : <span> www.travel.com</span></li>
                                                <li style="margin-bottom: 8px;">Phone : <span> 0124-4998999 </span></li>
                                                <li style="margin-bottom: 8px;">CI Number : <span>  U74999DL2006PTC155233 </span></li>
                                                <li style="margin-bottom: 8px;">PAN : <span> AACCT6259K </span></li>
                                                <li style="margin-bottom: 8px;">GST State : <span> Haryana </span></li>
                                                <li style="margin-bottom: 8px;">GSTIN : <span>  06AACCT6259K1ZZ </span></li>
                                                <li style="margin-bottom: 8px;">Travel Date : <span> <%=flightbooking.get("travel_date") %> </span></li>
                                            </ul>
                                        </div>
                                    </div>
                                    <div class="inv-col-md-6" style="width: 50%;display: inline-block;padding-right: 15px;padding-left: 15px;">
                                    <div class="confi-bod" style="border: 2px dashed #337ab7;padding: 20px;">
                                        <ul>
                                            <li style="margin-bottom: 8px;">EDPL HOLIDAYS</li>
                                            <li style="margin-bottom: 8px;">14 RAMSITA GHAT STREET,UTTARPARA, Kolkata</li>
                                            <li style="margin-bottom: 8px;">Phone : <span> 03326644343 </span></li>
                                            <li style="margin-bottom: 8px;">PAN : <span> ACPPD1851M </span></li>
                                            <li style="margin-bottom: 8px;">GST State : <span> west bangla </span></li>
                                            <li style="margin-bottom: 8px;">GSTIN : <span> 19ACPPD1851M1Z8 </span></li>
                                        </ul>
                                    </div>
                                </div>
                                </div>

                                <div class="in-tebil table-responsive-in" style="min-height: .01%;overflow-x: auto;">
                                    <table class="table-in in-table-bordered" style="width: 100%;max-width: 100%;margin-bottom: 20px;border: 1px solid #ddd;">
                                        <thead>
                                        <tr style="border-bottom: 1px solid #ccc;">
                                            <th style="border-right: 1px solid #ccc;font-size: 12px;font-weight: 500;padding: 10px;">Sr. No</th>
                                            <th style="border-right: 1px solid #ccc;font-size: 12px;font-weight: 500;padding: 10px;"   style="border-right: 1px solid #ccc;">Ticket No.</th>
                                            <th style="border-right: 1px solid #ccc;font-size: 12px;font-weight: 500;padding: 10px;">Sectors</th>
                                            <th style="border-right: 1px solid #ccc;font-size: 12px;font-weight: 500;padding: 10px;">Flight</th>
                                            <th style="border-right: 1px solid #ccc;font-size: 12px;font-weight: 500;padding: 10px;">PAX Name</th>
                                            <th style="border-right: 1px solid #ccc;font-size: 12px;font-weight: 500;padding: 10px;">Type</th>
                                            <th style="border-right: 1px solid #ccc;font-size: 12px;font-weight: 500;padding: 10px;">Class</th>
                                            <th style="border-right:1px solid #ccc;font-size: 12px;font-weight: 500;padding: 10px;">Fare</th>
                                            <th style="border-right: 1px solid #ccc;font-size: 12px;font-weight: 500;padding: 10px;">OT Tax</th>
                                            <th style="border-right: 1px solid #ccc;font-size: 12px;font-weight: 500;padding: 10px;">K3/GST</th>
                                            <th style="border-right: 1px solid #ccc;font-size: 12px;font-weight: 500;padding: 10px;">YQ Tax</th>
                                            <th style="border-right: 1px solid #ccc;font-size: 12px;font-weight: 500;padding: 10px;">Baggage Ch.</th>
                                            <th style="border-right: 1px solid #ccc;font-size: 12px;font-weight: 500;padding: 10px;">Meal Ch.</th>
                                            <th style="border-right: 1px solid #ccc;font-size: 12px;font-weight: 500;padding: 10px;">Seat Ch.</th>
                                            <th style="border-right: 1px solid #ccc;font-size: 12px;font-weight: 500;padding: 10px;">SpecialService Ch.</th>
                                            <th style="font-size: 12px;font-weight: 500;padding: 10px;">Service Chrgs</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <%
                                        for(int i=0;i<passengerlist.size();i++){
                                        
                                        %>
                                        <tr>
                                            <td style="border-right: 1px solid #ccc;padding: 10px;">1</td>
                                            <td style="border-right: 1px solid #ccc;padding: 10px;"><%=passengerlist.get(i).getTicketnumber()%></td>
                                            <td style="border-right: 1px solid #ccc;padding: 10px;"><%=flightbooking.get("source") %>-<%=flightbooking.get("destination") %></td>
                                            <td style="border-right: 1px solid #ccc;padding: 10px;"><%=Flightdetail.get(0).getFlight_number() %></td>
                                            <td style="border-right: 1px solid #ccc;padding: 10px;"><%=passengerlist.get(i).getTitle()%>. <%=passengerlist.get(i).getFirstname()%>  <%=passengerlist.get(i).getLastname()%></td>
                                            <td style="border-right: 1px solid #ccc;padding: 10px;">ADT</td>
                                            <td style="border-right: 1px solid #ccc;padding: 10px;">z</td>
                                            <td style="border-right: 1px solid #ccc;padding: 10px;">3,046.00</td>
                                            <td style="border-right: 1px solid #ccc;padding: 10px;">983.00</td>
                                            <td style="border-right: 1px solid #ccc;padding: 10px;">160.00</td>
                                            <td style="border-right: 1px solid #ccc;padding: 10px;">0.00</td>
                                            <td style="border-right: 1px solid #ccc;padding: 10px;">0.00</td>
                                            <td style="border-right: 1px solid #ccc;padding: 10px;">0.00</td>
                                            <td style="border-right: 1px solid #ccc;padding: 10px;">0.00</td>
                                            <td style="border-right: 1px solid #ccc;padding: 10px;">0.00</td>
                                            <td style="border-right: 1px solid #ccc;padding: 10px;">0.00</td>
                                        </tr>
                                        <%
                                        }%>
                                        </tbody>
                                    </table>
                                </div>

                                <div class="confi-bod-in" style="border: 2px dashed #337ab7;padding: 20px;">
                                    <div class="inv-row" style="margin-right: -15px;margin-left: -15px;display: flex;">
                                        <div class="col-md-4-inv" style="width:33%;position: relative;min-height: 1px;padding-right: 15px;padding-left: 15px;float: left;">
                                            <h3 style="margin-top: 35%;">Notes:</h3>
                                            <p>*** Voidation and Refund as per fare rules</p>
                                        </div>
                                        <div class="col-md-8-in table-responsive-in" style="min-height: .01%;overflow-x: auto;padding-right:15px;padding-left:15px;width: 66%;">
                                            <table class="table-in in-table-bordered" style="width: 100%;max-width: 100%;margin-bottom: 20px;border: 1px solid #ddd;">
                                                <thead>
                                                <tr style="border-bottom: 1px solid #ccc;">
                                                    <th style="border-right: 1px solid #ccc;padding: 10px;">Sr. No</th>
                                                    <th style="border-right: 1px solid #ccc;padding: 10px;">Gross:</th>
                                                    <th style="border-right: 1px solid #ccc;padding: 10px;">Rs</th>
                                                </tr>
                                                </thead>
                                                <tbody>
                                                    <tr>
                                                        <td style="border-right:1px solid #ccc; border-bottom: 1px solid #ccc; padding: 10px;">Less</td>
                                                        <td style="border-right:1px solid #ccc; border-bottom: 1px solid #ccc; padding: 10px;">Commission Earned</td>
                                                        <td style="border-right:1px solid #ccc; border-bottom: 1px solid #ccc; padding: 10px;">0.00</td>
                                                    </tr>
                                                    <tr>
                                                        <td style="border-right:1px solid #ccc; border-bottom: 1px solid #ccc; padding: 10px;">Add</td>
                                                        <td style="border-right:1px solid #ccc; border-bottom: 1px solid #ccc; padding: 10px;">Tra Fee</td>
                                                        <td style="border-right:1px solid #ccc; border-bottom: 1px solid #ccc; padding: 10px;">0.00</td>
                                                    </tr>
                                                    <tr>
                                                        <td style="border-right:1px solid #ccc; border-bottom: 1px solid #ccc; padding: 10px;">Add</td>
                                                        <td style="border-right:1px solid #ccc; border-bottom: 1px solid #ccc; padding: 10px;">TDS Deducted</td>
                                                        <td style="border-right:1px solid #ccc; border-bottom: 1px solid #ccc; padding: 10px;">0.00</td>
                                                    </tr>
                                                    <tr>
                                                        <td style="border-right:1px solid #ccc; border-bottom: 1px solid #ccc; padding: 10px;">Add</td>
                                                        <td style="border-right:1px solid #ccc; border-bottom: 1px solid #ccc; padding: 10px;">CGST @0.00%</td>
                                                        <td style="border-right:1px solid #ccc; border-bottom: 1px solid #ccc; padding: 10px;">0.00</td>
                                                    </tr>
                                                    <tr>
                                                        <td style="border-right:1px solid #ccc; border-bottom: 1px solid #ccc; padding: 10px;">Add</td>
                                                        <td style="border-right:1px solid #ccc; border-bottom: 1px solid #ccc; padding: 10px;">SGST @0.00%</td>
                                                        <td style="border-right:1px solid #ccc; border-bottom: 1px solid #ccc; padding: 10px;">0.00</td>
                                                    </tr>
                                                    <tr>
                                                        <td style="border-right:1px solid #ccc; border-bottom: 1px solid #ccc; padding: 10px;">Add</td>
                                                        <td style="border-right:1px solid #ccc; border-bottom: 1px solid #ccc; padding: 10px;">IGST @18.00%</td>
                                                        <td style="border-right:1px solid #ccc; border-bottom: 1px solid #ccc; padding: 10px;">0.00</td>
                                                    </tr>
                                                    <tr>
                                                        <td style="border-right:1px solid #ccc; border-bottom: 1px solid #ccc; padding: 10px;">Rs</td>
                                                        <td style="border-right:1px solid #ccc; border-bottom: 1px solid #ccc; padding: 10px;">Net Amount</td>
                                                        <td style="border-right:1px solid #ccc; border-bottom: 1px solid #ccc; padding: 10px;">0.00</td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>

                                <div class="gst">
                                    <div class="inv-row" style="margin-right: -15px;margin-left: -15px;display: flex;">
                                        <div class="col-md-6-in table-responsive-in" style="width: 50%; padding-right: 15px;padding-left: 15px; display: inline-block; min-height: .01%;overflow-x: auto;">
                                            <h2 style="font-size: 16px;font-weight: 600;">Passenger GST Details:</h2>
                                            <table class="table-in" style="width: 100%;max-width: 100%;margin-bottom: 20px;border: 1px solid #ddd;">
                                                <thead>
                                                    <tr style="border-bottom: 1px solid #ccc;">
                                                        <th style="font-size: 13px; font-weight: normal; padding: 10px; border-right: 1px solid #ccc;">Lead Pax Name</th>
                                                        <th style="font-size: 13px; font-weight: normal; padding: 10px; border-right: 1px solid #ccc;">GST Number.</th>
                                                        <th style="font-size: 13px; font-weight: normal; padding: 10px; border-right: 1px solid #ccc;">GST Company Contact Number</th>
                                                        <th style="font-size: 13px; font-weight: normal; padding: 10px; border-right: 1px solid #ccc;">GST Company Address</th>
                                                        <th style="font-size: 13px; font-weight: normal; padding: 10px; border-right: 1px solid #ccc;">GST Company Email</th>
                                                        <th style="font-size: 13px; font-weight: normal; padding: 10px; border-right: 1px solid #ccc;">GST Company Name</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <tr>
                                                        <td style="border-right:1px solid #ccc; border-bottom: 1px solid #ccc; padding: 10px;font-size: 13px;font-weight: normal;">Mr SANTOSH RAMAN</td>
                                                        <td style="border-right:1px solid #ccc; border-bottom: 1px solid #ccc; padding: 10px;font-size: 13px;font-weight: normal;">- - - -</td>
                                                        <td style="border-right:1px solid #ccc; border-bottom: 1px solid #ccc; padding: 10px;font-size: 13px;font-weight: normal;">- - - -</td>
                                                        <td style="border-right:1px solid #ccc; border-bottom: 1px solid #ccc; padding: 10px;font-size: 13px;font-weight: normal;">- - - -</td>
                                                        <td style="border-right:1px solid #ccc; border-bottom: 1px solid #ccc; padding: 10px;font-size: 13px;font-weight: normal;">- - - -</td>
                                                        <td style="border-right:1px solid #ccc; border-bottom: 1px solid #ccc; padding: 10px;font-size: 13px;font-weight: normal;">- - - -</td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                        <div class="col-md-6-in table-responsive-in" style="width: 50%; padding-right: 15px;padding-left: 15px; display: inline-block; min-height: .01%;overflow-x: auto;">
                                            <h2 style="font-size: 16px;font-weight: 600;">GST Details:</h2>
                                            <table class="table-in" style="width: 100%;max-width: 100%;margin-bottom: 20px;border: 1px solid #ddd;">
                                                <thead>
                                                    <tr style="border-bottom: 1px solid #ccc;">
                                                        <th style="border-right:1px solid #ccc; border-bottom: 1px solid #ccc; padding: 10px;font-size: 13px;font-weight: normal;">Service Description</th>
                                                        <th style="border-right:1px solid #ccc; border-bottom: 1px solid #ccc; padding: 10px;font-size: 13px;font-weight: normal;">SAC</th>
                                                        <th style="border-right:1px solid #ccc; border-bottom: 1px solid #ccc; padding: 10px;font-size: 13px;font-weight: normal;">Taxable Value</th>
                                                        <th style="border-right:1px solid #ccc; border-bottom: 1px solid #ccc; padding: 10px;font-size: 13px;font-weight: normal;">CGST @ 0.00%</th>
                                                        <th style="border-right:1px solid #ccc; border-bottom: 1px solid #ccc; padding: 10px;font-size: 13px;font-weight: normal;">SGST @ 0.00%</th>
                                                        <th style="border-right:1px solid #ccc; border-bottom: 1px solid #ccc; padding: 10px;font-size: 13px;font-weight: normal;">IGST @ 18.00%</th>
                                                        <th style="border-right:1px solid #ccc; border-bottom: 1px solid #ccc; padding: 10px;font-size: 13px;font-weight: normal;">otal GST</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <tr>
                                                        <td style="border-right:1px solid #ccc; border-bottom: 1px solid #ccc; padding: 10px;font-size: 13px;font-weight: normal;">Transaction Fees</td>
                                                        <td style="border-right:1px solid #ccc; border-bottom: 1px solid #ccc; padding: 10px;font-size: 13px;font-weight: normal;">0.00</td>
                                                        <td style="border-right:1px solid #ccc; border-bottom: 1px solid #ccc; padding: 10px;font-size: 13px;font-weight: normal;">0.00</td>
                                                        <td style="border-right:1px solid #ccc; border-bottom: 1px solid #ccc; padding: 10px;font-size: 13px;font-weight: normal;">0.00</td>
                                                        <td style="border-right:1px solid #ccc; border-bottom: 1px solid #ccc; padding: 10px;font-size: 13px;font-weight: normal;">0.00</td>
                                                        <td style="border-right:1px solid #ccc; border-bottom: 1px solid #ccc; padding: 10px;font-size: 13px;font-weight: normal;">0.00</td>
                                                        <td style="border-right:1px solid #ccc; border-bottom: 1px solid #ccc; padding: 10px;font-size: 13px;font-weight: normal;">0.00</td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>

                                <div class="billed">
                                    <div class="inv-row" style="display: flex;">
                                        <div class="col-md-6-in" style="width: 50%; padding-right: 15px;padding-left: 15px; display: inline-block;background-color:#e2e2e2;border: 2px dashed #337ab7;padding: 20px;">
                                            <div class="bo-le">
                                                <ul>
                                                    <li style="font-size: 13px;font-weight: 600;text-transform: capitalize;margin-bottom: 13px;">Billed by : <span style="font-size: 13px;font-weight: normal;"> Travel site name </span></li>
                                                    <li>Ticketed by : <span style="font-size: 13px;font-weight: normal;"> name  </span></li>
                                                    <li>Invoice Status : <span style="font-size: 13px;font-weight: normal;"> Paid </span></li>
                                                    <li style="font-size: 13px;font-weight: normal;">Agent Remarks .</li>
                                                    <li style="font-size: 13px;font-weight: normal;">Terms & Conditions :</li>
                                                </ul>
                                            </div>
                                            <div class="bild">
                                                <ul>
                                                    <li>* This is computer generated invoice signature not required.</li>
                                                    <li>* All Cases Disputes are subject to New Delhi Jurisdiction.</li>
                                                    <li>* Refunds Cancellations are subject to Airline's approval.</li>
                                                    <li>* Kindly check all details carefully to avoid unnecessary complications.</li>
                                                    <li>* CHEQUE : Must be drawn in favour of 'Travel site name'.</li>
                                                    <li>* LATE PAYMENT : Interest per annum will be charged on all outstanding bills after due date.</li>
                                                </ul>
                                            </div>
                                        </div>
                                        <div class="col-md-6-in" style="width: 50%; padding-right: 15px;padding-left: 15px; display: inline-block;">
                                            <div class="bo-le" style="text-align: right;">
                                                <ul style="margin-top: 20%;">
                                                    <li style="display: inline-block;"><a style="color: #fff !important;background-color: #0635ff;border: 1px solid #278cfc;display: inline-block;padding: 7px 12px;text-decoration: none;" href="#">Print Invoice</a></li>
                                                    <li style="display: inline-block;"><a style="color: #fff !important;background-color: #0635ff;border: 1px solid #278cfc;display: inline-block;padding: 7px 12px;text-decoration: none;" href="#">Email Invoice</a></li>
                                                    <li style="display: inline-block;"><a style="color: #fff !important;background-color: #0635ff;border: 1px solid #278cfc;display: inline-block;padding: 7px 12px;text-decoration: none;" href="#">Generate PDF</a></li>
                                                    <li style="display: inline-block;"><a style="color: #fff !important;background-color: #0635ff;border: 1px solid #278cfc;display: inline-block;padding: 7px 12px;text-decoration: none;" href="#">back</a></li>
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
</div>
</div>
<script src="assets/skyflight/js/j.js"></script>
<script src="assets/js/angular.min.js"></script>	
	<script src="assets/js/bootstrap.js"></script>
	<script src="assets/js/jquery.scrollabletab.js"></script>
	<script src="assets/js/autocompletedata.js"></script>
	<script src="assets/js/jquery-ui.js"></script> 
		<script src="assets/scripts/angular_Av2.min.js?version=1.0.5"></script>

<!-- Bootstrap Min JS -->
<!-- <script src="js/bootstrap.min.js"></script>
 -->
 <!-- Owl Carousel Min JS -->
<!-- <script src="js/owl.carousel.min.js"></script> -->
<!-- Nice Select Min JS -->
<!-- <script src="js/jquery-ui.min.js"></script> -->
<!-- Main JS -->
<!-- <script src="js/script.js"></script> -->

<script type="text/javascript">

    $(document).ready(function(){
        $('.sub-menu').parent().click(function() {
            var submenu = $(this).children('.sub-menu');
            if ( $(submenu).is(':hidden') ) {
                $(submenu).slideDown(200);
            } else {
                $(submenu).slideUp(200);
            }
        });
    });

</script>



</body>
</html>