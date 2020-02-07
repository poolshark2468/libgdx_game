package com.mygdx.game.weapons;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.sprites.SpriteObj;
import com.mygdx.game.world.Updatable;
import com.mygdx.game.world.World;

public abstract class Weapon implements Updatable {

    protected final double DUTY_CYCLE; //Min time between firing
    private float timeSinceLastFired = Float.MAX_VALUE;

    protected Vector2 position;
    protected float rotation; //degT

    protected World world = World.getInstance();

    protected SpriteObj owner;

    private boolean isFiring = false;

    protected Weapon(SpriteObj owner, double dutyCycle) {
        this.owner = owner;
        this.DUTY_CYCLE = dutyCycle;
    }



    public void setFiring(boolean firing) {
        isFiring = firing;
    }

    public void fire() {

        if (timeSinceLastFired > DUTY_CYCLE) {
            fireWeapon();
            timeSinceLastFired = 0;
        }
    };

    public void fireWeapon() {

    }


    public void update(float dt) {
        timeSinceLastFired += dt;

        if (isFiring) {
            fire();
        }
    }


}
