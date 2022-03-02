package com.example.attendencemanagementsystem;

public class Contactmodel {

    String Name,Attendence,Date;

    public Contactmodel(String name, String attendence, String date) {
        Name = name;
        Attendence = attendence;
        Date = date;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAttendence() {
        return Attendence;
    }

    public void setAttendence(String attendence) {
        Attendence = attendence;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }
}
