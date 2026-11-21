package com.kodewala.Interface4;

public class Account implements Cloneable {
    String name;
    String address;
    String accNo;

    public Account(String _name, String _address, String _accNo) {
        super();
        this.name = _name;
        this.address =_address;
        this.accNo = _accNo;
    }

    @Override
    public Object clone() throws CloneNotSupportedException
    {
        return super.clone();
    }
}
