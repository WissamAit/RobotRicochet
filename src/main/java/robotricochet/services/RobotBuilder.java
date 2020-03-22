package robotricochet.services;

import robotricochet.entity.Case;
import robotricochet.entity.Color;
import robotricochet.entity.Position;
import robotricochet.entity.Robot;

import java.util.HashMap;
import java.util.Map;

public class RobotBuilder {


    private Robot redRobot;
    private Robot greenRobot;
    private Robot blueRobot;
    private Robot yellowRobot;


    public RobotBuilder() {
        this.greenRobot = new Robot(Color.GREEN, new Position(1, 1));
        this.redRobot = new Robot(Color.RED, new Position(1, 1));
        this.blueRobot = new Robot(Color.BLUE, new Position(1, 1));
        this.yellowRobot = new Robot(Color.YELLOW, new Position(1, 1));

    }

    public void placeRobotOnStartingCases(Case[][] grid) {
        for (int i = 0; i < 18; i++) {
            for (int j = 0; j < 18; j++) {
                switch (grid[i][j].getCaseType()) {
                    case GREEN_ROBOT_START:
                        this.greenRobot = new Robot(Color.GREEN, new Position(i, j));
                        break;
                    case BLUE_ROBOT_START:
                        this.blueRobot = new Robot(Color.BLUE, new Position(i, j));
                        break;
                    case YELLOW_ROBOT_START:
                        this.yellowRobot = new Robot(Color.YELLOW, new Position(i, j));
                        break;
                    case RED_ROBOT_START:
                        this.redRobot = new Robot(Color.RED, new Position(i, j));
                        break;
                    default:
                        break;
                }
            }
        }
    }


    public Robot getBlueRobot() {
        return blueRobot;
    }

    public Robot getGreenRobot() {
        return greenRobot;
    }

    public Robot getRedRobot() {
        return redRobot;
    }

    public Robot getYellowRobot() {
        return yellowRobot;
    }

    ;

}
