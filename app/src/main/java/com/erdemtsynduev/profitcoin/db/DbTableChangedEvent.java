package com.erdemtsynduev.profitcoin.db;

public class DbTableChangedEvent {

    public final String nameTable;

    public DbTableChangedEvent(String nameTable) {
        this.nameTable = nameTable;
    }
}
