package com.company.playerType;

import com.company.model.Coordinate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shubhamagrawal on 27/07/17.
 */
public class Horse implements IPlayer {

    // given a coordinates it returns list of coordinates that it can move from there
    public List<Coordinate> move(Coordinate input) {
        List<Coordinate> list = new ArrayList<Coordinate>();
        list.add(new Coordinate(input.getX()+2, input.getY()+1 ));
        list.add(new Coordinate(input.getX()+2, input.getY()-1 ));
        list.add(new Coordinate(input.getX()-2, input.getY()+1 ));
        list.add( new Coordinate(input.getX()-2, input.getY()-1 ));
        list.add(new Coordinate(input.getX()+1, input.getY()+2 ));
        list.add(new Coordinate(input.getX()+1, input.getY()-2 ));
        list.add(new Coordinate(input.getX()-1, input.getY()+2 ));
        list.add( new Coordinate(input.getX()-1, input.getY()-2 ));
        return list;
    }
}
