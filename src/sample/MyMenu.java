package sample;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.scene.FXGLMenu;
import com.almasb.fxgl.scene.menu.MenuType;
import javafx.scene.Node;
import javafx.scene.control.Button;

public class MyMenu extends FXGLMenu {
    public MyMenu(GameApplication app, MenuType type) {
        super(app, type);
    }

    @Override
    protected Button createActionButton(String s, Runnable runnable) {
        return null;
    }

    @Override
    protected Node createBackground(double v, double v1) {
        return null;
    }

    @Override
    protected Node createTitleView(String s) {
        return null;
    }

    @Override
    protected Node createVersionView(String s) {
        return null;
    }

    @Override
    protected Node createProfileView(String s) {
        return null;
    }
}
