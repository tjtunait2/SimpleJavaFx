package sample;

import java.net.URL;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class InvinciBagel extends Application {
    static final double WIDTH = 800, HEIGHT = 800;
    int gameScore = 0;
    Text scoreText, scoreLabel;
    private Font scoreFont;
    private AudioClip iSound0, iSound1, iSound2, iSound3, iSound4, iSound5;
    private boolean up, down, left, right, aKey, dKey, sKey, wKey;
    Group root;
    private HBox buttonContainer;
    Bagel iBagel;
    Enemy iBeagle;
    Projectile iBullet, iCheese;
    Prop iPR0, iPR1, iPR2, iPR3, iPR4, iPR5, iPR6, iPR7, iPR8;
    PropH iPH0;
    PropV iPV0, iPV1;
    PropB iPB0;
    private Scene scene;
    private Image splashScreen, instructionLayer, legalLayer, scoresLayer, skyCloud;
    Treasure iTR0, iTR1;
    private Image iB0, iB1, iB2, iB3, iB4, iB5, iB6, iB7, iB8, iP0, iT0, iT1, iE0, iC0, iC1;
    private ImageView splashScreenBackplate, splashScreenTextArea;
    private Button gameButton, helpButton, scoreButton, legalButton;
    private Insets buttonContainerPadding;
    private GamePlayLoop gamePlayLoop;
    CastingDirector castDirector;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Stage");
        root = new Group();
        scene = new Scene(root, WIDTH, HEIGHT, Color.GREEN);
        primaryStage.setScene(scene);
        primaryStage.show();


        createSceneEventHandling();
        loadImageAssets();
        createGameActors();
        addGameActorNodes();
        createCastingDirection();
     //   createSplashScreenNodes();
        addNodesToStackPane();
        createStartGameLoop();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isaKey() {
        return aKey;
    }

    public void setaKey(boolean aKey) {
        this.aKey = aKey;
    }

    public boolean isdKey() {
        return dKey;
    }

    public void setdKey(boolean dKey) {
        this.dKey = dKey;
    }

    public boolean issKey() {
        return sKey;
    }

    public void setsKey(boolean sKey) {
        this.sKey = sKey;
    }

    public boolean iswKey() {
        return wKey;
    }

    public void setwKey(boolean wKey) {
        this.wKey = wKey;
    }

    private void createSplashScreenNodes() {
        scoreText = new Text();
        scoreText.setText(String.valueOf(gameScore));
        scoreText.setLayoutX(525);
        scoreText.setLayoutY(385);
        scoreFont = new Font("Verdana", 20);
        scoreText.setFont(scoreFont);
        scoreText.setFill(Color.BLUE);
        scoreLabel = new Text();
        scoreLabel.setText("SCORE:");
        scoreLabel.setLayoutY(385);
        scoreLabel.setLayoutX(445);
        scoreLabel.setFont(scoreFont);
        scoreLabel.setFill(Color.BLACK);
        buttonContainer = new HBox(12);
        buttonContainer.setLayoutY(365);
        buttonContainerPadding = new Insets(0, 0, 10, 16);
        buttonContainer.setPadding(buttonContainerPadding);
        gameButton = new Button();
        gameButton.setLayoutX(0);
        gameButton.setText("PLAY GAME");
        gameButton.setOnAction((ActionEvent) -> {
            splashScreenBackplate.setVisible(true);
            splashScreenBackplate.setImage(skyCloud);
            splashScreenBackplate.toBack();
            splashScreenTextArea.setVisible(false);
        });
        helpButton = new Button();
        helpButton.setText("INSTRUCTIONS");
        helpButton.setOnAction((ActionEvent) -> {
            splashScreenBackplate.setVisible(true);
            splashScreenBackplate.setImage(splashScreen);
            splashScreenBackplate.toFront();
            splashScreenTextArea.setVisible(true);
            splashScreenTextArea.setImage(instructionLayer);
            splashScreenTextArea.toFront();
            buttonContainer.toFront();
        });
        scoreButton = new Button();
        scoreButton.setText("HIGH SCORES");
        scoreButton.setOnAction((ActionEvent) -> {
            splashScreenBackplate.setVisible(true);
            splashScreenBackplate.setImage(splashScreen);
            splashScreenBackplate.toFront();
            splashScreenTextArea.setVisible(true);
            splashScreenTextArea.setImage(scoresLayer);
            splashScreenTextArea.toFront();
            buttonContainer.toFront();
        });
        legalButton = new Button();
        legalButton.setText("LEGAL & CREDITS");
        legalButton.setOnAction((ActionEvent) -> {
            splashScreenBackplate.setVisible(true);
            splashScreenBackplate.setImage(splashScreen);
            splashScreenBackplate.toFront();
            splashScreenTextArea.setVisible(true);
            splashScreenTextArea.setImage(legalLayer);
            splashScreenTextArea.toFront();
            buttonContainer.toFront();
        });
        buttonContainer.getChildren().addAll(gameButton, helpButton, scoreButton, legalButton);
        splashScreenBackplate = new ImageView();
        splashScreenBackplate.setImage(splashScreen);
        splashScreenTextArea = new ImageView();
        splashScreenTextArea.setImage(instructionLayer);
    }

    private void addNodesToStackPane() {
       /* root.getChildren().add(splashScreenBackplate);
        root.getChildren().add(splashScreenTextArea);
        root.getChildren().add(buttonContainer);
        root.getChildren().add(scoreText);
        root.getChildren().add(scoreLabel);*/
    }

    private void createSceneEventHandling() {
        scene.setOnKeyPressed((KeyEvent event) -> {
            switch (event.getCode()) {
                case W:  wKey = true; break;
                case I: up = true; break;
                case S:  sKey = true; break;
                case K: down = true; break;
                case A:  aKey = true; break;
                case J: left = true; break;
                case D: dKey = true; break;
                case L: right = true; break;
            }
        });
        scene.setOnKeyReleased((KeyEvent event) -> {
            switch (event.getCode()) {
                case W:  wKey = false; break;
                case I: up = false; break;
                case S: sKey = false; break;
                case K: down = false; break;
                case A: aKey = false; break;
                case J: left = false; break;
                case D: dKey = false; break;
                case L: right = false; break;
            }
        });
    }
    private void loadImageAssets() {
        iP0 = new Image("/prop0.png", 80, 80, false, false, true);

        iB0 = new Image("/sprite0.png", 81, 81, false, false, true);
        iB1 = new Image("/sprite1.png", 81, 81, false, false, true);
        iB2 = new Image("/sprite2.png", 81, 81, false, false, true);
        iB3 = new Image("/sprite3.png", 81, 81, false, false, true);
        iB4 = new Image("/sprite4.png", 81, 81, false, false, true);
        iB5 = new Image("/sprite5.png", 81, 81, false, false, true);
        iB6 = new Image("/sprite6.png", 81, 81, false, false, true);
        iB7 = new Image("/sprite7.png", 81, 81, false, false, true);
        iB8 = new Image("/sprite8.png", 81, 81, false, false, true);

        iT0 = new Image("/treasure1.png", 100, 100, false, false, true);
        iT1 = new Image("/treasure2.png", 64, 64, false, false, true);
        iE0 = new Image("/enemy.png", 70, 116, false, false, true);
        iC0 = new Image("/bullet.png", 64, 24, false, false, true);
        iC1 = new Image("/cheese.png", 32, 29, false, false, true);
        skyCloud = new Image("/skycloud.png", 640, 400, false, false, true);
    }

    private void createGameActors() {
        iBagel = new Bagel(this,
                "M58,8 L58,8 43,24 32,28 32,41 18,41 28,54 40,61 35,73 41,79 45,54 55,39 65,40 69,25 Z",
                WIDTH/2, HEIGHT/2, iB0, iB1, iB2, iB3, iB4, iB5, iB6, iB7, iB8);
        iPR0 = new Prop("M0,0 L0,32 72,32 72,0 Z", 0, 0, iP0);
        iPR1 = new Prop("M0,0 L0,32 72,32 72,0 Z", 0, 80, iP0);
        iPR2 = new Prop("M0,0 L0,32 72,32 72,0 Z", 0, 160, iP0);
        iPR3 = new Prop("M0,0 L0,32 72,32 72,0 Z", 0, 240, iP0);
        iPR4 = new Prop("M0,0 L0,32 72,32 72,0 Z", 0, 320, iP0);
        iPR5 = new Prop("M0,0 L0,32 72,32 72,0 Z", 0, 400, iP0);
        iPR6 = new Prop("M0,0 L0,32 72,32 72,0 Z", 0, 480, iP0);


        ///



        iPH0 = new PropH("M0,0 L0,32 72,32 72,0 Z", 0, 80, iP0);
        iPV0 = new PropV("M0,0 L0,32 72,32 72,0 Z", 396, 116, iP0);
        iPB0 = new PropB("M0,0 L0,32 72,32 72,0 Z", 512, 316, iP0);
        iTR0 = new Treasure("M0 0 L0 64 64 64 64 0 Z", 50, 105, iT0);
        iTR1 = new Treasure("M0 0 L0 64 64 64 64 0 Z", 533, 206, iT1);
        iBeagle = new Enemy(this, "M0 6 L0 52 70 52 70 70 70 93 115 45 115 0 84 0 68 16 Z", 520, 160, iE0);
        iBullet = new Projectile("M0 4 L0 16 64 16 64 4 Z", 8, 8, iC0);
        //iCheese = new Projectile("M0 0 L0 32 32 32 32 0 Z", -32, 0, iC1);
        iCheese = new Projectile("M0 4 L0 16 64 16 64 4 Z", 96, 8, iC1);
        //iPV1 = new PropV("M150 0 L75 200 L225 200 Z", 0, -58, iP1);
        //iPR1 = new Prop("M150 0 L75 200 L225 200 Z", 0, -150, iP1);
    }

    private void addGameActorNodes() {
        // add prop
        root.getChildren().add(iPR0.spriteFrame);
        root.getChildren().add(iPR1.spriteFrame);
        root.getChildren().add(iPR2.spriteFrame);
        root.getChildren().add(iPR3.spriteFrame);
        root.getChildren().add(iPR4.spriteFrame);
        root.getChildren().add(iPR5.spriteFrame);
        root.getChildren().add(iPR6.spriteFrame);
        //
        root.getChildren().add(iPH0.spriteFrame);
        root.getChildren().add(iPV0.spriteFrame);
        //root.getChildren().add(iPV1.spriteFrame);
        root.getChildren().add(iPB0.spriteFrame);
        //root.getChildren().add(iTR0.spriteFrame);
        //root.getChildren().add(iTR1.spriteFrame);
        root.getChildren().add(iBullet.spriteFrame);
        root.getChildren().add(iCheese.spriteFrame);
        root.getChildren().add(iBeagle.spriteFrame);
        root.getChildren().add(iBagel.spriteFrame);
    }

    private void createCastingDirection() {
        castDirector = new CastingDirector();
        castDirector.addCurrentCast(iPR0, iPR1, iPR2, iPR3, iPR4, iPR5, iPR6, iPH0, iPV0, iPB0);
    }


    private void createStartGameLoop() {
        gamePlayLoop = new GamePlayLoop(this);
        gamePlayLoop.start();
    }
}
