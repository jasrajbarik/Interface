package com.kodewala.Interface2;

public class Driver {
    public static void main(String[] args) {
        IBooking booking = new TrainBooking();
        booking.autoUpgrade1();
        IBooking b1 = new TrainBooking();
        b1.checkStatus();
    }
}
