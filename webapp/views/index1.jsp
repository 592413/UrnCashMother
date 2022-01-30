<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
       <%@page import="org.apache.log4j.Logger" %>
<%@page import="java.util.Map" %>
<%@page import="java.net.URL" %>
<%@page import="java.util.HashMap" %>
<%@page import="java.io.*" %>
    
    <%


	 //For Live Dynamic page
//    final Logger logger_log = Logger.getLogger("index.jsp");
// 	String rurl=request.getRequestURL().toString();
// 	final URL url = new URL(rurl);
// 	final String host = url.getHost();
// 	String[] domain = host.split("\\.");
// 	Map<String,String> mp=new HashMap<String, String>();
// 		mp.put("url",domain[1]); 
	 
     //For Local Dynamic page
	final Logger logger_log = Logger.getLogger("index.jsp");
	String url = request.getContextPath();
	url=url.substring(1);
	%>
<!DOCTYPE html>
<html lang="zxx">

<head>
    <!-- meta tag -->
    <meta charset="utf-8">
    <title>Home</title>
    <meta name="description" content="">
    <!-- responsive tag -->
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- favicon -->
    <!-- <link rel="shortcut icon" type="image/x-icon" href="images/fav.png">-->
    <!-- bootstrap v4 css -->
    <link rel="stylesheet" type="text/css" href="assets/encodigi/css/bootstrap.min.css">
    <!-- font-awesome css -->
    <link rel="stylesheet" type="text/css" href="assets/encodigi/css/font-awesome.min.css">
    <!-- animate css -->
    <link rel="stylesheet" type="text/css" href="assets/encodigi/css/animate.css">
    <!-- hover css -->
    <link rel="stylesheet" type="text/css" href="assets/encodigi/css/hover-min.css">
    <!-- owl.carousel css -->
    <link rel="stylesheet" type="text/css" href="assets/encodigi/css/owl.carousel.css">
    <!-- off canvas css -->
    <link rel="stylesheet" type="text/css" href="assets/encodigi/css/off-canvas.css">
    <!-- flaticon css  -->
    <link rel="stylesheet" type="text/css" href="assets/encodigi/fonts/flaticon.css">
    <!-- rsmenu CSS -->
    <link rel="stylesheet" type="text/css" href="assets/encodigi/css/rsmenu-main.css">
    <!-- magnific popup css -->
    <link rel="stylesheet" type="text/css" href="assets/encodigi/css/magnific-popup.css">
    <!-- rsmenu transitions CSS -->
    <link rel="stylesheet" type="text/css" href="assets/encodigi/css/rsmenu-transitions.css">
    <!-- style css -->
    <link rel="stylesheet" type="text/css" href="assets/encodigi/css/style.css">
    <!-- switch color presets css -->
    <link id="switch_style" href="#" rel="stylesheet" type="text/css">
    <!-- responsive css -->
    <link rel="stylesheet" type="text/css" href="assets/encodigi/css/responsive.css">

    <style>
        .login-panel{
            background-color: #f1f1f1;
            width: 100%;
            height: 100vh;
        }

        .login-about{
            background-color: #0e1254;
            width: 100%;
            height: 100%;
        }
        
        .loader {
    z-index: 9999;
    position: fixed;
    top: 0px;
    width: 100%;
    background-color: #030749;
   	height: 100vh;
}

.loader > span {
  display: inline-block;
  background: #dbdbdb;
  width: 15px;
  height: 15px;
  border-radius: 5px;
  -o-border-radius: 5px;
  -ms-border-radius: 5px;
  -moz-border-radius: 5px;
  -webkit-border-radius: 5px;
  margin: 0px;
  position: fixed;
  top: 50%;
  left: -10%;
  transition: 2.89s all cubic-bezier(0.030, 0.615, 0.995, 0.415);
  -o-transition: 2.89s all cubic-bezier(0.030, 0.615, 0.995, 0.415);
  -ms-transition: 2.89s all cubic-bezier(0.030, 0.615, 0.995, 0.415);
  -moz-transition: 2.89s all cubic-bezier(0.030, 0.615, 0.995, 0.415);
  -webkit-transition: 2.89s all cubic-bezier(0.030, 0.615, 0.995, 0.415);
  z-index: 101;
}

.loader > span.jmp {
  transition: none !important;
  -o-transition: none !important;
  -ms-transition: none !important;
  -moz-transition: none !important;
  -webkit-transition: none !important;
}
.lod_m{
    position: absolute;
    top: 45%;
    left: 50%;
    transform: translate(-50px, -50px);
    color: #fff;
    letter-spacing: 5px;
}

.loader span.l-1 { background: #e74c3c; }

.loader span.l-2 { background: #e67e22; }

.loader span.l-3 { background: #f1c40f; }

.loader span.l-4 { background: #2ecc71; }

.loader span.l-5 { background: #3498db; }

.loader span.l-6 { background: #9b59b6; }
        
        
    </style>


</head>
<body class="home-three" ng-app="app" ng-controller="appController">
<%--   <input type="hidden" id="uri" value="<%= domain[1]%>">  --%>
	<input type="hidden" id="uri" value="<%=url%>">
        <!-- Preloader Start -->
        
        <div class="yourPage">
		  <div class="loader">
		  	<h2 class="lod_m">LOADING . . .</h2> 
		    <span class="l-1"></span> 
		    <span class="l-2"></span> 
		    <span class="l-3"></span> 
		    <span class="l-4"></span> 
		    <span class="l-5"></span> 
		    <span class="l-6"></span> 
		  </div>
		</div>
		
         <!-- <div class="loading">
         	
            <div class="loading-text">
            	<img src="assets/images/lodl.png"><br>
                <span class="loading-text-words">L</span>
                <span class="loading-text-words">O</span>
                <span class="loading-text-words">A</span>
                <span class="loading-text-words">D</span>
                <span class="loading-text-words">I</span>
                <span class="loading-text-words">N</span>
                <span class="loading-text-words">G</span>
                <span class="loading-text-words">.</span>
            </div>
        </div> -->
        <!-- Preloader End -->
        
        <!--Full width header Start-->
        <section class="full-width-header">
            <div class="topbar-section">
                <div class="container">
                    <div class="row align-items-center">
                        <div class="col-md-7">
                            <ul class="mail-info">
                                <li> <a href="#"><i class="glyph-icon flaticon-mail"></i>info@encodigi.net.in</a></li>
                                <li> <a href="tel:9073977682"><i class="glyph-icon flaticon-phone-call"></i> 6290820958 / 6290820972 </a></li>
                            </ul>
                        </div>
                        <div class="col-md-5">
                            <div class="right-text text-right fix">
                                <div class="get-qoute">
                                    <a href="login">Login</a>
                                </div>
                                <div class="social-link">
                                    <ul>
                                        <li><a href="#"><i class="fa fa-facebook-official"></i></a></li>
                                        <li><a href="#"><i class="fa fa-twitter"></i></a></li>
                                        <li><a href="#"><i class="fa fa-pinterest-p"></i></a></li>
                                        <li><a href="#"><i class="fa fa-linkedin"></i></a></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!--Header Start-->
            <header id="rs-header" class="rs-header header-style-3">
                <!-- Menu Start -->
                <div class="menu-area menu-sticky transparent">
                    <div class="container">
                        <div class="main-menu">
                            <div class="row align-items-center">
                                <div class="col-lg-2">
                                    <div class="logo-area sm-center">
                                        <a href="home"><img src="assets/encodigi/images/logo/logo.png" alt="logo"></a>
                                    </div>
                                </div>
                                <div class="col-lg-10 search-bar">
                                    <div class="header-mid-section display-flex-center">   
                                        <div class="right-menu-area">  
                                            <a class="rs-menu-toggle"><i class="fa fa-bars"></i>Menu</a>
                                            <nav class="rs-menu">
                                                <ul class="nav-menu">
                                            <!-- Home -->
                                                    <li><a href="home"><span data-hover="Home">Home</span></a></li>
                                            <!-- End Home --> 

                                            <!--About Start-->
                                            <li><a href="about"><span data-hover="About">About</span></a></li>
                                            <!--About End-->

                                            <!--Services Menu Start -->
                                                    <li><a href="services"><span data-hover="services">services</span></a></li>
                                            <!--Services Menu End -->

                                            <!--Projects Menu Start -->
                                                    <li><a href="http://www.edplpay.com/keypoints.php"><span data-hover="Key Point">Key Point</span></a></li>
                                            <!--Projects Menu End -->

                                            <!--Contact Menu Start-->
                                                    <li><a href="contact"><span data-hover="Contact us">Contact us</span></a></li>

                                                    <!--Contact Menu Start-->
                                                    <li><a href="login"><span data-hover="login">Login</span></a></li>
                                        </ul> <!-- //.nav-menu -->
                                            </nav> 
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- Menu End -->
            </header>
            <!--Header End-->
        </section>
        <!--Full width header End-->

        <!--Slider Section Start-->
        <div id="rs-home-slider" class="rs-home-slider home3-slider">
            <div class="slider-carousel owl-carousel">
                <div class="single-slider">
                    <img src="assets/encodigi/images/slider/aeps.jpg" alt="">
                    <div class="content-inner2">
                        <div class="container">
                            <div class="slider-text">
                                <h4 class="sl-subtitle wow fadeInDown">We Ensure</h4>
                                <h1 class="sl-title wow fadeInDown"><span>AADHAR  ENABLED 
                     </span>Payment System</h1>
                                <div class="sl-desc">
                                   supports transactions from one Aadhaar linked account to another routed through Aadhaar Enabled device
                                </div>
                                <ul class="wow fadeInUp">
                                    <li><a href="#" class="readon btn1">Contact Us</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div> <!-- //.single-slider -->
                
                <div class="single-slider">
                    <img src="assets/encodigi/images/slider/bill.jpg" alt="">
                    <div class="content-inner2">
                        <div class="container">
                            <div class="slider-text">
                                <h3 class="sl-subtitle wow fadeInDown">The Best To Pay Your</h3>
                                <h1 class="sl-title wow fadeInDown"><span>Online Utility Bill </span>Payment</h1>
                                <div class="sl-desc">
								We provides Online/Offline Simple and Quick way Bill Payment Service in India .
                                </div>
                                <ul class="wow fadeInUp">
                                    <li><a href="#" class="readon btn1">Contact Us</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div> <!-- //.single-slider -->


                <div class="single-slider">
                    <img src="assets/encodigi/images/slider/pan.jpg" alt="">
                    <div class="content-inner2">
                        <div class="container">
                            <div class="slider-text">
                                <h3 class="sl-subtitle wow fadeInDown">Get Our</h3>
                                <h1 class="sl-title wow fadeInDown"><span>Pan Card Service </span> 
                    		    </h1>
                                <div class="sl-desc">

                        			If you want to start a business or want to join a company you need Permanent Account Number
                                </div>
                                <ul class="wow fadeInUp">
                                    <li><a href="#" class="readon btn1">Contact Us</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div> <!-- //.single-slider -->

                <div class="single-slider">
                    <img src="assets/encodigi/images/slider/travel.jpg" alt="">
                    <div class="content-inner2">
                        <div class="container">
                            <div class="slider-text">
                                <h3 class="sl-subtitle wow fadeInDown">Make your Holidays Better</h3>
                                <h1 class="sl-title wow fadeInDown"><span> Use Our Holidays  </span> Package </h1>
                                <div class="sl-desc">

                        					Book  tickets online from our website and get best deals on your booking

                                 </div>
                                <ul class="wow fadeInUp">
                                    <li><a href="#" class="readon btn1">Contact Us</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div> <!-- //.single-slider -->
                
                <div class="single-slider">
                    <img src="assets/encodigi/images/slider/yud.jpg" alt="">
                    <div class="content-inner2">
                        <div class="container">
                            <div class="slider-text">
                                <h3 class="sl-subtitle wow fadeInDown">Make your Holidays Better</h3>
                                <h1 class="sl-title wow fadeInDown"><span> Flight Ticket </span> Booking </h1>
                                <div class="sl-desc">
		                            If you are looking at blank
		                            cassettes on the web, you may be very confused at<br> the
		                            difference in price.You may see some for as low as each.
                                 </div>
                                <ul class="wow fadeInUp">
                                    <li><a href="#" class="readon btn1">Contact Us</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
                
                <div class="single-slider">
                    <img src="assets/encodigi/images/slider/xdf.jpg" alt="">
                    <div class="content-inner2">
                        <div class="container">
                            <div class="slider-text">
                                <h3 class="sl-subtitle wow fadeInDown">Make your Holidays Better</h3>
                                <h1 class="sl-title wow fadeInDown"><span>Relaxing Room</span> Booking </h1>
                                <div class="sl-desc">
		                            If you are looking at blank
									cassettes on the web, you may be very confused<br> at the
									difference in price. You may see some for as low as each.
                                 </div>
                                <ul class="wow fadeInUp">
                                    <li><a href="#" class="readon btn1">Contact Us</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
              
                <div class="single-slider">
                    <img src="assets/encodigi/images/slider/gft.jpg" alt="">
                    <div class="content-inner2">
                        <div class="container">
                            <div class="slider-text">
                                <!-- <h3 class="sl-subtitle wow fadeInDown">Make your Holidays Better</h3>
                                <h1 class="sl-title wow fadeInDown"><span> Flight Ticket </span> Booking </h1>
                                <div class="sl-desc">
		                            If you are looking at blank
		                            cassettes on the web, you may be very confused at<br> the
		                            difference in price.You may see some for as low as each.
                                 </div> -->
                                <ul class="wow fadeInUp" style="margin-top: 20%;">
                                    <li><a href="#" class="readon btn1">Contact Us</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
                

            </div> <!-- //.slider-carousel -->
        </div>
        <!--Slider Section End-->

        <!-- Process Section Start-->
        <div id="rs-process-section" class="rs-process-section clearfix">
            <div class="container ps-relative">                
                <div class="row align-items-center no-gutters">
                    <div class="col-lg">
                        <div class="process-inner active">
                            <div class="process-item text-center">
                               <!-- <div class="number">
                                    01
                                </div>-->
                                <h4 class="title">
                                    24X7  <br>SUPPORT
                                </h4>
                                <div class="des white-color">
                                    Our customer service is commited to serve you 24x7 for your queries.
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg">
                        <div class="process-inner">
                            <div class="process-item text-center">
                                <!--<div class="number">
                                    02
                                </div>-->
                                <h4 class="title">
                                    EASY <br>INTEGRATION
                                </h4>
                                <div class="des white-color">
                                    We provide service which is very simple to Operate.
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg">
                       <div class="process-inner">
                            <div class="process-item text-center">
                                <!--<div class="number">
                                    03
                                </div>-->
                                <h4 class="title">
                                    FAST <br>RESPONSE
                                </h4>
                                <div class="des white-color">
                                    We provide service which is very Fast to Recharge.
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg">
                        <div class="process-inner">
                            <div class="process-item text-center">
                               <!-- <div class="number">
                                    04
                                </div>-->
                                <h4 class="title">
                                    We are <br>RELIABLE
                                </h4>
                                <div class="des white-color">
                                    We provide service which is very Reliable to use.
                                </div>
                            </div>
                        </div>
                    </div>
                </div> 
            </div><!-- .container end -->
        </div>
        <!-- Process Section End-->

        <!-- About Section Start-->
        <div id="rs-about" class="rs-about-construction pt-120 pb-100 clearfix">
            <div class="container ps-relative">                
                <div class="row align-items-center">
                    <div class="col-lg-6 mmb-60">
                        <div class="rs-title2 margin-0">
                            <span>About Us</span>
                            <h2 class="mb-25">Encore Digitech Pvt. Ltd.</h2>
                            <p class="mb-20"> The company, introduced by Mr.T.K.Dutta, having an experience over 30 years in the various segments like Automobile, Engineering, Pipes & Tubes, Office furniture, Electronic items ,IT and FMCG sector .Having a thorough geographical ,cultural & socio-economic knowledge of all over India and posses a strong goodwill in all sectors & segments of people.
                                Started his own venture in Feb 06 with processed food products and diverted to service oriented products from Jan 09..</p>
                        </div>
                        <div class="services-list">
                            <h4 class="sub-title">Why Choose Us</h4>
                            <div class="row">
                                <div class="col-md-12">
                                    <ul class="check-list">
                                        <li class="font-weight-bold">Good Performance :</li>
                                        <p>EDPL business plan envisioned the creation of kiosk bank account  point, and customer service point for e-commerce related services.</p>
                                    </ul>
                                </div>
                                <div class="col-md-12">
                                    <ul class="check-list pr-15">
                                        <li class="font-weight-bold">Fast Processing System :</li>
                                        <p>Gears up to empower rural India</p>
                                    </ul>
                                </div>
                            </div>
                            <ul class="button-area">
                                <li><a class="readon" href="#">Read More</a></li>
                            </ul>
                        </div>
                    </div>
                    <div class="col-lg-6">
                        <div class="about-left-image d-pl-30"> 
                            <img src="assets/encodigi/images/about/right_1.png" alt="">
                        </div>
                    </div>
                </div>
            </div><!-- .container end -->
        </div>
        <!-- About Section End-->

        <!-- Customer Counter Start-->
        <div class="rs-customer-counter bg5" style="background-image: url(assets/encodigi/images/bg/bg5.jpg);">
            <div class="container_fluid">
                <div class="row">
                    <div class="col-lg-8 offset-lg-4">
                        <div class="customer-inner-counter primary-bg">
                            <div class="custom-max-width">
                                <div class="rs-title2 mb-45 white-text">
                                    <span>Why We Best</span>
                                    <h2>We are a team of professionals</h2>
                                    <p>We are a team of professionals working towards the betterment of our services and the industry as well.
                                        Coming from different verticals of life, we have a combined experience of over 4 years in between us.</p>
                                </div>
                                <div class="row mb-40">
                                    <div class="col-md-4">
                                        <div class="about-service-item display-flex-center">
                                            <div class="service-icon">
                                                <i class="glyph-icon flaticon-satisfaction"></i>
                                            </div>
                                            <div class="title">
                                                24x7 Support
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-4">
                                        <div class="about-service-item display-flex-center mt-sm-30 mb-sm-30">
                                            <div class="service-icon">
                                                <i class="glyph-icon flaticon-repair-expert"></i>
                                            </div>
                                            <div class="title">
                                                Fast Response
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-4">
                                        <div class="about-service-item display-flex-center">
                                            <div class="service-icon">
                                                <i class="glyph-icon flaticon-hotel"></i>
                                            </div>
                                            <div class="title">
                                                We Are Reliable
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-4">
                                        <div class="rs-counter-list">
                                            <div class="counter-text">
                                                <div class="rs-counter">2304</div>
                                                <h5>Project Completed</h5>
                                            </div>
                                        </div>
                                    </div>
                                    <!--<div class="col-md-4">
                                        <div class="rs-counter-list mt-sm-30 mb-sm-30">
                                            <div class="counter-text">
                                                <div class="rs-counter">2304 </div>
                                                <h5>Project Completed</h5>
                                            </div>
                                        </div>
                                    </div>-->
                                    <div class="col-md-4">
                                        <div class="rs-counter-list">
                                            <div class="counter-text">
                                                <div class="rs-counter">3498 </div>
                                                <h5>Happy Clients</h5>
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
        <!-- Customer Counter End-->

        <!-- Quality Services Section Start-->
        <div class="rs-quality-services negative-margin bg6 pt-170 pb-80" style="background-image: url(assets/encodigi/images/bg/bg6.jpg);">
            <div class="container">
                <div class="row align-items-center mb-40">
                    <div class="col-md-9">
                        <div class="rs-title2 mb-0">
                            <span> Services</span>
                            <h2 class="white-color">Our quality services</h2>
                        </div>
                    </div>
                    <div class="col-md-3">
                        <div class="view-all-service text-right mb-left">
                            <a class="readon white-color" href="#">View all Services</a>
                        </div>
                    </div>
                </div>

                <div class="rs-carousel owl-carousel" data-loop="true" data-items="4" data-margin="20" data-autoplay="true" data-autoplay-timeout="7000" data-smart-speed="2000" data-dots="false" data-nav="false" data-nav-speed="false" data-mobile-device="1" data-mobile-device-nav="false" data-mobile-device-dots="false" data-ipad-device="2" data-ipad-device-nav="false" data-ipad-device-dots="false" data-ipad-device2="2" data-ipad-device-nav2="false" data-ipad-device-dots2="false" data-md-device="4" data-md-device-nav="false" data-md-device-dots="false">

                    <div class="service-items">
                        <div class="service-content">
                            <!--<div class="service-icon">
                                <i class="glyph-icon flaticon-helmet"></i>
                            </div>-->
                            <h4 class="title"><a href="#">Domestic Money Transfer</a></h4>
                            <p>
                                money transfer by Direct bank, instant money transfer to beneficiary Account, Money transfer any time .....

                            </p>
                            <a class="view-details" href="services.html">View Details</a>
                        </div>
                    </div>

                    <div class="service-items">
                        <div class="service-content">
                           <!-- <div class="service-icon">
                                <i class="glyph-icon flaticon-gear"></i>
                            </div>-->
                            <h4 class="title"><a href="#">Pan Card Agency</a></h4>
                            <p>
                                To start a business or want to join a company you need Permanent Account Number which gives individual the approval ....

                            </p>
                            <a class="view-details" href="services.html">View Details</a>
                        </div>
                    </div>

                    <div class="service-items">
                        <div class="service-content">
                           <!-- <div class="service-icon">
                                <i class="glyph-icon flaticon-hard-hat"></i>
                            </div>-->
                            <h4 class="title"><a href="#">AePS Service</a></h4>
                            <p>We intends to provide AePS service across the all over India. We have the best commission structure among AePS service in....</p>
                            <a class="view-details" href="services.html">View Details</a>
                        </div>
                    </div>

                   <div class="service-items">
                        <div class="service-content">
                            <!--<div class="service-icon">
                                <i class="glyph-icon flaticon-help"></i>
                            </div>-->
                            <h4 class="title"><a href="#">Domestic Money Transfer</a></h4>
                            <p>
                                money transfer by Direct bank, instant money transfer to beneficiary Account, Money transfer any time .....

                            </p>
                            <a class="view-details" href="services.html">View Details</a>
                        </div>
                    </div>

                    <div class="service-items">
                         <div class="service-content">
                             <!-- <div class="service-icon">
                                 <i class="glyph-icon flaticon-maintenance-1"></i>
                             </div> -->
                             <h4 class="title"><a href="#">Micro atm</a></h4>
                             <p>
                             	Mini ATMs are actually modified point of sales terminals (card swipe machine)through which
                               	a bank can remotely connect to the bank's core banking system using his debit card.
                       		</p>
                             <a class="view-details" href="#">View Details</a>
                         </div>
                     </div>

                     <div class="service-items">
                         <div class="service-content">
                             <!-- <div class="service-icon">
                                 <i class="glyph-icon flaticon-gear"></i>
                             </div> -->
                             <h4 class="title"><a href="#">Flight Ticket booking</a></h4>
                             <p>
                             	Book flight tickets! Use map view, search everywhere tool for 
                               	cheapest flight offers. Compare air ticket prices from & more.
                              </p>
                             <a class="view-details" href="#">View Details</a>
                         </div>
                     </div>
                     
                      <div class="service-items">
                         <div class="service-content">
                             <!-- <div class="service-icon">
                                 <i class="glyph-icon flaticon-gear"></i>
                             </div> -->
                             <h4 class="title"><a href="#">Hotel Booking</a></h4>
                             <p>
                             	If you are looking at blank on the you may be very at the
								difference in price. You may see some for as low as each.
                              </p>
                             <a class="view-details" href="#">View Details</a>
                         </div>
                     </div>
                     
                </div>
            </div>
        </div>
        <!-- Quality Services Section End-->


        <!-- Project Section Start-->
        <div class="rs-project-section2 sec-spacer2">
            <div class="container ps-relative z-index-1">
                <div class="row align-items-center mb-40">
                    <div class="col-md-9">
                        <div class="rs-title2 mb-0">
                            <span>Utility Services</span>
                            <h2>Our complete projects</h2>
                        </div>
                    </div>
                    <div class="col-md-3">
                        <div class="view-all-service text-right mb-left">
                            <a class="readon" href="#">View all Projects</a>
                        </div>
                    </div>
                </div>
                 <div class="rs-carousel owl-carousel" data-loop="true"
                      data-items="3" data-margin="30" data-autoplay="true" data-autoplay-timeout="9000"
                      data-smart-speed="2000" data-dots="false" data-nav="false" data-nav-speed="false"
                      data-mobile-device="1" data-mobile-device-nav="false" data-mobile-device-dots="false"
                      data-ipad-device="2" data-ipad-device-nav="false" data-ipad-device-dots="false" data-ipad-device2="2"
                      data-ipad-device-nav2="false" data-ipad-device-dots2="false" data-md-device="3" data-md-device-nav="false"
                      data-md-device-dots="false">

                    <div class="project-item">
                        <div class="project-image">
                            <img src="assets/encodigi/images/project/bus.jpg" alt="">
                            <div class="p-icon">
                                <a href="http://www.edplpay.com/bus-ticket-booking-agency-provider.php"><i class="glyph-icon flaticon-next"></i></a>
                            </div>
                        </div>

                        <div class="project-content text-center">
                            <h4 class="title">
                                <span>Bus Ticket Booking </span>
                                <a href="http://www.edplpay.com/bus-ticket-booking-agency-provider.php">See in details</a>
                            </h4>
                        </div>
                        
                    </div>

                    <div class="project-item">
                        <div class="project-image">
                            <img src="assets/encodigi/images/project/flight.jpg" alt="">
                            <div class="p-icon">
                                <a href="http://www.edplpay.com/air-ticket-booking-agency-provider.php"><i class="glyph-icon flaticon-next"></i></a>
                            </div>
                        </div>

                        <div class="project-content text-center">
                            <h4 class="title">
                                <span>Air Ticket Booking</span>
                                <a href="http://www.edplpay.com/air-ticket-booking-agency-provider.php">See in details</a>
                            </h4>
                        </div>
                    </div>

                    <div class="project-item">
                        <div class="project-image">
                            <img src="assets/encodigi/images/project/train.jpg" alt="">
                            <div class="p-icon">
                                <a href="http://www.edplpay.com/irctc-agency-provider.php"><i class="glyph-icon flaticon-next"></i></a>
                            </div>
                        </div>

                        <div class="project-content text-center">
                            <h4 class="title">
                                <span>IRCTC Agency Provider</span>
                                <a href="http://www.edplpay.com/irctc-agency-provider.php">See in details</a>
                            </h4>
                        </div>
                    </div>

                    <div class="project-item">
                        <div class="project-image">
                            <img src="assets/encodigi/images/project/hotel.jpg" alt="">
                            <div class="p-icon">
                                <a href="http://www.edplpay.com/hotel-booking-agency-provider.php">
                                    <i class="glyph-icon flaticon-next"></i></a>
                            </div>
                        </div>

                        <div class="project-content text-center">
                            <h4 class="title">
                                <span>Hotel Booking Agency</span>
                                <a href="http://www.edplpay.com/hotel-booking-agency-provider.php">See in details</a>
                            </h4>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Project Section End-->

        <!-- Newsletter Start -->
        <div id="newsletter-section" class="newsletter-section pt-95 pb-170 bg7" style="background-image: url(assets/encodigi/images/bg/bg7.jpg);">
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-xl-8 col-lg-8"> 
                        <div class="subscribe-inner">
                            <div class="subscribe-text text-center">
                                <span class="sub-title">Are you looking for someone to help?</span>
                                <h3 class="title">Or any help call us : <a href="#"><br>6290820958 / 6290820972
                                </a></h3>
                               <div class="contact-info">
                                    <span><i class="fa fa-envelope-open-o"></i> <a href="#">info@encodigi.net.in</a></span>
                                   <!-- <span><i class="fa fa-clock-o"></i> Mon  Fri 08:00-15:00</span>-->
                                </div>
                            </div>  
                           <!-- <div class="subscribe-form">
                                <h2>Subscribe to Newsletter</h2>
                                <form class="news-form">
                                    <input type="text" class="form-input" placeholder="Email Address">
                                    <button type="submit" class="form-button readon">
                                        Subscribe Now
                                    </button>
                                </form> 
                            </div> -->
                        </div>                    
                    </div>
                </div>
            </div>
        </div>
        <!-- Newsletter End -->

  

        <!-- RS CTA Section Start -->
        <div class="rs-cta-section primary-bg">
            <div class="container">
                <div class="row align-items-center">

                    <div class="col-lg-9">
                        <div class="cta-title mmb-30">
                            <h2 class="white-color mb-0">Let's talk about your project</h2>
                        </div>
                    </div>

                    <div class="col-lg-3">
                        <div class="read-more text-right">
                            <a class="border-btn white-color readon" href="contact.html">Contact Us</a>
                        </div>
                    </div>

                </div>
            </div>
        </div>
        <!-- RS CTA Section End -->

        <!-- Footer Start -->
        <footer id="rs-footer" class="rs-footer pt-70">
            <div class="footer-top">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-3 single-footer-column mb-md-30">
                            <div class="about-widget">
                                <h5 class="footer-title">About Encore</h5>
                                <div class="footer-info">
                                    <p class="footer-desc white-color" style="text-align: justify">
                                        Welcome to a new breed of opportunity where you can participate in the most rewarding
                                        and profitable industries today where you can achieve an unprecedented freedom and build a very lucrative enterprise.
                                    </p>


                                </div>                                
                                <ul class="social-links">
                                    <li><a href="#"><i class="fa fa-facebook-official"></i></a></li>
                                    <li><a href="#"><i class="fa fa-twitter"></i></a></li>
                                    <li><a href="#"><i class="fa fa-pinterest-p"></i></a></li>
                                    <li><a href="#"><i class="fa fa-linkedin"></i></a></li>
                                </ul>
                            </div>
                        </div>

                        <div class="col-lg-3 single-footer-column">
                            <h5 class="footer-title">Contact Info</h5>
                            <ul class="footer-address">

                                <li class="align-items-center display-flex">
                                    <div class="icon-tag">
                                        <i class="fa fa-map-marker"></i> 
                                    </div>
                                    <div class="info">
                                        Office:  1 British India Street , Flat no. 109 , Kolkata : 700069
                                    </div>
                                </li>

                                <li class="align-items-center display-flex">
                                    <div class="icon-tag">
                                        <i class="fa fa-phone"></i> 
                                    </div>
                                    <div class="info">
                                        <a href="#">6290820962 / 6290820950 / 6290820955 </a><br>
                                        
                                    </div>
                                </li>

                                <li class="align-items-center display-flex">
                                    <div class="icon-tag">
                                        <i class="fa fa-envelope-o"></i> 
                                    </div>
                                    <div class="info">
                                        <a href="#">info@encodigi.net.in</a><br>
                                    </div>
                                </li>

                            </ul>
                        </div>

                        <div class="col-lg-3 single-footer-column mb-md-30">
                            <div class="footer-menu">
                                <h5 class="footer-title">Our Services</h5>
                                <ul>
                                    <li><a href="http://www.edplpay.com/domestic-money-transfer-agency-provider.php">Domestic Money Transfer</a></li>
                                    <li><a href="http://www.edplpay.com/electric-bill-payment-agency-provider.php">Electric Bill Payment</a></li>
                                    <li><a href="http://www.edplpay.com/bus-ticket-booking-agency-provider.php">Bus Ticket Booking</a></li>
                                    <li><a href="http://www.edplpay.com/air-ticket-booking-agency-provider.php">Air Ticket Booking</a></li>
                                    <li><a href="http://www.edplpay.com/hotel-booking-agency-provider.php">Hotel Booking</a></li>
                                </ul>
                            </div>
                        </div>

                        <div class="col-lg-3 single-footer-column mb-md-30">
                            <div class="footer-menu">
                                <h5 class="footer-title">Our Services</h5>
                                <ul>
                                    <li><a href="http://www.edplpay.com/irctc-agency-provider.php">IRCTC Agency Provid</a></li>
                                    <li><a href="http://www.edplpay.com/microATM-agency-provider.php">Micro ATM Agency Provider</a></li>
                                    <li><a href="http://www.edplpay.com/pancard-agency-provider.php">Pan Card Agency Provider</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="footer-bottom">
                <div class="container">                    
                    <div class="row align-items-center">
                        <!--<div class="col-md-5">
                            <div class="footer-logo">
                               <a href="index.html"><img src="images/logo-white.png" alt="logo"></a>
                            </div>
                        </div>-->
                        <div class="col-md-7">
                            <div class="copyright text-center">
                                <p>&copy; copyrights 2019 Encore Digitech Pvt. Ltd all rights reserved.</p>
                            </div>
                        </div>
                        <div class="col-md-5">
                            <div class="copyright text-center">
                                <p>Design By :<a target="blank" href="http://skypointindia.com/"> skypointindia.com</a></p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </footer>
        <!-- Footer End -->

      
        <!-- Scrool to Top Start -->
        <div id="scrollUp">
            <i class="fa fa-angle-up"></i>
        </div> 
        <!-- Scrool to Top End -->


<!-- modernizr js -->
<script src="assets/encodigi/js/modernizr-2.8.3.min.js"></script>
<!-- jquery latest version -->
<script src="assets/encodigi/js/jquery.min.js"></script>
<script src="assets/js/angular.min.js"></script>
<script src="assets/scripts/angular_scriptindex.js?version=1.0"></script>
<!-- Skill bar js -->
<script src="assets/encodigi/js/skill.bars.jquery.js"></script>
<!-- counter top js -->
<script src="assets/encodigi/js/jquery.counterup.min.js"></script>
<script src="assets/encodigi/js/waypoints.min.js"></script>
<!-- bootstrap js -->
<script src="assets/encodigi/js/bootstrap.min.js"></script>
<!-- owl.carousel js -->
<script src="assets/encodigi/js/owl.carousel.min.js"></script>
<!-- magnific popup -->
<script src="assets/encodigi/js/jquery.magnific-popup.min.js"></script>
<!-- slick js -->
<script src="assets/encodigi/js/slick.min.js"></script>
<!-- isotope.pkgd.min js -->
<script src="assets/encodigi/js/isotope.pkgd.min.js"></script>
<!-- imagesloaded.pkgd.min js -->
<script src="assets/encodigi/js/imagesloaded.pkgd.min.js"></script>
<!-- wow js -->
<script src="assets/encodigi/js/wow.min.js"></script>
<!-- rsmenu js -->
<script src="assets/encodigi/js/rsmenu-main.js"></script>
<!-- plugins js -->
<script src="assets/encodigi/js/plugins.js"></script>
<!-- Contact js -->
<script src="assets/encodigi/js/contact.form.js"></script>
<!-- main js -->
<script src="assets/encodigi/js/main.js"></script>

<script>
jQuery("document").ready(function($){
	var _pageWidth = $("body").outerWidth();
	var _timing = _pageWidth + 3500;
  	$(".loader span").each(function (i) {
		    // store the item around for use in the 'timeout' function
		    var _item = $(this); 
		    // execute this function sometime later:
		    setTimeout(function($) { 
		      _item.removeClass("jmp");
		      _item.css({"left": '110%'});
		      /* console.log("loop"); */
		    }, 180*i); //move each dot one after the other, transition handled by CSS
		    setTimeout(function($) { 
		      _item.addClass("jmp");
		      _item.css({"left": '-10%'});
		      /* console.log("de-loop"); */
		    }, 3000 + 180*i); //move each dot one back to start in order, transition removed via <.jmp> class
		}); //RUN ONCE OUT OF LOOP, AVOID DELAY
  
	$("window").resize(function(){
		var _pageWidth = $("body").outerWidth();
		var _timing = _pageWidth + 3500;
	});
	  
	  
	var _pageLoader = setInterval(function(){
		$(".loader span").each(function (i) {
		    // store the item around for use in the 'timeout' function
		    var _item = $(this); 
		    // execute this function sometime later:
		    setTimeout(function($) { 
		      _item.removeClass("jmp");
		      _item.css({"left": '110%'});
		      /* console.log("loop"); */
		    }, 180*i); //move each dot one after the other, transition handled by CSS
		    setTimeout(function($) { 
		      _item.addClass("jmp");
		      _item.css({"left": '-10%'});
		      /* console.log("de-loop"); */
		    }, 3000 + 180*i); //move each dot one back to start in order, transition removed via <.jmp> class
		});				
	}, _timing);
});
</script>

</body>

</html>