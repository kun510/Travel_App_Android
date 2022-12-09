package com.hqsoft.esales.LoginJDBC.Objects;

public class JDBCObjects {
    private String sServerName;
    private String sUserId;
    private String sPwd;
    private String sDatabase;
    private String sClass;
    private String sPrort;

    public JDBCObjects(String sServerName, String sUserId, String sPwd, String sDatabase, String sPrort) {
        this.sServerName = sServerName;
        this.sUserId = sUserId;
        this.sPwd = sPwd;
        this.sDatabase = sDatabase;
        this.sClass = "net.sourceforge.jtds.jdbc.Driver";
        this.sPrort = sPrort;
    }

    public String getsPrort() {
        return sPrort;
    }

    public void setsPrort(String sPrort) {
        this.sPrort = sPrort;
    }

    public String getsServerName() {
        return sServerName;
    }

    public void setsServerName(String sServerName) {
        this.sServerName = sServerName;
    }

    public String getsUserId() {
        return sUserId;
    }

    public void setsUserId(String sUserId) {
        this.sUserId = sUserId;
    }

    public String getsPwd() {
        return sPwd;
    }

    public void setsPwd(String sPwd) {
        this.sPwd = sPwd;
    }

    public String getsDatabase() {
        return sDatabase;
    }

    public void setsDatabase(String sDatabase) {
        this.sDatabase = sDatabase;
    }

    public String getsClass() {
        return sClass;
    }

    public void setsClass(String sClass) {
        this.sClass = sClass;
    }
}
