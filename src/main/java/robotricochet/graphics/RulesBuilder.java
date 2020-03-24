package robotricochet.graphics;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class RulesBuilder {


    public static void showRules() throws IOException {
        Stage primaryStage = new Stage();
        primaryStage.setResizable(false);
        primaryStage.setTitle("Ricochet robot solver rules");
        InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream("src/main/resources/rules.txt"));
        BufferedReader bufferReader = new BufferedReader(inputStreamReader);
        String line;
        String str = "";
        while ((line = bufferReader.readLine()) != null) {
            str = str + line + "\n";
        }

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        Label label = new Label(str);
        scrollPane.setContent(label);
        Scene scene = new Scene(scrollPane, 900, 660, Color.SANDYBROWN);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
