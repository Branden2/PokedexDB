import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
import java.net.URL;

public class PkmnMenu {

    private Stage window;
    private Scene menu;
    private Pane pkmnPane;
    private Scene pkmnMenu;
    private Button returnButton;
    private Button insertButton;
    private Button deleteButton;
    private Button updateButton;
    private Button viewButton;

    public PkmnMenu(Stage stage, Scene scene) {
        window = stage;
        menu = scene;

        pkmnPane = new Pane();

        URL menuResource = getClass().getResource("menuSelect.mp3");
        Media menuMedia = new Media (menuResource.toString());
        MediaPlayer menuPlayer = new MediaPlayer(menuMedia);

        BorderPane borderPkmn = new BorderPane();

        Image pkmnImg = new Image("Pokedex_Pokemon.jpg");
        BackgroundSize size = new BackgroundSize(100,100,true,true,true,false);
        BackgroundImage pkmnBackground = new BackgroundImage(pkmnImg, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, size);
        Background pkmnMenuBackground = new Background(pkmnBackground);

        insertButton = new Button("Insert");
        deleteButton = new Button("Delete");
        updateButton = new Button("Update");
        viewButton = new Button("View");
        returnButton = new Button("Return");
        styleButton(insertButton);
        styleButton(deleteButton);
        styleButton(updateButton);
        styleButton(viewButton);
        styleButton(returnButton);
        returnButton.setMinSize(62.5,37.5);

        returnButton.setOnMousePressed(e ->{
            menuPlayer.play();
        });

        returnButton.setOnMouseReleased(e ->{
            window.setScene(menu);
            menuPlayer.stop();
        });

        insertButton.setOnMousePressed( e -> {
            menuPlayer.play();
        });

        insertButton.setOnMouseReleased( e -> {
            menuPlayer.stop();
        });

        deleteButton.setOnMousePressed( e -> {
            menuPlayer.play();
        });

        deleteButton.setOnMouseReleased( e -> {
            menuPlayer.stop();
        });

        updateButton.setOnMousePressed(e -> {
            menuPlayer.play();
        });

        updateButton.setOnMouseReleased(e -> {
            menuPlayer.stop();
        });

        VBox leftButtons = new VBox(insertButton,updateButton);
        VBox rightButtons = new VBox(deleteButton,viewButton);
        HBox topButton = new HBox(returnButton);

        rightButtons.setSpacing(100);
        leftButtons.setSpacing(100);
        rightButtons.setPadding(new Insets(250,225,50,100));
        leftButtons.setPadding(new Insets(250,100,50,225));

        pkmnMenu = new Scene(borderPkmn, 1024, 576);
        borderPkmn.setLeft(leftButtons);
        borderPkmn.setRight(rightButtons);
        borderPkmn.setTop(topButton);
        borderPkmn.setBackground(pkmnMenuBackground);

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

