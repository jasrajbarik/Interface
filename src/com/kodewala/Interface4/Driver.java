package com.kodewala.Interface4;

public class Driver {
    public static void main(String[] args) throws CloneNotSupportedException {

        Account acc = new Account("Jasraj Barik","SLC Royal Colive","658596266565");
        Account clonedObj = (Account) acc.clone();
        System.out.println(clonedObj.address);

    }
}
