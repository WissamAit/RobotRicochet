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


    public void play() {

        logger.info("start of game ");
        CaseType currentToken = Token.drawToken();// to start the round we draw a token from the arraylistof tokens
        Robot currentRobot = pathFinder.getGame().currentRobot(currentToken);
        Position positionOfToken = pathFinder.getGame().searchPositionOf(currentToken);
        logger.info("the current token is : " + currentToken + "* position *" + positionOfToken);
        logger.info("the current robot is  : " + currentRobot.getColor() + " *position * " + currentRobot.getPosition());
        List<Position> astarArray = pathFinder.aStar(pathFinder.getGame().currentRobot(currentToken), currentToken);
        logger.info("the path : " + astarArray);
        logger.info("final position of robot : " + pathFinder.getGame().currentRobot(currentToken).getPosition());


        logger.info("end of game ");
    }
}
