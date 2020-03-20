package RobotRicochet.services;

import RobotRicochet.entity.Direction;
import RobotRicochet.entity.Position;
import RobotRicochet.entity.Robot;
import RobotRicochet.entity.Type;
import RobotRicochet.entity.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * Game
 */
public class Game {

	private static final boolean debug = true; // debug value to test program

	public Plateau plateau;
	public ArrayList<Type> tokens; // list of symbols that we draw each turn (for example BLUE_DIAMOND)
	public Random rand; // used to select the token randomly
	public ArrayList<Type> redTokens;
	public ArrayList<Type> greenTokens;
	public ArrayList<Type> blueTokens;
	public ArrayList<Type> yellowTokens;


	public Game() throws IOException {
		this.plateau = new Plateau();
		this.tokens = new ArrayList<>();
		this.rand = new Random();
		redTokens=new ArrayList<>();
		greenTokens=new ArrayList<>();
		yellowTokens=new ArrayList<>();
		blueTokens=new ArrayList<>();
		redTokens.add(Type.RED_CIRCLE);
		redTokens.add(Type.RED_SQUARE);
		redTokens.add(Type.RED_TRIANGLE);
		redTokens.add(Type.RED_DIAMOND);

		greenTokens.add(Type.GREEN_CIRCLE);
		greenTokens.add(Type.GREEN_SQUARE);
		greenTokens.add(Type.GREEN_TRIANGLE);
		greenTokens.add(Type.GREEN_DIAMOND);

		yellowTokens.add(Type.YELLOW_CIRCLE);
		yellowTokens.add(Type.YELLOW_SQUARE);
		yellowTokens.add(Type.YELLOW_TRIANGLE);
		yellowTokens.add(Type.YELLOW_DIAMOND);

		blueTokens.add(Type.BLUE_CIRCLE);
		blueTokens.add(Type.BLUE_SQUARE);
		blueTokens.add(Type.BLUE_TRIANGLE);
		blueTokens.add(Type.BLUE_DIAMOND);
 		this.initGame();

	}

	public Plateau getPlateau() {
		return this.plateau;
	}

	public void printTokensContent() {
		System.out.println("Content of tokens : ");
		for (Type type : this.tokens) {
			System.out.println(type);
		}
	}

	public void initGame() throws IOException {
		for (Type type : Type.values()) { // fills the ArrayList tokens with every types from enum Type
			this.tokens.add(type);
		}
		this.tokens.remove(Type.OBSTACLE); // remove types that aren't tokens in the arraylist tokens
		this.tokens.remove(Type.EMPTYSPACE);
		this.tokens.remove(Type.ANTISLASH_RED);
		this.tokens.remove(Type.SLASH_RED);
		this.tokens.remove(Type.ANTISLASH_GREEN);
		this.tokens.remove(Type.SLASH_GREEN);
		this.tokens.remove(Type.ANTISLASH_BLUE);
		this.tokens.remove(Type.SLASH_BLUE);
		this.tokens.remove(Type.ANTISLASH_YELLOW);
		this.tokens.remove(Type.SLASH_YELLOW);
		this.tokens.remove(Type.RED_ROBOT_START);
		this.tokens.remove(Type.GREEN_ROBOT_START);
		this.tokens.remove(Type.BLUE_ROBOT_START);
		this.tokens.remove(Type.YELLOW_ROBOT_START);

		if (debug) {
			System.out.println("");
			this.printTokensContent(); // print the arraylist of tokens
			this.plateau.printPlateau(); // print the plateau from the java classes in the same format as the file it
											// came from
		}
	}

	public Type drawToken() { // return a token from tokens, null if tokens is empty
		if (!this.tokens.isEmpty()) {
			int randomNumber = rand.nextInt(this.tokens.size()); // select a random number between 0 and the size of the
																	// array
			Type drawnToken = this.tokens.get(randomNumber); // select the token at that index
			this.tokens.remove(randomNumber); // removes it from the array
			return drawnToken;
		}
		return null;
	}






	public RobotRicochet.entity.Color randomColor(){
		Random choiceColor=new Random();
		int choosenColor=choiceColor.nextInt(3);
		if(choosenColor==0){
			return RobotRicochet.entity.Color.RED;
		}
		if(choosenColor==1) {
			return RobotRicochet.entity.Color.BLUE;
		}
		if(choosenColor==2) {
			return RobotRicochet.entity.Color.YELLOW;
		}
		if(choosenColor==3) {
			return RobotRicochet.entity.Color.GREEN;
		}
		return null;
	}

	public Robot currentRobot(Type token) {// return the robot which has to play according to the color of the token

		if (this.redTokens.contains(token)) {
			return this.plateau.getRobot(RobotRicochet.entity.Color.RED);
		}
		if (this.greenTokens.contains(token)) {
			return this.plateau.getRobot(RobotRicochet.entity.Color.GREEN);
		}
		if (this.blueTokens.contains(token)) {
			return this.plateau.getRobot(RobotRicochet.entity.Color.BLUE);
		}
		if (this.yellowTokens.contains(token)) {
			return this.plateau.getRobot(RobotRicochet.entity.Color.YELLOW);
		}
		if(token==Type.MULTICOLOR_VORTEX){
			return this.plateau.getRobot(this.randomColor());
		}

		return null;
	}


	public boolean isObstacleTest(Position position) {
		if (this.getPlateau().getRobot(RobotRicochet.entity.Color.BLUE).getPosition().isTheSameAs(position) || this.getPlateau().getRobot(RobotRicochet.entity.Color.GREEN).getPosition().isTheSameAs(position) || this.getPlateau().getRobot(RobotRicochet.entity.Color.RED).getPosition().isTheSameAs(position) || this.getPlateau().getRobot(RobotRicochet.entity.Color.YELLOW).getPosition().isTheSameAs(position)) {
			return true;
		}
		if (this.plateau.getPlateau()[position.getX()][position.getY()].getType()==Type.OBSTACLE) {
			return true;
		}
		return false;
	}

	public boolean isRicochetForRobot(RobotRicochet.entity.Color color, Position position) {
		if (color == RobotRicochet.entity.Color.BLUE && (this.plateau.getPlateau()[position.getX()][position.getY()].getType()==Type.SLASH_BLUE || this.plateau.getPlateau()[position.getX()][position.getY()].getType()==Type.ANTISLASH_BLUE)) {
			return true;
		}
		else if (color == RobotRicochet.entity.Color.GREEN && (this.plateau.getPlateau()[position.getX()][position.getY()].getType()==Type.SLASH_GREEN || this.plateau.getPlateau()[position.getX()][position.getY()].getType()==Type.ANTISLASH_GREEN)) {
			return true;
		}
		else if (color == RobotRicochet.entity.Color.RED && (this.plateau.getPlateau()[position.getX()][position.getY()].getType()==Type.SLASH_RED || this.plateau.getPlateau()[position.getX()][position.getY()].getType()==Type.ANTISLASH_RED)) {
			return true;
		}
		else if (color == RobotRicochet.entity.Color.YELLOW && (this.plateau.getPlateau()[position.getX()][position.getY()].getType()==Type.SLASH_YELLOW || this.plateau.getPlateau()[position.getX()][position.getY()].getType()==Type.ANTISLASH_YELLOW)) {
			return true;
		}
		return false;
	}

	public boolean isSlash(Position position) {
		if (this.plateau.getPlateau()[position.getX()][position.getY()].getType() == Type.SLASH_BLUE
		|| this.plateau.getPlateau()[position.getX()][position.getY()].getType() == Type.SLASH_GREEN
		|| this.plateau.getPlateau()[position.getX()][position.getY()].getType() == Type.SLASH_RED
		|| this.plateau.getPlateau()[position.getX()][position.getY()].getType() == Type.SLASH_YELLOW) {
			return true;
		}
		return false;
	}

	public Position getPositionWhenMovedInDirection(Position position, Direction direction, RobotRicochet.entity.Color color) { //return the position of a robot placed on position position if we were to move it in the direction
		Position ghostRobotPosition = new Position(position.getX(), position.getY()); //clone of the robot position before he moves
		if (direction == Direction.UP) {
			ghostRobotPosition.setX(ghostRobotPosition.getX()-1); //the ghost position is one case above the real robot
			while (!this.isObstacleTest(ghostRobotPosition)) { // while the case above isn't an obstacle

				if (this.isRicochetForRobot(color, ghostRobotPosition)) { // if it's a ricochet for the curret robot

					if (this.isSlash(ghostRobotPosition)) { // if it's a / ricochet

						return this.getPositionWhenMovedInDirection(ghostRobotPosition, Direction.RIGHT, color); //the position we want when moving up is the direction when we go right from the ghost robot
					}
					else { // if it's a \ ricochet
						return this.getPositionWhenMovedInDirection(ghostRobotPosition, Direction.LEFT, color); //same but in the oposite direction
					}
				}
				ghostRobotPosition.setX(ghostRobotPosition.getX()-1); //we move the ghost robot one case above
			}
			ghostRobotPosition.setX(ghostRobotPosition.getX()+1);
			return ghostRobotPosition;
		}
		if (direction == Direction.DOWN) {
			ghostRobotPosition.setX(ghostRobotPosition.getX()+1); //the ghost position is one case above the real robot
			while (!this.isObstacleTest(ghostRobotPosition)) { // while the case above isn't an obstacle
				if (this.isRicochetForRobot(color, ghostRobotPosition)) { // if it's a ricochet for the curret robot
					if (this.isSlash(ghostRobotPosition)) { // if it's a / ricochet
						return this.getPositionWhenMovedInDirection(ghostRobotPosition, Direction.LEFT, color); //the position we want when moving up is the direction when we go right from the ghost robot
					}
					else { // if it's a \ ricochet
						return this.getPositionWhenMovedInDirection(ghostRobotPosition, Direction.RIGHT, color); //same but in the oposite direction
					}
				}
				ghostRobotPosition.setX(ghostRobotPosition.getX()+1); //we move the ghost robot one case above
			}
			ghostRobotPosition.setX(ghostRobotPosition.getX()-1);
			return ghostRobotPosition;
		}
		if (direction == Direction.LEFT) {
			ghostRobotPosition.setY(ghostRobotPosition.getY()-1); //the ghost position is one case above the real robot
			while (!this.isObstacleTest(ghostRobotPosition)) { // while the case above isn't an obstacle
				if (this.isRicochetForRobot(color, ghostRobotPosition)) { // if it's a ricochet for the curret robot
					if (this.isSlash(ghostRobotPosition)) { // if it's a / ricochet
						return this.getPositionWhenMovedInDirection(ghostRobotPosition, Direction.DOWN, color); //the position we want when moving up is the direction when we go right from the ghost robot
					}
					else { // if it's a \ ricochet
						return this.getPositionWhenMovedInDirection(ghostRobotPosition, Direction.UP, color); //same but in the oposite direction
					}
				}
				ghostRobotPosition.setY(ghostRobotPosition.getY()-1); //we move the ghost robot one case above
			}
			ghostRobotPosition.setY(ghostRobotPosition.getY()+1);
			return ghostRobotPosition;
		}
		if (direction == Direction.RIGHT) {
			ghostRobotPosition.setY(ghostRobotPosition.getY()+1); //the ghost position is one case above the real robot
			while (!this.isObstacleTest(ghostRobotPosition)) { // while the case above isn't an obstacle
				if (this.isRicochetForRobot(color, ghostRobotPosition)) { // if it's a ricochet for the curret robot
					if (this.isSlash(ghostRobotPosition)) { // if it's a / ricochet
						return this.getPositionWhenMovedInDirection(ghostRobotPosition, Direction.UP, color); //the position we want when moving up is the direction when we go right from the ghost robot
					}
					else { // if it's a \ ricochet
						return this.getPositionWhenMovedInDirection(ghostRobotPosition, Direction.DOWN, color); //same but in the oposite direction
					}
				}
				ghostRobotPosition.setY(ghostRobotPosition.getY()+1); //we move the ghost robot one case above
			}
			ghostRobotPosition.setY(ghostRobotPosition.getY()-1);
			return ghostRobotPosition;
		}
		return null;
	}

	public Position[] getMovesTest(Robot robot) { // return the positions the robot can go to in a table of four position corresponding to up, down, left, right
		Position[] positions = new Position[4];
		positions[0] = this.getPositionWhenMovedInDirection(robot.getPosition(), Direction.UP, robot.getColor());
		positions[1] = this.getPositionWhenMovedInDirection(robot.getPosition(), Direction.DOWN, robot.getColor());
		positions[2] = this.getPositionWhenMovedInDirection(robot.getPosition(), Direction.LEFT, robot.getColor());
		positions[3] = this.getPositionWhenMovedInDirection(robot.getPosition(), Direction.RIGHT, robot.getColor());
		return positions;
	}



}
