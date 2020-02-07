package com.mygdx.game.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.sprites.SpriteObj;

import java.util.ArrayList;

public class World {

    //Collections of all sprites, in various groups
    private static ArrayList<SpriteObj> allSprites = new ArrayList<>();


    private static World instance;




    protected World() {
    }

    public static World getInstance() {
        if (instance==null) {
             instance = new World();
        }

        return instance;
    }

    public ArrayList<SpriteObj> getAllSprites() {
        return allSprites;
    }


    public void addSprite(SpriteObj sprite) {
        allSprites.add(sprite);
    }

    public boolean removeSprite(SpriteObj sprite) {
        return allSprites.remove(sprite);
    }

    public static boolean isOffScreen(Vector2 vec) {
        return ( (vec.x < 0)
                || (vec.y < 0)
                || (vec.x > Gdx.graphics.getWidth())
                || (vec.y > Gdx.graphics.getHeight()));
    }

    public static void reset() {
        allSprites = new ArrayList<SpriteObj>();

    }

}
