package com.kodewala.Interface;
//java version 1.7 or before
public interface IDiscount {
    int MAX_VALUE =1000; //by default public static and final
   public abstract int calculateDiscount(int amount, int rate); //abstract --> idea or thought
    public abstract void calculateDiscountTax(int tax, int maxTax);
}
