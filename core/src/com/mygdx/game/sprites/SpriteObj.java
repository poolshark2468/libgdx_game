package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.world.Updatable;
import com.mygdx.game.world.World;

public abstract class SpriteObj implements Updatable {



    protected Vector2 position;
    protected Vector2 velocity;
    protected float rotation; //degT

    protected final World world = World.getInstance();

    private final Texture texture;
    private final TextureRegion textureRegion;

    public final float WIDTH;
    public final float HEIGHT;

    protected double health;

    protected final boolean isAlly;
    protected final boolean isCollidable;

    protected boolean isAlive = true;


    protected SpriteObj(Texture texture, float WIDTH, float HEIGHT, boolean isAlly, boolean isCollidable) {
        this.texture = texture;
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
        this.isAlly = isAlly;
        this.isCollidable = isCollidable;

        this.textureRegion = new TextureRegion(texture);

        rotation = 0;

    }

    public boolean isAlly() {
        return isAlly;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void removeFromWorld() {
        world.removeSprite(this);
    }

    public void die() {
        // Default: just remove from world
        removeFromWorld();
        isAlive = false;
    }


    public void draw(SpriteBatch sb) {


        sb.draw( textureRegion,
                position.x - WIDTH/2,
                position.y - HEIGHT/2,
                WIDTH/2,
                HEIGHT/2,
                WIDTH,
                HEIGHT,
                1,
                1,
                rotation);

    }

    public void takeDamage(double damage) {
        //Returns true if damage is lethal
        health -= damage;

        if (health <= 0) {
            die();
        }
    }


    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public Vector2 getPosition() {
        return position;
    }

    public float getRotation() {
        return rotation;
    }

    public abstract void update(float dt);

    public Polygon getPolygon() {
        float [] vertices = {   position.x-WIDTH/2, position.y -HEIGHT/2, //bottom-left
                                position.x-WIDTH/2, position.y +HEIGHT/2, //top-left
                                position.x+WIDTH/2, position.y +HEIGHT/2, //top-right
                                position.x+WIDTH/2, position.y -HEIGHT/2, //bottom-right
                            }; //Note that vertices must be clockwise for Intersector to work

        Polygon p = new Polygon(vertices);
        p.setOrigin(position.x, position.y);
        p.rotate(rotation);
        return p;
    }

    public boolean isCollided(SpriteObj otherSprite) {
        if (!otherSprite.isCollidable) return false;
        if (this.isAlly == otherSprite.isAlly) return false;

        return Intersector.overlapConvexPolygons(this.getPolygon(), otherSprite.getPolygon());
    }






}
