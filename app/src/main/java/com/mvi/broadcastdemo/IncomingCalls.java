package com.mvi.broadcastdemo;

public class IncomingCalls {

    private int id;
    private String incomingNo;

    public IncomingCalls(int id, String incomingNo) {
        this.setId(id);
        this.setIncomingNo(incomingNo);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIncomingNo() {
        return incomingNo;
    }

    public void setIncomingNo(String incomingNo) {
        this.incomingNo = incomingNo;
    }
}
