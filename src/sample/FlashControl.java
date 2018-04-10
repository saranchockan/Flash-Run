package sample;

import com.almasb.fxgl.entity.Control;
import com.almasb.fxgl.entity.Entities;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import javafx.util.Duration;

public class FlashControl extends Control {

    private AnimatedTexture texture;
    private AnimationChannel flashIdle, flashRun;

    public FlashControl(){

        flashIdle = new AnimationChannel("flash sprite --steady.png", 5, 1000/5, 944, Duration.seconds(1),0,4);

        flashRun = new AnimationChannel("flash sprite-running.png", 10, 685/10, 194, Duration.seconds(1),0,9);

        texture = new AnimatedTexture(flashIdle);

    }

    @Override
    public void onAdded(Entity entity){
        entity.setView(texture);
    }

    @Override
    public void onUpdate(Entity entity, double v) {
    }

    public void left(){
    }

    public void right(){
    }

    public void up(){
    }

    public void down(){

    }

}
