package com.example.testapplication.Util;

public class Utils {
   static public String findweeks(String x) {
        String temp="";
        int y=0;
        String[] weeks = {"Mon","Tue","Wed","Thurs","Fri","Sat","Sun"};
        for(int i=0;i<x.length();i+=2)
        {
            if(x.charAt(i)=='1')
            {
                temp+= weeks[y];
                temp+=", ";
            }
            y++;
        }
        return temp;
    }
}
