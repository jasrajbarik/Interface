package com.kodewala.Interface1;

public class BusBooking implements IDiscount {
    @Override
    public int calculateDiscount(int amount, int rate) {
        return amount;
    }

    @Override
    public void calculateDiscountTax(int tax, int maxTax) {
        System.out.println("BusBooking.calculateDiscountTax");
    }

}
