package sample;

import com.almasb.fxgl.app.FXGL;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.scene.FXGLMenu;
import com.almasb.fxgl.scene.IntroScene;
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

            /* Creates Flash-Run Background */
            @Override
            protected Node createBackground(double width, double height) {
                return FXGL.getAssetLoader().loadTexture("Flash-Run_BG_2.png");
            }

            @Override
            protected Node createTitleView(String title) {
                return new Text("");
            }

            /* Creates Flash-Run Version */
            @Override
            protected Node createVersionView(String version) {
                Text view = FXGL.getUIFactory().newText("Version 3.0");
                view.setTranslateY((double)(this.app.getHeight() - 2));
                return view;
            }
        };
    }

    @NotNull
    @Override
    public IntroScene newIntro() {
        return super.newIntro();
    }

    @NotNull
    @Override
    public FXGLMenu newGameMenu(GameApplication app) {
        return new FXGLDefaultMenu(app, MenuType.GAME_MENU){
            @Override
            protected Node createBackground(double width, double height) {
                return FXGL.getAssetLoader().loadTexture("Flash-Run_BG_2.png");
            }

            @Override
            protected Node createTitleView(String title) {
                return new Text("");
            }


        };
    }


}
