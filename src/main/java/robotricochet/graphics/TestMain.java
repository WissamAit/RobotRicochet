package robotricochet.graphics;

import robotricochet.services.Game;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
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

public class TestMain extends Application {

	private static final boolean debug = true; // debug value to test program


	public static void main(String[] args) {

		Application.launch(TestMain.class, args);
	}

	@Override
	public void start(Stage primaryStage) throws FileNotFoundException, IOException, IllegalArgumentException {

		//plateauView.

		////////////////////////////////////////
		primaryStage.setTitle("RicochetRobot Game");
		Group root = new Group();
		Scene scene = new Scene(root, 900, 660);


		//Game game = new Game();
		//game.initGame();
		//game.play();


		ImageView backGroundImage = new ImageView(
				new Image(new FileInputStream("src/main/resources/images/ricochet-robots-board.jpg")));
		backGroundImage.setFitHeight(1400);
		backGroundImage.setFitWidth(900);
		backGroundImage.setPreserveRatio(true);

		Text gameTitle = new Text("Ricochet Robot");
		gameTitle.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 40));
		gameTitle.setX(20);
		gameTitle.setY(70);
		gameTitle.setFill(Color.YELLOW); // Setting the color
		gameTitle.setStrokeWidth(2); // Setting the Stroke
		gameTitle.setStroke(Color.BROWN); // Setting the stroke color

		Button playButton = new Button("New Game");
		playButton.setLayoutX(720);
		playButton.setLayoutY(450);
		playButton.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
		playButton.setStyle("-fx-background-color: transparent;-fx-text-fill: yellow;");

		Button rulesButton = new Button("Rules");
		rulesButton.setLayoutX(720);
		rulesButton.setLayoutY(480);
		rulesButton.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
		rulesButton.setStyle("-fx-background-color: transparent; -fx-text-fill: yellow;");

		Button exitButton = new Button("Exit");
		exitButton.setLayoutX(720);
		exitButton.setLayoutY(510);
		exitButton.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
		exitButton.setStyle("-fx-background-color: transparent; -fx-text-fill: yellow;");

		Shadow shadow = new Shadow();

		// Adding the shadow when the mouse cursor is on ( 1st button)
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
		// Adding the shadow when the mouse cursor is on ( 2nd button)
		rulesButton.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {

				rulesButton.setEffect(shadow);
			}
		});

		// Removing the shadow when the mouse cursor is off ( 2nd button)
		rulesButton.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				rulesButton.setEffect(null);
			}
		});
		// Adding the shadow when the mouse cursor is on ( 3rd button)
		exitButton.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {

				exitButton.setEffect(shadow);
			}
		});

		// Removing the shadow when the mouse cursor is off (3rd button)
		exitButton.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				exitButton.setEffect(null);
			}
		});


		GridPane grid=new GridPane(); grid.add(backGroundImage,0,0);
		grid.add(gameTitle, 0, 0, 2, 1); grid.add(playButton, 1, 1);
		//grid.add(introductionButton, 1, 1); grid.add(exitButton, 1, 1);
		grid.setHgap(25); grid.setVgap(20);

		playButton.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
				// TODO : play game
				try {
					showDialog();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});

		rulesButton.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
				// TODO : show game rules
				try {
					showRules();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});

		exitButton.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
				primaryStage.close(); // close window
			}
		});


		root.getChildren().addAll(backGroundImage, gameTitle, playButton, rulesButton, exitButton);
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();

		}



/////////////////////////////////////////





		private void showDialog() throws IOException{
		Stage primaryStage=new Stage();
		primaryStage.setResizable(false);
		primaryStage.setTitle("Ricochet robot solver");
		GridPane gridPane = new GridPane();
		Game game = new Game();
		game.initGame();
		Image image = null;
		ImageView imageview = null;
		gridPane.setHgap(2);
		gridPane.setVgap(2);
		for (int i = 0; i < game.plateau.plateau.length; i++) {
			for (int j = 0; j < game.plateau.plateau.length; j++) {
				switch (game.plateau.plateau[i][j].getType()) {
					case OBSTACLE:
						image = new Image(new FileInputStream("src/main/resources/images/OBSTACLE.jpg"));
						imageview = new ImageView(image);
                        imageview.setFitHeight(33);
                        imageview.setFitWidth(33);
						gridPane.add(imageview, j, i);
						image = null;
						imageview = null;
						break;

					case EMPTYSPACE:
						image = new Image(new FileInputStream("src/main/resources/images/EMPTYSPACE.jpg"));
						imageview = new ImageView(image);
						imageview.setFitHeight(33);
						imageview.setFitWidth(33);
						gridPane.add(imageview, j, i);
						image = null;
						imageview = null;
						break;

					case RED_ROBOT_START:
						image = new Image(new FileInputStream("src/main/resources/images/REDROBOTSTART.jpg"));
						imageview = new ImageView(image);
						imageview.setFitHeight(33);
						imageview.setFitWidth(33);
						gridPane.add(imageview, j, i);
						image = null;
						imageview = null;
						break;


					case GREEN_ROBOT_START:
						image = new Image(new FileInputStream("src/main/resources/images/GREENROBOTSTART.jpg"));
						imageview = new ImageView(image);
						imageview.setFitHeight(33);
						imageview.setFitWidth(33);
						gridPane.add(imageview, j, i);
						image = null;
						imageview = null;
						break;


					case BLUE_ROBOT_START:
						image = new Image(new FileInputStream("src/main/resources/images/BLUEROBOTSTART.jpg"));
						imageview = new ImageView(image);
						imageview.setFitHeight(33);
						imageview.setFitWidth(33);
						gridPane.add(imageview, j, i);
						image = null;
						imageview = null;
						break;


					case YELLOW_ROBOT_START:
						image = new Image(new FileInputStream("src/main/resources/images/YELLOWROBOTSTART.jpg"));
						imageview = new ImageView(image);
						imageview.setFitHeight(33);
						imageview.setFitWidth(33);
						gridPane.add(imageview, j, i);
						image = null;
						imageview = null;

						break;


					case YELLOW_CIRCLE:
						image = new Image(new FileInputStream("src/main/resources/images/YELLOWCIRCLE.jpg"));
						imageview = new ImageView(image);
						imageview.setFitHeight(33);
						imageview.setFitWidth(33);
						gridPane.add(imageview, j, i);
						image = null;
						imageview = null;
						break;


					case RED_CIRCLE:
						image = new Image(new FileInputStream("src/main/resources/images/REDCIRCLE.jpg"));
						imageview = new ImageView(image);
						imageview.setFitHeight(33);
						imageview.setFitWidth(33);
						gridPane.add(imageview, j, i);
						image = null;
						imageview = null;
						break;

					case GREEN_CIRCLE:
						image = new Image(new FileInputStream("src/main/resources/images/GREENCIRCLE.jpg"));
						imageview = new ImageView(image);
						imageview.setFitHeight(33);
						imageview.setFitWidth(33);
						gridPane.add(imageview, j, i);
						image = null;
						imageview = null;
						break;


					case BLUE_CIRCLE:
						image = new Image(new FileInputStream("src/main/resources/images/BLUECIRCLE.jpg"));
						imageview = new ImageView(image);
						imageview.setFitHeight(33);
						imageview.setFitWidth(33);
						gridPane.add(imageview, j, i);
						image = null;
						imageview = null;
						break;

					case BLUE_SQUARE:
						image = new Image(new FileInputStream("src/main/resources/images/BLUESQUARE.jpg"));
						imageview = new ImageView(image);
						imageview.setFitHeight(33);
						imageview.setFitWidth(33);
						gridPane.add(imageview, j, i);
						image = null;
						imageview = null;
						break;


					case YELLOW_SQUARE:
						image = new Image(new FileInputStream("src/main/resources/images/YELLOWSQUARE.jpg"));
						imageview = new ImageView(image);
						imageview.setFitHeight(33);
						imageview.setFitWidth(33);
						gridPane.add(imageview, j, i);
						image = null;
						imageview = null;
						break;


					case RED_SQUARE:
						image = new Image(new FileInputStream("src/main/resources/images/REDSQUARE.jpg"));
						imageview = new ImageView(image);
						imageview.setFitHeight(33);
						imageview.setFitWidth(33);
						gridPane.add(imageview, j, i);
						image = null;
						imageview = null;
						break;


					case GREEN_SQUARE:
						image = new Image(new FileInputStream("src/main/resources/images/GREENSQUARE.jpg"));
						imageview = new ImageView(image);
						imageview.setFitHeight(33);
						imageview.setFitWidth(33);
						gridPane.add(imageview, j, i);
						image = null;
						imageview = null;
						break;


					case BLUE_TRIANGLE:
						image = new Image(new FileInputStream("src/main/resources/images/BLUETRIANGLE.jpg"));
						imageview = new ImageView(image);
						imageview.setFitHeight(33);
						imageview.setFitWidth(33);
						gridPane.add(imageview, j, i);
						image = null;
						imageview = null;
						break;


					case YELLOW_TRIANGLE:
						image = new Image(new FileInputStream("src/main/resources/images/YELLOWTRIANGLE.jpg"));
						imageview = new ImageView(image);
						imageview.setFitHeight(33);
						imageview.setFitWidth(33);
						gridPane.add(imageview, j, i);
						image = null;
						imageview = null;
						break;


					case GREEN_TRIANGLE:
						image = new Image(new FileInputStream("src/main/resources/images/GREENTRIANGLE.jpg"));
						imageview = new ImageView(image);
						imageview.setFitHeight(33);
						imageview.setFitWidth(33);
						gridPane.add(imageview, j, i);
						image = null;
						imageview = null;
						break;

					case RED_TRIANGLE:
						image = new Image(new FileInputStream("src/main/resources/images/REDTRIANGLE.jpg"));
						imageview = new ImageView(image);
						imageview.setFitHeight(33);
						imageview.setFitWidth(33);
						gridPane.add(imageview, j, i);
						image = null;
						imageview = null;
						break;

					case RED_DIAMOND:
						image = new Image(new FileInputStream("src/main/resources/images/REDDIAMOND.jpg"));
						imageview = new ImageView(image);
						imageview.setFitHeight(33);
						imageview.setFitWidth(33);
						gridPane.add(imageview, j, i);
						image = null;
						imageview = null;
						break;


					case YELLOW_DIAMOND:
						image = new Image(new FileInputStream("src/main/resources/images/YELLOWDIAMOND.jpg"));
						imageview = new ImageView(image);
						imageview.setFitHeight(33);
						imageview.setFitWidth(33);
						gridPane.add(imageview, j, i);
						image = null;
						imageview = null;
						break;

					case GREEN_DIAMOND:
						image = new Image(new FileInputStream("src/main/resources/images/GREENDIAMOND.jpg"));
						imageview = new ImageView(image);
						imageview.setFitHeight(33);
						imageview.setFitWidth(33);
						gridPane.add(imageview, j, i);
						image = null;
						imageview = null;
						break;

					case BLUE_DIAMOND:
						image = new Image(new FileInputStream("src/main/resources/images/BLUEDIAMOND.jpg"));
						imageview = new ImageView(image);
						imageview.setFitHeight(33);
						imageview.setFitWidth(33);
						gridPane.add(imageview, j, i);
						image = null;
						imageview = null;
						break;

					case SLASH_BLUE:
						image = new Image(new FileInputStream("src/main/resources/images/BLUESLASH.jpg"));
						imageview = new ImageView(image);
						imageview.setFitHeight(33);
						imageview.setFitWidth(33);
						gridPane.add(imageview, j, i);
						image = null;
						imageview = null;
						break;

					case SLASH_GREEN:
						image = new Image(new FileInputStream("src/main/resources/images/GREENSLASH.jpg"));
						imageview = new ImageView(image);
						imageview.setFitHeight(33);
						imageview.setFitWidth(33);
						gridPane.add(imageview, j, i);
						image = null;
						imageview = null;
						break;


					case SLASH_RED:
						image = new Image(new FileInputStream("src/main/resources/images/REDSLASH.jpg"));
						imageview = new ImageView(image);
						imageview.setFitHeight(33);
						imageview.setFitWidth(33);
						gridPane.add(imageview, j, i);
						image = null;
						imageview = null;
						break;

					case SLASH_YELLOW:
						image = new Image(new FileInputStream("src/main/resources/images/YELLOWSLASH.jpg"));
						imageview = new ImageView(image);
						imageview.setFitHeight(33);
						imageview.setFitWidth(33);
						gridPane.add(imageview, j, i);
						image = null;
						imageview = null;
						break;

					case ANTISLASH_BLUE:
						image = new Image(new FileInputStream("src/main/resources/images/BLUEANTISLASH.jpg"));
						imageview = new ImageView(image);
						imageview.setFitHeight(33);
						imageview.setFitWidth(33);
						gridPane.add(imageview, j, i);
						image = null;
						imageview = null;
						break;

					case ANTISLASH_GREEN:
						image = new Image(new FileInputStream("src/main/resources/images/GREENANTISLASH.jpg"));
						imageview = new ImageView(image);
						imageview.setFitHeight(33);
						imageview.setFitWidth(33);
						gridPane.add(imageview, j, i);
						image = null;
						imageview = null;
						break;


					case ANTISLASH_YELLOW:
						image = new Image(new FileInputStream("src/main/resources/images/YELLOWANTISLASH.jpg"));
						imageview = new ImageView(image);
						imageview.setFitHeight(33);
						imageview.setFitWidth(33);
						gridPane.add(imageview, j, i);
						image = null;
						imageview = null;
						break;

					case MULTICOLOR_VORTEX:
						image = new Image(new FileInputStream("src/main/resources/images/MULTICOLORVORTEX.jpg"));
						imageview = new ImageView(image);
						imageview.setFitHeight(33);
						imageview.setFitWidth(33);
						gridPane.add(imageview, j, i);
						image = null;
						imageview = null;
						break;
					case ANTISLASH_RED:
						image = new Image(new FileInputStream("src/main/resources/images/REDANTISLASH.jpg"));
						imageview = new ImageView(image);
						imageview.setFitHeight(34);
						imageview.setFitWidth(34);
						gridPane.add(imageview, j, i);
						image = null;
						imageview = null;
						break;

					default:

						break;

				}
			}
		}


			Scene scene = new Scene(gridPane, 900, 640, Color.SANDYBROWN);
			primaryStage.setScene(scene);
			primaryStage.show();

		}

		private void showRules() throws IOException {
			Stage primaryStage=new Stage();
			primaryStage.setResizable(false);
			primaryStage.setTitle("Ricochet robot solver rules");

			//read the file rules.txt which contains the rules of the game
			InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream("src/main/resources/rules.txt"));
			BufferedReader bufferReader = new BufferedReader(inputStreamReader);
			String line;
			String str=" ";
			while ((line = bufferReader.readLine()) != null){
			str =str+line+"\n";//we add the lines one by one to the variable str to create one string to create a label after this
			}

			//create a scroll pane horizontal
			ScrollPane scrollPane=new ScrollPane();
			scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
			//create a label which is the contenent of the file readen
			Label label=new Label(str);
			scrollPane.setContent(label);
			Scene scene = new Scene(scrollPane, 900, 660, Color.SANDYBROWN);
			primaryStage.setScene(scene);
			primaryStage.show();
		}
		}



