package com.mygdx.game.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class Mouse {

    private static final Mouse instance = new Mouse();

    private Vector2 mousePosition = new Vector2();

    private Mouse() {
        //private constructor
    }

    public static Mouse getInstance() {
        return instance;
    }

    public void updateMouse() {
        Vector3 mouseInWorld3D = new Vector3();
        mouseInWorld3D.x = Gdx.input.getX();
        mouseInWorld3D.y = Gdx.input.getY();
        mouseInWorld3D.z = 0;
        //cam.unproject(mouseInWorld3D); //Convert to normalized coordinates
        mousePosition.x = mouseInWorld3D.x;
        mousePosition.y = Gdx.graphics.getHeight() - 1 - mouseInWorld3D.y; //convert from touch to screen coordinates
    }

    public Vector2 getMousePosition() {
        updateMouse();
        return mousePosition;
    }
}
