
package sample;

import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.entity.Control;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import javafx.util.Duration;

public class ReverseFlashControl extends Control {

    private AnimatedTexture texture;
    private AnimationChannel reverseflashIdle, reverseflashRun;
    private int speed = 0;

    public ReverseFlashControl(){

        //-- Creates the animated sprite

        reverseflashIdle = new AnimationChannel("reverse flash sprite --steady.png", 7, 1346/7, 170, Duration.seconds(1),0,6);
        reverseflashRun = new AnimationChannel("reverse flash sprite --steady.png", 7, 1346/7, 170, Duration.seconds(1),0,6);

        texture = new AnimatedTexture(reverseflashIdle);
    }

    @Override
    public void onAdded(Entity entity){
        entity.setView(texture);
    }

    @Override
    public void onUpdate(Entity entity, double v) {
        entity.translateX(speed * v);
        entity.translateY(speed * v);

        if(speed==0){
            texture.setAnimationChannel(reverseflashIdle);
        }

        else{
            texture.setAnimationChannel(reverseflashRun);
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

    public void up(){ speed = -20; }

    public void down(){
        speed = 20;
    }

}
