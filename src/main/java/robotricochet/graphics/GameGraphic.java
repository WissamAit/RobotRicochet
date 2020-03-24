package robotricochet.graphics;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.effect.Shadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import robotricochet.entity.CaseType;
import robotricochet.entity.Position;
import robotricochet.entity.Robot;
import robotricochet.entity.Token;
import robotricochet.services.GameBuilder;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

public class GameGraphic {

    CaseType drownToken = Token.getTokenCaseType();

    public static void placeRobotButton(GridPane gridPane, GameBuilder game, Shadow shadow) {
        Button placeRobot = new Button("Place Robots");
        placeRobot.setLayoutX(100);
        placeRobot.setLayoutY(450);
        placeRobot.setPrefWidth(120);
        placeRobot.setPrefHeight(40);
        placeRobot.setStyle("-fx-background-color: yellow; -fx-text-fill: black;");
        placeRobot.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {

                placeRobot.setEffect(shadow);
            }
        });
        gridPane.add(placeRobot, 50, 5);

        placeRobot.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<>() {
            @Override
            public void handle(MouseEvent e) {
                placeRobot.setEffect(null);
            }
        })
        ;
        placeRobot.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                try {
                    putRobots(gridPane, game);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    public void selectedTargetButton(GridPane gridPane, GameBuilder game, Shadow shadow) {
        Button target = new Button("Select a target");
        target.setLayoutX(100);
        target.setLayoutY(450);
        target.setPrefWidth(120);
        target.setPrefHeight(40);
        target.setStyle("-fx-background-color: yellow; -fx-text-fill: black;");
        target.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {

                target.setEffect(shadow);
            }
        });
        gridPane.add(target, 50, 7);

        target.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<>() {
            @Override
            public void handle(MouseEvent e) {
                target.setEffect(null);
            }
        })
        ;
        target.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                selectedTarget(game, drownToken, gridPane);
            }
        });
    }

    public void playButton(GridPane gridPane, GameBuilder game, Shadow shadow) {
        Button play = new Button("play");
        play.setLayoutX(100);
        play.setLayoutY(450);
        play.setPrefWidth(120);
        play.setPrefHeight(40);
        play.setStyle("-fx-background-color: yellow; -fx-text-fill: black;");
        play.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {

                play.setEffect(shadow);
            }
        });
        gridPane.add(play, 50, 9);

       play.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<>() {
            @Override
            public void handle(MouseEvent e) {
                play.setEffect(null);
            }
        })
        ;
       play.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                try {
                    play(gridPane, drownToken, game);
                } catch (FileNotFoundException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static void putRobots(GridPane pane, GameBuilder game) throws FileNotFoundException {
        Image image = null;
        ImageView imageview = null;
        for (int i = 0; i < game.getPlateau().getPlateau().length; i++) {
            for (int j = 0; j < game.getPlateau().getPlateau().length; j++) {
                switch (game.getPlateau().getPlateau()[i][j].getCaseType()) {
                    case BLUE_ROBOT_START:
                        image = new Image(new FileInputStream("src/main/resources/images/BLUE_ROBOT.jpg"));
                        imageview = new ImageView(image);
                        imageview.setFitHeight(33);
                        imageview.setFitWidth(33);
                        pane.add(imageview, j, i);
                        break;

                    case RED_ROBOT_START:
                        image = new Image(new FileInputStream("src/main/resources/images/RED_ROBOT.JPG"));
                        imageview = new ImageView(image);
                        imageview.setFitHeight(33);
                        imageview.setFitWidth(33);
                        pane.add(imageview, j, i);

                        break;

                    case YELLOW_ROBOT_START:
                        image = new Image(new FileInputStream("src/main/resources/images/YELLOW_ROBOT.jpg"));
                        imageview = new ImageView(image);
                        imageview.setFitHeight(33);
                        imageview.setFitWidth(33);
                        pane.add(imageview, j, i);
                        break;


                    case GREEN_ROBOT_START:
                        image = new Image(new FileInputStream("src/main/resources/images/GREEN_ROBOT.jpg"));
                        imageview = new ImageView(image);
                        imageview.setFitHeight(33);
                        imageview.setFitWidth(33);
                        pane.add(imageview, j, i);

                        break;

                    default:
                        break;
                }
            }
        }


    }


    public static void selectedTarget(GameBuilder game, CaseType drownToken, GridPane gridPane) {
        Position position = game.getPlateau().searchPositionOf(drownToken);
        Rectangle rectangle = new Rectangle(33, 33);
        rectangle.setStroke(Color.PURPLE);
        rectangle.setFill(Color.rgb(255, 0, 255, 0.4));
        gridPane.add(rectangle, position.getY(), position.getX());
    }


    public static void play(GridPane gridPane, CaseType drownToken, GameBuilder game) throws FileNotFoundException, InterruptedException {
        Robot currentRobot = game.currentRobot(drownToken);
        currentRobot.injectCurrentGrid(game.getPlateau());
        List<Position> shortestPath = currentRobot.aStar(currentRobot, drownToken);
        String robotImageString = robotColorImages(currentRobot).get(0);
        String startingCase = robotColorImages(currentRobot).get(1);
        final String STR = "src/main/resources/images/";
        Image image = new Image(new FileInputStream(STR + robotImageString));
        ImageView imageview = new ImageView(image);
        imageview.setFitHeight(32);
        imageview.setFitWidth(32);
        Image image2 = new Image(new FileInputStream(STR + robotImageString));
        ImageView imageview2;
        Image img = new Image(new FileInputStream(STR + startingCase));
        ImageView iv = new ImageView(img);
        iv.setFitHeight(32);
        iv.setFitWidth(32);
        if (!shortestPath.isEmpty()) {
            gridPane.add(iv, shortestPath.get(0).getY(), shortestPath.get(0).getX());
            for (int i = 1; i < shortestPath.size(); i++) {
                Position currentPosition = shortestPath.get(i);
                imageview2 = new ImageView(image2);
                imageview2.setFitHeight(32);
                imageview2.setFitWidth(32);
                Timer myTimer = new Timer();
                gridPane.add(imageview2, currentPosition.getY(), currentPosition.getX());


            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Is there any path to reach the selected target?");
            String s = "Sorry :) No path found, please exit and restart the game ";
            alert.setContentText(s);
            alert.show();
        }
    }


    private static List<String> robotColorImages(Robot robot) {
        List<String> arrayOfImages = new ArrayList<>();
        robotricochet.entity.Color robotColor = robot.getColor();
        switch (robotColor) {
            case RED:

                arrayOfImages.add("RED_ROBOT.jpg");
                arrayOfImages.add("REDROBOTSTART.jpg");
                break;
            case BLUE:

                arrayOfImages.add("BLUE_ROBOT.jpg");
                arrayOfImages.add("BLUEROBOTSTART.jpg");
                break;
            case GREEN:

                arrayOfImages.add("GREEN_ROBOT.jpg");
                arrayOfImages.add("GREENROBOTSTART.jpg");
                break;
            case YELLOW:

                arrayOfImages.add("YELLOW_ROBOT.jpg");
                arrayOfImages.add("YELLOWROBOTSTART.jpg");
                break;
            default:
                break;
        }
        return arrayOfImages;
    }

}
