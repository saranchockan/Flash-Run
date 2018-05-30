package sample;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.physics.CollisionHandler;
import com.almasb.fxgl.settings.GameSettings;
import com.almasb.fxgl.time.TimerAction;
import javafx.scene.control.Alert;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;
import java.util.Map;

public class Game extends GameApplication {


    //-- Game Settings

    @Override
    protected void initSettings(GameSettings gameSettings) {

        gameSettings.setWidth(1184);
        gameSettings.setHeight(672);

        /* Main Menu */
        gameSettings.setMenuEnabled(true);
        gameSettings.setSceneFactory(new MainMenu());

        gameSettings.setTitle("Flash-Run");
        gameSettings.setVersion("1.0");

    }


    //-- Builds and Loads Players into the Game
    private Entity flash;
    private Entity reverseFlash;
    private int seconds = 60;
    private int limit = 1;
    private TimerAction timerAction;

    private Alert alert;
    private boolean fC = false;
    private boolean rfC = false;


    @Override
    protected void initGame(){

        //-- Stops the intro music
        // getAudioPlayer().stopAllSounds();


        //-- Creates the SpeedForce maze
        getGameWorld().setLevelFromMap("SpeedForce.json");

        //-- Spawns the entities into the game
        flash = getGameWorld().spawn("flash",50,50);
        reverseFlash = getGameWorld().spawn("reverse-flash",700,200);

        timerAction = getMasterTimer().runAtInterval(() -> {

            if(getGameState().getInt("minutes")==0 && limit==3  && seconds==0){
                timerAction.expire();
                flash.removeFromWorld();
                reveseFlashWins();
                fC = true;
                stopTimer();
            }
            int sl = 0;
            int sr = 0;
            if(Integer.toString(seconds).length()==2){
                 sl = Integer.parseInt(Integer.toString(seconds).substring(0,1));
                 sr = Integer.parseInt(Integer.toString(seconds).substring(1,2));

            }

            if(seconds==60){
                getGameState().setValue("seconds_left",0);
                getGameState().setValue("seconds_right",0);
            }

            else if(seconds==59){
                getGameState().increment("minutes",-1);
                getGameState().setValue("seconds_left",sl);
                getGameState().setValue("seconds_right",sr);
            }

            else if(seconds<10){
                getGameState().setValue("seconds_left",0);
                getGameState().setValue("seconds_right",seconds);
            }
            else{
                getGameState().setValue("seconds_left",sl);
                getGameState().setValue("seconds_right",sr);
            }

            if(seconds>0){
                seconds--;
            }
            else{
                limit++;
                seconds = 60;
            }
        }, Duration.seconds(1));
    }

    public void stopTimer(){
        timerAction.expire();
    }

    public void flashWins(){
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("GAME OVER");
        alert.setHeaderText("The Flash Won!");
        alert.setContentText("Run Barry Run!");
        alert.show();
    }


    public void reveseFlashWins(){
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("GAME OVER");
        alert.setHeaderText("The Reverse Flash Won!");
        alert.setContentText("Looks like the flash ran out of time!");
        alert.show();
    }



    //-- Implements UI Elements into the Game
    @Override
    protected void initUI() {

        //-- Creates and implements Timer-
        Text minutes = new Text();

        minutes.setTranslateX(1090);
        minutes.setTranslateY(27.5);


        DropShadow ds = new DropShadow();
        ds.setOffsetY(3.0f);
        ds.setColor(Color.color(0.4f, 0.4f, 0.4f));
        minutes.setEffect(ds);
        minutes.setCache(true);
        minutes.setFill(Color.RED);
        minutes.setFont(Font.font(null, FontWeight.BOLD, 32));
        getGameScene().addUINode(minutes); // add to the scene graph
        minutes.textProperty().bind(getGameState().intProperty("minutes").asString());

        Text timeFormat = new Text();
        timeFormat.setTranslateX(1107.5);
        timeFormat.setTranslateY(27.5);

        timeFormat.setText(":");
        timeFormat.setEffect(ds);
        timeFormat.setCache(true);
        timeFormat.setFill(Color.RED);
        timeFormat.setFont(Font.font(null, FontWeight.BOLD, 32));
        getGameScene().addUINode(timeFormat); // add to the scene graph


        Text seconds_left = new Text();
        seconds_left.setTranslateX(1115);
        seconds_left.setTranslateY(27.5);

        seconds_left.setEffect(ds);
        seconds_left.setCache(true);
        seconds_left.setFill(Color.RED);
        seconds_left.setFont(Font.font(null, FontWeight.BOLD, 32));
        getGameScene().addUINode(seconds_left); // add to the scene graph
        seconds_left.textProperty().bind(getGameState().intProperty("seconds_left").asString());

        Text seconds_right = new Text();
        seconds_right.setTranslateX(1135);
        seconds_right.setTranslateY(27.5);
        seconds_right.setEffect(ds);
        seconds_right.setCache(true);
        seconds_right.setFill(Color.RED);
        seconds_right.setFont(Font.font(null, FontWeight.BOLD, 32));
        getGameScene().addUINode(seconds_right); // add to the scene graph
        seconds_right.textProperty().bind(getGameState().intProperty("seconds_right").asString());


        Text objective = new Text("Catch the Reverse-Flash!");

        objective.setTranslateX(400);
        objective.setTranslateY(27.5);


        DropShadow o = new DropShadow();
        o.setOffsetY(3.0f);
        o.setColor(Color.color(0.4f, 0.4f, 0.4f));
        objective.setEffect(o);
        objective.setCache(true);
        objective.setFill(Color.RED);
        objective.setFont(Font.font(null, FontWeight.BOLD, 32));
        getGameScene().addUINode(objective); // add to the scene graph
    }

    //-- Global Variables in the Game put into a Map
    @Override
    protected void initGameVars(Map<String, Object> vars) {
        vars.put("minutes",3);
        vars.put("seconds_left",0);
        vars.put("seconds_right",0);
    }
    //-- Implements Physics into the Game
    @Override
    protected void initPhysics() {

        getPhysicsWorld().addCollisionHandler(new CollisionHandler(FlashType.FLASH, FlashType.REVERSEFLASH) {

            // order of types is the same as passed into the constructor
            @Override
            protected void onCollisionBegin(Entity FLASH, Entity REVERSEFLASH) {

                REVERSEFLASH.removeFromWorld();
                flashWins();
                timerAction.expire();
                rfC = true;
            }
        });

    }

    //-- Implements Mouse and Keyboard Input into the Game

    @Override
    protected void initInput() {

        getAudioPlayer().playSound("flash-theme.wav");

        getInput().addAction(new UserAction("Flash-Left") {
            @Override
            protected void onAction(){
                if(!fC){
                    flash.getControl(FlashControl.class).left();
                }
            }

        },KeyCode.A);

        getInput().addAction(new UserAction("Flash-Right") {
            @Override
            protected void onAction(){
                if(!fC){
                    flash.getControl(FlashControl.class).right();
                }
            }

        },KeyCode.D);

        getInput().addAction(new UserAction("Flash-Up") {
            @Override
            protected void onAction(){
                if(!fC){
                    flash.getControl(FlashControl.class).up();
                }

            }

        },KeyCode.W);

        getInput().addAction(new UserAction("Flash-Down") {
            @Override
            protected void onAction(){
                if(!fC){
                    flash.getControl(FlashControl.class).down();
                }
            }

        },KeyCode.S);


        getInput().addAction(new UserAction("Flash-Flight") {
            @Override
            protected void onAction(){
                if(!fC){
                    flash.getControl(FlashControl.class).flight();
                }
            }

        },KeyCode.R);


        getInput().addAction(new UserAction("Flash-SR") {
            @Override
            protected void onAction(){
                if(!fC){
                    flash.getControl(FlashControl.class).switchRight();
                }
            }

        },KeyCode.Y);

        getInput().addAction(new UserAction("Flash-SL") {
            @Override
            protected void onAction(){
                if(!fC){
                    flash.getControl(FlashControl.class).switchLeft();
                }
            }

        },KeyCode.T);


        getInput().addAction(new UserAction("RevFlash-Left") {
            @Override
            protected void onAction(){
                if(!rfC){
                    reverseFlash.getControl(ReverseFlashControl.class).left();
                }
            }

        },KeyCode.LEFT);

        getInput().addAction(new UserAction("RevFlash-Right") {
            @Override
            protected void onAction(){
                if(!rfC){
                    reverseFlash.getControl(ReverseFlashControl.class).right();
                }
            }

        },KeyCode.RIGHT);

        getInput().addAction(new UserAction("RevFlash-Up") {
            @Override
            protected void onAction(){
                if(!rfC){
                    reverseFlash.getControl(ReverseFlashControl.class).up();
                }
            }

        },KeyCode.UP);

        getInput().addAction(new UserAction("RevFlash-Down") {
            @Override
            protected void onAction(){
                if(!rfC){
                    reverseFlash.getControl(ReverseFlashControl.class).down();
                }
            }

        },KeyCode.DOWN);


        getInput().addAction(new UserAction("RevFlash-Flight") {
            @Override
            protected void onAction(){
                if(!rfC){
                    reverseFlash.getControl(ReverseFlashControl.class).flight();
                }

            }

        },KeyCode.L);


        getInput().addAction(new UserAction("RevFlash-SR") {
            @Override
            protected void onAction(){
                if(!rfC){
                    reverseFlash.getControl(ReverseFlashControl.class).switchRight();
                }

            }

        },KeyCode.K);

        getInput().addAction(new UserAction("RevFlash-SL") {
            @Override
            protected void onAction(){

                if(!rfC){
                    reverseFlash.getControl(ReverseFlashControl.class).switchLeft();
                }
            }
        },KeyCode.J);


    }

    //-- Launches the Game
    public static void main(String[]args){
        launch(args);
    }

}
