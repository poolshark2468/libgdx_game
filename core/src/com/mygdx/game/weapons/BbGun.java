package com.mygdx.game.weapons;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.sprites.BB;
import com.mygdx.game.sprites.Bullet;
import com.mygdx.game.sprites.SpriteObj;
import com.mygdx.game.world.World;

public class BbGun extends Weapon {
    private SpriteObj owner;

    public BbGun(SpriteObj owner) {
        super(owner, 0.5);
        this.owner = owner;
    }

    @Override
    public void fireWeapon() {
        //BB position
        Vector2 bbPos = new Vector2(owner.getPosition() );
        bbPos.y -= owner.HEIGHT/2;

        //Add BB to world
        world.addSprite( new BB(bbPos, owner.isAlly()) );
    }

}
