package com.recharge.utill;

public class TestClass {
	void sum (Object a){
		System.out.println("OBJECT");
	}
	
	void sum(String a){
		System.out.println("STRING");
	}
	
	public static void main(String[] args) {
		TestClass obj = new TestClass();
		obj.sum(null);
	}
}
