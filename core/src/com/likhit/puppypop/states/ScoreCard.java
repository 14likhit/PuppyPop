package com.likhit.puppypop.states;

//ScoreCard->end state of the game.

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeType;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
//import com.gaboratorium.mytestgame.MyTestGame;
//import com.gaboratorium.mytestgame.helpers.Size;

import java.awt.geom.Rectangle2D;

public class ScoreCard extends State implements InputProcessor{

    //variable for background, replaybutton, title,highscores
    private Texture background;
    private Texture playBtn;
    private BitmapFont title;
    private BitmapFont highScoreType;
    private GlyphLayout highScoreTypeLayout;
    private GlyphLayout titleLayout;

    private FreeTypeFontGenerator fontGenerator;

    public ScoreCard(GameStateManager gsm)
    {
        super(gsm);
        camera.setToOrtho(false, Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2);
        background = new Texture("background.png");

        playBtn = new Texture("retrybtn.png");

        title = new BitmapFont();
        highScoreType = new BitmapFont();

        // Creating a font generator
        fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("FiraSans-Regular.otf"));
        FreeTypeFontGenerator fontGeneratorForTitle = new FreeTypeFontGenerator(Gdx.files.internal("Nightmare Before Christmas.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();



        parameter.size = 34;
        parameter.borderColor = Color.BLACK;
        parameter.borderWidth = 2;
        parameter.color = new Color(0, 208, 255, 1);
        title = fontGeneratorForTitle.generateFont(parameter);
        parameter.size = 28;
        //parameter.color = new Color(1, 1, 1, 1);
        parameter.color = new Color(255, 191, 0, 1);
        highScoreType = fontGenerator.generateFont(parameter);
        titleLayout = new GlyphLayout(title, "PuppyPop");
        highScoreTypeLayout = new GlyphLayout(highScoreType, "Score: " + PlayState.highscore );

        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void handleInput(){
        //if touched on screen  playstate begins
        if(Gdx.input.isTouched()){
            gsm.set(new PlayState(gsm));
        }
    }

    @Override
    public void update(float dt)
    {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb)
    {
        //set the required assets on the screen.
        sb.setProjectionMatrix(camera.combined);
        sb.begin();

        sb.draw(background, camera.position.x - (camera.viewportWidth / 2), - (-70) / 2);
        title.draw(sb, titleLayout, camera.viewportWidth / 2 - titleLayout.width / 2, camera.viewportHeight / 2 + 100);
        highScoreType.draw(sb, highScoreTypeLayout, camera.viewportWidth / 2 - highScoreTypeLayout.width / 2, camera.viewportHeight / 2 + 65);
        sb.draw(playBtn,background.getWidth()/2-playBtn.getWidth()/2-5,background.getHeight()/2-playBtn.getHeight()-25);

        sb.end();
    }

    @Override
    public void dispose()
    {
        background.dispose();
        playBtn.dispose();
        title.dispose();
        highScoreType.dispose();
        fontGenerator.dispose();
    }

    //Methods from Inputprocessors....to ive detail touches(like touch on particular image on screen.)
    // will be there with version2
    @Override
    public boolean keyDown(int keycode)
    {
        return false;
    }

    @Override
    public boolean keyUp(int keycode)
    {
        return false;
    }

    @Override
    public boolean keyTyped(char character)
    {
        return false;
    }

    Vector3 tp = new Vector3();
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button)
    {

        camera.unproject(tp.set(Gdx.input.getX(), Gdx.input.getY(), 0));
        //Rectangle bounds = new Rectangle(playBtnPos.x, playBtnPos.y, playBtnSize.getWidth(), playBtnSize.getHeight());

//        if (bounds.contains(tp.x, tp.y))
//        {
//            gsm.set(new PlayState(gsm));
//        }

        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button)
    {
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer)
    {
        return true;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY)
    {
        return false;
    }

    @Override
    public boolean scrolled(int amount)
    {
        return false;
    }

}


