<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
       <%@page import="org.apache.log4j.Logger" %>
<%@page import="java.util.Map" %>
<%@page import="java.net.URL" %>
<%@page import="java.util.HashMap" %>
<%@page import="java.io.*" %>
    
    <%


	 //For Live Dynamic page


// 	 final Logger logger_log = Logger.getLogger("index.jsp");
// 	String rurl=request.getRequestURL().toString();
//  	final URL url = new URL(rurl);
// 	final String host = url.getHost();
//   	String[] domain = host.split("\\.");
//  	Map<String,String> mp=new HashMap<String, String>();
// 		mp.put("url",domain[1]);        
	   
		
     //For Local Dynamic page
	 final Logger logger_log = Logger.getLogger("index.jsp");
	String url = request.getContextPath();
	url=url.substring(1);  


	      

	%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <!-- meta tag -->
    <meta charset="utf-8">
    <title>Login</title>
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
    
    <link href="assets/css/newstyle.css" rel="stylesheet" />
    <link href="assets/css/all.min.css" rel="stylesheet" />

    <style>
        .login-panel{
            width: 100%;
            height: 100vh;
            background-image: url('assets/images/t6.jpg');
            background-repeat:no-repeat;
            background-size: 100% 100%;
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
    left: 45%;
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
    
.cus a{
	display: inline-block;
    padding: 5px 15px 5px 8px;
    background-color: #d2315e;
    margin-top: 15px;
    color: #fff;
    border-radius: 3px;
}
#myDIV{
	background-color: #fff;
    width: 300px;
    float: right;
    text-align: left;
    padding: 5px 15px;
}     
</style>    
    
        
</style>


</head>
<body ng-app="app" ng-controller="appController" style="background-color: #fff;">



<%--       <input type="hidden" id="uri" value="<%= domain[1]%>">          --%>
 		 <input type="hidden" id="uri" value="<%=url%>">   



   
<!-- Preloader Start -->

 		<!-- <div class="yourPage">
		  <div class="loader">
		  	<h2 class="lod_m">LOADING . . .</h2> 
		    <span class="l-1"></span> 
		    <span class="l-2"></span> 
		    <span class="l-3"></span> 
		    <span class="l-4"></span> 
		    <span class="l-5"></span> 
		    <span class="l-6"></span> 
		  </div>
		</div> -->

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

<div class="topbar">
	<div class="container">
		<div class="row">
			<div class="col-md-3">
				<img alt="" src="" id="logo">
			</div>
			<div class="col-md-9 text-right">
				<div class="cus">
					<a href="#" onclick="myFunction()"><i class="fas fa-headset" style="font-size: 18px;"></i> CUSTOMER  CARE</a>
				</div>
				<div id="myDIV" style="display: none;">
					<strong style="display: block;"><i class="fas fa-mobile-alt"></i><span> </span></strong>
					<br>
					<strong><i class="fas fa-envelope-open-text"></i><span> </span></strong>
				</div>
			</div>
		</div>
	</div>
</div>

<div class="login-panel">
    <!-- Contact Start -->
    <section id="rs-contact" class="rs-contact">
        <div class="container contact-page-one  pt-20">
            <div class="row">
            	<div class="col-md-7">
            		<img src="assets/images/main.png" style="margin-top: 12%;">
            	</div>
            	<div class="col-md-5">
          			<div class="form-1-contant new_login" style="margin-top: 30%;">
		                <div class="contact-form contact-form1 map-top">
		                    <div class="rs-title nw_log">
		                        <h2 class="title text-center">Get In Touch</h2>
		                    </div>
		                    <!-- <div id="form-messages"></div> -->
		                    <form  ng-submit="userLogin();">
		                        <div class="row">
		
		                            <div class="col-lg-8 col-md-8 offset-lg-2">
		                                <div class="form-field">
		                                    <input type="text" placeholder="Enter User Id"  ng-model="user.username" required>
		                                </div>
		                            </div>
		                        </div>
		
		                        <div class="row">
		
		                            <div class="col-lg-8 col-md-8 offset-lg-2">
		                                <div class="form-field">
		                                    <input type="password" placeholder="Enter Password" ng-model="user.password" required>
		                                </div>
		                            </div>
		                        </div>
		                        <div class="row">
		                            <div class="col-lg-12">
		                                <div class="form-button">
		                                    <button type="submit" class="readon">Login</button>
		                                </div>
		                            </div>
		                        </div>
		                        
		                        <div class="row">
		                            <div class="col-lg-6">
		                                <div class="form-button">
		                                    <a data-toggle="modal" data-target="#Forgot" href="#">Forgot Password ?</a>
		                                </div>
		                            </div>
		                            <div class="col-lg-6">
		                                <div class="form-button">
		                                    <a data-toggle="modal" data-target="#Registration" href="#">Registration</a>
		                                </div>
		                            </div>
		                        </div>
		                        
		                    </form>
		                </div>
		            </div>
            	</div>
            </div>
            <div class="icons">
       			<div class="container">
       				<div class="row">
       					<div class="owl-carousel owl-theme owl-1">
				            <div class="item">
				              	<img src="assets/images/icon/flight.png">
				            </div>
				            <div class="item">
				              	<img src="assets/images/icon/bus.png">
				            </div>
				            <div class="item">
				              	<img src="assets/images/icon/hotel.png">
				            </div>
				            <div class="item">
				              	<img src="assets/images/icon/rail.png">
				            </div>
				            <div class="item">
				              	<img src="assets/images/icon/aeps.png">
				            </div>
				            <div class="item">
				              	<img src="assets/images/icon/rec.png">
				            </div>
				            <div class="item">
				              	<img src="assets/images/icon/mps.png">
				            </div>
				            <div class="item">
				              	<img src="assets/images/icon/dis.png">
				            </div>
				            <div class="item">
				              <img src="assets/images/icon/mtr.png">
				            </div>
				            <div class="item">
				              <img src="assets/images/icon/pan.png">
				            </div>
				         </div>
	       			</div>
       			</div>
       		</div>
        </div>
    </section>
    <!-- Contact End -->
    
   	<div class="partenr">
    	<div class="container">
    		<div class="row">
    			<div class="col-md-12">
    				<h2>Our partner</h2>
    				<div class="owl-carousel owl-theme owl-2">
			            <div class="item">
			              	<img src="assets/images/icon/Yalogo.png">
			            </div>
			            <div class="item">
			              	<img src="assets/images/icon/elog.png">
			            </div>
			            <div class="item">
			              	<img src="assets/images/icon/11.png">
			            </div>
			            <div class="item">
			              	<img src="assets/images/icon/5.png">
			            </div>
			            <div class="item">
			              	<img src="assets/images/icon/axis.jpg">
			            </div>
			            <div class="item">
			              	<img src="assets/images/icon/hdfc.jpg">
			            </div>
			            <div class="item">
			              	<img src="assets/images/icon/bob.png">
			            </div>
			            <div class="item">
			              	<img src="assets/images/icon/sbi-logo.png">
			            </div>
			            <div class="item">
			              <img src="assets/images/icon/ind.png">
			            </div>
			            <div class="item">
			              <img src="assets/images/icon/spicejet-logo.png">
			            </div>
			            <div class="item">
			              <img src="assets/images/icon/air-logo.png">
			            </div>
			         </div>
    			</div>
    		</div>
    	</div>
    </div>
    
    <div class="fotter">
    	<div class="container">
    		<div class="row">
    			<div class="col-md-8">
    				<h4>Copyright 2021 <a target="_blank" href="http://encodigi.net.in/">Encore Digitech Pvt. Ltd.</a></h4>
    			</div>
    			<div class="col-md-4">
    				<h5>SSl secured</h5>
    				<h5>We Accept</h5>
    				<img alt="" src="assets/images/icon/3.png">
    				<img alt="" src="assets/images/icon/8.jpg">
    			</div>
    		</div>
    	</div>
    </div>
    
</div>

<!-- modernizr js -->
<script src="assets/encodigi/js/modernizr-2.8.3.min.js"></script>
<!-- jquery latest version -->
<script src="assets/encodigi/js/jquery.min.js"></script>
<script src="assets/js/angular.min.js"></script>
<script src="assets/scripts/angular_script.js?version=1.0"></script>
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
	function myFunction() {
	  var x = document.getElementById("myDIV");
	  if (x.style.display === "none") {
	    x.style.display = "block";
	  } else {
	    x.style.display = "none";
	  }
	}
</script>

<script>
   $(document).ready(function() {
     $('.owl-1').owlCarousel({
       loop: true,
       margin: 10,
       responsiveClass: true,
       responsive: {
         0: {
           items: 1,
           nav: false
         },
         600: {
           items: 3,
           nav: false
         },
         1000: {
           items: 7,
           autoplay: true,
           autoplayTimeout:1000,
           autoplaySpeed: 3000,
           nav: false,
           loop: true,
           margin: 20
         }
       }
     })
   })
 </script>
 
 <script>
   $(document).ready(function() {
     $('.owl-2').owlCarousel({
       loop: true,
       margin: 10,
       responsiveClass: true,
       responsive: {
         0: {
           items: 1,
           autoplay: true,
           nav: false
         },
         600: {
           items: 3,
           autoplay: true,
           nav: false
         },
         1000: {
           items: 8,
           autoplay: true,
           autoplayTimeout:500,
           autoplaySpeed: 500,
           nav: false,
           loop: true,
           margin: 20
         }
       }
     })
   })
 </script>

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