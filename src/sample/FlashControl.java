
package sample;

import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.entity.Control;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import javafx.util.Duration;

public class FlashControl extends Control {

    private AnimatedTexture texture;
    private AnimationChannel flashIdle, flashRun;
    private int speed = 0;

    PhysicsComponent physics;

    public FlashControl(){

        //-- Creates the animated sprite

        flashIdle = new AnimationChannel("flash sprite --steady.png", 16, 1315/16, 97, Duration.seconds(1),0,15);
        flashRun = new AnimationChannel("flash sprite-running.png", 10, 1370/10, 97, Duration.seconds(1),0,9);
        texture = new AnimatedTexture(flashIdle);
    }

    @Override
    public void onAdded(Entity entity){
        entity.setView(texture);
    }

    @Override
    public void onUpdate(Entity entity, double v) {

        if(speed==0){
            texture.setAnimationChannel(flashIdle);
            physics.setVelocityX(0);
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
        speed = -700;
        physics.setVelocityX(-700);
        getEntity().setScaleX(-1);
    }

    public void right(){
        speed = 700;
        physics.setVelocityX(700);
        getEntity().setScaleX(1);
    }

    public void up(){
        speed = -700;
        physics.setVelocityY(-300);
    }

    public void down(){
        speed = 700;
        physics.setVelocityY(300);
    }

}
