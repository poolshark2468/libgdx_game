package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.world.World;

import java.util.ArrayList;

public class BB extends SpriteObj {

    private final float SPEED = 100; // px/s
    private final Vector2 velocity;

    public static final double DAMAGE = 100.0;



    public BB(Vector2 initialPosition, boolean isAlly) {
        super(new Texture("Inkscape/bb.png"), 48/5, 48/5, isAlly, false);
        position = new Vector2( initialPosition);
        velocity = new Vector2(0, -SPEED);
    }

    @Override
    public void update(float dt) {
        position.x += velocity.x*dt;
        position.y += velocity.y*dt;

        //Check for collisions
        ArrayList<SpriteObj> allSprites =  (ArrayList<SpriteObj>) world.getAllSprites().clone();
        for (SpriteObj otherSprite : allSprites ) {
            if (this.isCollided(otherSprite)) {
                this.takeDamage(1); //Kill the BB
                otherSprite.takeDamage(DAMAGE);
            }

        }


        //Remove once it is off of the screen
        if (World.isOffScreen(position)) {
            this.removeFromWorld();
        }
    }

}
