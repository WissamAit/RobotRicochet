package RobotRicochet.services;

import RobotRicochet.entity.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Plateau
 */
public class Plateau {

	private static final boolean debug = true; // debug value to test program



	public Case[][] plateau;
	public Robot redRobot;
	public Robot greenRobot;
	public Robot blueRobot;
	public Robot yellowRobot;

	public Plateau() throws IOException {
		this.plateau = this.constructPlateau();
		this.redRobot = new Robot(Color.RED, new Position(-1, -1));
		this.greenRobot = new Robot(Color.GREEN, new Position(-1, -1));
		this.blueRobot = new Robot(Color.BLUE, new Position(-1, -1));
		this.yellowRobot = new Robot(Color.YELLOW, new Position(-1, -1));
		this.placeRobotOnStartingCases();
	}

	public Case[][] getPlateau() {
		return this.plateau;
	}

	public void placeRobotOnStartingCases() { // positionne les robots sur les pastilles
		for (int i = 0; i < 18; i++) {
			for (int j = 0; j < 18; j++) {
				switch (this.plateau[i][j].getType()) {
				case GREEN_ROBOT_START:
					this.greenRobot.setPosition(i, j);
					break;
				case BLUE_ROBOT_START:
					this.blueRobot.setPosition(i, j);
					break;
				case YELLOW_ROBOT_START:
					this.yellowRobot.setPosition(i, j);
					break;
				case RED_ROBOT_START:
					this.redRobot.setPosition(i, j);
					break;
				default:
					break;
				}
			}
		}
	}

	public Robot getRobot(Color color) {
		if (color == Color.RED) {
			return this.redRobot;
		} else if (color == Color.GREEN) {
			return this.greenRobot;
		} else if (color == Color.BLUE) {
			return this.blueRobot;
		} else if (color == Color.YELLOW) {
			return this.yellowRobot;
		} else
			return null;
	}


	public void printPlateau(Case[][] plateau) throws NullPointerException {
		int i = 0;
		while (i < plateau.length) {
			for (int j = 0; j < plateau.length; j++) {
				System.out.print(plateau[i][j].toString());
			}
			System.out.println(""); // change line to print the subPlateau as a 2D table
			i++; // change line to print in the variable subPlateau
		}
	}

	public void printPlateau() throws NullPointerException {
		System.out.println("Plateau : ");
		printPlateau(this.plateau);
	}

	public Position searchPositionOf(Type type) { // return the position of the first case with Type type, else null
		for (int i = 0; i < this.plateau[0].length; i++) {
			for (int j = 0; j < this.plateau.length; j++) {
				if (plateau[i][j].getType() == type) {
					return new Position(i, j);
				}
			}
		}
		return null;
	}


	private Case[][] readFileSubPlateau(String fileName) throws IOException {
		Case[][] subPlateau = new Case[9][9];
		InputStream inputStream = Plateau.class.getResourceAsStream("/"+fileName);
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream); // reads the file with the subPlateau
		BufferedReader bufferReader = new BufferedReader(inputStreamReader);
		String line = null;
		int i = 0; // i is the line in tableau
		while ((line = bufferReader.readLine()) != null) { // until it reaches the end of the file
			String[] charactersInLine = line.split("(?!^)"); // charactersInLine contains the characters of each line by
																// line
			for (int j = 0; j < charactersInLine.length; j++) {
				switch (charactersInLine[j]) { // put the correct Type to each Case of the plateau
				case "#":
					subPlateau[i][j] = new Case(Type.OBSTACLE);
					break;
				case " ":
					subPlateau[i][j] = new Case(Type.EMPTYSPACE);
					break;
				case "r":
					subPlateau[i][j] = new Case(Type.RED_ROBOT_START);
					break;
				case "g":
					subPlateau[i][j] = new Case(Type.GREEN_ROBOT_START);
					break;
				case "b":
					subPlateau[i][j] = new Case(Type.BLUE_ROBOT_START);
					break;
				case "y":
					subPlateau[i][j] = new Case(Type.YELLOW_ROBOT_START);
					break;
				case "0":
					subPlateau[i][j] = new Case(Type.RED_CIRCLE);
					break;
				case "1":
					subPlateau[i][j] = new Case(Type.GREEN_CIRCLE);
					break;
				case "2":
					subPlateau[i][j] = new Case(Type.BLUE_CIRCLE);
					break;
				case "3":
					subPlateau[i][j] = new Case(Type.YELLOW_CIRCLE);
					break;
				case "4":
					subPlateau[i][j] = new Case(Type.RED_SQUARE);
					break;
				case "5":
					subPlateau[i][j] = new Case(Type.GREEN_SQUARE);
					break;
				case "6":
					subPlateau[i][j] = new Case(Type.BLUE_SQUARE);
					break;
				case "7":
					subPlateau[i][j] = new Case(Type.YELLOW_SQUARE);
					break;
				case "8":
					subPlateau[i][j] = new Case(Type.RED_TRIANGLE);
					break;
				case "9":
					subPlateau[i][j] = new Case(Type.GREEN_TRIANGLE);
					break;
				case "A":
					subPlateau[i][j] = new Case(Type.BLUE_TRIANGLE);
					break;
				case "B":
					subPlateau[i][j] = new Case(Type.YELLOW_TRIANGLE);
					break;
				case "C":
					subPlateau[i][j] = new Case(Type.RED_DIAMOND);
					break;
				case "D":
					subPlateau[i][j] = new Case(Type.GREEN_DIAMOND);
					break;
				case "E":
					subPlateau[i][j] = new Case(Type.BLUE_DIAMOND);
					break;
				case "F":
					subPlateau[i][j] = new Case(Type.YELLOW_DIAMOND);
					break;
				case "G":
					subPlateau[i][j] = new Case(Type.MULTICOLOR_VORTEX);
					break;
				case "H":
					subPlateau[i][j] = new Case(Type.ANTISLASH_RED);
					break;
				case "I":
					subPlateau[i][j] = new Case(Type.SLASH_RED);
					break;
				case "J":
					subPlateau[i][j] = new Case(Type.ANTISLASH_GREEN);
					break;
				case "K":
					subPlateau[i][j] = new Case(Type.SLASH_GREEN);
					break;
				case "L":
					subPlateau[i][j] = new Case(Type.ANTISLASH_BLUE);
					break;
				case "M":
					subPlateau[i][j] = new Case(Type.SLASH_BLUE);
					break;
				case "N":
					subPlateau[i][j] = new Case(Type.ANTISLASH_YELLOW);
					break;
				case "O":
					subPlateau[i][j] = new Case(Type.SLASH_YELLOW);
					break;
				}
			}
			i++; // the file reader switch to the next line, so we increase i to change row in
					// plateau
		}
		inputStreamReader.close();
		return subPlateau;
	}

	public Case[][] constructPlateau() throws IOException {


		Random rand = new Random();
		int randomNumber;
		ArrayList<String> subPlateauFiles = new ArrayList<String>(
				Arrays.asList("subPlateau1.txt", "subPlateau2.txt", "subPlateau3.txt", "subPlateau4.txt")); // list of
																											// subPlateau
																											// files
		randomNumber = rand.nextInt(subPlateauFiles.size());
		Case[][] subPlateauTopLeft = this.readFileSubPlateau("subPlateau/" + subPlateauFiles.get(randomNumber));
		subPlateauFiles.remove(randomNumber); // remove the file that was used to create subPlateauTopLeft from the list
												// of files
		randomNumber = rand.nextInt(subPlateauFiles.size());
		Case[][] subPlateauTopRightTemp = this.readFileSubPlateau("subPlateau/" + subPlateauFiles.get(randomNumber)); // subPlateau
																														// before
																														// rotation
		Case[][] subPlateauTopRight = new Case[9][9]; // subPlateau that will contain the subPlateau once its rotated
		subPlateauFiles.remove(randomNumber);
		randomNumber = rand.nextInt(subPlateauFiles.size());
		Case[][] subPlateauBottomLeftTemp = this.readFileSubPlateau("subPlateau/" + subPlateauFiles.get(randomNumber));
		Case[][] subPlateauBottomLeft = new Case[9][9];
		subPlateauFiles.remove(randomNumber);
		randomNumber = rand.nextInt(subPlateauFiles.size());
		Case[][] subPlateauBottomRightTemp = this.readFileSubPlateau("subPlateau/" + subPlateauFiles.get(randomNumber));
		Case[][] subPlateauBottomRight = new Case[9][9];
		subPlateauFiles.remove(randomNumber);

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {

				subPlateauTopRight[i][j] = subPlateauTopRightTemp[i][8 - j];// rotate the top right subPlateau by 90°
																			// to the right
																			//the switch is used for switching the slash by
																			//an anti-slash only with the rotation of 90°
				switch(subPlateauTopRight[i][j].toString()) {

				case "I":
					subPlateauTopRight[i][j]=new Case(Type.ANTISLASH_RED);
					break;
				case "M":
					subPlateauTopRight[i][j]=new Case(Type.ANTISLASH_BLUE);
					break;
				case "K":
					subPlateauTopRight[i][j]=new Case(Type.ANTISLASH_GREEN);
					break;
				case "O":
					subPlateauTopRight[i][j]=new Case(Type.ANTISLASH_YELLOW);
					break;

				case "H":
					subPlateauTopRight[i][j]=new Case(Type.SLASH_RED);
					break;
				case "L":
					subPlateauTopRight[i][j]=new Case(Type.SLASH_BLUE);
					break;
				case "J":
					subPlateauTopRight[i][j]=new Case(Type.SLASH_GREEN);
					break;
				case "N":
					subPlateauTopRight[i][j]=new Case(Type.SLASH_YELLOW);
					break;

				}

				subPlateauBottomLeft[i][j] = subPlateauBottomLeftTemp[8 - i][j];    // rotate the bottom left subPlateau by
																	               // 90° to the left
				                                                                  //the switch is used for switching the slash by
																		    	 //an anti-slash only with the rotation of 90°
				switch(subPlateauBottomLeft[i][j].toString()) {

				case "I":
					subPlateauBottomLeft[i][j]=new Case(Type.ANTISLASH_RED);
					break;
				case "M":
					subPlateauBottomLeft[i][j]=new Case(Type.ANTISLASH_BLUE);
					break;
				case "K":
					subPlateauBottomLeft[i][j]=new Case(Type.ANTISLASH_GREEN);
					break;
				case "O":
					subPlateauBottomLeft[i][j]=new Case(Type.ANTISLASH_YELLOW);
					break;

				case "H":
					subPlateauBottomLeft[i][j]=new Case(Type.SLASH_RED);
					break;
				case "L":
					subPlateauBottomLeft[i][j]=new Case(Type.SLASH_BLUE);
					break;
				case "J":
					subPlateauBottomLeft[i][j]=new Case(Type.SLASH_GREEN);
					break;
				case "N":
					subPlateauBottomLeft[i][j]=new Case(Type.SLASH_YELLOW);
					break;
				}

			}
		}
		// rotate the bottom right
		// subPlateau by 180°
		// no need to switch the slash by an
		// anti-slash because it's a 180° rotation

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				subPlateauBottomRight[i][j] = subPlateauBottomRightTemp[8 - i][8 - j];
			}
		}



		Case[][] concatenationOfAllSubPlateau = new Case[18][18];

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				concatenationOfAllSubPlateau[i][j] = subPlateauTopLeft[i][j]; // place the subPlateau in the correct
																				// place in the plateau
				concatenationOfAllSubPlateau[i][j+9] = subPlateauTopRight[i][j];
				concatenationOfAllSubPlateau[i+9][j] = subPlateauBottomLeft[i][j];
				concatenationOfAllSubPlateau[i+9][j+9] = subPlateauBottomRight[i][j];
			}
		}

		if (debug) {
			System.out.println("concatenation :");
			this.printPlateau(concatenationOfAllSubPlateau);
		}

		return concatenationOfAllSubPlateau;
	}

}
