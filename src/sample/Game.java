package sample;


import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.settings.GameSettings;
import javafx.scene.input.KeyCode;

import java.util.Map;

public class Game extends GameApplication {

    //-- Game Settings
    @Override
    protected void initSettings(GameSettings gameSettings) {

        gameSettings.setWidth(1216);
        gameSettings.setHeight(704);

        /* Main Menu */
        gameSettings.setMenuEnabled(true);
        gameSettings.setSceneFactory(new MainMenu());

        gameSettings.setTitle("Flash-Run");
        gameSettings.setVersion("3.0");

    }


    //-- Builds and Loads Players into the Game
    private Entity flash;
    private Entity reverseFlash;

    @Override
    protected void initGame(){

        //-- Stops the intro music
        getAudioPlayer().stopAllSounds();

        //-- Creates the SpeedForce maze
        getGameWorld().setLevelFromMap("SpeedForce.json");

        //-- Spawns the entities into the game

        flash = getGameWorld().spawn("flash",50,50);
        reverseFlash = getGameWorld().spawn("reverse-flash",200,200);

    }

    //-- Implements Mouse and Keyboard Input into the Game

    @Override
    protected void initInput() {

        getAudioPlayer().playSound("flash-theme.wav");

        getInput().addAction(new UserAction("F_Left") {
            @Override
            protected void onAction(){
                flash.getControl(FlashControl.class).left();
                flash.translateX(-20);
            }

        },KeyCode.A);

        getInput().addAction(new UserAction("F_Right") {
            @Override
            protected void onAction(){
                flash.getControl(FlashControl.class).right();
                flash.translateX(20);

            }

        },KeyCode.D);

        getInput().addAction(new UserAction("F_Up") {
            @Override
            protected void onAction(){
                flash.getControl(FlashControl.class).up();
                flash.translateY(-20);

            }

        },KeyCode.W);

        getInput().addAction(new UserAction("F_Down") {
            @Override
            protected void onAction(){
                flash.getControl(FlashControl.class).down();
                flash.translateY(20);
            }

        },KeyCode.S);



        getInput().addAction(new UserAction("Rf_Left") {
            @Override
            protected void onAction(){
                reverseFlash.getControl(ReverseFlashControl.class).left();
                reverseFlash.translateX(-40);
            }

        },KeyCode.LEFT);

        getInput().addAction(new UserAction("Rf_Right") {
            @Override
            protected void onAction(){
                reverseFlash.getControl(ReverseFlashControl.class).right();
                reverseFlash.translateX(40);

            }

        },KeyCode.RIGHT);

        getInput().addAction(new UserAction("Rf_Up") {
            @Override
            protected void onAction(){
                reverseFlash.getControl(ReverseFlashControl.class).up();
                reverseFlash.translateY(-40);

            }

        },KeyCode.UP);

        getInput().addAction(new UserAction("Rf_Down") {
            @Override
            protected void onAction(){
                reverseFlash.getControl(ReverseFlashControl.class).down();
                reverseFlash.translateY(40);
            }

        },KeyCode.DOWN);


    }

    //-- Implements UI Elements into the Game
    @Override
    protected void initUI() {

    }

    //-- Global Variables in the Game put into a Map
    @Override
    protected void initGameVars(Map<String, Object> vars) {

    }
    //-- Implements Physics into the Game
    @Override
    protected void initPhysics() {

    }

    //-- Launches the Game
    public static void main(String[]args){
        launch(args);
    }

}
