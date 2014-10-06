/*
assignment 8
pair 2
Chao Fang
bursty
Amanda Fode
afode11
 */
import javalib.worldimages.FromFileImage;
import javalib.worldimages.WorldImage;

//Bowser Class
public class Bowser extends Obstacles implements MarioConstants {


    Bowser(CartPt loc) {
        super(loc);
        this.h = 65;
        this.w = 40;
        this.img = "";
        this.sp = 0;
        this.num = 4;
        

    }

    //generate moving Bowser
    public void move() {


        this.loc.x = this.loc.x + this.sp;

    }
    
    public void level2() { };
    
    public void level3() { };

    public void bossComing() {
        if (this.loc.x >= width) {
            this.sp = 0 - bowspd;

        }
        if (this.loc.x < 10) {
            this.sp = bowspd;
        }
    }
    //
    public boolean collide(CartPt that) {
        int xdist = Math.abs(this.loc.x - that.x);
        int ydist = Math.abs(this.loc.y - that.y);
        if (xdist < this.w / 2 + 10) {
            return ydist < this.h / 2 + 12;
        }
        if (ydist < this.h / 2 + 12) {
            return xdist < this.w / 2 + 10;
        }
        else {
            return false;
        }
    }

    //to draw the Image of Bowser
    public WorldImage makeImage() {
        if (this.sp < 0) {
            return new FromFileImage(this.loc, "bowser3.png");
        }
        else {
            return new FromFileImage(this.loc, "bowser4.png");
        }
       
    }
}