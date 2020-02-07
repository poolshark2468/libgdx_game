package com.mygdx.game.levels;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.sprites.UFO;
import com.mygdx.game.world.World;

public class UfoWave1 extends Wave {

    private World world = World.getInstance();

    @Override
    public void runWave() {
        for (int ii = 1; ii < 6; ii++) {
            float xPos = (float) (Gdx.graphics.getWidth()*(ii/6.0) );
            world.addSprite(new UFO(xPos));
        }
    }
}
