//Standart imports
import javafx.application.Application;
import javafx.application.Platform;
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
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;




public class CardsHiLoGUI extends Application {
    //Declare compinents
    private Label lblFirstDeal, lblSecondDeal, lblNextCard, lblGameStatus;
    private Button btnDealLeft, btnDealRight;
    private RadioButton rbHigher, rbLower;
    private ToggleGroup rbToggle;
    private Image imgLeftCard, imgRightCard;
    private ImageView ivLeftCard, ivRightCard;
    private MenuBar mnuBar;
    private Menu mnuFile, mnuHelp;
    private MenuItem itemNewGame, itemShuffle, itemExit, itemAbout;
    private ProgressBar progBar;
    private ProgressIndicator progIndicator;
    private DeckOfCards deckOfCards;
    private Card leftCard, rightCard;
    private double score;


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
        itemShuffle.setDisable(true);//is disabled before game is started
        itemExit = new MenuItem("Exit");
        mnuFile.getItems().addAll(itemNewGame, itemShuffle, itemExit);
        mnuBar.getMenus().add(mnuFile);

        //handle events on menu items
        itemNewGame.setOnAction(ae -> newGame());
        itemNewGame.setAccelerator(new KeyCodeCombination(KeyCode.N, KeyCombination.CONTROL_DOWN));//add shortcut
        itemShuffle.setOnAction(ae -> {
            deckOfCards.shuffle();
            lblGameStatus.setText("Shuffled!");
        });
        itemExit.setOnAction(ae -> Platform.exit());
        itemExit.setAccelerator(new KeyCodeCombination(KeyCode.Q, KeyCombination.CONTROL_DOWN));

        mnuHelp = new Menu("Help");
        itemAbout = new MenuItem("About");
        mnuHelp.getItems().add(itemAbout);
        mnuBar.getMenus().add(mnuHelp);
        itemAbout.setOnAction(ae -> about());

        //Radio Buttons
        rbHigher = new RadioButton("Higher");
        rbLower = new RadioButton("Lower");
        rbToggle = new ToggleGroup();
        rbHigher.setToggleGroup(rbToggle);
        rbLower.setToggleGroup(rbToggle);

        //Buttons
        btnDealLeft = new Button("<- Deal First Card");
        btnDealLeft.setMinWidth(150);
        btnDealLeft.setDisable(true);//is disabled before game is started
        btnDealRight = new Button("Deal Second Card ->");
        btnDealRight.setMinWidth(150);
        btnDealRight.setDisable(true);//is disabled before game is started
        //handle button events
        btnDealLeft.setOnAction(ae -> dealLeft());
        btnDealRight.setOnAction(ae -> dealRight());

        //Image views
        ivLeftCard = new ImageView();
        ivRightCard = new ImageView();
        //set default images
        imgLeftCard = new Image("file:images/black_joker.png");
        imgRightCard = new Image("file:images/red_joker.png");
        ivLeftCard.setImage(imgLeftCard);
        ivRightCard.setImage(imgRightCard);

        //Progress indication
        progBar = new ProgressBar(0);
        progBar.setMinWidth(200);
        progIndicator = new ProgressIndicator(0);
        progIndicator.setMaxWidth(30);

    }//init

    private void newGame() {
        //create a new deck and shuffle
        deckOfCards = new DeckOfCards();
        deckOfCards.shuffle();

        //Deal 2 cards and set images
        leftCard = deckOfCards.dealTopCard();
        rightCard = deckOfCards.dealTopCard();
        imgLeftCard = new Image("file:images/" + leftCard.toString() + ".png");
        ivLeftCard.setImage(imgLeftCard);
        imgRightCard = new Image("file:images/" + rightCard.toString() + ".png");
        ivRightCard.setImage(imgRightCard);
        //set additional elements
        lblGameStatus.setText("Good luck!");
        score = 0;
        progBar.setProgress(score);
        progIndicator.setProgress(score);
        //enable buttons
        itemShuffle.setDisable(false);
        btnDealLeft.setDisable(false);
        btnDealRight.setDisable(false);
    }//new game method

    private void dealLeft() {
        //check if deck is empty
        if (deckOfCards.isEmpty()) {
            lblGameStatus.setText("Deck is empty");
            btnDealLeft.setDisable(true);
            btnDealRight.setDisable(true);
        }
        //check if user chose the option
        else if (!rbLower.isSelected() && !rbHigher.isSelected())
            lblGameStatus.setText("Choose the option!");
        else {
            //deal card, set image and check the guess
            leftCard = deckOfCards.dealTopCard();
            imgLeftCard = new Image("file:images/" + leftCard.toString() + ".png");
            ivLeftCard.setImage(imgLeftCard);
            //if guess was right
            if ((rbLower.isSelected() && leftCard.rankIsLessThan(rightCard)) || (rbHigher.isSelected() && leftCard.rankIsGreaterThan(rightCard))) {
                score += 0.2;
                progBar.setProgress(score);
                progIndicator.setProgress(score);
                lblGameStatus.setText("Good job!");
            }
            else {
                lblGameStatus.setText("You lost :(");
                btnDealLeft.setDisable(true);
                btnDealRight.setDisable(true);
            }
            //winning condition
            if (score==1) {
                lblGameStatus.setText("You won! GG WP");
                btnDealLeft.setDisable(true);
                btnDealRight.setDisable(true);
            }
        }
    }//dealLeft()

    private void dealRight() {
        //the same as dealLeft() but for right card
        if (deckOfCards.isEmpty()) {
            lblGameStatus.setText("Deck is empty");
            btnDealLeft.setDisable(true);
            btnDealRight.setDisable(true);
        }
        else if (!rbLower.isSelected() && !rbHigher.isSelected())
            lblGameStatus.setText("Choose the option!");
        else {
            rightCard = deckOfCards.dealTopCard();
            imgRightCard = new Image("file:images/" + rightCard.toString() + ".png");
            ivRightCard.setImage(imgRightCard);
            if ((rbLower.isSelected() && rightCard.rankIsLessThan(leftCard)) || (rbHigher.isSelected() && rightCard.rankIsGreaterThan(leftCard))) {
                score += 0.2;
                progBar.setProgress(score);
                progIndicator.setProgress(score);
                lblGameStatus.setText("Good job!");
            }
            else {
                lblGameStatus.setText("You lost :(");
                btnDealLeft.setDisable(true);
                btnDealRight.setDisable(true);
            }
            if (score==1) {
                lblGameStatus.setText("You won! GG WP");
                btnDealLeft.setDisable(true);
                btnDealRight.setDisable(true);
            }
        }
    }//dealRight()

    private void about(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Developers information");
        alert.setHeaderText("Nikita Folomeev");
        alert.setContentText("Student number: 2980885");
        alert.showAndWait();
    }//about

    @Override
    public void start(Stage pStage) {
        //Set width and height
        pStage.setWidth(500);
        pStage.setHeight(400);
        pStage.setResizable(false);
        //Create layout
        BorderPane bp = new BorderPane();
        GridPane gp = new GridPane();
        gp.setAlignment(Pos.BASELINE_CENTER);
        //Add components
        bp.setTop(mnuBar);
        bp.setCenter(gp);

        //Divide grid pane by 3 vertical boxes with content
        VBox vbLeft = new VBox();
        vbLeft.getChildren().addAll(lblFirstDeal, ivLeftCard);
        vbLeft.setAlignment(Pos.BASELINE_CENTER);
        vbLeft.setSpacing(15);
        VBox vbCenter = new VBox();
        vbCenter.getChildren().addAll(lblNextCard, rbHigher, rbLower, btnDealLeft, btnDealRight);
        vbCenter.setAlignment(Pos.BASELINE_CENTER);
        vbCenter.setSpacing(15);
        VBox vbRight = new VBox();
        vbRight.getChildren().addAll(lblSecondDeal, ivRightCard, lblGameStatus);
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
        gp.setHgap(10);

        //Create the scene
        Scene s = new Scene(bp);
        s.getStylesheets().add("css/styles.css");
        //Set the scene
        pStage.setScene(s);
        //Show the stage
        pStage.show();
    }//start

    public static void main(String[] args) {
        launch();
    }//main
}
