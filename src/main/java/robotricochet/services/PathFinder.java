package robotricochet.services;

import robotricochet.entity.Position;
import robotricochet.entity.Robot;
import robotricochet.entity.Type;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.logging.Logger;

import static java.lang.StrictMath.abs;

public class PathFinder extends Game {


    Logger logger = Logger.getAnonymousLogger();

    public PathFinder() throws IOException, NoSuchAlgorithmException {
        super();

    }


    public int countDistance(Position startPosition, Position nextPosition) {
        int x = nextPosition.getX() - startPosition.getX();
        int y = nextPosition.getY() - startPosition.getY();
        return abs(x) + abs(y);
    }


    // recontruction of the path from the goal
    public ArrayList<Position> reconstructPath(HashMap<Position, Position> cameFrom, Position current) {
        ArrayList<Position> totalPath = new ArrayList<>();
        totalPath.add(0, current);
        while (cameFrom.containsKey(current)) {
            current = cameFrom.get(current);
            totalPath.add(0, current);
        }

        return totalPath;

    }


    public ArrayList<Position> aStar(Robot robot, Type token) {


        // sauvegarder la position initaile du robot
        final Position start = new Position(robot.getPosition().getX(), robot.getPosition().getY());
        final Position goal = this.getPlateau().searchPositionOf(token);
        int tentativeGscrore;
        HashMap<Position, Position> cameFrom = new HashMap<>();
        HashMap<Position, Integer> gScore = new HashMap<>();
        HashMap<Position, Integer> fScore = new HashMap<>();

        Comparator<Position> comparator =
                (Position p1, Position p2) -> {
                    int f1 = fScore.get(p1);
                    int f2 = fScore.get(p2);

                    if (f1 > f2) {
                        return 1;
                    } else {
                        if (f1 < f2) {
                            return -1;
                        } else {
                            return 0;
                        }
                    }

                };

        PriorityQueue<Position> openQueue = new PriorityQueue<>(1, comparator);
        HashSet<Position> openSet = new HashSet<>();
        ArrayList<Position> aStartArray;


        openQueue.add(start);
        openSet.add(start);

        gScore.put(start, 0);
        fScore.put(start, countDistance(start, goal));

        while (!openQueue.isEmpty()) {
            final Position current = openQueue.poll();
            openSet.remove(current);
            gScore.put(current, countDistance(start, current));
            //returning the arraylist from start to the goal
            if ((current.getX() == goal.getX()) && (current.getY() == goal.getY())) {

                aStartArray = reconstructPath(cameFrom, current);
                robot.setPosition(aStartArray.get(aStartArray.size() - 1));
                return aStartArray;
            }
            robot.setPosition(current);

            Position[] moves = getMovesTest(robot);
            for (Position nextMove : moves) {
                tentativeGscrore = gScore.getOrDefault(current, Integer.MAX_VALUE) + this.countDistance(current,nextMove);
                if (tentativeGscrore < gScore.getOrDefault(nextMove, Integer.MAX_VALUE)) {
                    cameFrom.put(nextMove, current);
                    fScore.put(nextMove, tentativeGscrore + countDistance(nextMove, goal));
                    if (!openSet.contains(nextMove)) {
                        openQueue.add(nextMove);
                        openSet.add(nextMove);
                    }
                }
            }
        }
        return null;

    }

    public void play() throws NoSuchAlgorithmException {
        logger.info("start of game ");
        Type currentToken = this.drawToken();// to start the round we draw a token from the arraylistof tokens
        Robot currentRobot = this.currentRobot(currentToken);
        logger.info("the current token is : " + currentToken + "* position *" + this.getPlateau().searchPositionOf(currentToken));
        logger.info("the current robot is  : " + currentRobot.getColor() + " *position * " + currentRobot.getPosition());
        ArrayList<Position> astarArray = this.aStar(this.currentRobot(currentToken), currentToken);
        if (astarArray == null) {
            logger.info("No path for the current robot with the current token !!");
        } else {
            logger.info("the path : " + astarArray);
            logger.info("final position of robot : " + this.currentRobot(currentToken).getPosition());
        }

        System.out.println("end of game ");
    }

}