package com.likhit.puppypop.states;

//Parent class for all the states in the game.
//Contains all the necessary methods and variables.
//Abstract needs to be extended.

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public abstract class State {
    protected OrthographicCamera camera;
    protected Vector3 mousept;
    protected GameStateManager gsm;

    public State(GameStateManager gsm) {
        this.gsm = gsm;
        camera=new OrthographicCamera();
        mousept=new Vector3();
    }

    protected abstract void handleInput();
    public abstract void update(float deltaTime);
    public abstract void render(SpriteBatch bag);
    public abstract void dispose();
}
