package sample;

import com.almasb.fxgl.entity.*;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

@SetEntityFactory
public class FlashFactory implements EntityFactory {

    @Spawns("maze")
    public Entity newMaze(SpawnData data){
        return Entities.builder()
                .from(data)
                .bbox(new HitBox(BoundingShape.box(data.<Integer>get("width"),data.<Integer>get("height"))))
                .with(new PhysicsComponent())
                .build();
    }

    @Spawns("flash")
    public Entity newFlash(SpawnData data){

        return Entities.builder()
                .from(data)
                .bbox(new HitBox(BoundingShape.box(30,30)))
                .with(new FlashControl())
                .build();
    }

}
