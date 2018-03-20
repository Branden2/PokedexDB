import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class UserMenu {

    private Stage window;
    private Scene menu;
    private Pane userPane;
    private Scene userMenu;
    private Button returnButton;

    public UserMenu(Stage stage, Scene scene) {
        window = stage;
        menu = scene;

        userPane = new Pane();

        returnButton = new Button("Return");
        styleButton(returnButton);
        returnButton.setOnMouseReleased(e ->{
            window.setScene(menu);
        });

        BorderPane borderRgn = new BorderPane();

        Image userImg = new Image("Pokedex_Users.jpg");
        BackgroundSize size = new BackgroundSize(100,100,true,true,true,false);
        BackgroundImage userBackground = new BackgroundImage(userImg, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, size);
        Background userMenuBackground = new Background(userBackground);

        VBox leftButtons = new VBox();
        leftButtons.setSpacing(100);
        leftButtons.setPadding(new Insets(300,100,50,225));

        userMenu = new Scene(borderRgn, 1024, 576);
        borderRgn.setLeft(leftButtons);
        borderRgn.setBackground(userMenuBackground);
        //pkmnPane.getChildren().add(returnButton);
        leftButtons.getChildren().add(returnButton);
    }

    public Scene getScene(){
        return userMenu;
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

