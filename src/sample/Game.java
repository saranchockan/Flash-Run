package sample;


import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.entity.Entities;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.settings.GameSettings;
import java.util.Map;

public class Game extends GameApplication {

    private Entity flash;

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
    @Override
    protected void initGame(){

        getAudioPlayer().stopAllSounds();

        getGameWorld().setLevelFromMap("SpeedForce.json");
    }

    /* Implements Mouse and Keyboard Input into the Game */
    @Override
    protected void initInput() {
        getAudioPlayer().playSound("flash-theme.wav");
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
