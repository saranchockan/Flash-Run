package sample;

import com.almasb.fxgl.app.FXGL;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.scene.FXGLMenu;
import com.almasb.fxgl.scene.SceneFactory;
import com.almasb.fxgl.scene.menu.FXGLDefaultMenu;
import com.almasb.fxgl.scene.menu.MenuType;
import javafx.scene.Node;
import javafx.scene.text.Text;
import org.jetbrains.annotations.NotNull;

public class MainMenu extends SceneFactory {

    @NotNull
    @Override
    public FXGLMenu newMainMenu(GameApplication app) {
        return new FXGLDefaultMenu(app,MenuType.MAIN_MENU){
            @Override
            protected Node createBackground(double width, double height) {
                return FXGL.getAssetLoader().loadTexture("Flash-Run_BG.jpg");
            }

            @Override
            protected Node createTitleView(String title) {
                return new Text("Flash-Run");
            }
        };
    }

    @NotNull
    @Override
    public FXGLMenu newGameMenu(GameApplication app) {
        return new FXGLDefaultMenu(app, MenuType.GAME_MENU){
            @Override
            protected Node createBackground(double width, double height) {
                return FXGL.getAssetLoader().loadTexture("Flash-Run_BG.jpg");
            }

            @Override
            protected Node createTitleView(String title) {
                return new Text("Flash-Run");
            }
        };
    }
}
