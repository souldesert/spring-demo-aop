package ru.voskhod.aopdemo.dao;

import org.springframework.stereotype.Component;
import ru.voskhod.aopdemo.Account;

import java.util.ArrayList;
import java.util.List;

@Component
public class AccountDAO  {

    private String name;
    private String serviceCode;

    // add a new method called findAccounts()
    public List<Account> findAccounts() {
        List<Account> accounts = new ArrayList<>();

        // create sample accounts

        accounts.add(new Account("John", "Silver"));
        accounts.add(new Account("Madhu", "Platinum"));
        accounts.add(new Account("Luca", "Gold"));

        return accounts;

    }

    public void addAccount(Account account, boolean vipFlag) {
        System.out.println(getClass() + ": DOING MY DB WORK: ADDING AN ACCOUNT");
    }

    public boolean doWork() {
        System.out.println(getClass() + ": doWork()");
        return false;
    }

    public String getName() {
        System.out.println(getClass() + ": getName()");
        return name;
    }

    public void setName(String name) {
        System.out.println(getClass() + ": setName()");
        this.name = name;
    }

    public String getServiceCode() {
        System.out.println(getClass() + ": getServiceCode()");
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        System.out.println(getClass() + ": setServiceCode()");
        this.serviceCode = serviceCode;
    }
}
