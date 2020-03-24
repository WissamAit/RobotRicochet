package robotricochet.graphics;

import javafx.scene.Scene;
import javafx.scene.effect.Shadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import robotricochet.services.GameBuilder;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import static javafx.scene.paint.Color.SANDYBROWN;

public class GridBuilder {

    public static void showDialog() throws IOException, NoSuchAlgorithmException {
        Stage primaryStage = new Stage();
        primaryStage.setResizable(false);
        primaryStage.setTitle("Ricochet robot solver");
        GridPane gridPane = new GridPane();
        GameBuilder game = new GameBuilder();
        GameGraphic gameGraphic = new GameGraphic();
        Image image = null;
        ImageView imageview = null;
        gridPane.setHgap(2);
        gridPane.setVgap(2);
        Shadow shadow = new Shadow();
        for (int i = 0; i < game.getPlateau().getPlateau().length; i++) {
            for (int j = 0; j < game.getPlateau().getPlateau().length; j++) {
                switch (game.getPlateau().getPlateau()[i][j].getCaseType()) {
                    case OBSTACLE:
                        image = new Image(new FileInputStream("src/main/resources/images/OBSTACLE.jpg"));
                        imageview = new ImageView(image);
                        imageview.setFitHeight(33);
                        imageview.setFitWidth(33);
                        gridPane.add(imageview, j, i);
                        break;

                    case EMPTYSPACE:
                        image = new Image(new FileInputStream("src/main/resources/images/EMPTYSPACE.jpg"));
                        imageview = new ImageView(image);
                        imageview.setFitHeight(33);
                        imageview.setFitWidth(33);
                        gridPane.add(imageview, j, i);

                        break;

                    case RED_ROBOT_START:
                        image = new Image(new FileInputStream("src/main/resources/images/REDROBOTSTART.jpg"));
                        imageview = new ImageView(image);
                        imageview.setFitHeight(33);
                        imageview.setFitWidth(33);
                        gridPane.add(imageview, j, i);
                        break;


                    case GREEN_ROBOT_START:
                        image = new Image(new FileInputStream("src/main/resources/images/GREENROBOTSTART.jpg"));
                        imageview = new ImageView(image);
                        imageview.setFitHeight(33);
                        imageview.setFitWidth(33);
                        gridPane.add(imageview, j, i);

                        break;


                    case BLUE_ROBOT_START:
                        image = new Image(new FileInputStream("src/main/resources/images/BLUEROBOTSTART.jpg"));
                        imageview = new ImageView(image);
                        imageview.setFitHeight(33);
                        imageview.setFitWidth(33);
                        gridPane.add(imageview, j, i);

                        break;


                    case YELLOW_ROBOT_START:
                        image = new Image(new FileInputStream("src/main/resources/images/YELLOWROBOTSTART.jpg"));
                        imageview = new ImageView(image);
                        imageview.setFitHeight(33);
                        imageview.setFitWidth(33);
                        gridPane.add(imageview, j, i);


                        break;


                    case YELLOW_CIRCLE:
                        image = new Image(new FileInputStream("src/main/resources/images/YELLOWCIRCLE.jpg"));
                        imageview = new ImageView(image);
                        imageview.setFitHeight(33);
                        imageview.setFitWidth(33);
                        gridPane.add(imageview, j, i);

                        break;


                    case RED_CIRCLE:
                        image = new Image(new FileInputStream("src/main/resources/images/REDCIRCLE.jpg"));
                        imageview = new ImageView(image);
                        imageview.setFitHeight(33);
                        imageview.setFitWidth(33);
                        gridPane.add(imageview, j, i);

                        break;

                    case GREEN_CIRCLE:
                        image = new Image(new FileInputStream("src/main/resources/images/GREENCIRCLE.jpg"));
                        imageview = new ImageView(image);
                        imageview.setFitHeight(33);
                        imageview.setFitWidth(33);
                        gridPane.add(imageview, j, i);

                        break;


                    case BLUE_CIRCLE:
                        image = new Image(new FileInputStream("src/main/resources/images/BLUECIRCLE.jpg"));
                        imageview = new ImageView(image);
                        imageview.setFitHeight(33);
                        imageview.setFitWidth(33);
                        gridPane.add(imageview, j, i);

                        break;

                    case BLUE_SQUARE:
                        image = new Image(new FileInputStream("src/main/resources/images/BLUESQUARE.jpg"));
                        imageview = new ImageView(image);
                        imageview.setFitHeight(33);
                        imageview.setFitWidth(33);
                        gridPane.add(imageview, j, i);

                        break;


                    case YELLOW_SQUARE:
                        image = new Image(new FileInputStream("src/main/resources/images/YELLOWSQUARE.jpg"));
                        imageview = new ImageView(image);
                        imageview.setFitHeight(33);
                        imageview.setFitWidth(33);
                        gridPane.add(imageview, j, i);

                        break;


                    case RED_SQUARE:
                        image = new Image(new FileInputStream("src/main/resources/images/REDSQUARE.jpg"));
                        imageview = new ImageView(image);
                        imageview.setFitHeight(33);
                        imageview.setFitWidth(33);
                        gridPane.add(imageview, j, i);

                        break;


                    case GREEN_SQUARE:
                        image = new Image(new FileInputStream("src/main/resources/images/GREENSQUARE.jpg"));
                        imageview = new ImageView(image);
                        imageview.setFitHeight(33);
                        imageview.setFitWidth(33);
                        gridPane.add(imageview, j, i);

                        break;


                    case BLUE_TRIANGLE:
                        image = new Image(new FileInputStream("src/main/resources/images/BLUETRIANGLE.jpg"));
                        imageview = new ImageView(image);
                        imageview.setFitHeight(33);
                        imageview.setFitWidth(33);
                        gridPane.add(imageview, j, i);

                        break;


                    case YELLOW_TRIANGLE:
                        image = new Image(new FileInputStream("src/main/resources/images/YELLOWTRIANGLE.jpg"));
                        imageview = new ImageView(image);
                        imageview.setFitHeight(33);
                        imageview.setFitWidth(33);
                        gridPane.add(imageview, j, i);

                        break;


                    case GREEN_TRIANGLE:
                        image = new Image(new FileInputStream("src/main/resources/images/GREENTRIANGLE.jpg"));
                        imageview = new ImageView(image);
                        imageview.setFitHeight(33);
                        imageview.setFitWidth(33);
                        gridPane.add(imageview, j, i);

                        break;

                    case RED_TRIANGLE:
                        image = new Image(new FileInputStream("src/main/resources/images/REDTRIANGLE.jpg"));
                        imageview = new ImageView(image);
                        imageview.setFitHeight(33);
                        imageview.setFitWidth(33);
                        gridPane.add(imageview, j, i);


                        break;

                    case RED_DIAMOND:
                        image = new Image(new FileInputStream("src/main/resources/images/REDDIAMOND.jpg"));
                        imageview = new ImageView(image);
                        imageview.setFitHeight(33);
                        imageview.setFitWidth(33);
                        gridPane.add(imageview, j, i);


                        break;


                    case YELLOW_DIAMOND:
                        image = new Image(new FileInputStream("src/main/resources/images/YELLOWDIAMOND.jpg"));
                        imageview = new ImageView(image);
                        imageview.setFitHeight(33);
                        imageview.setFitWidth(33);
                        gridPane.add(imageview, j, i);


                        break;

                    case GREEN_DIAMOND:
                        image = new Image(new FileInputStream("src/main/resources/images/GREENDIAMOND.jpg"));
                        imageview = new ImageView(image);
                        imageview.setFitHeight(33);
                        imageview.setFitWidth(33);
                        gridPane.add(imageview, j, i);


                        break;

                    case BLUE_DIAMOND:
                        image = new Image(new FileInputStream("src/main/resources/images/BLUEDIAMOND.jpg"));
                        imageview = new ImageView(image);
                        imageview.setFitHeight(33);
                        imageview.setFitWidth(33);
                        gridPane.add(imageview, j, i);


                        break;

                    case SLASH_BLUE:
                        image = new Image(new FileInputStream("src/main/resources/images/BLUESLASH.jpg"));
                        imageview = new ImageView(image);
                        imageview.setFitHeight(33);
                        imageview.setFitWidth(33);
                        gridPane.add(imageview, j, i);

                        break;

                    case SLASH_GREEN:
                        image = new Image(new FileInputStream("src/main/resources/images/GREENSLASH.jpg"));
                        imageview = new ImageView(image);
                        imageview.setFitHeight(33);
                        imageview.setFitWidth(33);
                        gridPane.add(imageview, j, i);

                        break;


                    case SLASH_RED:
                        image = new Image(new FileInputStream("src/main/resources/images/REDSLASH.jpg"));
                        imageview = new ImageView(image);
                        imageview.setFitHeight(33);
                        imageview.setFitWidth(33);
                        gridPane.add(imageview, j, i);

                        break;

                    case SLASH_YELLOW:
                        image = new Image(new FileInputStream("src/main/resources/images/YELLOWSLASH.jpg"));
                        imageview = new ImageView(image);
                        imageview.setFitHeight(33);
                        imageview.setFitWidth(33);
                        gridPane.add(imageview, j, i);

                        break;

                    case ANTISLASH_BLUE:
                        image = new Image(new FileInputStream("src/main/resources/images/BLUEANTISLASH.jpg"));
                        imageview = new ImageView(image);
                        imageview.setFitHeight(33);
                        imageview.setFitWidth(33);
                        gridPane.add(imageview, j, i);

                        break;

                    case ANTISLASH_GREEN:
                        image = new Image(new FileInputStream("src/main/resources/images/GREENANTISLASH.jpg"));
                        imageview = new ImageView(image);
                        imageview.setFitHeight(33);
                        imageview.setFitWidth(33);
                        gridPane.add(imageview, j, i);

                        break;


                    case ANTISLASH_YELLOW:
                        image = new Image(new FileInputStream("src/main/resources/images/YELLOWANTISLASH.jpg"));
                        imageview = new ImageView(image);
                        imageview.setFitHeight(33);
                        imageview.setFitWidth(33);
                        gridPane.add(imageview, j, i);

                        break;

                    case MULTICOLOR_VORTEX:
                        image = new Image(new FileInputStream("src/main/resources/images/MULTICOLORVORTEX.jpg"));
                        imageview = new ImageView(image);
                        imageview.setFitHeight(33);
                        imageview.setFitWidth(33);
                        gridPane.add(imageview, j, i);

                        break;
                    case ANTISLASH_RED:
                        image = new Image(new FileInputStream("src/main/resources/images/REDANTISLASH.jpg"));
                        imageview = new ImageView(image);
                        imageview.setFitHeight(34);
                        imageview.setFitWidth(34);
                        gridPane.add(imageview, j, i);

                        break;

                    default:

                        break;

                }
            }
            GameGraphic.placeRobotButton(gridPane, game, shadow);
            gameGraphic.selectedTargetButton(gridPane,game,shadow);
            gameGraphic.playButton(gridPane,game,shadow);
        }


        gridPane.setStyle("-fx-background-color:#582900;");
        Scene scene = new Scene(gridPane, 900, 640);
        primaryStage.setScene(scene);
        primaryStage.show();

    }


}
