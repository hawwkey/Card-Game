//Standart imports
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

//Layout
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

//Components
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class CardsHiLoGUI extends Application {
    //Declare variables
    private Label lblFirstDeal, lblSecondDeal, lblNextCard, lblGameStatus;
    private Button btnDealFirst, btnDealSecond;
    private RadioButton rbHigher, rbLower;
    private ToggleGroup rbToggle;
    private VBox vbToggle;
    private Image imgFirstCard, imgSecondCard;
    private ImageView ivFirstCard, ivSecondCard;
    private MenuBar mnuBar;
    private Menu mnuFile, mnuHelp;
    private MenuItem itemNewGame, itemShuffle, itemExit, itemAbout;
    private ProgressBar progBar;
    private ProgressIndicator progIndicator;


    public void init() {
        //Labels
        lblFirstDeal = new Label("First card dealt: ");
        lblSecondDeal = new Label("Second card dealt: ");
        lblNextCard = new Label("Next card will be: ");
        lblGameStatus = new Label("");

        //Menu
        mnuBar = new MenuBar();
        mnuFile = new Menu("File");
        itemNewGame = new MenuItem("New game");
        itemShuffle = new MenuItem("Shuffle");
        itemExit = new MenuItem("Exit");
        mnuFile.getItems().addAll(itemNewGame, itemShuffle, itemExit);
        mnuBar.getMenus().add(mnuFile);
        //TODO handle events

        mnuHelp = new Menu("Help");
        itemAbout = new MenuItem("About");
        mnuHelp.getItems().add(itemAbout);
        mnuBar.getMenus().add(mnuHelp);
        //TODO handle events

        //Buttons
        btnDealFirst = new Button("<- Deal First Card");
        btnDealFirst.setMinWidth(150);
        btnDealSecond = new Button("Deal Second Card ->");
        btnDealSecond.setMinWidth(150);
        //TODO handle button events

        //Radio Buttons
        rbHigher = new RadioButton("Higher");
        rbLower = new RadioButton("Lower");
        rbToggle = new ToggleGroup();
        rbHigher.setToggleGroup(rbToggle);
        rbLower.setToggleGroup(rbToggle);
        vbToggle = new VBox();

        //Image views
        ivFirstCard = new ImageView();
        ivSecondCard = new ImageView();
        //TODO add images

        //Progress indication
        progBar = new ProgressBar(0);
        progBar.setMinWidth(200);
        progIndicator = new ProgressIndicator(0);
        progIndicator.setMaxWidth(30);

    }//init

    @Override
    public void start(Stage pStage) {
        //Set width and height
        pStage.setWidth(500);
        pStage.setHeight(400);
        //Create layout
        BorderPane bp = new BorderPane();//Can we add menus to grid pane??
        GridPane gp = new GridPane();
        gp.setAlignment(Pos.BASELINE_CENTER);
        //Add components
        bp.setTop(mnuBar);
        bp.setCenter(gp);

        //Divide grid pane by 3 vertical boxes with content
        VBox vbLeft = new VBox();
        vbLeft.getChildren().addAll(lblFirstDeal, ivFirstCard);
        vbLeft.setAlignment(Pos.BASELINE_CENTER);
        vbLeft.setSpacing(15);
        VBox vbCenter = new VBox();
        vbCenter.getChildren().addAll(lblNextCard, rbHigher, rbLower, btnDealFirst, btnDealSecond);
        vbCenter.setAlignment(Pos.BASELINE_CENTER);
        vbCenter.setSpacing(15);
        VBox vbRight = new VBox();
        vbRight.getChildren().addAll(lblSecondDeal, ivSecondCard, lblGameStatus);
        vbRight.setAlignment(Pos.BASELINE_CENTER);
        vbRight.setSpacing(15);

        //Add VBs to gp
        gp.add(vbLeft, 0, 0);
        gp.add(vbCenter, 1, 0);
        gp.add(vbRight, 2, 0);

        //Add indicators into Hbox
        HBox hbIndicators = new HBox();
        hbIndicators.getChildren().addAll(progBar, progIndicator);
        hbIndicators.setAlignment(Pos.CENTER);
        hbIndicators.setSpacing(10);

        gp.add(hbIndicators, 0, 1, 3, 1);

        //Beautify
        gp.setPadding(new Insets(10));
        gp.setVgap(10);
        //Create the scene
        Scene s = new Scene(bp);
        //Set the scene
        pStage.setScene(s);
        //Show the stage
        pStage.show();
    }//start

    public static void main(String[] args) {
        launch();
    }//main
}
