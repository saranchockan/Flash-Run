
package sample;

import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.entity.Control;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import javafx.util.Duration;

public class ReverseFlashControl extends Control {

    private AnimatedTexture texture;
    private AnimationChannel reverseflashIdle, reverseflashRun;
    private int speed = 0;
    private boolean down;
    PhysicsComponent physics;

    public ReverseFlashControl(){

        //-- Creates the animated sprite
        reverseflashIdle = new AnimationChannel("reverse flash sprite --steady.png", 7, 673/7, 85, Duration.seconds(1),0,6);
        reverseflashRun = new AnimationChannel("reverse flash sprite-running .png", 7, 1134/7, 77, Duration.seconds(1),0,6);
        texture = new AnimatedTexture(reverseflashRun);
        down = true;

    }

    @Override
    public void onAdded(Entity entity){
        entity.setView(texture);
        entity.setScaleX(-1);
    }

    @Override
    public void onUpdate(Entity entity, double v) {

        if(down){
            physics.setVelocityY(500);
        }
        if(speed==0){
            down = true;

            entity.getBoundingBoxComponent().clearHitBoxes();
            entity.getBoundingBoxComponent().addHitBox(new HitBox(BoundingShape.box(673/7,85)));
            texture.setAnimationChannel(reverseflashIdle);
            physics.setVelocityX(0);

        }
        else{
            down = true;
            entity.getBoundingBoxComponent().clearHitBoxes();
            entity.getBoundingBoxComponent().addHitBox(new HitBox(BoundingShape.box(1134/7,77)));
            texture.setAnimationChannel(reverseflashRun);
            speed = (int) (speed * 0.4);
            physics.setVelocityX(physics.getVelocityX()/2);
            physics.setVelocityY(physics.getVelocityY()/2);

            if (FXGLMath.abs(speed) < 1) {
                speed = 0;
            }
        }
    }


    public void left(){
        down = false;
        speed = -1300;
        physics.setVelocityX(-1300);
        physics.setVelocityY(500);
        getEntity().setScaleX(-1);
    }

    public void right(){
        down = false;
        speed = 1300;
        physics.setVelocityX(1300);
        physics.setVelocityY(500);
        getEntity().setScaleX(1);

    }

    public void up(){
        down = false;
        speed = -1100;
        physics.setVelocityY(-1000);
    }

    public void down(){
        down = false;
        speed = 500;
        physics.setVelocityY(500);
    }

    public void flight(){
        down = false;
        speed = -100;
        physics.setVelocityY(-100);
    }

    public void switchRight(){
        getEntity().setScaleX(1);
    }

    public void switchLeft(){
        getEntity().setScaleX(-1);
    }

}
