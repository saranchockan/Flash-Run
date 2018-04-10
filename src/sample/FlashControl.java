package sample;

import com.almasb.fxgl.core.math.FXGLMath;
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

    private int speed = 0;


    public FlashControl(){

        flashIdle = new AnimationChannel("flash sprite --steady.png", 16, 3200/16, 236, Duration.seconds(1),0,15);
        flashRun = new AnimationChannel("flash sprite-running.png", 10, 1370/10, 97, Duration.seconds(1),0,9);

        texture = new AnimatedTexture(flashRun);
    }

    @Override
    public void onAdded(Entity entity){
        entity.setView(texture);
    }

    @Override
    public void onUpdate(Entity entity, double v) {
        entity.translateX(speed * v);

        if(speed==0){
            texture.setAnimationChannel(flashIdle);
        }

        else{
            texture.setAnimationChannel(flashRun);
            speed = (int) (speed * 0.4);

            if (FXGLMath.abs(speed) < 1) {
                speed = 0;
            }
        }
    }


    public void left(){
        speed = -20;
        getEntity().setScaleX(-1);
    }

    public void right(){
        speed = 20;
        getEntity().setScaleX(1);

    }

    public void up(){
    }

    public void down(){

    }

}
