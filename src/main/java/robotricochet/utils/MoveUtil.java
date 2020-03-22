package robotricochet.utils;

import robotricochet.entity.*;
import robotricochet.services.Plateau;
import robotricochet.services.RobotBuilder;

public class MoveUtil {
    public static Position getPositionWhenMovedInDirection(Position position, Direction direction, Color color, Plateau plateau) { //return the position of a robot placed on position position if we were to move it in the direction
        Position ghostRobotPosition = new Position(position.getX(), position.getY()); //clone of the robot position before he moves
        if (direction == Direction.UP) {
            return moveUp(color, ghostRobotPosition, plateau);
        }
        if (direction == Direction.DOWN) {
            return moveDown(color, ghostRobotPosition, plateau);
        }
        if (direction == Direction.LEFT) {
            return moveLeft(color, ghostRobotPosition, plateau);
        }
        if (direction == Direction.RIGHT) {
            return moveRight(color, ghostRobotPosition, plateau);
        }
        return null;
    }

    public static Position moveRight(Color color, Position ghostRobotPosition, Plateau plateau) {
        ghostRobotPosition.setY(ghostRobotPosition.getY() + 1);
        while (!isObstacle(ghostRobotPosition, plateau)) {
            if (isRicochetForRobot(color, ghostRobotPosition, plateau)) {
                if (isSlash(ghostRobotPosition, plateau)) {
                    return getPositionWhenMovedInDirection(ghostRobotPosition, Direction.UP, color, plateau);
                } else {
                    return getPositionWhenMovedInDirection(ghostRobotPosition, Direction.DOWN, color, plateau);
                }
            }
            ghostRobotPosition.setY(ghostRobotPosition.getY() + 1);
        }
        ghostRobotPosition.setY(ghostRobotPosition.getY() - 1);
        return ghostRobotPosition;
    }

    public static Position moveLeft(Color color, Position ghostRobotPosition, Plateau plateau) {
        ghostRobotPosition.setY(ghostRobotPosition.getY() - 1);
        while (!isObstacle(ghostRobotPosition, plateau)) {
            if (isRicochetForRobot(color, ghostRobotPosition, plateau)) {
                if (isSlash(ghostRobotPosition, plateau)) {
                    return getPositionWhenMovedInDirection(ghostRobotPosition, Direction.DOWN, color, plateau);
                } else {
                    return getPositionWhenMovedInDirection(ghostRobotPosition, Direction.UP, color, plateau);
                }
            }
            ghostRobotPosition.setY(ghostRobotPosition.getY() - 1);
        }
        ghostRobotPosition.setY(ghostRobotPosition.getY() + 1);
        return ghostRobotPosition;
    }

    public static Position moveDown(Color color, Position ghostRobotPosition, Plateau plateau) {
        ghostRobotPosition.setX(ghostRobotPosition.getX() + 1);
        while (!isObstacle(ghostRobotPosition, plateau)) {
            if (isRicochetForRobot(color, ghostRobotPosition, plateau)) {
                if (isSlash(ghostRobotPosition, plateau)) {
                    return getPositionWhenMovedInDirection(ghostRobotPosition, Direction.LEFT, color, plateau);
                } else {
                    return getPositionWhenMovedInDirection(ghostRobotPosition, Direction.RIGHT, color, plateau);
                }
            }
            ghostRobotPosition.setX(ghostRobotPosition.getX() + 1);
        }
        ghostRobotPosition.setX(ghostRobotPosition.getX() - 1);
        return ghostRobotPosition;
    }

    public static Position moveUp(Color color, Position ghostRobotPosition, Plateau plateau) {
        ghostRobotPosition.setX(ghostRobotPosition.getX() - 1);
        while (!isObstacle(ghostRobotPosition, plateau)) {

            if (isRicochetForRobot(color, ghostRobotPosition, plateau)) {

                if (isSlash(ghostRobotPosition, plateau)) {
                    return getPositionWhenMovedInDirection(ghostRobotPosition, Direction.RIGHT, color, plateau);
                } else {
                    return getPositionWhenMovedInDirection(ghostRobotPosition, Direction.LEFT, color, plateau);
                }
            }
            ghostRobotPosition.setX(ghostRobotPosition.getX() - 1);
        }
        ghostRobotPosition.setX(ghostRobotPosition.getX() + 1);
        return ghostRobotPosition;
    }

    //TODO: move to Plateau
    public static boolean isObstacle(Position position, Plateau plateau) {
        return (plateau.getPlateau()[position.getX()][position.getY()].getCaseType() == CaseType.OBSTACLE);

    }

    //TODO/ move to Plateau
    public static boolean isRicochetForRobot(Color color, Position position, Plateau plateau) {
        CaseType caseType = plateau.getPlateau()[position.getX()][position.getY()].getCaseType();
        if (color == Color.BLUE && (caseType == CaseType.SLASH_BLUE || caseType == CaseType.ANTISLASH_BLUE)) {
            return true;
        }
        if (color == Color.GREEN && (caseType == CaseType.SLASH_GREEN || caseType == CaseType.ANTISLASH_GREEN)) {
            return true;
        }
        if (color == Color.RED && (caseType == CaseType.SLASH_RED || caseType == CaseType.ANTISLASH_RED)) {
            return true;
        }
        if (color == Color.YELLOW && (caseType == CaseType.SLASH_YELLOW || caseType == CaseType.ANTISLASH_YELLOW)) {
            return true;
        }
        return false;
    }

    //TODO move to Plateau
    public static boolean isSlash(Position position, Plateau plateau) {
        CaseType caseType = plateau.getPlateau()[position.getX()][position.getY()].getCaseType();
        return (caseType == CaseType.SLASH_BLUE
                || caseType == CaseType.SLASH_GREEN
                || caseType == CaseType.SLASH_RED
                || caseType == CaseType.SLASH_YELLOW);
    }


}
