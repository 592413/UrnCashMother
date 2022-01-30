package com.recharge.utill;

public class RoundNumber {
	public String splitter(String type){
	    String format="";
	        try{
	            if(type.contains(".")){
	            if( type.substring(type.lastIndexOf("."),type.length()).length()>3){
	                format=type.substring(0,type.lastIndexOf(".")+3);
//	                System.out.println(type);
	            }else{
	                format=type;
	            }
	            }else{
	                format=type;
	            }
	        }catch(Exception e){

	        }finally{
	        }
	        return format;
	    }
	public String round2(double num)
	{
	    double result = num * 100;
	    result = Math.round(result);
	    result = result / 100;
	    String rs1=String.valueOf(result);
	    if(rs1.substring(rs1.indexOf(".")+1, rs1.length()).length()<2)
	    {
	        rs1=rs1+"0";
	    }
	   // result=Double.parseDouble(rs1);
	    return rs1;
	}
	
	public double round4(double num)
	{
	    double result = num * 100;
	    result = Math.round(result);
	    result = result / 100;
	    String rs1=String.valueOf(result);
	    if(rs1.substring(rs1.indexOf(".")+1, rs1.length()).length()<2)
	    {
	        rs1=rs1+"0";
	    }
	   // result=Double.parseDouble(rs1);
	    return Double.parseDouble(rs1);
	}
	
	public String roundFigure(String number){
		String msg="";
		
		 if(number.contains(".")){
              msg=number.substring(0, number.indexOf("."));
             msg=msg+".00";
             
           }else{
        	   msg=number+".00";  
           }
		
		return msg;
	}public static double roundDouble(double number){
		double number1=number;
		
		
        double number2 = (int)Math.round(number1 * 100)/(double)100;
		
		return number2;
	}public static double roundDouble2(double number){
		double number1=number;
		
		
        double number2 = (int)Math.round(number1 * 100)/(double)100;
		
		return number2;
	}
	public String roundString(String number){
		double number1=Double.parseDouble(number);
		
		
		String number2 =String.valueOf((int)Math.round(number1 * 100)/(double)100);
		
		return number2;
	}public static String roundString2(String number){
		double number1=Double.parseDouble(number);
		
		
		String number2 =String.valueOf((int)Math.round(number1 * 100)/(double)100);
		
		return number2;
	}
public static void main(String args[]){
	System.out.println(RoundNumber.roundString2("40.065465465465464"));
}

}
