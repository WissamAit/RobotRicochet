package robotricochet.services;

import robotricochet.entity.*;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import java.util.List;
import java.util.logging.Logger;

/**
 * Game
 */
public class Game {

    private PathFinder pathFinder;
    Logger logger = Logger.getAnonymousLogger();

    public Game() throws IOException, NoSuchAlgorithmException {
        pathFinder = new PathFinder();
    }


}
