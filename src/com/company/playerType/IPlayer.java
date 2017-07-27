package com.company.playerType;

import com.company.model.Coordinate;

import java.util.List;

/**
 * Created by shubhamagrawal on 27/07/17.
 */
public interface IPlayer {

    public List<Coordinate> move(Coordinate input);
}
