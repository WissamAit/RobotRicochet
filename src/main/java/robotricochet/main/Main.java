package robotricochet.main;

import robotricochet.services.Game;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

/**
 * Main
 */
public class Main {


    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {


        System.out.println("----------------PLAY:-----------------");
        Game game =new Game();
        game.play();


      /* GameBuilder gameb= new GameBuilder();
       System.out.println(gameb.getPlateau().getRobotOnPlateau(Color.YELLOW).getPosition());
        Robot robot = new Robot(Color.YELLOW,gameb.getPlateau().getRobotOnPlateau(Color.YELLOW).getPosition());
        System.out.println(gameb.getPossibleMoves(robot));*/
    }

}
