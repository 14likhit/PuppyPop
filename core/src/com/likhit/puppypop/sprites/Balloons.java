package com.likhit.puppypop.sprites;

//Balloons-->gives the  sets of colourful balloons on screen.

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Balloons {

    //WIDTH  of a balloon
    //Random function FLUCTUATION limit 0 to 130
    //GAP configuring variable between two balloons in a set
    //minimum offset from ground
    public static final int WIDTH=50;
    private static final int FLUCTUATION=130;
    private static final int GAP=170;
    private static final int LOWEST_OPENING=100;

    //Configuration Variables for set of three balloons.
    private Texture balloon,balloon1,balloon2;
    private Vector2 posBalloon,posBalloon1,posBalloon2;
    private Rectangle boundsBalloon,boundsBalloon1,boundsBalloon2;

    //configuration variables for random function and score cnt
    private Random rand;
    private Random rand1;
    private int ran;
    //conters are set as per texture.
    private int cnt,cnt1,cnt2;
    //balloon.png->5pts
    //balloon1.png->10pts
    //balloon2.png->15pts
    //balloon3.png->0pts
    //transperent.png->0pts



    public Balloons(float x) {
        rand=new Random();
        rand1=new Random();
        ran=rand1.nextInt(4);
        //random logic to get random texture during creation.
        // And setting counter parameter along with that.
        if (ran==1){
            balloon=new Texture("balloon3.png");
            cnt=0;
            balloon1=new Texture("balloon2.png");
            cnt1=15;
            balloon2=new Texture("balloon.png");
            cnt2=5;
        }
        else if(ran==2){
            balloon=new Texture("balloon1.png");
            cnt=10;
            balloon1=new Texture("transparent.png");
            cnt1=0;
            balloon2=new Texture("balloon3.png");
            cnt2=0;
        }
        else if(ran==3){
            balloon=new Texture("balloon.png");
            cnt=5;
            balloon1=new Texture("balloon1.png");
            cnt1=10;
            balloon2=new Texture("balloon2.png");
            cnt2=15;
        }
        else{
            balloon=new Texture("transparent.png");
            cnt=0;
            balloon1=new Texture("balloon3.png");
            cnt1=0;
            balloon2=new Texture("balloon1.png");
            cnt2=10;
        }
        //setting positions of set of 3 balloon using random fluctuation and declared variables
        posBalloon=new Vector2(x,rand.nextInt(FLUCTUATION)+GAP+LOWEST_OPENING);
        posBalloon1=new Vector2(x,posBalloon.y-GAP/2-balloon1.getHeight());
        posBalloon2=new Vector2(x,posBalloon1.y-GAP/2-balloon2.getHeight());

        //Imaginary rectangles for each balloon
        boundsBalloon=new Rectangle(posBalloon.x,posBalloon.y,balloon.getWidth(),balloon.getHeight());
        boundsBalloon1=new Rectangle(posBalloon1.x,posBalloon1.y,balloon1.getWidth(),balloon1.getHeight());
        boundsBalloon2=new Rectangle(posBalloon2.x,posBalloon2.y,balloon2.getWidth(),balloon2.getHeight());
    }

    //every time balloons passess off the screen they get again reposition in sequence
    //to continue using same created object.
    //here also random logic to select texture and counters.
    public void reposition(float x){
        if (ran==1){
            int ballon=rancolour();
            if(ballon==1||ballon==2){
                setBalloon(new Texture("balloon1.png"));
                cnt=10;
                setBalloon1(new Texture("balloon.png"));
                cnt1=5;
                setBalloon2(new Texture("balloon3.png"));
                cnt2=0;
            }
            else{
                setBalloon(new Texture("balloon2.png"));
                cnt=15;
                setBalloon1(new Texture("transparent.png"));
                cnt1=0;
                setBalloon2(new Texture("balloon3.png"));
                cnt2=0;
            }
        }
        else if(ran==2){
            int ballon=rancolour();
            if(ballon==1||ballon==2){
                setBalloon(new Texture("balloon.png"));
                cnt=5;
                setBalloon1(new Texture("balloon2.png"));
                cnt1=15;
                setBalloon2(new Texture("balloon3.png"));
                cnt2=0;
            }
            else if(ballon==0){
                setBalloon(new Texture("balloon1.png"));
                cnt=15;
                setBalloon1(new Texture("balloon.png"));
                cnt1=5;
                setBalloon2(new Texture("transparent.png"));
                cnt2=0;
            }
            else{
                setBalloon(new Texture("transparent.png"));
                cnt=0;
                setBalloon1(new Texture("balloon.png"));
                cnt1=5;
                setBalloon2(new Texture("balloon2.png"));
                cnt2=15;
            }
        }
        else if(ran==3){
            int ballon=rancolour();
            if(ballon==1||ballon==2){
                setBalloon(new Texture("balloon1.png"));
                cnt=10;
                setBalloon1(new Texture("balloon3.png"));
                cnt1=0;
                setBalloon2(new Texture("balloon2.png"));
                cnt2=15;
            }

            else{
                setBalloon(new Texture("balloon3.png"));
                cnt=0;
                setBalloon1(new Texture("transparent.png"));
                cnt1=0;
                setBalloon2(new Texture("balloon.png"));
                cnt2=5;
            }
        }
        else{
            int ballon=rancolour();
            if(ballon==1||ballon==2){
                setBalloon(new Texture("balloon1.png"));
                cnt=10;
                setBalloon1(new Texture("balloon2.png"));
                cnt1=15;
                setBalloon2(new Texture("balloon.png"));
                cnt2=5;
            }
        }

        //setting positions of set of 3 balloon using random fluctuation and declared variables
        posBalloon.set(x,rand.nextInt(FLUCTUATION)+GAP+LOWEST_OPENING);
        posBalloon1.set(x,posBalloon.y-GAP/2-balloon1.getHeight());
        posBalloon2.set(x,posBalloon1.y-GAP/2-balloon2.getHeight());

        //settings position of Imaginary rectangles for each balloon
        boundsBalloon.setPosition(posBalloon.x,posBalloon.y);
        boundsBalloon1.setPosition(posBalloon1.x,posBalloon1.y);
        boundsBalloon2.setPosition(posBalloon2.x,posBalloon2.y);
    }

    public int getCnt() {
        return cnt;
    }

    //to detect collision
//    public boolean collide(Rectangle player){
//        return player.overlaps(boundsBalloon1);
//    }

    //to detect balloon bursts..if player overlaps rectangle remove rectangle from top of balloon.
    public boolean burst(Rectangle player){
        return (player.overlaps(boundsBalloon));
    }
    public boolean burst1(Rectangle player){
        return (player.overlaps(boundsBalloon1));
    }
    public boolean burst2(Rectangle player){
        return (player.overlaps(boundsBalloon2));
    }

    //random function logic's method for getting random textures
    public int rancolour(){
        Random colour=new Random();
        return colour.nextInt(3);
    }

    public int getRan() {
        return ran;
    }

    public Texture getBalloon() {
        return balloon;
    }

    public void setBalloon(Texture balloon) {
        this.balloon = balloon;
    }

    public Texture getBalloon1() {
        return balloon1;
    }

    public void setBalloon1(Texture balloon1) {
        this.balloon1 = balloon1;
    }

    public Vector2 getPosBalloon() {
        return posBalloon;
    }

    public void setPosBalloon(Vector2 posBalloon) {
        this.posBalloon = posBalloon;
    }

    public Vector2 getPosBalloon1() {
        return posBalloon1;
    }

    public void setPosBalloon1(Vector2 posBalloon1) {
        this.posBalloon1 = posBalloon1;
    }

    public Rectangle getBoundsBalloon() {
        return boundsBalloon;
    }

    public Rectangle getBoundsBalloon1() {
        return boundsBalloon1;
    }

    public void setBoundsBalloon(Rectangle boundsBalloon) {
        this.boundsBalloon = boundsBalloon;
    }

    public void setBoundsBalloon1(Rectangle boundsBalloon1) {
        this.boundsBalloon1 = boundsBalloon1;
    }

    public Texture getBalloon2() {
        return balloon2;
    }

    public void setBalloon2(Texture balloon2) {
        this.balloon2 = balloon2;
    }

    public Vector2 getPosBalloon2() {
        return posBalloon2;
    }

    public void setPosBalloon2(Vector2 posBalloon2) {
        this.posBalloon2 = posBalloon2;
    }

    public Rectangle getBoundsBalloon2() {
        return boundsBalloon2;
    }

    public void setBoundsBalloon2(Rectangle boundsBalloon2) {
        this.boundsBalloon2 = boundsBalloon2;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public int getCnt1() {
        return cnt1;
    }

    public void setCnt1(int cnt1) {
        this.cnt1 = cnt1;
    }

    public int getCnt2() {
        return cnt2;
    }

    public void setCnt2(int cnt2) {
        this.cnt2 = cnt2;
    }
}
