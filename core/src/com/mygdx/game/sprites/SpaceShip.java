package com.mygdx.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.weapons.Gun;
import com.mygdx.game.weapons.Weapon;
import com.mygdx.game.world.Mouse;
import com.mygdx.game.world.World;

public class SpaceShip extends SpriteObj {

    private final float speed = 500; // px/s
    private Mouse mouse;

    private Weapon primaryWeapon;

    public SpaceShip() {
        super(new Texture("Inkscape/spaceship.png"), 322/6, 412/6, true, true);

        position = new Vector2(50, 200);
        velocity = new Vector2(0, 0);

        mouse = Mouse.getInstance();
        primaryWeapon = new Gun(this);

        health = 500;

    }

    private void updateVelocity() {
        int W = Gdx.input.isKeyPressed(Input.Keys.W)  ? 1:0;
        int A = Gdx.input.isKeyPressed(Input.Keys.A)  ? 1:0;
        int S = Gdx.input.isKeyPressed(Input.Keys.S)  ? 1:0;
        int D = Gdx.input.isKeyPressed(Input.Keys.D)  ? 1:0;

        velocity.x = (-A + D) * speed;
        velocity.y = (-S + W) * speed;
    }

    public void orientToMouse() {
        Vector2 dPos = new Vector2();
        dPos.x = mouse.getMousePosition().x - position.x;
        dPos.y = mouse.getMousePosition().y - position.y;
        rotation = dPos.angle() - 90;
    }

    @Override
    public void die() {
        super.die();
        System.out.println("SpaceShip died!");
    }


    @Override
    public void update(float dt) {
        updateVelocity();
        orientToMouse();

        primaryWeapon.update(dt);

        // Move ship with keyboard
        position.x += velocity.x*dt;
        position.y += velocity.y*dt;

        //Keep ship from going out of bounds
        if (position.x < 0) { position.x = 0; }
        if (position.y < 0) { position.y = 0; }
        if (position.x > Gdx.graphics.getWidth())  { position.x = Gdx.graphics.getWidth();  };
        if (position.y > Gdx.graphics.getHeight()) { position.y = Gdx.graphics.getHeight(); };


        primaryWeapon.setFiring(Gdx.input.isTouched() || Gdx.input.justTouched()); //Primary weapon fires if/while the mouse is held down
        //primaryWeapon.update(dt);

        // Move ship to mouse position
        //position.x = mouse.getMousePosition().x;
        //position.y = mouse.getMousePosition().y;


    }
}
