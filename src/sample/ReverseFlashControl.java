
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
    
    PhysicsComponent physics;

    public ReverseFlashControl(){

        //-- Creates the animated sprite

        reverseflashIdle = new AnimationChannel("reverse flash sprite --steady.png", 7, 673/7, 85, Duration.seconds(1),0,6);
        reverseflashRun = new AnimationChannel("reverse flash sprite-running .png", 7, 1134/7, 77, Duration.seconds(1),0,6);
        texture = new AnimatedTexture(reverseflashRun);
    }

    @Override
    public void onAdded(Entity entity){ entity.setView(texture); }

    @Override
    public void onUpdate(Entity entity, double v) {

        if(speed==0){
            entity.getBoundingBoxComponent().clearHitBoxes();
            entity.getBoundingBoxComponent().addHitBox(new HitBox(BoundingShape.box(673/7,85)));
            texture.setAnimationChannel(reverseflashIdle);
            physics.setVelocityX(0);
        }
        else{
            entity.getBoundingBoxComponent().clearHitBoxes();
            entity.getBoundingBoxComponent().addHitBox(new HitBox(BoundingShape.box(1134/7,77)));
            texture.setAnimationChannel(reverseflashRun);
            speed = (int) (speed * 0.4);

            if (FXGLMath.abs(speed) < 1) {
                speed = 0;
            }
        }
    }


    public void left(){
        speed = -1500;
        physics.setVelocityX(-1500);
        getEntity().setScaleX(-1);
    }

    public void right(){
        speed = 1500;
        physics.setVelocityX(1500);
        getEntity().setScaleX(1);

    }

    public void up(){
        speed = -1500;
        physics.setVelocityY(-300);
    }

    public void down(){
        speed = 1500;
        physics.setVelocityY(300);
    }

}
