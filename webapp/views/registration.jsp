<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%--   
    <%@page import="com.recharge.model.User"%>
    <%	
	User user = (User) session.getAttribute("UserDetails");	
%> --%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
	<title>registration</title>
	<link rel="icon" href="#" type="image/x-icon">
	<link href="https://fonts.googleapis.com/css?family=Roboto:400,700&subset=latin,cyrillic-ext" rel="stylesheet" type="text/css">
	<link href="assets/css/material-icons.css" rel="stylesheet">
	<link href="assets/css/bootstrap.css" rel="stylesheet">
	<link href="assets/css/waves.css" rel="stylesheet" />
	<link href="assets/css/animate.css" rel="stylesheet" />
	<link href="assets/css/style.css" rel="stylesheet">
	<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
	<link href="assets/css/bootstrap-datepicker.min.css" rel="stylesheet" />
	<link href="assets/css/helper.css" rel="stylesheet" />
	
	
	<script src="assets/js/jquery.min.js"></script> 

	<script src="assets/js/jquery-1.11.3.min.js"></script>

	<script	src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.9.0/moment-with-locales.min.js"></script>
	<script	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/3.1.3/js/bootstrap-datetimepicker.min.js"></script>
	
	<style type="text/css">
	.floatMessage {
		position: absolute;
		z-index: 999999;
		left: 40%;
		top: 29%;
		width: 27%;
	}
	textarea {
    overflow: hidden;
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
	</style>
</head>

<body class="registar-page" ng-app="app" ng-controller="appController">	
	<div class="page-loader-wrapper" ng-show="loader" >
		<div class="loader">
			<div class="preloader"><img alt="" src="assets/images/loader.gif"></div>
			<p>Please wait...</p>
		</div>
	</div>
	
	<alert-message alert="alertMessage"></alert-message>
    <div class="login-box">
        <div class="logo">
            <a href="javascript:void(0);">Registration</a>
        </div>
        <div class="card">
            <div class="body">
                <form>
                <fieldset>
                <legend>User Type Selection <%-- <%if (!(session.getAttribute("UserDetails")).equals("")) { %><span class="pull-right"><a href="home">Go Back To Home</a></span><%} %> --%></legend>
                	  <div class="form-line">
                    	<label for="firstName" class="col-sm-3 control-label">Select User Type:</label>
                    	<div class="col-sm-9">
	                     	<select class="form-control " ng-model="regi.userType">
								<option value="">-- Please select User Type--</option>
								<option>White Level</option>
								<option>Super Distributor</option>
								<option>Distributor</option>
								<option>Retailer</option>
							</select>
                   		</div>
               		</div>
                  </fieldset>
                  <fieldset>
                <legend>User Details</legend>  
                    <div class="input-group">
                        <span class="input-group-addon">
                          
                        </span>
                        	<!--  <label>Name</label> -->
                            <!-- <input type="text" class="form-control" name="username" ng-model="user.username" placeholder="Username" autofocus maxlength="10"> -->
                            
                            
                          
                    </div>
                            <div class="form-line">
                    		<label for="firstName" class="col-sm-3 control-label">Full Name</label>
                    			<div class="col-sm-9">
                        			<input type="text" ng-model="regi.UserName" placeholder="Full Name" class="form-control" autofocus>
                   				 </div>
               			 	</div>
               			 	<div class="form-line">
                    		<label for="firstName" class="col-sm-3 control-label">Father's Name</label>
                    			<div class="col-sm-9">
                        			<input type="text" ng-model="regi.UFatherName" placeholder="Father's Name" class="form-control" autofocus>
                   				 </div>
               			 	</div>
               			 		<div class="form-line">
                    		<label for="firstName" class="col-sm-3 col-md-3 control-label">Address</label>
                    			<div class="col-sm-9 col-md-9">
                        			<textarea  type="text" ng-model="regi.Address" placeholder="Address" class="form-control" autofocus></textarea>
                   				 </div>
               			 	</div>
               			 	  <div class="form-line">
                    		<label for="firstName" class="col-sm-3 control-label">District</label>
                    			<div class="col-sm-9">
                        			<input type="text" ng-model="regi.district" placeholder="District" class="form-control" autofocus>
                   				 </div>
               			 	</div>
               			 	  <div class=" form-line">
                    		<label for="firstName" class="col-sm-3 control-label">State</label>
                    			<div class="col-sm-9 ui-widget">
                        			<input type="text" id="" ng-model="regi.state"  placeholder="Enter state" class="tags form-control" >
                   				 </div>
               			 	</div>
               			 	            			 	
               			 	  <div class="form-line">
                    		<label for="firstName" class="col-sm-3 control-label">Pin</label>
                    			<div class="col-sm-9">
                        			<input type="text" ng-model="regi.pin" placeholder="Enter Pin" class="form-control" autofocus>
                   				 </div>
               			 	</div>
               			 	  <div class="form-line">
                    		<label for="firstName" class="col-sm-3 control-label">Block</label>
                    			<div class="col-sm-9">
                        			<input type="text" ng-model="regi.block" placeholder="Enter Block" class="form-control" autofocus>
                   				 </div>
               			 	</div>
               			 	  <div class="form-line">
                    		<label for="firstName" class="col-sm-3 control-label">Mail Id</label>
                    			<div class="col-sm-9">
                        			<input type="text" ng-model="regi.mail_id" placeholder="E-mail Id" class="form-control" autofocus>
                   				 </div>
               			 	</div>
               			 	  <div class="form-line">
                    		<label for="firstName" class="col-sm-3 control-label">Mobile Number</label>
                    			<div class="col-sm-9">
                        			<input type="text" ng-model="regi.mobile" placeholder="Mobile no" class="form-control" ng-keypress = "filterValue($event);" maxlength="10" >
                   				 </div>
               			 	</div>               			 	
			                <div class="form-line">
			                    <label for="birthDate" class="col-sm-3 control-label">Date of Birth</label>
			                    <div class="col-sm-9">
			                        <input type="text" id="dp43"  ng-model="regi.dob" placeholder="DOB" class="form-control" autofocus>
			                    </div>
			                </div>
			                 <div class="form-line">
                    		<label for="firstName" class="col-sm-3 control-label"> Qualification</label>
                    			<div class="col-sm-9">
                        			<input type="text" ng-model="regi.qualification" placeholder=" Qualification" class="form-control" autofocus>
                   				 </div>
               			 	</div>
               			 	 <div class="form-line">
                    		<label for="firstName" class="col-sm-3 control-label"> Shop/Office Name</label>
                    			<div class="col-sm-9">
                        			<input type="text" ng-model="regi.office_name" placeholder="Shop/Office Name & Address" class="form-control" autofocus>
                   				 </div>
               			 	</div>
               		 </fieldset>
               		 <fieldset>
			              <legend>Bank Details</legend>   
                       			 <div class="form-line">
                    		<label for="firstName" class="col-sm-3 control-label">Bank name</label>
                    			<div class="col-sm-9">
                        			<input type="text" ng-model="regi.bank_name" placeholder="name of your bank" class="form-control" autofocus>
                   				 </div>
               			 	</div>
			                 <div class="form-line">
                    		<label for="firstName" class="col-sm-3 control-label">Branch Name</label>
                    			<div class="col-sm-9">
                        			<input type="text" ng-model="regi.branch_name" placeholder="Branch of your bank" class="form-control" autofocus>
                   				 </div>
               			 	</div>
               			 	 <div class="form-line">
                    		<label for="firstName" class="col-sm-3 control-label">IFSC Code</label>
                    			<div class="col-sm-9">
                        			<input type="text" ng-model="regi.ifsc" placeholder=" your bank IFSC code " class="form-control" autofocus>
                   				 </div>
               			 	</div>
               			 	 <div class="form-line">
                    		<label for="firstName" class="col-sm-3 control-label">Bank a/c no.</label>
                    			<div class="col-sm-9">
                        			<input type="text" ng-model="regi.account_no" placeholder=" your bank account number" class="form-control" autofocus>
                   				 </div>
               			 	</div>
                    </div>
                  
                    </fieldset>
                    <div class="row m-l-0 m-r-0">
                    	
                    	 <div class="col-xs-4 p-l-10 p-r-10 ">
                            <button class="btn btn-block bg-pink waves-effect" type="reset">Reset</button>
                        </div>
                        <div class="col-xs-4 p-l-10 p-r-10">
                            <button class="btn btn-block bg-pink waves-effect" type="submit" ng-click="addEnquery(regi);">Submit</button>
                        </div>
                        <div class="col-xs-4 p-l-10 p-r-10">
                            <button class="btn btn-block bg-pink waves-effect" type="submit" ng-click="back();">BACK</button>
                        </div>
                        
                    </div> 
                                    
                </form>
            </div>
        </div>
    </div>
    
    <!-- Forget Password Modal -->
	<div class="modal fade-scale" id="forgetPassword" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	  <div class="modal-dialog" role="document" style="left: 9%;top: 20%;">
	    <div class="modal-content" style="width: 60% !important;">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="myModalLabel">Forget Password</h4>
	      </div>
	      <div class="modal-body">
	      <div class="row clearfix">
	       <div class="input-group">
                 <span class="input-group-addon">
                     <i class="material-icons">phone</i>
                 </span>
                 <div class="form-line">
                     <input type="text" class="form-control" ng-model="sendPass.mobile" placeholder="Enter Your Phone Number" ng-keypress = "filterValue($event);" maxlength="10" >
                 </div>
             </div>
             </div>
         <div class="row clearfix">
         <div class="col-xs-4 col-md-offset-6">
            	<button class="btn btn-block bg-blue waves-effect" type="submit" data-dismiss="modal" ng-click = "forgotPassword(sendPass)" >Submit</button>
          </div>          
         </div>
	      </div>
	    </div>
	  </div>
	</div>

   <script src="assets/js/angular.min.js"></script>
   <script src="assets/js/bootstrap.js"></script>
   <script src="assets/js/autocompletedata.js" type="text/javascript"></script>
   <script src="assets/js/jquery-ui.js"></script> 
   <script src="assets/js/bootstrap-datepicker.min.js"></script>
   <script src="assets/js/datepicker-date.js" type="text/javascript"></script>
    
	<script src="assets/scripts/angular_script.js?version=1.0"></script>
    <script src="assets/js/admin.js"></script>
</body>

</html>