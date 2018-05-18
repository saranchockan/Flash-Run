
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

public class FlashControl extends Control {

    private AnimatedTexture texture;
    private AnimationChannel flashIdle, flashRun;
    private int speed = 0;

    PhysicsComponent physics;

    public FlashControl(){

        //-- Creates the animated sprite

        flashIdle = new AnimationChannel("flash sprite --steady.png", 16, 658/16, 49, Duration.seconds(1),0,15);
        flashRun = new AnimationChannel("flash sprite-running.png", 10, 685/10, 49, Duration.seconds(1),0,9);
        texture = new AnimatedTexture(flashIdle);
    }

    @Override
    public void onAdded(Entity entity){
        entity.setView(texture);
    }

    @Override
    public void onUpdate(Entity entity, double v) {

        if(speed==0){
            entity.getBoundingBoxComponent().clearHitBoxes();
            entity.getBoundingBoxComponent().addHitBox(new HitBox(BoundingShape.box(685/16,49)));
            texture.setAnimationChannel(flashIdle);
            physics.setVelocityX(0);
        }

        else{
            entity.getBoundingBoxComponent().clearHitBoxes();
            entity.getBoundingBoxComponent().addHitBox(new HitBox(BoundingShape.box(685/10,49)));
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
        speed = -0;
        physics.setVelocityY(-300);
    }

    public void down(){
        speed = 0;
        physics.setVelocityY(300);
    }

}
