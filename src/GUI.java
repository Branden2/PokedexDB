import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.*;
import javafx.application.*;
import javafx.scene.*;
import javafx.scene.effect.*;
import javafx.scene.media.Media;
import java.net.URL;
import java.util.Optional;


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
    private Button muteButton;
    public static Boolean isMute = false;
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

        URL pkmnResource = getClass().getResource("PkmnMusic.mp3");
        Media pkmnMedia = new Media (pkmnResource.toString());
        MediaPlayer pkmnPlayer = new MediaPlayer(pkmnMedia);
        pkmnPlayer.setCycleCount(MediaPlayer.INDEFINITE);

        URL userResource = getClass().getResource("UserMusic.mp3");
        Media userMedia = new Media (userResource.toString());
        MediaPlayer userPlayer = new MediaPlayer(userMedia);
        userPlayer.setCycleCount(MediaPlayer.INDEFINITE);

        URL rgnResource = getClass().getResource("rgnMusic.mp3");
        Media rgnMedia = new Media (rgnResource.toString());
        MediaPlayer rgnPlayer = new MediaPlayer(rgnMedia);
        rgnPlayer.setCycleCount(MediaPlayer.INDEFINITE);

        URL menuResource = getClass().getResource("menuSelect.mp3");
        Media menuMedia = new Media (menuResource.toString());
        MediaPlayer menuPlayer = new MediaPlayer(menuMedia);

        oakPlayer.play();

        //import out images
        Image oak = new Image("oakBackground.jpg");
        Image pokeball = new Image("pokeball.png");
        Image mute = new Image("mute.png");
        Image unmute = new Image("unmute.png");
        Image rturn = new Image("return.png");

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
        muteButton = new Button();


        //style all of the buttons
        styleButton(pkmnMenuButton);
        styleButton(userMenuButton);
        styleButton(rgnMenuButton);
        styleButton(exitButton);

        muteButton.setGraphic(new ImageView(unmute));
        muteButton.setMaxSize(50, 50);
        muteButton.setStyle("-fx-background-color: #99D9EA;");


        BorderPane borderMain = new BorderPane();

        VBox leftButtons = new VBox(pkmnMenuButton,userMenuButton);
        VBox rightButtons = new VBox(rgnMenuButton,exitButton);
        HBox topButton = new HBox(muteButton);

        //spacing vbox buttons
        rightButtons.setSpacing(100);
        leftButtons.setSpacing(100);
        rightButtons.setPadding(new Insets(250,225,50,100));
        leftButtons.setPadding(new Insets(250,100,50,225));
        topButton.setPadding(new Insets(0, 0, 0, 950));

        //adding panels to borderpane
        borderMain.setLeft(leftButtons);
        borderMain.setRight(rightButtons);
        borderMain.setTop(topButton);
        borderMain.setBackground(mainBackground);

        //making new scene and setting it to window
        mainMenu = new Scene(borderMain,1024,576);
        window.setScene(mainMenu);

        pkmn = new PkmnMenu(window, mainMenu, oakPlayer, pkmnPlayer, menuPlayer);
        rgn = new RgnMenu(window, mainMenu, oakPlayer, rgnPlayer, menuPlayer, pokeball);
        user = new UserMenu(window, mainMenu, oakPlayer, userPlayer, menuPlayer);

        pkmnMenuButton.setOnMousePressed( e -> {
            menuPlayer.play();
        });

        pkmnMenuButton.setOnMouseReleased( e -> {
            window.setScene(pkmn.getScene());
            menuPlayer.stop();
            if(!isMute) {
                oakPlayer.stop();
                pkmnPlayer.play();
            }

        });

        rgnMenuButton.setOnMousePressed( e -> {
            menuPlayer.play();
        });

        rgnMenuButton.setOnMouseReleased( e -> {
            window.setScene(rgn.getScene());
            menuPlayer.stop();
            if(!isMute) {
                oakPlayer.stop();
                rgnPlayer.play();
            }
        });

        userMenuButton.setOnMousePressed(e -> {
            menuPlayer.play();
        });

        userMenuButton.setOnMouseReleased(e -> {
            window.setScene(user.getScene());
            menuPlayer.stop();
            if(!isMute) {
                oakPlayer.stop();
                userPlayer.play();
            }
        });

        exitButton.setOnMouseReleased(e -> {
            System.exit(0);
            oakPlayer.stop();
        });

        muteButton.setOnMouseClicked(e -> {
            if(isMute){
                muteButton.setGraphic(new ImageView(unmute));
                oakPlayer.play();
                isMute = false;
            }
            else{
                muteButton.setGraphic(new ImageView(mute));
                oakPlayer.pause();
                isMute = true;
            }
        });

        //mainMedia.play();
        window.show();
    }

    public static void styleButton(Button button){
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

    public static Optional<String> makeDropDown(String[] rgnChoices, String title, String header, String context, Image pic){
        ChoiceDialog<String> rgnDialog = new ChoiceDialog<>(rgnChoices[0], rgnChoices);
        rgnDialog.setTitle(title);
        rgnDialog.setHeaderText(header);
        rgnDialog.setContentText(context);
        rgnDialog.setGraphic(new ImageView(pic));
        rgnDialog.setResizable(false);
        rgnDialog.getDialogPane().setStyle("-fx-background-color: #2a75bb");
        Optional<String> result = rgnDialog.showAndWait();
        return result;
    }
    public static Stage makePopupWindow(Stage window, String title, Image icon){
        Stage popupWindow = new Stage();
        popupWindow.initOwner(window);
        popupWindow.setTitle(title);
        popupWindow.getIcons().add(icon);
        popupWindow.setResizable(false);
        return popupWindow;
    }
    public static BorderPane makePopupPane(VBox info){
        BorderPane popupLayout = new BorderPane();
        popupLayout.setStyle("-fx-background-color: #99D9EA;");
        popupLayout.setPrefSize(500,500);
        popupLayout.setCenter(info);
        return popupLayout;
    }
}