package com.company.utils;

import com.company.model.Coordinate;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Gen {

    public static List<Coordinate> curateList(List<Coordinate> list){
        List<Coordinate> curatedList = new ArrayList<Coordinate>();
        for(Coordinate c: list){
            if(c.getX() < 8 && c.getY() < 8 && c.getX() >=0 && c.getY()>=0){
                curatedList.add(c);
            }
        }
        return curatedList;
    }
}
