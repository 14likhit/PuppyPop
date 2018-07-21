package com.likhit.puppypop.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.likhit.puppypop.PuppyPop;
import com.likhit.puppypop.sprites.Balloons;
import com.likhit.puppypop.sprites.Puppy;

import java.util.Random;

public class PlayState extends State  {
    
    //SPACING between two balloon sets
    //Count of no of balloon set to be created and used during game.
    private static final int SPACING=100;
    private static final int COUNT=8;
    
    //required variable declarations
    private Puppy puppy;
    private Texture bg;
    private Array<Balloons> balloons;
    private Random rand1;
    private Array<Integer> ran;

    public static int highscore;
    private int score;

    private String yourScore;
    private BitmapFont scoreBitmapFontName;

    private float timer;
    private String time;
    private BitmapFont timeBitmapFontName;

    private Sound balloonBurst;
    private Sound end;


    public PlayState(GameStateManager gsm) {
        super(gsm);
        puppy=new Puppy(50,300);
        //set camera on view port current arena.
        camera.setToOrtho(false, Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2);
        bg=new Texture("clouds.png");
        //creating count balloons set
        balloons=new Array<Balloons>();
        for (int i=1;i<=COUNT;i++){
            balloons.add(new Balloons(i*(SPACING+Balloons.WIDTH)));
        }
        //initialise score to 0
        score=0;
        //initilise on screen score to 0 at start
        yourScore = "Score: 0";
        scoreBitmapFontName = new BitmapFont();
        //initilise on screen timer to 30 at start
        timer=30;
        time="Time: 30";
        timeBitmapFontName=new BitmapFont();
        //Sound file for bursting of balloon
        balloonBurst=Gdx.audio.newSound(Gdx.files.internal("balloonPop.mp3"));
        end=Gdx.audio.newSound(Gdx.files.internal("End.mp3"));

    }

    @Override
    protected void handleInput() {

        //if touched on screen puppy jump
        if(Gdx.input.justTouched()){
            puppy.jump();
        }

    }

    @Override
    public void update(float deltaTime) {
        handleInput();
        puppy.update(deltaTime);
        //80 offset to camera on screen to proerly view puppy moving in x direction
        camera.position.x=puppy.getPosition().x+80;
        for(Balloons balloon:balloons){
            //for each balloon set check if balloon surpassess screen
            //if yes reposition it in sequence
            if(camera.position.x-(camera.viewportWidth/2)>balloon.getPosBalloon().x+balloon.getBalloon().getWidth()){
                balloon.reposition(balloon.getPosBalloon().x + ((Balloons.WIDTH+SPACING)*COUNT));
            }
            //check balloon burst
            if(balloon.burst(puppy.getBounds())){
                //add cnt we set in balloons as per texture to score
                score+=balloon.getCnt();
                //add score to label to show it on screen
                yourScore = "Score: " + score;
                //dispace bound rectangle to 0,0 to avoid further overlap->to maintain touch
                balloon.getBoundsBalloon().setPosition(0,0);
                //set transparent image to show balloon is bursted
                balloon.setBalloon(new Texture("transparent.png"));
                //pay balloon bursting sound
                balloonBurst.play(0.5f);

            }
            if(balloon.burst1(puppy.getBounds())){
                score+=balloon.getCnt1();
                yourScore = "Score: " + score;
                balloon.getBoundsBalloon1().setPosition(0,0);
                balloon.setBalloon1(new Texture("transparent.png"));
                balloonBurst.play(0.5f);
            }
            if(balloon.burst2(puppy.getBounds())){
                score+=balloon.getCnt2();
                yourScore = "Score: " + score;
                balloon.getBoundsBalloon2().setPosition(0,0);
                balloon.setBalloon2(new Texture("transparent.png"));
                balloonBurst.play(0.5f);
            }
            //if timer==0 end game and set state to Scorecard
            //set highscore to show it on Scorecard state.
            //adding end buzer
            if(((int)timer)==0){
                end.play(0.5f);
                end.dispose();
                highscore=score;
                gsm.set(new ScoreCard(gsm));
            }


        }
        //timer update and setting it on screen
        timer-=deltaTime;
        time="Time : " + Integer.toString((int)timer);
        camera.update();
    }

    @Override
    public void render(SpriteBatch bag) {


        bag.setProjectionMatrix(camera.combined);
        bag.begin();
        //set respective assests on screen
        bag.draw(bg,camera.position.x-(camera.viewportWidth/2),0);
        bag.draw(puppy.getPuppy(),puppy.getPosition().x,puppy.getPosition().y);
        scoreBitmapFontName.setColor(1.0f, 1.0f, 1.0f, 1.0f);
        scoreBitmapFontName.draw(bag, yourScore, camera.position.x-(camera.viewportWidth/2), 2*camera.position.y);
        timeBitmapFontName.setColor(255, 0, 0, 1.0f);
        timeBitmapFontName.draw(bag, time, camera.position.x + (camera.viewportWidth/4), 2*camera.position.y);
        for(Balloons balloon:balloons){
            bag.draw(balloon.getBalloon(),balloon.getPosBalloon().x,balloon.getPosBalloon().y);
            bag.draw(balloon.getBalloon1(),balloon.getPosBalloon1().x,balloon.getPosBalloon1().y);
            bag.draw(balloon.getBalloon2(),balloon.getPosBalloon2().x,balloon.getPosBalloon2().y);
        }

        bag.end();

    }


    @Override
    public void dispose() {
        balloonBurst.dispose();
        end.dispose();
    }


}
