package com.example.mycalc;

import java.util.Random;

public class Book {
    private String []countries=new String[]{
            "MOROCCO",
            "USA",
            "RUSSIA",
            "CANADA",
            "ZIMBABWI",
            "ITALY",
            "SPAIN",
            "GERMANY",
            "POLLAND",
            "ALGERY",
            "EGYPTE",
    };

    public String getRandomCountry(){
        Random rand=new Random();
        int num=rand.nextInt(countries.length);
        return countries[num];
    }
}
