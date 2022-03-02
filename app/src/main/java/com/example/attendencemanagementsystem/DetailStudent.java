package com.example.attendencemanagementsystem;

public class DetailStudent {

    String Name,Attendence;

    public DetailStudent(){}

    public DetailStudent(String name, String attendence) {
        Name = name;
        Attendence = attendence;
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
}
