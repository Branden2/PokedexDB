import javafx.geometry.Insets;
import javafx.scene.control.*;
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


public class GUI extends Application{

    private Stage window;
    private Scene mainMenu;
    private Scene pkmnMenu;
    private Scene regionMenu;
    private Scene userMenu;
    //private MediaPlayer mainMedia = new MediaPlayer(new Media(new File("oaksResearchLabMusic.mp3").toURI().toString()));


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage window) {
        
        Image oak = new Image("oakBackground.jpg");
        Image pokeball = new Image("pokeball.png");
        Image pkmnImg = new Image("PkmnMenu.jpg");

        BackgroundSize size = new BackgroundSize(100,100,true,true,true,false);
        BackgroundImage pkmnBackground = new BackgroundImage(pkmnImg, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, size);
        BackgroundImage oakBackground = new BackgroundImage(oak, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, size);
        Background mainBackground = new Background(oakBackground);
        Background pkmnMenuBackground = new Background(pkmnBackground);

        window.setTitle("Professor Oak's Pokemon Database");
        window.getIcons().add(pokeball);


        Button pkmnMenuButton = new Button("Pokemon");
        Button userMenuButton = new Button("Users");
        Button rgnMenuButton = new Button("Regions");
        Button exitButton = new Button("Exit");


        styleButton(pkmnMenuButton);
        styleButton(userMenuButton);
        styleButton(rgnMenuButton);
        styleButton(exitButton);


        BorderPane borderMain = new BorderPane();
        BorderPane borderPkmn = new BorderPane();

        VBox leftButtons = new VBox();
        VBox rightButtons = new VBox();

        rightButtons.setSpacing(100);
        leftButtons.setSpacing(100);
        rightButtons.setPadding(new Insets(300,225,50,100));
        leftButtons.setPadding(new Insets(300,100,50,225));


        borderMain.setLeft(leftButtons);
        borderMain.setRight(rightButtons);
        borderMain.setBackground(mainBackground);


        leftButtons.getChildren().add(pkmnMenuButton);
        leftButtons.getChildren().add(userMenuButton);
        rightButtons.getChildren().add(rgnMenuButton);
        rightButtons.getChildren().add(exitButton);


        mainMenu = new Scene(borderMain,1024,576);
        window.setScene(mainMenu);

        pkmnMenu = new Scene(borderPkmn, 1024, 576);
        borderPkmn.setBackground(pkmnMenuBackground);

        pkmnMenuButton.setOnTouchReleased( e -> {
            window.setScene(pkmnMenu);

        });

        exitButton.setOnMouseReleased(e -> {
            //mainMedia.stop();
            System.exit(0);
        });

        //mainMedia.play();
        window.show();
    }

    private void styleButton(Button button){
        button.setMinSize(125,75);
        button.setStyle("-fx-background-color: #FFC808; -fx-border-width: 6px; -fx-border-color: #3C5AA6;" +
                " -fx-base: #ed1c24; -fx-focus-color: transparent");
        button.setFont(Font.font("Impact", 20));
        DropShadow shadow = new DropShadow();
        shadow.setColor(Color.DARKBLUE);

        // will add drop shadow when muse hovers over, will remove when not
        button.setOnMouseEntered(e -> button.setEffect(shadow));
        button.setOnMouseExited(e -> button.setEffect(null));

    }


}
