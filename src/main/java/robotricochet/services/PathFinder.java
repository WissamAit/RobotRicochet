package robotricochet.services;

import robotricochet.entity.CaseType;
import robotricochet.entity.Position;
import robotricochet.entity.Robot;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.*;


import static java.lang.StrictMath.abs;

public class PathFinder {


    private GameBuilder game;
    Map<Position, Position> cameFrom = new HashMap<>();
    Map<Position, Integer> current2startCost = new HashMap<>();//gScore
    Map<Position, Integer> cheapestCost = new HashMap<>();//fScore


    public PathFinder() throws IOException, NoSuchAlgorithmException {
        game = new GameBuilder();
    }

    public GameBuilder getGame() {
        return game;
    }

    public List<Position> aStar(Robot robot, CaseType token) {

        final Position startPosition = new Position(robot.getPosition().getX(), robot.getPosition().getY());
        final Position targetPosition = game.searchPositionOf(token);
        int tentativeGscore;
        Comparator<Position> comparator = getPositionComparator(cheapestCost);

        PriorityQueue<Position> openQueuePositions = new PriorityQueue<>(1, comparator);
        Set<Position> openSetPositions = new HashSet<>();


        openQueuePositions.add(startPosition);
        openSetPositions.add(startPosition);

        current2startCost.put(startPosition, 0);
        cheapestCost.put(startPosition, countDistance(startPosition, targetPosition));

        while (!openQueuePositions.isEmpty()) {
            final Position current = openQueuePositions.poll();
            openSetPositions.remove(current);
            current2startCost.put(current, countDistance(startPosition, current));

            List<Position> finalSmallestPath = finalPositionIsReached(robot, targetPosition, current);
            if (!finalSmallestPath.isEmpty()) return finalSmallestPath;

            robot.setPosition(current);

            List<Position> possibleMoves = game.getPossibleMoves(robot);
            for (Position nextMove : possibleMoves) {
                tentativeGscore = current2startCost.getOrDefault(current, Integer.MAX_VALUE) + 1;
                if (tentativeGscore < current2startCost.getOrDefault(nextMove, Integer.MAX_VALUE)) {
                    cameFrom.put(nextMove, current);
                    cheapestCost.put(nextMove, tentativeGscore + countDistance(nextMove, targetPosition));
                    if (!openSetPositions.contains(nextMove)) {
                        openQueuePositions.add(nextMove);
                        openSetPositions.add(nextMove);
                    }
                }
            }
        }
        return Collections.emptyList();

    }

    private List<Position> finalPositionIsReached(Robot robot, Position goal, Position current) {
        ArrayList<Position> aStarArray;
        if ((current.getX() == goal.getX()) && (current.getY() == goal.getY())) {

            aStarArray = reconstructPath(cameFrom, current);
            robot.setPosition(aStarArray.get(aStarArray.size() - 1));
            return aStarArray;
        }
        return Collections.emptyList();
    }

    public int countDistance(Position startPosition, Position nextPosition) {
        int x = nextPosition.getX() - startPosition.getX();
        int y = nextPosition.getY() - startPosition.getY();
        return abs(x) + abs(y);
    }

    private Comparator<Position> getPositionComparator(Map<Position, Integer> cheapestCost) {
        return (Position p1, Position p2) -> {
            int cheapestCostPosition1 = cheapestCost.get(p1);
            int cheapestCostPosition2 = cheapestCost.get(p2);

            return Integer.compare(cheapestCostPosition1, cheapestCostPosition2);

        };
    }


    public ArrayList<Position> reconstructPath(Map<Position, Position> cameFrom, Position current) {
        ArrayList<Position> totalPath = new ArrayList<>();
        totalPath.add(0, current);
        while (cameFrom.containsKey(current)) {
            current = cameFrom.get(current);
            totalPath.add(0, current);
        }

        return totalPath;

    }


}