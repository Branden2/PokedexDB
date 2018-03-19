import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.*;
import javafx.application.*;
import javafx.scene.*;

public class GUI extends Application{

    private Stage window;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage window) {


        Image oak = new Image("oakBackground.jpg");
        Image pokeball = new Image("pokeball.png");

        BackgroundSize size = new BackgroundSize(100,100,true,true,true,false);
        BackgroundImage img = new BackgroundImage(oak, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, size);
        Background background = new Background(img);

        window.setTitle("Professor Oak's Pokemon Database");
        window.getIcons().add(pokeball);


        Button pkmnMenu = new Button("Pokemon");
        Button userMenu = new Button("Users");
        Button rgnMenu = new Button("Regions");
        Button exit = new Button("Exit");


        styleButton(pkmnMenu);
        styleButton(userMenu);
        styleButton(rgnMenu);
        styleButton(exit);


        BorderPane border = new BorderPane();
        VBox leftButtons = new VBox();
        VBox rightButtons = new VBox();

        rightButtons.setSpacing(100);
        leftButtons.setSpacing(100);
        rightButtons.setPadding(new Insets(20));
        leftButtons.setPadding(new Insets(20));


        border.setLeft(leftButtons);
        border.setRight(rightButtons);
        border.setBackground(background);


        leftButtons.getChildren().add(pkmnMenu);
        leftButtons.getChildren().add(userMenu);
        rightButtons.getChildren().add(rgnMenu);
        rightButtons.getChildren().add(exit);



        Scene scene = new Scene(border,1024,576);

        window.setScene(scene);
        window.show();
    }

    private void styleButton(Button button){
        button.setStyle("-fx-background-color: #FFC808; -fx-border-width: 5px; -fx-border-color: #3C5AA6; ");
               // "-fx-font: 12 Arial; -fx-shape: oval; ");

    }


}
