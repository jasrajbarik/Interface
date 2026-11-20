package com.kodewala.Interface2;
/*
* This class is implementing IBooking interface and must implements all the abstract
* methods of IBooking interface. (100% Contract)
* */
public class BusBooking implements IBooking {


    @Override
    public void book() {
        System.out.println("BusBooking.book");
    }

    @Override
    public void cancelBooking() {
        System.out.println("BusBooking.cancelBooking");
    }

    @Override
    public Booking checkStatus() {
        Booking booking = new Booking();
        booking.setBookingId("MMTBB256");
        booking.setStatus("Confirmed");
        return booking;
    }

    @Override
    public SearchResult search() {
        SearchResult searchResult = new SearchResult();
        return searchResult;
    }

    @Override
    public void autoUpgrade() {
        //empty method so that the interface chain doesn't break, i.e. 100% contract
    }
    public static void printTicket(String path, String content) {
        IBooking.printTicket("", "");
    }
}