package com.example.attendencemanagementsystem;

import java.util.Date;

public class LeaveData {

    String Name,LeaveDays,ToDate, FromDate;

    public LeaveData(){}

    public LeaveData(String name, String leaveDays, String toDate, String fromDate) {
        Name = name;
        LeaveDays = leaveDays;
        ToDate = toDate;
        FromDate = fromDate;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getLeaveDays() {
        return LeaveDays;
    }

    public void setLeaveDays(String leaveDays) {
        LeaveDays = leaveDays;
    }

    public String getToDate() {
        return ToDate;
    }

    public void setToDate(String toDate) {
        ToDate = toDate;
    }

    public String getFromDate() {
        return FromDate;
    }

    public void setFromDate(String fromDate) {
        FromDate = fromDate;
    }
}
