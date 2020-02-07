package com.mygdx.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.levels.UfoWave1;
import com.mygdx.game.levels.Wave;
import com.mygdx.game.sprites.SpaceShip;
import com.mygdx.game.sprites.SpriteObj;
import com.mygdx.game.sprites.UFO;
import com.mygdx.game.world.Updatable;
import com.mygdx.game.world.World;

import java.util.ArrayList;
import java.util.Iterator;

public class RunState extends State {
    World world = World.getInstance();
    Wave wave1 = new UfoWave1();
    SpaceShip spaceShip = new SpaceShip();

    public RunState(GameStateManager gsm) {
        super(gsm);


        world.addSprite(spaceShip);

    }

    @Override
    protected void handleInput() {


    }

    @Override
    public void update(float dt) {

        ArrayList<SpriteObj> allSprites =  (ArrayList<SpriteObj>) world.getAllSprites().clone();
        for ( SpriteObj sprite :  allSprites ) {
            sprite.update(dt);
        }

        //Continuously generate waves
        boolean hasUFO = false;
        for (SpriteObj sprite : allSprites) {
            if (sprite instanceof UFO) {
                hasUFO = true;
                break;
            }
        }
        if (!hasUFO) {
            wave1.runWave();
        }

        //Check that the spaceship is still alive
        if (! spaceShip.isAlive()) {
            gsm.set(new GameOverState(gsm));
        }


    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        for (SpriteObj sprite : world.getAllSprites()) {
            sprite.draw(sb);
        }
        sb.end();


    }
}
