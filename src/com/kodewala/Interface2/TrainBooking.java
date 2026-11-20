package com.kodewala.Interface2;

public class TrainBooking implements IBooking {

    @Override
    public void book() {
        System.out.println("TrainBooking.book");
    }

    @Override
    public void cancelBooking() {
        System.out.println("TrainBooking.cancelBooking");
    }

    @Override
    public Booking checkStatus() {
        return null;
    }

    @Override
    public SearchResult search() {
        return null;
    }

    @Override
    public void autoUpgrade() {
    //code to auto-upgrade...100 lines of code
        System.out.println("TrainBooking.autoUpgrade");
    }
    @Override
    public void autoUpgrade1()
    {
        System.out.println("TrainBooking.autoUpgrade1.....default method");
    }
    //static method
    void printTicket(String path, String contents) {
        IBooking.printTicket("","");
    }
}
