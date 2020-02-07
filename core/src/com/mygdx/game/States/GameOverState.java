package com.mygdx.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.world.World;

public class GameOverState extends State {
    public GameOverState(GameStateManager gsm) {
        super(gsm);
    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {

        if (Gdx.input.justTouched()) {
            World.reset();
            gsm.set(new RunState(gsm));
        }

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(new Texture("Inkscape/gameOverMsg.png"), 100, 300);
        sb.end();

    }
}
