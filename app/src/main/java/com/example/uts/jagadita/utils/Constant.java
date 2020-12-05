package com.example.uts.jagadita.utils;

public class Constant {
    public static final String BASE_URL="https://jagadita.herokuapp.com/";

    public static class URL{
        public static String carImage(String file){
            return BASE_URL+"uploads/"+file;
        }
        public static String api(String filename){
            return BASE_URL+"/"+filename;
        }
    }
}
