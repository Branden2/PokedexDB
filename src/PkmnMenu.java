import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.effect.*;


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
    Button muteButton;
    Boolean isMute = false;

    public PkmnMenu(Stage stage, Scene scene, MediaPlayer oakPlayer, MediaPlayer pkmnPlayer, MediaPlayer menuPlayer) {
        window = stage;
        menu = scene;

        pkmnPane = new Pane();

        BorderPane borderPkmn = new BorderPane();

        Image pkmnImg = new Image("Pokedex_Pokemon.jpg");
        Image unmute = new Image("unmute.png");
        Image mute = new Image("mute.png");
        BackgroundSize size = new BackgroundSize(100,100,true,true,true,false);
        BackgroundImage pkmnBackground = new BackgroundImage(pkmnImg, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, size);
        Background pkmnMenuBackground = new Background(pkmnBackground);

        insertButton = new Button("Insert");
        deleteButton = new Button("Delete");
        updateButton = new Button("Update");
        viewButton = new Button("View");
        returnButton = new Button("Return");
        muteButton = new Button();
        muteButton.setGraphic(new ImageView(unmute));

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
            pkmnPlayer.stop();
            if(!isMute)
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

