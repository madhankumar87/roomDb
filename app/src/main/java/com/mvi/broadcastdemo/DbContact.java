package com.mvi.broadcastdemo;

public class DbContact {

    public static final String TABLE_NAME = "incomingInfo";
    public static final String INCOMING_NUMBER = "incomingNumber";
    public static final String ID = "id";
    public static final String UPDATE_UI_FILTER = "com.mvi.broadcastdemo.UPDATE_UI";

    public static final String DATABASE_NAME = "incomingCallsDB";
    public static final int DATABASE_VERSION = 1;
    public static final String CREATE = "create table " + TABLE_NAME + "(id integer primary key autoincrement, " + INCOMING_NUMBER + " text);";
    public static final String DROPTABLE = "drop table if exists " + TABLE_NAME;
}
