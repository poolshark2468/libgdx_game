package com.mygdx.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.weapons.BbGun;
import com.mygdx.game.weapons.Weapon;
import com.mygdx.game.world.World;

public class UFO extends SpriteObj {

    private float timeElapsed = 0;

    Weapon bbGun;

    public UFO(float xPos) {
        super(new Texture("Inkscape/ufo.png"), 380/8, 150/8, false, true);

        position = new Vector2(xPos, Gdx.graphics.getHeight());
        velocity = new Vector2(0,0);

        bbGun = new BbGun(this);

        this.health = 500;

    }

    private void updateVelocity() {
        if (timeElapsed < 1) {
            velocity.y = -100;
        } else if (timeElapsed < 3){
            velocity.y = 0;
        } else if (timeElapsed < 4) {
            velocity.y = -200;
        } else {
            velocity.y = 0;
        }
    }

    private void updateIsFiring() {
        if (timeElapsed < 1) {
            bbGun.setFiring(false);
        } else {
            bbGun.setFiring(true);
        }
    }

    @Override
    public void update(float dt) {
        timeElapsed += dt;
        updateVelocity();

        position.x += velocity.x*dt;
        position.y += velocity.y*dt;

        updateIsFiring();
        bbGun.update(dt);


        //Remove once it is off of the screen
        if (World.isOffScreen(position)) {
            this.removeFromWorld();
        }


    }
}
