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
public abstract class Obstacles implements MarioConstants {
    
    CartPt loc;  //location of this obstacle
    int w;       //width of this obstacle
    int h;       //the height of this obstacle
    String img;  //the name of image of this obstacle
    int sp;   //the speed this obstacles can move
    int num;   //to mark the obstacles
    Obstacles(CartPt loc) {
        this.loc = loc;
        this.w = 20;
        this.h = 26;
        this.sp = 0;
    }
    
    //to build a new Obstacles at the right most side of canvas
    
    public Obstacles newObs() {
        int i = rand.nextInt(3) + 1;
        if (i == 1) { // 1/3 chance that new obstacle is mushroom
            return new Mushroom(new CartPt(width, height - ground - 13));
        }
        if (i == 2) { // 1/3 chance that new obstacle is tube
            return new Tube(new CartPt(width, height - ground - 20));
        }
        if (i == 3) { // 1/3 chance that new obstacle is turtle
            return new Turtle(new CartPt(width, height - ground - 13));
            
        }
        else {
            return this;
        }
    }
    //to make this obstacle move faster
    public abstract void level2();
    //to make this obstacle move faster
    public abstract void level3(); 
 
    //to move this obstacle
    public abstract void move();
    //to represent the Image of this Obstacles
    public WorldImage makeImage() {
        return new FromFileImage(this.loc, this.img);
    }
    
    //is this obstacle collide the given mario?
    public abstract boolean collide(CartPt that);
    
    //does the given mario step onto this obstacles
    public boolean stepOn(Mario mario) {
        if (Math.abs(this.loc.x - mario.x) < this.w / 2 + 3) {
            return this.loc.y - mario.y < (this.h / 2 + mario.h / 2);
        }
        else {
            return false;
        }
    }
    
    public abstract void bossComing();
}


