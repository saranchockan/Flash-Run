package sample;

import com.almasb.fxgl.entity.*;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;



@SetEntityFactory
public class FlashFactory implements EntityFactory {

    //-- Creates the maze Entity
    @Spawns("maze")
    public Entity newMaze(SpawnData data){
        return Entities.builder()
                .from(data)
                .type(FlashType.MAZE)
                .bbox(new HitBox(BoundingShape.box(data.<Integer>get("width"),data.<Integer>get("height"))))
                .with(new PhysicsComponent())
                .build();
    }

    //-- Creates the flash Entity
    @Spawns("flash")
    public Entity newFlash(SpawnData data){
        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.DYNAMIC);
        return Entities.builder()
                .from(data)
                .type(FlashType.SPEEDSTER)
                .bbox(new HitBox(BoundingShape.box(658/16,49)))
                .with(physics)
                .with(new FlashControl())
                .build();
    }

    //-- Creates the reverse flash Entity
    @Spawns("reverse-flash")
    public Entity newReverse(SpawnData data){
        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.DYNAMIC);
        return Entities.builder()
                .type(FlashType.SPEEDSTER)
                .from(data)
                .bbox(new HitBox(BoundingShape.box(673/7,85)))
                .with(physics)
                .with(new ReverseFlashControl())
                .build();
    }




}
