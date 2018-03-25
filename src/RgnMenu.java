import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Optional;


public class RgnMenu {

    private Stage window;
    private Scene menu;
    private Pane rgnPane;
    private Scene rgnMenu;
    private Stage viewWindow;
    private Button returnButton;
    private Button insertButton;
    private Button deleteButton;
    private Button updateButton;
    private Button viewButton;
    Boolean isMute = false;


    public RgnMenu(Stage stage, Scene scene, MediaPlayer oakPlayer, MediaPlayer rgnPlayer, MediaPlayer menuPlayer, Image pokeball) {
        window = stage;
        menu = scene;
        rgnPane = new Pane();

        BorderPane borderRgn = new BorderPane();

        final Popup viewPop = new Popup();
        viewPop.setX(500);
        viewPop.setY(500);

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
            rgnPlayer.stop();
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

        viewButton.setOnMousePressed(e -> {
            menuPlayer.play();
        });

        viewButton.setOnMouseReleased((MouseEvent e) -> {
            menuPlayer.stop();
            //this arrayList will be passed from the back end
            //Will contain two Strings, the region code and the region name;
            ArrayList<String> viewStatement = new ArrayList<String>();
            //for testing purposes we will set the arraylist here
            viewStatement.add("1");
            viewStatement.add("Kanto");
            //then we will get the name & code from the ArrayList
            String rgnCode = viewStatement.get(0);
            String rgnName = viewStatement.get(1);

            Text rgnCodeText = new Text("Region Code: " + rgnCode);
            Text rgnNameText = new Text("Region Name: " + rgnName);
            rgnCodeText.setFont(Font.font("Impact", 20));
            rgnNameText.setFont(Font.font("Impact", 20));
            Image rgnMap = new Image(rgnName+".png");


            VBox rgnInfo = new VBox(rgnCodeText, rgnNameText, new ImageView(rgnMap));
            rgnInfo.setPadding(new Insets(30, 250, 10, 250));
            viewWindow = new Stage();
            viewWindow.initOwner(window);
            viewWindow.setTitle("View Regions");
            viewWindow.getIcons().add(pokeball);
            BorderPane viewLayout = new BorderPane();
            viewLayout.setStyle("-fx-background-color: paleturquoise;");
            viewLayout.setPrefSize(500,500);
            viewLayout.setCenter(rgnInfo);
            viewWindow.setScene(new Scene(viewLayout));

            viewWindow.show();

//            Creates a choice dialog / drop-down menu
           /* String[]  rgnChoices = {"Kanto","Johto"};
            ChoiceDialog<String> rgnDialog = new ChoiceDialog<>("Kanto", rgnChoices);
            rgnDialog.setHeaderText("View Regions");
            rgnDialog.setContentText("Please select a region");
            rgnDialog.setGraphic(new ImageView(pokeball));
            Optional<String> result = rgnDialog.showAndWait();*/

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

