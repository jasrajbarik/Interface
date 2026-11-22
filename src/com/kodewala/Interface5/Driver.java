package com.kodewala.Interface5;

public class Driver {
    public static void main(String[] args) {

        User user = new User("Jasrajbarik","jas14252");
        DataCleaner dataCleaner = new DataCleaner();
        //cleaning the user object....
        dataCleaner.clean(user);

    }
}
