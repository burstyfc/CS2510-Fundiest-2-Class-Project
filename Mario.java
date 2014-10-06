/*
assignment 8
pair 2
Chao Fang
bursty
Amanda Fode
afode11
 */
import javalib.worldimages.*;


import java.util.*;

//to represent a collections of all needed 
// constants in this game
interface MarioConstants {
    //a random number generator
    public Random rand = new Random();

    //the width of the world
    public int width = 700;

    //the height of the world
    public int height = 400;

    //the height of the ground
    public int ground = 80;

    //the speed of turtles moving at level 1
    public int spdt1 = 4;

    //the speed of turtles moving at level 2
    public int spdt2 = 5;

    //the speed of turtles moving at level 3 & 4
    public int spdt3 = 7;

    //the move speed of Bowser
    public int bowspd = 5;

    //the speed of mushroom moving at level 1
    public int spd1 = 5;

    //the speed of mushroom moving at level 2
    public int spd2 = 7;

    //the speed of mushroom moving at level 3 & 4
    public int spd3 = 8;

    //the vertical moving speed of mario
    public int speed = 10;

    //the jump speed of mario
    public int jump = 7;

    //the falling speed of mario
    public int fall = 8;

    //the moving speed of coin
    public int coinspeed = 7;

    //to draw the background of mario game
    public WorldImage bg = 
            new OverlayImages(new FromFileImage(new Posn(width / 2, 
                    height / 2), 
                    "sky.png"), 
                    new FromFileImage(new Posn(width / 2, 
                            height - (ground / 2)), 
                            "ground2.png")).overlayImages(
                            new FromFileImage(new CartPt(450, 80), 
                                    "cloud.png")).overlayImages(
                                    new FromFileImage(new CartPt(250, 100), 
                                            "cloud.png"));
}

//To represent a location (x,y) in graphics coordinates
class CartPt extends Posn implements MarioConstants {

    // the standard constructor - invokes the one in the super class
    CartPt(int x, int y) {
        super(x, y);
    }


}

//to represent Mario class
public class Mario implements MarioConstants {
    int h;          //height of mario image
    int w;          //width of mario image
    int x;          //the x location of mario
    int y;          // the y location of mario
    int lives;      //to count the lives of mario
    int dist;       //the distance mario jump through





    //Constructor
    Mario(int x, int y) {
        this.h = 24;
        this.w = 20;
        this.x = x;
        this.y = y;
        this.lives = 5;
        this.dist = 0;

    }
    //to represent the original location of my plane
    CartPt marioLoc() {
        return new CartPt(this.x, this.y);
    }

    //to represent the image of Mario
    WorldImage marioImage() {
        return new FromFileImage(this.marioLoc(), "mariorun.png");
    }

    //to move mario by press the given key
    void move(String s) {
        if (s.equals("up") &&
                this.y >= height - ground - this.h / 2) {
            this.dist = 1;
            this.jumpMove();


        }
        if (this.y == 200 &&
                s.equals("down")) {
            this.dist = 4 * h;
        }
        if (s.equals("left") &&
                this.x > 0) {
            this.x = this.x - speed;
        }
        if (s.equals("right") &&
                this.x < width) {
            this.x = this.x + speed;
        }


    }
    //to do the jump moving
    void jumpMove() {

        if (this.dist < 4 * h &&  //the maximum height that mario can jump
                this.dist > 0)  { //the counter of distance mario jumped
            this.y = this.y - jump;   //mario is on his way up
            this.dist = this.dist + jump; // to add up the distance
        }
        if (this.dist >= 4 * h) { 
            this.y = this.y + fall; //mario is falling
            this.dist = this.dist + fall;  // to add up the distance
        }
        if (this.dist >= 8 * h) {
            this.y = height - ground - this.h / 2;   //mario lands
            this.dist = 0;  //reset the dist, ready for next jump
        }


    }


    //to relocate mario to the given CartPt
    public void relocate(CartPt p) {
        this.x = p.x;
        this.y = p.y;
        this.dist = 0;

    }



}
