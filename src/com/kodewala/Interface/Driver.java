package com.kodewala.Interface;

public class Driver {
    public static void main(String[] args) {
        //We cannot create object of interface , so it will not be having constructor
        //But interface can refer the object of child class
        IDiscount discount = new FlightBooking();
        int finalFare = discount.calculateDiscount(2000,10);
        System.out.println("The Final Fare is: "+finalFare);
    }
}
