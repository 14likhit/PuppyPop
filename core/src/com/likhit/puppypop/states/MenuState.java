package com.likhit.puppypop.states;

//Menu state ->start state for the game.

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Vector3;

public class MenuState extends State  {

    //variable for background, playbutton, title.
    private Texture background;
    private Texture playBtn;
    private BitmapFont title;
    private BitmapFont highScoreType;
    private GlyphLayout highScoreTypeLayout;
    private GlyphLayout titleLayout;

    private FreeTypeFontGenerator fontGenerator;


    private Music music;


    public MenuState(GameStateManager gsm) {
        super(gsm);
        background=new Texture("background.png");
        playBtn=new Texture("playbtn.png");

        //music file to start on start of game.
        music=Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
        music.setLooping(true);
        music.play();
        music.setVolume(0.3f);


        title = new BitmapFont();
        highScoreType = new BitmapFont();

        // Creating a font generator
        fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("FiraSans-Regular.otf"));
        FreeTypeFontGenerator fontGeneratorForTitle = new FreeTypeFontGenerator(Gdx.files.internal("Nightmare Before Christmas.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();


        parameter.size = 104;
        parameter.borderColor = Color.BLACK;
        parameter.borderWidth = 2;
        parameter.color = new Color(0, 208, 255, 1);
        title = fontGeneratorForTitle.generateFont(parameter);
        titleLayout = new GlyphLayout(title, "PuppyPop");

    }

    @Override
    public void handleInput() {
        //if touched on screen musics stops and playstate begins
        if(Gdx.input.isTouched()){
            music.stop();
            gsm.set(new PlayState(gsm));
        }
    }

    @Override
    public void update(float deltaTime) {
        handleInput();

    }

    @Override
    public void render(SpriteBatch bag) {
        //set the required assets on the screen.
        bag.begin();
        bag.draw(background,0,0, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        title.draw(bag, titleLayout, (Gdx.graphics.getWidth()/2-titleLayout.width/2),Gdx.graphics.getHeight()/2+playBtn.getHeight()+titleLayout.height+50);
        bag.draw(playBtn,(Gdx.graphics.getWidth()/2-playBtn.getWidth()/2),Gdx.graphics.getHeight()/2);
        bag.end();
    }

    @Override
    public void dispose() {

        background.dispose();
        playBtn.dispose();
        music.dispose();

    }

}
