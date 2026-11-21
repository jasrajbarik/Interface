package com.kodewala.Interface3;

public class Driver {
    public static void main(String[] args) {
        IAccount account = (accNO, name) -> {
            System.out.println("Opening Account....for "+name);
            return "Account Opened";
        };
      String result= account.openAccount("5845002445","Jasraj");
    }
}
//without implementing the interface without implementing

