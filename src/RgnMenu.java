import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
    private Button returnButton;
    private Button insertButton;
    private Button deleteButton;
    private Button updateButton;
    private Button viewButton;


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
            rgnPlayer.stop();
            if(!GUI.isMute)
                oakPlayer.play();
        });

        insertButton.setOnMousePressed( e -> {
            menuPlayer.play();
        });

        insertButton.setOnMouseReleased( e -> {
            menuPlayer.stop();
            Dialog<Array> dialog = new Dialog<>();
            dialog.setTitle("Insert Region");
            dialog.setHeaderText("Enter New Region Information");
            dialog.setResizable(false);
            Label codeLabel = new Label("Region Code: ");
//            codeLabel.setFont(Font.font("Impact", 20));
            Label nameLabel = new Label("Region Name: ");
//            nameLabel.setFont(Font.font("Impact", 20));
            TextField codeText = new TextField();
//            codeText.setStyle("-fx-background-color: #FFC808;");
            TextField nameText = new TextField();
//            nameText.setStyle("-fx-background-color: #FFC808;");

            GridPane grid = new GridPane();
            grid.setPadding(new Insets(10));
            grid.add(codeLabel, 1,1);
            grid.add(codeText, 2, 1);
            grid.add(nameLabel,1,3);
            grid.add(nameText,2,3);
            dialog.getDialogPane().setContent(grid);
            dialog.getDialogPane().setStyle("-fx-background-color: #2a75bb");
            dialog.setGraphic(new ImageView(pokeball));
            ButtonType buttonTypeOK = new
                    ButtonType("Insert", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().add(buttonTypeOK);

            dialog.show();
        });

        deleteButton.setOnMousePressed( e -> {
            menuPlayer.play();
        });

        deleteButton.setOnMouseReleased( e -> {
            menuPlayer.stop();
            String[]  rgnChoices = {"Kanto","Johto"};
            Optional<String> result = GUI.makeDropDown(rgnChoices, "Delete Regions","Delete Regions", "Select a Region", pokeball);
            if(result.isPresent()) {
                try {
                    ArrayList<String> viewStatement = DatabaseConnectTest.selectFROM(result.get());
                    DatabaseConnectTest.deleteFROM(result.get(), "Region_Name", "Region");
                    //then we will get the name & code from the ArrayList
                    String rgnName = viewStatement.get(1);

                    Text deleteText = new Text(rgnName + " was successfully deleted");
                    deleteText.setFont(Font.font("Impact", 20));
                    Image rgnMap = new Image(rgnName + ".png");

                    VBox rgnInfo = new VBox(deleteText, new ImageView(rgnMap));
                    rgnInfo.setPadding(new Insets(30, 250, 10, 250));
                    Stage viewWindow = GUI.makePopupWindow(window, "Delete Regions", pokeball);
                    BorderPane viewLayout = GUI.makePopupPane(rgnInfo);
                    viewWindow.setScene(new Scene(viewLayout));


                    viewWindow.show();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

        updateButton.setOnMousePressed(e -> {
            menuPlayer.play();
        });

        updateButton.setOnMouseReleased(e -> {
            menuPlayer.stop();
            String[]  rgnChoices = {"Kanto","Johto"};
            Optional<String> result = GUI.makeDropDown(rgnChoices, "Update Region","Update Region", "Select a Region", pokeball);
            if(result.isPresent()) {
                try {
                    ArrayList<String> viewStatement = DatabaseConnectTest.selectFROM(result.get());
                    //then we will get the name & code from the ArrayList
                    String rgnName = viewStatement.get(1);

                    Dialog<Array> dialog = new Dialog<>();
                    dialog.setTitle("Update Region");
                    dialog.setHeaderText("Enter New Region Name for " + rgnName);
                    dialog.setResizable(false);
                    Label nameLabel = new Label("Region Name: ");
                    TextField nameText = new TextField();

                    GridPane grid = new GridPane();
                    grid.add(nameLabel,1,3);
                    grid.add(nameText,2,3);
                    dialog.getDialogPane().setContent(grid);
                    dialog.getDialogPane().setStyle("-fx-background-color: #2a75bb");
                    dialog.setGraphic(new ImageView(pokeball));
                    ButtonType buttonTypeOK = new
                            ButtonType("Update", ButtonBar.ButtonData.OK_DONE);
                    dialog.getDialogPane().getButtonTypes().add(buttonTypeOK);

                    dialog.showAndWait();


                    if(nameText.getText()!=null){
                        DatabaseConnectTest.updateName(rgnName, nameText.getText());
                        Text deleteText = new Text(rgnName + " was successfully update to " + nameText.getText());
                        deleteText.setFont(Font.font("Impact", 20));
                        Image rgnMap = new Image(rgnName + ".png");


                        VBox rgnInfo = new VBox(deleteText, new ImageView(rgnMap));
                        rgnInfo.setPadding(new Insets(30, 250, 10, 250));
                        Stage viewWindow = GUI.makePopupWindow(window, "Update Region", pokeball);
                        BorderPane viewLayout = GUI.makePopupPane(rgnInfo);
                        viewWindow.setScene(new Scene(viewLayout));


                        viewWindow.show();
                    }


                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

        viewButton.setOnMousePressed(e -> {
            menuPlayer.play();
        });

        viewButton.setOnMouseReleased((MouseEvent e) -> {
            menuPlayer.stop();
            String[]  rgnChoices = {"Kanto","Johto"};//get this from connector
            Optional<String> result = GUI.makeDropDown(rgnChoices, "View Regions","View Regions", "Select a Region", pokeball);

            if(result.isPresent()){
                try {
                    ArrayList<String> viewStatement = DatabaseConnectTest.selectFROM(result.get());
                    //then we will get the name & code from the ArrayList
                    String rgnCode = viewStatement.get(0);
                    String rgnName = viewStatement.get(1);

                    Text rgnCodeText = new Text("Region Code: " + rgnCode);
                    Text rgnNameText = new Text("Region Name: " + rgnName);
                    rgnCodeText.setFont(Font.font("Impact", 20));
                    rgnNameText.setFont(Font.font("Impact", 20));
                    /*if(rgnName.equals("Kanto") || rgnName.equals("Johot")) {
                        Image rgnMap = new Image(rgnName + ".png");
                        VBox rgnInfo = new VBox(rgnCodeText, rgnNameText, new ImageView(rgnMap));
                    }
                    else*/
                    VBox rgnInfo = new VBox(rgnCodeText, rgnNameText);
                    if(rgnName.equals("Kanto") || rgnName.equals("Johot")) {
                        Image rgnMap = new Image(rgnName + ".png");
                        rgnInfo.getChildren().add(new ImageView(rgnMap));
                    }

                    rgnInfo.setPadding(new Insets(30, 250, 10, 250));
                    Stage viewWindow = GUI.makePopupWindow(window, "View Regions", pokeball);
                    BorderPane viewLayout = GUI.makePopupPane(rgnInfo);
                    viewWindow.setScene(new Scene(viewLayout));

                    viewWindow.show();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }

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
}

