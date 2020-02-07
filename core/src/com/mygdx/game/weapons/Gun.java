package com.mygdx.game.weapons;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.sprites.Bullet;
import com.mygdx.game.sprites.SpriteObj;
import com.mygdx.game.world.World;
import com.badlogic.gdx.physics.box2d.Transform;


public class Gun extends Weapon {


    public Gun(SpriteObj owner) {
        super(owner, 0.25);
    }

    @Override
    public void fireWeapon() {

        // Bullet positions relative to owner
        Vector2 deltaPos1 = new Vector2((float) (owner.WIDTH/2 *0.5), (float) (owner.HEIGHT/2 *0.8) );
        Vector2 deltaPos2 = new Vector2(deltaPos1);
        deltaPos2.x = -deltaPos2.x;
        deltaPos1.rotate(owner.getRotation());
        deltaPos2.rotate(owner.getRotation());

        //Bullet position
        Vector2 bulletPos1 = new Vector2(owner.getPosition() );
        Vector2 bulletPos2 = new Vector2(owner.getPosition() );
        bulletPos1.add(deltaPos1);
        bulletPos2.add(deltaPos2);

        //Add bullets to world
        Bullet bullet1 = new Bullet(bulletPos1, owner.getRotation(), owner.isAlly());
        Bullet bullet2 = new Bullet(bulletPos2, owner.getRotation(), owner.isAlly());
        World world = World.getInstance();
        world.addSprite(bullet1);
        world.addSprite(bullet2);
    }


}
