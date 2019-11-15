package com.example.catbreeds;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import androidx.room.TypeConverter;

public class Converters {
    @TypeConverter
    public String fromArray(ArrayList<String> strings) {
        String string = "";
        for(String s : strings) string += (s + ",");

        return string;
    }

//    @TypeConverter
//    public ArrayList<String> toArray(String concatenatedStrings) {
//        ArrayList<String> myStrings = new ArrayList<>();
//
//        for(String s : concatenatedStrings.split(",")){
//            myStrings.append(s);
//        }
//
//        return myStrings;
//    }
}
