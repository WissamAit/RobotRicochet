package robotricochet.services;

import robotricochet.entity.CaseType;
import robotricochet.entity.Position;
import robotricochet.entity.Robot;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.*;


import static java.lang.StrictMath.abs;

public class PathFinder {


    private GameBuilder gameBuilder;


    public PathFinder() throws IOException, NoSuchAlgorithmException {
        gameBuilder = new GameBuilder();
    }

    public GameBuilder getGameBuilder() {
        return gameBuilder;
    }



}