package robotricochet.tests;

import robotricochet.services.PathFinder;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

/**
 * Main
 */
public class Main {


    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {


        System.out.println("----------------PLAY:-----------------");
        PathFinder pf = new PathFinder();
        pf.play();


    }

}
