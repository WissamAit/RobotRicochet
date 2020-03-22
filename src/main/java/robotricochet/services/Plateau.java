
package robotricochet.services;

import robotricochet.config.PropertiesSingleton;
import robotricochet.entity.*;
import robotricochet.utils.PlateauUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.*;
import java.util.logging.Logger;

public class Plateau {
    private static Logger logger = Logger.getAnonymousLogger();
    private static final String SUB_PLATEAU = "subPlateau/";
    private static final boolean DEBUG = true; // debug value to test program
    public static final String FILE_PLATEAU_1 = "file.plateau1";
    public static final String FILE_PLATEAU_2 = "file.plateau2";
    public static final String FILE_PLATEAU_3 = "file.plateau3";
    public static final String FILE_PLATEAU_4 = "file.plateau4";
    private Properties propertiesConfig = PropertiesSingleton.getInstance();
    private Case[][] grid;


    public Plateau() throws IOException, NoSuchAlgorithmException {
        this.grid = this.constructPlateau();
    }


    public Position searchPositionOf(CaseType caseType) {
        for (int i = 0; i < grid[0].length; i++) {
            for (int j = 0; j < grid.length; j++) {
                if (grid[i][j].getCaseType() == caseType) {
                    return new Position(i, j);
                }
            }
        }
        return null;
    }

    public Case[][] getPlateau() {
        return grid;
    }


    public void printPlateau(Case[][] plateau) {
        int i = 0;
        while (i < plateau.length) {
            for (int j = 0; j < plateau.length; j++) {
                System.out.print(plateau[i][j]);
            }
            System.out.println("");
            i++;
        }
    }


    public void printPlateau() {
        printPlateau(this.grid);
    }


    private static Case[][] readFileSubPlateau(String fileName) throws IOException {
        Case[][] subPlateau = new Case[9][9];

        InputStream inputStream = Plateau.class.getResourceAsStream("/" + fileName);
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferReader = new BufferedReader(inputStreamReader);

        String line;
        int i = 0;
        while ((line = bufferReader.readLine()) != null) {
            String[] charactersInLine = line.split("(?!^)");

            for (int j = 0; j < charactersInLine.length; j++) {
                PlateauUtil.parsePlaneCharacters(subPlateau, i, charactersInLine, j);
            }
            i++;
        }
        inputStreamReader.close();
        return subPlateau;
    }

    public Case[][] constructPlateau() throws IOException, NoSuchAlgorithmException {
        Random rand = SecureRandom.getInstanceStrong();
        int randomNumber;
        ArrayList<String> subPlateauFiles = new ArrayList<>(Arrays.asList(
                propertiesConfig.getProperty(FILE_PLATEAU_1),
                propertiesConfig.getProperty(FILE_PLATEAU_2),
                propertiesConfig.getProperty(FILE_PLATEAU_3),
                propertiesConfig.getProperty(FILE_PLATEAU_4)));

        Case[][] subPlateauTopRight = new Case[9][9];
        Case[][] subPlateauBottomLeft = new Case[9][9];
        Case[][] subPlateauBottomRight = new Case[9][9];

        randomNumber = rand.nextInt(subPlateauFiles.size());
        Case[][] subPlateauTopLeft = this.readFileSubPlateau(SUB_PLATEAU + subPlateauFiles.remove(randomNumber));

        randomNumber = rand.nextInt(subPlateauFiles.size());
        Case[][] subPlateauTopRightTemp = this.readFileSubPlateau(SUB_PLATEAU + subPlateauFiles.remove(randomNumber));


        randomNumber = rand.nextInt(subPlateauFiles.size());
        Case[][] subPlateauBottomLeftTemp = this.readFileSubPlateau(SUB_PLATEAU + subPlateauFiles.remove(randomNumber));


        randomNumber = rand.nextInt(subPlateauFiles.size());
        Case[][] subPlateauBottomRightTemp = this.readFileSubPlateau(SUB_PLATEAU + subPlateauFiles.remove(randomNumber));

        finalSubPlateauRotated(subPlateauTopRight, subPlateauBottomLeft, subPlateauBottomRight, subPlateauTopRightTemp, subPlateauBottomLeftTemp, subPlateauBottomRightTemp);
        return concatenateSubPlateau(subPlateauTopLeft, subPlateauTopRight, subPlateauBottomLeft, subPlateauBottomRight);
    }

    private void finalSubPlateauRotated(Case[][] subPlateauTopRight, Case[][] subPlateauBottomLeft, Case[][] subPlateauBottomRight, Case[][] subPlateauTopRightTemp, Case[][] subPlateauBottomLeftTemp, Case[][] subPlateauBottomRightTemp) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                subPlateauTopRight[i][j] = subPlateauTopRightTemp[i][(8 - j)];
                subPlateauBottomLeft[i][j] = subPlateauBottomLeftTemp[8 - i][j];
                subPlateauBottomRight[i][j] = subPlateauBottomRightTemp[8 - i][8 - j];
                PlateauUtil.subPlateauRotation(subPlateauTopRight, i, j);
                PlateauUtil.subPlateauRotation(subPlateauBottomLeft, i, j);
                PlateauUtil.subPlateauRotation(subPlateauBottomRight, i, j);
            }
        }
    }

    private Case[][] concatenateSubPlateau(Case[][] subPlateauTopLeft, Case[][] subPlateauTopRight, Case[][] subPlateauBottomLeft, Case[][] subPlateauBottomRight) {
        Case[][] concatenationOfAllSubPlateau = new Case[18][18];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                concatenationOfAllSubPlateau[i][j] = subPlateauTopLeft[i][j];
                concatenationOfAllSubPlateau[i][j + 9] = subPlateauTopRight[i][j];
                concatenationOfAllSubPlateau[i + 9][j] = subPlateauBottomLeft[i][j];
                concatenationOfAllSubPlateau[i + 9][j + 9] = subPlateauBottomRight[i][j];
            }
        }

        if (DEBUG) {
            logger.info("Plateau final : ");
            this.printPlateau(concatenationOfAllSubPlateau);
        }
        return concatenationOfAllSubPlateau;
    }

}
