package com.example.attendencemanagementsystem;

public class Datadata {

   String Name;
   String Attendence;
   String Date;



   public Datadata(){}

    public Datadata(String name, String attendence, String date) {
        Name = name;
        Attendence = attendence;
        Date = date;



    }

    //public static String getImageUrl() {
        //return ImageUrl;
   // }

    //public void setImageUrl(String imageU) {
      //  ImageUrl = imageU;
   // }

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
