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
import java.net.URL;


public class GUI extends Application{

    private Stage window;
    private Scene mainMenu;
    private RgnMenu rgn;
    private PkmnMenu pkmn;
    private UserMenu user;
    private Button pkmnMenuButton;
    private Button userMenuButton;
    private Button rgnMenuButton;
    private Button exitButton;
    //private MediaPlayer mainMedia = new MediaPlayer(new Media(new File("oaksResearchLabMusic.mp3").toURI().toString()));


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage window) {

        URL oakResource = getClass().getResource("oakResearchLabMusic.mp3");
        Media oakMedia = new Media (oakResource.toString());
        MediaPlayer oakPlayer = new MediaPlayer(oakMedia);
        oakPlayer.setCycleCount(MediaPlayer.INDEFINITE);

        URL menuResource = getClass().getResource("menuSelect.mp3");
        Media menuMedia = new Media (menuResource.toString());
        MediaPlayer menuPlayer = new MediaPlayer(menuMedia);

        oakPlayer.play();

        //import out images
        Image oak = new Image("oakBackground.jpg");
        Image pokeball = new Image("pokeball.png");

        //make our background for main menu
        BackgroundSize size = new BackgroundSize(100,100,true,true,true,false);
        BackgroundImage oakBackground = new BackgroundImage(oak, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, size);
        Background mainBackground = new Background(oakBackground);

        //setting up window basics
        window.setTitle("Professor Oak's Pokemon Database");
        window.getIcons().add(pokeball);

        //set up buttons w/ text
        pkmnMenuButton = new Button("Pokemon");
        userMenuButton = new Button("Users");
        rgnMenuButton = new Button("Regions");
        exitButton = new Button("Exit");

        //style all of the buttons
        styleButton(pkmnMenuButton);
        styleButton(userMenuButton);
        styleButton(rgnMenuButton);
        styleButton(exitButton);

        BorderPane borderMain = new BorderPane();

        VBox leftButtons = new VBox(pkmnMenuButton,userMenuButton);
        VBox rightButtons = new VBox(rgnMenuButton,exitButton);

        //spacing vbox buttons
        rightButtons.setSpacing(100);
        leftButtons.setSpacing(100);
        rightButtons.setPadding(new Insets(300,225,50,100));
        leftButtons.setPadding(new Insets(300,100,50,225));

        //adding panels to borderpane
        borderMain.setLeft(leftButtons);
        borderMain.setRight(rightButtons);
        borderMain.setBackground(mainBackground);

        //making new scene and setting it to window
        mainMenu = new Scene(borderMain,1024,576);
        window.setScene(mainMenu);

        pkmn = new PkmnMenu(window, mainMenu);

        pkmnMenuButton.setOnMousePressed( e -> {
            menuPlayer.play();
        });

        pkmnMenuButton.setOnMouseReleased( e -> {
            window.setScene(pkmn.getScene());
            menuPlayer.stop();

        });

        rgn = new RgnMenu(window, mainMenu);

        rgnMenuButton.setOnMousePressed( e -> {
            menuPlayer.play();
        });

        rgnMenuButton.setOnMouseReleased( e -> {
            window.setScene(rgn.getScene());
            menuPlayer.stop();
        });

        user = new UserMenu(window, mainMenu);

        userMenuButton.setOnMousePressed(e -> {
            menuPlayer.play();
        });

        userMenuButton.setOnMouseReleased(e -> {
            window.setScene(user.getScene());
            oakPlayer.play();
        });

        exitButton.setOnMouseReleased(e -> {
            System.exit(0);
            oakPlayer.stop();
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