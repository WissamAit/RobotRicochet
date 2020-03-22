package robotricochet.services;

import robotricochet.entity.*;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

public class GameBuilder {
    private static Logger logger = Logger.getAnonymousLogger();
    private static final boolean DEBUG = true;
    private Plateau plateau;


    public GameBuilder() throws IOException, NoSuchAlgorithmException {
        this.plateau = new Plateau();
        this.initGame();
    }

    public Plateau getPlateau() {
        return plateau;
    }

    public void initGame() {
        if (DEBUG) {
            Token.printTokensContent();
            this.plateau.printPlateau();
        }
    }

//TODO: move to ColorUtils
    public Color randomColor() {
        Random choiceColor;
        try {
            choiceColor = SecureRandom.getInstanceStrong();
        } catch (NoSuchAlgorithmException e) {
            logger.warning("Can't take a random number");
            return null;
        }
        int chosenColor = choiceColor.nextInt(3);
        if (chosenColor == 0) {
            return Color.RED;
        }
        if (chosenColor == 1) {
            return Color.BLUE;
        }
        if (chosenColor == 2) {
            return Color.YELLOW;
        }
        if (chosenColor == 3) {
            return Color.GREEN;
        }
        return null;
    }

    //TODO: Move to Plateau
    public Robot currentRobot(CaseType token) {

        if (Token.redTokensList().contains(token)) {
            return this.plateau.getRobotOnPlateau(Color.RED);
        }
        if (Token.greenTokensList().contains(token)) {
            return this.plateau.getRobotOnPlateau(Color.GREEN);
        }
        if (Token.blueTokensList().contains(token)) {
            return this.plateau.getRobotOnPlateau(Color.BLUE);
        }
        if (Token.yellowTokensList().contains(token)) {
            return this.plateau.getRobotOnPlateau(Color.YELLOW);
        }
        if (token == CaseType.MULTICOLOR_VORTEX) {
            return this.plateau.getRobotOnPlateau(this.randomColor());
        }

        return null;
    }

//TODO: move to Plateau
    public Position searchPositionOf(CaseType caseType) {
        for (int i = 0; i < plateau.getPlateau()[0].length; i++) {
            for (int j = 0; j < plateau.getPlateau().length; j++) {
                if (plateau.getPlateau()[i][j].getCaseType() == caseType) {
                    return new Position(i, j);
                }
            }
        }
        return null;
    }

    //TODO: move to Plateau
    public boolean isObstacle(Position position) {
        if (this.getPlateau().getRobotOnPlateau(Color.BLUE).getPosition().isTheSameAs(position) ||
                this.getPlateau().getRobotOnPlateau(robotricochet.entity.Color.GREEN).getPosition().isTheSameAs(position) ||
                this.getPlateau().getRobotOnPlateau(robotricochet.entity.Color.RED).getPosition().isTheSameAs(position) ||
                this.getPlateau().getRobotOnPlateau(robotricochet.entity.Color.YELLOW).getPosition().isTheSameAs(position)) {
            return true;
        }
        return (this.getPlateau().getPlateau()[position.getX()][position.getY()].getCaseType() == CaseType.OBSTACLE);

    }

//TODO/ move to Plateau
    public boolean isRicochetForRobot(Color color, Position position) {
        CaseType caseType = this.plateau.getPlateau()[position.getX()][position.getY()].getCaseType();
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
    public boolean isSlash(Position position) {
        CaseType caseType = this.plateau.getPlateau()[position.getX()][position.getY()].getCaseType();
        return (caseType == CaseType.SLASH_BLUE
                || caseType == CaseType.SLASH_GREEN
                || caseType == CaseType.SLASH_RED
                || caseType == CaseType.SLASH_YELLOW);
    }


    public Position getPositionWhenMovedInDirection(Position position, Direction direction, Color color) { //return the position of a robot placed on position position if we were to move it in the direction
        Position ghostRobotPosition = new Position(position.getX(), position.getY()); //clone of the robot position before he moves
        if (direction == Direction.UP) {
            return moveUp(color, ghostRobotPosition);
        }
        if (direction == Direction.DOWN) {
            return moveDown(color, ghostRobotPosition);
        }
        if (direction == Direction.LEFT) {
            return moveLeft(color, ghostRobotPosition);
        }
        if (direction == Direction.RIGHT) {
            return moveRight(color, ghostRobotPosition);
        }
        return null;
    }

    private Position moveRight(Color color, Position ghostRobotPosition) {
        ghostRobotPosition.setY(ghostRobotPosition.getY() + 1);
        while (!this.isObstacle(ghostRobotPosition)) {
            if (this.isRicochetForRobot(color, ghostRobotPosition)) {
                if (this.isSlash(ghostRobotPosition)) {
                    return this.getPositionWhenMovedInDirection(ghostRobotPosition, Direction.UP, color);
                } else {
                    return this.getPositionWhenMovedInDirection(ghostRobotPosition, Direction.DOWN, color);
                }
            }
            ghostRobotPosition.setY(ghostRobotPosition.getY() + 1);
        }
        ghostRobotPosition.setY(ghostRobotPosition.getY() - 1);
        return ghostRobotPosition;
    }

    private Position moveLeft(Color color, Position ghostRobotPosition) {
        ghostRobotPosition.setY(ghostRobotPosition.getY() - 1);
        while (!this.isObstacle(ghostRobotPosition)) {
            if (this.isRicochetForRobot(color, ghostRobotPosition)) {
                if (this.isSlash(ghostRobotPosition)) {
                    return this.getPositionWhenMovedInDirection(ghostRobotPosition, Direction.DOWN, color);
                } else {
                    return this.getPositionWhenMovedInDirection(ghostRobotPosition, Direction.UP, color);
                }
            }
            ghostRobotPosition.setY(ghostRobotPosition.getY() - 1);
        }
        ghostRobotPosition.setY(ghostRobotPosition.getY() + 1);
        return ghostRobotPosition;
    }

    private Position moveDown(Color color, Position ghostRobotPosition) {
        ghostRobotPosition.setX(ghostRobotPosition.getX() + 1);
        while (!this.isObstacle(ghostRobotPosition)) {
            if (this.isRicochetForRobot(color, ghostRobotPosition)) {
                if (this.isSlash(ghostRobotPosition)) {
                    return this.getPositionWhenMovedInDirection(ghostRobotPosition, Direction.LEFT, color);
                } else {
                    return this.getPositionWhenMovedInDirection(ghostRobotPosition, Direction.RIGHT, color);
                }
            }
            ghostRobotPosition.setX(ghostRobotPosition.getX() + 1);
        }
        ghostRobotPosition.setX(ghostRobotPosition.getX() - 1);
        return ghostRobotPosition;
    }

    private Position moveUp(Color color, Position ghostRobotPosition) {
        ghostRobotPosition.setX(ghostRobotPosition.getX() - 1);
        while (!isObstacle(ghostRobotPosition)) {

            if (isRicochetForRobot(color, ghostRobotPosition)) {

                if (isSlash(ghostRobotPosition)) {
                    return getPositionWhenMovedInDirection(ghostRobotPosition, Direction.RIGHT, color);
                } else {
                    return getPositionWhenMovedInDirection(ghostRobotPosition, Direction.LEFT, color);
                }
            }
            ghostRobotPosition.setX(ghostRobotPosition.getX() - 1);
        }
        ghostRobotPosition.setX(ghostRobotPosition.getX() + 1);
        return ghostRobotPosition;
    }


    public List<Position> getPossibleMoves(Robot robot) {
        List<Position> positions = new ArrayList<>();
        positions.add( this.getPositionWhenMovedInDirection(robot.getPosition(), Direction.UP, robot.getColor()));
        positions.add(this.getPositionWhenMovedInDirection(robot.getPosition(), Direction.DOWN, robot.getColor()));
        positions.add(this.getPositionWhenMovedInDirection(robot.getPosition(), Direction.LEFT, robot.getColor()));
        positions.add(this.getPositionWhenMovedInDirection(robot.getPosition(), Direction.RIGHT, robot.getColor()));
        return positions;
    }



}
