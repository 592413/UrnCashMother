<!doctype html>
<html lang="zxx">

<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link href="https://fonts.googleapis.com/css?family=Poppins&display=swap" rel="stylesheet">
    <!-- Bootstrap Min CSS -->
    <link rel="stylesheet" href="assets/skyflight/css/bootstrap.min.css">
    <!-- Font Awesome Min CSS -->
    <link rel="stylesheet" href="assets/skyflight/css/fontawesome.min.css">
    <!-- Owl Carousel Min CSS -->
    <link rel="stylesheet" href="assets/skyflight/css/owl.carousel.min.css">
    <link rel="stylesheet" href="assets/skyflight/css/owl.theme.default.min.css">
    <!-- Owl Carousel Min CSS -->
    <link rel="stylesheet" href="assets/skyflight/css/jquery-ui.min.css">
    <!-- Style CSS -->
    <link rel="stylesheet" href="assets/skyflight/css/style.css">
    <!-- Responsive CSS -->
    <link rel="stylesheet" href="assets/skyflight/css/responsive.css">
    <title>Flight</title>
</head>

<body style="background-color: #FDFBFB" ng-app = "app" ng-controller = "appController">

<!-- Preloader -->
<div class="preloader">
    <div class="loader">
        <div class="loader-outter"></div>
        <div class="loader-inner"></div>
    </div>
</div>
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
                    <img src="assets/skyflight/images/logo/LOGO.png" style="width: 21%; padding: 4px;">
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


<div class="flight-list-page-search">
    <div class="container">
        <div class="row">
            <div class="flight-search-form">
                <form>
                    <div class="form-group">
                        <div class="row">
                            <div class="col-md-2">
                                <label>Depart From</label>
                                <input type="text" class="form-control">
                            </div>

                            <div class="col-md-2">
                                <label>Going To</label>
                                <input type="text" class="form-control">
                            </div>

                            <div class="col-md-2">
                                <label>Depart Date</label>
                                <input type="text" class="form-control">
                            </div>

                            <div class="col-md-2">
                                <label>Return Date</label>
                                <input type="text" class="form-control">
                            </div>

                            <div class="col-md-2">
                                <label>Adults<small>
                                    (12+ Years)</small></label>
                                <input type="text" class="form-control">
                            </div>

                            <div class="col-md-2">
                                <label>Child <small>( 2-12 YRS )</small></label>
                                <input type="text" class="form-control">
                            </div>

                        </div>

                        <div class="row">
                            <div class="col-md-2">
                                <label>Infants <small>( Below 2 YRS )</small></label>
                                <input type="text" class="form-control">
                            </div>

                            <div class="col-md-3">
                                <label>Traveller(s), Class</label>
                                <input type="text" class="form-control">
                            </div>
                            <div class="col-md-3">
                                <button class="bookbtn mt-20">Searching</button>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>


<div class="roundtrip-flight-list">
    <div class="container">
        <div class="row">
            <div class="col-md-6">
                <!--roundtrip-flight-list-info-->
                <div class="rtflight-list-info mt-30">
                    <div class="row">
                        <div class="col-md-4">
                            <div class="travel-path-head">
                                <p class="n-margin">Delhi → Mumbai</p>
                            </div>
                        </div>

                        <div class="col-md-4">
                            <div class="travel-duration-head">
                                <p class="n-margin">Sat 30-Nov-2019</p>
                            </div>
                        </div>

                        <div class="col-md-4">
                            <div class="travel-date-head">
                                <span>
                                    <a href="#">Previous Day</a> | <a href="#">Next Day</a>
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
                            <p class="n-margin">Airline	</p>
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
                <div class="rtflight-result-list mt-20">
                    <div class="row">
                        <div class="flight-list-v2">

                            <div class="col-md-12">
                                <div class="flight-list-main">
                                    <div class="col-md-2 col-sm-2 text-center airline">
                                        <img src="assets/skyflight/images/flightIcon/goair.jpg" alt="cruise">
                                        <h6>Vistara</h6>
                                    </div>
                                    <div class="col-md-2 col-sm-2 departure text-center pt-20">
                                        <h3><i class="fa fa-plane"></i> LHR 19:00</h3>
                                        <h5 class="bold">SAT, 21 SEP</h5>
                                        <h5>Goa, IN</h5>
                                    </div>
                                    <div class="col-md-3 col-sm-3 stop-duration text-center pt-20">
                                        <div class="flight-direction">
                                        </div>
                                        <div class="stop">
                                        </div>
                                        <div class="stop-box">
                                            <span>0 Stop</span>
                                        </div>
                                        <div class="duration text-center">
                                            <span><i class="fa fa-clock-o"></i> 02h 00m</span>
                                        </div>
                                    </div>
                                    <div class="col-md-2 col-sm-2 destination text-center pt-20">
                                        <h3><i class="fa fa-plane"></i> DEL 21:00</h3>
                                        <h5 class="bold">SAT, 21 SEP</h5>
                                        <h5>New Delhi, IN</h5>
                                    </div>

                                    <div class="col-md-3 col-sm-3  text-center pt-20">
                                        <div class="booking-Price">
                                            <h3>Rs. 7089</h3>
                                        </div>
                                        <div class="booking-btn pt-10">
                                            <button class="bookbtn">Booking</button>
                                        </div>
                                    </div>
                                </div>
                            </div>


                            <div class="col-md-12">
                                <div class="flight-list-footer">
                                    <div class="col-md-6 col-sm-6 sm-invisible">
                                        <span><i class="fa fa-plane"></i> Business Class</span>
                                        <span class="refund"><i class="fa fa-undo"></i> Refundable</span>

                                    </div>
                                    <div class="col-md-6 col-sm-6 col-xs-12 clear-padding">
                                        <div class="pull-right">
                                            <a href="#">Show Details +</a>
                                        </div>

                                        <div class="rtflight-radio-btn">
                                            <label class="radio-inline"><input type="radio" name="optradio"></label>
                                        </div>
                                    </div>
                                </div>
                            </div>


                        </div>
                        <div class="flight-details-tab">
                            <div class="col-md-12">
                                    <ul class="nav nav-tabs">
                                        <li class="active"><a data-toggle="tab" href="#home">Flight Information</a></li>
                                        <li><a data-toggle="tab" href="#menu1">Fare Details</a></li>
                                        <li><a data-toggle="tab" href="#menu2">Baggage Information</a></li>
                                        <li><a data-toggle="tab" href="#menu3">Cancellation Rules</a></li>
                                    </ul>

                                    <div class="tab-content">
                                        <div id="home" class="tab-pane fade in active">
                                            <div class="table-responsive">
                                                <table class="table table-bordered">
                                                    <thead>
                                                    <tr>
                                                        <th>
                                                            <div class="tabp-finfo-fimg text-center">
                                                                <img src="assets/skyflight/images/flightIcon/goair.jpg">
                                                            </div>
                                                        </th>
                                                        <th>
                                                            <div class="tabp-finfo-fname text-center">
                                                                <p>
                                                                    <b>SpiceJet</b><br>
                                                                    <small>(SG- 516)</small>
                                                                </p>
                                                            </div>
                                                        </th>
                                                        <th>
                                                            <div class="tabp-finfo-fname text-center">
                                                                <p>
                                                                    <b>CCU 06:55</b><br>
                                                                    <small>(Sat-30Nov2019)</small>
                                                                </p>
                                                            </div>
                                                        </th>
                                                        <th>
                                                            <div class="tabp-finfo-fdur text-center">
                                                                <i class="fa fa-clock"></i><br>
                                                                <p><b>02h 50m</b></p>
                                                            </div>
                                                        </th>
                                                        <th>
                                                            <div class="tabp-finfo-fname text-center">
                                                                <p>
                                                                    <b>CCU 06:55</b><br>
                                                                    <small>(Sat-30Nov2019)</small>
                                                                </p>
                                                            </div>
                                                        </th>
                                                    </tr>
                                                    </thead>
                                                </table>
                                            </div>
                                        </div>
                                        <div id="menu1" class="tab-pane fade">
                                            <div class="table-responsive">
                                                <table class="table table-bordered">
                                                    <tr>
                                                        <td class="text-center">1 x Adult</td>
                                                        <td class="text-center">Total (Base Fare)</td>
                                                        <td  class="text-center">Total Tax</td>
                                                        <td  class="text-center">Grand  Total</td>
                                                    </tr>
                                                    <tr>
                                                        <td class="text-center">Rs. 5048</td>
                                                        <td class="text-center">Rs. 5048</td>
                                                        <td class="text-center">Rs. 5048</td>
                                                        <td class="text-center"><b>Rs. 5048</b></td>
                                                    </tr>
                                                </table>
                                            </div>
                                        </div>

                                        <div id="menu2" class="tab-pane fade">
                                            <div class="table-responsive">
                                                <table class="table table-bordered">
                                                    <tr>
                                                        <td class="text-center">Airline</td>
                                                        <td class="text-center">Check-in Baggage</td>
                                                        <td  class="text-center">Cabin Baggage</td>
                                                    </tr>
                                                    <tr>
                                                        <td class="text-center">SpiceJet (SG- 516)</td>
                                                        <td class="text-center">15kg</td>
                                                        <td class="text-center">7kg</td>
                                                    </tr>
                                                </table>
                                            </div>
                                        </div>

                                        <div id="menu3" class="tab-pane fade">
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

                <!--roundtrip-flight-result-list-heading-->
                <div class="rtflight-result-list mt-20">
                    <div class="row">
                        <div class="flight-list-v2">

                            <div class="col-md-12">
                                <div class="flight-list-main">
                                    <div class="col-md-2 col-sm-2 text-center airline">
                                        <img src="assets/skyflight/images/flightIcon/goair.jpg" alt="cruise">
                                        <h6>Vistara</h6>
                                    </div>
                                    <div class="col-md-2 col-sm-2 departure text-center pt-20">
                                        <h3><i class="fa fa-plane"></i> LHR 19:00</h3>
                                        <h5 class="bold">SAT, 21 SEP</h5>
                                        <h5>Goa, IN</h5>
                                    </div>
                                    <div class="col-md-3 col-sm-3 stop-duration text-center pt-20">
                                        <div class="flight-direction">
                                        </div>
                                        <div class="stop">
                                        </div>
                                        <div class="stop-box">
                                            <span>0 Stop</span>
                                        </div>
                                        <div class="duration text-center">
                                            <span><i class="fa fa-clock-o"></i> 02h 00m</span>
                                        </div>
                                    </div>
                                    <div class="col-md-2 col-sm-2 destination text-center pt-20">
                                        <h3><i class="fa fa-plane"></i> DEL 21:00</h3>
                                        <h5 class="bold">SAT, 21 SEP</h5>
                                        <h5>New Delhi, IN</h5>
                                    </div>

                                    <div class="col-md-3 col-sm-3  text-center pt-20">
                                        <div class="booking-Price">
                                            <h3>Rs. 7089</h3>
                                        </div>
                                        <div class="booking-btn pt-10">
                                            <button class="bookbtn">Booking</button>
                                        </div>
                                    </div>
                                </div>
                            </div>


                            <div class="col-md-12">
                                <div class="flight-list-footer">
                                    <div class="col-md-6 col-sm-6 sm-invisible">
                                        <span><i class="fa fa-plane"></i> Business Class</span>
                                        <span class="refund"><i class="fa fa-undo"></i> Refundable</span>

                                    </div>
                                    <div class="col-md-6 col-sm-6 col-xs-12 clear-padding">
                                        <div class="pull-right">
                                            <a href="#">Show Details +</a>
                                        </div>

                                        <div class="rtflight-radio-btn">
                                            <label class="radio-inline"><input type="radio" name="optradio"></label>
                                        </div>
                                    </div>
                                </div>
                            </div>


                        </div>
                        <div class="flight-details-tab">
                            <div class="col-md-12">
                                <ul class="nav nav-tabs">
                                    <li class="active"><a data-toggle="tab" href="#home">Flight Information</a></li>
                                    <li><a data-toggle="tab" href="#menu1">Fare Details</a></li>
                                    <li><a data-toggle="tab" href="#menu2">Baggage Information</a></li>
                                    <li><a data-toggle="tab" href="#menu3">Cancellation Rules</a></li>
                                </ul>

                                <div class="tab-content">
                                    <div id="home" class="tab-pane fade in active">
                                        <div class="table-responsive">
                                            <table class="table table-bordered">
                                                <thead>
                                                <tr>
                                                    <th>
                                                        <div class="tabp-finfo-fimg text-center">
                                                            <img src="assets/skyflight/images/flightIcon/goair.jpg">
                                                        </div>
                                                    </th>
                                                    <th>
                                                        <div class="tabp-finfo-fname text-center">
                                                            <p>
                                                                <b>SpiceJet</b><br>
                                                                <small>(SG- 516)</small>
                                                            </p>
                                                        </div>
                                                    </th>
                                                    <th>
                                                        <div class="tabp-finfo-fname text-center">
                                                            <p>
                                                                <b>CCU 06:55</b><br>
                                                                <small>(Sat-30Nov2019)</small>
                                                            </p>
                                                        </div>
                                                    </th>
                                                    <th>
                                                        <div class="tabp-finfo-fdur text-center">
                                                            <i class="fa fa-clock"></i><br>
                                                            <p><b>02h 50m</b></p>
                                                        </div>
                                                    </th>
                                                    <th>
                                                        <div class="tabp-finfo-fname text-center">
                                                            <p>
                                                                <b>CCU 06:55</b><br>
                                                                <small>(Sat-30Nov2019)</small>
                                                            </p>
                                                        </div>
                                                    </th>
                                                </tr>
                                                </thead>
                                            </table>
                                        </div>
                                    </div>
                                    <div id="menu1" class="tab-pane fade">
                                        <div class="table-responsive">
                                            <table class="table table-bordered">
                                                <tr>
                                                    <td class="text-center">1 x Adult</td>
                                                    <td class="text-center">Total (Base Fare)</td>
                                                    <td  class="text-center">Total Tax</td>
                                                    <td  class="text-center">Grand  Total</td>
                                                </tr>
                                                <tr>
                                                    <td class="text-center">Rs. 5048</td>
                                                    <td class="text-center">Rs. 5048</td>
                                                    <td class="text-center">Rs. 5048</td>
                                                    <td class="text-center"><b>Rs. 5048</b></td>
                                                </tr>
                                            </table>
                                        </div>
                                    </div>

                                    <div id="menu2" class="tab-pane fade">
                                        <div class="table-responsive">
                                            <table class="table table-bordered">
                                                <tr>
                                                    <td class="text-center">Airline</td>
                                                    <td class="text-center">Check-in Baggage</td>
                                                    <td  class="text-center">Cabin Baggage</td>
                                                </tr>
                                                <tr>
                                                    <td class="text-center">SpiceJet (SG- 516)</td>
                                                    <td class="text-center">15kg</td>
                                                    <td class="text-center">7kg</td>
                                                </tr>
                                            </table>
                                        </div>
                                    </div>

                                    <div id="menu3" class="tab-pane fade">
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
            <div class="col-md-6">
                <!--roundtrip-flight-list-info-->
                <div class="rtflight-list-info mt-30">
                    <div class="row">
                        <div class="col-md-4">
                            <div class="travel-path-head">
                                <p class="n-margin">Delhi → Mumbai</p>
                            </div>
                        </div>

                        <div class="col-md-4">
                            <div class="travel-duration-head">
                                <p class="n-margin">Sat 30-Nov-2019</p>
                            </div>
                        </div>

                        <div class="col-md-4">
                            <div class="travel-date-head">
                                <span>
                                    <a href="#">Previous Day</a> | <a href="#">Next Day</a>
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
                            <p class="n-margin">Airline	</p>
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
                <div class="rtflight-result-list mt-20">
                    <div class="row">
                        <div class="flight-list-v2">
                            <div class="col-md-12">
                                <div class="flight-list-main">
                                    <div class="col-md-2 col-sm-2 text-center airline">
                                        <img src="assets/skyflight/images/flightIcon/goair.jpg" alt="cruise">
                                        <h6>Vistara</h6>
                                    </div>
                                    <div class="col-md-2 col-sm-2 departure text-center pt-20">
                                        <h3><i class="fa fa-plane"></i> LHR 19:00</h3>
                                        <h5 class="bold">SAT, 21 SEP</h5>
                                        <h5>Goa, IN</h5>
                                    </div>
                                    <div class="col-md-3 col-sm-3 stop-duration text-center pt-20">
                                        <div class="flight-direction">
                                        </div>
                                        <div class="stop">
                                        </div>
                                        <div class="stop-box">
                                            <span>0 Stop</span>
                                        </div>
                                        <div class="duration text-center">
                                            <span><i class="fa fa-clock-o"></i> 02h 00m</span>
                                        </div>
                                    </div>
                                    <div class="col-md-2 col-sm-2 destination text-center pt-20">
                                        <h3><i class="fa fa-plane"></i> DEL 21:00</h3>
                                        <h5 class="bold">SAT, 21 SEP</h5>
                                        <h5>New Delhi, IN</h5>
                                    </div>

                                    <div class="col-md-3 col-sm-3  text-center pt-20">
                                        <div class="booking-Price">
                                            <h3>Rs. 7089</h3>
                                        </div>
                                        <div class="booking-btn pt-10">
                                            <button class="bookbtn">Booking</button>
                                        </div>


                                    </div>
                                </div>
                            </div>
                            <div class="col-md-12">
                                <div class="flight-list-footer">
                                    <div class="col-md-6 col-sm-6 sm-invisible">
                                        <span><i class="fa fa-plane"></i> Business Class</span>
                                        <span class="refund"><i class="fa fa-undo"></i> Refundable</span>

                                    </div>
                                    <div class="col-md-6 col-sm-6 col-xs-12 clear-padding">
                                        <div class="pull-right">
                                            <a href="#">Show Details +</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="flight-details-tab">
                            <div class="col-md-12">
                                <ul class="nav nav-tabs">
                                    <li class="active"><a data-toggle="tab" href="#home">Flight Information</a></li>
                                    <li><a data-toggle="tab" href="#menu1">Fare Details</a></li>
                                    <li><a data-toggle="tab" href="#menu2">Baggage Information</a></li>
                                    <li><a data-toggle="tab" href="#menu3">Cancellation Rules</a></li>
                                </ul>

                                <div class="tab-content">
                                    <div id="home" class="tab-pane fade in active">
                                        <div class="table-responsive">
                                            <table class="table table-bordered">
                                                <thead>
                                                <tr>
                                                    <th>
                                                        <div class="tabp-finfo-fimg text-center">
                                                            <img src="assets/skyflight/images/flightIcon/goair.jpg">
                                                        </div>
                                                    </th>
                                                    <th>
                                                        <div class="tabp-finfo-fname text-center">
                                                            <p>
                                                                <b>SpiceJet</b><br>
                                                                <small>(SG- 516)</small>
                                                            </p>
                                                        </div>
                                                    </th>
                                                    <th>
                                                        <div class="tabp-finfo-fname text-center">
                                                            <p>
                                                                <b>CCU 06:55</b><br>
                                                                <small>(Sat-30Nov2019)</small>
                                                            </p>
                                                        </div>
                                                    </th>
                                                    <th>
                                                        <div class="tabp-finfo-fdur text-center">
                                                            <i class="fa fa-clock"></i><br>
                                                            <p><b>02h 50m</b></p>
                                                        </div>
                                                    </th>
                                                    <th>
                                                        <div class="tabp-finfo-fname text-center">
                                                            <p>
                                                                <b>CCU 06:55</b><br>
                                                                <small>(Sat-30Nov2019)</small>
                                                            </p>
                                                        </div>
                                                    </th>
                                                </tr>
                                                </thead>
                                            </table>
                                        </div>
                                    </div>
                                    <div id="menu1" class="tab-pane fade">
                                        <div class="table-responsive">
                                            <table class="table table-bordered">
                                                <tr>
                                                    <td class="text-center">1 x Adult</td>
                                                    <td class="text-center">Total (Base Fare)</td>
                                                    <td  class="text-center">Total Tax</td>
                                                    <td  class="text-center">Grand  Total</td>
                                                </tr>
                                                <tr>
                                                    <td class="text-center">Rs. 5048</td>
                                                    <td class="text-center">Rs. 5048</td>
                                                    <td class="text-center">Rs. 5048</td>
                                                    <td class="text-center"><b>Rs. 5048</b></td>
                                                </tr>
                                            </table>
                                        </div>
                                    </div>

                                    <div id="menu2" class="tab-pane fade">
                                        <div class="table-responsive">
                                            <table class="table table-bordered">
                                                <tr>
                                                    <td class="text-center">Airline</td>
                                                    <td class="text-center">Check-in Baggage</td>
                                                    <td  class="text-center">Cabin Baggage</td>
                                                </tr>
                                                <tr>
                                                    <td class="text-center">SpiceJet (SG- 516)</td>
                                                    <td class="text-center">15kg</td>
                                                    <td class="text-center">7kg</td>
                                                </tr>
                                            </table>
                                        </div>
                                    </div>

                                    <div id="menu3" class="tab-pane fade">
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


                <!--roundtrip-flight-result-list-heading-->
                <div class="rtflight-result-list mt-20">
                    <div class="row">
                        <div class="flight-list-v2">
                            <div class="col-md-12">
                                <div class="flight-list-main">
                                    <div class="col-md-2 col-sm-2 text-center airline">
                                        <img src="assets/skyflight/images/flightIcon/goair.jpg" alt="cruise">
                                        <h6>Vistara</h6>
                                    </div>
                                    <div class="col-md-2 col-sm-2 departure text-center pt-20">
                                        <h3><i class="fa fa-plane"></i> LHR 19:00</h3>
                                        <h5 class="bold">SAT, 21 SEP</h5>
                                        <h5>Goa, IN</h5>
                                    </div>
                                    <div class="col-md-3 col-sm-3 stop-duration text-center pt-20">
                                        <div class="flight-direction">
                                        </div>
                                        <div class="stop">
                                        </div>
                                        <div class="stop-box">
                                            <span>0 Stop</span>
                                        </div>
                                        <div class="duration text-center">
                                            <span><i class="fa fa-clock-o"></i> 02h 00m</span>
                                        </div>
                                    </div>
                                    <div class="col-md-2 col-sm-2 destination text-center pt-20">
                                        <h3><i class="fa fa-plane"></i> DEL 21:00</h3>
                                        <h5 class="bold">SAT, 21 SEP</h5>
                                        <h5>New Delhi, IN</h5>
                                    </div>

                                    <div class="col-md-3 col-sm-3  text-center pt-20">
                                        <div class="booking-Price">
                                            <h3>Rs. 7089</h3>
                                        </div>
                                        <div class="booking-btn pt-10">
                                            <button class="bookbtn">Booking</button>
                                        </div>


                                    </div>
                                </div>
                            </div>
                            <div class="col-md-12">
                                <div class="flight-list-footer">
                                    <div class="col-md-6 col-sm-6 sm-invisible">
                                        <span><i class="fa fa-plane"></i> Business Class</span>
                                        <span class="refund"><i class="fa fa-undo"></i> Refundable</span>

                                    </div>
                                    <div class="col-md-6 col-sm-6 col-xs-12 clear-padding">
                                        <div class="pull-right">
                                            <a href="#">Show Details +</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="flight-details-tab">
                            <div class="col-md-12">
                                <ul class="nav nav-tabs">
                                    <li class="active"><a data-toggle="tab" href="#home">Flight Information</a></li>
                                    <li><a data-toggle="tab" href="#menu1">Fare Details</a></li>
                                    <li><a data-toggle="tab" href="#menu2">Baggage Information</a></li>
                                    <li><a data-toggle="tab" href="#menu3">Cancellation Rules</a></li>
                                </ul>

                                <div class="tab-content">
                                    <div id="home" class="tab-pane fade in active">
                                        <div class="table-responsive">
                                            <table class="table table-bordered">
                                                <thead>
                                                <tr>
                                                    <th>
                                                        <div class="tabp-finfo-fimg text-center">
                                                            <img src="assets/skyflight/images/flightIcon/goair.jpg">
                                                        </div>
                                                    </th>
                                                    <th>
                                                        <div class="tabp-finfo-fname text-center">
                                                            <p>
                                                                <b>SpiceJet</b><br>
                                                                <small>(SG- 516)</small>
                                                            </p>
                                                        </div>
                                                    </th>
                                                    <th>
                                                        <div class="tabp-finfo-fname text-center">
                                                            <p>
                                                                <b>CCU 06:55</b><br>
                                                                <small>(Sat-30Nov2019)</small>
                                                            </p>
                                                        </div>
                                                    </th>
                                                    <th>
                                                        <div class="tabp-finfo-fdur text-center">
                                                            <i class="fa fa-clock"></i><br>
                                                            <p><b>02h 50m</b></p>
                                                        </div>
                                                    </th>
                                                    <th>
                                                        <div class="tabp-finfo-fname text-center">
                                                            <p>
                                                                <b>CCU 06:55</b><br>
                                                                <small>(Sat-30Nov2019)</small>
                                                            </p>
                                                        </div>
                                                    </th>
                                                </tr>
                                                </thead>
                                            </table>
                                        </div>
                                    </div>
                                    <div id="menu1" class="tab-pane fade">
                                        <div class="table-responsive">
                                            <table class="table table-bordered">
                                                <tr>
                                                    <td class="text-center">1 x Adult</td>
                                                    <td class="text-center">Total (Base Fare)</td>
                                                    <td  class="text-center">Total Tax</td>
                                                    <td  class="text-center">Grand  Total</td>
                                                </tr>
                                                <tr>
                                                    <td class="text-center">Rs. 5048</td>
                                                    <td class="text-center">Rs. 5048</td>
                                                    <td class="text-center">Rs. 5048</td>
                                                    <td class="text-center"><b>Rs. 5048</b></td>
                                                </tr>
                                            </table>
                                        </div>
                                    </div>

                                    <div id="menu2" class="tab-pane fade">
                                        <div class="table-responsive">
                                            <table class="table table-bordered">
                                                <tr>
                                                    <td class="text-center">Airline</td>
                                                    <td class="text-center">Check-in Baggage</td>
                                                    <td  class="text-center">Cabin Baggage</td>
                                                </tr>
                                                <tr>
                                                    <td class="text-center">SpiceJet (SG- 516)</td>
                                                    <td class="text-center">15kg</td>
                                                    <td class="text-center">7kg</td>
                                                </tr>
                                            </table>
                                        </div>
                                    </div>

                                    <div id="menu3" class="tab-pane fade">
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
                                <th colspan="5">DEL --> Mumbai</th>
                                <th colspan="5">DEL --> Mumbai</th>
                                <th colspan="2">Grand Total</th>
                            </tr>
                            </thead>

                            <tbody>
                            <tr>
                                <td><img src="assets/skyflight/images/flightIcon/goair.jpg" style="width: 32px;"></td>
                                <td>07:20</td>
                                <td>----></td>
                                <td>09:20</td>
                                <td>Grand Total</td>

                                <td><img src="assets/skyflight/images/flightIcon/goair.jpg" style="width: 32px;"></td>
                                <td>07:20</td>
                                <td>----></td>
                                <td>09:20</td>
                                <td>2h 00m<small>Non Stop</small></td>

                                <td>8,510</td>
                                <td><button class="btn-danger">Book Now</button></td>
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

<!-- jQuery Min JS -->
<script src="assets/skyflight/js/j.js"></script>

<script src="assets/js/angular.min.js"></script>	
<script src="assets/js/angular-sanitize.min.js"></script>
<script src="assets/js/jquery-ui.js"></script>
<script src="assets/js/cookieStore.js"></script>
	<script src="assets/js/alasql.min.js?version=1.0.0.2"></script>
	<script src="assets/js/xlsx.core.min.js?version=1.0.0.2"></script>
<script src="assets/scripts/angular_skyflight.js?version=1.0.5"></script>
<!-- Bootstrap Min JS -->
<script src="assets/skyflight/js/bootstrap.min.js"></script>
<!-- Owl Carousel Min JS -->
<script src="assets/skyflight/js/owl.carousel.min.js"></script>
<!-- Nice Select Min JS -->
<script src="assets/skyflight/js/jquery-ui.min.js"></script>
<!-- Main JS -->
<script src="assets/skyflight/js/main.js"></script>
<script>
    $(document).ready(function() {
        $('.owl-2').owlCarousel({
            loop: true,
            margin: 10,
            autoplay: false,
            autoplayTimeout: 3000,
            autoplaySpeed: 2000,
            responsiveClass: true,
            responsive: {
                0: {
                    items: 2,
                    nav: true
                },
                600: {
                    items: 4,
                    nav: false
                },
                1000: {
                    items: 7,
                    nav: true,
                    dots: false,
                    loop: false,
                    margin: 20
                }
            }
        })
    })
</script>


<script type="text/javascript">
    $('.collapse-icon').click(function () {
        $(this).parent().next('.checklist').slideToggle('slow');
    })
</script>

</body>
</html>