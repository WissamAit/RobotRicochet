package robotricochet.entity;

import robotricochet.services.Plateau;
import robotricochet.utils.MoveUtil;

import java.util.*;

import static java.lang.StrictMath.abs;

/**
 * Robot
 */
public class Robot {


    private Color color;
    private Position position;
    private Map<Position, Position> cameFrom = new HashMap<>();
    private Map<Position, Integer> currentTostartCost = new HashMap<>();//gScore
    private Map<Position, Integer> cheapestCost = new HashMap<>();//fScore
    private Plateau plateau;


    public Robot(Color color, Position position) {
        this.color = color;
        this.position = position;
    }

    public List<Position> getPossibleMoves() {
        List<Position> positions = new ArrayList<>();
        positions.add(MoveUtil.getPositionWhenMovedInDirection(getPosition(), Direction.UP, getColor(), plateau));
        positions.add(MoveUtil.getPositionWhenMovedInDirection(getPosition(), Direction.DOWN, getColor(), plateau));
        positions.add(MoveUtil.getPositionWhenMovedInDirection(getPosition(), Direction.LEFT, getColor(), plateau));
        positions.add(MoveUtil.getPositionWhenMovedInDirection(getPosition(), Direction.RIGHT, getColor(), plateau));
        return positions;
    }

    public Position getPosition() {
        return this.position;
    }

    public void setPosition(Position position) {
        this.position.setX(position.getX());
        this.position.setY(position.getY());
    }

    public void setPosition(int x, int y) {
        this.position.setX(x);
        this.position.setY(y);
    }

    public Color getColor() {
        return this.color;
    }

    public List<Position> aStar(Robot robot, CaseType token) {

        final Position startPosition = new Position(robot.getPosition().getX(), robot.getPosition().getY());
        final Position targetPosition = plateau.searchPositionOf(token);
        int tentativeGscore;
        Comparator<Position> comparator = getPositionComparator(cheapestCost);

        PriorityQueue<Position> openQueuePositions = new PriorityQueue<>(1, comparator);
        Set<Position> openSetPositions = new HashSet<>();


        openQueuePositions.add(startPosition);
        openSetPositions.add(startPosition);

        currentTostartCost.put(startPosition, 0);
        cheapestCost.put(startPosition, countDistance(startPosition, targetPosition));

        while (!openQueuePositions.isEmpty()) {
            final Position current = openQueuePositions.poll();
            openSetPositions.remove(current);
            currentTostartCost.put(current, countDistance(startPosition, current));

            List<Position> finalSmallestPath = finalPositionIsReached(robot, targetPosition, current);
            if (!finalSmallestPath.isEmpty()) return finalSmallestPath;

            robot.setPosition(current);

            List<Position> possibleMoves = getPossibleMoves();
            for (Position nextMove : possibleMoves) {
                tentativeGscore = currentTostartCost.getOrDefault(current, Integer.MAX_VALUE) + countDistance(nextMove,current);
                if (tentativeGscore < currentTostartCost.getOrDefault(nextMove, Integer.MAX_VALUE)) {
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


    public void injectCurrentGrid(Plateau plateau) {
        this.plateau = plateau;
    }
}