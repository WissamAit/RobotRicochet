package robotricochet.graphics;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.Shadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;
import java.security.NoSuchAlgorithmException;

public class TestMain extends Application {

    public static void main(String[] args) {
        Application.launch(TestMain.class, args);
    }

    @Override
    public void start(Stage primaryStage) throws NullPointerException {
        primaryStage.setTitle("RicochetRobot Game");
        Group root = new Group();
        Scene scene = new Scene(root, 900, 660);
        Shadow shadow = new Shadow();
        firstWindowView(primaryStage, root, scene, shadow);

    }

    private void firstWindowView(Stage primaryStage, Group root, Scene scene, Shadow shadow) {
        ImageView backGroundImage = null;
        try {
            backGroundImage = new ImageView(
                    new Image(new FileInputStream("src/main/resources/images/ricochet-robots-board.jpg")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        backGroundImage.setFitHeight(1400);
        backGroundImage.setFitWidth(900);
        backGroundImage.setPreserveRatio(true);
        Text gameTitle = new Text("Ricochet Robot Solver");
        gameTitle.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 40));
        gameTitle.setX(20);
        gameTitle.setY(70);
        gameTitle.setFill(Color.YELLOW); // Setting the color
        gameTitle.setStrokeWidth(2); // Setting the Stroke
        gameTitle.setStroke(Color.BROWN); // Setting the stroke color
        Button playButton = newGameButtoncreation(shadow);
        Button rulesButton = rulesButtonCreation(shadow);
        Button exitButton = exitButtonCreation(primaryStage, shadow);
        GridPane grid = new GridPane();
        grid.add(backGroundImage, 0, 0);
        grid.add(gameTitle, 0, 0, 2, 1);
        grid.add(playButton, 1, 1);
        grid.setHgap(25);
        grid.setVgap(20);
        root.getChildren().addAll(backGroundImage, gameTitle, playButton, rulesButton, exitButton);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private Button exitButtonCreation(Stage primaryStage, Shadow shadow) {
        Button exitButton = new Button("Exit");
        exitButton.setLayoutX(720);
        exitButton.setLayoutY(510);
        exitButton.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
        exitButton.setStyle("-fx-background-color: transparent; -fx-text-fill: yellow;");
        exitButton.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {

                exitButton.setEffect(shadow);
            }
        });

        exitButton.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                exitButton.setEffect(null);
            }
        });
        exitButton.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                primaryStage.close();
            }
        });
        return exitButton;
    }

    private Button newGameButtoncreation(Shadow shadow) {
        Button playButton = new Button("New Game");
        playButton.setLayoutX(720);
        playButton.setLayoutY(450);
        playButton.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
        playButton.setStyle("-fx-background-color: transparent;-fx-text-fill: yellow;");
        playButton.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {

                playButton.setEffect(shadow);

            }
        });

        // Removing the shadow when the mouse cursor is off ( 1st button)
        playButton.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                playButton.setEffect(null);

            }
        });
        playButton.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                // TODO : play game
                try {
                    GridBuilder.showDialog();
                } catch (IOException | NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
            }
        });
        return playButton;
    }

    private Button rulesButtonCreation(Shadow shadow) {
    	Button rulesButton = new Button("Rules");
        rulesButton.setLayoutX(720);
        rulesButton.setLayoutY(480);
        rulesButton.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
        rulesButton.setStyle("-fx-background-color: transparent; -fx-text-fill: yellow;");
        rulesButton.setOnAction(new EventHandler<ActionEvent>() {
        	public void handle(ActionEvent event) {
                try {
                    RulesBuilder.showRules();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        rulesButton.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {

                rulesButton.setEffect(shadow);
            }
        });

        rulesButton.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<>() {
            @Override
            public void handle(MouseEvent e) {
                rulesButton.setEffect(null);
            }
        });
        return rulesButton;
    }


/////////////////////////////////////////


}



