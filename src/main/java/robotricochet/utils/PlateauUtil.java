package robotricochet.utils;

import robotricochet.entity.Case;
import robotricochet.entity.Type;

import java.util.HashMap;
import java.util.Map;

public class PlateauUtil {

    private PlateauUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static void parsePlaneCharacters(Case[][] subPlateau, int x, String[] charactersInLine, int y) {

        Map<String, Case> casesMap = loadTypeCases();
        subPlateau[x][y] = casesMap.get(charactersInLine[y]);

    }

    private static Map<String, Case> loadTypeCases() {
        Map<String, Case> casesMap = new HashMap<>();

        casesMap.put("#", new Case(Type.OBSTACLE));
        casesMap.put(" ", new Case(Type.EMPTYSPACE));
        casesMap.put("r", new Case(Type.RED_ROBOT_START));
        casesMap.put("g", new Case(Type.GREEN_ROBOT_START));
        casesMap.put("b", new Case(Type.BLUE_ROBOT_START));
        casesMap.put("y", new Case(Type.YELLOW_ROBOT_START));
        casesMap.put("0", new Case(Type.RED_CIRCLE));
        casesMap.put("1", new Case(Type.GREEN_CIRCLE));
        casesMap.put("2", new Case(Type.BLUE_CIRCLE));
        casesMap.put("3", new Case(Type.YELLOW_CIRCLE));
        casesMap.put("4", new Case(Type.RED_SQUARE));
        casesMap.put("5", new Case(Type.GREEN_SQUARE));
        casesMap.put("6", new Case(Type.BLUE_SQUARE));
        casesMap.put("7", new Case(Type.YELLOW_SQUARE));
        casesMap.put("8", new Case(Type.RED_TRIANGLE));
        casesMap.put("9", new Case(Type.GREEN_TRIANGLE));
        casesMap.put("A", new Case(Type.BLUE_TRIANGLE));
        casesMap.put("B", new Case(Type.YELLOW_TRIANGLE));
        casesMap.put("C", new Case(Type.RED_DIAMOND));
        casesMap.put("D", new Case(Type.GREEN_DIAMOND));
        casesMap.put("E", new Case(Type.BLUE_DIAMOND));
        casesMap.put("F", new Case(Type.YELLOW_DIAMOND));
        casesMap.put("G", new Case(Type.MULTICOLOR_VORTEX));
        casesMap.put("H", new Case(Type.ANTISLASH_RED));
        casesMap.put("I", new Case(Type.SLASH_RED));
        casesMap.put("J", new Case(Type.ANTISLASH_GREEN));
        casesMap.put("K", new Case(Type.SLASH_GREEN));
        casesMap.put("L", new Case(Type.ANTISLASH_BLUE));
        casesMap.put("M", new Case(Type.SLASH_BLUE));
        casesMap.put("N", new Case(Type.ANTISLASH_YELLOW));
        casesMap.put("O", new Case(Type.SLASH_YELLOW));
        return casesMap;
    }
}
