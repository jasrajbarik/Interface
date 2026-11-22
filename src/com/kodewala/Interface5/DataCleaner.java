package com.kodewala.Interface5;

import java.lang.ref.Cleaner;

public class DataCleaner {
    public void clean(Object obj) {
        if(obj instanceof Cleanable) {
            System.out.println("Allowed to perform the data Cleaning");
        }
        else
        {
            System.err.println("Not allowed to perform the data Cleaning");
        }
    }
}
