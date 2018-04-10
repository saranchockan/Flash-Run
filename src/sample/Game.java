package sample;


import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.entity.Entities;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.settings.GameSettings;
import javafx.scene.input.KeyCode;

import java.util.Map;

public class Game extends GameApplication {


    public enum EntityType{
        SPEEDSTER, TIMEWRAITHS
    }

    /* Game Settings */
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


    /* Builds and Loads Players into the Game */
    private Entity flash;

    @Override
    protected void initGame(){

        //-- Stops the intro music
        getAudioPlayer().stopAllSounds();

        getGameWorld().setLevelFromMap("SpeedForce.json");

        flash = getGameWorld().spawn("flash",50,50);
    }

    /* Implements Mouse and Keyboard Input into the Game */

    @Override
    protected void initInput() {

        //-- getAudioPlayer().playSound("flash-theme.wav");

        getInput().addAction(new UserAction("Left") {
            @Override
            protected void onAction(){
                flash.getControl(FlashControl.class).left();
                flash.translateX(-20);
            }

        },KeyCode.A);

        getInput().addAction(new UserAction("Right") {
            @Override
            protected void onAction(){
                flash.getControl(FlashControl.class).right();
                flash.translateX(20);

            }

        },KeyCode.D);

        getInput().addAction(new UserAction("Up") {
            @Override
            protected void onAction(){
                flash.getControl(FlashControl.class).up();
                flash.translateY(-20);

            }

        },KeyCode.W);

        getInput().addAction(new UserAction("Down") {
            @Override
            protected void onAction(){
                flash.getControl(FlashControl.class).down();
                flash.translateY(20);
            }

        },KeyCode.S);

    }

    /* Implements UI Elements into the Game */
    @Override
    protected void initUI() {

    }

    /* Global Variables in the Game put into a Map */
    @Override
    protected void initGameVars(Map<String, Object> vars) {

    }
    /* Implements Physics into the Game */
    @Override
    protected void initPhysics() {

    }

    /* Launches the Game */
    public static void main(String[]args){
        launch(args);
    }

}
