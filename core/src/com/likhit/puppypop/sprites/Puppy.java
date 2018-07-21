package com.likhit.puppypop.sprites;

//Puppy Class-Denotes our flying puppy.
//Contains all the necessary movements and velocity.
//Also contains position.

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Puppy {

    //Negative variable...subtracts position in y direction->showcases force of gravity.
    private static final int GRAVITY=-15;
    //Movement denotes movement in x direction has to be added to x position.
    private static final int MOVEMENT=100;

    private Vector3 position;
    private Vector3 velocity;
    //bounds->imaginary rectangle over the object.
    private Rectangle bounds;
    private Texture puppy;

    public Puppy(int x,int y) {
        position=new Vector3(x,y,0);
        velocity=new Vector3(0,0,0);
        puppy=new Texture("puppyfly.png");
        bounds=new Rectangle(x,y,puppy.getWidth(),puppy.getHeight());
    }

    //Gives coefficient of gravity
    public static int getGRAVITY() {
        return GRAVITY;
    }

    public Vector3 getPosition() {
        return position;
    }

    public void setPosition(Vector3 position) {
        this.position = position;
    }

    public Vector3 getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector3 velocity) {
        this.velocity = velocity;
    }

    public Texture getPuppy() {
        return puppy;
    }

    public void setPuppy(Texture puppy) {
        this.puppy = puppy;
    }


    public void update(float dt){
        //if puppy in above ground (ground at x=0)
        if(position.y>0){
            //velocity->lifts object up
            velocity.add(0,GRAVITY,0);
        }
        //scaling with ref to time.
        velocity.scl(dt);
        //displacement in x direction with ref to time.
        position.add(MOVEMENT*dt,velocity.y,0);
        //Avoids going below ground level
        if(position.y<0){
            position.y=0;
        }
        else if(position.y > (Gdx.graphics.getHeight()/2)-puppy.getHeight()){
            position.y=(Gdx.graphics.getHeight()/2-puppy.getHeight());
        }
        velocity.scl(1/dt);
        bounds.setPosition(position.x,position.y);
    }

    public Rectangle getBounds() {
        return bounds;
    }
//uplifts the bird in y direction by 250->opposition to gravity
    public void jump(){
        velocity.y=250;
    }
}
