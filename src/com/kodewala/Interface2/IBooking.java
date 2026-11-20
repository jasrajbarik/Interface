package com.kodewala.Interface2;
/*
* in 2010 - MMT
*/
public interface IBooking {
    //what to do?
    public void book();
    public void cancelBooking();
    public Booking checkStatus();
    public SearchResult search();
    // In JAVA 7 all the methods in interface are public and abstract

    //In 2020 let say IRCTC introduced auto-upgrade
    public void autoUpgrade(); //except TrainBooking, all other class will keep this method empty
    //this was the problem till java 7

    //optional-2020
    public default void autoUpgrade1() //to some of the classes
    {
        //500 lines (checking status, connecting to DB , upgrading)
        System.out.println("IBooking.autoUpgrade1");

    }

    //optional-2020
    public default void sendNotification() {
        connecttoDB();
        //200 lines (connect to DB, get user details and send notification)
        System.out.println("IBooking.sendNotification");
    }

    private void connecttoDB() {
        System.out.println("IBooking.connecttoDB");
    }
    //static method
    public static void printTicket(String path, String contents) {
        //200 lines --> printing the ticket
        System.out.println("Printing Tickets...Static method...");
    }
}