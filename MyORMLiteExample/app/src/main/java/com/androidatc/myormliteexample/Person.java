package com.androidatc.myormliteexample;

import com.j256.ormlite.field.DatabaseField;

public class Person {
    @DatabaseField(generatedId = true)
    private int accountId;
    @DatabaseField
    private String name;
    public int getAccountId(){
        return accountId;
    }
    public void setAccountId(int accountId){
        this.accountId=accountId;
    }
    public String getName(){
        return name;
    }
    public void setName(String firstName)
    {
        this.name=firstName;
    }
}
