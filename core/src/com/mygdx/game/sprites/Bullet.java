package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.world.World;

import java.util.ArrayList;

public class Bullet extends SpriteObj {

    private final float SPEED = 350; // px/s
    private Vector2 velocity;

    public static final double DAMAGE = 100.0;


    public Bullet(Vector2 initialPosition, float rotation, boolean isAlly) {
        super(new Texture("Inkscape/bullet.png"), 48/6, 240/6, isAlly, false);
        position = new Vector2( initialPosition);
        this.rotation = rotation;

        velocity = new Vector2(SPEED, 0);
        velocity.rotate(rotation + 90);

        this.health = 1;

    }

    @Override
    public void update(float dt) {
        position.x += velocity.x*dt;
        position.y += velocity.y*dt;

        //Check for collisions
        ArrayList<SpriteObj> allSprites =  (ArrayList<SpriteObj>) world.getAllSprites().clone();
        for (SpriteObj otherSprite : allSprites ) {
            if (this.isCollided(otherSprite)) {
                this.takeDamage(1); //Kill the bullet
                otherSprite.takeDamage(DAMAGE);
            }

        }

        //Remove once it is off of the screen
        if (World.isOffScreen(position)) {
            this.removeFromWorld();
        }
    }
}
