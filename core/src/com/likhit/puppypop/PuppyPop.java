package com.likhit.puppypop;

//Main BAAP class

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.likhit.puppypop.states.GameStateManager;
import com.likhit.puppypop.states.MenuState;

public class PuppyPop extends ApplicationAdapter {

	private GameStateManager gsm;
	private SpriteBatch batch;

	@Override
	public void create () {
		batch = new SpriteBatch();
		gsm=new GameStateManager();
		Gdx.gl.glClearColor(1, 0, 0, 1);
		//on create start MenuState
		gsm.push(new MenuState(gsm));
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);
		float deltaTime = Gdx.graphics.getDeltaTime(); //You might prefer getRawDeltaTime()

	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
