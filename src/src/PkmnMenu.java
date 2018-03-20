import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.*;
import javafx.application.*;
import javafx.scene.*;
import javafx.scene.effect.*;
import javafx.scene.media.Media;
import java.io.File;

import java.awt.*;

public class PkmnMenu {

    private Stage window;
    private Scene menu;
    private Pane pkmnPane;
    private Scene pkmnMenu;
    private Button returnButton;

    public PkmnMenu(Stage stage, Scene scene) {
        window = stage;
        menu = scene;

        pkmnPane = new Pane();

        returnButton = new Button("Return");
        styleButton(returnButton);
        returnButton.setOnMouseReleased(e ->{
            window.setScene(menu);
        });

        BorderPane borderPkmn = new BorderPane();

        Image pkmnImg = new Image("Pokedex_Pokemon.jpg");
        BackgroundSize size = new BackgroundSize(100,100,true,true,true,false);
        BackgroundImage pkmnBackground = new BackgroundImage(pkmnImg, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, size);
        Background pkmnMenuBackground = new Background(pkmnBackground);

        VBox leftButtons = new VBox();
        leftButtons.setSpacing(100);
        leftButtons.setPadding(new Insets(300,100,50,225));

        pkmnMenu = new Scene(borderPkmn, 1024, 576);
        borderPkmn.setLeft(leftButtons);
        borderPkmn.setBackground(pkmnMenuBackground);
        //pkmnPane.getChildren().add(returnButton);
        leftButtons.getChildren().add(returnButton);
    }

    public Scene getScene(){
        return pkmnMenu;
    }

    private void styleButton(Button button){
        button.setMinSize(125,75);
        button.setStyle("-fx-background-color: #FFC808; -fx-border-width: 6px; -fx-border-color: #3C5AA6;" +
                " -fx-base: #ed1c24;");
        button.setFont(Font.font("Impact", 20));
        DropShadow shadow = new DropShadow();
        shadow.setColor(Color.DARKBLUE);

        // will add drop shadow when muse hovers over, will remove when not
        button.setOnMouseEntered(e -> button.setEffect(shadow));
        button.setOnMouseExited(e -> button.setEffect(null));

    }
}

