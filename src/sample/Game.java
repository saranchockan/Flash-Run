package sample;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.settings.GameSettings;

import java.util.Map;

public class Game extends GameApplication {

    /* Game Settings */
    @Override
    protected void initSettings(GameSettings gameSettings) {
        gameSettings.setWidth(600);
        gameSettings.setHeight(600);
        gameSettings.setTitle("Basic Game App");
        gameSettings.setVersion("0.1");
    }

    /* Builds and Loads Players into the Game */
    @Override
    protected void initGame(){

    }

    /* Implements Mouse and Keyboard Input into the Game */
    @Override
    protected void initInput() {

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
    public static void main(String[]args){ launch(args);}

}
