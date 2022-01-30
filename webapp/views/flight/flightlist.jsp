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

<div class="flight-list-page-search hidden-sm hidden-xs">
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


<div class="flight-list">
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
                                <input type="range" min="0" max="50" value="5">
                             </span>
                        </div>
                    </div>

                    <!--stopeg filter-->
                    <div class="flight-stop">
                        <div class="flight-header-name flight-stop-header">
                            <h6>Stops</h6>

                            <span class="collapse-icon">
                                <i class="fa fa-minus  mt-10 mb-10"></i>
                            </span>
                        </div>

                        <div class="checklist flight-stop-list">
                                <ul>
                                    <li>
                                        <div class="checkbox">
                                            <label><input type="checkbox" value="">Direct</label>
                                        </div>
                                    </li>

                                    <li>
                                        <div class="checkbox">
                                            <label><input type="checkbox" value="">1 Stop</label>
                                        </div>
                                    </li>

                                    <li>
                                        <div class="checkbox">
                                            <label><input type="checkbox" value="">2 Stop</label>
                                        </div>
                                    </li>


                                    <li>
                                        <div class="checkbox">
                                            <label><input type="checkbox" value="">More then 2 Stop</label>
                                        </div>
                                    </li>
                                </ul>
                            </div>
                        </div>

                    <!--airloinesd name-->
                    <div class="airlines-name">
                        <div class="flight-header-name airlines-name-header ">
                            <h6>Airlines</h6>

                            <span class="collapse-icon">
                                <i class="fa fa-minus  mt-10 mb-10"></i>
                            </span>
                        </div>

                        <div class="checklist flight-stop-list">
                            <ul>
                                <li>
                                    <div class="checkbox">
                                        <label><input type="checkbox" value="">Spice Jet - Rs.6709</label>
                                    </div>
                                </li>

                                <li>
                                    <div class="checkbox">
                                        <label><input type="checkbox" value="">Spice Jet - Rs.6709</label>
                                    </div>
                                </li>

                                <li>
                                    <div class="checkbox">
                                        <label><input type="checkbox" value="">Spice Jet - Rs.6709</label>
                                    </div>
                                </li>
                            </ul>
                        </div>
                    </div>

                    <!--fare type-->
                    <div class="fare-type">
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
                <!--owl date slider-->
                <div class="row pl-14 pr-14 hidden-sm hidden-xs">
                    <div class="price-slider mt-40">
                        <div class="owl-carousel owl-theme owl-2">
                            <div class="item">
                                <h4 class="n-margin item-date">dec 11</h4>
                                <p class="n-margin item-price">rs. 2013</p>
                            </div>

                            <div class="item">
                                <h4 class="n-margin item-date">dec 11</h4>
                                <p class="n-margin item-price">rs. 2013</p>
                            </div>

                            <div class="item">
                                <h4 class="n-margin item-date">dec 11</h4>
                                <p class="n-margin item-price">rs. 2013</p>
                            </div>

                            <div class="item">
                                <h4 class="n-margin item-date">dec 11</h4>
                                <p class="n-margin item-price">rs. 2013</p>
                            </div>

                            <div class="item">
                                <h4 class="n-margin item-date">dec 11</h4>
                                <p class="n-margin item-price">rs. 2013</p>
                            </div>

                            <div class="item">
                                <h4 class="n-margin item-date">dec 11</h4>
                                <p class="n-margin item-price">rs. 2013</p>
                            </div>

                            <div class="item">
                                <h4 class="n-margin item-date">dec 11</h4>
                                <p class="n-margin item-price">rs. 2013</p>
                            </div>


                            <div class="item">
                                <h4 class="n-margin item-date">dec 11</h4>
                                <p class="n-margin item-price">rs. 2013</p>
                            </div>


                            <div class="item">
                                <h4 class="n-margin item-date">dec 11</h4>
                                <p class="n-margin item-price">rs. 2013</p>
                            </div>


                            <div class="item">
                                <h4 class="n-margin item-date">dec 11</h4>
                                <p class="n-margin item-price">rs. 2013</p>
                            </div>

                            <div class="item">
                                <h4 class="n-margin item-date">dec 11</h4>
                                <p class="n-margin item-price">rs. 2013</p>
                            </div>


                            <div class="item">
                                <h4 class="n-margin item-date">dec 11</h4>
                                <p class="n-margin item-price">rs. 2013</p>
                            </div>

                            <div class="item">
                                <h4 class="n-margin item-date">dec 11</h4>
                                <p class="n-margin item-price">rs. 2013</p>
                            </div>


                            <div class="item">
                                <h4 class="n-margin item-date">dec 11</h4>
                                <p class="n-margin item-price">rs. 2013</p>
                            </div>

                            <div class="item">
                                <h4 class="n-margin item-date">dec 11</h4>
                                <p class="n-margin item-price">rs. 2013</p>
                            </div>

                            <div class="item">
                                <h4 class="n-margin item-date">dec 11</h4>
                                <p class="n-margin item-price">rs. 2013</p>
                            </div>

                            <div class="item">
                                <h4 class="n-margin item-date">dec 11</h4>
                                <p class="n-margin item-price">rs. 2013</p>
                            </div>

                            <div class="item">
                                <h4 class="n-margin item-date">dec 11</h4>
                                <p class="n-margin item-price">rs. 2013</p>
                            </div>


                            <div class="item">
                                <h4 class="n-margin item-date">dec 11</h4>
                                <p class="n-margin item-price">rs. 2013</p>
                            </div>


                            <div class="item">
                                <h4 class="n-margin item-date">dec 11</h4>
                                <p class="n-margin item-price">rs. 2013</p>
                            </div>


                            <div class="item">
                                <h4 class="n-margin item-date">dec 11</h4>
                                <p class="n-margin item-price">rs. 20154</p>
                            </div>

                        </div>
                    </div>
                </div>
                <!--/owl date slider-->

                <div class="flight-list">
                    <div class="flight-list-heading mt-20 mb-20  hidden-sm hidden-xs">
                        <div class="row list-heading-row">
                            <div class="col-md-2 col-sm-2 text-center">
                                <p>Airline	</p>
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
                        <div class="row flight-result-list">
                            <div class="flight-list-v2">
                                <div class="col-md-12">
                                    <div class="flight-list-main">
                                        <div class="col-md-2 col-sm-2 text-center airline">
                                            <img src="assets/skyflight/images/flightIcon/goair.jpg" alt="cruise">
                                            <h6>Vistara</h6>
                                        </div>
                                        <div class="col-md-2 col-sm-2 col-xs-4 departure text-center pt-20">
                                            <h3><i class="fa fa-plane"></i> LHR 19:00</h3>
                                            <h5 class="bold">SAT, 21 SEP</h5>
                                            <h5>Goa, IN</h5>
                                        </div>
                                        <div class="col-md-3 col-sm-3 col-xs-4 stop-duration text-center pt-20">
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
                                        <div class="col-md-2 col-sm-2 col-xs-4 destination text-center pt-20">
                                            <h3><i class="fa fa-plane"></i> DEL 21:00</h3>
                                            <h5 class="bold">SAT, 21 SEP</h5>
                                            <h5>New Delhi, IN</h5>
                                        </div>

                                        <div class="col-md-3 col-sm-3  col-xs-12  text-center pt-20">
                                            <div class="booking-Price">
                                                <h3>Rs. 7089</h3>
                                            </div>
                                            <div class="booking-btn pt-10">
                                                <button class="bookbtn">Booking</button>
                                            </div>


                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-12 col-xs-12">
                                    <div class="flight-list-footer">
                                        <div class="col-md-6 col-sm-6 sm-invisible">
                                            <span><i class="fa fa-plane"></i> Business Class</span>
                                            <span class="refund"><i class="fa fa-undo"></i> Refundable</span>
                                            <span><i class="fa fa-suitcase"></i> Free Meal</span>

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
                                <div class="row">
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
                                                        <tr>
                                                            <td class="text-center" rowspan="2">
                                                                <img src="assets/skyflight/images/flightIcon/spice.jpg">
                                                            </td>
                                                            <td class="text-center">SpiceJet</td>
                                                            <td  class="text-center">CCU 06:55</td>
                                                            <td  class="text-center"> <i class="fa fa-clock"></i></td>
                                                            <td class="text-center"><b>Grand  Total</b></td>
                                                        </tr>
                                                        <tr>
                                                            <td class="text-center">(SG- 516)</td>
                                                            <td class="text-center">(Sat-30Nov2019)</td>
                                                            <td class="text-center"><b>02h 50m</b></td>
                                                            <td class="text-center"><b>(Sat-30Nov2019)</b></td>
                                                        </tr>
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
                    </div>
                </div>
            </div>
            <!--/flight search result list-->
        </div>
    </div>
</div>


<!-- menu-container -->

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
    $('.collapse-icon').click(function () {
       $(this).parent().next('.checklist').slideToggle('slow');

    })
</script>

</body>
</html>