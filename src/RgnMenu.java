import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.net.URL;

public class RgnMenu {

    private Stage window;
    private Scene menu;
    private Pane rgnPane;
    private Scene rgnMenu;
    private Button returnButton;
    private Button insertButton;
    private Button deleteButton;
    private Button updateButton;
    private Button viewButton;


    public RgnMenu(Stage stage, Scene scene) {
        window = stage;
        menu = scene;

        rgnPane = new Pane();

        URL menuResource = getClass().getResource("menuSelect.mp3");
        Media menuMedia = new Media (menuResource.toString());
        MediaPlayer menuPlayer = new MediaPlayer(menuMedia);

        BorderPane borderRgn = new BorderPane();

        Image rgnImg = new Image("Pokedex_Region.jpg");
        BackgroundSize size = new BackgroundSize(100,100,true,true,true,false);
        BackgroundImage rgnBackground = new BackgroundImage(rgnImg, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, size);
        Background rgnMenuBackground = new Background(rgnBackground);

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


        rgnMenu = new Scene(borderRgn, 1024, 576);
        borderRgn.setLeft(leftButtons);
        borderRgn.setRight(rightButtons);
        borderRgn.setTop(topButton);
        borderRgn.setBackground(rgnMenuBackground);

    }

    public Scene getScene(){
        return rgnMenu;
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

