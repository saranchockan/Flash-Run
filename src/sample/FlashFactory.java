package sample;

import com.almasb.fxgl.entity.*;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;


@SetEntityFactory
public class FlashFactory implements EntityFactory {


    //-- Creates the maze Entity
    @Spawns("maze")
    public Entity newMaze(SpawnData data){
        return Entities.builder()
                .from(data)
                .bbox(new HitBox(BoundingShape.box(data.<Integer>get("width"),data.<Integer>get("height"))))
                .with(new PhysicsComponent())
                .build();
    }

    //-- Creates the flash Entity
    @Spawns("flash")
    public Entity newFlash(SpawnData data){
        return Entities.builder()
                .from(data)
                .bbox(new HitBox(BoundingShape.box(30,30)))
                .with(new FlashControl())
                .build();

    }

    //-- Creates the reverse flash Entity
    @Spawns("reverse-flash")
    public Entity newReverse(SpawnData data){
        return Entities.builder()
            .from(data)
            .bbox(new HitBox(BoundingShape.box(30,30)))
            .with(new ReverseFlashControl())
            .build();

    }


}
