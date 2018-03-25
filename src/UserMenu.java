import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.net.URL;

public class UserMenu {

    private Stage window;
    private Scene menu;
    private Pane userPane;
    private Scene userMenu;
    private Button returnButton;
    private Button insertButton;
    private Button deleteButton;
    private Button updateButton;
    private Button viewButton;


    public UserMenu(Stage stage, Scene scene, MediaPlayer oakPlayer, MediaPlayer userPlayer, MediaPlayer menuPlayer) {
        window = stage;
        menu = scene;

        userPane = new Pane();

        BorderPane borderUser = new BorderPane();

        Image userImg = new Image("Pokedex_Users.jpg");
        BackgroundSize size = new BackgroundSize(100,100,true,true,true,false);
        BackgroundImage userBackground = new BackgroundImage(userImg, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, size);
        Background userMenuBackground = new Background(userBackground);

        insertButton = new Button("Insert");
        deleteButton = new Button("Delete");
        updateButton = new Button("Update");
        viewButton = new Button("View");
        returnButton = new Button("Return");
        GUI.styleButton(insertButton);
        GUI.styleButton(deleteButton);
        GUI.styleButton(updateButton);
        GUI.styleButton(viewButton);
        GUI.styleButton(returnButton);
        returnButton.setMinSize(62.5,37.5);

        returnButton.setOnMousePressed(e ->{
            menuPlayer.play();
        });

        returnButton.setOnMouseReleased(e ->{
            window.setScene(menu);
            menuPlayer.stop();
            userPlayer.stop();
            if(!GUI.isMute)
                oakPlayer.play();
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

        viewButton.setOnMousePressed(e -> {
            menuPlayer.play();
        });

        viewButton.setOnMouseReleased(e ->{
            menuPlayer.stop();
        });

        VBox leftButtons = new VBox(insertButton,updateButton);
        VBox rightButtons = new VBox(deleteButton,viewButton);
        HBox topButton = new HBox(returnButton);

        rightButtons.setSpacing(100);
        leftButtons.setSpacing(100);
        rightButtons.setPadding(new Insets(250,225,50,100));
        leftButtons.setPadding(new Insets(250,100,50,225));

        userMenu = new Scene(borderUser, 1024, 576);
        borderUser.setLeft(leftButtons);
        borderUser.setRight(rightButtons);
        borderUser.setTop(topButton);
        borderUser.setBackground(userMenuBackground);

    }

    public Scene getScene(){
        return userMenu;
    }
}

